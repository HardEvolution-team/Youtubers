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

public class GenRocket extends GenYoutuberStructure {
   @Override
   public boolean generate(World world, Random random, BlockPos pos) {
      int i = pos.getX();
      int j = pos.getY();
      int k = pos.getZ();

      for (int a = 0; a < 20; a++) {
         float r = -(a * a / 64.0F) + a / 8.0F + 3.0F;
         float r2 = 0.0F;
         if (a < 8 && a > 1) {
            r2 = r - 1.0F;
         }

         this.GenCircle(world, random, Blocks.WOOL, 3, i, j + a, k, r, r2, 'y');
      }

      this.boxReplace(world, Blocks.WOOL, 3, Blocks.WOOL, 5, i - 3, j + 12, k - 3, i + 3, j + 20, k + 3, "all");
      this.setBlockState(world, new BlockPos(i, j + 2, k - 3), Blocks.OAK_DOOR, 1);
      this.setBlockState(world, new BlockPos(i, j + 3, k - 3), Blocks.OAK_DOOR, 9);
      this.setBlockState(world, new BlockPos(i, j, k - 5), Blocks.WOOL, 15);
      this.setBlockState(world, new BlockPos(i, j + 1, k - 4), Blocks.WOOL, 15);
      this.genBox(world, Blocks.WOOL, 4, 0, i + 3, j + 1, k + 3, i + 3, j + 4, k + 3);
      this.genBox(world, Blocks.WOOL, 4, 0, i + 3, j + 1, k - 3, i + 3, j + 4, k - 3);
      this.genBox(world, Blocks.WOOL, 4, 0, i - 3, j + 1, k + 3, i - 3, j + 4, k + 3);
      this.genBox(world, Blocks.WOOL, 4, 0, i - 3, j + 1, k - 3, i - 3, j + 4, k - 3);
      this.genBox(world, Blocks.WOOL, 4, 0, i + 4, j, k + 4, i + 4, j + 3, k + 4);
      this.genBox(world, Blocks.WOOL, 4, 0, i + 4, j, k - 4, i + 4, j + 3, k - 4);
      this.genBox(world, Blocks.WOOL, 4, 0, i - 4, j, k + 4, i - 4, j + 3, k + 4);
      this.genBox(world, Blocks.WOOL, 4, 0, i - 4, j, k - 4, i - 4, j + 3, k - 4);
      this.genBox(world, Blocks.AIR, 0, 0, i - 1, j + 9, k - 1, i + 1, j + 11, k + 1);
      this.setBlockToAir(world, new BlockPos(i, j + 8, k));
      this.setBlockState(world, new BlockPos(i, j + 11, k + 1), Blocks.LEVER, 15);
      this.setBlockState(world, new BlockPos(i, j + 10, k + 1), Blocks.NETHER_BRICK_STAIRS, 2);
      this.setBlockState(world, new BlockPos(i, j + 10, k - 1), Blocks.TORCH, 3);
      this.setBlockState(world, new BlockPos(i + 2, j + 6, k), Blocks.TORCH, 2);
      this.setBlockState(world, new BlockPos(i, j + 6, k + 2), Blocks.TORCH, 4);
      this.setBlockState(world, new BlockPos(i - 2, j + 6, k), Blocks.TORCH, 1);
      this.setBlockState(world, new BlockPos(i, j + 6, k - 2), Blocks.TORCH, 3);

      for (int a = 2; a < 6; a++) {
         this.addChest(world, random, i + 2, j + a, k);
         this.addChest(world, random, i - 2, j + a, k);
         this.addChest(world, random, i, j + a, k + 2);
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
      }
   }

   @Override
   public BlockPos getSpawnLoc(BlockPos pos) {
      return pos.up(2);
   }
}
