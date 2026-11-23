package com.electrifiedded.youtubers.Entity.model;

import com.electrifiedded.youtubers.Entity.EntityYTBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Biped of the mod's NPCs (64x32 skins): the vanilla biped plus the sitting pose of tamed NPCs that were told to sit
 * (arms forward, legs straight out, whole body 8 pixels lower).
 */
@SideOnly(Side.CLIENT)
public class ModelBipedAlt extends ModelBiped {
   public ModelBipedAlt() {
      this(0.0F);
   }

   public ModelBipedAlt(float scale) {
      this(scale, 0.0F, 64, 32);
   }

   public ModelBipedAlt(float scale, float yOffset, int texW, int texH) {
      super(scale, yOffset, texW, texH);
   }

   @Override
   public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale, Entity entity) {
      super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entity);
      applySitting(this, entity);
   }

   static boolean isSitting(Entity e) {
      return e instanceof EntityYTBase && ((EntityYTBase)e).isSitting();
   }

   /** The 1.7 sitting pose of the mod, applied on top of the vanilla angles. */
   public static void applySitting(ModelBiped m, Entity entity) {
      if (!isSitting(entity)) return;
      float shift = 8.0F;
      m.bipedRightArm.rotateAngleX += (float)(-Math.PI / 5);
      m.bipedLeftArm.rotateAngleX += (float)(-Math.PI / 5);
      m.bipedRightLeg.rotateAngleX = (float)(-Math.PI / 2);
      m.bipedLeftLeg.rotateAngleX = (float)(-Math.PI / 2);
      m.bipedRightLeg.rotateAngleY = (float)(Math.PI / 10);
      m.bipedLeftLeg.rotateAngleY = (float)(-Math.PI / 10);
      m.bipedRightLeg.rotationPointY = 12.0F + shift;
      m.bipedLeftLeg.rotationPointY = 12.0F + shift;
      m.bipedHead.rotationPointY = shift;
      m.bipedHeadwear.rotationPointY = shift;
      m.bipedBody.rotationPointY = shift;
      m.bipedRightArm.rotationPointY = 2.0F + shift;
      m.bipedLeftArm.rotationPointY = 2.0F + shift;
      if (m instanceof ModelPlayer) {
         ModelPlayer p = (ModelPlayer)m;
         copyModelAngles(p.bipedLeftLeg, p.bipedLeftLegwear);
         copyModelAngles(p.bipedRightLeg, p.bipedRightLegwear);
         copyModelAngles(p.bipedLeftArm, p.bipedLeftArmwear);
         copyModelAngles(p.bipedRightArm, p.bipedRightArmwear);
         copyModelAngles(p.bipedBody, p.bipedBodyWear);
      }
   }
}
