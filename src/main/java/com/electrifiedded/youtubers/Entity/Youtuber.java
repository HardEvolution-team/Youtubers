package com.electrifiedded.youtubers.Entity;

import com.electrifiedded.youtubers.YTList;
import com.electrifiedded.youtubers.Youtubers;
import net.minecraft.util.math.BlockPos;
import java.util.Random;
import java.util.concurrent.Callable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import com.electrifiedded.archaiclib.v1710.api.client.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.world.World;

public class Youtuber {
   public String ID;
   public String Name;
   public String Group;
   public int ColorA;
   public int ColorB;
   public int Eggtype;
   public boolean hasCustomIcon;
   public Item item;
   public IIcon icon;
   public Callable add_Trades;
   public int subTypes;
   public ResourceLocation[] subtextures;

   public Youtuber(String ID, String Group, Item item, String... Names) {
      this.ID = ID;
      this.Group = Group;
      this.Name = Names[0];
      this.subTypes = Names.length;
      this.subtextures = new ResourceLocation[Names.length];
      this.subtextures[0] = new ResourceLocation("youtubers", "textures/entity/Youtuber/" + this.Name + ".png");

      for (int i = 1; i < Names.length; i++) {
         this.subtextures[i] = new ResourceLocation("youtubers", "textures/entity/Youtuber/" + this.Name + Names[i] + ".png");
      }

      this.hasCustomIcon = true;
      this.item = item;
      this.ColorA = Youtubers.RGBtoInt(255, 255, 255);
      this.ColorB = Youtubers.RGBtoInt(255, 255, 255);
      this.Eggtype = 0;
   }

   public Youtuber(String ID, String Group, String... Names) {
      this.ID = ID;
      this.Group = Group;
      this.Name = Names[0];
      this.subTypes = Names.length;
      this.subtextures = new ResourceLocation[Names.length];
      this.subtextures[0] = new ResourceLocation("youtubers", "textures/entity/Youtuber/" + this.Name + ".png");

      for (int i = 1; i < Names.length; i++) {
         this.subtextures[i] = new ResourceLocation("youtubers", "textures/entity/Youtuber/" + this.Name + Names[i] + ".png");
      }

      this.hasCustomIcon = true;
      this.ColorA = Youtubers.RGBtoInt(255, 255, 255);
      this.ColorB = Youtubers.RGBtoInt(255, 255, 255);
      this.Eggtype = 0;
   }

   public Youtuber(String ID, String Group, int ColorA, int ColorB, int Eggtype, String... Names) {
      this.ID = ID;
      this.Group = Group;
      this.Name = Names[0];
      this.subTypes = Names.length;
      this.subtextures = new ResourceLocation[Names.length];
      this.subtextures[0] = new ResourceLocation("youtubers", "textures/entity/Youtuber/" + this.Name + ".png");

      for (int i = 1; i < Names.length; i++) {
         this.subtextures[i] = new ResourceLocation("youtubers", "textures/entity/Youtuber/" + this.Name + Names[i] + ".png");
      }

      this.hasCustomIcon = false;
      this.ColorA = ColorA;
      this.ColorB = ColorB;
      this.Eggtype = Eggtype;
   }

   public void Add_Trades(MerchantRecipeList recipeList, Random random, int Subtype) {
      YTList.Add_Generic_Trades(recipeList, random);
   }

   public void give_Items(EntityYoutuber youtuber) {
   }

   public void Add_AI(EntityYoutuber youtuber) {
   }

   public void dropItems(EntityYoutuber entityYoutuber, int par1) {
   }

   public boolean Interact(EntityYoutuber youtuber, EntityPlayer player, ItemStack item) {
      return false;
   }

   public void registerModel(Item yourItem, int metadata) {
   }

   public void spawnPets(World world, BlockPos pos) {
   }
}
