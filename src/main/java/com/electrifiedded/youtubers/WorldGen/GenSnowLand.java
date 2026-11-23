package com.electrifiedded.youtubers.WorldGen;

import net.minecraft.init.Enchantments;
import com.electrifiedded.archaiclib.world.StagedWorld;
import com.electrifiedded.youtubers.YTItems;
import net.minecraft.util.math.BlockPos;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.world.World;

public class GenSnowLand extends GenYoutuberStructure {
   @Override
   public boolean hasDemo() {
      return true;
   }

   public void genDemo(World world, Random random, int i, int j, int k) {
      this.generateWasteland(world, random, i, j, k, 7.0);
      this.generateBase(world, random, i, j, k);
      this.addDemoChest(world, random, i, j, k);
   }

   @Override
   public boolean generate(World world, Random random, BlockPos pos) {
      int i = pos.getX();
      int j = pos.getY();
      int k = pos.getZ();
      this.generateWasteland(world, random, i, j, k, 10.0);
      this.generateBase(world, random, i, j, k);
      return false;
   }

   private void generateBase(World world, Random random, int i, int j, int k) {
      for (int a = -2; a < 10; a++) {
         double r = Math.sqrt(Math.pow(4.0, 2.0) - Math.pow(a - 0.5, 2.0));
         this.GenCircle(world, random, Blocks.SNOW, 0, i, j + a, k, (float)r, 0.0F, 'y');
         if (a >= 0) {
            this.GenCircle(world, random, Blocks.AIR, 0, i, j + a, k, (float)(r - 1.0), 0.0F, 'y');
         }
      }

      this.setBlockState(world, new BlockPos(i, j - 1, k - 2), Blocks.SNOW, 0);
      this.setBlockState(world, new BlockPos(i, j, k - 3), Blocks.OAK_DOOR, 3);
      this.setBlockState(world, new BlockPos(i, j + 1, k - 3), Blocks.OAK_DOOR, 9);
      this.setBlockState(world, new BlockPos(i, j, k + 3), Blocks.OAK_DOOR, 1);
      this.setBlockState(world, new BlockPos(i, j + 1, k + 3), Blocks.OAK_DOOR, 9);
      this.setBlockState(world, new BlockPos(i - 3, j, k), Blocks.OAK_DOOR, 2);
      this.setBlockState(world, new BlockPos(i - 3, j + 1, k), Blocks.OAK_DOOR, 9);
      this.setBlockState(world, new BlockPos(i + 3, j, k), Blocks.OAK_DOOR, 0);
      this.setBlockState(world, new BlockPos(i + 3, j + 1, k), Blocks.OAK_DOOR, 9);
      this.setBlockState(world, new BlockPos(i + 2, j + 3, k), Blocks.TORCH, 2);
      this.setBlockState(world, new BlockPos(i, j + 3, k + 2), Blocks.TORCH, 4);
      this.setBlockState(world, new BlockPos(i - 2, j + 3, k), Blocks.TORCH, 1);
      this.setBlockState(world, new BlockPos(i, j + 3, k - 2), Blocks.TORCH, 3);
      int maxlength = 50;
      int length = 0;

      while (length < maxlength && !this.endPath(world, i, j + 1 + (int)(0.5 * length), k + 4 + length)) {
         this.GenCircle(world, random, Blocks.AIR, 0, i, j + 1 + (float)(0.5 * length), k + 4 + length, 1.0F, 0.0F, 'z');
         this.setBlockState(world, new BlockPos(i + 1, j + 1 + (int)(0.5 * ++length), k + 4 + length), Blocks.TORCH, 0);
         this.setBlockState(world, new BlockPos(i - 1, j + 1 + (int)(0.5 * length), k + 4 + length), Blocks.TORCH, 0);
      }

      length = 0;

      while (length < maxlength && !this.endPath(world, i, j + 1 + (int)(0.5 * length), k - 4 - length)) {
         this.GenCircle(world, random, Blocks.AIR, 0, i, j + 1 + (float)(0.5 * length), k - 4 - length, 1.0F, 0.0F, 'z');
         this.setBlockState(world, new BlockPos(i + 1, j + 1 + (int)(0.5 * ++length), k - 4 - length), Blocks.TORCH, 0);
         this.setBlockState(world, new BlockPos(i - 1, j + 1 + (int)(0.5 * length), k - 4 - length), Blocks.TORCH, 0);
      }

      length = 0;

      while (length < maxlength && !this.endPath(world, i + 4 + length, j + 1 + (int)(0.5 * length), k)) {
         this.GenCircle(world, random, Blocks.AIR, 0, i + 4 + length, j + 1 + (float)(0.5 * length), k, 1.0F, 0.0F, 'x');
         this.setBlockState(world, new BlockPos(i + 4 + ++length, j + 1 + (int)(0.5 * length), k + 1), Blocks.TORCH, 0);
         this.setBlockState(world, new BlockPos(i + 4 + length, j + 1 + (int)(0.5 * length), k - 1), Blocks.TORCH, 0);
      }

      length = 0;

      while (length < maxlength && !this.endPath(world, i - 4 - length, j + 1 + (int)(0.5 * length), k)) {
         this.GenCircle(world, random, Blocks.AIR, 0, i - 4 - length, j + 1 + (float)(0.5 * length), k, 1.0F, 0.0F, 'x');
         length++;
         this.setBlockState(world, new BlockPos(i - 4 - length, j + 1 + (int)(0.5 * length), k + 1), Blocks.TORCH, 0);
         this.setBlockState(world, new BlockPos(i - 4 - length, j + 1 + (int)(0.5 * length), k - 1), Blocks.TORCH, 0);
      }
   }

   boolean endPath(World world, int i, int j, int k) {
      return StagedWorld.canBlockSeeTheSky(world, i, j, k);
   }

   public void generateWasteland(World world, Random random, int i, int j, int k, double r) {
      for (int a = i - (int)(r + 2.0); a <= i + (int)(r + 2.0); a++) {
         for (int b = k - (int)(r + 2.0); b <= k + (int)(r + 2.0); b++) {
            double d = Math.sqrt(Math.pow(a - i, 2.0) + Math.pow(b - k, 2.0));
            if (d <= r) {
               this.remove_vegitation(world, a, b);
               this.remove_NonStone(world, a, b, 0);
            }
         }
      }

      for (int a = 0; a < 3; a++) {
         if (r > 4.0) {
            this.generateWasteland(world, random, i + random.nextInt((int)(r * 2.0)) - (int)r, j, k + random.nextInt((int)(r * 2.0)) - (int)r, r - 1.0);
         }
      }

      if (r < 6.0) {
         this.add_top_block_circle(world, random, Blocks.SNOW, 0, i, k, r - 1.0, 0.0);
      }

      if (random.nextInt(50) == 0) {
         int x = (int)(random.nextInt((int)(2.0 * r)) - r);
         int z = (int)(random.nextInt((int)(2.0 * r)) - r);
         int h = 1;

         while (this.getBlockState(world, new BlockPos(i + x, this.getTopBlock(world, new BlockPos(i, 5, k)).getY() - h, k + z)) == Blocks.SNOW) {
            h++;
         }

         int h2 = random.nextInt(Math.max(1, h - 4));
         int val = this.getTopBlock(world, new BlockPos(i, 5, k)).getY() + 1;
         if (this.getBlockState(world, new BlockPos(i + x + 1, val - 1 - h2, k + z)) == Blocks.SNOW
            && this.getBlockState(world, new BlockPos(i + x - 1, val - 1 - h2, k + z)) == Blocks.SNOW
            && this.getBlockState(world, new BlockPos(i + x, val - 1 - h2, k + z + 1)) == Blocks.SNOW
            && this.getBlockState(world, new BlockPos(i + x, val - 1 - h2, k + z - 1)) == Blocks.SNOW
            && this.getBlockState(world, new BlockPos(i + x, val - h2, k + z)) == Blocks.SNOW
            && this.getBlockState(world, new BlockPos(i + x, val - 2 - h2, k + z)) == Blocks.SNOW) {
            this.addChest(world, random, i + x, val - 1 - h2, k + z);
         }
      }
   }

   public void add_top_block_circle(World world, Random random, Block BlockID, int meta, double i2, double k2, double r, double r2) {
      int i = (int)i2;
      int k = (int)k2;

      for (int a = i - (int)(r + 2.0); a <= i + (int)(r + 2.0); a++) {
         for (int b = k - (int)(r + 2.0); b <= k + (int)(r + 2.0); b++) {
            double d = Math.sqrt(Math.pow(a - i, 2.0) + Math.pow(b - k, 2.0));
            if (d <= r && d >= r2) {
               this.setBlockState(world, new BlockPos(a, this.getTopBlock(world, new BlockPos(a, 5, b)).getY() + 1, b), BlockID, meta);
            }
         }
      }
   }

   private void Add_Stone(World world, int i, int k, Random random) {
      Block block = this.getBlockState(world, new BlockPos(i, this.getTopBlock(world, new BlockPos(i, 5, k)).getY() + 1, k));

      for (int a = 0; a < 1; a++) {
         if (random.nextInt(1) == 0) {
            this.setBlockState(world, new BlockPos(i, this.getTopBlock(world, new BlockPos(i, 5, k)).getY() + 1, k), Blocks.SNOW, 0);
         }
      }
   }

   public void remove_NonStone(World world, int i, int k, int a) {
      Block block = this.getBlockState(world, new BlockPos(i, this.getTopBlock(world, new BlockPos(i, 5, k)).getY(), k));
      if (block.getDefaultState().getMaterial() != Material.ROCK && block != Blocks.SNOW && block != Blocks.BEDROCK && a < 1000) {
         this.setBlockToAir(world, new BlockPos(i, this.getTopBlock(world, new BlockPos(i, 5, k)).getY(), k));
         this.remove_NonStone(world, i, k, a + 1);
         this.Add_Stone(world, i, k, new com.electrifiedded.archaiclib.util.FastRandom());
      }
   }

   private void addDemoChest(World world, Random random, int i, int j, int k) {
      this.setBlockState(world, new BlockPos(i, j, k), Blocks.CHEST, 0);
      TileEntityChest chestTE = (TileEntityChest)StagedWorld.getTileEntity(world, i, j, k);
      if (chestTE != null) {
         chestTE.setInventorySlotContents(0, new ItemStack(Blocks.SNOW, random.nextInt(64)));
         chestTE.setInventorySlotContents(1, new ItemStack(Blocks.ICE, random.nextInt(64)));
         chestTE.setInventorySlotContents(2, new ItemStack(Blocks.PACKED_ICE, random.nextInt(64)));
         chestTE.setInventorySlotContents(18, new ItemStack(Items.STONE_SHOVEL));
         chestTE.setInventorySlotContents(19, new ItemStack(Items.IRON_SHOVEL));
         chestTE.setInventorySlotContents(20, new ItemStack(Items.DIAMOND_SHOVEL));
         ItemStack item = new ItemStack(Items.DIAMOND_SHOVEL);
         item.setStackDisplayName("PowerShovel");
         item.addEnchantment(Enchantments.EFFICIENCY, 10);
         item.addEnchantment(Enchantments.UNBREAKING, 3);
         chestTE.setInventorySlotContents(21, item.copy());
      }
   }

   private void addChest(World world, Random random, int i, int j, int k) {
      this.setBlockState(world, new BlockPos(i, j, k), Blocks.CHEST, 0);
      TileEntityChest chestTE = (TileEntityChest)StagedWorld.getTileEntity(world, i, j, k);
      if (chestTE != null) {
         for (int a = 0; a < 27; a++) {
            chestTE.setInventorySlotContents(a, new ItemStack(Blocks.SNOW, random.nextInt(64)));
         }

         int numb = random.nextInt(10);

         for (int a = 0; a < numb; a++) {
            chestTE.setInventorySlotContents(random.nextInt(27), new ItemStack(YTItems.Money[random.nextInt(4)], random.nextInt(8) + 1));
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

         for (int a = 0; a < 20; a++) {
            if (random.nextInt(10) == 0) {
               if (random.nextBoolean()) {
                  chestTE.setInventorySlotContents(random.nextInt(27), new ItemStack(Blocks.ICE, random.nextInt(64)));
               } else {
                  chestTE.setInventorySlotContents(random.nextInt(27), new ItemStack(Blocks.PACKED_ICE, random.nextInt(64)));
               }
            }
         }

         if (random.nextInt(2) == 0) {
            if (random.nextInt(2) == 0) {
               if (random.nextInt(2) == 0) {
                  if (random.nextInt(2) == 0) {
                     ItemStack item = new ItemStack(Items.DIAMOND_SHOVEL);
                     item.setStackDisplayName("PowerShovel");
                     item.addEnchantment(Enchantments.EFFICIENCY, 10);
                     item.addEnchantment(Enchantments.UNBREAKING, 3);
                     chestTE.setInventorySlotContents(random.nextInt(27), item.copy());
                  } else {
                     chestTE.setInventorySlotContents(random.nextInt(27), new ItemStack(Items.DIAMOND_SHOVEL));
                  }
               } else {
                  chestTE.setInventorySlotContents(random.nextInt(27), new ItemStack(Items.IRON_SHOVEL));
               }
            } else {
               chestTE.setInventorySlotContents(random.nextInt(27), new ItemStack(Items.STONE_SHOVEL));
            }
         }
      }
   }
}
