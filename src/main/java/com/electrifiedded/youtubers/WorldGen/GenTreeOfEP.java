package com.electrifiedded.youtubers.WorldGen;

import net.minecraft.util.math.BlockPos;
import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class GenTreeOfEP extends GenYoutuberStructure {
   @Override
   public boolean generate(World world, Random random, BlockPos pos) {
      int i = pos.getX();
      int j = pos.getY();
      int k = pos.getZ();
      this.remove_vegitation_circle(world, i, k, 15.0);
      float rand = random.nextFloat() * (float) (Math.PI * 2);

      for (float a = 3.0F; a < 20.0F; a += 3.0F) {
         float rAt = a * -4.0F / 20.0F + 10.0F;
         rand = rand + (float) (Math.PI * 3.0 / 4.0) + (float)((random.nextFloat() - 0.5F) * Math.PI);
         this.add_branch(world, random, i, j + a, k, rAt, rand + a);
         this.add_branch(world, random, i, j + a, k, rAt, rand - (float) (Math.PI * 2.0 / 3.0) + (float)((random.nextFloat() - 0.5F) * Math.PI) + a);
         this.add_branch(world, random, i, j + a, k, rAt, rand + (float) (Math.PI * 2.0 / 3.0) + (float)((random.nextFloat() - 0.5F) * Math.PI) + a);
      }

      this.add_trunk_coil(world, random, i - 0.5F, j, k - 0.5F, 20, 1.6F);
      this.add_trunk(world, random, i, j, k, 20, 1.65F);

      for (float a = 0.0F; a < 5.0F; a++) {
         float rAt = a * -2.0F / 5.0F + 3.0F;
         this.GenCircle(world, random, Blocks.EMERALD_BLOCK, 0, i, j + 20.0F + a, k, rAt, 0.0F, 'y');
      }

      this.setBlockState(world, new BlockPos(i, j, k - 1), Blocks.OAK_DOOR, 1);
      this.setBlockState(world, new BlockPos(i, j + 1, k - 1), Blocks.OAK_DOOR, 9);
      this.setBlockToAir(world, new BlockPos(i, j + 1, k));
      return true;
   }

   public void add_trunk(World world, Random random, int i, int j, int k, int h, float r) {
      for (float a = 0.0F; a < h; a++) {
         float rAt = a * (1.0F - r) / h + r;
         this.GenCircle(world, random, Blocks.GOLD_BLOCK, 0, i, j + a, k, rAt, rAt - 1.0F, 'y');
      }
   }

   public void add_trunk_coil(World world, Random random, float i, float j, float k, int h, float r) {
      float rotations = 3.0F;

      for (float a = 0.0F; a < h; a++) {
         float rAt = a * (0.75F - r) / h + r;
         this.GenCircle(
            world,
            random,
            Blocks.DIAMOND_BLOCK,
            0,
            (float)(i + rAt * Math.sin(a * (rotations * 2.0F * Math.PI) / h)),
            j + a,
            (float)(k + rAt * Math.cos(a * (rotations * 2.0F * Math.PI) / h)),
            1.0F,
            0.0F,
            'y'
         );
      }
   }

   public void add_branch(World world, Random random, float i, float j, float k, float l, float angle) {
      for (float a = l / 2.0F; a <= l * 1.25; a++) {
         this.GenCircle(
            world,
            random,
            Blocks.EMERALD_BLOCK,
            0,
            (float)(i + a * Math.sin(angle)),
            j,
            (float)(k + a * Math.cos(angle)),
            Math.max((a - l / 2.0F) * (a - l * 1.25F) / -4.0F, 2.0F),
            0.0F,
            'y'
         );
      }

      for (float a = l / 2.0F; a <= l * 1.25; a++) {
         this.GenCircle(
            world,
            random,
            Blocks.EMERALD_BLOCK,
            0,
            (float)(i + a * Math.sin(angle)),
            j + 1.0F,
            (float)(k + a * Math.cos(angle)),
            Math.max((a - l / 2.0F) * (a - l * 1.25F) / -6.0F, 1.0F),
            0.0F,
            'y'
         );
      }

      for (float a = 0.0F; a <= l; a++) {
         this.GenCircle(world, random, Blocks.GOLD_BLOCK, 0, (float)(i + a * Math.sin(angle)), j, (float)(k + a * Math.cos(angle)), 1.0F, 0.0F, 'y');
      }
   }
}
