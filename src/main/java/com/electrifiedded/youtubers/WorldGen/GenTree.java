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

public class GenTree extends GenYoutuberStructure {
   @Override
   public boolean generate(World world, Random random, BlockPos pos) {
      int i = pos.getX();
      int j = pos.getY();
      int k = pos.getZ();
      int treehight = 15;

      for (int a = 0; a < 8; a++) {
         this.Generate_Root(world, random, i, j, k, 10, random.nextDouble() * Math.PI * 2.0, 2.0F * random.nextFloat() + 1.0F);
      }

      for (int a = 0; a < 16; a++) {
         double c = 2.0 * random.nextDouble() + 1.0;
         double b = random.nextDouble() * 8.0 + 9.0 - c;
         this.Generate_branch(world, random, i, j, k, 10 + random.nextInt(5), random.nextDouble() * Math.PI * 2.0, c, b);
      }

      this.GenCircle(world, random, Blocks.LOG, 0, i, j - 1, k, 3.0F, 0.0F, 'y');

      for (int a = 0; a < treehight; a++) {
         this.GenCircle(world, random, Blocks.LOG, 0, i, j + a, k, 3.0F, 2.0F, 'y');
         this.GenCircle(world, random, Blocks.AIR, 0, i, j + a, k, 2.0F, 0.0F, 'y');
      }

      for (int a = 0; a < 4; a++) {
         this.addChest(world, random, i + 2, j + a, k);
         this.addChest(world, random, i - 2, j + a, k);
         this.addChest(world, random, i, j + a, k + 2);
      }

      this.Genleaves(world, random, i, j + treehight + 4, k, 4.0);
      this.setBlockState(world, new BlockPos(i, j, k - 2), Blocks.OAK_DOOR, 1);
      this.setBlockState(world, new BlockPos(i, j + 1, k - 2), Blocks.OAK_DOOR, 9);
      this.setBlockState(world, new BlockPos(i, j, k - 3), Blocks.AIR, 0);
      this.setBlockState(world, new BlockPos(i, j + 1, k - 3), Blocks.AIR, 0);
      this.setBlockState(world, new BlockPos(i, j, k - 4), Blocks.AIR, 0);
      this.setBlockState(world, new BlockPos(i, j + 1, k - 4), Blocks.AIR, 0);
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

   void Generate_Root(World world, Random random, int i, int j, int k, int h, double angle, float r) {
      for (int a = 0; a < h; a++) {
         double r1 = 10 / (a + 3);
         this.GenCircle(world, random, Blocks.LOG, 0, (float)(i + r1 * Math.sin(angle)), j + a, (float)(k + r1 * Math.cos(angle)), r, r - 1.0F, 'y');
      }
   }

   void Generate_branch(World world, Random random, int i, int j, int k, int h, double angle, double angle2, double r2) {
      for (int a = 0; a < h; a++) {
         double r1 = Math.pow(2.0, a - h);
         this.GenCircle(world, random, Blocks.LOG, 0, (float)(i + r1 * Math.sin(angle)), j + a, (float)(k + r1 * Math.cos(angle)), 1.0F, 0.0F, 'y');
      }

      double b = 0.0;
      double r = 0.0;

      for (double a = h; a < h + r2 * angle2; a += 0.25) {
         double r1 = Math.pow(2.0, (a - h) / angle2);
         if (r1 < r2) {
            b = a;
            r = r1;
            this.GenCircle(
               world, random, Blocks.LOG, 0, (float)(i + r1 * Math.sin(angle)), (float)(j + a), (float)(k + r1 * Math.cos(angle)), 0.9F, 0.0F, 'y'
            );
         }
      }

      this.Genleaves(world, random, (int)(i + r * Math.sin(angle)), (int)(j + b), (int)(k + r * Math.cos(angle)), 4.0);
   }

   void Genleaves(World world, Random random, int i, int j, int k, double r) {
      this.setBlockState(world, new BlockPos(i, j, k), Blocks.LEAVES, 4);

      for (float a = i - (int)(r + 4.0); a <= i + (int)(r + 4.0); a++) {
         for (float b = j - (int)(r + 4.0); b <= j + (int)(r + 4.0); b++) {
            for (float c = k - (int)(r + 4.0); c <= k + (int)(r + 4.0); c++) {
               float d = (float)Math.sqrt(Math.pow(a - i, 2.0) + Math.pow((b - j) * 2.0F, 2.0) + Math.pow(c - k, 2.0));
               if (d <= r && this.getBlockState(world, new BlockPos((double)a, (double)b, (double)c)) == Blocks.AIR) {
                  this.setBlockState(world, new BlockPos((double)a, (double)b, (double)c), Blocks.LEAVES, 4);
               }
            }
         }
      }
   }
}
