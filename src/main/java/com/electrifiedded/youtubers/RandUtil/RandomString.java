package com.electrifiedded.youtubers.RandUtil;

import net.minecraft.util.WeightedRandom.Item;

public class RandomString extends Item {
   public String text;

   public RandomString(String Text, int p_i1556_1_) {
      super(p_i1556_1_);
      this.text = Text;
   }
}
