package com.electrifiedded.youtubers.WorldGen;

import com.electrifiedded.youtubers.YTBlocks;
import net.minecraft.util.math.BlockPos;
import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class GenPotato extends GenYoutuberStructure {
   @Override
   public boolean generate(World world, Random random, BlockPos pos) {
      int i = pos.getX();
      int j = pos.getY();
      int k = pos.getZ();

      for (int a = -2; a < 6; a++) {
         double r = Math.sqrt(Math.pow(2.5, 2.0) - Math.pow(a - 0.5, 2.0));
         this.GenCircle(world, random, YTBlocks.blockPotato, 0, i, j + a, k, (float)r, (float)(a < 0 ? 0.0 : r - 1.0), 'y');
      }

      this.setBlockState(world, new BlockPos(i, j + 3, k), YTBlocks.blockPotato, 0);
      this.setBlockState(world, new BlockPos(i, j - 1, k - 2), YTBlocks.blockPotato, 0);
      this.setBlockState(world, new BlockPos(i, j, k - 2), Blocks.OAK_DOOR, 1);
      this.setBlockState(world, new BlockPos(i, j + 1, k - 2), Blocks.OAK_DOOR, 9);

      for (int d = -2; d <= 2; d++) {
         for (int a = 0; a < 100; a++) {
            int b = random.nextInt(21) - 10;
            int c = random.nextInt(21) - 10;
            if ((Math.abs(b) > 3 || Math.abs(c) > 3) && this.canPlant(world, i + b, j + d, k + c)) {
               this.setBlockState(world, new BlockPos(i + b, j + d - 1, k + c), Blocks.FARMLAND, 0);
               this.setBlockState(world, new BlockPos(i + b, j + d, k + c), Blocks.POTATOES, 7);
            }
         }
      }

      return false;
   }

   boolean canPlant(World world, int i, int j, int k) {
      return this.getBlockState(world, new BlockPos(i, j - 1, k)) != Blocks.DIRT
            && this.getBlockState(world, new BlockPos(i, j - 1, k)) != Blocks.GRASS
         ? false
         : this.getBlockState(world, new BlockPos(i, j, k)) == Blocks.AIR;
   }
}
