package info.devels.jtte.models

import net.minecraft.client.model.ModelRenderer
import net.minecraft.entity.Entity
import org.lwjgl.opengl.GL11

/**
 * Clock - Kini
 * Created using Tabula 4.1.1
 */
class ClockModel : BaseTileModel() {
    var modelScale = doubleArrayOf(2.0, 2.0, 2.0)
    var shape2: ModelRenderer
    var shape3: ModelRenderer
    var shape5: ModelRenderer
    var shape3_1: ModelRenderer
    var shape5_1: ModelRenderer
    var shape8: ModelRenderer
    var shape9: ModelRenderer
    var shape8_1: ModelRenderer
    var shape9_1: ModelRenderer

    init {
        this.textureWidth = 172
        this.textureHeight = 151
        this.shape5 = ModelRenderer(this, 10, 0)
        this.shape5.setRotationPoint(0.0f, 2.0f, 1.0f)
        this.shape5.addBox(0.0f, 0.0f, 0.0f, 1, 25, 1, 0.0f)
        this.shape9 = ModelRenderer(this, 80, 42)
        this.shape9.setRotationPoint(0.0f, 1.0f, 2.0f)
        this.shape9.addBox(0.0f, 0.0f, 0.0f, 1, 1, 38, 0.0f)
        this.shape3_1 = ModelRenderer(this, 15, 0)
        this.shape3_1.setRotationPoint(0.0f, 3.0f, 41.0f)
        this.shape3_1.addBox(0.0f, 0.0f, 0.0f, 1, 23, 1, 0.0f)
        this.shape9_1 = ModelRenderer(this, 45, 107)
        this.shape9_1.setRotationPoint(0.0f, 27.0f, 2.0f)
        this.shape9_1.addBox(0.0f, 0.0f, 0.0f, 1, 1, 38, 0.0f)
        this.shape8_1 = ModelRenderer(this, 89, 86)
        this.shape8_1.setRotationPoint(0.0f, 28.0f, 3.0f)
        this.shape8_1.addBox(0.0f, 0.0f, 0.0f, 1, 1, 36, 0.0f)
        this.shape2 = ModelRenderer(this, 0, 0)
        this.shape2.setRotationPoint(0.0f, 2.0f, 2.0f)
        this.shape2.addBox(0.0f, 0.0f, 0.0f, 1, 25, 38, 0.0f)
        this.shape3 = ModelRenderer(this, 0, 0)
        this.shape3.setRotationPoint(0.0f, 3.0f, 0.0f)
        this.shape3.addBox(0.0f, 0.0f, 0.0f, 1, 23, 1, 0.0f)
        this.shape5_1 = ModelRenderer(this, 20, 0)
        this.shape5_1.setRotationPoint(0.0f, 2.0f, 40.0f)
        this.shape5_1.addBox(0.0f, 0.0f, 0.0f, 1, 25, 1, 0.0f)
        this.shape8 = ModelRenderer(this, 85, 0)
        this.shape8.setRotationPoint(0.0f, 0.0f, 3.0f)
        this.shape8.addBox(0.0f, 0.0f, 0.0f, 1, 1, 36, 0.0f)
    }

    override fun render(entity: Entity?, f: Float, f1: Float, f2: Float, f3: Float, f4: Float, f5: Float) {
        GL11.glPushMatrix()
        GL11.glScaled(1.0 / modelScale[0], 1.0 / modelScale[1], 1.0 / modelScale[2])
        this.shape5.render(f5)
        this.shape9.render(f5)
        this.shape3_1.render(f5)
        this.shape9_1.render(f5)
        this.shape8_1.render(f5)
        this.shape2.render(f5)
        this.shape3.render(f5)
        this.shape5_1.render(f5)
        this.shape8.render(f5)
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
}
