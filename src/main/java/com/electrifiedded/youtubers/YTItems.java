package com.electrifiedded.youtubers;

import com.electrifiedded.archaiclib.legacy.registry.LegacyGameRegistry;
import com.electrifiedded.archaiclib.legacy.registry.LegacyRecipes;
import com.electrifiedded.archaiclib.v1710.api.item.ArchaicItem;
import com.electrifiedded.youtubers.Entity.EntityTrayourus;
import com.electrifiedded.youtubers.Entity.SpawnEntityInfo;
import com.electrifiedded.youtubers.Entity.Youtuber;
import com.electrifiedded.youtubers.Items.ArmorSet;
import com.electrifiedded.youtubers.Items.ColoredCraftingItem;
import com.electrifiedded.youtubers.Items.CraftingItem;
import com.electrifiedded.youtubers.Items.ItemAdvEnderPearl;
import com.electrifiedded.youtubers.Items.ItemBankCard;
import com.electrifiedded.youtubers.Items.ItemBodilPrankKit;
import com.electrifiedded.youtubers.Items.ItemBoomPick;
import com.electrifiedded.youtubers.Items.ItemCluckyTool;
import com.electrifiedded.youtubers.Items.ItemDiamondMinecart;
import com.electrifiedded.youtubers.Items.ItemGiantSword;
import com.electrifiedded.youtubers.Items.ItemJetPack;
import com.electrifiedded.youtubers.Items.ItemMoney;
import com.electrifiedded.youtubers.Items.ItemPlayerTrade;
import com.electrifiedded.youtubers.Items.ItemSpeedKey;
import com.electrifiedded.youtubers.Items.ItemYTArmor;
import com.electrifiedded.youtubers.Items.Spawners.ItemMobSpawner;
import com.electrifiedded.youtubers.Items.Spawners.ItemSpawner;
import com.electrifiedded.youtubers.Items.Spawners.ItemStructureSpawner;
import com.electrifiedded.youtubers.Items.Spawners.Item_cLUCKey_spawner;
import com.electrifiedded.youtubers.Items.Spawners.PlayerSpawner;
import com.electrifiedded.youtubers.Items.Spawners.YoutuberSpawner;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;

public class YTItems {
   public static ArmorMaterial GREAT_Ssunglasses = ArmorSet.material("GREAT_Ssunglasses", 44, new int[]{5, 8, 7, 4}, 30);
   public static ArmorMaterial GREAT_MrCrainer = ArmorSet.material("GREAT_MrCrainer", 44, new int[]{5, 8, 7, 4}, 30);
   public static ToolMaterial BIGSWORDS = EnumHelper.addToolMaterial("YOUTUBERS_BIGSWORDS", 3, 1561, 8.0F, 21.0F, 10);
   /** item -> texture of its meta 0 icon, used by the youtuber eggs that show an item icon. */
   public static final Map<Item, String> ICONS = new HashMap<>();
   public static Item StructureSpawner;
   public static Item Spawner;
   public static Item SpawnerPL;
   public static Item SpawnerTrayourus;
   public static Item[] Money;
   public static Item BankCard;
   public static Item TobyFood_Nugget;
   public static Item TobyFood_Biscuit;
   public static Item TobyFood_Nugget_Biscuit;
   public static Item[] Bodiltrollkits;
   public static Item JetPack;
   public static Item BoomPick;
   public static Item DiamondMinecart;
   public static Item SpawnerLuckey;
   public static Item SpeedKey;
   public static Item MobSpawner;
   public static Item PlayerTrader;
   public static Item AdvPearl;
   public static ArmorSet PatArmor;
   public static ArmorSet JenArmor;
   public static ArmorSet TDMArmor;
   public static ItemYTArmor Ssunglasses;
   public static ArmorSet XisumaArmor;
   public static ArmorSet BiffaArmor;
   public static ArmorSet TrueMUArmor;
   public static ArmorSet LizardArmor;
   public static ItemYTArmor CrainerShirt;
   public static ArmorSet[] ButterArmor;
   public static Item Butter1;
   public static Item Butter2;
   public static Item Butter3;
   public static ArmorSet woodArmor;
   public static Item SuperDiamond;
   public static Item CluckTool;
   public static ArmorSet MumboArmor;
   public static ArmorSet JoebuzArmor;
   public static Item InsanityHoe;
   public static ArmorSet MarioArmor;
   public static ArmorSet LuigiArmor;
   public static ColoredCraftingItem ArmorGems;
   public static ArmorSet ModMakerArmor;
   public static Item CrazyCraftSword;
   public static Item EpicPreportionsSword;
   public static Item AtlantianSword;
   public static Item BudderSword;

   /** Registers an item (registry name = lower-case 1.7 name) with a single-texture icon. */
   public static <T extends Item> T reg(T item, String name, String texture) {
      LegacyGameRegistry.registerItem(item, name.toLowerCase(java.util.Locale.ROOT));
      if (texture != null) {
         ArchaicItem.registerMetaTexture(item, 0, texture);
         ICONS.put(item, texture);
      }
      return item;
   }

   public static ItemYTArmor armor(ItemYTArmor item, String setName) {
      item.setTranslationKey(setName + ItemYTArmor.ARMOR_TYPES[item.type]);
      return reg(item, setName + ItemYTArmor.ARMOR_TYPES[item.type], item.iconName());
   }

   /** preInit. */
   public static void Load() {
      LoadCraftingItems();
      LoadSpawners();
      LoadToolWeapons();
      LoadArmor();
      Money = new Item[13];
      for (int a = 0; a < 13; a++) Money[a] = reg(new ItemMoney(a), "ItemMoney" + a, "youtubers:Money");
      DiamondMinecart = reg(new ItemDiamondMinecart().setTranslationKey("DiamondMinecart"), "DiamondMinecart", "youtubers:DiamondMinecart");
      SpeedKey = reg(new ItemSpeedKey().setTranslationKey("speedkey"), "speedkey", "youtubers:speedkey");
      ArchaicItem.registerMetaTexture(SpeedKey, 1, "youtubers:speedkey");
      int[] sizes = {4, 8, 16, 32, 64, 128};
      Bodiltrollkits = new Item[sizes.length];
      for (int a = 0; a < sizes.length; a++) {
         Bodiltrollkits[a] = reg(new ItemBodilPrankKit(3, sizes[a]).setTranslationKey("BodilPrankKit.X" + sizes[a]), "BodilPrankKit_X" + sizes[a],
            "youtubers:BodilPrankKit_" + sizes[a]);
      }
      BankCard = reg(new ItemBankCard().setTranslationKey("ItemBankCard"), "ItemBankCard", null);
      for (int i = 0; i < 7; i++) ArchaicItem.registerMetaTexture(BankCard, i, "youtubers:BankCard_" + i);
      TobyFood_Nugget = reg(new ItemFood(4, 0.6F, true).setTranslationKey("TobyFood.Nugget"), "TobyFood_Nugget", "youtubers:ItemNugget");
      TobyFood_Biscuit = reg(new ItemFood(3, 0.6F, false).setTranslationKey("TobyFood.Biscuit"), "TobyFood_Biscuit", "youtubers:ItemBiscuit");
      TobyFood_Nugget_Biscuit = reg(new ItemFood(8, 0.6F, true).setTranslationKey("TobyFood.Nugget_Biscuit"), "TobyFood_Nugget_Biscuit",
         "youtubers:ItemNuggetInBiscuit");
      PlayerTrader = reg(new ItemPlayerTrade().setTranslationKey("PlayerTrader"), "PlayerTrader", "youtubers:PlayerTrader");
      ArchaicItem.registerMetaTexture(PlayerTrader, 1, "youtubers:PlayerTrader");
      AdvPearl = reg(new ItemAdvEnderPearl().setTranslationKey("AdvPearl"), "AdvPearl", "minecraft:ender_pearl");
      CluckTool = reg(new ItemCluckyTool().setTranslationKey("CluckTool"), "CluckTool", "minecraft:feather");
   }

   public static void LoadArmor() {
      JetPack = reg(new ItemJetPack().setTranslationKey("ItemJetPack"), "ItemJetPack", "youtubers:JetPack");
      PatArmor = new ArmorSet("PatArmor", 66, new int[]{5, 8, 7, 4}, 30);
      JenArmor = new ArmorSet("SuperGirlyGamer", 44, new int[]{5, 8, 7, 4}, 30);
      woodArmor = new ArmorSet("Wood", 5, new int[]{1, 3, 2, 1}, 15);
      TDMArmor = new ArmorSet("TDM", 66, new int[]{5, 8, 7, 4}, 30);
      XisumaArmor = new ArmorSet("Xisuma", 44, new int[]{5, 8, 7, 4}, 30);
      BiffaArmor = new ArmorSet("Biffa", 44, new int[]{5, 8, 7, 4}, 30);
      TrueMUArmor = new ArmorSet("TrueMU", 44, new int[]{5, 8, 7, 4}, 30);
      LizardArmor = new ArmorSet("Lizard", 44, new int[]{5, 8, 7, 4}, 30);
      MumboArmor = new ArmorSet("Mumbo", 44, new int[]{5, 8, 7, 4}, 30);
      JoebuzArmor = new ArmorSet("Joebuz", 55, new int[]{6, 9, 8, 5}, 30);
      Ssunglasses = new ItemYTArmor(GREAT_Ssunglasses, 0, "Ssunglasses");
      Ssunglasses.setTranslationKey("Ssunglasses");
      reg(Ssunglasses, "Ssunglasses", Ssunglasses.iconName());
      CrainerShirt = new ItemYTArmor(GREAT_MrCrainer, 1, "MrCrainer");
      CrainerShirt.setTranslationKey("CrainerShirt");
      reg(CrainerShirt, "CrainerShirt", CrainerShirt.iconName());
      ButterArmor = new ArmorSet[4];
      ButterArmor[0] = new ArmorSet("Budder", "Budder", 3, new int[]{1, 2, 2, 1}, 4);
      ButterArmor[1] = new ArmorSet("ReinforcedBudder", "Budder", 20, new int[]{2, 5, 4, 2}, 15);
      ButterArmor[2] = new ArmorSet("UltimaBudder", "Budder", 44, new int[]{5, 8, 7, 4}, 30);
      ButterArmor[3] = new ArmorSet("InfinaBudder", "Budder", 999, new int[]{50, 80, 70, 40}, 300);
      MarioArmor = new ArmorSet("Mario", 66, new int[]{5, 8, 7, 4}, 30);
      LuigiArmor = new ArmorSet("Luigi", 66, new int[]{5, 8, 7, 4}, 30);
      ModMakerArmor = new ArmorSet("Mod", 100, new int[]{10, 16, 14, 8}, 30);
   }

   public static void LoadToolWeapons() {
      BoomPick = reg(new ItemBoomPick().setTranslationKey("ItemBoomPick"), "ItemBoomPick", "minecraft:iron_pickaxe");
      InsanityHoe = reg(new ItemHoe(ToolMaterial.DIAMOND) {
         /** Tills every block in a sphere of radius 29.5 around the clicked block. */
         @Override
         public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hx, float hy, float hz) {
            int a = 0;
            for (int i = -30; i <= 30; i++) {
               for (int j = -30; j <= 30; j++) {
                  for (int k = -30; k <= 30; k++) {
                     if (Math.sqrt(i * i + j * j + k * k) < 29.5
                        && super.onItemUse(player, world, pos.add(i, j, k), hand, facing, hx, hy, hz) == EnumActionResult.SUCCESS) a++;
                  }
               }
            }
            return a != 0 ? EnumActionResult.SUCCESS : EnumActionResult.PASS;
         }
      }.setTranslationKey("InsanityHoe"), "InsanityHoe", "minecraft:gold_hoe");
      CrazyCraftSword = reg(new ItemGiantSword("crazycraftsword", BIGSWORDS), "crazycraftsword", "youtubers:CrazyCraftSword");
      EpicPreportionsSword = reg(new ItemGiantSword("EpicPreportionssword", BIGSWORDS), "EpicPreportionssword", "youtubers:EpicPreportionsSword");
      AtlantianSword = reg(new ItemGiantSword("AtlantianSword", BIGSWORDS), "AtlantianSword", "youtubers:AtlantianSword");
      BudderSword = reg(new ItemGiantSword("BudderSword", BIGSWORDS), "BudderSword", "youtubers:BudderSword");
   }

   public static void LoadCraftingItems() {
      Butter1 = reg(new CraftingItem("ReinforcedBudder", "minecraft:gold_ingot"), "ReinforcedBudder", "minecraft:gold_ingot");
      Butter2 = reg(new CraftingItem("UltimaBudder", "minecraft:gold_ingot"), "UltimaBudder", "minecraft:gold_ingot");
      Butter3 = reg(new CraftingItem("InfinaBudder", "minecraft:gold_ingot"), "InfinaBudder", "minecraft:gold_ingot");
      ArmorGems = reg(new ColoredCraftingItem("Gem", false), "Gem", null);
      for (int m = 0; m < 16; m++) ArchaicItem.registerMetaTexture(ArmorGems, m, ArmorGems.textureFor(m));
      SuperDiamond = reg(new CraftingItem("SuperDiamond", "minecraft:diamond").giveEfect(), "SuperDiamond", "minecraft:diamond");
   }

   public static void LoadSpawners() {
      StructureSpawner = reg(new ItemStructureSpawner().setTranslationKey("StructureSpawner"), "StructureSpawner", null);
      Spawner = reg(new YoutuberSpawner(), "ItemSpawner", null);
      SpawnerPL = reg(new PlayerSpawner().setTranslationKey("ItemSpawnerPL"), "ItemSpawnerPL", null);
      SpawnerTrayourus = new ItemSpawner(Youtubers.RGBtoInt(255, 255, 255), Youtubers.RGBtoInt(255, 255, 255), 0) {
         @Override
         public Entity getEntity(World world) {
            return new EntityTrayourus(world);
         }
      };
      SpawnerTrayourus.setCreativeTab(Youtubers.tabYoutubers);
      SpawnerTrayourus.setTranslationKey("SpawnerTrayourus");
      reg(SpawnerTrayourus, "SpawnerTrayourus", null);
      SpawnerLuckey = reg(new Item_cLUCKey_spawner().setTranslationKey("ItemSpawnerLUCK"), "ItemSpawnerLUCK", null);
      MobSpawner = reg(new ItemMobSpawner().setTranslationKey("ItemMobSpawner"), "ItemMobSpawner", null);
   }

   /** Icons that depend on the youtuber / mob / structure lists; call after those are loaded. */
   public static void registerListIcons() {
      String egg = "youtubers:spawn_egg";
      String spots = "youtubers:spawn_egg_overlay_";
      ArchaicItem.registerMetaTextureLayers(Spawner, 0, egg, spots + 0);
      for (int m = 1; m <= YTList.YoutuberList.length; m++) {
         Youtuber yt = YTList.YoutuberList[m - 1];
         if (yt.hasCustomIcon) {
            String icon = yt.item != null ? ICONS.getOrDefault(yt.item, egg) : "youtubers:" + yt.Name + "_egg";
            ArchaicItem.registerMetaTextureLayers(Spawner, m, icon);
         } else {
            ArchaicItem.registerMetaTextureLayers(Spawner, m, egg, spots + yt.Eggtype);
         }
      }
      ArchaicItem.registerMetaTextureLayers(SpawnerPL, 0, egg, spots + 0);
      ArchaicItem.registerMetaTextureLayers(SpawnerTrayourus, 0, egg, spots + ((ItemSpawner)SpawnerTrayourus).Type);
      for (int m = 0; m < 36; m++) ArchaicItem.registerMetaTextureLayers(SpawnerLuckey, m, egg, spots + 0);
      int m = 0;
      for (SpawnEntityInfo info : Youtubers.MobSpawns) {
         if (info != null) ArchaicItem.registerMetaTextureLayers(MobSpawner, m++, egg, spots + info.Eggtype);
      }
      for (int s = 0; s < YTStructures.Map.size(); s++) {
         ArchaicItem.registerMetaTexture(StructureSpawner, s, "youtubers:Structures/" + ItemStructureSpawner.nameOf(s));
      }
   }

   public static void addRecipies() {
      addRecipiesArmor();
      addRecipiesToolWeapons();
      addRecipiesCraftingItems();
      Object[] cardRim = {Items.PAPER, Items.IRON_INGOT, Items.REDSTONE, new ItemStack(Items.DYE, 1, 4), Items.GOLD_INGOT, Items.DIAMOND, Items.EMERALD};
      LegacyRecipes.addRecipe(new ItemStack(BankCard, 1, 0), "$o$", "o o", "$o$", 'o', Items.PAPER, '$', new ItemStack(Money[0]));
      for (int l = 1; l < 7; l++) {
         LegacyRecipes.addRecipe(new ItemStack(BankCard, 1, l), "$o$", "oco", "$o$", 'o', cardRim[l], '$', new ItemStack(Money[l * 2]),
            'c', new ItemStack(BankCard, 1, l - 1));
      }

      for (int i = 0; i < 13; i++) {
         ItemStack m = new ItemStack(Money[i]);
         if (i % 2 == 0) {
            if (i != 12) LegacyRecipes.addShapelessRecipe(new ItemStack(Money[i + 1]), m, m, m, m, m);
            if (i != 0) LegacyRecipes.addShapelessRecipe(new ItemStack(Money[i - 1], 2), m);
         } else {
            LegacyRecipes.addShapelessRecipe(new ItemStack(Money[i + 1]), m, m);
            LegacyRecipes.addShapelessRecipe(new ItemStack(Money[i - 1], 5), m);
         }
      }

      LegacyRecipes.addShapelessRecipe(new ItemStack(TobyFood_Nugget, 4), new ItemStack(Items.COOKED_CHICKEN, 1));
      LegacyRecipes.addShapelessRecipe(new ItemStack(TobyFood_Biscuit, 4), new ItemStack(Items.BREAD, 1));
      LegacyRecipes.addRecipe(new ItemStack(TobyFood_Nugget_Biscuit, 1), "b", "n", "b", 'n', TobyFood_Nugget, 'b', TobyFood_Biscuit);
   }

   public static void addRecipiesArmor() {
      PatArmor.addBasicRecipies(ArmorGems.Black);
      JenArmor.addBasicRecipies(ArmorGems.Pink);
      woodArmor.addBasicRecipies(new ItemStack(Blocks.LOG));
      LizardArmor.addBasicRecipies(ArmorGems.Green);
      MumboArmor.addBasicRecipies(ArmorGems.Gray);
      LegacyRecipes.addRecipe(new ItemStack(TDMArmor.Head), " e ", "e e", "ded", 'e', ArmorGems.Black, 'd', Items.DIAMOND);
      LegacyRecipes.addRecipe(new ItemStack(TDMArmor.Chest), "e e", "geg", "eee", 'e', ArmorGems.Black, 'g', Blocks.GOLD_BLOCK);
      LegacyRecipes.addRecipe(new ItemStack(TDMArmor.Legs), "eee", "g g", "e e", 'e', ArmorGems.Black, 'g', Blocks.GOLD_BLOCK);
      LegacyRecipes.addRecipe(new ItemStack(TDMArmor.Boots), "g g", "e e", 'e', ArmorGems.Black, 'g', Blocks.GOLD_BLOCK);
      LegacyRecipes.addRecipe(new ItemStack(JoebuzArmor.Head), "eee", "ded", 'e', Items.FEATHER, 'd', SuperDiamond);
      LegacyRecipes.addRecipe(new ItemStack(JoebuzArmor.Chest), "e e", "geg", "eee", 'e', Items.FEATHER, 'g', SuperDiamond);
      LegacyRecipes.addRecipe(new ItemStack(JoebuzArmor.Legs), "eee", "g g", "e e", 'e', Items.FEATHER, 'g', SuperDiamond);
      LegacyRecipes.addRecipe(new ItemStack(JoebuzArmor.Boots), "g g", "e e", 'e', Items.FEATHER, 'g', SuperDiamond);
      LegacyRecipes.addRecipe(new ItemStack(TrueMUArmor.Head), " e ", "ege", "eee", 'e', ArmorGems.Blue, 'g', Blocks.GOLD_BLOCK);
      LegacyRecipes.addRecipe(new ItemStack(TrueMUArmor.Chest), "e e", "eee", "eee", 'e', ArmorGems.Blue);
      LegacyRecipes.addRecipe(new ItemStack(TrueMUArmor.Legs), "eee", "e e", "e e", 'e', ArmorGems.Blue);
      LegacyRecipes.addRecipe(new ItemStack(TrueMUArmor.Boots), "e e", "e e", 'e', ArmorGems.Blue);
      LegacyRecipes.addRecipe(new ItemStack(XisumaArmor.Head), " e ", "ege", "eee", 'e', ArmorGems.Lime, 'g', ArmorGems.Blue);
      LegacyRecipes.addRecipe(new ItemStack(XisumaArmor.Chest), "e e", "eee", "ege", 'e', ArmorGems.Lime, 'g', ArmorGems.Gray);
      LegacyRecipes.addRecipe(new ItemStack(XisumaArmor.Legs), "eee", "e e", "e e", 'e', ArmorGems.Lime);
      LegacyRecipes.addRecipe(new ItemStack(XisumaArmor.Boots), "e e", "e e", 'e', ArmorGems.Lime);
      LegacyRecipes.addRecipe(new ItemStack(BiffaArmor.Head), " g ", "e e", "eee", 'e', ArmorGems.Pink, 'g', Blocks.GOLD_BLOCK);
      LegacyRecipes.addRecipe(new ItemStack(BiffaArmor.Chest), "e e", "eee", "geg", 'e', ArmorGems.Pink, 'g', Blocks.GOLD_BLOCK);
      LegacyRecipes.addRecipe(new ItemStack(BiffaArmor.Legs), "ggg", "e e", "e e", 'e', ArmorGems.Pink, 'g', Blocks.GOLD_BLOCK);
      LegacyRecipes.addRecipe(new ItemStack(BiffaArmor.Boots), "e e", "g g", 'e', ArmorGems.Pink, 'g', Blocks.GOLD_BLOCK);
      LegacyRecipes.addRecipe(new ItemStack(Ssunglasses), "e e", "eee", 'e', ArmorGems.Black);
      LegacyRecipes.addRecipe(new ItemStack(CrainerShirt), "q e", "asd", "zxc", 'q', ArmorGems.Red, 'e', ArmorGems.Blue, 'a', ArmorGems.Lime,
         's', Items.LEATHER_CHESTPLATE, 'd', ArmorGems.Green, 'z', ArmorGems.Pink, 'x', SuperDiamond, 'c', ArmorGems.Orange);
      LegacyRecipes.addRecipe(new ItemStack(ModMakerArmor.Head), "drd", "odo", 'r', ArmorGems.Red, 'o', ArmorGems.Orange, 'd', SuperDiamond);
      LegacyRecipes.addRecipe(new ItemStack(ModMakerArmor.Chest), "d d", "odo", "rdr", 'r', ArmorGems.Red, 'o', ArmorGems.Orange, 'd', SuperDiamond);
      LegacyRecipes.addRecipe(new ItemStack(ModMakerArmor.Legs), "ddd", "o o", "r r", 'r', ArmorGems.Red, 'o', ArmorGems.Orange, 'd', SuperDiamond);
      LegacyRecipes.addRecipe(new ItemStack(ModMakerArmor.Boots), "d d", "o o", 'r', ArmorGems.Red, 'o', ArmorGems.Orange, 'd', SuperDiamond);
      ButterArmor[0].addBasicRecipies(new ItemStack(YTBlocks.blockButter));
      ButterArmor[1].addBasicRecipies(new ItemStack(Butter1));
      ButterArmor[2].addBasicRecipies(new ItemStack(Butter2));
      if (YTConfig.OPness) ButterArmor[3].addBasicRecipies(new ItemStack(Butter3));
      MarioArmor.addBasicRecipies(ArmorGems.Red);
      LuigiArmor.addBasicRecipies(ArmorGems.Lime);
   }

   public static void addRecipiesToolWeapons() {
      LegacyRecipes.addRecipe(new ItemStack(SpeedKey, 1, 0), "ggg", "gsg", "ggg", 'g', Blocks.GOLD_BLOCK, 's', Items.SUGAR);
      LegacyRecipes.addRecipe(new ItemStack(SpeedKey, 1, 1), "ggg", "gsg", "ggg", 'g', new ItemStack(Items.GOLDEN_APPLE, 1, 1), 's', Items.NETHER_STAR);
      LegacyRecipes.addRecipe(new ItemStack(PlayerTrader, 1, 0), "iii", "imi", "iri", 'i', Items.IRON_INGOT, 'm', new ItemStack(Money[0]), 'r', Items.REDSTONE);
      LegacyRecipes.addRecipe(new ItemStack(PlayerTrader, 1, 1), "did", "mtm", "drd", 'd', Items.DIAMOND, 'i', Items.IRON_INGOT, 'm', new ItemStack(Money[8]),
         'r', Items.REDSTONE);
      LegacyRecipes.addRecipe(new ItemStack(InsanityHoe), "gg", "s ", "s ", 'g', Butter3, 's', Items.STICK);
      LegacyRecipes.addRecipe(new ItemStack(InsanityHoe), "gg", " s", " s", 'g', Butter3, 's', Items.STICK);
      LegacyRecipes.addRecipe(new ItemStack(BoomPick), "ddd", "bsb", "bsb", 'd', Items.DIAMOND, 's', Items.STICK, 'b', Bodiltrollkits[3]);
      LegacyRecipes.addRecipe(new ItemStack(BoomPick), "b b", " p ", "b b", 'p', Items.DIAMOND_PICKAXE, 'b', Bodiltrollkits[3]);
      LegacyRecipes.addRecipe(new ItemStack(Bodiltrollkits[0]), "bsb", "sss", "bsb", 'b', Items.TNT_MINECART, 's', Items.SLIME_BALL);
      LegacyRecipes.addRecipe(new ItemStack(Bodiltrollkits[1]), "sss", "bsb", "sss", 'b', Bodiltrollkits[0], 's', Items.SLIME_BALL);
      LegacyRecipes.addRecipe(new ItemStack(Bodiltrollkits[2]), "sss", "bmb", "sss", 'b', Bodiltrollkits[1], 's', Items.SLIME_BALL, 'm', Items.IRON_INGOT);
      LegacyRecipes.addRecipe(new ItemStack(Bodiltrollkits[3]), "sss", "bmb", "sss", 'b', Bodiltrollkits[2], 's', Items.SLIME_BALL, 'm', Items.DIAMOND);
      LegacyRecipes.addRecipe(new ItemStack(Bodiltrollkits[4]), "sss", "bmb", "sss", 'b', Bodiltrollkits[3], 's', Items.SLIME_BALL, 'm', Items.EMERALD);
      LegacyRecipes.addRecipe(new ItemStack(Bodiltrollkits[5]), "sss", "bmb", "sss", 'b', Bodiltrollkits[4], 's', Items.SLIME_BALL, 'm', SuperDiamond);
      LegacyRecipes.addRecipe(new ItemStack(EpicPreportionsSword), " ee", "eee", "see", 's', Items.STICK, 'e', ArmorGems.Black);
      LegacyRecipes.addRecipe(new ItemStack(AtlantianSword), "e", "e", "s", 's', Items.GOLD_INGOT, 'e', SuperDiamond);
      LegacyRecipes.addRecipe(new ItemStack(BudderSword), " ee", "eee", "see", 's', Items.STICK, 'e', Butter2);
      LegacyRecipes.addRecipe(new ItemStack(CrazyCraftSword), " ee", "eee", "see", 's', Items.STICK, 'e', Butter2);
   }

   public static void addRecipiesCraftingItems() {
      LegacyRecipes.addRecipe(new ItemStack(Butter1), "ooo", "oeo", "ooo", 'o', Items.GOLD_INGOT, 'e', YTBlocks.blockButter);
      LegacyRecipes.addRecipe(new ItemStack(Butter2), "ooo", "oeo", "ooo", 'o', Blocks.GOLD_BLOCK, 'e', Butter1);
      LegacyRecipes.addRecipe(new ItemStack(Butter3), "ooo", "oeo", "ooo", 'o', Butter2, 'e', Butter1);
      LegacyRecipes.addRecipe(ArmorGems.Black.copy(), "ooo", "oeo", "ooo", 'o', Blocks.OBSIDIAN, 'e', Items.EMERALD);
      LegacyRecipes.addRecipe(ArmorGems.Pink.copy(), "ooo", "oeo", "ooo", 'o', new ItemStack(Blocks.WOOL, 1, 6), 'e', Items.EMERALD);
      LegacyRecipes.addRecipe(ArmorGems.Green.copy(), "aCa", "beb", "cBc", 'a', new ItemStack(Blocks.STAINED_GLASS_PANE, 1, 13), 'b', new ItemStack(Blocks.WOOL, 1, 13),
         'c', new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, 13), 'e', Items.EMERALD, 'C', new ItemStack(Blocks.CACTUS), 'B', Blocks.MELON_BLOCK);
      LegacyRecipes.addRecipe(ArmorGems.Gray.copy(), "ooo", "oeo", "ooo", 'o', new ItemStack(Blocks.STONEBRICK), 'e', Items.EMERALD);
      LegacyRecipes.addRecipe(new ItemStack(SuperDiamond, 9), "ooo", "oeo", "ooo", 'o', Items.DIAMOND, 'e', Items.NETHER_STAR);
      LegacyRecipes.addRecipe(ArmorGems.Blue.copy(), "ooo", "beb", "ooo", 'o', Blocks.LAPIS_BLOCK, 'b', Items.LAVA_BUCKET, 'e', Items.EMERALD);
      LegacyRecipes.addRecipe(new ItemStack(ArmorGems, 8, ArmorGems.Lime.getMetadata()), "ooo", "oeo", "ooo", 'o', Items.EMERALD, 'e', Items.DIAMOND);
      // the 1.7 red/orange gem recipes used an undefined 'o' (= empty slot) - LegacyRecipes keeps that meaning
      LegacyRecipes.addRecipe(ArmorGems.Red.copy(), "oso", "ses", "oso", 'b', Items.LAVA_BUCKET, 's', new ItemStack(Blocks.SAND, 1, 1), 'e', Items.EMERALD);
      LegacyRecipes.addRecipe(ArmorGems.Orange.copy(), "oso", "ses", "oso", 'b', Items.LAVA_BUCKET, 's', new ItemStack(Blocks.SAND, 1, 1), 'e', SuperDiamond);
   }
}
