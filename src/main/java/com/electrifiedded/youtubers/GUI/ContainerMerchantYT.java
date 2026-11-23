package com.electrifiedded.youtubers.GUI;

import com.electrifiedded.youtubers.Entity.EntityYTBase;
import javax.annotation.Nullable;
import net.minecraft.entity.IMerchant;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ContainerMerchant;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Trading with a youtuber: vanilla merchant slots 0..38 (trade slots moved to the mod layout) plus the youtuber's held
 * item + 7 backpack slots (39..46, bottom row) and 4 armour slots (47..50, left column, helmet on top).
 */
public class ContainerMerchantYT extends ContainerMerchant implements YTNetwork.IMerchantContainer {
   private static final EntityEquipmentSlot[] ARMOR_TOP_DOWN = {EntityEquipmentSlot.HEAD, EntityEquipmentSlot.CHEST, EntityEquipmentSlot.LEGS, EntityEquipmentSlot.FEET};
   private static final int EXTRA_START = 39, EXTRA_END = 51;
   private final EntityYTBase theMerchant;

   public ContainerMerchantYT(InventoryPlayer playerInventory, EntityYTBase merchant) {
      super(playerInventory, merchant, merchant.world);
      this.theMerchant = merchant;
      ContainerMerchantPL.layoutTradeSlots(this.inventorySlots.get(0), this.inventorySlots.get(1), this.inventorySlots.get(2));
      InventoryMerchantYT inv = merchant.inventory;
      for (int i = 0; i < 8; i++) this.addSlotToContainer(new Slot(inv, i, 26 + i * 18, 62));
      for (int k = 0; k < 4; k++) {
         final EntityEquipmentSlot type = ARMOR_TOP_DOWN[k];
         this.addSlotToContainer(new Slot(inv, InventoryMerchantYT.ARMOR_START + 3 - k, 8, 8 + k * 18) {
            @Override
            public int getSlotStackLimit() {
               return 1;
            }

            @Override
            public boolean isItemValid(ItemStack stack) {
               return !stack.isEmpty() && stack.getItem().isValidArmor(stack, type, ContainerMerchantYT.this.theMerchant);
            }

            @Nullable
            @Override
            @SideOnly(Side.CLIENT)
            public String getSlotTexture() {
               return ItemArmor.EMPTY_SLOT_NAMES[type.getIndex()];
            }
         });
      }
   }

   @Override
   public IMerchant getMerchantRef() {
      return this.theMerchant;
   }

   @Override
   public boolean canInteractWith(EntityPlayer player) {
      return super.canInteractWith(player) && this.theMerchant.isEntityAlive();
   }

   @Override
   public ItemStack transferStackInSlot(EntityPlayer player, int index) {
      if (index < EXTRA_START) {
         Slot slot = this.inventorySlots.get(index);
         // from the player inventory: armour goes on the youtuber first, the rest follows vanilla rules
         if (index >= 3 && slot != null && slot.getHasStack() && slot.getStack().getItem() instanceof ItemArmor) {
            ItemStack s = slot.getStack();
            ItemStack copy = s.copy();
            if (this.mergeItemStack(s, EXTRA_END - 4, EXTRA_END, false)) {
               if (s.isEmpty()) slot.putStack(ItemStack.EMPTY); else slot.onSlotChanged();
               return copy;
            }
         }
         return super.transferStackInSlot(player, index);
      }
      Slot slot = this.inventorySlots.get(index);
      if (slot == null || !slot.getHasStack()) return ItemStack.EMPTY;
      ItemStack s = slot.getStack();
      ItemStack copy = s.copy();
      if (!this.mergeItemStack(s, 3, 39, true)) return ItemStack.EMPTY;
      if (s.isEmpty()) slot.putStack(ItemStack.EMPTY); else slot.onSlotChanged();
      return copy;
   }
}
