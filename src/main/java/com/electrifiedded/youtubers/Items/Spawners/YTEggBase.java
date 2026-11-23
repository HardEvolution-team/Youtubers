package com.electrifiedded.youtubers.Items.Spawners;

import javax.annotation.Nullable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

/**
 * 1.7 ItemMonsterPlacer-like spawn egg used by every spawner item of the mod: use on a block spawns on its face,
 * right-click on water spawns in the water. Layer 0 = egg, layer 1 = spots, both tinted by {@link #getColor}.
 */
public abstract class YTEggBase extends Item {
   protected YTEggBase() {
      this.setHasSubtypes(true);
   }

   /** New, not yet positioned entity for this meta, or null. */
   @Nullable
   protected abstract Entity create(World world, int meta);

   /** Tint of the layer (0 egg, 1 spots). */
   public abstract int getColor(ItemStack stack, int layer);

   /** Name given to the spawned entity when the egg was renamed in an anvil. */
   protected void applyName(Entity entity, ItemStack stack) {
      entity.setCustomNameTag(stack.getDisplayName());
   }

   public Entity spawn(World world, int meta, double x, double y, double z) {
      Entity entity = this.create(world, meta);
      if (entity instanceof EntityLiving) {
         EntityLiving living = (EntityLiving)entity;
         entity.setLocationAndAngles(x, y, z, MathHelper.wrapDegrees(world.rand.nextFloat() * 360.0F), 0.0F);
         living.rotationYawHead = living.rotationYaw;
         living.renderYawOffset = living.rotationYaw;
         living.onInitialSpawn(world.getDifficultyForLocation(new BlockPos(living)), (IEntityLivingData)null);
         world.spawnEntity(entity);
         living.playLivingSound();
      } else if (entity != null) {
         entity.setLocationAndAngles(x, y, z, 0.0F, 0.0F);
         world.spawnEntity(entity);
      }
      return entity;
   }

   private void finish(Entity entity, ItemStack stack, EntityPlayer player) {
      if (entity == null) return;
      if (stack.hasDisplayName()) this.applyName(entity, stack);
      if (!player.capabilities.isCreativeMode) stack.shrink(1);
   }

   @Override
   public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hx, float hy, float hz) {
      if (world.isRemote) return EnumActionResult.SUCCESS;
      ItemStack stack = player.getHeldItem(hand);
      if (!player.canPlayerEdit(pos.offset(facing), facing, stack)) return EnumActionResult.FAIL;
      IBlockState state = world.getBlockState(pos);
      BlockPos p = pos.offset(facing);
      double dy = facing == EnumFacing.UP && state.getBlock().getRenderType(state) == net.minecraft.util.EnumBlockRenderType.MODEL
         && !state.isFullCube() ? 0.5 : 0.0;
      this.finish(this.spawn(world, stack.getMetadata(), p.getX() + 0.5, p.getY() + dy, p.getZ() + 0.5), stack, player);
      return EnumActionResult.SUCCESS;
   }

   @Override
   public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
      ItemStack stack = player.getHeldItem(hand);
      if (world.isRemote) return new ActionResult<>(EnumActionResult.PASS, stack);
      RayTraceResult hit = this.rayTrace(world, player, true);
      if (hit == null || hit.typeOfHit != RayTraceResult.Type.BLOCK) return new ActionResult<>(EnumActionResult.PASS, stack);
      BlockPos p = hit.getBlockPos();
      if (!world.isBlockModifiable(player, p) || !player.canPlayerEdit(p, hit.sideHit, stack)) return new ActionResult<>(EnumActionResult.FAIL, stack);
      if (!world.getBlockState(p).getMaterial().isLiquid()) return new ActionResult<>(EnumActionResult.PASS, stack);
      this.finish(this.spawn(world, stack.getMetadata(), p.getX() + 0.5, p.getY() + 0.5, p.getZ() + 0.5), stack, player);
      return new ActionResult<>(EnumActionResult.SUCCESS, stack);
   }

   public static int RGBtoInt(int r, int g, int b) {
      return (r << 16) + (g << 8) + b;
   }

   /** The rainbow colour of the "random" eggs, changing every 6 seconds like in 1.7. */
   public static int cycleColor(long salt) {
      long min = System.currentTimeMillis() / 100L / 60L;
      java.util.Random r = new java.util.Random(min + salt);
      return RGBtoInt(r.nextInt(255), r.nextInt(255), r.nextInt(255));
   }
}
