package com.electrifiedded.youtubers.WorldGen;

import com.electrifiedded.archaiclib.world.StagedWorld;
import net.minecraft.util.math.BlockPos;
import com.electrifiedded.youtubers.RandUtil.RandomString;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.WeightedRandom;
import net.minecraft.util.WeightedRandom.Item;
import net.minecraft.world.World;

public class GenWouldYouRather extends GenYoutuberStructure {
   public static List<Item> things = new ArrayList<>();

   public static void addthings(String[] names) {
      for (int a = 0; a < names.length; a++) {
         things.add(new RandomString(names[a], 1));
      }
   }

   @Override
   public boolean generate(World world, Random random, BlockPos pos) {
      int i = pos.getX();
      int j = pos.getY();
      int k = pos.getZ();
      this.genBox(world, Blocks.AIR, 0, 0, i - 12, j, k - 7, i + 12, j + 10, k + 11);
      this.genBox(world, Blocks.PLANKS, 0, 0, i - 12, j - 1, k - 7, i + 12, j - 1, k + 11);
      this.genBox(world, Blocks.OAK_FENCE, 0, 1, i - 12, j, k - 7, i + 12, j, k + 11);
      this.genPltfm(world, pos.add(9, -1, 0));
      this.genPltfm(world, pos.add(3, -1, 0));
      this.genPltfm(world, pos.add(-3, -1, 0));
      this.genPltfm(world, pos.add(-9, -1, 0));
      this.genFront(world, random, pos.add(0, -1, 7));
      return false;
   }

   private void genFront(World world, Random random, BlockPos pos) {
      int i = pos.getX();
      int j = pos.getY();
      int k = pos.getZ();
      this.genBox(world, Blocks.PLANKS, 0, 0, i - 3, j + 1, k + 2, i + 3, j + 3, k + 2);
      this.genBox(world, Blocks.PLANKS, 0, 0, i - 5, j + 1, k + 1, i + 5, j + 2, k + 1);
      this.genBox(world, Blocks.PLANKS, 0, 0, i - 7, j + 1, k, i + 7, j + 1, k);
      this.genBox(world, Blocks.AIR, 0, 0, i - 3, j + 1, k + 1, i + 3, j + 2, k + 1);
      this.genBox(world, Blocks.AIR, 0, 0, i - 5, j + 1, k, i + 5, j + 1, k);
      this.setBlockState(world, pos.add(4, 3, 1), Blocks.WOODEN_SLAB, 0);
      this.setBlockState(world, pos.add(6, 2, 0), Blocks.WOODEN_SLAB, 0);
      this.setBlockState(world, pos.add(8, 1, -1), Blocks.WOODEN_SLAB, 0);
      this.setBlockState(world, pos.add(-4, 3, 1), Blocks.WOODEN_SLAB, 0);
      this.setBlockState(world, pos.add(-6, 2, 0), Blocks.WOODEN_SLAB, 0);
      this.setBlockState(world, pos.add(-8, 1, -1), Blocks.WOODEN_SLAB, 0);
      this.setBlockState(world, pos.add(1, 2, 2), Blocks.REDSTONE_LAMP, 0);
      this.setBlockState(world, pos.add(-1, 2, 2), Blocks.REDSTONE_LAMP, 0);
      this.setBlockState(world, pos.add(1, 2, 1), Blocks.LEVER, 4);
      this.setBlockState(world, pos.add(-1, 2, 1), Blocks.LEVER, 4);
      this.setBlockState(world, pos.add(1, 1, 2), Blocks.WOOL, 14);
      this.setBlockState(world, pos.add(-1, 1, 2), Blocks.WOOL, 5);
      this.setBlockState(world, pos.add(2, 1, 2), Blocks.WOOL, 14);
      this.setBlockState(world, pos.add(-2, 1, 2), Blocks.WOOL, 5);
      this.setBlockState(world, pos.add(2, 2, 2), Blocks.WOOL, 14);
      this.setBlockState(world, pos.add(-2, 2, 2), Blocks.WOOL, 5);
      this.setBlockState(world, pos.add(-1, 0, -1), Blocks.QUARTZ_BLOCK, 1);
      this.setBlockState(world, pos.add(-1, 0, 0), Blocks.QUARTZ_BLOCK, 3);
      this.setBlockState(world, pos.add(-1, 0, 1), Blocks.QUARTZ_BLOCK, 1);
      this.setBlockState(world, pos.add(0, 0, -1), Blocks.QUARTZ_BLOCK, 4);
      this.setBlockState(world, pos.add(0, 0, 0), Blocks.QUARTZ_BLOCK, 2);
      this.setBlockState(world, pos.add(0, 0, 1), Blocks.QUARTZ_BLOCK, 4);
      this.setBlockState(world, pos.add(1, 0, -1), Blocks.QUARTZ_BLOCK, 1);
      this.setBlockState(world, pos.add(1, 0, 0), Blocks.QUARTZ_BLOCK, 3);
      this.setBlockState(world, pos.add(1, 0, 1), Blocks.QUARTZ_BLOCK, 1);
      this.addChest(world, random, i + 2, j, k);
      this.addChest(world, random, i + 2, j, k + 1);
      this.addChest(world, random, i - 2, j, k);
      this.addChest(world, random, i - 2, j, k + 1);
   }

   private void addChest(World world, Random random, int i, int j, int k) {
      this.setBlockState(world, new BlockPos(i, j, k), Blocks.CHEST, 0);
      TileEntityChest chestTE = (TileEntityChest)StagedWorld.getTileEntity(world, i, j, k);
      if (chestTE != null) {
         for (int a = 0; a < 16; a++) {
            chestTE.setInventorySlotContents(a, this.GetBook(random));
         }
      }
   }

   private ItemStack GetBook(Random random) {
      ItemStack item = new ItemStack(Items.WRITTEN_BOOK);
      if (item.getTagCompound() == null) {
         item.setTagCompound(new NBTTagCompound());
      }

      NBTTagCompound nbt = item.getTagCompound();
      nbt.setString("title", "Would You Rather");
      switch (random.nextInt(4)) {
         case 0:
            nbt.setString("author", "SkyTheKidRS");
            break;
         case 1:
            nbt.setString("author", "ThatGuyBarney");
            break;
         case 2:
            nbt.setString("author", "House_Owner");
            break;
         case 3:
            nbt.setString("author", "JinBop");
      }

      NBTTagList list = new NBTTagList();
      list.appendTag(new NBTTagString(this.GetString(random)));
      nbt.setTag("pages", list);
      item.setTagCompound(nbt);
      return item;
   }

   private String GetString(Random random) {
      RandomString RText = (RandomString)WeightedRandom.getRandomItem(random, things);
      return RText.text == null ? "#NULL" : RText.text;
   }

   public void genPltfm(World world, BlockPos pos) {
      int i = pos.getX();
      int j = pos.getY();
      int k = pos.getZ();
      this.genBox(world, Blocks.PLANKS, 0, 0, i - 2, j, k - 6, i + 2, j, k + 4);
      this.genBox(world, Blocks.QUARTZ_BLOCK, 0, 1, i - 1, j + 1, k - 4, i + 1, j + 1, k - 2);
      this.genBox(world, Blocks.GLASS_PANE, 0, 1, i - 1, j + 2, k - 4, i + 1, j + 4, k - 2);
      this.genBox(world, Blocks.STONE_SLAB, 0, 0, i - 1, j + 5, k - 4, i + 1, j + 5, k - 2);
      this.setBlockState(world, pos.add(1, 1, -1), Blocks.STONEBRICK, 3);
      this.setBlockState(world, pos.add(1, 1, 2), Blocks.REDSTONE_LAMP, 0);
      this.setBlockState(world, pos.add(0, 1, 2), Blocks.PLANKS, 0);
      this.setBlockState(world, pos.add(-1, 1, 2), Blocks.REDSTONE_LAMP, 0);
      this.setBlockState(world, pos.add(1, 0, 1), Blocks.WOOL, 14);
      this.setBlockState(world, pos.add(-1, 0, 1), Blocks.WOOL, 5);
      this.setBlockState(world, pos.add(1, 0, 3), Blocks.WOOL, 14);
      this.setBlockState(world, pos.add(-1, 0, 3), Blocks.WOOL, 5);
      this.setBlockState(world, pos.add(1, 1, 1), Blocks.LEVER, 4);
      this.setBlockState(world, pos.add(-1, 1, 1), Blocks.LEVER, 4);
      this.setBlockState(world, pos.add(1, 2, -1), Blocks.STONE_BUTTON, 5);
      this.setBlockState(world, pos.add(1, 0, -1), Blocks.STICKY_PISTON, 0);
      this.setBlockState(world, pos.add(1, -1, -1), Blocks.REDSTONE_BLOCK, 0);
      this.setBlockToAir(world, pos.add(1, -2, -1));
      this.setBlockState(world, pos.add(1, -3, -2), Blocks.STONEBRICK, 0);
      this.setBlockState(world, pos.add(2, -3, -2), Blocks.STONEBRICK, 0);
      this.setBlockState(world, pos.add(2, -3, -3), Blocks.STONEBRICK, 0);
      this.setBlockState(world, pos.add(1, -3, -3), Blocks.STONEBRICK, 0);
      this.setBlockState(world, pos.add(2, -3, -4), Blocks.STONEBRICK, 0);
      this.setBlockState(world, pos.add(1, -3, -6), Blocks.STONEBRICK, 0);
      this.setBlockState(world, pos.add(1, -2, -2), Blocks.REDSTONE_WIRE, 0);
      this.setBlockState(world, pos.add(2, -2, -2), Blocks.REDSTONE_WIRE, 0);
      this.setBlockState(world, pos.add(2, -2, -3), Blocks.REDSTONE_WIRE, 0);
      this.setBlockState(world, pos.add(0, -2, -2), Blocks.OBSIDIAN, 0);
      this.setBlockState(world, pos.add(0, -3, -3), Blocks.PISTON, 1);
      this.setBlockState(world, pos.add(1, -2, -3), Blocks.UNPOWERED_REPEATER, 3);
      this.setBlockState(world, pos.add(2, -2, -4), Blocks.UNPOWERED_REPEATER, 12);
      this.setBlockState(world, pos.add(2, -2, -5), Blocks.STONEBRICK, 0);
      this.setBlockState(world, pos.add(2, -2, -6), Blocks.REDSTONE_TORCH, 4);
      this.setBlockState(world, pos.add(1, -2, -6), Blocks.POWERED_REPEATER, 3);
      this.setBlockState(world, pos.add(0, -2, -6), Blocks.PISTON, 3);
      this.setBlockState(world, pos.add(1, -2, -5), Blocks.LEVER, 2);
      this.setBlockToAir(world, pos.add(1, -2, -4));
      this.setBlockToAir(world, pos.add(1, -1, -3));
      this.setBlockToAir(world, pos.add(1, -1, -4));
      this.setBlockToAir(world, pos.add(1, -1, -5));
      this.setBlockToAir(world, pos.add(0, -1, -4));
      this.setBlockState(world, pos.add(0, -2, -3), Blocks.STONEBRICK, 0);
      this.setBlockState(world, pos.add(0, -2, -4), Blocks.STONEBRICK, 0);
      this.setBlockState(world, pos.add(0, -2, -5), Blocks.STONEBRICK, 0);
      this.setBlockState(world, pos.add(0, -2, -3), Blocks.GOLD_BLOCK, 0);
      this.setBlockState(world, pos.add(0, -2, -4), Blocks.GOLD_BLOCK, 0);
      this.setBlockState(world, pos.add(0, -2, -5), Blocks.GOLD_BLOCK, 0);
      this.setBlockState(world, pos.add(0, -1, -3), Blocks.GOLD_BLOCK, 0);
      this.setBlockState(world, pos.add(0, 0, -3), Blocks.GOLD_BLOCK, 0);
      this.setBlockState(world, pos.add(0, 1, -3), Blocks.GOLD_BLOCK, 0);
      this.setBlockState(world, pos.add(0, -1, -5), Blocks.SAND, 0);
      this.setBlockState(world, pos.add(0, 0, -5), Blocks.SAND, 0);
      this.setBlockState(world, pos.add(0, 1, -5), Blocks.SAND, 0);
   }

   static {
      addthings(
         new String[]{
            "Poop a Brick(litteraly)",
            "Spend a day attached to ROSS",
            "Slap a deaf kid",
            "end all conversations with 'Cobblestone is life'",
            "trip a blind kid",
            "Eat a Sandwich",
            "lick ThatGuyBarnys Eyepatch",
            "fill your mouth with salt",
            "Quit youtube",
            "listen to nothing but Nickelback",
            "Sing everything you would normally say",
            "Punch youself in the face every night",
            "get shot out a cannon",
            "get slapped with a fish when you least expect it",
            "Go to the other room and punch someone in the same Minecraft world",
            "wet the bed",
            "wear underwear on your head in public",
            "have the Nyan cat song be the soundtrack to your entire life",
            "roll around in stinging nettles",
            "Sleep on LEGOs",
            "Eat a bug",
            "Smell of Skunk",
            "do the cinnamon challenge",
            "Gain 20 pounds of blubber",
            "eat a stick of butter",
            "literally die laughing",
            "Be stalked by an animatronic bear in a top hat and bow tie holding a microphone for the rest of your life",
            "every creepypasta you read happening to you",
            "Swallow a frog whole",
            "be late to every job interview/important meeting",
            "Heil Hitler in a Jewish school",
            "Pour forth a spouting of gas from your rear with auditory intencity so beings within a 20 foot radius may hear it every hour on the hour",
            "compulsivly solve every scrambled rubix cube you see",
            "have debilitating OCD!!!!!1!",
            "write everything in texting shorthand",
            "kiss a random homeless guy",
            "live in a literal pigsty",
            "have everyone around you use the word 'literaly' the like, literaly the wrong way",
            "have every other scentance you say be 'so like, no way' or 'Oh my Gawd' or the like",
            "poop twice as often than is normal",
            "Speak in a language that noone will be able to fully understand",
            "know everything but be unable to communicate any of it",
            "play Kaizo mario untill beaten",
            "become a brain intolerant zombie",
            "have every chair you sit in have a wobble",
            "Bite a rock every birthday",
            "drool 24/7",
            "relive the most boring day forever",
            "never be able to sleep when you want to",
            "Talk like cookie monster every 5 miniutes after eating a cookie",
            "count to one million",
            "smell weak old socks",
            "Beltch every time you walk up to someone you find attractive",
            "Speel avorytheong rong (spell everything wrong)",
            "be unable to eat fast food",
            "Eat a 15 lb bag of dogfood over a week",
            "you yodel instead of laugh",
            "Come up with 400 Would you rather ideas",
            "Crack a dosen eggs that you paid for over your head",
            "only be able to have icecream outside in the winter",
            "all glasses of water you drink are warm",
            "every insult you make is heard as a complement",
            "every complement you make is heard as a insult",
            "go prematurely bald"
         }
      );
   }
}
