package info.devels.jtte.gui;

import info.devels.jtte.blocks.BlockBeacon;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;


public class JTTECreativeTab extends CreativeTabs {
    public JTTECreativeTab() {
        super("jtte");
    }

    @Override
    public Item getTabIconItem() {
        return BlockBeacon.blockBeacon.getItem();
    }
}
