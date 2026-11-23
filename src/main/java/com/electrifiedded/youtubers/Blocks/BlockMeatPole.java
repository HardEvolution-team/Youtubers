package com.electrifiedded.youtubers.Blocks;

import com.electrifiedded.archaiclib.legacy.block.LegacyMetaBlock;
import com.electrifiedded.archaiclib.v1710.api.block.ArchaicRenderPart;
import com.electrifiedded.archaiclib.v1710.api.block.IArchaicRenderBlock;
import com.electrifiedded.archaiclib.v1710.api.client.IIcon;
import com.electrifiedded.archaiclib.v1710.api.client.IIconRegister;
import com.electrifiedded.youtubers.Youtubers;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Meat pole: hang 4 rotten flesh on it (meta 1), it slowly "cures" with random ticks: meta 1-9 rotten,
 * 10-14 raw meat, 15 cooked. Right-click harvests 4 pieces of the current stage and resets it.
 */
public class BlockMeatPole extends LegacyMetaBlock implements IArchaicRenderBlock {
   public final Random rand = new com.electrifiedded.archaiclib.util.FastRandom();
   private static final AxisAlignedBB TOP = new AxisAlignedBB(0.0, 0.8125, 0.0, 1.0, 1.0, 1.0);
   private static final AxisAlignedBB POLE = new AxisAlignedBB(0.125, 0.0, 0.125, 0.875, 0.8125, 0.875);
   private static final AxisAlignedBB MEAT = new AxisAlignedBB(0.0625, 0.0, 0.0625, 0.9375, 0.8125, 0.9375);
   @SideOnly(Side.CLIENT)
   private IIcon transparent, rotten, raw, cooked, planks;

   public BlockMeatPole() {
      super(Material.WOOD);
      this.setCreativeTab(CreativeTabs.FOOD);
      this.setTickRandomly(true);
      this.setHardness(2.0F);
      this.setSoundType(SoundType.WOOD);
      this.setLegacyFullCube(false);
   }

   @Override
   public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hx, float hy, float hz) {
      int m = meta(state);
      int x = pos.getX(), y = pos.getY(), z = pos.getZ();
      if (m == 15 || m >= 10) {
         Item[] cookedSet = {Items.COOKED_BEEF, Items.COOKED_CHICKEN, Items.COOKED_FISH, Items.COOKED_PORKCHOP};
         Item[] rawSet = {Items.BEEF, Items.CHICKEN, Items.FISH, Items.PORKCHOP};
         if (!world.isRemote) Youtubers.Drop_Item(world, x, y, z, new ItemStack((m == 15 ? cookedSet : rawSet)[this.rand.nextInt(4)], 4));
         setMeta(world, pos, 0, 3);
         return true;
      }
      if (m > 0) {
         if (!world.isRemote) Youtubers.Drop_Item(world, x, y, z, new ItemStack(Items.ROTTEN_FLESH, 4));
         setMeta(world, pos, 0, 3);
      } else {
         ItemStack item = player.getHeldItem(hand);
         if (!item.isEmpty() && item.getItem() == Items.ROTTEN_FLESH && item.getCount() >= 4) {
            if (!player.capabilities.isCreativeMode) item.shrink(4);
            setMeta(world, pos, 1, 3);
         }
      }
      return false;
   }

   @Override
   public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
      int m = meta(state);
      if (m > 0 && m != 15 || m == 0 && rand.nextInt(200) == 0) setMeta(world, pos, m + 1, 3);
   }

   @Override
   public int damageDropped(IBlockState state) {
      return 0;
   }

   @Override
   @SideOnly(Side.CLIENT)
   public BlockRenderLayer getRenderLayer() {
      return BlockRenderLayer.CUTOUT;
   }

   @Override
   @SideOnly(Side.CLIENT)
   public void registerBlockIcons(IIconRegister reg) {
      this.transparent = reg.registerIcon("youtubers:Transparency");
      this.rotten = reg.registerIcon("youtubers:rotten_flesh");
      this.raw = reg.registerIcon("youtubers:rawmeat");
      this.cooked = reg.registerIcon("youtubers:cookedmeat");
      this.planks = reg.registerIcon("minecraft:planks_oak");
   }

   @Override
   @SideOnly(Side.CLIENT)
   public IIcon getIcon(int side, int meta) {
      return this.planks;
   }

   @SideOnly(Side.CLIENT)
   private IIcon meatIcon(int meta) {
      return meta == 15 ? this.cooked : meta >= 10 ? this.raw : meta > 0 ? this.rotten : this.transparent;
   }

   @Override
   public int getArchaicItemRenderKey(int meta) {
      return 0;
   }

   @Override
   @SideOnly(Side.CLIENT)
   public List<ArchaicRenderPart> getArchaicRenderParts(int key) {
      List<ArchaicRenderPart> parts = new ArrayList<>();
      parts.add(ArchaicRenderPart.box(TOP, this.planks));
      parts.add(ArchaicRenderPart.box(POLE, this.planks));
      IIcon m = meatIcon(key);
      parts.add(ArchaicRenderPart.box(MEAT, new IIcon[]{this.transparent, this.transparent, m, m, m, m}).renderAllFaces());
      return parts;
   }
}
