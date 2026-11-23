package com.electrifiedded.youtubers.Blocks;

import com.electrifiedded.archaiclib.v1710.api.client.IIcon;
import com.electrifiedded.archaiclib.v1710.api.client.IIconRegister;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/** "TNT Map Destroyer": looks like TNT, when lit it throws 200 primed TNT up and around. */
public class BlockYTNT extends YTBlock {
   @SideOnly(Side.CLIENT)
   private IIcon top, bottom;

   public BlockYTNT() {
      super(Material.CAKE, "YTNT");
      this.setCreativeTab(CreativeTabs.REDSTONE);
      this.setSoundType(SoundType.PLANT);
   }

   @Override
   @SideOnly(Side.CLIENT)
   public void registerBlockIcons(IIconRegister reg) {
      this.blockIcon = reg.registerIcon("minecraft:tnt_side");
      this.top = reg.registerIcon("minecraft:tnt_top");
      this.bottom = reg.registerIcon("minecraft:tnt_bottom");
   }

   @Override
   @SideOnly(Side.CLIENT)
   public IIcon getIcon(int side, int meta) {
      return side == 0 ? this.bottom : side == 1 ? this.top : this.blockIcon;
   }

   @Override
   public void onBlockAdded(World world, BlockPos pos, IBlockState state) {
      super.onBlockAdded(world, pos, state);
      if (world.isBlockPowered(pos)) {
         this.explode(world, pos, null);
         world.setBlockToAir(pos);
      }
   }

   @Override
   public void neighborChanged(IBlockState state, World world, BlockPos pos, Block block, BlockPos from) {
      if (world.isBlockPowered(pos)) {
         this.explode(world, pos, null);
         world.setBlockToAir(pos);
      }
   }

   @Override
   public int quantityDropped(Random random) {
      return 1;
   }

   @Override
   public void onExplosionDestroy(World world, BlockPos pos, Explosion explosion) {
      this.explode(world, pos, explosion.getExplosivePlacedBy());
   }

   public void explode(World world, BlockPos pos, EntityLivingBase igniter) {
      if (world.isRemote) return;
      Random rand = new com.electrifiedded.archaiclib.util.FastRandom();
      double speed = 2.0;
      double x = pos.getX() + 0.5, y = pos.getY() + 0.5, z = pos.getZ() + 0.5;
      for (int a = 0; a < 200; a++) {
         EntityTNTPrimed tnt = new EntityTNTPrimed(world, x, y, z, igniter);
         tnt.motionX = speed * (rand.nextDouble() * 2.0 - 1.0);
         tnt.motionY = speed;
         tnt.motionZ = speed * (rand.nextDouble() * 2.0 - 1.0);
         world.spawnEntity(tnt);
      }
      EntityTNTPrimed center = new EntityTNTPrimed(world, x, y, z, igniter);
      world.spawnEntity(center);
      world.playSound(null, center.posX, center.posY, center.posZ, SoundEvents.ENTITY_TNT_PRIMED, SoundCategory.BLOCKS, 1.0F, 1.0F);
   }

   @Override
   public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hx, float hy, float hz) {
      ItemStack held = player.getHeldItem(hand);
      if (!held.isEmpty() && held.getItem() == Items.FLINT_AND_STEEL) {
         this.explode(world, pos, player);
         world.setBlockToAir(pos);
         held.damageItem(1, player);
         return true;
      }
      return super.onBlockActivated(world, pos, state, player, hand, facing, hx, hy, hz);
   }

   @Override
   public void onEntityCollision(World world, BlockPos pos, IBlockState state, Entity entity) {
      if (entity instanceof EntityArrow && !world.isRemote && entity.isBurning()) {
         EntityArrow arrow = (EntityArrow)entity;
         this.explode(world, pos, arrow.shootingEntity instanceof EntityLivingBase ? (EntityLivingBase)arrow.shootingEntity : null);
         world.setBlockToAir(pos);
      }
   }

   @Override
   public boolean canDropFromExplosion(Explosion explosionIn) {
      return false;
   }
}
