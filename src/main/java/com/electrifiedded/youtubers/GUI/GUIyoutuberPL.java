package com.electrifiedded.youtubers.GUI;

import com.electrifiedded.youtubers.Youtubers;
import com.electrifiedded.youtubers.Entity.PlayerTrade;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/** Trade GUI with a player (or with yourself) through the Player Trader item. */
@SideOnly(Side.CLIENT)
public class GUIyoutuberPL extends GuiYTMerchantBase {
   private static final ResourceLocation TEXTURE = new ResourceLocation("youtubers", "textures/gui/PLgui_bg.png");
   private final PlayerTrade trader;

   public GUIyoutuberPL(InventoryPlayer invPlayer, PlayerTrade yt) {
      super(new ContainerMerchantPL(invPlayer, yt), yt, TEXTURE);
      this.trader = yt;
   }

   @Override
   protected EntityLivingBase displayedEntity() {
      return this.trader.getCustomer() == this.trader.getTrader() ? this.mc.player : this.trader.getTrader();
   }

   @Override
   protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
      String name = this.trader.getTrader().getName();
      String label = "supercat765".equals(name) ? "YT+ Mod Maker" : Youtubers.GetYoutuberIDbyName(name) >= 0 ? "YT+ Youtuber" : "Random Person";
      this.fontRenderer.drawString(label, 48, 67, 0x000000);
   }
}
