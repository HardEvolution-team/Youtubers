package com.electrifiedded.youtubers.Entity.cLUCKY;

import net.minecraft.init.Enchantments;
import com.electrifiedded.archaiclib.legacy.world.LegacyWorld;
import com.electrifiedded.youtubers.YTConfig;
import com.electrifiedded.youtubers.YTItems;
import com.electrifiedded.youtubers.Youtubers;
import net.minecraft.util.math.BlockPos;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.NumberInvalidException;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import com.electrifiedded.archaiclib.legacy.entity.LegacyEquipment;
import com.electrifiedded.archaiclib.legacy.entity.LegacyEntityHelper;
import com.electrifiedded.archaiclib.legacy.entity.LegacyEntityList;
import com.electrifiedded.archaiclib.legacy.world.LegacyBlockIds;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityEnderPearl;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntitySign;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class CluckyDrop {
   public String STR;
   public String status;
   public BlockPos loc;
   private World world;
   private Random rand;
   EntityLivingBase entity;

   public CluckyDrop(World world, BlockPos pos, EntityLivingBase entity) {
      this.loc = pos;
      this.world = world;
      this.rand = new com.electrifiedded.archaiclib.util.FastRandom();
      this.entity = entity;
   }

   public void parseDrop(String Drop) {
      System.out.println("Parsing Drop");
      String[] var = Drop.split(";");

      for (int a = 0; a < var.length; a++) {
         this.STR = var[a];
         this.parseMain();
      }
   }

   private void parseMain() {
      if (!this.parseMainUtil()
         && !this.parseMainBoom()
         && !this.parseMainPresetish()
         && !this.parseMainBlocks()
         && !this.parseMainPlayer()
         && !this.parseMainEntity()
         && this.parseMainItems()) {
      }
   }

   private boolean parseMainUtil() {
      if (this.STR.indexOf("setLoc(") == 0) {
         System.out.println("Parsing setLOC");
         this.STR = this.STR.substring(7);
         double X = this.getPosX();
         double Y = this.getPosY();
         double Z = this.getPosZ();
         this.loc = new BlockPos(X, Y, Z);
         return true;
      } else if (this.STR.indexOf("setTime(") == 0) {
         this.STR = this.STR.substring(8);
         if (this.STR.indexOf("dawn)") == 0) {
            this.STR = this.STR.substring(5);
            this.setTime(0L);
         } else if (this.STR.indexOf("day)") == 0) {
            this.STR = this.STR.substring(4);
            this.setTime(1000L);
         } else if (this.STR.indexOf("noon)") == 0) {
            this.STR = this.STR.substring(5);
            this.setTime(6000L);
         } else if (this.STR.indexOf("dusk)") == 0) {
            this.STR = this.STR.substring(5);
            this.setTime(12000L);
         } else if (this.STR.indexOf("night)") == 0) {
            this.STR = this.STR.substring(6);
            this.setTime(14000L);
         } else if (this.STR.indexOf("midnight)") == 0) {
            this.STR = this.STR.substring(9);
            this.setTime(18000L);
         } else {
            this.setTime((long)this.get_number());
         }

         return true;
      } else {
         if (this.STR.indexOf("wait(") == 0) {
            this.STR = this.STR.substring(5);

            try {
               Thread.sleep((long)this.get_number());
            } catch (InterruptedException var7) {
               var7.printStackTrace();
            }
         }

         return false;
      }
   }

   private boolean parseMainBoom() {
      if (this.STR.indexOf("dropTNT(") == 0) {
         System.out.println("Parsing TNTdrop");
         this.STR = this.STR.substring(8);
         int n = (int)this.getNumb();
         double speed = this.getNumb();
         this.dropTNT(n, speed);
         return true;
      } else if (this.STR.indexOf("explode(") == 0) {
         System.out.println("Parsing explode");
         this.STR = this.STR.substring(8);
         float size = (float)this.getNumb();
         System.out.println("size=" + size);
         boolean fire = this.getBool();
         System.out.println("fire=" + fire);
         this.explode(size, fire);
         System.out.println("explode Drop compleate");
         return true;
      } else {
         return false;
      }
   }

   private boolean parseMainPresetish() {
      if (this.STR.indexOf("dropPlayers(") == 0) {
         this.STR = this.STR.substring(12);
         int maxdepth = (int)this.getNumb();
         boolean lava = this.getBool();
         boolean keepinworld = this.getBool();
         this.dropPlayers(maxdepth, lava, keepinworld);
         return true;
      } else if (this.STR.indexOf("enderperl(") == 0) {
         this.STR = this.STR.substring(10);
         int n = (int)this.getNumb();
         double speed = this.getNumb();
         this.enderperl(n, speed);
         return true;
      } else if (this.STR.indexOf("tower(") == 0) {
         this.STR = this.STR.substring(6);
         ItemStack block = this.getItemStack();
         int[][] cluckies = this.toIntArray(this.getArrayNumb2());
         this.Tower(block, cluckies);
         return true;
      } else if (this.STR.indexOf("Well(") == 0) {
         this.STR = this.STR.substring(5);
         ItemStack block = this.getItemStack();
         String cluck = this.getString();
         this.Well(block, cluck);
         return true;
      } else if (this.STR.indexOf("DrownTrap()") == 0) {
         this.STR = this.STR.substring(11);
         this.DrownTrap();
         return true;
      } else if (this.STR.indexOf("dodrop(") == 0) {
         this.STR = this.STR.substring(7);
         int level = (int)this.getNumb();
         int luck = (int)this.getNumb();
         return true;
      } else {
         return false;
      }
   }

   private boolean parseMainBlocks() {
      if (this.STR.indexOf("tossBlocks(") == 0) {
         this.STR = this.STR.substring(11);
         int n = (int)this.getNumb();
         double speed = this.getNumb();
         ItemStack block = this.getItemStack();
         this.tossBlocks(n, speed, block);
         return true;
      } else if (this.STR.indexOf("dropTower(") == 0) {
         this.STR = this.STR.substring(10);
         long delay = (long)this.getNumb();
         ItemStack[] blocks = this.getArrayItemStack();
         this.dropTower(delay, blocks);
         return true;
      } else if (this.STR.indexOf("dropfallingblock(") == 0) {
         this.STR = this.STR.substring(17);
         ItemStack block = this.getItemStack();
         double x = this.getPosX();
         double y = this.getPosY();
         double z = this.getPosZ();
         double Vx = this.getNumb();
         double Vy = this.getNumb();
         double Vz = this.getNumb();
         this.dropfallingblock(this.blockFromStack(block), block.getMetadata(), x, y, z, Vx, Vy, Vz);
         return true;
      } else {
         if (this.STR.indexOf("placeSign(") == 0) {
            this.STR = this.STR.substring(10);
            int x = (int)this.getPosX();
            int y = (int)this.getPosY();
            int z = (int)this.getPosZ();
            String[] text = this.getArrayString();
            this.placeSign(x, y, z, text);
         } else if (this.STR.indexOf("setBlock(") == 0) {
            this.STR = this.STR.substring(9);
            ItemStack block = this.getItemStack();
            int x = (int)this.getPosX();
            int y = (int)this.getPosY();
            int z = (int)this.getPosZ();
            LegacyWorld.setBlock(this.world, x, y, z, this.blockFromStack(block), block.getMetadata(), 3);
         }

         return false;
      }
   }

   private boolean parseMainPlayer() {
      if (this.STR.indexOf("addPotionEfect(") == 0) {
         this.STR = this.STR.substring(15);
         PotionEffect effect = this.getPotionEffect();
         int range = (int)this.getNumb();
         this.addPotionEfect(effect, range);
         return true;
      } else if (this.STR.indexOf("addPotionEfectP(") == 0) {
         this.STR = this.STR.substring(15);
         PotionEffect effect = this.getPotionEffect();
         EntityPlayer player = this.getPlayer();
         this.addPotionEfectP(effect, player);
         return true;
      } else if (this.STR.indexOf("wisperP(") == 0) {
         this.STR = this.STR.substring(8);
         EntityPlayer player = this.getPlayer();
         String text = this.getString();
         this.wisperP(player, text);
         return true;
      } else if (this.STR.indexOf("wisper(") == 0) {
         this.STR = this.STR.substring(7);
         String text = this.getString();
         double range = this.getNumb();
         this.wisper(text, range);
         return true;
      } else if (this.STR.indexOf("dropInventory()") == 0) {
         this.STR = this.STR.substring(15);
         this.dropInventory();
         return true;
      } else if (this.STR.indexOf("TPplayer(") == 0) {
         this.STR = this.STR.substring(9);
         double x = this.getPosX();
         double y = this.getPosY();
         double z = this.getPosZ();
         EntityPlayer player = this.getPlayer();
         this.TPplayers(x, y, z, player);
         return true;
      } else {
         return false;
      }
   }

   private boolean parseMainEntity() {
      if (this.STR.indexOf("spawnHord(") == 0) {
         this.STR = this.STR.substring(10);
         int number = (int)this.getNumb();
         int spread = (int)this.getNumb();
         Entity entity = this.getEntity();
         this.spawnHord(number, spread, entity);
         return true;
      } else if (this.STR.indexOf("SpawnEntity(") == 0) {
         this.STR = this.STR.substring(12);
         Entity entity = this.getEntity();
         this.SpawnEntity(entity);
         return true;
      } else if (this.STR.indexOf("SpawnEggs(") == 0) {
         this.STR = this.STR.substring(10);
         int n = (int)this.getNumb();
         double speed = this.getNumb();
         Entity entity = this.getEntity();
         this.tossEggs(n, speed, entity);
         return true;
      } else if (this.STR.indexOf("SpawnEggsItem(") == 0) {
         this.STR = this.STR.substring(14);
         int n = (int)this.getNumb();
         double speed = this.getNumb();
         ItemStack item = this.getItemStack();
         this.tossEggs(n, speed, item);
         return true;
      } else if (this.STR.indexOf("SpawnEggsDrop(") == 0) {
         this.STR = this.STR.substring(14);
         int n = (int)this.getNumb();
         double speed = this.getNumb();
         String drop = this.getString();
         this.tossEggs(n, speed, drop);
         return true;
      } else {
         return false;
      }
   }

   private boolean parseMainItems() {
      if (this.STR.indexOf("makeItRain(") == 0) {
         this.STR = this.STR.substring(11);
         int n = (int)this.getNumb();
         this.makeItRain(n);
         return true;
      } else if (this.STR.indexOf("dropRandomCrap(") == 0) {
         this.STR = this.STR.substring(15);
         int amount = (int)this.getNumb();
         this.dropRandomCrap(amount);
         return true;
      } else if (this.STR.indexOf("SpawnItem(") == 0) {
         this.STR = this.STR.substring(10);
         ItemStack item = this.getItemStack();
         this.SpawnItem(item);
         return true;
      } else if (this.STR.indexOf("spreadItems(") == 0) {
         this.STR = this.STR.substring(10);
         int number = (int)this.getNumb();
         int spread = (int)this.getNumb();
         ItemStack item = this.getItemStack();
         this.spreadItems(number, spread, item);
         return true;
      } else if (this.STR.indexOf("tossItems(") == 0) {
         this.STR = this.STR.substring(10);
         int number = (int)this.getNumb();
         double spread = this.getNumb();
         ItemStack item = this.getItemStack();
         this.tossItems(number, spread, item);
         return true;
      } else {
         return false;
      }
   }

   private double getPosX() {
      if (this.STR.indexOf("~") == 0) {
         this.STR = this.STR.substring(1);
         return this.loc.getX() + this.getNumb();
      } else {
         return this.getNumb();
      }
   }

   private double getPosY() {
      if (this.STR.indexOf("~") == 0) {
         this.STR = this.STR.substring(1);
         return this.loc.getY() + this.getNumb();
      } else {
         return this.getNumb();
      }
   }

   private double getPosZ() {
      if (this.STR.indexOf("~") == 0) {
         this.STR = this.STR.substring(1);
         return this.loc.getZ() + this.getNumb();
      } else {
         return this.getNumb();
      }
   }

   private double[][] getArrayNumb2() {
      if (this.STR.indexOf("[") == 0) {
         this.STR = this.STR.substring(1);
      }

      List list = new ArrayList();

      while (this.STR.indexOf("]") != 0) {
         list.add(this.getArrayNumb());
      }

      this.STR = this.STR.substring(1);
      int min = -1;

      for (int a = 0; a < list.size(); a++) {
         if (((double[])list.get(a)).length < min || min == -1) {
            min = ((double[])list.get(a)).length;
         }
      }

      double[][] arr = new double[list.size()][min];

      for (int ax = 0; ax < arr.length; ax++) {
         arr[ax] = (double[])list.get(ax);
      }

      return arr;
   }

   private double[] getArrayNumb() {
      if (this.STR.indexOf("[") == 0) {
         this.STR = this.STR.substring(1);
      }

      List<Double> list = new ArrayList<>();

      while (this.STR.indexOf("]") != 0) {
         list.add(this.getNumb());
      }

      this.STR = this.STR.substring(1);
      double[] arr = new double[list.size()];

      for (int a = 0; a < arr.length; a++) {
         arr[a] = list.get(a);
      }

      return arr;
   }

   private int[][] toIntArray(double[][] in) {
      int[][] out = new int[in.length][in[0].length];

      for (int a = 0; a < out.length; a++) {
         for (int b = 0; b < out[a].length; b++) {
            out[a][b] = (int)in[a][b];
         }
      }

      if (this.STR.indexOf(")") == 0 || this.STR.indexOf(",") == 0) {
         this.STR = this.STR.substring(1);
      }

      return out;
   }

   private double getNumb() {
      double val = this.compute_next_Numb();
      this.status = "good";

      while (this.STR.length() > 0 && "good".equals(this.status)) {
         val = this.get_opperator_Numb(val, "primary");
      }

      if (this.STR.indexOf(")") == 0 || this.STR.indexOf(",") == 0) {
         this.STR = this.STR.substring(1);
      }

      return val;
   }

   private double compute_next_Numb() {
      int sgn = this.get_sign();
      double numb = this.get_function_Numb();
      numb = this.get_opperator_Numb(numb, "secondary");
      return numb * sgn;
   }

   private double get_function_Numb() {
      if (this.STR.indexOf("randInt(") == 0) {
         this.STR = this.STR.substring(8);
         int min = (int)this.getNumb();
         int max = (int)this.getNumb();
         return this.rand.nextInt(max - min + 1) + min;
      } else if (this.STR.indexOf("randFlo(") == 0) {
         this.STR = this.STR.substring(8);
         double min = this.getNumb();
         double max = this.getNumb();
         return this.rand.nextDouble() * (max - min) + min;
      } else if (this.STR.indexOf("PlayersWithin(") == 0) {
         this.STR = this.STR.substring(14);
         double range = this.getNumb();
         return this.getnearPlayers(range).size();
      } else {
         return this.get_number();
      }
   }

   private double get_number() {
      double numb = 0.0;
      int a = 0;
      int b = this.STR.length();
      int n = 0;
      boolean tst = true;

      for (int var8 = 0; var8 < b; var8++) {
         if (this.isNumber(this.STR.charAt(var8)) && tst) {
            n = var8;
         } else {
            tst = false;
         }
      }

      numb = Double.valueOf(this.STR.substring(0, n + 1));
      this.STR = this.STR.substring(n + 1);
      return numb;
   }

   private boolean isNumber(char charAt) {
      if (charAt == '1') {
         return true;
      } else if (charAt == '2') {
         return true;
      } else if (charAt == '3') {
         return true;
      } else if (charAt == '4') {
         return true;
      } else if (charAt == '5') {
         return true;
      } else if (charAt == '6') {
         return true;
      } else if (charAt == '7') {
         return true;
      } else if (charAt == '8') {
         return true;
      } else if (charAt == '9') {
         return true;
      } else {
         return charAt == '0' ? true : charAt == '.';
      }
   }

   private double get_opperator_Numb(double value, String type) {
      int in = 0;
      if ("primary".equals(type)) {
         in = 0;
      }

      if ("secondary".equals(type)) {
         in = 1;
      }

      switch (in) {
         case 0:
            if (this.STR.indexOf("+") == 0) {
               this.STR = this.STR.substring(1);
               double num = this.compute_next_Numb();
               return value + num;
            } else if (this.STR.indexOf("-") == 0) {
               this.STR = this.STR.substring(1);
               double num = this.compute_next_Numb();
               return value - num;
            } else if (this.STR.indexOf("*") == 0) {
               this.STR = this.STR.substring(1);
               double num = this.compute_next_Numb();
               return value * num;
            } else if (this.STR.indexOf("/") == 0) {
               this.STR = this.STR.substring(1);
               double num = this.compute_next_Numb();
               return value / num;
            } else {
               if (this.isNumber(this.STR.charAt(0))) {
                  double num = this.compute_next_Numb();
                  return value * num;
               }

               this.status = "comma";
               return value;
            }
         case 1:
            if (this.STR.indexOf("^") == 0) {
               this.STR = this.STR.substring(1);
               double num = this.compute_next_Numb();
               return Math.pow(value, num);
            } else {
               if (this.STR.indexOf("!") != 0) {
                  if (this.STR.indexOf("++") == 0) {
                     this.STR = this.STR.substring(2);
                     return value + 1.0;
                  }

                  if (this.STR.indexOf("--") == 0) {
                     this.STR = this.STR.substring(2);
                     return value - 1.0;
                  }

                  return value;
               }

               this.STR = this.STR.substring(1);
               double num = 1.0;

               for (int i = 1; i <= value; i++) {
                  num *= i;
               }

               return num;
            }
         default:
            return value;
      }
   }

   private int get_sign() {
      if (this.STR.indexOf("-") == 0) {
         this.STR = this.STR.substring(1);
         return -1;
      } else {
         return 1;
      }
   }

   private boolean getBool() {
      boolean val = this.compute_next_Bool();
      this.status = "good";

      while (this.STR.length() > 0 && "good".equals(this.status)) {
         val = this.get_opperator_Bool(val, "primary");
      }

      if (this.STR.indexOf(")") == 0 || this.STR.indexOf(",") == 0) {
         this.STR = this.STR.substring(1);
      }

      return val;
   }

   private boolean compute_next_Bool() {
      boolean sgn = this.get_invtr();
      boolean numb = this.get_function_Bool();
      return sgn ? !numb : numb;
   }

   private boolean get_Bool() {
      if (this.STR.indexOf("true") == 0) {
         this.STR = this.STR.substring(4);
         return true;
      } else if (this.STR.indexOf("false") == 0) {
         this.STR = this.STR.substring(5);
         return true;
      } else {
         return false;
      }
   }

   private boolean get_invtr() {
      if (this.STR.indexOf("!") == 0) {
         this.STR = this.STR.substring(1);
         return true;
      } else {
         return false;
      }
   }

   private boolean get_opperator_Bool(boolean value, String type) {
      int in = 0;
      if ("primary".equals(type)) {
         in = 0;
      }

      if ("secondary".equals(type)) {
         in = 1;
      }

      switch (in) {
         case 0:
            if (this.STR.indexOf("||") == 0) {
               this.STR = this.STR.substring(1);
               boolean num = this.compute_next_Bool();
               return value || num;
            } else if (this.STR.indexOf("&&") != 0) {
               if (this.STR.indexOf("^") == 0) {
                  this.STR = this.STR.substring(1);
                  boolean num = this.compute_next_Bool();
                  return value ^ num;
               } else {
                  this.status = "end";
                  return value;
               }
            } else {
               this.STR = this.STR.substring(1);
               boolean num = this.compute_next_Bool();
               return value && num;
            }
         default:
            return value;
      }
   }

   private boolean get_function_Bool() {
      if (this.STR.indexOf("randBool()") == 0) {
         this.STR = this.STR.substring(9);
         return this.rand.nextBoolean();
      } else if (this.STR.indexOf("numb(") == 0) {
         this.STR = this.STR.substring(5);
         double in1 = this.getNumb();
         return this.getComp(in1);
      } else {
         return this.get_Bool();
      }
   }

   private boolean getComp(double in1) {
      if (this.STR.indexOf("==") == 0) {
         this.STR = this.STR.substring(2);
         double in2 = this.getNumb();
         return in1 == in2;
      } else if (this.STR.indexOf("!=") == 0) {
         this.STR = this.STR.substring(2);
         double in2 = this.getNumb();
         return in1 != in2;
      } else if (this.STR.indexOf(">") == 0) {
         this.STR = this.STR.substring(1);
         double in2 = this.getNumb();
         return in1 == in2;
      } else if (this.STR.indexOf("<") == 0) {
         this.STR = this.STR.substring(1);
         double in2 = this.getNumb();
         return in1 == in2;
      } else if (this.STR.indexOf(">=") == 0) {
         this.STR = this.STR.substring(2);
         double in2 = this.getNumb();
         return in1 == in2;
      } else if (this.STR.indexOf("<=") == 0) {
         this.STR = this.STR.substring(2);
         double in2 = this.getNumb();
         return in1 == in2;
      } else {
         return false;
      }
   }

   private ItemStack[] getArrayItemStack() {
      if (this.STR.indexOf("[") == 0) {
         this.STR = this.STR.substring(1);
      }

      List list = new ArrayList();

      while (this.STR.indexOf("]") != 0) {
         list.add(this.getItemStack());
      }

      this.STR = this.STR.substring(1);
      ItemStack[] arr = new ItemStack[list.size()];

      for (int a = 0; a < arr.length; a++) {
         arr[a] = (ItemStack)list.get(a);
      }

      return arr;
   }

   private ItemStack getItemStack() {
      if (this.STR.indexOf("ItemStack(") == 0) {
         this.STR = this.STR.substring(10);

         Item item = itemByName(this.getString());

         int n = (int)this.getNumb();
         int damage = (int)this.getNumb();
         if (this.STR.indexOf(")") == 0 || this.STR.indexOf(",") == 0) {
            this.STR = this.STR.substring(1);
         }

         return new ItemStack(item, n, damage);
      } else if (this.STR.indexOf("Enchant(") == 0) {
         this.STR = this.STR.substring(8);
         Enchantment enchant = Enchantment.getEnchantmentByID((int)this.getNumb());
         int level = (int)this.getNumb();
         ItemStack itemx = this.getItemStack();
         return this.Enchant(enchant, level, itemx);
      } else if (this.STR.indexOf("ReName(") == 0) {
         this.STR = this.STR.substring(7);
         String name = this.getString();
         ItemStack itemx = this.getItemStack();
         return this.ReName(name, itemx);
      } else {
         return new ItemStack(Blocks.DIRT, 1, 0);
      }
   }

   /**
    * Item by registry name. Water, lava and fire have no item on 1.12 (on 1.7 the lookup silently became dirt): they
    * are carried by their bucket / flint and steel and turned back into the block by {@link #blockFromStack}.
    */
   private static Item itemByName(String name) {
      String n = name.startsWith("minecraft:") ? name.substring(10) : name;
      if (n.equals("water") || n.equals("flowing_water")) return Items.WATER_BUCKET;
      if (n.equals("lava") || n.equals("flowing_lava")) return Items.LAVA_BUCKET;
      if (n.equals("fire")) return Items.FLINT_AND_STEEL;
      Item item = Item.getByNameOrId(name);
      if (item == null || item == Items.AIR) {
         Block b = Block.getBlockFromName(name);
         item = b != null ? Item.getItemFromBlock(b) : null;
      }
      return item == null || item == Items.AIR ? Item.getItemFromBlock(Blocks.DIRT) : item;
   }

   /** Block placed for a block stack of a drop script (see {@link #itemByName}). */
   private Block blockFromStack(ItemStack stack) {
      if (stack.getItem() == Items.WATER_BUCKET) return Blocks.WATER;
      if (stack.getItem() == Items.LAVA_BUCKET) return Blocks.LAVA;
      if (stack.getItem() == Items.FLINT_AND_STEEL) return Blocks.FIRE;
      Block b = Block.getBlockFromItem(stack.getItem());
      return b == Blocks.AIR ? Blocks.DIRT : b;
   }

   private String[] getArrayString() {
      if (this.STR.indexOf("[") == 0) {
         this.STR = this.STR.substring(1);
      }

      List list = new ArrayList();

      while (this.STR.indexOf("]") != 0) {
         list.add(this.getString());
      }

      this.STR = this.STR.substring(1);
      String[] arr = new String[list.size()];

      for (int a = 0; a < arr.length; a++) {
         arr[a] = (String)list.get(a);
      }

      return arr;
   }

   private String getString() {
      String str = this.get_String();
      if (this.STR.indexOf(")") == 0 || this.STR.indexOf(",") == 0) {
         this.STR = this.STR.substring(1);
      }

      return str;
   }

   private String get_String() {
      this.STR = this.STR.substring(1);
      int n = 0;
      boolean ended = false;
      int layer = 0;

      for (int a = 1; a < this.STR.length(); a++) {
         if (!ended) {
            if (this.STR.charAt(a) == '<') {
               layer++;
            }

            if (this.STR.charAt(a) == '>') {
               if (layer == 0) {
                  ended = true;
               } else {
                  layer--;
               }
            }

            n = a;
         }
      }

      String out = this.STR.substring(0, n);
      this.STR = this.STR.substring(n + 1);
      out = out.replaceAll("/\"", "\"");
      out = out.replaceAll("/s", " ");
      out = out.replaceAll("/e", ";");
      System.out.println(out);
      return out;
   }

   private PotionEffect getPotionEffect() {
      if (this.STR.indexOf("PotionEffect(") != 0) {
         return null;
      } else {
         this.STR = this.STR.substring(13);
         Potion pot = Potion.getPotionById((int)this.getNumb());
         int length = (int)this.getNumb();
         int mult = (int)this.getNumb();
         if (this.STR.indexOf(")") == 0 || this.STR.indexOf(",") == 0) {
            this.STR = this.STR.substring(1);
         }

         return pot == null ? null : new PotionEffect(pot, length, mult);
      }
   }

   private EntityPlayer getPlayer() {
      if (this.STR.indexOf("nearestPlayer") != 0) {
         if (this.STR.indexOf("randomPlayer(") == 0) {
            this.STR = this.STR.substring(13);
         } else if (this.STR.indexOf("randomPlayer") == 0) {
            this.STR = this.STR.substring(13);
         }

         return null;
      } else {
         this.STR = this.STR.substring(13);
         if (this.STR.indexOf(")") == 0 || this.STR.indexOf(",") == 0) {
            this.STR = this.STR.substring(1);
         }

         return this.getnearestPlayer();
      }
   }

   private Entity getEntity() {
      if (this.STR.indexOf("EntityName(") == 0) {
         this.STR = this.STR.substring(11);
         String name = this.getString();
         Entity entity = this.getEntity();
         return this.EntityName(name, entity);
      } else if (this.STR.indexOf("EntityPos(") == 0) {
         this.STR = this.STR.substring(10);
         double x = this.getPosX();
         double y = this.getPosY();
         double z = this.getPosZ();
         Entity entity = this.getEntity();
         return this.EntityPos(x, y, z, entity);
      } else if (this.STR.indexOf("Bobify(") == 0) {
         this.STR = this.STR.substring(7);
         int level = (int)this.getNumb();
         Entity entity = this.getEntity();
         return this.Bobify(level, entity);
      } else if (this.STR.indexOf("Entity(") == 0) {
         this.STR = this.STR.substring(7);
         return this.MakeEntity(this.getString());
      } else if (this.STR.indexOf("cLUCK(") == 0) {
         this.STR = this.STR.substring(6);
         return new EntitycLuckey(this.world, this.getString());
      } else if (this.STR.indexOf("cLUCKy(") == 0) {
         this.STR = this.STR.substring(7);
         int lvl = (int)this.getNumb();
         int luck = (int)this.getNumb();
         return new EntitycLuckey(this.world, lvl, luck);
      } else {
         if (this.STR.indexOf("EntityItem(") == 0) {
            this.STR = this.STR.substring(11);
            ItemStack item = this.getItemStack();
            new EntityItem(this.world, this.loc.getX(), this.loc.getY(), this.loc.getZ(), item);
         }

         return null;
      }
   }

   /** 1.7 entity string id ("Zombie", "PigZombie", "LavaSlime", mod ids...) -> new entity at the drop location. */
   private Entity MakeEntity(String str) {
      Entity entity2 = LegacyEntityList.createEntityByName(str, this.world);
      if (entity2 == null) System.out.println("[cLUCKey] unknown entity " + str);
      else this.EntityPos(this.loc, entity2);
      return entity2;
   }

   public void dropRandomCrap(int amount) {
      Random rnd = new com.electrifiedded.archaiclib.util.FastRandom();

      for (int i = 0; i < amount; i++) {
         ItemStack item;
         switch (rnd.nextInt(22)) {
            case 0:
               item = new ItemStack(Blocks.DIRT);
               break;
            case 1:
               item = new ItemStack(Blocks.SAND);
               break;
            case 2:
               item = new ItemStack(Blocks.GRAVEL);
               break;
            case 3:
               item = new ItemStack(Items.MELON);
               break;
            case 4:
               item = new ItemStack(Items.STICK);
               break;
            case 5:
               item = new ItemStack(Items.PAPER);
               break;
            case 6:
               item = new ItemStack(Items.BOAT);
               break;
            case 7:
               item = new ItemStack(Items.POISONOUS_POTATO);
               break;
            case 8:
               item = new ItemStack(Items.CARROT_ON_A_STICK);
               break;
            case 9:
               item = new ItemStack(Items.SPIDER_EYE);
               break;
            case 10:
               item = new ItemStack(Items.ARROW);
               break;
            case 11:
               item = new ItemStack(Items.BOWL);
               break;
            case 12:
               item = new ItemStack(Items.COOKIE);
               break;
            case 13:
               item = new ItemStack(Items.ROTTEN_FLESH);
               break;
            case 14:
               item = new ItemStack(Items.SUGAR);
               break;
            case 15:
               item = new ItemStack(Items.CAKE);
               break;
            case 16:
               item = new ItemStack(Blocks.MOB_SPAWNER);
               break;
            case 17:
               item = new ItemStack(Blocks.MONSTER_EGG, 1, rnd.nextInt(5));
               break;
            case 18:
               item = new ItemStack(Blocks.SPONGE);
               break;
            case 19:
               item = new ItemStack(Blocks.TORCH);
               break;
            case 20:
               item = new ItemStack(Blocks.GRASS);
               break;
            default:
               item = new ItemStack(Items.CAULDRON);
         }

         item.setStackDisplayName("Item Number:" + rnd.nextInt(4000));
         Youtubers.Drop_Item(this.world, this.loc.getX(), this.loc.getY(), this.loc.getZ(), item.copy());
      }
   }

   public void explode(float size, boolean fire) {
      this.world.createExplosion(this.entity, this.loc.getX(), this.loc.getY(), this.loc.getZ(), size, fire);
   }

   public void dropTNT(int n, double speed) {
      if (!this.world.isRemote) {
         for (int i = 0; i < n; i++) {
            EntityTNTPrimed tnt = new EntityTNTPrimed(this.world, this.loc.getX(), this.loc.getY(), this.loc.getZ(), this.entity);
            this.setVelocity(tnt, speed * (this.rand.nextDouble() * 2.0 - 1.0), speed, speed * (this.rand.nextDouble() * 2.0 - 1.0));
            this.world.spawnEntity(tnt);
         }
      }
   }

   public void enderperl(int n, double speed) {
      for (EntityPlayer player : this.getnearPlayers(20.0)) {
         for (int i = 0; i < n; i++) {
            EntityEnderPearl perl = new EntityEnderPearl(this.world, player);
            this.setVelocity(
               perl, speed * (this.rand.nextDouble() * 2.0 - 1.0), speed * this.rand.nextDouble() + 0.5 * speed, speed * (this.rand.nextDouble() * 2.0 - 1.0)
            );
            this.world.spawnEntity(perl);
         }
      }
   }

   public void dropPlayers(int maxdepth, boolean lava, boolean keepinworld) {
      for (EntityPlayer player : this.getnearPlayers(20.0)) {
         System.out.println("Dropping " + player.getDisplayName());
         int y = (int)(player.posY + 1.0);
         boolean flag = false;

         for (int i = 0; i < maxdepth; i++) {
            if (y - i == 0 && keepinworld) {
               flag = true;
            }

            if (y - i == 0 && !keepinworld) {
               for (int a = -1; a <= 1; a++) {
                  for (int b = -1; b <= 1; b++) {
                     LegacyWorld.setBlockToAir(this.world, (int)player.posX + a, y - i, (int)player.posZ + b - 1);
                  }
               }
            } else if (y - i > 0) {
               for (int a = -1; a <= 1; a++) {
                  for (int b = -1; b <= 1; b++) {
                     LegacyWorld.setBlockToAir(this.world, (int)player.posX + a, y - i, (int)player.posZ + b - 1);
                  }
               }
            }
         }

         if (lava) {
            if (flag) {
               for (int a = -1; a <= 1; a++) {
                  for (int b = -1; b <= 1; b++) {
                     LegacyWorld.setBlock(this.world, (int)player.posX + a, 0, (int)player.posZ + b - 1, Blocks.LAVA);
                     LegacyWorld.setBlock(this.world, (int)player.posX + a, 1, (int)player.posZ + b - 1, Blocks.WEB);
                  }
               }
            } else {
               for (int a = -1; a <= 1; a++) {
                  for (int b = -1; b <= 1; b++) {
                     LegacyWorld.setBlock(this.world, (int)player.posX + a, y - maxdepth, (int)player.posZ + b - 1, Blocks.LAVA);
                     LegacyWorld.setBlock(this.world, (int)player.posX + a, y - maxdepth + 1, (int)player.posZ + b - 1, Blocks.WEB);
                  }
               }
            }
         }
      }
   }

   public List<EntityPlayer> getnearPlayers(double d) {
      List<EntityPlayer> list = this.world.playerEntities;
      List<EntityPlayer> players = new ArrayList<>();

      for (EntityPlayer player : list) {
         if (this.entityDist(player, this.loc) < d) {
            players.add(player);
         }
      }

      return players;
   }

   public EntityPlayer getnearestPlayer() {
      List list = this.world.playerEntities;
      Iterator iterator = list.iterator();
      double d = -1.0;
      EntityPlayer Pout = null;

      while (iterator.hasNext()) {
         EntityPlayer player = (EntityPlayer)iterator.next();
         double r = this.entityDist(player, this.loc);
         if (r < d || d < 0.0) {
            d = r;
            Pout = player;
         }
      }

      return Pout;
   }

   public double entityDist(Entity a, Entity b) {
      return Math.sqrt(
         Math.pow(a.posX - b.posX, 2.0) + Math.pow(a.posY - b.posY, 2.0) + Math.pow(a.posZ - b.posZ, 2.0)
      );
   }

   public double entityDist(BlockPos b, Entity a) {
      return this.entityDist(a, b);
   }

   public double entityDist(Entity a, BlockPos b) {
      return Math.sqrt(Math.pow(a.posX - b.getX(), 2.0) + Math.pow(a.posY - b.getY(), 2.0) + Math.pow(a.posZ - b.getZ(), 2.0));
   }

   public void SpawnItem(ItemStack item) {
      Youtubers.Drop_Item(this.world, this.loc.getX(), this.loc.getY(), this.loc.getZ(), item);
      if (item.hasDisplayName()) {
         System.out.println("dropping Item:" + item.getDisplayName());
      } else {
         System.out.println("dropping Item:" + item.getItem().getTranslationKey());
      }
   }

   public ItemStack Enchant(Enchantment enchant, int level, ItemStack item) {
      item.addEnchantment(enchant, level);
      return item;
   }

   public ItemStack ReName(String name, ItemStack item) {
      item.setStackDisplayName(name);
      return item;
   }

   public void dropTower(long delay, ItemStack[] blocks) {
      for (int a = 0; a < blocks.length * 2 + 3; a++) {
         LegacyWorld.setBlockToAir(this.world, this.loc.getX(), this.loc.getY() + a, this.loc.getZ());
      }

      for (int a = 0; a < blocks.length; a++) {
         if (this.blockFromStack(blocks[a]) == Blocks.DRAGON_EGG) {
            LegacyWorld.setBlock(this.world, this.loc.getX(), this.loc.getY() + 2 * blocks.length, this.loc.getZ(), Blocks.DRAGON_EGG);
         } else {
            this.dropfallingblock(
               this.blockFromStack(blocks[a]),
               blocks[a].getMetadata(),
               this.loc.getX() + 0.5,
               this.loc.getY() + 2 * blocks.length,
               this.loc.getZ() + 0.5,
               0.0,
               0.0,
               0.0
            );
         }
      }
   }

   public void tossBlocks(int n, double speed, ItemStack block) {
      for (int i = 0; i < n; i++) {
         this.dropfallingblock(
            this.blockFromStack(block),
            block.getMetadata(),
            this.loc.getX(),
            this.loc.getY() + 0.75,
            this.loc.getZ(),
            speed * (this.rand.nextDouble() * 2.0 - 1.0),
            speed * this.rand.nextDouble() + 0.5 * speed,
            speed * (this.rand.nextDouble() * 2.0 - 1.0)
         );
      }
   }

   public void tossItems(int n, double speed, ItemStack item) {
      for (int i = 0; i < n; i++) {
         EntityItem itemE = new EntityItem(
            this.world, this.loc.getX(), this.loc.getY() + 0.75 + this.rand.nextInt(10), this.loc.getZ(), item.copy()
         );
         this.setVelocity(itemE, this.rand.nextDouble() * 2.0 - 1.0, speed * this.rand.nextDouble() + 0.5 * speed, this.rand.nextDouble() * 2.0 - 1.0);
         itemE.setPickupDelay(100);
         this.world.spawnEntity(itemE);
      }
   }

   public void dropfallingblock(Block block, int meta, double x, double y, double z, double Vx, double Vy, double Vz) {
      System.out.println("[Clucky testing] Dropping a falling block x:" + x + ",y:" + y + ",z:" + z);
      if (!this.world.isRemote) {
         EntityFallingBlock fblock = new EntityFallingBlock(this.world, x + 0.5, y, z + 0.5, LegacyBlockIds.state(block, meta));
         fblock.fallTime = 1;
         this.setVelocity(fblock, Vx, Vy, Vz);
         fblock.setPosition(x + 0.5, y, z + 0.5);
         fblock.preventEntitySpawning = false;
         fblock.fallDistance = 1.0F;
         this.world.spawnEntity(fblock);
      }
   }

   public void setVelocity(Entity entity, double vx, double vy, double vz) {
      entity.motionX = vx;
      entity.motionY = vy;
      entity.motionZ = vz;
   }

   public void placeSign(int x, int y, int z, String[] text) {
      LegacyWorld.setBlock(this.world, x, y, z, Blocks.STANDING_SIGN);
      TileEntitySign te = (TileEntitySign)LegacyWorld.getTileEntity(this.world, x, y, z);

      if (te == null) return;
      for (int a = 0; a < text.length && a < te.signText.length; a++) {
         te.signText[a] = new net.minecraft.util.text.TextComponentString(text[a]);
      }
   }

   public void SpawnEntity(Entity entity) {
      this.world.spawnEntity(entity);
   }

   public Entity EntityName(String name, Entity entity) {
      if (entity instanceof EntityLiving) {
         ((EntityLiving)entity).setCustomNameTag(name);
         ((EntityLiving)entity).setAlwaysRenderNameTag(true);
      }

      return entity;
   }

   public Entity EntityPos(double x, double y, double z, Entity entity) {
      entity.setPosition(x, y, z);
      return entity;
   }

   public Entity EntityPos(BlockPos pos, Entity entity) {
      entity.setPosition(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
      return entity;
   }

   public void Tower(ItemStack block, int[][] cluckies) {
      for (int a = -2; a < 3; a++) {
         for (int b = -2; b < 3; b++) {
            LegacyWorld.setBlock(this.world, this.loc.getX() + a, this.loc.getY() - 1, this.loc.getZ() + b, Blocks.SANDSTONE);
            if (a != -2 && a != 2 && b != -2 && b != 2) {
               LegacyWorld.setBlockToAir(this.world, this.loc.getX() + a, this.loc.getY(), this.loc.getZ() + b);
               LegacyWorld.setBlockToAir(this.world, this.loc.getX() + a, this.loc.getY(), this.loc.getZ() + b);
            } else {
               LegacyWorld.setBlock(this.world, this.loc.getX() + a, this.loc.getY(), this.loc.getZ() + b, Blocks.SANDSTONE);
               LegacyWorld.setBlock(this.world, this.loc.getX() + a, this.loc.getY() + 1, this.loc.getZ() + b, Blocks.SANDSTONE);
            }

            if ((a == -2 || a == 2) && (b == -2 || b == 2)) {
               LegacyWorld.setBlock(this.world, 
                     this.loc.getX() + a, this.loc.getY() + 1, this.loc.getZ() + b, this.blockFromStack(block), block.getMetadata(), 3
                  );
            }
         }
      }

      for (int a = 0; a < cluckies.length; a++) {
         this.SpawnEntity(this.EntityPos(this.loc.getX(), this.loc.getY(), this.loc.getZ(), new EntitycLuckey(this.world, cluckies[a][0], cluckies[a][1])));
      }
   }

   public void makeItRain(int n) {
      double speed = 2.0;

      for (int i = 0; i < n; i++) {
         EntityItem item = new EntityItem(
            this.world, this.loc.getX(), this.loc.getY() + 0.75 + this.rand.nextInt(10), this.loc.getZ(), new ItemStack(YTItems.Money[0])
         );
         this.setVelocity(item, this.rand.nextDouble() * 2.0 - 1.0, speed * this.rand.nextDouble() + 0.5 * speed, this.rand.nextDouble() * 2.0 - 1.0);
         item.setPickupDelay(100);
         this.world.spawnEntity(item);
      }
   }

   public void TPplayers(double x, double y, double z, EntityPlayer player) {
      player.setPositionAndUpdate(x, y, z);
   }

   public void TPup(int h) {
      for (EntityPlayer player : this.getnearPlayers(20.0)) {
         if (player.posY + h > 1.0) {
            this.TPplayers(player.posX, player.posY + h, player.posZ, player);
         } else {
            this.TPplayers(player.posX, 1.5, player.posZ, player);
         }
      }
   }

   public void wisper(String text, double range) {
      List<EntityPlayer> players;
      if (range == -1.0) {
         players = this.world.playerEntities;
      } else {
         players = this.getnearPlayers(range);
      }

      for (EntityPlayer player : players) {
         this.wisperP(player, text);
      }
   }

   public void wisperP(EntityPlayer player, String text) {
      if (player != null) {
         text = text.replace("@p", player.getName());
         player.sendMessage(new TextComponentString(text));
      }
   }

   public void dropInventory() {
      for (EntityPlayer player : this.getnearPlayers(20.0)) {
         player.inventory.dropAllItems();
      }
   }

   public void DrownTrap() {
      for (int i = -1; i < 2; i++) {
         for (int j = -1; j < 3; j++) {
            for (int k = -1; k < 2; k++) {
               Block block = Blocks.OBSIDIAN;
               if (j == 1) {
                  block = Blocks.GLASS;
               }

               LegacyWorld.setBlock(this.world, this.loc.getX() + i, this.loc.getY() + j, this.loc.getZ() + k, block);
            }
         }
      }

      LegacyWorld.setBlock(this.world, this.loc.getX(), this.loc.getY(), this.loc.getZ(), Blocks.WATER);
      LegacyWorld.setBlock(this.world, this.loc.getX(), this.loc.getY() + 1, this.loc.getZ(), Blocks.WATER);

      for (EntityPlayer player : this.getnearPlayers(20.0)) {
         this.TPplayers(this.loc.getX() + 0.5, this.loc.getY(), this.loc.getZ() + 0.5, player);
         switch (this.rand.nextInt(19)) {
            case 0:
               this.wisperP(player, "No Daver coopefielding");
               break;
            case 1:
               this.wisperP(player, "dAvarCopfiald dissabeld");
               break;
            case 2:
               this.wisperP(player, "Dav Qwoperfled unAvilable");
               break;
            case 3:
               this.wisperP(player, "witnass The lAck Of coAparFailding");
               break;
            case 4:
               this.wisperP(player, "Die, @p, DIE!");
               break;
            case 5:
               this.wisperP(player, "yOur CaopErfliding skIls Seam lAkiNg");
               break;
            case 6:
               this.wisperP(player, "No dAverrr Cooooopfled");
               break;
            case 7:
               this.wisperP(player, "YOu CaNt CopprfLied YOr Whey OuT Of dis");
               break;
            case 8:
               this.wisperP(player, "OOOOOOhhhh! YoU Think you can escape. How Cute.");
               break;
            case 9:
               this.wisperP(player, "This test is about spending extended time underwater without a breating apperatus. Arn't you GLaD");
               break;
            case 10:
               this.wisperP(player, "duVir Cuipfeld NoNFUnktoining");
               break;
            case 11:
               this.wisperP(player, "ERROR 404:Bad Joke about PopularMMO's Daver Cooperfielding drowning traps not found");
               break;
            case 12:
               this.wisperP(player, "Have a nice day!");
               break;
            case 13:
               this.wisperP(player, "&#8593&#8593&#8595&#8595&#8592&#8594&#8592&#8594BA");
               break;
            case 14:
               this.wisperP(player, "Their is no way to Dover coopfled this time");
               break;
            case 15:
               this.wisperP(player, "Noo Coyparfyleding");
               break;
            case 16:
               this.wisperP(player, "@p no Copperfielding");
               break;
            case 17:
               this.wisperP(player, "@p! Escape this.");
               break;
            case 18:
               this.wisperP(player, "Enderperls are nice to have in this situation.");
         }
      }
   }

   public void setTime(long time) {
      this.world.setWorldTime(time);
   }

   public void spawnHord(int number, int spread, Entity entity) {
      int spawned = 0;
      int attempts = 0;

      while (spawned < number && attempts < 100 * number) {
         attempts++;
         int xoff = this.rand.nextInt(2 * spread) - spread;
         int zoff = this.rand.nextInt(2 * spread) - spread;
         int yoff = this.rand.nextInt(4 + spread / 4) - 2 - spread / 8;

         while (!LegacyWorld.doesBlockHaveSolidTopSurface(this.world, this.loc.getX() + xoff, this.loc.getY() + yoff - 1, this.loc.getZ() + zoff) && this.loc.getY() + yoff > 0) {
            yoff--;
         }

         while (!LegacyWorld.isAirBlock(this.world, this.loc.getX() + xoff, this.loc.getY() + yoff, this.loc.getZ() + zoff) && this.loc.getY() + yoff < 255) {
            yoff++;
         }

         try {
            Entity entout = (Entity)entity.getClass().getConstructor(World.class).newInstance(this.world);
            LegacyEntityHelper.copyDataFrom(entout, entity);
            entout = this.EntityPos(this.loc.getX() + xoff, this.loc.getY() + yoff, this.loc.getZ() + zoff, entout);
            this.SpawnEntity(entout);
            spawned++;
         } catch (InstantiationException var10) {
            var10.printStackTrace();
         } catch (IllegalAccessException var11) {
            var11.printStackTrace();
         } catch (IllegalArgumentException var12) {
            var12.printStackTrace();
         } catch (InvocationTargetException var13) {
            var13.printStackTrace();
         } catch (NoSuchMethodException var14) {
            var14.printStackTrace();
         } catch (SecurityException var15) {
            var15.printStackTrace();
         }
      }
   }

   public void spreadItems(int number, int spread, ItemStack item) {
      int spawned = 0;

      for (int attempts = 0; spawned < number && attempts < 100 * number; spawned++) {
         attempts++;
         int xoff = this.rand.nextInt(2 * spread) - spread;
         int zoff = this.rand.nextInt(2 * spread) - spread;
         int yoff = this.rand.nextInt(4 + spread / 4) - 2 - spread / 8;

         while (!LegacyWorld.doesBlockHaveSolidTopSurface(this.world, this.loc.getX() + xoff, this.loc.getY() + yoff - 1, this.loc.getZ() + zoff) && this.loc.getY() + yoff > 0) {
            yoff--;
         }

         while (!LegacyWorld.isAirBlock(this.world, this.loc.getX() + xoff, this.loc.getY() + yoff, this.loc.getZ() + zoff) && this.loc.getY() + yoff < 255) {
            yoff++;
         }

         Entity entout = new EntityItem(this.world, this.loc.getX(), this.loc.getY(), this.loc.getZ(), item.copy());
         entout = this.EntityPos(this.loc.getX() + xoff, this.loc.getY() + yoff, this.loc.getZ() + zoff, entout);
         this.SpawnEntity(entout);
      }
   }

   public Entity Bobify(int level, Entity entity) {
      if (entity instanceof EntityLiving) {
         ItemStack Sword = new ItemStack(Items.DIAMOND_SWORD);
         ItemStack Boots = new ItemStack(Items.DIAMOND_BOOTS);
         ItemStack Legs = new ItemStack(Items.DIAMOND_LEGGINGS);
         ItemStack Chest = new ItemStack(Items.DIAMOND_CHESTPLATE);
         ItemStack Head = new ItemStack(Items.DIAMOND_HELMET);
         switch (level) {
            case 3:
               Sword = this.Enchant(Enchantments.SHARPNESS, 15, Sword);
               Sword = this.Enchant(Enchantments.BANE_OF_ARTHROPODS, 15, Sword);
               Sword = this.Enchant(Enchantments.SMITE, 15, Sword);
               Sword = this.Enchant(Enchantments.LOOTING, 11, Sword);
               Sword = this.Enchant(Enchantments.FIRE_ASPECT, 6, Sword);
               Sword = this.Enchant(Enchantments.UNBREAKING, 50, Sword);
               Head = this.Enchant(Enchantments.PROTECTION, 10, Head);
               Head = this.Enchant(Enchantments.FIRE_PROTECTION, 8, Head);
               Head = this.Enchant(Enchantments.BLAST_PROTECTION, 15, Head);
               Head = this.Enchant(Enchantments.PROJECTILE_PROTECTION, 5, Head);
               Head = this.Enchant(Enchantments.THORNS, 20, Head);
               Head = this.Enchant(Enchantments.UNBREAKING, 50, Head);
               Head = this.Enchant(Enchantments.RESPIRATION, 18, Head);
               Head = this.Enchant(Enchantments.AQUA_AFFINITY, 10, Head);
               Chest = this.Enchant(Enchantments.PROTECTION, 10, Chest);
               Chest = this.Enchant(Enchantments.FIRE_PROTECTION, 8, Chest);
               Chest = this.Enchant(Enchantments.BLAST_PROTECTION, 15, Chest);
               Chest = this.Enchant(Enchantments.PROJECTILE_PROTECTION, 5, Chest);
               Chest = this.Enchant(Enchantments.THORNS, 20, Chest);
               Chest = this.Enchant(Enchantments.UNBREAKING, 50, Chest);
               Legs = this.Enchant(Enchantments.PROTECTION, 10, Legs);
               Legs = this.Enchant(Enchantments.FIRE_PROTECTION, 8, Legs);
               Legs = this.Enchant(Enchantments.BLAST_PROTECTION, 15, Legs);
               Legs = this.Enchant(Enchantments.PROJECTILE_PROTECTION, 5, Legs);
               Legs = this.Enchant(Enchantments.THORNS, 20, Legs);
               Legs = this.Enchant(Enchantments.UNBREAKING, 50, Legs);
               Boots = this.Enchant(Enchantments.PROTECTION, 10, Boots);
               Boots = this.Enchant(Enchantments.FIRE_PROTECTION, 8, Boots);
               Boots = this.Enchant(Enchantments.BLAST_PROTECTION, 15, Boots);
               Boots = this.Enchant(Enchantments.PROJECTILE_PROTECTION, 5, Boots);
               Boots = this.Enchant(Enchantments.THORNS, 20, Boots);
               Boots = this.Enchant(Enchantments.UNBREAKING, 50, Boots);
            case 10:
               Sword = this.Enchant(Enchantments.SHARPNESS, 100, Sword);
               Sword = this.Enchant(Enchantments.BANE_OF_ARTHROPODS, 100, Sword);
               Sword = this.Enchant(Enchantments.SMITE, 100, Sword);
               if (YTConfig.OPness) {
                  Sword = this.Enchant(Enchantments.LOOTING, 100, Sword);
               } else {
                  Sword = this.Enchant(Enchantments.LOOTING, 11, Sword);
               }

               Sword = this.Enchant(Enchantments.FIRE_ASPECT, 100, Sword);
               Sword = this.Enchant(Enchantments.UNBREAKING, 100, Sword);
               Head = this.Enchant(Enchantments.PROTECTION, 100, Head);
               Head = this.Enchant(Enchantments.FIRE_PROTECTION, 100, Head);
               Head = this.Enchant(Enchantments.BLAST_PROTECTION, 100, Head);
               Head = this.Enchant(Enchantments.PROJECTILE_PROTECTION, 100, Head);
               Head = this.Enchant(Enchantments.THORNS, 100, Head);
               Head = this.Enchant(Enchantments.UNBREAKING, 100, Head);
               Head = this.Enchant(Enchantments.RESPIRATION, 100, Head);
               Head = this.Enchant(Enchantments.AQUA_AFFINITY, 100, Head);
               Chest = this.Enchant(Enchantments.PROTECTION, 100, Chest);
               Chest = this.Enchant(Enchantments.FIRE_PROTECTION, 100, Chest);
               Chest = this.Enchant(Enchantments.BLAST_PROTECTION, 100, Chest);
               Chest = this.Enchant(Enchantments.PROJECTILE_PROTECTION, 100, Chest);
               Chest = this.Enchant(Enchantments.THORNS, 100, Chest);
               Chest = this.Enchant(Enchantments.UNBREAKING, 100, Chest);
               Legs = this.Enchant(Enchantments.PROTECTION, 100, Legs);
               Legs = this.Enchant(Enchantments.FIRE_PROTECTION, 100, Legs);
               Legs = this.Enchant(Enchantments.BLAST_PROTECTION, 100, Legs);
               Legs = this.Enchant(Enchantments.PROJECTILE_PROTECTION, 100, Legs);
               Legs = this.Enchant(Enchantments.THORNS, 100, Legs);
               Legs = this.Enchant(Enchantments.UNBREAKING, 100, Legs);
               Boots = this.Enchant(Enchantments.PROTECTION, 100, Boots);
               Boots = this.Enchant(Enchantments.FIRE_PROTECTION, 100, Boots);
               Boots = this.Enchant(Enchantments.BLAST_PROTECTION, 100, Boots);
               Boots = this.Enchant(Enchantments.PROJECTILE_PROTECTION, 100, Boots);
               Boots = this.Enchant(Enchantments.THORNS, 100, Boots);
               Boots = this.Enchant(Enchantments.UNBREAKING, 100, Boots);
            default:
               LegacyEquipment.set((EntityLiving)entity, 0, Sword);
               LegacyEquipment.set((EntityLiving)entity, 1, Boots);
               LegacyEquipment.set((EntityLiving)entity, 2, Legs);
               LegacyEquipment.set((EntityLiving)entity, 3, Chest);
               LegacyEquipment.set((EntityLiving)entity, 4, Head);
         }
      }

      return entity;
   }

   public void addPotionEfect(PotionEffect effect, int range) {
      for (EntityPlayer player : this.getnearPlayers(range)) {
         player.addPotionEffect(effect);
      }
   }

   public void addPotionEfectP(PotionEffect effect, EntityPlayer player) {
      if (player != null && effect != null) {
         player.addPotionEffect(effect);
      }
   }

   public void SpawnAllMonsters(int limit, int copies, int bob) {
      List list = this.world.loadedEntityList;
      Iterator iterator = list.iterator();
      List list2 = new ArrayList();

      while (iterator.hasNext()) {
         Entity entity = (Entity)iterator.next();
         if (entity instanceof EntityMob) {
            list2.add(entity);
         }
      }

      Iterator iterator2 = list2.iterator();
      int test = 0;

      while (iterator2.hasNext()) {
         Entity entity = (Entity)iterator2.next();
         if (test < limit) {
            if (bob != -1) {
               this.spawnHord(copies, 15, entity);
            } else {
               this.spawnHord(copies, 15, this.Bobify(bob, entity));
            }
         }
      }
   }

   public void tossEggs(int n, double speed, Entity entity) {
      for (int i = 0; i < n; i++) {
         try {
            Entity entout;
            if (entity instanceof EntityItem) {
               entout = (Entity)entity.getClass()
                  .getConstructor(World.class, double.class, double.class, double.class, ItemStack.class)
                  .newInstance(this.world, this.loc.getX(), this.loc.getY(), this.loc.getZ(), ((EntityItem)entity).getItem().copy());
            } else {
               entout = (Entity)entity.getClass().getConstructor(World.class).newInstance(this.world);
            }

            if (entout != null) {
               LegacyEntityHelper.copyDataFrom(entout, entity);
               double a = this.rand.nextDouble() * 2.0 - 1.0;
               double b = this.rand.nextDouble() * 2.0 - 1.0;
               this.dropEgg(
                  this.loc.getX() + a / 2.0,
                  (double)(this.loc.getY() + 1),
                  this.loc.getZ() + b / 2.0,
                  speed * a / 2.0,
                  speed * this.rand.nextDouble() + 0.5 * speed,
                  speed * b / 2.0,
                  entout
               );
            }
         } catch (InstantiationException var11) {
            var11.printStackTrace();
         } catch (IllegalAccessException var12) {
            var12.printStackTrace();
         } catch (IllegalArgumentException var13) {
            var13.printStackTrace();
         } catch (InvocationTargetException var14) {
            var14.printStackTrace();
         } catch (NoSuchMethodException var15) {
            var15.printStackTrace();
         } catch (SecurityException var16) {
            var16.printStackTrace();
         }
      }
   }

   public void tossEggs(int n, double speed, ItemStack item) {
      for (int i = 0; i < n; i++) {
         EntityItem entout = new EntityItem(this.world, this.loc.getX(), this.loc.getY(), this.loc.getZ(), item.copy());
         double a = this.rand.nextDouble() * 2.0 - 1.0;
         double b = this.rand.nextDouble() * 2.0 - 1.0;
         this.dropEgg(
            this.loc.getX() + a / 2.0,
            (double)(this.loc.getY() + 1),
            this.loc.getZ() + b / 2.0,
            speed * a / 2.0,
            speed * this.rand.nextDouble() + 0.5 * speed,
            speed * b / 2.0,
            entout
         );
      }
   }

   public void dropEgg(double x, double y, double z, double Vx, double Vy, double Vz, Entity entity) {
      if (!this.world.isRemote) {
         EntityCrazyEgg egg = new EntityCrazyEgg(this.world, entity);
         this.setVelocity(egg, Vx, Vy, Vz);
         egg.setPosition(x, y, z);
         this.world.spawnEntity(egg);
      }
   }

   public void tossEggs(int n, double speed, String str) {
      for (int i = 0; i < n; i++) {
         double a = this.rand.nextDouble() * 2.0 - 1.0;
         double b = this.rand.nextDouble() * 2.0 - 1.0;
         this.dropEgg(
            this.loc.getX() + a / 2.0,
            (double)(this.loc.getY() + 1),
            this.loc.getZ() + b / 2.0,
            speed * a / 2.0,
            speed * this.rand.nextDouble() + 0.5 * speed,
            speed * b / 2.0,
            str
         );
      }
   }

   public void dropEgg(double x, double y, double z, double Vx, double Vy, double Vz, String str) {
      if (!this.world.isRemote) {
         EventEgg egg = new EventEgg(this.world, str);
         this.setVelocity(egg, Vx, Vy, Vz);
         egg.setPosition(x, y, z);
         this.world.spawnEntity(egg);
      }
   }

   public void Well(ItemStack blockin, String cluck) {
      this.setBlockState(this.world, this.loc.add(0, 0, 0), Blocks.WATER, 0);
      this.setBlockState(this.world, this.loc.add(1, 0, 1), Blocks.COBBLESTONE, 0);
      this.setBlockState(this.world, this.loc.add(1, 0, 0), Blocks.COBBLESTONE, 0);
      this.setBlockState(this.world, this.loc.add(1, 0, -1), Blocks.COBBLESTONE, 0);
      this.setBlockState(this.world, this.loc.add(0, 0, 1), Blocks.COBBLESTONE, 0);
      this.setBlockState(this.world, this.loc.add(0, 0, -1), Blocks.COBBLESTONE, 0);
      this.setBlockState(this.world, this.loc.add(-1, 0, 1), Blocks.COBBLESTONE, 0);
      this.setBlockState(this.world, this.loc.add(-1, 0, 0), Blocks.COBBLESTONE, 0);
      this.setBlockState(this.world, this.loc.add(-1, 0, -1), Blocks.COBBLESTONE, 0);
      this.setBlockState(this.world, this.loc.add(0, -1, 0), Blocks.COBBLESTONE, 0);
      this.setBlockState(this.world, this.loc.add(1, -1, 1), Blocks.COBBLESTONE, 0);
      this.setBlockState(this.world, this.loc.add(1, -1, 0), Blocks.COBBLESTONE, 0);
      this.setBlockState(this.world, this.loc.add(1, -1, -1), Blocks.COBBLESTONE, 0);
      this.setBlockState(this.world, this.loc.add(0, -1, 1), Blocks.COBBLESTONE, 0);
      this.setBlockState(this.world, this.loc.add(0, -1, -1), Blocks.COBBLESTONE, 0);
      this.setBlockState(this.world, this.loc.add(-1, -1, 1), Blocks.COBBLESTONE, 0);
      this.setBlockState(this.world, this.loc.add(-1, -1, 0), Blocks.COBBLESTONE, 0);
      this.setBlockState(this.world, this.loc.add(-1, -1, -1), Blocks.COBBLESTONE, 0);
      Block block = this.blockFromStack(blockin);
      this.setBlockState(this.world, this.loc.add(0, -2, 0), block, 0);
      this.setBlockState(this.world, this.loc.add(1, -2, 1), block, 0);
      this.setBlockState(this.world, this.loc.add(1, -2, 0), block, 0);
      this.setBlockState(this.world, this.loc.add(1, -2, -1), block, 0);
      this.setBlockState(this.world, this.loc.add(0, -2, 1), block, 0);
      this.setBlockState(this.world, this.loc.add(0, -2, -1), block, 0);
      this.setBlockState(this.world, this.loc.add(-1, -2, 1), block, 0);
      this.setBlockState(this.world, this.loc.add(-1, -2, 0), block, 0);
      this.setBlockState(this.world, this.loc.add(-1, -2, -1), block, 0);
      this.setBlockState(this.world, this.loc.add(1, 1, 1), Blocks.COBBLESTONE_WALL, 0);
      this.setBlockState(this.world, this.loc.add(1, 1, -1), Blocks.COBBLESTONE_WALL, 0);
      this.setBlockState(this.world, this.loc.add(-1, 1, 1), Blocks.COBBLESTONE_WALL, 0);
      this.setBlockState(this.world, this.loc.add(-1, 1, -1), Blocks.COBBLESTONE_WALL, 0);
      this.setBlockState(this.world, this.loc.add(1, 2, 1), Blocks.COBBLESTONE_WALL, 0);
      this.setBlockState(this.world, this.loc.add(1, 2, -1), Blocks.COBBLESTONE_WALL, 0);
      this.setBlockState(this.world, this.loc.add(-1, 2, 1), Blocks.COBBLESTONE_WALL, 0);
      this.setBlockState(this.world, this.loc.add(-1, 2, -1), Blocks.COBBLESTONE_WALL, 0);
      this.setBlockState(this.world, this.loc.add(1, 3, 1), Blocks.STONE_SLAB, 0);
      this.setBlockState(this.world, this.loc.add(1, 3, -1), Blocks.STONE_SLAB, 0);
      this.setBlockState(this.world, this.loc.add(-1, 3, 1), Blocks.STONE_SLAB, 0);
      this.setBlockState(this.world, this.loc.add(-1, 3, -1), Blocks.STONE_SLAB, 0);
      this.setBlockState(this.world, this.loc.add(1, 3, 0), Blocks.STONE_SLAB, 8);
      this.setBlockState(this.world, this.loc.add(0, 3, 1), Blocks.STONE_SLAB, 8);
      this.setBlockState(this.world, this.loc.add(0, 3, -1), Blocks.STONE_SLAB, 8);
      this.setBlockState(this.world, this.loc.add(-1, 3, 0), Blocks.STONE_SLAB, 8);
      this.setBlockState(this.world, this.loc.add(0, 3, 0), Blocks.GLASS, 0);
      String blk = "setBlock(ItemStack(<youtubers:wetBeacon>,1,0)," + this.loc.getX() + "," + (this.loc.getY() - 1) + "," + this.loc.getZ() + ");";
      String lft = "setLoc(" + this.loc.getX() + "," + (this.loc.getY() + 7) + "," + this.loc.getZ() + ");";
      this.SpawnEntity(this.EntityPos(this.loc, new EntitycLuckey(this.world, blk + lft + cluck)));
   }

   public void setBlockState(World world, BlockPos pos, Block block, int meta) {
      LegacyWorld.setBlock(world, pos.getX(), pos.getY(), pos.getZ(), block, meta, 3);
   }
}
