package com.electrifiedded.youtubers.WorldGen;

import com.electrifiedded.archaiclib.world.StagedWorld;
import com.electrifiedded.youtubers.YTItems;
import com.electrifiedded.youtubers.YTStructures;
import net.minecraft.util.math.BlockPos;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSapling;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.world.World;

public class GenSkyFactory extends GenYoutuberStructure {
   @Override
   public boolean generate(World world, Random random, BlockPos pos) {
      int i = pos.getX();
      int j = pos.getY();
      int k = pos.getZ();
      this.ClearSphere(world, i, j, k, 20.0);
      this.ClearSphere(world, i, j, k + 20, 10.0);
      this.DiamondPlatform(world, i, j - 1, k, 10.0);
      this.DiamondPlatform(world, i, j - 1, k + 17, 7.0);
      this.genPath(world, i + 4, j - 1, k + 17, i + 4, j - 1, k + 4);
      this.genPath(world, i - 5, j - 1, k + 4, i - 5, j - 1, k + 17);
      YTStructures.Map.get("Generic").generate(world, new com.electrifiedded.archaiclib.util.FastRandom(), new BlockPos(i, j, k + 10));

      for (int a = 0; a < 5; a++) {
         this.addChest(world, random, i, j + a, k - 1);
         this.addChest(world, random, i - 1, j + a, k - 1);
      }

      for (int a = 0; a < 5; a++) {
         this.setBlockState(world, new BlockPos(i + 1 + a, j, k - 1), Blocks.FURNACE, 2);
      }

      for (int a = 0; a < 4; a++) {
         for (int b = 0; b < 4; b++) {
            int n = 2;
            if (a == 0 || a == 3) {
               n--;
            }

            if (b == 0 || b == 3) {
               n--;
            }

            switch (n) {
               case 1:
                  this.setStoneBrick(world, random, i + 2 + a, j, k - 3 - b);
                  this.setStoneBrick(world, random, i + 2 + a, j + 1, k - 3 - b);
                  break;
               case 2:
                  this.setStoneBrick(world, random, i + 2 + a, j - 1, k - 3 - b);
                  this.setBlockToAir(world, new BlockPos(i + 2 + a, j, k - 3 - b));
            }
         }
      }

      this.setBlockState(world, new BlockPos(i, j - 1, k - 7), Blocks.WATER, 0);
      this.setBlockState(world, new BlockPos(i, j - 2, k - 7), Blocks.STONE_SLAB, 11);
      this.setBlockState(world, new BlockPos(i, j - 1, k - 6), Blocks.DIRT, 0);
      this.setBlockState(world, new BlockPos(i, j - 1, k - 8), Blocks.DIRT, 0);
      this.setBlockState(world, new BlockPos(i - 1, j - 1, k - 7), Blocks.DIRT, 0);
      this.setBlockState(world, new BlockPos(i + 1, j - 1, k - 7), Blocks.DIRT, 0);
      this.setBlockState(world, new BlockPos(i, j, k - 6), Blocks.REEDS, 0);
      this.setBlockState(world, new BlockPos(i, j, k - 8), Blocks.REEDS, 0);
      this.setBlockState(world, new BlockPos(i - 1, j, k - 7), Blocks.REEDS, 0);
      this.setBlockState(world, new BlockPos(i + 1, j, k - 7), Blocks.REEDS, 0);
      this.setBlockState(world, new BlockPos(i - 6, j - 1, k), Blocks.STONE_SLAB, 11);
      this.addCobbleChest(world, i - 6, j, k);
      this.setBlockState(world, new BlockPos(i - 6, j - 1, k + 1), Blocks.STONE_SLAB, 11);
      this.setBlockState(world, new BlockPos(i - 6, j - 1, k - 1), Blocks.STONE_SLAB, 11);
      this.setBlockState(world, new BlockPos(i - 5, j - 1, k), Blocks.STONE_SLAB, 11);
      this.setBlockState(world, new BlockPos(i - 7, j - 1, k), Blocks.STONE_SLAB, 11);
      this.setBlockState(world, new BlockPos(i - 6, j, k + 1), Blocks.STONE_SLAB, 3);
      this.setBlockState(world, new BlockPos(i - 6, j, k - 1), Blocks.STONE_SLAB, 3);
      this.setBlockState(world, new BlockPos(i - 5, j, k), Blocks.STONE_SLAB, 3);
      this.setBlockState(world, new BlockPos(i - 7, j, k), Blocks.STONE_SLAB, 3);
      this.DiamondPlatform(world, i, j - 6, k, 7.0);
      this.setBlockToAir(world, new BlockPos(i - 4, j - 1, k + 4));
      this.setBlockToAir(world, new BlockPos(i - 4, j - 1, k + 3));
      this.setBlockToAir(world, new BlockPos(i - 4, j - 1, k + 2));
      this.setBlockState(world, new BlockPos(i - 4, j - 1, k + 5), Blocks.OAK_STAIRS, 2);
      this.setBlockState(world, new BlockPos(i - 4, j - 2, k + 4), Blocks.OAK_STAIRS, 2);
      this.setBlockState(world, new BlockPos(i - 4, j - 3, k + 3), Blocks.OAK_STAIRS, 2);
      this.setBlockState(world, new BlockPos(i - 4, j - 4, k + 2), Blocks.OAK_STAIRS, 2);
      this.setBlockState(world, new BlockPos(i - 4, j - 5, k + 1), Blocks.OAK_STAIRS, 2);
      this.setBlockState(world, new BlockPos(i - 4, j - 1, k + 6), Blocks.OAK_STAIRS, 6);
      this.setBlockState(world, new BlockPos(i - 4, j - 2, k + 5), Blocks.OAK_STAIRS, 6);
      this.setBlockState(world, new BlockPos(i - 4, j - 3, k + 4), Blocks.OAK_STAIRS, 6);
      this.setBlockState(world, new BlockPos(i - 4, j - 4, k + 3), Blocks.OAK_STAIRS, 6);
      this.setBlockState(world, new BlockPos(i - 4, j - 5, k + 2), Blocks.OAK_STAIRS, 6);
      this.setBlockState(world, new BlockPos(i - 5, j - 4, k + 2), Blocks.OAK_FENCE, 0);
      this.setBlockState(world, new BlockPos(i - 5, j - 4, k + 1), Blocks.OAK_FENCE, 0);
      this.setBlockState(world, new BlockPos(i - 5, j - 3, k + 3), Blocks.OAK_FENCE, 0);
      this.setBlockState(world, new BlockPos(i - 5, j - 3, k + 2), Blocks.OAK_FENCE, 0);
      this.setBlockState(world, new BlockPos(i - 5, j - 3, k + 1), Blocks.OAK_FENCE, 0);
      this.setBlockState(world, new BlockPos(i - 5, j - 2, k + 4), Blocks.OAK_FENCE, 0);
      this.setBlockState(world, new BlockPos(i - 5, j - 2, k + 3), Blocks.OAK_FENCE, 0);
      this.setBlockState(world, new BlockPos(i - 5, j - 2, k + 2), Blocks.OAK_FENCE, 0);
      this.setBlockState(world, new BlockPos(i - 5, j - 2, k + 1), Blocks.OAK_FENCE, 0);
      this.setBlockState(world, new BlockPos(i - 2, j - 1, k + 19), Blocks.DIRT, 0);
      this.setBlockState(world, new BlockPos(i + 2, j - 1, k + 19), Blocks.DIRT, 0);
      this.setBlockState(world, new BlockPos(i - 2, j, k + 19), Blocks.SAPLING, 0);
      this.setBlockState(world, new BlockPos(i + 2, j, k + 19), Blocks.SAPLING, 0);
      StagedWorld.growSapling(world, i - 2, j, k + 19, random);
      StagedWorld.growSapling(world, i + 2, j, k + 19, random);
      return false;
   }

   public void genPath(World world, double i, double j, double k, double i2, double j2, double k2) {
      int width = 4;
      int a = (int)Math.sqrt(Math.pow(i - i2, 2.0) + Math.pow(j - j2, 2.0) + Math.pow(k - k2, 2.0)) * 2;
      double di = (i2 - i) / a;
      double dj = (j2 - j) / a;
      double dk = (k2 - k) / a;

      for (int q = 0; q < a; q++) {
         for (int w = 0; w < width * 2; w++) {
            double num = j + dj * q - Math.floor(j + dj * q);
            if (num < 0.5 && num != 0.0) {
               this.setBlockState(
                  world, new BlockPos((int)(i + di * q - dk * (w - width)), (int)(j + dj * q), (int)(k + dk * q + di * (w - width))), Blocks.WOODEN_SLAB, 0
               );
            } else {
               this.setBlockState(
                  world, new BlockPos((int)(i + di * q - dk * (w - width)), (int)(j + dj * q), (int)(k + dk * q + di * (w - width))), Blocks.WOODEN_SLAB, 8
               );
            }

            this.setBlockToAir(world, new BlockPos((int)(i + di * q - dk * (w - width)), (int)(j + dj * q) + 1, (int)(k + dk * q + di * (w - width))));
            if (w == 0 || w == width * 2 - 1) {
               this.setBlockState(
                  world,
                  new BlockPos((int)(i + di * q - dk * (w - width)), (int)(j + dj * q) + 1, (int)(k + dk * q + di * (w - width))),
                  Blocks.OAK_FENCE,
                  0
               );
            }
         }
      }
   }

   public void DiamondPlatform(World world, int i, int j, int k, double r) {
      for (int a = (int)(-r - 1.0); a < (int)(r + 1.0); a++) {
         for (int b = (int)(-r - 1.0); b < (int)(r + 1.0); b++) {
            if (Math.abs(a) + Math.abs(b) <= r) {
               this.setBlockState(world, new BlockPos(i + a, j, k + b), Blocks.WOODEN_SLAB, 8);
               if (Math.abs(Math.abs(a) + Math.abs(b) - r) <= 1.0) {
                  this.setBlockState(world, new BlockPos(i + a, j + 1, k + b), Blocks.OAK_FENCE, 0);
               }
            }
         }
      }
   }

   private void addCobbleChest(World world, int i, int j, int k) {
      this.setBlockState(world, new BlockPos(i, j, k), Blocks.CHEST, 0);
      TileEntityChest chestTE = (TileEntityChest)StagedWorld.getTileEntity(world, i, j, k);
      if (chestTE != null) {
         for (int a = 0; a < 27; a++) {
            chestTE.setInventorySlotContents(a, new ItemStack(Blocks.COBBLESTONE, 999));
         }
      }
   }

   private void addChest(World world, Random random, int i, int j, int k) {
      this.setBlockState(world, new BlockPos(i, j, k), Blocks.CHEST, 0);
      TileEntityChest chestTE = (TileEntityChest)StagedWorld.getTileEntity(world, i, j, k);
      if (chestTE != null) {
         int numb = random.nextInt(10) + 5;

         for (int a = 0; a < numb; a++) {
            chestTE.setInventorySlotContents(random.nextInt(27), new ItemStack(YTItems.Money[random.nextInt(4)], random.nextInt(8) + 1));
         }

         if (random.nextInt(7) == 0) {
            chestTE.setInventorySlotContents(random.nextInt(27), new ItemStack(Items.IRON_INGOT, random.nextInt(18) + 1));
         }

         if (random.nextInt(10) == 0) {
            chestTE.setInventorySlotContents(random.nextInt(27), new ItemStack(Items.GOLD_INGOT, random.nextInt(5) + 1));
         }

         if (random.nextInt(10) == 0) {
            chestTE.setInventorySlotContents(random.nextInt(27), new ItemStack(Blocks.OBSIDIAN, random.nextInt(5) + 1));
         }

         if (random.nextInt(15) == 0) {
            chestTE.setInventorySlotContents(random.nextInt(27), new ItemStack(Items.DIAMOND, random.nextInt(3) + 1));
         }

         numb = random.nextInt(10) + 10;

         for (int a = 0; a < numb; a++) {
            switch (random.nextInt(13)) {
               case 0:
                  chestTE.setInventorySlotContents(random.nextInt(27), new ItemStack(Items.STICK, random.nextInt(8) + 1));
                  break;
               case 1:
                  chestTE.setInventorySlotContents(random.nextInt(27), new ItemStack(Blocks.DIRT, random.nextInt(8) + 1, random.nextInt(2)));
                  break;
               case 2:
                  chestTE.setInventorySlotContents(random.nextInt(27), new ItemStack(Blocks.SAND, random.nextInt(8) + 1, random.nextInt(2)));
                  break;
               case 3:
                  chestTE.setInventorySlotContents(random.nextInt(27), new ItemStack(Blocks.GRAVEL, random.nextInt(8) + 1));
                  break;
               case 4:
                  chestTE.setInventorySlotContents(random.nextInt(27), new ItemStack(Items.FEATHER, random.nextInt(8) + 1));
                  break;
               case 5:
                  chestTE.setInventorySlotContents(random.nextInt(27), new ItemStack(Items.SPIDER_EYE, random.nextInt(8) + 1));
                  break;
               case 6:
                  chestTE.setInventorySlotContents(random.nextInt(27), new ItemStack(Blocks.GRASS, random.nextInt(8) + 1));
                  break;
               case 7:
                  chestTE.setInventorySlotContents(random.nextInt(27), new ItemStack(Items.BOAT));
                  break;
               case 8:
                  chestTE.setInventorySlotContents(random.nextInt(27), new ItemStack(Items.BED));
                  break;
               case 9:
                  chestTE.setInventorySlotContents(random.nextInt(27), new ItemStack(Items.BOWL, random.nextInt(8) + 1));
                  break;
               case 10:
                  chestTE.setInventorySlotContents(random.nextInt(27), new ItemStack(Items.CARROT_ON_A_STICK));
                  break;
               case 11:
                  chestTE.setInventorySlotContents(random.nextInt(27), new ItemStack(Items.PAINTING, random.nextInt(8) + 1));
                  break;
               case 12:
                  chestTE.setInventorySlotContents(random.nextInt(27), new ItemStack(Items.ITEM_FRAME, random.nextInt(8) + 1));
            }
         }
      }
   }

   private void setStoneBrick(World world, Random random, int x, int y, int z) {
      Block block = Blocks.STONEBRICK;
      int meta = 0;
      if (random.nextInt(2) == 0) {
         if (random.nextBoolean()) {
            meta += 2;
         } else {
            meta++;
         }
      }

      this.setBlockState(world, new BlockPos(x, y, z), block, meta);
   }
}
