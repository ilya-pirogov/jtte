package info.devels.jtte.core

import cpw.mods.fml.common.registry.GameRegistry
import info.devels.jtte.entities.TileEntityBeacon


open class Proxy {
    open fun registerEntities() {
        GameRegistry.registerTileEntity(TileEntityBeacon::class.java, "jtte.TileEntityBeacon")
    }

    open fun registerRenderInformation() {
    }
}
