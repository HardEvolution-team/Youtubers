package com.electrifiedded.youtubers.WorldGen;

import com.electrifiedded.archaiclib.world.StagedWorld;
import com.electrifiedded.youtubers.YTItems;
import net.minecraft.util.math.BlockPos;
import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.world.World;

public class GenDrunkHouse extends GenYoutuberStructure {
   @Override
   public boolean generate(World world, Random random, BlockPos pos) {
      int i = pos.getX();
      int j = pos.getY();
      int k = pos.getZ();
      this.genBox(world, Blocks.PLANKS, 0, 0, i - 3, j - 1, k - 3, i + 3, j + 3, k + 3);
      this.genBoxAir(world, 0, i - 2, j, k - 2, i + 2, j + 2, k + 2);
      this.genBox(world, Blocks.PLANKS, 0, 0, i - 3, j - 1 + 4, k - 3, i + 3, j + 3 + 4, k + 3);
      this.genBoxAir(world, 0, i - 2, j + 4, k - 2, i + 2, j + 6, k + 2);
      this.setBlockState(world, new BlockPos(i + 2, j + 1, k - 3), Blocks.GLASS, 0);
      this.setBlockState(world, new BlockPos(i - 2, j + 1, k - 3), Blocks.GLASS, 0);
      this.setBlockState(world, new BlockPos(i + 3, j + 1, k - 1), Blocks.GLASS, 0);
      this.setBlockState(world, new BlockPos(i + 3, j + 1, k), Blocks.GLASS, 0);
      this.setBlockState(world, new BlockPos(i + 3, j + 1, k + 1), Blocks.GLASS, 0);
      this.setBlockState(world, new BlockPos(i - 3, j + 1, k - 1), Blocks.GLASS, 0);
      this.setBlockState(world, new BlockPos(i - 3, j + 1, k), Blocks.GLASS, 0);
      this.setBlockState(world, new BlockPos(i - 3, j + 1, k + 1), Blocks.GLASS, 0);
      this.setBlockState(world, new BlockPos(i + 1, j + 5, k - 3), Blocks.GLASS, 0);
      this.setBlockState(world, new BlockPos(i, j + 5, k - 3), Blocks.GLASS, 0);
      this.setBlockState(world, new BlockPos(i - 1, j + 5, k - 3), Blocks.GLASS, 0);
      this.setBlockState(world, new BlockPos(i + 3, j + 5, k - 1), Blocks.GLASS, 0);
      this.setBlockState(world, new BlockPos(i + 3, j + 5, k), Blocks.GLASS, 0);
      this.setBlockState(world, new BlockPos(i + 3, j + 5, k + 1), Blocks.GLASS, 0);
      this.setBlockState(world, new BlockPos(i - 3, j + 5, k - 1), Blocks.GLASS, 0);
      this.setBlockState(world, new BlockPos(i - 3, j + 5, k), Blocks.GLASS, 0);
      this.setBlockState(world, new BlockPos(i - 3, j + 5, k + 1), Blocks.GLASS, 0);

      for (int a = -1; a <= 3; a++) {
         this.setBlockState(world, new BlockPos(i - 3, j + a, k - 3), Blocks.LOG, 0);
         this.setBlockState(world, new BlockPos(i - 3, j + a, k + 3), Blocks.LOG, 0);
         this.setBlockState(world, new BlockPos(i + 3, j + a, k - 3), Blocks.LOG, 0);
         this.setBlockState(world, new BlockPos(i + 3, j + a, k + 3), Blocks.LOG, 0);
      }

      for (int a = -1; a <= 3; a++) {
         this.setBlockState(world, new BlockPos(i - 3, j + a + 4, k - 3), Blocks.LOG, 0);
         this.setBlockState(world, new BlockPos(i - 3, j + a + 4, k + 3), Blocks.LOG, 0);
         this.setBlockState(world, new BlockPos(i + 3, j + a + 4, k - 3), Blocks.LOG, 0);
         this.setBlockState(world, new BlockPos(i + 3, j + a + 4, k + 3), Blocks.LOG, 0);
      }

      for (int a = -2; a < 3; a++) {
         this.setBlockState(world, new BlockPos(i - 3, j + 3, k + a), Blocks.LOG, 8);
         this.setBlockState(world, new BlockPos(i + 3, j + 3, k + a), Blocks.LOG, 8);
         this.setBlockState(world, new BlockPos(i + a, j + 3, k - 3), Blocks.LOG, 4);
         this.setBlockState(world, new BlockPos(i + a, j + 3, k + 3), Blocks.LOG, 4);
      }

      for (int a = -2; a < 3; a++) {
         this.setBlockState(world, new BlockPos(i - 3, j + 7, k + a), Blocks.LOG, 8);
         this.setBlockState(world, new BlockPos(i + 3, j + 7, k + a), Blocks.LOG, 8);
         this.setBlockState(world, new BlockPos(i + a, j + 7, k - 3), Blocks.LOG, 4);
         this.setBlockState(world, new BlockPos(i + a, j + 7, k + 3), Blocks.LOG, 4);
      }

      this.setBlockState(world, new BlockPos(i, j + 4, k + 2), Blocks.BED, 8);
      this.setBlockState(world, new BlockPos(i, j + 4, k + 1), Blocks.BED, 0);
      this.setBlockState(world, new BlockPos(i + 1, j + 4, k + 2), Blocks.BED, 8);
      this.setBlockState(world, new BlockPos(i + 1, j + 4, k + 1), Blocks.BED, 0);
      this.setBlockState(world, new BlockPos(i + 2, j + 4, k + 2), Blocks.BED, 8);
      this.setBlockState(world, new BlockPos(i + 2, j + 4, k + 1), Blocks.BED, 0);
      this.setBlockState(world, new BlockPos(i, j, k - 3), Blocks.OAK_DOOR, 1);
      this.setBlockState(world, new BlockPos(i, j + 1, k - 3), Blocks.OAK_DOOR, 9);
      this.setBlockToAir(world, new BlockPos(i - 2, j + 3, k - 1));
      this.setBlockToAir(world, new BlockPos(i - 2, j + 3, k));
      this.setBlockToAir(world, new BlockPos(i - 2, j + 3, k + 1));
      this.setBlockToAir(world, new BlockPos(i - 2, j + 3, k + 2));
      this.setBlockState(world, new BlockPos(i - 2, j, k - 1), Blocks.OAK_STAIRS, 2);
      this.setBlockState(world, new BlockPos(i - 2, j + 1, k), Blocks.OAK_STAIRS, 2);
      this.setBlockState(world, new BlockPos(i - 2, j + 2, k + 1), Blocks.OAK_STAIRS, 2);
      this.setBlockState(world, new BlockPos(i - 2, j + 3, k + 2), Blocks.OAK_STAIRS, 2);
      this.setBlockState(world, new BlockPos(i - 2, j, k), Blocks.OAK_STAIRS, 5);
      this.setBlockState(world, new BlockPos(i - 2, j + 1, k + 1), Blocks.OAK_STAIRS, 5);
      this.setBlockState(world, new BlockPos(i - 2, j + 2, k + 2), Blocks.OAK_STAIRS, 5);
      this.setBlockState(world, new BlockPos(i + 2, j, k), Blocks.OAK_STAIRS, 6);
      this.setBlockState(world, new BlockPos(i + 1, j, k), Blocks.OAK_STAIRS, 6);
      this.setBlockState(world, new BlockPos(i, j, k), Blocks.OAK_STAIRS, 6);
      this.setBlockState(world, new BlockPos(i - 1, j, k), Blocks.OAK_STAIRS, 6);
      this.addChest(world, random, i - 2, j, k + 2);
      this.addChest(world, random, i - 2, j, k + 1);
      this.setBlockState(world, new BlockPos(i + 2, j, k + 2), Blocks.CRAFTING_TABLE, 0);
      this.setBlockState(world, new BlockPos(i + 2, j, k + 1), Blocks.FURNACE, 0);
      this.setBlockState(world, new BlockPos(i + 2, j + 2, k), Blocks.TORCH, 2);
      this.setBlockState(world, new BlockPos(i, j + 2, k + 2), Blocks.TORCH, 4);
      this.setBlockState(world, new BlockPos(i - 2, j + 2, k), Blocks.TORCH, 1);
      this.setBlockState(world, new BlockPos(i, j + 2, k - 2), Blocks.TORCH, 3);
      this.setBlockState(world, new BlockPos(i + 2, j + 6, k), Blocks.TORCH, 2);
      this.setBlockState(world, new BlockPos(i, j + 6, k + 2), Blocks.TORCH, 4);
      this.setBlockState(world, new BlockPos(i - 2, j + 6, k), Blocks.TORCH, 1);
      this.setBlockState(world, new BlockPos(i, j + 6, k - 2), Blocks.TORCH, 3);
      return false;
   }

   private void addChest(World world, Random random, int i, int j, int k) {
      this.setBlockState(world, new BlockPos(i, j, k), Blocks.CHEST, 0);
      TileEntityChest chestTE = (TileEntityChest)StagedWorld.getTileEntity(world, i, j, k);
      if (chestTE != null) {
         int numb = random.nextInt(10);

         for (int a = 0; a < numb; a++) {
            chestTE.setInventorySlotContents(random.nextInt(27), new ItemStack(YTItems.Money[random.nextInt(4)], random.nextInt(8) + 1));
         }

         for (int a = 0; a < 27; a++) {
            if (random.nextInt(5) == 0) {
               ItemStack item = this.getPotion(random);
               chestTE.setInventorySlotContents(a, item);
            }
         }

         if (random.nextInt(7) == 0) {
            chestTE.setInventorySlotContents(random.nextInt(27), new ItemStack(Items.IRON_INGOT, random.nextInt(18) + 1));
         }

         if (random.nextInt(10) == 0) {
            chestTE.setInventorySlotContents(random.nextInt(27), new ItemStack(Items.GOLD_INGOT, random.nextInt(5) + 1));
         }

         if (random.nextInt(10) == 0) {
            chestTE.setInventorySlotContents(random.nextInt(27), new ItemStack(Blocks.OBSIDIAN, random.nextInt(5) + 1));
         }

         if (random.nextInt(15) == 0) {
            chestTE.setInventorySlotContents(random.nextInt(27), new ItemStack(Items.DIAMOND, random.nextInt(3) + 1));
         }
      }
   }

   private ItemStack getPotion(Random random) {
      ItemStack item = new ItemStack(Items.POTIONITEM, 1, 31);
      item = addCustomPotionEffect(item, 9, 3 + this.GetStreanth(random), random.nextInt(5000) + 6000);
      boolean speed = true;
      boolean mine = true;
      boolean health = true;
      boolean food = true;
      boolean damage = true;
      boolean regen = true;
      if (random.nextInt(10) == 0) {
         item = addCustomPotionEffect(item, 2, this.GetStreanth(random), random.nextInt(1000) + 4000);
         speed = false;
      }

      if (random.nextInt(15) == 0) {
         item = addCustomPotionEffect(item, 4, this.GetStreanth(random), random.nextInt(700) + 1000);
         mine = false;
      }

      if (random.nextInt(50) == 0) {
         item = addCustomPotionEffect(item, 7, this.GetStreanth(random) / 2, random.nextInt(150) + 50);
         health = false;
      }

      if (random.nextInt(30) == 0) {
         item = addCustomPotionEffect(item, 15, this.GetStreanth(random), random.nextInt(10000) + 1000);
      }

      if (random.nextInt(5) == 0) {
         item = addCustomPotionEffect(item, 17, this.GetStreanth(random), random.nextInt(9000) + 9000);
         food = false;
      }

      if (random.nextInt(30) == 0) {
         item = addCustomPotionEffect(item, 18, this.GetStreanth(random), random.nextInt(3000) + 2000);
         damage = false;
      }

      if (random.nextInt(10) == 0) {
         item = addCustomPotionEffect(item, 19, this.GetStreanth(random), random.nextInt(1000) + 1000);
         if (random.nextInt(4) < 3) {
            regen = false;
         }
      }

      if (random.nextInt(60) == 0) {
         item = addCustomPotionEffect(item, 20, this.GetStreanth(random), random.nextInt(1000) + 400);
         regen = false;
      }

      if (random.nextInt(20) == 0) {
         item = addCustomPotionEffect(item, 8, this.GetStreanth(random) * 2, random.nextInt(4000) + 600);
      }

      if (random.nextInt(15) == 0 && speed) {
         item = addCustomPotionEffect(item, 1, this.GetStreanth(random), random.nextInt(1000) + 4000);
      }

      if (random.nextInt(20) == 0 && mine) {
         item = addCustomPotionEffect(item, 3, this.GetStreanth(random), random.nextInt(700) + 1000);
      }

      if (random.nextInt(100) == 0 && health) {
         item = addCustomPotionEffect(item, 6, this.GetStreanth(random) / 4, random.nextInt(150) + 50);
      }

      if (random.nextInt(30) == 0) {
         item = addCustomPotionEffect(item, 16, this.GetStreanth(random), random.nextInt(10000) + 1000);
      }

      if (random.nextInt(8) == 0 && food) {
         item = addCustomPotionEffect(item, 23, this.GetStreanth(random), random.nextInt(9000) + 9000);
      }

      if (random.nextInt(40) == 0 && damage) {
         item = addCustomPotionEffect(item, 5, this.GetStreanth(random), random.nextInt(3000) + 2000);
      }

      if (random.nextInt(80) == 0 && regen) {
         item = addCustomPotionEffect(item, 10, this.GetStreanth(random), random.nextInt(1000) + 1000);
      }

      if (random.nextInt(40) == 0) {
         item = addCustomPotionEffect(item, 12, this.GetStreanth(random), random.nextInt(5000) + 1000);
      }

      if (random.nextInt(40) == 0) {
         item = addCustomPotionEffect(item, 13, this.GetStreanth(random), random.nextInt(9000) + 9000);
      }

      if (random.nextInt(40) == 0) {
         item = addCustomPotionEffect(item, 11, this.GetStreanth(random), random.nextInt(5000) + 1000);
      }

      if (random.nextInt(60) == 0) {
         item = addCustomPotionEffect(item, 22, this.GetStreanth(random) * 2, random.nextInt(2000) + 1000);
      }

      if (random.nextInt(120) == 0) {
         item = addCustomPotionEffect(item, 21, this.GetStreanth(random) * 2, random.nextInt(9000) + 9000);
      }

      return item;
   }

   private int GetStreanth(Random random) {
      int a = 0;

      while (random.nextBoolean() && a < 100) {
         a++;
      }

      return a;
   }

   public static ItemStack addCustomPotionEffect(ItemStack nmsStack, int type, int amplifier, int duration) {
      NBTTagCompound tag = null;
      if (!nmsStack.hasTagCompound()) {
         tag = new NBTTagCompound();
         nmsStack.setTagCompound(tag);
      }

      if (tag == null) {
         tag = nmsStack.getTagCompound();
      }

      NBTTagList CustomPotionEffects = null;
      if (!tag.hasKey("CustomPotionEffects")) {
         CustomPotionEffects = new NBTTagList();
         tag.setTag("CustomPotionEffects", CustomPotionEffects);
      }

      if (CustomPotionEffects == null) {
         CustomPotionEffects = (NBTTagList)tag.getTag("CustomPotionEffects");
      }

      NBTTagCompound CustomPotionEffect = new NBTTagCompound();
      CustomPotionEffect.setByte("Id", (byte)type);
      CustomPotionEffect.setByte("Amplifier", (byte)amplifier);
      CustomPotionEffect.setInteger("Duration", duration);
      CustomPotionEffects.appendTag(CustomPotionEffect);
      tag.setTag("CustomPotionEffects", CustomPotionEffects);
      nmsStack.setTagCompound(tag);
      return nmsStack;
   }
}
