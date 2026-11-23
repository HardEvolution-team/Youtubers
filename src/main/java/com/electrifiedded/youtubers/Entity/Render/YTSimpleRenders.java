package com.electrifiedded.youtubers.Entity.Render;

import com.electrifiedded.archaiclib.legacy.client.LegacyRenderLiving;
import com.electrifiedded.youtubers.Entity.EntityAdvEnderPearl;
import com.electrifiedded.youtubers.Entity.EntityBomby;
import com.electrifiedded.youtubers.Entity.EntityDiamondMinecart;
import com.electrifiedded.youtubers.Entity.EntityTrayourus;
import com.electrifiedded.youtubers.Entity.Mobs.EntityCactus;
import com.electrifiedded.youtubers.Entity.Mobs.EntityEnderFish;
import com.electrifiedded.youtubers.Entity.Mobs.EntityMushroom;
import com.electrifiedded.youtubers.Entity.Mobs.EntityScorpion;
import com.electrifiedded.youtubers.Entity.cLUCKY.EntityCrazyEgg;
import com.electrifiedded.youtubers.Entity.cLUCKY.EntitycLuckey;
import com.electrifiedded.youtubers.Entity.cLUCKY.EventEgg;
import com.electrifiedded.youtubers.Entity.model.ModelCactus;
import com.electrifiedded.youtubers.Entity.model.ModelEnderFish;
import com.electrifiedded.youtubers.Entity.model.ModelScorpion;
import com.electrifiedded.youtubers.Entity.model.Modelmushroom;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelCreeper;
import net.minecraft.client.model.ModelVillager;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderChicken;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderMinecart;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.init.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/** Renderers that are a vanilla renderer with another texture/model/scale (the 1.7 one-class-per-mob renderers). */
@SideOnly(Side.CLIENT)
public final class YTSimpleRenders {
   private YTSimpleRenders() {}

   private static ResourceLocation yt(String path) {
      return new ResourceLocation("youtubers", "textures/entity/" + path);
   }

   public static void register() {
      LegacyRenderLiving.register(EntityCactus.class, ModelCactus::new, 0.5F, e -> yt("Cactus.png"));
      LegacyRenderLiving.register(EntityEnderFish.class, ModelEnderFish::new, 0.5F, e -> yt("EnderFish.png"));
      LegacyRenderLiving.register(EntityScorpion.class, ModelScorpion::new, 0.5F, e -> yt("Scorpion.png"));
      ResourceLocation[] mush = {yt("Mushroom/mushroom.png"), yt("Mushroom/mushroom1.png"), yt("Mushroom/mushroom2.png"), yt("Mushroom/mushroom3.png")};
      LegacyRenderLiving.register(EntityMushroom.class, Modelmushroom::new, 0.2F, e -> mush[Math.max(0, Math.min(3, e.getType()))]);
      ResourceLocation librarian = new ResourceLocation("textures/entity/villager/librarian.png");
      LegacyRenderLiving.register(EntityTrayourus.class, () -> new ModelVillager(0.0F), 0.5F, e -> librarian);
      ResourceLocation creeper = new ResourceLocation("textures/entity/creeper/creeper.png");
      RenderingRegistry.registerEntityRenderingHandler(EntityBomby.class, m -> new RenderLiving<EntityBomby>(m, new ModelCreeper(), 0.5F) {
         @Override
         protected void preRenderCallback(EntityBomby e, float partialTicks) {
            GlStateManager.scale(0.35, 0.35, 0.35);
         }

         @Override
         protected ResourceLocation getEntityTexture(EntityBomby e) {
            return creeper;
         }
      });
      RenderingRegistry.registerEntityRenderingHandler(EntitycLuckey.class, RenderChicken::new);
      ResourceLocation cart = yt("diamondminecart.png");
      RenderingRegistry.registerEntityRenderingHandler(EntityDiamondMinecart.class, m -> new RenderMinecart<EntityDiamondMinecart>(m) {
         @Override
         protected ResourceLocation getEntityTexture(EntityDiamondMinecart e) {
            return cart;
         }
      });
      RenderingRegistry.registerEntityRenderingHandler(EntityAdvEnderPearl.class, m -> new RenderSnowball<>(m, Items.ENDER_PEARL, Minecraft.getMinecraft().getRenderItem()));
      RenderingRegistry.registerEntityRenderingHandler(EntityCrazyEgg.class, m -> new RenderSnowball<>(m, Items.EGG, Minecraft.getMinecraft().getRenderItem()));
      RenderingRegistry.registerEntityRenderingHandler(EventEgg.class, m -> new RenderSnowball<>(m, Items.EGG, Minecraft.getMinecraft().getRenderItem()));
   }
}
