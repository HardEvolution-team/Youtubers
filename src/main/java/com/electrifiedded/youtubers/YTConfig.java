package com.electrifiedded.youtubers;

import net.minecraftforge.common.config.Configuration;

public class YTConfig {
   public static boolean Offline;
   public static boolean OPness;
   public static String[] PlayerNames = new String[]{
      "Ninja4liferyan",
      "MineralMiner532",
      "boushh20",
      "zekzava",
      "chelseastriker",
      "Chainr3action",
      "carpetfloor15",
      "loganhelper",
      "noqh123",
      "KingCandy8",
      "terdjerky12",
      "DragonVenom_",
      "Drewcraft99",
      "Far",
      "Herobrine",
      "ez",
      "Racccccer12",
      "UlyssesSlaughter",
      "newspaperboy55",
      "eatwhitefish",
      "AmpTxFeaR",
      "danteELITE",
      "SoulPL",
      "Ami2804",
      "Infamus",
      "Rufus1852",
      "Raymoo9",
      "Muhs",
      "MuHW",
      "Steelion",
      "Zee_Man",
      "jackpeneycad",
      "Mugz",
      "Goonerman97",
      "Mimoun",
      "bjoel2",
      "Pottux",
      "timdv",
      "DDJD9",
      "Damien27",
      "TheEyesOfLife",
      "muji",
      "Private_Public",
      "BrickInTheHead",
      "TimeZ",
      "rip1427",
      "lennonzb654",
      "Parger129",
      "Snowbar",
      "Vladivostok1",
      "Ginger879",
      "instagibb",
      "marhom",
      "paramime",
      "RyleLGS",
      "verbel",
      "trovanon",
      "Thorizzle",
      "NinjaManBLAM",
      "mudu",
      "Delirus",
      "SmickeyMcgee",
      "MathewMkay",
      "UncleMonteh",
      "BigZ94",
      "tomtheuseless",
      "Damien76",
      "Muff",
      "snijboon1",
      "fuzzyliam780",
      "Gigith",
      "Kanten",
      "Jimmybean",
      "Soulsy",
      "SoulSR",
      "Timic",
      "deadlus",
      "damien86",
      "Timin",
      "Hutchenstein",
      "damien95",
      "azoreanjeff",
      "quackstar84",
      "randomshot",
      "Triss247",
      "Tythiss",
      "samwise",
      "Murg",
      "loltank",
      "Murx",
      "Harbinger617",
      "musa",
      "killian123",
      "gumigoo",
      "Soulix",
      "BrettsFly",
      "BrianBoyko",
      "zarkboy",
      "Mupz",
      "Elad98",
      "tkranyak",
      "kazeako",
      "Turbopenguin42",
      "Tubbywatch",
      "zionishe",
      "johnyliltoe",
      "Riczone",
      "gamesta62",
      "AHungryHungarian",
      "mups",
      "varun_nath93",
      "Ratherdash",
      "XenosEriadin",
      "Vaedritol",
      "dannyrocker",
      "Mull",
      "Tonya01",
      "sully96",
      "NJL97",
      "interfect",
      "novack",
      "spacepotatoman",
      "Timan",
      "funkjosh",
      "teecubed333",
      "Snarkout89",
      "Nubsawce",
      "DoubleOBond",
      "Vault48",
      "Ninjila",
      "DevonWargod",
      "jkjk600",
      "Mulm",
      "nathan329",
      "Mimore",
      "reisyukaku",
      "Georukh",
      "Tokiko",
      "NotBaldwin"
   };
   public static boolean AllowDemo;
   public static double MoneyDropMultiplier;
   public static boolean LiveYoutuberSkins;
   public static int SpawnRarity;
   public static int[] spawnDimensions;
   public static boolean Mindcrack;
   public static boolean Hermitcraft;
   public static boolean TeamCrafted;
   public static boolean Mojang;
   public static boolean ItsJerryandHarry;
   public static boolean atlanicraft;
   public static boolean Yogscast;
   public static boolean drunk;
   public static boolean reviewers;
   public static boolean TDM;
   public static boolean Trolls;
   public static boolean People1;
   public static boolean People2;
   public static boolean People3;
   public static boolean People4;
   public static boolean People5;
   public static boolean People6;
   public static boolean People7;
   public static boolean People8;
   public static boolean People9;
   public static boolean People10;
   public static boolean People11;
   public static boolean People12;
   public static boolean People13;
   public static boolean Crundee;
   public static boolean storytellers;
   public static boolean non;
   public static boolean stampy;
   public static boolean Creatures;
   public static boolean nonEnglish;
   public static boolean ThePack;
   public static int cLuckLimit;
   public static int PlayerSpawn;
   public static int cLuckySpawn;
   public static int CreatureSpawn;
   public static boolean RedStone;
   public static boolean News;
   public static boolean SkyGames;

   public static void GetConfig(Configuration config) {
      cLuckLimit = config.get("cLuckies", "Minimum Level Limit", 1).getInt(1);
      Mindcrack = config.get("YoutuberGroups", "Mindcrack", true).getBoolean(true);
      Hermitcraft = config.get("YoutuberGroups", "Hermitcraft", true).getBoolean(true);
      Crundee = config.get("YoutuberGroups", "Crundee", true).getBoolean(true);
      TeamCrafted = config.get("YoutuberGroups", "TeamCrafted", true).getBoolean(true);
      SkyGames = config.get("YoutuberGroups", "SkyGames", true).getBoolean(true);
      ItsJerryandHarry = config.get("YoutuberGroups", "ItsJerryandHarry", true).getBoolean(true);
      Mojang = config.get("YoutuberGroups", "Mojang", true).getBoolean(true);
      atlanicraft = config.get("YoutuberGroups", "Atlanicraft", true).getBoolean(true);
      Yogscast = config.get("YoutuberGroups", "The Yogscast", true).getBoolean(true);
      drunk = config.get("YoutuberGroups", "Drunk Minecraft", true).getBoolean(true);
      TDM = config.get("YoutuberGroups", "The Diamond Minecart", true).getBoolean(true);
      Trolls = config.get("YoutuberGroups", "Trolls and Pranksters", true).getBoolean(true);
      RedStone = config.get("YoutuberGroups", "RedStone", true).getBoolean(true);
      News = config.get("YoutuberGroups", "News", true).getBoolean(true);
      People1 = config.get("YoutuberGroups", "Unsorted 1", true).getBoolean(true);
      People2 = config.get("YoutuberGroups", "Unsorted 2", true).getBoolean(true);
      People3 = config.get("YoutuberGroups", "Unsorted 3", true).getBoolean(true);
      People4 = config.get("YoutuberGroups", "Unsorted 4", true).getBoolean(true);
      People5 = config.get("YoutuberGroups", "Unsorted 5", true).getBoolean(true);
      People6 = config.get("YoutuberGroups", "Unsorted 6", true).getBoolean(true);
      People7 = config.get("YoutuberGroups", "Unsorted 7", true).getBoolean(true);
      People8 = config.get("YoutuberGroups", "Unsorted 8", true).getBoolean(true);
      People9 = config.get("YoutuberGroups", "Unsorted 9", true).getBoolean(true);
      People10 = config.get("YoutuberGroups", "Unsorted 10", true).getBoolean(true);
      People11 = config.get("YoutuberGroups", "Unsorted 11", true).getBoolean(true);
      People12 = config.get("YoutuberGroups", "Unsorted 12", true).getBoolean(true);
      People13 = config.get("YoutuberGroups", "Unsorted 13", true).getBoolean(true);
      storytellers = config.get("YoutuberGroups", "Storytellers?", true).getBoolean(true);
      non = config.get("YoutuberGroups", "non Minecrafters", true).getBoolean(true);
      stampy = config.get("YoutuberGroups", "Stampy and friends", true).getBoolean(true);
      Creatures = config.get("YoutuberGroups", "The Creatures", true).getBoolean(true);
      nonEnglish = config.get("YoutuberGroups", "Unsorted Non English", true).getBoolean(true);
      ThePack = config.get("YoutuberGroups", "ThePack", true).getBoolean(true);
      reviewers = config.get("YoutuberGroups", "Reviewers of this mod", true).getBoolean(true);
      MoneyDropMultiplier = config.get("Mob Drops", "Money Drop Multiplier", 100).getDouble(100.0);
      SpawnRarity = config.get("Structures", "Spawn Rarity", 10).getInt();
      spawnDimensions = config.get("Structures", "Spawn Dimensions", new int[]{0}).getIntList();
      AllowDemo = config.get("Reviewing", "Use Demo Structures", false).getBoolean(false);
      Offline = config.get("General", "Don't download player skins", false,
         "Random player NPCs normally wear the current skin of the player they are named after (looked up through the Mojang profile service, the 1.7 skin server is gone). True = always Steve/Alex.").getBoolean(false);
      LiveYoutuberSkins = config.get("General", "Use current youtuber skins", false,
         "True = youtubers wear the skin their account has today (from the Mojang profile service) instead of the skin bundled with the mod. Falls back to the bundled skin while loading or offline.").getBoolean(false);
      PlayerSpawn = config.get("General", "Player Spawn Rate", 50).getInt(50);
      cLuckySpawn = config.get("General", "cLucky Spawn Rate", 50).getInt(50);
      CreatureSpawn = config.get("General", "Creature Spawn Rate", 50).getInt(50);
      OPness = config.get("General", "Allow Over Powered Items", true).getBoolean(true);
   }
}
