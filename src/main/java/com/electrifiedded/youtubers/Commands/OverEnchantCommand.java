package com.electrifiedded.youtubers.Commands;

import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.NumberInvalidException;
import net.minecraft.command.WrongUsageException;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;

/**
 * /overEnchant &lt;player&gt; &lt;enchantment id or name&gt; [level -100..100]: like /enchant but without the level cap and
 * on any item; an existing enchantment of that type gets the new level.
 */
public class OverEnchantCommand extends CommandBase {
   @Override
   public String getName() {
      return "overEnchant";
   }

   @Override
   public int getRequiredPermissionLevel() {
      return 2;
   }

   @Override
   public String getUsage(ICommandSender sender) {
      return "/overEnchant <player> <enchantment ID> [level]";
   }

   @Override
   public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
      if (args.length < 2) throw new WrongUsageException(this.getUsage(sender));
      EntityPlayerMP player = getPlayer(server, sender, args[0]);
      Enchantment enchantment;
      try {
         enchantment = Enchantment.getEnchantmentByID(parseInt(args[1], 0));
      } catch (NumberInvalidException e) {
         enchantment = Enchantment.getEnchantmentByLocation(args[1]);
      }
      if (enchantment == null) throw new NumberInvalidException("commands.enchant.notFound", args[1]);
      int level = args.length >= 3 ? parseInt(args[2], -100, 100) : 1;
      ItemStack stack = player.getHeldItemMainhand();
      if (stack.isEmpty()) throw new CommandException("commands.enchant.noItem");
      NBTTagList list = stack.getEnchantmentTagList();
      for (int k = 0; k < list.tagCount(); k++) {
         if (Enchantment.getEnchantmentByID(list.getCompoundTagAt(k).getShort("id")) == enchantment) {
            list.getCompoundTagAt(k).setShort("lvl", (short)level);
            return;
         }
      }
      stack.addEnchantment(enchantment, level);
   }

   @Override
   public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos pos) {
      if (args.length == 1) return getListOfStringsMatchingLastWord(args, server.getOnlinePlayerNames());
      if (args.length == 2) return getListOfStringsMatchingLastWord(args, Enchantment.REGISTRY.getKeys());
      return Collections.emptyList();
   }

   @Override
   public boolean isUsernameIndex(String[] args, int index) {
      return index == 0;
   }
}
