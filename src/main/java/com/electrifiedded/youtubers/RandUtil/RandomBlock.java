package com.electrifiedded.youtubers.RandUtil;

import net.minecraft.block.Block;
import net.minecraft.util.WeightedRandom.Item;

public class RandomBlock extends Item {
   public Block block;
   public int Meta;

   public RandomBlock(Block block, int weight) {
      super(weight);
      this.block = block;
      this.Meta = 0;
   }

   public RandomBlock(Block block, int meta, int weight) {
      super(weight);
      this.block = block;
      this.Meta = meta;
   }
}
