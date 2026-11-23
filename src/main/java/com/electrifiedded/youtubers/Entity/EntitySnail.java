package com.electrifiedded.youtubers.Entity;

import com.electrifiedded.archaiclib.legacy.sound.LegacyModSounds;
import com.electrifiedded.youtubers.Youtubers;
import javax.annotation.Nullable;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIFollowParent;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

/**
 * Yodelling snail of the swamps: shell can be dyed (colour = wool colour index), breeds with tall flowers (double
 * plants), drops stained clay of its colour. Babies mix the parents' colours like sheep.
 */
public class EntitySnail extends EntityAnimal {
   private static final DataParameter<Byte> COLOR = EntityDataManager.createKey(EntitySnail.class, DataSerializers.BYTE);
   /** Shell tint per wool colour (the sheep fleece table). */
   public static final float[][] fleeceColorTable = new float[][]{
      {1.0F, 1.0F, 1.0F}, {0.85F, 0.5F, 0.2F}, {0.7F, 0.3F, 0.85F}, {0.4F, 0.6F, 0.85F}, {0.9F, 0.9F, 0.2F}, {0.5F, 0.8F, 0.1F},
      {0.95F, 0.5F, 0.65F}, {0.3F, 0.3F, 0.3F}, {0.6F, 0.6F, 0.6F}, {0.3F, 0.5F, 0.6F}, {0.5F, 0.25F, 0.7F}, {0.2F, 0.3F, 0.7F},
      {0.4F, 0.3F, 0.2F}, {0.4F, 0.5F, 0.2F}, {0.6F, 0.2F, 0.2F}, {0.1F, 0.1F, 0.1F}
   };

   public EntitySnail(World world) {
      super(world);
      this.setSize(0.9F, 1.3F);
      this.setFleeceColor(EnumDyeColor.byMetadata(this.rand.nextInt(16)));
   }

   @Override
   protected void initEntityAI() {
      this.tasks.addTask(0, new EntityAISwimming(this));
      this.tasks.addTask(1, new EntityAIPanic(this, 1.25));
      this.tasks.addTask(2, new EntityAIMate(this, 1.0));
      this.tasks.addTask(3, new EntityAITempt(this, 1.1, Item.getItemFromBlock(Blocks.DOUBLE_PLANT), false));
      this.tasks.addTask(4, new EntityAIFollowParent(this, 1.1));
      this.tasks.addTask(6, new EntityAIWander(this, 1.0));
      this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
      this.tasks.addTask(8, new EntityAILookIdle(this));
   }

   @Override
   public int getTalkInterval() {
      return 400 + this.rand.nextInt(400);
   }

   @Override
   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(8.0);
      this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.23F);
   }

   @Override
   protected void entityInit() {
      super.entityInit();
      this.dataManager.register(COLOR, (byte)0);
   }

   @Override
   protected void dropFewItems(boolean recentlyHit, int looting) {
      int a = this.rand.nextInt(4) + 3;
      for (int b = 0; b < looting; b++) a += this.rand.nextInt(3);
      for (int b = 0; b <= a; b++) this.entityDropItem(new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, this.getFleeceColor()), 0.0F);
   }

   @Override
   protected net.minecraft.util.ResourceLocation getLootTable() {
      return null;
   }

   @Override
   public boolean processInteract(EntityPlayer player, EnumHand hand) {
      ItemStack itemstack = player.getHeldItem(hand);
      if (!itemstack.isEmpty() && itemstack.getItem() == Items.DYE) {
         EnumDyeColor c = EnumDyeColor.byDyeDamage(itemstack.getMetadata());
         if (this.getFleeceColor() != c.getMetadata()) {
            this.setFleeceColor(c);
            if (!player.capabilities.isCreativeMode) itemstack.shrink(1);
            return true;
         }
      }
      return super.processInteract(player, hand);
   }

   @Override
   public void writeEntityToNBT(NBTTagCompound nbt) {
      super.writeEntityToNBT(nbt);
      nbt.setByte("Color", (byte)this.getFleeceColor());
   }

   @Override
   public void readEntityFromNBT(NBTTagCompound nbt) {
      super.readEntityFromNBT(nbt);
      this.setFleeceColor(EnumDyeColor.byMetadata(nbt.getByte("Color")));
   }

   @Override
   protected SoundEvent getAmbientSound() {
      return LegacyModSounds.get(Youtubers.MODID, this.rand.nextInt(10) == 0 ? "YodelLong" : "YodelShort");
   }

   @Override
   protected SoundEvent getHurtSound(DamageSource src) {
      return SoundEvents.ENTITY_ZOMBIE_ATTACK_DOOR_WOOD;
   }

   @Override
   protected SoundEvent getDeathSound() {
      return SoundEvents.ENTITY_ZOMBIE_BREAK_DOOR_WOOD;
   }

   @Override
   protected void playStepSound(BlockPos pos, Block block) {
      this.playSound(SoundEvents.ENTITY_SHEEP_STEP, 0.15F, 1.0F);
   }

   /** Wool colour index 0..15. */
   public int getFleeceColor() {
      return this.dataManager.get(COLOR) & 15;
   }

   public void setFleeceColor(EnumDyeColor color) {
      this.dataManager.set(COLOR, (byte)(color.getMetadata() & 15));
   }

   @Override
   public boolean isBreedingItem(ItemStack stack) {
      return !stack.isEmpty() && stack.getItem() == Item.getItemFromBlock(Blocks.DOUBLE_PLANT);
   }

   @Override
   public EntityAgeable createChild(EntityAgeable mate) {
      EntitySnail child = new EntitySnail(this.world);
      child.setFleeceColor(this.mixColor(this, (EntitySnail)mate));
      return child;
   }

   /** Sheep rule: if the two parents' dyes craft into a dye, the baby has that colour, else one of the parents' colours. */
   private EnumDyeColor mixColor(EntitySnail a, EntitySnail b) {
      int da = EnumDyeColor.byMetadata(a.getFleeceColor()).getDyeDamage();
      int db = EnumDyeColor.byMetadata(b.getFleeceColor()).getDyeDamage();
      InventoryCrafting grid = new InventoryCrafting(new Container() {
         @Override
         public boolean canInteractWith(EntityPlayer p) {
            return false;
         }
      }, 2, 1);
      grid.setInventorySlotContents(0, new ItemStack(Items.DYE, 1, da));
      grid.setInventorySlotContents(1, new ItemStack(Items.DYE, 1, db));
      ItemStack out = CraftingManager.findMatchingResult(grid, this.world);
      int dye = !out.isEmpty() && out.getItem() == Items.DYE ? out.getMetadata() : (this.world.rand.nextBoolean() ? da : db);
      return EnumDyeColor.byDyeDamage(dye);
   }

   @Nullable
   @Override
   public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData data) {
      data = super.onInitialSpawn(difficulty, data);
      this.setFleeceColor(EnumDyeColor.byMetadata(this.world.rand.nextInt(16)));
      return data;
   }
}
