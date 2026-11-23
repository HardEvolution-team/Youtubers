package com.electrifiedded.youtubers.Entity;

import java.lang.reflect.InvocationTargetException;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;

public class SpawnEntityInfo {
   public String Name;
   public Class EntityClass;
   public int ColorA;
   public int ColorB;
   public int Eggtype;

   public SpawnEntityInfo(Class EntityClass, String Name, int ColorA, int ColorB, int Eggtype) {
      this.EntityClass = EntityClass;
      this.Name = Name;
      this.ColorA = ColorA;
      this.ColorB = ColorB;
      this.Eggtype = Eggtype;
   }

   public Entity Spawn(World world) {
      try {
         return (Entity)this.EntityClass.getConstructor(World.class).newInstance(world);
      } catch (InstantiationException var3) {
         var3.printStackTrace();
      } catch (IllegalAccessException var4) {
         var4.printStackTrace();
      } catch (IllegalArgumentException var5) {
         var5.printStackTrace();
      } catch (InvocationTargetException var6) {
         var6.printStackTrace();
      } catch (NoSuchMethodException var7) {
         var7.printStackTrace();
      } catch (SecurityException var8) {
         var8.printStackTrace();
      }

      return (Entity)null;
   }
}
