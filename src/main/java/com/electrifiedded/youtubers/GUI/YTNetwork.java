package com.electrifiedded.youtubers.GUI;

import io.netty.buffer.ByteBuf;
import java.io.IOException;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.IMerchant;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.Container;
import net.minecraft.network.PacketBuffer;
import net.minecraft.village.MerchantRecipeList;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * The 1.7 trade GUIs read the offers from the client copy of the merchant, which generated its own random list, so the
 * client showed other trades than the server used. The server list is now sent to the client when a trade GUI opens.
 */
public final class YTNetwork {
   public static SimpleNetworkWrapper CHANNEL;

   private YTNetwork() {}

   public static void init() {
      CHANNEL = NetworkRegistry.INSTANCE.newSimpleChannel("youtubers");
      CHANNEL.registerMessage(RecipesHandler.class, MsgRecipes.class, 0, Side.CLIENT);
   }

   /** Called on the server right after the trade container was opened. */
   public static void sendRecipes(EntityPlayer player, IMerchant merchant) {
      if (!(player instanceof EntityPlayerMP) || CHANNEL == null) return;
      MerchantRecipeList list = merchant.getRecipes(player);
      if (list == null) return;
      CHANNEL.sendTo(new MsgRecipes(player.openContainer.windowId, list), (EntityPlayerMP)player);
   }

   /** Containers of the mod's trade GUIs expose the client side merchant the recipes go to. */
   public interface IMerchantContainer {
      IMerchant getMerchantRef();
   }

   public static class MsgRecipes implements IMessage {
      int windowId;
      MerchantRecipeList list;

      public MsgRecipes() {
      }

      MsgRecipes(int windowId, MerchantRecipeList list) {
         this.windowId = windowId;
         this.list = list;
      }

      @Override
      public void toBytes(ByteBuf buf) {
         PacketBuffer pb = new PacketBuffer(buf);
         pb.writeInt(this.windowId);
         this.list.writeToBuf(pb);
      }

      @Override
      public void fromBytes(ByteBuf buf) {
         PacketBuffer pb = new PacketBuffer(buf);
         this.windowId = pb.readInt();
         try {
            this.list = MerchantRecipeList.readFromBuf(pb);
         } catch (IOException e) {
            this.list = new MerchantRecipeList();
         }
      }
   }

   public static class RecipesHandler implements IMessageHandler<MsgRecipes, IMessage> {
      @Override
      public IMessage onMessage(MsgRecipes msg, MessageContext ctx) {
         apply(msg);
         return null;
      }

      @SideOnly(Side.CLIENT)
      private static void apply(MsgRecipes msg) {
         Minecraft mc = Minecraft.getMinecraft();
         mc.addScheduledTask(() -> {
            if (mc.player == null) return;
            Container c = mc.player.openContainer;
            if (c != null && c.windowId == msg.windowId && c instanceof IMerchantContainer) {
               ((IMerchantContainer)c).getMerchantRef().setRecipes(msg.list);
            }
         });
      }
   }
}
