package com.electrifiedded.youtubers.WorldGen;

import net.minecraft.util.math.BlockPos;
import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class GenJenHole extends GenYoutuberStructure {
   @Override
   public boolean generate(World world, Random random, BlockPos pos) {
      int i = pos.getX();
      int j = pos.getY();
      int k = pos.getZ();
      this.boxReplace(world, Blocks.AIR, 0, Blocks.DIRT, 0, i - 3, j - 3, k - 3, i + 3, j - 1, k + 3, "all");
      this.GenCircle(world, random, Blocks.AIR, 0, i, j - 1, k, 2.5F, 0.0F, 'y');
      this.GenCircle(world, random, Blocks.AIR, 0, i, j - 2, k, 2.5F, 0.0F, 'y');

      for (int a = 0; a < 15; a++) {
         this.GenCircle(world, random, Blocks.AIR, 0, i, j + a, k, 2.5F, 0.0F, 'y');
      }

      return true;
   }

   @Override
   public BlockPos getSpawnLoc(BlockPos pos) {
      return pos.down(2);
   }
}
