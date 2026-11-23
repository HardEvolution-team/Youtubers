package com.electrifiedded.youtubers.Entity;

import com.electrifiedded.archaiclib.legacy.sound.LegacyModSounds;
import com.electrifiedded.youtubers.Youtubers;
import com.electrifiedded.youtubers.Entity.AI.EntityAIPlayYT;
import com.electrifiedded.youtubers.Items.ItemMoney;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

/** Craig the Mailman (DanTDM's lab): NPC with his own voice, tameable with 500$ or bigger notes. */
public class EntityCraigTheMailman extends EntityYTBase {
   public EntityCraigTheMailman(World world) {
      super(world);
      this.setCustomNameTag(this.getName());
      this.setSize(0.6F, 1.8F);
      this.tasks.addTask(8, new EntityAIPlayYT(this, 0.32));
   }

   @Override
   public String getName() {
      return "Craig the Mailman";
   }

   @Override
   public boolean processInteract(EntityPlayer player, EnumHand hand) {
      if (hand != EnumHand.MAIN_HAND) return false;
      ItemStack itemstack = player.getHeldItem(hand);
      if (!itemstack.isEmpty()) {
         if (this.isTamed()) {
            if (itemstack.getItem() instanceof ItemFood && this.getHealth() < 20.0F) {
               if (!player.capabilities.isCreativeMode) itemstack.shrink(1);
               this.heal(((ItemFood)itemstack.getItem()).getSaturationModifier(itemstack));
               return true;
            }
            if (itemstack.getItem() instanceof ItemMoney && player.getName().equals(this.getOwnerName())) {
               if (!this.world.isRemote) {
                  this.aiSit.setSitting(!this.isSitting());
                  this.isJumping = false;
                  this.setAttackTarget(null);
               }
               return true;
            }
         } else if (itemstack.getItem() instanceof ItemMoney && ((ItemMoney)itemstack.getItem()).n >= 5) {
            int[] chances = {2000, 1000, 200, 100, 20, 10, 2, 1};
            int chance = chances[Math.min(7, ((ItemMoney)itemstack.getItem()).n - 5)];
            if (!player.capabilities.isCreativeMode) itemstack.shrink(1);
            if (!this.world.isRemote) {
               if (this.rand.nextInt(chance) == 0) {
                  this.setTamed(true);
                  this.setAttackTarget(null);
                  this.aiSit.setSitting(true);
                  this.setHealth(20.0F);
                  this.setOwner(player.getName());
                  this.playTameEffect(true);
               } else {
                  this.playTameEffect(false);
               }
            }
            return true;
         }
      }
      return super.processInteract(player, hand);
   }

   private static SoundEvent craig() {
      return LegacyModSounds.get(Youtubers.MODID, "Craig");
   }

   @Override
   protected SoundEvent getAmbientSound() {
      return craig();
   }

   @Override
   protected SoundEvent getHurtSound(DamageSource src) {
      return craig();
   }

   @Override
   protected SoundEvent getDeathSound() {
      return craig();
   }

   @Override
   public boolean attackEntityAsMob(Entity target) {
      return this.legacyMelee(target, 2.0F);
   }
}
