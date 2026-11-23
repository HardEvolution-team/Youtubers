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

public class GenJungleBace extends GenYoutuberStructure {
   BlockPos Center;
   boolean ChestType;

   @Override
   public boolean hasDemo() {
      return true;
   }

   @Override
   public void genDemo(World world, Random random, BlockPos pos) {
      this.Center = pos;
      int i = pos.getX();
      int j = pos.getY();
      int k = pos.getZ();
      this.remove_vegitation_circle(world, i, k, 15.0);

      for (int a = 0; a < 3; a++) {
         double radius = 10.0;
         float offset = random.nextBoolean() ? (float) (Math.PI / 6) : (float) (-Math.PI / 6);
         this.generatePath(
            world,
            random,
            i + radius * Math.sin((Math.PI * 2) * a / 3.0 + offset),
            j,
            k + radius * Math.cos((Math.PI * 2) * a / 3.0 + offset),
            (float)((Math.PI * 2) * a + offset) / 3.0F,
            0.0F,
            8,
            -3
         );
      }

      this.generatecenter(world, random, i + 1, j, k + 1);
      int number_of_pillars = 6;

      for (int a = 0; a < number_of_pillars; a++) {
         double radius = 10.0;
         this.generatePillar(
            world, random, i + radius * Math.sin((Math.PI * 2) * a / number_of_pillars), j + 1, k + radius * Math.cos((Math.PI * 2) * a / number_of_pillars)
         );
         this.generatePillar(
            world, random, i + radius * Math.sin((Math.PI * 2) * a / number_of_pillars), j + 1, k + radius * Math.cos((Math.PI * 2) * a / number_of_pillars)
         );
      }

      this.GenCircle(world, random, Blocks.PLANKS, 0, i + 1.0F, j + 5.0F, k + 1.0F, 12.0F, 8.0F, 'y');
      this.GenCircle(world, random, Blocks.STONEBRICK, 0, i + 1.0F, j + 5.0F, k + 1.0F, 13.25F, 6.75F, 'y');
      this.GenCircle(world, random, Blocks.STONE_SLAB, 0, i + 1.0F, j + 5.0F, k + 1.0F, 12.05F, 8.05F, 'y');
      this.GenCircle(world, random, Blocks.STONE_SLAB, 3, i + 1.0F, j + 5.0F, k + 1.0F, 10.75F, 9.25F, 'y');
   }

   @Override
   public boolean generate(World world, Random random, BlockPos pos) {
      this.Center = pos;
      int i = pos.getX();
      int j = pos.getY();
      int k = pos.getZ();
      this.remove_vegitation_circle(world, i, k, 15.0);

      for (int a = 0; a < 3; a++) {
         double radius = 10.0;
         float offset = random.nextBoolean() ? (float) (Math.PI / 6) : (float) (-Math.PI / 6);
         this.generatePath(
            world,
            random,
            i + radius * Math.sin((Math.PI * 2) * a / 3.0 + offset),
            j,
            k + radius * Math.cos((Math.PI * 2) * a / 3.0 + offset),
            (float)((Math.PI * 2) * a + offset) / 3.0F,
            0.0F,
            30,
            -3
         );
      }

      this.generatecenter(world, random, i + 1, j, k + 1);
      int number_of_pillars = 6;

      for (int a = 0; a < number_of_pillars; a++) {
         double radius = 10.0;
         this.generatePillar(
            world, random, i + radius * Math.sin((Math.PI * 2) * a / number_of_pillars), j, k + radius * Math.cos((Math.PI * 2) * a / number_of_pillars)
         );
         this.generatePillar(
            world, random, i + radius * Math.sin((Math.PI * 2) * a / number_of_pillars), j, k + radius * Math.cos((Math.PI * 2) * a / number_of_pillars)
         );
      }

      this.GenCircle(world, random, Blocks.PLANKS, 0, i + 1.0F, j + 5.0F, k + 1.0F, 12.0F, 8.0F, 'y');
      this.GenCircle(world, random, Blocks.STONEBRICK, 0, i + 1.0F, j + 5.0F, k + 1.0F, 13.25F, 6.75F, 'y');
      this.GenCircle(world, random, Blocks.STONE_SLAB, 0, i + 1.0F, j + 5.0F, k + 1.0F, 12.05F, 8.05F, 'y');
      this.GenCircle(world, random, Blocks.STONE_SLAB, 3, i + 1.0F, j + 5.0F, k + 1.0F, 10.75F, 9.25F, 'y');
      return false;
   }

   private void generatecenter(World world, Random random, int i, int j, int k) {
      this.GenCircle(world, random, Blocks.STONEBRICK, 0, i, j, k, 3.5F, 2.5F, 'y');
      this.GenCircle(world, random, Blocks.AIR, 0, i, j, k, 2.5F, 0.0F, 'y');
      this.GenCircle(world, random, Blocks.STONEBRICK, 0, i, j - 1.0F, k, 3.5F, 2.5F, 'y');
      this.GenCircle(world, random, Blocks.AIR, 0, i, j - 1.0F, k, 2.5F, 0.0F, 'y');
      this.GenCircle(world, random, Blocks.STONEBRICK, 0, i, j - 2.0F, k, 3.5F, 2.5F, 'y');
      this.GenCircle(world, random, Blocks.AIR, 0, i, j - 2.0F, k, 2.5F, 0.0F, 'y');
      this.GenCircle(world, random, Blocks.STONEBRICK, 0, i, j - 3.0F, k, 3.5F, 2.5F, 'y');
      this.GenCircle(world, random, Blocks.AIR, 0, i, j - 3.0F, k, 2.5F, 0.0F, 'y');
      this.GenCircle(world, random, Blocks.STONEBRICK, 0, i, j - 4.0F, k, 3.5F, 0.0F, 'y');
      this.setBlockState(world, new BlockPos(i + 3, j, k + 2), Blocks.STONE_BRICK_STAIRS, 1);
      this.setBlockState(world, new BlockPos(i + 4, j, k + 1), Blocks.STONE_BRICK_STAIRS, 1);
      this.setBlockState(world, new BlockPos(i + 4, j, k), Blocks.STONE_BRICK_STAIRS, 1);
      this.setBlockState(world, new BlockPos(i + 4, j, k - 1), Blocks.STONE_BRICK_STAIRS, 1);
      this.setBlockState(world, new BlockPos(i + 3, j, k - 2), Blocks.STONE_BRICK_STAIRS, 1);
      this.setBlockState(world, new BlockPos(i - 3, j, k + 2), Blocks.STONE_BRICK_STAIRS, 0);
      this.setBlockState(world, new BlockPos(i - 4, j, k + 1), Blocks.STONE_BRICK_STAIRS, 0);
      this.setBlockState(world, new BlockPos(i - 4, j, k), Blocks.STONE_BRICK_STAIRS, 0);
      this.setBlockState(world, new BlockPos(i - 4, j, k - 1), Blocks.STONE_BRICK_STAIRS, 0);
      this.setBlockState(world, new BlockPos(i - 3, j, k - 2), Blocks.STONE_BRICK_STAIRS, 0);
      this.setBlockState(world, new BlockPos(i + 2, j, k + 3), Blocks.STONE_BRICK_STAIRS, 3);
      this.setBlockState(world, new BlockPos(i + 1, j, k + 4), Blocks.STONE_BRICK_STAIRS, 3);
      this.setBlockState(world, new BlockPos(i, j, k + 4), Blocks.STONE_BRICK_STAIRS, 3);
      this.setBlockState(world, new BlockPos(i - 1, j, k + 4), Blocks.STONE_BRICK_STAIRS, 3);
      this.setBlockState(world, new BlockPos(i - 2, j, k + 3), Blocks.STONE_BRICK_STAIRS, 3);
      this.setBlockState(world, new BlockPos(i + 2, j, k - 3), Blocks.STONE_BRICK_STAIRS, 2);
      this.setBlockState(world, new BlockPos(i + 1, j, k - 4), Blocks.STONE_BRICK_STAIRS, 2);
      this.setBlockState(world, new BlockPos(i, j, k - 4), Blocks.STONE_BRICK_STAIRS, 2);
      this.setBlockState(world, new BlockPos(i - 1, j, k - 4), Blocks.STONE_BRICK_STAIRS, 2);
      this.setBlockState(world, new BlockPos(i - 2, j, k - 3), Blocks.STONE_BRICK_STAIRS, 2);
      this.setBlockState(world, new BlockPos(i + 3, j, k + 1), Blocks.STONE_BRICK_STAIRS, 4);
      this.setBlockState(world, new BlockPos(i + 3, j, k), Blocks.STONE_BRICK_STAIRS, 4);
      this.setBlockState(world, new BlockPos(i + 3, j, k - 1), Blocks.STONE_BRICK_STAIRS, 4);
      this.setBlockState(world, new BlockPos(i + 1, j, k + 3), Blocks.STONE_BRICK_STAIRS, 6);
      this.setBlockState(world, new BlockPos(i, j, k + 3), Blocks.STONE_BRICK_STAIRS, 6);
      this.setBlockState(world, new BlockPos(i - 1, j, k + 3), Blocks.STONE_BRICK_STAIRS, 6);
      this.setBlockState(world, new BlockPos(i + 1, j, k - 3), Blocks.STONE_BRICK_STAIRS, 7);
      this.setBlockState(world, new BlockPos(i, j, k - 3), Blocks.STONE_BRICK_STAIRS, 7);
      this.setBlockState(world, new BlockPos(i - 1, j, k - 3), Blocks.STONE_BRICK_STAIRS, 7);

      for (int a = 0; a < 3; a++) {
         this.ChestType = a % 2 == 0;

         for (int b = 0; b < 3; b++) {
            for (int c = 0; c < 2; c++) {
               this.addChest2(world, random, i - 1 + a, j - 3 + b, k - 3 - c);
               this.addChest2(world, random, i + 3 + c, j - 3 + b, k - 1 + a);
               this.addChest2(world, random, i - 1 + a, j - 3 + b, k + 3 + c);
            }
         }
      }

      this.setBlockState(world, new BlockPos(i - 3, j - 4, k), Blocks.STONEBRICK, 0);
      this.setBlockToAir(world, new BlockPos(i - 3, j - 3, k));
      this.setBlockToAir(world, new BlockPos(i - 3, j - 2, k));
      this.setBlockToAir(world, new BlockPos(i - 3, j - 1, k));
      this.setBlockState(world, new BlockPos(i - 4, j - 4, k), Blocks.STONEBRICK, 0);
      this.setBlockToAir(world, new BlockPos(i - 4, j - 3, k));
      this.setBlockToAir(world, new BlockPos(i - 4, j - 2, k));
      this.setBlockToAir(world, new BlockPos(i - 4, j - 1, k));
      this.GenStairs(world, i - 5, j - 3, k);
   }

   public void addChest2(World world, Random random, int i, int j, int k) {
      if (this.ChestType) {
         this.setBlockState(world, new BlockPos(i, j, k), Blocks.CHEST, 0);
      } else {
         this.setBlockState(world, new BlockPos(i, j, k), Blocks.TRAPPED_CHEST, 0);
      }

      TileEntityChest chestTE = (TileEntityChest)StagedWorld.getTileEntity(world, i, j, k);
      if (chestTE != null) {
         for (int a = 0; a < 27; a++) {
            switch (random.nextInt(12)) {
               case 0:
                  chestTE.setInventorySlotContents(a, new ItemStack(Blocks.STONEBRICK, 64));
                  break;
               case 1:
                  chestTE.setInventorySlotContents(a, new ItemStack(Blocks.STONE_SLAB, 64));
                  break;
               case 2:
                  chestTE.setInventorySlotContents(a, new ItemStack(Items.REDSTONE, 64));
                  break;
               case 3:
                  chestTE.setInventorySlotContents(a, new ItemStack(Blocks.STONEBRICK, 32));
                  break;
               case 4:
                  chestTE.setInventorySlotContents(a, new ItemStack(Blocks.STONEBRICK, 16));
                  break;
               case 5:
                  chestTE.setInventorySlotContents(a, new ItemStack(Items.REDSTONE, 32));
                  break;
               case 6:
                  chestTE.setInventorySlotContents(a, new ItemStack(Items.REDSTONE, 16));
                  break;
               case 7:
                  chestTE.setInventorySlotContents(a, new ItemStack(Blocks.STONEBRICK, 50));
                  break;
               case 8:
                  chestTE.setInventorySlotContents(a, new ItemStack(Blocks.STONEBRICK, 64, 1));
                  break;
               case 9:
                  chestTE.setInventorySlotContents(a, new ItemStack(Blocks.STONEBRICK, 64, 2));
                  break;
               case 10:
                  chestTE.setInventorySlotContents(a, new ItemStack(Blocks.STONEBRICK, 32, 1));
                  break;
               case 11:
                  chestTE.setInventorySlotContents(a, new ItemStack(Blocks.STONEBRICK, 32, 2));
            }
         }

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

   private void GenStairs(World world, int i, int j, int k) {
      this.setBlockState(world, new BlockPos(i, j, k), Blocks.STONE_BRICK_STAIRS, 1);
      this.setBlockToAir(world, new BlockPos(i, j + 1, k));
      this.setBlockToAir(world, new BlockPos(i, j + 2, k));
      this.setBlockToAir(world, new BlockPos(i, j + 3, k));
      if (StagedWorld.getTopSolidOrLiquidBlock(world, i - 1, k) > j + 1) {
         this.GenStairs(world, i - 1, j + 1, k);
      }
   }

   @Override
   public BlockPos getSpawnLoc(BlockPos pos) {
      return pos;
   }

   public void generatePillar(World world, Random random, double i, double j, double k) {
      int height = Math.max(5 + (int)j - this.getTopBlock(world, new BlockPos((int)i, 5, (int)k)).getY() + 1, 0);
      int dropval = (int)j - this.getTopBlock(world, new BlockPos((int)i, 5, (int)k)).getY() + 1;
      float base = 3.0F;
      float top = 1.0F;
      float randoffset = 0.5F;
      float randoffset2 = 1.0F;
      Random rand = new com.electrifiedded.archaiclib.util.FastRandom();

      for (float a = 0.0F; a < height; a++) {
         float r = a * (top - base) / height + base;
         this.GenCircle(
            world,
            random,
            Blocks.LOG,
            3,
            (float)i + rand.nextFloat() * randoffset,
            (float)j + a - dropval,
            (float)k + rand.nextFloat() * randoffset,
            r + rand.nextFloat() * randoffset2 - randoffset2 / 2.0F,
            0.0F,
            'y'
         );
      }
   }

   public void generatehut(World world, Random random, int i, int j, int k) {
      this.remove_vegitation_circle(world, i, k, 2.0);
      this.genBox(world, Blocks.PLANKS, 0, 0, i - 2, j - 1, k - 1, i + 1, j + 2, k + 2);
      this.genBoxAir(world, 0, i - 1, j, k, i, j + 1, k + 1);
      this.setBlockState(world, new BlockPos(i, j, k - 1), Blocks.OAK_DOOR, 1);
      this.setBlockState(world, new BlockPos(i, j + 1, k - 1), Blocks.OAK_DOOR, 9);

      for (int a = -1; a < 2; a++) {
         this.setBlockState(world, new BlockPos(i - 2, j + a, k - 1), Blocks.STONEBRICK, 0);
         this.setBlockState(world, new BlockPos(i - 2, j + a, k + 2), Blocks.STONEBRICK, 0);
         this.setBlockState(world, new BlockPos(i + 1, j + a, k - 1), Blocks.STONEBRICK, 0);
         this.setBlockState(world, new BlockPos(i + 1, j + a, k + 2), Blocks.STONEBRICK, 0);
      }

      this.addChest(world, random, i - 1, j, k + 1);
      this.setBlockState(world, new BlockPos(i + 1, j + 1, k + 1), Blocks.GLASS_PANE, 0);
      this.setBlockState(world, new BlockPos(i - 2, j + 1, k), Blocks.GLASS_PANE, 0);
      this.genBox(world, Blocks.STONEBRICK, 0, 0, i - 2, j + 2, k - 1, i + 1, j + 2, k + 2);
      this.genBox(world, Blocks.STONE_SLAB, 0, 1, i - 2, j + 2, k - 1, i + 1, j + 2, k + 2);
   }

   public void generatePath(World world, Random random, double i, double j, double k, float angle, float changeinangle, int length, int addsidepath) {
      while (angle > Math.PI) {
         angle = (float)(angle - (Math.PI * 2));
      }

      while (angle < -Math.PI) {
         angle = (float)(angle + (Math.PI * 2));
      }

      double Arb = Math.PI * 2.0 / 5.0;
      double ang = Math.atan2(i - this.Center.getX(), k - this.Center.getZ()) + Arb;
      float flow = 0.0F;
      if (angle < ang - 0.3) {
         flow = 0.1F;
      }

      if (angle > ang + 0.3) {
         flow = -0.1F;
      }

      if (angle < ang - Math.PI) {
         flow = -0.1F;
      }

      if (angle > ang + Math.PI) {
         flow = 0.1F;
      }

      int segment_length = 4;
      this.remove_vegitation_circle(world, (int)(i + segment_length * Math.sin(angle)), (int)(k + segment_length * Math.cos(angle)), 2.0);
      if (Math.abs(
            this.getTopBlock(world, new BlockPos((int)i, 5, (int)j)).getY()
               - this.getTopBlock(world, new BlockPos((int)(i + segment_length * Math.sin(angle)), 5, (int)(k + segment_length * Math.cos(angle)))).getY()
         )
         < 10) {
         for (int a = 0; a <= segment_length; a++) {
            this.remove_vegitation_circle(world, (int)(i + a * Math.sin(angle)), (int)(k + a * Math.cos(angle)), 3.0);
            this.replace_top_block_circle(world, random, Blocks.STONEBRICK, 0, i + a * Math.sin(angle), k + a * Math.cos(angle), 1.5, 0.0);
         }

         if (addsidepath == 0 && length % 2 == 1 && random.nextInt(1) == 0) {
            if (random.nextBoolean()) {
               int distfromroad = 5;
               double a = i + distfromroad * Math.sin(angle + (float) (Math.PI / 2));
               double b = k + distfromroad * Math.cos(angle + (float) (Math.PI / 2));
               this.generatehut(world, random, (int)a, this.getTopBlock(world, new BlockPos((int)a, 5, (int)b)).getY() + 1, (int)b);
            } else {
               int distfromroad = 3;
               double a = i + distfromroad * Math.sin(angle - (float) (Math.PI / 2));
               double b = k + distfromroad * Math.cos(angle - (float) (Math.PI / 2));
               this.generatehut(world, random, (int)a, this.getTopBlock(world, new BlockPos((int)a, 5, (int)b)).getY() + 1, (int)b);
            }
         }

         if (addsidepath == 2) {
            changeinangle = (random.nextFloat() - 0.5F) / 2.0F;
            if (Math.signum(changeinangle) != Math.signum(flow)) {
               changeinangle += flow;
            }

            if (random.nextInt(5) == 0) {
               double a = i + segment_length * Math.sin(angle);
               double b = k + segment_length * Math.cos(angle);
               if (random.nextBoolean()) {
                  this.generatePath(
                     world,
                     random,
                     a,
                     this.getTopBlock(world, new BlockPos((int)a, 5, (int)b)).getY() + 1,
                     b,
                     angle + changeinangle / 2.0F + (float) (Math.PI / 2),
                     changeinangle,
                     random.nextInt(length + 1) + 1,
                     -3
                  );
               } else {
                  this.generatePath(
                     world,
                     random,
                     a,
                     this.getTopBlock(world, new BlockPos((int)a, 5, (int)b)).getY() + 1,
                     b,
                     angle + changeinangle / 2.0F - (float) (Math.PI / 2),
                     changeinangle,
                     random.nextInt(length + 1) + 1,
                     -3
                  );
               }
            }
         }

         if (addsidepath != 0) {
            if (addsidepath < 0) {
               addsidepath++;
            } else {
               addsidepath--;
            }
         }

         if (length % 4 == 3) {
            addsidepath = 3;
         }

         if (length > 0) {
            double a = i + segment_length * Math.sin(angle);
            double b = k + segment_length * Math.cos(angle);
            this.generatePath(
               world,
               random,
               a,
               this.getTopBlock(world, new BlockPos((int)a, 5, (int)b)).getY(),
               b,
               angle + changeinangle + flow * 3.0F,
               changeinangle,
               length - 1,
               addsidepath
            );
         }
      }
   }

   private void addChest(World world, Random random, int i, int j, int k) {
      if (this.ChestType) {
         this.setBlockState(world, new BlockPos(i, j, k), Blocks.CHEST, 0);
      } else {
         this.setBlockState(world, new BlockPos(i, j, k), Blocks.TRAPPED_CHEST, 0);
      }

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
