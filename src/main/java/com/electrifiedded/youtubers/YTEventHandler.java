package com.electrifiedded.youtubers;

import com.electrifiedded.youtubers.Entity.EntityDiamondMinecart;
import com.electrifiedded.youtubers.Entity.EntityYoutuber;
import com.electrifiedded.youtubers.Entity.ExtendedPlayerProperties;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.init.Blocks;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.item.ItemExpireEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class YTEventHandler {
   @SubscribeEvent
   public void EntityConstructingEvent(EntityConstructing event) {
      if (event.getEntity() instanceof EntityPlayer) ExtendedPlayerProperties.get((EntityPlayer)event.getEntity());
   }

   @SubscribeEvent
   public void onClone(PlayerEvent.Clone event) {
      ExtendedPlayerProperties.get(event.getEntityPlayer()).copyFrom(ExtendedPlayerProperties.get(event.getOriginal()));
   }

   private static double base(EntityMob mob, net.minecraft.entity.ai.attributes.IAttribute a) {
      IAttributeInstance i = mob.getEntityAttribute(a);
      return i == null ? 0.0 : i.getBaseValue();
   }

   /** Mobs drop money scaled by their stats, difficulty and Looting; players named like a youtuber drop his items. */
   @SubscribeEvent
   public void DropItems(LivingDeathEvent event) {
      Entity e = event.getEntity();
      if (e.world.isRemote) return;
      if (e instanceof EntityMob) {
         EntityMob mob = (EntityMob)e;
         double atk = base(mob, SharedMonsterAttributes.ATTACK_DAMAGE);
         double hth = base(mob, SharedMonsterAttributes.MAX_HEALTH);
         double spd = base(mob, SharedMonsterAttributes.MOVEMENT_SPEED);
         double knb = base(mob, SharedMonsterAttributes.KNOCKBACK_RESISTANCE);
         int amount = (int)((Math.pow(atk + hth, 2.0) + (spd + knb)) / 10.0 * (YTConfig.MoneyDropMultiplier / 100.0));
         int l;
         switch (e.world.getDifficulty()) {
            case NORMAL: l = 2; break;
            case HARD: l = 4; break;
            default: l = 0;
         }
         if (event.getSource().getTrueSource() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer)event.getSource().getTrueSource();
            int lvl = EnchantmentHelper.getEnchantmentLevel(Enchantments.LOOTING, player.getHeldItemMainhand());
            if (lvl != 0) amount = (int)(amount * Math.pow(1.05F, lvl + l));
         }
         Youtubers.dropMoney(mob, amount);
      } else if (e instanceof EntityPlayer) {
         EntityPlayer ep = (EntityPlayer)e;
         int id = Youtubers.GetYoutuberIDbyName(ep.getName());
         if (id != -1) {
            EntityYoutuber ey = new EntityYoutuber(ep.world, id);
            ey.setPosition(ep.posX, ep.posY, ep.posZ);
            YTList.YoutuberList[id].dropItems(ey, 3);
         }
      }
   }

   /** Jetpack: sneak in the air to fly up while fuel lasts; trade offer refresh of the player-merchant. */
   @SubscribeEvent
   public void onLivingUpdateEvent(LivingUpdateEvent event) {
      if (!(event.getEntity() instanceof EntityPlayer)) return;
      EntityPlayer player = (EntityPlayer)event.getEntity();
      ExtendedPlayerProperties props = ExtendedPlayerProperties.get(player);
      props.Trader.updateAITick();
      if (player.onGround) props.JetPackTime = -2;
      ItemStack chest = player.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
      if (chest.isEmpty() || chest.getItem() != YTItems.JetPack) {
         props.JetPackTime = -1;
      } else if (props.JetPackTime >= 0) {
         if (player.isSneaking()) {
            player.motionY = 0.2;
            player.velocityChanged = true;
            props.JetPackTime--;
            player.fallDistance = 0.0F;
         }
      } else if (props.JetPackTime == -2 && player.isSneaking() && player.isAirBorne) {
         player.motionY = 0.2;
         player.velocityChanged = true;
         props.JetPackTime = 80;
      }
   }

   @SubscribeEvent
   public void onLivingJumpEvent(LivingJumpEvent event) {
      if (!(event.getEntity() instanceof EntityPlayer)) return;
      EntityPlayer player = (EntityPlayer)event.getEntity();
      ItemStack chest = player.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
      if (!chest.isEmpty() && chest.getItem() == YTItems.JetPack && player.isSneaking()) {
         player.motionY = 0.2;
         player.velocityChanged = true;
         ExtendedPlayerProperties.get(player).JetPackTime = 150;
      }
   }

   /** A diamond block despawning next to a minecart surrounded by 4 primed TNT turns the cart into a Diamond Minecart. */
   @SubscribeEvent
   public void itemExpireEvent(ItemExpireEvent event) {
      if (event.getEntityItem().getItem().getItem() != Item.getItemFromBlock(Blocks.DIAMOND_BLOCK)) return;
      EntityMinecart cart = null;
      int nearTNT = 0;
      for (Entity entity : event.getEntityItem().world.loadedEntityList) {
         if (entity instanceof EntityMinecart && cart == null && event.getEntityItem().getDistance(entity) < 3.0F) cart = (EntityMinecart)entity;
         if (entity instanceof EntityTNTPrimed && event.getEntityItem().getDistance(entity) < 4.0F) nearTNT++;
      }
      if (cart != null && nearTNT == 4) this.ReplacewithDiamondMinecart(cart);
   }

   private void ReplacewithDiamondMinecart(EntityMinecart entity) {
      EntityDiamondMinecart mc = new EntityDiamondMinecart(entity.world);
      mc.setLocationAndAngles(entity.posX, entity.posY, entity.posZ, entity.rotationYaw, entity.rotationPitch);
      entity.world.spawnEntity(mc);
      entity.setDead();
   }

   /** SuperGirlyGamer turns picked up leather armour into wooden armour. */
   @SubscribeEvent
   public void ItemPickUp(EntityItemPickupEvent event) {
      if (!"SuperGirlyGamer".equals(event.getEntityPlayer().getName())) return;
      ItemStack s = event.getItem().getItem();
      int damage = s.getMetadata();
      Item it = s.getItem();
      Item to = it == Items.LEATHER_HELMET ? YTItems.woodArmor.Head : it == Items.LEATHER_CHESTPLATE ? YTItems.woodArmor.Chest
         : it == Items.LEATHER_LEGGINGS ? YTItems.woodArmor.Legs : it == Items.LEATHER_BOOTS ? YTItems.woodArmor.Boots : null;
      if (to != null) event.getItem().setItem(new ItemStack(to, s.getCount(), damage));
   }
}
