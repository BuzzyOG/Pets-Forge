package se.DMarby.Pets_Forge;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class PetModel extends ModelBase
{
    private ModelRenderer head;
    private ModelRenderer spine;
    private ModelRenderer jaw;
    private ModelRenderer body;
    private ModelRenderer rearLeg;
    private ModelRenderer frontLeg;
    private ModelRenderer rearLegTip;
    private ModelRenderer frontLegTip;
    private ModelRenderer rearFoot;
    private ModelRenderer frontFoot;
    private ModelRenderer wing;
    private ModelRenderer wingTip;

    private ModelRenderer batHead;
    private ModelRenderer batBody;
    private ModelRenderer batRightWing;
    private ModelRenderer batLeftWing;
    private ModelRenderer batOuterRightWing;
    private ModelRenderer batOuterLeftWing;
    private float partialTicks;
    private float animTime = 0F;
    private float prevAnimTime = 0F;

    public static final float HEAD_SIZE = 2F;
    public static final float HEAD_ROTATION_FACTOR = 1 / HEAD_SIZE;

    public PetModel(float p_i1169_1_)
    {
        this.textureWidth = 256;
        this.textureHeight = 256;

        setTextureOffset("body.body", 0, 0);
        setTextureOffset("wing.skin", -56, 88);
        setTextureOffset("wingtip.skin", -56, 144);
        setTextureOffset("rearleg.main", 0, 0);
        setTextureOffset("rearfoot.main", 112, 0);
        setTextureOffset("rearlegtip.main", 196, 0);
        setTextureOffset("head.upperhead", 112, 30);
        setTextureOffset("wing.bone", 112, 88);
        setTextureOffset("head.upperlip", 176, 44);
        setTextureOffset("jaw.jaw", 176, 65);
        setTextureOffset("frontleg.main", 112, 104);
        setTextureOffset("wingtip.bone", 112, 136);
        setTextureOffset("frontfoot.main", 144, 104);
        setTextureOffset("neck.box", 192, 104);
        setTextureOffset("frontlegtip.main", 226, 138);
        setTextureOffset("body.scale", 220, 53);
        setTextureOffset("head.scale", 0, 0);
        setTextureOffset("neck.scale", 48, 0);
        setTextureOffset("head.nostril", 112, 0);

        float f = -16.0F;
        this.head = new ModelRenderer(this, "head");
        this.head.addBox("upperlip", -6.0F, -1.0F, -8.0F + f, 12, 5, 16);
        this.head.addBox("upperhead", -8.0F, -8.0F, 6.0F + f, 16, 16, 16);
        this.head.mirror = true;
        this.head.addBox("scale", -5.0F, -12.0F, 12.0F + f, 2, 4, 6);
        this.head.addBox("nostril", -5.0F, -3.0F, -6.0F + f, 2, 2, 4);
        this.head.mirror = false;
        this.head.addBox("scale", 3.0F, -12.0F, 12.0F + f, 2, 4, 6);
        this.head.addBox("nostril", 3.0F, -3.0F, -6.0F + f, 2, 2, 4);

        this.jaw = new ModelRenderer(this, "jaw");
        this.jaw.setRotationPoint(0.0F, 4.0F, 8.0F + f);
        this.jaw.addBox("jaw", -6.0F, 0.0F, -16.0F, 12, 4, 16);
        this.head.addChild(this.jaw);


        this.spine = new ModelRenderer(this, "neck");
        this.spine.addBox("box", -5.0F, -5.0F, -5.0F, 10, 10, 10);
        this.spine.addBox("scale", -1.0F, -9.0F, -3.0F, 2, 4, 6);

        this.body = new ModelRenderer(this, "body");
        this.body.setRotationPoint(0.0F, 4.0F, 8.0F);
        this.body.addBox("body", -12.0F, 0.0F, -16.0F, 24, 24, 64);
        this.body.addBox("scale", -1.0F, -6.0F, -10.0F, 2, 6, 12);
        this.body.addBox("scale", -1.0F, -6.0F, 10.0F, 2, 6, 12);
        this.body.addBox("scale", -1.0F, -6.0F, 30.0F, 2, 6, 12);

        this.wing = new ModelRenderer(this, "wing");
        this.wing.setRotationPoint(-12.0F, 5.0F, 2.0F);
        this.wing.addBox("bone", -56.0F, -4.0F, -4.0F, 56, 8, 8);
        this.wing.addBox("skin", -56.0F, 0.0F, 2.0F, 56, 0, 56);
        this.wingTip = new ModelRenderer(this, "wingtip");
        this.wingTip.setRotationPoint(-56.0F, 0.0F, 0.0F);
        this.wingTip.addBox("bone", -56.0F, -2.0F, -2.0F, 56, 4, 4);
        this.wingTip.addBox("skin", -56.0F, 0.0F, 2.0F, 56, 0, 56);
        this.wing.addChild(this.wingTip);

        this.frontLeg = new ModelRenderer(this, "frontleg");
        this.frontLeg.setRotationPoint(-12.0F, 20.0F, 2.0F);
        this.frontLeg.addBox("main", -4.0F, -4.0F, -4.0F, 8, 24, 8);
        this.frontLegTip = new ModelRenderer(this, "frontlegtip");
        this.frontLegTip.setRotationPoint(0.0F, 20.0F, -1.0F);
        this.frontLegTip.addBox("main", -3.0F, -1.0F, -3.0F, 6, 24, 6);
        this.frontLeg.addChild(this.frontLegTip);
        this.frontFoot = new ModelRenderer(this, "frontfoot");
        this.frontFoot.setRotationPoint(0.0F, 23.0F, 0.0F);
        this.frontFoot.addBox("main", -4.0F, 0.0F, -12.0F, 8, 4, 16);
        this.frontLegTip.addChild(this.frontFoot);

        this.rearLeg = new ModelRenderer(this, "rearleg");
        this.rearLeg.setRotationPoint(-16.0F, 16.0F, 42.0F);
        this.rearLeg.addBox("main", -8.0F, -4.0F, -8.0F, 16, 32, 16);
        this.rearLegTip = new ModelRenderer(this, "rearlegtip");
        this.rearLegTip.setRotationPoint(0.0F, 32.0F, -4.0F);
        this.rearLegTip.addBox("main", -6.0F, -2.0F, 0.0F, 12, 32, 12);
        this.rearLeg.addChild(this.rearLegTip);
        this.rearFoot = new ModelRenderer(this, "rearfoot");
        this.rearFoot.setRotationPoint(0.0F, 31.0F, 4.0F);
        this.rearFoot.addBox("main", -9.0F, 0.0F, -20.0F, 18, 6, 24);
        this.rearLegTip.addChild(this.rearFoot);

        this.textureWidth = 64;
        this.textureHeight = 64;

        this.batHead = new ModelRenderer(this, 0, 0);
        this.batHead.addBox(-3.0F, -3.0F, -3.0F, 6, 6, 6);

        ModelRenderer localModelRenderer1 = new ModelRenderer(this, 24, 0);
        localModelRenderer1.addBox(-4.0F, -6.0F, -2.0F, 3, 4, 1);
        this.batHead.addChild(localModelRenderer1);
        ModelRenderer localModelRenderer2 = new ModelRenderer(this, 24, 0);
        localModelRenderer2.mirror = true;
        localModelRenderer2.addBox(1.0F, -6.0F, -2.0F, 3, 4, 1);
        this.batHead.addChild(localModelRenderer2);

        this.batBody = new ModelRenderer(this, 0, 16);
        this.batBody.addBox(-3.0F, 4.0F, -3.0F, 6, 12, 6);
        this.batBody.setTextureOffset(0, 34).addBox(-5.0F, 16.0F, 0.0F, 10, 6, 1);

        this.batRightWing = new ModelRenderer(this, 42, 0);
        this.batRightWing.addBox(-12.0F, 1.0F, 1.5F, 10, 16, 1);
        this.batOuterRightWing = new ModelRenderer(this, 24, 16);
        this.batOuterRightWing.setRotationPoint(-12.0F, 1.0F, 1.5F);
        this.batOuterRightWing.addBox(-8.0F, 1.0F, 0.0F, 8, 12, 1);

        this.batLeftWing = new ModelRenderer(this, 42, 0);
        this.batLeftWing.mirror = true;
        this.batLeftWing.addBox(2.0F, 1.0F, 1.5F, 10, 16, 1);
        this.batOuterLeftWing = new ModelRenderer(this, 24, 16);
        this.batOuterLeftWing.mirror = true;
        this.batOuterLeftWing.setRotationPoint(12.0F, 1.0F, 1.5F);
        this.batOuterLeftWing.addBox(0.0F, 1.0F, 0.0F, 8, 12, 1);

        this.batBody.addChild(this.batRightWing);
        this.batBody.addChild(this.batLeftWing);
        this.batRightWing.addChild(this.batOuterRightWing);
        this.batLeftWing.addChild(this.batOuterLeftWing);
    }

    public void setLivingAnimations(EntityLivingBase paramEntityLivingBase, float paramFloat1, float paramFloat2, float paramFloat3)
    {
        this.partialTicks = paramFloat3;
    }

    public void render(Entity paramEntity, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6)
    {
        EntityBat localEntityDragon = (EntityBat)paramEntity;
        if (localEntityDragon.getCustomNameTag().startsWith("§b§b§r")) {
            GL11.glPushMatrix();
            GL11.glScalef(0.15F, 0.15F, 0.15F);

            float f1 = this.prevAnimTime + (this.animTime - this.prevAnimTime) * this.partialTicks;
            this.prevAnimTime = animTime;
            this.animTime += 0.03F;
            //float f1 = 10 + (20 - 10) * 5F;
            this.jaw.rotateAngleX = ((float)(Math.sin(f1 * 3.141593F * 2.0F) + 1.0D) * 0.2F);

            float f2 = (float)(Math.sin(f1 * 3.141593F * 2.0F - 1.0F) + 1.0D);
            f2 = (f2 * f2 * 1.0F + f2 * 2.0F) * 0.05F;

            GL11.glTranslatef(0.0F, 4.0F, -3.0F);
            GL11.glRotatef(2.0F, 1.0F, 0.0F, 0.0F);

            float f3 = -30.0F;

            float f5 = 0.0F;

            float f6 = 1.5F;

           // double[] arrayOfDouble1 = localEntityDragon.getMovementOffsets(6, this.partialTicks);

            //float f7 = updateRotations(localEntityDragon.getMovementOffsets(5, this.partialTicks)[0] - localEntityDragon.getMovementOffsets(10, this.partialTicks)[0]);
           // float f8 = updateRotations(localEntityDragon.getMovementOffsets(5, this.partialTicks)[0] + f7 / 2.0F);

            f3 += 2.0F;

            float f9 = f1 * 3.141593F * 2.0F;
            f3 = 20.0F;
            float f4 = -12.0F;
            float f11;
            for (int i = 0; i < 5; i++) {
           //     double[] arrayOfDouble3 = localEntityDragon.getMovementOffsets(5 - i, this.partialTicks);
                f11 = (float)Math.cos(i * 0.45F + f9) * 0.15F;
                this.spine.rotateAngleY = (updateRotations(1F) * 3.141593F / 180.0F * f6);
                this.spine.rotateAngleX = (f11 + (float)(1F) * 3.141593F / 180.0F * f6 * 5.0F);
                this.spine.rotateAngleZ = (-updateRotations(1F) * 3.141593F / 180.0F * f6);

                this.spine.rotateAngleY = (updateRotations(1F) * 3.141593F / 180.0F * f6);
                this.spine.rotateAngleX = (f11 + (float)(1F) * 3.141593F / 180.0F * f6 * 5.0F);
                this.spine.rotateAngleZ = (-updateRotations(1F) * 3.141593F / 180.0F * f6);

                this.spine.rotationPointY = f3;
                this.spine.rotationPointZ = f4;
                this.spine.rotationPointX = f5;
                f3 = (float)(f3 + Math.sin(this.spine.rotateAngleX) * 10.0D);
                f4 = (float)(f4 - Math.cos(this.spine.rotateAngleY) * Math.cos(this.spine.rotateAngleX) * 10.0D);
                f5 = (float)(f5 - Math.sin(this.spine.rotateAngleY) * Math.cos(this.spine.rotateAngleX) * 10.0D);
                this.spine.render(paramFloat6);
            }

            this.head.rotationPointY = f3 * HEAD_ROTATION_FACTOR;
            this.head.rotationPointZ = f4 * HEAD_ROTATION_FACTOR;
            this.head.rotationPointX = f5 * HEAD_ROTATION_FACTOR;
            //double[] arrayOfDouble2 = localEntityDragon.getMovementOffsets(0, this.partialTicks);
           // this.head.rotateAngleY = (updateRotations(arrayOfDouble2[0] - arrayOfDouble1[0]) * 3.141593F / 180.0F * 1.0F);
           // this.head.rotateAngleZ = (-updateRotations(arrayOfDouble2[0] - f8) * 3.141593F / 180.0F * 1.0F);
            GL11.glPushMatrix();
            GL11.glScalef(HEAD_SIZE, HEAD_SIZE, HEAD_SIZE);
            this.head.render(paramFloat6);
            GL11.glPopMatrix();

            GL11.glPushMatrix();
            GL11.glTranslatef(0.0F, 1.0F, 0.0F);
            GL11.glRotatef(-0.2F * f6 * 1.0F, 0.0F, 0.0F, 1.0F);
            GL11.glTranslatef(0.0F, -1.0F, 0.0F);
            this.body.rotateAngleZ = 0.0F;
            this.body.render(paramFloat6);

            for (int j = 0; j < 2; j++) {
                GL11.glEnable(2884);
                f11 = f1 * 3.141593F * 2.0F;
                this.wing.rotateAngleX = (0.125F - (float)Math.cos(f11) * 0.2F);
                this.wing.rotateAngleY = 0.25F;
                this.wing.rotateAngleZ = ((float)(Math.sin(f11) + 0.125D) * 0.8F);
                this.wingTip.rotateAngleZ = (-(float)(Math.sin(f11 + 2.0F) + 0.5D) * 0.75F);

                this.rearLeg.rotateAngleX = (1.0F + f2 * 0.1F);
                this.rearLegTip.rotateAngleX = (0.5F + f2 * 0.1F);
                this.rearFoot.rotateAngleX = (0.75F + f2 * 0.1F);

                this.frontLeg.rotateAngleX = (1.3F + f2 * 0.1F);
                this.frontLegTip.rotateAngleX = (-0.5F - f2 * 0.1F);
                this.frontFoot.rotateAngleX = (0.75F + f2 * 0.1F);
                this.wing.render(paramFloat6);
                this.frontLeg.render(paramFloat6);
                this.rearLeg.render(paramFloat6);
                GL11.glScalef(-1.0F, 1.0F, 1.0F);

                if (j == 0) {
                    GL11.glCullFace(1028);
                }
            }
            GL11.glPopMatrix();
            GL11.glCullFace(1029);
            GL11.glDisable(2884);

            float f10 = -(float)Math.sin(f1 * 3.141593F * 2.0F) * 0.0F;
            f9 = f1 * 3.141593F * 2.0F;
            f3 = 10.0F;
            f4 = 60.0F;
            f5 = 0.0F;
          //  arrayOfDouble1 = localEntityDragon.getMovementOffsets(11, this.partialTicks);
            for (int k = 0; k < 12; k++) {
            //    arrayOfDouble2 = localEntityDragon.getMovementOffsets(12 + k, this.partialTicks);
                f10 = (float)(f10 + Math.sin(k * 0.45F + f9) * 0.0500000007450581D);
                this.spine.rotateAngleY = ((updateRotations(1F) * f6 + 180.0F) * 3.141593F / 180.0F);
                this.spine.rotateAngleX = (f10 + (float)(1F) * 3.141593F / 180.0F * f6 * 5.0F);
                this.spine.rotateAngleZ = (updateRotations(1F) * 3.141593F / 180.0F * f6);
                this.spine.rotationPointY = f3;
                this.spine.rotationPointZ = f4;
                this.spine.rotationPointX = f5;
                f3 = (float)(f3 + Math.sin(this.spine.rotateAngleX) * 10.0D);
                f4 = (float)(f4 - Math.cos(this.spine.rotateAngleY) * Math.cos(this.spine.rotateAngleX) * 10.0D);
                f5 = (float)(f5 - Math.sin(this.spine.rotateAngleY) * Math.cos(this.spine.rotateAngleX) * 10.0D);
                this.spine.render(paramFloat6);
            }

            GL11.glPopMatrix();
        } else {
            EntityBat localEntityBat = (EntityBat)paramEntity;
            float f;
            if (localEntityBat.getIsBatHanging()) {
                f = 57.295776F;
                this.batHead.rotateAngleX = (paramFloat5 / 57.295776F);
                this.batHead.rotateAngleY = (3.141593F - paramFloat4 / 57.295776F);
                this.batHead.rotateAngleZ = 3.141593F;

                this.batHead.setRotationPoint(0.0F, -2.0F, 0.0F);
                this.batRightWing.setRotationPoint(-3.0F, 0.0F, 3.0F);
                this.batLeftWing.setRotationPoint(3.0F, 0.0F, 3.0F);

                this.batBody.rotateAngleX = 3.141593F;

                this.batRightWing.rotateAngleX = -0.1570796F;
                this.batRightWing.rotateAngleY = -1.256637F;
                this.batOuterRightWing.rotateAngleY = -1.727876F;
                this.batLeftWing.rotateAngleX = this.batRightWing.rotateAngleX;
                this.batLeftWing.rotateAngleY = (-this.batRightWing.rotateAngleY);
                this.batOuterLeftWing.rotateAngleY = (-this.batOuterRightWing.rotateAngleY);
            } else {
                f = 57.295776F;
                this.batHead.rotateAngleX = (paramFloat5 / 57.295776F);
                this.batHead.rotateAngleY = (paramFloat4 / 57.295776F);
                this.batHead.rotateAngleZ = 0.0F;

                this.batHead.setRotationPoint(0.0F, 0.0F, 0.0F);
                this.batRightWing.setRotationPoint(0.0F, 0.0F, 0.0F);
                this.batLeftWing.setRotationPoint(0.0F, 0.0F, 0.0F);

                this.batBody.rotateAngleX = (0.7853982F + MathHelper.cos(paramFloat3 * 0.1F) * 0.15F);
                this.batBody.rotateAngleY = 0.0F;

                this.batRightWing.rotateAngleY = (MathHelper.cos(paramFloat3 * 1.3F) * 3.141593F * 0.25F);
                this.batLeftWing.rotateAngleY = (-this.batRightWing.rotateAngleY);
                this.batOuterRightWing.rotateAngleY = (this.batRightWing.rotateAngleY * 0.5F);
                this.batOuterLeftWing.rotateAngleY = (-this.batRightWing.rotateAngleY * 0.5F);
            }

            GL11.glPushMatrix();
            GL11.glScalef(0.35F, 0.35F, 0.35F);
            if(!localEntityBat.getIsBatHanging()) {
                GL11.glTranslatef(0.0F, 2.0F, 0.0F);
            } else {
                GL11.glTranslatef(0.0F, 3.0F, 0.0F);
            }
            this.batHead.render(paramFloat6);
            this.batBody.render(paramFloat6);
            GL11.glPopMatrix();
        }
    }

    private float updateRotations(double paramDouble) {
        while (paramDouble >= 180.0D)
            paramDouble -= 360.0D;
        while (paramDouble < -180.0D)
            paramDouble += 360.0D;
        return (float)paramDouble;
    }

    public int getBatSize()
    {
        return 36;
    }
}