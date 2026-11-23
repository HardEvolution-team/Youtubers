package com.electrifiedded.youtubers.Entity.TileEntity;

import com.electrifiedded.youtubers.YTBlocks;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.SoundCategory;

/** Ender-chest-like tile of the Bank: tracks players using it and plays the chest open/close sounds. */
public class TileEntityBank extends TileEntity implements ITickable {
   public float lidAngle;
   public float prevLidAngle;
   public int numPlayersUsing;
   private int ticksSinceSync;

   @Override
   public void update() {
      if (++this.ticksSinceSync % 80 == 0) this.world.addBlockEvent(this.pos, YTBlocks.Bank, 1, this.numPlayersUsing);
      this.prevLidAngle = this.lidAngle;
      double x = this.pos.getX() + 0.5, y = this.pos.getY() + 0.5, z = this.pos.getZ() + 0.5;
      if (this.numPlayersUsing > 0 && this.lidAngle == 0.0F)
         this.world.playSound(null, x, y, z, SoundEvents.BLOCK_CHEST_OPEN, SoundCategory.BLOCKS, 0.5F, this.world.rand.nextFloat() * 0.1F + 0.9F);
      if (this.numPlayersUsing == 0 && this.lidAngle > 0.0F || this.numPlayersUsing > 0 && this.lidAngle < 1.0F) {
         float prev = this.lidAngle;
         this.lidAngle += this.numPlayersUsing > 0 ? 0.1F : -0.1F;
         if (this.lidAngle > 1.0F) this.lidAngle = 1.0F;
         if (this.lidAngle < 0.5F && prev >= 0.5F)
            this.world.playSound(null, x, y, z, SoundEvents.BLOCK_CHEST_CLOSE, SoundCategory.BLOCKS, 0.5F, this.world.rand.nextFloat() * 0.1F + 0.9F);
         if (this.lidAngle < 0.0F) this.lidAngle = 0.0F;
      }
   }

   @Override
   public boolean receiveClientEvent(int id, int type) {
      if (id == 1) {
         this.numPlayersUsing = type;
         return true;
      }
      return super.receiveClientEvent(id, type);
   }

   @Override
   public void invalidate() {
      this.updateContainingBlockInfo();
      super.invalidate();
   }

   public void openChest() {
      this.numPlayersUsing++;
      this.world.addBlockEvent(this.pos, YTBlocks.Bank, 1, this.numPlayersUsing);
   }

   public void closeChest() {
      this.numPlayersUsing--;
      this.world.addBlockEvent(this.pos, YTBlocks.Bank, 1, this.numPlayersUsing);
   }

   public boolean canBeUsed(EntityPlayer p) {
      return this.world.getTileEntity(this.pos) == this && p.getDistanceSq(this.pos.getX() + 0.5, this.pos.getY() + 0.5, this.pos.getZ() + 0.5) <= 64.0;
   }
}
