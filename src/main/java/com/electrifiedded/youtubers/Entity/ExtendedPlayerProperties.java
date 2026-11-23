package com.electrifiedded.youtubers.Entity;

import com.electrifiedded.archaiclib.legacy.entity.ExtendedEntityProperties;
import com.electrifiedded.archaiclib.legacy.entity.IExtendedEntityProperties;
import com.electrifiedded.youtubers.InventoryBank;
import java.util.Calendar;
import java.util.GregorianCalendar;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.world.World;

/** Per-player data of the mod (1.7 "ExtendedPropsYT"): bank, player-trade offers, jetpack fuel, daily mail. */
public class ExtendedPlayerProperties implements IExtendedEntityProperties {
   public static final String NAME = "ExtendedPropsYT";
   public int JetPackTime;
   public PlayerTrade Trader;
   private InventoryBank theInventoryBank;
   public int MailDay;
   public EntityYTBase YTTrade;

   public ExtendedPlayerProperties(EntityPlayer pl) {
      this.Trader = new PlayerTrade(pl);
      this.theInventoryBank = new InventoryBank();
      this.MailDay = 0;
      this.YTTrade = null;
   }

   /** Properties of the player, created on first use (1.7 registered them in EntityConstructing). */
   public static synchronized ExtendedPlayerProperties get(EntityPlayer player) {
      ExtendedPlayerProperties p = ExtendedEntityProperties.getAs(player, NAME);
      if (p == null) {
         p = new ExtendedPlayerProperties(player);
         ExtendedEntityProperties.register(player, NAME, p);
      }
      return p;
   }

   public InventoryBank getInventoryBank() {
      return this.theInventoryBank;
   }

   public void setInventoryBank(InventoryBank inv) {
      this.theInventoryBank = inv;
   }

   public boolean CanGetMail() {
      return this.MailDay != new GregorianCalendar().get(Calendar.DAY_OF_YEAR);
   }

   public void GotMail() {
      this.MailDay = new GregorianCalendar().get(Calendar.DAY_OF_YEAR);
   }

   @Override
   public void saveNBTData(NBTTagCompound compound) {
      NBTTagCompound properties = new NBTTagCompound();
      properties.setTag("BankItems", this.theInventoryBank.saveInventoryToNBT());
      properties.setInteger("Riches", this.Trader.wealth);
      if (this.Trader.buyingList != null) properties.setTag("Offers", this.Trader.buyingList.getRecipiesAsTags());
      properties.setInteger("MailDay", this.MailDay);
      compound.setTag("YTExt", properties);
   }

   @Override
   public void loadNBTData(NBTTagCompound compound) {
      if (!compound.hasKey("YTExt", 10)) return;
      NBTTagCompound properties = compound.getCompoundTag("YTExt");
      if (properties.hasKey("BankItems")) {
         NBTTagList list = properties.getTagList("BankItems", 10);
         this.theInventoryBank.loadInventoryFromNBT(list);
      }
      this.Trader.wealth = properties.getInteger("Riches");
      if (properties.hasKey("Offers", 10)) this.Trader.buyingList = new MerchantRecipeList(properties.getCompoundTag("Offers"));
      this.MailDay = properties.getInteger("MailDay");
   }

   /** Death / end-portal respawn: keep the bank and the rest like an ender chest keeps its items. */
   public void copyFrom(ExtendedPlayerProperties old) {
      NBTTagCompound tag = new NBTTagCompound();
      old.saveNBTData(tag);
      this.loadNBTData(tag);
   }

   @Override
   public void init(Entity entity, World world) {
      this.JetPackTime = -1;
   }
}
