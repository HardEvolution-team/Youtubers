package com.electrifiedded.youtubers.Entity.AI;

import java.util.List;
import java.util.function.Predicate;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.pathfinding.Path;
import net.minecraft.util.math.Vec3d;

/** Runs away from visible entities of a class (squids, wolves...). Subclasses narrow the entities with {@link #matches}. */
public class EntityAIAvoidEntityYT extends EntityAIBase {
   protected final EntityCreature theEntity;
   private final double farSpeed;
   private final double nearSpeed;
   private Entity closestLivingEntity;
   private final float distanceFromEntity;
   private Path entityPath;
   private final Class<? extends Entity> targetEntityClass;

   public EntityAIAvoidEntityYT(EntityCreature entity, Class<? extends Entity> cls, float distance, double farSpeed, double nearSpeed) {
      this.theEntity = entity;
      this.targetEntityClass = cls;
      this.distanceFromEntity = distance;
      this.farSpeed = farSpeed;
      this.nearSpeed = nearSpeed;
      this.setMutexBits(1);
   }

   protected boolean matches(Entity e) {
      return true;
   }

   @Override
   public boolean shouldExecute() {
      if (this.targetEntityClass == EntityPlayer.class) {
         if (this.theEntity instanceof EntityTameable && ((EntityTameable)this.theEntity).isTamed()) return false;
         this.closestLivingEntity = this.theEntity.world.getClosestPlayerToEntity(this.theEntity, this.distanceFromEntity);
         if (this.closestLivingEntity == null) return false;
      } else {
         Predicate<Entity> p = e -> e != this.theEntity && e.isEntityAlive() && this.theEntity.getEntitySenses().canSee(e) && this.matches(e);
         List<? extends Entity> list = this.theEntity.world.getEntitiesWithinAABB(this.targetEntityClass,
            this.theEntity.getEntityBoundingBox().grow(this.distanceFromEntity, 3.0, this.distanceFromEntity), p::test);
         if (list.isEmpty()) return false;
         this.closestLivingEntity = list.get(0);
      }
      Vec3d v = RandomPositionGenerator.findRandomTargetBlockAwayFrom(this.theEntity, 16, 7,
         new Vec3d(this.closestLivingEntity.posX, this.closestLivingEntity.posY, this.closestLivingEntity.posZ));
      if (v == null || this.closestLivingEntity.getDistanceSq(v.x, v.y, v.z) < this.closestLivingEntity.getDistanceSq(this.theEntity)) return false;
      this.entityPath = this.theEntity.getNavigator().getPathToXYZ(v.x, v.y, v.z);
      return this.entityPath != null;
   }

   @Override
   public boolean shouldContinueExecuting() {
      return !this.theEntity.getNavigator().noPath();
   }

   @Override
   public void startExecuting() {
      this.theEntity.getNavigator().setPath(this.entityPath, this.farSpeed);
   }

   @Override
   public void resetTask() {
      this.closestLivingEntity = null;
   }

   @Override
   public void updateTask() {
      this.theEntity.getNavigator().setSpeed(this.theEntity.getDistanceSq(this.closestLivingEntity) < 49.0 ? this.nearSpeed : this.farSpeed);
   }
}
