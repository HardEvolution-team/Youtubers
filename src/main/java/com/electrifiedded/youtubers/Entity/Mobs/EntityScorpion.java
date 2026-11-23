package com.electrifiedded.youtubers.Entity.Mobs;

import com.electrifiedded.archaiclib.legacy.item.LegacyPotions;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * Desert scorpion: the 1.7 class was a copy of the spider (old AI: hostile in the dark, leaps, climbs walls, rare
 * skeleton jockey) with a faster walk; the vanilla 1.12 spider does all of that. Extra drops: "Scorpion Eye" and a
 * "Scorpion Venom" splash potion of poison.
 */
public class EntityScorpion extends EntitySpider {
   public EntityScorpion(World world) {
      super(world);
      this.setSize(1.4F, 0.9F);
   }

   @Override
   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(16.0);
      this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.8F);
   }

   @Override
   protected void dropFewItems(boolean recentlyHit, int looting) {
      super.dropFewItems(recentlyHit, looting);
      if (recentlyHit && (this.rand.nextInt(3) == 0 || this.rand.nextInt(1 + looting) > 0)) {
         ItemStack item = new ItemStack(Items.SPIDER_EYE, 1);
         item.setStackDisplayName("Scorpion Eye");
         this.entityDropItem(item, 1.0F);
      }
      if (recentlyHit && (this.rand.nextInt(3) == 0 || this.rand.nextInt(1 + looting) > 0)) {
         ItemStack item = LegacyPotions.stack(16500, 1);
         item.setStackDisplayName("Scorpion Venom");
         this.entityDropItem(item, 1.0F);
      }
   }
}
