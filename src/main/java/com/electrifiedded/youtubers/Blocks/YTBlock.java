package com.electrifiedded.youtubers.Blocks;

import com.electrifiedded.archaiclib.v1710.api.block.ArchaicBlock;
import com.electrifiedded.archaiclib.v1710.api.client.IIcon;
import com.electrifiedded.archaiclib.v1710.api.client.IIconRegister;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/** Plain full block with one 1.7 icon "youtubers:&lt;name&gt;". */
public class YTBlock extends ArchaicBlock {
   protected final String n;
   @SideOnly(Side.CLIENT)
   protected IIcon blockIcon;

   public YTBlock(Material materialIn, String string) {
      super(materialIn);
      this.setTranslationKey(string);
      this.n = string;
   }

   @Override
   @SideOnly(Side.CLIENT)
   public void registerBlockIcons(IIconRegister reg) {
      this.blockIcon = reg.registerIcon("youtubers:" + this.n);
   }

   @Override
   @SideOnly(Side.CLIENT)
   public IIcon getIcon(int side, int meta) {
      return this.blockIcon;
   }
}
