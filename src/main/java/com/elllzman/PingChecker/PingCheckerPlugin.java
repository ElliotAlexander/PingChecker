package com.elllzman.PingChecker;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class PingCheckerPlugin extends JavaPlugin {

    private PingChecker pingChecker;

    public void onEnable()
    {
        pingChecker = new PingChecker();
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
        if(cmd.getName().equalsIgnoreCase("ping")) {
            if(!(sender instanceof Player)) {
                sender.sendMessage(ChatColor.RED + "This command can only be called by a player");
                return true;
            }

            short ping = pingChecker.checkPingForPlayer((Player) sender);
            sender.sendMessage(ChatColor.GOLD + "Your ping is: " + ping);
            return true;
        }
        return false;
    }
}
