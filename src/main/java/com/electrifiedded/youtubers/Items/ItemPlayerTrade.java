package com.electrifiedded.youtubers.Items;

import com.electrifiedded.youtubers.GUI.YTGuiHandler;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Player Trader: right-click another player to see the offers generated from his name; the advanced one (meta 1) also
 * opens your own offers when used on a block.
 */
public class ItemPlayerTrade extends Item {
   public ItemPlayerTrade() {
      this.setMaxStackSize(1);
      this.setCreativeTab(CreativeTabs.TOOLS);
      this.setHasSubtypes(true);
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

   @Override
   public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hx, float hy, float hz) {
      if (player.getHeldItem(hand).getMetadata() <= 0) return EnumActionResult.PASS;
      YTGuiHandler.openPlayer(player, player);
      return EnumActionResult.SUCCESS;
   }

   @Override
   public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer player, EntityLivingBase ent, EnumHand hand) {
      if (!(ent instanceof EntityPlayer)) return false;
      YTGuiHandler.openPlayer(player, (EntityPlayer)ent);
      return true;
   }
}
