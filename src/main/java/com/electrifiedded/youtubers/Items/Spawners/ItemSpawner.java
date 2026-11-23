package com.electrifiedded.youtubers.Items.Spawners;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/** Egg for one fixed entity (e.g. Trayourus). */
public abstract class ItemSpawner extends YTEggBase {
   final int ColA;
   final int ColB;
   public final int Type;

   public ItemSpawner(int a, int b, int type) {
      this.ColA = a;
      this.ColB = b;
      this.Type = type;
      this.setHasSubtypes(false);
   }

   public abstract Entity getEntity(World world);

   @Override
   protected Entity create(World world, int meta) {
      return this.getEntity(world);
   }

   @Override
   public int getColor(ItemStack stack, int layer) {
      return layer == 0 ? this.ColA : this.ColB;
   }
}
