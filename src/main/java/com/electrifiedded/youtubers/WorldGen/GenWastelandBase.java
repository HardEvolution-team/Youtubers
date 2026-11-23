package com.electrifiedded.youtubers.WorldGen;

import com.electrifiedded.archaiclib.world.StagedWorld;
import net.minecraft.util.math.BlockPos;
import com.electrifiedded.youtubers.RandUtil.RandomItemStack;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.WeightedRandom;
import net.minecraft.world.World;

public class GenWastelandBase extends GenYoutuberStructure {
   boolean ChestType;
   public static List<RandomItemStack> Materials = new ArrayList<>();

   @Override
   public boolean hasDemo() {
      return true;
   }

   public void genDemo(World world, Random random, int i, int j, int k) {
      this.generateWasteland(world, random, i, j, k, 9.0);
      this.generateBase(world, random, i, j, k);
   }

   @Override
   public boolean generate(World world, Random random, BlockPos pos) {
      this.generateWasteland(world, random, pos.getX(), pos.getY(), pos.getZ(), 13.0);
      this.generateBase(world, random, pos.getX(), pos.getY(), pos.getZ());
      return false;
   }

   private void generateBase(World world, Random random, int i, int j, int k) {
      this.ChestType = true;
      int size = 8;
      this.genBox(world, Blocks.SANDSTONE, 0, 0, i - size - 1, j - 1, k - size - 1, i + size + 1, j - 1, k + size + 1);
      this.genBoxAir(world, 0, i - size, j, k - size, i + size, j + size, k + size);
      this.genBox(world, Blocks.SANDSTONE, 2, 1, i - size, j, k - size, i + size, j + 4, k + size);

      for (int a = 0; a < size; a++) {
         this.genBox(world, Blocks.SANDSTONE, 2, 1, i - (size - a), j + a + 2, k - (size - a), i + (size - a), j + a + 2, k + (size - a));
      }

      this.add_tower(world, i + (size - 1), j, k + (size - 1));
      this.add_tower(world, i - (size - 1), j, k + (size - 1));
      this.add_tower(world, i + (size - 1), j, k - (size - 1));
      this.add_tower(world, i - (size - 1), j, k - (size - 1));
      this.genBoxAir(world, 0, i - size + 1, j, k - size + 1, i + size - 1, j + 2, k + size - 1);
      this.genBox(world, Blocks.SANDSTONE, 2, 0, i - 4, j + 3, k + 6, i + 4, j + 4, k + 7);
      this.genBox(world, Blocks.SANDSTONE, 2, 0, i - 4, j + 3, k - 7, i + 4, j + 4, k - 6);
      this.genBox(world, Blocks.SANDSTONE, 2, 0, i + 6, j + 3, k - 4, i + 7, j + 4, k + 4);
      this.genBox(world, Blocks.SANDSTONE, 2, 0, i - 7, j + 3, k - 4, i - 6, j + 4, k + 4);

      for (int a = 0; a < 9; a++) {
         this.ChestType = a % 2 == 0;

         for (int b = 0; b < 3; b++) {
            for (int c = 0; c < 2; c++) {
               this.add_chest_Mat(world, random, i + 4 - a, j + b, k + 6 + c);
            }
         }
      }

      for (int a = 0; a < 2; a++) {
         this.ChestType = a % 2 == 0;

         for (int b = 0; b < 3; b++) {
            for (int c = 0; c < 2; c++) {
               this.add_chest_Mat(world, random, i + 6 + c, j + b, k + 4 - a);
               this.add_chest_Mat(world, random, i + 6 + c, j + b, k - 4 + a);
            }
         }
      }

      for (int a = 0; a < 5; a++) {
         this.setBlockState(world, new BlockPos(i + 2, j - 1, k - 2 + a), Blocks.SANDSTONE_STAIRS, 0);
         this.setBlockState(world, new BlockPos(i - 2, j - 1, k - 2 + a), Blocks.SANDSTONE_STAIRS, 1);
         this.setBlockState(world, new BlockPos(i - 2 + a, j - 1, k + 2), Blocks.SANDSTONE_STAIRS, 2);
         this.setBlockState(world, new BlockPos(i - 2 + a, j - 1, k - 2), Blocks.SANDSTONE_STAIRS, 3);
      }

      this.genBox(world, Blocks.AIR, 2, 0, i - 1, j - 1, k - 1, i + 1, j - 1, k + 1);
      this.genBox(world, Blocks.SANDSTONE, 2, 0, i - 2, j - 2, k - 2, i + 2, j - 2, k + 2);
      this.setBlockState(world, new BlockPos(i + 5, j + 4, k + 5), Blocks.GLOWSTONE, 0);
      this.setBlockState(world, new BlockPos(i + 5, j + 4, k - 5), Blocks.GLOWSTONE, 0);
      this.setBlockState(world, new BlockPos(i - 5, j + 4, k + 5), Blocks.GLOWSTONE, 0);
      this.setBlockState(world, new BlockPos(i - 5, j + 4, k - 5), Blocks.GLOWSTONE, 0);
      this.setBlockState(world, new BlockPos(i + 7, j + 1, k), Blocks.DROPPER, 5);
      this.setBlockState(world, new BlockPos(i + 7, j, k - 1), Blocks.DROPPER, 5);
      this.setBlockState(world, new BlockPos(i + 7, j, k + 1), Blocks.DROPPER, 5);
      this.setBlockState(world, new BlockPos(i + 6, j + 1, k), Blocks.WOODEN_BUTTON, 2);
      this.setBlockState(world, new BlockPos(i + 6, j, k - 1), Blocks.WOODEN_BUTTON, 2);
      this.setBlockState(world, new BlockPos(i + 6, j, k + 1), Blocks.WOODEN_BUTTON, 2);
      this.setBlockState(world, new BlockPos(i + 7, j + 2, k), Blocks.ENDER_CHEST, 0);
      this.setBlockState(world, new BlockPos(i + 7, j + 3, k), Blocks.SANDSTONE_STAIRS, 5);
      this.setBlockState(world, new BlockPos(i + 7, j + 2, k + 1), Blocks.HOPPER, 0);
      this.setBlockState(world, new BlockPos(i + 7, j + 2, k - 1), Blocks.HOPPER, 0);
      this.setBlockState(world, new BlockPos(i + 6, j - 1, k - 1), Blocks.FURNACE, 0);
      this.setBlockState(world, new BlockPos(i + 6, j - 1, k - 2), Blocks.FURNACE, 0);
      this.setBlockState(world, new BlockPos(i + 6, j - 1, k + 1), Blocks.FURNACE, 0);
      this.setBlockState(world, new BlockPos(i + 6, j - 1, k + 2), Blocks.FURNACE, 0);
      this.setBlockState(world, new BlockPos(i + 4, j, k + 3), Blocks.BED, 8);
      this.setBlockState(world, new BlockPos(i + 4, j, k + 2), Blocks.BED, 0);
      this.setBlockState(world, new BlockPos(i + 4, j, k - 3), Blocks.BED, 10);
      this.setBlockState(world, new BlockPos(i + 4, j, k - 2), Blocks.BED, 2);
      this.setBlockState(world, new BlockPos(i + 3, j, k + 4), Blocks.BED, 11);
      this.setBlockState(world, new BlockPos(i + 2, j, k + 4), Blocks.BED, 3);
      this.setBlockState(world, new BlockPos(i + 3, j, k - 4), Blocks.BED, 11);
      this.setBlockState(world, new BlockPos(i + 2, j, k - 4), Blocks.BED, 3);
      this.genBox(world, Blocks.AIR, 2, 0, i - 1, j, k - 8, i + 1, j + 2, k - 8);
      this.setBlockState(world, new BlockPos(i + 1, j + 2, k - 8), Blocks.SANDSTONE_STAIRS, 4);
      this.setBlockState(world, new BlockPos(i - 1, j + 2, k - 8), Blocks.SANDSTONE_STAIRS, 5);
      this.ChestType = true;
      this.add_chest_Mat(world, random, i + 7, j, k + 2);
      this.add_chest_Mat(world, random, i + 7, j + 1, k + 2);
      this.add_chest_Mat(world, random, i + 7, j + 2, k + 2);
      this.add_chest_Mat(world, random, i + 7, j, k - 2);
      this.add_chest_Mat(world, random, i + 7, j + 1, k - 2);
      this.add_chest_Mat(world, random, i + 7, j + 2, k - 2);
   }

   public void add_tower(World world, int i, int j, int k) {
      this.genBox(world, Blocks.SANDSTONE, 2, 1, i - 2, j, k - 2, i + 2, j + 8, k + 2);
      this.genBox(world, Blocks.SANDSTONE, 2, 0, i - 1, j + 8, k - 1, i + 1, j + 9, k + 1);
      this.setBlockState(world, new BlockPos(i + 2, j + 9, k), Blocks.SANDSTONE_STAIRS, 1);
      this.setBlockState(world, new BlockPos(i - 2, j + 9, k), Blocks.SANDSTONE_STAIRS, 0);
      this.setBlockState(world, new BlockPos(i, j + 9, k + 2), Blocks.SANDSTONE_STAIRS, 3);
      this.setBlockState(world, new BlockPos(i, j + 9, k - 2), Blocks.SANDSTONE_STAIRS, 2);
      this.genBoxAir(world, 0, i - 1, j, k - 1, i + 1, j + 8, k + 1);
      this.setBlockState(world, new BlockPos(i, j + 8, k), Blocks.GLOWSTONE, 0);
   }

   public void add_chest_Mat(World world, Random rand, int i, int j, int k) {
      this.add_chest_Base(world, i, j, k);
      TileEntityChest chestTE = (TileEntityChest)StagedWorld.getTileEntity(world, i, j, k);
      if (chestTE != null) {
         ItemStack item = ((RandomItemStack)WeightedRandom.getRandomItem(rand, Materials)).item;

         for (int a = 0; a < 27; a++) {
            if (item == new ItemStack(Blocks.LOG, 64)) {
               switch (rand.nextInt(6)) {
                  case 0:
                     chestTE.setInventorySlotContents(a, new ItemStack(Blocks.LOG, 64, 0));
                     break;
                  case 1:
                     chestTE.setInventorySlotContents(a, new ItemStack(Blocks.LOG, 64, 1));
                     break;
                  case 2:
                     chestTE.setInventorySlotContents(a, new ItemStack(Blocks.LOG, 64, 2));
                     break;
                  case 3:
                     chestTE.setInventorySlotContents(a, new ItemStack(Blocks.LOG, 64, 3));
                     break;
                  case 4:
                     chestTE.setInventorySlotContents(a, new ItemStack(Blocks.LOG2, 64, 0));
                     break;
                  case 5:
                     chestTE.setInventorySlotContents(a, new ItemStack(Blocks.LOG2, 64, 1));
               }
            } else {
               chestTE.setInventorySlotContents(a, item.copy());
            }
         }
      }
   }

   public void add_chest_Base(World world, int i, int j, int k) {
      if (this.ChestType) {
         this.setBlockState(world, new BlockPos(i, j, k), Blocks.CHEST, 0);
      } else {
         this.setBlockState(world, new BlockPos(i, j, k), Blocks.TRAPPED_CHEST, 0);
      }

      if (this.getBlockState(world, new BlockPos(i, j + 1, k)) == Blocks.SANDSTONE) {
         this.setBlockState(world, new BlockPos(i, j + 1, k), Blocks.STONE_SLAB, 9);
      }
   }

   public void generateWasteland(World world, Random random, int i, int j, int k, double r) {
      for (int a = i - (int)(r + 2.0); a <= i + (int)(r + 2.0); a++) {
         for (int b = k - (int)(r + 2.0); b <= k + (int)(r + 2.0); b++) {
            double d = Math.sqrt(Math.pow(a - i, 2.0) + Math.pow(b - k, 2.0));
            if (d <= r) {
               this.remove_vegitation(world, a, b);
               this.remove_NonStone(world, a, b, 0);
            }
         }
      }

      for (int a = 0; a < 3; a++) {
         if (r > 4.0) {
            this.generateWasteland(
               world, random, i + random.nextInt((int)(r * 3.0)) - (int)(r * 1.5), j, k + random.nextInt((int)(r * 3.0)) - (int)(r * 1.5), r - 1.0
            );
         }
      }
   }

   private void Add_Stone(World world, int i, int k, Random random) {
      BlockPos top = this.getTopBlock(world, new BlockPos(i, 5, k));
      top = top.up();

      for (int a = 0; a < 4; a++) {
         if (random.nextInt(6) == 0) {
            if (random.nextInt(20) == 0) {
               if (random.nextInt(2) == 0) {
                  if (random.nextInt(2) == 0) {
                     if (random.nextInt(4) == 0) {
                        if (random.nextInt(2) == 0) {
                           if (random.nextInt(3) == 0) {
                              this.setBlockState(world, new BlockPos(i, top.getY(), k), Blocks.EMERALD_ORE, 0);
                           } else {
                              this.setBlockState(world, new BlockPos(i, top.getY(), k), Blocks.DIAMOND_ORE, 0);
                           }
                        } else {
                           this.setBlockState(world, new BlockPos(i, top.getY(), k), Blocks.GOLD_ORE, 0);
                        }
                     } else if (random.nextBoolean()) {
                        this.setBlockState(world, new BlockPos(i, top.getY(), k), Blocks.REDSTONE_ORE, 0);
                     } else {
                        this.setBlockState(world, new BlockPos(i, top.getY(), k), Blocks.LAPIS_ORE, 0);
                     }
                  } else {
                     this.setBlockState(world, new BlockPos(i, top.getY(), k), Blocks.IRON_ORE, 0);
                  }
               } else {
                  this.setBlockState(world, new BlockPos(i, top.getY(), k), Blocks.COAL_ORE, 0);
               }
            } else {
               this.setBlockState(world, new BlockPos(i, top.getY(), k), Blocks.STONE, 0);
            }
         }
      }
   }

   public void remove_NonStone(World world, int i, int k, int a) {
      BlockPos top = this.getTopBlock(world, new BlockPos(i, 5, k));
      Block block = this.getBlockState(world, top);
      if (block.getDefaultState().getMaterial() != Material.ROCK && block != Blocks.BEDROCK && a < 100) {
         this.setBlockToAir(world, top);
         this.remove_NonStone(world, i, k, a + 1);
         this.Add_Stone(world, i, k, new com.electrifiedded.archaiclib.util.FastRandom());
      }
   }

   static {
      Materials.add(new RandomItemStack(new ItemStack(Blocks.GRAVEL, 64), 200));
      Materials.add(new RandomItemStack(new ItemStack(Blocks.SAND, 64), 200));
      Materials.add(new RandomItemStack(new ItemStack(Blocks.COBBLESTONE, 64), 200));
      Materials.add(new RandomItemStack(new ItemStack(Blocks.DIRT, 64), 200));
      Materials.add(new RandomItemStack(new ItemStack(Blocks.SANDSTONE, 64), 100));
      Materials.add(new RandomItemStack(new ItemStack(Blocks.LOG, 64), 100));
      Materials.add(new RandomItemStack(new ItemStack(Items.BONE, 64), 60));
      Materials.add(new RandomItemStack(new ItemStack(Items.FLINT, 64), 60));
      Materials.add(new RandomItemStack(new ItemStack(Blocks.NETHERRACK, 64), 40));
      Materials.add(new RandomItemStack(new ItemStack(Blocks.BRICK_BLOCK, 64), 40));
      Materials.add(new RandomItemStack(new ItemStack(Blocks.NETHER_BRICK, 64), 40));
      Materials.add(new RandomItemStack(new ItemStack(Blocks.IRON_ORE, 32), 10));
      Materials.add(new RandomItemStack(new ItemStack(Blocks.GOLD_ORE, 32), 10));
      Materials.add(new RandomItemStack(new ItemStack(Blocks.REDSTONE_ORE, 45), 7));
      Materials.add(new RandomItemStack(new ItemStack(Blocks.LAPIS_ORE, 45), 7));
      Materials.add(new RandomItemStack(new ItemStack(Items.SLIME_BALL, 32), 63));
      Materials.add(new RandomItemStack(new ItemStack(Blocks.DIAMOND_ORE, 16), 4));
      Materials.add(new RandomItemStack(new ItemStack(Blocks.EMERALD_ORE, 16), 1));
   }
}
