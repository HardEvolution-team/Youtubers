package com.electrifiedded.youtubers.Blocks;

import com.electrifiedded.archaiclib.v1710.api.block.ArchaicRenderPart;
import com.electrifiedded.archaiclib.v1710.api.block.IArchaicRenderBlock;
import com.electrifiedded.archaiclib.v1710.api.client.IIcon;
import com.electrifiedded.archaiclib.v1710.api.client.IIconRegister;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Straw: a climbable hollow "tube" made of four thin walls (1.7 custom renderer drew the same four boxes).
 * Collides with its walls (upper half for three of them, like the 1.7 collision boxes) and works as a ladder.
 */
public class BlockStraw extends Block implements IArchaicRenderBlock {
   private static final double T = 0.0625;
   private static final AxisAlignedBB[] WALLS = {
      new AxisAlignedBB(0, 0, 0, T, 1, 1), new AxisAlignedBB(0, 0, 0, 1, 1, T),
      new AxisAlignedBB(1 - T, 0, 0, 1, 1, 1), new AxisAlignedBB(0, 0, 1 - T, 1, 1, 1)
   };
   private static final AxisAlignedBB[] COLLISION = {
      new AxisAlignedBB(0, 0, 0, T, 1, 1), new AxisAlignedBB(0, 0.5, 0, 1, 1, T),
      new AxisAlignedBB(1 - T, 0.5, 0, 1, 1, 1), new AxisAlignedBB(0, 0.5, 1 - T, 1, 1, 1)
   };
   @SideOnly(Side.CLIENT)
   private IIcon top;
   @SideOnly(Side.CLIENT)
   private IIcon side;

   public BlockStraw() {
      super(Material.GLASS);
      this.setCreativeTab(CreativeTabs.DECORATIONS);
      this.setHardness(0.3F);
      this.setSoundType(SoundType.PLANT);
   }

   @Override public boolean isOpaqueCube(IBlockState s) { return false; }
   @Override public boolean isFullCube(IBlockState s) { return false; }
   @Override @SideOnly(Side.CLIENT) public BlockRenderLayer getRenderLayer() { return BlockRenderLayer.CUTOUT; }
   @Override public boolean isSideSolid(IBlockState s, IBlockAccess w, BlockPos p, EnumFacing side) { return side.getAxis() != EnumFacing.Axis.Y; }
   @Override public BlockFaceShape getBlockFaceShape(IBlockAccess w, IBlockState s, BlockPos p, EnumFacing f) {
      return f.getAxis() == EnumFacing.Axis.Y ? BlockFaceShape.UNDEFINED : BlockFaceShape.SOLID;
   }
   @Override public boolean isLadder(IBlockState s, IBlockAccess w, BlockPos p, EntityLivingBase e) { return true; }

   @Override
   public void addCollisionBoxToList(IBlockState state, World world, BlockPos pos, AxisAlignedBB mask, List<AxisAlignedBB> list, @Nullable Entity entity, boolean actual) {
      for (AxisAlignedBB bb : COLLISION) addCollisionBoxToList(pos, mask, list, bb);
   }

   @Override
   @SideOnly(Side.CLIENT)
   public void registerBlockIcons(IIconRegister reg) {
      this.top = reg.registerIcon("youtubers:StrawTop");
      this.side = reg.registerIcon("youtubers:StrawSide");
   }

   @Override
   @SideOnly(Side.CLIENT)
   public IIcon getIcon(int s, int meta) {
      return s > 1 ? this.side : this.top;
   }

   @Override
   @SideOnly(Side.CLIENT)
   public List<ArchaicRenderPart> getArchaicRenderParts(int key) {
      List<ArchaicRenderPart> parts = new ArrayList<>();
      IIcon[] icons = {this.top, this.top, this.side, this.side, this.side, this.side};
      for (AxisAlignedBB bb : WALLS) parts.add(ArchaicRenderPart.box(bb, icons).renderAllFaces());
      return parts;
   }
}
