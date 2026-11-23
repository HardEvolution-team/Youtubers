package com.electrifiedded.youtubers.Entity.model;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelJetpack extends ModelBiped {
   ModelRenderer body;
   ModelRenderer Wing1;
   ModelRenderer Wing2;
   ModelRenderer Jet1;
   ModelRenderer Jet2;
   ModelRenderer Cap1;
   ModelRenderer Cap2;

   public ModelJetpack(float f) {
      super(f, 0.0F, 128, 128);
      this.textureWidth = 64;
      this.textureHeight = 32;
      this.body = new ModelRenderer(this, 16, 16);
      this.body.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4);
      this.body.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.body.setTextureSize(64, 32);
      this.body.mirror = true;
      this.setRotation(this.body, 0.0F, 0.0F, 0.0F);
      this.Wing1 = new ModelRenderer(this, 32, 0);
      this.Wing1.addBox(0.0F, 0.0F, 0.0F, 12, 8, 0);
      this.Wing1.setRotationPoint(-3.0F, 1.0F, 2.0F);
      this.Wing1.setTextureSize(64, 32);
      this.Wing1.mirror = true;
      this.setRotation(this.Wing1, 0.0F, -2.530727F, 0.0F);
      this.Wing2 = new ModelRenderer(this, 32, 0);
      this.Wing2.addBox(0.0F, 0.0F, 0.0F, 12, 8, 0);
      this.Wing2.setRotationPoint(3.0F, 1.0F, 2.0F);
      this.Wing2.setTextureSize(64, 32);
      this.Wing2.mirror = true;
      this.setRotation(this.Wing2, 0.0F, -0.6108652F, 0.0F);
      this.Jet1 = new ModelRenderer(this, 40, 19);
      this.Jet1.addBox(-2.0F, 0.0F, -2.0F, 4, 9, 4);
      this.Jet1.setRotationPoint(-7.0F, 0.0F, 6.0F);
      this.Jet1.setTextureSize(64, 32);
      this.Jet1.mirror = true;
      this.setRotation(this.Jet1, 0.0F, 0.0F, 0.0F);
      this.Jet1.mirror = false;
      this.Jet2 = new ModelRenderer(this, 40, 19);
      this.Jet2.addBox(-2.0F, 0.0F, -2.0F, 4, 9, 4);
      this.Jet2.setRotationPoint(7.0F, 0.0F, 6.0F);
      this.Jet2.setTextureSize(64, 32);
      this.Jet2.mirror = true;
      this.setRotation(this.Jet2, 0.0F, 0.0F, 0.0F);
      this.Cap1 = new ModelRenderer(this, 40, 16);
      this.Cap1.addBox(-1.0F, 0.0F, -1.0F, 2, 1, 2);
      this.Cap1.setRotationPoint(-7.0F, -1.0F, 6.0F);
      this.Cap1.setTextureSize(64, 32);
      this.Cap1.mirror = true;
      this.setRotation(this.Cap1, 0.0F, 0.0F, 0.0F);
      this.Cap2 = new ModelRenderer(this, 40, 16);
      this.Cap2.addBox(-1.0F, 0.0F, -1.0F, 2, 1, 2);
      this.Cap2.setRotationPoint(7.0F, -1.0F, 6.0F);
      this.Cap2.setTextureSize(64, 32);
      this.Cap2.mirror = true;
      this.setRotation(this.Cap2, 0.0F, 0.0F, 0.0F);
   }

   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      super.render(entity, f, f1, f2, f3, f4, f5);
      this.setRotationAngles(entity, f, f1, f2, f3, f4, f5);
      this.body.render(f5);
      this.Wing1.render(f5);
      this.Wing2.render(f5);
      this.Jet1.render(f5);
      this.Jet2.render(f5);
      this.Cap1.render(f5);
      this.Cap2.render(f5);
   }

   private void setRotation(ModelRenderer model, float x, float y, float z) {
      model.rotateAngleX = x;
      model.rotateAngleY = y;
      model.rotateAngleZ = z;
   }

   public void setRotationAngles(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
      if (this.isSneak) {
         this.body.rotateAngleX = 0.5F;
      } else {
         this.body.rotateAngleX = 0.0F;
      }
   }
}
