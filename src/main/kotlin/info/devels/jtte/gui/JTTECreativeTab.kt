package info.devels.jtte.gui

import info.devels.jtte.blocks.BlockBeacon
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.Item


class JTTECreativeTab : CreativeTabs("jtte") {
    override fun getTabIconItem(): Item {
        return BlockBeacon.blockBeacon.item
    }
}
