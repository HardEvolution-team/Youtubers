package com.electrifiedded.youtubers.Entity.Render;

import com.electrifiedded.youtubers.Entity.EntityGrimm;
import com.electrifiedded.youtubers.Entity.model.ModelGrimm;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/** Grim: wolf-like model, dyed vanilla wolf collar when tamed. */
@SideOnly(Side.CLIENT)
public class RenderGrimm extends RenderLiving<EntityGrimm> {
   private static final ResourceLocation TEXTURE = new ResourceLocation("youtubers", "textures/entity/Grim.png");
   private static final ResourceLocation COLLAR = new ResourceLocation("textures/entity/wolf/wolf_collar.png");

   public RenderGrimm(RenderManager manager) {
      super(manager, new ModelGrimm(), 0.5F);
      this.addLayer(new LayerRenderer<EntityGrimm>() {
         @Override
         public void doRenderLayer(EntityGrimm e, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw,
                                   float headPitch, float scale) {
            if (!e.isTamed() || e.isInvisible()) return;
            RenderGrimm.this.bindTexture(COLLAR);
            float[] c = EntitySheep.getDyeRgb(e.getCollarColor());
            GlStateManager.color(c[0], c[1], c[2]);
            RenderGrimm.this.getMainModel().render(e, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
            GlStateManager.color(1.0F, 1.0F, 1.0F);
         }

         @Override
         public boolean shouldCombineTextures() {
            return true;
         }
      });
   }

   @Override
   protected float handleRotationFloat(EntityGrimm e, float partialTicks) {
      return e.getTailRotation();
   }

   @Override
   protected ResourceLocation getEntityTexture(EntityGrimm entity) {
      return TEXTURE;
   }
}
