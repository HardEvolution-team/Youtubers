package com.electrifiedded.youtubers.Entity;

import com.electrifiedded.youtubers.YTConfig;
import com.electrifiedded.youtubers.Youtubers;
import com.electrifiedded.youtubers.Items.ItemMoney;
import java.util.Random;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.world.World;

/**
 * "Random player": an NPC named after a (real) player name; renders with that player's current skin. Trades
 * generated from the name, tameable with big notes, an owner can rename it with a name tag (and so change its skin).
 */
public class PlayerEntity extends EntityYTBase implements IRangedAttackMob {
   private static final DataParameter<String> NAME = EntityDataManager.createKey(PlayerEntity.class, DataSerializers.STRING);
   public boolean RenderFailed = false;

   public PlayerEntity(World world) {
      this(world, -1);
   }

   public PlayerEntity(World world, int index) {
      this(world, index < 0 || index >= YTConfig.PlayerNames.length ? YTConfig.PlayerNames[com.electrifiedded.archaiclib.util.FastRandom.shared().nextInt(YTConfig.PlayerNames.length)] : YTConfig.PlayerNames[index]);
   }

   public PlayerEntity(World world, String name) {
      super(world);
      this.setName(name);
      this.setSize(0.6F, 1.8F);
   }

   @Override
   protected void entityInit() {
      super.entityInit();
      this.dataManager.register(NAME, "");
   }

   @Override
   public void AddTrades(MerchantRecipeList recipeList) {
      Youtubers.addPlayerTrades(recipeList, this.getName(), true);
   }

   @Override
   public String getName() {
      return this.dataManager.get(NAME);
   }

   public void setName(String name) {
      this.dataManager.set(NAME, name);
      this.setCustomNameTag(name);
   }

   @Override
   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(25.0);
      this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.5);
   }

   @Override
   public boolean processInteract(EntityPlayer player, EnumHand hand) {
      if (hand != EnumHand.MAIN_HAND) return false;
      ItemStack itemstack = player.getHeldItem(hand);
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
            // players are cheaper to tame than youtubers: 1$ = 1/2000 ... 5000$ and more always work
            int[] chances = {2000, 1000, 200, 100, 20, 10, 2, 1};
            int meta = ((ItemMoney)itemstack.getItem()).n;
            int chance = meta >= 0 && meta < chances.length ? chances[meta] : 1;
            boolean canTame = true;
            if (!player.capabilities.isCreativeMode) itemstack.shrink(1);
            if (!this.world.isRemote) {
               if (canTame && this.rand.nextInt(chance) == 0) {
                  this.setTamed(true);
                  this.setAttackTarget(null);
                  this.aiSit.setSitting(true);
                  this.setHealth(20.0F);
                  this.setOwner(player.getName());
                  this.playTameEffect(true);
               } else {
                  this.playTameEffect(false);
               }
            }
            return true;
         }
         if (itemstack.getItem() == Items.NAME_TAG) {
            if (itemstack.hasDisplayName() && player.getName().equalsIgnoreCase(this.getOwnerName()) && !this.world.isRemote) {
               this.setName(itemstack.getDisplayName());
               if (!player.capabilities.isCreativeMode) itemstack.shrink(1);
            }
            return true;
         }
      }
      return super.processInteract(player, hand);
   }

   @Override
   public void writeEntityToNBT(NBTTagCompound nbt) {
      super.writeEntityToNBT(nbt);
      nbt.setString("Name", this.getName());
   }

   @Override
   public void readEntityFromNBT(NBTTagCompound nbt) {
      super.readEntityFromNBT(nbt);
      if (!nbt.getString("Name").isEmpty()) this.setName(nbt.getString("Name"));
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
   public int getGrowingAge() {
      return 0;
   }

   @Override
   public void attackEntityWithRangedAttack(EntityLivingBase target, float power) {
      this.legacyArrow(target, power);
   }

   @Override
   public void setSwingingArms(boolean swinging) {
   }

   @Override
   protected void dropFewItems(boolean recentlyHit, int looting) {
   }

   @Override
   public boolean attackEntityAsMob(Entity target) {
      return this.legacyMelee(target, 1.0F);
   }
}
