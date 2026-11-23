package com.electrifiedded.youtubers.Blocks;

import com.electrifiedded.youtubers.Entity.TileEntity.TileEntityWetBeacon;
import java.util.Random;
import net.minecraft.block.BlockBeacon;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * "Wet Beacon": looks and beams like a beacon, has no GUI (the 1.7 block refused activation) and drips water
 * (lava in the Nether) from every open face.
 */
public class BlockWetBeacon extends BlockBeacon {
   @Override
   public TileEntity createNewTileEntity(World worldIn, int meta) {
      return new TileEntityWetBeacon();
   }

   @Override
   public boolean onBlockActivated(World w, BlockPos p, IBlockState s, EntityPlayer pl, EnumHand h, EnumFacing f, float x, float y, float z) {
      return false;
   }

   @Override
   @SideOnly(Side.CLIENT)
   public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random rand) {
      EnumParticleTypes drip = world.provider.getDimension() != -1 ? EnumParticleTypes.DRIP_WATER : EnumParticleTypes.DRIP_LAVA;
      for (EnumFacing f : new EnumFacing[]{EnumFacing.UP, EnumFacing.DOWN, EnumFacing.NORTH, EnumFacing.SOUTH, EnumFacing.WEST, EnumFacing.EAST}) {
         if (rand.nextInt(5) != 0) continue;
         Material m = world.getBlockState(pos.offset(f)).getMaterial();
         if (m.blocksMovement() || m.isLiquid()) continue;
         double x = pos.getX() + rand.nextFloat(), y = pos.getY() + rand.nextFloat(), z = pos.getZ() + rand.nextFloat();
         switch (f) {
            case UP: y = pos.getY() + 1.07; break;
            case DOWN: y = pos.getY() - 0.07; break;
            case NORTH: z = pos.getZ() - 0.07; break;
            case SOUTH: z = pos.getZ() + 1.1; break;
            case WEST: x = pos.getX() - 0.07; break;
            default: x = pos.getX() + 1.1;
         }
         world.spawnParticle(drip, x, y, z, 0.0, 0.0, 0.0);
      }
   }
}
