package com.electrifiedded.youtubers.WorldGen;

import com.electrifiedded.archaiclib.world.StagedWorld;
import com.electrifiedded.youtubers.YTConfig;
import com.electrifiedded.youtubers.YTItems;
import net.minecraft.util.math.BlockPos;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.world.World;

public class GenJail extends GenYoutuberStructure {
   @Override
   public boolean hasDemo() {
      return true;
   }

   @Override
   public void genDemo(World world, Random random, BlockPos pos) {
      int i = pos.getX();
      int j = pos.getY();
      int k = pos.getZ();
      this.GenBlackMarket(world, random, i, j, k, 1);
      this.GenJailMine(world, random, i, j, k - 56, 0);
      this.GenJailMine(world, random, i, j, k - 48, 1);
      this.GenJailMine(world, random, i, j, k - 40, 2);
      this.GenJailMine(world, random, i, j, k - 32, 3);
      this.GenJailMine(world, random, i, j, k - 24, 4);
      this.GenJailMine(world, random, i, j, k - 16, 5);
      this.genBaceJail(world, random, i, j, k - 8, true);
      this.GenJailWoodMine(world, random, i + 8, j, k - 8);
      this.GenJailSandMine(world, random, i - 8, j, k - 8);
      this.genBaceJail(world, random, i + 0, j, k + 8, true);
      this.GenUtilityEnchant(world, random, i + 0, j, k + 16, random.nextInt(25));
      this.GenUtilitySmelt(world, random, i + 8, j, k + 8, random.nextInt(30));
      this.GenUtilityAnvil(world, random, i - 8, j, k + 8, random.nextInt(30));
   }

   @Override
   public boolean generate(World world, Random random, BlockPos pos) {
      int i = pos.getX();
      int j = pos.getY();
      int k = pos.getZ();
      int[][] locations = new int[11][11];
      int a = random.nextInt(2);
      locations[6][6] = 2 + a;
      this.GenBlackMarket(world, random, i, j, k, a);
      int attempts = 0;
      int placed = 0;
      int tobepaced = 6 + random.nextInt(10);

      do {
         int x = random.nextInt(11);
         int z = random.nextInt(11);
         boolean fits = false;
         if (locations[Math.min(x + 1, 10)][z] == 1) {
            fits = true;
         }

         if (locations[Math.max(x - 1, 0)][z] == 1) {
            fits = true;
         }

         if (locations[x][Math.min(z + 1, 10)] == 1) {
            fits = true;
         }

         if (locations[x][Math.max(z - 1, 0)] == 1) {
            fits = true;
         }

         if (locations[Math.min(x + 1, 10)][z] == 2) {
            fits = true;
         }

         if (locations[Math.max(x - 1, 0)][z] == 2) {
            fits = true;
         }

         if (locations[x][Math.min(z + 1, 10)] == 3) {
            fits = true;
         }

         if (locations[x][Math.max(z - 1, 0)] == 3) {
            fits = true;
         }

         if (this.get_water_depth(world, x, z) > 14) {
            fits = false;
         }

         if (Math.abs(StagedWorld.getTopSolidOrLiquidBlock(world, i + (x - 6) * 8, k + (z - 6) * 8) - j) > 7 && fits) {
            fits = false;
         }

         if (locations[x][z] != 0) {
            fits = false;
         }

         if (fits) {
            locations[x][z] = 1;
            switch (random.nextInt(9)) {
               case 0:
               case 1:
               case 2:
               case 3:
                  this.GenJailMine(world, random, i + (x - 6) * 8, j, k + (z - 6) * 8, -1);
                  break;
               case 4:
               case 5:
                  if (random.nextBoolean()) {
                     this.GenJailWoodMine(world, random, i + (x - 6) * 8, j, k + (z - 6) * 8);
                  } else {
                     this.GenJailSandMine(world, random, i + (x - 6) * 8, j, k + (z - 6) * 8);
                  }
                  break;
               case 6:
               case 7:
                  this.GenUtility(world, random, i + (x - 6) * 8, j, k + (z - 6) * 8);
                  break;
               case 8:
                  this.genBaceJail(world, random, i + (x - 6) * 8, j, k + (z - 6) * 8, true);
            }

            placed++;
         }
      } while (placed < tobepaced && ++attempts < 200);

      return false;
   }

   private int get_water_depth(World world, int x, int z) {
      int a = StagedWorld.getTopSolidOrLiquidBlock(world, x, z);

      int depth;
      for (depth = 0; this.getBlockState(world, new BlockPos(x, a, z)) == Blocks.WATER; depth++) {
         a--;
      }

      return depth;
   }

   private void genBaceJail(World world, Random random, int i, int j, int k, boolean Paths) {
      this.genBoxAir(world, 0, i - 3, j, k - 3, i + 3, j + 3, k + 3);
      this.genBox(world, Block.getBlockById(98), 0, 0, i - 3, j - 1, k - 3, i + 3, j - 1, k + 3);
      this.genBox(world, Block.getBlockById(101), 0, 1, i - 3, j, k - 3, i + 3, j + 2, k + 3);
      this.GenCeiling(world, random, i, j, k);
      if (Paths) {
         this.addpaths(world, random, i, j, k);
      }
   }

   private void addpaths(World world, Random random, int i, int j, int k) {
      Block id = Blocks.STONEBRICK;
      if (this.getBlockState(world, new BlockPos(i - 5, j - 1, k)) == id && this.getBlockState(world, new BlockPos(i - 3, j - 1, k)) == id) {
         this.GenPath(world, i - 4, j, k, 0);
      }

      if (this.getBlockState(world, new BlockPos(i + 5, j - 1, k)) == id && this.getBlockState(world, new BlockPos(i + 3, j - 1, k)) == id) {
         this.GenPath(world, i + 4, j, k, 0);
      }

      if (this.getBlockState(world, new BlockPos(i, j - 1, k - 5)) == id && this.getBlockState(world, new BlockPos(i, j - 1, k - 3)) == id) {
         this.GenPath(world, i, j, k - 4, 1);
      }

      if (this.getBlockState(world, new BlockPos(i, j - 1, k + 5)) == id && this.getBlockState(world, new BlockPos(i, j - 1, k + 3)) == id) {
         this.GenPath(world, i, j, k + 4, 1);
      }
   }

   private void GenCeiling(World world, Random random, int i, int j, int k) {
      this.genBox(world, Block.getBlockById(44), 5, 1, i - 3, j + 3, k - 3, i + 3, j + 3, k + 3);
      this.genBox(world, Block.getBlockById(98), 0, 1, i - 2, j + 3, k - 2, i + 2, j + 3, k + 2);
      this.genBox(world, Block.getBlockById(44), 5, 1, i - 1, j + 4, k - 1, i + 1, j + 4, k + 1);
      this.setBlockState(world, new BlockPos(i, j + 4, k), Block.getBlockById(44), 13);
      this.setBlockState(world, new BlockPos(i + 1, j + 3, k), Block.getBlockById(50), 2);
      this.setBlockState(world, new BlockPos(i, j + 3, k + 1), Block.getBlockById(50), 4);
      this.setBlockState(world, new BlockPos(i - 1, j + 3, k), Block.getBlockById(50), 1);
      this.setBlockState(world, new BlockPos(i, j + 3, k - 1), Block.getBlockById(50), 3);
      this.setBlockState(world, new BlockPos(i + 1, j + 3, k + 1), Block.getBlockById(44), 13);
      this.setBlockState(world, new BlockPos(i - 1, j + 3, k + 1), Block.getBlockById(44), 13);
      this.setBlockState(world, new BlockPos(i + 1, j + 3, k - 1), Block.getBlockById(44), 13);
      this.setBlockState(world, new BlockPos(i - 1, j + 3, k - 1), Block.getBlockById(44), 13);
   }

   private void GenJailMine(World world, Random random, int i, int j, int k, int in) {
      this.genBaceJail(world, random, i, j, k, true);
      this.genBox(world, Block.getBlockById(98), 0, 0, i - 3, j - 4, k - 3, i + 3, j - 1, k + 3);
      this.populateMine(world, random, i, j, k, in);
   }

   private void GenJailWoodMine(World world, Random random, int i, int j, int k) {
      this.genBaceJail(world, random, i, j, k, true);
      this.genBox(world, Block.getBlockById(98), 0, 0, i - 3, j - 4, k - 3, i + 3, j - 1, k + 3);

      for (int x = i - 2; x <= i + 2; x++) {
         for (int y = j - 3; y <= j - 1; y++) {
            for (int z = k - 2; z <= k + 2; z++) {
               if (random.nextInt(6) < 5) {
                  this.setBlockState(world, new BlockPos(x, y, z), Blocks.LOG, random.nextInt(12));
               } else {
                  int[] meta = new int[]{0, 1, 4, 5, 8, 9};
                  this.setBlockState(world, new BlockPos(x, y, z), Blocks.LOG2, meta[random.nextInt(6)]);
               }
            }
         }
      }
   }

   private void GenJailSandMine(World world, Random random, int i, int j, int k) {
      this.genBaceJail(world, random, i, j, k, true);
      this.genBox(world, Block.getBlockById(98), 0, 0, i - 3, j - 4, k - 3, i + 3, j - 1, k + 3);

      for (int x = i - 2; x <= i + 2; x++) {
         for (int y = j - 3; y <= j - 1; y++) {
            for (int z = k - 2; z <= k + 2; z++) {
               switch (random.nextInt(3)) {
                  case 0:
                     this.setBlockState(world, new BlockPos(x, y, z), Blocks.SAND, 0);
                     break;
                  case 1:
                     this.setBlockState(world, new BlockPos(x, y, z), Blocks.SAND, 1);
                     break;
                  case 2:
                     this.setBlockState(world, new BlockPos(x, y, z), Blocks.GRAVEL, 0);
               }
            }
         }
      }
   }

   private void GenUtility(World world, Random random, int i, int j, int k) {
      switch (random.nextInt(3)) {
         case 0:
            this.GenUtilityEnchant(world, random, i, j, k, random.nextInt(25));
            break;
         case 1:
            this.GenUtilitySmelt(world, random, i, j, k, random.nextInt(30));
            break;
         case 2:
            this.GenUtilityAnvil(world, random, i, j, k, random.nextInt(30));
      }
   }

   private void GenUtilityEnchant(World world, Random random, int i, int j, int k, int strength) {
      this.genBaceJail(world, random, i, j, k, true);
      this.genBox(world, Block.getBlockById(98), 0, 0, i - 3, j - 2, k - 3, i + 3, j - 1, k + 3);
      this.genBoxAir(world, 0, i - 1, j - 1, k - 1, i + 1, j - 1, k + 1);
      this.setBlockState(world, new BlockPos(i, j - 1, k), Blocks.ENCHANTING_TABLE, 0);

      for (int a = 0; a < strength; a++) {
         int b = random.nextInt(5) - 2;
         int c = random.nextBoolean() ? -2 : 2;
         if (random.nextBoolean()) {
            this.setBlockState(world, new BlockPos(i + b, j - 1, k + c), Blocks.BOOKSHELF, 0);
         } else {
            this.setBlockState(world, new BlockPos(i + c, j - 1, k + b), Blocks.BOOKSHELF, 0);
         }
      }
   }

   private void GenUtilitySmelt(World world, Random random, int i, int j, int k, int strength) {
      this.genBaceJail(world, random, i, j, k, true);
      this.genBox(world, Block.getBlockById(98), 0, 0, i - 3, j - 2, k - 3, i + 3, j - 1, k + 3);
      this.genBoxAir(world, 0, i - 1, j - 1, k - 1, i + 1, j - 1, k + 1);
      this.setBlockState(world, new BlockPos(i, j - 1, k), Blocks.CRAFTING_TABLE, 0);

      for (int a = 0; a < strength; a++) {
         int b = random.nextInt(5) - 2;
         int c = random.nextBoolean() ? -2 : 2;
         if (random.nextBoolean()) {
            this.setBlockState(world, new BlockPos(i + b, j - 1, k + c), Blocks.FURNACE, random.nextInt(4) + 1);
         } else {
            this.setBlockState(world, new BlockPos(i + c, j - 1, k + b), Blocks.FURNACE, random.nextInt(4) + 1);
         }
      }
   }

   private void GenUtilityAnvil(World world, Random random, int i, int j, int k, int strength) {
      this.genBaceJail(world, random, i, j, k, true);
      this.genBox(world, Block.getBlockById(98), 0, 0, i - 3, j - 2, k - 3, i + 3, j - 1, k + 3);
      this.genBoxAir(world, 0, i - 1, j - 1, k - 1, i + 1, j - 1, k + 1);
      this.PlaceRandAnvil(world, random, i, j - 1, k);

      for (int a = 0; a < strength; a++) {
         int b = random.nextInt(5) - 2;
         int c = random.nextBoolean() ? -2 : 2;
         if (random.nextBoolean()) {
            this.PlaceRandAnvil(world, random, i + b, j - 1, k + c);
         } else {
            this.PlaceRandAnvil(world, random, i + c, j - 1, k + b);
         }
      }
   }

   private void PlaceRandAnvil(World world, Random random, int i, int j, int k) {
      if (random.nextInt(2) == 0) {
         if (random.nextInt(2) == 0) {
            this.setBlockState(world, new BlockPos(i, j, k), Blocks.ANVIL, random.nextInt(2));
         } else {
            this.setBlockState(world, new BlockPos(i, j, k), Blocks.ANVIL, random.nextInt(2) + 4);
         }
      } else {
         this.setBlockState(world, new BlockPos(i, j, k), Blocks.ANVIL, random.nextInt(2) + 8);
      }
   }

   private void GenBlackMarket(World world, Random random, int i, int j, int k, int type) {
      this.genBaceJail(world, random, i, j, k, false);
      switch (type) {
         case 0:
            this.addChest(world, random, i - 2, j, k + 2);
            this.addChest(world, random, i - 2, j + 1, k + 2);
            this.setBlockState(world, new BlockPos(i - 1, j, k + 2), Blocks.IRON_BARS, 0);
            this.setBlockState(world, new BlockPos(i - 1, j + 1, k + 2), Blocks.IRON_BARS, 0);
            this.addChest(world, random, i, j, k + 2);
            this.addChest(world, random, i, j + 1, k + 2);
            this.setBlockState(world, new BlockPos(i + 1, j, k + 2), Blocks.IRON_BARS, 0);
            this.setBlockState(world, new BlockPos(i + 1, j + 1, k + 2), Blocks.IRON_BARS, 0);
            this.addChest(world, random, i + 2, j, k + 2);
            this.addChest(world, random, i + 2, j + 1, k + 2);
            this.addChest(world, random, i - 2, j, k - 2);
            this.addChest(world, random, i - 2, j + 1, k - 2);
            this.setBlockState(world, new BlockPos(i - 1, j, k - 2), Blocks.IRON_BARS, 0);
            this.setBlockState(world, new BlockPos(i - 1, j + 1, k - 2), Blocks.IRON_BARS, 0);
            this.addChest(world, random, i, j, k - 2);
            this.addChest(world, random, i, j + 1, k - 2);
            this.setBlockState(world, new BlockPos(i + 1, j, k - 2), Blocks.IRON_BARS, 0);
            this.setBlockState(world, new BlockPos(i + 1, j + 1, k - 2), Blocks.IRON_BARS, 0);
            this.addChest(world, random, i + 2, j, k - 2);
            this.addChest(world, random, i + 2, j + 1, k - 2);
            this.setBlockState(world, new BlockPos(i, j - 1, k + 3), Blocks.OBSIDIAN, 0);
            this.setBlockState(world, new BlockPos(i, j - 1, k - 3), Blocks.OBSIDIAN, 0);
            break;
         case 1:
            this.addChest(world, random, i + 2, j, k - 2);
            this.addChest(world, random, i + 2, j + 1, k - 2);
            this.setBlockState(world, new BlockPos(i + 2, j, k - 1), Blocks.IRON_BARS, 0);
            this.setBlockState(world, new BlockPos(i + 2, j + 1, k - 1), Blocks.IRON_BARS, 0);
            this.addChest(world, random, i + 2, j, k);
            this.addChest(world, random, i + 2, j + 1, k);
            this.setBlockState(world, new BlockPos(i + 2, j, k + 1), Blocks.IRON_BARS, 0);
            this.setBlockState(world, new BlockPos(i + 2, j + 1, k + 1), Blocks.IRON_BARS, 0);
            this.addChest(world, random, i + 2, j, k + 2);
            this.addChest(world, random, i + 2, j + 1, k + 2);
            this.addChest(world, random, i - 2, j, k - 2);
            this.addChest(world, random, i - 2, j + 1, k - 2);
            this.setBlockState(world, new BlockPos(i - 2, j, k - 1), Blocks.IRON_BARS, 0);
            this.setBlockState(world, new BlockPos(i - 2, j + 1, k - 1), Blocks.IRON_BARS, 0);
            this.addChest(world, random, i - 2, j, k);
            this.addChest(world, random, i - 2, j + 1, k);
            this.setBlockState(world, new BlockPos(i - 2, j, k + 1), Blocks.IRON_BARS, 0);
            this.setBlockState(world, new BlockPos(i - 2, j + 1, k + 1), Blocks.IRON_BARS, 0);
            this.addChest(world, random, i - 2, j, k + 2);
            this.addChest(world, random, i - 2, j + 1, k + 2);
            this.setBlockState(world, new BlockPos(i + 3, j - 1, k), Blocks.OBSIDIAN, 0);
            this.setBlockState(world, new BlockPos(i - 3, j - 1, k), Blocks.OBSIDIAN, 0);
      }

      this.addpaths(world, random, i, j, k);
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

   private void populateMine(World world, Random random, int i, int j, int k, int in) {
      Block[] Filler = new Block[]{
         Blocks.WATER, Blocks.SNOW, Blocks.NETHERRACK, Blocks.SANDSTONE, Blocks.STONE, Blocks.COBBLESTONE
      };
      int type = Filler.length - 1;
      if (in == -1) {
         if (random.nextInt(3) != 0) {
            type--;
            if (random.nextInt(3) != 0) {
               type--;
               if (random.nextInt(3) != 0) {
                  type--;
                  if (random.nextInt(3) != 0) {
                     type--;
                     if (random.nextInt(3) != 0) {
                        type--;
                     }
                  }
               }
            }
         }
      } else {
         type = in;
      }

      this.genBox(world, Filler[type], 0, 0, i - 2, j - 3, k - 2, i + 2, j - 1, k + 2);
      Block[][] Items;
      switch (type) {
         case 0:
         default:
            Items = new Block[][]{{Blocks.DIAMOND_ORE, Blocks.EMERALD_ORE}, {Blocks.DIAMOND_BLOCK, Blocks.EMERALD_BLOCK}, {Blocks.BEACON}};
            break;
         case 1:
            Items = new Block[][]{
               {Blocks.IRON_BLOCK, Blocks.GOLD_BLOCK, Blocks.COAL_BLOCK},
               {Blocks.DIAMOND_ORE, Blocks.EMERALD_ORE},
               {Blocks.DIAMOND_BLOCK, Blocks.EMERALD_BLOCK}
            };
            break;
         case 2:
            Items = new Block[][]{
               {Blocks.QUARTZ_BLOCK},
               {Blocks.QUARTZ_BLOCK, Blocks.GOLD_ORE, Blocks.IRON_ORE, Blocks.IRON_BLOCK},
               {Blocks.QUARTZ_BLOCK, Blocks.DIAMOND_ORE}
            };
            break;
         case 3:
            Items = new Block[][]{
               {Blocks.COAL_BLOCK, Blocks.GOLD_ORE, Blocks.IRON_ORE},
               {Blocks.IRON_BLOCK, Blocks.LAPIS_BLOCK, Blocks.REDSTONE_BLOCK},
               {Blocks.GOLD_BLOCK}
            };
            break;
         case 4:
            Items = new Block[][]{
               {Blocks.LAPIS_ORE, Blocks.REDSTONE_ORE, Blocks.COAL_ORE},
               {Blocks.COAL_BLOCK, Blocks.GOLD_ORE, Blocks.IRON_ORE},
               {Blocks.IRON_BLOCK, Blocks.LAPIS_BLOCK}
            };
            break;
         case 5:
            Items = new Block[][]{{Filler[type]}, {Blocks.LAPIS_ORE, Blocks.IRON_ORE, Blocks.COAL_ORE}, {Blocks.GOLD_ORE}};
      }

      for (int a = 0; a < 10; a++) {
         int x = i - 2 + random.nextInt(5);
         int y = j - 3 + random.nextInt(3);
         int z = k - 2 + random.nextInt(5);
         int rareity = 0;
         if (random.nextInt(5) == 0) {
            rareity++;
         }

         if (random.nextInt(5) == 1) {
            rareity++;
         }

         this.setBlockState(world, new BlockPos(x, y, z), Items[rareity][random.nextInt(Items[rareity].length)], 0);
      }

      if (YTConfig.AllowDemo) {
         for (int a = 0; a < Items.length; a++) {
            for (int b = 0; b < Items[a].length; b++) {
               this.setBlockState(world, new BlockPos(i - 3, j + b, k + 1 - a), Items[a][b], 0);
            }
         }
      }
   }

   private void GenPath(World world, int i, int j, int k, int type) {
      this.genBox(world, Block.getBlockById(98), 0, 0, i - 1, j - 1, k - 1, i + 1, j - 1, k + 1);
      this.genBox(world, Block.getBlockById(101), 0, 1, i - 1, j, k - 1, i + 1, j + 2, k + 1);
      this.genBox(world, Block.getBlockById(44), 5, 1, i - 1, j + 3, k - 1, i + 1, j + 3, k + 1);
      this.setBlockState(world, new BlockPos(i, j + 3, k), Block.getBlockById(98), 0);
      this.setBlockToAir(world, new BlockPos(i, j, k));
      this.setBlockToAir(world, new BlockPos(i, j + 1, k));
      this.setBlockToAir(world, new BlockPos(i, j + 2, k));
      if (type == 0) {
         this.setBlockState(world, new BlockPos(i - 1, j + 3, k), Block.getBlockById(98), 0);
         this.setBlockState(world, new BlockPos(i + 1, j + 3, k), Block.getBlockById(98), 0);
         this.setBlockToAir(world, new BlockPos(i + 1, j, k));
         this.setBlockToAir(world, new BlockPos(i + 1, j + 1, k));
         this.setBlockToAir(world, new BlockPos(i - 1, j, k));
         this.setBlockToAir(world, new BlockPos(i - 1, j + 1, k));
      }

      if (type == 1) {
         this.setBlockState(world, new BlockPos(i, j + 3, k - 1), Block.getBlockById(98), 0);
         this.setBlockState(world, new BlockPos(i, j + 3, k + 1), Block.getBlockById(98), 0);
         this.setBlockToAir(world, new BlockPos(i, j, k + 1));
         this.setBlockToAir(world, new BlockPos(i, j + 1, k + 1));
         this.setBlockToAir(world, new BlockPos(i, j, k - 1));
         this.setBlockToAir(world, new BlockPos(i, j + 1, k - 1));
      }
   }
}
