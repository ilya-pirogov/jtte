package info.devels.jtte.render

import info.devels.jtte.core.*
import info.devels.jtte.models.ClockModel
import net.minecraft.client.Minecraft
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraftforge.client.IItemRenderer
import net.minecraftforge.client.IItemRenderer.ItemRenderType.FIRST_PERSON_MAP
import org.lwjgl.opengl.GL11
import org.lwjgl.opengl.GL12
import net.minecraftforge.client.IItemRenderer.ItemRenderType.*


class ItemClockRenderer : IItemRenderer {
    var texture = ResourceLocation(modId, String.format("textures/items/%s.png", "clock"))
    var model = ClockModel()

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

        val world = Minecraft.getMinecraft().theWorld
        val time = (world.worldTime + (dayLength / 4)) % dayLength
        val hours = (time / 1000).toInt();
        val minutes = (((time % 1000) * 60) / 1000).toInt()
        model.text = "%02d:%02d".format(hours, minutes)

        GL11.glPushMatrix()

        GL11.glEnable(GL12.GL_RESCALE_NORMAL)
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f)
        GL11.glScalef(1.0f, -1.0f, -1.0f)

        when(type) {
            EQUIPPED_FIRST_PERSON -> {
                GL11.glScalef(-1.0f, -1.0f, 1.0f)

                GL11.glScalef(0.65f, 0.65f, 0.65f)
                GL11.glTranslatef(-1.30f, 0.10f, -0.50f)
                GL11.glRotatef(-70.00f, 1f, 0f, 0f)
                GL11.glRotatef(-30.00f, 0f, 1f, 0f)
                GL11.glRotatef(-50.00f, 0f, 0f, 1f)
            }
            INVENTORY -> {
                GL11.glScalef(1.10f, 1.10f, 1.10f)
                GL11.glTranslatef(-0.85f, -0.20f, 0.00f)
                GL11.glRotatef(60.00f, 0f, 1f, 0f)
            }
            ENTITY -> {
                GL11.glScalef(0.70f, 0.70f, 0.70f)
                GL11.glTranslatef(-0.05f, -0.50f, -0.65f)
            }
            EQUIPPED -> {
                GL11.glScalef(0.60f, 0.60f, 0.60f)
                GL11.glTranslatef(1.45f, -1.05f, 0.20f)
                GL11.glRotatef(-110.00f, 0f, 1f, 0f)
            }
            FIRST_PERSON_MAP -> {
                // ???
            }
        }

        if (type == EQUIPPED_FIRST_PERSON) {
            GL11.glPushMatrix()
            GL11.glRotatef(-180.00f, 1f, 0f, 0f)
            GL11.glTranslatef(0.00f, -1.00f, -1.30f)
        }

        model.renderTime(time.toInt(), 0.0625f)

        if (type == EQUIPPED_FIRST_PERSON) {
            GL11.glPopMatrix()
        }

        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f)

        GL11.glDisable(GL11.GL_BLEND)
        GL11.glDisable(GL12.GL_RESCALE_NORMAL)
        GL11.glPopMatrix()
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f)
    }
}
