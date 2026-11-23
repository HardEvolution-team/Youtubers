package com.electrifiedded.youtubers.WorldGen;

import com.electrifiedded.archaiclib.world.StagedWorld;
import net.minecraft.util.math.BlockPos;
import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class GenBuildGame extends GenYoutuberStructure {
   int[][] Texta = new int[][]{
      {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0},
      {0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 1, 0, 1, 0},
      {0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 1, 0, 0, 0, 1, 0, 0, 1, 1, 1, 0},
      {0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1, 0, 0, 1, 0, 1, 0},
      {0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 0, 0, 1, 0, 0, 1, 0, 1, 0}
   };
   int[][] Textb = new int[][]{
      {0, 2, 2, 0, 0, 2, 0, 0, 0, 2, 0, 2, 0, 0, 0, 2, 0, 0, 2, 2, 0, 0},
      {0, 2, 0, 2, 0, 2, 0, 0, 0, 2, 0, 2, 2, 0, 0, 2, 0, 2, 0, 0, 0, 0},
      {0, 2, 2, 0, 0, 2, 0, 0, 0, 2, 0, 2, 0, 2, 0, 2, 0, 2, 0, 2, 2, 0},
      {0, 2, 0, 2, 0, 2, 0, 0, 0, 2, 0, 2, 0, 0, 2, 2, 0, 2, 0, 0, 2, 0},
      {0, 2, 2, 0, 0, 2, 2, 2, 0, 2, 0, 2, 0, 0, 0, 2, 0, 0, 2, 2, 0, 0}
   };
   int[][] Textc = new int[][]{
      {0, 0, 0, 3, 3, 0, 0, 3, 0, 3, 0, 3, 0, 3, 0, 0, 0, 3, 3, 0, 0, 0},
      {0, 0, 0, 3, 0, 3, 0, 3, 0, 3, 0, 3, 0, 3, 0, 0, 0, 3, 0, 3, 0, 0},
      {0, 0, 0, 3, 3, 0, 0, 3, 0, 3, 0, 3, 0, 3, 0, 0, 0, 3, 0, 3, 0, 0},
      {0, 0, 0, 3, 0, 3, 0, 3, 0, 3, 0, 3, 0, 3, 0, 0, 0, 3, 0, 3, 0, 0},
      {0, 0, 0, 3, 3, 0, 0, 0, 3, 0, 0, 3, 0, 3, 3, 3, 0, 3, 3, 0, 0, 0}
   };
   int[][] Textd = new int[][]{
      {0, 0, 0, 0, 3, 3, 0, 0, 0, 3, 0, 0, 3, 0, 0, 0, 3, 0, 0, 3, 3, 0},
      {0, 0, 0, 3, 0, 0, 0, 0, 3, 0, 3, 0, 3, 3, 0, 3, 3, 0, 3, 0, 0, 0},
      {0, 0, 0, 3, 0, 3, 3, 0, 3, 3, 3, 0, 3, 0, 3, 0, 3, 0, 3, 3, 0, 0},
      {0, 0, 0, 3, 0, 0, 3, 0, 3, 0, 3, 0, 3, 0, 0, 0, 3, 0, 3, 0, 0, 0},
      {0, 0, 0, 0, 3, 3, 0, 0, 3, 0, 3, 0, 3, 0, 0, 0, 3, 0, 0, 3, 3, 0}
   };

   @Override
   public boolean generate(World world, Random random, BlockPos pos) {
      int i = pos.getX();
      int j = pos.getY();
      int k = pos.getZ();
      this.genBox(world, Blocks.STONE, 6, 0, i - 12, j - 1, k - 12, i + 12, j - 1, k + 12);
      this.genBox(world, Blocks.STAINED_HARDENED_CLAY, 12, 1, i - 12, j, k - 12, i + 12, j + 6, k + 12);
      this.genBox(world, Blocks.STONE, 6, 0, i - 9, j - 5, k - 9, i + 9, j - 2, k + 9);
      this.genBox(world, Blocks.SNOW, 0, 0, i - 8, j - 4, k - 8, i + 8, j - 1, k + 8);

      for (int a = 0; a < 22; a++) {
         for (int b = 0; b < 5; b++) {
            if (this.Texta[b][a] != 0) {
               StagedWorld.setBlock(world, i - 12, j + 5 - b, k + 11 - a, Blocks.REDSTONE_BLOCK, 0, 3);
            }

            if (this.Textb[b][a] != 0) {
               StagedWorld.setBlock(world, i - 11 + a, j + 5 - b, k - 12, Blocks.GOLD_BLOCK, 0, 3);
            }

            if (this.Textc[b][a] != 0) {
               StagedWorld.setBlock(world, i + 12, j + 5 - b, k - 9 + a, Blocks.STONEBRICK, 0, 3);
            }

            if (this.Textd[b][a] != 0) {
               StagedWorld.setBlock(world, i + 12 - a, j + 5 - b, k + 12, Blocks.STONEBRICK, 0, 3);
            }
         }
      }

      this.genBox(world, Blocks.AIR, 0, 0, i - 11, j, k - 11, i + 11, j + 40, k + 11);
      return true;
   }
}
