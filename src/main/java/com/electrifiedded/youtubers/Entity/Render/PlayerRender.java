package com.electrifiedded.youtubers.Entity.Render;

import com.electrifiedded.archaiclib.client.skin.LegacyPlayerSkins;
import com.electrifiedded.youtubers.YTConfig;
import com.electrifiedded.youtubers.Entity.PlayerEntity;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * "Random player" NPCs wear the current skin of the player they are named after.
 * <p>
 * The 1.7 mod downloaded {@code http://skins.minecraft.net/MinecraftSkins/<name>.png} into a "YoutubersSkin" resource
 * pack the player had to enable (and cut 64x64 skins down to 64x32). That server is gone, so the skins are now
 * resolved through the Mojang profile service and loaded by the game's own skin manager (ArchaicLib
 * LegacyPlayerSkins): no resource pack, 64x64 and slim skins supported, cached like player heads. Names without an
 * account, offline mode or "Don't download player skins" = the default Steve/Alex skin of that name.
 */
@SideOnly(Side.CLIENT)
public class PlayerRender extends RenderNPC<PlayerEntity> {
   public PlayerRender(RenderManager manager) {
      super(manager, 0.2F);
   }

   @Override
   protected ResourceLocation skin(PlayerEntity e) {
      if (YTConfig.Offline) return DefaultPlayerSkin.getDefaultSkin(EntityPlayer.getOfflineUUID(e.getName()));
      return LegacyPlayerSkins.getSkin(e.getName());
   }

   @Override
   protected boolean slimArms(PlayerEntity e) {
      return LegacyPlayerSkins.isSlim(e.getName());
   }
}
