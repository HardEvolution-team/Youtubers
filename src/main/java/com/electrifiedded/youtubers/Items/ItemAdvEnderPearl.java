package com.electrifiedded.youtubers.Items;

import com.electrifiedded.youtubers.Entity.EntityAdvEnderPearl;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Advanced ender pearl. The 1.7 item only used itself up in creative mode (inverted check), so in survival it was
 * reusable; the port keeps it reusable in survival and does not consume it in creative either.
 */
public class ItemAdvEnderPearl extends Item {
   public ItemAdvEnderPearl() {
      this.maxStackSize = 16;
      this.setCreativeTab(CreativeTabs.MISC);
   }

   @Override
   public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
      ItemStack stack = player.getHeldItem(hand);
      world.playSound(null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_ENDERPEARL_THROW, SoundCategory.NEUTRAL, 0.5F,
         0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
      if (!world.isRemote) {
         EntityAdvEnderPearl pearl = new EntityAdvEnderPearl(world, player);
         pearl.shoot(player, player.rotationPitch, player.rotationYaw, 0.0F, 1.5F, 1.0F);
         world.spawnEntity(pearl);
      }
      return new ActionResult<>(EnumActionResult.SUCCESS, stack);
   }

   @Override
   @SideOnly(Side.CLIENT)
   public boolean hasEffect(ItemStack stack) {
      return true;
   }
}
