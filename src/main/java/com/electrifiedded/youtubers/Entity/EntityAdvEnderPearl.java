package com.electrifiedded.youtubers.Entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.EnderTeleportEvent;

/** Advanced ender pearl: teleports the thrower like an ender pearl, without the fall damage and without endermites. */
public class EntityAdvEnderPearl extends EntityThrowable {
   public EntityAdvEnderPearl(World world) {
      super(world);
   }

   public EntityAdvEnderPearl(World world, EntityLivingBase thrower) {
      super(world, thrower);
   }

   public EntityAdvEnderPearl(World world, double x, double y, double z) {
      super(world, x, y, z);
   }

   @Override
   protected void onImpact(RayTraceResult result) {
      for (int i = 0; i < 32; i++) {
         this.world.spawnParticle(EnumParticleTypes.PORTAL, this.posX, this.posY + this.rand.nextDouble() * 2.0, this.posZ, this.rand.nextGaussian(), 0.0,
            this.rand.nextGaussian());
      }
      if (!this.world.isRemote) {
         EntityLivingBase thrower = this.getThrower();
         if (thrower instanceof EntityPlayerMP) {
            EntityPlayerMP p = (EntityPlayerMP)thrower;
            if (p.connection.getNetworkManager().isChannelOpen() && p.world == this.world && !p.isPlayerSleeping()) {
               EnderTeleportEvent event = new EnderTeleportEvent(p, this.posX, this.posY, this.posZ, 0.0F);
               if (!MinecraftForge.EVENT_BUS.post(event)) {
                  if (p.isRiding()) p.dismountRidingEntity();
                  p.setPositionAndUpdate(event.getTargetX(), event.getTargetY(), event.getTargetZ());
                  p.fallDistance = 0.0F;
               }
            }
         }
         this.setDead();
      }
   }
}
