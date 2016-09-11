package info.devels.jtte.render

import com.mojang.authlib.GameProfile
import com.mojang.authlib.minecraft.MinecraftProfileTexture
import cpw.mods.fml.relauncher.Side
import cpw.mods.fml.relauncher.SideOnly
import info.devels.jtte.entities.TileEntityTerminal
import info.devels.jtte.models.TerminalModel
import net.minecraft.client.Minecraft
import net.minecraft.client.entity.AbstractClientPlayer
import net.minecraft.client.model.ModelSkeletonHead
import net.minecraft.client.renderer.Tessellator
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.ResourceLocation
import net.minecraftforge.common.util.ForgeDirection
import org.lwjgl.opengl.GL11
import org.lwjgl.opengl.GL12


@SideOnly(Side.CLIENT)
class TileEntityTerminalRenderer(protected val model: TerminalModel, protected val texture: ResourceLocation) : TileEntitySpecialRenderer() {
    val obsidianTexture = ResourceLocation("jtte", "textures/blocks/under_construction.png")

    fun renderTileEntityAt(tile: TileEntityTerminal, x: Double, y: Double, z: Double, n: Float) {
        if (tile.state == TileEntityTerminal.State.MIDDLE || tile.state == TileEntityTerminal.State.TOP) {
            return
        }

        GL11.glPushMatrix()
        GL11.glEnable(GL12.GL_RESCALE_NORMAL)
        GL11.glTranslatef(x.toFloat(), y.toFloat(), z.toFloat())

        if (tile.state == TileEntityTerminal.State.BOTTOM) {
            this.bindTexture(texture)

            GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f)
            GL11.glTranslatef(0f, 1.5f, 0f)
            GL11.glScalef(1.0f, -1.0f, -1.0f)


            when (tile.face) {
                ForgeDirection.NORTH -> {
                    GL11.glRotatef(180f, 0f, 1f, 0f)
                    GL11.glTranslatef(-0.5f, 0f, 0.5f)
                }

                ForgeDirection.EAST -> {
                    GL11.glRotatef(-90f, 0f, 1f, 0f)
                    GL11.glTranslatef(-0.5f, 0f, -0.5f)
                }

                ForgeDirection.SOUTH -> {
                    GL11.glRotatef(0f, 0f, 1f, 0f)
                    GL11.glTranslatef(+0.5f, 0f, -0.5f)
                }

                ForgeDirection.WEST -> {
                    GL11.glRotatef(90f, 0f, 1f, 0f)
                    GL11.glTranslatef(+0.5f, 0f, +0.5f)
                }

                ForgeDirection.UNKNOWN -> {}
                ForgeDirection.DOWN -> {}
                ForgeDirection.UP -> {}
            }

            model.screenOffsetX = tile.screenOffsetX.toDouble()
            if (tile.isMoving) {
                model.screenOffsetX += (n + tile.nOffset) / tile.speed
            }

            model.renderAll()

            GL11.glPushMatrix()
            GL11.glRotated(((tile.angleHead + tile.rtHead * n) / Math.PI) * 180, 0.0, 1.0, 0.0)

            val gp = tile.usesBy
            if (gp != null) {
                renderHead(gp)
            }
            GL11.glPopMatrix()

        } else {
            bindTexture(obsidianTexture)
            GL11.glEnable(GL11.GL_BLEND)
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA)

            val t = Tessellator.instance;
            t.startDrawingQuads()

            t.setNormal(0f, 0f, -1f);
            t.addVertexWithUV(0.0, 1.0, 0.0, 1.0, 0.0)
            t.addVertexWithUV(1.0, 1.0, 0.0, 0.0, 0.0)
            t.addVertexWithUV(1.0, 0.0, 0.0, 0.0, 1.0)
            t.addVertexWithUV(0.0, 0.0, 0.0, 1.0, 1.0)

            t.setNormal(1f, 0f, 0f);
            t.addVertexWithUV(1.0, 0.0, 1.0, 0.0, 1.0)
            t.addVertexWithUV(1.0, 0.0, 0.0, 1.0, 1.0)
            t.addVertexWithUV(1.0, 1.0, 0.0, 1.0, 0.0)
            t.addVertexWithUV(1.0, 1.0, 1.0, 0.0, 0.0)

            t.setNormal(0f, 0f, 1f);
            t.addVertexWithUV(1.0, 1.0, 1.0, 1.0, 0.0)
            t.addVertexWithUV(0.0, 1.0, 1.0, 0.0, 0.0)
            t.addVertexWithUV(0.0, 0.0, 1.0, 0.0, 1.0)
            t.addVertexWithUV(1.0, 0.0, 1.0, 1.0, 1.0)

            t.setNormal(-1f, 0f, 0f);
            t.addVertexWithUV(0.0, 0.0, 0.0, 0.0, 1.0)
            t.addVertexWithUV(0.0, 0.0, 1.0, 1.0, 1.0)
            t.addVertexWithUV(0.0, 1.0, 1.0, 1.0, 0.0)
            t.addVertexWithUV(0.0, 1.0, 0.0, 0.0, 0.0)

            t.setNormal(0f, 1f, 0f);
            t.addVertexWithUV(0.0, 1.0, 0.0, 1.0, 1.0)
            t.addVertexWithUV(0.0, 1.0, 1.0, 1.0, 0.0)
            t.addVertexWithUV(1.0, 1.0, 1.0, 0.0, 0.0)
            t.addVertexWithUV(1.0, 1.0, 0.0, 0.0, 1.0)

            t.setNormal(0f, -1f, 0f);
            t.addVertexWithUV(1.0, 0.0, 1.0, 0.0, 0.0)
            t.addVertexWithUV(0.0, 0.0, 1.0, 1.0, 0.0)
            t.addVertexWithUV(0.0, 0.0, 0.0, 1.0, 1.0)
            t.addVertexWithUV(1.0, 0.0, 0.0, 0.0, 1.0)

            t.draw()

            GL11.glDisable(GL11.GL_BLEND)
        }

        GL11.glPopMatrix()
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f)
    }

    fun getSkin(name: String): ResourceLocation {
        var resourcelocation = AbstractClientPlayer.locationStevePng;
        if (name.length > 0)
        {
            resourcelocation = AbstractClientPlayer.getLocationSkin(name);
            AbstractClientPlayer.getDownloadImageSkin(resourcelocation, name);
        }
        return resourcelocation;
    }

    fun renderHead(gameProfile: GameProfile) {

        var resourcelocation = AbstractClientPlayer.locationStevePng

        val minecraft = Minecraft.getMinecraft()
        val map = minecraft.func_152342_ad().func_152788_a(gameProfile)

        if (map.containsKey(MinecraftProfileTexture.Type.SKIN)) {
            resourcelocation = minecraft.func_152342_ad().func_152792_a(map[MinecraftProfileTexture.Type.SKIN] as MinecraftProfileTexture, MinecraftProfileTexture.Type.SKIN)
        } else {
            resourcelocation = getSkin(gameProfile.name)
        }

        this.bindTexture(resourcelocation)

        GL11.glPushMatrix()
        GL11.glDisable(GL11.GL_CULL_FACE)

        val f4 = 0.0625f
        GL11.glEnable(GL12.GL_RESCALE_NORMAL)
        GL11.glScalef(0.80f, 0.80f, 0.80f)
        GL11.glTranslatef(0.0f, -0.35f, 0.0f)
        GL11.glEnable(GL11.GL_ALPHA_TEST)

        headModel.render(null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, f4)
        GL11.glPopMatrix()
    }

//    fun renderTileEntityAt(tile: TileEntityTerminal, x: Double, y: Double, z: Double, n: Float) {
//        GL11.glPushMatrix();
//        GL11.glTranslatef(x.toFloat(), y.toFloat(), z.toFloat());
//
//
//        val tess = Tessellator.instance;
//        this.bindTexture(obsidianTexture);
//        tess.startDrawingQuads(); //Starts drawing
//        tess.addVertexWithUV(0.0, 0.0, 1.0, 1.0, 1.0);
//        tess.addVertexWithUV(1.0, 1.0, 1.0, 1.0, 0.0);
//        tess.addVertexWithUV(0.0, 1.0, 0.0, 0.0, 0.0);
//        tess.addVertexWithUV(0.0, 0.0, 0.0, 0.0, 1.0);
//        tess.draw(); //Draws
//        GL11.glPopMatrix();
//    }

    override fun renderTileEntityAt(tile: TileEntity, x: Double, y: Double, z: Double, n: Float) {
        renderTileEntityAt(tile as TileEntityTerminal, x, y, z, n)
    }

    companion object {
        val headModel = ModelSkeletonHead(0, 0, 64, 32);
    }
}
