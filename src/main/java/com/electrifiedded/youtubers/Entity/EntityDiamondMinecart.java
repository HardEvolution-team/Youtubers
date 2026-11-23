package com.electrifiedded.youtubers.Entity;

import com.electrifiedded.youtubers.YTItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRailPowered;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.item.EntityMinecartEmpty;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

/**
 * TDM's Diamond Minecart: fast rideable cart (speed cap 1.1 on rails, 3.0 on ice), keeps its speed (low drag), immune
 * to explosions; activator rails let it pick up mobs that bump into it and powered ones throw the rider off.
 */
public class EntityDiamondMinecart extends EntityMinecartEmpty {
   public EntityDiamondMinecart(World world) {
      super(world);
      this.setCurrentCartSpeedCapOnRail(0.6F);
      this.setDragAir(0.9999999880790711);
      this.setMaxSpeedAirLateral(3.0F);
   }

   public EntityDiamondMinecart(World world, double x, double y, double z) {
      super(world, x, y, z);
      this.setCurrentCartSpeedCapOnRail(1.1F);
      this.setDragAir(0.9999999880790711);
      this.setMaxSpeedAirLateral(3.0F);
   }

   @Override
   protected void applyDrag() {
      double d = this.isBeingRidden() ? 0.998999979019165 : 0.997F;
      this.motionX *= d;
      this.motionY *= 0.0;
      this.motionZ *= d;
   }

   @Override
   public ItemStack getCartItem() {
      return new ItemStack(YTItems.DiamondMinecart);
   }

   @Override
   public void killMinecart(DamageSource source) {
      this.setDead();
      if (this.world.getGameRules().getBoolean("doEntityDrops")) this.entityDropItem(new ItemStack(YTItems.DiamondMinecart, 1), 0.0F);
   }

   private Block blockBelow() {
      return this.world.getBlockState(new BlockPos(MathHelper.floor(this.posX), MathHelper.floor(this.posY) - 1, MathHelper.floor(this.posZ))).getBlock();
   }

   private boolean isIce() {
      Block b = this.blockBelow();
      return b == Blocks.ICE || b == Blocks.PACKED_ICE;
   }

   @Override
   protected double getMaximumSpeed() {
      return this.isIce() ? 3.0 : 0.4;
   }

   @Override
   public void applyEntityCollision(Entity other) {
      if (!this.world.isRemote && !this.isBeingRidden() && !other.isRiding() && other.getRidingEntity() == null) {
         IBlockState s = this.world.getBlockState(new BlockPos(this));
         if (s.getBlock() == Blocks.ACTIVATOR_RAIL && !s.getValue(BlockRailPowered.POWERED)) {
            other.startRiding(this);
            return;
         }
      }
      super.applyEntityCollision(other);
   }

   @Override
   public void onActivatorRailPass(int x, int y, int z, boolean receivingPower) {
      if (receivingPower) {
         if (this.isBeingRidden()) this.removePassengers();
         if (this.getRollingAmplitude() == 0) {
            this.setRollingDirection(-this.getRollingDirection());
            this.setRollingAmplitude(10);
            this.setDamage(50.0F);
            this.markVelocityChanged();
         }
      }
   }

   @Override
   protected void moveDerailedMinecart() {
      double max = this.onGround ? this.getMaximumSpeed() : this.getMaxSpeedAirLateral();
      this.motionX = MathHelper.clamp(this.motionX, -max, max);
      this.motionZ = MathHelper.clamp(this.motionZ, -max, max);
      double moveY = this.motionY;
      if (this.getMaxSpeedAirVertical() > 0.0F && this.motionY > this.getMaxSpeedAirVertical()) {
         moveY = this.getMaxSpeedAirVertical();
         if (Math.abs(this.motionX) < 0.3F && Math.abs(this.motionZ) < 0.3F) {
            moveY = 0.15F;
            this.motionY = moveY;
         }
      }
      if (this.onGround) {
         double d = this.isIce() ? 0.93 : 0.5;
         this.motionX *= d;
         this.motionY *= d;
         this.motionZ *= d;
      }
      this.move(MoverType.SELF, this.motionX, moveY, this.motionZ);
      if (!this.onGround) {
         this.motionX *= this.getDragAir();
         this.motionY *= this.getDragAir();
         this.motionZ *= this.getDragAir();
      }
   }

   @Override
   public boolean attackEntityFrom(DamageSource src, float amount) {
      return !src.isExplosion() && super.attackEntityFrom(src, amount);
   }
}
