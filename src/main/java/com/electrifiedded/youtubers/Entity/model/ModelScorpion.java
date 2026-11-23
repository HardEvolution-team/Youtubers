package com.electrifiedded.youtubers.Entity.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelScorpion extends ModelBase {
   ModelRenderer Head;
   ModelRenderer Body;
   ModelRenderer Leg8;
   ModelRenderer Leg6;
   ModelRenderer Leg4;
   ModelRenderer Leg2;
   ModelRenderer Leg7;
   ModelRenderer Leg5;
   ModelRenderer Leg3;
   ModelRenderer Leg1;
   ModelRenderer Tail1;
   ModelRenderer Tail2;
   ModelRenderer Tail3;
   ModelRenderer Tail4;
   ModelRenderer Claw1;
   ModelRenderer Claw2;

   public ModelScorpion() {
      this.textureWidth = 64;
      this.textureHeight = 32;
      this.Head = new ModelRenderer(this, 32, 4);
      this.Head.addBox(-4.0F, -4.0F, -8.0F, 8, 8, 8);
      this.Head.setRotationPoint(0.0F, 20.0F, -3.0F);
      this.Head.setTextureSize(64, 32);
      this.Head.mirror = true;
      this.setRotation(this.Head, 0.0F, 0.0F, 0.0F);
      this.Body = new ModelRenderer(this, 0, 16);
      this.Body.addBox(-4.0F, -3.0F, -3.0F, 8, 6, 10);
      this.Body.setRotationPoint(0.0F, 20.0F, 0.0F);
      this.Body.setTextureSize(64, 32);
      this.Body.mirror = true;
      this.setRotation(this.Body, 0.0F, 0.0F, 0.0F);
      this.Leg8 = new ModelRenderer(this, 28, 0);
      this.Leg8.addBox(-1.0F, -1.0F, -1.0F, 16, 2, 2);
      this.Leg8.setRotationPoint(4.0F, 20.0F, -1.0F);
      this.Leg8.setTextureSize(64, 32);
      this.Leg8.mirror = true;
      this.setRotation(this.Leg8, 0.0F, 1.308997F, 0.0872665F);
      this.Leg6 = new ModelRenderer(this, 28, 0);
      this.Leg6.addBox(-1.0F, -1.0F, -1.0F, 16, 2, 2);
      this.Leg6.setRotationPoint(4.0F, 20.0F, 0.0F);
      this.Leg6.setTextureSize(64, 32);
      this.Leg6.mirror = true;
      this.setRotation(this.Leg6, 0.0F, 0.2792527F, 0.1919862F);
      this.Leg4 = new ModelRenderer(this, 28, 0);
      this.Leg4.addBox(-1.0F, -1.0F, -1.0F, 16, 2, 2);
      this.Leg4.setRotationPoint(4.0F, 20.0F, 1.0F);
      this.Leg4.setTextureSize(64, 32);
      this.Leg4.mirror = true;
      this.setRotation(this.Leg4, 0.0F, -0.2792527F, 0.1919862F);
      this.Leg2 = new ModelRenderer(this, 28, 0);
      this.Leg2.addBox(-1.0F, -1.0F, -1.0F, 16, 2, 2);
      this.Leg2.setRotationPoint(4.0F, 20.0F, 2.0F);
      this.Leg2.setTextureSize(64, 32);
      this.Leg2.mirror = true;
      this.setRotation(this.Leg2, 0.0F, -0.5759587F, 0.1919862F);
      this.Leg7 = new ModelRenderer(this, 28, 0);
      this.Leg7.addBox(-15.0F, -1.0F, -1.0F, 16, 2, 2);
      this.Leg7.setRotationPoint(-4.0F, 20.0F, -1.0F);
      this.Leg7.setTextureSize(64, 32);
      this.Leg7.mirror = true;
      this.setRotation(this.Leg7, 0.0F, -1.308997F, -0.0872665F);
      this.Leg5 = new ModelRenderer(this, 28, 0);
      this.Leg5.addBox(-15.0F, -1.0F, -1.0F, 16, 2, 2);
      this.Leg5.setRotationPoint(-4.0F, 20.0F, 0.0F);
      this.Leg5.setTextureSize(64, 32);
      this.Leg5.mirror = true;
      this.setRotation(this.Leg5, 0.0F, -0.2792527F, -0.1919862F);
      this.Leg3 = new ModelRenderer(this, 28, 0);
      this.Leg3.addBox(-15.0F, -1.0F, -1.0F, 16, 2, 2);
      this.Leg3.setRotationPoint(-4.0F, 20.0F, 1.0F);
      this.Leg3.setTextureSize(64, 32);
      this.Leg3.mirror = true;
      this.setRotation(this.Leg3, 0.0F, 0.2792527F, -0.1919862F);
      this.Leg1 = new ModelRenderer(this, 28, 0);
      this.Leg1.addBox(-15.0F, -1.0F, -1.0F, 16, 2, 2);
      this.Leg1.setRotationPoint(-4.0F, 20.0F, 2.0F);
      this.Leg1.setTextureSize(64, 32);
      this.Leg1.mirror = true;
      this.setRotation(this.Leg1, 0.0F, 0.5759587F, -0.1919862F);
      this.Tail1 = new ModelRenderer(this, 2, 16);
      this.Tail1.addBox(-3.0F, -3.0F, 0.0F, 6, 6, 10);
      this.Tail1.setRotationPoint(0.0F, 19.0F, 5.0F);
      this.Tail1.setTextureSize(64, 32);
      this.Tail1.mirror = true;
      this.setRotation(this.Tail1, 0.8179294F, 0.0F, 0.0F);
      this.Tail2 = new ModelRenderer(this, 2, 16);
      this.Tail2.addBox(-2.0F, -2.0F, -2.0F, 4, 4, 12);
      this.Tail2.setRotationPoint(0.0F, 10.0F, 10.0F);
      this.Tail2.setTextureSize(64, 32);
      this.Tail2.mirror = true;
      this.setRotation(this.Tail2, 2.602503F, 0.0F, 0.0F);
      this.Tail3 = new ModelRenderer(this, 0, 0);
      this.Tail3.addBox(-2.5F, -2.0F, -2.0F, 5, 5, 4);
      this.Tail3.setRotationPoint(0.0F, 7.0F, 0.0F);
      this.Tail3.setTextureSize(64, 32);
      this.Tail3.mirror = true;
      this.setRotation(this.Tail3, 0.0F, 0.0F, 0.0F);
      this.Tail4 = new ModelRenderer(this, 18, 0);
      this.Tail4.addBox(-0.5F, 0.0F, -1.0F, 1, 4, 1);
      this.Tail4.setRotationPoint(0.0F, 10.0F, 2.0F);
      this.Tail4.setTextureSize(64, 32);
      this.Tail4.mirror = true;
      this.setRotation(this.Tail4, -0.7435722F, 0.0F, 0.0F);
      this.Claw1 = new ModelRenderer(this, 48, 24);
      this.Claw1.addBox(-1.5F, -1.5F, -4.0F, 3, 3, 5);
      this.Claw1.setRotationPoint(7.0F, 21.0F, -14.0F);
      this.Claw1.setTextureSize(64, 32);
      this.Claw1.mirror = true;
      this.setRotation(this.Claw1, 0.0F, (float) (Math.PI / 6), -1.570796F);
      this.Claw2 = new ModelRenderer(this, 48, 24);
      this.Claw2.addBox(-1.5F, -1.5F, -4.0F, 3, 3, 5);
      this.Claw2.setRotationPoint(-8.0F, 21.0F, -14.0F);
      this.Claw2.setTextureSize(64, 32);
      this.Claw2.mirror = true;
      this.setRotation(this.Claw2, 0.0F, (float) (-Math.PI / 6), 1.570796F);
   }

   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      super.render(entity, f, f1, f2, f3, f4, f5);
      this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
      this.Head.render(f5);
      this.Body.render(f5);
      this.Leg8.render(f5);
      this.Leg6.render(f5);
      this.Leg4.render(f5);
      this.Leg2.render(f5);
      this.Leg7.render(f5);
      this.Leg5.render(f5);
      this.Leg3.render(f5);
      this.Leg1.render(f5);
      this.Tail1.render(f5);
      this.Tail2.render(f5);
      this.Tail3.render(f5);
      this.Tail4.render(f5);
      this.Claw1.render(f5);
      this.Claw2.render(f5);
   }

   private void setRotation(ModelRenderer model, float x, float y, float z) {
      model.rotateAngleX = x;
      model.rotateAngleY = y;
      model.rotateAngleZ = z;
   }

   public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity Entity) {
      super.setRotationAngles(f, f1, f2, f3, f4, f5, Entity);
      this.Head.rotateAngleY = f3 / (180.0F / (float)Math.PI);
      this.Head.rotateAngleX = f4 / (180.0F / (float)Math.PI);
      if (f3 < 0.0F) {
         this.Leg8.rotateAngleY = this.Head.rotateAngleY + 1.308997F;
      } else {
         this.Leg8.rotateAngleY = this.Body.rotateAngleY + 1.308997F;
      }

      if (f3 > 0.0F) {
         this.Leg7.rotateAngleY = this.Head.rotateAngleY - 1.308997F;
      } else {
         this.Leg7.rotateAngleY = this.Body.rotateAngleY - 1.308997F;
      }

      this.Claw1.setRotationPoint(4.0F + 16.0F * (float)Math.cos(this.Leg8.rotateAngleY), 20.0F, -1.0F - 16.0F * (float)Math.sin(this.Leg8.rotateAngleY));
      this.Claw2.setRotationPoint(-4.0F - 16.0F * (float)Math.cos(this.Leg7.rotateAngleY), 20.0F, -1.0F + 16.0F * (float)Math.sin(this.Leg7.rotateAngleY));
      this.Claw1.rotateAngleX = -this.Head.rotateAngleY;
      this.Claw2.rotateAngleX = this.Head.rotateAngleY;
      float f6 = (float) (Math.PI / 4);
      this.Leg1.rotateAngleZ = -f6;
      this.Leg2.rotateAngleZ = f6;
      this.Leg3.rotateAngleZ = -f6 * 0.74F;
      this.Leg4.rotateAngleZ = f6 * 0.74F;
      this.Leg5.rotateAngleZ = -f6 * 0.74F;
      this.Leg6.rotateAngleZ = f6 * 0.74F;
      float f7 = -0.0F;
      float f8 = (float) (Math.PI / 8);
      this.Leg1.rotateAngleY = f8 * 2.0F + f7;
      this.Leg2.rotateAngleY = -f8 * 2.0F - f7;
      this.Leg3.rotateAngleY = f8 * 1.0F + f7;
      this.Leg4.rotateAngleY = -f8 * 1.0F - f7;
      this.Leg5.rotateAngleY = -f8 * 1.0F + f7;
      this.Leg6.rotateAngleY = f8 * 1.0F - f7;
      float f9 = -(MathHelper.cos(f * 0.6662F * 2.0F + 0.0F) * 0.4F) * f1;
      float f10 = -(MathHelper.cos(f * 0.6662F * 2.0F + (float) Math.PI) * 0.4F) * f1;
      float f11 = -(MathHelper.cos(f * 0.6662F * 2.0F + (float) (Math.PI / 2)) * 0.4F) * f1;
      float f12 = -(MathHelper.cos(f * 0.6662F * 2.0F + (float) (Math.PI * 3.0 / 2.0)) * 0.4F) * f1;
      float f13 = Math.abs(MathHelper.sin(f * 0.6662F + 0.0F) * 0.4F) * f1;
      float f14 = Math.abs(MathHelper.sin(f * 0.6662F + (float) Math.PI) * 0.4F) * f1;
      float f15 = Math.abs(MathHelper.sin(f * 0.6662F + (float) (Math.PI / 2)) * 0.4F) * f1;
      float f16 = Math.abs(MathHelper.sin(f * 0.6662F + (float) (Math.PI * 3.0 / 2.0)) * 0.4F) * f1;
      this.Leg1.rotateAngleY += f9;
      this.Leg2.rotateAngleY += -f9;
      this.Leg3.rotateAngleY += f10;
      this.Leg4.rotateAngleY += -f10;
      this.Leg5.rotateAngleY += f11;
      this.Leg6.rotateAngleY += -f11;
      this.Leg1.rotateAngleZ += f13;
      this.Leg2.rotateAngleZ += -f13;
      this.Leg3.rotateAngleZ += f14;
      this.Leg4.rotateAngleZ += -f14;
      this.Leg5.rotateAngleZ += f15;
      this.Leg6.rotateAngleZ += -f15;
   }
}
