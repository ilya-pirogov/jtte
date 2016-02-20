package info.devels.jtte.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

/**
 * Beacon_mini - Kini
 * Created using Tabula 4.1.1
 */
public class BeaconHandModel extends BaseTileModel {
    public double[] modelScale = new double[] { 2.0D, 2.0D, 2.0D };
    public ModelRenderer shape2;
    public ModelRenderer shape3;
    public ModelRenderer shape3_1;
    public ModelRenderer shape5;
    public ModelRenderer shape6;
    public ModelRenderer shape6_1;
    public ModelRenderer shape8;
    public ModelRenderer shape9;
    public ModelRenderer shape10;
    public ModelRenderer shape11;
    public ModelRenderer cube;

    public float cubeAngleY = 0;

    public BeaconHandModel() {
        this.textureWidth = 74;
        this.textureHeight = 52;
        this.shape3 = new ModelRenderer(this, 0, 11);
        this.shape3.setRotationPoint(-7.0F, 25.0F, -0.5F);
        this.shape3.addBox(0.0F, 0.0F, 0.0F, 3, 6, 1, 0.0F);
        this.cube = new ModelRenderer(this, 50, 0);
        this.cube.setRotationPoint(0.0F, 7.0F, -0.5F);
        this.cube.addBox(-5.0F, -5.0F, 0.0F, 10, 10, 1, 0.0F);
        this.setRotateAngle(cube, 0.0F, 0.0F, 0.7853981633974483F);
        this.shape2 = new ModelRenderer(this, 0, 0);
        this.shape2.setRotationPoint(-8.0F, 31.0F, 0.0F);
        this.shape2.addBox(0.0F, 0.0F, 0.0F, 16, 5, 1, 0.0F);
        this.shape10 = new ModelRenderer(this, 0, 45);
        this.shape10.setRotationPoint(4.0F, 15.0F, -0.5F);
        this.shape10.addBox(0.0F, 0.0F, 0.0F, 1, 3, 1, 0.0F);
        this.shape3_1 = new ModelRenderer(this, 0, 11);
        this.shape3_1.setRotationPoint(4.0F, 25.0F, -0.5F);
        this.shape3_1.addBox(0.0F, 0.0F, 0.0F, 3, 6, 1, 0.0F);
        this.shape11 = new ModelRenderer(this, 15, 45);
        this.shape11.setRotationPoint(-1.0F, 15.0F, -0.5F);
        this.shape11.addBox(0.0F, 0.0F, 0.0F, 2, 3, 1, 0.0F);
        this.shape6 = new ModelRenderer(this, 0, 33);
        this.shape6.setRotationPoint(-6.0F, 18.0F, -0.5F);
        this.shape6.addBox(0.0F, 0.0F, 0.0F, 2, 7, 1, 0.0F);
        this.shape6_1 = new ModelRenderer(this, 0, 33);
        this.shape6_1.setRotationPoint(4.0F, 18.0F, -0.5F);
        this.shape6_1.addBox(0.0F, 0.0F, 0.0F, 2, 7, 1, 0.0F);
        this.shape5 = new ModelRenderer(this, 0, 21);
        this.shape5.setRotationPoint(-3.0F, 25.0F, -0.5F);
        this.shape5.addBox(0.0F, 0.0F, 0.0F, 6, 6, 1, 0.0F);
        this.shape8 = new ModelRenderer(this, 15, 33);
        this.shape8.setRotationPoint(-2.0F, 18.0F, -0.5F);
        this.shape8.addBox(0.0F, 0.0F, 0.0F, 4, 7, 1, 0.0F);
        this.shape9 = new ModelRenderer(this, 0, 45);
        this.shape9.mirror = true;
        this.shape9.setRotationPoint(-5.0F, 15.0F, -0.5F);
        this.shape9.addBox(0.0F, 0.0F, 0.0F, 1, 3, 1, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        GL11.glPushMatrix();
        GL11.glScaled(1D / modelScale[0], 1D / modelScale[1], 1D / modelScale[2]);
        this.shape3.render(f5);

        GL11.glPushMatrix();
        GL11.glRotatef((float) ((cubeAngleY * 180) / Math.PI), 0, 1, 0);
        this.cube.render(f5);
        GL11.glPopMatrix();

        this.shape2.render(f5);
        this.shape10.render(f5);
        this.shape3_1.render(f5);
        this.shape11.render(f5);
        this.shape6.render(f5);
        this.shape6_1.render(f5);
        this.shape5.render(f5);
        this.shape8.render(f5);
        this.shape9.render(f5);
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
        this.cubeAngleY = y;
    }
}