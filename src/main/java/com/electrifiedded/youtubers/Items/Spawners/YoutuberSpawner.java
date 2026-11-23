package com.electrifiedded.youtubers.Items.Spawners;

import com.electrifiedded.youtubers.YTList;
import com.electrifiedded.youtubers.Youtubers;
import com.electrifiedded.youtubers.Entity.EntityYoutuber;
import com.electrifiedded.youtubers.Entity.Youtuber;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/** Youtuber eggs: meta 0 = random youtuber, meta n = YTList.YoutuberList[n - 1]. */
public class YoutuberSpawner extends YTEggBase {
   public YoutuberSpawner() {
      this.setCreativeTab(Youtubers.tabYoutubers);
      this.setTranslationKey("ItemSpawner");
   }

   @Override
   public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> list) {
      if (this.isInCreativeTab(tab)) for (int a = 0; a <= YTList.YoutuberList.length; a++) list.add(new ItemStack(this, 1, a));
   }

   @Nullable
   public static Youtuber youtuber(int meta) {
      return meta > 0 && meta <= YTList.YoutuberList.length ? YTList.YoutuberList[meta - 1] : null;
   }

   @Override
   protected Entity create(World world, int meta) {
      return meta == 0 || youtuber(meta) == null ? new EntityYoutuber(world) : new EntityYoutuber(world, meta - 1);
   }

   public static Entity spawnCreature(World world, int meta, double x, double y, double z) {
      return ((YTEggBase)com.electrifiedded.youtubers.YTItems.Spawner).spawn(world, meta, x, y, z);
   }

   @Override
   public String getTranslationKey(ItemStack stack) {
      Youtuber yt = youtuber(stack.getMetadata());
      return super.getTranslationKey() + "." + (yt == null ? "Youtuber" : yt.Name);
   }

   @Override
   public int getColor(ItemStack stack, int layer) {
      Youtuber yt = youtuber(stack.getMetadata());
      if (yt == null) return cycleColor(layer == 0 ? 64892L : 29479L);
      if (yt.hasCustomIcon) return 0xFFFFFF;
      return layer == 0 ? yt.ColorA : yt.ColorB;
   }

   @Override
   @SideOnly(Side.CLIENT)
   public void addInformation(ItemStack stack, @Nullable World world, List<String> list, ITooltipFlag flag) {
      Youtuber yt = youtuber(stack.getMetadata());
      if (yt != null && !yt.Group.isEmpty()) list.add(yt.Group);
   }
}
