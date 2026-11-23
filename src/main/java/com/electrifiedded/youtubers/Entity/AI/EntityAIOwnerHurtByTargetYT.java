package com.electrifiedded.youtubers.Entity.AI;

import com.electrifiedded.youtubers.Entity.EntityYTBase;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAITarget;

/** Tamed NPC attacks whoever attacked its owner. */
public class EntityAIOwnerHurtByTargetYT extends EntityAITarget {
   final EntityYTBase tameable;
   EntityLivingBase attacker;
   private int timestamp;

   public EntityAIOwnerHurtByTargetYT(EntityYTBase tameable) {
      super(tameable, false);
      this.tameable = tameable;
      this.setMutexBits(1);
   }

   @Override
   public boolean shouldExecute() {
      if (!this.tameable.isTamed()) return false;
      EntityLivingBase owner = this.tameable.getOwner();
      if (owner == null) return false;
      this.attacker = owner.getRevengeTarget();
      return owner.getRevengeTimer() != this.timestamp && this.isSuitableTarget(this.attacker, false);
   }

   @Override
   public void startExecuting() {
      this.taskOwner.setAttackTarget(this.attacker);
      EntityLivingBase owner = this.tameable.getOwner();
      if (owner != null) this.timestamp = owner.getRevengeTimer();
      super.startExecuting();
   }
}
