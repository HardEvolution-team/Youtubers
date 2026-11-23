package com.electrifiedded.youtubers.Entity.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelOceanicCreeper extends ModelBase {
   ModelRenderer head;
   ModelRenderer body;
   ModelRenderer leg3;
   ModelRenderer leg4;
   ModelRenderer leg1;
   ModelRenderer leg2;
   ModelRenderer Body2;
   ModelRenderer Tail;
   ModelRenderer Fin;

   public ModelOceanicCreeper() {
      this.textureWidth = 64;
      this.textureHeight = 32;
      this.head = new ModelRenderer(this, 0, 0);
      this.head.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8);
      this.head.setRotationPoint(0.0F, 11.0F, 0.0F);
      this.head.setTextureSize(64, 32);
      this.head.mirror = true;
      this.setRotation(this.head, 0.0F, 0.0F, 0.0F);
      this.body = new ModelRenderer(this, 16, 16);
      this.body.addBox(-4.0F, 0.0F, -2.0F, 8, 7, 9);
      this.body.setRotationPoint(0.0F, 11.0F, 0.0F);
      this.body.setTextureSize(64, 32);
      this.body.mirror = true;
      this.setRotation(this.body, 0.0F, 0.0F, 0.0F);
      this.leg3 = new ModelRenderer(this, 0, 16);
      this.leg3.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4);
      this.leg3.setRotationPoint(-2.0F, 18.0F, -4.0F);
      this.leg3.setTextureSize(64, 32);
      this.leg3.mirror = true;
      this.setRotation(this.leg3, 0.0F, 0.0F, 0.0F);
      this.leg4 = new ModelRenderer(this, 0, 16);
      this.leg4.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4);
      this.leg4.setRotationPoint(2.0F, 18.0F, -4.0F);
      this.leg4.setTextureSize(64, 32);
      this.leg4.mirror = true;
      this.setRotation(this.leg4, 0.0F, 0.0F, 0.0F);
      this.leg1 = new ModelRenderer(this, 0, 16);
      this.leg1.addBox(0.0F, 0.0F, -2.0F, 4, 6, 4);
      this.leg1.setRotationPoint(-4.0F, 18.0F, 4.0F);
      this.leg1.setTextureSize(64, 32);
      this.leg1.mirror = true;
      this.setRotation(this.leg1, 0.0F, 0.0F, 0.0F);
      this.leg2 = new ModelRenderer(this, 0, 16);
      this.leg2.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4);
      this.leg2.setRotationPoint(2.0F, 18.0F, 4.0F);
      this.leg2.setTextureSize(64, 32);
      this.leg2.mirror = true;
      this.setRotation(this.leg2, 0.0F, 0.0F, 0.0F);
      this.Body2 = new ModelRenderer(this, 32, 5);
      this.Body2.addBox(-3.0F, -2.0F, 0.0F, 6, 5, 6);
      this.Body2.setRotationPoint(0.0F, 14.0F, 6.0F);
      this.Body2.setTextureSize(64, 32);
      this.Body2.mirror = true;
      this.setRotation(this.Body2, 0.0F, 0.0F, 0.0F);
      this.Tail = new ModelRenderer(this, 50, 16);
      this.Tail.addBox(0.0F, -6.0F, 0.0F, 0, 9, 7);
      this.Tail.setRotationPoint(0.0F, 15.0F, 11.0F);
      this.Tail.setTextureSize(64, 32);
      this.Tail.mirror = true;
      this.setRotation(this.Tail, 0.0F, 0.0F, 0.0F);
      this.Fin = new ModelRenderer(this, 54, 12);
      this.Fin.addBox(0.0F, -4.0F, 0.0F, 0, 5, 5);
      this.Fin.setRotationPoint(0.0F, 11.0F, 4.0F);
      this.Fin.setTextureSize(64, 32);
      this.Fin.mirror = true;
      this.setRotation(this.Fin, 0.0F, 0.0F, 0.0F);
   }

   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      super.render(entity, f, f1, f2, f3, f4, f5);
      this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
      this.head.render(f5);
      this.body.render(f5);
      this.leg3.render(f5);
      this.leg4.render(f5);
      this.leg1.render(f5);
      this.leg2.render(f5);
      this.Body2.render(f5);
      this.Tail.render(f5);
      this.Fin.render(f5);
   }

   private void setRotation(ModelRenderer model, float x, float y, float z) {
      model.rotateAngleX = x;
      model.rotateAngleY = y;
      model.rotateAngleZ = z;
   }

   public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
      this.head.rotateAngleY = par4 / (180.0F / (float)Math.PI);
      this.head.rotateAngleX = par5 / (180.0F / (float)Math.PI);
      this.leg1.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
      this.leg2.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float) Math.PI) * 1.4F * par2;
      this.leg3.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float) Math.PI) * 1.4F * par2;
      this.leg4.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
      this.Body2.rotateAngleY = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
      this.Fin.rotateAngleY = MathHelper.cos(par1 * 0.6662F) * 0.7F * par2;
      this.Tail.rotateAngleY = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
      this.Tail.offsetX = MathHelper.cos(par1 * 0.6662F) * 0.7F * par2;
   }
}
