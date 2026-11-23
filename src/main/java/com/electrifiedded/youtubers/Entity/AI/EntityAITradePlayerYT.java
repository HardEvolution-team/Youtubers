package com.electrifiedded.youtubers.Entity.AI;

import com.electrifiedded.youtubers.Entity.EntityYTBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;

/** Stands still while a player trades; frees the merchant when the player walks away or closes the GUI. */
public class EntityAITradePlayerYT extends EntityAIBase {
   private final EntityYTBase villager;

   public EntityAITradePlayerYT(EntityYTBase villager) {
      this.villager = villager;
      this.setMutexBits(5);
   }

   @Override
   public boolean shouldExecute() {
      if (!this.villager.isEntityAlive() || this.villager.isInWater() || !this.villager.onGround || this.villager.velocityChanged) return false;
      EntityPlayer p = this.villager.getCustomer();
      return p != null && this.villager.getDistanceSq(p) <= 16.0 && p.openContainer != p.inventoryContainer;
   }

   @Override
   public void startExecuting() {
      this.villager.getNavigator().clearPath();
   }

   @Override
   public void resetTask() {
      this.villager.setCustomer(null);
   }
}
