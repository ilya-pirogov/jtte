package info.devels.jtte;

import cofh.CoFHCore;
import cofh.core.CoFHProps;
import cofh.core.util.ConfigHandler;
import cofh.mod.BaseMod;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.*;
import info.devels.jtte.blocks.JTTEBlocks;
import info.devels.jtte.core.JTTEProps;
import info.devels.jtte.core.Proxy;
import info.devels.jtte.gui.JTTECreativeTab;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;


@Mod(modid = JTTE.modId, name = JTTE.modName, version = JTTE.version, dependencies = JTTE.dependencies)
public class JTTE extends BaseMod {
    public static final String modId = "jtte";
    public static final String modName = "Journey to the End";
    public static final String version = "0.1.3";
    public static final String version_max = "0.2.0";
    public static final String dependencies = CoFHCore.version_group;

    public static final String version_group = "required-after:" + modId + "@[" + version + "," + version_max + ");";

    public static final Logger log = LogManager.getLogger(modId);
    public static final ConfigHandler config = new ConfigHandler(version);
    public static CreativeTabs tab;

    @SidedProxy(clientSide = "info.devels.jtte.core.ProxyClient", serverSide = "info.devels.jtte.core.Proxy")
    public static Proxy proxy;

    public JTTE() {
        super();
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        config.setConfiguration(new Configuration(new File(CoFHProps.configDir, "/jtte.cfg"), true));

        tab = new JTTECreativeTab();

        configOptions();

        JTTEBlocks.preInit();
    }

    @Mod.EventHandler
    public void initialize(FMLInitializationEvent event) {
        JTTEBlocks.initialize();

        MinecraftForge.EVENT_BUS.register(proxy);
        FMLCommonHandler.instance().bus().register(proxy);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {

        JTTEBlocks.postInit();

        proxy.registerEntities();
        proxy.registerRenderInformation();
    }

    @Mod.EventHandler
    public void loadComplete(FMLLoadCompleteEvent event) {
        config.cleanUp(false, true);

//        log.info("Load Complete");
    }

    private void configOptions() {
        String category;
        String comment;

        category = "Animations";

        comment = "Rotation Ticks of Cube";
        JTTEProps.ticksCube = config.get(category, "TicksCube", JTTEProps.ticksCube, comment);

        comment = "Rotation Ticks of Bottom Pieces";
        JTTEProps.ticksBottomPieces = config.get(category, "TicksBottomPieces", JTTEProps.ticksBottomPieces, comment);

        comment = "Rotation Ticks of Middle Pieces";
        JTTEProps.ticksMiddlePieces = config.get(category, "TicksMiddlePieces", JTTEProps.ticksMiddlePieces, comment);

        comment = "Rotation Ticks of Top Pieces";
        JTTEProps.ticksTopPieces = config.get(category, "TicksTopPieces", JTTEProps.ticksTopPieces, comment);
    }

    @Override
    public String getModId() {
        return modId;
    }

    @Override
    public String getModName() {
        return modName;
    }

    @Override
    public String getModVersion() {
        return version;
    }
}
