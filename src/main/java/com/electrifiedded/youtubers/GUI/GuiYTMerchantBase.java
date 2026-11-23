package com.electrifiedded.youtubers.GUI;

import io.netty.buffer.Unpooled;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IMerchant;
import net.minecraft.inventory.ContainerMerchant;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.client.CPacketCustomPayload;
import net.minecraft.util.ResourceLocation;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Shared part of the two trade GUIs of the mod: the trader is drawn on the left, the selected offer is previewed at the
 * top right with arrow buttons (texture u 176+ like the vanilla merchant GUI), trade slots below it.
 */
@SideOnly(Side.CLIENT)
public abstract class GuiYTMerchantBase extends GuiContainer {
   protected final ResourceLocation texture;
   protected final IMerchant merchant;
   private MerchantButton nextButton;
   private MerchantButton previousButton;
   private int selectedMerchantRecipe;
   private float oldMouseX;
   private float oldMouseY;

   protected GuiYTMerchantBase(ContainerMerchant container, IMerchant merchant, ResourceLocation texture) {
      super(container);
      this.merchant = merchant;
      this.texture = texture;
      this.xSize = 176;
      this.ySize = 166;
   }

   /** The entity drawn in the left frame. */
   protected abstract EntityLivingBase displayedEntity();

   protected MerchantRecipeList recipes() {
      return this.merchant.getRecipes(this.mc.player);
   }

   @Override
   public void initGui() {
      super.initGui();
      int i = (this.width - this.xSize) / 2;
      int j = (this.height - this.ySize) / 2;
      this.buttonList.add(this.nextButton = new MerchantButton(this.texture, 1, i + 161, j + 10, true));
      this.buttonList.add(this.previousButton = new MerchantButton(this.texture, 2, i + 89, j + 10, false));
      this.nextButton.enabled = false;
      this.previousButton.enabled = false;
   }

   @Override
   protected void drawGuiContainerBackgroundLayer(float partialTicks, int x, int y) {
      this.mc.getTextureManager().bindTexture(this.texture);
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
      int k = this.guiLeft;
      int l = this.guiTop;
      EntityLivingBase ent = this.displayedEntity();
      if (ent != null) GuiInventory.drawEntityOnScreen(k + 51 + 8, l + 75 - 16, 25, k + 51 - this.oldMouseX, l + 75 - 50 - this.oldMouseY, ent);
      MerchantRecipeList list = this.recipes();
      if (list != null && !list.isEmpty() && this.selectedMerchantRecipe < list.size()) {
         MerchantRecipe r = list.get(this.selectedMerchantRecipe);
         if (r.isRecipeDisabled()) {
            this.mc.getTextureManager().bindTexture(this.texture);
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            GlStateManager.disableLighting();
            this.drawTexturedModalRect(this.guiLeft + 83, this.guiTop + 21, 212, 0, 28, 21);
            this.drawTexturedModalRect(this.guiLeft + 83, this.guiTop + 51, 212, 0, 28, 21);
         }
      }
   }

   @Override
   public void updateScreen() {
      super.updateScreen();
      MerchantRecipeList list = this.recipes();
      if (list != null) {
         if (this.selectedMerchantRecipe >= list.size()) this.selectedMerchantRecipe = Math.max(0, list.size() - 1);
         this.nextButton.enabled = this.selectedMerchantRecipe < list.size() - 1;
         this.previousButton.enabled = this.selectedMerchantRecipe > 0;
      }
   }

   @Override
   protected void actionPerformed(GuiButton button) {
      boolean changed = false;
      MerchantRecipeList list = this.recipes();
      if (button == this.nextButton) {
         this.selectedMerchantRecipe++;
         if (list != null && this.selectedMerchantRecipe >= list.size()) this.selectedMerchantRecipe = list.size() - 1;
         changed = true;
      } else if (button == this.previousButton) {
         this.selectedMerchantRecipe--;
         if (this.selectedMerchantRecipe < 0) this.selectedMerchantRecipe = 0;
         changed = true;
      }
      if (changed) {
         ((ContainerMerchant)this.inventorySlots).setCurrentRecipeIndex(this.selectedMerchantRecipe);
         PacketBuffer buf = new PacketBuffer(Unpooled.buffer());
         buf.writeInt(this.selectedMerchantRecipe);
         this.mc.getConnection().sendPacket(new CPacketCustomPayload("MC|TrSel", buf));
      }
   }

   @Override
   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
      this.drawDefaultBackground();
      super.drawScreen(mouseX, mouseY, partialTicks);
      this.oldMouseX = mouseX;
      this.oldMouseY = mouseY;
      MerchantRecipeList list = this.recipes();
      if (list != null && !list.isEmpty() && this.selectedMerchantRecipe < list.size()) {
         int k = this.guiLeft;
         int l = this.guiTop;
         MerchantRecipe r = list.get(this.selectedMerchantRecipe);
         ItemStack buy1 = r.getItemToBuy();
         ItemStack buy2 = r.getSecondItemToBuy();
         ItemStack sell = r.getItemToSell();
         GlStateManager.pushMatrix();
         RenderHelper.enableGUIStandardItemLighting();
         GlStateManager.disableLighting();
         GlStateManager.enableRescaleNormal();
         GlStateManager.enableColorMaterial();
         GlStateManager.enableLighting();
         this.itemRender.zLevel = 100.0F;
         this.itemRender.renderItemAndEffectIntoGUI(buy1, k + 101, l + 10);
         this.itemRender.renderItemOverlays(this.fontRenderer, buy1, k + 101, l + 10);
         if (!buy2.isEmpty()) {
            this.itemRender.renderItemAndEffectIntoGUI(buy2, k + 119, l + 10);
            this.itemRender.renderItemOverlays(this.fontRenderer, buy2, k + 119, l + 10);
         }
         this.itemRender.renderItemAndEffectIntoGUI(sell, k + 143, l + 10);
         this.itemRender.renderItemOverlays(this.fontRenderer, sell, k + 143, l + 10);
         this.itemRender.zLevel = 0.0F;
         GlStateManager.disableLighting();
         if (this.isPointInRegion(101, 10, 16, 16, mouseX, mouseY) && !buy1.isEmpty()) {
            this.renderToolTip(buy1, mouseX, mouseY);
         } else if (!buy2.isEmpty() && this.isPointInRegion(119, 10, 16, 16, mouseX, mouseY)) {
            this.renderToolTip(buy2, mouseX, mouseY);
         } else if (!sell.isEmpty() && this.isPointInRegion(143, 10, 16, 16, mouseX, mouseY)) {
            this.renderToolTip(sell, mouseX, mouseY);
         } else if (r.isRecipeDisabled() && (this.isPointInRegion(83, 21, 28, 21, mouseX, mouseY) || this.isPointInRegion(83, 51, 28, 21, mouseX, mouseY))) {
            this.drawHoveringText(I18n.format("merchant.deprecated"), mouseX, mouseY);
         }
         GlStateManager.popMatrix();
         GlStateManager.enableLighting();
         GlStateManager.enableDepth();
         RenderHelper.enableStandardItemLighting();
      }
      this.renderHoveredToolTip(mouseX, mouseY);
   }

   @SideOnly(Side.CLIENT)
   static class MerchantButton extends GuiButton {
      private final boolean forward;
      private final ResourceLocation texture;

      MerchantButton(ResourceLocation texture, int buttonID, int x, int y, boolean forward) {
         super(buttonID, x, y, 12, 19, "");
         this.forward = forward;
         this.texture = texture;
      }

      @Override
      public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
         if (!this.visible) return;
         mc.getTextureManager().bindTexture(this.texture);
         GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
         boolean hover = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
         int v = this.forward ? 0 : this.height;
         int u = 176;
         if (!this.enabled) u += this.width * 2;
         else if (hover) u += this.width;
         this.drawTexturedModalRect(this.x, this.y, u, v, this.width, this.height);
      }
   }
}
