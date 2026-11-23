package com.electrifiedded.youtubers.Commands;

import com.electrifiedded.youtubers.Entity.EntityYoutuber;
import com.electrifiedded.youtubers.Entity.PlayerEntity;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

/** /ImLonely: 2-8 random player NPCs and one random youtuber appear around you. */
public class LonelyCommand extends CommandBase {
   @Override
   public String getName() {
      return "ImLonely";
   }

   @Override
   public String getUsage(ICommandSender sender) {
      return "ImLonely";
   }

   @Override
   public List<String> getAliases() {
      return Collections.singletonList("Lonely");
   }

   @Override
   public int getRequiredPermissionLevel() {
      return 0;
   }

   @Override
   public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
      return true;
   }

   @Override
   public void execute(MinecraftServer server, ICommandSender sender, String[] args) {
      World world = sender.getEntityWorld();
      Vec3d p = sender.getPositionVector();
      int numb = com.electrifiedded.archaiclib.util.FastRandom.shared().nextInt(7) + 2;
      for (int a = 0; a < numb; a++) SpawnCommand.spawnAt(world, new PlayerEntity(world), p);
      SpawnCommand.spawnAt(world, new EntityYoutuber(world), p);
   }
}
