package com.electrifiedded.youtubers.Entity.model;

import net.minecraft.client.model.ModelPlayer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/** Player model (64x64 skins, classic or slim arms) with the NPC sitting pose; used for real player skins. */
@SideOnly(Side.CLIENT)
public class ModelPlayerAlt extends ModelPlayer {
   public ModelPlayerAlt(float scale, boolean slim) {
      super(scale, slim);
   }

   @Override
   public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale, Entity entity) {
      super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entity);
      ModelBipedAlt.applySitting(this, entity);
   }
}
