package info.devels.jtte.models

import net.minecraft.client.model.ModelRenderer
import net.minecraft.entity.Entity
import org.lwjgl.opengl.GL11


class BeaconModel : BaseTileModel() {
    var modelScale = doubleArrayOf(2.0, 2.0, 2.0)
    val base: ModelRenderer
    val cube: ModelRenderer
    val intBottom: ModelRenderer
    val intSide1: ModelRenderer
    val intSide2: ModelRenderer
    val intSide3: ModelRenderer
    val intSide4: ModelRenderer
    val pillarBottom1: ModelRenderer
    val pillarMiddle1: ModelRenderer
    val pillarTop1: ModelRenderer
    val pillarBottom2: ModelRenderer
    val pillarMiddle2: ModelRenderer
    val pillarTop2: ModelRenderer
    val pillarBottom3: ModelRenderer
    val pillarMiddle3: ModelRenderer
    val pillarTop3: ModelRenderer
    val pillarBottom4: ModelRenderer
    val pillarMiddle4: ModelRenderer
    val pillarTop4: ModelRenderer
    val pieceMiddle1: ModelRenderer
    val pieceMiddle2: ModelRenderer
    val pieceMiddle3: ModelRenderer
    val pieceMiddle4: ModelRenderer
    val pieceBottom1: ModelRenderer
    val pieceBottom2: ModelRenderer
    val pieceBottom3: ModelRenderer
    val pieceBottom4: ModelRenderer
    val pieceTop1: ModelRenderer
    val pieceTop2: ModelRenderer
    val pieceTop3: ModelRenderer
    val pieceTop4: ModelRenderer

    init {
        this.textureWidth = 128
        this.textureHeight = 128
        this.pillarBottom4 = ModelRenderer(this, 0, 50)
        this.pillarBottom4.setRotationPoint(-10.0f, 24.0f, -10.0f)
        this.pillarBottom4.addBox(-3.0f, 0.0f, -3.0f, 5, 12, 5, 0.0f)
        this.pillarMiddle1 = ModelRenderer(this, 0, 75)
        this.pillarMiddle1.setRotationPoint(11.0f, 8.0f, 11.0f)
        this.pillarMiddle1.addBox(-2.0f, 0.0f, -2.0f, 3, 16, 3, 0.0f)
        this.pillarTop3 = ModelRenderer(this, 30, 50)
        this.pillarTop3.setRotationPoint(9.0f, 3.0000000000000018f, -9.0f)
        this.pillarTop3.addBox(-1.0f, 0.0f, -1.0f, 2, 5, 2, 0.0f)
        this.setRotateAngle(pillarTop3, 0.0f, -3.3909832619528366E-18f, -1.5017211588648275E-17f)
        this.pillarMiddle2 = ModelRenderer(this, 0, 75)
        this.pillarMiddle2.setRotationPoint(-11.0f, 8.0f, 11.0f)
        this.pillarMiddle2.addBox(-1.0f, 0.0f, -2.0f, 3, 16, 3, 0.0f)
        this.pillarMiddle3 = ModelRenderer(this, 0, 75)
        this.pillarMiddle3.setRotationPoint(11.0f, 8.0f, -11.0f)
        this.pillarMiddle3.addBox(-2.0f, 0.0f, -1.0f, 3, 16, 3, 0.0f)
        this.pieceBottom3 = ModelRenderer(this, 0, 0)
        this.pieceBottom3.setRotationPoint(0.0f, 30.0f, 0.0f)
        this.pieceBottom3.addBox(-0.5f, 0.0f, 19.0f, 1, 1, 1, 0.0f)
        this.pillarBottom3 = ModelRenderer(this, 0, 50)
        this.pillarBottom3.setRotationPoint(11.0f, 24.0f, -10.0f)
        this.pillarBottom3.addBox(-3.0f, 0.0f, -3.0f, 5, 12, 5, 0.0f)
        this.base = ModelRenderer(this, 0, 0)
        this.base.setRotationPoint(-16.0f, -16.99999999999995f, -16.0f)
        this.base.addBox(0.0f, 53.0f, 0.0f, 32, 12, 32, 0.0f)
        this.setRotateAngle(base, 0.0f, -3.3909832619528366E-18f, -1.5017211588648275E-17f)
        this.pieceMiddle1 = ModelRenderer(this, 0, 0)
        this.pieceMiddle1.setRotationPoint(0.0f, 16.0f, 0.0f)
        this.pieceMiddle1.addBox(-18.0f, 0.0f, -0.5f, 1, 1, 1, 0.0f)
        this.pieceBottom4 = ModelRenderer(this, 0, 0)
        this.pieceBottom4.setRotationPoint(0.0f, 30.0f, 0.0f)
        this.pieceBottom4.addBox(-0.5f, 0.0f, -20.0f, 1, 1, 1, 0.0f)
        this.pieceMiddle4 = ModelRenderer(this, 0, 0)
        this.pieceMiddle4.setRotationPoint(0.0f, 16.0f, 0.0f)
        this.pieceMiddle4.addBox(-0.5f, 0.0f, -18.0f, 1, 1, 1, 0.0f)
        this.pieceBottom2 = ModelRenderer(this, 0, 0)
        this.pieceBottom2.setRotationPoint(0.0f, 30.0f, 0.0f)
        this.pieceBottom2.addBox(19.0f, 0.0f, -0.5f, 1, 1, 1, 0.0f)
        this.pillarTop1 = ModelRenderer(this, 30, 50)
        this.pillarTop1.setRotationPoint(9.0f, 3.0f, 9.0f)
        this.pillarTop1.addBox(-1.0f, 0.0f, -1.0f, 2, 5, 2, 0.0f)
        this.setRotateAngle(pillarTop1, 0.0f, -1.5707963267948966f, 0.0f)
        this.pillarBottom2 = ModelRenderer(this, 0, 50)
        this.pillarBottom2.setRotationPoint(-10.0f, 24.0f, 11.0f)
        this.pillarBottom2.addBox(-3.0f, 0.0f, -3.0f, 5, 12, 5, 0.0f)
        this.pieceTop1 = ModelRenderer(this, 0, 0)
        this.pieceTop1.setRotationPoint(0.0f, 5.0f, 0.0f)
        this.pieceTop1.addBox(-16.0f, 0.0f, -0.5f, 1, 1, 1, 0.0f)
        this.pillarMiddle4 = ModelRenderer(this, 0, 75)
        this.pillarMiddle4.setRotationPoint(-11.0f, 8.0f, -11.0f)
        this.pillarMiddle4.addBox(-1.0f, 0.0f, -1.0f, 3, 16, 3, 0.0f)
        this.pieceMiddle2 = ModelRenderer(this, 0, 0)
        this.pieceMiddle2.setRotationPoint(0.0f, 16.0f, 0.0f)
        this.pieceMiddle2.addBox(17.0f, 0.0f, -0.5f, 1, 1, 1, 0.0f)
        this.pillarTop2 = ModelRenderer(this, 30, 50)
        this.pillarTop2.mirror = true
        this.pillarTop2.setRotationPoint(-9.0f, 3.0f, 9.0f)
        this.pillarTop2.addBox(-1.0f, 0.0f, -1.0f, 2, 5, 2, 0.0f)
        this.setRotateAngle(pillarTop2, 0.0f, 1.5707963267948966f, -0.0f)
        this.pieceTop3 = ModelRenderer(this, 0, 0)
        this.pieceTop3.setRotationPoint(0.0f, 5.0f, 0.0f)
        this.pieceTop3.addBox(-0.5f, 0.0f, 15.0f, 1, 1, 1, 0.0f)
        this.pillarBottom1 = ModelRenderer(this, 0, 50)
        this.pillarBottom1.setRotationPoint(11.0f, 24.0f, 11.0f)
        this.pillarBottom1.addBox(-3.0f, 0.0f, -3.0f, 5, 12, 5, 0.0f)
        this.pillarTop4 = ModelRenderer(this, 30, 50)
        this.pillarTop4.mirror = true
        this.pillarTop4.setRotationPoint(-9.0f, 3.0f, -9.0f)
        this.pillarTop4.addBox(-1.0f, 0.0f, -1.0f, 2, 5, 2, 0.0f)
        this.pieceTop2 = ModelRenderer(this, 0, 0)
        this.pieceTop2.setRotationPoint(0.0f, 5.0f, 0.0f)
        this.pieceTop2.addBox(15.0f, 0.0f, -0.5f, 1, 1, 1, 0.0f)
        this.pieceBottom1 = ModelRenderer(this, 0, 0)
        this.pieceBottom1.setRotationPoint(0.0f, 30.0f, 0.0f)
        this.pieceBottom1.addBox(-20.0f, 0.0f, -0.5f, 1, 1, 1, 0.0f)
        this.cube = ModelRenderer(this, 72, 45)
        this.cube.setRotationPoint(0.0f, -9.0f, 0.0f)
        this.cube.addBox(-7.0f, 0.0f, -14.0f, 14, 14, 14, 0.0f)
        this.setRotateAngle(cube, 0.7853981633974483f, 5.497787143782138f, 0.0f)
        this.pieceMiddle3 = ModelRenderer(this, 0, 0)
        this.pieceMiddle3.setRotationPoint(0.0f, 16.0f, 0.0f)
        this.pieceMiddle3.addBox(-0.5f, 0.0f, 17.0f, 1, 1, 1, 0.0f)
        this.pieceTop4 = ModelRenderer(this, 0, 0)
        this.pieceTop4.setRotationPoint(0.0f, 5.0f, 0.0f)
        this.pieceTop4.addBox(-0.5f, 0.0f, -16.0f, 1, 1, 1, 0.0f)
        this.intSide4 = ModelRenderer(this, 0, 85)
        this.intSide4.setRotationPoint(-15.8f, 42.0f, 0.0f)
        this.intSide4.addBox(0.5f, -5.9f, -15.0f, 1, 11, 30, 0.0f)
        this.intBottom = ModelRenderer(this, 2, 74)
        this.intBottom.setRotationPoint(0.0f, 46.5f, 0.0f)
        this.intBottom.addBox(-15.5f, 0.0f, -15.5f, 31, 1, 31, 0.0f)
        this.intSide1 = ModelRenderer(this, 0, 85)
        this.intSide1.setRotationPoint(14.8f, 42.0f, 0.0f)
        this.intSide1.addBox(-0.5f, -5.9f, -15.0f, 1, 11, 30, 0.0f)
        this.intSide3 = ModelRenderer(this, 0, 115)
        this.intSide3.setRotationPoint(0.0f, 42.0f, 14.8f)
        this.intSide3.addBox(-15.0f, -5.9f, -0.5f, 30, 11, 1, 0.0f)
        this.intSide2 = ModelRenderer(this, 0, 115)
        this.intSide2.setRotationPoint(0.0f, 42.0f, -15.8f)
        this.intSide2.addBox(-15.0f, -5.9f, 0.5f, 30, 11, 1, 0.0f)
    }

    override fun render(entity: Entity?, f: Float, f1: Float, f2: Float, f3: Float, f4: Float, f5: Float) {
        GL11.glPushMatrix()
        GL11.glScaled(1.0 / modelScale[0], 1.0 / modelScale[1], 1.0 / modelScale[2])
        this.intSide1.render(f5)
        this.intSide2.render(f5)
        this.intSide3.render(f5)
        this.intSide4.render(f5)
        this.intBottom.render(f5)
        this.pillarBottom4.render(f5)
        this.pillarMiddle1.render(f5)
        this.pillarTop3.render(f5)
        this.pillarMiddle2.render(f5)
        this.pillarMiddle3.render(f5)
        this.pieceBottom3.render(f5)
        this.pillarBottom3.render(f5)
        this.base.render(f5)
        this.pieceMiddle1.render(f5)
        this.pieceBottom4.render(f5)
        this.pieceMiddle4.render(f5)
        this.pieceBottom2.render(f5)
        this.pillarTop1.render(f5)
        this.pillarBottom2.render(f5)
        this.pieceTop1.render(f5)
        this.pillarMiddle4.render(f5)
        this.pieceMiddle2.render(f5)
        this.pillarTop2.render(f5)
        this.pieceTop3.render(f5)
        this.pillarBottom1.render(f5)
        this.pillarTop4.render(f5)
        this.pieceTop2.render(f5)
        this.pieceBottom1.render(f5)
        this.cube.render(f5)
        this.pieceMiddle3.render(f5)
        this.pieceTop4.render(f5)
        GL11.glPopMatrix()
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    fun setRotateAngle(modelRenderer: ModelRenderer, x: Float, y: Float, z: Float) {
        modelRenderer.rotateAngleX = x
        modelRenderer.rotateAngleY = y
        modelRenderer.rotateAngleZ = z
    }

    fun rotateCube(y: Float) {
        this.setRotateAngle(this.cube, this.cube.rotateAngleX, y, this.cube.rotateAngleZ)
    }

    fun rotateBottomPieces(y: Float) {
        this.setRotateAngle(this.pieceBottom1, this.pieceBottom1.rotateAngleX, y, this.pieceBottom1.rotateAngleZ)
        this.setRotateAngle(this.pieceBottom2, this.pieceBottom2.rotateAngleX, y, this.pieceBottom2.rotateAngleZ)
        this.setRotateAngle(this.pieceBottom3, this.pieceBottom3.rotateAngleX, y, this.pieceBottom3.rotateAngleZ)
        this.setRotateAngle(this.pieceBottom4, this.pieceBottom4.rotateAngleX, y, this.pieceBottom4.rotateAngleZ)
    }

    fun rotateMiddlePieces(y: Float) {
        this.setRotateAngle(this.pieceMiddle1, this.pieceMiddle1.rotateAngleX, y, this.pieceMiddle1.rotateAngleZ)
        this.setRotateAngle(this.pieceMiddle2, this.pieceMiddle2.rotateAngleX, y, this.pieceMiddle2.rotateAngleZ)
        this.setRotateAngle(this.pieceMiddle3, this.pieceMiddle3.rotateAngleX, y, this.pieceMiddle3.rotateAngleZ)
        this.setRotateAngle(this.pieceMiddle4, this.pieceMiddle4.rotateAngleX, y, this.pieceMiddle4.rotateAngleZ)
    }

    fun rotateTopPieces(y: Float) {
        this.setRotateAngle(this.pieceTop1, this.pieceTop1.rotateAngleX, y, this.pieceTop1.rotateAngleZ)
        this.setRotateAngle(this.pieceTop2, this.pieceTop2.rotateAngleX, y, this.pieceTop2.rotateAngleZ)
        this.setRotateAngle(this.pieceTop3, this.pieceTop3.rotateAngleX, y, this.pieceTop3.rotateAngleZ)
        this.setRotateAngle(this.pieceTop4, this.pieceTop4.rotateAngleX, y, this.pieceTop4.rotateAngleZ)
    }
}
