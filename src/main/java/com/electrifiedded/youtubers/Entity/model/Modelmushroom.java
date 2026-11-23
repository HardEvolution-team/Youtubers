package com.electrifiedded.youtubers.Entity.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class Modelmushroom extends ModelBase {
   ModelRenderer Mushroom1;
   ModelRenderer Mushroom2;
   ModelRenderer Mushroom3;
   ModelRenderer Mushroom4;

   public Modelmushroom() {
      this.textureWidth = 32;
      this.textureHeight = 32;
      this.Mushroom1 = new ModelRenderer(this, 0, -5);
      this.Mushroom1.addBox(0.0F, -10.0F, 0.0F, 0, 10, 5);
      this.Mushroom1.setRotationPoint(0.0F, 24.0F, 0.0F);
      this.Mushroom1.setTextureSize(32, 32);
      this.Mushroom1.mirror = true;
      this.setRotation(this.Mushroom1, 0.0F, (float) (-Math.PI / 4), 0.0F);
      this.Mushroom2 = new ModelRenderer(this, 0, -5);
      this.Mushroom2.addBox(0.0F, -10.0F, 0.0F, 0, 10, 5);
      this.Mushroom2.setRotationPoint(0.0F, 24.0F, 0.0F);
      this.Mushroom2.setTextureSize(32, 32);
      this.Mushroom2.mirror = true;
      this.setRotation(this.Mushroom2, 0.0F, (float) (Math.PI / 4), 0.0F);
      this.Mushroom3 = new ModelRenderer(this, 10, -5);
      this.Mushroom3.addBox(0.0F, -10.0F, 0.0F, 0, 10, 5);
      this.Mushroom3.setRotationPoint(0.0F, 24.0F, 0.0F);
      this.Mushroom3.setTextureSize(32, 32);
      this.Mushroom3.mirror = true;
      this.setRotation(this.Mushroom3, 0.0F, 2.356194F, 0.0F);
      this.Mushroom4 = new ModelRenderer(this, 10, -5);
      this.Mushroom4.addBox(0.0F, -10.0F, 0.0F, 0, 10, 5);
      this.Mushroom4.setRotationPoint(0.0F, 24.0F, 0.0F);
      this.Mushroom4.setTextureSize(32, 32);
      this.Mushroom4.mirror = true;
      this.setRotation(this.Mushroom4, 0.0F, -2.356194F, 0.0F);
   }

   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      super.render(entity, f, f1, f2, f3, f4, f5);
      this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
      this.Mushroom1.render(f5);
      this.Mushroom2.render(f5);
      this.Mushroom3.render(f5);
      this.Mushroom4.render(f5);
   }

   private void setRotation(ModelRenderer model, float x, float y, float z) {
      model.rotateAngleX = x;
      model.rotateAngleY = y;
      model.rotateAngleZ = z;
   }

   public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
      super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
   }
}
