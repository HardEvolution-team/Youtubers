package com.electrifiedded.youtubers.Entity.cLUCKY;

import net.minecraft.entity.projectile.EntityEgg;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

/** Egg thrown by a cLUCKey drop that runs another drop script where it lands. */
public class EventEgg extends EntityEgg {
   public String STR;

   public EventEgg(World world) {
      super(world);
      this.STR = "SpawnEntity(EntityPos(~0,~0,~0,Entity(\"Chicken\")))";
   }

   public EventEgg(World world, String str) {
      super(world);
      this.STR = str;
   }

   @Override
   protected void onImpact(RayTraceResult result) {
      if (result.entityHit != null) result.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), 0.0F);
      if (!this.world.isRemote) new CluckyDrop(this.world, new BlockPos(this.posX, this.posY + 1.0, this.posZ), null).parseDrop(this.STR);
      for (int j = 0; j < 8; j++) {
         this.world.spawnParticle(EnumParticleTypes.ITEM_CRACK, this.posX, this.posY, this.posZ, (this.rand.nextFloat() - 0.5) * 0.08,
            (this.rand.nextFloat() - 0.5) * 0.08, (this.rand.nextFloat() - 0.5) * 0.08, Item.getIdFromItem(Items.EGG));
      }
      if (!this.world.isRemote) this.setDead();
   }
}
