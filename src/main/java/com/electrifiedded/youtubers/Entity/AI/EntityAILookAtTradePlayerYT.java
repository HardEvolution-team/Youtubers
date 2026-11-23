package com.electrifiedded.youtubers.Entity.AI;

import com.electrifiedded.youtubers.Entity.EntityYTBase;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;

public class EntityAILookAtTradePlayerYT extends EntityAIWatchClosest {
   private final EntityYTBase theMerchant;

   public EntityAILookAtTradePlayerYT(EntityYTBase merchant) {
      super(merchant, EntityPlayer.class, 8.0F);
      this.theMerchant = merchant;
   }

   @Override
   public boolean shouldExecute() {
      if (!this.theMerchant.isTrading()) return false;
      this.closestEntity = this.theMerchant.getCustomer();
      return true;
   }
}
