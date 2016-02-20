package info.devels.jtte.core;

import cpw.mods.fml.common.registry.GameRegistry;
import info.devels.jtte.entities.TileEntityBeacon;

public class Proxy {
    public void registerEntities()
    {
        GameRegistry.registerTileEntity(TileEntityBeacon.class, "jtte.TileEntityBeacon");
    }

    public void registerRenderInformation() {
    }
}
