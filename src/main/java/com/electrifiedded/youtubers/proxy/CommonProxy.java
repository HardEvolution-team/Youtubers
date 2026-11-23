package com.electrifiedded.youtubers.proxy;

import com.electrifiedded.youtubers.Entity.EntityYTBase;
import com.electrifiedded.youtubers.Entity.PlayerTrade;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.player.InventoryPlayer;

public class CommonProxy {
   public void preInit() {
   }

   public void init() {
   }

   public ModelBiped getArmorModel(int id) {
      return null;
   }

   public Object newGUIyoutuberPL(InventoryPlayer inventory, PlayerTrade trader) {
      return null;
   }

   public Object newGUIyoutuber(InventoryPlayer inventory, EntityYTBase entityYTBase) {
      return null;
   }
}
