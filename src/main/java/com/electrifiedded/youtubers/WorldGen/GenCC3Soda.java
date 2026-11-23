package com.electrifiedded.youtubers.WorldGen;

import com.electrifiedded.archaiclib.world.StagedWorld;
import com.electrifiedded.youtubers.YTBlocks;
import net.minecraft.util.math.BlockPos;
import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class GenCC3Soda extends GenYoutuberStructure {
   Random rand = new com.electrifiedded.archaiclib.util.FastRandom();

   @Override
   public boolean generate(World world, Random random, BlockPos pos) {
      int i = pos.getX();
      int j = pos.getY();
      int k = pos.getZ();
      this.GenCircle(world, random, Blocks.QUARTZ_BLOCK, 0, i, j - 1, k, 3.0F, 0.0F, 'y');

      for (int a = 0; a <= 10; a++) {
         this.GenCircle(world, random, Blocks.QUARTZ_BLOCK, 0, i, j + a, k, 3.0F + 0.1F * a, 2.0F + 0.1F * a, 'y');
      }

      this.GenCircle(world, random, Blocks.STAINED_GLASS, 0, i, j + 10, k, 4.5F, 4.0F, 'y');
      this.GenCircle(world, random, Blocks.STAINED_GLASS, 0, i, j + 11, k, 3.5F, 0.0F, 'y');

      for (int a = 2; a <= 13; a++) {
         this.setBlockState(world, pos.up(a), YTBlocks.Straw, 0);
      }

      this.setBlockState(world, pos.add(0, 0, 0), Blocks.LADDER, 4);
      this.setBlockState(world, pos.add(0, 1, 0), Blocks.LADDER, 4);
      this.setBlockState(world, pos.add(1, 0, 0), Blocks.QUARTZ_BLOCK, 0);
      this.setBlockState(world, pos.add(1, 1, 0), Blocks.QUARTZ_BLOCK, 0);
      this.setBlockState(world, pos.add(2, 1, 0), Blocks.QUARTZ_BLOCK, 0);
      this.setBlockState(world, pos.add(-1, 13, 0), Blocks.LADDER, 4);
      this.setBlockToAir(world, pos.add(-2, 0, 0));
      this.setBlockToAir(world, pos.add(-3, 0, 0));
      this.setBlockToAir(world, pos.add(-3, 1, 0));
      this.Flow(world, pos.add(0, 9, -4), 100, 0);
      return true;
   }

   public void Flow(World world, BlockPos pos, int lim, int flow) {
      if (lim >= 0 && flow < 7 && StagedWorld.getBlock(world, pos.getX(), pos.getY(), pos.getZ()) != Blocks.STAINED_GLASS) {
         this.setBlockState(world, pos, Blocks.STAINED_GLASS, 12);
         if (!this.getBlockState(world, pos.add(0, -1, 0)).getDefaultState().isNormalCube()) {
            System.out.println("down");
            this.Flow(world, pos.add(0, -1, 0), lim - 1, 0);
         } else {
            boolean N = true;
            boolean S = true;
            boolean E = true;
            boolean W = true;
            int n = 0;
            int s = 0;
            int e = 0;
            int w = 0;

            for (int a = 1; a < 5; a++) {
               if (N) {
                  if (this.getBlockState(world, pos.add(a, 0, 0)).getDefaultState().isNormalCube()) {
                     n = 1000000;
                     N = false;
                  } else if (!this.getBlockState(world, pos.add(a, -1, 0)).getDefaultState().isNormalCube()) {
                     n = a;
                     N = false;
                  }
               }

               if (S) {
                  if (this.getBlockState(world, pos.add(-a, 0, 0)).getDefaultState().isNormalCube()) {
                     s = 1000000;
                     S = false;
                  } else if (!this.getBlockState(world, pos.add(-a, -1, 0)).getDefaultState().isNormalCube()) {
                     s = a;
                     S = false;
                  }
               }

               if (E) {
                  if (this.getBlockState(world, pos.add(0, 0, a)).getDefaultState().isNormalCube()) {
                     e = 1000000;
                     E = false;
                  } else if (!this.getBlockState(world, pos.add(0, -1, a)).getDefaultState().isNormalCube()) {
                     e = a;
                     E = false;
                  }
               }

               if (W) {
                  if (this.getBlockState(world, pos.add(0, 0, -a)).getDefaultState().isNormalCube()) {
                     w = 1000000;
                     W = false;
                  } else if (!this.getBlockState(world, pos.add(0, -1, -a)).getDefaultState().isNormalCube()) {
                     w = a;
                     W = false;
                  }
               }
            }

            if (N) {
               n = 1000000;
               N = false;
            }

            if (S) {
               s = 1000000;
               S = false;
            }

            if (E) {
               e = 1000000;
               E = false;
            }

            if (W) {
               w = 1000000;
               W = false;
            }

            int val = Math.min(n, Math.min(s, Math.min(e, w)));
            if (val > 100) {
               switch (this.rand.nextInt(4)) {
                  case 0:
                     if (!this.getBlockState(world, pos.add(1, 0, 0)).getDefaultState().isNormalCube()) {
                        this.Flow(world, pos.add(1, 0, 0), lim - 1, flow + 1);
                     }

                     if (!this.getBlockState(world, pos.add(-1, 0, 0)).getDefaultState().isNormalCube()) {
                        this.Flow(world, pos.add(-1, 0, 0), lim - 1, flow + 1);
                     }

                     if (!this.getBlockState(world, pos.add(0, 0, 1)).getDefaultState().isNormalCube()) {
                        this.Flow(world, pos.add(0, 0, 1), lim - 1, flow + 1);
                     }

                     if (!this.getBlockState(world, pos.add(0, 0, -1)).getDefaultState().isNormalCube()) {
                        this.Flow(world, pos.add(0, 0, -1), lim - 1, flow + 1);
                     }
                     break;
                  case 1:
                     if (!this.getBlockState(world, pos.add(-1, 0, 0)).getDefaultState().isNormalCube()) {
                        this.Flow(world, pos.add(-1, 0, 0), lim - 1, flow + 1);
                     }

                     if (!this.getBlockState(world, pos.add(0, 0, 1)).getDefaultState().isNormalCube()) {
                        this.Flow(world, pos.add(0, 0, 1), lim - 1, flow + 1);
                     }

                     if (!this.getBlockState(world, pos.add(0, 0, -1)).getDefaultState().isNormalCube()) {
                        this.Flow(world, pos.add(0, 0, -1), lim - 1, flow + 1);
                     }

                     if (!this.getBlockState(world, pos.add(1, 0, 0)).getDefaultState().isNormalCube()) {
                        this.Flow(world, pos.add(1, 0, 0), lim - 1, flow + 1);
                     }
                     break;
                  case 2:
                     if (!this.getBlockState(world, pos.add(0, 0, 1)).getDefaultState().isNormalCube()) {
                        this.Flow(world, pos.add(0, 0, 1), lim - 1, flow + 1);
                     }

                     if (!this.getBlockState(world, pos.add(0, 0, -1)).getDefaultState().isNormalCube()) {
                        this.Flow(world, pos.add(0, 0, -1), lim - 1, flow + 1);
                     }

                     if (!this.getBlockState(world, pos.add(1, 0, 0)).getDefaultState().isNormalCube()) {
                        this.Flow(world, pos.add(1, 0, 0), lim - 1, flow + 1);
                     }

                     if (!this.getBlockState(world, pos.add(-1, 0, 0)).getDefaultState().isNormalCube()) {
                        this.Flow(world, pos.add(-1, 0, 0), lim - 1, flow + 1);
                     }
                     break;
                  case 3:
                     if (!this.getBlockState(world, pos.add(0, 0, -1)).getDefaultState().isNormalCube()) {
                        this.Flow(world, pos.add(0, 0, -1), lim - 1, flow + 1);
                     }

                     if (!this.getBlockState(world, pos.add(1, 0, 0)).getDefaultState().isNormalCube()) {
                        this.Flow(world, pos.add(1, 0, 0), lim - 1, flow + 1);
                     }

                     if (!this.getBlockState(world, pos.add(-1, 0, 0)).getDefaultState().isNormalCube()) {
                        this.Flow(world, pos.add(-1, 0, 0), lim - 1, flow + 1);
                     }

                     if (!this.getBlockState(world, pos.add(0, 0, 1)).getDefaultState().isNormalCube()) {
                        this.Flow(world, pos.add(0, 0, 1), lim - 1, flow + 1);
                     }
               }
            } else {
               switch (this.rand.nextInt(4)) {
                  case 0:
                     if (n == val) {
                        this.Flow(world, pos.add(1, 0, 0), lim - 1, flow + 1);
                     }

                     if (s == val) {
                        this.Flow(world, pos.add(-1, 0, 0), lim - 1, flow + 1);
                     }

                     if (e == val) {
                        this.Flow(world, pos.add(0, 0, 1), lim - 1, flow + 1);
                     }

                     if (w == val) {
                        this.Flow(world, pos.add(0, 0, -1), lim - 1, flow + 1);
                     }
                     break;
                  case 1:
                     if (s == val) {
                        this.Flow(world, pos.add(-1, 0, 0), lim - 1, flow + 1);
                     }

                     if (e == val) {
                        this.Flow(world, pos.add(0, 0, 1), lim - 1, flow + 1);
                     }

                     if (w == val) {
                        this.Flow(world, pos.add(0, 0, -1), lim - 1, flow + 1);
                     }

                     if (n == val) {
                        this.Flow(world, pos.add(1, 0, 0), lim - 1, flow + 1);
                     }
                     break;
                  case 2:
                     if (e == val) {
                        this.Flow(world, pos.add(0, 0, 1), lim - 1, flow + 1);
                     }

                     if (w == val) {
                        this.Flow(world, pos.add(0, 0, -1), lim - 1, flow + 1);
                     }

                     if (n == val) {
                        this.Flow(world, pos.add(1, 0, 0), lim - 1, flow + 1);
                     }

                     if (s == val) {
                        this.Flow(world, pos.add(-1, 0, 0), lim - 1, flow + 1);
                     }
                     break;
                  case 3:
                     if (w == val) {
                        this.Flow(world, pos.add(0, 0, -1), lim - 1, flow + 1);
                     }

                     if (n == val) {
                        this.Flow(world, pos.add(1, 0, 0), lim - 1, flow + 1);
                     }

                     if (s == val) {
                        this.Flow(world, pos.add(-1, 0, 0), lim - 1, flow + 1);
                     }

                     if (e == val) {
                        this.Flow(world, pos.add(0, 0, 1), lim - 1, flow + 1);
                     }
               }
            }
         }
      }
   }
}
