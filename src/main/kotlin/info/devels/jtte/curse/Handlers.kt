package info.devels.jtte.curse

import cofh.lib.util.position.ChunkCoord
import cpw.mods.fml.common.eventhandler.EventPriority
import cpw.mods.fml.common.eventhandler.SubscribeEvent
import cpw.mods.fml.common.gameevent.TickEvent
import cpw.mods.fml.relauncher.Side
import cpw.mods.fml.relauncher.SideOnly
import info.devels.api.extentions.position
import info.devels.jtte.core.curseDimension
import info.devels.jtte.core.curseRadius
import net.minecraft.block.Block
import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.Tessellator
import net.minecraft.client.renderer.WorldRenderer
import net.minecraft.init.Blocks
import net.minecraft.util.AxisAlignedBB
import net.minecraft.util.IIcon
import net.minecraft.util.ResourceLocation
import net.minecraftforge.client.event.DrawBlockHighlightEvent
import net.minecraftforge.client.event.RenderBlockOverlayEvent
import net.minecraftforge.client.event.RenderWorldEvent
import net.minecraftforge.client.event.RenderWorldLastEvent
import net.minecraftforge.event.world.ChunkDataEvent
import org.lwjgl.opengl.GL11


class Handlers {
    var playerTick = 0
    var curseMap = CurseMap();

    @SubscribeEvent
    fun onPlayerTick(event: TickEvent.PlayerTickEvent) {
        if (event.side != Side.SERVER || event.player.dimension != curseDimension) {
            return
        }

        if (playerTick++ % 61 == 0) {
            curseMap.handleMove(event.player)
//            event.player.worldObj.markBlockRangeForRenderUpdate(
//                    event.player.posX.toInt() - curseRadius,
//                    0,
//                    event.player.posZ.toInt() - curseRadius,
//                    event.player.posX.toInt() + curseRadius,
//                    256,
//                    event.player.posZ.toInt() + curseRadius
//            )
//            println("%s %s %s".format(event.player.playerCoordinates.posX % 16, event.player.playerCoordinates.posZ % 16, event.player.dimension))
        }
    }

    @SubscribeEvent
    fun onChunkLoad(event: ChunkDataEvent.Load) {
        val a = event.data.getIntArray("plannedCurseMap")
//        println("load the chunk %s: %s".format(event.chunk.position(), a.dump()))
        curseMap.planned.fromRaw(event.chunk.position(), event.data.getIntArray("plannedCurseMap"))
        curseMap.affected.fromRaw(event.chunk.position(), event.data.getIntArray("affectedCurseMap"))
    }

    @SubscribeEvent
    fun onChunkSave(event: ChunkDataEvent.Save) {
        val a = curseMap.planned.getRaw(event.chunk.position())
        if (!(curseMap.planned[event.chunk.position()] ?: CurseChunkMap()).bitSet.isEmpty) {
            println("save the chunk %s: %s".format(event.chunk.position(), a.dump()))
        }
        event.data.setIntArray("plannedCurseMap", curseMap.planned.getRaw(event.chunk.position()))
        event.data.setIntArray("affectedCurseMap", curseMap.affected.getRaw(event.chunk.position()))
    }

    @SideOnly(Side.CLIENT)
    fun onRenderPost2(event: RenderWorldLastEvent) {
//        event.renderer.d
//        GL11.glEnable(GL11.GL_BLEND);
//        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
//        GL11.glColor4f(1.0F, 0.0F, 0.0F, 1F);
//        GL11.glLineWidth(2.0F);
//        GL11.glDisable(GL11.GL_TEXTURE_2D);
//        GL11.glDepthMask(false);
//        GL11.glDisable(GL11.GL_DEPTH_TEST);

//        d3 = 0.25001952052116394
//        d4 = 0.28123047947883606
//        d5 = 0.12503905594348907
//        d6 = 0.18746094405651093
//        d7 = 0.28123047947883606
//        d8 = 0.25001952052116394
//        d9 = 0.12503905594348907
//        d10 = 0.18746094405651093
//        d11 = 16.0
//        d12 = 17.0
//        d13 = 67.0
//        d14 = 16.0
//        d15 = 17.0

//        tessellator.addVertexWithUV(d12, d13, d15, d4, d6)
//        tessellator.addVertexWithUV(d12, d13, d14, d7, d9)
//        tessellator.addVertexWithUV(d11, d13, d14, d3, d5)
//        tessellator.addVertexWithUV(d11, d13, d15, d8, d10)

//        println(event.pass)
//        if (event.pass != 1) {
//            return
//        }

        GL11.glPushMatrix()

        Tessellator.instance.startDrawingQuads()
        Tessellator.instance.setTranslation((-event.renderer.posX).toDouble(), (-event.renderer.posY).toDouble(), (-event.renderer.posZ).toDouble())

        for (z in (event.renderer.posZ)..(event.renderer.posZ + 15)) {
            for (x in (event.renderer.posX)..(event.renderer.posX + 15)) {
                for (i in  0..256) {
                    val y = 256 - i
                    if (!Minecraft.getMinecraft().theWorld.isAirBlock(x, y, z)) {
                        if (x > 0 && x < 8 && z > 0 && z < 8) {
                            println("hit: %d %d %d".format(x, y + 2, z))

//                            val bb = AxisAlignedBB.getBoundingBox(0.0, 0.0, 0.0, 1.0, 1.0, 1.0);
//                            drawOutlinedBoundingBox(bb);

//                            val ch = Minecraft.getMinecraft().theWorld.getChunkFromBlockCoords(x, z)
//                            val list = event.renderer.getGLCallListForPass(0)
                            event.renderBlocks.renderFaceYPos(Blocks.diamond_block, x.toDouble(), y.toDouble() + 0.1, z.toDouble(), event.renderBlocks.getBlockIcon(Blocks.diamond_block))
//                            event.renderBlocks.renderStandardBlockWithColorMultiplier(Blocks.diamond_block, x, y, z, 0.5f, 0f, 0f)
                            break
                        }
                    }
                }
            }
        }

        Tessellator.instance.draw()

        GL11.glPopMatrix()

//        event.renderer.markDirty()

//        val x = 15
//        val y = 68
//        val z = 15
//        val player = Minecraft.getMinecraft().thePlayer
//        val bb = AxisAlignedBB.getBoundingBox(x - player.posX, y - player.posY, z - player.posZ, x + 1 - player.posX, y + 1 - player.posY, z + 1 - player.posZ);
//
//
////        drawOutlinedBoundingBox(bb);
////        event.context.renderStandardBlockWithColorMultiplier(Blocks.grass, x, y + 1, z, 0.4f, 0f, 0f)
//
//        GL11.glDepthMask(true);
//        GL11.glEnable(GL11.GL_TEXTURE_2D);
//        GL11.glDisable(GL11.GL_BLEND);
//        GL11.glEnable(GL11.GL_DEPTH_TEST);
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    fun onRenderPost(event: RenderWorldEvent.Post) {
//        event.renderer.d
//        GL11.glEnable(GL11.GL_BLEND);
//        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
//        GL11.glColor4f(1.0F, 0.0F, 0.0F, 1F);
//        GL11.glLineWidth(2.0F);
//        GL11.glDisable(GL11.GL_TEXTURE_2D);
//        GL11.glDepthMask(false);
//        GL11.glDisable(GL11.GL_DEPTH_TEST);

//        d3 = 0.25001952052116394
//        d4 = 0.28123047947883606
//        d5 = 0.12503905594348907
//        d6 = 0.18746094405651093
//        d7 = 0.28123047947883606
//        d8 = 0.25001952052116394
//        d9 = 0.12503905594348907
//        d10 = 0.18746094405651093
//        d11 = 16.0
//        d12 = 17.0
//        d13 = 67.0
//        d14 = 16.0
//        d15 = 17.0

//        tessellator.addVertexWithUV(d12, d13, d15, d4, d6)
//        tessellator.addVertexWithUV(d12, d13, d14, d7, d9)
//        tessellator.addVertexWithUV(d11, d13, d14, d3, d5)
//        tessellator.addVertexWithUV(d11, d13, d15, d8, d10)

//        println(event.pass)
        if (event.pass != 1) {
            return
        }

        GL11.glPushMatrix()

        Tessellator.instance.startDrawingQuads()
        Tessellator.instance.setTranslation((-event.renderer.posX).toDouble(), (-event.renderer.posY).toDouble(), (-event.renderer.posZ).toDouble())

        for (z in (event.renderer.posZ)..(event.renderer.posZ + 15)) {
            for (x in (event.renderer.posX)..(event.renderer.posX + 15)) {
                for (i in  0..256) {
                    val y = 256 - i
                    if (!Minecraft.getMinecraft().theWorld.isAirBlock(x, y, z)) {
                        if (x > 0 && x < 8 && z > 0 && z < 8) {
                            println("hit: %d %d %d".format(x, y + 2, z))

//                            val bb = AxisAlignedBB.getBoundingBox(0.0, 0.0, 0.0, 1.0, 1.0, 1.0);
//                            drawOutlinedBoundingBox(bb);

//                            val ch = Minecraft.getMinecraft().theWorld.getChunkFromBlockCoords(x, z)
//                            val list = event.renderer.getGLCallListForPass(0)
                            event.renderBlocks.renderFaceYPos(Blocks.diamond_block, x.toDouble(), y.toDouble() + 0.1, z.toDouble(), event.renderBlocks.getBlockIcon(Blocks.diamond_block))
//                            event.renderBlocks.renderStandardBlockWithColorMultiplier(Blocks.diamond_block, x, y, z, 0.5f, 0f, 0f)
                            break
                        }
                    }
                }
            }
        }

        Tessellator.instance.draw()

        GL11.glPopMatrix()

//        event.renderer.markDirty()

//        val x = 15
//        val y = 68
//        val z = 15
//        val player = Minecraft.getMinecraft().thePlayer
//        val bb = AxisAlignedBB.getBoundingBox(x - player.posX, y - player.posY, z - player.posZ, x + 1 - player.posX, y + 1 - player.posY, z + 1 - player.posZ);
//
//
////        drawOutlinedBoundingBox(bb);
////        event.context.renderStandardBlockWithColorMultiplier(Blocks.grass, x, y + 1, z, 0.4f, 0f, 0f)
//
//        GL11.glDepthMask(true);
//        GL11.glEnable(GL11.GL_TEXTURE_2D);
//        GL11.glDisable(GL11.GL_BLEND);
//        GL11.glEnable(GL11.GL_DEPTH_TEST);
    }

//    @SideOnly(Side.CLIENT)
//    @SubscribeEvent
//    fun asd(event: RenderWorldLastEvent) {
//        val x = 15
//        val y = 68
//        val z = 15
//        val player = Minecraft.getMinecraft().thePlayer
//        val bb = AxisAlignedBB.getBoundingBox(x - player.posX, y - player.posY, z - player.posZ, x + 1 - player.posX, y + 1 - player.posY, z + 1 - player.posZ);
//
//        GL11.glEnable(GL11.GL_BLEND);
//        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
//        GL11.glColor4f(1.0F, 0.0F, 0.0F, 1F);
//        GL11.glLineWidth(2.0F);
//        GL11.glDisable(GL11.GL_TEXTURE_2D);
//        GL11.glDepthMask(false);
//        GL11.glDisable(GL11.GL_DEPTH_TEST);
//
//        drawOutlinedBoundingBox(bb);
////        event.context .renderStandardBlockWithColorMultiplier(Blocks.grass, x, y + 1, z, 0.4f, 0f, 0f)
//
//        GL11.glDepthMask(true);
//        GL11.glEnable(GL11.GL_TEXTURE_2D);
//        GL11.glDisable(GL11.GL_BLEND);
//        GL11.glEnable(GL11.GL_DEPTH_TEST);
//    }
//
//    @SideOnly(Side.CLIENT)
//    @SubscribeEvent
//    fun onRender(event: RenderWorldEvent.Post) {
////        event.renderBlocks.clearOverrideBlockTexture()
////        GL11.glPopMatrix()
//    }

    /**
     * Draws lines for the edges of the bounding box.
     */
    fun drawOutlinedBoundingBox(axisAlignedBB: AxisAlignedBB) {
        //System.out.println("BHGUI drawBox Min:" + (int)axisAlignedBB.minX + "," + (int)axisAlignedBB.minY + "," + (int)axisAlignedBB.minZ + " Max: " + (int)axisAlignedBB.maxX + "," + (int)axisAlignedBB.maxY + "," + (int)axisAlignedBB.maxZ);
        val tessellator = Tessellator.instance
//        tessellator.startDrawing(2)
        tessellator.addVertex(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ)
        tessellator.addVertex(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ)
        tessellator.addVertex(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ)
        tessellator.addVertex(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ)
        tessellator.addVertex(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ)
    }
}