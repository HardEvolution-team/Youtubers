package com.electrifiedded.youtubers.Entity;

import com.electrifiedded.youtubers.Youtubers;
import java.util.Random;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.world.World;

/** Lets a player act as a villager: offers are generated from the player's name (see Youtubers.addPlayerTrades). */
public class PlayerTrade implements net.minecraft.entity.IMerchant {
   private final EntityPlayer TradingPlayer;
   private EntityPlayer buyingPlayer;
   MerchantRecipeList buyingList;
   private final Random rand = new com.electrifiedded.archaiclib.util.FastRandom();
   int wealth;
   private boolean needsInitilization;
   private int timeUntilReset;

   public PlayerTrade(EntityPlayer var1) {
      this.TradingPlayer = var1;
   }

   public EntityPlayer getTrader() {
      return this.TradingPlayer;
   }

   @Override
   public void setCustomer(EntityPlayer var1) {
      this.buyingPlayer = var1;
   }

   @Override
   public EntityPlayer getCustomer() {
      return this.buyingPlayer;
   }

   @Override
   public MerchantRecipeList getRecipes(EntityPlayer var1) {
      if (this.TradingPlayer.world.isRemote) return this.buyingList == null ? new MerchantRecipeList() : this.buyingList;
      if (this.buyingList == null) this.addDefaultEquipmentAndRecipies();
      return this.buyingList;
   }

   private void addDefaultEquipmentAndRecipies() {
      MerchantRecipeList list = new MerchantRecipeList();
      Youtubers.addPlayerTrades(list, this.TradingPlayer.getName(), false);
      if (list.isEmpty()) list.add(new MerchantRecipe(new ItemStack(Items.GOLD_INGOT), Items.EMERALD));
      if (this.buyingList == null) this.buyingList = new MerchantRecipeList();
      this.buyingList.addAll(list);
   }

   @Override
   public void setRecipes(MerchantRecipeList var1) {
      if (this.TradingPlayer.world.isRemote) this.buyingList = var1; // offers synced from the server
   }

   @Override
   public void useRecipe(MerchantRecipe recipe) {
      if (recipe == this.buyingList.get(this.buyingList.size() - 1)) {
         this.timeUntilReset = 40;
         this.needsInitilization = true;
      }
      if (recipe.getItemToBuy().getItem() == Items.EMERALD) this.wealth += recipe.getItemToBuy().getCount();
   }

   public boolean isTrading() {
      return this.buyingPlayer != null;
   }

   public void updateAITick() {
      if (!this.isTrading() && this.timeUntilReset > 0) {
         this.timeUntilReset--;
         if (this.timeUntilReset <= 0) {
            if (this.needsInitilization) {
               this.addDefaultEquipmentAndRecipies();
               this.needsInitilization = false;
            }
            if (this.buyingPlayer != this.TradingPlayer) this.TradingPlayer.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 200, 0));
         }
      }
   }

   @Override
   public ITextComponent getDisplayName() {
      return new TextComponentString(this.TradingPlayer.getName());
   }

   @Override
   public void verifySellingItem(ItemStack arg0) {
   }

   @Override
   public World getWorld() {
      return this.TradingPlayer.world;
   }

   @Override
   public BlockPos getPos() {
      return new BlockPos(this.TradingPlayer);
   }
}
