package com.electrifiedded.youtubers.WorldGen;

import com.electrifiedded.archaiclib.world.StagedWorld;
import net.minecraft.util.math.BlockPos;
import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class GenFloatCastle extends GenYoutuberStructure {
   @Override
   public boolean generate(World world, Random random, BlockPos pos) {
      int i = pos.getX();
      int j = pos.getY();
      int k = pos.getZ();
      this.generateFloatingIsland(world, random, i, j + 30, k, 12.0);
      int number_of_pillars = 6;
      double radius = 10.0;

      for (int a = 0; a < number_of_pillars; a++) {
         this.generateFloatingIslandBit(
            world,
            random,
            (float)(i + radius * Math.sin((Math.PI * 2) * a / number_of_pillars)),
            j + 30,
            (float)(k + radius * Math.cos((Math.PI * 2) * a / number_of_pillars)),
            8.0
         );
         this.generateFloatingIslandBit(
            world,
            random,
            (float)(i + radius * Math.sin((Math.PI * 2) * a / number_of_pillars)),
            j + 30,
            (float)(k + radius * Math.cos((Math.PI * 2) * a / number_of_pillars)),
            8.0
         );
      }

      int var11 = 8;
      radius = 14.0;

      for (int a = 0; a < var11; a++) {
         this.generateFloatingIslandBit(
            world, random, (float)(i + radius * Math.sin((Math.PI * 2) * a / var11)), j + 30, (float)(k + radius * Math.cos((Math.PI * 2) * a / var11)), 6.0
         );
         this.generateFloatingIslandBit(
            world, random, (float)(i + radius * Math.sin((Math.PI * 2) * a / var11)), j + 30, (float)(k + radius * Math.cos((Math.PI * 2) * a / var11)), 6.0
         );
      }

      this.GenCircle(world, random, Blocks.GRASS, 0, i, j + 30, k, 15.0F, 0.0F, 'y');
      this.GenCastle(world, random, i, j + 31, k);
      return false;
   }

   @Override
   public BlockPos getSpawnLoc(BlockPos pos) {
      return pos.up(35);
   }

   private void GenCastle(World world, Random random, int i, int j, int k) {
      this.genTower(world, random, i + 7, j, k + 7);
      this.genTower(world, random, i + 7, j, k - 7);
      this.genTower(world, random, i - 7, j, k + 7);
      this.genTower(world, random, i - 7, j, k - 7);
      this.genFrontPath(world, random, i, j - 1, k);

      for (int a = 0; a < 9; a++) {
         if (a % 2 == 0) {
            this.genBox(world, Blocks.STONEBRICK, 0, 0, i + 6, j - 1, k - 4 + a, i + 8, j + 4, k - 4 + a);
            this.genBox(world, Blocks.STONEBRICK, 0, 0, i - 8, j - 1, k - 4 + a, i - 6, j + 4, k - 4 + a);
         } else {
            this.genBox(world, Blocks.STONEBRICK, 0, 0, i + 6, j - 1, k - 4 + a, i + 8, j + 3, k - 4 + a);
            this.genBox(world, Blocks.STONEBRICK, 0, 0, i - 8, j - 1, k - 4 + a, i - 6, j + 3, k - 4 + a);
         }

         this.setBlockToAir(world, new BlockPos(i + 7, j, k - 4 + a));
         this.setBlockToAir(world, new BlockPos(i + 7, j + 1, k - 4 + a));
         this.setBlockToAir(world, new BlockPos(i + 7, j + 4, k - 4 + a));
         this.setBlockToAir(world, new BlockPos(i + 7, j + 5, k - 4 + a));
         this.setBlockToAir(world, new BlockPos(i - 7, j, k - 4 + a));
         this.setBlockToAir(world, new BlockPos(i - 7, j + 1, k - 4 + a));
         this.setBlockToAir(world, new BlockPos(i - 7, j + 4, k - 4 + a));
         this.setBlockToAir(world, new BlockPos(i - 7, j + 5, k - 4 + a));
      }

      this.genBox(world, Blocks.STONEBRICK, 0, 0, i - 4, j - 1, k + 2, i + 4, j - 1, k + 8);
      this.genBox(world, Blocks.STONEBRICK, 0, 1, i - 4, j, k + 2, i + 4, j + 3, k + 8);
      this.genBox(world, Blocks.PLANKS, 0, 0, i - 3, j + 3, k + 3, i + 3, j + 3, k + 7);
      this.genBox(world, Blocks.STONEBRICK, 0, 1, i - 4, j + 4, k + 3, i + 4, j + 6, k + 8);
      this.genBox(world, Blocks.PLANKS, 0, 1, i - 4, j + 7, k + 3, i + 4, j + 7, k + 8);
      this.genBox(world, Blocks.PLANKS, 0, 1, i - 3, j + 8, k + 4, i + 3, j + 8, k + 7);
      this.genBox(world, Blocks.PLANKS, 0, 0, i - 2, j + 9, k + 5, i + 2, j + 9, k + 6);

      for (int a = 0; a < 9; a++) {
         if (a % 2 == 0) {
            this.setBlockState(world, new BlockPos(i - 4 + a, j + 4, k + 2), Blocks.STONEBRICK, 0);
         }
      }

      this.setBlockToAir(world, new BlockPos(i, j, k + 2));
      this.setBlockToAir(world, new BlockPos(i, j + 1, k + 2));
      this.genBox(world, Blocks.COBBLESTONE_WALL, 0, 0, i - 2, j, k - 6, i - 2, j, k + 1);
      this.genBox(world, Blocks.COBBLESTONE_WALL, 0, 0, i + 2, j, k - 6, i + 2, j, k + 1);
      this.genBox(world, Blocks.AIR, 0, 0, i - 6, j, k, i + 6, j + 1, k);
      this.genBox(world, Blocks.STONEBRICK, 0, 0, i - 6, j - 1, k, i + 6, j - 1, k);
      this.setBlockState(world, new BlockPos(i + 3, j, k + 3), Blocks.OAK_STAIRS, 0);
      this.setBlockState(world, new BlockPos(i + 3, j + 1, k + 4), Blocks.OAK_STAIRS, 2);
      this.setBlockState(world, new BlockPos(i + 3, j + 2, k + 5), Blocks.OAK_STAIRS, 2);
      this.setBlockState(world, new BlockPos(i + 3, j + 3, k + 6), Blocks.OAK_STAIRS, 2);
      this.setBlockToAir(world, new BlockPos(i + 3, j + 3, k + 3));
      this.setBlockToAir(world, new BlockPos(i + 3, j + 3, k + 4));
      this.setBlockToAir(world, new BlockPos(i + 3, j + 3, k + 5));
      this.setBlockToAir(world, new BlockPos(i + 4, j + 4, k + 7));
      this.setBlockToAir(world, new BlockPos(i + 4, j + 5, k + 7));
      this.setBlockToAir(world, new BlockPos(i - 4, j + 4, k + 7));
      this.setBlockToAir(world, new BlockPos(i - 4, j + 5, k + 7));
      this.genBox(world, Blocks.STONEBRICK, 0, 0, i - 3, j, k - 7, i + 3, j + 3, k - 7);
      this.genBox(world, Blocks.AIR, 0, 0, i - 1, j, k - 7, i + 1, j + 2, k - 7);
      this.setBlockState(world, new BlockPos(i, j + 4, k - 7), Blocks.STONEBRICK, 0);
      this.setBlockState(world, new BlockPos(i - 2, j + 4, k - 7), Blocks.STONEBRICK, 0);
      this.setBlockState(world, new BlockPos(i + 2, j + 4, k - 7), Blocks.STONEBRICK, 0);
      this.setBlockState(world, new BlockPos(i + 1, j + 2, k - 7), Blocks.STONE_BRICK_STAIRS, 4);
      this.setBlockState(world, new BlockPos(i - 1, j + 2, k - 7), Blocks.STONE_BRICK_STAIRS, 5);
      this.setBlockToAir(world, new BlockPos(i + 7, j + 4, k + 10));
      this.setBlockToAir(world, new BlockPos(i + 7, j + 5, k + 10));
      this.setBlockToAir(world, new BlockPos(i + 7, j + 6, k + 10));
      this.setBlockState(world, new BlockPos(i + 6, j + 4, k + 11), Blocks.IRON_BARS, 0);
      this.setBlockState(world, new BlockPos(i + 7, j + 4, k + 11), Blocks.IRON_BARS, 0);
      this.setBlockState(world, new BlockPos(i + 8, j + 4, k + 11), Blocks.IRON_BARS, 0);
      this.setBlockToAir(world, new BlockPos(i - 7, j + 4, k + 10));
      this.setBlockToAir(world, new BlockPos(i - 7, j + 5, k + 10));
      this.setBlockToAir(world, new BlockPos(i - 7, j + 6, k + 10));
      this.setBlockState(world, new BlockPos(i - 6, j + 4, k + 11), Blocks.IRON_BARS, 0);
      this.setBlockState(world, new BlockPos(i - 7, j + 4, k + 11), Blocks.IRON_BARS, 0);
      this.setBlockState(world, new BlockPos(i - 8, j + 4, k + 11), Blocks.IRON_BARS, 0);
      this.setBlockToAir(world, new BlockPos(i + 7, j + 4, k - 10));
      this.setBlockToAir(world, new BlockPos(i + 7, j + 5, k - 10));
      this.setBlockToAir(world, new BlockPos(i + 7, j + 6, k - 10));
      this.setBlockState(world, new BlockPos(i + 6, j + 4, k - 11), Blocks.IRON_BARS, 0);
      this.setBlockState(world, new BlockPos(i + 7, j + 4, k - 11), Blocks.IRON_BARS, 0);
      this.setBlockState(world, new BlockPos(i + 8, j + 4, k - 11), Blocks.IRON_BARS, 0);
      this.setBlockToAir(world, new BlockPos(i - 7, j + 4, k - 10));
      this.setBlockToAir(world, new BlockPos(i - 7, j + 5, k - 10));
      this.setBlockToAir(world, new BlockPos(i - 7, j + 6, k - 10));
      this.setBlockState(world, new BlockPos(i - 6, j + 4, k - 11), Blocks.IRON_BARS, 0);
      this.setBlockState(world, new BlockPos(i - 7, j + 4, k - 11), Blocks.IRON_BARS, 0);
      this.setBlockState(world, new BlockPos(i - 8, j + 4, k - 11), Blocks.IRON_BARS, 0);
      this.setBlockToAir(world, new BlockPos(i + 10, j + 4, k + 7));
      this.setBlockToAir(world, new BlockPos(i + 10, j + 5, k + 7));
      this.setBlockToAir(world, new BlockPos(i + 10, j + 6, k + 7));
      this.setBlockState(world, new BlockPos(i + 11, j + 4, k + 6), Blocks.IRON_BARS, 0);
      this.setBlockState(world, new BlockPos(i + 11, j + 4, k + 7), Blocks.IRON_BARS, 0);
      this.setBlockState(world, new BlockPos(i + 11, j + 4, k + 8), Blocks.IRON_BARS, 0);
      this.setBlockToAir(world, new BlockPos(i - 10, j + 4, k + 7));
      this.setBlockToAir(world, new BlockPos(i - 10, j + 5, k + 7));
      this.setBlockToAir(world, new BlockPos(i - 10, j + 6, k + 7));
      this.setBlockState(world, new BlockPos(i - 11, j + 4, k + 6), Blocks.IRON_BARS, 0);
      this.setBlockState(world, new BlockPos(i - 11, j + 4, k + 7), Blocks.IRON_BARS, 0);
      this.setBlockState(world, new BlockPos(i - 11, j + 4, k + 8), Blocks.IRON_BARS, 0);
      this.setBlockToAir(world, new BlockPos(i + 10, j + 4, k - 7));
      this.setBlockToAir(world, new BlockPos(i + 10, j + 5, k - 7));
      this.setBlockToAir(world, new BlockPos(i + 10, j + 6, k - 7));
      this.setBlockState(world, new BlockPos(i + 11, j + 4, k - 6), Blocks.IRON_BARS, 0);
      this.setBlockState(world, new BlockPos(i + 11, j + 4, k - 7), Blocks.IRON_BARS, 0);
      this.setBlockState(world, new BlockPos(i + 11, j + 4, k - 8), Blocks.IRON_BARS, 0);
      this.setBlockToAir(world, new BlockPos(i - 10, j + 4, k - 7));
      this.setBlockToAir(world, new BlockPos(i - 10, j + 5, k - 7));
      this.setBlockToAir(world, new BlockPos(i - 10, j + 6, k - 7));
      this.setBlockState(world, new BlockPos(i - 11, j + 4, k - 6), Blocks.IRON_BARS, 0);
      this.setBlockState(world, new BlockPos(i - 11, j + 4, k - 7), Blocks.IRON_BARS, 0);
      this.setBlockState(world, new BlockPos(i - 11, j + 4, k - 8), Blocks.IRON_BARS, 0);
      this.genBox(world, Blocks.GLASS_PANE, 0, 0, i - 2, j + 5, k + 8, i + 2, j + 5, k + 8);
   }

   private void genTower(World world, Random random, int i, int j, int k) {
      this.GenCircle(world, random, Blocks.STONEBRICK, 0, i, j - 1, k, 3.5F, 0.0F, 'y');
      this.GenCircle(world, random, Blocks.STONEBRICK, 0, i, j, k, 3.5F, 2.5F, 'y');
      this.GenCircle(world, random, Blocks.STONEBRICK, 0, i, j + 1, k, 3.5F, 2.5F, 'y');
      this.GenCircle(world, random, Blocks.STONEBRICK, 0, i, j + 2, k, 3.5F, 2.5F, 'y');
      this.GenCircle(world, random, Blocks.STONEBRICK, 0, i, j + 3, k, 3.5F, 0.0F, 'y');
      this.GenCircle(world, random, Blocks.STONEBRICK, 0, i, j + 4, k, 3.5F, 2.5F, 'y');
      this.GenCircle(world, random, Blocks.STONEBRICK, 0, i, j + 5, k, 3.5F, 2.5F, 'y');
      this.GenCircle(world, random, Blocks.STONEBRICK, 0, i, j + 6, k, 3.5F, 2.5F, 'y');
      this.GenCircle(world, random, Blocks.PLANKS, 0, i, j + 7, k, 3.5F, 2.5F, 'y');
      this.GenCircle(world, random, Blocks.PLANKS, 0, i, j + 8, k, 2.5F, 1.5F, 'y');
      this.GenCircle(world, random, Blocks.PLANKS, 0, i, j + 9, k, 1.5F, 0.5F, 'y');
      this.GenCircle(world, random, Blocks.PLANKS, 0, i, j + 10, k, 0.5F, 0.0F, 'y');
   }

   private void genFrontPath(World world, Random random, int i, int j, int k) {
      int a;
      for (a = 1; !StagedWorld.isAirBlock(world, i, j, k + a) || !StagedWorld.isAirBlock(world, i + 1, j, k + a) || !StagedWorld.isAirBlock(world, i - 1, j, k + a); a--) {
         this.setBlockState(world, new BlockPos(i + 1, j, k + a), Blocks.STONEBRICK, 0);
         this.setBlockState(world, new BlockPos(i, j, k + a), Blocks.STONEBRICK, 0);
         this.setBlockState(world, new BlockPos(i - 1, j, k + a), Blocks.STONEBRICK, 0);
      }

      this.genBox(world, Blocks.AIR, 0, 0, i - 1, j - 5, k + a - 3, i + 1, j, k + a);
      this.setBlockState(world, new BlockPos(i + 2, j, k + a), Blocks.STONEBRICK, 0);
      this.setBlockState(world, new BlockPos(i + 1, j, k + a), Blocks.WATER, 0);
      this.setBlockState(world, new BlockPos(i, j, k + a), Blocks.WATER, 0);
      this.setBlockState(world, new BlockPos(i - 1, j, k + a), Blocks.WATER, 0);
      this.setBlockState(world, new BlockPos(i - 2, j, k + a), Blocks.STONEBRICK, 0);
      if (StagedWorld.canBlockFreeze(world, i, j, k + a, false) || StagedWorld.canBlockFreeze(world, i + 1, j, k + a, false) || StagedWorld.canBlockFreeze(world, i - 1, j, k + a, false)) {
         this.setBlockState(world, new BlockPos(i + 1, j + 1, k + a), Blocks.CARPET, 0);
         this.setBlockState(world, new BlockPos(i, j + 1, k + a), Blocks.CARPET, 0);
         this.setBlockState(world, new BlockPos(i - 1, j + 1, k + a), Blocks.CARPET, 0);
      }
   }

   public void generateFloatingIsland(World world, Random random, int i, int j, int k, double r) {
      for (int a = 0; a < 3; a++) {
         if (r > 4.0) {
            this.generateFloatingIsland(
               world, random, i + random.nextInt((int)(r * 2.0)) - (int)(r * 1.0), j, k + random.nextInt((int)(r * 2.0)) - (int)(r * 1.0), r - 2.0
            );
         }
      }

      this.generateFloatingIslandBit(world, random, i, j, k, r - 1.0);
   }

   private void generateFloatingIslandBit(World world, Random random, float i, float j, float k, double r) {
      this.GenCircle(world, random, Blocks.GRASS, 0, i, j, k, (float)(r * 0.6), 0.0F, 'y');
      this.GenCircle(world, random, Blocks.GRASS, 0, i, j - 1.0F, k, (float)(r * 0.9), 0.0F, 'y');
      this.GenCircle(world, random, Blocks.GRASS, 0, i, j - 2.0F, k, (float)r, 0.0F, 'y');

      for (int a = 0; a < r; a++) {
         this.GenCircle(world, random, Blocks.DIRT, 0, i, j - 3.0F - a, k, (float)(r - a), 0.0F, 'y');
      }
   }
}
