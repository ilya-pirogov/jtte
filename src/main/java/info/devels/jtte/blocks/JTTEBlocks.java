package info.devels.jtte.blocks;

public class JTTEBlocks {
    public static BlockBeacon blockBeacon;
    public static BlockTerminal blockTerminal;

    public static void preInit() {
        blockBeacon = new BlockBeacon();
        blockTerminal = new BlockTerminal();

        blockBeacon.preInit();
        blockTerminal.preInit();
    }

    public static void initialize() {

    }

    public static void postInit() {
        blockBeacon.postInit();
        blockTerminal.postInit();
    }
}
