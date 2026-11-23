package com.electrifiedded.youtubers.Entity.Mobs;

import com.electrifiedded.archaiclib.legacy.entity.ai.EntityAILegacyCreature;
import com.electrifiedded.archaiclib.legacy.entity.ai.ILegacyCreature;
import java.util.List;
import java.util.Random;
import javax.annotation.Nullable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

/**
 * Walking cactus: a stack of 1-6 segments, each riding the one below ("at" = position from the bottom, "Cactusheight" =
 * stack size). The bottom segment grows new segments over time (cactus green on it too) and splits when it gets too tall;
 * shears cut the stack where you click. Neutral like a zombie pigman: hitting one angers every cactus within 32 blocks.
 */
public class EntityCactus extends EntityMob implements ILegacyCreature {
   private int angerLevel;
   public int Timer = 0;
   public float rotation = 0.0F;
   private int at;
   private int Cactusheight;
   private Entity entityToAttack;
   private int legacyAttackTime;

   public EntityCactus(World world) {
      this(world, 1, com.electrifiedded.archaiclib.util.FastRandom.shared().nextInt(10) == 0 ? com.electrifiedded.archaiclib.util.FastRandom.shared().nextInt(2) + 4 : com.electrifiedded.archaiclib.util.FastRandom.shared().nextInt(4));
   }

   public EntityCactus(World world, int at, int height) {
      super(world);
      this.at = at;
      this.Cactusheight = height;
      this.tasks.addTask(0, new EntityAISwimming(this));
      this.tasks.addTask(1, new EntityAILegacyCreature(this, 1.0));
   }

   @Override
   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.5);
      this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(5.0);
   }

   // ---- old AI -------------------------------------------------------------------------------------------------------

   @Override
   public Entity getEntityToAttack() {
      return this.entityToAttack;
   }

   @Override
   public void setEntityToAttack(Entity target) {
      this.entityToAttack = target;
      if (target == null) this.setAttackTarget(null);
   }

   /** Only angry cacti look for players (1.7 EntityMob: nearest vulnerable player within 16 blocks). */
   @Nullable
   @Override
   public Entity findPlayerToAttack() {
      if (this.angerLevel == 0) return null;
      EntityPlayer p = this.world.getNearestAttackablePlayer(this, 16.0, 16.0);
      return p != null && this.canEntityBeSeen(p) ? p : null;
   }

   @Override
   public void attackEntity(Entity target, float distance) {
      if (this.legacyAttackTime <= 0 && distance < 2.0F && target.getEntityBoundingBox().maxY > this.getEntityBoundingBox().minY
         && target.getEntityBoundingBox().minY < this.getEntityBoundingBox().maxY) {
         this.legacyAttackTime = 20;
         this.attackEntityAsMob(target);
      }
   }

   // ---- stack ------------------------------------------------------------------------------------------------------

   @Nullable
   private EntityCactus above() {
      List<Entity> p = this.getPassengers();
      return !p.isEmpty() && p.get(0) instanceof EntityCactus ? (EntityCactus)p.get(0) : null;
   }

   @Override
   public void onLivingUpdate() {
      if (this.legacyAttackTime > 0) this.legacyAttackTime--;
      if (!this.world.isRemote) {
         if (this.getBaseCactus().findThisAt(this) != this.at) this.getBaseCactus().resetCactus(1);
         if (this.at == 1 && this.Cactusheight > 7) this.Split(this.rand.nextInt(this.Cactusheight - 1) + 1);
         if (this.rand.nextInt(20) == 0) this.Timer++;
         if (this.Timer == 40) {
            if (this.at == 1) {
               if (this.rand.nextInt(30 + 10 * this.Cactusheight - 2) == 0) {
                  if (this.canGrow()) this.Grow();
               } else if (this.Cactusheight > 2) {
                  float divider = 2.0F;
                  for (int i = 2; i < this.Cactusheight; i++) divider *= 2.0F;
                  int chance = Math.max(1, (int)(50.0F / divider));
                  if (this.rand.nextInt(chance) == 0) this.Split(this.rand.nextInt(this.Cactusheight - 1) + 1);
               }
            }
            this.Timer = 0;
         }
         Entity below = this.getRidingEntity();
         if (below != null && below.isDead) {
            this.dismountRidingEntity();
            this.resetCactus(1);
         }
      }
      super.onLivingUpdate();
   }

   /** At most 64 cacti within 64 blocks. */
   public boolean canGrow() {
      int n = 0;
      for (Entity e : this.world.loadedEntityList) if (e instanceof EntityCactus && e.getDistanceSq(this) < 64.0 * 64.0) n++;
      return n < 64;
   }

   public void Grow() {
      EntityCactus up = this.above();
      this.Cactusheight++;
      if (up == null) this.spawnSegmentsAbove();
      else up.Grow();
   }

   public void Split(int splitH) {
      if (splitH >= this.Cactusheight) return;
      if (this.at >= splitH) {
         this.dismountRidingEntity();
         this.resetCactus(1);
      } else {
         this.Cactusheight = splitH - 1;
         EntityCactus up = this.above();
         if (up != null) up.Split(splitH);
      }
   }

   public int resetCactus(int at) {
      this.at = at;
      EntityCactus up = this.above();
      this.Cactusheight = up != null ? up.resetCactus(at + 1) : at;
      return this.Cactusheight;
   }

   public EntityCactus getBaseCactus() {
      return this.getRidingEntity() instanceof EntityCactus ? ((EntityCactus)this.getRidingEntity()).getBaseCactus() : this;
   }

   public int findThisAt(EntityCactus cactus) {
      if (this == cactus) return 1;
      EntityCactus up = this.above();
      if (up == null) return -1;
      int ret = up.findThisAt(cactus);
      return ret == -1 ? -1 : ret + 1;
   }

   @Override
   public boolean processInteract(EntityPlayer player, EnumHand hand) {
      ItemStack held = player.getHeldItem(hand);
      if (held.isEmpty() || this.world.isRemote) return false;
      if (held.isItemEqual(new ItemStack(Items.DYE, 1, 2))) {
         this.getBaseCactus().Grow();
      } else if (held.getItem() == Items.SHEARS) {
         this.getBaseCactus().Split(this.at);
      }
      return false;
   }

   @Override
   public boolean getCanSpawnHere() {
      return this.world.getDifficulty() != EnumDifficulty.PEACEFUL && this.world.checkNoEntityCollision(this.getEntityBoundingBox(), this)
         && this.world.getCollisionBoxes(this, this.getEntityBoundingBox()).isEmpty() && !this.world.containsAnyLiquid(this.getEntityBoundingBox());
   }

   @Override
   public void writeEntityToNBT(NBTTagCompound nbt) {
      super.writeEntityToNBT(nbt);
      nbt.setShort("Anger", (short)this.angerLevel);
      nbt.setInteger("At", this.at);
      nbt.setInteger("CactusHeight", this.Cactusheight);
   }

   @Override
   public void readEntityFromNBT(NBTTagCompound nbt) {
      super.readEntityFromNBT(nbt);
      this.angerLevel = nbt.getShort("Anger");
      if (nbt.hasKey("At")) this.at = nbt.getInteger("At");
      if (nbt.hasKey("CactusHeight")) this.Cactusheight = nbt.getInteger("CactusHeight");
   }

   @Override
   public boolean attackEntityFrom(DamageSource src, float amount) {
      if (this.isEntityInvulnerable(src) || src == DamageSource.CACTUS) return false;
      Entity attacker = src.getTrueSource();
      if (attacker instanceof EntityPlayer) {
         for (EntityCactus c : this.world.getEntitiesWithinAABB(EntityCactus.class, this.getEntityBoundingBox().grow(32.0))) c.becomeAngryAt(attacker);
      }
      return super.attackEntityFrom(src, amount);
   }

   private void becomeAngryAt(Entity e) {
      this.setEntityToAttack(e);
      if (e instanceof EntityLivingBase) this.setAttackTarget((EntityLivingBase)e);
      this.angerLevel = 400 + this.rand.nextInt(400);
   }

   @Override
   protected void dropFewItems(boolean recentlyHit, int looting) {
      this.entityDropItem(new ItemStack(Blocks.CACTUS), 1.0F);
      for (int i = 0; i < 3; i++) if (this.rand.nextInt(2) == 0) this.entityDropItem(new ItemStack(Items.DYE, 1, 2), 1.0F);
   }

   @Override
   public void updatePassenger(Entity passenger) {
      if (this.isPassenger(passenger)) {
         passenger.setPosition(this.posX + Math.cos(this.rotationYaw * Math.PI / 180.0) * this.rotation,
            this.posY + this.getMountedYOffset() + passenger.getYOffset(), this.posZ + Math.sin(this.rotationYaw * Math.PI / 180.0) * this.rotation);
      }
   }

   @Override
   public double getMountedYOffset() {
      return 1.0;
   }

   @Override
   public boolean canBeSteered() {
      return false;
   }

   /** Spawns the segments above this one (1.7 did it in onSpawnWithEgg). */
   private void spawnSegmentsAbove() {
      if (this.at < this.Cactusheight && !this.world.isRemote) {
         EntityCactus c = new EntityCactus(this.world, this.at + 1, this.Cactusheight);
         c.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0.0F);
         this.world.spawnEntity(c);
         c.spawnSegmentsAbove();
         c.startRiding(this, true);
      }
   }

   @Nullable
   @Override
   public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData data) {
      this.spawnSegmentsAbove();
      return super.onInitialSpawn(difficulty, data);
   }
}
