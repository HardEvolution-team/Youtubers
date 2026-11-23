package com.electrifiedded.youtubers.WorldGen;

import com.electrifiedded.archaiclib.world.StagedWorld;
import net.minecraft.util.math.BlockPos;
import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class GenLuckeyBlockBattleArena extends GenYoutuberStructure {
   @Override
   public boolean generate(World world, Random random, BlockPos pos) {
      int i = pos.getX();
      int j = pos.getY();
      int k = pos.getZ();
      double angle1 = random.nextDouble() * 2.0 * Math.PI;
      double angle2 = angle1 + (Math.PI / 4) + random.nextDouble() * 1.5 * Math.PI;
      double angle3 = (angle1 + angle2) / 2.0;
      if (angle2 - angle1 < Math.PI) {
         angle3 += Math.PI;
      }

      this.genPathPlatform(world, random, i, j, k, angle1, 15 + random.nextInt(15));
      this.genPathPlatform(world, random, i, j, k, angle2, 15 + random.nextInt(15));
      this.genPathArena(world, random, i, j, k, angle3, 50 + random.nextInt(40));
      return true;
   }

   public void genPathArena(World world, Random random, int i, int j, int k, double angle, int dist) {
      double i2 = i + Math.sin(angle) * dist;
      double k2 = k + Math.cos(angle) * dist;
      int j2 = StagedWorld.getTopSolidOrLiquidBlock(world, (int)i2, (int)k2) + 1 - 30;
      int j2max = (int)Math.sqrt(Math.pow(i - i2, 2.0) + Math.pow(k - k2, 2.0)) - 25 - 3;
      if (j2 > j + j2max) {
         j2 = StagedWorld.getTopSolidOrLiquidBlock(world, (int)i2, (int)k2) + 1;
      }

      if (j2 > j + j2max) {
         j2 = j + random.nextInt(j2max * 2) - j2max;
      } else if (j2 < j - j2max) {
         j2 = j + random.nextInt(j2max * 2) - j2max;
      }

      if (j2 < 3) {
         j2 = 3;
      }

      if (j2 > world.getActualHeight() - 3) {
         j2 = world.getActualHeight() - 3;
      }

      this.genPath(world, random, i, j - 1, k, i + Math.sin(angle) * 3.0, j - 1, k + Math.cos(angle) * 3.0);
      this.genPath(world, random, i2 - Math.sin(angle) * 25.0, j2 - 1, k2 - Math.cos(angle) * 25.0, i2, j2 - 1, k2);
      this.genPath(world, random, i + Math.sin(angle) * 3.0, j - 1, k + Math.cos(angle) * 3.0, i2 - Math.sin(angle) * 25.0, j2 - 1, k2 - Math.cos(angle) * 25.0);
      this.genArena(world, random, (int)i2, j2, (int)k2, 25);
   }

   private void genArena(World world, Random random, int i, int j, int k, int size) {
      this.genPlatform(world, random, i, j, k, size);
      int n = 6;
      double r = size * 0.8;

      for (int a = 0; a < n; a++) {
         double angle = a * 2 * Math.PI / n;
         this.genLavabucket(world, random, (int)(i + r * Math.sin(angle)), j, (int)(k + r * Math.cos(angle)));
      }
   }

   private void genLavabucket(World world, Random random, int i, int j, int k) {
      this.setBlockState(world, new BlockPos(i, j, k), Blocks.LAVA, 0);
      this.setBlockState(world, new BlockPos(i + 1, j, k), Blocks.NETHER_BRICK_STAIRS, 1);
      this.setBlockState(world, new BlockPos(i - 1, j, k), Blocks.NETHER_BRICK_STAIRS, 0);
      this.setBlockState(world, new BlockPos(i, j, k + 1), Blocks.NETHER_BRICK_STAIRS, 3);
      this.setBlockState(world, new BlockPos(i, j, k - 1), Blocks.NETHER_BRICK_STAIRS, 2);
      this.setBlockState(world, new BlockPos(i + 1, j, k + 1), Blocks.NETHER_BRICK_STAIRS, 1);
      this.setBlockState(world, new BlockPos(i - 1, j, k - 1), Blocks.NETHER_BRICK_STAIRS, 0);
      this.setBlockState(world, new BlockPos(i - 1, j, k + 1), Blocks.NETHER_BRICK_STAIRS, 3);
      this.setBlockState(world, new BlockPos(i + 1, j, k - 1), Blocks.NETHER_BRICK_STAIRS, 2);
   }

   public void genPathPlatform(World world, Random random, int i, int j, int k, double angle, int dist) {
      double i2 = i + Math.sin(angle) * dist;
      double k2 = k + Math.cos(angle) * dist;
      int j2 = StagedWorld.getTopSolidOrLiquidBlock(world, (int)i2, (int)k2) + 1;
      int j2max = (int)Math.sqrt(Math.pow(i - i2, 2.0) + Math.pow(k - k2, 2.0)) - 7 - 3;
      if (j2 > j + j2max) {
         j2 = j + random.nextInt(j2max * 2) - j2max;
      } else if (j2 < j - j2max) {
         j2 = j + random.nextInt(j2max * 2) - j2max;
      }

      if (j2 < 3) {
         j2 = 3;
      }

      if (j2 > world.getActualHeight() - 3) {
         j2 = world.getActualHeight() - 3;
      }

      this.genPath(world, random, i, j - 1, k, i + Math.sin(angle) * 3.0, j - 1, k + Math.cos(angle) * 3.0);
      this.genPath(world, random, i2 - Math.sin(angle) * 7.0, j2 - 1, k2 - Math.cos(angle) * 7.0, i2, j2 - 1, k2);
      this.genPath(world, random, i + Math.sin(angle) * 3.0, j - 1, k + Math.cos(angle) * 3.0, i2 - Math.sin(angle) * 7.0, j2 - 1, k2 - Math.cos(angle) * 7.0);
      this.genPlatform(world, random, (int)i2, j2, (int)k2, 7);
   }

   public void genPath(World world, Random random, double i, double j, double k, double i2, double j2, double k2) {
      int width = 4;
      int a = (int)Math.sqrt(Math.pow(i - i2, 2.0) + Math.pow(j - j2, 2.0) + Math.pow(k - k2, 2.0)) * 2;
      double di = (i2 - i) / a;
      double dj = (j2 - j) / a;
      double dk = (k2 - k) / a;

      for (int q = 0; q < a; q++) {
         for (int w = 0; w < width * 2; w++) {
            this.setBlockState(
               world, new BlockPos((int)(i + di * q - dk * (w - width)), (int)(j + dj * q), (int)(k + dk * q + di * (w - width))), Blocks.STONEBRICK, 0
            );
            this.setBlockToAir(world, new BlockPos((int)(i + di * q - dk * (w - width)), (int)(j + dj * q) + 1, (int)(k + dk * q + di * (w - width))));
            this.setBlockToAir(world, new BlockPos((int)(i + di * q - dk * (w - width)), (int)(j + dj * q) + 2, (int)(k + dk * q + di * (w - width))));
            this.setBlockToAir(world, new BlockPos((int)(i + di * q - dk * (w - width)), (int)(j + dj * q) + 3, (int)(k + dk * q + di * (w - width))));
         }
      }
   }

   public void genPlatform(World world, Random random, int i, int j, int k, int size) {
      this.GenCircle(world, random, Blocks.QUARTZ_BLOCK, 0, i, j - 1, k, size, 0.0F, 'y');
      this.GenCircle(world, random, Blocks.BEDROCK, 0, i, j - 1, k, size - 1, 0.0F, 'y');
      int angleA = random.nextInt(8);
      this.vein(world, random, i, j - 1, k, angleA, 0, size * 2 + 1);
      this.vein(world, random, i, j - 1, k, (angleA + 2) % 8, 0, size * 2 + 1);
      this.vein(world, random, i, j - 1, k, (angleA + 4) % 8, 0, size * 2 + 1);
      this.vein(world, random, i, j - 1, k, (angleA + 6) % 8, 0, size * 2 + 1);
      this.GenCircle(world, random, Blocks.AIR, 0, i, j, k, size, 0.0F, 'y');
      this.GenCircle(world, random, Blocks.AIR, 0, i, j + 1, k, size, 0.0F, 'y');
      this.GenCircle(world, random, Blocks.AIR, 0, i, j + 2, k, size, 0.0F, 'y');
      this.GenCircle(world, random, Blocks.AIR, 0, i, j + 3, k, size, 0.0F, 'y');
      this.GenCircle(world, random, Blocks.AIR, 0, i, j + 4, k, size, 0.0F, 'y');
      this.GenCircle(world, random, Blocks.AIR, 0, i, j + 5, k, size, 0.0F, 'y');
   }

   public void vein(World world, Random random, int i, int j, int k, int angle, int n, int limit) {
      if (n < limit
         && (
            this.getBlockState(world, new BlockPos(i, j, k)) == Blocks.BEDROCK
               || this.getBlockState(world, new BlockPos(i, j, k)) == Blocks.NETHER_BRICK
         )) {
         this.setBlockState(world, new BlockPos(i, j, k), Blocks.NETHER_BRICK, 0);
         switch (angle) {
            case 0:
               if (random.nextBoolean()) {
                  this.vein(world, random, i + 1, j, k, angle, n + 1, limit);
               } else if (random.nextBoolean()) {
                  this.vein(world, random, i + 1, j, k + 1, angle, n + 1, limit);
               } else {
                  this.vein(world, random, i + 1, j, k - 1, angle, n + 1, limit);
               }
               break;
            case 1:
               if (random.nextBoolean()) {
                  this.vein(world, random, i + 1, j, k + 1, angle, n + 1, limit);
               } else if (random.nextBoolean()) {
                  this.vein(world, random, i, j, k + 1, angle, n + 1, limit);
               } else {
                  this.vein(world, random, i + 1, j, k, angle, n + 1, limit);
               }
               break;
            case 2:
               if (random.nextBoolean()) {
                  this.vein(world, random, i, j, k + 1, angle, n + 1, limit);
               } else if (random.nextBoolean()) {
                  this.vein(world, random, i - 1, j, k + 1, angle, n + 1, limit);
               } else {
                  this.vein(world, random, i + 1, j, k + 1, angle, n + 1, limit);
               }
               break;
            case 3:
               if (random.nextBoolean()) {
                  this.vein(world, random, i - 1, j, k + 1, angle, n + 1, limit);
               } else if (random.nextBoolean()) {
                  this.vein(world, random, i - 1, j, k, angle, n + 1, limit);
               } else {
                  this.vein(world, random, i, j, k + 1, angle, n + 1, limit);
               }
               break;
            case 4:
               if (random.nextBoolean()) {
                  this.vein(world, random, i - 1, j, k, angle, n + 1, limit);
               } else if (random.nextBoolean()) {
                  this.vein(world, random, i - 1, j, k - 1, angle, n + 1, limit);
               } else {
                  this.vein(world, random, i - 1, j, k + 1, angle, n + 1, limit);
               }
               break;
            case 5:
               if (random.nextBoolean()) {
                  this.vein(world, random, i - 1, j, k - 1, angle, n + 1, limit);
               } else if (random.nextBoolean()) {
                  this.vein(world, random, i, j, k - 1, angle, n + 1, limit);
               } else {
                  this.vein(world, random, i - 1, j, k, angle, n + 1, limit);
               }
               break;
            case 6:
               if (random.nextBoolean()) {
                  this.vein(world, random, i, j, k - 1, angle, n + 1, limit);
               } else if (random.nextBoolean()) {
                  this.vein(world, random, i + 1, j, k - 1, angle, n + 1, limit);
               } else {
                  this.vein(world, random, i - 1, j, k - 1, angle, n + 1, limit);
               }
               break;
            case 7:
               if (random.nextBoolean()) {
                  this.vein(world, random, i + 1, j, k - 1, angle, n + 1, limit);
               } else if (random.nextBoolean()) {
                  this.vein(world, random, i + 1, j, k, angle, n + 1, limit);
               } else {
                  this.vein(world, random, i, j, k - 1, angle, n + 1, limit);
               }
         }

         if (n % 3 == 0 && random.nextInt(7) == 0) {
            int a = 1 + random.nextInt(2);
            if (random.nextBoolean()) {
               int b = angle + a;
               if (b > 7) {
                  b -= 8;
               }

               this.vein(world, random, i, j, k, b, n, limit);
            } else {
               int b = angle - a;
               if (b < 0) {
                  b += 8;
               }

               this.vein(world, random, i, j, k, b, n, limit);
            }
         }
      }
   }
}
