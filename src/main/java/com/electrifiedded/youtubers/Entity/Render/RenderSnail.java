package com.electrifiedded.youtubers.Entity.Render;

import com.electrifiedded.youtubers.Entity.EntitySnail;
import com.electrifiedded.youtubers.Entity.model.ModelSnail;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/** Snail: body model plus the shell model tinted with the snail's colour; babies are small. */
@SideOnly(Side.CLIENT)
public class RenderSnail extends RenderLiving<EntitySnail> {
   private static final ResourceLocation TEXTURE = new ResourceLocation("youtubers", "textures/entity/Snail.png");

   public RenderSnail(RenderManager manager) {
      super(manager, new ModelSnail(2), 1.0F);
      final ModelBase shell = new ModelSnail(1);
      this.addLayer(new LayerRenderer<EntitySnail>() {
         @Override
         public void doRenderLayer(EntitySnail e, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw,
                                   float headPitch, float scale) {
            if (e.isInvisible()) return;
            RenderSnail.this.bindTexture(TEXTURE);
            float[] c = EntitySnail.fleeceColorTable[e.getFleeceColor()];
            GlStateManager.color(c[0], c[1], c[2]);
            shell.setModelAttributes(RenderSnail.this.getMainModel());
            shell.setLivingAnimations(e, limbSwing, limbSwingAmount, partialTicks);
            shell.render(e, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
            GlStateManager.color(1.0F, 1.0F, 1.0F);
         }

         @Override
         public boolean shouldCombineTextures() {
            return true;
         }
      });
   }

   @Override
   protected void preRenderCallback(EntitySnail e, float partialTicks) {
      if (e.isChild()) GlStateManager.scale(0.35, 0.35, 0.35);
   }

   @Override
   protected ResourceLocation getEntityTexture(EntitySnail entity) {
      return TEXTURE;
   }
}
