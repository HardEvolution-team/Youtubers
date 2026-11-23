package com.electrifiedded.youtubers.Items;

import com.electrifiedded.youtubers.Youtubers;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/** One money note item per value (1$ .. 1000000$). */
public class ItemMoney extends Item {
   public static final String[] VALUES = {"1$", "5$", "10$", "50$", "100$", "500$", "1000$", "5000$", "10000$", "50000$", "100000$", "500000$", "1000000$"};
   public final int n;

   public ItemMoney(int n) {
      this.setCreativeTab(Youtubers.tabMisc);
      this.setTranslationKey("ItemMoney");
      this.n = n;
   }

   @Override
   public String getTranslationKey(ItemStack stack) {
      return super.getTranslationKey() + "." + getValue(this.n);
   }

   public static String getValue(int damage) {
      return damage >= 0 && damage < VALUES.length ? VALUES[damage] : "Money";
   }
}
