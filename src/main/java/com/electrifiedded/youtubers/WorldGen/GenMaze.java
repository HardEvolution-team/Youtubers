package com.electrifiedded.youtubers.WorldGen;

import com.electrifiedded.archaiclib.world.StagedWorld;
import net.minecraft.util.math.BlockPos;
import com.electrifiedded.youtubers.RandUtil.RandomBlock;
import com.electrifiedded.youtubers.RandUtil.RandomString;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.WeightedRandom;
import net.minecraft.util.WeightedRandom.Item;
import net.minecraft.world.World;

public class GenMaze extends GenYoutuberStructure {
   public static final int mazeHeight = 7;
   public static final int mazeWidth = 7;
   public GenMaze.MazeSegmentInfo[][][] mazeseg;
   public static List<Item> SpawnOres = new ArrayList<>();
   public static List<Item> BiomeSelect = new ArrayList<>();

   @Override
   public BlockPos getSpawnLoc(BlockPos pos) {
      return pos.up(18);
   }

   @Override
   public boolean generate(World world, Random random, BlockPos pos) {
      int i = pos.getX();
      int j = pos.getY();
      int k = pos.getZ();
      GenMaze.MazeBitInfo[][][] maze = new GenMaze.MazeBitInfo[9][7][9];

      for (int a = 0; a < 9; a++) {
         for (int b = 0; b < 7; b++) {
            for (int c = 0; c < 9; c++) {
               maze[a][b][c] = new GenMaze.MazeBitInfo(world, random, i - 24 - 8 - 4 + 8 * a, j - 2 + 6 * b, k - 24 - 8 - 4 + 8 * c);
            }
         }
      }

      this.mazeseg = new GenMaze.MazeSegmentInfo[7][7][7];

      for (int a = 0; a < 7; a++) {
         for (int b = 0; b < 7; b++) {
            for (int c = 0; c < 7; c++) {
               this.mazeseg[a][b][c] = new GenMaze.MazeSegmentInfo(
                  maze[a + 1][b][c + 1], maze[a + 2][b][c + 1], maze[a][b][c + 1], maze[a + 1][b][c + 2], maze[a + 1][b][c], random, a, b, c
               );
            }
         }
      }

      this.mazeseg[3][3][3].Biome = "Precious Maze";
      this.mazeseg[3][3][3].stairsUP = false;
      this.mazeseg[3][3][3].stairsDN = false;
      this.mazeseg[3][4][3].stairsDN = false;
      this.mazeseg[3][2][3].stairsDN = false;

      for (int a = 0; a < 7; a++) {
         for (int c = 0; c < 7; c++) {
            for (int b = 0; b < 7; b++) {
               this.GenMazeSegment(this.mazeseg[a][b][c]);
            }
         }
      }

      for (int a = 0; a < 7; a++) {
         for (int c = 0; c < 7; c++) {
            for (int b = 0; b < 7; b++) {
               this.populateMaze(this.mazeseg[a][b][c], random);
            }
         }
      }

      for (int a = 0; a < 7; a++) {
         for (int c = 0; c < 7; c++) {
            for (int b = 0; b < 7; b++) {
               this.GenMazeStairs(this.mazeseg[a][b][c]);
            }
         }
      }

      return false;
   }

   public void GenMazeSegment(GenMaze.MazeSegmentInfo Info) {
      World world = Info.world;
      int x = Info.x;
      int y = Info.y;
      int z = Info.z;
      this.genBox(world, Blocks.AIR, 0, 0, x + 1, y, z + 1, x + 8, y + 4, z + 8);

      for (int a = 0; a < 9; a++) {
         this.setBlockState(world, new BlockPos(x + 8, y, z + a), Blocks.BEDROCK, 0);
         if (Info.distx < 0.3) {
            this.setBlockState(world, new BlockPos(x + 8, y + 1, z + a), Blocks.BEDROCK, 0);
            this.setBlockState(world, new BlockPos(x + 8, y + 2, z + a), Blocks.BEDROCK, 0);
            this.setBlockState(world, new BlockPos(x + 8, y + 3, z + a), Blocks.BEDROCK, 0);
            this.setBlockState(world, new BlockPos(x + 8, y + 4, z + a), Blocks.BEDROCK, 0);
         }

         this.setBlockState(world, new BlockPos(x + a, y, z + 8), Blocks.BEDROCK, 0);
         if (Info.distx2 < 0.3) {
            this.setBlockState(world, new BlockPos(x, y + 1, z + a), Blocks.BEDROCK, 0);
            this.setBlockState(world, new BlockPos(x, y + 2, z + a), Blocks.BEDROCK, 0);
            this.setBlockState(world, new BlockPos(x, y + 3, z + a), Blocks.BEDROCK, 0);
            this.setBlockState(world, new BlockPos(x, y + 4, z + a), Blocks.BEDROCK, 0);
         }

         this.setBlockState(world, new BlockPos(x, y, z + a), Blocks.BEDROCK, 0);
         if (Info.distz < 0.3) {
            this.setBlockState(world, new BlockPos(x + a, y + 1, z + 8), Blocks.BEDROCK, 0);
            this.setBlockState(world, new BlockPos(x + a, y + 2, z + 8), Blocks.BEDROCK, 0);
            this.setBlockState(world, new BlockPos(x + a, y + 3, z + 8), Blocks.BEDROCK, 0);
            this.setBlockState(world, new BlockPos(x + a, y + 4, z + 8), Blocks.BEDROCK, 0);
         }

         this.setBlockState(world, new BlockPos(x + a, y, z), Blocks.BEDROCK, 0);
         if (Info.distz2 < 0.3) {
            this.setBlockState(world, new BlockPos(x + a, y + 1, z), Blocks.BEDROCK, 0);
            this.setBlockState(world, new BlockPos(x + a, y + 2, z), Blocks.BEDROCK, 0);
            this.setBlockState(world, new BlockPos(x + a, y + 3, z), Blocks.BEDROCK, 0);
            this.setBlockState(world, new BlockPos(x + a, y + 4, z), Blocks.BEDROCK, 0);
         }
      }

      for (int a = 0; a < 9; a++) {
         for (int b = 0; b < 9; b++) {
            this.setBlockState(world, new BlockPos(x + a, y - 1, z + b), Blocks.BEDROCK, 0);
            this.setBlockState(world, new BlockPos(x + a, y + 5, z + b), Blocks.BEDROCK, 0);
         }
      }
   }

   private void GenMazeStairs(GenMaze.MazeSegmentInfo Info) {
      if (Info.stairsUP && Info.layer + 1 < 7) {
         this.addStairs(Info.world, Info.x + 4, Info.y + 6, Info.z + 4);
      }

      if (Info.stairsDN && Info.layer > 0) {
         this.addStairs(Info.world, Info.x + 4, Info.y, Info.z + 4);
      }
   }

   private void addStairs(World world, int x, int y, int z) {
      for (int a = 0; a < 6; a++) {
         this.setBlockToAir(world, new BlockPos(x - 1, y - a, z - 1));
         this.setBlockToAir(world, new BlockPos(x, y - a, z - 1));
         this.setBlockToAir(world, new BlockPos(x + 1, y - a, z - 1));
         this.setBlockToAir(world, new BlockPos(x + 1, y - a, z));
         this.setBlockToAir(world, new BlockPos(x - 1, y - a, z));
         this.setBlockToAir(world, new BlockPos(x - 1, y - a, z + 1));
         this.setBlockToAir(world, new BlockPos(x, y - a, z + 1));
         this.setBlockToAir(world, new BlockPos(x + 1, y - a, z + 1));
         this.setBlockState(world, new BlockPos(x, y - a, z), Blocks.BEDROCK, 0);
         switch ((y - a) % 4) {
            case 0:
               this.setBlockState(world, new BlockPos(x, y - a, z - 1), Blocks.STONE_SLAB, 8);
               this.setBlockState(world, new BlockPos(x + 1, y - a, z - 1), Blocks.STONE_SLAB, 0);
               break;
            case 1:
               this.setBlockState(world, new BlockPos(x - 1, y - a, z), Blocks.STONE_SLAB, 8);
               this.setBlockState(world, new BlockPos(x - 1, y - a, z - 1), Blocks.STONE_SLAB, 0);
               break;
            case 2:
               this.setBlockState(world, new BlockPos(x, y - a, z + 1), Blocks.STONE_SLAB, 8);
               this.setBlockState(world, new BlockPos(x - 1, y - a, z + 1), Blocks.STONE_SLAB, 0);
               break;
            case 3:
               this.setBlockState(world, new BlockPos(x + 1, y - a, z), Blocks.STONE_SLAB, 8);
               this.setBlockState(world, new BlockPos(x + 1, y - a, z + 1), Blocks.STONE_SLAB, 0);
         }
      }
   }

   public void populatePreciousMaze(GenMaze.MazeSegmentInfo Info) {
      World world = Info.world;
      int x = Info.x + 1;
      int z = Info.z + 1;
      int y = Info.y;
      this.setBlockState(world, new BlockPos(x + 1, y + 4, z + 1), Blocks.GLOWSTONE, 0);
      this.setBlockState(world, new BlockPos(x + 1, y + 4, z + 5), Blocks.GLOWSTONE, 0);
      this.setBlockState(world, new BlockPos(x + 5, y + 4, z + 1), Blocks.GLOWSTONE, 0);
      this.setBlockState(world, new BlockPos(x + 5, y + 4, z + 5), Blocks.GLOWSTONE, 0);

      for (int a = 0; a < 7; a++) {
         for (int b = 0; b < 7; b++) {
            this.setBlockState(world, new BlockPos(x + a, y, z + b), Blocks.COAL_BLOCK, 0);
         }
      }

      for (int a = 0; a < 5; a++) {
         for (int b = 0; b < 7; b++) {
            if (Info.distx2 < 0.3) {
               if (a < 2) {
                  this.setBlockState(world, new BlockPos(x, y + a, z + b), Blocks.GOLD_BLOCK, 0);
               } else {
                  this.setBlockState(world, new BlockPos(x, y + a, z + b), Blocks.IRON_BLOCK, 0);
               }
            }

            if (Info.distx < 0.3) {
               if (a < 2) {
                  this.setBlockState(world, new BlockPos(x + 6, y + a, z + b), Blocks.GOLD_BLOCK, 0);
               } else {
                  this.setBlockState(world, new BlockPos(x + 6, y + a, z + b), Blocks.IRON_BLOCK, 0);
               }
            }

            if (Info.distz2 < 0.3) {
               if (a < 2) {
                  this.setBlockState(world, new BlockPos(x + b, y + a, z), Blocks.GOLD_BLOCK, 0);
               } else {
                  this.setBlockState(world, new BlockPos(x + b, y + a, z), Blocks.IRON_BLOCK, 0);
               }
            }

            if (Info.distz < 0.3) {
               if (a < 2) {
                  this.setBlockState(world, new BlockPos(x + b, y + a, z + 6), Blocks.GOLD_BLOCK, 0);
               } else {
                  this.setBlockState(world, new BlockPos(x + b, y + a, z + 6), Blocks.IRON_BLOCK, 0);
               }
            }
         }

         if (Info.distx < 0.3) {
            if (a < 3) {
               this.setBlockState(world, new BlockPos(x + 5, y + a, z + 3), Blocks.EMERALD_BLOCK, 0);
            } else {
               this.setBlockState(world, new BlockPos(x + 5, y + a, z + 3), Blocks.DIAMOND_BLOCK, 0);
            }
         }

         if (Info.distz2 < 0.3) {
            if (a < 3) {
               this.setBlockState(world, new BlockPos(x + 3, y + a, z + 1), Blocks.EMERALD_BLOCK, 0);
            } else {
               this.setBlockState(world, new BlockPos(x + 3, y + a, z + 1), Blocks.DIAMOND_BLOCK, 0);
            }
         }
      }

      if (Info.distx > 0.3 && "Precious Maze".equals(Info.BiomeX)) {
         for (int a = 0; a < 7; a++) {
            this.setBlockState(world, new BlockPos(x + 7, y, z + a), Blocks.REDSTONE_BLOCK, 0);
         }
      }

      if (Info.distz > 0.3 && "Precious Maze".equals(Info.BiomeZ)) {
         for (int a = 0; a < 7; a++) {
            this.setBlockState(world, new BlockPos(x + a, y, z + 7), Blocks.LAPIS_BLOCK, 0);
         }
      }
   }

   public void populateCaveMaze(GenMaze.MazeSegmentInfo Info, Random random) {
      World world = Info.world;
      int x = Info.x + 1;
      int z = Info.z + 1;
      int y = Info.y;

      for (int a = 0; a < 7; a++) {
         for (int b = 0; b < 7; b++) {
            this.setOre(world, random, x + a, y, z + b);
            this.setOre(world, random, x + a, y + 3, z + b);
            this.setOre(world, random, x + a, y + 4, z + b);
         }
      }

      for (int a = 1; a < 5; a++) {
         for (int b = 0; b < 7; b++) {
            if (Info.distx2 < 0.3) {
               this.setOre(world, random, x, y + a, z + b);
               this.setOre(world, random, x + 1, y + a, z + b);
            }

            if (Info.distx < 0.3) {
               this.setOre(world, random, x + 6, y + a, z + b);
               this.setOre(world, random, x + 5, y + a, z + b);
            }

            if (Info.distz2 < 0.3) {
               this.setOre(world, random, x + b, y + a, z);
               this.setOre(world, random, x + b, y + a, z + 1);
            }

            if (Info.distz < 0.3) {
               this.setOre(world, random, x + b, y + a, z + 6);
               this.setOre(world, random, x + b, y + a, z + 5);
            }
         }
      }

      if (Info.distx > 0.3 && "Cave Maze".equals(Info.BiomeX)) {
         for (int a = 0; a < 7; a++) {
            this.setOre(world, random, x + 7, y, z + a);
            this.setOre(world, random, x + 7, y + 3, z + a);
            this.setOre(world, random, x + 7, y + 4, z + a);
            if (a < 5) {
               this.setOre(world, random, x + 7, y + a, z);
               this.setOre(world, random, x + 7, y + a, z + 6);
            }
         }
      }

      if (Info.distz > 0.3 && "Cave Maze".equals(Info.BiomeZ)) {
         for (int ax = 0; ax < 7; ax++) {
            this.setOre(world, random, x + ax, y, z + 7);
            this.setOre(world, random, x + ax, y + 3, z + 7);
            this.setOre(world, random, x + ax, y + 4, z + 7);
            if (ax < 5) {
               this.setOre(world, random, x, y + ax, z + 7);
               this.setOre(world, random, x + 6, y + ax, z + 7);
            }
         }
      }
   }

   private void setOre(World world, Random random, int x, int y, int z) {
      int num = random.nextInt(6);
      if (num == 0) {
         RandomBlock RBlock = (RandomBlock)WeightedRandom.getRandomItem(random, SpawnOres);
         if (RBlock.block == null) {
            this.setBlockState(world, new BlockPos(x, y, z), Blocks.STONE, 0);
         } else if (RBlock.Meta == 0) {
            this.setBlockState(world, new BlockPos(x, y, z), RBlock.block, 0);
         } else {
            this.setBlockState(world, new BlockPos(x, y, z), RBlock.block, RBlock.Meta);
         }
      } else {
         this.setBlockState(world, new BlockPos(x, y, z), Blocks.STONE, 0);
      }
   }

   public void populateDesertMaze(GenMaze.MazeSegmentInfo Info, Random random) {
      World world = Info.world;
      int x = Info.x + 1;
      int z = Info.z + 1;
      int y = Info.y;

      for (int a = 0; a < 7; a++) {
         for (int b = 0; b < 7; b++) {
            this.setDesert(world, random, x + a, y, z + b);
         }
      }

      if (Info.distx > 0.3 && "Desert Maze".equals(Info.BiomeX)) {
         for (int a = 0; a < 7; a++) {
            this.setDesert(world, random, x + 7, y, z + a);
         }
      }

      if (Info.distz > 0.3 && "Desert Maze".equals(Info.BiomeZ)) {
         for (int a = 0; a < 7; a++) {
            this.setDesert(world, random, x + a, y, z + 7);
         }
      }
   }

   private void setDesert(World world, Random random, int x, int y, int z) {
      this.setBlockState(world, new BlockPos(x, y, z), Blocks.SAND, 0);
      int a = 0;
      if (random.nextInt(5) == 0) {
         this.setBlockState(world, new BlockPos(x, y, z), Blocks.SANDSTONE, random.nextInt(3));
         this.setBlockState(world, new BlockPos(x, y + 1, z), Blocks.SAND, 0);
         a++;
      }

      if (random.nextInt(15) == 0) {
         int c = random.nextInt(3) + 2;

         for (int b = 1; b < c; b++) {
            this.setBlockState(world, new BlockPos(x, y + a + b, z), Blocks.CACTUS, 0);
         }
      }
   }

   public void populateEnderMaze(GenMaze.MazeSegmentInfo Info, Random random) {
      World world = Info.world;
      int x = Info.x + 1;
      int z = Info.z + 1;
      int y = Info.y;

      for (int a = 0; a < 7; a++) {
         for (int b = 0; b < 7; b++) {
            this.setBlockState(world, new BlockPos(x + a, y, z + b), Blocks.END_STONE, 0);
         }
      }

      for (int a = 1; a < 3; a++) {
         if (Info.distx2 < 0.3 || Info.distz2 < 0.3) {
            this.setBlockState(world, new BlockPos(x + 1, y + a, z + 1), Blocks.OBSIDIAN, 0);
         }

         if (Info.distx < 0.3 || Info.distz2 < 0.3) {
            this.setBlockState(world, new BlockPos(x + 5, y + a, z + 1), Blocks.OBSIDIAN, 0);
         }

         if (Info.distx2 < 0.3 || Info.distz < 0.3) {
            this.setBlockState(world, new BlockPos(x + 1, y + a, z + 5), Blocks.OBSIDIAN, 0);
         }

         if (Info.distx < 0.3 || Info.distz < 0.3) {
            this.setBlockState(world, new BlockPos(x + 5, y + a, z + 5), Blocks.OBSIDIAN, 0);
         }
      }

      if (Info.distx > 0.3 && "Ender Maze".equals(Info.BiomeX)) {
         for (int a = 0; a < 7; a++) {
            this.setBlockState(world, new BlockPos(x + 7, y, z + a), Blocks.END_STONE, 0);
         }

         if (random.nextInt(7) == 0) {
            this.setBlockState(world, new BlockPos(x + 7, y + 1, z + 3), Blocks.OBSIDIAN, 0);
            this.addEndCristal(world, x + 7, y + 1, z + 3);
         }
      }

      if (Info.distz > 0.3 && "Ender Maze".equals(Info.BiomeZ)) {
         for (int a = 0; a < 7; a++) {
            this.setBlockState(world, new BlockPos(x + a, y, z + 7), Blocks.END_STONE, 0);
         }

         if (random.nextInt(7) == 0) {
            this.setBlockState(world, new BlockPos(x + 3, y + 1, z + 7), Blocks.OBSIDIAN, 0);
            this.addEndCristal(world, x + 3, y + 1, z + 7);
         }
      }
   }

   private void addEndCristal(World world, int x, int y, int z) {
      if (!world.isRemote) StagedWorld.onMain(() -> {
         EntityEnderCrystal entity = new EntityEnderCrystal(world);
         entity.setPosition(x + 0.5, y + 0.75, z + 0.5);
         StagedWorld.spawnEntity(world, entity);
      });
   }

   public void populateNetherMaze(GenMaze.MazeSegmentInfo Info, Random random) {
      World world = Info.world;
      int x = Info.x + 1;
      int z = Info.z + 1;
      int y = Info.y;

      for (int a = 0; a < 7; a++) {
         for (int b = 0; b < 7; b++) {
            this.setBlockState(world, new BlockPos(x + a, y, z + b), Blocks.LAVA, 0);
         }
      }

      for (int a = 2; a < 5; a++) {
         this.setNetherrack(world, random, x + 1, y, z + a);
         this.setNetherrack(world, random, x + 5, y, z + a);
         this.setNetherrack(world, random, x + a, y, z + 1);
         this.setNetherrack(world, random, x + a, y, z + 2);
         this.setNetherrack(world, random, x + a, y, z + 3);
         this.setNetherrack(world, random, x + a, y, z + 4);
         this.setNetherrack(world, random, x + a, y, z + 5);
      }

      if (Info.distx2 > 0.3) {
         this.setNetherrack(world, random, x, y, z + 1 + (int)((Info.distx2 - 0.3) / 0.8 * 5.0));
      }

      if (Info.distx > 0.3) {
         this.setNetherrack(world, random, x + 6, y, z + 1 + (int)((Info.distx - 0.3) / 0.8 * 5.0));
         if ("Firey Maze".equals(Info.BiomeX)) {
            for (int a = 0; a < 7; a++) {
               this.setBlockState(world, new BlockPos(x + 7, y, z + a), Blocks.LAVA, 0);
            }

            this.setNetherrack(world, random, x + 7, y, z + 1 + (int)((Info.distx - 0.3) / 0.8 * 5.0));
         }
      }

      if (Info.distz2 > 0.3) {
         this.setNetherrack(world, random, x + 1 + (int)((Info.distz2 - 0.3) / 0.8 * 5.0), y, z);
      }

      if (Info.distz > 0.3) {
         this.setNetherrack(world, random, x + 1 + (int)((Info.distz - 0.3) / 0.8 * 5.0), y, z + 6);
         if ("Firey Maze".equals(Info.BiomeZ)) {
            for (int a = 0; a < 7; a++) {
               this.setBlockState(world, new BlockPos(x + a, y, z + 7), Blocks.LAVA, 0);
            }

            this.setNetherrack(world, random, x + 1 + (int)((Info.distz - 0.3) / 0.8 * 5.0), y, z + 7);
         }
      }
   }

   private void setNetherrack(World world, Random random, int x, int y, int z) {
      this.setBlockState(world, new BlockPos(x, y, z), Blocks.NETHERRACK, 0);
      if (random.nextInt(10) == 0) {
         this.setBlockState(world, new BlockPos(x, y + 1, z), Blocks.FIRE, 0);
      }
   }

   public void populateWitherMaze(GenMaze.MazeSegmentInfo Info) {
      World world = Info.world;
      int x = Info.x + 1;
      int z = Info.z + 1;
      int y = Info.y;

      for (int a = 0; a < 7; a++) {
         for (int b = 0; b < 7; b++) {
            this.setBlockState(world, new BlockPos(x + a, y, z + b), Blocks.NETHER_BRICK, 0);
         }
      }

      for (int a = 1; a < 5; a++) {
         if (Info.distx2 < 0.3 || Info.distz2 < 0.3) {
            this.setBlockState(world, new BlockPos(x, y + a, z), Blocks.OBSIDIAN, 0);
            this.setBlockState(world, new BlockPos(x + 1, y + a, z + 1), Blocks.NETHER_BRICK, 0);
         }

         if (Info.distx < 0.3 || Info.distz2 < 0.3) {
            this.setBlockState(world, new BlockPos(x + 6, y + a, z), Blocks.OBSIDIAN, 0);
            this.setBlockState(world, new BlockPos(x + 5, y + a, z + 1), Blocks.NETHER_BRICK, 0);
         }

         if (Info.distx2 < 0.3 || Info.distz < 0.3) {
            this.setBlockState(world, new BlockPos(x, y + a, z + 6), Blocks.OBSIDIAN, 0);
            this.setBlockState(world, new BlockPos(x + 1, y + a, z + 5), Blocks.NETHER_BRICK, 0);
         }

         if (Info.distx < 0.3 || Info.distz < 0.3) {
            this.setBlockState(world, new BlockPos(x + 6, y + a, z + 6), Blocks.OBSIDIAN, 0);
            this.setBlockState(world, new BlockPos(x + 5, y + a, z + 5), Blocks.NETHER_BRICK, 0);
         }

         for (int b = 1; b < 6; b++) {
            if (Info.distx2 < 0.3) {
               this.setBlockState(world, new BlockPos(x, y + a, z + b), Blocks.LAVA, 0);
            }

            if (Info.distx < 0.3) {
               this.setBlockState(world, new BlockPos(x + 6, y + a, z + b), Blocks.LAVA, 0);
            }

            if (Info.distz2 < 0.3) {
               this.setBlockState(world, new BlockPos(x + b, y + a, z), Blocks.LAVA, 0);
            }

            if (Info.distz < 0.3) {
               this.setBlockState(world, new BlockPos(x + b, y + a, z + 6), Blocks.LAVA, 0);
            }
         }

         for (int b = 2; b < 5; b++) {
            Block block = Blocks.NETHER_BRICK_FENCE;
            if (a == 4) {
               block = Blocks.NETHER_BRICK;
            }

            if (Info.distx2 < 0.3) {
               this.setBlockState(world, new BlockPos(x + 1, y + a, z + b), block, 0);
            }

            if (Info.distx < 0.3) {
               this.setBlockState(world, new BlockPos(x + 5, y + a, z + b), block, 0);
            }

            if (Info.distz2 < 0.3) {
               this.setBlockState(world, new BlockPos(x + b, y + a, z + 1), block, 0);
            }

            if (Info.distz < 0.3) {
               this.setBlockState(world, new BlockPos(x + b, y + a, z + 5), block, 0);
            }
         }
      }

      if (Info.distx > 0.3 && "Withering Maze".equals(Info.BiomeX)) {
         for (int a = 0; a < 7; a++) {
            this.setBlockState(world, new BlockPos(x + 7, y, z + a), Blocks.NETHER_BRICK, 0);
         }
      }

      if (Info.distz > 0.3 && "Withering Maze".equals(Info.BiomeZ)) {
         for (int a = 0; a < 7; a++) {
            this.setBlockState(world, new BlockPos(x + a, y, z + 7), Blocks.NETHER_BRICK, 0);
         }
      }
   }

   public void populateMesaMaze(GenMaze.MazeSegmentInfo Info, Random random) {
      World world = Info.world;
      int x = Info.x + 1;
      int z = Info.z + 1;
      int y = Info.y;

      for (int a = 0; a < 7; a++) {
         for (int b = 0; b < 7; b++) {
            this.setBlockState(world, new BlockPos(x + a, y, z + b), Blocks.SAND, 1);
         }
      }

      for (int a = 0; a < 5; a++) {
         for (int b = 0; b < 7; b++) {
            if (Info.distx2 < 0.3) {
               this.setClay(world, x, y + a, z + b);
            }

            if (Info.distx < 0.3) {
               this.setClay(world, x + 6, y + a, z + b);
            }

            if (Info.distz2 < 0.3) {
               this.setClay(world, x + b, y + a, z);
            }

            if (Info.distz < 0.3) {
               this.setClay(world, x + b, y + a, z + 6);
            }
         }

         if (Info.distx2 < 0.3) {
            this.setClay(world, x + 1, y + a, z + 3);
         }

         if (Info.distx < 0.3) {
            this.setClay(world, x + 5, y + a, z + 3);
         }

         if (Info.distz2 < 0.3) {
            this.setClay(world, x + 3, y + a, z + 1);
         }

         if (Info.distz < 0.3) {
            this.setClay(world, x + 3, y + a, z + 5);
         }
      }

      if (Info.distx > 0.3 && "Mesa Maze".equals(Info.BiomeX)) {
         for (int a = 0; a < 7; a++) {
            this.setBlockState(world, new BlockPos(x + 7, y, z + a), Blocks.SAND, 1);
            if (a < 5) {
               this.setClay(world, x + 7, y + 4 - a, z);
               this.setClay(world, x + 7, y + 4 - a, z + 6);
            }
         }
      }

      if (Info.distz > 0.3 && "Mesa Maze".equals(Info.BiomeZ)) {
         for (int ax = 0; ax < 7; ax++) {
            this.setBlockState(world, new BlockPos(x + ax, y, z + 7), Blocks.SAND, 1);
            if (ax < 5) {
               this.setClay(world, x, y + 4 - ax, z + 7);
               this.setClay(world, x + 6, y + 4 - ax, z + 7);
            }
         }
      }
   }

   private void setClay(World world, int x, int y, int z) {
      Random random = this.getRandomiser(new long[]{world.getSeed(), y, 75624L, x / 64, 24861L, z / 64, 33511L});
      if (random.nextBoolean()) {
         this.setBlockState(world, new BlockPos(x, y, z), Blocks.STAINED_HARDENED_CLAY, random.nextInt(16));
      } else {
         this.setBlockState(world, new BlockPos(x, y, z), Blocks.HARDENED_CLAY, 0);
      }
   }

   private Random getRandomiser(long[] n) {
      Random random = new com.electrifiedded.archaiclib.util.FastRandom();
      random.setSeed(n[0]);

      for (int a = 1; a < n.length; a += 2) {
         random.setSeed(random.nextLong() + n[a] * n[a + 1]);
      }

      return random;
   }

   public void populateForestMaze(GenMaze.MazeSegmentInfo Info, Random random) {
      World world = Info.world;
      int x = Info.x + 1;
      int z = Info.z + 1;
      int y = Info.y;

      for (int a = 0; a < 7; a++) {
         for (int b = 0; b < 7; b++) {
            this.setGrass(world, random, x + a, y, z + b);
            this.setleaves(world, random, x + a, y + 3, z + b, false);
            this.setleaves(world, random, x + a, y + 4, z + b, false);
            this.addTrunks(world, random, x + a, y, z + b);
         }
      }

      if (Info.distx > 0.3 && "Forest Maze".equals(Info.BiomeX)) {
         for (int a = 0; a < 7; a++) {
            this.setGrass(world, random, x + 7, y, z + a);
            this.setleaves(world, random, x + 7, y + 3, z + a, false);
            this.setleaves(world, random, x + 7, y + 4, z + a, false);
            this.addTrunks(world, random, x + 7, y, z + a);
         }
      }

      if (Info.distz > 0.3 && "Forest Maze".equals(Info.BiomeZ)) {
         for (int a = 0; a < 7; a++) {
            this.setGrass(world, random, x + a, y, z + 7);
            this.setleaves(world, random, x + a, y + 3, z + 7, false);
            this.setleaves(world, random, x + a, y + 4, z + 7, false);
            this.addTrunks(world, random, x + a, y, z + 7);
         }
      }
   }

   private void addTrunks(World world, Random random, int x, int y, int z) {
      if (random.nextInt(20) == 0) {
         int n = random.nextInt(6);

         for (int a = 1; a < 5; a++) {
            switch (n) {
               case 0:
                  this.setBlockState(world, new BlockPos(x, y + a, z), Blocks.LOG, 0);
                  break;
               case 1:
                  this.setBlockState(world, new BlockPos(x, y + a, z), Blocks.LOG, 1);
                  break;
               case 2:
                  this.setBlockState(world, new BlockPos(x, y + a, z), Blocks.LOG, 2);
                  break;
               case 3:
                  this.setBlockState(world, new BlockPos(x, y + a, z), Blocks.LOG, 3);
                  break;
               case 4:
                  this.setBlockState(world, new BlockPos(x, y + a, z), Blocks.LOG2, 0);
                  break;
               case 5:
                  this.setBlockState(world, new BlockPos(x, y + a, z), Blocks.LOG2, 1);
            }
         }
      }
   }

   private void setleaves(World world, Random random, int x, int y, int z, boolean decay) {
      switch (random.nextInt(6)) {
         case 0:
            this.setBlockState(world, new BlockPos(x, y, z), Blocks.LEAVES, 0 + (decay ? 4 : 0));
            break;
         case 1:
            this.setBlockState(world, new BlockPos(x, y, z), Blocks.LEAVES, 1 + (decay ? 4 : 0));
            break;
         case 2:
            this.setBlockState(world, new BlockPos(x, y, z), Blocks.LEAVES, 2 + (decay ? 4 : 0));
            break;
         case 3:
            this.setBlockState(world, new BlockPos(x, y, z), Blocks.LEAVES, 3 + (decay ? 4 : 0));
            break;
         case 4:
            this.setBlockState(world, new BlockPos(x, y, z), Blocks.LEAVES2, 0 + (decay ? 4 : 0));
            break;
         case 5:
            this.setBlockState(world, new BlockPos(x, y, z), Blocks.LEAVES2, 1 + (decay ? 4 : 0));
      }
   }

   private void setGrass(World world, Random random, int x, int y, int z) {
      this.setBlockState(world, new BlockPos(x, y, z), Blocks.GRASS, 0);
      if (random.nextInt(30) == 0) {
         StagedWorld.grow(world, random, x, y, z);
      }
   }

   public void populateVillageMaze(GenMaze.MazeSegmentInfo Info, Random rand) {
      World world = Info.world;
      int x = Info.x + 1;
      int z = Info.z + 1;
      int y = Info.y;
      this.setBlockState(world, new BlockPos(x + 1, y + 4, z + 1), Blocks.GLOWSTONE, 0);
      this.setBlockState(world, new BlockPos(x + 1, y + 4, z + 5), Blocks.GLOWSTONE, 0);
      this.setBlockState(world, new BlockPos(x + 5, y + 4, z + 1), Blocks.GLOWSTONE, 0);
      this.setBlockState(world, new BlockPos(x + 5, y + 4, z + 5), Blocks.GLOWSTONE, 0);

      for (int a = 0; a < 7; a++) {
         for (int b = 0; b < 7; b++) {
            this.setBlockState(world, new BlockPos(x + a, y, z + b), Blocks.GRASS, 0);
         }
      }

      for (int a = 2; a < 5; a++) {
         for (int b = 2; b < 5; b++) {
            this.setBlockState(world, new BlockPos(x + a, y, z + b), Blocks.GRAVEL, 0);
         }
      }

      if (Info.distx > 0.3) {
         if ("Village Maze".equals(Info.BiomeX)) {
            for (int a = 0; a < 7; a++) {
               this.setBlockState(world, new BlockPos(x + 7, y, z + a), Blocks.GRASS, 0);
            }

            for (int a = 2; a < 5; a++) {
               this.setBlockState(world, new BlockPos(x + 7, y, z + a), Blocks.GRAVEL, 0);
            }
         }

         for (int a = 5; a < 7; a++) {
            for (int b = 2; b < 5; b++) {
               this.setBlockState(world, new BlockPos(x + a, y, z + b), Blocks.GRAVEL, 0);
            }
         }
      }

      if (Info.distz > 0.3) {
         if ("Village Maze".equals(Info.BiomeZ)) {
            for (int a = 0; a < 7; a++) {
               this.setBlockState(world, new BlockPos(x + a, y, z + 7), Blocks.GRASS, 0);
            }

            for (int a = 2; a < 5; a++) {
               this.setBlockState(world, new BlockPos(x + a, y, z + 7), Blocks.GRAVEL, 0);
            }
         }

         for (int a = 5; a < 7; a++) {
            for (int b = 2; b < 5; b++) {
               this.setBlockState(world, new BlockPos(x + b, y, z + a), Blocks.GRAVEL, 0);
            }
         }
      }

      if (Info.distx2 > 0.3) {
         for (int a = 0; a < 2; a++) {
            for (int b = 2; b < 5; b++) {
               this.setBlockState(world, new BlockPos(x + a, y, z + b), Blocks.GRAVEL, 0);
            }
         }
      }

      if (Info.distz2 > 0.3) {
         for (int a = 0; a < 2; a++) {
            for (int b = 2; b < 5; b++) {
               this.setBlockState(world, new BlockPos(x + b, y, z + a), Blocks.GRAVEL, 0);
            }
         }
      }

      GenMaze.MazeSegmentInfo Info2 = Info;
      GenMaze.MazeSegmentInfo Info3 = Info;
      if (Info.layer + 1 < 7) {
         Info2 = this.mazeseg[Info.Cx][Info.layer + 1][Info.Cz];
      }

      if (Info.layer > 0) {
         Info3 = this.mazeseg[Info.Cx][Info.layer - 1][Info.Cz];
      }

      if (rand.nextInt(3) == 0) {
         this.addLargeHouse(Info);
      } else if (!Info.stairsDN && !Info.stairsUP && !Info2.stairsDN && !Info3.stairsUP) {
         if (rand.nextInt(30) == 0) {
            this.addWell(Info);
         } else if (rand.nextInt(6) == 0) {
            this.addFarm(Info, rand);
         } else if (rand.nextInt(3) == 0) {
            switch (rand.nextInt(3)) {
               case 0:
                  this.addButcher(Info);
                  break;
               case 1:
                  this.addLibrary(Info);
                  break;
               case 2:
                  this.addSmithy(Info, rand);
            }
         } else {
            this.addTiniHouse(world, x, y, z);
         }
      }
   }

   private void addSmithy(GenMaze.MazeSegmentInfo Info, Random rand) {
      World world = Info.world;
      int x = Info.x + 1;
      int z = Info.z + 1;
      int y = Info.y;
      this.addHut(world, x + 1, y, z + 1, x + 3, y + 3, z + 5, true);

      for (int a = 1; a < 6; a++) {
         this.setBlockState(world, new BlockPos(x + 4, y, z + a), Blocks.COBBLESTONE, 0);
         this.setBlockState(world, new BlockPos(x + 4, y, z + a), Blocks.COBBLESTONE, 0);
      }

      for (int a = 3; a < 6; a++) {
         for (int b = 1; b < 4; b++) {
            this.setBlockState(world, new BlockPos(x + a, y + 1, z + b), Blocks.COBBLESTONE, 0);
            this.setBlockState(world, new BlockPos(x + a, y + 3, z + b), Blocks.COBBLESTONE, 0);
         }
      }

      this.setBlockState(world, new BlockPos(x + 3, y + 2, z + 1), Blocks.COBBLESTONE, 0);
      this.setBlockState(world, new BlockPos(x + 3, y + 2, z + 2), Blocks.COBBLESTONE, 0);
      this.setBlockState(world, new BlockPos(x + 3, y + 2, z + 3), Blocks.FURNACE, 3);
      this.setBlockState(world, new BlockPos(x + 4, y + 2, z + 1), Blocks.IRON_BARS, 0);
      this.setBlockState(world, new BlockPos(x + 4, y + 2, z + 3), Blocks.IRON_BARS, 0);
      this.setBlockState(world, new BlockPos(x + 5, y + 2, z + 1), Blocks.IRON_BARS, 0);
      this.setBlockState(world, new BlockPos(x + 5, y + 2, z + 2), Blocks.IRON_BARS, 0);
      this.setBlockState(world, new BlockPos(x + 4, y + 1, z + 2), Blocks.LAVA, 0);
      this.setBlockToAir(world, new BlockPos(x + 3, y + 1, z + 4));
      this.setBlockToAir(world, new BlockPos(x + 3, y + 2, z + 4));
      this.setBlockState(world, new BlockPos(x + 1, y + 2, z + 4), Blocks.GLASS_PANE, 0);
      this.setBlockState(world, new BlockPos(x + 2, y + 2, z + 5), Blocks.GLASS_PANE, 0);
      this.setBlockState(world, new BlockPos(x + 5, y + 1, z + 5), Blocks.ANVIL, com.electrifiedded.archaiclib.util.FastRandom.shared().nextInt(4) + 8);
      this.setBlockState(world, new BlockPos(x + 2, y + 1, z + 2), Blocks.CHEST, 4);
      TileEntityChest tileentitychest = (TileEntityChest)StagedWorld.getTileEntity(world, x + 2, y + 1, z + 2);
      if (tileentitychest != null) {
         tileentitychest.setLootTable(net.minecraft.world.storage.loot.LootTableList.CHESTS_VILLAGE_BLACKSMITH, rand.nextLong());
      }
   }

   private void addButcher(GenMaze.MazeSegmentInfo Info) {
      World world = Info.world;
      int x = Info.x + 1;
      int z = Info.z + 1;
      int y = Info.y;
      this.addHut(world, x + 2, y, z + 1, x + 5, y + 3, z + 5, true);
      this.addDoor(world, x + 2, y + 1, z + 4, 0);
      this.addDoor(world, x + 5, y + 1, z + 2, 2);
      this.setBlockState(world, new BlockPos(x + 5, y + 2, z + 4), Blocks.GLASS_PANE, 0);
      this.setBlockState(world, new BlockPos(x + 2, y + 2, z + 2), Blocks.GLASS_PANE, 0);
      this.setBlockState(world, new BlockPos(x + 3, y + 2, z + 5), Blocks.GLASS_PANE, 0);
      this.setBlockState(world, new BlockPos(x + 4, y + 1, z + 3), Blocks.DOUBLE_STONE_SLAB, 0);
      this.setBlockState(world, new BlockPos(x + 1, y + 1, z + 1), Blocks.OAK_FENCE, 0);
      this.setBlockState(world, new BlockPos(x, y + 1, z + 1), Blocks.OAK_FENCE, 0);
      this.setBlockState(world, new BlockPos(x + 1, y + 1, z + 5), Blocks.OAK_FENCE, 0);
      this.setBlockState(world, new BlockPos(x, y + 1, z + 5), Blocks.OAK_FENCE, 0);

      for (int a = 2; a < 5; a++) {
         this.setBlockState(world, new BlockPos(x, y, z + a), Blocks.GRASS, 0);
         this.setBlockState(world, new BlockPos(x + 1, y, z + a), Blocks.GRASS, 0);
         if (Info.distx2 > 0.3) {
            this.setBlockState(world, new BlockPos(x, y + 1, z + a), Blocks.OAK_FENCE, 0);
         }
      }
   }

   private void addLibrary(GenMaze.MazeSegmentInfo Info) {
      World world = Info.world;
      int x = Info.x + 1;
      int z = Info.z + 1;
      int y = Info.y;
      this.addHut(world, x + 1, y, z + 2, x + 5, y + 3, z + 5, true);
      this.addDoor(world, x + 2, y + 1, z + 2, 1);
      this.setBlockState(world, new BlockPos(x + 4, y + 1, z + 2), Blocks.GLASS_PANE, 0);
      this.setBlockState(world, new BlockPos(x + 4, y + 2, z + 2), Blocks.GLASS_PANE, 0);
      this.setBlockState(world, new BlockPos(x + 5, y + 2, z + 3), Blocks.GLASS_PANE, 0);
      this.setBlockState(world, new BlockPos(x + 4, y + 2, z + 5), Blocks.GLASS_PANE, 0);
      this.setBlockState(world, new BlockPos(x + 2, y + 2, z + 5), Blocks.GLASS_PANE, 0);
      this.setBlockState(world, new BlockPos(x + 1, y + 2, z + 4), Blocks.GLASS_PANE, 0);
      this.setBlockState(world, new BlockPos(x + 4, y + 1, z + 4), Blocks.CRAFTING_TABLE, 0);
      this.setBlockState(world, new BlockPos(x + 4, y + 3, z + 4), Blocks.BOOKSHELF, 0);
      this.setBlockState(world, new BlockPos(x + 3, y + 3, z + 4), Blocks.BOOKSHELF, 0);
      this.setBlockState(world, new BlockPos(x + 2, y + 3, z + 4), Blocks.BOOKSHELF, 0);
      this.setBlockState(world, new BlockPos(x + 4, y + 3, z + 3), Blocks.WOODEN_SLAB, 8);
      this.setBlockState(world, new BlockPos(x + 3, y + 3, z + 3), Blocks.WOODEN_SLAB, 8);
      this.setBlockState(world, new BlockPos(x + 2, y + 3, z + 3), Blocks.WOODEN_SLAB, 8);
   }

   private void addLargeHouse(GenMaze.MazeSegmentInfo Info) {
      World world = Info.world;
      int x = Info.x + 1;
      int z = Info.z + 1;
      int y = Info.y;
      this.addHut(world, x, y, z, x + 6, y + 4, z + 6, false);
      this.boxReplace(world, Blocks.PLANKS, 0, Blocks.COBBLESTONE, 0, x, y, z, x + 6, y + 1, z + 6, "all");
      if (Info.distx2 > 0.3) {
         this.setBlockState(world, new BlockPos(x, y + 2, z + 1), Blocks.GLASS_PANE, 0);
         this.addDoor(world, x, y + 1, z + 3, 0);
         this.setBlockState(world, new BlockPos(x, y + 2, z + 5), Blocks.GLASS_PANE, 0);
      }

      if (Info.distz2 > 0.3) {
         this.setBlockState(world, new BlockPos(x + 1, y + 2, z), Blocks.GLASS_PANE, 0);
         this.addDoor(world, x + 3, y + 1, z, 1);
         this.setBlockState(world, new BlockPos(x + 5, y + 2, z), Blocks.GLASS_PANE, 0);
      }

      if (Info.distx > 0.3) {
         this.setBlockState(world, new BlockPos(x + 6, y + 2, z + 1), Blocks.GLASS_PANE, 0);
         this.addDoor(world, x + 6, y + 1, z + 3, 2);
         this.setBlockState(world, new BlockPos(x + 6, y + 2, z + 5), Blocks.GLASS_PANE, 0);
      }

      if (Info.distz > 0.3) {
         this.setBlockState(world, new BlockPos(x + 1, y + 2, z + 6), Blocks.GLASS_PANE, 0);
         this.addDoor(world, x + 3, y + 1, z + 6, 3);
         this.setBlockState(world, new BlockPos(x + 5, y + 2, z + 6), Blocks.GLASS_PANE, 0);
      }
   }

   private void addWell(GenMaze.MazeSegmentInfo Info) {
      World world = Info.world;
      int x = Info.x + 1;
      int z = Info.z + 1;
      int y = Info.y;

      for (int a = 2; a < 5; a++) {
         for (int b = 2; b < 5; b++) {
            this.setBlockState(world, new BlockPos(x + a, y, z + b), Blocks.COBBLESTONE, 0);
            this.setBlockState(world, new BlockPos(x + a, y + 1, z + b), Blocks.COBBLESTONE, 0);
            this.setBlockState(world, new BlockPos(x + a, y + 4, z + b), Blocks.COBBLESTONE, 0);
            this.setBlockState(world, new BlockPos(x + a, y, z + 1), Blocks.GRAVEL, 0);
            this.setBlockState(world, new BlockPos(x + a, y, z + 5), Blocks.GRAVEL, 0);
            this.setBlockState(world, new BlockPos(x + 1, y, z + b), Blocks.GRAVEL, 0);
            this.setBlockState(world, new BlockPos(x + 5, y, z + b), Blocks.GRAVEL, 0);
         }
      }

      this.setBlockState(world, new BlockPos(x + 3, y, z + 3), Blocks.WATER, 0);
      this.setBlockState(world, new BlockPos(x + 3, y + 1, z + 3), Blocks.WATER, 0);
      this.setBlockState(world, new BlockPos(x + 2, y + 2, z + 2), Blocks.OAK_FENCE, 0);
      this.setBlockState(world, new BlockPos(x + 4, y + 2, z + 2), Blocks.OAK_FENCE, 0);
      this.setBlockState(world, new BlockPos(x + 2, y + 2, z + 4), Blocks.OAK_FENCE, 0);
      this.setBlockState(world, new BlockPos(x + 4, y + 2, z + 4), Blocks.OAK_FENCE, 0);
      this.setBlockState(world, new BlockPos(x + 2, y + 3, z + 2), Blocks.OAK_FENCE, 0);
      this.setBlockState(world, new BlockPos(x + 4, y + 3, z + 2), Blocks.OAK_FENCE, 0);
      this.setBlockState(world, new BlockPos(x + 2, y + 3, z + 4), Blocks.OAK_FENCE, 0);
      this.setBlockState(world, new BlockPos(x + 4, y + 3, z + 4), Blocks.OAK_FENCE, 0);
   }

   private void addFarm(GenMaze.MazeSegmentInfo Info, Random rand) {
      World world = Info.world;
      int x = Info.x + 1;
      int z = Info.z + 1;
      int y = Info.y;

      for (int a = 0; a < 7; a++) {
         for (int b = 0; b < 7; b++) {
            this.setBlockState(world, new BlockPos(x + a, y + 1, z + b), Blocks.LOG, 0);
         }
      }

      Block crop1 = Blocks.WHEAT;
      Block crop2 = Blocks.WHEAT;
      if (rand.nextBoolean()) {
         if (rand.nextBoolean()) {
            crop1 = Blocks.CARROTS;
         } else {
            crop1 = Blocks.POTATOES;
         }
      }

      if (rand.nextBoolean()) {
         if (rand.nextBoolean()) {
            crop2 = Blocks.CARROTS;
         } else {
            crop2 = Blocks.POTATOES;
         }
      }

      boolean dir = rand.nextBoolean();

      for (int a = 1; a < 6; a++) {
         if (dir) {
            this.setBlockState(world, new BlockPos(x + a, y + 1, z + 1), Blocks.FARMLAND, 7);
            this.setBlockState(world, new BlockPos(x + a, y + 1, z + 2), Blocks.FARMLAND, 7);
            this.setBlockState(world, new BlockPos(x + a, y + 1, z + 3), Blocks.WATER, 0);
            this.setBlockState(world, new BlockPos(x + a, y + 1, z + 4), Blocks.FARMLAND, 7);
            this.setBlockState(world, new BlockPos(x + a, y + 1, z + 5), Blocks.FARMLAND, 7);
            this.setBlockState(world, new BlockPos(x + a, y + 2, z + 1), crop2, 7);
            this.setBlockState(world, new BlockPos(x + a, y + 2, z + 2), crop2, 7);
            this.setBlockState(world, new BlockPos(x + a, y + 2, z + 4), crop1, 7);
            this.setBlockState(world, new BlockPos(x + a, y + 2, z + 5), crop1, 7);
         } else {
            this.setBlockState(world, new BlockPos(x + 1, y + 1, z + a), Blocks.FARMLAND, 7);
            this.setBlockState(world, new BlockPos(x + 2, y + 1, z + a), Blocks.FARMLAND, 7);
            this.setBlockState(world, new BlockPos(x + 3, y + 1, z + a), Blocks.WATER, 0);
            this.setBlockState(world, new BlockPos(x + 4, y + 1, z + a), Blocks.FARMLAND, 7);
            this.setBlockState(world, new BlockPos(x + 5, y + 1, z + a), Blocks.FARMLAND, 7);
            this.setBlockState(world, new BlockPos(x + 1, y + 2, z + a), crop2, 7);
            this.setBlockState(world, new BlockPos(x + 2, y + 2, z + a), crop2, 7);
            this.setBlockState(world, new BlockPos(x + 4, y + 2, z + a), crop1, 7);
            this.setBlockState(world, new BlockPos(x + 5, y + 2, z + a), crop1, 7);
         }
      }
   }

   private void addTiniHouse(World world, int x, int y, int z) {
      this.addHut(world, x + 2, y, z + 2, x + 4, y + 3, z + 4, true);
      switch (com.electrifiedded.archaiclib.util.FastRandom.shared().nextInt(4)) {
         case 0:
            this.addDoor(world, x + 2, y + 1, z + 3, 0);
            break;
         case 1:
            this.addDoor(world, x + 3, y + 1, z + 2, 1);
            break;
         case 2:
            this.addDoor(world, x + 4, y + 1, z + 3, 2);
            break;
         case 3:
            this.addDoor(world, x + 3, y + 1, z + 4, 3);
      }
   }

   private void addDoor(World world, int x, int y, int z, int dir) {
      this.setBlockState(world, new BlockPos(x, y, z), Blocks.OAK_DOOR, dir);
      this.setBlockState(world, new BlockPos(x, y + 1, z), Blocks.OAK_DOOR, 9);
   }

   private void addHut(World world, int x, int y, int z, int x2, int y2, int z2, boolean roof) {
      for (int i = x; i <= x2; i++) {
         for (int k = z; k <= z2; k++) {
            this.setBlockState(world, new BlockPos(i, y, k), Blocks.COBBLESTONE, 0);

            for (int j = y + 1; j <= y2; j++) {
               int type = 0;
               if (i == x || i == x2) {
                  type++;
               }

               if (j == y || j == y2) {
                  type++;
               }

               if (k == z || k == z2) {
                  type++;
               }

               switch (type) {
                  case 1:
                     if (j != y2 || roof) {
                        this.setBlockState(world, new BlockPos(i, j, k), Blocks.PLANKS, 0);
                     }
                     break;
                  case 2:
                     if (i != x && i != x2) {
                        this.setBlockState(world, new BlockPos(i, j, k), Blocks.LOG, 4);
                     } else if (j != y && j != y2) {
                        this.setBlockState(world, new BlockPos(i, j, k), Blocks.LOG, 0);
                     } else if (k != z && k != z2) {
                        this.setBlockState(world, new BlockPos(i, j, k), Blocks.LOG, 8);
                     }
                     break;
                  case 3:
                     this.setBlockState(world, new BlockPos(i, j, k), Blocks.LOG, 0);
               }
            }
         }
      }
   }

   private void populateMaze(GenMaze.MazeSegmentInfo Info, Random random) {
      if ("Precious Maze".equals(Info.Biome)) {
         this.populatePreciousMaze(Info);
      }

      if ("Village Maze".equals(Info.Biome)) {
         this.populateVillageMaze(Info, random);
      }

      if ("Forest Maze".equals(Info.Biome)) {
         this.populateForestMaze(Info, random);
      }

      if ("Mesa Maze".equals(Info.Biome)) {
         this.populateMesaMaze(Info, random);
      }

      if ("Withering Maze".equals(Info.Biome)) {
         this.populateWitherMaze(Info);
      }

      if ("Firey Maze".equals(Info.Biome)) {
         this.populateNetherMaze(Info, random);
      }

      if ("Ender Maze".equals(Info.Biome)) {
         this.populateEnderMaze(Info, random);
      }

      if ("Desert Maze".equals(Info.Biome)) {
         this.populateDesertMaze(Info, random);
      }

      if ("Cave Maze".equals(Info.Biome)) {
         this.populateCaveMaze(Info, random);
      }
   }

   static {
      SpawnOres.add(new RandomBlock(Blocks.COAL_ORE, 400));
      SpawnOres.add(new RandomBlock(Blocks.IRON_ORE, 200));
      SpawnOres.add(new RandomBlock(Blocks.REDSTONE_ORE, 160));
      SpawnOres.add(new RandomBlock(Blocks.LAPIS_ORE, 160));
      SpawnOres.add(new RandomBlock(Blocks.GOLD_ORE, 120));
      SpawnOres.add(new RandomBlock(Blocks.DIAMOND_ORE, 60));
      SpawnOres.add(new RandomBlock(Blocks.EMERALD_ORE, 20));
      BiomeSelect.add(new RandomString("Precious Maze", 1));
      BiomeSelect.add(new RandomString("Ender Maze", 3));
      BiomeSelect.add(new RandomString("Withering Maze", 4));
      BiomeSelect.add(new RandomString("Firey Maze", 5));
      BiomeSelect.add(new RandomString("Cave Maze", 10));
      BiomeSelect.add(new RandomString("Mesa Maze", 20));
      BiomeSelect.add(new RandomString("Desert Maze", 20));
      BiomeSelect.add(new RandomString("Forest Maze", 25));
      BiomeSelect.add(new RandomString("Village Maze", 30));
   }

   public class MazeBitInfo {
      public int x;
      public int z;
      public int y;
      public World world;
      public float numba;
      public String Biome;

      public MazeBitInfo(World world, Random rand, int x, int y, int z) {
         this.world = world;
         this.x = x;
         this.z = z;
         this.y = y;
         this.numba = rand.nextFloat();
         this.Biome = this.getBiome(rand);
      }

      private String getBiome(Random rand) {
         return ((RandomString)WeightedRandom.getRandomItem(rand, GenMaze.BiomeSelect)).text;
      }
   }

   public class MazeSegmentInfo {
      public int x;
      public int z;
      public int y;
      public int layer;
      public float numba;
      public float numbx;
      public float numbz;
      public float numbx2;
      public float numbz2;
      public float distx;
      public float distz;
      public float distx2;
      public float distz2;
      public boolean stairsUP;
      public boolean stairsDN;
      public String Biome;
      public String BiomeX;
      public String BiomeZ;
      public String BiomeX2;
      public String BiomeZ2;
      public World world;
      public int Cx;
      public int Cz;

      public MazeSegmentInfo(
         GenMaze.MazeBitInfo mazeBitInfo,
         GenMaze.MazeBitInfo mazeBitInfoX,
         GenMaze.MazeBitInfo mazeBitInfoX2,
         GenMaze.MazeBitInfo mazeBitInfoZ,
         GenMaze.MazeBitInfo mazeBitInfoZ2,
         Random rand,
         int Cx,
         int layer,
         int Cz
      ) {
         this.world = mazeBitInfo.world;
         this.Cx = Cx;
         this.Cz = Cz;
         this.layer = layer;
         this.x = mazeBitInfo.x;
         this.z = mazeBitInfo.z;
         this.y = mazeBitInfo.y;
         this.numba = mazeBitInfo.numba;
         this.numbx = mazeBitInfoX.numba;
         this.numbz = mazeBitInfoZ.numba;
         this.numbx2 = mazeBitInfoX2.numba;
         this.numbz2 = mazeBitInfoZ2.numba;
         this.distx = Math.max(this.numba, this.numbx) - Math.min(this.numba, this.numbx);
         this.distz = Math.max(this.numba, this.numbz) - Math.min(this.numba, this.numbz);
         this.distx2 = Math.max(this.numba, this.numbx2) - Math.min(this.numba, this.numbx2);
         this.distz2 = Math.max(this.numba, this.numbz2) - Math.min(this.numba, this.numbz2);
         int walls = 0;
         if (this.distx < 0.3) {
            walls++;
         }

         if (this.distz < 0.3) {
            walls++;
         }

         if (this.distx2 < 0.3) {
            walls++;
         }

         if (this.distz2 < 0.3) {
            walls++;
         }

         if (walls == 3) {
            this.stairsUP = rand.nextInt(10) == 0;
            this.stairsDN = rand.nextInt(10) == 0;
         } else if (walls == 4) {
            this.stairsUP = true;
            this.stairsDN = true;
         } else {
            this.stairsUP = false;
            this.stairsDN = false;
         }

         this.Biome = mazeBitInfo.Biome;
         this.BiomeX = mazeBitInfoX.Biome;
         this.BiomeZ = mazeBitInfoZ.Biome;
         this.BiomeX2 = mazeBitInfoX2.Biome;
         this.BiomeZ2 = mazeBitInfoZ2.Biome;
      }
   }
}
