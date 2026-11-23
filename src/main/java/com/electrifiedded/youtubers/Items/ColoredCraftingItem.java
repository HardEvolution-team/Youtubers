package com.electrifiedded.youtubers.Items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

/** 16 dye coloured variants (meta = 1.7 wool colour) of a crafting item, e.g. the armour gems. */
public class ColoredCraftingItem extends CraftingItem {
   public static final String[] COLORS = {
      "White", "Orange", "Magenta", "LightBlue", "Yellow", "Lime", "Pink", "Gray", "LightGray", "Cyan", "Purple", "Blue", "Brown", "Green", "Red", "Black"
   };
   public ItemStack White, Orange, Magenta, LightBlue, Yellow, Lime, Pink, Gray, LightGray, Cyan, Purple, Blue, Brown, Green, Red, Black;

   public ColoredCraftingItem(String name, boolean useColors) {
      super(name);
      this.setHasSubtypes(true);
      ItemStack[] s = new ItemStack[16];
      for (int i = 0; i < 16; i++) s[i] = new ItemStack(this, 1, i);
      White = s[0]; Orange = s[1]; Magenta = s[2]; LightBlue = s[3]; Yellow = s[4]; Lime = s[5]; Pink = s[6]; Gray = s[7];
      LightGray = s[8]; Cyan = s[9]; Purple = s[10]; Blue = s[11]; Brown = s[12]; Green = s[13]; Red = s[14]; Black = s[15];
   }

   @Override
   public String getTranslationKey(ItemStack item) {
      return super.getTranslationKey(item) + "." + COLORS[item.getMetadata() & 15];
   }

   /**
    * Icon of each colour. The original jar ships only some of them: the black gem is "ObsidianEmerald" and the lime gem
    * "Lyme"; colours without a texture (only reachable from the creative tab) reuse the gray gem tinted with the dye colour.
    */
   public String textureFor(int meta) {
      switch (meta & 15) {
         case 1: return "youtubers:OrangeGem";
         case 5: return "youtubers:Lyme";
         case 6: return "youtubers:PinkGem";
         case 7: return "youtubers:GrayGem";
         case 11: return "youtubers:BlueGem";
         case 13: return "youtubers:GreenGem";
         case 14: return "youtubers:RedGem";
         case 15: return "youtubers:ObsidianEmerald";
         default: return "youtubers:GrayGem";
      }
   }

   /** Tint for the variants that borrow the gray gem texture, white otherwise. */
   public int tintFor(int meta) {
      int m = meta & 15;
      if (m == 1 || m == 5 || m == 6 || m == 7 || m == 11 || m == 13 || m == 14 || m == 15) return 0xFFFFFF;
      return EnumDyeColor.byMetadata(m).getColorValue();
   }

   @Override
   public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> list) {
      if (this.isInCreativeTab(tab)) for (int a = 0; a <= 15; a++) list.add(new ItemStack(this, 1, a));
   }
}
