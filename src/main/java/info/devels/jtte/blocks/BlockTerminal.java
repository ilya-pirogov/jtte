package info.devels.jtte.blocks;

import cofh.api.core.IInitializer;
import cofh.core.block.BlockCoFHBase;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import info.devels.jtte.JTTE;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import java.util.ArrayList;

public class BlockTerminal extends Block implements IInitializer {
    public static ItemStack blockTerminal;

    public BlockTerminal() {
        super(Material.iron);
        setHardness(5.0F);
        setResistance(10.0F);
        setStepSound(soundTypeMetal);
        setCreativeTab(JTTE.tab);
        setBlockName("jtte.terminal");
    }

//    @Override
//    public ArrayList<ItemStack> dismantleBlock(EntityPlayer entityPlayer, NBTTagCompound nbtTagCompound, World world, int i, int i1, int i2, boolean b, boolean b1) {
//        return null;
//    }

    @Override
    public boolean preInit() {
        GameRegistry.registerBlock(this, ItemBlockTerminal.class, "Terminal");
        blockTerminal = new ItemStack(this, 1, 0);
        return true;
    }

    @Override
    public boolean initialize() {
        return true;
    }

    @Override
    public boolean postInit() {
        return true;
    }

//    @Override
//    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
//        return null;
//    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iIconRegister) {
        super.registerBlockIcons(iIconRegister);
    }
}
