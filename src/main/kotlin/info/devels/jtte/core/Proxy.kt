package info.devels.jtte.core

import cpw.mods.fml.common.registry.GameRegistry
import info.devels.jtte.entities.TileEntityBeacon
import info.devels.jtte.entities.TileEntityTerminal


open class Proxy {
    open fun registerEntities() {
        println(TileEntityBeacon::class.qualifiedName!!)
        GameRegistry.registerTileEntity(TileEntityBeacon::class.java, TileEntityBeacon::class.qualifiedName!!)
        GameRegistry.registerTileEntity(TileEntityTerminal::class.java, TileEntityTerminal::class.qualifiedName!!)
    }

    open fun registerRenderInformation() {
    }
}
