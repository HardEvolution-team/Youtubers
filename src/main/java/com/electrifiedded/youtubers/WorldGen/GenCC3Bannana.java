package com.electrifiedded.youtubers.WorldGen;

import com.electrifiedded.archaiclib.world.StagedWorld;
import com.electrifiedded.youtubers.YTItems;
import net.minecraft.util.math.BlockPos;
import com.electrifiedded.youtubers.RandUtil.RandomString;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntitySign;
import net.minecraft.util.WeightedRandom;
import net.minecraft.util.WeightedRandom.Item;
import net.minecraft.world.World;

public class GenCC3Bannana extends GenYoutuberStructure {
   public static List<Item> hashTags = new ArrayList<>();
   public static Random rand = new com.electrifiedded.archaiclib.util.FastRandom();

   public static void addTags(String[] names) {
      for (int a = 0; a < names.length; a++) {
         hashTags.add(new RandomString(names[a], 1));
      }
   }

   @Override
   public boolean generate(World world, Random random, BlockPos pos) {
      int[][][] bannana = new int[][][]{
         {
               {0, 0, 0, 0, 0, 0, 0, 0, 0},
               {0, 0, 0, 0, 1, 1, 0, 0, 0},
               {0, 0, 1, 1, 1, 1, 0, 0, 0},
               {0, 1, 1, 1, 1, 0, 0, 0, 0},
               {0, 1, 1, 1, 2, 2, 0, 0, 0},
               {1, 1, 1, 1, 2, 3, 0, 0, 0},
               {1, 1, 1, 1, 0, 0, 0, 0, 0},
               {1, 1, 1, 1, 3, 0, 0, 0, 0},
               {1, 1, 1, 1, 0, 0, 0, 0, 0},
               {0, 1, 1, 1, 0, 0, 0, 0, 0},
               {0, 1, 1, 1, 1, 0, 0, 0, 0},
               {0, 0, 1, 1, 1, 1, 0, 0, 0},
               {0, 0, 0, 0, 1, 1, 0, 0, 0},
               {0, 0, 0, 0, 0, 0, 0, 0, 0}
         },
         {
               {0, 0, 0, 0, 1, 1, 1, 0, 0},
               {0, 0, 1, 1, 1, 1, 1, 4, 0},
               {0, 1, 5, 5, 5, 1, 1, 0, 0},
               {1, 5, 5, 5, 5, 1, 0, 0, 0},
               {1, 5, 5, 5, 1, 2, 0, 0, 0},
               {1, 5, 5, 5, 1, 2, 0, 0, 0},
               {1, 5, 5, 5, 1, 0, 0, 0, 0},
               {1, 5, 5, 5, 1, 0, 0, 0, 0},
               {1, 5, 5, 5, 1, 3, 0, 0, 0},
               {1, 5, 5, 5, 1, 0, 0, 0, 0},
               {1, 5, 5, 5, 5, 1, 0, 0, 0},
               {0, 1, 5, 5, 5, 1, 1, 0, 0},
               {0, 0, 1, 1, 1, 1, 1, 4, 0},
               {0, 0, 0, 0, 1, 1, 1, 0, 0}
         },
         {
               {0, 0, 1, 1, 5, 1, 1, 4, 0},
               {0, 1, 1, 1, 5, 1, 1, 4, 4},
               {1, 5, 5, 5, 5, 1, 1, 4, 0},
               {1, 5, 5, 5, 5, 1, 1, 0, 0},
               {1, 5, 5, 5, 1, 0, 0, 0, 0},
               {1, 5, 5, 5, 1, 0, 0, 0, 0},
               {1, 5, 5, 5, 1, 0, 0, 0, 0},
               {1, 5, 5, 5, 1, 0, 0, 0, 0},
               {1, 5, 5, 5, 1, 3, 0, 0, 0},
               {1, 5, 5, 5, 1, 0, 0, 0, 0},
               {1, 5, 5, 5, 5, 1, 1, 0, 0},
               {1, 5, 5, 5, 5, 1, 1, 4, 0},
               {0, 1, 1, 1, 5, 1, 1, 4, 4},
               {0, 0, 1, 1, 5, 1, 1, 4, 0}
         },
         {
               {0, 0, 0, 0, 1, 1, 1, 0, 0},
               {0, 0, 1, 1, 1, 1, 1, 4, 0},
               {0, 1, 5, 5, 5, 1, 1, 0, 0},
               {1, 5, 5, 5, 5, 1, 0, 0, 0},
               {1, 5, 5, 5, 1, 2, 0, 0, 0},
               {1, 5, 5, 5, 1, 2, 0, 0, 0},
               {1, 5, 5, 5, 1, 0, 0, 0, 0},
               {1, 5, 5, 5, 1, 0, 0, 0, 0},
               {1, 5, 5, 5, 1, 3, 0, 0, 0},
               {1, 5, 5, 5, 1, 0, 0, 0, 0},
               {1, 5, 5, 5, 5, 1, 0, 0, 0},
               {0, 1, 5, 5, 5, 1, 1, 0, 0},
               {0, 0, 1, 1, 1, 1, 1, 4, 0},
               {0, 0, 0, 0, 1, 1, 1, 0, 0}
         },
         {
               {0, 0, 0, 0, 0, 0, 0, 0, 0},
               {0, 0, 0, 0, 1, 1, 0, 0, 0},
               {0, 0, 1, 1, 1, 1, 0, 0, 0},
               {0, 1, 1, 1, 1, 0, 0, 0, 0},
               {0, 1, 1, 1, 2, 3, 0, 0, 0},
               {1, 1, 1, 1, 2, 2, 0, 0, 0},
               {1, 1, 1, 1, 0, 0, 0, 0, 0},
               {1, 1, 1, 1, 3, 0, 0, 0, 0},
               {1, 1, 1, 1, 0, 0, 0, 0, 0},
               {0, 1, 1, 1, 0, 0, 0, 0, 0},
               {0, 1, 1, 1, 1, 0, 0, 0, 0},
               {0, 0, 1, 1, 1, 1, 0, 0, 0},
               {0, 0, 0, 0, 1, 1, 0, 0, 0},
               {0, 0, 0, 0, 0, 0, 0, 0, 0}
         }
      };
      int i = pos.getX();
      int j = pos.getY();
      int k = pos.getZ();

      for (int a = 0; a < 5; a++) {
         for (int b = 0; b < 14; b++) {
            for (int c = 0; c < 9; c++) {
               switch (bannana[a][b][c]) {
                  case 1:
                     this.setBlockState(world, pos.add(4 - c, 16 - b, -2 + a), Blocks.WOOL, 4);
                     break;
                  case 2:
                     this.setBlockState(world, pos.add(4 - c, 16 - b, -2 + a), Blocks.WOOL, 0);
                     break;
                  case 3:
                     this.setBlockState(world, pos.add(4 - c, 16 - b, -2 + a), Blocks.WOOL, 15);
                     break;
                  case 4:
                     this.setBlockState(world, pos.add(4 - c, 16 - b, -2 + a), Blocks.STAINED_HARDENED_CLAY, 12);
                     break;
                  case 5:
                     this.setBlockToAir(world, pos.add(4 - c, 16 - b, -2 + a));
               }
            }
         }
      }

      this.setBlockState(world, pos.add(1, 0, 0), Blocks.WOOL, 15);
      this.setBlockState(world, pos.add(1, 1, 0), Blocks.WOOL, 15);
      this.setBlockState(world, pos.add(1, 2, 0), Blocks.WOOL, 15);
      this.genBox(world, Blocks.WOOL, 15, 0, i - 2, j, k - 4, i, j, k - 3);
      this.genBox(world, Blocks.WOOL, 15, 0, i - 2, j, k + 3, i, j, k + 4);
      this.genBox(world, Blocks.WOOL, 15, 0, i, j, k - 3, i, j + 5, k - 3);
      this.genBox(world, Blocks.WOOL, 15, 0, i, j, k + 3, i, j + 5, k + 3);
      this.setBlockState(world, pos.add(2, 11, 3), Blocks.WOOL, 15);
      this.setBlockState(world, pos.add(2, 12, 4), Blocks.WOOL, 15);
      this.setBlockState(world, pos.add(2, 13, 5), Blocks.WOOL, 15);
      this.setBlockState(world, pos.add(2, 13, 6), Blocks.WOOL, 15);
      this.setBlockState(world, pos.add(2, 14, 5), Blocks.WOOL, 15);
      this.setBlockState(world, pos.add(2, 11, -3), Blocks.WOOL, 15);
      this.setBlockState(world, pos.add(2, 12, -4), Blocks.WOOL, 15);
      this.setBlockState(world, pos.add(2, 13, -5), Blocks.WOOL, 15);
      this.setBlockState(world, pos.add(2, 13, -6), Blocks.WOOL, 15);
      this.setBlockState(world, pos.add(2, 14, -5), Blocks.WOOL, 15);
      this.setBlockState(world, pos.add(0, 0, 0), Blocks.LADDER, 4);
      this.setBlockState(world, pos.add(0, 1, 0), Blocks.LADDER, 4);
      this.setBlockState(world, pos.add(0, 2, 0), Blocks.LADDER, 4);
      this.setBlockState(world, pos.add(0, 3, 0), Blocks.LADDER, 4);
      this.setBlockState(world, pos.add(0, 4, 0), Blocks.LADDER, 4);
      this.setBlockState(world, pos.add(0, 13, 0), Blocks.LADDER, 5);
      this.setBlockState(world, pos.add(0, 14, 0), Blocks.LADDER, 5);
      this.setBlockState(world, pos.add(0, 15, 0), Blocks.LADDER, 5);
      this.setBlockState(world, pos.add(0, 16, 0), Blocks.LADDER, 5);
      this.genBox(world, Blocks.WOOL, 4, 0, i + 1, j + 7, k - 1, i + 3, j + 7, k + 1);
      this.genBox(world, Blocks.WOOL, 4, 0, i + 1, j + 11, k - 1, i + 3, j + 11, k + 1);
      this.genBox(world, Blocks.LADDER, 4, 0, i + 3, j + 5, k, i + 3, j + 11, k);
      this.setBlockState(world, pos.add(1, 7, -1), Blocks.GLOWSTONE, 0);
      this.setBlockState(world, pos.add(1, 7, 1), Blocks.GLOWSTONE, 0);
      this.setBlockState(world, pos.add(1, 11, -1), Blocks.GLOWSTONE, 0);
      this.setBlockState(world, pos.add(1, 11, 1), Blocks.GLOWSTONE, 0);
      this.setBlockState(world, new BlockPos(i + 2, j + 8, k - 1), Blocks.BED, 11);
      this.setBlockState(world, new BlockPos(i + 1, j + 8, k - 1), Blocks.BED, 3);
      this.setBlockState(world, new BlockPos(i + 2, j + 8, k + 1), Blocks.BED, 11);
      this.setBlockState(world, new BlockPos(i + 1, j + 8, k + 1), Blocks.BED, 3);
      this.setBlockState(world, pos.add(3, 8, 1), Blocks.FURNACE, 3);
      this.setBlockState(world, pos.add(3, 8, -1), Blocks.CRAFTING_TABLE, 0);
      this.addChest(world, random, i + 3, j + 12, k - 1);
      this.addChest(world, random, i + 2, j + 12, k - 1);
      this.addChest(world, random, i + 3, j + 12, k + 1);
      this.addChest(world, random, i + 2, j + 12, k + 1);
      this.setSign(world, i, j + 5, k - 1, 3);
      this.setSign(world, i, j + 6, k - 1, 3);
      this.setSign(world, i + 1, j + 5, k - 1, 3);
      this.setSign(world, i + 1, j + 6, k - 1, 3);
      this.setSign(world, i + 2, j + 5, k - 1, 3);
      this.setSign(world, i + 2, j + 6, k - 1, 3);
      this.setSign(world, i + 3, j + 6, k - 1, 3);
      this.setSign(world, i, j + 5, k + 1, 2);
      this.setSign(world, i, j + 6, k + 1, 2);
      this.setSign(world, i + 1, j + 5, k + 1, 2);
      this.setSign(world, i + 1, j + 6, k + 1, 2);
      this.setSign(world, i + 2, j + 5, k + 1, 2);
      this.setSign(world, i + 2, j + 6, k + 1, 2);
      this.setSign(world, i + 3, j + 6, k + 1, 2);
      return true;
   }

   public void setSign(World world, int x, int y, int z, int dir) {
      StagedWorld.setBlock(world, x, y, z, Blocks.WALL_SIGN, dir, 3);
      TileEntitySign te = (TileEntitySign)StagedWorld.getTileEntity(world, x, y, z);
      te.signText[1] = new net.minecraft.util.text.TextComponentString(getTag(world));
      te.signText[2] = new net.minecraft.util.text.TextComponentString(GenFansion.getName(world));
   }

   public static String getTag(World world) {
      RandomString RText = (RandomString)WeightedRandom.getRandomItem(rand, hashTags);
      return RText.text == null ? "#NULL" : RText.text;
   }

   private void addChest(World world, Random random, int i, int j, int k) {
      this.setBlockState(world, new BlockPos(i, j, k), Blocks.CHEST, 0);
      TileEntityChest chestTE = (TileEntityChest)StagedWorld.getTileEntity(world, i, j, k);
      if (chestTE != null) {
         int numb = random.nextInt(20) + 10;

         for (int a = 0; a < numb; a++) {
            chestTE.setInventorySlotContents(random.nextInt(27), new ItemStack(YTItems.Money[random.nextInt(4)], random.nextInt(8) + 1));
         }

         for (int a = 0; a < 7; a++) {
            if (random.nextInt(7) == 0) {
               chestTE.setInventorySlotContents(random.nextInt(27), new ItemStack(Items.IRON_INGOT, random.nextInt(18) + 1));
            }

            if (random.nextInt(10) == 0) {
               chestTE.setInventorySlotContents(random.nextInt(27), new ItemStack(Items.GOLD_INGOT, random.nextInt(5) + 1));
            }

            if (random.nextInt(10) == 0) {
               chestTE.setInventorySlotContents(random.nextInt(27), new ItemStack(Blocks.OBSIDIAN, random.nextInt(5) + 1));
            }

            if (random.nextInt(15) == 0) {
               chestTE.setInventorySlotContents(random.nextInt(27), new ItemStack(Items.DIAMOND, random.nextInt(3) + 1));
            }
         }
      }
   }

   static {
      addTags(
         new String[]{
            "#PigTARDIS",
            "#FlyingTARDIS",
            "#Fail",
            "#PacManace",
            "#Transformium",
            "#PandoraTurtle",
            "#LuckyBlock",
            "#DaBuz",
            "#Cobble=Life",
            "#Shrekt",
            "#Vampire",
            "#Death",
            "#Vurp",
            "#BoringDay",
            "#DaveIsBack",
            "#Chicksplosion",
            "#Berger",
            "#NyanPigs",
            "#CrazyCraft3.0",
            "#Insanity",
            "#Wormz",
            "#EpicMine",
            "#WeCanFly",
            "#Oooops",
            "#TinyHead",
            "#Goat",
            "#SpeedIsKey",
            "#DaveDied",
            "#DaveIsBackAgain",
            "#Swodilypoop",
            "#SkyIsDead",
            "#BigBurtha",
            "#FallenKing",
            "#FallenQueen",
            "#Mobzilla",
            "#Jazz",
            "#Excrement",
            "#TheSims",
            "#Youtubers+",
            "#IMadeThis",
            "#Iron4Dayz",
            "#DiamondBath",
            "#FireEverywhere",
            "#SSundee",
            "#PortalGun",
            "#Beavers",
            "#Termites",
            "#AnotherWorld",
            "#TheNether",
            "#TheEnd",
            "#EnderDragon",
            "#Wither",
            "#Barrels",
            "#TrojanHorse",
            "#Pacman",
            "#NightTerrors",
            "#Creeper",
            "#DaMaveric",
            "#CreeperPet",
            "#Bannana",
            "#Wichery",
            "#Magic",
            "#PunchingTrees",
            "#PeskyAnts",
            "#Burgar",
            "#GameTime",
            "#Energon",
            "#Qwop",
            "#NoThisIsPatric",
            "#Trollolollolo",
            "#Fries",
            "#Soda",
            "#ToTheMoon",
            "#BugFix",
            "#Cats",
            "#I<3U",
            "#TogetherForever",
            "#Pokemon",
            "#SpellingError",
            "#TBD"
         }
      );
   }
}
