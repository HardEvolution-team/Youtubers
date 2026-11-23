package com.electrifiedded.youtubers.Entity.AI;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

/** 1.7 "follow owner" step shared by the follow AIs: path to the target, teleport next to it when stuck 12+ blocks away. */
public final class FollowHelper {
   private FollowHelper() {}

   public static void follow(EntityLiving mob, Entity target, double speed) {
      if (mob.getNavigator().tryMoveToEntityLiving(target, speed) || mob.getLeashed() || mob.getDistanceSq(target) < 144.0) return;
      World w = mob.world;
      int i = MathHelper.floor(target.posX) - 2;
      int j = MathHelper.floor(target.posZ) - 2;
      int k = MathHelper.floor(target.getEntityBoundingBox().minY);
      for (int l = 0; l <= 4; l++) {
         for (int i1 = 0; i1 <= 4; i1++) {
            if (l >= 1 && i1 >= 1 && l <= 3 && i1 <= 3) continue;
            BlockPos below = new BlockPos(i + l, k - 1, j + i1);
            if (w.getBlockState(below).isSideSolid(w, below, EnumFacing.UP) && !w.getBlockState(below.up()).isNormalCube()
               && !w.getBlockState(below.up(2)).isNormalCube()) {
               mob.setLocationAndAngles(i + l + 0.5, k, j + i1 + 0.5, mob.rotationYaw, mob.rotationPitch);
               mob.getNavigator().clearPath();
               return;
            }
         }
      }
   }
}
