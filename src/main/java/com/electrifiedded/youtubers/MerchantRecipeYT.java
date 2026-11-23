package com.electrifiedded.youtubers;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.village.MerchantRecipe;

public class MerchantRecipeYT extends MerchantRecipe {
   public MerchantRecipeYT(ItemStack itemin, Item itemout) {
      super(itemin, itemout);
      this.increaseMaxTradeUses(2000000000);
   }

   public MerchantRecipeYT(ItemStack itemin, ItemStack itemout) {
      super(itemin, itemout);
      this.increaseMaxTradeUses(2000000000);
   }

   public MerchantRecipeYT(ItemStack itemin1, ItemStack itemin2, ItemStack itemout) {
      super(itemin1, itemin2, itemout);
      this.increaseMaxTradeUses(2000000000);
   }

   public MerchantRecipeYT(ItemStack itemin1, ItemStack itemin2, Item itemout) {
      super(itemin1, itemin2, new ItemStack(itemout));
      this.increaseMaxTradeUses(2000000000);
   }
}
