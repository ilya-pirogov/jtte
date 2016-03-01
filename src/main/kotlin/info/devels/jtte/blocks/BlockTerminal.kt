package info.devels.jtte.blocks

import cofh.api.core.IInitializer
import cpw.mods.fml.common.registry.GameRegistry
import cpw.mods.fml.relauncher.Side
import cpw.mods.fml.relauncher.SideOnly
import info.devels.api.extentions.onServer
import info.devels.api.extentions.transferAtBlock
import info.devels.api.extentions.transferToDimension
import info.devels.jtte.JTTE
import net.minecraft.block.Block
import net.minecraft.block.material.Material
import net.minecraft.client.renderer.texture.IIconRegister
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.server.MinecraftServer
import net.minecraft.world.World


class BlockTerminal : Block(Material.iron), IInitializer {
    init {
        setHardness(5.0f)
        setResistance(10.0f)
        setStepSound(Block.soundTypeMetal)
        setCreativeTab(JTTE.tab)
        setBlockName("jtte.terminal")
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

    override fun onBlockClicked(world: World, x: Int, y: Int, z: Int, player: EntityPlayer) {
        super.onBlockClicked(world, x, y, z, player)

        world.onServer {
            if (world.provider.dimensionId != 0) {
                player.transferToDimension(0, MinecraftServer.getServer().configurationManager)
            }

            player.transferAtBlock(BlockBeacon.teleportPosition)
        }

    }

    companion object {
        lateinit var blockTerminal: ItemStack
    }
}
