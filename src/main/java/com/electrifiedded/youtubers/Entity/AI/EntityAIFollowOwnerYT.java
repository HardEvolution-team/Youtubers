package com.electrifiedded.youtubers.Entity.AI;

import com.electrifiedded.youtubers.Entity.EntityYTBase;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.pathfinding.PathNodeType;

/** Tamed NPC follows its owner (teleports when far and stuck), like a wolf. */
public class EntityAIFollowOwnerYT extends EntityAIBase {
   private final EntityYTBase thePet;
   private EntityLivingBase theOwner;
   private final double followSpeed;
   private int timeToRecalcPath;
   private final float maxDist;
   private final float minDist;
   private float oldWaterCost;

   public EntityAIFollowOwnerYT(EntityYTBase pet, double speed, float minDist, float maxDist) {
      this.thePet = pet;
      this.followSpeed = speed;
      this.minDist = minDist;
      this.maxDist = maxDist;
      this.setMutexBits(3);
   }

   @Override
   public boolean shouldExecute() {
      EntityLivingBase owner = this.thePet.getOwner();
      if (owner == null || this.thePet.isSitting() || this.thePet.getDistanceSq(owner) < this.minDist * this.minDist) return false;
      this.theOwner = owner;
      return true;
   }

   @Override
   public boolean shouldContinueExecuting() {
      return !this.thePet.getNavigator().noPath() && this.thePet.getDistanceSq(this.theOwner) > this.maxDist * this.maxDist && !this.thePet.isSitting();
   }

   @Override
   public void startExecuting() {
      this.timeToRecalcPath = 0;
      this.oldWaterCost = this.thePet.getPathPriority(PathNodeType.WATER);
      this.thePet.setPathPriority(PathNodeType.WATER, 0.0F);
   }

   @Override
   public void resetTask() {
      this.theOwner = null;
      this.thePet.getNavigator().clearPath();
      this.thePet.setPathPriority(PathNodeType.WATER, this.oldWaterCost);
   }

   @Override
   public void updateTask() {
      this.thePet.getLookHelper().setLookPositionWithEntity(this.theOwner, 10.0F, this.thePet.getVerticalFaceSpeed());
      if (!this.thePet.isSitting() && --this.timeToRecalcPath <= 0) {
         this.timeToRecalcPath = 10;
         FollowHelper.follow(this.thePet, this.theOwner, this.followSpeed);
      }
   }
}
