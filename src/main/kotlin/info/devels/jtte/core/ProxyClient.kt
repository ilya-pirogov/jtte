package info.devels.jtte.core

import cpw.mods.fml.client.registry.ClientRegistry
import cpw.mods.fml.common.eventhandler.SubscribeEvent
import cpw.mods.fml.common.gameevent.InputEvent
import cpw.mods.fml.common.gameevent.TickEvent
import info.devels.jtte.blocks.BlockBeacon
import info.devels.jtte.blocks.BlockTerminal
import info.devels.jtte.entities.TileEntityBeacon
import info.devels.jtte.entities.TileEntityTerminal
import info.devels.jtte.items.itemClock
import info.devels.jtte.models.BeaconModel
import info.devels.jtte.models.TerminalModel
import info.devels.jtte.render.*
import net.minecraft.util.ResourceLocation
import net.minecraftforge.client.MinecraftForgeClient
import org.lwjgl.input.Keyboard


class ProxyClient : Proxy() {
    private var msLastTick: Long = 0
    private val beaconRenderer = ItemBeaconRenderer()
    private val terminalRenderer = ItemTerminalRenderer()
    private val clockRenderer = ItemClockRenderer()

    override fun registerRenderInformation() {
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBeacon::class.java, TileEntityBeaconRenderer(
                BeaconModel(), ResourceLocation(modId, String.format("textures/entity/%s.png", "beacon")))
        )

        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTerminal::class.java, TileEntityTerminalRenderer(
                TerminalModel(), ResourceLocation(modId, String.format("textures/entity/%s.png", "terminal")))
        )

        MinecraftForgeClient.registerItemRenderer(BlockBeacon.blockBeacon.item, beaconRenderer)
        MinecraftForgeClient.registerItemRenderer(BlockTerminal.blockTerminal.item, terminalRenderer)
        MinecraftForgeClient.registerItemRenderer(itemClock, clockRenderer)
    }

    @Suppress("unused", "UNUSED_PARAMETER")
    @SubscribeEvent
    fun onClientTick(event: TickEvent.ClientTickEvent) {
        msLastTick = System.nanoTime()
        beaconRenderer.tick()
    }

    @Suppress("unused", "UNUSED_PARAMETER")
    @SubscribeEvent
    fun onRenderTick(event: TickEvent.RenderTickEvent) {
        beaconRenderer.tickOffset = (System.nanoTime() - msLastTick) / 50000000f
    }

    @Suppress("unused", "UNUSED_PARAMETER")
    @SubscribeEvent
    fun onKeyInput(event: InputEvent.KeyInputEvent) {
        val step = 0.05
        if (Keyboard.isKeyDown(Keyboard.KEY_LCONTROL)) {
            if (Keyboard.isKeyDown(Keyboard.KEY_NUMPAD7)) dbgRotX += 90
            if (Keyboard.isKeyDown(Keyboard.KEY_NUMPAD1)) dbgRotX -= 90

            if (Keyboard.isKeyDown(Keyboard.KEY_NUMPAD8)) dbgRotY += 90
            if (Keyboard.isKeyDown(Keyboard.KEY_NUMPAD2)) dbgRotY -= 90

            if (Keyboard.isKeyDown(Keyboard.KEY_NUMPAD9)) dbgRotZ += 90
            if (Keyboard.isKeyDown(Keyboard.KEY_NUMPAD3)) dbgRotZ -= 90

            if (Keyboard.isKeyDown(Keyboard.KEY_NUMPAD4)) dbgScale -= 0.005f
            if (Keyboard.isKeyDown(Keyboard.KEY_NUMPAD6)) dbgScale += 0.005f
        } else {
            if (Keyboard.isKeyDown(Keyboard.KEY_NUMPAD7)) dbgTrX += step
            if (Keyboard.isKeyDown(Keyboard.KEY_NUMPAD1)) dbgTrX -= step

            if (Keyboard.isKeyDown(Keyboard.KEY_NUMPAD8)) dbgTrY += step
            if (Keyboard.isKeyDown(Keyboard.KEY_NUMPAD2)) dbgTrY -= step

            if (Keyboard.isKeyDown(Keyboard.KEY_NUMPAD9)) dbgTrZ += step
            if (Keyboard.isKeyDown(Keyboard.KEY_NUMPAD3)) dbgTrZ -= step

            if (Keyboard.isKeyDown(Keyboard.KEY_NUMPAD4)) dbgScale -= 0.1f
            if (Keyboard.isKeyDown(Keyboard.KEY_NUMPAD6)) dbgScale += 0.1f
        }
    }
}
