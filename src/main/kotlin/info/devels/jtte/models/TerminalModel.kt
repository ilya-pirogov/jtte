package info.devels.jtte.models

import net.minecraft.client.model.ModelRenderer
import net.minecraft.client.renderer.Tessellator
import net.minecraft.entity.Entity
import org.lwjgl.opengl.GL11

/**
 * Hub - Kini
 * Created using Tabula 4.1.1
 */
class TerminalModel : BaseTileModel() {
    var modelScale = doubleArrayOf(2.0, 2.0, 2.0)
    var head: ModelRenderer
    var a7: ModelRenderer
    var a8: ModelRenderer
    var a9: ModelRenderer
    var back: ModelRenderer
    var front: ModelRenderer
    var f1: ModelRenderer
    var f2: ModelRenderer
    var f3: ModelRenderer
    var f4: ModelRenderer
    var a1: ModelRenderer
    var a2: ModelRenderer
    var a3: ModelRenderer
    var a4: ModelRenderer
    var a5: ModelRenderer
    var a6: ModelRenderer
    var stub: ModelRenderer

    var screenOffsetX = 0.0

    init {
        this.textureWidth = 511
        this.textureHeight = 202
        this.f3 = ModelRenderer(this, 75, 180)
        this.f3.setRotationPoint(-15.0f, -4.0f, 0.7f)
        this.f3.addBox(0.0f, 0.0f, 0.0f, 30, 1, 1, 0.0f)
        this.f2 = ModelRenderer(this, 135, 0)
        this.f2.setRotationPoint(-16.0f, -3.0f, 0.7f)
        this.f2.addBox(0.0f, 0.0f, 0.0f, 1, 32, 1, 0.0f)
        this.a4 = ModelRenderer(this, 20, 45)
        this.a4.setRotationPoint(0.0f, 46.5f, 0.0f)
        this.a4.addBox(-15.5f, 0.0f, -15.5f, 31, 1, 31, 0.0f)
        this.a6 = ModelRenderer(this, 67, 49)
        this.a6.setRotationPoint(-15.8f, 42.0f, 0.0f)
        this.a6.addBox(0.5f, -5.9f, -15.0f, 1, 11, 30, 0.0f)
        this.a3 = ModelRenderer(this, 67, 78)
        this.a3.setRotationPoint(0.0f, 42.0f, -15.8f)
        this.a3.addBox(-15.0f, -5.9f, 0.5f, 30, 11, 1, 0.0f)
        this.a7 = ModelRenderer(this, 0, 9)
        this.a7.setRotationPoint(0.0f, -1.5f, 12.7f)
        this.a7.addBox(-17.0f, -17.0f, 0.0f, 14, 14, 2, 0.0f)
        this.setRotateAngle(a7, 0.0f, -0.003490658503988659f, 0.7853981633974483f)
        this.f4 = ModelRenderer(this, 75, 180)
        this.f4.setRotationPoint(-15.0f, 29.0f, 0.7f)
        this.f4.addBox(0.0f, 0.0f, 0.0f, 30, 1, 1, 0.0f)
        this.head = ModelRenderer(this, 67, 91)
        this.head.setRotationPoint(0.0f, -15.0f, 0.0f)
        this.head.addBox(-8.0f, -8.0f, -8.0f, 16, 16, 16, -1.5f)
        this.f1 = ModelRenderer(this, 135, 0)
        this.f1.setRotationPoint(15.0f, -3.0f, 0.7f)
        this.f1.addBox(0.0f, 0.0f, 0.0f, 1, 32, 1, 0.0f)
        this.front = ModelRenderer(this, 0, 167)
        this.front.setRotationPoint(0.0f, 1.0f, 1.0f)
        this.front.addBox(-16.0f, -5.0f, 0.0f, 32, 34, 1, 0.0f)
        this.a5 = ModelRenderer(this, 67, 78)
        this.a5.setRotationPoint(0.0f, 42.0f, 14.8f)
        this.a5.addBox(-15.0f, -5.9f, -0.5f, 30, 11, 1, 0.0f)
        this.back = ModelRenderer(this, 0, 132)
        this.back.setRotationPoint(0.0f, 1.0f, 0.7f)
        this.back.addBox(-15.0f, -4.0f, 0.0f, 30, 32, 1, 0.0f)
        this.a2 = ModelRenderer(this, 67, 49)
        this.a2.setRotationPoint(14.8f, 42.0f, 0.0f)
        this.a2.addBox(-0.5f, -5.9f, -15.0f, 1, 11, 30, 0.0f)
        this.a8 = ModelRenderer(this, 0, 45)
        this.a8.setRotationPoint(0.0f, -1.0f, 14.6f)
        this.a8.addBox(-22.0f, -22.0f, 0.0f, 23, 23, 1, -0.2f)
        this.setRotateAngle(a8, 0.0f, 0.0f, 0.7853981633974483f)
        this.a9 = ModelRenderer(this, 0, 78)
        this.a9.setRotationPoint(0.0f, -1.0f, 15.0f)
        this.a9.addBox(-16.0f, -15.0f, 0.0f, 32, 52, 1, 0.0f)
        this.a1 = ModelRenderer(this, 0, 0)
        this.a1.setRotationPoint(-16.0f, -17.0f, -16.0f)
        this.a1.addBox(0.0f, 53.0f, 0.0f, 32, 12, 32, 0.0f)
        this.stub = ModelRenderer(this, 0, 0)
        this.stub.setRotationPoint(0f, 0f, 0f)
        this.stub.addBox(0.0f, 0.0f, 0.0f, 32, 32, 32, 0.0f)
    }

    override fun render(entity: Entity?, f: Float, f1: Float, f2: Float, f3: Float, f4: Float, f5: Float) {
        GL11.glPushMatrix()
        GL11.glEnable(GL11.GL_BLEND)
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA)

        GL11.glScaled(1.0 / modelScale[0], 1.0 / modelScale[1], 1.0 / modelScale[2])

        this.a4.render(f5)
        this.a6.render(f5)
        this.a3.render(f5)
        this.a7.render(f5)
        this.a5.render(f5)
        this.a2.render(f5)
        GL11.glPushMatrix()
        GL11.glTranslatef(this.a8.offsetX, this.a8.offsetY, this.a8.offsetZ)
        GL11.glTranslatef(this.a8.rotationPointX * f5, this.a8.rotationPointY * f5, this.a8.rotationPointZ * f5)
        GL11.glScaled(1.0, 1.0, 0.5)
        GL11.glTranslatef(-this.a8.offsetX, -this.a8.offsetY, -this.a8.offsetZ)
        GL11.glTranslatef(-this.a8.rotationPointX * f5, -this.a8.rotationPointY * f5, -this.a8.rotationPointZ * f5)
        this.a8.render(f5)
        GL11.glPopMatrix()
        this.a9.render(f5)
        this.a1.render(f5)

        GL11.glPushMatrix()
        GL11.glTranslatef(this.front.offsetX, this.front.offsetY, this.front.offsetZ)
        GL11.glTranslatef(this.front.rotationPointX * f5, this.front.rotationPointY * f5, this.front.rotationPointZ * f5)
        GL11.glScaled(1.0, 1.0, 0.2)
        GL11.glTranslatef(-this.front.offsetX, -this.front.offsetY, -this.front.offsetZ)
        GL11.glTranslatef(-this.front.rotationPointX * f5, -this.front.rotationPointY * f5, -this.front.rotationPointZ * f5)

        back.setTextureOffset(466, 132)

        this.front.render(f5)
        GL11.glPopMatrix()
        GL11.glPushMatrix()

        val t = Tessellator.instance;
        t.startDrawingQuads()

        val u = (screenOffsetX / textureWidth)
        val U = u + (30.0 / textureWidth)
        val v = (132.0 / textureHeight)
        val V = v + (32.0 / textureHeight)

        t.addVertexWithUV(-1.0, -0.19, 0.05, u, v)
        t.addVertexWithUV(-1.0, 1.82, 0.05, u, V)
        t.addVertexWithUV(1.0, 1.82, 0.05, U, V)
        t.addVertexWithUV(1.0, -0.19, 0.05, U, v)

        t.draw()

        GL11.glPopMatrix()
        GL11.glDisable(GL11.GL_BLEND)
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA)

        GL11.glPushMatrix()
        GL11.glTranslatef(this.f3.offsetX, this.f3.offsetY, this.f3.offsetZ)
        GL11.glTranslatef(this.f3.rotationPointX * f5, this.f3.rotationPointY * f5, this.f3.rotationPointZ * f5)
        GL11.glScaled(1.0, 1.0, 0.3)
        GL11.glTranslatef(-this.f3.offsetX, -this.f3.offsetY, -this.f3.offsetZ)
        GL11.glTranslatef(-this.f3.rotationPointX * f5, -this.f3.rotationPointY * f5, -this.f3.rotationPointZ * f5)
        this.f3.render(f5)
        GL11.glPopMatrix()
        GL11.glPushMatrix()
        GL11.glTranslatef(this.f2.offsetX, this.f2.offsetY, this.f2.offsetZ)
        GL11.glTranslatef(this.f2.rotationPointX * f5, this.f2.rotationPointY * f5, this.f2.rotationPointZ * f5)
        GL11.glScaled(1.0, 1.0, 0.3)
        GL11.glTranslatef(-this.f2.offsetX, -this.f2.offsetY, -this.f2.offsetZ)
        GL11.glTranslatef(-this.f2.rotationPointX * f5, -this.f2.rotationPointY * f5, -this.f2.rotationPointZ * f5)
        this.f2.render(f5)
        GL11.glPopMatrix()

        GL11.glPushMatrix()
        GL11.glTranslatef(this.f4.offsetX, this.f4.offsetY, this.f4.offsetZ)
        GL11.glTranslatef(this.f4.rotationPointX * f5, this.f4.rotationPointY * f5, this.f4.rotationPointZ * f5)
        GL11.glScaled(1.0, 1.0, 0.3)
        GL11.glTranslatef(-this.f4.offsetX, -this.f4.offsetY, -this.f4.offsetZ)
        GL11.glTranslatef(-this.f4.rotationPointX * f5, -this.f4.rotationPointY * f5, -this.f4.rotationPointZ * f5)
        this.f4.render(f5)
        GL11.glTranslatef(this.f1.offsetX, this.f1.offsetY, this.f1.offsetZ)
        GL11.glTranslatef(this.f1.rotationPointX * f5, this.f1.rotationPointY * f5, this.f1.rotationPointZ * f5)
        GL11.glScaled(1.0, 1.0, 0.3)
        GL11.glTranslatef(-this.f1.offsetX, -this.f1.offsetY, -this.f1.offsetZ)
        GL11.glTranslatef(-this.f1.rotationPointX * f5, -this.f1.rotationPointY * f5, -this.f1.rotationPointZ * f5)
        this.f1.render(f5)
        GL11.glPopMatrix()

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
