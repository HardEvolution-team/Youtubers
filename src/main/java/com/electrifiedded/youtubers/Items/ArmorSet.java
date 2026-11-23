package com.electrifiedded.youtubers.Items;

import com.electrifiedded.youtubers.YTItems;
import com.electrifiedded.archaiclib.legacy.registry.LegacyRecipes;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;

/** A full helm/plate/legs/boots set registered as &lt;name&gt;Helm, &lt;name&gt;Plate, ... */
public class ArmorSet {
   public ItemYTArmor Head;
   public ItemYTArmor Chest;
   public ItemYTArmor Legs;
   public ItemYTArmor Boots;
   public String name;
   public ArmorMaterial material;

   public ArmorSet(String name, int durability, int[] reductionAmounts, int enchantability) {
      this(name, name, durability, reductionAmounts, enchantability);
   }

   /** reductionAmounts in the 1.7 order helm, plate, legs, boots. */
   public ArmorSet(String name, String file, int durability, int[] reductionAmounts, int enchantability) {
      this.name = file;
      this.material = material(name, durability, reductionAmounts, enchantability);
      this.make(this.material, name, file);
   }

   public static ArmorMaterial material(String name, int durability, int[] r, int enchantability) {
      return EnumHelper.addArmorMaterial("YOUTUBERS_" + name.toUpperCase(), "youtubers:" + name, durability,
         new int[]{r[3], r[2], r[1], r[0]}, enchantability, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 0.0F);
   }

   private void make(ArmorMaterial m, String name, String file) {
      this.Head = YTItems.armor(new ItemYTArmor(m, 0, file), name);
      this.Chest = YTItems.armor(new ItemYTArmor(m, 1, file), name);
      this.Legs = YTItems.armor(new ItemYTArmor(m, 2, file), name);
      this.Boots = YTItems.armor(new ItemYTArmor(m, 3, file), name);
   }

   public void addBasicRecipies(ItemStack item) {
      LegacyRecipes.addRecipe(new ItemStack(this.Head), "iii", "i i", 'i', item);
      LegacyRecipes.addRecipe(new ItemStack(this.Chest), "i i", "iii", "iii", 'i', item);
      LegacyRecipes.addRecipe(new ItemStack(this.Legs), "iii", "i i", "i i", 'i', item);
      LegacyRecipes.addRecipe(new ItemStack(this.Boots), "i i", "i i", 'i', item);
   }
}
