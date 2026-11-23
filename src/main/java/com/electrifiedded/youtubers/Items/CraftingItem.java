package com.electrifiedded.youtubers.Items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/** Simple crafting material; icon is "youtubers:&lt;name&gt;" or a vanilla item icon (Budder ingots use gold, Super Diamond the diamond). */
public class CraftingItem extends Item {
   public final String n;
   public final String texture;
   boolean effect;

   public CraftingItem(String name) {
      this(name, "youtubers:" + name);
   }

   public CraftingItem(String name, String texture) {
      this.setTranslationKey(name);
      this.setCreativeTab(CreativeTabs.MATERIALS);
      this.n = name;
      this.texture = texture;
   }

   @Override
   public boolean hasEffect(ItemStack item) {
      return this.effect;
   }

   public Item giveEfect() {
      this.effect = true;
      return this;
   }
}
