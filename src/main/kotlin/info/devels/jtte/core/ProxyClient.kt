package info.devels.jtte.core

import cpw.mods.fml.client.registry.ClientRegistry
import cpw.mods.fml.common.eventhandler.SubscribeEvent
import cpw.mods.fml.common.gameevent.InputEvent
import cpw.mods.fml.common.gameevent.TickEvent
import cpw.mods.fml.relauncher.Side
import info.devels.jtte.blocks.BlockBeacon
import info.devels.jtte.entities.TileEntityBeacon
import info.devels.jtte.models.BeaconModel
import info.devels.jtte.render.ItemBeaconRenderer
import info.devels.jtte.render.TileEntityBeaconRenderer
import net.minecraft.util.ResourceLocation
import net.minecraftforge.client.MinecraftForgeClient
import org.lwjgl.input.Keyboard


class ProxyClient : Proxy() {
    private var msLastTick: Long = 0
    lateinit private var beaconRenderer: ItemBeaconRenderer

    override fun registerRenderInformation() {
        beaconRenderer = ItemBeaconRenderer()

        val renderer = TileEntityBeaconRenderer(
                BeaconModel(), ResourceLocation(modId, String.format("textures/entity/%s.png", "beacon")))

        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBeacon::class.java, renderer)
        MinecraftForgeClient.registerItemRenderer(BlockBeacon.blockBeacon.item, beaconRenderer)
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
//        if (Keyboard.isKeyDown(Keyboard.KEY_LCONTROL)) {
//            if (Keyboard.isKeyDown(Keyboard.KEY_NUMPAD7)) dbgRotX += 5
//            if (Keyboard.isKeyDown(Keyboard.KEY_NUMPAD1)) dbgRotX -= 5
//
//            if (Keyboard.isKeyDown(Keyboard.KEY_NUMPAD8)) dbgRotY += 5
//            if (Keyboard.isKeyDown(Keyboard.KEY_NUMPAD2)) dbgRotY -= 5
//
//            if (Keyboard.isKeyDown(Keyboard.KEY_NUMPAD9)) dbgRotZ += 5
//            if (Keyboard.isKeyDown(Keyboard.KEY_NUMPAD3)) dbgRotZ -= 5
//        } else {
//            if (Keyboard.isKeyDown(Keyboard.KEY_NUMPAD7)) dbgTrX += 0.1f
//            if (Keyboard.isKeyDown(Keyboard.KEY_NUMPAD1)) dbgTrX -= 0.1f
//
//            if (Keyboard.isKeyDown(Keyboard.KEY_NUMPAD8)) dbgTrY += 0.1f
//            if (Keyboard.isKeyDown(Keyboard.KEY_NUMPAD2)) dbgTrY -= 0.1f
//
//            if (Keyboard.isKeyDown(Keyboard.KEY_NUMPAD9)) dbgTrZ += 0.1f
//            if (Keyboard.isKeyDown(Keyboard.KEY_NUMPAD3)) dbgTrZ -= 0.1f
//
//            if (Keyboard.isKeyDown(Keyboard.KEY_NUMPAD4)) dbgScale -= 0.1f
//            if (Keyboard.isKeyDown(Keyboard.KEY_NUMPAD6)) dbgScale += 0.1f
//        }
    }
}
