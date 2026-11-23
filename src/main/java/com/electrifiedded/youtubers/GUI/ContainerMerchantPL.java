package com.electrifiedded.youtubers.GUI;

import com.electrifiedded.youtubers.Entity.PlayerTrade;
import net.minecraft.entity.IMerchant;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ContainerMerchant;
import net.minecraft.inventory.Slot;

/**
 * Trading with a player (Player Trader item). Vanilla merchant container (so the vanilla "MC|TrSel" offer selection
 * reaches the server) with the trade slots moved to the layout of the mod's GUI texture.
 */
public class ContainerMerchantPL extends ContainerMerchant implements YTNetwork.IMerchantContainer {
   private final PlayerTrade theMerchant;

   public ContainerMerchantPL(InventoryPlayer playerInventory, PlayerTrade merchant) {
      super(playerInventory, merchant, playerInventory.player.world);
      this.theMerchant = merchant;
      layoutTradeSlots(this.inventorySlots.get(0), this.inventorySlots.get(1), this.inventorySlots.get(2));
   }

   static void layoutTradeSlots(Slot in1, Slot in2, Slot out) {
      in1.xPos = 101; in1.yPos = 38;
      in2.xPos = 119; in2.yPos = 38;
      out.xPos = 141; out.yPos = 38;
   }

   @Override
   public IMerchant getMerchantRef() {
      return this.theMerchant;
   }
}
