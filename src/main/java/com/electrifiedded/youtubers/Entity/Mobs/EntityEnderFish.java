package com.electrifiedded.youtubers.Entity.Mobs;

import com.electrifiedded.archaiclib.legacy.entity.LegacyStare;
import com.electrifiedded.archaiclib.legacy.entity.ai.EntityAILegacyCreature;
import com.electrifiedded.archaiclib.legacy.entity.ai.ILegacyCreature;
import javax.annotation.Nullable;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.EnderTeleportEvent;

/**
 * Ender fish: a small enderman of the seas (1.7 old AI): angry when stared at, teleports around and away from
 * projectiles and daylight, hits boats too. Drops ender pearls.
 */
public class EntityEnderFish extends EntityMob implements ILegacyCreature {
   private static final DataParameter<Boolean> SCREAMING = EntityDataManager.createKey(EntityEnderFish.class, DataSerializers.BOOLEAN);
   private int teleportDelay;
   private int stareTimer;
   private boolean isAggressive;
   private Entity entityToAttack;
   private int legacyAttackTime;

   public EntityEnderFish(World world) {
      super(world);
      this.setSize(0.6F, 1.0F);
      this.stepHeight = 1.0F;
      this.tasks.addTask(0, new EntityAISwimming(this));
      this.tasks.addTask(1, new EntityAILegacyCreature(this, 1.0));
   }

   @Override
   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(40.0);
      this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3F);
      this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(7.0);
   }

   @Override
   protected void entityInit() {
      super.entityInit();
      this.dataManager.register(SCREAMING, false);
   }

   @Override
   public Entity getEntityToAttack() {
      return this.entityToAttack;
   }

   @Override
   public void setEntityToAttack(Entity target) {
      this.entityToAttack = target;
      if (target == null) this.setAttackTarget(null);
   }

   @Override
   public int legacyWanderChance() {
      return 80;
   }

   /** Becomes hostile to a player that stares at it for 5 ticks (pumpkin heads are safe). */
   @Nullable
   @Override
   public Entity findPlayerToAttack() {
      EntityPlayer p = this.world.getNearestAttackablePlayer(this, 64.0, 64.0);
      if (p != null) {
         if (this.shouldAttackPlayer(p)) {
            this.isAggressive = true;
            if (this.stareTimer == 0) this.world.playSound(null, p.posX, p.posY, p.posZ, SoundEvents.ENTITY_ENDERMEN_STARE, SoundCategory.HOSTILE, 1.0F, 1.0F);
            if (this.stareTimer++ == 5) {
               this.stareTimer = 0;
               this.setScreaming(true);
               return p;
            }
         } else {
            this.stareTimer = 0;
         }
      }
      return null;
   }

   /** 1.7 EntityMob.attackEntity: melee when within 2 blocks and overlapping vertically, every 20 ticks. */
   @Override
   public void attackEntity(Entity target, float distance) {
      if (this.legacyAttackTime <= 0 && distance < 2.0F && target.getEntityBoundingBox().maxY > this.getEntityBoundingBox().minY
         && target.getEntityBoundingBox().minY < this.getEntityBoundingBox().maxY) {
         this.legacyAttackTime = 20;
         this.attackEntityAsMob(target);
      }
   }

   private boolean shouldAttackPlayer(EntityPlayer player) {
      return LegacyStare.isStaredAtNoPumpkin(player, this, 0.025);
   }

   @Override
   public void onLivingUpdate() {
      if (this.legacyAttackTime > 0) this.legacyAttackTime--;
      if (this.world.isRemote) {
         for (int i = 0; i < 2; i++) {
            this.world.spawnParticle(EnumParticleTypes.PORTAL, this.posX + (this.rand.nextDouble() - 0.5) * this.width,
               this.posY + this.rand.nextDouble() * this.height - 0.25, this.posZ + (this.rand.nextDouble() - 0.5) * this.width,
               (this.rand.nextDouble() - 0.5) * 2.0, -this.rand.nextDouble(), (this.rand.nextDouble() - 0.5) * 2.0);
         }
      }
      if (this.world.isDaytime() && !this.world.isRemote) {
         float f = this.getBrightness();
         if (f > 0.5F && this.world.canSeeSky(new BlockPos(this)) && this.rand.nextFloat() * 30.0F < (f - 0.4F) * 2.0F) {
            this.setEntityToAttack(null);
            this.setScreaming(false);
            this.isAggressive = false;
            this.teleportRandomly();
         }
      }
      if (this.isScreaming() && !this.isAggressive && this.rand.nextInt(100) == 0) this.setScreaming(false);
      this.isJumping = false;
      if (this.entityToAttack != null) this.faceEntity(this.entityToAttack, 100.0F, 100.0F);
      if (!this.world.isRemote && this.isEntityAlive()) {
         if (this.entityToAttack != null) {
            if (this.entityToAttack instanceof EntityPlayer && this.shouldAttackPlayer((EntityPlayer)this.entityToAttack)) {
               if (this.entityToAttack.getDistanceSq(this) < 16.0) this.teleportRandomly();
               this.teleportDelay = 0;
            } else if (this.entityToAttack.getDistanceSq(this) > 256.0 && this.teleportDelay++ >= 30 && this.teleportToEntity(this.entityToAttack)) {
               this.teleportDelay = 0;
            }
         } else {
            this.setScreaming(false);
            this.teleportDelay = 0;
         }
      }
      super.onLivingUpdate();
   }

   protected boolean teleportRandomly() {
      return this.teleportTo(this.posX + (this.rand.nextDouble() - 0.5) * 64.0, this.posY + (this.rand.nextInt(64) - 32),
         this.posZ + (this.rand.nextDouble() - 0.5) * 64.0);
   }

   protected boolean teleportToEntity(Entity e) {
      Vec3d v = new Vec3d(this.posX - e.posX, this.getEntityBoundingBox().minY + this.height / 2.0F - e.posY + e.getEyeHeight(), this.posZ - e.posZ).normalize();
      double d = 16.0;
      return this.teleportTo(this.posX + (this.rand.nextDouble() - 0.5) * 8.0 - v.x * d, this.posY + (this.rand.nextInt(16) - 8) - v.y * d,
         this.posZ + (this.rand.nextDouble() - 0.5) * 8.0 - v.z * d);
   }

   protected boolean teleportTo(double x, double y, double z) {
      EnderTeleportEvent event = new EnderTeleportEvent(this, x, y, z, 0.0F);
      if (MinecraftForge.EVENT_BUS.post(event)) return false;
      double ox = this.posX, oy = this.posY, oz = this.posZ;
      boolean ok = this.attemptTeleport(event.getTargetX(), event.getTargetY(), event.getTargetZ());
      if (ok) {
         this.world.playSound(null, ox, oy, oz, SoundEvents.ENTITY_ENDERMEN_TELEPORT, this.getSoundCategory(), 1.0F, 1.0F);
         this.playSound(SoundEvents.ENTITY_ENDERMEN_TELEPORT, 1.0F, 1.0F);
      }
      return ok;
   }

   @Override
   protected SoundEvent getAmbientSound() {
      return this.isScreaming() ? SoundEvents.ENTITY_ENDERMEN_SCREAM : SoundEvents.ENTITY_ENDERMEN_AMBIENT;
   }

   @Override
   protected SoundEvent getHurtSound(DamageSource src) {
      return SoundEvents.ENTITY_ENDERMEN_HURT;
   }

   @Override
   protected SoundEvent getDeathSound() {
      return SoundEvents.ENTITY_ENDERMEN_DEATH;
   }

   @Override
   protected void dropFewItems(boolean recentlyHit, int looting) {
      int k = this.rand.nextInt(2 + looting);
      for (int l = 0; l < k; l++) this.dropItem(Items.ENDER_PEARL, 1);
   }

   @Override
   public boolean attackEntityFrom(DamageSource src, float amount) {
      if (this.isEntityInvulnerable(src)) return false;
      this.setScreaming(true);
      if (src instanceof EntityDamageSource && src.getTrueSource() instanceof EntityPlayer) this.isAggressive = true;
      if (src instanceof EntityDamageSourceIndirect) {
         this.isAggressive = false;
         for (int i = 0; i < 64; i++) if (this.teleportRandomly()) return true;
      }
      boolean hit = super.attackEntityFrom(src, amount);
      if (hit && src.getTrueSource() != null && src.getTrueSource() != this) this.setEntityToAttack(src.getTrueSource());
      return hit;
   }

   public boolean isScreaming() {
      return this.dataManager.get(SCREAMING);
   }

   public void setScreaming(boolean screaming) {
      this.dataManager.set(SCREAMING, screaming);
   }

   @Override
   public boolean attackEntityAsMob(Entity target) {
      float f = (float)this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getAttributeValue();
      int knock = 0;
      if (target instanceof EntityBoat) f = 4.0F;
      if (target instanceof EntityLivingBase) {
         f += EnchantmentHelper.getModifierForCreature(this.getHeldItemMainhand(), ((EntityLivingBase)target).getCreatureAttribute());
         knock += EnchantmentHelper.getKnockbackModifier(this);
      }
      boolean hit = target.attackEntityFrom(DamageSource.causeMobDamage(this), f);
      if (hit) {
         if (knock > 0) {
            if (!(target instanceof EntityBoat)) {
               target.addVelocity(-MathHelper.sin(this.rotationYaw * (float)Math.PI / 180.0F) * knock * 0.5F, 0.1,
                  MathHelper.cos(this.rotationYaw * (float)Math.PI / 180.0F) * knock * 0.5F);
               this.motionX *= 0.6;
               this.motionZ *= 0.6;
            } else {
               target.motionX *= 0.6;
               target.motionZ *= 0.6;
            }
         }
         int fire = EnchantmentHelper.getFireAspectModifier(this);
         if (fire > 0) target.setFire(fire * 4);
      }
      return hit;
   }
}
