package com.electrifiedded.youtubers.Entity.Mobs;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.World;

/** Explosive mushroom: behaves exactly like the oceanic creeper (same 1.7 code), 4 looks. */
public class EntityExplosiveMushroom extends EntityOceanCreeper {
   private static final DataParameter<Integer> TYPE = EntityDataManager.createKey(EntityExplosiveMushroom.class, DataSerializers.VARINT);

   public EntityExplosiveMushroom(World world, int type) {
      super(world);
      this.setType(type);
   }

   public EntityExplosiveMushroom(World world) {
      super(world);
      this.setType(this.rand.nextInt(4));
   }

   @Override
   protected void entityInit() {
      super.entityInit();
      this.dataManager.register(TYPE, 0);
   }

   public int getType() {
      return this.dataManager.get(TYPE);
   }

   public void setType(int val) {
      this.dataManager.set(TYPE, val);
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
}
