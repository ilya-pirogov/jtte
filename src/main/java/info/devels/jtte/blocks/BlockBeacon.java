package info.devels.jtte.blocks;

import cofh.api.block.IBlockInfo;
import cofh.api.core.IInitializer;
import cofh.api.tileentity.ITileInfo;
import cofh.core.block.BlockCoFHBase;
import cofh.lib.util.helpers.ItemHelper;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import info.devels.jtte.JTTE;
import info.devels.jtte.entities.TileEntityBeacon;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.ArrayList;
import java.util.List;


public class BlockBeacon extends Block implements ITileEntityProvider, IBlockInfo, IInitializer {
    public static ItemStack blockBeacon;

    public BlockBeacon() {
        super(Material.rock);
        setHardness(5.0F);
        setResistance(100F);
        setStepSound(soundTypeStone);
        setCreativeTab(JTTE.tab);
        setBlockTextureName("obsidian");
        setBlockName("jtte.beacon");

        setBlockBounds(0F, 0F, 0F, 1F, 1.8F, 1F);
    }

    @Override
    public int getLightValue(IBlockAccess iBlockAccess, int i, int i1, int i2) {
        return 15;
    }

    @Override
    public boolean preInit() {
        GameRegistry.registerBlock(this, ItemBlockBeacon.class, "Beacon");
        blockBeacon = new ItemStack(this, 1, 0);
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

    @Override
    public TileEntity createNewTileEntity(World world, int metadata) {
        return new TileEntityBeacon();
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public int getRenderType() {
        return -1;
    }

    @Override
    public void getBlockInfo(IBlockAccess world, int x, int y, int z, ForgeDirection dir, EntityPlayer player, List<IChatComponent> list, boolean b) {
        TileEntity tile = world.getTileEntity(x, y, z);
        if(tile instanceof ITileInfo) {
            ((ITileInfo)tile).getTileInfo(list, dir, player, b);
        }
    }
}
