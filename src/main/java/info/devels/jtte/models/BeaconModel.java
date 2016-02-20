package info.devels.jtte.models;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

/**
 * Beacon - KiniNoob
 * Created using Tabula 4.1.1
 */
public class BeaconModel extends BaseTileModel {
    public double[] modelScale = new double[] { 2.0D, 2.0D, 2.0D };
    public ModelRenderer base;
    public ModelRenderer cube;
    public ModelRenderer intBottom;
    public ModelRenderer intSide1;
    public ModelRenderer intSide2;
    public ModelRenderer intSide3;
    public ModelRenderer intSide4;
    public ModelRenderer pillarBottom1;
    public ModelRenderer pillarMiddle1;
    public ModelRenderer pillarTop1;
    public ModelRenderer pillarBottom2;
    public ModelRenderer pillarMiddle2;
    public ModelRenderer pillarTop2;
    public ModelRenderer pillarBottom3;
    public ModelRenderer pillarMiddle3;
    public ModelRenderer pillarTop3;
    public ModelRenderer pillarBottom4;
    public ModelRenderer pillarMiddle4;
    public ModelRenderer pillarTop4;
    public ModelRenderer pieceMiddle1;
    public ModelRenderer pieceMiddle2;
    public ModelRenderer pieceMiddle3;
    public ModelRenderer pieceMiddle4;
    public ModelRenderer pieceBottom1;
    public ModelRenderer pieceBottom2;
    public ModelRenderer pieceBottom3;
    public ModelRenderer pieceBottom4;
    public ModelRenderer pieceTop1;
    public ModelRenderer pieceTop2;
    public ModelRenderer pieceTop3;
    public ModelRenderer pieceTop4;

    public BeaconModel() {
        this.textureWidth = 128;
        this.textureHeight = 128;
        this.pillarBottom4 = new ModelRenderer(this, 0, 50);
        this.pillarBottom4.setRotationPoint(-10.0F, 24.0F, -10.0F);
        this.pillarBottom4.addBox(-3.0F, 0.0F, -3.0F, 5, 12, 5, 0.0F);
        this.pillarMiddle1 = new ModelRenderer(this, 0, 75);
        this.pillarMiddle1.setRotationPoint(11.0F, 8.0F, 11.0F);
        this.pillarMiddle1.addBox(-2.0F, 0.0F, -2.0F, 3, 16, 3, 0.0F);
        this.pillarTop3 = new ModelRenderer(this, 30, 50);
        this.pillarTop3.setRotationPoint(9.0F, 3.0000000000000018F, -9.0F);
        this.pillarTop3.addBox(-1.0F, 0.0F, -1.0F, 2, 5, 2, 0.0F);
        this.setRotateAngle(pillarTop3, 0.0F, -3.3909832619528366E-18F, -1.5017211588648275E-17F);
        this.pillarMiddle2 = new ModelRenderer(this, 0, 75);
        this.pillarMiddle2.setRotationPoint(-11.0F, 8.0F, 11.0F);
        this.pillarMiddle2.addBox(-1.0F, 0.0F, -2.0F, 3, 16, 3, 0.0F);
        this.pillarMiddle3 = new ModelRenderer(this, 0, 75);
        this.pillarMiddle3.setRotationPoint(11.0F, 8.0F, -11.0F);
        this.pillarMiddle3.addBox(-2.0F, 0.0F, -1.0F, 3, 16, 3, 0.0F);
        this.pieceBottom3 = new ModelRenderer(this, 0, 0);
        this.pieceBottom3.setRotationPoint(0.0F, 30.0F, 0.0F);
        this.pieceBottom3.addBox(-0.5F, 0.0F, 19.0F, 1, 1, 1, 0.0F);
        this.pillarBottom3 = new ModelRenderer(this, 0, 50);
        this.pillarBottom3.setRotationPoint(11.0F, 24.0F, -10.0F);
        this.pillarBottom3.addBox(-3.0F, 0.0F, -3.0F, 5, 12, 5, 0.0F);
        this.base = new ModelRenderer(this, 0, 0);
        this.base.setRotationPoint(-16.0F, -16.99999999999995F, -16.0F);
        this.base.addBox(0.0F, 53.0F, 0.0F, 32, 12, 32, 0.0F);
        this.setRotateAngle(base, 0.0F, -3.3909832619528366E-18F, -1.5017211588648275E-17F);
        this.pieceMiddle1 = new ModelRenderer(this, 0, 0);
        this.pieceMiddle1.setRotationPoint(0.0F, 16.0F, 0.0F);
        this.pieceMiddle1.addBox(-18.0F, 0.0F, -0.5F, 1, 1, 1, 0.0F);
        this.pieceBottom4 = new ModelRenderer(this, 0, 0);
        this.pieceBottom4.setRotationPoint(0.0F, 30.0F, 0.0F);
        this.pieceBottom4.addBox(-0.5F, 0.0F, -20.0F, 1, 1, 1, 0.0F);
        this.pieceMiddle4 = new ModelRenderer(this, 0, 0);
        this.pieceMiddle4.setRotationPoint(0.0F, 16.0F, 0.0F);
        this.pieceMiddle4.addBox(-0.5F, 0.0F, -18.0F, 1, 1, 1, 0.0F);
        this.pieceBottom2 = new ModelRenderer(this, 0, 0);
        this.pieceBottom2.setRotationPoint(0.0F, 30.0F, 0.0F);
        this.pieceBottom2.addBox(19.0F, 0.0F, -0.5F, 1, 1, 1, 0.0F);
        this.pillarTop1 = new ModelRenderer(this, 30, 50);
        this.pillarTop1.setRotationPoint(9.0F, 3.0F, 9.0F);
        this.pillarTop1.addBox(-1.0F, 0.0F, -1.0F, 2, 5, 2, 0.0F);
        this.setRotateAngle(pillarTop1, 0.0F, -1.5707963267948966F, 0.0F);
        this.pillarBottom2 = new ModelRenderer(this, 0, 50);
        this.pillarBottom2.setRotationPoint(-10.0F, 24.0F, 11.0F);
        this.pillarBottom2.addBox(-3.0F, 0.0F, -3.0F, 5, 12, 5, 0.0F);
        this.pieceTop1 = new ModelRenderer(this, 0, 0);
        this.pieceTop1.setRotationPoint(0.0F, 5.0F, 0.0F);
        this.pieceTop1.addBox(-16.0F, 0.0F, -0.5F, 1, 1, 1, 0.0F);
        this.pillarMiddle4 = new ModelRenderer(this, 0, 75);
        this.pillarMiddle4.setRotationPoint(-11.0F, 8.0F, -11.0F);
        this.pillarMiddle4.addBox(-1.0F, 0.0F, -1.0F, 3, 16, 3, 0.0F);
        this.pieceMiddle2 = new ModelRenderer(this, 0, 0);
        this.pieceMiddle2.setRotationPoint(0.0F, 16.0F, 0.0F);
        this.pieceMiddle2.addBox(17.0F, 0.0F, -0.5F, 1, 1, 1, 0.0F);
        this.pillarTop2 = new ModelRenderer(this, 30, 50);
        this.pillarTop2.mirror = true;
        this.pillarTop2.setRotationPoint(-9.0F, 3.0F, 9.0F);
        this.pillarTop2.addBox(-1.0F, 0.0F, -1.0F, 2, 5, 2, 0.0F);
        this.setRotateAngle(pillarTop2, 0.0F, 1.5707963267948966F, -0.0F);
        this.pieceTop3 = new ModelRenderer(this, 0, 0);
        this.pieceTop3.setRotationPoint(0.0F, 5.0F, 0.0F);
        this.pieceTop3.addBox(-0.5F, 0.0F, 15.0F, 1, 1, 1, 0.0F);
        this.pillarBottom1 = new ModelRenderer(this, 0, 50);
        this.pillarBottom1.setRotationPoint(11.0F, 24.0F, 11.0F);
        this.pillarBottom1.addBox(-3.0F, 0.0F, -3.0F, 5, 12, 5, 0.0F);
        this.pillarTop4 = new ModelRenderer(this, 30, 50);
        this.pillarTop4.mirror = true;
        this.pillarTop4.setRotationPoint(-9.0F, 3.0F, -9.0F);
        this.pillarTop4.addBox(-1.0F, 0.0F, -1.0F, 2, 5, 2, 0.0F);
        this.pieceTop2 = new ModelRenderer(this, 0, 0);
        this.pieceTop2.setRotationPoint(0.0F, 5.0F, 0.0F);
        this.pieceTop2.addBox(15.0F, 0.0F, -0.5F, 1, 1, 1, 0.0F);
        this.pieceBottom1 = new ModelRenderer(this, 0, 0);
        this.pieceBottom1.setRotationPoint(0.0F, 30.0F, 0.0F);
        this.pieceBottom1.addBox(-20.0F, 0.0F, -0.5F, 1, 1, 1, 0.0F);
        this.cube = new ModelRenderer(this, 72, 45);
        this.cube.setRotationPoint(0.0F, -9.0F, 0.0F);
        this.cube.addBox(-7.0F, 0.0F, -14.0F, 14, 14, 14, 0.0F);
        this.setRotateAngle(cube, 0.7853981633974483F, 5.497787143782138F, 0.0F);
        this.pieceMiddle3 = new ModelRenderer(this, 0, 0);
        this.pieceMiddle3.setRotationPoint(0.0F, 16.0F, 0.0F);
        this.pieceMiddle3.addBox(-0.5F, 0.0F, 17.0F, 1, 1, 1, 0.0F);
        this.pieceTop4 = new ModelRenderer(this, 0, 0);
        this.pieceTop4.setRotationPoint(0.0F, 5.0F, 0.0F);
        this.pieceTop4.addBox(-0.5F, 0.0F, -16.0F, 1, 1, 1, 0.0F);
        this.intSide4 = new ModelRenderer(this, 0, 85);
        this.intSide4.setRotationPoint(-15.8F, 42.0F, 0.0F);
        this.intSide4.addBox(0.5F, -5.9F, -15.0F, 1, 11, 30, 0.0F);
        this.intBottom = new ModelRenderer(this, 2, 74);
        this.intBottom.setRotationPoint(0.0F, 46.5F, 0.0F);
        this.intBottom.addBox(-15.5F, 0.0F, -15.5F, 31, 1, 31, 0.0F);
        this.intSide1 = new ModelRenderer(this, 0, 85);
        this.intSide1.setRotationPoint(14.8F, 42.0F, 0.0F);
        this.intSide1.addBox(-0.5F, -5.9F, -15.0F, 1, 11, 30, 0.0F);
        this.intSide3 = new ModelRenderer(this, 0, 115);
        this.intSide3.setRotationPoint(0.0F, 42.0F, 14.8F);
        this.intSide3.addBox(-15.0F, -5.9F, -0.5F, 30, 11, 1, 0.0F);
        this.intSide2 = new ModelRenderer(this, 0, 115);
        this.intSide2.setRotationPoint(0.0F, 42.0F, -15.8F);
        this.intSide2.addBox(-15.0F, -5.9F, 0.5F, 30, 11, 1, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        GL11.glPushMatrix();
        GL11.glScaled(1D / modelScale[0], 1D / modelScale[1], 1D / modelScale[2]);
        this.intSide1.render(f5);
        this.intSide2.render(f5);
        this.intSide3.render(f5);
        this.intSide4.render(f5);
        this.intBottom.render(f5);
        this.pillarBottom4.render(f5);
        this.pillarMiddle1.render(f5);
        this.pillarTop3.render(f5);
        this.pillarMiddle2.render(f5);
        this.pillarMiddle3.render(f5);
        this.pieceBottom3.render(f5);
        this.pillarBottom3.render(f5);
        this.base.render(f5);
        this.pieceMiddle1.render(f5);
        this.pieceBottom4.render(f5);
        this.pieceMiddle4.render(f5);
        this.pieceBottom2.render(f5);
        this.pillarTop1.render(f5);
        this.pillarBottom2.render(f5);
        this.pieceTop1.render(f5);
        this.pillarMiddle4.render(f5);
        this.pieceMiddle2.render(f5);
        this.pillarTop2.render(f5);
        this.pieceTop3.render(f5);
        this.pillarBottom1.render(f5);
        this.pillarTop4.render(f5);
        this.pieceTop2.render(f5);
        this.pieceBottom1.render(f5);
        this.cube.render(f5);
        this.pieceMiddle3.render(f5);
        this.pieceTop4.render(f5);
        GL11.glPopMatrix();
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

    public void rotateCube(float y) {
        this.setRotateAngle(this.cube, this.cube.rotateAngleX, y, this.cube.rotateAngleZ);
    }

    public void rotateBottomPieces(float y) {
        this.setRotateAngle(this.pieceBottom1, this.pieceBottom1.rotateAngleX, y, this.pieceBottom1.rotateAngleZ);
        this.setRotateAngle(this.pieceBottom2, this.pieceBottom2.rotateAngleX, y, this.pieceBottom2.rotateAngleZ);
        this.setRotateAngle(this.pieceBottom3, this.pieceBottom3.rotateAngleX, y, this.pieceBottom3.rotateAngleZ);
        this.setRotateAngle(this.pieceBottom4, this.pieceBottom4.rotateAngleX, y, this.pieceBottom4.rotateAngleZ);
    }

    public void rotateMiddlePieces(float y) {
        this.setRotateAngle(this.pieceMiddle1, this.pieceMiddle1.rotateAngleX, y, this.pieceMiddle1.rotateAngleZ);
        this.setRotateAngle(this.pieceMiddle2, this.pieceMiddle2.rotateAngleX, y, this.pieceMiddle2.rotateAngleZ);
        this.setRotateAngle(this.pieceMiddle3, this.pieceMiddle3.rotateAngleX, y, this.pieceMiddle3.rotateAngleZ);
        this.setRotateAngle(this.pieceMiddle4, this.pieceMiddle4.rotateAngleX, y, this.pieceMiddle4.rotateAngleZ);
    }

    public void rotateTopPieces(float y) {
        this.setRotateAngle(this.pieceTop1, this.pieceTop1.rotateAngleX, y, this.pieceTop1.rotateAngleZ);
        this.setRotateAngle(this.pieceTop2, this.pieceTop2.rotateAngleX, y, this.pieceTop2.rotateAngleZ);
        this.setRotateAngle(this.pieceTop3, this.pieceTop3.rotateAngleX, y, this.pieceTop3.rotateAngleZ);
        this.setRotateAngle(this.pieceTop4, this.pieceTop4.rotateAngleX, y, this.pieceTop4.rotateAngleZ);
    }

    public void rotateAllPieces(float y) {
        this.rotateBottomPieces(y);
        this.rotateMiddlePieces(y);
        this.rotateTopPieces(y);
    }
}
