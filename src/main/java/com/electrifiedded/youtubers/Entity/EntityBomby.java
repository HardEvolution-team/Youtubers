package com.electrifiedded.youtubers.Entity;

import com.electrifiedded.youtubers.Youtubers;
import com.electrifiedded.youtubers.Entity.AI.pet.EntityAIFollowOwnerPet;
import com.electrifiedded.youtubers.Entity.AI.pet.EntityAIOwnerHurtByTargetPet;
import com.electrifiedded.youtubers.Entity.AI.pet.EntityAIOwnerHurtTargetPet;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

/** Bomby, FuriousDestroyer's little creeper: attacks by blowing up (without breaking blocks), immune to explosions, eats gunpowder. */
public class EntityBomby extends YoutuberPetBase {
   public EntityBomby(World world) {
      super(world);
      this.setCustomNameTag(this.getName());
      this.setSize(0.5F, 0.5F);
      this.tasks.addTask(1, new EntityAISwimming(this));
      this.tasks.addTask(2, this.aiSit);
      this.tasks.addTask(3, new EntityAILeapAtTarget(this, 0.4F));
      this.tasks.addTask(4, new EntityAIAttackMelee(this, 1.0, true));
      this.tasks.addTask(5, new EntityAIFollowOwnerPet(this, 1.0, 10.0F, 2.0F));
      this.tasks.addTask(7, new EntityAIWander(this, 1.0));
      this.tasks.addTask(9, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
      this.tasks.addTask(9, new EntityAILookIdle(this));
      this.targetTasks.addTask(1, new EntityAIOwnerHurtByTargetPet(this));
      this.targetTasks.addTask(2, new EntityAIOwnerHurtTargetPet(this));
      this.targetTasks.addTask(3, new EntityAIHurtByTarget(this, true));
   }

   @Override
   public String getName() {
      return "Bomby";
   }

   @Override
   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(50.0);
      this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3);
   }

   @Override
   public void onLivingUpdate() {
      super.onLivingUpdate();
      if (!this.world.isRemote && this.getOwner() == this && this.ticksExisted % 20 == 0) {
         int fd = Youtubers.GetYoutuberIDbyName("FuriousDestroyer");
         for (EntityYoutuber e : this.world.getEntitiesWithinAABB(EntityYoutuber.class, this.getEntityBoundingBox().grow(3.0))) {
            if (e.getYoutuber() == fd && this.getDistanceSq(e) < 5.0) {
               this.setOwner(e);
               this.setTamed(true);
            }
         }
         if (this.getOwner() == this) this.getPlayerOwner();
      }
   }

   @Override
   public boolean processInteract(EntityPlayer player, EnumHand hand) {
      ItemStack itemstack = player.getHeldItem(hand);
      if (this.isTamed() && !itemstack.isEmpty() && itemstack.getItem() == Items.GUNPOWDER && this.getHealth() < 50.0F) {
         if (!player.capabilities.isCreativeMode) itemstack.shrink(1);
         this.heal(5.0F);
         return true;
      }
      return super.processInteract(player, hand);
   }

   @Override
   public boolean attackEntityAsMob(Entity target) {
      this.world.createExplosion(this, this.posX, this.posY, this.posZ, 2.0F, false);
      return true;
   }

   @Override
   public boolean attackEntityFrom(DamageSource src, float amount) {
      return !src.isExplosion() && super.attackEntityFrom(src, amount);
   }
}
