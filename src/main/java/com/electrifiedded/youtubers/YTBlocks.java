package com.electrifiedded.youtubers;

import com.electrifiedded.archaiclib.legacy.registry.LegacyGameRegistry;
import com.electrifiedded.archaiclib.legacy.registry.LegacyRecipes;
import com.electrifiedded.youtubers.Blocks.BlockBank;
import com.electrifiedded.youtubers.Blocks.BlockMeatPole;
import com.electrifiedded.youtubers.Blocks.BlockStraw;
import com.electrifiedded.youtubers.Blocks.BlockTech;
import com.electrifiedded.youtubers.Blocks.BlockTechItem;
import com.electrifiedded.youtubers.Blocks.BlockWetBeacon;
import com.electrifiedded.youtubers.Blocks.BlockYTNT;
import com.electrifiedded.youtubers.Blocks.ButterBlock;
import com.electrifiedded.youtubers.Blocks.PotatoBlock;
import com.electrifiedded.youtubers.Blocks.YTBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class YTBlocks {
   public static Block Bank;
   public static Block blockButter;
   public static Block blockPotato;
   public static Block blockYTNT;
   public static Block wetBeacon;
   public static Block Straw;
   public static Block PulseRstone;
   public static Block PulseLapis;
   public static Block Tech;
   public static Block meatpole;

   /** preInit. Registry names are the 1.7 ones in lower case. */
   public static void Load() {
      Bank = reg(new BlockBank().setTranslationKey("Bank"), "bank");
      blockButter = reg(new ButterBlock().setHardness(0.5F), "butterblock");
      blockPotato = reg(new PotatoBlock().setHardness(0.5F), "potatoblock");
      blockYTNT = reg(new BlockYTNT(), "ytnt");
      wetBeacon = reg(new BlockWetBeacon().setTranslationKey("wetBeacon").setLightLevel(1.0F).setCreativeTab(CreativeTabs.MISC), "wetbeacon");
      Straw = reg(new BlockStraw().setTranslationKey("Straw"), "straw");
      PulseRstone = reg(new YTBlock(Material.ROCK, "PulseRed").setHardness(1.5F).setCreativeTab(CreativeTabs.DECORATIONS), "pulsered");
      PulseLapis = reg(new YTBlock(Material.ROCK, "PulseBlue").setHardness(1.5F).setCreativeTab(CreativeTabs.DECORATIONS), "pulseblue");
      Tech = new BlockTech().setTranslationKey("TechBlock").setHardness(1.0F).setCreativeTab(CreativeTabs.DECORATIONS);
      LegacyGameRegistry.registerBlock(Tech, new BlockTechItem(Tech), "techblock");
      meatpole = reg(new BlockMeatPole().setTranslationKey("MeatPole"), "meatpole");
   }

   public static void addRecipes() {
      LegacyRecipes.addRecipe(new ItemStack(Bank), "iii", "iei", "ici", 'i', Items.IRON_INGOT, 'e', Blocks.ENDER_CHEST, 'c', Items.COMPARATOR);
      LegacyRecipes.addRecipe(new ItemStack(blockPotato), "ppp", "pPp", "ppp", 'p', Items.POTATO, 'P', Items.POISONOUS_POTATO);
      LegacyRecipes.addShapelessRecipe(new ItemStack(Items.POTATO, 8), blockPotato);
      LegacyRecipes.addRecipe(new ItemStack(meatpole, 4), "sss", "mpm", "mpm", 's', Blocks.WOODEN_SLAB, 'p', Blocks.PLANKS, 'm', Items.ROTTEN_FLESH);
   }

   private static Block reg(Block b, String name) {
      return LegacyGameRegistry.registerBlock(b, name);
   }
}
