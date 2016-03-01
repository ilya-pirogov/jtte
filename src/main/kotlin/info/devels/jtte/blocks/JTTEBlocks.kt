package info.devels.jtte.blocks


val blockBeacon: BlockBeacon = BlockBeacon()
val blockTerminal: BlockTerminal = BlockTerminal()

fun blocksPreInit() {
    blockBeacon.preInit()
    blockTerminal.preInit()
}

fun blocksInitialize() {

}

fun blocksPostInit() {
    blockBeacon.postInit()
    blockTerminal.postInit()
}
