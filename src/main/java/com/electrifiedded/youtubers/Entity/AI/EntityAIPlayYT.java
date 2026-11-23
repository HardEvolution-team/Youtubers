package com.electrifiedded.youtubers.Entity.AI;

import com.electrifiedded.youtubers.Entity.EntityYTBase;
import com.electrifiedded.youtubers.Entity.EntityYoutuber;
import java.util.List;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.util.math.Vec3d;

/** Baby youtubers run after each other (villager child play AI). */
public class EntityAIPlayYT extends EntityAIBase {
   private final EntityYTBase villagerObj;
   private EntityLivingBase targetVillager;
   private final double speed;
   private int playTime;

   public EntityAIPlayYT(EntityYTBase villager, double speed) {
      this.villagerObj = villager;
      this.speed = speed;
      this.setMutexBits(1);
   }

   @Override
   public boolean shouldExecute() {
      if (this.villagerObj.getGrowingAge() >= 0 || this.villagerObj.getRNG().nextInt(400) != 0) return false;
      List<EntityYoutuber> list = this.villagerObj.world.getEntitiesWithinAABB(EntityYoutuber.class, this.villagerObj.getEntityBoundingBox().grow(6.0, 3.0, 6.0));
      double d0 = Double.MAX_VALUE;
      for (EntityYoutuber v : list) {
         if (v != this.villagerObj && !v.isPlaying() && v.getGrowingAge() < 0) {
            double d1 = v.getDistanceSq(this.villagerObj);
            if (d1 <= d0) {
               d0 = d1;
               this.targetVillager = v;
            }
         }
      }
      return this.targetVillager != null || RandomPositionGenerator.findRandomTarget(this.villagerObj, 16, 3) != null;
   }

   @Override
   public boolean shouldContinueExecuting() {
      return this.playTime > 0;
   }

   @Override
   public void startExecuting() {
      if (this.targetVillager != null) this.villagerObj.setPlaying(true);
      this.playTime = 1000;
   }

   @Override
   public void resetTask() {
      this.villagerObj.setPlaying(false);
      this.targetVillager = null;
   }

   @Override
   public void updateTask() {
      this.playTime--;
      if (this.targetVillager != null) {
         if (this.villagerObj.getDistanceSq(this.targetVillager) > 4.0) this.villagerObj.getNavigator().tryMoveToEntityLiving(this.targetVillager, this.speed);
      } else if (this.villagerObj.getNavigator().noPath()) {
         Vec3d v = RandomPositionGenerator.findRandomTarget(this.villagerObj, 16, 3);
         if (v != null) this.villagerObj.getNavigator().tryMoveToXYZ(v.x, v.y, v.z, this.speed);
      }
   }
}
