package com.electrifiedded.youtubers.RandUtil;

import net.minecraft.util.WeightedRandom.Item;

public class RandomItem extends Item {
   public net.minecraft.item.Item item;
   public int damage;

   public RandomItem(net.minecraft.item.Item item, int weight) {
      super(weight);
      this.item = item;
      this.damage = 0;
   }

   public RandomItem(net.minecraft.item.Item item, int damage, int weight) {
      super(weight);
      this.item = item;
      this.damage = damage;
   }
}
