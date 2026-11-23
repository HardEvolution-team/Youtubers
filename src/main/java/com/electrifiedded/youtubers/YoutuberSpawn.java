package com.electrifiedded.youtubers;

import com.electrifiedded.archaiclib.world.StagedWorld;
import com.electrifiedded.youtubers.Entity.EntityYoutuber;
import net.minecraft.util.math.BlockPos;
import com.electrifiedded.youtubers.RandUtil.RandomStruc;
import com.electrifiedded.youtubers.WorldGen.GenBlank;
import com.electrifiedded.youtubers.WorldGen.GenYoutuberStructure;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.WeightedRandom;
import net.minecraft.world.World;

public class YoutuberSpawn {
   public List<RandomStruc> main = new ArrayList<>();
   public List<String> YTmain = new ArrayList<>();
   public int mainLim;
   public List<RandomStruc> sec = new ArrayList<>();
   public List<String> YTsec = new ArrayList<>();
   public int secLim;
   public List<RandomStruc> tir = new ArrayList<>();
   public List<String> YTtir = new ArrayList<>();
   public int tirLim;

   public YoutuberSpawn(GenYoutuberStructure struc, String name) {
      this.main.add(new RandomStruc(struc, 5));
      this.YTmain.add(name);
      this.mainLim = 1000;
      this.secLim = 1000;
      this.tirLim = 1000;
   }

   public YoutuberSpawn(GenYoutuberStructure struc, int rare, String name) {
      this.main.add(new RandomStruc(struc, rare));
      this.YTmain.add(name);
      this.mainLim = 1000;
      this.secLim = 1000;
      this.tirLim = 1000;
   }

   public YoutuberSpawn() {
      this.main.add(new RandomStruc(YTStructures.Map.get("Generic"), 5));
      this.mainLim = 1000;
      this.secLim = 1000;
      this.tirLim = 1000;
   }

   public YoutuberSpawn setMLim(int lim) {
      this.mainLim = lim;
      return this;
   }

   public YoutuberSpawn set2Lim(int lim) {
      this.secLim = lim;
      return this;
   }

   public YoutuberSpawn set3Lim(int lim) {
      this.tirLim = lim;
      return this;
   }

   public YoutuberSpawn addYTM(String name) {
      this.YTmain.add(name);
      return this;
   }

   public YoutuberSpawn addYT2(String name) {
      this.YTsec.add(name);
      return this;
   }

   public YoutuberSpawn addYT3(String name) {
      this.YTtir.add(name);
      return this;
   }

   public YoutuberSpawn addSTM(GenYoutuberStructure struc) {
      this.main.add(new RandomStruc(struc, 5));
      return this;
   }

   public YoutuberSpawn addST2(GenYoutuberStructure struc) {
      this.sec.add(new RandomStruc(struc, 5));
      return this;
   }

   public YoutuberSpawn addST3(GenYoutuberStructure struc) {
      this.tir.add(new RandomStruc(struc, 5));
      return this;
   }

   public YoutuberSpawn addSTM(GenYoutuberStructure struc, int rare) {
      this.main.add(new RandomStruc(struc, rare));
      return this;
   }

   public YoutuberSpawn addST2(GenYoutuberStructure struc, int rare) {
      this.sec.add(new RandomStruc(struc, rare));
      return this;
   }

   public YoutuberSpawn addST3(GenYoutuberStructure struc, int rare) {
      this.tir.add(new RandomStruc(struc, rare));
      return this;
   }

   public YoutuberSpawn addSTM(String struc) {
      this.main.add(new RandomStruc(YTStructures.Map.get(struc), 5));
      return this;
   }

   public YoutuberSpawn addST2(String struc) {
      this.sec.add(new RandomStruc(YTStructures.Map.get(struc), 5));
      return this;
   }

   public YoutuberSpawn addST3(String struc) {
      this.tir.add(new RandomStruc(YTStructures.Map.get(struc), 5));
      return this;
   }

   public YoutuberSpawn addSTM(String struc, int rare) {
      this.main.add(new RandomStruc(YTStructures.Map.get(struc), rare));
      return this;
   }

   public YoutuberSpawn addST2(String struc, int rare) {
      this.sec.add(new RandomStruc(YTStructures.Map.get(struc), rare));
      return this;
   }

   public YoutuberSpawn addST3(String struc, int rare) {
      this.tir.add(new RandomStruc(YTStructures.Map.get(struc), rare));
      return this;
   }

   public void spawnin(World world, BlockPos pos, Random rand) {
      GenYoutuberStructure Smain = ((RandomStruc)WeightedRandom.getRandomItem(rand, this.main)).stru;
      Smain.generate(world, rand, pos);
      if (this.mainLim < this.YTmain.size()) {
         Collections.shuffle(this.YTmain);
      }

      for (int a = 0; a < this.mainLim && a < this.YTmain.size(); a++) {
         int YTid = Youtubers.GetYoutuberIDbyName(this.YTmain.get(a));
         BlockPos loc = Smain.getSpawnLoc(pos);
         this.addYoutuber(world, loc, this.YTmain.get(a));
      }

      GenYoutuberStructure Ssec;
      if (this.sec.size() == 0) {
         Ssec = new GenBlank();
      } else {
         Ssec = ((RandomStruc)WeightedRandom.getRandomItem(rand, this.sec)).stru;
      }

      float angle = rand.nextFloat() * (float) (Math.PI * 2);
      int r = rand.nextInt(20) + 30;
      int xsec = (int)(r * Math.sin(angle));
      int zsec = (int)(r * Math.cos(angle));
      int ysec = StagedWorld.getTopSolidOrLiquidBlock(world, pos.getX() + xsec, pos.getZ() + zsec);
      Ssec.generate(world, rand, new BlockPos(pos.getX() + xsec, ysec, pos.getZ() + zsec));
      if (this.mainLim < this.YTmain.size()) {
         Collections.shuffle(this.YTsec);
      }

      for (int a = 0; a < this.secLim && a < this.YTsec.size(); a++) {
         int YTid = Youtubers.GetYoutuberIDbyName(this.YTsec.get(a));
         if (Ssec instanceof GenBlank) {
            BlockPos loc = Smain.getSpawnLoc(pos);
            this.addYoutuber(world, loc, this.YTsec.get(a));
         } else {
            BlockPos loc = Ssec.getSpawnLoc(new BlockPos(pos.getX() + xsec, ysec, pos.getZ() + zsec));
            this.addYoutuber(world, loc, this.YTsec.get(a));
         }
      }

      GenYoutuberStructure Stir;
      if (this.tir.size() == 0) {
         Stir = new GenBlank();
      } else {
         Stir = ((RandomStruc)WeightedRandom.getRandomItem(rand, this.tir)).stru;
      }

      float angle2 = (float)(angle + rand.nextFloat() * (float) (Math.PI * 3.0 / 2.0) + (Math.PI / 4));
      int r2 = rand.nextInt(20) + 30;
      int xtir = (int)(r2 * Math.sin(angle2));
      int ztir = (int)(r2 * Math.cos(angle2));
      int ytir = StagedWorld.getTopSolidOrLiquidBlock(world, pos.getX() + xtir, pos.getZ() + ztir);
      Stir.generate(world, rand, new BlockPos(pos.getX() + xtir, ytir, pos.getZ() + ztir));
      if (this.mainLim < this.YTmain.size()) {
         Collections.shuffle(this.YTtir);
      }

      for (int ax = 0; ax < this.tirLim && ax < this.YTtir.size(); ax++) {
         int YTid = Youtubers.GetYoutuberIDbyName(this.YTtir.get(ax));
         if (Stir instanceof GenBlank) {
            BlockPos loc = Smain.getSpawnLoc(pos);
            this.addYoutuber(world, loc, this.YTtir.get(ax));
         } else {
            BlockPos loc = Stir.getSpawnLoc(new BlockPos(pos.getX() + xtir, ytir, pos.getZ() + ztir));
            this.addYoutuber(world, loc, this.YTtir.get(ax));
         }
      }
   }

   public void addYoutuber(World world, BlockPos pos, String name) {
      StagedWorld.onMain(() -> addYoutuberNow(world, pos, name));
   }

   private void addYoutuberNow(World world, BlockPos pos, String name) {
      int YTid = Youtubers.GetYoutuberIDbyName(name);
      EntityYoutuber entity = new EntityYoutuber(world, YTid);
      entity.setLocationAndAngles(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, MathHelper.wrapDegrees(world.rand.nextFloat() * 360.0F), 0.0F);
      entity.rotationYawHead = entity.rotationYaw;
      entity.renderYawOffset = entity.rotationYaw;
      StagedWorld.spawnEntity(world, entity);
      YTList.YoutuberList[YTid].spawnPets(world, pos);
   }
}
