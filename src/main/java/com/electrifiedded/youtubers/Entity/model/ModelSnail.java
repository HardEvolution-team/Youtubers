package com.electrifiedded.youtubers.Entity.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelSnail extends ModelBase {
   ModelRenderer TrackR4;
   ModelRenderer TrackR3;
   ModelRenderer TrackR2;
   ModelRenderer TrackR1;
   ModelRenderer TrackL4;
   ModelRenderer TrackL3;
   ModelRenderer TrackL2;
   ModelRenderer TrackL1;
   ModelRenderer TrackR;
   ModelRenderer Eyes1;
   ModelRenderer Eyes2;
   ModelRenderer Head;
   ModelRenderer ShellC1;
   ModelRenderer ShellC2;
   ModelRenderer ShellC3;
   ModelRenderer ShellC4;
   ModelRenderer ShellR1;
   ModelRenderer ShellR2;
   ModelRenderer ShellR3;
   ModelRenderer ShellR4;
   ModelRenderer ShellL1;
   ModelRenderer ShellL2;
   ModelRenderer ShellL4;
   ModelRenderer ShellL3;
   boolean Shell;
   boolean body;

   public ModelSnail() {
      this(0);
   }

   public ModelSnail(int part) {
      this.textureWidth = 64;
      this.textureHeight = 32;
      switch (part) {
         case 1:
            this.Shell = true;
            this.body = false;
            break;
         case 2:
            this.Shell = false;
            this.body = true;
            break;
         default:
            this.Shell = true;
            this.body = true;
      }

      this.TrackR4 = new ModelRenderer(this, 0, 0);
      this.TrackR4.addBox(-8.0F, 0.0F, 0.0F, 8, 1, 4);
      this.TrackR4.setRotationPoint(0.0F, 23.0F, 4.0F);
      this.TrackR4.setTextureSize(64, 32);
      this.TrackR4.mirror = true;
      this.setRotation(this.TrackR4, 0.0F, 0.0F, 0.0F);
      this.TrackR3 = new ModelRenderer(this, 0, 0);
      this.TrackR3.addBox(-8.0F, 0.0F, 0.0F, 8, 1, 4);
      this.TrackR3.setRotationPoint(0.0F, 23.0F, 0.0F);
      this.TrackR3.setTextureSize(64, 32);
      this.TrackR3.mirror = true;
      this.setRotation(this.TrackR3, 0.0F, 0.0F, 0.0F);
      this.TrackR2 = new ModelRenderer(this, 0, 0);
      this.TrackR2.addBox(-8.0F, 0.0F, 0.0F, 8, 1, 4);
      this.TrackR2.setRotationPoint(0.0F, 23.0F, -4.0F);
      this.TrackR2.setTextureSize(64, 32);
      this.TrackR2.mirror = true;
      this.setRotation(this.TrackR2, 0.0F, 0.0F, 0.0F);
      this.TrackR1 = new ModelRenderer(this, 0, 0);
      this.TrackR1.addBox(-8.0F, 0.0F, 0.0F, 8, 1, 4);
      this.TrackR1.setRotationPoint(0.0F, 23.0F, -8.0F);
      this.TrackR1.setTextureSize(64, 32);
      this.TrackR1.mirror = true;
      this.setRotation(this.TrackR1, 0.0F, 0.0F, 0.0F);
      this.TrackL4 = new ModelRenderer(this, 0, 0);
      this.TrackL4.addBox(0.0F, 0.0F, 0.0F, 8, 1, 4);
      this.TrackL4.setRotationPoint(0.0F, 23.0F, 4.0F);
      this.TrackL4.setTextureSize(64, 32);
      this.TrackL4.mirror = true;
      this.setRotation(this.TrackL4, 0.0F, 0.0F, 0.0F);
      this.TrackL3 = new ModelRenderer(this, 0, 0);
      this.TrackL3.addBox(0.0F, 0.0F, 0.0F, 8, 1, 4);
      this.TrackL3.setRotationPoint(0.0F, 23.0F, 0.0F);
      this.TrackL3.setTextureSize(64, 32);
      this.TrackL3.mirror = true;
      this.setRotation(this.TrackL3, 0.0F, 0.0F, 0.0F);
      this.TrackL2 = new ModelRenderer(this, 0, 0);
      this.TrackL2.addBox(0.0F, 0.0F, 0.0F, 8, 1, 4);
      this.TrackL2.setRotationPoint(0.0F, 23.0F, -4.0F);
      this.TrackL2.setTextureSize(64, 32);
      this.TrackL2.mirror = true;
      this.setRotation(this.TrackL2, 0.0F, 0.0F, 0.0F);
      this.TrackL1 = new ModelRenderer(this, 0, 0);
      this.TrackL1.addBox(0.0F, 0.0F, 0.0F, 8, 1, 4);
      this.TrackL1.setRotationPoint(0.0F, 23.0F, -8.0F);
      this.TrackL1.setTextureSize(64, 32);
      this.TrackL1.mirror = true;
      this.setRotation(this.TrackL1, 0.0F, 0.0F, 0.0F);
      this.TrackR4 = new ModelRenderer(this, 0, 0);
      this.TrackR4.addBox(-4.0F, 0.0F, 0.0F, 8, 1, 4);
      this.TrackR4.setRotationPoint(0.0F, 23.0F, 8.0F);
      this.TrackR4.setTextureSize(64, 32);
      this.TrackR4.mirror = true;
      this.setRotation(this.TrackR4, 0.0F, 0.0F, 0.0F);
      this.ShellC1 = new ModelRenderer(this, 24, 0);
      this.ShellC1.addBox(-5.0F, 0.0F, 0.0F, 10, 10, 10);
      this.ShellC1.setRotationPoint(0.0F, 13.0F, 0.0F);
      this.ShellC1.setTextureSize(64, 32);
      this.ShellC1.mirror = true;
      this.setRotation(this.ShellC1, 1.58825F, 0.0F, 0.0F);
      this.ShellC2 = new ModelRenderer(this, 24, 0);
      this.ShellC2.addBox(-5.0F, 0.0F, 0.0F, 10, 10, 10);
      this.ShellC2.setRotationPoint(0.0F, 13.0F, 0.0F);
      this.ShellC2.setTextureSize(64, 32);
      this.ShellC2.mirror = true;
      this.setRotation(this.ShellC2, 0.0F, 0.0F, 0.0F);
      this.ShellC3 = new ModelRenderer(this, 24, 0);
      this.ShellC3.addBox(-5.0F, 0.0F, 0.0F, 10, 10, 10);
      this.ShellC3.setRotationPoint(0.0F, 13.0F, 0.0F);
      this.ShellC3.setTextureSize(64, 32);
      this.ShellC3.mirror = true;
      this.setRotation(this.ShellC3, -3.141593F, 0.0F, 0.0F);
      this.ShellC4 = new ModelRenderer(this, 24, 0);
      this.ShellC4.addBox(-5.0F, 0.0F, -10.0F, 10, 10, 10);
      this.ShellC4.setRotationPoint(0.0F, 13.0F, 0.0F);
      this.ShellC4.setTextureSize(64, 32);
      this.ShellC4.mirror = true;
      this.setRotation(this.ShellC4, 0.0F, 0.0F, 0.0F);
      this.ShellR1 = new ModelRenderer(this, 0, 5);
      this.ShellR1.addBox(-3.0F, 0.0F, 0.0F, 3, 7, 7);
      this.ShellR1.setRotationPoint(-5.0F, 13.0F, 0.0F);
      this.ShellR1.setTextureSize(64, 32);
      this.ShellR1.mirror = false;
      this.setRotation(this.ShellR1, -3.141593F, 0.0F, 0.0F);
      this.ShellR2 = new ModelRenderer(this, 0, 5);
      this.ShellR2.addBox(-3.0F, 0.0F, 0.0F, 3, 7, 7);
      this.ShellR2.setRotationPoint(-5.0F, 13.0F, 0.0F);
      this.ShellR2.setTextureSize(64, 32);
      this.ShellR2.mirror = false;
      this.setRotation(this.ShellR2, 1.58825F, 0.0F, 0.0F);
      this.ShellR3 = new ModelRenderer(this, 0, 5);
      this.ShellR3.addBox(-3.0F, 0.0F, 0.0F, 3, 7, 7);
      this.ShellR3.setRotationPoint(-5.0F, 13.0F, 0.0F);
      this.ShellR3.setTextureSize(64, 32);
      this.ShellR3.mirror = true;
      this.setRotation(this.ShellR3, -1.570796F, 0.0F, 0.0F);
      this.ShellR4 = new ModelRenderer(this, 0, 5);
      this.ShellR4.addBox(-3.0F, 0.0F, 0.0F, 3, 7, 7);
      this.ShellR4.setRotationPoint(-5.0F, 13.0F, 0.0F);
      this.ShellR4.setTextureSize(64, 32);
      this.ShellR4.mirror = false;
      this.setRotation(this.ShellR4, 0.0F, 0.0F, 0.0F);
      this.ShellL1 = new ModelRenderer(this, 0, 5);
      this.ShellL1.addBox(0.0F, 0.0F, 0.0F, 3, 7, 7);
      this.ShellL1.setRotationPoint(5.0F, 13.0F, 0.0F);
      this.ShellL1.setTextureSize(64, 32);
      this.ShellL1.mirror = true;
      this.setRotation(this.ShellL1, -3.141593F, 0.0F, 0.0F);
      this.ShellL2 = new ModelRenderer(this, 0, 5);
      this.ShellL2.addBox(0.0F, 0.0F, 0.0F, 3, 7, 7);
      this.ShellL2.setRotationPoint(5.0F, 13.0F, 0.0F);
      this.ShellL2.setTextureSize(64, 32);
      this.ShellL2.mirror = true;
      this.setRotation(this.ShellL2, 1.58825F, 0.0F, 0.0F);
      this.ShellL4 = new ModelRenderer(this, 0, 5);
      this.ShellL4.addBox(0.0F, 0.0F, 0.0F, 3, 7, 7);
      this.ShellL4.setRotationPoint(5.0F, 13.0F, 0.0F);
      this.ShellL4.setTextureSize(64, 32);
      this.ShellL4.mirror = true;
      this.setRotation(this.ShellL4, 0.0F, 0.0F, 0.0F);
      this.ShellL3 = new ModelRenderer(this, 0, 5);
      this.ShellL3.addBox(0.0F, 0.0F, 0.0F, 3, 7, 7);
      this.ShellL3.setRotationPoint(5.0F, 13.0F, 0.0F);
      this.ShellL3.setTextureSize(64, 32);
      this.ShellL3.mirror = false;
      this.setRotation(this.ShellL3, -1.570796F, 0.0F, 0.0F);
      float a = (float) (-Math.PI / 2);
      float b = (float) (Math.PI / 4);
      float c = (float) (-Math.PI / 2);
      this.Eyes1 = new ModelRenderer(this, 48, 22);
      this.Eyes1.addBox(12.0F, -6.0F, -3.0F, 8, 10, 0);
      this.Eyes1.setRotationPoint(0.0F, 24.0F, -8.0F);
      this.Eyes1.setTextureSize(64, 32);
      this.Eyes1.mirror = true;
      this.setRotation(this.Eyes1, a, b, c);
      this.Eyes2 = new ModelRenderer(this, 48, 22);
      this.Eyes2.addBox(12.0F, -6.0F, 3.0F, 8, 10, 0);
      this.Eyes2.setRotationPoint(0.0F, 24.0F, -8.0F);
      this.Eyes2.setTextureSize(64, 32);
      this.Eyes2.mirror = true;
      this.setRotation(this.Eyes2, a, b, c);
      this.Head = new ModelRenderer(this, 0, 20);
      this.Head.addBox(0.0F, -6.0F, -3.0F, 14, 6, 6);
      this.Head.setRotationPoint(0.0F, 24.0F, -8.0F);
      this.Head.setTextureSize(64, 32);
      this.Head.mirror = true;
      this.setRotation(this.Head, a, b, c);
   }

   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      super.render(entity, f, f1, f2, f3, f4, f5);
      this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
      if (this.body) {
         this.TrackR4.render(f5);
         this.TrackR3.render(f5);
         this.TrackR2.render(f5);
         this.TrackR1.render(f5);
         this.TrackL4.render(f5);
         this.TrackL3.render(f5);
         this.TrackL2.render(f5);
         this.TrackL1.render(f5);
         this.TrackR4.render(f5);
         this.Eyes1.render(f5);
         this.Eyes2.render(f5);
         this.Head.render(f5);
      }

      if (this.Shell) {
         this.ShellC1.render(f5);
         this.ShellC2.render(f5);
         this.ShellC3.render(f5);
         this.ShellC4.render(f5);
         this.ShellR1.render(f5);
         this.ShellR2.render(f5);
         this.ShellR3.render(f5);
         this.ShellR4.render(f5);
         this.ShellL1.render(f5);
         this.ShellL2.render(f5);
         this.ShellL4.render(f5);
         this.ShellL3.render(f5);
      }
   }

   private void setRotation(ModelRenderer model, float x, float y, float z) {
      model.rotateAngleX = x;
      model.rotateAngleZ = z;
      model.rotateAngleY = y;
   }

   public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
      super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
      this.setRotation(this.TrackR1, 0.0F, (float)(0.08F * Math.sin(f)), 0.0F);
      this.setRotation(this.TrackR2, 0.0F, (float)(0.08F * Math.sin(f + 0.5)), 0.0F);
      this.setRotation(this.TrackR3, 0.0F, (float)(0.08F * Math.sin(f + 1.0F)), 0.0F);
      this.setRotation(this.TrackR4, 0.0F, (float)(0.08F * Math.sin(f + 1.5)), 0.0F);
      this.setRotation(this.TrackL1, 0.0F, (float)(0.08F * Math.sin(f + 2.5)), 0.0F);
      this.setRotation(this.TrackL2, 0.0F, (float)(0.08F * Math.sin(f + 3.0F)), 0.0F);
      this.setRotation(this.TrackL3, 0.0F, (float)(0.08F * Math.sin(f + 3.5)), 0.0F);
      this.setRotation(this.TrackL4, 0.0F, (float)(0.08F * Math.sin(f + 4.0F)), 0.0F);
   }
}
