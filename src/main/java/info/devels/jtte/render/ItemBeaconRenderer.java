package info.devels.jtte.render;

import info.devels.jtte.JTTE;
import info.devels.jtte.core.JTTEProps;
import info.devels.jtte.models.BeaconHandModel;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;


public class ItemBeaconRenderer implements IItemRenderer {
    TileEntityBeaconRenderer render;
    public float tickOffset;
    ResourceLocation texture;
    BeaconHandModel model;
    public double rtCube = (Math.PI / JTTEProps.ticksCube);
    public float angleCube = 0;

    public ItemBeaconRenderer() {
        this.texture = new ResourceLocation(JTTE.modId, String.format("textures/items/%s.png", "beaconHand"));
        this.model = new BeaconHandModel();
    }

    protected void bindTexture(ResourceLocation texture)
    {
        TextureManager texturemanager = Minecraft.getMinecraft().getTextureManager();
        if (texturemanager != null)
        {
            texturemanager.bindTexture(texture);
        }
    }

    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type) {
        return true;
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
        return type == ItemRenderType.ENTITY || type == ItemRenderType.INVENTORY;
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
        this.bindTexture(texture);

        GL11.glPushMatrix();
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

        GL11.glScalef(1.0F, -1.0F, -1.0F);

        if (type == ItemRenderType.EQUIPPED_FIRST_PERSON) {
            GL11.glTranslatef(0.1F, -1.2F, 0.4F);
        } else if (type == ItemRenderType.INVENTORY) {
            GL11.glScalef(1.8F, 1.8F, 1.8F);
            GL11.glTranslatef(0F, -0.6F, 0F);
            GL11.glRotatef(-45F, 0, 1F, 0);
        } else if (type == ItemRenderType.ENTITY) {
            GL11.glScalef(2.4F, 2.4F, 2.4F);
            GL11.glTranslatef(0F, -1F, 0F);
        } else if (type == ItemRenderType.EQUIPPED) {
            GL11.glTranslatef(0.4F, -1.3F, 0F);
        }

        model.rotateCube((float) (angleCube + rtCube * tickOffset));
        model.renderAll();

        GL11.glDisable(GL11.GL_BLEND);
        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        GL11.glPopMatrix();
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    }

    public void tick() {
        angleCube += rtCube;
        if (angleCube > Math.PI) {
            angleCube -= 2 * Math.PI;
        }
    }
}
