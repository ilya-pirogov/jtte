package info.devels.jtte.blocks

import cofh.api.block.IBlockInfo
import cofh.api.core.IInitializer
import cofh.api.tileentity.ITileInfo
import cpw.mods.fml.common.registry.GameRegistry
import info.devels.jtte.JTTE
import info.devels.jtte.entities.TileEntityBeacon
import net.minecraft.block.Block
import net.minecraft.block.ITileEntityProvider
import net.minecraft.block.material.Material
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.IChatComponent
import net.minecraft.world.IBlockAccess
import net.minecraft.world.World
import net.minecraftforge.common.util.ForgeDirection


class BlockBeacon : Block(Material.rock), ITileEntityProvider, IBlockInfo, IInitializer {
    init {
        setHardness(5.0f)
        setResistance(100f)
        setStepSound(Block.soundTypeStone)
        setCreativeTab(JTTE.tab)
        setBlockTextureName("obsidian")
        setBlockName("jtte.beacon")

        setBlockBounds(0f, 0f, 0f, 1f, 1.8f, 1f)
    }

    override fun getLightValue(iBlockAccess: IBlockAccess, i: Int, i1: Int, i2: Int) = 15

    override fun preInit(): Boolean {
        GameRegistry.registerBlock(this, ItemBlockBeacon::class.java, "Beacon")
        blockBeacon = ItemStack(this, 1, 0)
        return true
    }

    override fun initialize(): Boolean {
        return true
    }

    override fun postInit(): Boolean {
        return true
    }

    override fun createNewTileEntity(world: World, metadata: Int): TileEntity {
        return TileEntityBeacon()
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

    override fun getBlockInfo(world: IBlockAccess, x: Int, y: Int, z: Int, dir: ForgeDirection, player: EntityPlayer, list: List<IChatComponent>, b: Boolean) {
        val tile = world.getTileEntity(x, y, z)
        if (tile is ITileInfo) {
            tile.getTileInfo(list, dir, player, b)
        }
    }

    companion object {
        lateinit var blockBeacon: ItemStack
    }
}
