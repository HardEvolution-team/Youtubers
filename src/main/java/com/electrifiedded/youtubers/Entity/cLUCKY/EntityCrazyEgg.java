package com.electrifiedded.youtubers.Entity.cLUCKY;

import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.projectile.EntityEgg;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

/** Egg thrown by a cLUCKey drop: hatches the entity it carries (a baby chicken by default) where it lands. */
public class EntityCrazyEgg extends EntityEgg {
   Entity entity;

   public EntityCrazyEgg(World world) {
      super(world);
      EntityChicken chick = new EntityChicken(this.world);
      chick.setGrowingAge(-24000);
      this.entity = chick;
   }

   public EntityCrazyEgg(World world, Entity ent) {
      super(world);
      this.entity = ent;
   }

   @Override
   protected void onImpact(RayTraceResult result) {
      if (result.entityHit != null) result.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), 0.0F);
      if (!this.world.isRemote && this.entity != null) {
         this.entity.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0.0F);
         this.world.spawnEntity(this.entity);
      }
      for (int j = 0; j < 8; j++) {
         this.world.spawnParticle(EnumParticleTypes.ITEM_CRACK, this.posX, this.posY, this.posZ, (this.rand.nextFloat() - 0.5) * 0.08,
            (this.rand.nextFloat() - 0.5) * 0.08, (this.rand.nextFloat() - 0.5) * 0.08, Item.getIdFromItem(Items.EGG));
      }
      if (!this.world.isRemote) this.setDead();
   }
}
