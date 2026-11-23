package com.electrifiedded.youtubers.WorldGen;

import com.electrifiedded.archaiclib.world.StagedWorld;
import com.electrifiedded.youtubers.YTItems;
import net.minecraft.util.math.BlockPos;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.world.World;

public class GenCrainerCastle extends GenYoutuberStructure {
   @Override
   public boolean generate(World world, Random random, BlockPos pos) {
      int i = pos.getX();
      int j = pos.getY();
      int k = pos.getZ();
      this.genBox(world, Blocks.AIR, 0, 0, i - 12, j, k - 4, i + 12, j + 11, k + 16);
      this.genBox(world, Blocks.DIRT, 0, 0, i - 12, j - 1, k - 4, i + 12, j - 1, k + 16);
      this.genTower(world, random, i + 8, j, k);
      this.genTower(world, random, i - 8, j, k);
      this.genBox(world, Blocks.STONEBRICK, 0, 0, i + 5, j - 1, k - 4, i + 5, j + 4, k - 2);
      this.genBox(world, Blocks.STONEBRICK, 0, 0, i - 5, j - 1, k - 4, i - 5, j + 4, k - 2);
      this.genBox(world, Blocks.STONEBRICK, 0, 0, i - 4, j - 1, k - 4, i + 4, j + 5, k - 4);
      this.genBox(world, Blocks.STONEBRICK, 0, 0, i - 4, j + 5, k - 4, i - 4, j + 5, k + 4);
      this.genBox(world, Blocks.STONEBRICK, 0, 0, i - 7, j + 5, k + 4, i - 4, j + 5, k + 4);
      this.genBox(world, Blocks.STONEBRICK, 0, 0, i + 4, j + 5, k - 4, i + 4, j + 5, k + 4);
      this.genBox(world, Blocks.STONEBRICK, 0, 0, i + 4, j + 5, k + 4, i + 7, j + 5, k + 4);
      this.genBox(world, Blocks.STONEBRICK, 0, 0, i + 10, j - 1, k + 5, i + 10, j + 5, k + 5);
      this.genBox(world, Blocks.STONEBRICK, 0, 0, i + 11, j - 1, k + 6, i + 11, j + 5, k + 6);
      this.genBox(world, Blocks.STONEBRICK, 0, 0, i + 12, j - 1, k + 7, i + 12, j + 5, k + 13);
      this.genBox(world, Blocks.STONEBRICK, 0, 0, i + 11, j - 1, k + 14, i + 11, j + 5, k + 14);
      this.genBox(world, Blocks.STONEBRICK, 0, 0, i + 10, j - 1, k + 15, i + 10, j + 5, k + 15);
      this.genBox(world, Blocks.STONEBRICK, 0, 0, i - 10, j - 1, k + 5, i - 10, j + 5, k + 5);
      this.genBox(world, Blocks.STONEBRICK, 0, 0, i - 11, j - 1, k + 6, i - 11, j + 5, k + 6);
      this.genBox(world, Blocks.STONEBRICK, 0, 0, i - 12, j - 1, k + 7, i - 12, j + 5, k + 13);
      this.genBox(world, Blocks.STONEBRICK, 0, 0, i - 11, j - 1, k + 14, i - 11, j + 5, k + 14);
      this.genBox(world, Blocks.STONEBRICK, 0, 0, i - 10, j - 1, k + 15, i - 10, j + 5, k + 15);
      this.genBox(world, Blocks.STONEBRICK, 0, 0, i - 9, j - 1, k + 16, i + 9, j + 5, k + 16);
      this.fillFloor(world, random, i, j - 1, k);
      this.fillRoof(world, random, i, j + 5, k, false);
      this.fillCeil(world, random, i, j + 4, k);
      this.genBox(world, Blocks.AIR, 0, 0, i - 1, j, k - 4, i + 1, j + 3, k - 4);
      this.setBlockState(world, new BlockPos(i + 1, j + 3, k - 4), Blocks.STONE_BRICK_STAIRS, 4);
      this.setBlockState(world, new BlockPos(i - 1, j + 3, k - 4), Blocks.STONE_BRICK_STAIRS, 5);
      this.setBlockState(world, new BlockPos(i, j - 1, k - 4), Blocks.DOUBLE_STONE_SLAB, 0);
      this.setBlockState(world, new BlockPos(i, j - 1, k - 3), Blocks.DOUBLE_STONE_SLAB, 0);
      this.setBlockState(world, new BlockPos(i + 1, j - 1, k - 4), Blocks.COBBLESTONE, 0);
      this.setBlockState(world, new BlockPos(i - 1, j - 1, k - 4), Blocks.COBBLESTONE, 0);
      this.genBox(world, Blocks.AIR, 0, 0, i + 12, j, k + 9, i + 12, j + 3, k + 11);
      this.setBlockState(world, new BlockPos(i + 12, j + 3, k + 9), Blocks.STONE_BRICK_STAIRS, 7);
      this.setBlockState(world, new BlockPos(i + 12, j + 3, k + 11), Blocks.STONE_BRICK_STAIRS, 6);
      this.setBlockState(world, new BlockPos(i + 12, j - 1, k + 10), Blocks.DOUBLE_STONE_SLAB, 0);
      this.setBlockState(world, new BlockPos(i + 11, j - 1, k + 10), Blocks.DOUBLE_STONE_SLAB, 0);
      this.setBlockState(world, new BlockPos(i + 12, j - 1, k + 9), Blocks.COBBLESTONE, 0);
      this.setBlockState(world, new BlockPos(i + 12, j - 1, k + 11), Blocks.COBBLESTONE, 0);

      for (int a = 2; a < 6; a++) {
         this.setBlockState(world, new BlockPos(i + a, j - 1, k), Blocks.DOUBLE_STONE_SLAB, 0);
         this.setBlockToAir(world, new BlockPos(i + a, j, k));
         this.setBlockToAir(world, new BlockPos(i + a, j + 1, k));
         this.setBlockState(world, new BlockPos(i - a, j - 1, k), Blocks.DOUBLE_STONE_SLAB, 0);
         this.setBlockToAir(world, new BlockPos(i - a, j, k));
         this.setBlockToAir(world, new BlockPos(i - a, j + 1, k));
      }

      for (int a = 0; a < 7; a++) {
         this.setBlockState(world, new BlockPos(i + 11, j + a, k), Blocks.STONEBRICK, 0);
         this.setBlockState(world, new BlockPos(i + 10, j + a, k), Blocks.LADDER, 4);
         this.setBlockState(world, new BlockPos(i - 11, j + a, k), Blocks.STONEBRICK, 0);
         this.setBlockState(world, new BlockPos(i - 10, j + a, k), Blocks.LADDER, 5);
         this.setBlockState(world, new BlockPos(i - 6, j + a, k + 15), Blocks.STONEBRICK, 0);
         this.setBlockState(world, new BlockPos(i - 6, j + a, k + 14), Blocks.LADDER, 2);
      }

      this.setBlockState(world, new BlockPos(i - 11, j, k + 7), Blocks.FURNACE, 5);
      this.setBlockState(world, new BlockPos(i - 11, j, k + 8), Blocks.FURNACE, 5);
      this.setBlockState(world, new BlockPos(i - 11, j, k + 9), Blocks.FURNACE, 5);
      this.setBlockState(world, new BlockPos(i - 11, j, k + 10), Blocks.FURNACE, 5);
      this.addChest(world, random, i - 11, j, k + 11);
      this.addChest(world, random, i - 11, j, k + 12);
      this.addChest(world, random, i - 11, j + 1, k + 11);
      this.addChest(world, random, i - 11, j + 1, k + 12);
      this.addChest(world, random, i - 11, j + 2, k + 11);
      this.addChest(world, random, i - 11, j + 2, k + 12);
      this.setBlockState(world, new BlockPos(i - 11, j, k + 13), Blocks.BED, 9);
      this.setBlockState(world, new BlockPos(i - 10, j, k + 13), Blocks.BED, 1);
      return false;
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

   private void fillRoof(World world, Random random, int i, int j, int k, boolean Diag) {
      boolean found = false;
      int dist = 0;

      for (int a = 0; a < 7; a++) {
         if (!found) {
            if (this.getBlockState(world, new BlockPos(i + a, j, k)) == Blocks.STONEBRICK) {
               dist = a;
               found = true;
            }

            if (this.getBlockState(world, new BlockPos(i - a, j, k)) == Blocks.STONEBRICK) {
               dist = a;
               found = true;
            }

            if (this.getBlockState(world, new BlockPos(i, j, k + a)) == Blocks.STONEBRICK) {
               dist = a;
               found = true;
            }

            if (this.getBlockState(world, new BlockPos(i, j, k - a)) == Blocks.STONEBRICK) {
               dist = a;
               found = true;
            }

            if (Diag) {
               if (this.getBlockState(world, new BlockPos(i + a, j, k + a)) == Blocks.STONEBRICK) {
                  dist = a;
                  found = true;
               }

               if (this.getBlockState(world, new BlockPos(i - a, j, k - a)) == Blocks.STONEBRICK) {
                  dist = a;
                  found = true;
               }

               if (this.getBlockState(world, new BlockPos(i - a, j, k + a)) == Blocks.STONEBRICK) {
                  dist = a;
                  found = true;
               }

               if (this.getBlockState(world, new BlockPos(i + a, j, k - a)) == Blocks.STONEBRICK) {
                  dist = a;
                  found = true;
               }
            }
         }
      }

      if (found) {
         Block block = this.getBlockState(world, new BlockPos(i, j + dist, k));
         if (block != Blocks.STONEBRICK) {
            this.setBlockState(world, new BlockPos(i, j + dist, k), Blocks.STONEBRICK, 0);
            this.fillRoof(world, random, i + 1, j, k, false);
            this.fillRoof(world, random, i - 1, j, k, false);
            this.fillRoof(world, random, i, j, k + 1, false);
            this.fillRoof(world, random, i, j, k - 1, false);
         }
      } else if (!Diag) {
         this.fillRoof(world, random, i, j, k, true);
      }
   }

   private void fillCeil(World world, Random random, int i, int j, int k) {
      Block block = this.getBlockState(world, new BlockPos(i, j, k));
      if (block != Blocks.STONEBRICK) {
         this.setBlockState(world, new BlockPos(i, j, k), Blocks.STONEBRICK, 0);
         if (this.getBlockState(world, new BlockPos(i, j + 1, k)) != Blocks.STONEBRICK && random.nextInt(8) == 0) {
            this.setBlockState(world, new BlockPos(i, j + 1, k), Blocks.TORCH, 0);
         }

         this.fillCeil(world, random, i + 1, j, k);
         this.fillCeil(world, random, i - 1, j, k);
         this.fillCeil(world, random, i, j, k + 1);
         this.fillCeil(world, random, i, j, k - 1);
      }
   }

   private void fillFloor(World world, Random random, int i, int j, int k) {
      Block block = this.getBlockState(world, new BlockPos(i, j, k));
      if (block != Blocks.STONEBRICK && block != Blocks.COBBLESTONE && block != Blocks.DOUBLE_STONE_SLAB) {
         if (this.getBlockState(world, new BlockPos(i + 1, j, k)) == Blocks.STONEBRICK) {
            this.setBlockState(world, new BlockPos(i, j, k), Blocks.COBBLESTONE, 0);
         } else if (this.getBlockState(world, new BlockPos(i - 1, j, k)) == Blocks.STONEBRICK) {
            this.setBlockState(world, new BlockPos(i, j, k), Blocks.COBBLESTONE, 0);
         } else if (this.getBlockState(world, new BlockPos(i, j, k + 1)) == Blocks.STONEBRICK) {
            this.setBlockState(world, new BlockPos(i, j, k), Blocks.COBBLESTONE, 0);
         } else if (this.getBlockState(world, new BlockPos(i, j, k - 1)) == Blocks.STONEBRICK) {
            this.setBlockState(world, new BlockPos(i, j, k), Blocks.COBBLESTONE, 0);
         } else {
            this.setBlockState(world, new BlockPos(i, j, k), Blocks.DOUBLE_STONE_SLAB, 0);
            if (random.nextInt(8) == 0) {
               this.setBlockState(world, new BlockPos(i, j + 1, k), Blocks.TORCH, 0);
            }
         }

         this.fillFloor(world, random, i + 1, j, k);
         this.fillFloor(world, random, i - 1, j, k);
         this.fillFloor(world, random, i, j, k + 1);
         this.fillFloor(world, random, i, j, k - 1);
      }
   }

   private void genTower(World world, Random random, int i, int j, int k) {
      this.GenCircle(world, random, Blocks.STONEBRICK, 0, i, j - 1, k, 4.15F, 3.5F, 'y');
      this.GenCircle(world, random, Blocks.STONEBRICK, 0, i, j, k, 4.15F, 3.5F, 'y');
      this.GenCircle(world, random, Blocks.STONEBRICK, 0, i, j + 1, k, 4.15F, 3.5F, 'y');
      this.GenCircle(world, random, Blocks.STONEBRICK, 0, i, j + 2, k, 4.15F, 3.5F, 'y');
      this.GenCircle(world, random, Blocks.STONEBRICK, 0, i, j + 3, k, 4.15F, 3.5F, 'y');
      this.GenCircle(world, random, Blocks.STONEBRICK, 0, i, j + 4, k, 4.15F, 3.5F, 'y');
      this.GenCircle(world, random, Blocks.STONEBRICK, 0, i, j + 5, k, 4.15F, 3.5F, 'y');
      this.fillFloor(world, random, i, j - 1, k);
      this.fillCeil(world, random, i, j + 4, k);
      this.fillRoof(world, random, i, j + 5, k, false);
   }
}
