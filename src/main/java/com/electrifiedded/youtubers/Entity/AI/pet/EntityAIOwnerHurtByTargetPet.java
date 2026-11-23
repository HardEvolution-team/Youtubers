package com.electrifiedded.youtubers.Entity.AI.pet;

import com.electrifiedded.youtubers.Entity.YoutuberPetBase;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAITarget;

/** Pet attacks whoever attacked its owner. */
public class EntityAIOwnerHurtByTargetPet extends EntityAITarget {
   final YoutuberPetBase tameable;
   EntityLivingBase attacker;
   private int timestamp;

   public EntityAIOwnerHurtByTargetPet(YoutuberPetBase tameable) {
      super(tameable, false);
      this.tameable = tameable;
      this.setMutexBits(1);
   }

   private EntityLivingBase owner() {
      return this.tameable.getOwner() instanceof EntityLivingBase ? (EntityLivingBase)this.tameable.getOwner() : null;
   }

   @Override
   public boolean shouldExecute() {
      if (!this.tameable.isTamed()) return false;
      EntityLivingBase owner = this.owner();
      if (owner == null) return false;
      this.attacker = owner.getRevengeTarget();
      return owner.getRevengeTimer() != this.timestamp && this.isSuitableTarget(this.attacker, false)
         && this.tameable.shouldAttackEntity(this.attacker, owner);
   }

   @Override
   public void startExecuting() {
      this.taskOwner.setAttackTarget(this.attacker);
      EntityLivingBase owner = this.owner();
      if (owner != null) this.timestamp = owner.getRevengeTimer();
      super.startExecuting();
   }
}
