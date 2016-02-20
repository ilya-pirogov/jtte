package info.devels.jtte.entities;

import cofh.core.block.TileCoFHBase;
import info.devels.jtte.core.JTTEProps;

public class TileEntityBeacon extends TileCoFHBase {
    public double rtCube = ((Math.PI * 2) / JTTEProps.ticksCube);
    public double rtBottomPieces = ((Math.PI * 2) / JTTEProps.ticksBottomPieces);
    public double rtMiddlePieces = ((Math.PI * 2)/ JTTEProps.ticksMiddlePieces);
    public double rtTopPieces = ((Math.PI * 2) / JTTEProps.ticksTopPieces);

    public float angleCube = 0;
    public float angleBottomPieces = 0;
    public float angleMiddlePieces = 0;
    public float angleTopPieces = 0;

    public TileEntityBeacon() {
        this(false);
    }

    public TileEntityBeacon(boolean fake) {
        if (fake) {
            rtCube /= 2;
            rtBottomPieces /= 2;
            rtMiddlePieces /= 2;
            rtTopPieces /= 2;
        }
    }

    @Override
    public void updateEntity() {
        angleCube += rtCube;
        if (angleCube > Math.PI) {
            angleCube -= 2 * Math.PI;
        }

        angleBottomPieces += rtBottomPieces;
        if (angleBottomPieces > Math.PI) {
            angleBottomPieces -= 2 * Math.PI;
        }

        angleMiddlePieces += rtMiddlePieces;
        if (angleMiddlePieces > Math.PI) {
            angleMiddlePieces -= 2 * Math.PI;
        }

        angleTopPieces += rtTopPieces;
        if (angleTopPieces > Math.PI) {
            angleTopPieces -= 2 * Math.PI;
        }
    }

    @Override
    public String getName() {
        return "tileentitybeacon";
    }

    @Override
    public int getType() {
        return 0;
    }
}
