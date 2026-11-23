package com.electrifiedded.youtubers.Entity.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelEnderFish extends ModelBase {
   ModelRenderer Head;
   ModelRenderer Body;
   ModelRenderer RightArm;
   ModelRenderer LeftArm;
   ModelRenderer Shape1;
   ModelRenderer Shape2;
   ModelRenderer Head2;
   public boolean isCarrying;
   public boolean isAttacking;

   public ModelEnderFish() {
      this.textureWidth = 64;
      this.textureHeight = 32;
      this.Head = new ModelRenderer(this, 0, 0);
      this.Head.addBox(-4.0F, -4.0F, -8.0F, 8, 8, 8);
      this.Head.setRotationPoint(0.0F, 13.0F, 0.0F);
      this.Head.setTextureSize(64, 32);
      this.Head.mirror = true;
      this.setRotation(this.Head, 0.0F, 0.0F, 0.0F);
      this.Body = new ModelRenderer(this, 32, 18);
      this.Body.addBox(-4.0F, -3.0F, 0.0F, 8, 6, 8);
      this.Body.setRotationPoint(0.0F, 13.0F, 0.0F);
      this.Body.setTextureSize(64, 32);
      this.Body.mirror = true;
      this.setRotation(this.Body, 0.0F, 0.0F, 0.0F);
      this.RightArm = new ModelRenderer(this, 56, 0);
      this.RightArm.addBox(-1.0F, -2.0F, -1.0F, 2, 16, 2);
      this.RightArm.setRotationPoint(-5.0F, 15.0F, 1.0F);
      this.RightArm.setTextureSize(64, 32);
      this.RightArm.mirror = true;
      this.setRotation(this.RightArm, -1.22173F, 0.0F, 0.0F);
      this.LeftArm = new ModelRenderer(this, 56, 0);
      this.LeftArm.addBox(-1.0F, -2.0F, -1.0F, 2, 16, 2);
      this.LeftArm.setRotationPoint(5.0F, 15.0F, 1.0F);
      this.LeftArm.setTextureSize(64, 32);
      this.LeftArm.mirror = true;
      this.setRotation(this.LeftArm, -1.22173F, 0.0F, 0.0F);
      this.LeftArm.mirror = false;
      this.Shape1 = new ModelRenderer(this, 32, -4);
      this.Shape1.addBox(0.0F, -3.0F, 0.0F, 0, 6, 4);
      this.Shape1.setRotationPoint(0.0F, 12.0F, 8.0F);
      this.Shape1.setTextureSize(64, 32);
      this.Shape1.mirror = true;
      this.setRotation(this.Shape1, 0.0F, 0.0F, 0.0F);
      this.Shape2 = new ModelRenderer(this, 32, -4);
      this.Shape2.addBox(0.0F, -2.0F, 0.0F, 0, 6, 4);
      this.Shape2.setRotationPoint(0.0F, 9.0F, 0.0F);
      this.Shape2.setTextureSize(64, 32);
      this.Shape2.mirror = true;
      this.setRotation(this.Shape2, 0.4833219F, 0.0F, 0.0F);
      this.Head2 = new ModelRenderer(this, 0, 16);
      this.Head2.addBox(-4.0F, -4.0F, -8.0F, 8, 8, 8);
      this.Head2.setRotationPoint(0.0F, 13.0F, 0.0F);
      this.Head2.setTextureSize(64, 32);
      this.Head2.mirror = true;
      this.setRotation(this.Head2, 0.0F, 0.0F, 0.0F);
   }

   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      super.render(entity, f, f1, f2, f3, f4, f5);
      this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
      this.Head.render(f5);
      this.Body.render(f5);
      this.RightArm.render(f5);
      this.LeftArm.render(f5);
      this.Shape1.render(f5);
      this.Shape2.render(f5);
      this.Head2.render(f5);
   }

   private void setRotation(ModelRenderer model, float x, float y, float z) {
      model.rotateAngleX = x;
      model.rotateAngleY = y;
      model.rotateAngleZ = z;
   }

   public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
      super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
      this.Shape2.rotateAngleY = MathHelper.cos(f * 0.6662F) * 0.7F * f1;
      this.Shape1.rotateAngleY = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
      if (this.isAttacking) {
         float f8 = 1.0F;
         this.Head.rotationPointY -= f8 * 5.0F;
      }
   }
}
