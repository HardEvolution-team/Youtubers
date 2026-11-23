package com.electrifiedded.youtubers.Items;

import net.minecraft.item.ItemSword;

/** Big swords (CrazyCraft, Epic Proportions, Atlantian, Budder); icon "youtubers:&lt;name&gt;". */
public class ItemGiantSword extends ItemSword {
   public final String n;

   public ItemGiantSword(String name, ToolMaterial material) {
      super(material);
      this.setTranslationKey(name);
      this.n = name;
   }
}
