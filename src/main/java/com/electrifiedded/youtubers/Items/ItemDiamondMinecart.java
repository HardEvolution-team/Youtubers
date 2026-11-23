package com.electrifiedded.youtubers.Items;

import com.electrifiedded.youtubers.Entity.EntityDiamondMinecart;
import net.minecraft.block.BlockDispenser;
import net.minecraft.block.BlockRailBase;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.dispenser.BehaviorDefaultDispenseItem;
import net.minecraft.dispenser.IBehaviorDispenseItem;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/** Places / dispenses the Diamond Minecart. */
public class ItemDiamondMinecart extends Item {
   private static final IBehaviorDispenseItem DISPENSE = new BehaviorDefaultDispenseItem() {
      private final BehaviorDefaultDispenseItem fallback = new BehaviorDefaultDispenseItem();

      @Override
      public ItemStack dispenseStack(IBlockSource source, ItemStack stack) {
         EnumFacing facing = source.getBlockState().getValue(BlockDispenser.FACING);
         World world = source.getWorld();
         double x = source.getX() + facing.getXOffset() * 1.125;
         double y = Math.floor(source.getY()) + facing.getYOffset();
         double z = source.getZ() + facing.getZOffset() * 1.125;
         BlockPos p = source.getBlockPos().offset(facing);
         double dy;
         if (BlockRailBase.isRailBlock(world.getBlockState(p))) {
            dy = 0.0625;
         } else {
            if (!world.isAirBlock(p) || !BlockRailBase.isRailBlock(world.getBlockState(p.down()))) return this.fallback.dispense(source, stack);
            dy = -0.9375;
         }
         EntityMinecart cart = new EntityDiamondMinecart(world, x, y + dy, z);
         if (stack.hasDisplayName()) cart.setCustomNameTag(stack.getDisplayName());
         world.spawnEntity(cart);
         stack.shrink(1);
         return stack;
      }

      @Override
      protected void playDispenseSound(IBlockSource source) {
         source.getWorld().playEvent(1000, source.getBlockPos(), 0);
      }
   };

   public ItemDiamondMinecart() {
      this.maxStackSize = 1;
      this.setCreativeTab(CreativeTabs.TRANSPORTATION);
      BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(this, DISPENSE);
   }

   @Override
   public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hx, float hy, float hz) {
      if (!BlockRailBase.isRailBlock(world.getBlockState(pos))) return EnumActionResult.FAIL;
      ItemStack stack = player.getHeldItem(hand);
      if (!world.isRemote) {
         EntityMinecart cart = new EntityDiamondMinecart(world, pos.getX() + 0.5, pos.getY() + 0.0625, pos.getZ() + 0.5);
         if (stack.hasDisplayName()) cart.setCustomNameTag(stack.getDisplayName());
         world.spawnEntity(cart);
      }
      stack.shrink(1);
      return EnumActionResult.SUCCESS;
   }
}
