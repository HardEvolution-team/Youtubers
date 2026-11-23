package com.electrifiedded.youtubers.Items.Spawners;

import com.electrifiedded.youtubers.Youtubers;
import com.electrifiedded.youtubers.Entity.SpawnEntityInfo;
import com.electrifiedded.youtubers.Entity.YoutuberPetBase;
import javax.annotation.Nullable;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;

/**
 * Eggs of the mod's mobs. The meta is the index among the registered mobs (Youtubers.MobSpawns, gaps skipped).
 * Using it on a mob spawns a pet owned by that mob for pet-type mobs.
 */
public class ItemMobSpawner extends YTEggBase {
   public ItemMobSpawner() {
      this.setCreativeTab(Youtubers.tabMisc);
      this.setTranslationKey("MobSpawner");
   }

   @Override
   public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> list) {
      if (!this.isInCreativeTab(tab)) return;
      int i = 0;
      for (SpawnEntityInfo info : Youtubers.MobSpawns) if (info != null) list.add(new ItemStack(this, 1, i++));
   }

   /** Meta of the egg of the mob registered under this id. */
   public static int getDamage(int entityID) {
      int a = 0;
      for (int j = 0; j < entityID; j++) if (Youtubers.MobSpawns[j] != null) a++;
      return a;
   }

   public static int getEntityID(int damage) {
      int a = 0;
      for (int j = 0; j < Youtubers.MobSpawns.length; j++) {
         if (Youtubers.MobSpawns[j] != null) {
            if (a == damage) return j;
            a++;
         }
      }
      return -1;
   }

   @Nullable
   public static SpawnEntityInfo info(int damage) {
      int id = getEntityID(damage);
      return id == -1 ? null : Youtubers.MobSpawns[id];
   }

   @Override
   protected Entity create(World world, int meta) {
      SpawnEntityInfo info = info(meta);
      return info == null ? null : info.Spawn(world);
   }

   public static Entity spawnCreature(World world, int meta, double x, double y, double z) {
      return ((YTEggBase)com.electrifiedded.youtubers.YTItems.MobSpawner).spawn(world, meta, x, y, z);
   }

   @Override
   public String getTranslationKey(ItemStack stack) {
      SpawnEntityInfo info = info(stack.getMetadata());
      return super.getTranslationKey() + "." + (info == null ? "SpawnerError_" + stack.getMetadata() : info.Name);
   }

   @Override
   public int getColor(ItemStack stack, int layer) {
      SpawnEntityInfo info = info(stack.getMetadata());
      return info == null ? 0xFFFFFF : layer == 0 ? info.ColorA : info.ColorB;
   }

   @Override
   public boolean itemInteractionForEntity(ItemStack item, EntityPlayer player, EntityLivingBase target, EnumHand hand) {
      if (target.world.isRemote) return false;
      Entity spawn = this.spawn(target.world, item.getMetadata(), target.posX, target.posY, target.posZ);
      if (spawn instanceof YoutuberPetBase) ((YoutuberPetBase)spawn).setOwner(target);
      if (spawn != null) {
         if (item.hasDisplayName()) spawn.setCustomNameTag(item.getDisplayName());
         if (!player.capabilities.isCreativeMode) item.shrink(1);
         return true;
      }
      return false;
   }
}
