package com.electrifiedded.youtubers.Blocks;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class BlockTechItem extends ItemBlock {
   public BlockTechItem(Block block) {
      super(block);
      this.setHasSubtypes(true);
   }

   @Override
   public String getTranslationKey(ItemStack itemstack) {
      int m = itemstack.getMetadata();
      return m >= 0 && m < BlockTech.TYPES.length ? "tile.TechBlock." + BlockTech.TYPES[m] : "tile.TechBlock.Broken";
   }

   @Override
   public int getMetadata(int par1) {
      return par1;
   }
}
