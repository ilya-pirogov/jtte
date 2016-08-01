package info.devels.jtte.blocks

import cofh.core.block.BlockCoFHBase
import cpw.mods.fml.common.registry.GameRegistry
import cpw.mods.fml.relauncher.Side
import cpw.mods.fml.relauncher.SideOnly
import info.devels.api.extentions.markForUpdate
import info.devels.api.extentions.onServer
import info.devels.api.extentions.transferAtBlock
import info.devels.api.extentions.transferToDimension
import info.devels.jtte.JTTE
import info.devels.jtte.core.spawnPosition
import info.devels.jtte.core.terminalDimension
import info.devels.jtte.entities.TileEntityBeacon
import info.devels.jtte.entities.TileEntityTerminal
import net.minecraft.block.Block
import net.minecraft.block.material.Material
import net.minecraft.client.renderer.texture.IIconRegister
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.server.MinecraftServer
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.ChatComponentText
import net.minecraft.world.IBlockAccess
import net.minecraft.world.World
import java.util.*


class BlockTerminal : BlockCoFHBase(Material.iron) {
    override fun dismantleBlock(p0: EntityPlayer?, p1: NBTTagCompound?, p2: World?, p3: Int, p4: Int, p5: Int, p6: Boolean, p7: Boolean): ArrayList<ItemStack>? {
        return arrayListOf(BlockTerminal.blockTerminal);
    }

    override fun createNewTileEntity(p_149915_1_: World?, p_149915_2_: Int): TileEntity? {
        return TileEntityTerminal()
    }

    init {
        setHardness(5.0f)
        setResistance(10.0f)
        setStepSound(Block.soundTypeMetal)
        setCreativeTab(JTTE.tab)
        setBlockName("jtte.terminal")
    }

    override fun setBlockBoundsBasedOnState(world: IBlockAccess, x: Int, y: Int, z: Int) {
        val tile = world.getTileEntity(x, y, z)
        if (tile == null || tile !is TileEntityTerminal) {
            return
        }

        when (tile.state) {
            TileEntityTerminal.State.NOT_INITIALIZED -> setBlockBounds(0f, 0f, 0f, 1f, 1f, 1f)
            TileEntityTerminal.State.BOTTOM -> setBlockBounds(0f, 0f, 0f, 1f, 2.5f, 1f)
            TileEntityTerminal.State.MIDDLE -> setBlockBounds(0f, -1f, 0f, 1f, 1.5f, 1f)
            TileEntityTerminal.State.TOP -> setBlockBounds(0f, -2f, 0f, 1f, 0.5f, 1f)
        }
    }

    override fun preInit(): Boolean {
        GameRegistry.registerBlock(this, ItemBlockTerminal::class.java, "Terminal")
        blockTerminal = ItemStack(this, 1, 0)
        return true
    }

    override fun initialize(): Boolean {
        return true
    }

    override fun postInit(): Boolean {
        return true
    }

    @SideOnly(Side.CLIENT)
    override fun registerBlockIcons(iIconRegister: IIconRegister) {
        super.registerBlockIcons(iIconRegister)
    }

    override fun onBlockActivated(world: World, x: Int, y: Int, z: Int, player: EntityPlayer, meta: Int, dx: Float, dy: Float, dz: Float): Boolean {
        world.onServer {
            val tile = world.getTileEntity(x, y, z)
            if (tile == null || tile !is TileEntityTerminal || tile.state == TileEntityTerminal.State.NOT_INITIALIZED) {
                return false
            }

            val bottom = tile.getBottom() ?: return false
            val usesBy = bottom.usesBy

            if (usesBy != null && usesBy.id != player.uniqueID) {
                player.addChatMessage(ChatComponentText("Terminal is used by ${usesBy.name}"))
                return false
            }

            bottom.usesBy = player.gameProfile
            bottom.markDirty()
            bottom.markForUpdate()

            if (world.provider.dimensionId != terminalDimension) {
                player.transferToDimension(terminalDimension, MinecraftServer.getServer().configurationManager)
            }

            if (TileEntityBeacon.beacons.size > 0) {
                player.transferAtBlock(TileEntityBeacon.beacons.keys.elementAt(0))
            } else {
                player.transferAtBlock(spawnPosition)
            }

        }
        return true
    }

    override fun isOpaqueCube(): Boolean {
        return false
    }

    override fun renderAsNormalBlock(): Boolean {
        return false
    }

    override fun getRenderType(): Int {
        return -1
    }


    companion object {
        lateinit var blockTerminal: ItemStack
    }
}
