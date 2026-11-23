package com.electrifiedded.youtubers.Entity.cLUCKY;

import com.electrifiedded.youtubers.YTConfig;
import com.electrifiedded.youtubers.YTItems;
import net.minecraft.util.math.BlockPos;
import com.electrifiedded.youtubers.RandUtil.RandomString;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.WeightedRandom;
import net.minecraft.util.WeightedRandom.Item;
import net.minecraft.world.World;

public class EntitycLuckey extends EntityChicken {
   public Random rand = new com.electrifiedded.archaiclib.util.FastRandom();
   public int level;
   public int luck;
   public String customDrop;
   public static List<RandomDrop> DropList = new ArrayList<>();

   public static void doRDrop(int level, int luck, CluckyDrop Dropper) {
      List<RandomString> WeightedDropList = new ArrayList<>();

      for (int a = 0; a < DropList.size(); a++) {
         RandomDrop drop = DropList.get(a);
         double n = Math.pow(drop.luck - luck, 2.0) / 25.0;
         double i = Math.pow(drop.level - level, 2.0) / 13.5;
         double q = Math.pow(n + i, 0.5);
         q = Math.exp(-q) * 100.0;
         q = Math.floor(q);
         int weight = (int)q;
         if (weight != 0) {
            WeightedDropList.add(new RandomString(drop.drop, weight));
         }
      }

      Random rand = new com.electrifiedded.archaiclib.util.FastRandom();
      int ax = 1;
      if (WeightedRandom.getTotalWeight(WeightedDropList) > 0) {
         ax = WeightedRandom.getTotalWeight(WeightedDropList);
      }

      if (WeightedDropList.isEmpty()) return;
      RandomString RText = WeightedRandom.getRandomItem(rand, WeightedDropList);
      Dropper.parseDrop(RText.text);
   }

   public EntitycLuckey(World arg0) {
      this(arg0, -1, -1);
   }

   public EntitycLuckey(World arg0, String str) {
      super(arg0);
      this.customDrop = str;
      this.setLevel(-1);
      this.setLuck(-1);
   }

   public EntitycLuckey(World arg0, int level, int luck) {
      super(arg0);
      this.customDrop = "";
      if (level == -1) {
         this.level = 0;
         if (this.rand.nextInt(4) == 0 || YTConfig.cLuckLimit > 1) {
            this.level = 1;
            if (this.rand.nextInt(4) == 0 || YTConfig.cLuckLimit > 2) {
               this.level = 2;
               if (this.rand.nextInt(4) == 0 || YTConfig.cLuckLimit > 3) {
                  this.level = 3;
                  if (this.rand.nextInt(4) == 0 || YTConfig.cLuckLimit > 4) {
                     this.level = 4;
                  }
               }
            }
         }
      } else {
         this.level = level;
      }

      if (luck == -1) {
         this.luck = 2;
         int luckoffset = 0;
         if (this.rand.nextInt(3) > 0) {
            luckoffset = 1;
            if (this.rand.nextInt(5) == 0) {
               luckoffset = 2;
            }
         }

         if (this.rand.nextInt(2) == 0) {
            this.luck += luckoffset;
         } else {
            this.luck -= luckoffset;
         }
      } else {
         this.luck = luck;
      }

      this.setLevel(this.level);
      this.setLuck(this.luck);
   }

   public EntityChicken createChild(EntityAgeable arg0) {
      int lucko = this.rand.nextInt((((EntitycLuckey)arg0).luck + this.luck) / 2 + 1);
      int levelo = this.rand.nextInt((((EntitycLuckey)arg0).level + this.level) / 2 + 1);
      return new EntitycLuckey(arg0.world, levelo, lucko);
   }

   private static final net.minecraft.network.datasync.DataParameter<Integer> LEVEL =
      net.minecraft.network.datasync.EntityDataManager.createKey(EntitycLuckey.class, net.minecraft.network.datasync.DataSerializers.VARINT);
   private static final net.minecraft.network.datasync.DataParameter<Integer> LUCK =
      net.minecraft.network.datasync.EntityDataManager.createKey(EntitycLuckey.class, net.minecraft.network.datasync.DataSerializers.VARINT);

   @Override
   protected void entityInit() {
      super.entityInit();
      this.dataManager.register(LEVEL, 0);
      this.dataManager.register(LUCK, 0);
   }

   public void setLevel(int type) {
      this.dataManager.set(LEVEL, type);
   }

   public int getLevel() {
      return this.dataManager.get(LEVEL);
   }

   public void setLuck(int type) {
      this.dataManager.set(LUCK, type);
   }

   public int getLuck() {
      return this.dataManager.get(LUCK);
   }

   public void writeEntityToNBT(NBTTagCompound nbtag) {
      super.writeEntityToNBT(nbtag);
      nbtag.setInteger("Level", this.getLevel());
      nbtag.setInteger("Luck", this.getLuck());
      nbtag.setString("cDrop", this.customDrop);
   }

   public void readEntityFromNBT(NBTTagCompound nbtag) {
      super.readEntityFromNBT(nbtag);
      this.setLevel(nbtag.getInteger("Level"));
      this.setLuck(nbtag.getInteger("Luck"));
      this.customDrop = nbtag.getString("cDrop");
   }

   public boolean isBreedingItem(ItemStack par1ItemStack) {
      return par1ItemStack.getItem() == Items.DIAMOND;
   }

   /** The Clucky tool shows the level / luck (or the custom drop script) of a cLUCKey. */
   @Override
   public boolean processInteract(EntityPlayer player, net.minecraft.util.EnumHand hand) {
      ItemStack itemstack = player.getHeldItem(hand);
      if (!itemstack.isEmpty() && itemstack.getItem() == YTItems.CluckTool) {
         if (!this.world.isRemote) {
            player.sendMessage(new TextComponentString(this.customDrop == null || this.customDrop.isEmpty()
               ? "cLUCKey level " + this.getLevel() + ", luck " + this.getLuck() : this.customDrop));
         }
         return true;
      }
      return super.processInteract(player, hand);
   }

   protected void dropFewItems(boolean par1, int par2) {
      super.dropFewItems(par1, par2);
      boolean disabled = false;
      if (!this.world.isRemote && !disabled) {
         CluckyDrop cluckydrop = new CluckyDrop(this.world, new BlockPos(this.posX, this.posY, this.posZ), this);
         if (this.customDrop != null && !this.customDrop.isEmpty()) {
            cluckydrop.parseDrop(this.customDrop);
         } else {
            int luck = this.getLuck();
            int level = this.getLevel();
            luck = this.convertLuck(luck);
            level = this.convertLevel(level);
            doRDrop(level, luck, cluckydrop);
         }
      }
   }

   private int convertLevel(int level) {
      switch (level) {
         case 0:
            return 0;
         case 1:
            return 25;
         case 2:
            return 50;
         case 3:
            return 75;
         case 4:
            return 100;
         default:
            return 0;
      }
   }

   private int convertLuck(int luck) {
      switch (luck) {
         case 0:
            return -100;
         case 1:
            return -50;
         case 2:
            return 0;
         case 3:
            return 50;
         case 4:
            return 100;
         default:
            return 0;
      }
   }

   static {
      DropList.add(new RandomDrop(5, -20, 1.0, "explode(2,false)"));
      DropList.add(new RandomDrop(10, -30, 1.0, "explode(5,false)"));
      DropList.add(new RandomDrop(15, -40, 1.0, "explode(8,false)"));
      DropList.add(new RandomDrop(20, -50, 1.0, "explode(12,false)"));
      DropList.add(new RandomDrop(25, -60, 1.0, "explode(16,false)"));
      DropList.add(new RandomDrop(30, -50, 1.0, "explode(10,true)"));
      DropList.add(new RandomDrop(40, -60, 1.0, "explode(15,true)"));
      DropList.add(new RandomDrop(50, -70, 1.0, "explode(20,true)"));
      DropList.add(new RandomDrop(60, -80, 1.0, "explode(25,true)"));
      DropList.add(new RandomDrop(70, -90, 1.0, "explode(30,true)"));
      DropList.add(new RandomDrop(5, -10, 1.5, "enderperl(1,1)"));
      DropList.add(new RandomDrop(7, -15, 1.5, "enderperl(2,1)"));
      DropList.add(new RandomDrop(13, -30, 1.5, "enderperl(3,1.25)"));
      DropList.add(new RandomDrop(17, -40, 1.5, "enderperl(4,1.25)"));
      DropList.add(new RandomDrop(75, -80, 1.5, "enderperl(8,1.25)"));
      DropList.add(new RandomDrop(90, -100, 1.5, "enderperl(10,1.5)"));
      DropList.add(new RandomDrop(13, -5, 1.0, "dropPlayers(3,false,true)"));
      DropList.add(new RandomDrop(15, -10, 1.0, "dropPlayers(6,false,true)"));
      DropList.add(new RandomDrop(25, -15, 1.0, "dropPlayers(9,false,true)"));
      DropList.add(new RandomDrop(40, -30, 1.0, "dropPlayers(12,false,true)"));
      DropList.add(new RandomDrop(80, -70, 1.0, "dropPlayers(27,false,true)"));
      DropList.add(new RandomDrop(80, -70, 1.0, "dropPlayers(6,true,true)"));
      DropList.add(new RandomDrop(60, -75, 1.0, "dropPlayers(19,true,true)"));
      DropList.add(new RandomDrop(70, -80, 1.0, "dropPlayers(30,true,true)"));
      DropList.add(new RandomDrop(80, -70, 1.0, "dropPlayers(40,true,true)"));
      DropList.add(new RandomDrop(100, -100, 0.5, "dropPlayers(255,false,false)"));
      DropList.add(new RandomDrop(100, -100, 0.5, "dropPlayers(255,true,true)"));
      DropList.add(new RandomDrop(5, -10, 1.0, "dropTNT(1,0)"));
      DropList.add(new RandomDrop(20, -20, 1.0, "dropTNT(4,.1)"));
      DropList.add(new RandomDrop(25, -40, 1.0, "dropTNT(8,.1)"));
      DropList.add(new RandomDrop(30, -60, 1.0, "dropTNT(16,.2)"));
      DropList.add(new RandomDrop(30, -20, 1.0, "dropTNT(3,.3)"));
      DropList.add(new RandomDrop(35, -40, 1.0, "dropTNT(7,.3)"));
      DropList.add(new RandomDrop(40, -60, 1.0, "dropTNT(19,.3)"));
      DropList.add(new RandomDrop(30, -20, 1.0, "dropTNT(10,.4)"));
      DropList.add(new RandomDrop(50, -40, 1.0, "dropTNT(20,.4)"));
      DropList.add(new RandomDrop(70, -60, 1.0, "dropTNT(30,.5)"));
      DropList.add(new RandomDrop(90, -80, 1.0, "dropTNT(40,.6)"));
      DropList.add(
         new RandomDrop(
            50,
            -80,
            1.0,
            "addPotionEfect(PotionEffect(15,700,5),20);SpawnItem(ItemStack(<minecraft:wooden_sword>,1,0));setTime(night);spawnHord(7,10,Entity(<Spider>));spawnHord(5,10,new Entity(<Creeper>));spawnHord(10,10,new Entity(<Zombie>));spawnHord(10,10,new Entity(<Skeleton>))"
         )
      );
      DropList.add(
         new RandomDrop(
            40,
            -80,
            1.0,
            "addPotionEfect(PotionEffect(15,700,5),20);SpawnItem(ItemStack(<minecraft:wooden_sword>,1,0));setTime(night);spawnHord(7,10,Entity(<Spider>))"
         )
      );
      DropList.add(
         new RandomDrop(
            40,
            -80,
            1.0,
            "addPotionEfect(PotionEffect(15,700,5),20);SpawnItem(ItemStack(<minecraft:wooden_sword>,1,0));setTime(night);spawnHord(5,10,Entity(<Creeper>))"
         )
      );
      DropList.add(
         new RandomDrop(
            25,
            -80,
            1.0,
            "addPotionEfect(PotionEffect(15,700,5),20);SpawnItem(ItemStack(<minecraft:wooden_sword>,1,0));setTime(night);spawnHord(10,10,Entity(<Zombie>))"
         )
      );
      DropList.add(
         new RandomDrop(
            30,
            -80,
            1.0,
            "addPotionEfect(PotionEffect(15,700,5),20);SpawnItem(ItemStack(<minecraft:wooden_sword>,1,0));setTime(night);spawnHord(10,10,Entity(<Skeleton>))"
         )
      );
      DropList.add(new RandomDrop(5, 20, 1.0, "addPotionEfect(PotionEffect(1,500,1),20)"));
      DropList.add(new RandomDrop(30, 40, 1.0, "addPotionEfect(PotionEffect(1,4000,2),20)"));
      DropList.add(new RandomDrop(60, 60, 1.0, "addPotionEfect(PotionEffect(1,1000000,0),20)"));
      DropList.add(new RandomDrop(80, 80, 1.0, "addPotionEfect(PotionEffect(1,1000000,5),20)"));
      DropList.add(new RandomDrop(5, -20, 1.0, "addPotionEfect(PotionEffect2,1000,1),20)"));
      DropList.add(new RandomDrop(30, -40, 1.0, "addPotionEfect(PotionEffect2,4000,2),20)"));
      DropList.add(new RandomDrop(60, -60, 1.0, "addPotionEfect(PotionEffect2,1000000,0),20)"));
      DropList.add(new RandomDrop(80, -80, 1.0, "addPotionEfect(PotionEffect2,1000000,5),20)"));
      DropList.add(new RandomDrop(5, 20, 1.0, "addPotionEfect(PotionEffect(3,1000,1),20)"));
      DropList.add(new RandomDrop(30, 40, 1.0, "addPotionEfect(PotionEffect(3,4000,2),20)"));
      DropList.add(new RandomDrop(60, 60, 1.0, "addPotionEfect(PotionEffect(3,1000000,0),20)"));
      DropList.add(new RandomDrop(80, 80, 1.0, "addPotionEfect(PotionEffect(3,1000000,5),20)"));
      DropList.add(new RandomDrop(5, -20, 1.0, "addPotionEfect(PotionEffect(4,1000,1),20)"));
      DropList.add(new RandomDrop(30, -40, 1.0, "addPotionEfect(PotionEffect(4,4000,2),20)"));
      DropList.add(new RandomDrop(60, -60, 1.0, "addPotionEfect(PotionEffect(4,1000000,0),20)"));
      DropList.add(new RandomDrop(80, -80, 1.0, "addPotionEfect(PotionEffect(4,1000000,5),20)"));
      DropList.add(new RandomDrop(10, 20, 1.0, "addPotionEfect(PotionEffect(5,1000,1),20)"));
      DropList.add(new RandomDrop(35, 40, 1.0, "addPotionEfect(PotionEffect(5,4000,2),20)"));
      DropList.add(new RandomDrop(65, 60, 1.0, "addPotionEfect(PotionEffect(5,1000000,0),20)"));
      DropList.add(new RandomDrop(85, 80, 1.0, "addPotionEfect(PotionEffect(5,1000000,5),20)"));
      DropList.add(new RandomDrop(10, -20, 1.0, "addPotionEfect(PotionEffect(18,1000,1),20)"));
      DropList.add(new RandomDrop(35, -40, 1.0, "addPotionEfect(PotionEffect(18,4000,2),20)"));
      DropList.add(new RandomDrop(65, -60, 1.0, "addPotionEfect(PotionEffect(18,1000000,0),20)"));
      DropList.add(new RandomDrop(85, -80, 1.0, "addPotionEfect(PotionEffect(18,1000000,5),20)"));
      DropList.add(new RandomDrop(20, 20, 1.0, "addPotionEfect(PotionEffect(10,1000,1),20)"));
      DropList.add(new RandomDrop(60, 40, 1.0, "addPotionEfect(PotionEffect(10,4000,2),20)"));
      DropList.add(new RandomDrop(80, 60, 1.0, "addPotionEfect(PotionEffect(10,1000000,0),20)"));
      DropList.add(new RandomDrop(100, 80, 1.0, "addPotionEfect(PotionEffect(10,1000000,5),20)"));
      DropList.add(new RandomDrop(20, -20, 1.0, "addPotionEfect(PotionEffect(19,1000,1),20)"));
      DropList.add(new RandomDrop(60, -40, 1.0, "addPotionEfect(PotionEffect(19,4000,2),20)"));
      DropList.add(new RandomDrop(80, -60, 1.0, "addPotionEfect(PotionEffect(19,1000000,0),20)"));
      DropList.add(new RandomDrop(100, -80, 1.0, "addPotionEfect(PotionEffect(19,1000000,5),20)"));
      DropList.add(new RandomDrop(5, 20, 1.0, "addPotionEfect(PotionEffect(11,1000,1),20)"));
      DropList.add(new RandomDrop(20, 40, 1.0, "addPotionEfect(PotionEffect(11,4000,2),20)"));
      DropList.add(new RandomDrop(60, 60, 1.0, "addPotionEfect(PotionEffect(11,1000000,0),20)"));
      DropList.add(new RandomDrop(80, 80, 1.0, "addPotionEfect(PotionEffect(11,1000000,5),20)"));
      DropList.add(new RandomDrop(10, 20, 0.1, "addPotionEfect(PotionEffect(12,1000,1),20)"));
      DropList.add(new RandomDrop(30, 40, 0.1, "addPotionEfect(PotionEffect(12,4000,2),20)"));
      DropList.add(new RandomDrop(40, 60, 0.1, "addPotionEfect(PotionEffect(12,1000000,0),20)"));
      DropList.add(new RandomDrop(60, 80, 1.0, "addPotionEfect(PotionEffect(12,1000000,5),20)"));
      DropList.add(new RandomDrop(5, -20, 1.0, "addPotionEfect(PotionEffect(17,1000,1),20)"));
      DropList.add(new RandomDrop(15, -40, 1.0, "addPotionEfect(PotionEffect(17,4000,2),20)"));
      DropList.add(new RandomDrop(35, -60, 1.0, "addPotionEfect(PotionEffect(17,1000000,0),20)"));
      DropList.add(new RandomDrop(55, -80, 1.0, "addPotionEfect(PotionEffect(17,1000000,5),20)"));
      DropList.add(new RandomDrop(5, 20, 1.0, "addPotionEfect(PotionEffect(23,1000,1),20)"));
      DropList.add(new RandomDrop(15, 40, 1.0, "addPotionEfect(PotionEffect(23,4000,2),20)"));
      DropList.add(new RandomDrop(35, 60, 1.0, "addPotionEfect(PotionEffect(23,1000000,0),20)"));
      DropList.add(new RandomDrop(55, 80, 1.0, "addPotionEfect(PotionEffect(23,1000000,5),20)"));
      DropList.add(new RandomDrop(80, -80, 1.0, "addPotionEfect(PotionEffect(8,1000000,255),20);wisper(<Hey @p! Now, Jump!>,20)"));
      DropList.add(new RandomDrop(30, -30, 1.0, "addPotionEfect(PotionEffect(8,300,20),20);wisper(<Hey @p! Now, Jump!>,20)"));
      DropList.add(new RandomDrop(25, 10, 1.0, "addPotionEfect(PotionEffect(21,1000000,0),20)"));
      DropList.add(new RandomDrop(35, 20, 1.0, "addPotionEfect(PotionEffect(21,1000000,2),20)"));
      DropList.add(new RandomDrop(45, 40, 1.0, "addPotionEfect(PotionEffect(21,1000000,4),20)"));
      DropList.add(new RandomDrop(55, 60, 1.0, "addPotionEfect(PotionEffect(21,1000000,6),20)"));
      DropList.add(new RandomDrop(65, 80, 1.0, "addPotionEfect(PotionEffect(21,1000000,8),20)"));
      DropList.add(new RandomDrop(75, 100, 1.0, "addPotionEfect(PotionEffect(21,1000000,10),20)"));
      DropList.add(new RandomDrop(5, 10, 1.0, "addPotionEfect(PotionEffect(22,1000000,0),20)"));
      DropList.add(new RandomDrop(15, 20, 1.0, "addPotionEfect(PotionEffect(22,1000000,2),20)"));
      DropList.add(new RandomDrop(25, 40, 1.0, "addPotionEfect(PotionEffect(22,1000000,4),20)"));
      DropList.add(new RandomDrop(35, 60, 1.0, "addPotionEfect(PotionEffect(22,1000000,6),20)"));
      DropList.add(new RandomDrop(45, 80, 1.0, "addPotionEfect(PotionEffect(22,1000000,8),20)"));
      DropList.add(new RandomDrop(55, 100, 1.0, "addPotionEfect(PotionEffect(22,1000000,10),20)"));
      DropList.add(
         new RandomDrop(
            100,
            -100,
            1.0,
            "addPotionEfect(PotionEffect(16,1000000,0),20);addPotionEfect(PotionEffect(15,1000000,0),20);wisper(<Hey @p! Find the milk!>,20);spreadItems(PlayersWithin(20)+3,20,ItemStack(<minecraft:milk_bucket>,1,0))"
         )
      );
      DropList.add(new RandomDrop(5, -50, 1.0, "addPotionEfect(PotionEffect(14,1000000,0),20)"));
      DropList.add(new RandomDrop(5, 50, 1.0, "addPotionEfect(PotionEffect(14,1000000,0),20)"));
      DropList.add(
         new RandomDrop(
            100,
            30,
            1.0,
            "addPotionEfectP(PotionEffect(21,1000000,255),nearestPlayer);addPotionEfectP(PotionEffect(9,1000000,255),nearestPlayer);wisperP(<@p, is it worth it?>,nearestPlayer);"
         )
      );
      DropList.add(new RandomDrop(55, 50, 1.0, "addPotionEfect(PotionEffect(13,1000000,10),20);wisper(<@p, you are now a fish!>,20)"));
      DropList.add(new RandomDrop(100, -100, 1.0, "addPotionEfect(PotionEffect(20,1000000,0),20);wisper(<I'm so mean.>,20)"));
      DropList.add(new RandomDrop(100, 100, 1.0, "dropTower(750,[" + TowerHelp.Beacon + "])"));
      DropList.add(new RandomDrop(100, 100, 1.0, "dropTower(750,[" + TowerHelp.DEgg + "])"));
      DropList.add(new RandomDrop(90, 80, 1.0, "dropTower(750,[" + TowerHelp.BlkEMD + "])"));
      DropList.add(new RandomDrop(90, 80, 1.0, "dropTower(750,[" + TowerHelp.BlkDMD + "])"));
      DropList.add(new RandomDrop(80, 80, 1.0, "dropTower(750,[" + TowerHelp.BlkGLD + "])"));
      DropList.add(new RandomDrop(70, 80, 1.0, "dropTower(750,[" + TowerHelp.BlkIRN + "])"));
      DropList.add(new RandomDrop(70, 80, 1.0, "dropTower(750,[" + TowerHelp.BlkLPS + "])"));
      DropList.add(new RandomDrop(60, 80, 1.0, "dropTower(750,[" + TowerHelp.BlkRDS + "])"));
      DropList.add(new RandomDrop(50, 80, 1.0, "dropTower(750,[" + TowerHelp.BlkCL + "])"));
      DropList.add(new RandomDrop(60, 50, 1.0, "dropTower(750,[" + TowerHelp.OreEMD + "])"));
      DropList.add(new RandomDrop(60, 50, 1.0, "dropTower(750,[" + TowerHelp.OreDMD + "])"));
      DropList.add(new RandomDrop(50, 50, 1.0, "dropTower(750,[" + TowerHelp.OreGLD + "])"));
      DropList.add(new RandomDrop(40, 50, 1.0, "dropTower(750,[" + TowerHelp.OreIRN + "])"));
      DropList.add(new RandomDrop(40, 50, 1.0, "dropTower(750,[" + TowerHelp.OreLPS + "])"));
      DropList.add(new RandomDrop(30, 50, 1.0, "dropTower(750,[" + TowerHelp.OreRDS + "])"));
      DropList.add(new RandomDrop(40, 50, 1.0, "dropTower(750,[" + TowerHelp.OreQ + "])"));
      DropList.add(new RandomDrop(20, 50, 1.0, "dropTower(750,[" + TowerHelp.OreCL + "])"));
      DropList.add(new RandomDrop(70, 80, 1.0, "dropTower(750,[" + TowerHelp.sponge + "])"));
      DropList.add(new RandomDrop(50, 90, 1.0, "dropTower(750,[" + TowerHelp.ENtable + "])"));
      DropList.add(new RandomDrop(30, 60, 1.0, "dropTower(750,[" + TowerHelp.Ctable + "])"));
      DropList.add(new RandomDrop(20, 70, 1.0, "dropTower(750,[" + TowerHelp.cake + "])"));
      DropList.add(new RandomDrop(40, 70, 1.0, "dropTower(750,[" + TowerHelp.fungi + "])"));
      DropList.add(new RandomDrop(30, 60, 1.0, "dropTower(750,[" + TowerHelp.melon + "])"));
      DropList.add(new RandomDrop(50, 70, 1.0, "dropTower(750,[" + TowerHelp.slime + "])"));
      DropList.add(new RandomDrop(10, 50, 1.0, "dropTower(750,[" + TowerHelp.chest + "])"));
      DropList.add(new RandomDrop(30, 40, 1.0, "dropTower(750,[" + TowerHelp.obsidian + "])"));
      DropList.add(new RandomDrop(5, 0, 1.0, "dropTower(750,[" + TowerHelp.dirt + "])"));
      DropList.add(new RandomDrop(15, 0, 1.0, "dropTower(750,[" + TowerHelp.stone + "])"));
      DropList.add(new RandomDrop(20, 10, 1.0, "dropTower(750,[" + TowerHelp.TNT + "])"));
      DropList.add(new RandomDrop(30, -10, 1.0, "dropTower(750,[" + TowerHelp.water + "])"));
      DropList.add(new RandomDrop(30, -10, 1.0, "dropTower(750,[" + TowerHelp.web + "])"));
      DropList.add(new RandomDrop(30, -50, 1.0, "dropTower(750,[" + TowerHelp.fire + "])"));
      DropList.add(new RandomDrop(60, -40, 1.0, "dropTower(750,[" + TowerHelp.lava + "])"));
      DropList.add(new RandomDrop(90, -90, 1.0, "dropTower(750,[" + TowerHelp.bedrock + "])"));
      DropList.add(new RandomDrop(90, -90, 1.0, "dropTower(750,[" + TowerHelp.barrier + "])"));
      DropList.add(new RandomDrop(25, -20, 1.0, "dropTower(750,[" + TowerHelp.Redstone + "," + TowerHelp.TNT + "])"));
      DropList.add(new RandomDrop(25, -30, 1.0, "dropTower(750,[" + TowerHelp.Redstone + "," + TowerHelp.TNT + "," + TowerHelp.TNT + "])"));
      DropList.add(
         new RandomDrop(25, -40, 1.0, "dropTower(750,[" + TowerHelp.Redstone + "," + TowerHelp.TNT + "," + TowerHelp.TNT + "," + TowerHelp.TNT + "])")
      );
      DropList.add(
         new RandomDrop(
            25, -50, 1.0, "dropTower(750,[" + TowerHelp.Redstone + "," + TowerHelp.TNT + "," + TowerHelp.TNT + "," + TowerHelp.TNT + "," + TowerHelp.TNT + "])"
         )
      );
      DropList.add(
         new RandomDrop(
            90,
            50,
            1.0,
            "dropTower(750,["
               + TowerHelp.BlkDMD
               + ","
               + TowerHelp.BlkEMD
               + ","
               + TowerHelp.BlkDMD
               + ","
               + TowerHelp.BlkEMD
               + ","
               + TowerHelp.BlkDMD
               + ","
               + TowerHelp.BlkEMD
               + "])"
         )
      );
      DropList.add(
         new RandomDrop(
            75,
            50,
            1.0,
            "dropTower(750,["
               + TowerHelp.OreDMD
               + ","
               + TowerHelp.OreEMD
               + ","
               + TowerHelp.OreDMD
               + ","
               + TowerHelp.OreEMD
               + ","
               + TowerHelp.OreDMD
               + ","
               + TowerHelp.OreEMD
               + "])"
         )
      );
      DropList.add(
         new RandomDrop(
            60,
            60,
            1.0,
            "dropTower(750,["
               + TowerHelp.books
               + ","
               + TowerHelp.books
               + ","
               + TowerHelp.books
               + ","
               + TowerHelp.books
               + ","
               + TowerHelp.books
               + ","
               + TowerHelp.books
               + ","
               + TowerHelp.books
               + ","
               + TowerHelp.ENtable
               + "])"
         )
      );
      DropList.add(
         new RandomDrop(
            100,
            100,
            1.0,
            "dropTower(750,["
               + TowerHelp.RndSClay
               + ","
               + TowerHelp.RndSClay
               + ","
               + TowerHelp.RndSClay
               + ","
               + TowerHelp.RndSClay
               + ","
               + TowerHelp.RndSClay
               + ","
               + TowerHelp.RndSClay
               + ","
               + TowerHelp.RndSClay
               + ","
               + TowerHelp.RndSClay
               + ","
               + TowerHelp.RndSClay
               + ","
               + TowerHelp.RndSClay
               + ","
               + TowerHelp.RndSClay
               + ","
               + TowerHelp.RndSClay
               + ","
               + TowerHelp.RndSClay
               + ","
               + TowerHelp.RndSClay
               + ","
               + TowerHelp.RndSClay
               + ","
               + TowerHelp.DEgg
               + "])"
         )
      );
      DropList.add(
         new RandomDrop(
            100,
            100,
            1.0,
            "dropTower(750,["
               + TowerHelp.RndSClay
               + ","
               + TowerHelp.RndSClay
               + ","
               + TowerHelp.RndSClay
               + ","
               + TowerHelp.RndSClay
               + ","
               + TowerHelp.RndSClay
               + ","
               + TowerHelp.RndSClay
               + ","
               + TowerHelp.RndSClay
               + ","
               + TowerHelp.RndSClay
               + ","
               + TowerHelp.RndSClay
               + ","
               + TowerHelp.RndSClay
               + ","
               + TowerHelp.RndSClay
               + ","
               + TowerHelp.RndSClay
               + ","
               + TowerHelp.RndSClay
               + ","
               + TowerHelp.RndSClay
               + ","
               + TowerHelp.RndSClay
               + ","
               + TowerHelp.Beacon
               + "])"
         )
      );
      DropList.add(
         new RandomDrop(
            25,
            0,
            1.0,
            "dropTower(750,[" + TowerHelp.bars + "," + TowerHelp.bars + "," + TowerHelp.bars + "," + TowerHelp.bars + "," + TowerHelp.glowstone + "])"
         )
      );
      DropList.add(
         new RandomDrop(
            0,
            0,
            1.0,
            "wisper(<Oh look a well!>,100);Well("
               + TowerHelp.BlkIRN
               + ",<wisper(<Hope you love Potatoes!>,100)/etossItems(80,.3,ItemStack(<minecraft:potato>,1,0))/etossItems(80,.3,ItemStack(<minecraft:baked_potato>,1,0))/etossItems(80,.3,ItemStack(<minecraft:poisonous_potato>,1,0))>)"
         )
      );
      DropList.add(new RandomDrop(70, -80, 1.0, "wisper(<Oh look a well!>,100);Well(" + TowerHelp.BlkDMD + ",<wisper(<Dodge this>,100)/edropTNT(40,.3)>)"));
      DropList.add(
         new RandomDrop(
            100,
            -50,
            1.0,
            "wisper(<Oh look a well! (there is something suspicious about this one)>,100);Well("
               + TowerHelp.netherrack
               + ",<wisper(<IT CAME FROM THE NETHER!>,100)/etossBlocks(500,.6,"
               + TowerHelp.lava
               + ")/espawnHord(40,10,Entity(<PigZombie>))/espawnHord(7,10,Entity(<Ghast>))/espawnHord(20,10,Entity(<Blaze>))/espawnHord(50,10,Entity(<LavaSlime>))>)"
         )
      );
      DropList.add(
         new RandomDrop(
            80,
            60,
            1.0,
            "wisper(<Oh look a well! I dont trust it>,100);Well("
               + TowerHelp.BlkIRN
               + ",<wisper(<Oh I was Wrong>,100)/etossItems(15,.3,ItemStack(<minecraft:diamond>,1,0))/etossItems(15,.3,ItemStack(<minecraft:emerald>,1,0))/etossItems(25,.3,ItemStack(<minecraft:gold_ingot>,1,0))/etossItems(40,.3,ItemStack(<minecraft:iron_ingot>,1,0))>)"
         )
      );
      DropList.add(new RandomDrop(0, 0, 1.0, "wisper(<Oh look a well!>,100);Well(" + TowerHelp.BlkIRN + ",<wisper(<Erm? nothing happened!>,100)>)"));
      DropList.add(
         new RandomDrop(
            100,
            -100,
            1.0,
            "wisper(<Oh look a well! This is not a good well! Trust me>,100);Well("
               + TowerHelp.BlkIRN
               + ",<wisper(<You just Had to do it, you thought it could Have been a mountain of diamonds no It is just the BOB APOCCOLIPSE>,100)/espawnHord(20,10,Bobify(10,Entity(<Zombie>)))>)"
         )
      );
      DropList.add(
         new RandomDrop(
            100,
            0,
            1.0,
            "wisper(<Oh look a well!>,100);Well("
               + TowerHelp.BlkIRN
               + ",<wisper(<HAVE THE GIFT OF LOVE AND LIFE>,100)/etossBlocks(100,.4,"
               + TowerHelp.cobble
               + ")/etossBlocks(200,.5,"
               + TowerHelp.cobble
               + ")/etossBlocks(200,.6,"
               + TowerHelp.cobble
               + ")/etossBlocks(100,.7,"
               + TowerHelp.cobble
               + ")>)"
         )
      );
      DropList.add(
         new RandomDrop(
            30,
            0,
            1.0,
            "wisper(<Oh look a well!>,100);Well(" + TowerHelp.dirt + ",<wisper(<well that was anticlimatic>,100)/etossBlocks(10,.4," + TowerHelp.dirt + ")>)"
         )
      );
      DropList.add(
         new RandomDrop(20, -40, 1.0, "wisper(<Oh look a well!>,100);Well(" + TowerHelp.BlkGLD + ",<wisper(<Your wish is My command>,100)/edropTNT(20,.2)>)")
      );
      DropList.add(
         new RandomDrop(
            25,
            0,
            1.0,
            "wisper(<Oh look a well!>,100);Well("
               + TowerHelp.melon
               + ",<wisper(<well they were suposed to be diamonds?>,100)/etossBlocks(20,.6,"
               + TowerHelp.melon
               + ")>)"
         )
      );
      DropList.add(
         new RandomDrop(
            0, 0, 1.0, "wisper(<Oh look a well!>,100);Well(" + TowerHelp.BlkIRN + ",<wisper(<RUN IT IS COMEING!!!!>,100)/eSpawnEntity(Entity(<Cow>))>)"
         )
      );
      DropList.add(
         new RandomDrop(
            0,
            0,
            1.0,
            "wisper(<@p, You killed an inosent chicken. How dare you! He or she probibly had a family And you just slautered it. Do you feel bad about it cause you should.>,10)"
         )
      );
      DropList.add(new RandomDrop(0, 0, 1.0, "SpawnEntity(EntityPos(~0,~0,~0,cLUCKy(0,2)));wisper(<Ha you failed to kill it!>,10)"));
      DropList.add(
         new RandomDrop(
            0,
            0,
            1.0,
            "placeSign(~0,~0,~0,[<One is>,<Luckey>,<One is>,<NOT>]);SpawnEntity(EntityPos(~0,~0,~0,cLUCKy(0,4)));SpawnEntity(EntityPos(~0,~0,~0,cLUCKy(0,0)))"
         )
      );
      DropList.add(new RandomDrop(5, 0, 1.0, "SpawnItem(ItemStack(<minecraft:coal>,2+randInt(0,5),0))"));
      DropList.add(new RandomDrop(0, 0, 1.0, "SpawnItem(ItemStack(<minecraft:log>,2+randInt(0,5),0))"));
      DropList.add(new RandomDrop(3, 0, 1.0, "SpawnItem(ItemStack(<minecraft:wooden_sword>,1,0))"));
      DropList.add(new RandomDrop(4, 0, 1.0, "SpawnItem(ItemStack(<minecraft:bone>,2+randInt(0,5),0))"));
      DropList.add(
         new RandomDrop(
            100,
            100,
            1.0,
            "SpawnItem(Enchant(<minecraft:sharpness>,15,Enchant(<minecraft:bane_of_arthropods>,15,Enchant(<minecraft:smite>,15,Enchant(<minecraft:looting>,11,Enchant(<minecraft:fire_aspect>,6,Enchant(<minecraft:unbreaking>,50,ReName(<cLUCKy SWORD>,ItemStack(<minecraft:diamond_sword>,1,0)))"
         )
      );
      DropList.add(
         new RandomDrop(
            100,
            100,
            1.0,
            "SpawnItem(Enchant(<minecraft:power>,15,Enchant(<minecraft:looting>,11,Enchant(<minecraft:punch>,11,Enchant(<minecraft:infinity>,100,Enchant(<minecraft:flame>,6,Enchant(<minecraft:unbreaking>,50,ReName(<cLUCKy BOW>,ItemStack(<minecraft:bow>,0,0)))"
         )
      );
      DropList.add(new RandomDrop(100, 100, 1.0, "SpawnItem(Enchant(<minecraft:unbreaking>,50,ReName(<cLUCKy HOE>,ItemStack(<minecraft:diamond_hoe>,1,0)))"));
      DropList.add(
         new RandomDrop(
            100,
            100,
            1.0,
            "SpawnItem(Enchant(<minecraft:efficiency>,15,Enchant(<minecraft:fortune>,11,Enchant(<minecraft:unbreaking>,50,ReName(<cLUCKy PICK>,ItemStack(<minecraft:diamond_pickaxe>,1,0)))"
         )
      );
      DropList.add(
         new RandomDrop(
            100,
            100,
            1.0,
            "SpawnItem(Enchant(<minecraft:efficiency>,15,Enchant(<minecraft:fortune>,11,Enchant(<minecraft:unbreaking>,50,ReName(<cLUCKy SHOVEL>,ItemStack(<minecraft:diamond_shovel>,1,0)))"
         )
      );
      DropList.add(
         new RandomDrop(
            100,
            100,
            1.0,
            "SpawnItem(Enchant(<minecraft:sharpness>,15,Enchant(<minecraft:efficiency>,15,Enchant(<minecraft:fortune>,11,Enchant(<minecraft:unbreaking>,50,ReName(<cLUCKy AXE>,ItemStack(<minecraft:diamond_axe>,1,0)))"
         )
      );
      DropList.add(
         new RandomDrop(
            100,
            100,
            1.0,
            "SpawnItem(Enchant(<minecraft:protection>,10,Enchant(<minecraft:fire_protection>,8,Enchant(<minecraft:blast_protection>,15,Enchant(<minecraft:projectile_protection>,5,Enchant(<minecraft:thorns>,15,Enchant(<minecraft:respiration>,18,Enchant(<minecraft:aqua_affinity>,10,Enchant(<minecraft:unbreaking>,50,ReName(<cLUCKy HELMET>,ItemStack(<minecraft:diamond_helmet>,1,0)))"
         )
      );
      DropList.add(
         new RandomDrop(
            100,
            100,
            1.0,
            "SpawnItem(Enchant(<minecraft:protection>,10,Enchant(<minecraft:fire_protection>,8,Enchant(<minecraft:blast_protection>,15,Enchant(<minecraft:projectile_protection>,5,Enchant(<minecraft:thorns>,15,Enchant(<minecraft:unbreaking>,50,ReName(<cLUCKy CHESTPLATE>,ItemStack(<minecraft:diamond_chestplate>,1,0)))"
         )
      );
      DropList.add(
         new RandomDrop(
            100,
            100,
            1.0,
            "SpawnItem(Enchant(<minecraft:protection>,10,Enchant(<minecraft:fire_protection>,8,Enchant(<minecraft:blast_protection>,15,Enchant(<minecraft:projectile_protection>,5,Enchant(<minecraft:thorns>,15,Enchant(<minecraft:unbreaking>,50,ReName(<cLUCKy LEGGINGS>,ItemStack(<minecraft:diamond_leggings>,1,0)))"
         )
      );
      DropList.add(
         new RandomDrop(
            100,
            100,
            1.0,
            "SpawnItem(Enchant(<minecraft:protection>,10,Enchant(<minecraft:fire_protection>,8,Enchant(<minecraft:blast_protection>,15,Enchant(<minecraft:projectile_protection>,5,Enchant(<minecraft:thorns>,15,Enchant(<minecraft:feather_falling>,100,Enchant(<minecraft:depth_strider>,15,Enchant(<minecraft:unbreaking>,50,ReName(<cLUCKy BOOTS>,ItemStack(<minecraft:diamond_boots>,1,0)))"
         )
      );
      DropList.add(new RandomDrop(0, 0, 1.0, "makeItRain(randInt(0,40)*5+40)"));
      DropList.add(new RandomDrop(0, 10, 1.0, "SpawnItem(ReName(<I'm sticking to this joke>,ItemStack(<minecraft:stick>,1,0)))"));
      DropList.add(new RandomDrop(0, 0, 1.0, "SpawnItem(ReName(<Light It Up!>,ItemStack(<minecraft:flint_and_steel>,1,0)))"));
      DropList.add(new RandomDrop(10, 20, 1.0, "SpawnItem(ReName(<A rock that burns!!!!>,ItemStack(<minecraft:coal>,1,0)))"));
      DropList.add(new RandomDrop(0, 0, 1.0, "SpawnItem(ReName(<Be carefull of the pointy end>,ItemStack(<minecraft:arrow>,1,0)))"));
      DropList.add(new RandomDrop(20, 100, 1.0, "SpawnItem(ReName(<SHINY>,ItemStack(<minecraft:diamond>,1,0)))"));
      DropList.add(new RandomDrop(0, 0, 1.0, "SpawnItem(ReName(<Villager Leg Soup>,ItemStack(<minecraft:mushroom_stew>,1,0)))"));
      DropList.add(new RandomDrop(0, 0, 1.0, "SpawnItem(ReName(<RedWater>,ItemStack(<minecraft:lava_bucket>,1,0)))"));
      DropList.add(new RandomDrop(0, -100, 1.0, "SpawnItem(ReName(<Blood>,ItemStack(<minecraft:redstone>,1,0)))"));
      DropList.add(new RandomDrop(0, 10, 1.0, "SpawnItem(ReName(<This one will hatch!>,ItemStack(<minecraft:egg>,1,0)))"));
      DropList.add(new RandomDrop(0, -5, 1.0, "SpawnItem(ReName(<bone to pick with>,ItemStack(<minecraft:bone>,1,0)))"));
      DropList.add(new RandomDrop(0, 5, 1.0, "SpawnItem(ReName(<Turkey>,ItemStack(<minecraft:cooked_chicken>,1,0)))"));
      DropList.add(new RandomDrop(50, 50, 1.0, "SpawnItem(ReName(<toilet>,ItemStack(<minecraft:cauldron>,1,0)))"));
      DropList.add(new RandomDrop(50, -50, 1.0, "SpawnItem(ReName(<get lost>,ItemStack(<minecraft:map>,1,0)))"));
      DropList.add(new RandomDrop(20, -50, 1.0, "SpawnItem(ReName(<Human Finger Cheese>,ItemStack(<minecraft:pumpkin_pie>,1,0)))"));
      DropList.add(new RandomDrop(10, -50, 1.0, "SpawnItem(ReName(<You are wasting your time>,ItemStack(<minecraft:clock>,1,0)))"));
      DropList.add(new RandomDrop(10, 50, 1.0, "SpawnItem(ReName(<You are NOT wasting your time>,ItemStack(<minecraft:clock>,1,0)))"));
      DropList.add(new RandomDrop(0, 100, 1.0, "SpawnItem(ReName(<I'm on a rail with these named Items>,ItemStack(<minecraft:golden_rail>,1,0)))"));
      DropList.add(new RandomDrop(50, 0, 1.0, "SpawnItem(ReName(<the Brush off bush>,ItemStack(<minecraft:deadbush>,1,0)))"));
      DropList.add(new RandomDrop(60, 0, 1.0, "SpawnItem(ReName(<property of Charlotte>,ItemStack(<minecraft:web>,1,0)))"));
      DropList.add(new RandomDrop(80, -80, 1.0, "SpawnItem(ReName(<Why are you so cold harted?>,ItemStack(<minecraft:ice>,1,0)))"));
      DropList.add(
         new RandomDrop(
            80,
            80,
            1.0,
            "SpawnItem(ReName(<Do you want to build a Snowman?>,ItemStack(<minecraft:snow>,1,0)));SpawnItem(ReName(<Do you want to build a Snowman?>,ItemStack(<minecraft:pumpkin>,1,0)))"
         )
      );
   }
}
