package info.devels.jtte.items

import cofh.api.core.IInitializer
import cpw.mods.fml.common.registry.GameRegistry
import cpw.mods.fml.relauncher.Side
import cpw.mods.fml.relauncher.SideOnly
import info.devels.api.extentions.onServer
import info.devels.api.extentions.transferToBlock
import info.devels.api.extentions.transferToDimension
import info.devels.jtte.JTTE
import net.minecraft.client.renderer.texture.IIconRegister
import net.minecraft.entity.Entity
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.entity.player.EntityPlayerMP
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.server.MinecraftServer
import net.minecraft.util.IIcon
import net.minecraft.world.World


class ItemClock: Item, IInitializer {
    lateinit var itemIcons: List<IIcon>
    var time = 0

    constructor() {
        maxDamage = 0
        maxStackSize = 1
        creativeTab = JTTE.tab
        unlocalizedName = "clock"
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

    override fun onUpdate(stack: ItemStack, world: World, entity: Entity?, metadata: Int, bool: Boolean) {
        time = Math.floor(((world.worldTime + 6000) % 24000) / 1000.0).toInt()
        if (time > 23) {
            time = 23
        }
        if (time < 0) {
            time = 1
        }
        stack.itemDamage = time
    }

    @SideOnly(Side.CLIENT)
    override fun registerIcons(iconRegister: IIconRegister) {
        for (i in 1..24) {
            itemIcons = listOf(1..24).flatten().map {
                iconRegister.registerIcon("${JTTE.instance.modId}:clock_$it")
            }
        }
        this.itemIcon = this.itemIcons[0]
    }

    override fun getIconFromDamage(damage: Int): IIcon {
        this.itemIcon = this.itemIcons[damage]
        return itemIcon
    }

    override fun onItemRightClick(stack: ItemStack, world: World, player: EntityPlayer): ItemStack {
        world.onServer {
            if (world.provider.dimensionId != 0) {
                player.transferToDimension(0, MinecraftServer.getServer().configurationManager)
            }

            player.transferToBlock(JTTE.spawnPosition)
        }

        return stack
    }
}