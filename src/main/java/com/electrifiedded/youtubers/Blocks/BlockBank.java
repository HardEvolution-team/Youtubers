package com.electrifiedded.youtubers.Blocks;

import com.electrifiedded.archaiclib.v1710.api.block.IArchaicRenderBlock;
import com.electrifiedded.archaiclib.v1710.api.client.IIcon;
import com.electrifiedded.archaiclib.v1710.api.client.IIconRegister;
import com.electrifiedded.youtubers.InventoryBank;
import com.electrifiedded.youtubers.YTItems;
import com.electrifiedded.youtubers.Entity.ExtendedPlayerProperties;
import com.electrifiedded.youtubers.Entity.TileEntity.TileEntityBank;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/** Bank: opens the player's personal 27 slot bank inventory (shared between all banks, like an ender chest). */
public class BlockBank extends BlockContainer implements IArchaicRenderBlock {
   @SideOnly(Side.CLIENT)
   private IIcon side;
   @SideOnly(Side.CLIENT)
   private IIcon top;

   public BlockBank() {
      super(Material.PISTON);
      this.setCreativeTab(CreativeTabs.DECORATIONS);
      this.setHardness(2.5F);
      this.setSoundType(SoundType.METAL);
   }

   @Override
   public EnumBlockRenderType getRenderType(IBlockState state) {
      return EnumBlockRenderType.MODEL;
   }

   @Override
   @SideOnly(Side.CLIENT)
   public void registerBlockIcons(IIconRegister reg) {
      this.side = reg.registerIcon("youtubers:Bank");
      this.top = reg.registerIcon("minecraft:iron_block");
   }

   @Override
   @SideOnly(Side.CLIENT)
   public IIcon getIcon(int s, int meta) {
      return s > 1 ? this.side : this.top;
   }

   @Override
   public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hx, float hy, float hz) {
      TileEntity te = world.getTileEntity(pos);
      if (!(te instanceof TileEntityBank) || world.isRemote) return true;
      InventoryBank bank = ExtendedPlayerProperties.get(player).getInventoryBank();
      if ("supercat765".equals(player.getName()) && bank.isEmpty()) {
         bank.addItemStackToInventory(new ItemStack(YTItems.Money[12], 64)); // easter egg of the original author
      }
      bank.setChestTileEntity((TileEntityBank)te);
      player.displayGUIChest(bank);
      return true;
   }

   @Override
   public TileEntity createNewTileEntity(World world, int meta) {
      return new TileEntityBank();
   }
}
