package info.devels.jtte.render

import info.devels.jtte.core.*
import info.devels.jtte.models.TerminalModel
import net.minecraft.client.Minecraft
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraftforge.client.IItemRenderer
import net.minecraftforge.client.IItemRenderer.ItemRenderType.*
import org.lwjgl.opengl.GL11
import org.lwjgl.opengl.GL12


class ItemTerminalRenderer : IItemRenderer {
    var texture = ResourceLocation(modId, String.format("textures/entity/%s.png", "terminal"))
    var model = TerminalModel()

    protected fun bindTexture(texture: ResourceLocation) {
        val textureManager = Minecraft.getMinecraft().textureManager
        textureManager?.bindTexture(texture)
    }

    override fun handleRenderType(item: ItemStack, type: IItemRenderer.ItemRenderType) = true

    override fun shouldUseRenderHelper(type: IItemRenderer.ItemRenderType, item: ItemStack, helper: IItemRenderer.ItemRendererHelper): Boolean {
        return type == IItemRenderer.ItemRenderType.ENTITY || type == IItemRenderer.ItemRenderType.INVENTORY
    }

    override fun renderItem(type: IItemRenderer.ItemRenderType, item: ItemStack, vararg data: Any) {
        this.bindTexture(texture)

        GL11.glPushMatrix()
        GL11.glEnable(GL12.GL_RESCALE_NORMAL)
        GL11.glEnable(GL11.GL_BLEND)
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA)
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f)
        GL11.glScalef(1.0f, -1.0f, -1.0f)

        when(type) {
            EQUIPPED_FIRST_PERSON -> {
                GL11.glScalef(0.4f, 0.4f, 0.4f)
                GL11.glTranslatef(0.25f, -2f, 1f)
                GL11.glRotatef(155f, 0f, 1f, 0f)
            }
            INVENTORY -> {
                GL11.glScalef(0.6f, 0.6f, 0.6f)
                GL11.glTranslatef(0f, -0.4f, 0f)
                GL11.glRotatef(-45f, 0f, 1f, 0f)
            }
            ENTITY -> {
                GL11.glScalef(0.55f, 0.55f, 0.55f)
                GL11.glTranslatef(0.50f, -0.50f, 0.00f)
                GL11.glRotatef(-90.00f, 0f, 1f, 0f)
            }
            EQUIPPED -> {
                GL11.glScalef(0.6f, 0.6f, 0.6f)
                GL11.glTranslatef(1f, -0.7f, -0.55f)
            }
            FIRST_PERSON_MAP -> {
                // ???
            }
        }

        model.renderAll()

        GL11.glDisable(GL11.GL_BLEND)
        GL11.glDisable(GL12.GL_RESCALE_NORMAL)
        GL11.glPopMatrix()
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f)
    }
}
