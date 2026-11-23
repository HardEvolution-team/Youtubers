package com.electrifiedded.youtubers.Entity.Render;

import com.electrifiedded.youtubers.Entity.Mobs.EntityOceanCreeper;
import com.electrifiedded.youtubers.Entity.model.ModelOceanicCreeper;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/** Oceanic creeper (and the explosive mushroom, which subclasses this): swells while its fuse burns. */
@SideOnly(Side.CLIENT)
public class RenderOceanCreeper<T extends EntityOceanCreeper> extends RenderLiving<T> {
   private static final ResourceLocation TEXTURE = new ResourceLocation("youtubers", "textures/entity/OceanCreeper.png");

   public RenderOceanCreeper(RenderManager manager) {
      this(manager, new ModelOceanicCreeper(), 0.5F);
   }

   protected RenderOceanCreeper(RenderManager manager, ModelBase model, float shadow) {
      super(manager, model, shadow);
   }

   @Override
   protected void preRenderCallback(T creeper, float partialTicks) {
      float f1 = creeper.getCreeperFlashIntensity(partialTicks);
      float f2 = 1.0F + MathHelper.sin(f1 * 100.0F) * f1 * 0.01F;
      f1 = MathHelper.clamp(f1, 0.0F, 1.0F);
      f1 *= f1;
      f1 *= f1;
      GlStateManager.scale((1.0F + f1 * 0.4F) * f2, (1.0F + f1 * 0.1F) / f2, (1.0F + f1 * 0.4F) * f2);
   }

   @Override
   protected ResourceLocation getEntityTexture(T entity) {
      return TEXTURE;
   }
}
