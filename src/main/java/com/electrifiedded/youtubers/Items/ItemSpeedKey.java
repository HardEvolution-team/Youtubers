package com.electrifiedded.youtubers.Items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/** Speed key: held in the main hand gives Speed II; the enchanted one (meta 1) gives huge speed plus regen/resistance/fire res and hunger. */
public class ItemSpeedKey extends Item {
   public ItemSpeedKey() {
      this.setCreativeTab(CreativeTabs.TOOLS);
      this.setHasSubtypes(true);
      this.setMaxStackSize(1);
   }

   @Override
   public void onUpdate(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
      if (world.isRemote || !(entity instanceof EntityPlayer)) return;
      EntityPlayer player = (EntityPlayer)entity;
      if (player.getHeldItemMainhand() != stack) return;
      // 1.7 re-applied 0 tick effects every tick; a few ticks keep them alive between updates on 1.12
      if (stack.getMetadata() > 0) {
         player.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 3, 2, false, false));
         player.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 3, 0, false, false));
         player.addPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE, 3, 0, false, false));
         player.addPotionEffect(new PotionEffect(MobEffects.SPEED, 3, 20, false, false));
         player.addPotionEffect(new PotionEffect(MobEffects.HUNGER, 3, 10, false, false));
      } else {
         player.addPotionEffect(new PotionEffect(MobEffects.SPEED, 3, 1, false, false));
      }
   }

   @Override
   @SideOnly(Side.CLIENT)
   public boolean hasEffect(ItemStack stack) {
      return stack.getMetadata() > 0;
   }

   @Override
   public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> list) {
      if (this.isInCreativeTab(tab)) {
         list.add(new ItemStack(this, 1, 0));
         list.add(new ItemStack(this, 1, 1));
      }
   }
}
