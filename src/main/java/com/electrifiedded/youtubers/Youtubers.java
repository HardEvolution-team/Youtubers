package com.electrifiedded.youtubers;

import com.electrifiedded.archaiclib.legacy.registry.LegacyGameRegistry;
import com.electrifiedded.archaiclib.legacy.sound.LegacyModSounds;
import com.electrifiedded.archaiclib.legacy.world.LegacySpawns;
import com.electrifiedded.archaiclib.original.OriginalModAssets;
import com.electrifiedded.youtubers.Commands.LonelyCommand;
import com.electrifiedded.youtubers.Commands.OverEnchantCommand;
import com.electrifiedded.youtubers.Commands.SpawnCommand;
import com.electrifiedded.youtubers.Entity.EntityAdvEnderPearl;
import com.electrifiedded.youtubers.Entity.EntityBomby;
import com.electrifiedded.youtubers.Entity.EntityCraigTheMailman;
import com.electrifiedded.youtubers.Entity.EntityDiamondMinecart;
import com.electrifiedded.youtubers.Entity.EntityGrimm;
import com.electrifiedded.youtubers.Entity.EntitySnail;
import com.electrifiedded.youtubers.Entity.EntityTrayourus;
import com.electrifiedded.youtubers.Entity.EntityYoutuber;
import com.electrifiedded.youtubers.Entity.PlayerEntity;
import com.electrifiedded.youtubers.Entity.SpawnEntityInfo;
import com.electrifiedded.youtubers.Entity.Mobs.EntityCactus;
import com.electrifiedded.youtubers.Entity.Mobs.EntityEnderFish;
import com.electrifiedded.youtubers.Entity.Mobs.EntityExplosiveMushroom;
import com.electrifiedded.youtubers.Entity.Mobs.EntityMushroom;
import com.electrifiedded.youtubers.Entity.Mobs.EntityOceanCreeper;
import com.electrifiedded.youtubers.Entity.Mobs.EntityScorpion;
import com.electrifiedded.youtubers.Entity.TileEntity.TileEntityBank;
import com.electrifiedded.youtubers.Entity.TileEntity.TileEntityWetBeacon;
import com.electrifiedded.youtubers.Entity.cLUCKY.EntitycLuckey;
import com.electrifiedded.youtubers.GUI.YTGuiHandler;
import com.electrifiedded.youtubers.GUI.YTNetwork;
import com.electrifiedded.youtubers.Items.ArmorSet;
import com.electrifiedded.youtubers.RandUtil.RandomItem;
import com.electrifiedded.youtubers.WorldGen.GenArena;
import com.electrifiedded.youtubers.WorldGen.GenYoutuberStructure;
import com.electrifiedded.youtubers.proxy.CommonProxy;
import java.util.Random;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Port of Youtubers+ Mod (supercat765, 1.7.10) to 1.12.2. Textures and sounds come from the player's own copy of the
 * original jar in archaic_originals (OriginalModAssets); this jar ships no original asset.
 */
@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION, dependencies = "required-after:archaiclib@[1.40.0,)")
public class Youtubers {
   public static final String MODID = "youtubers";
   public static final String VERSION = Reference.VERSION;
   public static final String ORIGINAL_NAME = "youtubers-1.8.0-MC1.7.10.jar (Youtubers+ Mod 1.8.0 by supercat765)";
   public static final String ORIGINAL_URL = "https://www.curseforge.com/minecraft/mc-mods/youtubers-mod";
   public static SpawnEntityInfo[] MobSpawns = new SpawnEntityInfo[255];

   private static long tick10() {
      return System.currentTimeMillis() / 100L / 10L;
   }

   public static CreativeTabs tabArmor = new CreativeTabs("tabYTArmor") {
      @Override
      public ItemStack createIcon() {
         return new ItemStack(YTItems.JenArmor.Head);
      }

      @Override
      public ItemStack getIcon() {
         // 1.7 picked a random piece of a random set every second
         Random r = new Random(tick10());
         ArmorSet[] sets = {YTItems.JenArmor, YTItems.TDMArmor, YTItems.MarioArmor, YTItems.JoebuzArmor, YTItems.PatArmor, YTItems.MumboArmor,
            YTItems.TrueMUArmor, YTItems.XisumaArmor, YTItems.ModMakerArmor};
         ArmorSet s = sets[r.nextInt(sets.length)];
         switch (r.nextInt(4)) {
            case 0: return new ItemStack(s.Boots);
            case 1: return new ItemStack(s.Chest);
            case 3: return new ItemStack(s.Legs);
            default: return new ItemStack(s.Head);
         }
      }
   };
   public static CreativeTabs tabStructure = new CreativeTabs("tabYTStructure") {
      @Override
      public ItemStack createIcon() {
         return new ItemStack(YTItems.StructureSpawner);
      }

      @Override
      public ItemStack getIcon() {
         return new ItemStack(YTItems.StructureSpawner, 1, new Random(tick10()).nextInt(Math.max(1, YTStructures.Map.size())));
      }
   };
   public static CreativeTabs tabYoutubers = new CreativeTabs("tabYTYoutubers") {
      @Override
      public ItemStack createIcon() {
         return new ItemStack(YTItems.Spawner);
      }

      @Override
      public ItemStack getIcon() {
         return new ItemStack(YTItems.Spawner, 1, new Random(tick10()).nextInt(YTList.ListYT.size() + 1));
      }
   };
   public static CreativeTabs tabMisc = new CreativeTabs("tabYTMisc") {
      @Override
      public ItemStack createIcon() {
         return new ItemStack(YTItems.BankCard);
      }

      @Override
      public ItemStack getIcon() {
         return new ItemStack(YTItems.BankCard, 1, new Random(tick10()).nextInt(7));
      }
   };
   @Instance(MODID)
   public static Youtubers instance;
   @SidedProxy(clientSide = "com.electrifiedded.youtubers.proxy.ClientProxy", serverSide = "com.electrifiedded.youtubers.proxy.CommonProxy")
   public static CommonProxy proxy;

   public Youtubers() {
      // the original lang has the same keys as the port and is used as is; the port adds a few keys in its own lang
      OriginalModAssets.alias(MODID, "lang/en_us.lang", "lang/en_US.lang");
   }

   @EventHandler
   public void preInit(FMLPreInitializationEvent event) {
      com.electrifiedded.archaiclib.world.StagedWorld.init();
      OriginalModAssets.require(MODID, MODID, ORIGINAL_NAME, ORIGINAL_URL, MODID);
      Configuration config = new Configuration(event.getSuggestedConfigurationFile());
      config.load();
      YTConfig.GetConfig(config);
      config.save();
      LegacyModSounds.autoRegister(MODID);
      YTItems.Load();
      YTBlocks.Load();
      YTStructures.Load();
      this.registerEntities();
      YTList.Load();
      YTItems.registerListIcons();
      GameRegistry.registerTileEntity(TileEntityBank.class, new ResourceLocation(MODID, "bank"));
      GameRegistry.registerTileEntity(TileEntityWetBeacon.class, new ResourceLocation(MODID, "wet_beacon"));
      YTNetwork.init();
      proxy.preInit();
   }

   @EventHandler
   public void load(FMLInitializationEvent event) {
      YTItems.addRecipies();
      YTBlocks.addRecipes();
      MinecraftForge.EVENT_BUS.register(new YTEventHandler());
      NetworkRegistry.INSTANCE.registerGuiHandler(this, new YTGuiHandler());
      GameRegistry.registerWorldGenerator(new GenYoutuberStructure(), 1);
      this.registerSpawns();
      proxy.init();
   }

   @EventHandler
   public void postInit(FMLPostInitializationEvent event) {
   }

   @EventHandler
   public void serverLoad(FMLServerStartingEvent event) {
      event.registerServerCommand(new SpawnCommand());
      event.registerServerCommand(new LonelyCommand());
      event.registerServerCommand(new OverEnchantCommand());
   }

   private void registerEntities() {
      LegacyGameRegistry.registerModEntity(EntityYoutuber.class, "Youtuber", 0, this, 80, 3, true);
      LegacyGameRegistry.registerModEntity(PlayerEntity.class, "RandomPlayer", 1, this, 80, 3, true);
      LegacyGameRegistry.registerModEntity(EntityDiamondMinecart.class, "DiamondMinecart", 2, this, 80, 3, true);
      LegacyGameRegistry.registerModEntity(EntityAdvEnderPearl.class, "AdvEnderPearl", 3, this, 80, 3, true);
      // the cLUCKey eggs were not registered in 1.7 (invisible on clients); registered so they can be seen flying
      LegacyGameRegistry.registerModEntity(com.electrifiedded.youtubers.Entity.cLUCKY.EntityCrazyEgg.class, "CrazyEgg", 4, this, 64, 10, true);
      LegacyGameRegistry.registerModEntity(com.electrifiedded.youtubers.Entity.cLUCKY.EventEgg.class, "EventEgg", 5, this, 64, 10, true);
      LegacyGameRegistry.registerModEntity(EntitycLuckey.class, "cLUCKey", 31, this, 80, 3, true);
      this.addMob(32, true, 10, EntityOceanCreeper.class, "OceanicCreeper", 341456, RGBtoInt(0, 165, 0), 0);
      this.addMob(33, true, 10, EntityEnderFish.class, "EnderFish", 341456, RGBtoInt(0, 0, 0), 0);
      this.addMob(34, true, 10, EntityScorpion.class, "DesertScorpion", 13485467, RGBtoInt(68, 66, 56), 0);
      this.addMob(35, true, 10, EntityMushroom.class, "Mushroom", 7104634, RGBtoInt(112, 79, 53), 0);
      this.addMob(36, true, 10, EntityCactus.class, "Cactus", 13485467, RGBtoInt(0, 200, 0), 0);
      this.addMob(37, true, 10, EntityExplosiveMushroom.class, "ExplosiveMushroom", 7104634, RGBtoInt(112, 79, 53), 2);
      this.addMob(38, true, -5, EntitySnail.class, "Snail", RGBtoInt(112, 0, 0), RGBtoInt(0, 112, 0), 5);
      this.addMob(61, false, 0, EntityCraigTheMailman.class, "CraigTheMailman", RGBtoInt(0, 0, 100), RGBtoInt(0, 0, 200), 4);
      this.addMob(62, false, 0, EntityGrimm.class, "Grimm", RGBtoInt(255, 255, 255), RGBtoInt(240, 240, 240), 0);
      this.addMob(63, false, 0, EntityBomby.class, "Bomby", RGBtoInt(105, 255, 105), RGBtoInt(100, 240, 100), 0);
      LegacyGameRegistry.registerModEntity(EntityTrayourus.class, "Trayourus", 64, this, 80, 3, true);
   }

   private static void spawn(Class<? extends EntityLiving> cls, int weight, int min, int max, EnumCreatureType type, Biome... biomes) {
      if (weight > 0) LegacySpawns.addSpawn(cls, weight, min, max, type, biomes);
   }

   private void registerSpawns() {
      Biome[] people = {Biomes.BEACH, Biomes.EXTREME_HILLS, Biomes.EXTREME_HILLS_EDGE, Biomes.FOREST, Biomes.FOREST_HILLS, Biomes.JUNGLE,
         Biomes.JUNGLE_HILLS, Biomes.MUSHROOM_ISLAND, Biomes.MUSHROOM_ISLAND_SHORE, Biomes.OCEAN, Biomes.PLAINS, Biomes.RIVER, Biomes.SWAMPLAND};
      Biome[] water = {Biomes.BEACH, Biomes.OCEAN, Biomes.DEEP_OCEAN, Biomes.RIVER, Biomes.SWAMPLAND};
      Biome[] desert = {Biomes.DESERT, Biomes.DESERT_HILLS, Biomes.MESA, Biomes.MESA_ROCK, Biomes.MESA_CLEAR_ROCK};
      Biome[] shrooms = {Biomes.ROOFED_FOREST, Biomes.MUSHROOM_ISLAND, Biomes.MUSHROOM_ISLAND_SHORE, Biomes.SWAMPLAND};
      spawn(PlayerEntity.class, YTConfig.PlayerSpawn, 3, 4, EnumCreatureType.CREATURE, people);
      spawn(EntitycLuckey.class, YTConfig.cLuckySpawn, 1, 4, EnumCreatureType.CREATURE, people);
      spawn(EntityOceanCreeper.class, YTConfig.CreatureSpawn, 3, 4, EnumCreatureType.MONSTER, water);
      spawn(EntityEnderFish.class, YTConfig.CreatureSpawn, 3, 4, EnumCreatureType.MONSTER, water);
      spawn(EntityScorpion.class, YTConfig.CreatureSpawn, 3, 4, EnumCreatureType.MONSTER, desert);
      spawn(EntityCactus.class, YTConfig.CreatureSpawn, 3, 4, EnumCreatureType.MONSTER, desert);
      spawn(EntityMushroom.class, YTConfig.CreatureSpawn * 2, 3, 4, EnumCreatureType.MONSTER, shrooms);
      spawn(EntityExplosiveMushroom.class, YTConfig.CreatureSpawn, 3, 4, EnumCreatureType.MONSTER, shrooms);
      spawn(EntitySnail.class, YTConfig.CreatureSpawn, 3, 4, EnumCreatureType.CREATURE, Biomes.SWAMPLAND);
   }

   public static int RGBtoInt(int R, int G, int B) {
      return (R << 16) + (G << 8) + B;
   }

   /** Drops notes worth {@code amount}$ (largest notes first, same steps as the 1.7 mod). */
   public static void dropMoney(Entity mob, int amount) {
      int loopingLimit = amount;
      int[] value = {1, 5, 10, 50, 100, 500, 1000, 5000, 10000, 50000, 100000, 500000, 1000000};
      // 1.7 thresholds/decrements (some notes were subtracted with the value of the next lower note)
      int[] threshold = {1, 5, 10, 50, 100, 500, 1000, 5000, 10000, 50000, 100000, 500000, 1000000};
      int[] decrement = {1, 5, 10, 50, 100, 500, 1000, 5000, 10000, 10000, 100000, 100000, 1000000};
      do {
         boolean dropped = false;
         for (int a = 12; a >= 0; a--) {
            if (amount > threshold[a]) {
               Drop_Item_Entity(mob, new ItemStack(YTItems.Money[a], 1));
               amount -= decrement[a];
               dropped = true;
               break;
            }
         }
         if (!dropped) break;
      } while (amount > 0 && --loopingLimit > 0);
   }

   public static int GetYoutuberIDbyName(String Name) {
      for (int i = 0; i < YTList.YoutuberList.length; i++) {
         if (YTList.YoutuberList[i].Name.equals(Name) || YTList.YoutuberList[i].ID.equals(Name)) return i;
      }
      return -1;
   }

   public static void Drop_Item_Entity(Entity entity, ItemStack item) {
      Drop_Item(entity.world, entity.posX, entity.posY, entity.posZ, item);
   }

   public static void Drop_Item(World world, double x, double y, double z, ItemStack item) {
      if (!world.isRemote && !item.isEmpty()) world.spawnEntity(new EntityItem(world, x, y, z, item));
   }

   public void addMob(int id, boolean mobBattle, int weight, Class<? extends Entity> EntityClass, String Name, int ColorA, int ColorB, int Eggtype) {
      LegacyGameRegistry.registerModEntity(EntityClass, Name, id, this, 80, 3, true);
      MobSpawns[id] = new SpawnEntityInfo(EntityClass, Name, ColorA, ColorB, Eggtype);
      if (mobBattle) {
         int meta = com.electrifiedded.youtubers.Items.Spawners.ItemMobSpawner.getDamage(id);
         if (weight > 0) GenArena.SpawnEggs.add(new RandomItem(YTItems.MobSpawner, meta, weight));
         else GenArena.SpawnEggs2.add(new RandomItem(YTItems.MobSpawner, meta, -weight));
      }
   }

   public static void addPlayerTrades(MerchantRecipeList recipeList, String name, boolean override) {
      int id = GetYoutuberIDbyName(name);
      if (id != -1 && !override) {
         YTList.YoutuberList[id].Add_Trades(recipeList, new com.electrifiedded.archaiclib.util.FastRandom(), 0);
         return;
      }
      // offers of a random player are derived from his name, so the same name always sells the same things
      Random rand = new Random(name.isEmpty() ? 0 : name.charAt(0));
      for (int a = 1; a < name.length(); a++) rand.setSeed(rand.nextLong() + name.charAt(a));

      recipeList.add(new MerchantRecipeYT(new ItemStack(YTItems.Money[1], 5), new ItemStack(Items.BAKED_POTATO, 5)));
      ChanceAddTrade(1, 5, rand, recipeList, new ItemStack(Items.IRON_INGOT, 5), new ItemStack(YTItems.Money[2], 7));
      ChanceAddTrade(1, 25, rand, recipeList, new ItemStack(Items.GOLD_INGOT, 5), new ItemStack(YTItems.Money[3], 7));
      ChanceAddTrade(1, 50, rand, recipeList, new ItemStack(Items.DIAMOND, 5), new ItemStack(YTItems.Money[4], 7));
      ChanceAddTrade(1, 50, rand, recipeList, new ItemStack(Items.EMERALD, 1), new ItemStack(YTItems.Money[4], 7));
      for (int n = 1; n <= 5; n++) {
         ChanceAddTrade(1, 5, rand, recipeList, new ItemStack(YTItems.Money[2], 7), new ItemStack(YTItems.Money[1], n), new ItemStack(Items.IRON_INGOT, 5));
         ChanceAddTrade(1, 25, rand, recipeList, new ItemStack(YTItems.Money[3], 7), new ItemStack(YTItems.Money[2], n), new ItemStack(Items.GOLD_INGOT, 5));
         ChanceAddTrade(1, 50, rand, recipeList, new ItemStack(YTItems.Money[4], 7), new ItemStack(YTItems.Money[2], n), new ItemStack(Items.DIAMOND, 5));
         ChanceAddTrade(1, 50, rand, recipeList, new ItemStack(YTItems.Money[4], 7), new ItemStack(YTItems.Money[3], n), new ItemStack(Items.EMERALD, 1));
      }

      for (int n = recipeList.size(); n < 10; n++) {
         ItemStack junk;
         switch (rand.nextInt(22)) {
            case 0: junk = new ItemStack(Blocks.DIRT); break;
            case 1: junk = new ItemStack(Blocks.SAND, 1, rand.nextInt(2)); break;
            case 2: junk = new ItemStack(Blocks.GRAVEL); break;
            case 3: junk = new ItemStack(Items.MELON); break;
            case 4: junk = new ItemStack(Items.STICK); break;
            case 5: junk = new ItemStack(Items.PAPER); break;
            case 6: junk = new ItemStack(Items.BOAT); break;
            case 7: junk = new ItemStack(Items.POISONOUS_POTATO); break;
            case 8: junk = new ItemStack(Items.CARROT_ON_A_STICK); break;
            case 9: junk = new ItemStack(Items.SPIDER_EYE); break;
            case 10: junk = new ItemStack(Items.ARROW); break;
            case 11: junk = new ItemStack(Items.BOWL); break;
            case 12: junk = new ItemStack(Items.COOKIE); break;
            case 13: junk = new ItemStack(Items.ROTTEN_FLESH); break;
            case 14: junk = new ItemStack(Items.SUGAR); break;
            case 15: junk = new ItemStack(Items.CAKE); break;
            case 16: junk = new ItemStack(Blocks.BROWN_MUSHROOM_BLOCK); break;
            case 17: junk = new ItemStack(Blocks.MONSTER_EGG, 1, rand.nextInt(5)); break;
            case 18: junk = new ItemStack(Blocks.SPONGE); break;
            case 19: junk = new ItemStack(Blocks.TORCH); break;
            case 20: junk = new ItemStack(Blocks.GRASS); break;
            default: junk = new ItemStack(Items.CAULDRON);
         }
         junk.setStackDisplayName("Item Number:" + rand.nextInt());
         recipeList.add(new MerchantRecipeYT(new ItemStack(YTItems.Money[2], 3 + rand.nextInt(5)), junk));
      }

      if ("DragonVenom_".equals(name)) {
         ItemStack item = new ItemStack(Items.GOLDEN_SWORD);
         item.setStackDisplayName("Blazing Sword");
         item.addEnchantment(Enchantments.UNBREAKING, 88);
         item.addEnchantment(Enchantments.FIRE_ASPECT, 5);
         item.addEnchantment(Enchantments.FIRE_PROTECTION, 27);
         recipeList.add(new MerchantRecipeYT(new ItemStack(YTItems.Money[12], 3), item.copy()));
      }
   }

   public static void ChanceAddTrade(int a, int b, Random rand, MerchantRecipeList recipeList, ItemStack itemin, ItemStack itemout) {
      if (rand.nextInt(b) < a) recipeList.add(new MerchantRecipeYT(itemin, itemout));
   }

   public static void ChanceAddTrade(int a, int b, Random rand, MerchantRecipeList recipeList, ItemStack itemin1, ItemStack itemin2, ItemStack itemout) {
      if (rand.nextInt(b) < a) recipeList.add(new MerchantRecipeYT(itemin1, itemin2, itemout));
   }
}
