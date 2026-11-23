package com.electrifiedded.youtubers;

import com.electrifiedded.archaiclib.legacy.world.LegacyWorld;
import net.minecraft.util.math.BlockPos;
import com.electrifiedded.youtubers.WorldGen.GenArena;
import com.electrifiedded.youtubers.WorldGen.GenBlank;
import com.electrifiedded.youtubers.WorldGen.GenBuildGame;
import com.electrifiedded.youtubers.WorldGen.GenCC3Bannana;
import com.electrifiedded.youtubers.WorldGen.GenCC3Soda;
import com.electrifiedded.youtubers.WorldGen.GenCastle;
import com.electrifiedded.youtubers.WorldGen.GenCrainerCastle;
import com.electrifiedded.youtubers.WorldGen.GenDrunkHouse;
import com.electrifiedded.youtubers.WorldGen.GenFansion;
import com.electrifiedded.youtubers.WorldGen.GenFloatCastle;
import com.electrifiedded.youtubers.WorldGen.GenGenericHouse;
import com.electrifiedded.youtubers.WorldGen.GenJail;
import com.electrifiedded.youtubers.WorldGen.GenJenHole;
import com.electrifiedded.youtubers.WorldGen.GenJenHouse;
import com.electrifiedded.youtubers.WorldGen.GenJungleBace;
import com.electrifiedded.youtubers.WorldGen.GenKFC;
import com.electrifiedded.youtubers.WorldGen.GenLab;
import com.electrifiedded.youtubers.WorldGen.GenLuckeyBlockBattleArena;
import com.electrifiedded.youtubers.WorldGen.GenMaze;
import com.electrifiedded.youtubers.WorldGen.GenMelon;
import com.electrifiedded.youtubers.WorldGen.GenMojangOffice;
import com.electrifiedded.youtubers.WorldGen.GenOreMountain;
import com.electrifiedded.youtubers.WorldGen.GenPizzaSpleef;
import com.electrifiedded.youtubers.WorldGen.GenPotato;
import com.electrifiedded.youtubers.WorldGen.GenRocket;
import com.electrifiedded.youtubers.WorldGen.GenSkyBlock;
import com.electrifiedded.youtubers.WorldGen.GenSkyFactory;
import com.electrifiedded.youtubers.WorldGen.GenSnowLand;
import com.electrifiedded.youtubers.WorldGen.GenStampyhouse;
import com.electrifiedded.youtubers.WorldGen.GenTDM;
import com.electrifiedded.youtubers.WorldGen.GenTobuscusTower;
import com.electrifiedded.youtubers.WorldGen.GenTree;
import com.electrifiedded.youtubers.WorldGen.GenTreeOfEP;
import com.electrifiedded.youtubers.WorldGen.GenWastelandBase;
import com.electrifiedded.youtubers.WorldGen.GenWouldYouRather;
import com.electrifiedded.youtubers.WorldGen.GenYoutuberStructure;
import java.util.HashMap;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class YTStructures {
   public static HashMap<String, GenYoutuberStructure> Map = new HashMap<>();

   public static void Load() {
      Map.put("jail", new GenJail());
      Map.put("arena", new GenArena());
      Map.put("Lab", new GenLab());
      Map.put("Base", new GenJungleBace());
      Map.put("OPTree", new GenTreeOfEP());
      Map.put("KFC", new GenKFC());
      Map.put("melon", new GenMelon());
      Map.put("Wasteland", new GenWastelandBase());
      Map.put("TobyTower", new GenTobuscusTower());
      Map.put("Rocket", new GenRocket());
      Map.put("BigTree", new GenTree());
      Map.put("Butter", new GenCastle() {
         @Override
         public Block getBlock(Random rand) {
            return rand.nextInt(10) == 0 ? Blocks.GOLD_BLOCK : YTBlocks.blockButter;
         }
      });
      Map.put("potato", new GenPotato());
      Map.put("SnowLand", new GenSnowLand());
      Map.put("Generic", new GenGenericHouse());
      Map.put("LuckeyBoss", new GenLuckeyBlockBattleArena());
      Map.put("Mojang", new GenMojangOffice());
      Map.put("JenHole", new GenJenHole());
      Map.put("DiggyHole", new GenOreMountain());
      Map.put("Ice", new GenCastle() {
         @Override
         public Block getBlock(Random rand) {
            return Blocks.PACKED_ICE;
         }
      });
      Map.put("TNT", new GenCastle() {
         @Override
         public Block getBlock(Random rand) {
            return Blocks.TNT;
         }

         @Override
         public void Alter(World world, Random random, BlockPos pos) {
            int i = pos.getX();
            int j = pos.getY();
            int k = pos.getZ();
            LegacyWorld.setBlock(world, i, j - 1, k - 3, YTBlocks.blockYTNT);
            LegacyWorld.setBlock(world, i + 3, j + 2, k, YTBlocks.blockYTNT);
            LegacyWorld.setBlock(world, i, j + 2, k + 3, YTBlocks.blockYTNT);
            LegacyWorld.setBlock(world, i - 3, j + 2, k, YTBlocks.blockYTNT);
            LegacyWorld.setBlock(world, i, j + 2, k - 3, YTBlocks.blockYTNT);
         }
      });
      Map.put("DrunkHouse", new GenDrunkHouse());
      Map.put("Fansion", new GenFansion());
      Map.put("SkyFactory", new GenSkyFactory());
      Map.put("StampyHouse", new GenStampyhouse());
      Map.put("SkyBlock", new GenSkyBlock());
      Map.put("JenHouse", new GenJenHouse());
      Map.put("FloatingCastle", new GenFloatCastle());
      Map.put("Maze", new GenMaze());
      Map.put("CrainerCastle", new GenCrainerCastle());
      Map.put("TDM", new GenTDM());
      Map.put("BuildGame", new GenBuildGame());
      Map.put("CC3Bannana", new GenCC3Bannana());
      Map.put("CC3Soda", new GenCC3Soda());
      Map.put("SkyWYR", new GenWouldYouRather());
      Map.put("PizzaSpleef", new GenPizzaSpleef());
      Map.put("Blank", new GenBlank());
   }
}
