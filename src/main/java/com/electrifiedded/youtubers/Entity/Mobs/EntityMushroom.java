package com.electrifiedded.youtubers.Entity.Mobs;

import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/** Tiny walking mushroom (brown or red) that bites players; burns in daylight except on mushroom islands. */
public class EntityMushroom extends EntityMob {
   private static final DataParameter<Integer> TYPE = EntityDataManager.createKey(EntityMushroom.class, DataSerializers.VARINT);

   public EntityMushroom(World world, int type) {
      super(world);
      this.setType(type);
      this.init();
   }

   public EntityMushroom(World world) {
      super(world);
      this.setType(this.rand.nextInt(2));
      this.init();
   }

   private void init() {
      this.setSize(0.3F, 0.3F);
      this.tasks.addTask(0, new EntityAISwimming(this));
      this.tasks.addTask(1, new EntityAIAttackMelee(this, 1.0, false));
      this.tasks.addTask(2, new EntityAIWander(this, 1.0));
      this.tasks.addTask(3, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
      this.tasks.addTask(4, new EntityAILookIdle(this));
      // 1.7 EntityAIAttackOnCollide(this, EntityPlayer.class, ...) attacked any player it ran into
      this.targetTasks.addTask(1, new EntityAINearestAttackableTarget<>(this, EntityPlayer.class, true));
   }

   @Override
   protected void entityInit() {
      super.entityInit();
      this.dataManager.register(TYPE, 0);
   }

   @Override
   public void writeEntityToNBT(NBTTagCompound nbt) {
      super.writeEntityToNBT(nbt);
      nbt.setInteger("Type", this.getType());
   }

   @Override
   public void readEntityFromNBT(NBTTagCompound nbt) {
      super.readEntityFromNBT(nbt);
      this.setType(nbt.getInteger("Type"));
   }

   public int getType() {
      return this.dataManager.get(TYPE);
   }

   public void setType(int val) {
      this.dataManager.set(TYPE, val);
   }

   @Override
   protected Item getDropItem() {
      return Item.getItemFromBlock(this.getType() == 1 ? Blocks.RED_MUSHROOM : Blocks.BROWN_MUSHROOM);
   }

   @Override
   protected net.minecraft.util.ResourceLocation getLootTable() {
      return null; // drops its mushroom through getDropItem like in 1.7
   }

   @Override
   public void onLivingUpdate() {
      if (this.world.isDaytime() && !this.world.isRemote) {
         float f = this.getBrightness();
         BlockPos p = new BlockPos(this);
         if (this.world.getBiome(p) != Biomes.MUSHROOM_ISLAND && this.world.getBiome(p) != Biomes.MUSHROOM_ISLAND_SHORE && f > 0.5F
            && this.rand.nextFloat() * 30.0F < (f - 0.4F) * 2.0F && this.world.canSeeSky(p)) {
            this.setFire(8);
         }
      }
      super.onLivingUpdate();
   }
}
