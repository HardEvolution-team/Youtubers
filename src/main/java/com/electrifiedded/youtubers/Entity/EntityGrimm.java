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
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

/** Grim, DanTDM's dog: a wolf-like pet that adopts a nearby DanTDM (or player), collar can be dyed. */
public class EntityGrimm extends YoutuberPetBase {
   private static final DataParameter<Byte> COLLAR = EntityDataManager.createKey(EntityGrimm.class, DataSerializers.BYTE);

   public EntityGrimm(World world) {
      super(world);
      this.setCustomNameTag(this.getName());
      this.setSize(0.6F, 0.8F);
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
      return "Grim";
   }

   @Override
   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(50.0);
      this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.5);
      this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(4.0);
   }

   @Override
   protected void entityInit() {
      super.entityInit();
      this.dataManager.register(COLLAR, (byte)EnumDyeColor.RED.getMetadata());
   }

   @Override
   protected SoundEvent getAmbientSound() {
      if (this.rand.nextInt(3) == 0) return this.isTamed() && this.getHealth() < 10.0F ? SoundEvents.ENTITY_WOLF_WHINE : SoundEvents.ENTITY_WOLF_PANT;
      return SoundEvents.ENTITY_WOLF_AMBIENT;
   }

   @Override
   protected SoundEvent getHurtSound(DamageSource src) {
      return SoundEvents.ENTITY_WOLF_HURT;
   }

   @Override
   protected SoundEvent getDeathSound() {
      return SoundEvents.ENTITY_WOLF_DEATH;
   }

   @Override
   protected float getSoundVolume() {
      return 0.4F;
   }

   @Override
   public void onLivingUpdate() {
      super.onLivingUpdate();
      if (!this.world.isRemote && this.getOwner() == this && this.ticksExisted % 20 == 0) {
         int dan = Youtubers.GetYoutuberIDbyName("DanTDM");
         for (Entity e : this.world.getEntitiesWithinAABB(EntityYoutuber.class, this.getEntityBoundingBox().grow(3.0))) {
            if (((EntityYoutuber)e).getYoutuber() == dan && this.getDistanceSq(e) < 5.0) {
               this.setOwner(e);
               this.setTamed(true);
            }
         }
         if (this.getOwner() == this) this.getPlayerOwner();
      }
   }

   @Override
   public float getEyeHeight() {
      return this.height * 0.8F;
   }

   public float getTailRotation() {
      return this.isTamed()
         ? (0.55F - (20.0F - this.getHealth() * 20.0F / (float)this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).getAttributeValue()) * 0.02F) * (float)Math.PI
         : (float)(Math.PI / 5);
   }

   @Override
   public boolean processInteract(EntityPlayer player, EnumHand hand) {
      ItemStack itemstack = player.getHeldItem(hand);
      if (this.isTamed() && !itemstack.isEmpty()) {
         if (itemstack.getItem() instanceof ItemFood) {
            ItemFood food = (ItemFood)itemstack.getItem();
            if (food.isWolfsFavoriteMeat() && this.getHealth() < 20.0F) {
               if (!player.capabilities.isCreativeMode) itemstack.shrink(1);
               this.heal(food.getSaturationModifier(itemstack));
               return true;
            }
         } else if (itemstack.getItem() == Items.DYE) {
            EnumDyeColor c = EnumDyeColor.byDyeDamage(itemstack.getMetadata());
            if (c != this.getCollarColor()) {
               this.setCollarColor(c);
               if (!player.capabilities.isCreativeMode) itemstack.shrink(1);
               return true;
            }
         }
      }
      return super.processInteract(player, hand);
   }

   public EnumDyeColor getCollarColor() {
      return EnumDyeColor.byMetadata(this.dataManager.get(COLLAR) & 15);
   }

   public void setCollarColor(EnumDyeColor c) {
      this.dataManager.set(COLLAR, (byte)(c.getMetadata() & 15));
   }

   @Override
   public void writeEntityToNBT(NBTTagCompound nbt) {
      super.writeEntityToNBT(nbt);
      nbt.setByte("CollarColor", (byte)this.getCollarColor().getMetadata());
   }

   @Override
   public void readEntityFromNBT(NBTTagCompound nbt) {
      super.readEntityFromNBT(nbt);
      if (nbt.hasKey("CollarColor")) this.setCollarColor(EnumDyeColor.byMetadata(nbt.getByte("CollarColor")));
   }
}
