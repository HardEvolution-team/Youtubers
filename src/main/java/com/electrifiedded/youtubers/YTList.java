package com.electrifiedded.youtubers;

import net.minecraft.init.Enchantments;
import com.electrifiedded.youtubers.Entity.EntityBomby;
import com.electrifiedded.youtubers.Entity.EntityCraigTheMailman;
import com.electrifiedded.youtubers.Entity.EntityGrimm;
import com.electrifiedded.youtubers.Entity.EntityTrayourus;
import com.electrifiedded.youtubers.Entity.EntityYoutuber;
import com.electrifiedded.youtubers.Entity.Youtuber;
import com.electrifiedded.youtubers.Entity.AI.EntityAIAlterNames;
import com.electrifiedded.youtubers.Entity.AI.EntityAIAvoidEntityYT;
import com.electrifiedded.youtubers.Entity.AI.EntityAIAvoidYoutuber;
import com.electrifiedded.youtubers.Entity.AI.EntityAIstaywithYoutuber;
import com.electrifiedded.youtubers.Items.Spawners.Item_cLUCKey_spawner;
import net.minecraft.util.math.BlockPos;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.math.MathHelper;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.world.World;

public class YTList {
   public static Youtuber[] YoutuberList;
   public static List<Youtuber> ListYT = new ArrayList<>();
   public static List<YoutuberSpawn> ListSP = new ArrayList<>();

   public static void Load() {
      YoutuberSpawn BChermicraft = new YoutuberSpawn().setMLim(4);
      YoutuberSpawn BCmindcrack = new YoutuberSpawn().setMLim(4);
      YoutuberSpawn BCtcrafted = new YoutuberSpawn().setMLim(4);
      YoutuberSpawn BCtrolls = new YoutuberSpawn();
      YoutuberSpawn BCyoggs = new YoutuberSpawn().setMLim(3);
      YoutuberSpawn BCother = new YoutuberSpawn().setMLim(2);
      YoutuberSpawn BCstory = new YoutuberSpawn().setMLim(1);
      YoutuberSpawn BCstamps = new YoutuberSpawn().setMLim(2);
      YoutuberSpawn BCpack = new YoutuberSpawn().setMLim(2);
      YoutuberSpawn BClang = new YoutuberSpawn().setMLim(1);
      YoutuberSpawn BCcreature = new YoutuberSpawn().setMLim(4);
      if (YTConfig.Hermitcraft) {
         ListYT.add(new Youtuber("", "Hermitcraft", Youtubers.RGBtoInt(150, 28, 137), Youtubers.RGBtoInt(222, 222, 0), 2, "Biffa2001") {
            @Override
            public void Add_Trades(MerchantRecipeList recipeList, Random random, int Subtype) {
               YTList.Add_Generic_Trades(recipeList, random);
            }
         });
         ListYT.add(new Youtuber("", "Hermitcraft", Youtubers.RGBtoInt(106, 187, 249), Youtubers.RGBtoInt(106, 187, 249), 7, "Keralis1") {
            @Override
            public void Add_Trades(MerchantRecipeList recipeList, Random random, int Subtype) {
               YTList.Add_Generic_Trades(recipeList, random);
               ItemStack item = new ItemStack(Items.COOKED_CHICKEN, 8);
               item.setStackDisplayName("Keralis Fried Chicken");
               recipeList.add(new MerchantRecipeYT(new ItemStack(YTItems.Money[2], 3), item.copy()));
               item = new ItemStack(Items.COOKED_CHICKEN, 4);
               item.setStackDisplayName("Keralis Spicy Chicken");
               item.addEnchantment(Enchantments.FIRE_ASPECT, 1);
               recipeList.add(new MerchantRecipeYT(new ItemStack(YTItems.Money[5], 3), item.copy()));
            }

            @Override
            public void dropItems(EntityYoutuber entityYoutuber, int par1) {
               ItemStack item = new ItemStack(Items.COOKED_CHICKEN, com.electrifiedded.archaiclib.util.FastRandom.shared().nextInt(4 + 2 * par1));
               item.setStackDisplayName("Keralis Fried Chicken");
               Youtubers.Drop_Item_Entity(entityYoutuber, item.copy());
            }
         });
         ListYT.add(new Youtuber("", "Hermitcraft", Youtubers.RGBtoInt(255, 0, 0), Youtubers.RGBtoInt(255, 255, 255), 7, "KingDaddyDMAC") {
            @Override
            public void Add_Trades(MerchantRecipeList recipeList, Random random, int Subtype) {
               YTList.Add_Generic_Trades(recipeList, random);
            }
         });
         ListYT.add(new Youtuber("", "Hermitcraft", Youtubers.RGBtoInt(15, 208, 56), Youtubers.RGBtoInt(191, 135, 49), 4, "Monkeyfarm") {
            @Override
            public void Add_Trades(MerchantRecipeList recipeList, Random random, int Subtype) {
               YTList.Add_Generic_Trades(recipeList, random);
            }
         });
         ListYT.add(new Youtuber("", "Hermitcraft", Youtubers.RGBtoInt(5, 97, 14), Youtubers.RGBtoInt(159, 159, 159), 3, "MrMumbo") {
            @Override
            public void Add_Trades(MerchantRecipeList recipeList, Random random, int Subtype) {
               YTList.Add_Generic_Trades(recipeList, random);
               recipeList.add(new MerchantRecipeYT(new ItemStack(YTItems.Money[4], 7), new ItemStack(Blocks.OAK_FENCE, 10, 0)));
               recipeList.add(new MerchantRecipeYT(new ItemStack(YTItems.Money[4], 7), new ItemStack(Blocks.STONEBRICK, 8, 0)));
               recipeList.add(new MerchantRecipeYT(new ItemStack(YTItems.Money[4], 7), new ItemStack(Blocks.STONE_SLAB, 16, 0)));
               YTList.Add_Redstone_Trades(recipeList, random);
            }

            @Override
            public void dropItems(EntityYoutuber entityYoutuber, int par1) {
               Youtubers.Drop_Item_Entity(entityYoutuber, new ItemStack(Blocks.VINE, com.electrifiedded.archaiclib.util.FastRandom.shared().nextInt(4 + 2 * par1)));
            }
         });
         ListYT.add(new Youtuber("", "Hermitcraft", Youtubers.RGBtoInt(0, 0, 0), Youtubers.RGBtoInt(240, 190, 0), 1, "King_Happy") {
            @Override
            public void Add_Trades(MerchantRecipeList recipeList, Random random, int Subtype) {
               YTList.Add_Generic_Trades(recipeList, random);
            }
         });
         ListYT.add(new Youtuber("", "Hermitcraft", Youtubers.RGBtoInt(24, 157, 20), Youtubers.RGBtoInt(250, 253, 22), 0, "ZombieCleo") {
            @Override
            public void Add_Trades(MerchantRecipeList recipeList, Random random, int Subtype) {
               YTList.Add_Generic_Trades(recipeList, random);
            }
         });
         ListYT.add(new Youtuber("", "Hermitcraft", Youtubers.RGBtoInt(50, 119, 42), Youtubers.RGBtoInt(118, 118, 118), 2, "Xisuma") {
            @Override
            public void Add_Trades(MerchantRecipeList recipeList, Random random, int Subtype) {
               YTList.Add_Generic_Trades(recipeList, random);
            }
         });
         ListYT.add(new Youtuber("", "Hermitcraft", Youtubers.RGBtoInt(233, 216, 55), Youtubers.RGBtoInt(40, 44, 39), 7, "FalseSymmetry"));
         ListYT.add(new Youtuber("", "Hermitcraft", Youtubers.RGBtoInt(231, 206, 39), Youtubers.RGBtoInt(57, 57, 57), 7, "TangoTek"));
         ListYT.add(new Youtuber("", "Hermitcraft", Youtubers.RGBtoInt(16, 17, 21), Youtubers.RGBtoInt(130, 126, 125), 8, "ImpulseSV"));
         ListYT.add(new Youtuber("", "Hermitcraft", Youtubers.RGBtoInt(0, 0, 0), Youtubers.RGBtoInt(255, 255, 255), 0, "Zueljin"));
         ListYT.add(new Youtuber("", "Hermitcraft", Youtubers.RGBtoInt(0, 0, 0), Youtubers.RGBtoInt(255, 255, 255), 0, "Pythongb"));
         ListSP.add(new YoutuberSpawn(YTStructures.Map.get("KFC"), "Keralis1"));
         ListSP.add(new YoutuberSpawn(YTStructures.Map.get("BigTree"), "Monkeyfarm"));
         ListSP.add(new YoutuberSpawn(YTStructures.Map.get("Base"), "MrMumbo"));
         ListSP.add(new YoutuberSpawn(YTStructures.Map.get("Wasteland"), "Xisuma"));
         BChermicraft.addYTM("Biffa2001");
         BChermicraft.addYTM("KingDaddyDMAC");
         BChermicraft.addYTM("King_Happy");
         BChermicraft.addYTM("ZombieCleo");
         BChermicraft.addYTM("FalseSymmetry");
         BChermicraft.addYTM("TangoTek");
         BChermicraft.addYTM("Zueljin");
         BChermicraft.addYTM("Pythongb");
      }

      if (YTConfig.Hermitcraft || YTConfig.Mindcrack) {
         ListYT.add(new Youtuber("", "Hermitcraft Mindcrack", Youtubers.RGBtoInt(0, 174, 0), Youtubers.RGBtoInt(50, 50, 50), 1, "docm77"));
         ListYT.add(new Youtuber("", "Hermitcraft Mindcrack", Youtubers.RGBtoInt(0, 0, 0), Youtubers.RGBtoInt(255, 255, 255), 0, "aureylian"));
         ListYT.add(new Youtuber("", "Hermitcraft Mindcrack", Youtubers.RGBtoInt(44, 49, 63), Youtubers.RGBtoInt(118, 132, 111), 3, "EthosLab") {
            @Override
            public void Add_Trades(MerchantRecipeList recipeList, Random random, int Subtype) {
               YTList.Add_Generic_Trades(recipeList, random);
            }
         });
         BChermicraft.addYTM("docm77");
         BChermicraft.addYTM("aureylian");
         BChermicraft.addYTM("EthosLab");
         BCmindcrack.addYTM("docm77");
         BCmindcrack.addYTM("aureylian");
         BCmindcrack.addYTM("EthosLab");
      }

      if (YTConfig.Mindcrack) {
         ListYT.add(new Youtuber("", "Mindcrack", Youtubers.RGBtoInt(255, 255, 255), Youtubers.RGBtoInt(255, 255, 255), 7, "BdoubleO100") {
            @Override
            public void Add_Trades(MerchantRecipeList recipeList, Random random, int Subtype) {
               YTList.Add_Generic_Trades(recipeList, random);
            }
         });
         ListYT.add(new Youtuber("", "Mindcrack", Youtubers.RGBtoInt(204, 204, 204), Youtubers.RGBtoInt(0, 0, 0), 6, "Guude") {
            @Override
            public void Add_Trades(MerchantRecipeList recipeList, Random random, int Subtype) {
               YTList.Add_Generic_Trades(recipeList, random);
            }
         });
         ListYT.add(new Youtuber("", "Mindcrack", Youtubers.RGBtoInt(65, 36, 8), Youtubers.RGBtoInt(148, 97, 47), 2, "PauseUnpause") {
            @Override
            public void Add_Trades(MerchantRecipeList recipeList, Random random, int Subtype) {
               YTList.Add_Generic_Trades(recipeList, random);
            }
         });
         ListYT.add(new Youtuber("", "Mindcrack", Youtubers.RGBtoInt(255, 0, 0), Youtubers.RGBtoInt(0, 0, 255), 5, "Sethbling") {
            @Override
            public void Add_Trades(MerchantRecipeList recipeList, Random random, int Subtype) {
               YTList.Add_Generic_Trades(recipeList, random);
               YTList.Add_Redstone_Trades(recipeList, random);
            }

            @Override
            public void dropItems(EntityYoutuber entityYoutuber, int par1) {
               Youtubers.Drop_Item_Entity(entityYoutuber, new ItemStack(Items.REDSTONE, com.electrifiedded.archaiclib.util.FastRandom.shared().nextInt(3 + 2 * par1) + 5));
               Youtubers.Drop_Item_Entity(entityYoutuber, new ItemStack(Blocks.REDSTONE_TORCH, com.electrifiedded.archaiclib.util.FastRandom.shared().nextInt(3 + 2 * par1) + 1));
               Youtubers.Drop_Item_Entity(entityYoutuber, new ItemStack(Items.REPEATER, com.electrifiedded.archaiclib.util.FastRandom.shared().nextInt(3 + 2 * par1) + 1));
               Youtubers.Drop_Item_Entity(entityYoutuber, new ItemStack(Items.COMPARATOR, com.electrifiedded.archaiclib.util.FastRandom.shared().nextInt(2 + 2 * par1) + 1));
            }
         });
         ListYT.add(new Youtuber("", "Mindcrack", Youtubers.RGBtoInt(151, 2, 2), Youtubers.RGBtoInt(0, 122, 0), 4, "Vechs"));
         ListYT.add(new Youtuber("", "Mindcrack", Youtubers.RGBtoInt(53, 15, 0), Youtubers.RGBtoInt(60, 60, 60), 7, "Nebris"));
         ListYT.add(new Youtuber("", "Mindcrack", Youtubers.RGBtoInt(254, 254, 254), Youtubers.RGBtoInt(154, 16, 1), 0, "VintageBeef"));
         ListYT.add(new Youtuber("", "Mindcrack", Youtubers.RGBtoInt(132, 117, 77), Youtubers.RGBtoInt(39, 51, 88), 5, "kurtmac"));
         ListYT.add(new Youtuber("", "Mindcrack", Youtubers.RGBtoInt(128, 91, 17), Youtubers.RGBtoInt(204, 204, 204), 5, "Genericb"));
         ListSP.add(
            new YoutuberSpawn(YTStructures.Map.get("BuildGame"), "Sethbling")
               .addSTM("PizzaSpleef")
               .addYT2("Guude")
               .addYT2("BdoubleO100")
               .addYT2("Vechs")
               .addYT2("Nebris")
               .addYT2("kurtmac")
               .addYT2("Genericb")
               .set2Lim(3)
         );
         BCmindcrack.addYTM("BdoubleO100");
         BCmindcrack.addYTM("Guude");
         BCmindcrack.addYTM("PauseUnpause");
         BCmindcrack.addYTM("Vechs");
         BCmindcrack.addYTM("Nebris");
         BCmindcrack.addYTM("VintageBeef");
         BCmindcrack.addYTM("kurtmac");
         BCmindcrack.addYTM("Genericb");
      }

      if (YTConfig.TeamCrafted || YTConfig.Crundee) {
         ListYT.add(new Youtuber("", "Crundee", "MrCrainer") {
            @Override
            public void Add_Trades(MerchantRecipeList recipeList, Random random, int Subtype) {
               YTList.Add_Generic_Trades(recipeList, random);
               recipeList.add(new MerchantRecipeYT(new ItemStack(YTItems.Money[4], 20), new ItemStack(YTItems.CrainerShirt)));
               YTList.Add_cLUCKey_Trades(recipeList, new int[]{0, 1, 2, 3, 4, 5, 6, 12, 18, 24, 30});
            }

            @Override
            public void Add_AI(EntityYoutuber youtuber) {
               youtuber.tasks.addTask(1, new EntityAIstaywithYoutuber(youtuber, "Ssundee", 50.0, 100.0, 1.0));
            }
         });
         ListYT.add(new Youtuber("", "Crundee TeamCrafted", Youtubers.RGBtoInt(255, 255, 0), Youtubers.RGBtoInt(5, 28, 46), 2, "Ssundee", "_Derp") {
            @Override
            public void Add_Trades(MerchantRecipeList recipeList, Random random, int Subtype) {
               if (Subtype == 1) {
                  recipeList.add(new MerchantRecipeYT(new ItemStack(Blocks.DIRT, 1), new ItemStack(Blocks.GRASS, 1)));
                  ItemStack item = new ItemStack(Items.STICK);
                  item.setStackDisplayName("Stick Of Awesome");
                  item.addEnchantment(Enchantments.AQUA_AFFINITY, 1);
                  recipeList.add(new MerchantRecipeYT(new ItemStack(YTItems.Money[0], 20), new ItemStack(YTItems.Money[3], 1), item.copy()));
                  item = new ItemStack(Blocks.CACTUS);
                  item.setStackDisplayName("Pokey Plant");
                  item.addEnchantment(Enchantments.THORNS, 1);
                  recipeList.add(new MerchantRecipeYT(new ItemStack(YTItems.Money[1], 4), new ItemStack(YTItems.Money[2], 2), item.copy()));
                  item = new ItemStack(Items.PAPER);
                  item.setStackDisplayName("Spider Swatter");
                  item.addEnchantment(Enchantments.BANE_OF_ARTHROPODS, 1);
                  recipeList.add(new MerchantRecipeYT(new ItemStack(YTItems.Money[3], 1), new ItemStack(YTItems.Money[0], 20), item.copy()));
                  item = new ItemStack(Items.POISONOUS_POTATO);
                  item.setStackDisplayName("Protective Potato");
                  item.addEnchantment(Enchantments.PROTECTION, 1);
                  recipeList.add(new MerchantRecipeYT(new ItemStack(YTItems.Money[2], 2), new ItemStack(YTItems.Money[1], 4), item.copy()));
               }

               ItemStack item = new ItemStack(Blocks.COBBLESTONE, 64);
               item.setStackDisplayName("LIFE");
               recipeList.add(new MerchantRecipeYT(new ItemStack(YTItems.Money[3], 1), new ItemStack(YTItems.Money[2], 4), item.copy()));
               YTList.Add_Generic_Trades(recipeList, random);
               YTList.Add_cLUCKey_Trades(recipeList, new int[]{0, 1, 2, 3, 4, 5, 6, 12, 18, 24, 30});
            }

            @Override
            public void dropItems(EntityYoutuber entityYoutuber, int par1) {
               if (entityYoutuber.getSubtype() == 1) {
                  Random rnd = new com.electrifiedded.archaiclib.util.FastRandom();

                  for (int i = 0; i < 216; i++) {
                     ItemStack item;
                     switch (rnd.nextInt(22)) {
                        case 0:
                           item = new ItemStack(Blocks.DIRT);
                           break;
                        case 1:
                           item = new ItemStack(Blocks.SAND);
                           break;
                        case 2:
                           item = new ItemStack(Blocks.GRAVEL);
                           break;
                        case 3:
                           item = new ItemStack(Items.MELON);
                           break;
                        case 4:
                           item = new ItemStack(Items.STICK);
                           break;
                        case 5:
                           item = new ItemStack(Items.PAPER);
                           break;
                        case 6:
                           item = new ItemStack(Items.BOAT);
                           break;
                        case 7:
                           item = new ItemStack(Items.POISONOUS_POTATO);
                           break;
                        case 8:
                           item = new ItemStack(Items.CARROT_ON_A_STICK);
                           break;
                        case 9:
                           item = new ItemStack(Items.SPIDER_EYE);
                           break;
                        case 10:
                           item = new ItemStack(Items.ARROW);
                           break;
                        case 11:
                           item = new ItemStack(Items.BOWL);
                           break;
                        case 12:
                           item = new ItemStack(Items.COOKIE);
                           break;
                        case 13:
                           item = new ItemStack(Items.ROTTEN_FLESH);
                           break;
                        case 14:
                           item = new ItemStack(Items.SUGAR);
                           break;
                        case 15:
                           item = new ItemStack(Items.CAKE);
                           break;
                        case 16:
                           item = new ItemStack(Blocks.MOB_SPAWNER);
                           break;
                        case 17:
                           item = new ItemStack(Blocks.MONSTER_EGG, 1, rnd.nextInt(5));
                           break;
                        case 18:
                           item = new ItemStack(Blocks.SPONGE);
                           break;
                        case 19:
                           item = new ItemStack(Blocks.TORCH);
                           break;
                        case 20:
                           item = new ItemStack(Blocks.GRASS);
                           break;
                        default:
                           item = new ItemStack(Items.CAULDRON);
                     }

                     item.setStackDisplayName("Item Number:" + rnd.nextInt(4000));
                     Youtubers.Drop_Item_Entity(entityYoutuber, item.copy());
                  }
               }
            }
         });
         ListSP.add(new YoutuberSpawn(YTStructures.Map.get("CrainerCastle"), "MrCrainer"));
         ListSP.add(new YoutuberSpawn(YTStructures.Map.get("FloatingCastle"), "Ssundee").addSTM("jail"));
         ListSP.add(new YoutuberSpawn(YTStructures.Map.get("SkyFactory"), "Ssundee").addYTM("MrCrainer"));
      }

      if (YTConfig.TeamCrafted || YTConfig.SkyGames) {
         ListYT.add(
            new Youtuber("", "TeamCrafted SkyGames", Youtubers.RGBtoInt(222, 222, 0), Youtubers.RGBtoInt(235, 235, 139), 4, "SkyTheKidRS", "_Pixelmon") {
               @Override
               public void Add_Trades(MerchantRecipeList recipeList, Random random, int Subtype) {
                  YTList.Add_Generic_Trades(recipeList, random);
                  ItemStack item = new ItemStack(YTItems.BudderSword);
                  item.addEnchantment(Enchantments.SHARPNESS, 10);
                  item.addEnchantment(Enchantments.LOOTING, 20);
                  item.addEnchantment(Enchantments.UNBREAKING, 20);
                  item.addEnchantment(Enchantments.FIRE_ASPECT, 5);
                  recipeList.add(new MerchantRecipeYT(new ItemStack(YTItems.Money[12], 3), item.copy()));
               }

               @Override
               public void Add_AI(EntityYoutuber youtuber) {
                  youtuber.tasks.addTask(1, new EntityAIAvoidEntityYT(youtuber, EntitySquid.class, 8.0F, 0.6, 0.6));
                  youtuber.tasks.addTask(1, new EntityAIAlterNames(youtuber, "Gold", "Butter", 6.0));
                  youtuber.tasks.addTask(1, new EntityAIAlterNames(youtuber, "Bodil40", "Boatdil40", 6.0));
                  youtuber.tasks.addTask(1, new EntityAIAlterNames(youtuber, "Squid", "DEMONSPAWN", 15.0));
               }

               @Override
               public void dropItems(EntityYoutuber entityYoutuber, int par1) {
                  Youtubers.Drop_Item_Entity(entityYoutuber, new ItemStack(YTBlocks.blockButter, com.electrifiedded.archaiclib.util.FastRandom.shared().nextInt(8 + 4 * par1)));
               }
            }
         );
         ListSP.add(new YoutuberSpawn(YTStructures.Map.get("Butter"), "SkyTheKidRS"));
      }

      if (YTConfig.TeamCrafted) {
         ListYT.add(new Youtuber("", "TeamCrafted", Youtubers.RGBtoInt(36, 184, 21), Youtubers.RGBtoInt(27, 111, 19), 3, "Bashurverse") {
            @Override
            public void Add_Trades(MerchantRecipeList recipeList, Random random, int Subtype) {
               YTList.Add_Generic_Trades(recipeList, random);
               recipeList.add(new MerchantRecipeYT(new ItemStack(YTItems.Money[3], 7), new ItemStack(Items.MELON, 16, 0)));
               recipeList.add(new MerchantRecipeYT(new ItemStack(YTItems.Money[3], 7), new ItemStack(Items.MELON_SEEDS, 16, 0)));
               recipeList.add(new MerchantRecipeYT(new ItemStack(YTItems.Money[3], 7), new ItemStack(Blocks.MELON_BLOCK, 4, 0)));
            }

            @Override
            public void dropItems(EntityYoutuber entityYoutuber, int par1) {
               Youtubers.Drop_Item_Entity(entityYoutuber, new ItemStack(Items.MELON, com.electrifiedded.archaiclib.util.FastRandom.shared().nextInt(4 + 2 * par1)));
            }
         });
         ListYT.add(new Youtuber("", "TeamCrafted", Youtubers.RGBtoInt(113, 227, 223), Youtubers.RGBtoInt(162, 112, 34), 3, "Gizzy14Gazza") {
            @Override
            public void Add_Trades(MerchantRecipeList recipeList, Random random, int Subtype) {
               YTList.Add_Generic_Trades(recipeList, random);
            }
         });
         ListYT.add(new Youtuber("", "TeamCrafted", Items.BAKED_POTATO, "MunchingBrotato", "_fox", "_cat", "_Dino", "_girl") {
            @Override
            public void Add_Trades(MerchantRecipeList recipeList, Random random, int Subtype) {
               YTList.Add_Generic_Trades(recipeList, random);
            }
         });
         ListYT.add(new Youtuber("", "TeamCrafted", Youtubers.RGBtoInt(4, 4, 55), Youtubers.RGBtoInt(4, 4, 95), 1, "TrueMU") {
            @Override
            public void Add_Trades(MerchantRecipeList recipeList, Random random, int Subtype) {
               YTList.Add_Generic_Trades(recipeList, random);
            }
         });
         ListYT.add(new Youtuber("", "TeamCrafted", Youtubers.RGBtoInt(255, 0, 0), Youtubers.RGBtoInt(255, 255, 255), 7, "HuskyMudKipz") {
            @Override
            public void Add_Trades(MerchantRecipeList recipeList, Random random, int Subtype) {
               YTList.Add_Generic_Trades(recipeList, random);
            }

            @Override
            public void Add_AI(EntityYoutuber youtuber) {
               youtuber.tasks.addTask(1, new EntityAIAvoidYoutuber(youtuber, "KingDaddyDMAC", -1, 8.0F, 0.6, 0.6));
               youtuber.tasks.addTask(1, new EntityAIAvoidYoutuber(youtuber, "SkyTheKidRS", 1, 8.0F, 0.6, 0.6));
            }

            @Override
            public void dropItems(EntityYoutuber entityYoutuber, int par1) {
               Youtubers.Drop_Item_Entity(entityYoutuber, new ItemStack(Items.FISH, com.electrifiedded.archaiclib.util.FastRandom.shared().nextInt(4 + 2 * par1)));
            }
         });
         ListYT.add(new Youtuber("", "TeamCrafted", Youtubers.RGBtoInt(0, 0, 0), Youtubers.RGBtoInt(255, 255, 255), 0, "XRPMX13"));
         ListYT.add(new Youtuber("", "TeamCrafted", Youtubers.RGBtoInt(0, 0, 0), Youtubers.RGBtoInt(255, 255, 255), 0, "WeedLion"));
         ListSP.add(new YoutuberSpawn(YTStructures.Map.get("melon"), "Bashurverse"));
         ListSP.add(new YoutuberSpawn(YTStructures.Map.get("potato"), "MunchingBrotato"));
         ListSP.add(new YoutuberSpawn(YTStructures.Map.get("Rocket"), "TrueMU"));
         BCtcrafted.addYTM("Gizzy14Gazza");
         BCtcrafted.addYTM("HuskyMudKipz");
         BCtcrafted.addYTM("XRPMX13");
         BCtcrafted.addYTM("WeedLion");
      }

      if (YTConfig.SkyGames) {
         ListYT.add(new Youtuber("", "SkyGames", Youtubers.RGBtoInt(0, 0, 0), Youtubers.RGBtoInt(255, 255, 255), 0, "ThatGuyBarney"));
         ListYT.add(new Youtuber("", "SkyGames", Youtubers.RGBtoInt(0, 0, 0), Youtubers.RGBtoInt(255, 255, 255), 0, "House_Owner"));
         ListYT.add(new Youtuber("", "SkyGames", Youtubers.RGBtoInt(0, 0, 0), Youtubers.RGBtoInt(255, 255, 255), 0, "JinBop"));
         ListYT.add(new Youtuber("", "SkyGames", Youtubers.RGBtoInt(0, 182, 0), Youtubers.RGBtoInt(34, 230, 18), 6, "SubZeroExtabyte") {
            @Override
            public void dropItems(EntityYoutuber entityYoutuber, int par1) {
               Youtubers.Drop_Item_Entity(entityYoutuber, new ItemStack(Blocks.ICE, com.electrifiedded.archaiclib.util.FastRandom.shared().nextInt(4 + 2 * par1)));
               Youtubers.Drop_Item_Entity(entityYoutuber, new ItemStack(Blocks.PACKED_ICE, com.electrifiedded.archaiclib.util.FastRandom.shared().nextInt(4 + 2 * par1)));
            }
         });
         ListSP.add(new YoutuberSpawn(YTStructures.Map.get("SkyWYR"), "SkyTheKidRS").addYTM("ThatGuyBarney").addYTM("House_Owner").addYTM("JinBop"));
         ListSP.add(new YoutuberSpawn(YTStructures.Map.get("Ice"), "SubZeroExtabyte"));
      }

      if (YTConfig.ItsJerryandHarry) {
         ListYT.add(new Youtuber("", "ItsJerryandHarry", Youtubers.RGBtoInt(75, 61, 56), Youtubers.RGBtoInt(198, 41, 40), 7, "ItsHarry") {
            @Override
            public void Add_Trades(MerchantRecipeList recipeList, Random random, int Subtype) {
               YTList.Add_Generic_Trades(recipeList, random);
            }
         });
         ListYT.add(new Youtuber("", "ItsJerryandHarry", Youtubers.RGBtoInt(75, 61, 56), Youtubers.RGBtoInt(3, 189, 176), 7, "ItsJerry") {
            @Override
            public void Add_Trades(MerchantRecipeList recipeList, Random random, int Subtype) {
               YTList.Add_Generic_Trades(recipeList, random);
            }
         });
         ListYT.add(new Youtuber("pwnagepotato", "ItsJerryandHarry", Items.POTATO, "PwnagePotato", "_2.0") {
            @Override
            public void Add_Trades(MerchantRecipeList recipeList, Random random, int Subtype) {
               YTList.Add_Generic_Trades(recipeList, random);
            }

            @Override
            public void give_Items(EntityYoutuber youtuber) {
               if (youtuber.getSubtype() == 1 && "pwnagepotato".equals(youtuber.getName())) {
                  ItemStack item = new ItemStack(Items.POTATO);
                  item.setStackDisplayName("pwnagepotato 2.0");
                  youtuber.setCurrentItemOrArmor(0, item);
               }
            }

            @Override
            public void dropItems(EntityYoutuber entityYoutuber, int par1) {
               Youtubers.Drop_Item_Entity(entityYoutuber, new ItemStack(Items.POTATO, com.electrifiedded.archaiclib.util.FastRandom.shared().nextInt(4 + 2 * par1)));
            }
         });
         ListSP.add(new YoutuberSpawn(YTStructures.Map.get("Generic"), "ItsHarry").addYTM("ItsJerry").addST2("potato").addYT2("PwnagePotato"));
      }

      if (YTConfig.atlanicraft) {
         ListYT.add(new Youtuber("", "atlanicraft", "TheCodyMaverick") {
            @Override
            public void Add_Trades(MerchantRecipeList recipeList, Random random, int Subtype) {
               YTList.Add_Generic_Trades(recipeList, random);
               ItemStack item = new ItemStack(YTItems.AtlantianSword);
               item.addEnchantment(Enchantments.SHARPNESS, 15);
               item.addEnchantment(Enchantments.LOOTING, 5);
               item.addEnchantment(Enchantments.UNBREAKING, 10);
               item.addEnchantment(Enchantments.FIRE_ASPECT, 5);
               recipeList.add(new MerchantRecipeYT(new ItemStack(YTItems.Money[12], 3), item.copy()));
               item = new ItemStack(YTItems.CrazyCraftSword);
               item.addEnchantment(Enchantments.SHARPNESS, 15);
               item.addEnchantment(Enchantments.LOOTING, 5);
               item.addEnchantment(Enchantments.UNBREAKING, 10);
               item.addEnchantment(Enchantments.FIRE_ASPECT, 5);
               recipeList.add(new MerchantRecipeYT(new ItemStack(YTItems.Money[12], 3), item.copy()));
               YTList.Add_cLUCKey_Trades(recipeList, new int[]{0, 1, 2, 3, 4, 5, 6, 12, 18, 24, 30});
            }

            @Override
            public void dropItems(EntityYoutuber entityYoutuber, int par1) {
               Youtubers.Drop_Item_Entity(entityYoutuber, new ItemStack(Items.FEATHER, com.electrifiedded.archaiclib.util.FastRandom.shared().nextInt(4 + 2 * par1)));
            }
         });
         ListYT.add(new Youtuber("", "atlanicraft", Items.COOKED_CHICKEN, "Joebuz") {
            @Override
            public void Add_Trades(MerchantRecipeList recipeList, Random random, int Subtype) {
               YTList.Add_Generic_Trades(recipeList, random);
               ItemStack item = new ItemStack(YTItems.AtlantianSword);
               item.addEnchantment(Enchantments.SHARPNESS, 15);
               item.addEnchantment(Enchantments.LOOTING, 5);
               item.addEnchantment(Enchantments.UNBREAKING, 10);
               item.addEnchantment(Enchantments.FIRE_ASPECT, 5);
               recipeList.add(new MerchantRecipeYT(new ItemStack(YTItems.Money[12], 3), item.copy()));
               item = new ItemStack(YTItems.CrazyCraftSword);
               item.addEnchantment(Enchantments.SHARPNESS, 15);
               item.addEnchantment(Enchantments.LOOTING, 5);
               item.addEnchantment(Enchantments.UNBREAKING, 10);
               item.addEnchantment(Enchantments.FIRE_ASPECT, 5);
               recipeList.add(new MerchantRecipeYT(new ItemStack(YTItems.Money[12], 3), item.copy()));
               YTList.Add_cLUCKey_Trades(recipeList, new int[]{0, 1, 2, 3, 4, 5, 6, 12, 18, 24, 30});
            }

            @Override
            public void Add_AI(EntityYoutuber youtuber) {
               youtuber.tasks.addTask(1, new EntityAIAvoidYoutuber(youtuber, "Keralis1", -1, 8.0F, 0.6, 0.6));
            }

            @Override
            public void dropItems(EntityYoutuber entityYoutuber, int par1) {
               Youtubers.Drop_Item_Entity(entityYoutuber, new ItemStack(Items.CHICKEN, com.electrifiedded.archaiclib.util.FastRandom.shared().nextInt(1 + 2 * par1)));
               Youtubers.Drop_Item_Entity(entityYoutuber, new ItemStack(Items.FEATHER, com.electrifiedded.archaiclib.util.FastRandom.shared().nextInt(4 + 2 * par1)));
            }
         });
         ListYT.add(new Youtuber("", "atlanicraft", Youtubers.RGBtoInt(74, 64, 64), Youtubers.RGBtoInt(203, 167, 100), 7, "TinyTurtleG") {});
         ListYT.add(new Youtuber("", "atlanicraft", Youtubers.RGBtoInt(7, 79, 0), Youtubers.RGBtoInt(5, 59, 0), 0, "LittleLizardG") {});
         ListSP.add(new YoutuberSpawn(YTStructures.Map.get("Generic"), "LittleLizardG").addYTM("TinyTurtleG"));
         ListSP.add(new YoutuberSpawn(YTStructures.Map.get("LuckeyBoss"), "TheCodyMaverick").addYTM("Joebuz"));
         ListSP.add(new YoutuberSpawn(YTStructures.Map.get("CC3Bannana"), "TheCodyMaverick").addYTM("Joebuz").addST2("CC3Soda"));
      }

      if (YTConfig.TDM) {
         ListYT.add(
            new Youtuber("", "TDM", YTItems.DiamondMinecart, "DanTDM") {
               @Override
               public void Add_Trades(MerchantRecipeList recipeList, Random random, int Subtype) {
                  YTList.Add_Generic_Trades(recipeList, random);
                  recipeList.add(new MerchantRecipeYT(new ItemStack(YTItems.Money[4], 7), new ItemStack(Items.DIAMOND, 3, 0)));
                  recipeList.add(new MerchantRecipeYT(new ItemStack(YTItems.Money[4], 7), new ItemStack(Items.MINECART, 2, 0)));
                  recipeList.add(new MerchantRecipeYT(new ItemStack(YTItems.Money[9], 7), new ItemStack(YTItems.DiamondMinecart, 1, 0)));
                  ItemStack item = new ItemStack(Items.DIAMOND_SWORD);
                  item.setStackDisplayName("Trayaurus 9001");
                  item.addEnchantment(Enchantments.SHARPNESS, 10);
                  item.addEnchantment(Enchantments.LOOTING, 10);
                  item.addEnchantment(Enchantments.UNBREAKING, 10);
                  item.addEnchantment(Enchantments.FIRE_ASPECT, 5);
                  recipeList.add(new MerchantRecipeYT(new ItemStack(YTItems.Money[12], 3), item.copy()));
                  recipeList.add(new MerchantRecipeYT(new ItemStack(Items.MINECART, 1), new ItemStack(YTItems.Money[4], 2)));
               }

               @Override
               public void spawnPets(World world, BlockPos pos) {
                  EntityTrayourus entity = new EntityTrayourus(world);
                  entity.setLocationAndAngles(
                     pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, MathHelper.wrapDegrees(world.rand.nextFloat() * 360.0F), 0.0F
                  );
                  entity.rotationYawHead = entity.rotationYaw;
                  entity.renderYawOffset = entity.rotationYaw;
                  com.electrifiedded.archaiclib.world.StagedWorld.spawnEntity(world, entity);
                  EntityGrimm entityG = new EntityGrimm(world);
                  entityG.setLocationAndAngles(
                     pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, MathHelper.wrapDegrees(world.rand.nextFloat() * 360.0F), 0.0F
                  );
                  entityG.rotationYawHead = entityG.rotationYaw;
                  entityG.renderYawOffset = entityG.rotationYaw;
                  com.electrifiedded.archaiclib.world.StagedWorld.spawnEntity(world, entityG);
                  EntityCraigTheMailman entityC = new EntityCraigTheMailman(world);
                  entity.setLocationAndAngles(
                     pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, MathHelper.wrapDegrees(world.rand.nextFloat() * 360.0F), 0.0F
                  );
                  entityC.rotationYawHead = entityC.rotationYaw;
                  entityC.renderYawOffset = entityC.rotationYaw;
                  com.electrifiedded.archaiclib.world.StagedWorld.spawnEntity(world, entityC);
               }
            }
         );
         ListYT.add(new Youtuber("", "TDM", Youtubers.RGBtoInt(255, 255, 255), Youtubers.RGBtoInt(253, 153, 200), 2, "xXJemmaMXx") {
            @Override
            public void Add_Trades(MerchantRecipeList recipeList, Random random, int Subtype) {
               YTList.Add_Generic_Trades(recipeList, random);
               recipeList.add(new MerchantRecipeYT(new ItemStack(YTItems.Money[4], 7), new ItemStack(Items.COOKED_PORKCHOP, 8, 0)));
               recipeList.add(new MerchantRecipeYT(new ItemStack(YTItems.Money[4], 7), new ItemStack(Items.PORKCHOP, 16, 0)));
               ItemStack item = new ItemStack(Items.BOW);
               item.addEnchantment(Enchantments.FLAME, 5);
               item.addEnchantment(Enchantments.PUNCH, 5);
               item.addEnchantment(Enchantments.POWER, 5);
               item.addEnchantment(Enchantments.INFINITY, 1);
               recipeList.add(new MerchantRecipeYT(new ItemStack(YTItems.Money[10], 4), item.copy()));
            }
         });
         ListSP.add(new YoutuberSpawn(YTStructures.Map.get("Lab"), "DanTDM").addST2("Generic").addYT2("xXJemmaMXx"));
      }

      if (YTConfig.Trolls) {
         ListYT.add(new Youtuber("", "Trolls", Youtubers.RGBtoInt(0, 0, 0), Youtubers.RGBtoInt(0, 0, 255), 0, "ZexyZek") {
            @Override
            public void Add_Trades(MerchantRecipeList recipeList, Random random, int Subtype) {
               YTList.Add_Generic_Trades(recipeList, random);
               ItemStack item = new ItemStack(Items.LAVA_BUCKET, 0, 0);
               item.setStackDisplayName("Red Water");
               recipeList.add(new MerchantRecipeYT(new ItemStack(YTItems.Money[4], 7), item.copy()));
            }
         });
         ListYT.add(new Youtuber("", "Trolls", Youtubers.RGBtoInt(0, 0, 0), Youtubers.RGBtoInt(255, 173, 0), 5, "Bodil40") {
            @Override
            public void Add_Trades(MerchantRecipeList recipeList, Random random, int Subtype) {
               YTList.Add_Generic_Trades(recipeList, random);
               recipeList.add(new MerchantRecipeYT(new ItemStack(YTItems.Money[10], 8), new ItemStack(YTItems.JetPack, 1, 0)));
               recipeList.add(new MerchantRecipeYT(new ItemStack(YTItems.Money[4], 3), new ItemStack(YTItems.Money[0], 1), YTItems.Bodiltrollkits[0]));
               recipeList.add(new MerchantRecipeYT(new ItemStack(YTItems.Money[4], 6), new ItemStack(YTItems.Money[0], 2), YTItems.Bodiltrollkits[1]));
               recipeList.add(new MerchantRecipeYT(new ItemStack(YTItems.Money[4], 13), new ItemStack(YTItems.Money[0], 3), YTItems.Bodiltrollkits[2]));
               recipeList.add(new MerchantRecipeYT(new ItemStack(YTItems.Money[4], 28), new ItemStack(YTItems.Money[0], 4), YTItems.Bodiltrollkits[3]));
               recipeList.add(new MerchantRecipeYT(new ItemStack(YTItems.Money[4], 59), new ItemStack(YTItems.Money[0], 5), YTItems.Bodiltrollkits[4]));
               YTList.Add_cLUCKey_Trades(recipeList, new int[]{0, 1, 2, 3, 4, 5, 6, 12, 18, 24, 30});
            }

            @Override
            public void dropItems(EntityYoutuber entityYoutuber, int par1) {
               Youtubers.Drop_Item_Entity(entityYoutuber, new ItemStack(Items.TNT_MINECART, com.electrifiedded.archaiclib.util.FastRandom.shared().nextInt(4 + 2 * par1)));
            }
         });
         BCtrolls.addYTM("ZexyZek");
         BCtrolls.addYTM("Bodil40");
      }

      if (YTConfig.Yogscast) {
         ListYT.add(new Youtuber("", "Yogscast", Youtubers.RGBtoInt(131, 41, 41), Youtubers.RGBtoInt(255, 168, 0), 2, "Xephos"));
         ListYT.add(new Youtuber("", "Yogscast", Items.DIAMOND_PICKAXE, "Honeydew") {});
         ListYT.add(new Youtuber("", "Yogscast", Youtubers.RGBtoInt(231, 234, 237), Youtubers.RGBtoInt(94, 126, 152), 5, "Sjin"));
         ListYT.add(new Youtuber("", "Yogscast", Youtubers.RGBtoInt(247, 247, 248), Youtubers.RGBtoInt(94, 2, 123), 0, "LividCoffee"));
         ListYT.add(new Youtuber("", "Yogscast", Youtubers.RGBtoInt(41, 45, 46), Youtubers.RGBtoInt(21, 162, 234), 4, "Sips_"));
         ListYT.add(new Youtuber("", "Yogscast", Youtubers.RGBtoInt(237, 184, 41), Youtubers.RGBtoInt(17, 80, 133), 7, "Lomadia"));
         ListYT.add(new Youtuber("", "Yogscast", Youtubers.RGBtoInt(121, 217, 241), Youtubers.RGBtoInt(0, 96, 145), 2, "InTheLittleWood"));
         ListYT.add(new Youtuber("", "Yogscast", Youtubers.RGBtoInt(34, 47, 59), Youtubers.RGBtoInt(209, 207, 122), 1, "Nilesy_"));
         ListYT.add(new Youtuber("", "Yogscast", Youtubers.RGBtoInt(34, 20, 9), Youtubers.RGBtoInt(10, 10, 10), 7, "Rythian"));
         ListYT.add(new Youtuber("", "Yogscast", Youtubers.RGBtoInt(87, 0, 104), Youtubers.RGBtoInt(206, 0, 0), 6, "Nanosounds"));
         ListSP.add(new YoutuberSpawn(YTStructures.Map.get("DiggyHole"), "Honeydew"));
         BCyoggs.addYTM("Xephos");
         BCyoggs.addYTM("Honeydew");
         BCyoggs.addYTM("Sjin");
         BCyoggs.addYTM("LividCoffee");
         BCyoggs.addYTM("Sips_");
         BCyoggs.addYTM("Lomadia");
         BCyoggs.addYTM("InTheLittleWood");
         BCyoggs.addYTM("Nilesy_");
         BCyoggs.addYTM("Rythian");
         BCyoggs.addYTM("Nanosounds");
      }

      if (YTConfig.drunk) {
         ListYT.add(new Youtuber("", "drunk", Youtubers.RGBtoInt(59, 59, 59), Youtubers.RGBtoInt(214, 0, 0), 0, "Markiplier") {
            @Override
            public void Add_Trades(MerchantRecipeList recipeList, Random random, int Subtype) {
               YTList.Add_Generic_Trades(recipeList, random);
               YTList.Add_Drunk_Trades(recipeList, random);
            }
         });
         ListYT.add(new Youtuber("", "drunk", Youtubers.RGBtoInt(214, 0, 0), Youtubers.RGBtoInt(59, 59, 59), 0, "LordMinion777") {
            @Override
            public void Add_Trades(MerchantRecipeList recipeList, Random random, int Subtype) {
               YTList.Add_Generic_Trades(recipeList, random);
               YTList.Add_Drunk_Trades(recipeList, random);
            }
         });
         ListYT.add(new Youtuber("", "drunk", Youtubers.RGBtoInt(59, 10, 74), Youtubers.RGBtoInt(255, 255, 255), 7, "muyskerm") {
            @Override
            public void Add_Trades(MerchantRecipeList recipeList, Random random, int Subtype) {
               YTList.Add_Generic_Trades(recipeList, random);
               YTList.Add_Drunk_Trades(recipeList, random);
            }
         });
         ListSP.add(new YoutuberSpawn(YTStructures.Map.get("DrunkHouse"), "Markiplier").addYTM("LordMinion777").addYTM("muyskerm"));
      }

      if (YTConfig.RedStone) {
         ListYT.add(new Youtuber("", "RedStone", Youtubers.RGBtoInt(0, 0, 0), Youtubers.RGBtoInt(255, 255, 255), 0, "CubeHamster"));
         BCother.addYTM("CubeHamster");
      }

      if (YTConfig.News) {
         ListYT.add(new Youtuber("", "News", Youtubers.RGBtoInt(0, 0, 0), Youtubers.RGBtoInt(255, 255, 255), 0, "swimmingbird941"));
         BCother.addYTM("swimmingbird941");
      }

      ListYT.add(new Youtuber("", "", Youtubers.RGBtoInt(255, 255, 255), Youtubers.RGBtoInt(77, 40, 8), 4, "deadlox"));
      ListYT.add(new Youtuber("", "", Youtubers.RGBtoInt(36, 36, 36), Youtubers.RGBtoInt(239, 246, 0), 2, "ghosteez"));
      ListYT.add(new Youtuber("", "", Youtubers.RGBtoInt(27, 111, 19), Youtubers.RGBtoInt(36, 184, 21), 3, "Simonhds90"));
      ListYT.add(new Youtuber("", "", "ibaki961") {
         @Override
         public void dropItems(EntityYoutuber entityYoutuber, int par1) {
            Youtubers.Drop_Item_Entity(entityYoutuber, new ItemStack(Blocks.SNOW, com.electrifiedded.archaiclib.util.FastRandom.shared().nextInt(4 + 2 * par1)));
            Youtubers.Drop_Item_Entity(entityYoutuber, new ItemStack(Items.SNOWBALL, com.electrifiedded.archaiclib.util.FastRandom.shared().nextInt(4 + 2 * par1)));
         }
      });
      ListYT.add(new Youtuber("", "", Items.GOLD_INGOT, "GoldSolace") {});
      ListYT.add(new Youtuber("", "", Youtubers.RGBtoInt(0, 0, 0), Youtubers.RGBtoInt(255, 255, 255), 0, "mlgHwnt"));
      ListYT.add(new Youtuber("", "", Youtubers.RGBtoInt(0, 0, 0), Youtubers.RGBtoInt(75, 35, 20), 1, "OkwardIndustries"));
      ListYT.add(new Youtuber("", "", Youtubers.RGBtoInt(94, 51, 2), Youtubers.RGBtoInt(96, 37, 0), 7, "PerpetualJordan"));
      ListSP.add(new YoutuberSpawn(YTStructures.Map.get("SnowLand"), "ibaki961"));
      BCother.addYTM("deadlox");
      BCother.addYTM("ghosteez");
      BCother.addYTM("Simonhds90");
      BCother.addYTM("GoldSolace");
      BCother.addYTM("mlgHwnt");
      BCother.addYTM("OkwardIndustries");
      BCother.addYTM("PerpetualJordan");
      if (YTConfig.People1) {
         ListYT.add(new Youtuber("", "People1", Youtubers.RGBtoInt(117, 115, 115), Youtubers.RGBtoInt(156, 154, 154), 5, "RealTBNRkenWorth"));
         ListYT.add(new Youtuber("", "People1", Youtubers.RGBtoInt(0, 0, 0), Youtubers.RGBtoInt(255, 255, 255), 0, "TurtleDerp"));
         ListYT.add(new Youtuber("", "People1", Youtubers.RGBtoInt(0, 0, 0), Youtubers.RGBtoInt(230, 194, 21), 2, "ypperin"));
         ListYT.add(new Youtuber("", "People1", Youtubers.RGBtoInt(0, 0, 0), Youtubers.RGBtoInt(255, 255, 255), 0, "captainsparkelz"));
         ListYT.add(new Youtuber("", "People1", Youtubers.RGBtoInt(0, 0, 0), Youtubers.RGBtoInt(255, 255, 255), 0, "syndicate"));
         ListYT.add(new Youtuber("", "People1", Youtubers.RGBtoInt(0, 0, 0), Youtubers.RGBtoInt(255, 255, 255), 0, "LDShadowLady"));
         ListYT.add(new Youtuber("", "People1", Youtubers.RGBtoInt(24, 24, 24), Youtubers.RGBtoInt(93, 45, 30), 7, "CraftyGarrett"));
         ListYT.add(new Youtuber("", "People1", Youtubers.RGBtoInt(49, 136, 58), Youtubers.RGBtoInt(47, 119, 54), 0, "CrookedReign"));
         ListSP.add(
            new YoutuberSpawn(YTStructures.Map.get("Generic"), "RealTBNRkenWorth")
               .addYTM("TurtleDerp")
               .addYTM("ypperin")
               .addYTM("captainsparkelz")
               .addYTM("syndicate")
               .addYTM("LDShadowLady")
               .addYTM("CraftyGarrett")
               .addYTM("CrookedReign")
               .setMLim(2)
         );
      }

      if (YTConfig.People2) {
         ListYT.add(new Youtuber("", "People2", Youtubers.RGBtoInt(0, 0, 0), Youtubers.RGBtoInt(255, 255, 255), 0, "RealThinknoodles"));
         ListYT.add(new Youtuber("", "People2", Youtubers.RGBtoInt(0, 0, 0), Youtubers.RGBtoInt(255, 255, 255), 0, "Jericho"));
         ListYT.add(new Youtuber("", "People2", Youtubers.RGBtoInt(0, 0, 0), Youtubers.RGBtoInt(255, 255, 255), 0, "Omgitsfirefoxx"));
         ListYT.add(new Youtuber("", "People2", Youtubers.RGBtoInt(0, 0, 0), Youtubers.RGBtoInt(255, 255, 255), 0, "Ashleymariee"));
         ListYT.add(new Youtuber("", "People2", Youtubers.RGBtoInt(0, 0, 0), Youtubers.RGBtoInt(255, 255, 255), 0, "smallishbeans"));
         ListYT.add(new Youtuber("", "People2", Youtubers.RGBtoInt(0, 0, 0), Youtubers.RGBtoInt(255, 255, 255), 0, "Thnxcya"));
         ListYT.add(new Youtuber("", "People2", Youtubers.RGBtoInt(0, 0, 0), Youtubers.RGBtoInt(255, 255, 255), 0, "antvenom"));
         ListYT.add(new Youtuber("", "People2", Youtubers.RGBtoInt(0, 0, 0), Youtubers.RGBtoInt(255, 255, 255), 0, "Superchache39"));
         ListYT.add(new Youtuber("", "People2", Youtubers.RGBtoInt(0, 0, 0), Youtubers.RGBtoInt(255, 255, 255), 0, "prestonplayz"));
         ListSP.add(
            new YoutuberSpawn(YTStructures.Map.get("Generic"), "RealThinknoodles")
               .addYTM("Jericho")
               .addYTM("Omgitsfirefoxx")
               .addYTM("Ashleymariee")
               .addYTM("smallishbeans")
               .addYTM("Thnxcya")
               .addYTM("antvenom")
               .addYTM("Superchache39")
               .addYTM("prestonplayz")
               .setMLim(2)
         );
      }

      if (YTConfig.People3) {
         ListYT.add(new Youtuber("", "People3", Youtubers.RGBtoInt(0, 0, 0), Youtubers.RGBtoInt(255, 255, 255), 0, "ihascupquake"));
         ListYT.add(new Youtuber("", "People3", Youtubers.RGBtoInt(0, 0, 0), Youtubers.RGBtoInt(255, 255, 255), 0, "stacyplays"));
         ListYT.add(new Youtuber("", "People3", Youtubers.RGBtoInt(0, 0, 0), Youtubers.RGBtoInt(255, 255, 255), 0, "TheZombiUnicorn"));
         ListYT.add(new Youtuber("", "People3", Youtubers.RGBtoInt(0, 0, 0), Youtubers.RGBtoInt(255, 255, 255), 0, "Grapeapplesause"));
         ListYT.add(new Youtuber("", "People3", Youtubers.RGBtoInt(0, 0, 0), Youtubers.RGBtoInt(255, 255, 255), 0, "SlyFoxHound"));
         ListYT.add(new Youtuber("", "People3", Youtubers.RGBtoInt(0, 0, 0), Youtubers.RGBtoInt(255, 255, 255), 0, "ElRichMC"));
         ListYT.add(new Youtuber("", "People3", Youtubers.RGBtoInt(0, 0, 0), Youtubers.RGBtoInt(255, 255, 255), 0, "PeteZahHutt"));
         ListYT.add(new Youtuber("", "People3", Youtubers.RGBtoInt(0, 0, 0), Youtubers.RGBtoInt(255, 255, 255), 0, "NoochM"));
         ListYT.add(new Youtuber("", "People3", Youtubers.RGBtoInt(139, 1, 146), Youtubers.RGBtoInt(250, 0, 200), 6, "Kkcomics", "_bunny"));
         ListYT.add(new Youtuber("", "People3", Youtubers.RGBtoInt(44, 19, 156), Youtubers.RGBtoInt(84, 149, 240), 5, "Dartron"));
         ListSP.add(
            new YoutuberSpawn(YTStructures.Map.get("Generic"), "ihascupquake")
               .addYTM("stacyplays")
               .addYTM("TheZombiUnicorn")
               .addYTM("Grapeapplesause")
               .addYTM("SlyFoxHound")
               .addYTM("ElRichMC")
               .addYTM("PeteZahHutt")
               .addYTM("NoochM")
               .addYTM("Kkcomics")
               .addYTM("Dartron")
               .setMLim(2)
         );
      }

      if (YTConfig.People4) {
         ListYT.add(new Youtuber("", "People4", Youtubers.RGBtoInt(0, 0, 0), Youtubers.RGBtoInt(255, 255, 255), 0, "Biggs87x"));
         ListYT.add(new Youtuber("", "People4", Youtubers.RGBtoInt(0, 0, 0), Youtubers.RGBtoInt(255, 255, 255), 0, "Zailetsplay"));
         ListYT.add(new Youtuber("", "People4", Youtubers.RGBtoInt(0, 0, 0), Youtubers.RGBtoInt(255, 255, 255), 0, "TBNRkenWorth"));
         ListYT.add(new Youtuber("", "People4", Youtubers.RGBtoInt(0, 0, 0), Youtubers.RGBtoInt(255, 255, 255), 0, "NiceMark"));
         ListYT.add(new Youtuber("", "People4", Youtubers.RGBtoInt(0, 0, 0), Youtubers.RGBtoInt(255, 255, 255), 0, "MrWilliamo"));
         ListYT.add(new Youtuber("", "People4", Youtubers.RGBtoInt(0, 0, 0), Youtubers.RGBtoInt(255, 255, 255), 0, "graser10"));
         ListYT.add(new Youtuber("", "People4", Youtubers.RGBtoInt(0, 0, 0), Youtubers.RGBtoInt(255, 255, 255), 0, "FearADubh"));
         ListYT.add(new Youtuber("", "People4", Youtubers.RGBtoInt(0, 0, 0), Youtubers.RGBtoInt(255, 255, 255), 0, "chimneyswift11"));
         ListYT.add(new Youtuber("", "People4", Youtubers.RGBtoInt(0, 0, 0), Youtubers.RGBtoInt(255, 255, 255), 0, "Vanoss"));
         ListYT.add(new Youtuber("", "People4", Youtubers.RGBtoInt(0, 0, 0), Youtubers.RGBtoInt(255, 255, 255), 0, "logdotzip"));
         ListSP.add(
            new YoutuberSpawn(YTStructures.Map.get("Generic"), "Biggs87x")
               .addYTM("Zailetsplay")
               .addYTM("TBNRkenWorth")
               .addYTM("NiceMark")
               .addYTM("MrWilliamo")
               .addYTM("graser10")
               .addYTM("FearADubh")
               .addYTM("chimneyswift11")
               .addYTM("Vanoss")
               .addYTM("logdotzip")
               .setMLim(2)
         );
      }

      if (YTConfig.People5) {
         ListYT.add(new Youtuber("", "People5", Youtubers.RGBtoInt(0, 0, 0), Youtubers.RGBtoInt(255, 255, 255), 0, "FollowKevn"));
         ListYT.add(new Youtuber("", "People5", Youtubers.RGBtoInt(0, 0, 0), Youtubers.RGBtoInt(255, 255, 255), 0, "daHbomb"));
         ListYT.add(new Youtuber("", "People5", Youtubers.RGBtoInt(0, 0, 0), Youtubers.RGBtoInt(255, 255, 255), 0, "XBayani"));
         ListYT.add(new Youtuber("", "People5", Youtubers.RGBtoInt(0, 0, 0), Youtubers.RGBtoInt(255, 255, 255), 0, "Strauberryjam"));
         ListYT.add(new Youtuber("", "People5", Youtubers.RGBtoInt(0, 0, 0), Youtubers.RGBtoInt(255, 255, 255), 0, "Lifesimmer"));
         ListYT.add(new Youtuber("", "People5", Youtubers.RGBtoInt(0, 0, 0), Youtubers.RGBtoInt(255, 255, 255), 0, "seedeng"));
         ListYT.add(new Youtuber("", "People5", Youtubers.RGBtoInt(0, 0, 0), Youtubers.RGBtoInt(255, 255, 255), 0, "masterovv"));
         ListYT.add(new Youtuber("", "People5", Youtubers.RGBtoInt(0, 0, 0), Youtubers.RGBtoInt(255, 255, 255), 0, "omgitsalia"));
         ListYT.add(new Youtuber("", "People5", Youtubers.RGBtoInt(0, 0, 0), Youtubers.RGBtoInt(255, 255, 255), 0, "CyaNideEPIC"));
         ListYT.add(new Youtuber("", "People5", Youtubers.RGBtoInt(0, 0, 0), Youtubers.RGBtoInt(255, 255, 255), 0, "YouAlwaysWin"));
         ListSP.add(
            new YoutuberSpawn(YTStructures.Map.get("Generic"), "FollowKevn")
               .addYTM("daHbomb")
               .addYTM("XBayani")
               .addYTM("Strauberryjam")
               .addYTM("Lifesimmer")
               .addYTM("seedeng")
               .addYTM("masterovv")
               .addYTM("omgitsalia")
               .addYTM("CyaNideEPIC")
               .addYTM("YouAlwaysWin")
               .setMLim(2)
         );
      }

      ListYT.add(new Youtuber("", "unadded", Youtubers.RGBtoInt(0, 0, 0), Youtubers.RGBtoInt(255, 255, 255), 0, "Aphmau"));
      ListYT.add(new Youtuber("", "unadded", Youtubers.RGBtoInt(0, 0, 0), Youtubers.RGBtoInt(255, 255, 255), 0, "mustashpup9"));
      ListYT.add(new Youtuber("", "unadded", Youtubers.RGBtoInt(0, 0, 0), Youtubers.RGBtoInt(255, 255, 255), 0, "dfield"));
      BCother.addYTM("Aphmau");
      BCother.addYTM("mustashpup9");
      BCother.addYTM("dfield");
      if (YTConfig.storytellers) {
         ListYT.add(
            new Youtuber("", "storytellers", "ExplodingTNT", "_suprised") {
               @Override
               public void dropItems(EntityYoutuber entityYoutuber, int par1) {
                  if (entityYoutuber.isBurning()) {
                     int numb = com.electrifiedded.archaiclib.util.FastRandom.shared().nextInt(4 + 2 * par1);

                     for (int i = 0; i < numb; i++) {
                        if (!entityYoutuber.world.isRemote) {
                           EntityTNTPrimed entitytntprimed = new EntityTNTPrimed(
                              entityYoutuber.world,
                              entityYoutuber.posX,
                              entityYoutuber.posY,
                              entityYoutuber.posZ,
                              (EntityLivingBase)null
                           );
                           entitytntprimed.setFuse(entityYoutuber.world.rand.nextInt(entitytntprimed.getFuse() / 4) + entitytntprimed.getFuse() / 8);
                           entityYoutuber.world.spawnEntity(entitytntprimed);
                        }
                     }
                  } else {
                     Youtubers.Drop_Item_Entity(entityYoutuber, new ItemStack(Blocks.TNT, com.electrifiedded.archaiclib.util.FastRandom.shared().nextInt(4 + 2 * par1)));
                  }
               }
            }
         );
         ListYT.add(new Youtuber("", "storytellers", Youtubers.RGBtoInt(0, 0, 0), Youtubers.RGBtoInt(255, 255, 255), 0, "Slamacow"));
         ListYT.add(new Youtuber("", "storytellers", Youtubers.RGBtoInt(0, 0, 0), Youtubers.RGBtoInt(255, 255, 255), 0, "CavemanFilms"));
         ListYT.add(new Youtuber("", "storytellers", Youtubers.RGBtoInt(0, 0, 0), Youtubers.RGBtoInt(255, 255, 255), 0, "BlueMonkeyYT"));
         ListSP.add(new YoutuberSpawn(YTStructures.Map.get("TNT"), "ExplodingTNT"));
         BCstory.addYTM("Slamacow");
         BCstory.addYTM("CavemanFilms");
         BCstory.addYTM("BlueMonkeyYT");
      }

      if (YTConfig.non) {
         ListYT.add(new Youtuber("", "non", Youtubers.RGBtoInt(0, 0, 0), Youtubers.RGBtoInt(255, 255, 255), 0, "JackSepticEye"));
         BCother.addYTM("JackSepticEye");
      }

      if (YTConfig.stampy) {
         ListYT.add(new Youtuber("", "stampy", Items.CAKE, "stampylongnose") {
            @Override
            public void dropItems(EntityYoutuber entityYoutuber, int par1) {
               int n = com.electrifiedded.archaiclib.util.FastRandom.shared().nextInt(4 + 2 * par1) + 4;

               for (int a = 0; a < n; a++) {
                  Youtubers.Drop_Item_Entity(entityYoutuber, new ItemStack(Items.CAKE));
               }
            }
         });
         ListYT.add(new Youtuber("", "stampy", Youtubers.RGBtoInt(0, 0, 0), Youtubers.RGBtoInt(255, 255, 255), 0, "ibalisticsquid") {
            @Override
            public void dropItems(EntityYoutuber entityYoutuber, int par1) {
               Youtubers.Drop_Item_Entity(entityYoutuber, new ItemStack(Items.DYE, com.electrifiedded.archaiclib.util.FastRandom.shared().nextInt(4 + 2 * par1) + 4));
            }
         });
         ListYT.add(new Youtuber("", "stampy", Youtubers.RGBtoInt(0, 0, 0), Youtubers.RGBtoInt(255, 255, 255), 0, "Sqaishey"));
         ListYT.add(new Youtuber("", "stampy", Youtubers.RGBtoInt(0, 0, 0), Youtubers.RGBtoInt(255, 255, 255), 0, "Amylee33"));
         ListYT.add(new Youtuber("", "stampy", Youtubers.RGBtoInt(0, 0, 0), Youtubers.RGBtoInt(255, 255, 255), 0, "LONGbowX99"));
         ListSP.add(new YoutuberSpawn(YTStructures.Map.get("StampyHouse"), "stampylongnose"));
         ListSP.add(new YoutuberSpawn(YTStructures.Map.get("SkyBlock"), "ibalisticsquid"));
         BCstamps.addYTM("stampylongnose");
         BCstamps.addYTM("ibalisticsquid");
         BCstamps.addYTM("Sqaishey");
         BCstamps.addYTM("Amylee33");
         BCstamps.addYTM("LONGbowX99");
      }

      if (YTConfig.TeamCrafted || YTConfig.ThePack) {
         ListYT.add(new Youtuber("", "TeamCrafted ThePack", Youtubers.RGBtoInt(255, 0, 0), Youtubers.RGBtoInt(0, 0, 0), 0, "TheBajanCanadian") {
            @Override
            public void Add_Trades(MerchantRecipeList recipeList, Random random, int Subtype) {
               YTList.Add_Generic_Trades(recipeList, random);
            }
         });
         ListYT.add(new Youtuber("ASF Jerome", "TeamCrafted ThePack", Youtubers.RGBtoInt(62, 63, 10), Youtubers.RGBtoInt(46, 46, 46), 7, "ASFJerome") {
            @Override
            public void Add_Trades(MerchantRecipeList recipeList, Random random, int Subtype) {
               YTList.Add_Generic_Trades(recipeList, random);
               ItemStack item = new ItemStack(Items.DIAMOND_AXE);
               item.setStackDisplayName("Betty");
               item.addEnchantment(Enchantments.SHARPNESS, 100);
               item.addEnchantment(Enchantments.LOOTING, 10);
               item.addEnchantment(Enchantments.UNBREAKING, 10);
               item.addEnchantment(Enchantments.EFFICIENCY, 6);
               recipeList.add(new MerchantRecipeYT(new ItemStack(YTItems.Money[12], 3), item.copy()));
            }
         });
         BCtcrafted.addYTM("TheBajanCanadian");
         BCtcrafted.addYTM("ASFJerome");
         BCpack.addYTM("TheBajanCanadian");
         BCpack.addYTM("ASFJerome");
      }

      if (YTConfig.ThePack) {
         ListYT.add(new Youtuber("", "ThePack", Youtubers.RGBtoInt(0, 0, 0), Youtubers.RGBtoInt(255, 255, 255), 0, "Lachlan"));
         ListYT.add(new Youtuber("", "ThePack", "Vikkstar123"));
         ListYT.add(new Youtuber("", "ThePack", Youtubers.RGBtoInt(0, 0, 0), Youtubers.RGBtoInt(255, 255, 255), 0, "tbnrfrags"));
         BCpack.addYTM("Lachlan");
         BCpack.addYTM("Vikkstar123");
         BCpack.addYTM("tbnrfrags");
      }

      if (YTConfig.Creatures) {
         ListYT.add(new Youtuber("", "Creatures", Youtubers.RGBtoInt(0, 0, 0), Youtubers.RGBtoInt(255, 255, 255), 0, "TheDexterManning"));
         ListYT.add(new Youtuber("", "Creatures", Youtubers.RGBtoInt(0, 0, 0), Youtubers.RGBtoInt(255, 255, 255), 0, "DanzNewz"));
         ListYT.add(new Youtuber("", "Creatures", Youtubers.RGBtoInt(0, 0, 0), Youtubers.RGBtoInt(255, 255, 255), 0, "ZeRoyalViking"));
         ListYT.add(new Youtuber("", "Creatures", Youtubers.RGBtoInt(0, 0, 0), Youtubers.RGBtoInt(255, 255, 255), 0, "Sp00nerism"));
         ListYT.add(new Youtuber("", "Creatures", Youtubers.RGBtoInt(0, 0, 0), Youtubers.RGBtoInt(255, 255, 255), 0, "TomAnex"));
         ListYT.add(new Youtuber("", "Creatures", Youtubers.RGBtoInt(0, 0, 0), Youtubers.RGBtoInt(255, 255, 255), 0, "ImmortalHD"));
         ListYT.add(new Youtuber("", "Creatures", Youtubers.RGBtoInt(0, 0, 0), Youtubers.RGBtoInt(255, 255, 255), 0, "Kootra"));
         ListYT.add(new Youtuber("", "Creatures", Youtubers.RGBtoInt(0, 0, 0), Youtubers.RGBtoInt(255, 255, 255), 0, "paragonnova"));
         ListYT.add(new Youtuber("", "Creatures", Youtubers.RGBtoInt(0, 0, 0), Youtubers.RGBtoInt(255, 255, 255), 0, "SSohPKC"));
         BCcreature.addYTM("TheDexterManning");
         BCcreature.addYTM("DanzNewz");
         BCcreature.addYTM("ZeRoyalViking");
         BCcreature.addYTM("Sp00nerism");
         BCcreature.addYTM("TomAnex");
         BCcreature.addYTM("ImmortalHD");
         BCcreature.addYTM("Kootra");
         BCcreature.addYTM("paragonnova");
         BCcreature.addYTM("SSohPKC");
      }

      if (YTConfig.nonEnglish) {
         ListYT.add(new Youtuber("", "nonEnglish", Youtubers.RGBtoInt(0, 0, 0), Youtubers.RGBtoInt(255, 255, 255), 0, "alexby11"));
         ListYT.add(new Youtuber("", "nonEnglish", Youtubers.RGBtoInt(0, 0, 0), Youtubers.RGBtoInt(255, 255, 255), 0, "luzugames"));
         ListYT.add(new Youtuber("", "nonEnglish", Youtubers.RGBtoInt(0, 0, 0), Youtubers.RGBtoInt(255, 255, 255), 0, "bystaxx"));
         ListYT.add(new Youtuber("", "nonEnglish", Youtubers.RGBtoInt(0, 0, 0), Youtubers.RGBtoInt(255, 255, 255), 0, "theWillyrex"));
         ListYT.add(new Youtuber("", "nonEnglish", Youtubers.RGBtoInt(0, 0, 0), Youtubers.RGBtoInt(255, 255, 255), 0, "VegettaGaymer"));
         BClang.addYTM("alexby11");
         BClang.addYTM("luzugames");
         BClang.addYTM("bystaxx");
         BClang.addYTM("theWillyrex");
         BClang.addYTM("VegettaGaymer");
      }

      ListYT.add(new Youtuber("", "popularMMOs", Youtubers.RGBtoInt(104, 104, 104), Youtubers.RGBtoInt(0, 0, 0), 4, "FuriousDestroyer") {
         @Override
         public void spawnPets(World world, BlockPos pos) {
            EntityBomby entity = new EntityBomby(world);
            entity.setLocationAndAngles(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, MathHelper.wrapDegrees(world.rand.nextFloat() * 360.0F), 0.0F);
            entity.rotationYawHead = entity.rotationYaw;
            entity.renderYawOffset = entity.rotationYaw;
            com.electrifiedded.archaiclib.world.StagedWorld.spawnEntity(world, entity);
         }

         @Override
         public void Add_Trades(MerchantRecipeList recipeList, Random random, int Subtype) {
            YTList.Add_Generic_Trades(recipeList, random);
            recipeList.add(new MerchantRecipeYT(new ItemStack(YTItems.Money[5], 3), new ItemStack(YTBlocks.blockYTNT, 1)));
            recipeList.add(new MerchantRecipeYT(new ItemStack(YTItems.Money[4], 4), new ItemStack(Blocks.TNT, 64)));
            ItemStack item = new ItemStack(YTItems.EpicPreportionsSword);
            item.addEnchantment(Enchantments.SHARPNESS, 15);
            item.addEnchantment(Enchantments.LOOTING, 5);
            item.addEnchantment(Enchantments.UNBREAKING, 10);
            item.addEnchantment(Enchantments.FIRE_ASPECT, 5);
            recipeList.add(new MerchantRecipeYT(new ItemStack(YTItems.Money[12], 3), item.copy()));
            YTList.Add_cLUCKey_Trades(recipeList, new int[]{0, 1, 2, 3, 4, 5, 6, 12, 18, 24, 30});
         }

         @Override
         public void Add_AI(EntityYoutuber youtuber) {
         }

         @Override
         public void dropItems(EntityYoutuber entityYoutuber, int par1) {
            Youtubers.Drop_Item_Entity(entityYoutuber, new ItemStack(Items.BAKED_POTATO, com.electrifiedded.archaiclib.util.FastRandom.shared().nextInt(16 + 8 * par1) + 1));
            Youtubers.Drop_Item_Entity(entityYoutuber, new ItemStack(Items.FLINT_AND_STEEL));
            if (com.electrifiedded.archaiclib.util.FastRandom.shared().nextInt(4) == 0) {
               Youtubers.Drop_Item_Entity(entityYoutuber, new ItemStack(YTBlocks.blockYTNT, com.electrifiedded.archaiclib.util.FastRandom.shared().nextInt(1 + 2 * par1) + 1));
            } else {
               Youtubers.Drop_Item_Entity(entityYoutuber, new ItemStack(Blocks.TNT, com.electrifiedded.archaiclib.util.FastRandom.shared().nextInt(16 + 8 * par1) + 1));
            }
         }
      });
      ListYT.add(new Youtuber("", "popularMMOs", Youtubers.RGBtoInt(231, 133, 151), Youtubers.RGBtoInt(201, 82, 125), 2, "SuperGirlyGamer", "_2") {
         @Override
         public void Add_Trades(MerchantRecipeList recipeList, Random random, int Subtype) {
            YTList.Add_Generic_Trades(recipeList, random);
            recipeList.add(new MerchantRecipeYT(new ItemStack(Items.FISH, 1, 3), new ItemStack(YTItems.Money[5], 3)));
            ItemStack item = new ItemStack(YTItems.EpicPreportionsSword);
            item.addEnchantment(Enchantments.SHARPNESS, 15);
            item.addEnchantment(Enchantments.LOOTING, 5);
            item.addEnchantment(Enchantments.UNBREAKING, 10);
            item.addEnchantment(Enchantments.FIRE_ASPECT, 5);
            recipeList.add(new MerchantRecipeYT(new ItemStack(YTItems.Money[12], 3), item.copy()));
            item = new ItemStack(Items.GLOWSTONE_DUST);
            item.setStackDisplayName("Mind Control Dust");
            recipeList.add(new MerchantRecipeYT(new ItemStack(YTItems.Money[3], 3), item.copy()));
            YTList.Add_cLUCKey_Trades(recipeList, new int[]{0, 1, 2, 3, 4, 5, 6, 12, 18, 24, 30});
         }

         @Override
         public void Add_AI(EntityYoutuber youtuber) {
            youtuber.tasks.addTask(1, new EntityAIstaywithYoutuber(youtuber, "FuriousDestroyer", 50.0, 100.0, 1.0));
         }

         @Override
         public boolean Interact(EntityYoutuber youtuber, EntityPlayer player, ItemStack item) {
            if (item != null) {
               if (item.getItem() == Items.LEATHER_HELMET) {
                  if (!youtuber.getEquipmentInSlot(4).isEmpty()) {
                     if (!player.capabilities.isCreativeMode && !youtuber.world.isRemote) {
                        youtuber.entityDropItem(youtuber.getEquipmentInSlot(4), 0.0F);
                     }

                     youtuber.setCurrentItemOrArmor(4, null);
                  } else {
                     youtuber.setCurrentItemOrArmor(4, new ItemStack(YTItems.woodArmor.Head, 1, item.getMetadata()));
                     if (!player.capabilities.isCreativeMode) {
                        player.inventory.mainInventory.set(player.inventory.currentItem, ItemStack.EMPTY);
                     }
                  }

                  return true;
               }

               if (item.getItem() == Items.LEATHER_CHESTPLATE) {
                  if (!youtuber.getEquipmentInSlot(3).isEmpty()) {
                     if (!player.capabilities.isCreativeMode && !youtuber.world.isRemote) {
                        youtuber.entityDropItem(youtuber.getEquipmentInSlot(3), 0.0F);
                     }

                     youtuber.setCurrentItemOrArmor(3, null);
                  } else {
                     youtuber.setCurrentItemOrArmor(3, new ItemStack(YTItems.woodArmor.Chest, 1, item.getMetadata()));
                     if (!player.capabilities.isCreativeMode) {
                        player.inventory.mainInventory.set(player.inventory.currentItem, ItemStack.EMPTY);
                     }
                  }

                  return true;
               }

               if (item.getItem() == Items.LEATHER_LEGGINGS) {
                  if (!youtuber.getEquipmentInSlot(2).isEmpty()) {
                     if (!player.capabilities.isCreativeMode && !youtuber.world.isRemote) {
                        youtuber.entityDropItem(youtuber.getEquipmentInSlot(2), 0.0F);
                     }

                     youtuber.setCurrentItemOrArmor(2, null);
                  } else {
                     youtuber.setCurrentItemOrArmor(2, new ItemStack(YTItems.woodArmor.Legs, 1, item.getMetadata()));
                     if (!player.capabilities.isCreativeMode) {
                        player.inventory.mainInventory.set(player.inventory.currentItem, ItemStack.EMPTY);
                     }
                  }

                  return true;
               }

               if (item.getItem() == Items.LEATHER_BOOTS) {
                  if (!youtuber.getEquipmentInSlot(1).isEmpty()) {
                     if (!player.capabilities.isCreativeMode && !youtuber.world.isRemote) {
                        youtuber.entityDropItem(youtuber.getEquipmentInSlot(1), 0.0F);
                     }

                     youtuber.setCurrentItemOrArmor(1, null);
                  } else {
                     youtuber.setCurrentItemOrArmor(1, new ItemStack(YTItems.woodArmor.Boots, 1, item.getMetadata()));
                     if (!player.capabilities.isCreativeMode) {
                        player.inventory.mainInventory.set(player.inventory.currentItem, ItemStack.EMPTY);
                     }
                  }

                  return true;
               }
            }

            return false;
         }

         @Override
         public void dropItems(EntityYoutuber entityYoutuber, int par1) {
            Youtubers.Drop_Item_Entity(entityYoutuber, new ItemStack(Items.FISH, com.electrifiedded.archaiclib.util.FastRandom.shared().nextInt(16 + 8 * par1), 3));
            Youtubers.Drop_Item_Entity(entityYoutuber, new ItemStack(Blocks.WOOL, com.electrifiedded.archaiclib.util.FastRandom.shared().nextInt(16 + 8 * par1), 6));
         }
      });
      ListYT.add(new Youtuber("", "", Youtubers.RGBtoInt(0, 255, 18), Youtubers.RGBtoInt(245, 245, 235), 5, "Tobuscus") {
         @Override
         public void Add_Trades(MerchantRecipeList recipeList, Random random, int Subtype) {
            YTList.Add_Generic_Trades(recipeList, random);
            recipeList.add(new MerchantRecipeYT(new ItemStack(YTItems.Money[4], 7), new ItemStack(Blocks.LAPIS_BLOCK, 1, 0)));
         }
      });
      ListSP.add(
         new YoutuberSpawn(YTStructures.Map.get("arena"), 4, "FuriousDestroyer")
            .addSTM("FloatingCastle", 4)
            .addSTM("OPTree", 1)
            .addSTM("JenHouse", 4)
            .addST2("Fansion")
            .addST3("Blank", 4)
            .addST3("JenHole", 2)
            .addYT3("SuperGirlyGamer")
      );
      ListSP.add(new YoutuberSpawn(YTStructures.Map.get("TobyTower"), "Tobuscus"));
      if (YTConfig.reviewers) {
         ListYT.add(new Youtuber("Jmanmc", "reviewers", "Jmanbeatsgames") {
            @Override
            public void Add_Trades(MerchantRecipeList recipeList, Random random, int Subtype) {
               YTList.Add_Generic_Trades(recipeList, random);
            }
         });
         ListYT.add(new Youtuber("", "reviewers", Youtubers.RGBtoInt(0, 171, 218), Youtubers.RGBtoInt(136, 236, 247), 2, "pbayrockstar"));
         ListYT.add(new Youtuber("", "reviewers", Youtubers.RGBtoInt(56, 56, 55), Youtubers.RGBtoInt(255, 204, 51), 1, "KingBear15"));
         ListYT.add(new Youtuber("", "reviewers", Youtubers.RGBtoInt(41, 28, 15), Youtubers.RGBtoInt(28, 79, 20), 0, "AunaQuin"));
         ListYT.add(new Youtuber("", "reviewers", Youtubers.RGBtoInt(0, 0, 0), Youtubers.RGBtoInt(255, 255, 255), 0, "RockedSolid"));
         ListYT.add(new Youtuber("", "reviewers", Youtubers.RGBtoInt(0, 0, 0), Youtubers.RGBtoInt(255, 255, 255), 0, "thorin102"));
         ListYT.add(new Youtuber("", "reviewers", Youtubers.RGBtoInt(0, 0, 0), Youtubers.RGBtoInt(255, 255, 255), 0, "SylerGames11"));
         ListYT.add(new Youtuber("", "reviewers", Youtubers.RGBtoInt(0, 0, 0), Youtubers.RGBtoInt(255, 255, 255), 0, "EliteEGN"));
         ListYT.add(new Youtuber("", "reviewers", Youtubers.RGBtoInt(0, 0, 0), Youtubers.RGBtoInt(255, 255, 255), 0, "Leemanardo"));
         ListYT.add(new Youtuber("", "reviewers", Youtubers.RGBtoInt(0, 0, 0), Youtubers.RGBtoInt(255, 255, 255), 0, "DxtoryMC"));
         ListYT.add(new Youtuber("", "reviewers", Youtubers.RGBtoInt(0, 0, 0), Youtubers.RGBtoInt(255, 255, 255), 0, "siblingpower"));
         ListYT.add(new Youtuber("", "reviewers", Youtubers.RGBtoInt(0, 0, 0), Youtubers.RGBtoInt(255, 255, 255), 0, "Uglydollsrock9"));
         ListYT.add(new Youtuber("", "reviewers", Youtubers.RGBtoInt(0, 0, 0), Youtubers.RGBtoInt(255, 255, 255), 0, "MCRuler234") {
            @Override
            public void dropItems(EntityYoutuber entityYoutuber, int par1) {
               Youtubers.Drop_Item_Entity(entityYoutuber, new ItemStack(YTItems.SuperDiamond, com.electrifiedded.archaiclib.util.FastRandom.shared().nextInt(1 + 2 * par1) + 1));
            }
         });
         ListYT.add(new Youtuber("", "reviewers", Youtubers.RGBtoInt(0, 0, 0), Youtubers.RGBtoInt(255, 255, 255), 0, "doucet81") {
            @Override
            public void dropItems(EntityYoutuber entityYoutuber, int par1) {
               Youtubers.Drop_Item_Entity(entityYoutuber, new ItemStack(YTItems.SuperDiamond, com.electrifiedded.archaiclib.util.FastRandom.shared().nextInt(1 + 2 * par1) + 1));
            }
         });
         ListYT.add(new Youtuber("", "reviewers", Youtubers.RGBtoInt(0, 0, 0), Youtubers.RGBtoInt(255, 255, 255), 2, "Movresrax"));
         ListYT.add(new Youtuber("", "reviewers", Youtubers.RGBtoInt(0, 0, 0), Youtubers.RGBtoInt(255, 255, 255), 2, "zoomikins575"));
         ListYT.add(new Youtuber("", "reviewers", Youtubers.RGBtoInt(0, 0, 0), Youtubers.RGBtoInt(255, 255, 255), 2, "babysocco"));
         ListYT.add(new Youtuber("", "reviewers SleazeCo", Youtubers.RGBtoInt(0, 0, 0), Youtubers.RGBtoInt(255, 255, 255), 2, "ryguyrocky"));
         ListYT.add(new Youtuber("", "reviewers SleazeCo", Youtubers.RGBtoInt(0, 0, 0), Youtubers.RGBtoInt(255, 255, 255), 2, "Mr_Sleezy6969", "_FNAF_chika"));
         ListYT.add(new Youtuber("", "reviewers", Youtubers.RGBtoInt(0, 0, 0), Youtubers.RGBtoInt(255, 255, 255), 2, "NinjaProlog"));
         ListYT.add(new Youtuber("", "reviewers", Youtubers.RGBtoInt(0, 0, 0), Youtubers.RGBtoInt(255, 255, 255), 2, "2eke"));
         ListSP.add(
            new YoutuberSpawn(YTStructures.Map.get("Generic"), "Jmanbeatsgames")
               .addYTM("pbayrockstar")
               .addYTM("KingBear15")
               .addYTM("AunaQuin")
               .addYTM("RockedSolid")
               .addYTM("thorin102")
               .addYTM("SylerGames11")
               .addYTM("EliteEGN")
               .addYTM("Leemanardo")
               .addYTM("DxtoryMC")
               .addYTM("siblingpower")
               .addYTM("Uglydollsrock9")
               .addYTM("MCRuler234")
               .addYTM("doucet81")
               .addYTM("Movresrax")
               .addYTM("zoomikins575")
               .addYTM("babysocco")
               .addYTM("ryguyrocky")
               .addYTM("Mr_Sleezy6969")
               .addYTM("NinjaProlog")
               .addYTM("2eke")
               .setMLim(2)
         );
      }

      ListYT.add(new Youtuber("", "Other/new", Youtubers.RGBtoInt(255, 150, 150), Youtubers.RGBtoInt(0, 0, 0), 0, "The Minecraft Puppy"));
      BCother.addYTM("The Minecraft Puppy");
      if (YTConfig.Mojang) {
         ListYT.add(new Youtuber("", "Mojang", Youtubers.RGBtoInt(204, 4, 4), Youtubers.RGBtoInt(104, 0, 0), 5, "Notch") {
            @Override
            public void Add_Trades(MerchantRecipeList recipeList, Random random, int Subtype) {
               YTList.Add_Generic_Trades(recipeList, random);
               recipeList.add(new MerchantRecipeYT(new ItemStack(YTItems.Money[4], 7), new ItemStack(Items.APPLE, 16, 0)));
               recipeList.add(new MerchantRecipeYT(new ItemStack(YTItems.Money[4], 7), new ItemStack(Items.GOLDEN_APPLE, 1, 0)));
               recipeList.add(new MerchantRecipeYT(new ItemStack(YTItems.Money[5], 7), new ItemStack(Items.GOLDEN_APPLE, 1, 1)));
            }

            @Override
            public void Add_AI(EntityYoutuber youtuber) {
               youtuber.tasks.addTask(1, new EntityAIAlterNames(youtuber, "Herobrine", "REMOVED", 6.0));
            }
         });
         ListYT.add(new Youtuber("", "Mojang", Youtubers.RGBtoInt(204, 0, 104), Youtubers.RGBtoInt(104, 4, 204), 6, "Dinnerbone") {
            @Override
            public void Add_Trades(MerchantRecipeList recipeList, Random random, int Subtype) {
               YTList.Add_Generic_Trades(recipeList, random);
               recipeList.add(new MerchantRecipeYT(new ItemStack(YTItems.Money[4], 7), new ItemStack(Blocks.LAPIS_BLOCK, 1, 0)));
               ItemStack item = new ItemStack(Items.NAME_TAG, 2);
               item.setStackDisplayName("Dinnerbone");
               recipeList.add(new MerchantRecipeYT(new ItemStack(YTItems.Money[4], 7), item.copy()));
            }
         });
         ListYT.add(new Youtuber("", "Mojang", Youtubers.RGBtoInt(217, 219, 201), Youtubers.RGBtoInt(0, 68, 0), 1, "jeb_") {
            @Override
            public void Add_Trades(MerchantRecipeList recipeList, Random random, int Subtype) {
               YTList.Add_Generic_Trades(recipeList, random);
            }

            @Override
            public void Add_AI(EntityYoutuber youtuber) {
               youtuber.tasks.addTask(1, new EntityAIAlterNames(youtuber, "Herobrine", "REMOVED", 6.0));
            }
         });
         ListYT.add(new Youtuber("", "Mojang", Youtubers.RGBtoInt(217, 0, 0), Youtubers.RGBtoInt(60, 0, 0), 0, "Grumm") {
            @Override
            public void Add_Trades(MerchantRecipeList recipeList, Random random, int Subtype) {
               YTList.Add_Generic_Trades(recipeList, random);
               recipeList.add(new MerchantRecipeYT(new ItemStack(YTItems.Money[4], 7), new ItemStack(Blocks.LAPIS_BLOCK, 1, 0)));
               ItemStack item = new ItemStack(Items.NAME_TAG, 2);
               item.setStackDisplayName("Grumm");
               recipeList.add(new MerchantRecipeYT(new ItemStack(YTItems.Money[4], 7), item.copy()));
            }
         });
         ListSP.add(new YoutuberSpawn(YTStructures.Map.get("Mojang"), "Notch").addYTM("Dinnerbone").addYTM("jeb_").addYTM("Grumm").setMLim(2));
      }

      ListYT.add(
         new Youtuber("", "ModMaker", Youtubers.RGBtoInt(255, 198, 0), Youtubers.RGBtoInt(255, 60, 0), 1, "supercat765", "_TDM", "_Derp") {
            @Override
            public void Add_Trades(MerchantRecipeList recipeList, Random random, int Subtype) {
               YTList.Add_Generic_Trades(recipeList, random);

               for (int i = 0; i < YTStructures.Map.size(); i++) {
                  recipeList.add(
                     new MerchantRecipeYT(
                        new ItemStack(YTItems.Money[12], 10), new ItemStack(YTItems.Money[i / 64], 64 - i % 64), new ItemStack(YTItems.StructureSpawner, 1, i)
                     )
                  );
               }

               for (int i = 0; i < YTList.ListYT.size(); i++) {
                  recipeList.add(
                     new MerchantRecipeYT(
                        new ItemStack(YTItems.Money[1], 12), new ItemStack(YTItems.Money[i / 64], 64 - i % 64), new ItemStack(YTItems.Spawner, 1, i)
                     )
                  );
               }
            }

            @Override
            public void Add_AI(EntityYoutuber youtuber) {
               youtuber.tasks.addTask(1, new EntityAIAvoidEntityYT(youtuber, EntityWolf.class, 8.0F, 0.6, 0.6));
            }
         }
      );
      ListSP.add(
         new YoutuberSpawn(YTStructures.Map.get("Maze"), 2, "supercat765")
            .addSTM("jail", 5)
            .addSTM("arena", 5)
            .addSTM("Lab", 3)
            .addSTM("SkyBlock", 3)
            .addSTM("FloatingCastle", 5)
            .addSTM("TDM", 1)
            .addSTM("TobyTower", 3)
      );
      if (BChermicraft.YTmain.size() != 0) {
         ListSP.add(BChermicraft);
      }

      if (BCmindcrack.YTmain.size() != 0) {
         ListSP.add(BCmindcrack);
      }

      if (BCtcrafted.YTmain.size() != 0) {
         ListSP.add(BCtcrafted);
      }

      if (BCtrolls.YTmain.size() != 0) {
         ListSP.add(BCtrolls);
      }

      if (BCyoggs.YTmain.size() != 0) {
         ListSP.add(BCyoggs);
      }

      if (BCother.YTmain.size() != 0) {
         ListSP.add(BCother);
      }

      if (BCstory.YTmain.size() != 0) {
         ListSP.add(BCstory);
      }

      if (BCstamps.YTmain.size() != 0) {
         ListSP.add(BCstamps);
      }

      if (BCpack.YTmain.size() != 0) {
         ListSP.add(BCpack);
      }

      if (BClang.YTmain.size() != 0) {
         ListSP.add(BClang);
      }

      if (BCcreature.YTmain.size() != 0) {
         ListSP.add(BCcreature);
      }

      YoutuberList = new Youtuber[ListYT.size()];
      YoutuberList = ListYT.toArray(YoutuberList);
      System.out.println("Loaded " + YoutuberList.length + " Youtubers");
   }

   public static void SpawnEntity(BlockPos pos, Entity entity) {
      entity.setPosition(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
      entity.world.spawnEntity(entity);
   }

   public static void Add_Generic_Trades(MerchantRecipeList recipeList, Random random) {
      recipeList.add(new MerchantRecipeYT(new ItemStack(YTItems.Money[2], 7), new ItemStack(YTItems.Money[1], 1), new ItemStack(Items.IRON_INGOT, 5)));
      recipeList.add(new MerchantRecipeYT(new ItemStack(YTItems.Money[3], 7), new ItemStack(YTItems.Money[2], 1), new ItemStack(Items.GOLD_INGOT, 5)));
      recipeList.add(new MerchantRecipeYT(new ItemStack(YTItems.Money[4], 7), new ItemStack(YTItems.Money[2], 1), new ItemStack(Items.DIAMOND, 5)));
      recipeList.add(new MerchantRecipeYT(new ItemStack(YTItems.Money[4], 7), new ItemStack(YTItems.Money[3], 1), new ItemStack(Items.EMERALD, 1)));
      recipeList.add(new MerchantRecipeYT(new ItemStack(Items.IRON_INGOT, 5), new ItemStack(YTItems.Money[2], 7)));
      recipeList.add(new MerchantRecipeYT(new ItemStack(Items.GOLD_INGOT, 5), new ItemStack(YTItems.Money[3], 7)));
      recipeList.add(new MerchantRecipeYT(new ItemStack(Items.DIAMOND, 5), new ItemStack(YTItems.Money[4], 7)));
      recipeList.add(new MerchantRecipeYT(new ItemStack(Items.EMERALD, 1), new ItemStack(YTItems.Money[4], 7)));
   }

   public static void Add_Redstone_Trades(MerchantRecipeList recipeList, Random random) {
      recipeList.add(new MerchantRecipeYT(new ItemStack(YTItems.Money[4], 7), new ItemStack(Blocks.REDSTONE_BLOCK, 5, 0)));
      recipeList.add(new MerchantRecipeYT(new ItemStack(YTItems.Money[4], 7), new ItemStack(Blocks.REDSTONE_TORCH, 16, 0)));
      recipeList.add(new MerchantRecipeYT(new ItemStack(YTItems.Money[4], 7), new ItemStack(Items.COMPARATOR, 3, 0)));
      recipeList.add(new MerchantRecipeYT(new ItemStack(YTItems.Money[4], 7), new ItemStack(Blocks.HOPPER, 5, 0)));
      recipeList.add(new MerchantRecipeYT(new ItemStack(YTItems.Money[4], 7), new ItemStack(Blocks.NOTEBLOCK, 4, 0)));
      recipeList.add(new MerchantRecipeYT(new ItemStack(YTItems.Money[4], 7), new ItemStack(Blocks.PISTON, 4, 0)));
      recipeList.add(new MerchantRecipeYT(new ItemStack(YTItems.Money[4], 7), new ItemStack(Blocks.REDSTONE_LAMP, 8, 0)));
      recipeList.add(new MerchantRecipeYT(new ItemStack(YTItems.Money[4], 7), new ItemStack(Blocks.LEVER, 8, 0)));
      recipeList.add(new MerchantRecipeYT(new ItemStack(YTItems.Money[4], 7), new ItemStack(Blocks.STONE_BUTTON, 10, 0)));
      recipeList.add(new MerchantRecipeYT(new ItemStack(YTItems.Money[4], 7), new ItemStack(Blocks.STONE_PRESSURE_PLATE, 6, 0)));
      recipeList.add(new MerchantRecipeYT(new ItemStack(YTItems.Money[4], 7), new ItemStack(Blocks.WOODEN_PRESSURE_PLATE, 10, 0)));
      recipeList.add(new MerchantRecipeYT(new ItemStack(YTItems.Money[4], 7), new ItemStack(Blocks.HEAVY_WEIGHTED_PRESSURE_PLATE, 1, 0)));
      recipeList.add(new MerchantRecipeYT(new ItemStack(YTItems.Money[4], 7), new ItemStack(Blocks.LIGHT_WEIGHTED_PRESSURE_PLATE, 2, 0)));
      recipeList.add(new MerchantRecipeYT(new ItemStack(YTItems.Money[4], 7), new ItemStack(Blocks.TRAPPED_CHEST, 8, 0)));
      recipeList.add(new MerchantRecipeYT(new ItemStack(YTItems.Money[4], 7), new ItemStack(Blocks.DAYLIGHT_DETECTOR, 3, 0)));
   }

   public static void Add_cLUCKey_Trades(MerchantRecipeList recipeList, int[] list) {
      for (int i = 0; i < list.length; i++) {
         recipeList.add(
            new MerchantRecipeYT(
               new ItemStack(YTItems.Money[8], 4 + Item_cLUCKey_spawner.dataval[i][0] + Item_cLUCKey_spawner.dataval[i][1]),
               new ItemStack(YTItems.SpawnerLuckey, 1, list[i])
            )
         );
      }
   }

   public static void Add_Drunk_Trades(MerchantRecipeList recipeList, Random random) {
      int a = random.nextInt(10) + 20;

      for (int i = 0; i < a; i++) {
         recipeList.add(new MerchantRecipeYT(new ItemStack(YTItems.Money[7], random.nextInt(3) + 5), getPotion(random)));
      }
   }

   private static ItemStack getPotion(Random random) {
      ItemStack item = new ItemStack(Items.POTIONITEM, 1, 31);
      item = addCustomPotionEffect(item, 9, 3 + GetStreanth(random), random.nextInt(5000) + 6000);
      boolean speed = true;
      boolean mine = true;
      boolean health = true;
      boolean food = true;
      boolean damage = true;
      boolean regen = true;
      if (random.nextInt(10) == 0) {
         item = addCustomPotionEffect(item, 2, GetStreanth(random), random.nextInt(1000) + 4000);
         speed = false;
      }

      if (random.nextInt(15) == 0) {
         item = addCustomPotionEffect(item, 4, GetStreanth(random), random.nextInt(700) + 1000);
         mine = false;
      }

      if (random.nextInt(50) == 0) {
         item = addCustomPotionEffect(item, 7, GetStreanth(random) / 2, random.nextInt(150) + 50);
         health = false;
      }

      if (random.nextInt(30) == 0) {
         item = addCustomPotionEffect(item, 15, GetStreanth(random), random.nextInt(10000) + 1000);
      }

      if (random.nextInt(5) == 0) {
         item = addCustomPotionEffect(item, 17, GetStreanth(random), random.nextInt(9000) + 9000);
         food = false;
      }

      if (random.nextInt(30) == 0) {
         item = addCustomPotionEffect(item, 18, GetStreanth(random), random.nextInt(3000) + 2000);
         damage = false;
      }

      if (random.nextInt(10) == 0) {
         item = addCustomPotionEffect(item, 19, GetStreanth(random), random.nextInt(1000) + 1000);
         if (random.nextInt(4) < 3) {
            regen = false;
         }
      }

      if (random.nextInt(60) == 0) {
         item = addCustomPotionEffect(item, 20, GetStreanth(random), random.nextInt(1000) + 400);
         regen = false;
      }

      if (random.nextInt(20) == 0) {
         item = addCustomPotionEffect(item, 8, GetStreanth(random) * 2, random.nextInt(4000) + 600);
      }

      if (random.nextInt(15) == 0 && speed) {
         item = addCustomPotionEffect(item, 1, GetStreanth(random), random.nextInt(1000) + 4000);
      }

      if (random.nextInt(20) == 0 && mine) {
         item = addCustomPotionEffect(item, 3, GetStreanth(random), random.nextInt(700) + 1000);
      }

      if (random.nextInt(100) == 0 && health) {
         item = addCustomPotionEffect(item, 6, GetStreanth(random) / 4, random.nextInt(150) + 50);
      }

      if (random.nextInt(30) == 0) {
         item = addCustomPotionEffect(item, 16, GetStreanth(random), random.nextInt(10000) + 1000);
      }

      if (random.nextInt(8) == 0 && food) {
         item = addCustomPotionEffect(item, 23, GetStreanth(random), random.nextInt(9000) + 9000);
      }

      if (random.nextInt(40) == 0 && damage) {
         item = addCustomPotionEffect(item, 5, GetStreanth(random), random.nextInt(3000) + 2000);
      }

      if (random.nextInt(80) == 0 && regen) {
         item = addCustomPotionEffect(item, 10, GetStreanth(random), random.nextInt(1000) + 1000);
      }

      if (random.nextInt(40) == 0) {
         item = addCustomPotionEffect(item, 12, GetStreanth(random), random.nextInt(5000) + 1000);
      }

      if (random.nextInt(40) == 0) {
         item = addCustomPotionEffect(item, 13, GetStreanth(random), random.nextInt(9000) + 9000);
      }

      if (random.nextInt(40) == 0) {
         item = addCustomPotionEffect(item, 11, GetStreanth(random), random.nextInt(5000) + 1000);
      }

      if (random.nextInt(60) == 0) {
         item = addCustomPotionEffect(item, 22, GetStreanth(random) * 2, random.nextInt(2000) + 1000);
      }

      if (random.nextInt(120) == 0) {
         item = addCustomPotionEffect(item, 21, GetStreanth(random) * 2, random.nextInt(9000) + 9000);
      }

      return item;
   }

   private static int GetStreanth(Random random) {
      int a = 0;

      while (random.nextBoolean() && a < 100) {
         a++;
      }

      return a;
   }

   public static ItemStack addCustomPotionEffect(ItemStack nmsStack, int type, int amplifier, int duration) {
      NBTTagCompound tag = null;
      if (!nmsStack.hasTagCompound()) {
         tag = new NBTTagCompound();
         nmsStack.setTagCompound(tag);
      }

      if (tag == null) {
         tag = nmsStack.getTagCompound();
      }

      NBTTagList CustomPotionEffects = null;
      if (!tag.hasKey("CustomPotionEffects")) {
         CustomPotionEffects = new NBTTagList();
         tag.setTag("CustomPotionEffects", CustomPotionEffects);
      }

      if (CustomPotionEffects == null) {
         CustomPotionEffects = (NBTTagList)tag.getTag("CustomPotionEffects");
      }

      NBTTagCompound CustomPotionEffect = new NBTTagCompound();
      CustomPotionEffect.setByte("Id", (byte)type);
      CustomPotionEffect.setByte("Amplifier", (byte)amplifier);
      CustomPotionEffect.setInteger("Duration", duration);
      CustomPotionEffects.appendTag(CustomPotionEffect);
      tag.setTag("CustomPotionEffects", CustomPotionEffects);
      nmsStack.setTagCompound(tag);
      return nmsStack;
   }
}
