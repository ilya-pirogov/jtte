package info.devels.jtte.blocks

import cofh.core.block.BlockCoFHBase
import cpw.mods.fml.common.registry.GameRegistry
import info.devels.jtte.JTTE
import info.devels.jtte.entities.TileEntityBeacon
import net.minecraft.block.Block
import net.minecraft.block.material.Material
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.tileentity.TileEntity
import net.minecraft.world.IBlockAccess
import net.minecraft.world.World
import java.util.*


class BlockBeacon : BlockCoFHBase(Material.iron) {
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

    override fun dismantleBlock(p0: EntityPlayer?, p1: NBTTagCompound?, p2: World?, p3: Int, p4: Int, p5: Int, p6: Boolean, p7: Boolean): ArrayList<ItemStack> {
        return arrayListOf(blockBeacon);
    }

    override fun getItemDropped(metadata: Int, seed: Random, asd: Int): Item? {
        return blockBeacon.item;
    }

    companion object {
        lateinit var blockBeacon: ItemStack
    }
}
