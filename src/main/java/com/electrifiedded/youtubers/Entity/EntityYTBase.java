package com.electrifiedded.youtubers.Entity;

import com.electrifiedded.archaiclib.legacy.entity.LegacyEquipment;
import com.electrifiedded.youtubers.Youtubers;
import com.electrifiedded.youtubers.Entity.AI.EntityAIFollowOwnerYT;
import com.electrifiedded.youtubers.Entity.AI.EntityAILookAtTradePlayerYT;
import com.electrifiedded.youtubers.Entity.AI.EntityAIOwnerHurtByTargetYT;
import com.electrifiedded.youtubers.Entity.AI.EntityAIOwnerHurtTargetYT;
import com.electrifiedded.youtubers.Entity.AI.EntityAISitYT;
import com.electrifiedded.youtubers.Entity.AI.EntityAITradePlayerYT;
import com.electrifiedded.youtubers.Entity.AI.EntityAItorch;
import com.electrifiedded.youtubers.GUI.InventoryMerchantYT;
import com.electrifiedded.youtubers.GUI.YTGuiHandler;
import com.electrifiedded.youtubers.Items.Spawners.ItemMobSpawner;
import com.electrifiedded.youtubers.Items.Spawners.ItemSpawner;
import com.electrifiedded.youtubers.Items.Spawners.PlayerSpawner;
import com.electrifiedded.youtubers.Items.Spawners.YoutuberSpawner;
import javax.annotation.Nullable;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityTippedArrow;
import net.minecraft.init.Enchantments;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumAction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IMerchant;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIOpenDoor;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.EntityAIWatchClosest2;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Base of all trading/tameable NPCs: a villager-like merchant with its own inventory (held item, backpack, armour),
 * that can be tamed (sit / follow / defend the owner), grows up from a baby and regenerates slowly.
 */
public class EntityYTBase extends EntityCreature implements IMerchant {
   private static final DataParameter<Integer> AGE = EntityDataManager.createKey(EntityYTBase.class, DataSerializers.VARINT);
   private static final DataParameter<Byte> FLAGS = EntityDataManager.createKey(EntityYTBase.class, DataSerializers.BYTE);
   private static final DataParameter<String> OWNER = EntityDataManager.createKey(EntityYTBase.class, DataSerializers.STRING);
   protected int regentimer = 0;
   private float ageWidth = -1.0F;
   private float ageHeight;
   protected EntityAISitYT aiSit = new EntityAISitYT(this);
   public InventoryMerchantYT inventory;
   protected EntityPlayer buyingPlayer;
   private MerchantRecipeList buyingList;
   private int timeUntilReset;
   private boolean needsInitilization;
   private int wealth;
   private boolean isPlaying;

   public EntityYTBase(World world) {
      super(world);
      this.inventory = new InventoryMerchantYT(this);
      if (this.getNavigator() instanceof PathNavigateGround) {
         ((PathNavigateGround)this.getNavigator()).setBreakDoors(true);
         ((PathNavigateGround)this.getNavigator()).setEnterDoors(true);
      }
      this.tasks.addTask(2, new EntityAIAttackMelee(this, 0.8, false));
      this.tasks.addTask(0, new EntityAISwimming(this));
      this.AddAI();
      this.tasks.addTask(2, new EntityAItorch(this));
      this.tasks.addTask(2, this.aiSit);
      this.tasks.addTask(1, new EntityAITradePlayerYT(this));
      this.tasks.addTask(1, new EntityAILookAtTradePlayerYT(this));
      this.tasks.addTask(5, new EntityAIFollowOwnerYT(this, 1.0, 10.0F, 2.0F));
      this.tasks.addTask(4, new EntityAIOpenDoor(this, true));
      this.tasks.addTask(9, new EntityAIWatchClosest2(this, EntityPlayer.class, 3.0F, 1.0F));
      this.tasks.addTask(9, new EntityAIWatchClosest2(this, EntityYoutuber.class, 5.0F, 0.02F));
      this.tasks.addTask(9, new EntityAIWander(this, 0.5));
      this.tasks.addTask(10, new EntityAIWatchClosest(this, EntityLiving.class, 8.0F));
      this.targetTasks.addTask(1, new EntityAIOwnerHurtByTargetYT(this));
      this.targetTasks.addTask(2, new EntityAIOwnerHurtTargetYT(this));
      this.targetTasks.addTask(3, new EntityAIHurtByTarget(this, true));
      this.targetTasks.addTask(4, new EntityAINearestAttackableTarget<>(this, EntityMob.class, 5, true, false, null));
   }

   @Override
   protected void entityInit() {
      super.entityInit();
      this.dataManager.register(AGE, 0);
      this.dataManager.register(FLAGS, (byte)0);
      this.dataManager.register(OWNER, "");
   }

   @Override
   public void onDeath(DamageSource ds) {
      super.onDeath(ds);
      if (this.inventory != null && !this.world.isRemote) {
         // the equipment slots mirror the inventory: drop the inventory once and clear the equipment
         for (int i = 0; i < this.inventory.getSizeInventory(); i++) {
            ItemStack s = this.inventory.getStackInSlot(i);
            if (!s.isEmpty()) this.entityDropItem(s, 0.0F);
         }
         this.inventory.clear();
         for (net.minecraft.inventory.EntityEquipmentSlot slot : net.minecraft.inventory.EntityEquipmentSlot.values()) this.setItemStackToSlot(slot, ItemStack.EMPTY);
      }
   }

   /** Extra AI of subclasses, called from the constructor. */
   public void AddAI() {
   }

   @Override
   protected boolean canDespawn() {
      return false;
   }

   @Override
   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(50.0);
      this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.5);
      this.getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(2.0);
   }

   @Override
   public boolean getAlwaysRenderNameTagForRender() {
      return true;
   }

   // ---- 1.7 equipment index helpers (0 = held, 1 boots .. 4 helmet); they go through the youtuber's inventory ----

   public ItemStack getEquipmentInSlot(int legacy) {
      return LegacyEquipment.get(this, legacy);
   }

   public void setCurrentItemOrArmor(int legacy, @Nullable ItemStack stack) {
      ItemStack s = stack == null ? ItemStack.EMPTY : stack;
      int idx = legacy == 0 ? InventoryMerchantYT.HELD : InventoryMerchantYT.ARMOR_START + legacy - 1;
      if (this.inventory != null) this.inventory.setInventorySlotContents(idx, s);
      LegacyEquipment.set(this, legacy, s);
   }

   public ItemStack getHeldItem() {
      return this.getHeldItemMainhand();
   }

   @Override
   public void writeEntityToNBT(NBTTagCompound nbt) {
      super.writeEntityToNBT(nbt);
      nbt.setInteger("Riches", this.wealth);
      if (this.buyingList != null) nbt.setTag("Offers", this.buyingList.getRecipiesAsTags());
      nbt.setString("Owner", this.getOwnerName());
      nbt.setBoolean("Sitting", this.isSitting());
      nbt.setInteger("Age", this.getGrowingAge());
      nbt.setTag("YTItems", this.inventory.writeToNBT(new NBTTagList()));
   }

   @Override
   public void readEntityFromNBT(NBTTagCompound nbt) {
      super.readEntityFromNBT(nbt);
      this.wealth = nbt.getInteger("Riches");
      if (nbt.hasKey("Offers", 10)) this.buyingList = new MerchantRecipeList(nbt.getCompoundTag("Offers"));
      String s = nbt.getString("Owner");
      if (!s.isEmpty()) {
         this.setOwner(s);
         this.setTamed(true);
      }
      this.aiSit.setSitting(nbt.getBoolean("Sitting"));
      this.setSitting(nbt.getBoolean("Sitting"));
      this.setGrowingAge(nbt.getInteger("Age"));
      if (nbt.hasKey("YTItems", 9)) {
         this.inventory.readFromNBT(nbt.getTagList("YTItems", 10));
         this.inventory.syncAll();
      } else {
         // entity saved before it had an inventory tag: take over the equipment it was saved with
         for (int l = 0; l <= 4; l++) {
            ItemStack e = LegacyEquipment.get(this, l);
            if (!e.isEmpty()) this.inventory.setInventorySlotContents(l == 0 ? InventoryMerchantYT.HELD : InventoryMerchantYT.ARMOR_START + l - 1, e.copy());
         }
      }
      this.SetArmor();
   }

   public void SetArmor() {
   }

   @Override
   public void onLivingUpdate() {
      super.onLivingUpdate();
      if (++this.regentimer == 100) {
         this.heal(this.isTamed() ? 2.0F : 1.0F);
         this.regentimer = 0;
      }
      if (this.world.isRemote) {
         this.setScaleForAge(this.isChild());
      } else {
         int i = this.getGrowingAge();
         if (i < 0) this.setGrowingAge(++i);
         else if (i > 0) this.setGrowingAge(--i);
      }
   }

   @Override
   protected void updateAITasks() {
      EntityLivingBase owner = this.getOwner();
      if (owner != null && this.getAttackTarget() == owner) this.setAttackTarget(null);
      if (this.getAttackTarget() instanceof EntityYTBase && owner != null && ((EntityYTBase)this.getAttackTarget()).getOwner() == owner) this.setAttackTarget(null);
      if (!this.isTrading() && this.timeUntilReset > 0) {
         this.timeUntilReset--;
         if (this.timeUntilReset <= 0) {
            if (this.needsInitilization) {
               this.populateBuyingList();
               this.needsInitilization = false;
            }
            this.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 200, 0));
         }
      }
      super.updateAITasks();
   }

   // ---- IMerchant --------------------------------------------------------------------------------------------------

   @Override
   public void useRecipe(MerchantRecipe recipe) {
      recipe.incrementToolUses();
      this.livingSoundTime = -this.getTalkInterval();
      this.playSound(SoundEvents.ENTITY_VILLAGER_YES, this.getSoundVolume(), this.getSoundPitch());
      if (recipe.getItemToBuy().getItem() == net.minecraft.init.Items.EMERALD) this.wealth += recipe.getItemToBuy().getCount();
   }

   /** Client: offers sent by the server when the trade GUI opens. */
   @Override
   public void setRecipes(@Nullable MerchantRecipeList recipeList) {
      if (this.world.isRemote) this.buyingList = recipeList;
   }

   @Nullable
   @Override
   public MerchantRecipeList getRecipes(EntityPlayer player) {
      if (this.world.isRemote) return this.buyingList == null ? new MerchantRecipeList() : this.buyingList;
      if (this.buyingList == null) this.populateBuyingList();
      return this.buyingList;
   }

   private void populateBuyingList() {
      if (this.buyingList == null) this.buyingList = new MerchantRecipeList();
      this.AddTrades(this.buyingList);
   }

   public void AddTrades(MerchantRecipeList list) {
   }

   public boolean isTrading() {
      return this.buyingPlayer != null;
   }

   @Override
   public void setCustomer(@Nullable EntityPlayer player) {
      this.buyingPlayer = player;
   }

   @Nullable
   @Override
   public EntityPlayer getCustomer() {
      return this.buyingPlayer;
   }

   @Override
   public void verifySellingItem(ItemStack stack) {
      if (!this.world.isRemote && this.livingSoundTime > -this.getTalkInterval() + 20) {
         this.livingSoundTime = -this.getTalkInterval();
         this.playSound(stack.isEmpty() ? SoundEvents.ENTITY_VILLAGER_NO : SoundEvents.ENTITY_VILLAGER_YES, this.getSoundVolume(), this.getSoundPitch());
      }
   }

   @Override
   public World getWorld() {
      return this.world;
   }

   @Override
   public BlockPos getPos() {
      return new BlockPos(this);
   }

   @Override
   public ITextComponent getDisplayName() {
      return super.getDisplayName();
   }

   // ---- taming -----------------------------------------------------------------------------------------------------

   /** Server side: hearts (true) or smoke (false) shown on clients through entity status 7 / 6. */
   protected void playTameEffect(boolean success) {
      this.world.setEntityState(this, (byte)(success ? 7 : 6));
   }

   @Override
   @SideOnly(Side.CLIENT)
   public void handleStatusUpdate(byte id) {
      if (id == 7 || id == 6) {
         EnumParticleTypes p = id == 7 ? EnumParticleTypes.HEART : EnumParticleTypes.SMOKE_NORMAL;
         for (int i = 0; i < 7; i++) {
            this.world.spawnParticle(p, this.posX + this.rand.nextFloat() * this.width * 2.0F - this.width, this.posY + 0.5 + this.rand.nextFloat() * this.height,
               this.posZ + this.rand.nextFloat() * this.width * 2.0F - this.width, this.rand.nextGaussian() * 0.02, this.rand.nextGaussian() * 0.02,
               this.rand.nextGaussian() * 0.02);
         }
      } else {
         super.handleStatusUpdate(id);
      }
   }

   public boolean isTamed() {
      return (this.dataManager.get(FLAGS) & 4) != 0;
   }

   public void setTamed(boolean tamed) {
      byte b = this.dataManager.get(FLAGS);
      this.dataManager.set(FLAGS, (byte)(tamed ? b | 4 : b & ~4));
   }

   public boolean isSitting() {
      return (this.dataManager.get(FLAGS) & 1) != 0;
   }

   public void setSitting(boolean sitting) {
      byte b = this.dataManager.get(FLAGS);
      this.dataManager.set(FLAGS, (byte)(sitting ? b | 1 : b & ~1));
   }

   public String getOwnerName() {
      return this.dataManager.get(OWNER);
   }

   public void setOwner(String name) {
      this.dataManager.set(OWNER, name == null ? "" : name);
   }

   @Nullable
   public EntityLivingBase getOwner() {
      String n = this.getOwnerName();
      return n.isEmpty() ? null : this.world.getPlayerEntityByName(n);
   }

   public void setPlaying(boolean playing) {
      this.isPlaying = playing;
   }

   public boolean isPlaying() {
      return this.isPlaying;
   }

   // ---- age --------------------------------------------------------------------------------------------------------

   public int getGrowingAge() {
      return this.dataManager.get(AGE);
   }

   public void addGrowth(int growth) {
      int j = this.getGrowingAge() + growth * 20;
      this.setGrowingAge(Math.min(j, 0));
   }

   public void setGrowingAge(int age) {
      this.dataManager.set(AGE, age);
      this.setScaleForAge(this.isChild());
   }

   @Override
   public boolean isChild() {
      return this.getGrowingAge() < 0;
   }

   public void setScaleForAge(boolean child) {
      this.setScale(child ? 0.5F : 1.0F);
   }

   protected final void setScale(float scale) {
      super.setSize(this.ageWidth * scale, this.ageHeight * scale);
   }

   @Override
   protected final void setSize(float width, float height) {
      boolean set = this.ageWidth > 0.0F;
      this.ageWidth = width;
      this.ageHeight = height;
      if (!set) this.setScale(1.0F);
   }

   // ---- interaction ------------------------------------------------------------------------------------------------

   @Override
   public boolean processInteract(EntityPlayer player, EnumHand hand) {
      if (hand != EnumHand.MAIN_HAND) return false;
      ItemStack itemstack = player.getHeldItem(hand);
      if (!itemstack.isEmpty() && this.isSpawnerItem(itemstack)) {
         if (!this.world.isRemote) {
            EntityYTBase child = this.createChild(itemstack);
            if (child != null) {
               child.setGrowingAge(-24000);
               child.setLocationAndAngles(this.posX, this.posY, this.posZ, 0.0F, 0.0F);
               this.world.spawnEntity(child);
               if (itemstack.hasDisplayName()) child.setCustomNameTag(itemstack.getDisplayName());
               if (!player.capabilities.isCreativeMode) itemstack.shrink(1);
               return true;
            }
         }
         return false;
      }
      if (this.isEntityAlive() && !this.isTrading() && !this.isChild() && !player.isSneaking()) {
         if (this.buyingList == null || !this.buyingList.isEmpty() || this.world.isRemote) {
            if (!this.world.isRemote) YTGuiHandler.openYoutuber(player, this);
         }
         return true;
      }
      return super.processInteract(player, hand);
   }

   private boolean isSpawnerItem(ItemStack s) {
      return s.getItem() instanceof YoutuberSpawner || s.getItem() instanceof PlayerSpawner || s.getItem() instanceof ItemMobSpawner
         || s.getItem() instanceof ItemSpawner;
   }

   /** Spawn eggs used on a NPC make a baby of the egg's NPC type (only NPC types, other mobs are ignored). */
   @Nullable
   private EntityYTBase createChild(ItemStack s) {
      Entity entity = null;
      if (s.getItem() instanceof YoutuberSpawner) entity = s.getMetadata() == 0 ? new EntityYoutuber(this.world) : new EntityYoutuber(this.world, s.getMetadata() - 1);
      else if (s.getItem() instanceof PlayerSpawner) entity = new PlayerEntity(this.world);
      else if (s.getItem() instanceof ItemMobSpawner) {
         int id = ItemMobSpawner.getEntityID(s.getMetadata());
         if (id != -1) entity = Youtubers.MobSpawns[id].Spawn(this.world);
      } else if (s.getItem() instanceof ItemSpawner) {
         entity = ((ItemSpawner)s.getItem()).getEntity(this.world);
      }
      return entity instanceof EntityYTBase ? (EntityYTBase)entity : null;
   }

   /** 1.7 skeleton-style arrow shot, only when holding a bow. */
   public void legacyArrow(EntityLivingBase target, float power) {
      ItemStack held = this.getHeldItemMainhand();
      if (held.isEmpty() || held.getItemUseAction() != EnumAction.BOW) return;
      EntityArrow arrow = new EntityTippedArrow(this.world, this);
      double dx = target.posX - this.posX;
      double dy = target.getEntityBoundingBox().minY + target.height / 3.0F - arrow.posY;
      double dz = target.posZ - this.posZ;
      double d = MathHelper.sqrt(dx * dx + dz * dz);
      arrow.shoot(dx, dy + d * 0.2, dz, 1.6F, 14 - this.world.getDifficulty().getId() * 4);
      int power1 = EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER, held);
      int punch = EnchantmentHelper.getEnchantmentLevel(Enchantments.PUNCH, held);
      arrow.setDamage(power * 2.0F + this.rand.nextGaussian() * 0.25 + this.world.getDifficulty().getId() * 0.11F);
      if (power1 > 0) arrow.setDamage(arrow.getDamage() + power1 * 0.5 + 0.5);
      if (punch > 0) arrow.setKnockbackStrength(punch);
      if (EnchantmentHelper.getEnchantmentLevel(Enchantments.FLAME, held) > 0) arrow.setFire(100);
      this.playSound(SoundEvents.ENTITY_SKELETON_SHOOT, 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
      this.world.spawnEntity(arrow);
   }

   /** 1.7 melee: base + the held item's attack damage, enchantment damage, knockback, fire aspect, thorns. */
   public boolean legacyMelee(Entity target, float base) {
      float f = base;
      ItemStack held = this.getHeldItemMainhand();
      if (!held.isEmpty()) {
         for (AttributeModifier m : held.getAttributeModifiers(EntityEquipmentSlot.MAINHAND).get(SharedMonsterAttributes.ATTACK_DAMAGE.getName())) {
            f += (float)m.getAmount();
         }
      }
      int knock = 0;
      if (target instanceof EntityLivingBase) {
         f += EnchantmentHelper.getModifierForCreature(held, ((EntityLivingBase)target).getCreatureAttribute());
         knock += EnchantmentHelper.getKnockbackModifier(this);
      }
      boolean hit = target.attackEntityFrom(DamageSource.causeMobDamage(this), f);
      if (hit) {
         if (knock > 0) {
            target.addVelocity(-MathHelper.sin(this.rotationYaw * (float)Math.PI / 180.0F) * knock * 0.5F, 0.1,
               MathHelper.cos(this.rotationYaw * (float)Math.PI / 180.0F) * knock * 0.5F);
            this.motionX *= 0.6;
            this.motionZ *= 0.6;
         }
         int fire = EnchantmentHelper.getFireAspectModifier(this);
         if (fire > 0) target.setFire(fire * 4);
         if (target instanceof EntityLivingBase) EnchantmentHelper.applyThornEnchantments((EntityLivingBase)target, this);
         EnchantmentHelper.applyArthropodEnchantments(this, target);
      }
      return hit;
   }
}
