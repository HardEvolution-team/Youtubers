package com.electrifiedded.youtubers.Entity.AI;

import com.electrifiedded.youtubers.Entity.EntityYTBase;
import com.electrifiedded.youtubers.GUI.InventoryMerchantYT;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;

/** An NPC carrying torches (held item or backpack) places one where it stands when it is dark (light 9 or less). */
public class EntityAItorch extends EntityAIBase {
   private final EntityYTBase villager;
   private int stack = -1;

   public EntityAItorch(EntityYTBase villager) {
      this.villager = villager;
   }

   @Override
   public boolean shouldExecute() {
      if (!this.villager.isEntityAlive() || this.villager.isInWater() || !this.villager.onGround || this.villager.velocityChanged) return false;
      BlockPos p = new BlockPos(this.villager);
      if (!this.villager.world.getBlockState(p.down()).isSideSolid(this.villager.world, p.down(), EnumFacing.UP)) return false;
      if (!this.villager.world.isAirBlock(p) || this.villager.world.getLight(p) > 9) return false;
      this.stack = this.findTorches();
      return this.stack != -1;
   }

   private int findTorches() {
      for (int i = InventoryMerchantYT.HELD; i <= InventoryMerchantYT.BACKPACK; i++) {
         ItemStack s = this.villager.inventory.getStackInSlot(i);
         if (!s.isEmpty() && s.getItem() == Item.getItemFromBlock(Blocks.TORCH)) return i;
      }
      return -1;
   }

   @Override
   public void startExecuting() {
      this.villager.world.setBlockState(new BlockPos(this.villager), Blocks.TORCH.getDefaultState());
      ItemStack item = this.villager.inventory.getStackInSlot(this.stack).copy();
      item.shrink(1);
      this.villager.inventory.setInventorySlotContents(this.stack, item);
   }

   @Override
   public boolean shouldContinueExecuting() {
      return false;
   }
}
