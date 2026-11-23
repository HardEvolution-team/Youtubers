package com.electrifiedded.youtubers.GUI;

import com.electrifiedded.youtubers.Entity.EntityYTBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;

/**
 * The youtuber's own inventory, edited in its trade GUI: slot 0 = held item, 1..7 = backpack,
 * 8..11 = armour boots, legs, chest, helmet. Held item and armour are mirrored to the entity equipment.
 */
public class InventoryMerchantYT implements IInventory {
   public static final int HELD = 0, BACKPACK = 7, ARMOR_START = 8, SIZE = 12;
   private static final EntityEquipmentSlot[] ARMOR = {EntityEquipmentSlot.FEET, EntityEquipmentSlot.LEGS, EntityEquipmentSlot.CHEST, EntityEquipmentSlot.HEAD};
   private final EntityYTBase theMerchant;
   private final NonNullList<ItemStack> items = NonNullList.withSize(SIZE, ItemStack.EMPTY);

   public InventoryMerchantYT(EntityYTBase merchant) {
      this.theMerchant = merchant;
   }

   private static EntityEquipmentSlot equipmentFor(int index) {
      if (index == HELD) return EntityEquipmentSlot.MAINHAND;
      if (index >= ARMOR_START) return ARMOR[index - ARMOR_START];
      return null;
   }

   /** armor index 0 boots .. 3 helmet (1.7 armorInventory order). */
   public ItemStack getArmor(int i) {
      return this.items.get(ARMOR_START + i);
   }

   @Override public int getSizeInventory() { return SIZE; }
   @Override public boolean isEmpty() { for (ItemStack s : this.items) if (!s.isEmpty()) return false; return true; }

   @Override
   public ItemStack getStackInSlot(int index) {
      return index >= 0 && index < SIZE ? this.items.get(index) : ItemStack.EMPTY;
   }

   @Override
   public ItemStack decrStackSize(int index, int count) {
      ItemStack s = net.minecraft.inventory.ItemStackHelper.getAndSplit(this.items, index, count);
      if (!s.isEmpty()) this.sync(index);
      return s;
   }

   @Override
   public ItemStack removeStackFromSlot(int index) {
      ItemStack s = net.minecraft.inventory.ItemStackHelper.getAndRemove(this.items, index);
      this.sync(index);
      return s;
   }

   @Override
   public void setInventorySlotContents(int index, ItemStack stack) {
      if (index < 0 || index >= SIZE) return;
      this.items.set(index, stack);
      this.sync(index);
   }

   private void sync(int index) {
      EntityEquipmentSlot slot = equipmentFor(index);
      if (slot != null && !this.theMerchant.world.isRemote) this.theMerchant.setItemStackToSlot(slot, this.items.get(index).copy());
   }

   /** Re-mirrors all equipment (after loading). */
   public void syncAll() {
      for (int i = 0; i < SIZE; i++) this.sync(i);
   }

   public NBTTagList writeToNBT(NBTTagList list) {
      for (int i = 0; i < SIZE; i++) {
         ItemStack s = this.items.get(i);
         if (!s.isEmpty()) {
            NBTTagCompound tag = new NBTTagCompound();
            tag.setByte("Slot", (byte)i);
            s.writeToNBT(tag);
            list.appendTag(tag);
         }
      }
      return list;
   }

   public void readFromNBT(NBTTagList list) {
      this.clear();
      for (int i = 0; i < list.tagCount(); i++) {
         NBTTagCompound tag = list.getCompoundTagAt(i);
         int j = tag.getByte("Slot") & 255;
         if (j < SIZE) this.items.set(j, new ItemStack(tag));
      }
   }

   @Override public String getName() { return "entity.villager.name"; }
   @Override public boolean hasCustomName() { return false; }
   @Override public ITextComponent getDisplayName() { return new TextComponentTranslation(this.getName()); }
   @Override public int getInventoryStackLimit() { return 64; }
   @Override public void markDirty() { }
   @Override public boolean isUsableByPlayer(EntityPlayer player) { return this.theMerchant.isEntityAlive() && player.getDistanceSq(this.theMerchant) < 64.0; }
   @Override public void openInventory(EntityPlayer player) { }
   @Override public void closeInventory(EntityPlayer player) { }
   @Override public boolean isItemValidForSlot(int index, ItemStack stack) { return true; }
   @Override public int getField(int id) { return 0; }
   @Override public void setField(int id, int value) { }
   @Override public int getFieldCount() { return 0; }
   @Override public void clear() { for (int i = 0; i < SIZE; i++) this.items.set(i, ItemStack.EMPTY); }
}
