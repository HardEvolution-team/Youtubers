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

public class GenJenHouse extends GenYoutuberStructure {
   @Override
   public boolean generate(World world, Random random, BlockPos pos) {
      int i = pos.getX();
      int j = pos.getY();
      int k = pos.getZ();
      this.genRoom(world, i - 6, j + 4, k);
      this.genRoom(world, i, j + 4, k);
      this.genRoom(world, i + 6, j + 4, k);
      this.genRoom(world, i - 6, j + 4, k + 6);
      this.genRoom(world, i, j + 4, k + 6);
      this.genRoom(world, i + 6, j + 4, k + 6);
      this.genRoom(world, i - 6, j, k);
      this.genRoom(world, i, j, k);
      this.genRoom(world, i + 6, j, k);
      this.genRoom(world, i - 6, j, k + 6);
      this.genRoom(world, i, j, k + 6);
      this.genRoom(world, i + 6, j, k + 6);
      this.setBlockState(world, new BlockPos(i, j, k - 3), Blocks.OAK_DOOR, 1);
      this.setBlockState(world, new BlockPos(i, j + 1, k - 3), Blocks.OAK_DOOR, 9);
      this.setBlockState(world, new BlockPos(i + 2, j + 1, k - 3), Blocks.GLASS, 0);
      this.setBlockState(world, new BlockPos(i - 2, j + 1, k - 3), Blocks.GLASS, 0);
      this.setBlockState(world, new BlockPos(i + 9, j + 1, k - 1), Blocks.GLASS, 0);
      this.setBlockState(world, new BlockPos(i + 9, j + 1, k), Blocks.GLASS, 0);
      this.setBlockState(world, new BlockPos(i + 9, j + 1, k + 1), Blocks.GLASS, 0);
      this.setBlockState(world, new BlockPos(i - 9, j + 1, k - 1), Blocks.GLASS, 0);
      this.setBlockState(world, new BlockPos(i - 9, j + 1, k), Blocks.GLASS, 0);
      this.setBlockState(world, new BlockPos(i - 9, j + 1, k + 1), Blocks.GLASS, 0);
      this.setBlockState(world, new BlockPos(i - 1, j, k + 1), Blocks.IRON_BARS, 0);
      this.setBlockState(world, new BlockPos(i, j, k + 1), Blocks.IRON_BARS, 0);
      this.setBlockState(world, new BlockPos(i + 1, j, k + 1), Blocks.IRON_BARS, 0);
      this.setBlockState(world, new BlockPos(i + 2, j, k + 1), Blocks.IRON_BARS, 0);
      this.setBlockState(world, new BlockPos(i, j - 1, k + 1), Blocks.STONEBRICK, 0);
      this.setBlockState(world, new BlockPos(i + 1, j - 1, k + 1), Blocks.STONEBRICK, 0);
      this.genBox(world, Blocks.STONEBRICK, 0, 0, i - 1, j - 1, k + 2, i + 2, j + 2, k + 2);
      this.genBox(world, Blocks.STONEBRICK, 0, 0, i, j - 1, k + 3, i + 1, j + 2, k + 3);
      this.genBox(world, Blocks.AIR, 0, 0, i, j, k + 2, i + 1, j + 1, k + 2);
      this.setBlockState(world, new BlockPos(i, j - 1, k + 2), Blocks.NETHERRACK, 0);
      this.setBlockState(world, new BlockPos(i + 1, j - 1, k + 2), Blocks.NETHERRACK, 0);
      this.setBlockToAir(world, new BlockPos(i + 3, j, k - 1));
      this.setBlockToAir(world, new BlockPos(i + 3, j + 1, k - 1));
      this.setBlockState(world, new BlockPos(i + 8, j, k - 2), Blocks.CRAFTING_TABLE, 0);
      this.setBlockState(world, new BlockPos(i + 5, j, k + 2), Blocks.BED, 8);
      this.setBlockState(world, new BlockPos(i + 5, j, k + 1), Blocks.BED, 0);
      this.setBlockState(world, new BlockPos(i + 4, j, k + 2), Blocks.BED, 8);
      this.setBlockState(world, new BlockPos(i + 4, j, k + 1), Blocks.BED, 0);
      this.setBlockToAir(world, new BlockPos(i + 6, j, k + 3));
      this.setBlockToAir(world, new BlockPos(i + 6, j + 1, k + 3));
      this.addChest(world, random, i + 4, j, k + 4);
      this.addChest(world, random, i + 4, j, k + 5);
      this.addChest(world, random, i + 4, j, k + 7);
      this.addChest(world, random, i + 4, j, k + 8);
      this.addChest(world, random, i + 4, j + 1, k + 4);
      this.addChest(world, random, i + 4, j + 1, k + 5);
      this.addChest(world, random, i + 4, j + 1, k + 7);
      this.addChest(world, random, i + 4, j + 1, k + 8);
      this.addChest(world, random, i + 8, j, k + 4);
      this.addChest(world, random, i + 8, j, k + 5);
      this.addChest(world, random, i + 8, j, k + 7);
      this.addChest(world, random, i + 8, j, k + 8);
      this.addChest(world, random, i + 8, j + 1, k + 4);
      this.addChest(world, random, i + 8, j + 1, k + 5);
      this.addChest(world, random, i + 8, j + 1, k + 7);
      this.addChest(world, random, i + 8, j + 1, k + 8);
      this.setBlockToAir(world, new BlockPos(i - 3, j, k - 1));
      this.setBlockToAir(world, new BlockPos(i - 3, j + 1, k - 1));
      this.setBlockState(world, new BlockPos(i - 6, j, k), Blocks.ENCHANTING_TABLE, 0);
      this.setBlockState(world, new BlockPos(i - 6, j, k + 2), Blocks.BOOKSHELF, 0);
      this.setBlockState(world, new BlockPos(i - 7, j, k + 2), Blocks.BOOKSHELF, 0);
      this.setBlockState(world, new BlockPos(i - 8, j, k + 2), Blocks.BOOKSHELF, 0);
      this.setBlockState(world, new BlockPos(i - 6, j + 1, k + 2), Blocks.BOOKSHELF, 0);
      this.setBlockState(world, new BlockPos(i - 7, j + 1, k + 2), Blocks.BOOKSHELF, 0);
      this.setBlockState(world, new BlockPos(i - 8, j + 1, k + 2), Blocks.BOOKSHELF, 0);
      this.setBlockState(world, new BlockPos(i - 6, j, k - 2), Blocks.BOOKSHELF, 0);
      this.setBlockState(world, new BlockPos(i - 7, j, k - 2), Blocks.BOOKSHELF, 0);
      this.setBlockState(world, new BlockPos(i - 8, j, k - 2), Blocks.BOOKSHELF, 0);
      this.setBlockState(world, new BlockPos(i - 6, j + 1, k - 2), Blocks.BOOKSHELF, 0);
      this.setBlockState(world, new BlockPos(i - 7, j + 1, k - 2), Blocks.BOOKSHELF, 0);
      this.setBlockState(world, new BlockPos(i - 8, j + 1, k - 2), Blocks.BOOKSHELF, 0);
      this.setBlockState(world, new BlockPos(i - 8, j, k - 1), Blocks.BOOKSHELF, 0);
      this.setBlockState(world, new BlockPos(i - 8, j, k), Blocks.BOOKSHELF, 0);
      this.setBlockState(world, new BlockPos(i - 8, j, k + 1), Blocks.BOOKSHELF, 0);
      this.setBlockState(world, new BlockPos(i - 5, j, k + 2), Blocks.BIRCH_STAIRS, 6);
      this.setBlockState(world, new BlockPos(i - 4, j, k + 2), Blocks.BIRCH_STAIRS, 6);
      this.setBlockToAir(world, new BlockPos(i - 2, j, k + 3));
      this.setBlockToAir(world, new BlockPos(i - 2, j + 1, k + 3));
      this.setBlockState(world, new BlockPos(i + 2, j, k + 5), Blocks.ANVIL, 0);
      this.setBlockState(world, new BlockPos(i + 2, j, k + 6), Blocks.FURNACE, 0);
      this.setBlockState(world, new BlockPos(i + 2, j + 1, k + 6), Blocks.FURNACE, 0);
      this.setBlockState(world, new BlockPos(i + 2, j, k + 7), Blocks.CRAFTING_TABLE, 0);
      this.setBlockState(world, new BlockPos(i + 2, j, k + 8), Blocks.FURNACE, 0);
      this.setBlockState(world, new BlockPos(i + 2, j + 1, k + 8), Blocks.FURNACE, 0);
      this.setBlockState(world, new BlockPos(i, j, k + 9), Blocks.OAK_DOOR, 1);
      this.setBlockState(world, new BlockPos(i, j + 1, k + 9), Blocks.OAK_DOOR, 9);
      this.setBlockState(world, new BlockPos(i + 1, j, k + 4), Blocks.OAK_STAIRS, 0);
      this.setBlockState(world, new BlockPos(i + 2, j, k + 4), Blocks.OAK_STAIRS, 3);
      this.setBlockState(world, new BlockPos(i + 2, j + 1, k + 4), Blocks.OAK_STAIRS, 0);
      this.setBlockState(world, new BlockPos(i + 2, j + 2, k + 5), Blocks.OAK_STAIRS, 2);
      this.setBlockState(world, new BlockPos(i + 1, j + 3, k + 5), Blocks.OAK_STAIRS, 1);
      this.setBlockToAir(world, new BlockPos(i + 2, j + 3, k + 5));
      this.setBlockToAir(world, new BlockPos(i + 2, j + 3, k + 4));
      this.setBlockToAir(world, new BlockPos(i + 1, j + 3, k + 4));
      this.setBlockState(world, new BlockPos(i, j + 2, k - 4), Blocks.WOOL, 6);
      this.setBlockState(world, new BlockPos(i + 1, j + 3, k - 4), Blocks.WOOL, 6);
      this.setBlockState(world, new BlockPos(i - 1, j + 3, k - 4), Blocks.WOOL, 6);
      this.setBlockState(world, new BlockPos(i + 2, j + 4, k - 4), Blocks.WOOL, 6);
      this.setBlockState(world, new BlockPos(i - 2, j + 4, k - 4), Blocks.WOOL, 6);
      this.setBlockState(world, new BlockPos(i + 3, j + 5, k - 4), Blocks.WOOL, 6);
      this.setBlockState(world, new BlockPos(i - 3, j + 5, k - 4), Blocks.WOOL, 6);
      this.setBlockState(world, new BlockPos(i + 3, j + 6, k - 4), Blocks.WOOL, 6);
      this.setBlockState(world, new BlockPos(i - 3, j + 6, k - 4), Blocks.WOOL, 6);
      this.setBlockState(world, new BlockPos(i + 2, j + 7, k - 4), Blocks.WOOL, 6);
      this.setBlockState(world, new BlockPos(i - 2, j + 7, k - 4), Blocks.WOOL, 6);
      this.setBlockState(world, new BlockPos(i + 1, j + 7, k - 4), Blocks.WOOL, 6);
      this.setBlockState(world, new BlockPos(i - 1, j + 7, k - 4), Blocks.WOOL, 6);
      this.setBlockState(world, new BlockPos(i, j + 6, k - 4), Blocks.WOOL, 6);
      this.setBlockState(world, new BlockPos(i - 3, j, k + 7), Blocks.OAK_DOOR, 2);
      this.setBlockState(world, new BlockPos(i - 3, j + 1, k + 7), Blocks.OAK_DOOR, 9);
      this.setBlockState(world, new BlockPos(i - 6, j, k + 9), Blocks.OAK_DOOR, 1);
      this.setBlockState(world, new BlockPos(i - 6, j + 1, k + 9), Blocks.OAK_DOOR, 9);
      this.boxReplace(world, Blocks.PLANKS, 0, Blocks.GLASS, 0, i - 9, j, k + 4, i - 4, j + 2, k + 9, "all");
      this.setBlockToAir(world, new BlockPos(i, j + 4, k + 3));
      this.setBlockToAir(world, new BlockPos(i, j + 5, k + 3));
      this.setBlockToAir(world, new BlockPos(i + 3, j + 4, k));
      this.setBlockToAir(world, new BlockPos(i + 3, j + 5, k));
      this.setBlockToAir(world, new BlockPos(i - 3, j + 4, k));
      this.setBlockToAir(world, new BlockPos(i - 3, j + 5, k));
      this.setBlockToAir(world, new BlockPos(i + 3, j + 4, k + 6));
      this.setBlockToAir(world, new BlockPos(i + 3, j + 5, k + 6));
      this.setBlockToAir(world, new BlockPos(i - 3, j + 4, k + 6));
      this.setBlockToAir(world, new BlockPos(i - 3, j + 5, k + 6));
      return false;
   }

   public void genRoom(World world, int i, int j, int k) {
      this.genBox(world, Blocks.PLANKS, 0, 0, i - 3, j - 1, k - 3, i + 3, j + 3, k + 3);
      this.genBoxAir(world, 0, i - 2, j, k - 2, i + 2, j + 2, k + 2);

      for (int a = -1; a <= 3; a++) {
         this.setBlockState(world, new BlockPos(i - 3, j + a, k - 3), Blocks.LOG, 0);
         this.setBlockState(world, new BlockPos(i - 3, j + a, k + 3), Blocks.LOG, 0);
         this.setBlockState(world, new BlockPos(i + 3, j + a, k - 3), Blocks.LOG, 0);
         this.setBlockState(world, new BlockPos(i + 3, j + a, k + 3), Blocks.LOG, 0);
      }

      for (int a = -2; a < 3; a++) {
         this.setBlockState(world, new BlockPos(i - 3, j + 3, k + a), Blocks.LOG, 8);
         this.setBlockState(world, new BlockPos(i + 3, j + 3, k + a), Blocks.LOG, 8);
         this.setBlockState(world, new BlockPos(i + a, j + 3, k - 3), Blocks.LOG, 4);
         this.setBlockState(world, new BlockPos(i + a, j + 3, k + 3), Blocks.LOG, 4);
      }

      this.setBlockState(world, new BlockPos(i + 2, j + 2, k), Blocks.TORCH, 2);
      this.setBlockState(world, new BlockPos(i, j + 2, k + 2), Blocks.TORCH, 4);
      this.setBlockState(world, new BlockPos(i - 2, j + 2, k), Blocks.TORCH, 1);
      this.setBlockState(world, new BlockPos(i, j + 2, k - 2), Blocks.TORCH, 3);
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
      }
   }
}
