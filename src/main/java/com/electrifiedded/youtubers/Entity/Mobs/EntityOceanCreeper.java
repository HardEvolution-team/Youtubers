package com.electrifiedded.youtubers.Entity.Mobs;

import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/** Oceanic creeper: a creeper of beaches and rivers; starts its fuse when its target is within 7 blocks and in sight. */
public class EntityOceanCreeper extends EntityMob {
   private static final DataParameter<Integer> STATE = EntityDataManager.createKey(EntityOceanCreeper.class, DataSerializers.VARINT);
   private static final DataParameter<Boolean> POWERED = EntityDataManager.createKey(EntityOceanCreeper.class, DataSerializers.BOOLEAN);
   private int lastActiveTime;
   private int timeSinceIgnited;
   private int fuseTime = 30;
   private int explosionRadius = 3;

   public EntityOceanCreeper(World world) {
      super(world);
      this.setSize(0.6F, 1.7F);
      this.tasks.addTask(1, new EntityAISwimming(this));
      this.tasks.addTask(3, new EntityAIAvoidEntity<>(this, EntityOcelot.class, 6.0F, 1.0, 1.2));
      this.tasks.addTask(4, new EntityAIAttackMelee(this, 1.0, false));
      this.tasks.addTask(5, new EntityAIWander(this, 0.8));
      this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
      this.tasks.addTask(6, new EntityAILookIdle(this));
      this.targetTasks.addTask(1, new EntityAINearestAttackableTarget<>(this, EntityPlayer.class, true));
      this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, false));
   }

   @Override
   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25);
   }

   @Override
   public int getMaxFallHeight() {
      return this.getAttackTarget() == null ? 3 : 3 + (int)(this.getHealth() - 1.0F);
   }

   @Override
   public void fall(float distance, float damageMultiplier) {
      super.fall(distance, damageMultiplier);
      this.timeSinceIgnited = (int)(this.timeSinceIgnited + distance * 1.5F);
      if (this.timeSinceIgnited > this.fuseTime - 5) this.timeSinceIgnited = this.fuseTime - 5;
   }

   @Override
   protected void entityInit() {
      super.entityInit();
      this.dataManager.register(STATE, -1);
      this.dataManager.register(POWERED, false);
   }

   @Override
   public void writeEntityToNBT(NBTTagCompound nbt) {
      super.writeEntityToNBT(nbt);
      if (this.getPowered()) nbt.setBoolean("powered", true);
      nbt.setShort("Fuse", (short)this.fuseTime);
      nbt.setByte("ExplosionRadius", (byte)this.explosionRadius);
   }

   @Override
   public void readEntityFromNBT(NBTTagCompound nbt) {
      super.readEntityFromNBT(nbt);
      this.dataManager.set(POWERED, nbt.getBoolean("powered"));
      if (nbt.hasKey("Fuse")) this.fuseTime = nbt.getShort("Fuse");
      if (nbt.hasKey("ExplosionRadius")) this.explosionRadius = nbt.getByte("ExplosionRadius");
   }

   @Override
   public void onUpdate() {
      if (this.isEntityAlive()) {
         if (!this.world.isRemote) {
            Entity t = this.getAttackTarget();
            this.setCreeperState(t != null && this.getDistanceSq(t) <= 49.0 && this.getEntitySenses().canSee(t) ? 1 : -1);
         }
         this.lastActiveTime = this.timeSinceIgnited;
         int i = this.getCreeperState();
         if (i > 0 && this.timeSinceIgnited == 0) this.playSound(SoundEvents.ENTITY_CREEPER_PRIMED, 1.0F, 0.5F);
         this.timeSinceIgnited += i;
         if (this.timeSinceIgnited < 0) this.timeSinceIgnited = 0;
         if (this.timeSinceIgnited >= this.fuseTime) {
            this.timeSinceIgnited = this.fuseTime;
            if (!this.world.isRemote) {
               boolean grief = net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.world, this);
               this.world.createExplosion(this, this.posX, this.posY, this.posZ, this.getPowered() ? this.explosionRadius * 2 : this.explosionRadius, grief);
               this.setDead();
            }
         }
      }
      super.onUpdate();
   }

   @Override
   protected SoundEvent getHurtSound(DamageSource src) {
      return SoundEvents.ENTITY_CREEPER_HURT;
   }

   @Override
   protected SoundEvent getDeathSound() {
      return SoundEvents.ENTITY_CREEPER_DEATH;
   }

   @Override
   public boolean attackEntityAsMob(Entity entity) {
      return true;
   }

   public boolean getPowered() {
      return this.dataManager.get(POWERED);
   }

   @SideOnly(Side.CLIENT)
   public float getCreeperFlashIntensity(float partialTicks) {
      return (this.lastActiveTime + (this.timeSinceIgnited - this.lastActiveTime) * partialTicks) / (this.fuseTime - 2);
   }

   public int getCreeperState() {
      return this.dataManager.get(STATE);
   }

   public void setCreeperState(int state) {
      this.dataManager.set(STATE, state);
   }

   @Override
   public void onStruckByLightning(EntityLightningBolt bolt) {
      super.onStruckByLightning(bolt);
      this.dataManager.set(POWERED, true);
   }
}
