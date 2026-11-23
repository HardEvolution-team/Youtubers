package com.electrifiedded.youtubers.Entity.Render;

import com.electrifiedded.archaiclib.client.skin.LegacyPlayerSkins;
import com.electrifiedded.youtubers.Entity.EntityYTBase;
import com.electrifiedded.youtubers.Entity.model.ModelBipedAlt;
import com.electrifiedded.youtubers.Entity.model.ModelPlayerAlt;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Renderer of the mod's human NPCs. Picks per entity a 64x32 biped (old skins), a 64x64 player model or a slim
 * player model, all with the NPC sitting pose; held items, armour (also posed) and heads like players.
 */
@SideOnly(Side.CLIENT)
public abstract class RenderNPC<T extends EntityYTBase> extends RenderLiving<T> {
   private final ModelBiped legacy = new ModelBipedAlt(0.0F);
   private final ModelBiped wide = new ModelPlayerAlt(0.0F, false);
   private final ModelBiped slim = new ModelPlayerAlt(0.0F, true);

   protected RenderNPC(RenderManager manager, float shadow) {
      super(manager, new ModelBipedAlt(0.0F), shadow);
      this.mainModel = this.legacy;
      this.addLayer(new LayerHeldItem(this));
      this.addLayer(new LayerBipedArmor(this) {
         @Override
         protected void initArmor() {
            this.modelLeggings = new ModelBipedAlt(0.5F);
            this.modelArmor = new ModelBipedAlt(1.0F);
         }
      });
   }

   /** Skin texture of the entity. */
   protected abstract ResourceLocation skin(T entity);

   /** Whether the skin uses slim (3 pixel) arms; only asked for 64x64 skins. */
   protected boolean slimArms(T entity) {
      return false;
   }

   @Override
   public void doRender(T entity, double x, double y, double z, float yaw, float partialTicks) {
      ResourceLocation tex = this.skin(entity);
      this.mainModel = LegacyPlayerSkins.isModernLayout(tex) ? (this.slimArms(entity) ? this.slim : this.wide) : this.legacy;
      super.doRender(entity, x, y, z, yaw, partialTicks);
   }

   @Override
   protected ResourceLocation getEntityTexture(T entity) {
      return this.skin(entity);
   }

   @Override
   public void transformHeldFull3DItemLayer() {
      net.minecraft.client.renderer.GlStateManager.translate(0.0F, 0.1875F, 0.0F);
   }
}
