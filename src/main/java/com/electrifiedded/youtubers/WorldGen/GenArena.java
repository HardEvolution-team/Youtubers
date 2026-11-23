package com.electrifiedded.youtubers.WorldGen;

import com.electrifiedded.archaiclib.world.StagedWorld;
import com.electrifiedded.youtubers.YTItems;
import com.electrifiedded.youtubers.Items.Spawners.ItemMobSpawner;
import net.minecraft.util.math.BlockPos;
import com.electrifiedded.youtubers.RandUtil.RandomItem;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.WeightedRandom;
import net.minecraft.util.WeightedRandom.Item;
import net.minecraft.world.World;

public class GenArena extends GenYoutuberStructure {
   public static List<Item> SpawnEggs = new ArrayList<>();
   public static List<Item> SpawnEggs2 = new ArrayList<>();

   @Override
   public boolean generate(World world, Random random, BlockPos pos) {
      int i = pos.getX();
      int j = pos.getY();
      int k = pos.getZ();
      this.remove_vegitation_circle(world, i, j, 15.0);
      this.GenCircle(world, random, Blocks.PLANKS, 1, i, j - 1, k, 8.55F, 0.0F, 'y');
      this.GenCircle(world, random, Blocks.STONEBRICK, 3, i, j - 1, k, 4.0F, 0.0F, 'y');
      this.GenCircle(world, random, Blocks.STONE_SLAB, 5, i, j, k, 4.0F, 3.125F, 'y');
      this.GenCircle(world, random, Blocks.LOG, 1, i, j, k, 9.55F, 8.55F, 'y');
      this.GenCircle(world, random, Blocks.LOG, 1, i, j + 1, k, 9.55F, 8.55F, 'y');
      this.GenCircle(world, random, Blocks.LOG, 1, i, j + 2, k, 9.55F, 8.55F, 'y');
      this.GenCircle(world, random, Blocks.LOG, 1, i, j + 3, k, 9.55F, 8.45F, 'y');
      this.GenCircle(world, random, Blocks.LOG, 1, i, j + 4, k, 9.55F, 8.45F, 'y');
      this.GenCircle(world, random, Blocks.GLOWSTONE, 1, i, j + 4, k, 8.45F, 8.2F, 'y');
      this.GenCircle(world, random, Blocks.STONEBRICK, 0, i, j + 5, k, 10.25F, 6.45F, 'y');
      this.GenCircle(world, random, Blocks.LOG, 1, i, j + 6, k, 9.55F, 8.55F, 'y');
      this.GenCircle(world, random, Blocks.STONEBRICK, 0, i, j + 7, k, 10.25F, 8.45F, 'y');
      this.boxReplace(world, Blocks.PLANKS, 1, Blocks.PLANKS, 2, i - 9, j - 2, k - 9, i + 9, j, k + 9, "chekers");
      StagedWorld.setBlock(world, i, j - 1, k, Blocks.LIT_REDSTONE_LAMP, 0, 3);
      StagedWorld.setBlock(world, i, j - 2, k, Blocks.REDSTONE_BLOCK, 0, 3);
      StagedWorld.setBlock(world, i + 1, j - 1, k + 1, Blocks.LIT_REDSTONE_LAMP, 0, 3);
      StagedWorld.setBlock(world, i + 1, j - 2, k + 1, Blocks.REDSTONE_BLOCK, 0, 3);
      StagedWorld.setBlock(world, i + 2, j - 1, k + 2, Blocks.LIT_REDSTONE_LAMP, 0, 3);
      StagedWorld.setBlock(world, i + 2, j - 2, k + 2, Blocks.REDSTONE_BLOCK, 0, 3);
      StagedWorld.setBlock(world, i - 1, j - 1, k + 1, Blocks.LIT_REDSTONE_LAMP, 0, 3);
      StagedWorld.setBlock(world, i - 1, j - 2, k + 1, Blocks.REDSTONE_BLOCK, 0, 3);
      StagedWorld.setBlock(world, i - 2, j - 1, k + 2, Blocks.LIT_REDSTONE_LAMP, 0, 3);
      StagedWorld.setBlock(world, i - 2, j - 2, k + 2, Blocks.REDSTONE_BLOCK, 0, 3);
      StagedWorld.setBlock(world, i + 1, j - 1, k - 1, Blocks.LIT_REDSTONE_LAMP, 0, 3);
      StagedWorld.setBlock(world, i + 1, j - 2, k - 1, Blocks.REDSTONE_BLOCK, 0, 3);
      StagedWorld.setBlock(world, i + 2, j - 1, k - 2, Blocks.LIT_REDSTONE_LAMP, 0, 3);
      StagedWorld.setBlock(world, i + 2, j - 2, k - 2, Blocks.REDSTONE_BLOCK, 0, 3);
      StagedWorld.setBlock(world, i - 1, j - 1, k - 1, Blocks.LIT_REDSTONE_LAMP, 0, 3);
      StagedWorld.setBlock(world, i - 1, j - 2, k - 1, Blocks.REDSTONE_BLOCK, 0, 3);
      StagedWorld.setBlock(world, i - 2, j - 1, k - 2, Blocks.LIT_REDSTONE_LAMP, 0, 3);
      StagedWorld.setBlock(world, i - 2, j - 2, k - 2, Blocks.REDSTONE_BLOCK, 0, 3);
      StagedWorld.setBlock(world, i + 6, j + 4, k + 6, Blocks.GLOWSTONE, 0, 3);
      StagedWorld.setBlock(world, i - 6, j + 4, k + 6, Blocks.GLOWSTONE, 0, 3);
      StagedWorld.setBlock(world, i + 6, j + 4, k - 6, Blocks.GLOWSTONE, 0, 3);
      StagedWorld.setBlock(world, i - 6, j + 4, k - 6, Blocks.GLOWSTONE, 0, 3);
      StagedWorld.setBlock(world, i + 8, j + 6, k + 1, Blocks.SPRUCE_STAIRS, 0, 3);
      StagedWorld.setBlock(world, i + 8, j + 6, k, Blocks.SPRUCE_STAIRS, 0, 3);
      StagedWorld.setBlock(world, i + 8, j + 6, k - 1, Blocks.SPRUCE_STAIRS, 0, 3);
      StagedWorld.setBlock(world, i - 8, j + 6, k + 1, Blocks.SPRUCE_STAIRS, 1, 3);
      StagedWorld.setBlock(world, i - 8, j + 6, k, Blocks.SPRUCE_STAIRS, 1, 3);
      StagedWorld.setBlock(world, i - 8, j + 6, k - 1, Blocks.SPRUCE_STAIRS, 1, 3);
      StagedWorld.setBlock(world, i + 1, j + 6, k + 8, Blocks.SPRUCE_STAIRS, 2, 3);
      StagedWorld.setBlock(world, i, j + 6, k + 8, Blocks.SPRUCE_STAIRS, 2, 3);
      StagedWorld.setBlock(world, i - 1, j + 6, k + 8, Blocks.SPRUCE_STAIRS, 2, 3);
      StagedWorld.setBlock(world, i + 1, j + 6, k - 8, Blocks.SPRUCE_STAIRS, 3, 3);
      StagedWorld.setBlock(world, i, j + 6, k - 8, Blocks.SPRUCE_STAIRS, 3, 3);
      StagedWorld.setBlock(world, i - 1, j + 6, k - 8, Blocks.SPRUCE_STAIRS, 3, 3);
      StagedWorld.setBlock(world, i + 10, j + 8, k + 1, Blocks.SPRUCE_STAIRS, 0, 3);
      StagedWorld.setBlock(world, i + 10, j + 8, k, Blocks.SPRUCE_STAIRS, 0, 3);
      StagedWorld.setBlock(world, i + 10, j + 8, k - 1, Blocks.SPRUCE_STAIRS, 0, 3);
      StagedWorld.setBlock(world, i - 10, j + 8, k + 1, Blocks.SPRUCE_STAIRS, 1, 3);
      StagedWorld.setBlock(world, i - 10, j + 8, k, Blocks.SPRUCE_STAIRS, 1, 3);
      StagedWorld.setBlock(world, i - 10, j + 8, k - 1, Blocks.SPRUCE_STAIRS, 1, 3);
      StagedWorld.setBlock(world, i + 1, j + 8, k + 10, Blocks.SPRUCE_STAIRS, 2, 3);
      StagedWorld.setBlock(world, i, j + 8, k + 10, Blocks.SPRUCE_STAIRS, 2, 3);
      StagedWorld.setBlock(world, i - 1, j + 8, k + 10, Blocks.SPRUCE_STAIRS, 2, 3);
      StagedWorld.setBlock(world, i + 1, j + 8, k - 10, Blocks.SPRUCE_STAIRS, 3, 3);
      StagedWorld.setBlock(world, i, j + 8, k - 10, Blocks.SPRUCE_STAIRS, 3, 3);
      StagedWorld.setBlock(world, i - 1, j + 8, k - 10, Blocks.SPRUCE_STAIRS, 3, 3);
      this.AddTower(world, i + 6, j + 8, k + 6, 0);
      this.AddTower(world, i - 6, j + 8, k + 6, 1);
      this.AddTower(world, i + 6, j + 8, k - 6, 2);
      this.AddTower(world, i - 6, j + 8, k - 6, 3);
      this.AddDoor(world, i, j, k + 8, 0);
      this.AddDoor(world, i, j, k - 8, 0);
      this.AddDoor(world, i + 8, j, k, 1);
      this.AddDoor(world, i - 8, j, k, 2);

      for (int a = 0; a < 2; a++) {
         this.addChest(world, random, i + 6, j + a, k + 6);
         this.addChest(world, random, i + 6, j + a, k - 6);
         this.addChest(world, random, i - 6, j + a, k + 6);
         this.addChest(world, random, i - 6, j + a, k - 6);
      }

      this.addChest2(world, random, i, j, k);
      return false;
   }

   private void addChest2(World world, Random random, int i, int j, int k) {
      StagedWorld.setBlock(world, i, j, k, Blocks.CHEST, 0, 3);
      TileEntityChest chestTE = (TileEntityChest)StagedWorld.getTileEntity(world, i, j, k);
      if (chestTE != null) {
         RandomItem RItem1 = (RandomItem)WeightedRandom.getRandomItem(random, SpawnEggs);
         if (RItem1.item == YTItems.MobSpawner) {
            chestTE.setInventorySlotContents(14, new ItemStack(RItem1.item, 64, ItemMobSpawner.getDamage(RItem1.damage)));
         } else {
            chestTE.setInventorySlotContents(14, new ItemStack(RItem1.item, 64, RItem1.damage));
         }

         RandomItem RItem2 = (RandomItem)WeightedRandom.getRandomItem(random, SpawnEggs2);
         if (RItem2.item == YTItems.MobSpawner) {
            chestTE.setInventorySlotContents(12, new ItemStack(RItem2.item, random.nextInt(5) + 3, ItemMobSpawner.getDamage(RItem2.damage)));
         } else {
            chestTE.setInventorySlotContents(12, new ItemStack(RItem2.item, random.nextInt(5) + 3, RItem2.damage));
         }
      }
   }

   private void addChest(World world, Random random, int i, int j, int k) {
      StagedWorld.setBlock(world, i, j, k, Blocks.CHEST, 0, 3);
      TileEntityChest chestTE = (TileEntityChest)StagedWorld.getTileEntity(world, i, j, k);
      if (chestTE != null) {
         int numb = random.nextInt(10);

         for (int a = 0; a < numb; a++) {
            chestTE.setInventorySlotContents(random.nextInt(27), new ItemStack(YTItems.Money[random.nextInt(4) + 1], random.nextInt(8) + 1));
         }

         for (int a = 0; a < 3; a++) {
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

   private void AddDoor(World world, int i, int j, int k, int type) {
      if (type == 0) {
         StagedWorld.setBlock(world, i + 2, j, k, Blocks.STONEBRICK, 3, 3);
         StagedWorld.setBlock(world, i + 1, j, k, Blocks.IRON_BARS, 0, 3);
         StagedWorld.setBlock(world, i, j, k, Blocks.IRON_BARS, 0, 3);
         StagedWorld.setBlock(world, i - 1, j, k, Blocks.IRON_BARS, 0, 3);
         StagedWorld.setBlock(world, i - 2, j, k, Blocks.STONEBRICK, 3, 3);
         StagedWorld.setBlock(world, i + 2, j + 1, k, Blocks.STONEBRICK, 3, 3);
         StagedWorld.setBlock(world, i + 1, j + 1, k, Blocks.IRON_BARS, 0, 3);
         StagedWorld.setBlock(world, i, j + 1, k, Blocks.IRON_BARS, 0, 3);
         StagedWorld.setBlock(world, i - 1, j + 1, k, Blocks.IRON_BARS, 0, 3);
         StagedWorld.setBlock(world, i - 2, j + 1, k, Blocks.STONEBRICK, 3, 3);
         StagedWorld.setBlock(world, i + 1, j + 2, k, Blocks.STONEBRICK, 3, 3);
         StagedWorld.setBlock(world, i, j + 2, k, Blocks.IRON_BARS, 0, 3);
         StagedWorld.setBlock(world, i - 1, j + 2, k, Blocks.STONEBRICK, 3, 3);
         StagedWorld.setBlock(world, i, j + 3, k, Blocks.STONEBRICK, 3, 3);
      } else {
         StagedWorld.setBlock(world, i, j, k + 2, Blocks.STONEBRICK, 3, 3);
         StagedWorld.setBlock(world, i, j, k + 1, Blocks.NETHER_BRICK_FENCE, 0, 3);
         StagedWorld.setBlock(world, i, j, k, Blocks.NETHER_BRICK_FENCE, 0, 3);
         StagedWorld.setBlock(world, i, j, k - 1, Blocks.NETHER_BRICK_FENCE, 0, 3);
         StagedWorld.setBlock(world, i, j, k - 2, Blocks.STONEBRICK, 3, 3);
         StagedWorld.setBlock(world, i, j + 1, k + 2, Blocks.STONEBRICK, 3, 3);
         StagedWorld.setBlock(world, i, j + 1, k + 1, Blocks.NETHER_BRICK_FENCE, 0, 3);
         StagedWorld.setBlock(world, i, j + 1, k, Blocks.NETHER_BRICK_FENCE, 0, 3);
         StagedWorld.setBlock(world, i, j + 1, k - 1, Blocks.NETHER_BRICK_FENCE, 0, 3);
         StagedWorld.setBlock(world, i, j + 1, k - 2, Blocks.STONEBRICK, 3, 3);
         StagedWorld.setBlock(world, i, j + 2, k + 1, Blocks.STONEBRICK, 3, 3);
         StagedWorld.setBlock(world, i, j + 2, k, Blocks.NETHER_BRICK_FENCE, 0, 3);
         StagedWorld.setBlock(world, i, j + 2, k - 1, Blocks.STONEBRICK, 3, 3);
         StagedWorld.setBlock(world, i, j + 3, k, Blocks.STONEBRICK, 3, 3);
         int a = -1;
         if (type == 1) {
            a = 1;
         }

         StagedWorld.setBlockToAir(world, i + a, j, k + 2);
         StagedWorld.setBlockToAir(world, i + a, j, k + 1);
         StagedWorld.setBlockToAir(world, i + a, j, k);
         StagedWorld.setBlockToAir(world, i + a, j, k - 1);
         StagedWorld.setBlockToAir(world, i + a, j, k - 2);
         StagedWorld.setBlockToAir(world, i + a, j + 1, k + 2);
         StagedWorld.setBlockToAir(world, i + a, j + 1, k + 1);
         StagedWorld.setBlockToAir(world, i + a, j + 1, k);
         StagedWorld.setBlockToAir(world, i + a, j + 1, k - 1);
         StagedWorld.setBlockToAir(world, i + a, j + 1, k - 2);
         StagedWorld.setBlockToAir(world, i + a, j + 2, k + 1);
         StagedWorld.setBlockToAir(world, i + a, j + 2, k);
         StagedWorld.setBlockToAir(world, i + a, j + 2, k - 1);
         StagedWorld.setBlockToAir(world, i + a, j + 3, k);
         this.boxReplace(world, Blocks.LOG, 1, Blocks.STONEBRICK, 0, i + a, j, k - 3, i + a, j + 2, k + 3, "all");
         this.boxReplace(world, Blocks.LOG, 1, Blocks.STONEBRICK, 0, i + a, j + 3, k - 2, i + a, j + 3, k + 2, "all");
      }
   }

   private void AddTower(World world, int i, int j, int k, int number) {
      StagedWorld.setBlock(world, i, j, k, Blocks.STONEBRICK, 0, 3);
      StagedWorld.setBlock(world, i, j + 1, k, Blocks.STONEBRICK, 0, 3);
      StagedWorld.setBlock(world, i, j + 2, k, Blocks.STONEBRICK, 0, 3);
      StagedWorld.setBlock(world, i, j + 3, k, Blocks.LIT_REDSTONE_LAMP, 0, 3);
      StagedWorld.setBlock(world, i, j + 4, k, Blocks.STONEBRICK, 0, 3);
      switch (number) {
         case 0:
            StagedWorld.setBlock(world, i + 1, j + 2, k, Blocks.STONEBRICK, 0, 3);
            StagedWorld.setBlock(world, i + 1, j + 3, k, Blocks.STONEBRICK, 0, 3);
            StagedWorld.setBlock(world, i + 1, j + 2, k + 1, Blocks.STONEBRICK, 0, 3);
            StagedWorld.setBlock(world, i + 1, j + 3, k + 1, Blocks.STONEBRICK, 0, 3);
            StagedWorld.setBlock(world, i, j + 2, k + 1, Blocks.STONEBRICK, 0, 3);
            StagedWorld.setBlock(world, i, j + 3, k + 1, Blocks.STONEBRICK, 0, 3);
            StagedWorld.setBlock(world, i + 1, j + 4, k, Blocks.LEVER, 9, 3);
            StagedWorld.setBlock(world, i, j + 4, k + 1, Blocks.LEVER, 11, 3);
            break;
         case 1:
            StagedWorld.setBlock(world, i - 1, j + 2, k, Blocks.STONEBRICK, 0, 3);
            StagedWorld.setBlock(world, i - 1, j + 3, k, Blocks.STONEBRICK, 0, 3);
            StagedWorld.setBlock(world, i - 1, j + 2, k + 1, Blocks.STONEBRICK, 0, 3);
            StagedWorld.setBlock(world, i - 1, j + 3, k + 1, Blocks.STONEBRICK, 0, 3);
            StagedWorld.setBlock(world, i, j + 2, k + 1, Blocks.STONEBRICK, 0, 3);
            StagedWorld.setBlock(world, i, j + 3, k + 1, Blocks.STONEBRICK, 0, 3);
            StagedWorld.setBlock(world, i - 1, j + 4, k, Blocks.LEVER, 10, 3);
            StagedWorld.setBlock(world, i, j + 4, k + 1, Blocks.LEVER, 11, 3);
            break;
         case 2:
            StagedWorld.setBlock(world, i + 1, j + 2, k, Blocks.STONEBRICK, 0, 3);
            StagedWorld.setBlock(world, i + 1, j + 3, k, Blocks.STONEBRICK, 0, 3);
            StagedWorld.setBlock(world, i + 1, j + 2, k - 1, Blocks.STONEBRICK, 0, 3);
            StagedWorld.setBlock(world, i + 1, j + 3, k - 1, Blocks.STONEBRICK, 0, 3);
            StagedWorld.setBlock(world, i, j + 2, k - 1, Blocks.STONEBRICK, 0, 3);
            StagedWorld.setBlock(world, i, j + 3, k - 1, Blocks.STONEBRICK, 0, 3);
            StagedWorld.setBlock(world, i + 1, j + 4, k, Blocks.LEVER, 9, 3);
            StagedWorld.setBlock(world, i, j + 4, k - 1, Blocks.LEVER, 12, 3);
            break;
         case 3:
            StagedWorld.setBlock(world, i - 1, j + 2, k, Blocks.STONEBRICK, 0, 3);
            StagedWorld.setBlock(world, i - 1, j + 3, k, Blocks.STONEBRICK, 0, 3);
            StagedWorld.setBlock(world, i - 1, j + 2, k - 1, Blocks.STONEBRICK, 0, 3);
            StagedWorld.setBlock(world, i - 1, j + 3, k - 1, Blocks.STONEBRICK, 0, 3);
            StagedWorld.setBlock(world, i, j + 2, k - 1, Blocks.STONEBRICK, 0, 3);
            StagedWorld.setBlock(world, i, j + 3, k - 1, Blocks.STONEBRICK, 0, 3);
            StagedWorld.setBlock(world, i - 1, j + 4, k, Blocks.LEVER, 10, 3);
            StagedWorld.setBlock(world, i, j + 4, k - 1, Blocks.LEVER, 12, 3);
      }
   }

   static {
      SpawnEggs.add(new RandomItem(Items.SPAWN_EGG, 50, 5));
      SpawnEggs.add(new RandomItem(Items.SPAWN_EGG, 51, 5));
      SpawnEggs.add(new RandomItem(Items.SPAWN_EGG, 52, 5));
      SpawnEggs.add(new RandomItem(Items.SPAWN_EGG, 54, 5));
      SpawnEggs.add(new RandomItem(Items.SPAWN_EGG, 55, 5));
      SpawnEggs.add(new RandomItem(Items.SPAWN_EGG, 57, 5));
      SpawnEggs.add(new RandomItem(Items.SPAWN_EGG, 58, 5));
      SpawnEggs.add(new RandomItem(Items.SPAWN_EGG, 59, 5));
      SpawnEggs.add(new RandomItem(Items.SPAWN_EGG, 60, 5));
      SpawnEggs.add(new RandomItem(Items.SPAWN_EGG, 62, 5));
      SpawnEggs.add(new RandomItem(Items.SPAWN_EGG, 66, 5));
      SpawnEggs.add(new RandomItem(Items.SPAWN_EGG, 94, 1));
      SpawnEggs2.add(new RandomItem(Items.SPAWN_EGG, 65, 5));
      SpawnEggs2.add(new RandomItem(Items.SPAWN_EGG, 90, 5));
      SpawnEggs2.add(new RandomItem(Items.SPAWN_EGG, 91, 5));
      SpawnEggs2.add(new RandomItem(Items.SPAWN_EGG, 92, 5));
      SpawnEggs2.add(new RandomItem(Items.SPAWN_EGG, 93, 5));
      SpawnEggs2.add(new RandomItem(Items.SPAWN_EGG, 96, 5));
   }
}
