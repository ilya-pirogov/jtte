package info.devels.jtte.core;

public class JTTEProps {
    public static int ticksCube = 160;
    public static int ticksBottomPieces = 90;
    public static int ticksMiddlePieces = 60;
    public static int ticksTopPieces = 40;
    private static float dbgTrX = 0F;
    private static float dbgTrY = 0F;
    private static float dbgTrZ = 0F;
    private static float dbgRotX = 0F;
    private static float dbgRotY = 0F;
    private static float dbgRotZ = 0F;
    private static float dbgScale = 1F;

    public static void printDbg() {
        System.out.println(String.format("X:%f Y:%f Z:%f; rotX:%f rotY:%f rotZ:%f; Scale:%f", dbgTrX, dbgTrY, dbgTrZ, dbgRotX, dbgRotY, dbgRotZ, dbgScale));
    }

    public static float getDbgRotX() {
        return dbgRotX;
    }

    public static void setDbgRotX(float dbgRotX) {
        JTTEProps.dbgRotX = dbgRotX;
        JTTEProps.printDbg();
    }

    public static float getDbgTrX() {
        return dbgTrX;
    }

    public static void setDbgTrX(float dbgTrX) {
        JTTEProps.dbgTrX = dbgTrX;
        JTTEProps.printDbg();
    }

    public static float getDbgTrY() {
        return dbgTrY;
    }

    public static void setDbgTrY(float dbgTrY) {
        JTTEProps.dbgTrY = dbgTrY;
        JTTEProps.printDbg();
    }

    public static float getDbgTrZ() {
        return dbgTrZ;
    }

    public static void setDbgTrZ(float dbgTrZ) {
        JTTEProps.dbgTrZ = dbgTrZ;
        JTTEProps.printDbg();
    }

    public static float getDbgRotY() {
        return dbgRotY;
    }

    public static void setDbgRotY(float dbgRotY) {
        JTTEProps.dbgRotY = dbgRotY;
        JTTEProps.printDbg();
    }

    public static float getDbgRotZ() {
        return dbgRotZ;
    }

    public static void setDbgRotZ(float dbgRotZ) {
        JTTEProps.dbgRotZ = dbgRotZ;
        JTTEProps.printDbg();
    }

    public static float getDbgScale() {
        return dbgScale;
    }

    public static void setDbgScale(float dbgScale) {
        JTTEProps.dbgScale = dbgScale;
        JTTEProps.printDbg();
    }
}
