package com.electrifiedded.youtubers.GUI;

import com.electrifiedded.youtubers.Entity.EntityYTBase;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/** Trade + equipment GUI of a youtuber. */
@SideOnly(Side.CLIENT)
public class GUIyoutuber extends GuiYTMerchantBase {
   private static final ResourceLocation TEXTURE = new ResourceLocation("youtubers", "textures/gui/YTgui_bg.png");
   private final EntityYTBase entity;

   public GUIyoutuber(InventoryPlayer invPlayer, EntityYTBase yt) {
      super(new ContainerMerchantYT(invPlayer, yt), yt, TEXTURE);
      this.entity = yt;
   }

   @Override
   protected EntityLivingBase displayedEntity() {
      return this.entity;
   }
}
