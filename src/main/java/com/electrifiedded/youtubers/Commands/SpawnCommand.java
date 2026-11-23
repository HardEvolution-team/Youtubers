package com.electrifiedded.youtubers.Commands;

import com.electrifiedded.youtubers.Entity.PlayerEntity;
import java.util.Arrays;
import java.util.List;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

/** /SpawnPlayer &lt;name&gt;: spawns a "random player" NPC with that name (and that player's skin) at the sender. */
public class SpawnCommand extends CommandBase {
   @Override
   public String getName() {
      return "SpawnPlayer";
   }

   @Override
   public String getUsage(ICommandSender sender) {
      return "SpawnPlayer <name>";
   }

   @Override
   public List<String> getAliases() {
      return Arrays.asList("PlayerSpawn", "PSpawn");
   }

   @Override
   public int getRequiredPermissionLevel() {
      return 0;
   }

   @Override
   public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
      return true;
   }

   static void spawnAt(World world, EntityLiving e, Vec3d p) {
      e.setLocationAndAngles(p.x, p.y, p.z, MathHelper.wrapDegrees(world.rand.nextFloat() * 360.0F), 0.0F);
      e.rotationYawHead = e.rotationYaw;
      e.renderYawOffset = e.rotationYaw;
      e.onInitialSpawn(world.getDifficultyForLocation(e.getPosition()), (IEntityLivingData)null);
      world.spawnEntity(e);
      e.playLivingSound();
   }

   @Override
   public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws WrongUsageException {
      if (args.length != 1) throw new WrongUsageException(this.getUsage(sender));
      World world = sender.getEntityWorld();
      spawnAt(world, new PlayerEntity(world, args[0]), sender.getPositionVector());
   }
}
