package com.electrifiedded.youtubers.Items;

import com.google.common.collect.Sets;
import java.util.Set;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/** Boom Pick: mining stone-like blocks mines the 5x5x5 cube around them and sets off a (non-fiery) explosion. */
public class ItemBoomPick extends ItemPickaxe {
   private static final Set<Block> BLOCKS = Sets.newHashSet(
      Blocks.COBBLESTONE, Blocks.DOUBLE_STONE_SLAB, Blocks.STONE_SLAB, Blocks.STONE, Blocks.SANDSTONE, Blocks.MOSSY_COBBLESTONE,
      Blocks.IRON_ORE, Blocks.IRON_BLOCK, Blocks.COAL_ORE, Blocks.GOLD_BLOCK, Blocks.GOLD_ORE, Blocks.DIAMOND_ORE, Blocks.DIAMOND_BLOCK,
      Blocks.ICE, Blocks.NETHERRACK, Blocks.LAPIS_ORE, Blocks.LAPIS_BLOCK, Blocks.REDSTONE_ORE, Blocks.LIT_REDSTONE_ORE, Blocks.RAIL,
      Blocks.DETECTOR_RAIL, Blocks.GOLDEN_RAIL, Blocks.ACTIVATOR_RAIL
   );

   public ItemBoomPick() {
      super(ToolMaterial.IRON);
      this.setCreativeTab(CreativeTabs.TOOLS);
   }

   private static boolean boomable(IBlockState s) {
      return BLOCKS.contains(s.getBlock()) || s.getMaterial() == Material.ROCK && s.getBlock() != Blocks.BEDROCK;
   }

   @Override
   public boolean onBlockDestroyed(ItemStack tool, World world, IBlockState state, BlockPos pos, EntityLivingBase entity) {
      if (!world.isRemote && (BLOCKS.contains(state.getBlock()) || state.getMaterial() == Material.ROCK)) {
         for (BlockPos p : BlockPos.getAllInBoxMutable(pos.add(-2, -2, -2), pos.add(2, 2, 2))) {
            IBlockState s = world.getBlockState(p);
            if (boomable(s)) world.destroyBlock(p.toImmutable(), true);
         }
         world.createExplosion(entity, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, 5.0F, false);
      }
      return super.onBlockDestroyed(tool, world, state, pos, entity);
   }
}
