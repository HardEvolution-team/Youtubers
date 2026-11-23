package com.electrifiedded.youtubers.Blocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class PotatoBlock extends YTBlock {
   public PotatoBlock() {
      super(Material.CLAY, "PotatoBlock");
      this.setCreativeTab(CreativeTabs.FOOD);
      this.setSoundType(SoundType.GROUND);
   }

   @Override
   @net.minecraftforge.fml.relauncher.SideOnly(net.minecraftforge.fml.relauncher.Side.CLIENT)
   public void registerBlockIcons(com.electrifiedded.archaiclib.v1710.api.client.IIconRegister reg) {
      this.blockIcon = reg.registerIcon("youtubers:Potato");
   }
}
