package info.devels.jtte.render;

import info.devels.jtte.entities.TileEntityBeacon;
import info.devels.jtte.models.BeaconModel;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

public class TileEntityBeaconRenderer extends TileEntitySpecialRenderer {
    protected BeaconModel model;
    protected ResourceLocation texture;

    public TileEntityBeaconRenderer(BeaconModel beaconModel, ResourceLocation texture) {
        this.model = beaconModel;
        this.texture = texture;
    }

    public void renderTileEntityAt(TileEntityBeacon tile, double x, double y, double z, float n) {
        this.bindTexture(texture);

        GL11.glPushMatrix();
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

        float dx = (float) x;
        float dy = (float) y + 1.5F;
        float dz = (float) z;

        GL11.glTranslatef(dx, dy, dz);
        GL11.glScalef(1.0F, -1.0F, -1.0F);

        GL11.glRotatef(180, 0, 1F, 0);
        dx = -0.5F;
        dy = 0F;
        dz = +0.5F;
        GL11.glTranslatef(dx, dy, dz);

        model.rotateCube((float) (tile.angleCube + tile.rtCube * n));
        model.rotateBottomPieces((float) (tile.angleBottomPieces + tile.rtBottomPieces * n));
        model.rotateMiddlePieces((float) (tile.angleMiddlePieces + tile.rtMiddlePieces * n));
        model.rotateTopPieces((float) (tile.angleTopPieces + tile.rtTopPieces * n));

        model.renderAll();

        GL11.glDisable(GL11.GL_BLEND);
        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        GL11.glPopMatrix();
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    }

    @Override
    public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float n) {
        renderTileEntityAt((TileEntityBeacon)tile, x, y, z, n);
    }
}
