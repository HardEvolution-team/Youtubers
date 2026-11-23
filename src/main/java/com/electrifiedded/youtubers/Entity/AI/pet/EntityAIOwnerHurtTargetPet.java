package com.electrifiedded.youtubers.Entity.AI.pet;

import com.electrifiedded.youtubers.Entity.YoutuberPetBase;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAITarget;

/** Pet attacks what its owner attacks. */
public class EntityAIOwnerHurtTargetPet extends EntityAITarget {
   final YoutuberPetBase tameable;
   EntityLivingBase target;
   private int timestamp;

   public EntityAIOwnerHurtTargetPet(YoutuberPetBase tameable) {
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
      this.target = owner.getLastAttackedEntity();
      return owner.getLastAttackedEntityTime() != this.timestamp && this.isSuitableTarget(this.target, false)
         && this.tameable.shouldAttackEntity(this.target, owner);
   }

   @Override
   public void startExecuting() {
      this.taskOwner.setAttackTarget(this.target);
      EntityLivingBase owner = this.owner();
      if (owner != null) this.timestamp = owner.getLastAttackedEntityTime();
      super.startExecuting();
   }
}
