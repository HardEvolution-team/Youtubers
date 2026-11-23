package com.electrifiedded.youtubers.Entity.AI;

import com.electrifiedded.youtubers.Entity.EntityYTBase;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;

/** Keeps a tamed NPC sitting (stands up when its owner is attacked nearby). */
public class EntityAISitYT extends EntityAIBase {
   private final EntityYTBase theEntity;
   private boolean isSitting;

   public EntityAISitYT(EntityYTBase entity) {
      this.theEntity = entity;
      this.setMutexBits(5);
   }

   @Override
   public boolean shouldExecute() {
      if (!this.theEntity.isTamed() || this.theEntity.isInWater() || !this.theEntity.onGround) return false;
      EntityLivingBase owner = this.theEntity.getOwner();
      if (owner == null) return true;
      if (this.theEntity.getDistanceSq(owner) < 144.0 && owner.getRevengeTarget() != null) return false;
      return this.isSitting;
   }

   @Override
   public void startExecuting() {
      this.theEntity.getNavigator().clearPath();
      this.theEntity.setSitting(true);
   }

   @Override
   public void resetTask() {
      this.theEntity.setSitting(false);
   }

   public void setSitting(boolean sitting) {
      this.isSitting = sitting;
   }
}
