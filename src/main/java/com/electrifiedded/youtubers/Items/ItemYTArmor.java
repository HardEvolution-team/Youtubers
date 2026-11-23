package com.electrifiedded.youtubers.Items;

import com.electrifiedded.youtubers.Youtubers;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

/**
 * Youtuber armour piece. 1.7 type 0 helm, 1 plate, 2 legs, 3 boots; model textures
 * textures/models/armor/&lt;file&gt;_1.png (_2 for legs), icons textures/items/Armor/&lt;file&gt;&lt;Helm|Plate|Legs|Boots&gt;.
 */
public class ItemYTArmor extends ItemArmor {
   public static final String[] ARMOR_TYPES = {"Helm", "Plate", "Legs", "Boots"};
   public static final EntityEquipmentSlot[] SLOTS = {EntityEquipmentSlot.HEAD, EntityEquipmentSlot.CHEST, EntityEquipmentSlot.LEGS, EntityEquipmentSlot.FEET};
   public final String armorFile;
   public final int type;

   public ItemYTArmor(ArmorMaterial material, int type, String armorFile) {
      super(material, 1, SLOTS[type]);
      this.armorFile = armorFile;
      this.setCreativeTab(Youtubers.tabArmor);
      this.type = type;
   }

   public String iconName() {
      return "youtubers:Armor/" + this.armorFile + ARMOR_TYPES[this.type];
   }

   @Override
   public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
      return "youtubers:textures/models/armor/" + this.armorFile + (this.type == 2 ? "_2" : "_1") + ".png";
   }
}
