package com.electrifiedded.youtubers.WorldGen;

import net.minecraft.util.math.BlockPos;
import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class GenPizzaSpleef extends GenYoutuberStructure {
   @Override
   public boolean generate(World world, Random random, BlockPos pos) {
      int i = pos.getX();
      int j = pos.getY();
      int k = pos.getZ();
      this.ClearSphere(world, i, j, k, 20.0);
      this.GenCircle(world, random, Blocks.STAINED_HARDENED_CLAY, 12, i, j - 1, k, 14.75F, 0.0F, 'y');
      this.GenCircle(world, random, Blocks.STAINED_HARDENED_CLAY, 4, i, j - 1, k, 13.0F, 0.0F, 'y');
      int number = 6;

      for (int a = 0; a < number; a++) {
         double radius = 3.5;
         this.generateTopping(
            world, random, i + (int)(radius * Math.sin((Math.PI * 2) * a / number)), j, k + (int)(radius * Math.cos((Math.PI * 2) * a / number))
         );
      }

      int var11 = 12;

      for (int a = 0; a < var11; a++) {
         double radius = 11.5;
         this.generateTopping(
            world, random, i + (int)(radius * Math.sin((Math.PI * 2) * a / var11)), j, k + (int)(radius * Math.cos((Math.PI * 2) * a / var11))
         );
      }

      var11 = 9;

      for (int a = 0; a < var11; a++) {
         double radius = 7.5;
         this.generateTopping(
            world, random, i + (int)(radius * Math.sin((Math.PI * 2) * a / var11)), j, k + (int)(radius * Math.cos((Math.PI * 2) * a / var11))
         );
      }

      return false;
   }

   private void generateTopping(World world, Random random, int i, int j, int k) {
      switch (random.nextInt(7)) {
         case 0:
         case 6:
            this.genPepperoni(world, random, i, j, k);
            break;
         case 1:
            this.genShroom(world, random, i, j, k);
            break;
         case 2:
            this.genOlive(world, random, i, j, k);
            break;
         case 3:
            this.genPepper(world, random, i, j, k);
            break;
         case 4:
            this.genOnion(world, random, i, j, k);
            break;
         case 5:
            this.genSausage(world, random, i, j, k);
      }
   }

   private void genSausage(World world, Random random, int i, int j, int k) {
      BlockPos pos = new BlockPos(i, j, k);
      if (random.nextBoolean()) {
         this.setBlockState(world, pos.add(1, 0, 0), Blocks.PLANKS, 1);
         this.setBlockState(world, pos.add(-1, 0, 0), Blocks.PLANKS, 1);
      } else {
         this.setBlockState(world, pos.add(0, 0, 1), Blocks.PLANKS, 1);
         this.setBlockState(world, pos.add(0, 0, -1), Blocks.PLANKS, 1);
      }
   }

   private void genOnion(World world, Random random, int i, int j, int k) {
      BlockPos pos = new BlockPos(i, j, k);
      this.setBlockState(world, pos, Blocks.STAINED_GLASS, 0);
      switch (random.nextInt(8)) {
         case 0:
            this.setBlockState(world, pos.add(1, 0, 0), Blocks.STAINED_GLASS, 0);
            this.setBlockState(world, pos.add(-1, 0, 1), Blocks.STAINED_GLASS, 0);
            break;
         case 1:
            this.setBlockState(world, pos.add(-1, 0, 0), Blocks.STAINED_GLASS, 0);
            this.setBlockState(world, pos.add(1, 0, 1), Blocks.STAINED_GLASS, 0);
            break;
         case 2:
            this.setBlockState(world, pos.add(0, 0, 1), Blocks.STAINED_GLASS, 0);
            this.setBlockState(world, pos.add(1, 0, -1), Blocks.STAINED_GLASS, 0);
            break;
         case 3:
            this.setBlockState(world, pos.add(0, 0, -1), Blocks.STAINED_GLASS, 0);
            this.setBlockState(world, pos.add(1, 0, 1), Blocks.STAINED_GLASS, 0);
            break;
         case 4:
            this.setBlockState(world, pos.add(1, 0, 0), Blocks.STAINED_GLASS, 0);
            this.setBlockState(world, pos.add(-1, 0, -1), Blocks.STAINED_GLASS, 0);
            break;
         case 5:
            this.setBlockState(world, pos.add(-1, 0, 0), Blocks.STAINED_GLASS, 0);
            this.setBlockState(world, pos.add(1, 0, -1), Blocks.STAINED_GLASS, 0);
            break;
         case 6:
            this.setBlockState(world, pos.add(0, 0, 1), Blocks.STAINED_GLASS, 0);
            this.setBlockState(world, pos.add(-1, 0, -1), Blocks.STAINED_GLASS, 0);
            break;
         case 7:
            this.setBlockState(world, pos.add(0, 0, -1), Blocks.STAINED_GLASS, 0);
            this.setBlockState(world, pos.add(-1, 0, 1), Blocks.STAINED_GLASS, 0);
      }
   }

   private void genPepper(World world, Random random, int i, int j, int k) {
      BlockPos pos = new BlockPos(i, j, k);
      this.setBlockState(world, pos, Blocks.WOOL, 5);
      switch (random.nextInt(4)) {
         case 0:
            this.setBlockState(world, pos.add(1, 0, 0), Blocks.WOOL, 5);
            break;
         case 1:
            this.setBlockState(world, pos.add(-1, 0, 0), Blocks.WOOL, 5);
            break;
         case 2:
            this.setBlockState(world, pos.add(0, 0, 1), Blocks.WOOL, 5);
            break;
         case 3:
            this.setBlockState(world, pos.add(0, 0, -1), Blocks.WOOL, 5);
      }
   }

   private void genOlive(World world, Random random, int i, int j, int k) {
      BlockPos pos = new BlockPos(i, j, k);
      this.setBlockState(world, pos, Blocks.OBSIDIAN, 0);
   }

   private void genShroom(World world, Random random, int i, int j, int k) {
      BlockPos pos = new BlockPos(i, j, k);
      this.setBlockState(world, pos, Blocks.SOUL_SAND, 0);
   }

   private void genPepperoni(World world, Random random, int i, int j, int k) {
      switch (random.nextInt(4)) {
         case 0:
            this.genBox(world, Blocks.REDSTONE_BLOCK, 0, 0, i, j, k, i + 1, j, k + 1);
            break;
         case 1:
            this.genBox(world, Blocks.REDSTONE_BLOCK, 0, 0, i - 1, j, k, i, j, k + 1);
            break;
         case 2:
            this.genBox(world, Blocks.REDSTONE_BLOCK, 0, 0, i, j, k - 1, i + 1, j, k);
            break;
         case 3:
            this.genBox(world, Blocks.REDSTONE_BLOCK, 0, 0, i - 1, j, k - 1, i, j, k);
      }
   }
}
