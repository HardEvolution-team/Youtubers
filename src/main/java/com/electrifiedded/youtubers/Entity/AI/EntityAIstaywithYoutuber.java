package com.electrifiedded.youtubers.Entity.AI;

import com.electrifiedded.youtubers.Entity.EntityYoutuber;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;

/** An untamed youtuber walks back to a friend (by name) when it is between r and r2 (squared distance) away from it. */
public class EntityAIstaywithYoutuber extends EntityAIBase {
   private final EntityLiving YT;
   private final String followName;
   private final double r;
   private final double r2;
   private final double speed;
   private EntityYoutuber target;

   public EntityAIstaywithYoutuber(EntityLiving yt, String followName, double r, double r2, double speed) {
      this.YT = yt;
      this.followName = followName;
      this.r = r;
      this.r2 = r2;
      this.speed = speed;
   }

   private EntityYoutuber nearest() {
      EntityYoutuber best = null;
      double dist = -1.0;
      for (Entity e : this.YT.world.loadedEntityList) {
         if (e instanceof EntityYoutuber && e != this.YT && this.followName.equals(((EntityYoutuber)e).getName())) {
            double d = e.getDistanceSq(this.YT);
            if (dist < 0 || d < dist) {
               dist = d;
               best = (EntityYoutuber)e;
            }
         }
      }
      return best;
   }

   @Override
   public boolean shouldExecute() {
      if (this.YT instanceof EntityYoutuber && (((EntityYoutuber)this.YT).isTamed() || ((EntityYoutuber)this.YT).isTrading())) return false;
      if (this.YT.ticksExisted % 20 != 0) return false;
      this.target = this.nearest();
      if (this.target == null) return false;
      double d = this.target.getDistanceSq(this.YT);
      return d > this.r && d < this.r2;
   }

   @Override
   public boolean shouldContinueExecuting() {
      return this.target != null && this.target.isEntityAlive() && this.target.getDistanceSq(this.YT) > this.r && !this.YT.getNavigator().noPath();
   }

   @Override
   public void startExecuting() {
      this.updateTask();
   }

   @Override
   public void updateTask() {
      if (this.target == null) return;
      this.YT.getLookHelper().setLookPositionWithEntity(this.target, 10.0F, this.YT.getVerticalFaceSpeed());
      boolean canWalk = !(this.YT instanceof EntityYoutuber) || !((EntityYoutuber)this.YT).isSitting();
      if (canWalk && this.YT.ticksExisted % 10 == 0) FollowHelper.follow(this.YT, this.target, this.speed);
   }

   @Override
   public void resetTask() {
      this.target = null;
   }
}
