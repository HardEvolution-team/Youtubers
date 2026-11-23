package com.electrifiedded.youtubers.WorldGen;

import com.electrifiedded.archaiclib.world.StagedWorld;
import com.electrifiedded.youtubers.YTItems;
import net.minecraft.util.math.BlockPos;
import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.world.World;

public class GenTobuscusTower extends GenYoutuberStructure {
   int[][] Text = new int[][]{
      {1, 1, 1, 1, 1, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 0, 0, 1, 0, 0, 1, 1, 1, 0, 0, 1, 1, 0, 1, 0, 0, 1, 0, 0, 1, 1, 1},
      {0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 1, 0, 1, 0, 0, 0},
      {0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 1, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 1, 0, 0, 1, 0, 0, 1, 1, 0},
      {0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 1, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 1},
      {0, 0, 1, 0, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0}
   };

   @Override
   public boolean generate(World world, Random random, BlockPos pos) {
      int i = pos.getX();
      int j = pos.getY();
      int k = pos.getZ();
      this.genBox(world, Blocks.WOOL, 13, 0, i - 4, j - 1, k - 4, i + 4, j + 35, k + 4);
      this.genBoxAir(world, 0, i - 3, j, k - 3, i + 3, j + 34, k + 3);
      this.boxReplace(world, Blocks.WOOL, 13, Blocks.WOOL, 5, i - 4, j - 1, k - 4, i + 4, j + 35, k + 4, "random");
      this.boxReplace(world, Blocks.WOOL, 13, Blocks.WOOL, 5, i - 4, j - 1, k - 4, i + 4, j + 35, k + 4, "random");
      this.boxReplace(world, Blocks.WOOL, 5, Blocks.STAINED_HARDENED_CLAY, 5, i - 4, j - 1, k - 4, i + 4, j + 35, k + 4, "random");
      this.boxReplace(world, Blocks.STAINED_HARDENED_CLAY, 5, Blocks.WOOL, 5, i - 4, j - 1, k - 4, i + 4, j + 35, k + 4, "random");
      this.genBox(world, Blocks.WOOL, 13, 0, i - 20, j + 36, k - 4, i + 20, j + 44, k + 4);
      this.boxReplace(world, Blocks.WOOL, 13, Blocks.WOOL, 5, i - 20, j + 36, k - 4, i + 20, j + 44, k + 4, "random");
      this.boxReplace(world, Blocks.WOOL, 13, Blocks.WOOL, 5, i - 20, j + 36, k - 4, i + 20, j + 44, k + 4, "random");
      this.boxReplace(world, Blocks.WOOL, 5, Blocks.STAINED_HARDENED_CLAY, 5, i - 20, j + 36, k - 4, i + 20, j + 44, k + 4, "random");
      this.boxReplace(world, Blocks.STAINED_HARDENED_CLAY, 5, Blocks.WOOL, 5, i - 20, j + 36, k - 4, i + 20, j + 44, k + 4, "random");
      this.setBlockState(world, new BlockPos(i, j, k - 4), Blocks.OAK_DOOR, 1);
      this.setBlockState(world, new BlockPos(i, j + 1, k - 4), Blocks.OAK_DOOR, 9);

      for (int a = 0; a < 37; a++) {
         for (int b = 0; b < 5; b++) {
            if (this.Text[b][a] == 1) {
               this.setBlockState(world, new BlockPos(i - 18 + a, j + 42 - b, k + 4), Blocks.SNOW, 0);
               this.setBlockState(world, new BlockPos(i + 18 - a, j + 42 - b, k - 4), Blocks.SNOW, 0);
            }
         }
      }

      for (int a = 0; a < 35; a++) {
         this.addChest(world, random, i - 3, j + a, k + 3);
         this.addChest(world, random, i - 3, j + a, k + 2);
         this.setBlockState(world, new BlockPos(i - 3, j + a, k + 1), Blocks.LADDER, 5);
         this.setBlockState(world, new BlockPos(i - 3, j + a, k - 1), Blocks.LADDER, 5);
         this.addChest(world, random, i - 3, j + a, k - 2);
         this.addChest(world, random, i - 3, j + a, k - 3);
         this.addChest(world, random, i + 3, j + a, k + 3);
         this.addChest(world, random, i + 3, j + a, k + 2);
         this.setBlockState(world, new BlockPos(i + 3, j + a, k + 1), Blocks.LADDER, 4);
         this.setBlockState(world, new BlockPos(i + 3, j + a, k - 1), Blocks.LADDER, 4);
         this.addChest(world, random, i + 3, j + a, k - 2);
         this.addChest(world, random, i + 3, j + a, k - 3);
         if ((a + 1) % 3 == 0) {
            this.setBlockState(world, new BlockPos(i + 3, j + a, k), Blocks.TORCH, 2);
            this.setBlockState(world, new BlockPos(i, j + a, k + 3), Blocks.TORCH, 4);
            this.setBlockState(world, new BlockPos(i - 3, j + a, k), Blocks.TORCH, 1);
            this.setBlockState(world, new BlockPos(i, j + a, k - 3), Blocks.TORCH, 3);
         } else {
            this.setBlockState(world, new BlockPos(i - 3, j + a, k), Blocks.LADDER, 5);
            this.setBlockState(world, new BlockPos(i + 3, j + a, k), Blocks.LADDER, 4);
         }
      }

      return false;
   }

   private void addChest(World world, Random random, int i, int j, int k) {
      this.setBlockState(world, new BlockPos(i, j, k), Blocks.CHEST, 0);
      TileEntityChest chestTE = (TileEntityChest)StagedWorld.getTileEntity(world, i, j, k);
      if (chestTE != null) {
         int numb = random.nextInt(10);

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
}
