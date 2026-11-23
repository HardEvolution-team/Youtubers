package com.electrifiedded.youtubers.Items;

import com.electrifiedded.youtubers.Youtubers;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/** Jetpack chestplate (iron stats): sneak while airborne to fly up, see YTEventHandler; rendered with a 3D jetpack model. */
public class ItemJetPack extends ItemArmor {
   public ItemJetPack() {
      super(ArmorMaterial.IRON, 1, EntityEquipmentSlot.CHEST);
      this.setCreativeTab(CreativeTabs.TRANSPORTATION);
   }

   @Override
   public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
      return "youtubers:textures/models/armor/JetPack3D.png";
   }

   @Override
   @SideOnly(Side.CLIENT)
   public ModelBiped getArmorModel(EntityLivingBase entity, ItemStack stack, EntityEquipmentSlot slot, ModelBiped def) {
      ModelBiped model = Youtubers.proxy.getArmorModel(0);
      if (model == null) return null;
      model.setModelAttributes(def);
      model.bipedBody.showModel = slot == EntityEquipmentSlot.CHEST;
      model.bipedRightArm.showModel = slot == EntityEquipmentSlot.CHEST;
      model.bipedLeftArm.showModel = slot == EntityEquipmentSlot.CHEST;
      return model;
   }
}
