package com.electrifiedded.youtubers.WorldGen;

import com.electrifiedded.archaiclib.world.StagedWorld;
import net.minecraft.util.math.BlockPos;
import com.electrifiedded.youtubers.RandUtil.RandomString;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntitySign;
import net.minecraft.util.WeightedRandom;
import net.minecraft.util.WeightedRandom.Item;
import net.minecraft.world.World;

public class GenFansion extends GenYoutuberStructure {
   public static List<Item> FansionNames = new ArrayList<>();
   public static Random rand = new com.electrifiedded.archaiclib.util.FastRandom();

   public static void addNames(String[] names) {
      for (int a = 0; a < names.length; a++) {
         FansionNames.add(new RandomString(names[a], 1));
      }
   }

   @Override
   public boolean generate(World world, Random random, BlockPos pos) {
      int i = pos.getX();
      int j = pos.getY();
      int k = pos.getZ();
      this.remove_vegitation_circle(world, i, j, 15.0);
      this.GenCircle(world, random, Blocks.MOSSY_COBBLESTONE, 0, i, j - 1, k, 5.9F, 0.0F, 'y');
      this.GenCircle(world, random, Blocks.DOUBLE_STONE_SLAB, 0, i, j - 1, k, 5.0F, 0.0F, 'y');
      this.GenCircle(world, random, Blocks.MOSSY_COBBLESTONE, 0, i, j, k, 5.9F, 5.0F, 'y');

      for (int floor = 0; floor < 5; floor++) {
         this.GenCircle(world, random, Blocks.MOSSY_COBBLESTONE, 0, i, j + 4 + floor * 5, k, 5.9F, 0.0F, 'y');
         this.GenCircle(world, random, Blocks.DOUBLE_STONE_SLAB, 0, i, j + 4 + floor * 5, k, 5.0F, 0.0F, 'y');

         for (int a = 0; a < 5; a++) {
            this.GenCircle(world, random, Blocks.MOSSY_COBBLESTONE, 0, i, j + 1 + a + floor * 5, k, 5.9F, 5.0F, 'y');
         }

         for (int a = 1; a < 4; a++) {
            for (int b = -1; b < 2; b++) {
               this.setBlockToAir(world, new BlockPos(i + 5, j + a + floor * 5, k + b));
               this.setBlockToAir(world, new BlockPos(i - 5, j + a + floor * 5, k + b));
            }
         }

         this.setBlockToAir(world, new BlockPos(i + 2, j + 4 + floor * 5, k + 4));
         this.setBlockToAir(world, new BlockPos(i + 1, j + 4 + floor * 5, k + 4));
         this.setBlockToAir(world, new BlockPos(i, j + 4 + floor * 5, k + 4));
         this.setBlockToAir(world, new BlockPos(i - 1, j + 4 + floor * 5, k + 4));
         this.setBlockState(world, new BlockPos(i + 2, j + floor * 5, k + 4), Blocks.STONE_BRICK_STAIRS, 2);
         this.setBlockState(world, new BlockPos(i + 1, j + 1 + floor * 5, k + 4), Blocks.STONE_BRICK_STAIRS, 1);
         this.setBlockState(world, new BlockPos(i, j + 2 + floor * 5, k + 4), Blocks.STONE_BRICK_STAIRS, 1);
         this.setBlockState(world, new BlockPos(i - 1, j + 3 + floor * 5, k + 4), Blocks.STONE_BRICK_STAIRS, 1);
         this.setBlockState(world, new BlockPos(i - 2, j + 4 + floor * 5, k + 4), Blocks.STONE_BRICK_STAIRS, 1);
         this.setBlockState(world, new BlockPos(i + 2, j - 1 + floor * 5, k + 4), Blocks.STONE_BRICK_STAIRS, 6);
         this.setBlockState(world, new BlockPos(i + 1, j + floor * 5, k + 4), Blocks.STONE_BRICK_STAIRS, 6);
         this.setBlockState(world, new BlockPos(i, j + 1 + floor * 5, k + 4), Blocks.STONE_BRICK_STAIRS, 6);
         this.setBlockState(world, new BlockPos(i - 1, j + 2 + floor * 5, k + 4), Blocks.STONE_BRICK_STAIRS, 6);
         this.setBlockState(world, new BlockPos(i - 2, j + 3 + floor * 5, k + 4), Blocks.STONE_BRICK_STAIRS, 6);
         this.setSign(world, i + 2, j + 1 + floor * 5, k - 4, 3);
         this.setSign(world, i + 1, j + 1 + floor * 5, k - 4, 3);
         this.setSign(world, i, j + 1 + floor * 5, k - 4, 3);
         this.setSign(world, i - 1, j + 1 + floor * 5, k - 4, 3);
         this.setSign(world, i - 2, j + 1 + floor * 5, k - 4, 3);
      }

      return true;
   }

   public void setSign(World world, int x, int y, int z, int dir) {
      this.setBlockState(world, new BlockPos(x, y, z), Blocks.WALL_SIGN, dir);
      TileEntitySign te = (TileEntitySign)StagedWorld.getTileEntity(world, x, y, z);

      for (int a = 0; a < te.signText.length; a++) {
         te.signText[a] = new net.minecraft.util.text.TextComponentString(getName(world));
      }
   }

   public static String getName(World world) {
      RandomString RText = (RandomString)WeightedRandom.getRandomItem(rand, FansionNames);
      return RText.text == null ? "" : RText.text;
   }

   static {
      FansionNames.add(new RandomString("Bryan Hinze", 5));
      addNames(
         new String[]{
            "Robo EnderSlayer",
            "Wesley Pate",
            "Josh Saucier",
            "Steve Greiner",
            "jack Montoya",
            "Jax The Gamer",
            "Laurie Whitehead",
            "Alyssia Valdez",
            "Bridget Short",
            "dylon thomas",
            "awsome panda",
            "Osvaldo Turrent",
            "david curran",
            "trolland greif",
            "Trey Alec",
            "Alex Hernandez",
            "jacori murphy",
            "Joseph Savarese",
            "Emily Diaz",
            "Sugerburst Burst",
            "dan gotesdyner",
            "Liam Caldwell",
            "Joseph Caje",
            "BioHazardous",
            "Logan Dobar",
            "Faith Rinehart",
            "Agent Ben Gaming",
            "William Xia",
            "tomas soqui",
            "NaturalGamer11",
            "annemokhtar",
            "Keelen Begley",
            "TDM Jr",
            "Martin Martin",
            "S.S.Browning",
            "Liv Jappert",
            "Claudia Santos",
            "Kasey Heizman",
            "Kitaek Yang",
            "Electric Pineapple",
            "Jack Mortzheim",
            "Bay Whistle",
            "Walker Quinn",
            "Tarishah 23",
            "Lindsey Clark",
            "zane awawdeh",
            "MrHemingray",
            "Ide Diaz",
            "Dean Quiambao",
            "FarmerswagMC",
            "Jonah Escobar",
            "robbie Beattie",
            "Snacker V",
            "Galaxy Creeper",
            "the cool kid",
            "emmylou galleros",
            "Caleb Kellenberger",
            "Nitasha Salam",
            "Yuliana Lopez",
            "happyhorserider12",
            "Christopher Valencia",
            "Nedra Salha",
            "Jeffrey Richard",
            "fredyespflo",
            "Julie Hastings",
            "coolman574",
            "Ender Shard",
            "O.U.F.A LTU",
            "Zavvy Velazquez",
            "Aubrey M.",
            "Frank yu",
            "Colton Banks",
            "Dylan Rooney",
            "Jupiter Jaca",
            "kelvin",
            "Kate F",
            "CrackedCode",
            "Aaron Allan",
            "Kathryn Albitz",
            "Bob Wolcott",
            "Jason Boucher",
            "Jefry Sabillon",
            "LittleDisaster 1",
            "Sammy Hall",
            "Warren Barber",
            "Sysysly",
            "Quinn Natalini",
            "swag master",
            "Kelly Ailon",
            "ZeldaFan 115",
            "Alyssia Austwick",
            "Kerem Bingol",
            "JujuRocks1 n",
            "Melanie Nielsen",
            "Xander Balderas",
            "Shane Hanley",
            "Alex Tiberio",
            "michael scomazzon",
            "Coral Wasserman",
            "Kaytlynn Honga",
            "Bob Bobg",
            "Alex Kern",
            "dan2461001",
            "rca06",
            "A Sandwich",
            "Carlos Salazar",
            "Raphael Pallasigui",
            "Joe Dalton",
            "Sophia Viera",
            "Hannah Smith",
            "TheresNoH",
            "Matthew Whaley",
            "xmasCockatiel",
            "Golden Zackatron",
            "Cameron James",
            "camboy0313",
            "Silver Fox",
            "Shauna Megan",
            "Kimberly phillips",
            "Wyatt",
            "Dragonvenom_",
            "Cole!"
         }
      );
   }
}
