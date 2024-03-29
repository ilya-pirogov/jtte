package info.devels.jtte

import cofh.core.CoFHProps
import cofh.core.util.ConfigHandler
import cofh.lib.util.position.BlockPosition
import cofh.mod.BaseMod
import cpw.mods.fml.common.Mod
import cpw.mods.fml.common.SidedProxy
import cpw.mods.fml.common.event.FMLInitializationEvent
import cpw.mods.fml.common.event.FMLPreInitializationEvent
import cpw.mods.fml.common.FMLCommonHandler
import cpw.mods.fml.common.event.*
import cpw.mods.fml.relauncher.Side
import info.devels.api.extentions.blockPosition
import info.devels.csmdashboard.commands.JtteCommand
import info.devels.jtte.blocks.BlockBeacon
import info.devels.jtte.blocks.blocksInitialize
import info.devels.jtte.blocks.blocksPostInit
import info.devels.jtte.blocks.blocksPreInit
import info.devels.jtte.core.modId as jtteModId
import info.devels.jtte.core.modName as jtteModName
import info.devels.jtte.core.version as jtteModVersion
import info.devels.jtte.core.*
import info.devels.jtte.curse.Handlers
import info.devels.jtte.gui.JTTECreativeTab
import info.devels.jtte.items.itemsInitialize
import info.devels.jtte.items.itemsPostInit
import info.devels.jtte.items.itemsPreInit
import info.devels.jtte.utils.FastBitSet
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.server.MinecraftServer
import net.minecraftforge.common.ForgeChunkManager
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.common.config.Configuration
import org.apache.logging.log4j.LogManager
import java.io.File


@Mod(modid = jtteModId, name = jtteModName, version = jtteModVersion, dependencies = dependencies)
class JTTE : BaseMod {
    constructor() {
        instance = this
    }

    @Suppress("unused", "UNUSED_PARAMETER")
    @Mod.EventHandler
    fun preInit(event: FMLPreInitializationEvent) {
        config.configuration = Configuration(File(CoFHProps.configDir, "/jtte.cfg"), true)

        tab = JTTECreativeTab()

        configOptions()

        blocksPreInit()
        itemsPreInit()

        val bs = FastBitSet(256)
        bs[127] = true
        println(bs[127])
        println(bs.getEnabledBits())
    }

    @Suppress("unused", "UNUSED_PARAMETER")
    @Mod.EventHandler
    fun initialize(event: FMLInitializationEvent) {
        blocksInitialize()
        itemsInitialize()

        MinecraftForge.EVENT_BUS.register(proxy)
        MinecraftForge.EVENT_BUS.register(curseHandlers)
        FMLCommonHandler.instance().bus().register(proxy)
        FMLCommonHandler.instance().bus().register(curseHandlers)
    }

    @Suppress("unused", "UNUSED_PARAMETER")
    @Mod.EventHandler
    fun postInit(event:FMLPostInitializationEvent) {
        blocksPostInit()
        itemsPostInit()

        proxy.registerEntities()
        proxy.registerRenderInformation()
    }

    @Suppress("unused")
    @Mod.EventHandler
    fun loadComplete(event:FMLLoadCompleteEvent) {
        config.cleanUp(false, true)
        log.info("Load Complete")
    }

    @Suppress("unused")
    @Mod.EventHandler
    fun serverLoad(event: FMLServerStartingEvent) {
        event.registerServerCommand(JtteCommand())
    }

    private fun configOptions() {
        var category:String

        category = "Animations"
        ticksCube = config.get(category, "TicksCube", ticksCube,
                "Rotation Ticks of Cube")

        ticksBottomPieces = config.get(category, "TicksBottomPieces", ticksBottomPieces,
                "Rotation Ticks of Bottom Pieces")

        ticksMiddlePieces = config.get(category, "TicksMiddlePieces", ticksMiddlePieces,
                "Rotation Ticks of Middle Pieces")

        ticksTopPieces = config.get(category, "TicksTopPieces", ticksTopPieces,
                "Rotation Ticks of Top Pieces")

        category = "Terminal"
        clockReturnPoint = config.get(category, "ClockReturnPoint", clockReturnPoint.toConfig()).toBlockPosition()

        terminalDimension = config.get(category, "Dimension", terminalDimension)

        category = "Curse"
        curseFreeZones = config.configuration.get(category, "FreeZones", curseFreeZones.map { it.toConfig() }.toTypedArray()).stringList.map {
            it.toArea()
        }.toTypedArray()

        curseDimension = config.get(category, "Dimension", curseDimension)

        curseRadius = config.get(category, "Radius", curseRadius)

        dayLength = config.get(category, "DayLength", dayLength)

        curseStartTime = config.get(category, "CurseStartTime", curseStartTime)

        curseEndTime = config.get(category, "CurseEndTime", curseEndTime)

        cursedBlocksWhitelist = config.configuration.get(category, "CursedBlocksWhitelist", cursedBlocksWhitelist).stringList

    }

    override fun getModName(): String? {
        return jtteModName
    }

    override fun getModVersion(): String? {
        return jtteModVersion
    }

    override fun getModId(): String? {
        return jtteModId
    }

    companion object {
        val log = LogManager.getLogger(jtteModId)
        val config = ConfigHandler(jtteModVersion)
        val curseHandlers = Handlers()

        lateinit var instance: JTTE

        lateinit var tab: CreativeTabs

//        lateinit var spawnPosition: BlockPosition

        @SidedProxy(clientSide = "info.devels.jtte.core.ProxyClient", serverSide = "info.devels.jtte.core.Proxy")
        lateinit var proxy: Proxy
    }
}