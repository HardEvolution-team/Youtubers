package com.electrifiedded.youtubers.RandUtil;

import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandom.Item;

public class RandomItemStack extends Item {
   public ItemStack item;
   public int damage;

   public RandomItemStack(ItemStack item, int weight) {
      super(weight);
      this.item = item;
      this.damage = 0;
   }
}
