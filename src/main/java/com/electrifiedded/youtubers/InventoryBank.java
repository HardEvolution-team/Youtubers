package com.electrifiedded.youtubers;

import com.electrifiedded.youtubers.Entity.TileEntity.TileEntityBank;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

/** Per-player 27 slot bank (ender chest style), opened through any Bank block. */
public class InventoryBank extends InventoryBasic {
   private TileEntityBank associatedChest;

   public InventoryBank() {
      super("Bank", false, 27);
   }

   public void setChestTileEntity(TileEntityBank te) {
      this.associatedChest = te;
   }

   public void loadInventoryFromNBT(NBTTagList list) {
      this.clear();
      for (int k = 0; k < list.tagCount(); k++) {
         NBTTagCompound tag = list.getCompoundTagAt(k);
         int j = tag.getByte("Slot") & 255;
         if (j < this.getSizeInventory()) this.setInventorySlotContents(j, new ItemStack(tag));
      }
   }

   public NBTTagList saveInventoryToNBT() {
      NBTTagList list = new NBTTagList();
      for (int i = 0; i < this.getSizeInventory(); i++) {
         ItemStack s = this.getStackInSlot(i);
         if (!s.isEmpty()) {
            NBTTagCompound tag = new NBTTagCompound();
            tag.setByte("Slot", (byte)i);
            s.writeToNBT(tag);
            list.appendTag(tag);
         }
      }
      return list;
   }

   /** Merges into matching stacks first, then empty slots; shrinks the given stack. True if anything was stored. */
   public boolean addItemStackToInventory(ItemStack stack) {
      if (stack.isEmpty()) return false;
      int before = stack.getCount();
      if (stack.isStackable()) {
         for (int i = 0; i < this.getSizeInventory() && !stack.isEmpty(); i++) {
            ItemStack s = this.getStackInSlot(i);
            if (!s.isEmpty() && ItemStack.areItemsEqual(s, stack) && ItemStack.areItemStackTagsEqual(s, stack)) {
               int move = Math.min(stack.getCount(), Math.min(s.getMaxStackSize(), this.getInventoryStackLimit()) - s.getCount());
               if (move > 0) { s.grow(move); stack.shrink(move); }
            }
         }
      }
      for (int i = 0; i < this.getSizeInventory() && !stack.isEmpty(); i++) {
         if (this.getStackInSlot(i).isEmpty()) {
            int move = Math.min(stack.getCount(), Math.min(stack.getMaxStackSize(), this.getInventoryStackLimit()));
            this.setInventorySlotContents(i, stack.splitStack(move));
         }
      }
      this.markDirty();
      return stack.getCount() < before;
   }

   @Override
   public boolean isUsableByPlayer(EntityPlayer player) {
      return (this.associatedChest == null || this.associatedChest.canBeUsed(player)) && super.isUsableByPlayer(player);
   }

   @Override
   public void openInventory(EntityPlayer player) {
      if (this.associatedChest != null) this.associatedChest.openChest();
      super.openInventory(player);
   }

   @Override
   public void closeInventory(EntityPlayer player) {
      if (this.associatedChest != null) this.associatedChest.closeChest();
      super.closeInventory(player);
      this.associatedChest = null;
   }
}
