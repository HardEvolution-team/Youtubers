package com.electrifiedded.youtubers.Blocks;

import com.electrifiedded.archaiclib.legacy.block.LegacyMetaBlock;
import com.electrifiedded.archaiclib.v1710.api.block.IArchaicRenderBlock;
import com.electrifiedded.archaiclib.v1710.api.client.IIcon;
import com.electrifiedded.archaiclib.v1710.api.client.IIconRegister;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/** Decorative "computer" block, 7 front textures (meta 0..6, some animated), same top/bottom. */
public class BlockTech extends LegacyMetaBlock implements IArchaicRenderBlock {
   public static final String[] TYPES = {"Off", "BSOD", "Prompt", "Scroll", "Lever", "Lights", "Reel"};
   @SideOnly(Side.CLIENT)
   private IIcon top;
   @SideOnly(Side.CLIENT)
   private IIcon[] icons;

   public BlockTech() {
      super(Material.CIRCUITS);
      this.setSoundType(SoundType.METAL);
   }

   @Override
   public void getSubBlocks(CreativeTabs tab, NonNullList<ItemStack> list) {
      for (int i = 0; i < TYPES.length; i++) list.add(new ItemStack(this, 1, i));
   }

   @Override
   @SideOnly(Side.CLIENT)
   public void registerBlockIcons(IIconRegister reg) {
      this.top = reg.registerIcon("youtubers:TechTB");
      this.icons = new IIcon[TYPES.length];
      for (int i = 0; i < TYPES.length; i++) this.icons[i] = reg.registerIcon("youtubers:Tech" + TYPES[i]);
   }

   @Override
   @SideOnly(Side.CLIENT)
   public IIcon getIcon(int side, int meta) {
      return side > 1 ? this.icons[meta % TYPES.length] : this.top;
   }
}
