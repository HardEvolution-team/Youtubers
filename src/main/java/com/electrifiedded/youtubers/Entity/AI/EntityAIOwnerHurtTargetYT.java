package com.electrifiedded.youtubers.Entity.AI;

import com.electrifiedded.youtubers.Entity.EntityYTBase;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAITarget;

/** Tamed NPC attacks what its owner attacks. */
public class EntityAIOwnerHurtTargetYT extends EntityAITarget {
   final EntityYTBase tameable;
   EntityLivingBase target;
   private int timestamp;

   public EntityAIOwnerHurtTargetYT(EntityYTBase tameable) {
      super(tameable, false);
      this.tameable = tameable;
      this.setMutexBits(1);
   }

   @Override
   public boolean shouldExecute() {
      if (!this.tameable.isTamed()) return false;
      EntityLivingBase owner = this.tameable.getOwner();
      if (owner == null) return false;
      this.target = owner.getLastAttackedEntity();
      return owner.getLastAttackedEntityTime() != this.timestamp && this.isSuitableTarget(this.target, false);
   }

   @Override
   public void startExecuting() {
      this.taskOwner.setAttackTarget(this.target);
      EntityLivingBase owner = this.tameable.getOwner();
      if (owner != null) this.timestamp = owner.getLastAttackedEntityTime();
      super.startExecuting();
   }
}
