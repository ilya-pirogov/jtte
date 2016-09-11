package info.devels.jtte.blocks

import cpw.mods.fml.common.registry.GameRegistry


val blockBeacon: BlockBeacon = BlockBeacon()
val blockTerminal: BlockTerminal = BlockTerminal()
val blockCursedDirt: BlockCursedDirt = BlockCursedDirt()

fun blocksPreInit() {
    blockBeacon.preInit()
    blockTerminal.preInit()
    GameRegistry.registerBlock(blockCursedDirt, "CursedDirt")
}

fun blocksInitialize() {

}

fun blocksPostInit() {
    blockBeacon.postInit()
    blockTerminal.postInit()
}
