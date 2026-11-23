package com.electrifiedded.youtubers.Items.Spawners;

import com.electrifiedded.youtubers.Youtubers;
import com.electrifiedded.youtubers.Entity.PlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/** Spawns a "random player" NPC; renaming the egg gives the NPC that player name (and so that player's skin). */
public class PlayerSpawner extends YTEggBase {
   public PlayerSpawner() {
      this.setCreativeTab(Youtubers.tabYoutubers);
      this.setHasSubtypes(false);
      this.setTranslationKey("PlayerSpawner");
   }

   @Override
   protected Entity create(World world, int meta) {
      return new PlayerEntity(world);
   }

   @Override
   protected void applyName(Entity entity, ItemStack stack) {
      ((PlayerEntity)entity).setName(stack.getDisplayName());
   }

   @Override
   public int getColor(ItemStack stack, int layer) {
      return cycleColor(layer == 0 ? 18374L : 72645L);
   }
}
