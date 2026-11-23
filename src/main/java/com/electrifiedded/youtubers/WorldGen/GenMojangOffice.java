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

public class GenMojangOffice extends GenYoutuberStructure {
   int[][] Logo = new int[][]{
      {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
      {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0},
      {0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0},
      {0, 1, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0},
      {0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0},
      {0, 1, 0, 0, 1, 1, 0, 0, 0, 0, 1, 0},
      {0, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 0},
      {0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0},
      {0, 1, 0, 0, 1, 1, 1, 1, 1, 0, 1, 0},
      {0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0},
      {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0},
      {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
   };

   @Override
   public boolean generate(World world, Random random, BlockPos pos) {
      int i = pos.getX();
      int j = pos.getY();
      int k = pos.getZ();
      this.genBox(world, Blocks.QUARTZ_BLOCK, 13, 0, i - 5, j - 1, k - 5, i + 6, j + 13, k + 6);
      this.genBoxAir(world, 0, i - 4, j, k - 4, i + 5, j + 12, k + 5);

      for (int a = 0; a < 12; a++) {
         for (int b = 0; b < 12; b++) {
            if (this.Logo[b][a] == 1) {
               this.setBlockState(world, new BlockPos(i - 5 + a, j + 13 - b, k + 6), Blocks.SAND, 1);
               this.setBlockState(world, new BlockPos(i + 6 - a, j + 13 - b, k - 5), Blocks.SAND, 1);
            }
         }
      }

      this.setBlockState(world, new BlockPos(i, j, k - 5), Blocks.OAK_DOOR, 1);
      this.setBlockState(world, new BlockPos(i, j + 1, k - 5), Blocks.OAK_DOOR, 9);
      this.setBlockState(world, new BlockPos(i + 1, j, k - 5), Blocks.OAK_DOOR, 1);
      this.setBlockState(world, new BlockPos(i + 1, j + 1, k - 5), Blocks.OAK_DOOR, 8);
      this.setBlockState(world, new BlockPos(i, j, k + 5), Blocks.BED, 8);
      this.setBlockState(world, new BlockPos(i, j, k + 4), Blocks.BED, 0);
      this.addChest(world, random, i - 4, j, k + 5);
      this.addChest(world, random, i - 3, j, k + 5);
      this.addChest(world, random, i - 4, j + 1, k + 5);
      this.addChest(world, random, i - 3, j + 1, k + 5);
      this.addChest(world, random, i - 4, j + 2, k + 5);
      this.addChest(world, random, i - 3, j + 2, k + 5);
      this.addChest(world, random, i - 4, j + 3, k + 5);
      this.addChest(world, random, i - 3, j + 3, k + 5);
      this.addChest(world, random, i - 4, j, k + 3);
      this.addChest(world, random, i - 3, j, k + 3);
      this.addChest(world, random, i - 4, j + 1, k + 3);
      this.addChest(world, random, i - 3, j + 1, k + 3);
      this.addChest(world, random, i - 4, j + 2, k + 3);
      this.addChest(world, random, i - 3, j + 2, k + 3);
      this.addChest(world, random, i - 4, j + 3, k + 3);
      this.addChest(world, random, i - 3, j + 3, k + 3);
      this.setBlockState(world, new BlockPos(i + 5, j, k + 5), Blocks.CRAFTING_TABLE, 0);
      this.setBlockState(world, new BlockPos(i + 5, j, k + 4), Blocks.FURNACE, 0);
      this.setBlockState(world, new BlockPos(i + 5, j + 6, k + 5), Blocks.REDSTONE_BLOCK, 0);
      this.setBlockState(world, new BlockPos(i + 5, j + 6, k - 4), Blocks.REDSTONE_BLOCK, 0);
      this.setBlockState(world, new BlockPos(i - 4, j + 6, k + 5), Blocks.REDSTONE_BLOCK, 0);
      this.setBlockState(world, new BlockPos(i - 4, j + 6, k - 4), Blocks.REDSTONE_BLOCK, 0);
      this.setBlockState(world, new BlockPos(i + 5, j + 5, k + 5), Blocks.LIT_REDSTONE_LAMP, 0);
      this.setBlockState(world, new BlockPos(i + 5, j + 5, k - 4), Blocks.LIT_REDSTONE_LAMP, 0);
      this.setBlockState(world, new BlockPos(i - 4, j + 5, k + 5), Blocks.LIT_REDSTONE_LAMP, 0);
      this.setBlockState(world, new BlockPos(i - 4, j + 5, k - 4), Blocks.LIT_REDSTONE_LAMP, 0);
      return true;
   }

   private void addChest(World world, Random random, int i, int j, int k) {
      this.setBlockState(world, new BlockPos(i, j, k), Blocks.CHEST, 0);
      TileEntityChest chestTE = (TileEntityChest)StagedWorld.getTileEntity(world, i, j, k);
      if (chestTE != null) {
         int numb = random.nextInt(20) + 10;

         for (int a = 0; a < numb; a++) {
            chestTE.setInventorySlotContents(random.nextInt(27), new ItemStack(YTItems.Money[random.nextInt(4)], random.nextInt(8) + 1));
         }

         for (int a = 0; a < 5; a++) {
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
         }
      }
   }
}
