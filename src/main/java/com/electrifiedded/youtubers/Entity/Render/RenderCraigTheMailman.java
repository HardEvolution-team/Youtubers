package com.electrifiedded.youtubers.Entity.Render;

import com.electrifiedded.youtubers.Entity.EntityCraigTheMailman;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderCraigTheMailman extends RenderNPC<EntityCraigTheMailman> {
   private static final ResourceLocation TEXTURE = new ResourceLocation("youtubers", "textures/entity/CraigTheMailman.png");

   public RenderCraigTheMailman(RenderManager manager) {
      super(manager, 0.2F);
   }

   @Override
   protected ResourceLocation skin(EntityCraigTheMailman e) {
      return TEXTURE;
   }
}
