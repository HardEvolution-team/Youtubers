package com.electrifiedded.youtubers.WorldGen;

import com.electrifiedded.archaiclib.world.StagedWorld;
import com.electrifiedded.youtubers.YTItems;
import net.minecraft.util.math.BlockPos;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.world.World;

public class GenCastle extends GenYoutuberStructure {
   public Block getBlock(Random rand) {
      return Blocks.STONEBRICK;
   }

   public void Alter(World world, Random random, BlockPos pos) {
   }

   @Override
   public boolean generate(World world, Random random, BlockPos pos) {
      int i = pos.getX();
      int j = pos.getY();
      int k = pos.getZ();
      Block block = this.getBlock(random);
      this.genBox(world, block, 0, 0, i - 3, j - 1, k - 3, i + 3, j + 3, k + 3);
      this.genBoxAir(world, 0, i - 2, j, k - 2, i + 2, j + 2, k + 2);

      for (int a = -1; a < 5; a++) {
         this.GenCircle(world, random, block, 0, i + 4, j + a, k + 4, 2.0F, 0.0F, 'y');
         this.GenCircle(world, random, block, 0, i + 4, j + a, k - 4, 2.0F, 0.0F, 'y');
         this.GenCircle(world, random, block, 0, i - 4, j + a, k + 4, 2.0F, 0.0F, 'y');
         this.GenCircle(world, random, block, 0, i - 4, j + a, k - 4, 2.0F, 0.0F, 'y');
      }

      this.GenCircle(world, random, block, 0, i + 4, j + 5, k + 4, 2.0F, 1.5F, 'y');
      this.GenCircle(world, random, block, 0, i + 4, j + 5, k - 4, 2.0F, 1.5F, 'y');
      this.GenCircle(world, random, block, 0, i - 4, j + 5, k + 4, 2.0F, 1.5F, 'y');
      this.GenCircle(world, random, block, 0, i - 4, j + 5, k - 4, 2.0F, 1.5F, 'y');
      StagedWorld.setBlock(world, i + 2, j + 1, k - 3, Blocks.GLASS, 0, 3);
      StagedWorld.setBlock(world, i - 2, j + 1, k - 3, Blocks.GLASS, 0, 3);
      StagedWorld.setBlock(world, i + 2, j + 1, k - 4, Blocks.GLASS, 0, 3);
      StagedWorld.setBlock(world, i - 2, j + 1, k - 4, Blocks.GLASS, 0, 3);
      StagedWorld.setBlock(world, i + 3, j + 1, k - 1, Blocks.GLASS, 0, 3);
      StagedWorld.setBlock(world, i + 3, j + 1, k, Blocks.GLASS, 0, 3);
      StagedWorld.setBlock(world, i + 3, j + 1, k + 1, Blocks.GLASS, 0, 3);
      StagedWorld.setBlock(world, i - 3, j + 1, k - 1, Blocks.GLASS, 0, 3);
      StagedWorld.setBlock(world, i - 3, j + 1, k, Blocks.GLASS, 0, 3);
      StagedWorld.setBlock(world, i - 3, j + 1, k + 1, Blocks.GLASS, 0, 3);
      this.addChest(world, random, i - 2, j, k + 2);
      this.addChest(world, random, i - 2, j, k + 1);
      this.addChest(world, random, i - 2, j + 1, k + 2);
      this.addChest(world, random, i - 2, j + 1, k + 1);
      StagedWorld.setBlock(world, i + 2, j, k + 2, Blocks.CRAFTING_TABLE, 0, 3);
      StagedWorld.setBlock(world, i + 2, j, k + 1, Blocks.FURNACE, 0, 3);
      StagedWorld.setBlock(world, i, j, k + 2, Blocks.BED, 8, 3);
      StagedWorld.setBlock(world, i, j, k + 1, Blocks.BED, 0, 3);
      this.Alter(world, random, pos);
      StagedWorld.setBlock(world, i, j, k - 3, Blocks.OAK_DOOR, 1, 3);
      StagedWorld.setBlock(world, i, j + 1, k - 3, Blocks.OAK_DOOR, 9, 3);
      StagedWorld.setBlock(world, i + 2, j + 2, k, Blocks.TORCH, 2, 3);
      StagedWorld.setBlock(world, i, j + 2, k + 2, Blocks.TORCH, 4, 3);
      StagedWorld.setBlock(world, i - 2, j + 2, k, Blocks.TORCH, 1, 3);
      StagedWorld.setBlock(world, i, j + 2, k - 2, Blocks.TORCH, 3, 3);
      return false;
   }

   private void addChest(World world, Random random, int i, int j, int k) {
      StagedWorld.setBlock(world, i, j, k, Blocks.CHEST, 0, 3);
      TileEntityChest chestTE = (TileEntityChest)StagedWorld.getTileEntity(world, i, j, k);
      if (chestTE != null) {
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
      }
   }
}
