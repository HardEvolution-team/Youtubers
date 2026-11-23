package com.electrifiedded.youtubers.WorldGen;

import net.minecraft.util.math.BlockPos;
import java.util.Random;
import net.minecraft.world.World;

public class GenBlank extends GenYoutuberStructure {
   @Override
   public boolean generate(World world, Random random, BlockPos pos) {
      return true;
   }
}
