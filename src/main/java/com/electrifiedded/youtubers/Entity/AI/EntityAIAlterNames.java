package com.electrifiedded.youtubers.Entity.AI;

import java.util.List;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;

/**
 * Running gag: renames visible living entities within r blocks whose (custom) name contains {@code in}, replacing it with
 * {@code out} (Ssundee calls gold "Butter", Bodil40 "Boatdil40", squids "DEMONSPAWN"; Mojang devs turn Herobrine into
 * "REMOVED"). Unnamed entities get the replaced type name as custom name, shown above their head.
 */
public class EntityAIAlterNames extends EntityAIBase {
   private final EntityCreature theEntity;
   private final String in;
   private final String out;
   private final double r;
   private List<EntityLiving> list;

   public EntityAIAlterNames(EntityCreature entity, String in, String out, double r) {
      this.theEntity = entity;
      this.r = r;
      this.in = in;
      this.out = out;
      this.setMutexBits(1);
   }

   private boolean matches(EntityLiving e) {
      if (e == this.theEntity || !e.isEntityAlive()) return false;
      if (!e.getCustomNameTag().contains(this.in) && !e.getName().contains(this.in)) return false;
      return this.theEntity.getEntitySenses().canSee(e);
   }

   @Override
   public boolean shouldExecute() {
      this.list = this.theEntity.world.getEntitiesWithinAABB(EntityLiving.class, this.theEntity.getEntityBoundingBox().grow(this.r, 3.0, this.r), this::matches);
      return !this.list.isEmpty();
   }

   @Override
   public void startExecuting() {
      for (EntityLiving e : this.list) {
         if (e.hasCustomName()) {
            e.setCustomNameTag(e.getCustomNameTag().replace(this.in, this.out));
         } else {
            e.setCustomNameTag(e.getName().replace(this.in, this.out));
            e.setAlwaysRenderNameTag(true);
         }
      }
   }

   @Override
   public boolean shouldContinueExecuting() {
      return false;
   }
}
