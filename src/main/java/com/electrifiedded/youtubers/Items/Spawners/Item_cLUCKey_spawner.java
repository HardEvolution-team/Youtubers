package com.electrifiedded.youtubers.Items.Spawners;

import com.electrifiedded.youtubers.Youtubers;
import com.electrifiedded.youtubers.Entity.cLUCKY.EntitycLuckey;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;

/** cLUCKey (lucky chicken) eggs: 36 combinations of level (-1..4) and luck (-1..4); -1 = random. */
public class Item_cLUCKey_spawner extends YTEggBase {
   public static final int[][] dataval = new int[36][];

   static {
      for (int i = 0; i < 36; i++) dataval[i] = new int[]{i % 6 - 1, i / 6 - 1};
   }

   public Item_cLUCKey_spawner() {
      this.setCreativeTab(Youtubers.tabMisc);
      this.setTranslationKey("YoutuberSpawner");
   }

   private static int[] data(int meta) {
      return dataval[Math.max(0, Math.min(35, meta))];
   }

   @Override
   public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> list) {
      if (this.isInCreativeTab(tab)) for (int a = 0; a < 36; a++) list.add(new ItemStack(this, 1, a));
   }

   @Override
   protected Entity create(World world, int meta) {
      int[] d = data(meta);
      return new EntitycLuckey(world, d[0], d[1]);
   }

   @Override
   public String getTranslationKey(ItemStack stack) {
      int[] d = data(stack.getMetadata());
      return super.getTranslationKey() + ".cLUCKey." + d[0] + "." + d[1];
   }

   @Override
   public int getColor(ItemStack stack, int layer) {
      int[] d = data(stack.getMetadata());
      java.util.Random r = new java.util.Random(layer == 0 ? d[0] + 64892 : d[1] + 29479);
      return RGBtoInt(r.nextInt(255), r.nextInt(255), r.nextInt(255));
   }
}
