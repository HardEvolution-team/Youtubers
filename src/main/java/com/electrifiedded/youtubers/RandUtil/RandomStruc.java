package com.electrifiedded.youtubers.RandUtil;

import com.electrifiedded.youtubers.WorldGen.GenYoutuberStructure;
import net.minecraft.util.WeightedRandom.Item;

public class RandomStruc extends Item {
   public GenYoutuberStructure stru;

   public RandomStruc(GenYoutuberStructure stru, int weight) {
      super(weight);
      this.stru = stru;
   }
}
