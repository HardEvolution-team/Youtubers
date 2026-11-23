package com.electrifiedded.youtubers.proxy;

import com.electrifiedded.youtubers.YTItems;
import com.electrifiedded.youtubers.Entity.EntityCraigTheMailman;
import com.electrifiedded.youtubers.Entity.EntityGrimm;
import com.electrifiedded.youtubers.Entity.EntitySnail;
import com.electrifiedded.youtubers.Entity.EntityYTBase;
import com.electrifiedded.youtubers.Entity.EntityYoutuber;
import com.electrifiedded.youtubers.Entity.PlayerEntity;
import com.electrifiedded.youtubers.Entity.PlayerTrade;
import com.electrifiedded.youtubers.Entity.Mobs.EntityExplosiveMushroom;
import com.electrifiedded.youtubers.Entity.Mobs.EntityOceanCreeper;
import com.electrifiedded.youtubers.Entity.Render.PlayerRender;
import com.electrifiedded.youtubers.Entity.Render.RenderCraigTheMailman;
import com.electrifiedded.youtubers.Entity.Render.RenderExplosiveMushroom;
import com.electrifiedded.youtubers.Entity.Render.RenderGrimm;
import com.electrifiedded.youtubers.Entity.Render.RenderOceanCreeper;
import com.electrifiedded.youtubers.Entity.Render.RenderSnail;
import com.electrifiedded.youtubers.Entity.Render.RenderYoutuber;
import com.electrifiedded.youtubers.Entity.Render.YTSimpleRenders;
import com.electrifiedded.youtubers.Entity.model.ModelJetpack;
import com.electrifiedded.youtubers.GUI.GUIyoutuber;
import com.electrifiedded.youtubers.GUI.GUIyoutuberPL;
import com.electrifiedded.youtubers.Items.ColoredCraftingItem;
import com.electrifiedded.youtubers.Items.Spawners.YTEggBase;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {
   private static ModelJetpack jetpackModel;

   @Override
   public void preInit() {
      RenderingRegistry.registerEntityRenderingHandler(EntityYoutuber.class, RenderYoutuber::new);
      RenderingRegistry.registerEntityRenderingHandler(PlayerEntity.class, PlayerRender::new);
      RenderingRegistry.registerEntityRenderingHandler(EntityCraigTheMailman.class, RenderCraigTheMailman::new);
      RenderingRegistry.registerEntityRenderingHandler(EntityGrimm.class, RenderGrimm::new);
      RenderingRegistry.registerEntityRenderingHandler(EntityOceanCreeper.class, RenderOceanCreeper::new);
      RenderingRegistry.registerEntityRenderingHandler(EntityExplosiveMushroom.class, RenderExplosiveMushroom::new);
      RenderingRegistry.registerEntityRenderingHandler(EntitySnail.class, RenderSnail::new);
      YTSimpleRenders.register();
   }

   @Override
   public void init() {
      Item[] eggs = {YTItems.Spawner, YTItems.SpawnerPL, YTItems.SpawnerTrayourus, YTItems.SpawnerLuckey, YTItems.MobSpawner};
      for (Item egg : eggs) {
         Minecraft.getMinecraft().getItemColors().registerItemColorHandler((stack, layer) -> ((YTEggBase)stack.getItem()).getColor(stack, layer), egg);
      }
      Minecraft.getMinecraft().getItemColors().registerItemColorHandler(
         (stack, layer) -> layer == 0 ? ((ColoredCraftingItem)stack.getItem()).tintFor(stack.getMetadata()) : 0xFFFFFF, YTItems.ArmorGems);
   }

   @Override
   public ModelBiped getArmorModel(int id) {
      if (id != 0) return null;
      if (jetpackModel == null) jetpackModel = new ModelJetpack(1.0F);
      return jetpackModel;
   }

   @Override
   public Object newGUIyoutuberPL(InventoryPlayer inventory, PlayerTrade trader) {
      return new GUIyoutuberPL(inventory, trader);
   }

   @Override
   public Object newGUIyoutuber(InventoryPlayer inventory, EntityYTBase entityYTBase) {
      return new GUIyoutuber(inventory, entityYTBase);
   }
}
