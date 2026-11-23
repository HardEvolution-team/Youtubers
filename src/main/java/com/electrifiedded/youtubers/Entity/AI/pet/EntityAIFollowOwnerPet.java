package com.electrifiedded.youtubers.Entity.AI.pet;

import com.electrifiedded.youtubers.Entity.YoutuberPetBase;
import com.electrifiedded.youtubers.Entity.AI.FollowHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.pathfinding.PathNodeType;

/** Pet follows its owner (any entity), teleports next to it when far and stuck. */
public class EntityAIFollowOwnerPet extends EntityAIBase {
   private final YoutuberPetBase thePet;
   private Entity theOwner;
   private final double followSpeed;
   private int timeToRecalcPath;
   private final float maxDist;
   private final float minDist;
   private float oldWaterCost;

   public EntityAIFollowOwnerPet(YoutuberPetBase pet, double speed, float minDist, float maxDist) {
      this.thePet = pet;
      this.followSpeed = speed;
      this.minDist = minDist;
      this.maxDist = maxDist;
      this.setMutexBits(3);
   }

   @Override
   public boolean shouldExecute() {
      Entity owner = this.thePet.getOwner();
      if (owner == null || owner == this.thePet || this.thePet.isSitting() || this.thePet.getDistanceSq(owner) < this.minDist * this.minDist) return false;
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
