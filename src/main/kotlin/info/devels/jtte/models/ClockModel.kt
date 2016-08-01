package info.devels.jtte.models

import net.minecraft.client.Minecraft
import net.minecraft.client.model.ModelRenderer
import net.minecraft.entity.Entity
import org.lwjgl.opengl.GL11

/**
 * Clock - Kini
 * Created using Tabula 4.1.1
 */
class ClockModel : BaseTileModel() {
    var modelScale = doubleArrayOf(2.0, 2.0, 2.0)
    var faceMorning: ModelRenderer
    var faceDay: ModelRenderer
    var faceEvening: ModelRenderer
    var faceNight: ModelRenderer
    var shape3: ModelRenderer
    var shape5: ModelRenderer
    var shape3_1: ModelRenderer
    var shape5_1: ModelRenderer
    var shape8: ModelRenderer
    var shape9: ModelRenderer
    var shape8_1: ModelRenderer
    var shape9_1: ModelRenderer
    var text = ""

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

        this.faceMorning = ModelRenderer(this, 0, 0)
        this.faceMorning.setRotationPoint(0.0f, 2.0f, 2.0f)
        this.faceMorning.addBox(0.0f, 0.0f, 0.0f, 1, 25, 38, 0.0f)

        this.faceDay = ModelRenderer(this, 0, 25)
        this.faceDay.setRotationPoint(0.0f, 2.0f, 2.0f)
        this.faceDay.addBox(0.0f, 0.0f, 0.0f, 1, 25, 38, 0.0f)

        this.faceEvening = ModelRenderer(this, 0, 50)
        this.faceEvening.setRotationPoint(0.0f, 2.0f, 2.0f)
        this.faceEvening.addBox(0.0f, 0.0f, 0.0f, 1, 25, 38, 0.0f)

        this.faceNight = ModelRenderer(this, 0, 75)
        this.faceNight.setRotationPoint(0.0f, 2.0f, 2.0f)
        this.faceNight.addBox(0.0f, 0.0f, 0.0f, 1, 25, 38, 0.0f)

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

    fun renderTime(time: Int, f5: Float) {
        GL11.glPushMatrix()
        GL11.glScaled(1.0 / modelScale[0], 1.0 / modelScale[1], 1.0 / modelScale[2])

        this.shape5.render(f5)
        this.shape9.render(f5)
        this.shape3_1.render(f5)
        this.shape9_1.render(f5)
        this.shape8_1.render(f5)
        this.shape3.render(f5)
        this.shape5_1.render(f5)
        this.shape8.render(f5)

        when(time) {
            in 0..6000 -> faceNight.render(0.0625f)
            in 6001..12000 -> faceMorning.render(0.0625f)
            in 12001..18000 -> faceDay.render(0.0625f)
            in 18001..24000 -> faceEvening.render(0.0625f)
        }

        GL11.glPushMatrix()
        GL11.glNormal3f(0.0f, 0.0f, -1.0f)

        GL11.glScalef(0.09f, 0.09f, 0.09f)
        GL11.glTranslatef(0.70f, 8f, 2.05f)
        GL11.glRotatef(-90f, 0f, 1f, 0f)
        GL11.glDepthMask(false)

        Minecraft.getMinecraft().fontRenderer.drawString(text, 0, 0, 0xF65700)
        GL11.glDepthMask(true)
        GL11.glPopMatrix()
        GL11.glPopMatrix()
    }

    override fun render(entity: Entity?, f: Float, f1: Float, f2: Float, f3: Float, f4: Float, f5: Float) {
        renderTime(0, f5)
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
