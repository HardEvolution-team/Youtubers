package com.electrifiedded.youtubers.GUI;

import com.electrifiedded.youtubers.Youtubers;
import com.electrifiedded.youtubers.Entity.EntityYTBase;
import com.electrifiedded.youtubers.Entity.ExtendedPlayerProperties;
import com.electrifiedded.youtubers.Entity.PlayerTrade;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

/** GUI 0: youtuber trade/equipment (x = entity id). GUI 1: player trade (x = id of the trading player, may be yourself). */
public class YTGuiHandler implements IGuiHandler {
   public static final int YOUTUBER = 0, PLAYER = 1;

   /** Server side: opens the youtuber GUI and sends the offers to the client. */
   public static void openYoutuber(EntityPlayer player, EntityYTBase yt) {
      if (player.world.isRemote) return;
      yt.setCustomer(player);
      player.openGui(Youtubers.instance, YOUTUBER, player.world, yt.getEntityId(), 0, 0);
      YTNetwork.sendRecipes(player, yt);
   }

   /** Server side: opens the trade GUI of {@code trader} (a player) for {@code player}. */
   public static void openPlayer(EntityPlayer player, EntityPlayer trader) {
      if (player.world.isRemote) return;
      PlayerTrade t = ExtendedPlayerProperties.get(trader).Trader;
      t.setCustomer(player);
      player.openGui(Youtubers.instance, PLAYER, player.world, trader.getEntityId(), 0, 0);
      YTNetwork.sendRecipes(player, t);
   }

   @Override
   public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
      Entity ent = world.getEntityByID(x);
      if (ID == YOUTUBER && ent instanceof EntityYTBase) return new ContainerMerchantYT(player.inventory, (EntityYTBase)ent);
      if (ID == PLAYER && ent instanceof EntityPlayer) return new ContainerMerchantPL(player.inventory, ExtendedPlayerProperties.get((EntityPlayer)ent).Trader);
      return null;
   }

   @Override
   public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
      Entity ent = world.getEntityByID(x);
      if (ID == YOUTUBER && ent instanceof EntityYTBase) {
         ((EntityYTBase)ent).setCustomer(player);
         return Youtubers.proxy.newGUIyoutuber(player.inventory, (EntityYTBase)ent);
      }
      if (ID == PLAYER && ent instanceof EntityPlayer) {
         PlayerTrade t = ExtendedPlayerProperties.get((EntityPlayer)ent).Trader;
         t.setCustomer(player);
         return Youtubers.proxy.newGUIyoutuberPL(player.inventory, t);
      }
      return null;
   }
}
