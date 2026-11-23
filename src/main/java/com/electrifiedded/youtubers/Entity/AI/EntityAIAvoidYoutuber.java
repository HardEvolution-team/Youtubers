package com.electrifiedded.youtubers.Entity.AI;

import com.electrifiedded.youtubers.Entity.EntityYoutuber;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;

/** Runs away from a given youtuber (optionally only one skin subtype, -1 = any). */
public class EntityAIAvoidYoutuber extends EntityAIAvoidEntityYT {
   private final String type;
   private final int sub;

   public EntityAIAvoidYoutuber(EntityCreature entity, String name, int sub, float distance, double farSpeed, double nearSpeed) {
      super(entity, EntityYoutuber.class, distance, farSpeed, nearSpeed);
      this.type = name;
      this.sub = sub;
   }

   @Override
   protected boolean matches(Entity e) {
      // 1.7 compared the names with == (only ever true for interned literals); equals is what was meant
      return e instanceof EntityYoutuber && this.type.equals(((EntityYoutuber)e).getName())
         && (this.sub == -1 || this.sub == ((EntityYoutuber)e).getSubtype());
   }
}
