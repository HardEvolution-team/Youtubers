package com.electrifiedded.youtubers.Items;

import com.electrifiedded.youtubers.InventoryBank;
import com.electrifiedded.youtubers.YTItems;
import com.electrifiedded.youtubers.Youtubers;
import com.electrifiedded.youtubers.Entity.ExtendedPlayerProperties;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Bank card (paper .. emerald): right-click moves one money note up to the card's limit from the inventory into the
 * bank, then auto-crafts notes in the bank up to the card's combine limit (5 or 2 notes into the next value).
 */
public class ItemBankCard extends Item {
   private static final String[] TYPES = {"paper", "iron", "redstone", "lapis", "gold", "diamond", "emerald"};
   private static final int[] DEPOSIT = {0, 2, 4, 6, 8, 10, 12};
   private static final int[] COMBINE = {-1, -1, 2, 3, 5, 8, 12};

   public ItemBankCard() {
      this.setHasSubtypes(true);
      this.setMaxStackSize(1);
      this.setCreativeTab(Youtubers.tabMisc);
   }

   private static int lvl(ItemStack s) {
      return Math.max(0, Math.min(6, s.getMetadata()));
   }

   private ItemStack tryRemoveItem(InventoryPlayer inventory, int max) {
      for (int a = 0; a < inventory.getSizeInventory(); a++) {
         ItemStack s = inventory.getStackInSlot(a);
         if (!s.isEmpty() && s.getItem() instanceof ItemMoney && ((ItemMoney)s.getItem()).n <= max) return inventory.decrStackSize(a, 1);
      }
      return ItemStack.EMPTY;
   }

   @Override
   public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
      ItemStack card = player.getHeldItem(hand);
      if (!world.isRemote) {
         InventoryBank inv = ExtendedPlayerProperties.get(player).getInventoryBank();
         ItemStack item = this.tryRemoveItem(player.inventory, DEPOSIT[lvl(card)]);
         if (!item.isEmpty() && !inv.addItemStackToInventory(item)) player.inventory.addItemStackToInventory(item);
         this.combineMoney(inv, COMBINE[lvl(card)]);
      }
      return new ActionResult<>(EnumActionResult.SUCCESS, card);
   }

   @Override
   @SideOnly(Side.CLIENT)
   public void addInformation(ItemStack stack, @Nullable World world, List<String> list, ITooltipFlag flag) {
      int l = lvl(stack);
      list.add("Deposits up to " + ItemMoney.getValue(DEPOSIT[l]));
      if (COMBINE[l] != -1) list.add("AutoCrafts up to " + ItemMoney.getValue(COMBINE[l]));
      if (l != 6) {
         list.add("Next Level " + TYPES[l + 1]);
         list.add("Deposits up to " + ItemMoney.getValue(DEPOSIT[l + 1]));
         if (COMBINE[l + 1] != -1) list.add("AutoCrafts up to " + ItemMoney.getValue(COMBINE[l + 1]));
      }
   }

   /** 5 notes of an even index (1$,10$,...) or 2 notes of an odd index (5$,50$,...) become one note of the next value. */
   public void combineMoney(InventoryBank inventory, int max) {
      boolean repeat = true;
      while (repeat) {
         repeat = false;
         for (int a = 0; a < inventory.getSizeInventory(); a++) {
            ItemStack s = inventory.getStackInSlot(a);
            if (s.isEmpty() || !(s.getItem() instanceof ItemMoney)) continue;
            int n = ((ItemMoney)s.getItem()).n;
            if (n > max || n >= 12) continue;
            int need = n % 2 == 0 ? 5 : 2;
            if (s.getCount() > need) {
               s.shrink(need);
               ItemStack up = new ItemStack(YTItems.Money[n + 1], 1);
               if (!inventory.addItemStackToInventory(up)) { s.grow(need); continue; }
               repeat = true;
            }
         }
      }
      inventory.markDirty();
   }

   @Override
   public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> list) {
      if (this.isInCreativeTab(tab)) for (int i = 0; i < 7; i++) list.add(new ItemStack(this, 1, i));
   }

   @Override
   public String getTranslationKey(ItemStack stack) {
      return super.getTranslationKey() + "." + TYPES[lvl(stack)];
   }
}
