package info.devels.jtte.models

import net.minecraft.client.model.ModelRenderer
import net.minecraft.entity.Entity
import org.lwjgl.opengl.GL11


class BeaconHandModel : BaseTileModel() {
    private val modelScale = doubleArrayOf(2.0, 2.0, 2.0)
    private val shape2: ModelRenderer
    private val shape3: ModelRenderer
    private val shape3_1: ModelRenderer
    private val shape5: ModelRenderer
    private val shape6: ModelRenderer
    private val shape6_1: ModelRenderer
    private val shape8: ModelRenderer
    private val shape9: ModelRenderer
    private val shape10: ModelRenderer
    private val shape11: ModelRenderer
    private val cube: ModelRenderer

    var cubeAngleY = 0f

    init {
        this.textureWidth = 74
        this.textureHeight = 52
        this.shape3 = ModelRenderer(this, 0, 11)
        this.shape3.setRotationPoint(-7.0f, 25.0f, -0.5f)
        this.shape3.addBox(0.0f, 0.0f, 0.0f, 3, 6, 1, 0.0f)
        this.cube = ModelRenderer(this, 50, 0)
        this.cube.setRotationPoint(0.0f, 7.0f, -0.5f)
        this.cube.addBox(-5.0f, -5.0f, 0.0f, 10, 10, 1, 0.0f)
        this.setRotateAngle(cube, 0.0f, 0.0f, 0.7853981633974483f)
        this.shape2 = ModelRenderer(this, 0, 0)
        this.shape2.setRotationPoint(-8.0f, 31.0f, 0.0f)
        this.shape2.addBox(0.0f, 0.0f, 0.0f, 16, 5, 1, 0.0f)
        this.shape10 = ModelRenderer(this, 0, 45)
        this.shape10.setRotationPoint(4.0f, 15.0f, -0.5f)
        this.shape10.addBox(0.0f, 0.0f, 0.0f, 1, 3, 1, 0.0f)
        this.shape3_1 = ModelRenderer(this, 0, 11)
        this.shape3_1.setRotationPoint(4.0f, 25.0f, -0.5f)
        this.shape3_1.addBox(0.0f, 0.0f, 0.0f, 3, 6, 1, 0.0f)
        this.shape11 = ModelRenderer(this, 15, 45)
        this.shape11.setRotationPoint(-1.0f, 15.0f, -0.5f)
        this.shape11.addBox(0.0f, 0.0f, 0.0f, 2, 3, 1, 0.0f)
        this.shape6 = ModelRenderer(this, 0, 33)
        this.shape6.setRotationPoint(-6.0f, 18.0f, -0.5f)
        this.shape6.addBox(0.0f, 0.0f, 0.0f, 2, 7, 1, 0.0f)
        this.shape6_1 = ModelRenderer(this, 0, 33)
        this.shape6_1.setRotationPoint(4.0f, 18.0f, -0.5f)
        this.shape6_1.addBox(0.0f, 0.0f, 0.0f, 2, 7, 1, 0.0f)
        this.shape5 = ModelRenderer(this, 0, 21)
        this.shape5.setRotationPoint(-3.0f, 25.0f, -0.5f)
        this.shape5.addBox(0.0f, 0.0f, 0.0f, 6, 6, 1, 0.0f)
        this.shape8 = ModelRenderer(this, 15, 33)
        this.shape8.setRotationPoint(-2.0f, 18.0f, -0.5f)
        this.shape8.addBox(0.0f, 0.0f, 0.0f, 4, 7, 1, 0.0f)
        this.shape9 = ModelRenderer(this, 0, 45)
        this.shape9.mirror = true
        this.shape9.setRotationPoint(-5.0f, 15.0f, -0.5f)
        this.shape9.addBox(0.0f, 0.0f, 0.0f, 1, 3, 1, 0.0f)
    }

    override fun render(entity: Entity?, f: Float, f1: Float, f2: Float, f3: Float, f4: Float, f5: Float) {
        GL11.glPushMatrix()
        GL11.glScaled(1.0 / modelScale[0], 1.0 / modelScale[1], 1.0 / modelScale[2])
        this.shape3.render(f5)

        GL11.glPushMatrix()
        GL11.glRotatef((cubeAngleY * 180 / Math.PI).toFloat(), 0f, 1f, 0f)
        this.cube.render(f5)
        GL11.glPopMatrix()

        this.shape2.render(f5)
        this.shape10.render(f5)
        this.shape3_1.render(f5)
        this.shape11.render(f5)
        this.shape6.render(f5)
        this.shape6_1.render(f5)
        this.shape5.render(f5)
        this.shape8.render(f5)
        this.shape9.render(f5)
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
        this.cubeAngleY = y
    }
}