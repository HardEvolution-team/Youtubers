package com.electrifiedded.youtubers.Items.Spawners;

import com.electrifiedded.youtubers.YTConfig;
import com.electrifiedded.youtubers.YTStructures;
import com.electrifiedded.youtubers.Youtubers;
import com.electrifiedded.youtubers.WorldGen.GenYoutuberStructure;
import java.util.Random;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/** Builds a structure (meta = index in YTStructures.Map) on top of the clicked block; the "demo" version if allowed. */
public class ItemStructureSpawner extends Item {
   private static long LastTime = 0L;

   public ItemStructureSpawner() {
      this.setHasSubtypes(true);
      this.setCreativeTab(Youtubers.tabStructure);
   }

   public static String nameOf(int meta) {
      String[] names = YTStructures.Map.keySet().toArray(new String[0]);
      return meta >= 0 && meta < names.length ? names[meta] : names[0];
   }

   @Override
   public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> list) {
      if (this.isInCreativeTab(tab)) for (int i = 0; i < YTStructures.Map.size(); i++) list.add(new ItemStack(this, 1, i));
   }

   @Override
   public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hx, float hy, float hz) {
      if (world.isRemote) return EnumActionResult.SUCCESS;
      long time = System.currentTimeMillis();
      if (time - LastTime <= 500L) return EnumActionResult.FAIL;
      LastTime = time;
      GenYoutuberStructure structure = YTStructures.Map.get(nameOf(player.getHeldItem(hand).getMetadata()));
      if (structure == null) return EnumActionResult.FAIL;
      BlockPos at = pos.up();
      boolean demo = YTConfig.AllowDemo && structure.hasDemo();
      com.electrifiedded.archaiclib.world.StagedWorld.submit(world, () -> {
         if (demo) structure.genDemo(world, new com.electrifiedded.archaiclib.util.FastRandom(), at);
         else structure.generate(world, new com.electrifiedded.archaiclib.util.FastRandom(), at);
      });
      return EnumActionResult.SUCCESS;
   }

   @Override
   public String getTranslationKey(ItemStack stack) {
      return super.getTranslationKey() + "." + nameOf(stack.getMetadata());
   }
}
