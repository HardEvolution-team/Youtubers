package com.electrifiedded.youtubers.Entity;

import com.electrifiedded.youtubers.Entity.AI.pet.EntityAISitPet;
import java.util.UUID;
import javax.annotation.Nullable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

/** Pet of a youtuber (Trayourus, Grimm, Bomby...): owned by any entity (UUID), follows and defends it, can sit. */
public class YoutuberPetBase extends EntityCreature {
   private static final DataParameter<Byte> FLAGS = EntityDataManager.createKey(YoutuberPetBase.class, DataSerializers.BYTE);
   private static final DataParameter<String> OWNER = EntityDataManager.createKey(YoutuberPetBase.class, DataSerializers.STRING);
   protected int regentimer = 0;
   protected EntityAISitPet aiSit = new EntityAISitPet(this);
   private Entity cachedOwner;

   public YoutuberPetBase(World world) {
      super(world);
   }

   @Override
   protected void entityInit() {
      super.entityInit();
      this.dataManager.register(FLAGS, (byte)0);
      this.dataManager.register(OWNER, this.getUniqueID().toString());
   }

   @Override
   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      // 1.7 EntityCreature had no attack damage attribute (pets hit for 0); 1.12 melee AI needs one
      this.getAttributeMap().registerAttribute(net.minecraft.entity.SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(2.0);
   }

   public boolean shouldAttackEntity(EntityLivingBase target, EntityLivingBase owner) {
      return true;
   }

   @Nullable
   public UUID getOwnerUUID() {
      try {
         return UUID.fromString(this.dataManager.get(OWNER));
      } catch (IllegalArgumentException e) {
         return null;
      }
   }

   @Nullable
   public Entity getOwner() {
      UUID uuid = this.getOwnerUUID();
      if (uuid == null) return null;
      if (this.cachedOwner != null && !this.cachedOwner.isDead && uuid.equals(this.cachedOwner.getUniqueID())) return this.cachedOwner;
      this.cachedOwner = null;
      if (this.world instanceof WorldServer) {
         this.cachedOwner = ((WorldServer)this.world).getEntityFromUuid(uuid);
      } else {
         for (Entity e : this.world.loadedEntityList) {
            if (uuid.equals(e.getUniqueID())) {
               this.cachedOwner = e;
               break;
            }
         }
      }
      return this.cachedOwner;
   }

   public void setOwner(Entity owner) {
      this.dataManager.set(OWNER, owner.getUniqueID().toString());
      this.cachedOwner = owner;
   }

   public void setOwner(String uuid) {
      this.dataManager.set(OWNER, uuid);
      this.cachedOwner = null;
   }

   @Override
   public boolean getAlwaysRenderNameTagForRender() {
      return true;
   }

   @Override
   public void onLivingUpdate() {
      super.onLivingUpdate();
      if (++this.regentimer == 100) {
         this.heal(this.isTamed() ? 2.0F : 1.0F);
         this.regentimer = 0;
      }
   }

   @Override
   public void writeEntityToNBT(NBTTagCompound nbt) {
      super.writeEntityToNBT(nbt);
      nbt.setBoolean("Sitting", this.isSitting());
      nbt.setBoolean("Tamed", this.isTamed());
      nbt.setString("OwnerUUID", this.dataManager.get(OWNER));
   }

   @Override
   public void readEntityFromNBT(NBTTagCompound nbt) {
      super.readEntityFromNBT(nbt);
      this.aiSit.setSitting(nbt.getBoolean("Sitting"));
      this.setSitting(nbt.getBoolean("Sitting"));
      if (nbt.getBoolean("Tamed")) this.setTamed(true);
      if (!nbt.getString("OwnerUUID").isEmpty()) this.setOwner(nbt.getString("OwnerUUID"));
   }

   @Override
   protected boolean canDespawn() {
      return false;
   }

   @Override
   public boolean processInteract(EntityPlayer player, EnumHand hand) {
      if (hand == EnumHand.MAIN_HAND && this.isTamed() && this.getOwner() == player && player.getHeldItem(hand).isEmpty()) {
         if (!this.world.isRemote) {
            this.setSitting(!this.isSitting());
            this.aiSit.setSitting(this.isSitting());
         }
         return true;
      }
      return super.processInteract(player, hand);
   }

   public boolean isTamed() {
      return (this.dataManager.get(FLAGS) & 4) != 0;
   }

   public void setTamed(boolean tamed) {
      byte b = this.dataManager.get(FLAGS);
      this.dataManager.set(FLAGS, (byte)(tamed ? b | 4 : b & ~4));
   }

   public boolean isSitting() {
      return (this.dataManager.get(FLAGS) & 1) != 0;
   }

   public void setSitting(boolean sitting) {
      byte b = this.dataManager.get(FLAGS);
      this.dataManager.set(FLAGS, (byte)(sitting ? b | 1 : b & ~1));
   }

   /** Makes the closest player (within ~2 blocks) the owner. */
   public void getPlayerOwner() {
      for (EntityPlayer p : this.world.playerEntities) {
         if (this.getDistanceSq(p) < 5.0) {
            this.setOwner(p);
            this.setTamed(true);
         }
      }
   }
}
