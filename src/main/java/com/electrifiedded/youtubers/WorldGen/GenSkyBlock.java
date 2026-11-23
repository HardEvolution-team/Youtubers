package com.electrifiedded.youtubers.WorldGen;

import com.electrifiedded.archaiclib.world.StagedWorld;
import com.electrifiedded.youtubers.YTItems;
import net.minecraft.util.math.BlockPos;
import java.util.Random;
import net.minecraft.block.BlockSapling;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.world.World;

public class GenSkyBlock extends GenYoutuberStructure {
   @Override
   public boolean generate(World world, Random random, BlockPos pos) {
      int i = pos.getX();
      int j = pos.getY();
      int k = pos.getZ();
      this.ClearSphere(world, i + 1, j, k, 20.0);
      this.genBox(world, Blocks.DIRT, 0, 0, i - 1, j - 3, k - 2, i + 3, j - 2, k + 2);
      this.genBox(world, Blocks.SAND, 0, 0, i, j - 2, k - 1, i + 2, j - 2, k + 1);
      this.genBox(world, Blocks.GRASS, 0, 0, i - 1, j - 1, k - 2, i + 3, j - 1, k + 2);
      this.setBlockState(world, new BlockPos(i + 1, j, k), Blocks.SAPLING, 0);
      StagedWorld.growSapling(world, i + 1, j, k, random);
      this.addChest(world, random, i + 2, j, k);
      return true;
   }

   private void addChest(World world, Random random, int i, int j, int k) {
      this.setBlockState(world, new BlockPos(i, j, k), Blocks.CHEST, 0);
      TileEntityChest chestTE = (TileEntityChest)StagedWorld.getTileEntity(world, i, j, k);
      if (chestTE != null) {
         int numb = random.nextInt(10) + 5;

         for (int a = 0; a < numb; a++) {
            chestTE.setInventorySlotContents(random.nextInt(27), new ItemStack(YTItems.Money[random.nextInt(4) + 2], random.nextInt(8) + 1));
         }

         chestTE.setInventorySlotContents(0, new ItemStack(Items.MELON));
         chestTE.setInventorySlotContents(1, new ItemStack(Items.LAVA_BUCKET));
         chestTE.setInventorySlotContents(2, new ItemStack(Blocks.ICE, 2));
         chestTE.setInventorySlotContents(3, new ItemStack(Items.BONE));
         chestTE.setInventorySlotContents(4, new ItemStack(Items.CARROT_ON_A_STICK));
         chestTE.setInventorySlotContents(5, new ItemStack(Items.PUMPKIN_PIE));
      }
   }
}
