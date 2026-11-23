package com.electrifiedded.youtubers.WorldGen;

import com.electrifiedded.archaiclib.world.StagedWorld;
import net.minecraft.util.math.BlockPos;
import java.util.Random;
import net.minecraft.entity.item.EntityPainting;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class GenStampyhouse extends GenYoutuberStructure {
   @Override
   public boolean generate(World world, Random random, BlockPos pos) {
      int i = pos.getX();
      int j = pos.getY();
      int k = pos.getZ();
      this.GenP1(world, random, i, j, k);
      return true;
   }

   private void GenP1(World world, Random random, int i, int j, int k) {
      this.genBox(world, Blocks.AIR, 0, 0, i, j - 1, k - 11, i + 10, j + 10, k + 11);
      this.genBox(world, Blocks.PLANKS, 0, 0, i, j - 1, k - 11, i + 10, j - 1, k + 11);

      for (int a = -11; a <= 11; a++) {
         int n = Math.abs(a);
         if (a != 0) {
            if (n != 2 && n != 4 && n != 7 && n != 9) {
               for (int b = 0; b < 3; b++) {
                  this.setBlockState(world, new BlockPos(i, j + b, k + a), Blocks.PLANKS, 0);
                  this.setBlockState(world, new BlockPos(i + 10, j + b, k + a), Blocks.PLANKS, 0);
               }
            } else {
               for (int b = 0; b < 3; b++) {
                  this.setBlockState(world, new BlockPos(i, j + b, k + a), Blocks.SANDSTONE, 0);
                  this.setBlockState(world, new BlockPos(i + 10, j + b, k + a), Blocks.SANDSTONE, 0);
               }
            }
         }

         this.setBlockState(world, new BlockPos(i, j + 3, k + a), Blocks.PLANKS, 0);
         this.setBlockState(world, new BlockPos(i + 10, j + 3, k + a), Blocks.PLANKS, 0);
         double q = 5 - 5 * (n + 1) / 10;

         for (int b = 0; b <= q; b++) {
            if (n != 2 && n != 4 && n != 0) {
               this.setBlockState(world, new BlockPos(i, j + 4 + b, k + a), Blocks.PLANKS, 0);
               this.setBlockState(world, new BlockPos(i + 10, j + 4 + b, k + a), Blocks.PLANKS, 0);
            } else {
               this.setBlockState(world, new BlockPos(i, j + 4 + b, k + a), Blocks.SANDSTONE, 0);
               this.setBlockState(world, new BlockPos(i + 10, j + 4 + b, k + a), Blocks.SANDSTONE, 0);
            }
         }

         for (int w = 0; w < 11; w++) {
            if (a % 2 == 0) {
               this.setBlockState(world, new BlockPos(i + w, j + 5 + (int)Math.floor(q), k + a), Blocks.WOODEN_SLAB, 0);
               this.setBlockState(world, new BlockPos(i + w, j + 4 + (int)Math.floor(q), k + a), Blocks.PLANKS, 0);
            } else if (a > 0) {
               this.setBlockState(world, new BlockPos(i + w, j + 5 + (int)Math.floor(q), k + a), Blocks.OAK_STAIRS, 3);
            } else {
               this.setBlockState(world, new BlockPos(i + w, j + 5 + (int)Math.floor(q), k + a), Blocks.OAK_STAIRS, 2);
            }
         }
      }

      this.setBlockState(world, new BlockPos(i, j + 1, k + 4), Blocks.GLASS, 0);
      this.setBlockState(world, new BlockPos(i, j + 1, k + 3), Blocks.GLASS, 0);
      this.setBlockState(world, new BlockPos(i, j + 1, k + 2), Blocks.GLASS, 0);
      this.setBlockState(world, new BlockPos(i, j + 1, k - 2), Blocks.GLASS, 0);
      this.setBlockState(world, new BlockPos(i, j + 1, k - 3), Blocks.GLASS, 0);
      this.setBlockState(world, new BlockPos(i, j + 1, k - 4), Blocks.GLASS, 0);
      this.setBlockState(world, new BlockPos(i + 10, j + 1, k + 4), Blocks.GLASS, 0);
      this.setBlockState(world, new BlockPos(i + 10, j + 1, k + 3), Blocks.GLASS, 0);
      this.setBlockState(world, new BlockPos(i + 10, j + 1, k + 2), Blocks.GLASS, 0);
      this.setBlockState(world, new BlockPos(i + 10, j + 1, k - 2), Blocks.GLASS, 0);
      this.setBlockState(world, new BlockPos(i + 10, j + 1, k - 3), Blocks.GLASS, 0);
      this.setBlockState(world, new BlockPos(i + 10, j + 1, k - 4), Blocks.GLASS, 0);

      for (int a = 1; a < 10; a++) {
         for (int bx = 0; bx < 7; bx++) {
            if (a % 2 == 0) {
               this.setBlockState(world, new BlockPos(i + a, j + bx, k + 5), Blocks.PLANKS, 0);
               this.setBlockState(world, new BlockPos(i + a, j + bx, k - 5), Blocks.PLANKS, 0);
            } else {
               this.setBlockState(world, new BlockPos(i + a, j + bx, k + 5), Blocks.SANDSTONE, 0);
               this.setBlockState(world, new BlockPos(i + a, j + bx, k - 5), Blocks.SANDSTONE, 0);
            }
         }

         this.setBlockState(world, new BlockPos(i + a, j + 3, k + 5), Blocks.PLANKS, 0);
         this.setBlockState(world, new BlockPos(i + a, j + 3, k - 5), Blocks.PLANKS, 0);
      }

      this.setBlockState(world, new BlockPos(i + 1, j, k + 5), Blocks.PLANKS, 0);
      this.setBlockState(world, new BlockPos(i + 9, j, k + 5), Blocks.PLANKS, 0);
      this.setBlockState(world, new BlockPos(i + 1, j, k - 5), Blocks.PLANKS, 0);
      this.setBlockState(world, new BlockPos(i + 9, j, k - 5), Blocks.PLANKS, 0);
      this.genBox(world, Blocks.GLASS, 0, 0, i + 3, j - 1, k - 2, i + 7, j - 1, k + 2);
      this.genBox(world, Blocks.PLANKS, 0, 0, i + 1, j + 3, k - 4, i + 9, j + 3, k + 4);
      this.genBox(world, Blocks.AIR, 0, 0, i + 3, j + 3, k - 2, i + 7, j + 3, k - 2);
      this.genBox(world, Blocks.AIR, 0, 0, i + 3, j + 3, k + 2, i + 7, j + 3, k + 2);
      this.setBlockToAir(world, new BlockPos(i + 4, j + 3, k - 4));
      this.setBlockState(world, new BlockPos(i + 4, j, k - 4), Blocks.OAK_STAIRS, 0);
      this.setBlockState(world, new BlockPos(i + 5, j, k - 4), Blocks.PLANKS, 0);
      this.setBlockState(world, new BlockPos(i + 6, j, k - 4), Blocks.PLANKS, 0);
      this.setBlockToAir(world, new BlockPos(i + 5, j + 3, k - 4));
      this.setBlockState(world, new BlockPos(i + 5, j + 1, k - 4), Blocks.OAK_STAIRS, 0);
      this.setBlockState(world, new BlockPos(i + 6, j + 1, k - 4), Blocks.PLANKS, 0);
      this.setBlockToAir(world, new BlockPos(i + 6, j + 3, k - 4));
      this.setBlockState(world, new BlockPos(i + 6, j + 2, k - 4), Blocks.OAK_STAIRS, 0);
      this.setBlockState(world, new BlockPos(i + 7, j + 3, k - 4), Blocks.OAK_STAIRS, 0);
      this.setBlockToAir(world, new BlockPos(i + 4, j + 3, k + 4));
      this.setBlockState(world, new BlockPos(i + 4, j, k + 4), Blocks.OAK_STAIRS, 0);
      this.setBlockState(world, new BlockPos(i + 5, j, k + 4), Blocks.PLANKS, 0);
      this.setBlockState(world, new BlockPos(i + 6, j, k + 4), Blocks.PLANKS, 0);
      this.setBlockToAir(world, new BlockPos(i + 5, j + 3, k + 4));
      this.setBlockState(world, new BlockPos(i + 5, j + 1, k + 4), Blocks.OAK_STAIRS, 0);
      this.setBlockState(world, new BlockPos(i + 6, j + 1, k + 4), Blocks.PLANKS, 0);
      this.setBlockToAir(world, new BlockPos(i + 6, j + 3, k + 4));
      this.setBlockState(world, new BlockPos(i + 6, j + 2, k + 4), Blocks.OAK_STAIRS, 0);
      this.setBlockState(world, new BlockPos(i + 7, j + 3, k + 4), Blocks.OAK_STAIRS, 0);
      this.genBox(world, Blocks.OAK_FENCE, 0, 0, i + 3, j + 4, k - 3, i + 7, j + 4, k - 3);
      this.genBox(world, Blocks.OAK_FENCE, 0, 0, i + 3, j + 4, k + 3, i + 7, j + 4, k + 3);
      this.genBox(world, Blocks.OAK_FENCE, 0, 0, i + 2, j + 4, k - 3, i + 2, j + 4, k - 1);
      this.genBox(world, Blocks.OAK_FENCE, 0, 0, i + 2, j + 4, k + 1, i + 2, j + 4, k + 3);
      this.setBlockState(world, new BlockPos(i + 3, j + 4, k + 1), Blocks.OAK_FENCE, 0);
      this.setBlockState(world, new BlockPos(i + 3, j + 4, k - 1), Blocks.OAK_FENCE, 0);
      this.genBox(world, Blocks.PLANKS, 0, 0, i + 4, j + 4, k - 1, i + 9, j + 4, k + 1);

      for (int a = 5; a <= 9; a++) {
         this.setBlockState(world, new BlockPos(i + a, j + 4, k), Blocks.WATER, 0);
      }

      for (int a = 0; a < 3; a++) {
         this.GenCircle(world, random, Blocks.GLASS, 0, i + 5, j + a, k, 1.0F, 0.0F, 'y');
      }

      for (int a = -1; a < 4; a++) {
         this.setBlockState(world, new BlockPos(i + 5, j + a, k), Blocks.WATER, 0);
      }

      this.setBlockState(world, new BlockPos(i + 9, j + 5, k), Blocks.WOODEN_SLAB, 0);
      this.setBlockState(world, new BlockPos(i + 8, j + 5, k), Blocks.WOODEN_SLAB, 0);
      this.setBlockState(world, new BlockPos(i + 9, j + 4, k + 2), Blocks.WOODEN_SLAB, 0);
      this.setBlockState(world, new BlockPos(i + 8, j + 4, k + 2), Blocks.WOODEN_SLAB, 0);
      this.setBlockState(world, new BlockPos(i + 9, j + 4, k - 2), Blocks.WOODEN_SLAB, 0);
      this.setBlockState(world, new BlockPos(i + 8, j + 4, k - 2), Blocks.WOODEN_SLAB, 0);
      this.genBox(world, Blocks.GLASS, 0, 0, i + 1, j, k - 11, i + 9, j + 2, k - 11);
      this.genBox(world, Blocks.MOSSY_COBBLESTONE, 0, 0, i + 1, j - 1, k - 10, i + 9, j - 1, k - 6);
      this.genBox(world, Blocks.MOSSY_COBBLESTONE, 0, 0, i + 1, j, k - 10, i + 1, j + 2, k - 6);
      this.genBox(world, Blocks.MOSSY_COBBLESTONE, 0, 0, i + 1, j, k - 6, i + 4, j + 2, k - 6);
      this.genBox(world, Blocks.SNOW, 0, 0, i + 9, j, k - 10, i + 9, j + 2, k - 6);
      this.genBox(world, Blocks.SNOW, 0, 0, i + 5, j, k - 6, i + 9, j + 2, k - 6);
      this.setBlockState(world, new BlockPos(i + 5, j + 1, k - 6), Blocks.MOSSY_COBBLESTONE, 0);
      this.setBlockState(world, new BlockPos(i + 9, j + 1, k - 7), Blocks.LIT_PUMPKIN, 1);
      this.setBlockState(world, new BlockPos(i + 9, j + 1, k - 9), Blocks.LIT_PUMPKIN, 1);
      this.setBlockToAir(world, new BlockPos(i + 3, j, k - 6));
      this.setBlockToAir(world, new BlockPos(i + 3, j + 1, k - 6));
      this.setBlockToAir(world, new BlockPos(i + 3, j, k - 5));
      this.setBlockToAir(world, new BlockPos(i + 3, j + 1, k - 5));
      this.setBlockState(world, new BlockPos(i + 3, j, k - 5), Blocks.OAK_DOOR, 1);
      this.setBlockState(world, new BlockPos(i + 3, j + 1, k - 5), Blocks.OAK_DOOR, 9);
      this.setBlockToAir(world, new BlockPos(i + 7, j, k - 6));
      this.setBlockToAir(world, new BlockPos(i + 7, j + 1, k - 6));
      this.setBlockToAir(world, new BlockPos(i + 7, j, k - 5));
      this.setBlockToAir(world, new BlockPos(i + 7, j + 1, k - 5));
      this.setBlockState(world, new BlockPos(i + 7, j, k - 5), Blocks.OAK_DOOR, 1);
      this.setBlockState(world, new BlockPos(i + 7, j + 1, k - 5), Blocks.OAK_DOOR, 9);
      this.genBox(world, Blocks.SNOW, 0, 0, i + 1, j + 3, k - 8, i + 9, j + 3, k - 6);
      this.genBox(world, Blocks.PLANKS, 0, 0, i + 1, j + 3, k - 11, i + 9, j + 3, k - 9);
      this.genBox(world, Blocks.AIR, 0, 0, i + 1, j + 4, k - 8, i + 9, j + 5, k - 6);
      this.genBox(world, Blocks.PLANKS, 0, 0, i + 1, j + 4, k - 9, i + 9, j + 4, k - 9);

      for (int a = 0; a < 4; a++) {
         this.setBlockState(world, new BlockPos(i + 2 + 2 * a, j + 4, k - 8), Blocks.BED, 10);
         this.setBlockState(world, new BlockPos(i + 2 + 2 * a, j + 4, k - 7), Blocks.BED, 2);
         this.setBlockState(world, new BlockPos(i + 2 + 2 * a, j + 5, k - 8), Blocks.TORCH, 3);
         this.setBlockState(world, new BlockPos(i + 2 + 2 * a, j + 5, k - 6), Blocks.TORCH, 4);
      }

      this.setBlockState(world, new BlockPos(i + 1, j + 4, k - 5), Blocks.OAK_DOOR, 1);
      this.setBlockState(world, new BlockPos(i + 1, j + 5, k - 5), Blocks.OAK_DOOR, 9);
      this.genBox(world, Blocks.SANDSTONE, 0, 0, i + 1, j, k + 11, i + 9, j + 2, k + 11);
      this.genBox(world, Blocks.WOOL, 11, 0, i + 1, j - 1, k + 6, i + 9, j - 1, k + 10);
      this.genBox(world, Blocks.SNOW, 0, 0, i + 1, j, k + 6, i + 1, j + 2, k + 10);
      this.genBox(world, Blocks.SNOW, 0, 0, i + 1, j, k + 6, i + 9, j + 2, k + 6);
      this.genBox(world, Blocks.SNOW, 0, 0, i + 9, j, k + 6, i + 9, j + 2, k + 10);
      this.genBox(world, Blocks.PLANKS, 11, 0, i + 1, j + 3, k + 11, i + 9, j + 3, k + 11);
      this.genBox(world, Blocks.STONE, 0, 0, i + 1, j + 3, k + 6, i + 9, j + 3, k + 10);
      this.boxReplace(world, Blocks.STONE, 0, Blocks.WOOL, 7, i + 1, j + 3, k + 6, i + 9, j + 3, k + 10, "chekers");
      this.setBlockToAir(world, new BlockPos(i + 3, j, k + 6));
      this.setBlockToAir(world, new BlockPos(i + 3, j + 1, k + 6));
      this.setBlockToAir(world, new BlockPos(i + 3, j, k + 5));
      this.setBlockToAir(world, new BlockPos(i + 3, j + 1, k + 5));
      this.setBlockState(world, new BlockPos(i + 3, j, k + 5), Blocks.OAK_DOOR, 3);
      this.setBlockState(world, new BlockPos(i + 3, j + 1, k + 5), Blocks.OAK_DOOR, 9);
      this.setBlockToAir(world, new BlockPos(i + 7, j, k + 6));
      this.setBlockToAir(world, new BlockPos(i + 7, j + 1, k + 6));
      this.setBlockToAir(world, new BlockPos(i + 7, j, k + 5));
      this.setBlockToAir(world, new BlockPos(i + 7, j + 1, k + 5));
      this.setBlockState(world, new BlockPos(i + 7, j, k + 5), Blocks.OAK_DOOR, 3);
      this.setBlockState(world, new BlockPos(i + 7, j + 1, k + 5), Blocks.OAK_DOOR, 9);
      this.setBlockState(world, new BlockPos(i + 4, j, k + 7), Blocks.BOOKSHELF, 0);
      this.setBlockState(world, new BlockPos(i + 5, j, k + 7), Blocks.BED, 10);
      this.setBlockState(world, new BlockPos(i + 5, j, k + 8), Blocks.BED, 2);
      this.setBlockState(world, new BlockPos(i + 6, j, k + 7), Blocks.BOOKSHELF, 0);
      this.genBox(world, Blocks.AIR, 0, 0, i + 3, j - 1, k + 11, i + 7, j + 2, k + 14);
      this.genBox(world, Blocks.PLANKS, 0, 0, i + 3, j - 1, k + 11, i + 7, j - 1, k + 14);
      this.genBox(world, Blocks.OAK_FENCE, 0, 1, i + 3, j, k + 11, i + 7, j, k + 14);
      this.genBox(world, Blocks.GLASS, 0, 0, i + 2, j, k + 11, i + 8, j + 2, k + 11);
      this.setBlockState(world, new BlockPos(i + 5, j, k + 11), Blocks.OAK_DOOR, 3);
      this.setBlockState(world, new BlockPos(i + 5, j + 1, k + 11), Blocks.OAK_DOOR, 9);
      this.setBlockState(world, new BlockPos(i + 1, j, k + 7), Blocks.OAK_DOOR, 3);
      this.setBlockState(world, new BlockPos(i + 1, j + 1, k + 7), Blocks.OAK_DOOR, 9);
      if (!world.isRemote) {
         StagedWorld.onMain(() -> StagedWorld.spawnEntity(world, StagedWorld.newPainting(world, i + 1, j, k + 8, 3, "Bust")));
      }

      for (int a = 0; a < 7; a++) {
         this.setBlockState(world, new BlockPos(i + 1, j + a, k + 6), Blocks.LADDER, 3);
      }

      this.setBlockState(world, new BlockPos(i + 10, j + 5, k), Blocks.GLASS, 0);
      this.setBlockState(world, new BlockPos(i + 10, j + 6, k), Blocks.GLASS, 0);
      this.setBlockState(world, new BlockPos(i + 9, j + 2, k + 2), Blocks.TORCH, 2);
      this.setBlockState(world, new BlockPos(i + 3, j + 2, k + 4), Blocks.TORCH, 4);
      this.setBlockState(world, new BlockPos(i + 1, j + 2, k + 2), Blocks.TORCH, 1);
      this.setBlockState(world, new BlockPos(i + 3, j + 2, k - 4), Blocks.TORCH, 3);
      this.setBlockState(world, new BlockPos(i + 9, j + 2, k - 2), Blocks.TORCH, 2);
      this.setBlockState(world, new BlockPos(i + 7, j + 2, k + 4), Blocks.TORCH, 4);
      this.setBlockState(world, new BlockPos(i + 1, j + 2, k - 2), Blocks.TORCH, 1);
      this.setBlockState(world, new BlockPos(i + 7, j + 2, k - 4), Blocks.TORCH, 3);
      this.setBlockState(world, new BlockPos(i + 9, j + 6, k + 2), Blocks.TORCH, 2);
      this.setBlockState(world, new BlockPos(i + 3, j + 6, k + 4), Blocks.TORCH, 4);
      this.setBlockState(world, new BlockPos(i + 1, j + 6, k + 2), Blocks.TORCH, 1);
      this.setBlockState(world, new BlockPos(i + 3, j + 6, k - 4), Blocks.TORCH, 3);
      this.setBlockState(world, new BlockPos(i + 9, j + 6, k - 2), Blocks.TORCH, 2);
      this.setBlockState(world, new BlockPos(i + 7, j + 6, k + 4), Blocks.TORCH, 4);
      this.setBlockState(world, new BlockPos(i + 1, j + 6, k - 2), Blocks.TORCH, 1);
      this.setBlockState(world, new BlockPos(i + 7, j + 6, k - 4), Blocks.TORCH, 3);
      this.setBlockState(world, new BlockPos(i + 3, j + 2, k - 7), Blocks.TORCH, 4);
      this.setBlockState(world, new BlockPos(i + 3, j + 2, k + 7), Blocks.TORCH, 3);
      this.setBlockState(world, new BlockPos(i + 7, j + 2, k - 7), Blocks.TORCH, 4);
      this.setBlockState(world, new BlockPos(i + 7, j + 2, k + 7), Blocks.TORCH, 3);
      this.setBlockState(world, new BlockPos(i + 3, j + 6, k + 6), Blocks.TORCH, 3);
      this.setBlockState(world, new BlockPos(i + 7, j + 6, k + 6), Blocks.TORCH, 3);

      for (int a = 4; a < 10; a++) {
         this.setBlockState(world, new BlockPos(i + 4, j + a, k), Blocks.PLANKS, 0);
         this.setBlockState(world, new BlockPos(i + 3, j + a, k), Blocks.LADDER, 4);
      }
   }
}
