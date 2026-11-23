package com.electrifiedded.youtubers.Entity.Render;

import com.electrifiedded.archaiclib.client.skin.LegacyPlayerSkins;
import com.electrifiedded.youtubers.YTConfig;
import com.electrifiedded.youtubers.Entity.EntityYoutuber;
import com.electrifiedded.youtubers.Entity.Youtuber;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Youtubers wear the skin bundled with the original mod (per subtype). The 1.7 renderer also tried to download the
 * youtuber's skin from skins.minecraft.net (a server that no longer exists) into a resource pack and then never used
 * it; with "Use current youtuber skins" enabled the port really uses the account's current skin (Mojang profile
 * service) once it is loaded, falling back to the bundled one.
 */
@SideOnly(Side.CLIENT)
public class RenderYoutuber extends RenderNPC<EntityYoutuber> {
   public RenderYoutuber(RenderManager manager) {
      super(manager, 0.2F);
   }

   private static boolean live(EntityYoutuber e) {
      return YTConfig.LiveYoutuberSkins && !YTConfig.Offline && LegacyPlayerSkins.isLoaded(e.getName());
   }

   @Override
   protected ResourceLocation skin(EntityYoutuber e) {
      if (YTConfig.LiveYoutuberSkins && !YTConfig.Offline) {
         ResourceLocation s = LegacyPlayerSkins.getSkin(e.getName()); // starts the lookup
         if (LegacyPlayerSkins.isLoaded(e.getName())) return s;
      }
      Youtuber yt = e.youtuber();
      int type = e.getSubtype();
      if (type < 0 || type >= yt.subTypes) type = 0;
      return yt.subtextures[type];
   }

   @Override
   protected boolean slimArms(EntityYoutuber e) {
      return live(e) && LegacyPlayerSkins.isSlim(e.getName());
   }
}
