package com.electrifiedded.youtubers.Entity.Render;

import com.electrifiedded.youtubers.Entity.Mobs.EntityExplosiveMushroom;
import com.electrifiedded.youtubers.Entity.model.ModelCreeperLegs;
import com.electrifiedded.youtubers.Entity.model.Modelmushroom;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/** A mushroom (4 looks) walking on small creeper legs, swelling before it explodes. */
@SideOnly(Side.CLIENT)
public class RenderExplosiveMushroom extends RenderOceanCreeper<EntityExplosiveMushroom> {
   private static final ResourceLocation CREEPER = new ResourceLocation("textures/entity/creeper/creeper.png");
   private static final ResourceLocation[] TEX = {
      new ResourceLocation("youtubers", "textures/entity/Mushroom/mushroom.png"), new ResourceLocation("youtubers", "textures/entity/Mushroom/mushroom1.png"),
      new ResourceLocation("youtubers", "textures/entity/Mushroom/mushroom2.png"), new ResourceLocation("youtubers", "textures/entity/Mushroom/mushroom3.png")
   };

   public RenderExplosiveMushroom(RenderManager manager) {
      super(manager, new Modelmushroom(), 0.2F);
      final ModelBase legs = new ModelCreeperLegs();
      this.addLayer(new LayerRenderer<EntityExplosiveMushroom>() {
         @Override
         public void doRenderLayer(EntityExplosiveMushroom e, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw,
                                   float headPitch, float scale) {
            RenderExplosiveMushroom.this.bindTexture(CREEPER);
            GlStateManager.pushMatrix();
            GlStateManager.translate(0.0, 1.1, 0.0);
            GlStateManager.scale(0.3F, 0.3F, 0.3F);
            legs.setModelAttributes(RenderExplosiveMushroom.this.getMainModel());
            legs.setLivingAnimations(e, limbSwing, limbSwingAmount, partialTicks);
            legs.render(e, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
            GlStateManager.popMatrix();
         }

         @Override
         public boolean shouldCombineTextures() {
            return false;
         }
      });
   }

   @Override
   protected ResourceLocation getEntityTexture(EntityExplosiveMushroom entity) {
      return TEX[Math.max(0, Math.min(3, entity.getType()))];
   }
}
