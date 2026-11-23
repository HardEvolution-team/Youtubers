package com.electrifiedded.youtubers.Entity;

import com.electrifiedded.youtubers.YTItems;
import com.electrifiedded.youtubers.YTList;
import com.electrifiedded.youtubers.Items.ItemMoney;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityTippedArrow;
import net.minecraft.init.Enchantments;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.world.World;

/** A youtuber NPC; which one is a synced index into YTList.YoutuberList (+ skin subtype). */
public class EntityYoutuber extends EntityYTBase implements IRangedAttackMob {
   private static final DataParameter<Integer> YOUTUBER = EntityDataManager.createKey(EntityYoutuber.class, DataSerializers.VARINT);
   private static final DataParameter<Integer> SUBTYPE = EntityDataManager.createKey(EntityYoutuber.class, DataSerializers.VARINT);
   public boolean RenderFailed = false;
   /** AI tasks added by the current youtuber's Add_AI, replaced when the youtuber changes. */
   private final List<EntityAIBase> customTasks = new ArrayList<>();
   private int customAiFor = -1;

   public EntityYoutuber(World world) {
      this(world, -1);
   }

   public EntityYoutuber(World world, int type) {
      super(world);
      this.setYoutuber(type < 0 || type >= YTList.YoutuberList.length ? this.rand.nextInt(YTList.YoutuberList.length) : type);
      Youtuber yt = this.youtuber();
      if (yt.subTypes > 0) this.SetSubtype(this.rand.nextInt(yt.subTypes));
      this.setSize(0.6F, 1.8F);
      yt.give_Items(this);
   }

   public Youtuber youtuber() {
      int i = this.getYoutuber();
      return YTList.YoutuberList[i >= 0 && i < YTList.YoutuberList.length ? i : 0];
   }

   @Override
   protected void entityInit() {
      super.entityInit();
      this.dataManager.register(YOUTUBER, 0);
      this.dataManager.register(SUBTYPE, 0);
   }

   /**
    * The 1.7 base constructor ran Add_AI before the youtuber was chosen, so every youtuber got the AI of youtuber #0;
    * the youtuber specific AI (avoid / follow / rename other youtubers) is now applied whenever the type is set.
    */
   private void applyYoutuberAI() {
      int t = this.getYoutuber();
      if (t == this.customAiFor || this.world == null || this.world.isRemote) return;
      for (EntityAIBase task : this.customTasks) this.tasks.removeTask(task);
      this.customTasks.clear();
      Set<EntityAIBase> before = new HashSet<>();
      for (EntityAITasks.EntityAITaskEntry e : this.tasks.taskEntries) before.add(e.action);
      this.youtuber().Add_AI(this);
      for (EntityAITasks.EntityAITaskEntry e : this.tasks.taskEntries) if (!before.contains(e.action)) this.customTasks.add(e.action);
      this.customAiFor = t;
   }

   public void setYoutuber(int type) {
      this.dataManager.set(YOUTUBER, type);
      this.setCustomNameTag(this.youtuber().Name);
      this.applyYoutuberAI();
   }

   public int getYoutuber() {
      return this.dataManager.get(YOUTUBER);
   }

   public int getProfession() {
      return 9;
   }

   public int getSubtype() {
      return this.dataManager.get(SUBTYPE);
   }

   public void SetSubtype(int type) {
      this.dataManager.set(SUBTYPE, type);
   }

   @Override
   public String getName() {
      return this.youtuber().Name;
   }

   @Override
   public boolean processInteract(EntityPlayer player, EnumHand hand) {
      if (hand != EnumHand.MAIN_HAND) return false;
      ItemStack itemstack = player.getHeldItem(hand);
      if (this.youtuber().Interact(this, player, itemstack.isEmpty() ? null : itemstack)) return true;
      if (!itemstack.isEmpty()) {
         if (this.isTamed()) {
            if (itemstack.getItem() instanceof ItemFood && this.getHealth() < 20.0F) {
               if (!player.capabilities.isCreativeMode) itemstack.shrink(1);
               this.heal(((ItemFood)itemstack.getItem()).getSaturationModifier(itemstack));
               return true;
            }
            if (itemstack.getItem() instanceof ItemMoney && player.getName().equalsIgnoreCase(this.getOwnerName())) {
               if (!this.world.isRemote) {
                  this.aiSit.setSitting(!this.isSitting());
                  this.isJumping = false;
                  this.setAttackTarget(null);
               }
               return true;
            }
         } else if (itemstack.getItem() instanceof ItemMoney) {
            // taming: the bigger the note, the better the chance (1000000$ always works)
            int[] chances = {1000000, 200000, 100000, 20000, 10000, 2000, 1000, 200, 100, 20, 10, 2, 1};
            int meta = ((ItemMoney)itemstack.getItem()).n;
            int chance = meta >= 0 && meta < chances.length ? chances[meta] : 100;
            if (!player.capabilities.isCreativeMode) itemstack.shrink(1);
            if (!this.world.isRemote) {
               if (this.rand.nextInt(chance) == 0) {
                  this.setTamed(true);
                  this.setAttackTarget(null);
                  this.aiSit.setSitting(true);
                  this.setOwner(player.getName());
                  this.playTameEffect(true);
               } else {
                  this.playTameEffect(false);
               }
            }
            return true;
         }
      }
      return super.processInteract(player, hand);
   }

   @Override
   public void writeEntityToNBT(NBTTagCompound nbt) {
      super.writeEntityToNBT(nbt);
      nbt.setInteger("Youtuber", this.getYoutuber());
      nbt.setInteger("SubType", this.getSubtype());
      nbt.setString("Youtuber_name", this.getName());
   }

   @Override
   public void readEntityFromNBT(NBTTagCompound nbt) {
      super.readEntityFromNBT(nbt);
      this.checkNsetType(nbt.getInteger("Youtuber"), nbt.getInteger("SubType"), nbt.getString("Youtuber_name"));
   }

   /** The saved index may point to another youtuber when groups were switched off in the config: find him by name. */
   public void checkNsetType(int type, int sub, String name) {
      if (type >= 0 && type < YTList.YoutuberList.length && YTList.YoutuberList[type].Name.equals(name)) {
         this.setYoutuber(type);
         this.SetSubtype(sub);
         return;
      }
      for (int i = 0; i < YTList.YoutuberList.length; i++) {
         if (YTList.YoutuberList[i].Name.equals(name)) {
            this.setYoutuber(i);
            this.SetSubtype(sub);
            return;
         }
      }
   }

   @Override
   protected SoundEvent getAmbientSound() {
      return this.isTrading() ? SoundEvents.ENTITY_VILLAGER_TRADING : SoundEvents.ENTITY_VILLAGER_AMBIENT;
   }

   @Override
   protected SoundEvent getHurtSound(DamageSource src) {
      return SoundEvents.ENTITY_VILLAGER_HURT;
   }

   @Override
   protected SoundEvent getDeathSound() {
      return SoundEvents.ENTITY_VILLAGER_DEATH;
   }

   @Override
   public void AddTrades(MerchantRecipeList list) {
      this.youtuber().Add_Trades(list, this.rand, this.getSubtype());
   }

   @Override
   public void attackEntityWithRangedAttack(EntityLivingBase target, float power) {
      this.legacyArrow(target, power);
   }

   @Override
   public void setSwingingArms(boolean swinging) {
   }

   @Override
   public ItemStack getPickedResult(RayTraceResult target) {
      return new ItemStack(YTItems.Spawner, 1, 1 + this.getYoutuber());
   }

   @Override
   protected void dropFewItems(boolean recentlyHit, int looting) {
      this.youtuber().dropItems(this, looting);
   }

   @Override
   public boolean attackEntityAsMob(Entity target) {
      return this.legacyMelee(target, 2.0F);
   }
}
