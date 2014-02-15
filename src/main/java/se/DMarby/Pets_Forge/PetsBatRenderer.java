package se.DMarby.Pets_Forge;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class PetsBatRenderer extends RenderLiving
{
    private static final ResourceLocation enderDragonExplodingTextures = new ResourceLocation("textures/entity/enderdragon/dragon_exploding.png");
    private static final ResourceLocation enderDragonCrystalBeamTextures = new ResourceLocation("textures/entity/endercrystal/endercrystal_beam.png");
    private static final ResourceLocation enderDragonEyesTextures = new ResourceLocation("textures/entity/enderdragon/dragon_eyes.png");
    private static final ResourceLocation enderDragonTextures = new ResourceLocation("textures/entity/enderdragon/dragon.png");
    private static final ResourceLocation batTextures = new ResourceLocation("textures/entity/bat.png");
    private int renderedBatSize;

    public PetsBatRenderer()
    {
        super(new PetModel(0.0F), 0.25F);

        this.renderManager = RenderManager.instance;

        setRenderPassModel(this.mainModel);
    }

    protected ResourceLocation getEntityTexture(Entity entity) {
        if (((EntityBat)entity).getCustomNameTag().startsWith("§b§b§r")) {
            return enderDragonTextures;
        }
        return batTextures;
    }

    protected ResourceLocation getEntityTexture(EntityBat entity) {
        if (entity.getCustomNameTag().startsWith("§b§b§r")) {
            return enderDragonTextures;
        }
        return batTextures;
    }

    protected void renderLivingAt(EntityBat paramEntityBat, double paramDouble1, double paramDouble2, double paramDouble3)
    {
        if (!paramEntityBat.getCustomNameTag().startsWith("§b§b§r")) {
            super.renderLivingAt(paramEntityBat, paramDouble1, paramDouble2, paramDouble3);
        }
    }

    protected void rotateCorpse(EntityBat paramEntityBat, float paramFloat1, float paramFloat2, float paramFloat3)
    {
        if (!paramEntityBat.getCustomNameTag().startsWith("§b§b§r")) {
            if (!paramEntityBat.getIsBatHanging())
                GL11.glTranslatef(0.0F, MathHelper.cos(paramFloat1 * 0.3F) * 0.1F, 0.0F);
            else {
                GL11.glTranslatef(0.0F, -0.1F, 0.0F);
            }
            super.rotateCorpse(paramEntityBat, paramFloat1, paramFloat2, paramFloat3);
        }
    }

    protected void renderModel(EntityBat paramEntityBat, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6)
    {
        if (paramEntityBat.getCustomNameTag().startsWith("§b§b§r")) {
            bindEntityTexture(paramEntityBat);
            this.mainModel.render(paramEntityBat, paramFloat1, paramFloat2, paramFloat3, paramFloat4, paramFloat5, paramFloat6);

            if (paramEntityBat.hurtTime > 0) {
                GL11.glDepthFunc(514);
                GL11.glDisable(3553);
                GL11.glEnable(3042);
                GL11.glBlendFunc(770, 771);
                GL11.glColor4f(1.0F, 0.0F, 0.0F, 0.5F);
                this.mainModel.render(paramEntityBat, paramFloat1, paramFloat2, paramFloat3, paramFloat4, paramFloat5, paramFloat6);
                GL11.glEnable(3553);
                GL11.glDisable(3042);
                GL11.glDepthFunc(515);
            }
        }
    }

    public void doRender(EntityBat paramEntityBat, double paramDouble1, double paramDouble2, double paramDouble3, float paramFloat1, float paramFloat2)
    {
        super.doRender(paramEntityBat, paramDouble1, paramDouble2, paramDouble3, paramFloat1, paramFloat2);
    }

    protected void preRenderCallback(EntityBat paramEntityBat, float paramFloat)
    {
        if (!paramEntityBat.getCustomNameTag().startsWith("§b§b§r")) {
            GL11.glScalef(0.35F, 0.35F, 0.35F);
        }
    }


    protected int shouldRenderPass(EntityBat paramEntityBat, int paramInt, float paramFloat)
    {
        if (paramEntityBat.getCustomNameTag().startsWith("§b§b§r")) {

            if (paramInt == 1) {
                GL11.glDepthFunc(515);
            }
            if (paramInt != 0) return -1;

            bindTexture(enderDragonEyesTextures);
            GL11.glEnable(3042);
            GL11.glDisable(3008);
            GL11.glBlendFunc(1, 1);
            GL11.glDisable(2896);
            GL11.glDepthFunc(514);

            int i = 61680;
            int j = i % 65536;
            int k = i / 65536;

            OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, j / 1.0F, k / 1.0F);

            GL11.glEnable(2896);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            return 1;
        }
        return -1;
    }
}