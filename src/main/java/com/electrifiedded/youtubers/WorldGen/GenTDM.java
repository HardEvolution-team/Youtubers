package com.electrifiedded.youtubers.WorldGen;

import com.electrifiedded.archaiclib.world.StagedWorld;
import net.minecraft.util.math.BlockPos;
import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.world.World;

public class GenTDM extends GenYoutuberStructure {
   @Override
   public boolean generate(World world, Random random, BlockPos pos) {
      int i = pos.getX();
      int j = pos.getY();
      int k = pos.getZ();
      this.genBox(world, Blocks.AIR, 0, 0, i - 4, j, k, i, j + 2, k + 7);
      this.genBox(world, Blocks.DOUBLE_STONE_SLAB, 0, 0, i - 4, j - 1, k, i, j - 1, k + 7);
      this.addChest(world, random, i + 1, j, k);
      this.setBlockState(world, new BlockPos(i, j, k + 1), Blocks.COBBLESTONE, 0);
      this.setBlockState(world, new BlockPos(i - 1, j, k + 1), Blocks.COBBLESTONE, 0);
      this.setBlockState(world, new BlockPos(i - 2, j, k + 1), Blocks.COBBLESTONE, 0);
      this.setBlockState(world, new BlockPos(i - 3, j, k + 1), Blocks.COBBLESTONE, 0);
      this.setBlockState(world, new BlockPos(i - 4, j, k + 2), Blocks.COBBLESTONE, 0);
      this.setBlockState(world, new BlockPos(i, j, k + 3), Blocks.COBBLESTONE, 0);
      this.setBlockState(world, new BlockPos(i - 1, j, k + 3), Blocks.COBBLESTONE, 0);
      this.setBlockState(world, new BlockPos(i - 2, j, k + 3), Blocks.COBBLESTONE, 0);
      this.setBlockState(world, new BlockPos(i - 3, j, k + 3), Blocks.COBBLESTONE, 0);
      this.setBlockState(world, new BlockPos(i - 4, j, k + 4), Blocks.COBBLESTONE, 0);
      this.setBlockState(world, new BlockPos(i, j, k + 4), Blocks.COBBLESTONE, 0);
      this.setBlockState(world, new BlockPos(i - 1, j, k + 5), Blocks.COBBLESTONE, 0);
      this.setBlockState(world, new BlockPos(i - 2, j, k + 5), Blocks.COBBLESTONE, 0);
      this.setBlockState(world, new BlockPos(i - 3, j, k + 5), Blocks.COBBLESTONE, 0);
      this.setBlockState(world, new BlockPos(i - 4, j, k + 5), Blocks.COBBLESTONE, 0);
      this.setBlockState(world, new BlockPos(i, j, k + 6), Blocks.COBBLESTONE, 0);
      this.setBlockState(world, new BlockPos(i - 4, j, k + 6), Blocks.COBBLESTONE, 0);
      this.setBlockState(world, new BlockPos(i, j, k + 7), Blocks.COBBLESTONE, 0);
      this.setBlockState(world, new BlockPos(i - 1, j, k + 7), Blocks.COBBLESTONE, 0);
      this.setBlockState(world, new BlockPos(i - 2, j, k + 7), Blocks.COBBLESTONE, 0);
      this.setBlockState(world, new BlockPos(i - 3, j, k + 7), Blocks.COBBLESTONE, 0);
      this.setBlockState(world, new BlockPos(i - 4, j, k + 7), Blocks.COBBLESTONE, 0);
      this.setBlockState(world, new BlockPos(i, j, k), Blocks.REDSTONE_TORCH, 4);
      this.setBlockState(world, new BlockPos(i - 4, j, k + 3), Blocks.REDSTONE_TORCH, 3);
      this.setBlockState(world, new BlockPos(i, j, k + 5), Blocks.REDSTONE_WIRE, 0);
      this.setBlockState(world, new BlockPos(i - 4, j, k), Blocks.REDSTONE_WIRE, 0);
      this.setBlockState(world, new BlockPos(i - 2, j, k + 2), Blocks.REDSTONE_WIRE, 0);
      this.setBlockState(world, new BlockPos(i - 2, j, k + 4), Blocks.WATER, 0);
      this.setBlockState(world, new BlockPos(i - 2, j, k + 6), Blocks.WATER, 0);
      this.setBlockState(world, new BlockPos(i - 1, j, k), Blocks.POWERED_REPEATER, 15);
      this.setBlockState(world, new BlockPos(i - 2, j, k), Blocks.POWERED_REPEATER, 15);
      this.setBlockState(world, new BlockPos(i - 3, j, k), Blocks.POWERED_REPEATER, 15);
      this.setBlockState(world, new BlockPos(i - 4, j, k + 1), Blocks.POWERED_REPEATER, 14);
      this.setBlockState(world, new BlockPos(i - 3, j, k + 2), Blocks.UNPOWERED_REPEATER, 3);
      this.setBlockState(world, new BlockPos(i, j + 1, k + 1), Blocks.COBBLESTONE, 0);
      this.setBlockState(world, new BlockPos(i, j + 1, k + 5), Blocks.COBBLESTONE, 0);
      this.setBlockState(world, new BlockPos(i - 1, j + 1, k + 1), Blocks.DISPENSER, 5);
      this.setBlockState(world, new BlockPos(i - 1, j + 1, k + 5), Blocks.DISPENSER, 5);
      this.setBlockToAir(world, new BlockPos(i, j + 1, k + 1));
      this.setBlockToAir(world, new BlockPos(i, j + 1, k + 5));
      this.setBlockState(world, new BlockPos(i - 2, j + 1, k), Blocks.COBBLESTONE, 0);
      this.setBlockState(world, new BlockPos(i - 3, j + 1, k + 1), Blocks.COBBLESTONE, 0);
      this.setBlockState(world, new BlockPos(i - 1, j + 1, k + 2), Blocks.COBBLESTONE, 0);
      this.setBlockState(world, new BlockPos(i - 2, j + 1, k + 2), Blocks.COBBLESTONE, 0);
      this.setBlockState(world, new BlockPos(i - 1, j + 1, k + 3), Blocks.COBBLESTONE, 0);
      this.setBlockState(world, new BlockPos(i - 4, j + 1, k + 3), Blocks.COBBLESTONE, 0);
      this.setBlockState(world, new BlockPos(i - 1, j + 1, k + 4), Blocks.STONE_SLAB, 8);
      this.setBlockState(world, new BlockPos(i - 2, j + 1, k + 4), Blocks.COBBLESTONE, 0);
      this.setBlockState(world, new BlockPos(i - 3, j + 1, k + 5), Blocks.COBBLESTONE, 0);
      this.setBlockState(world, new BlockPos(i - 2, j + 1, k + 6), Blocks.COBBLESTONE, 0);
      this.setBlockState(world, new BlockPos(i - 2, j + 1, k + 1), Blocks.WOODEN_PRESSURE_PLATE, 0);
      this.setBlockState(world, new BlockPos(i - 2, j + 1, k + 5), Blocks.RAIL, 0);
      this.setBlockState(world, new BlockPos(i - 1, j + 1, k), Blocks.LEVER, 4);
      this.setBlockState(world, new BlockPos(i, j + 1, k + 1), Blocks.REDSTONE_WIRE, 0);
      this.setBlockState(world, new BlockPos(i, j + 1, k + 4), Blocks.REDSTONE_WIRE, 0);
      this.setBlockState(world, new BlockPos(i, j + 1, k + 6), Blocks.REDSTONE_WIRE, 0);
      this.setBlockState(world, new BlockPos(i, j + 1, k + 7), Blocks.REDSTONE_WIRE, 0);
      this.setBlockState(world, new BlockPos(i - 1, j + 1, k + 7), Blocks.REDSTONE_WIRE, 0);
      this.setBlockState(world, new BlockPos(i - 2, j + 1, k + 7), Blocks.REDSTONE_WIRE, 0);
      this.setBlockState(world, new BlockPos(i - 3, j + 1, k + 7), Blocks.REDSTONE_WIRE, 0);
      this.setBlockState(world, new BlockPos(i - 4, j + 1, k + 7), Blocks.REDSTONE_WIRE, 0);
      this.setBlockState(world, new BlockPos(i - 4, j + 1, k + 6), Blocks.REDSTONE_WIRE, 0);
      this.setBlockState(world, new BlockPos(i - 4, j + 1, k + 5), Blocks.REDSTONE_WIRE, 0);
      this.setBlockState(world, new BlockPos(i - 4, j + 1, k + 4), Blocks.REDSTONE_WIRE, 0);
      this.setBlockState(world, new BlockPos(i - 1, j + 2, k + 1), Blocks.REDSTONE_WIRE, 0);
      this.setBlockState(world, new BlockPos(i - 1, j + 2, k + 2), Blocks.POWERED_REPEATER, 14);
      this.setBlockState(world, new BlockPos(i - 1, j + 2, k + 3), Blocks.POWERED_REPEATER, 14);
      this.setBlockState(world, new BlockPos(i - 1, j + 2, k + 4), Blocks.POWERED_REPEATER, 14);
      this.setBlockState(world, new BlockPos(i - 1, j + 2, k + 5), Blocks.REDSTONE_WIRE, 0);
      return false;
   }

   private void addChest(World world, Random random, int i, int j, int k) {
      this.setBlockState(world, new BlockPos(i, j, k), Blocks.CHEST, 0);
      TileEntityChest chestTE = (TileEntityChest)StagedWorld.getTileEntity(world, i, j, k);
      if (chestTE != null) {
         chestTE.setInventorySlotContents(4, new ItemStack(Items.STICK));
         chestTE.setInventorySlotContents(12, new ItemStack(Items.MINECART));
         chestTE.setInventorySlotContents(14, new ItemStack(Blocks.DIAMOND_BLOCK));
         chestTE.setInventorySlotContents(22, new ItemStack(Blocks.TNT, 4));
      }
   }
}
