package info.devels.jtte.items

import cofh.api.core.IInitializer
import cpw.mods.fml.common.registry.GameRegistry
import info.devels.api.extentions.onServer
import info.devels.api.extentions.transferToBlock
import info.devels.api.extentions.transferToDimension
import info.devels.jtte.JTTE
import info.devels.jtte.core.clockReturnPoint
import info.devels.jtte.core.terminalDimension
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.EnumAction
import net.minecraft.item.ItemFood
import net.minecraft.item.ItemStack
import net.minecraft.server.MinecraftServer
import net.minecraft.world.World


class ItemClock: ItemFood, IInitializer {
    constructor() : super(0, 0F, false) {
        maxDamage = 0
        maxStackSize = 1
        creativeTab = JTTE.tab
        unlocalizedName = "clock"
        setAlwaysEdible()
    }

    override fun preInit(): Boolean {
        GameRegistry.registerItem(this, "clock", JTTE.instance.modId)
        return true
    }

    override fun postInit(): Boolean {
        return true
    }

    override fun initialize(): Boolean {
        return true
    }

    override fun onEaten(stack: ItemStack, world: World, player: EntityPlayer): ItemStack {
        world.onServer {
            if (world.provider.dimensionId != 0) {
                player.transferToDimension(terminalDimension, MinecraftServer.getServer().configurationManager)
            }

            player.transferToBlock(clockReturnPoint)
        }

        return stack
    }

    override fun getItemUseAction(stack: ItemStack): EnumAction {
        return EnumAction.bow
    }

    override fun onItemRightClick(stack: ItemStack, world: World, player: EntityPlayer): ItemStack {
        player.setItemInUse(stack, 32);
        return stack
    }
}