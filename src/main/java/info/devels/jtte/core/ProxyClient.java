package info.devels.jtte.core;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.relauncher.Side;
import info.devels.jtte.JTTE;
import info.devels.jtte.blocks.BlockBeacon;
import info.devels.jtte.entities.TileEntityBeacon;
import info.devels.jtte.models.BeaconModel;
import info.devels.jtte.render.ItemBeaconRenderer;
import info.devels.jtte.render.TileEntityBeaconRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.MinecraftForgeClient;

public class ProxyClient extends Proxy {
    private TileEntityBeacon fakeBeaconTile;
    private long msLastTick = 0;
    private ItemBeaconRenderer beaconRenderer;

    @Override
    public void registerRenderInformation() {
        fakeBeaconTile = new TileEntityBeacon(true);

        TileEntityBeaconRenderer renderer = new TileEntityBeaconRenderer(
                new BeaconModel(), new ResourceLocation(JTTE.modId, String.format("textures/entity/%s.png", "beacon")));

        beaconRenderer = new ItemBeaconRenderer( renderer, fakeBeaconTile );

        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBeacon.class, renderer);
        MinecraftForgeClient.registerItemRenderer(BlockBeacon.blockBeacon.getItem(), beaconRenderer);
    }

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.side == Side.CLIENT) {
            msLastTick = System.nanoTime();
            fakeBeaconTile.updateEntity();
        }
    }

    @SubscribeEvent
    public void onRenderTick(TickEvent.RenderTickEvent event) {
        beaconRenderer.tickOffset = (System.nanoTime() - msLastTick) / 50000000F;
    }
}
