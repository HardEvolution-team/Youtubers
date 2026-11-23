package com.electrifiedded.youtubers.WorldGen;

import net.minecraft.init.Enchantments;
import com.electrifiedded.archaiclib.world.StagedWorld;
import com.electrifiedded.youtubers.YTItems;
import net.minecraft.util.math.BlockPos;
import java.util.Random;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.world.World;

public class GenKFC extends GenYoutuberStructure {
   @Override
   public boolean generate(World world, Random random, BlockPos pos) {
      int i = pos.getX();
      int j = pos.getY();
      int k = pos.getZ();
      this.remove_vegitation_circle(world, i, j, 15.0);
      this.GenCircle(world, random, Blocks.SNOW, 1, i, j - 1, k, 3.0F, 0.0F, 'y');

      for (int a = 0; a < 4; a++) {
         this.GenCircle(world, random, Blocks.SNOW, 1, i, j + a, k, 3.5F, 2.5F, 'y');
         this.setBlockState(world, new BlockPos(i - 3, j + a, k - 1), Blocks.STAINED_HARDENED_CLAY, 14);
         this.setBlockState(world, new BlockPos(i - 3, j + a, k + 1), Blocks.STAINED_HARDENED_CLAY, 14);
         this.setBlockState(world, new BlockPos(i + 3, j + a, k - 1), Blocks.STAINED_HARDENED_CLAY, 14);
         this.setBlockState(world, new BlockPos(i + 3, j + a, k + 1), Blocks.STAINED_HARDENED_CLAY, 14);
         this.setBlockState(world, new BlockPos(i - 1, j + a, k - 3), Blocks.STAINED_HARDENED_CLAY, 14);
         this.setBlockState(world, new BlockPos(i + 1, j + a, k - 3), Blocks.STAINED_HARDENED_CLAY, 14);
         this.setBlockState(world, new BlockPos(i - 1, j + a, k + 3), Blocks.STAINED_HARDENED_CLAY, 14);
         this.setBlockState(world, new BlockPos(i + 1, j + a, k + 3), Blocks.STAINED_HARDENED_CLAY, 14);
      }

      this.setBlockState(world, new BlockPos(i, j, k - 3), Blocks.OAK_DOOR, 1);
      this.setBlockState(world, new BlockPos(i, j + 1, k - 3), Blocks.OAK_DOOR, 9);
      this.genBox(world, Blocks.SNOW, 0, 0, i + 3, j + 4, k - 2, i + 3, j + 4, k + 2);
      this.genBox(world, Blocks.SNOW, 0, 0, i - 3, j + 4, k - 2, i - 3, j + 4, k + 2);
      this.genBox(world, Blocks.SNOW, 0, 0, i - 2, j + 4, k + 3, i + 2, j + 4, k + 3);
      this.genBox(world, Blocks.SNOW, 0, 0, i - 2, j + 4, k - 3, i + 2, j + 4, k - 3);
      this.genBox(world, Blocks.WOOL, 0, 0, i - 2, j + 4, k - 2, i + 2, j + 4, k + 2);
      this.genBox(world, Blocks.WOOL, 0, 0, i - 2, j + 4, k - 1, i + 2, j + 10, k + 1);
      this.genBox(world, Blocks.WOOL, 1, 0, i - 2, j + 7, k - 3, i + 2, j + 8, k - 2);
      this.setBlockState(world, new BlockPos(i + 2, j + 9, k - 1), Blocks.WOOL, 15);
      this.setBlockState(world, new BlockPos(i - 2, j + 9, k - 1), Blocks.WOOL, 15);
      this.genBox(world, Blocks.WOOL, 4, 0, i - 3, j + 5, k - 2, i + 3, j + 5, k - 2);
      this.genBox(world, Blocks.WOOL, 14, 0, i - 1, j + 5, k - 2, i + 1, j + 6, k - 2);
      this.genBox(world, Blocks.WOOL, 4, 0, i - 3, j + 2, k - 3, i - 3, j + 5, k - 3);
      this.genBox(world, Blocks.WOOL, 4, 0, i + 3, j + 2, k - 3, i + 3, j + 5, k - 3);
      this.setBlockState(world, new BlockPos(i - 3, j + 2, k - 4), Blocks.WOOL, 4);
      this.setBlockState(world, new BlockPos(i + 3, j + 2, k - 4), Blocks.WOOL, 4);
      this.setBlockState(world, new BlockPos(i - 4, j + 2, k - 3), Blocks.WOOL, 4);
      this.setBlockState(world, new BlockPos(i + 4, j + 2, k - 3), Blocks.WOOL, 4);
      this.addChest(world, random, i - 1, j, k + 2);
      this.addChest(world, random, i - 1, j + 1, k + 2);
      this.addChest(world, random, i + 1, j, k + 2);
      this.addChest(world, random, i + 1, j + 1, k + 2);
      return false;
   }

   private void addChest(World world, Random random, int i, int j, int k) {
      this.setBlockState(world, new BlockPos(i, j, k), Blocks.CHEST, 0);
      TileEntityChest chestTE = (TileEntityChest)StagedWorld.getTileEntity(world, i, j, k);
      if (chestTE != null) {
         int numb = random.nextInt(10) + 1;

         for (int a = 0; a < numb; a++) {
            chestTE.setInventorySlotContents(random.nextInt(27), new ItemStack(YTItems.Money[random.nextInt(4) + 1], random.nextInt(8) + 1));
         }

         numb = random.nextInt(7) + 5;

         for (int a = 0; a < numb; a++) {
            ItemStack item = new ItemStack(Items.COOKED_CHICKEN, random.nextInt(8) + 3);
            item.setStackDisplayName("Keralis Fried Chicken");
            chestTE.setInventorySlotContents(random.nextInt(27), item.copy());
         }

         for (int a = 0; a < 4; a++) {
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

         for (int a = 0; a < 4; a++) {
            if (random.nextInt(15) == 0) {
               ItemStack item = new ItemStack(Items.COOKED_CHICKEN, random.nextInt(3) + 1);
               item.setStackDisplayName("Keralis Spicy Chicken");
               item.addEnchantment(Enchantments.FIRE_ASPECT, 1);
               chestTE.setInventorySlotContents(random.nextInt(27), item.copy());
            }
         }
      }
   }
}
