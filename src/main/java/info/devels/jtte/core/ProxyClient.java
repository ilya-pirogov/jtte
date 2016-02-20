package info.devels.jtte.core;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import info.devels.jtte.JTTE;
import info.devels.jtte.blocks.BlockBeacon;
import info.devels.jtte.entities.TileEntityBeacon;
import info.devels.jtte.models.BeaconModel;
import info.devels.jtte.render.ItemBeaconRenderer;
import info.devels.jtte.render.TileEntityBeaconRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.MinecraftForgeClient;
import org.lwjgl.input.Keyboard;

public class ProxyClient extends Proxy {
    private long msLastTick = 0;
    private ItemBeaconRenderer beaconRenderer;

    @Override
    public void registerRenderInformation() {
        beaconRenderer = new ItemBeaconRenderer();

        TileEntityBeaconRenderer renderer = new TileEntityBeaconRenderer(
                new BeaconModel(), new ResourceLocation(JTTE.modId, String.format("textures/entity/%s.png", "beacon")));

        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBeacon.class, renderer);
        MinecraftForgeClient.registerItemRenderer(BlockBeacon.blockBeacon.getItem(), beaconRenderer);
    }

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        msLastTick = System.nanoTime();
        beaconRenderer.tick();
    }

    @SubscribeEvent
    public void onRenderTick(TickEvent.RenderTickEvent event) {
        beaconRenderer.tickOffset = (System.nanoTime() - msLastTick) / 50000000F;
    }

    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        if (Keyboard.isKeyDown(Keyboard.KEY_LCONTROL)) {
            if (Keyboard.isKeyDown(Keyboard.KEY_NUMPAD7)) {
                JTTEProps.setDbgRotX(JTTEProps.getDbgRotX() + 5F);
            }

            if (Keyboard.isKeyDown(Keyboard.KEY_NUMPAD1)) {
                JTTEProps.setDbgRotX(JTTEProps.getDbgRotX() - 5F);
            }

            if (Keyboard.isKeyDown(Keyboard.KEY_NUMPAD8)) {
                JTTEProps.setDbgRotY(JTTEProps.getDbgRotY() + 5F);
            }

            if (Keyboard.isKeyDown(Keyboard.KEY_NUMPAD2)) {
                JTTEProps.setDbgRotY(JTTEProps.getDbgRotY() - 5F);
            }

            if (Keyboard.isKeyDown(Keyboard.KEY_NUMPAD9)) {
                JTTEProps.setDbgRotZ(JTTEProps.getDbgRotZ() + 5F);
            }

            if (Keyboard.isKeyDown(Keyboard.KEY_NUMPAD3)) {
                JTTEProps.setDbgRotZ(JTTEProps.getDbgRotZ() - 5F);
            }
        } else {
            if (Keyboard.isKeyDown(Keyboard.KEY_NUMPAD7)) {
                JTTEProps.setDbgTrX(JTTEProps.getDbgTrX() + 0.1F);
            }

            if (Keyboard.isKeyDown(Keyboard.KEY_NUMPAD1)) {
                JTTEProps.setDbgTrX(JTTEProps.getDbgTrX() - 0.1F);
            }

            if (Keyboard.isKeyDown(Keyboard.KEY_NUMPAD8)) {
                JTTEProps.setDbgTrY(JTTEProps.getDbgTrY() + 0.1F);
            }

            if (Keyboard.isKeyDown(Keyboard.KEY_NUMPAD2)) {
                JTTEProps.setDbgTrY(JTTEProps.getDbgTrY() - 0.1F);
            }

            if (Keyboard.isKeyDown(Keyboard.KEY_NUMPAD9)) {
                JTTEProps.setDbgTrZ(JTTEProps.getDbgTrZ() + 0.1F);
            }

            if (Keyboard.isKeyDown(Keyboard.KEY_NUMPAD3)) {
                JTTEProps.setDbgTrZ(JTTEProps.getDbgTrZ() - 0.1F);
            }

            if (Keyboard.isKeyDown(Keyboard.KEY_NUMPAD4)) {
                JTTEProps.setDbgScale(JTTEProps.getDbgScale() - 0.1F);
            }

            if (Keyboard.isKeyDown(Keyboard.KEY_NUMPAD6)) {
                JTTEProps.setDbgScale(JTTEProps.getDbgScale() + 0.1F);
            }
        }
    }
}
