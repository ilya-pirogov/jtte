package info.devels.jtte.render

import info.devels.jtte.core.modId
import info.devels.jtte.models.ClockModel
import net.minecraft.client.Minecraft
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraftforge.client.IItemRenderer
import net.minecraftforge.client.IItemRenderer.ItemRenderType.FIRST_PERSON_MAP
import org.lwjgl.opengl.GL11
import org.lwjgl.opengl.GL12


class ItemClockRenderer : IItemRenderer {
    var texture = ResourceLocation(modId, String.format("textures/items/%s.png", "clock"))
    var model = ClockModel()

    protected fun bindTexture(texture: ResourceLocation) {
        val textureManager = Minecraft.getMinecraft().textureManager
        textureManager?.bindTexture(texture)
    }

    override fun handleRenderType(item: ItemStack, type: IItemRenderer.ItemRenderType) = type !== FIRST_PERSON_MAP

    override fun shouldUseRenderHelper(type: IItemRenderer.ItemRenderType, item: ItemStack, helper: IItemRenderer.ItemRendererHelper): Boolean {
        return type == IItemRenderer.ItemRenderType.ENTITY || type == IItemRenderer.ItemRenderType.INVENTORY
    }

    override fun renderItem(type: IItemRenderer.ItemRenderType, item: ItemStack, vararg data: Any) {
        this.bindTexture(texture)

        GL11.glPushMatrix()
        GL11.glEnable(GL12.GL_RESCALE_NORMAL)
//        GL11.glEnable(GL11.GL_BLEND)
//        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA)
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f)

//        GL11.glScalef(1.0f, -1.0f, -1.0f)

//        when(type) {
//            EQUIPPED_FIRST_PERSON -> {
//                GL11.glTranslatef(0.1f, -1.2f, 0.4f)
//            }
//            INVENTORY -> {
//                GL11.glScalef(1.8f, 1.8f, 1.8f)
//                GL11.glTranslatef(0f, -0.6f, 0f)
//                GL11.glRotatef(-45f, 0f, 1f, 0f)
//            }
//            ENTITY -> {
//                GL11.glScalef(2.4f, 2.4f, 2.4f)
//                GL11.glTranslatef(0f, -1f, 0f)
//            }
//            EQUIPPED -> {
//                GL11.glTranslatef(0.4f, -1.3f, 0f)
//            }
//            FIRST_PERSON_MAP -> {
//                // ???
//            }
//        }

        model.renderAll()

        GL11.glDisable(GL11.GL_BLEND)
        GL11.glDisable(GL12.GL_RESCALE_NORMAL)
        GL11.glPopMatrix()
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f)
    }
}
