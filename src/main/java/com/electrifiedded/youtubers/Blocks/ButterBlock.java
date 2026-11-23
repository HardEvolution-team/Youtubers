package com.electrifiedded.youtubers.Blocks;

import com.electrifiedded.archaiclib.v1710.api.client.IIconRegister;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/** "Block of Budder": a soft (cake material) block that looks like a gold block. */
public class ButterBlock extends YTBlock {
   public ButterBlock() {
      super(Material.CAKE, "ButterBlock");
      this.setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
      this.setSoundType(SoundType.CLOTH);
   }

   @Override
   @SideOnly(Side.CLIENT)
   public void registerBlockIcons(IIconRegister reg) {
      this.blockIcon = reg.registerIcon("minecraft:gold_block");
   }
}
