package com.elllzman.PingChecker;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class PingCheckerPlugin extends JavaPlugin {

    private PingChecker pingChecker;

    public void onEnable()
    {
        try {
            pingChecker = new PingChecker(this);
        } catch(UnknownMinecraftVersionException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("deprecation")
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
        if(cmd.getName().equalsIgnoreCase("ping")) {
            if(pingChecker == null) {
                sender.sendMessage(ChatColor.RED + "There was an when the plugin was started, check the console for more information");
                return true;
            }

            Player player;
            //if there is a player name provided
            if(args.length > 0) {
                if(!sender.hasPermission("ping.command.other")) {
                    sender.sendMessage(ChatColor.RED + "You do not have permission to ping another player");
                    return true;
                }

                player = Bukkit.getPlayer(args[0]);
                if(null == player) {
                    sender.sendMessage(ChatColor.RED + "Player is not online!");
                    return true;
                }
            } else {
                if(!sender.hasPermission("ping.command.self")) {
                    sender.sendMessage(ChatColor.RED + "You do not have permission to ping yourself");
                    return true;
                }

                if(!(sender instanceof Player)) {
                    sender.sendMessage(ChatColor.RED + "Must provide a player name if not ran as a player");
                    return true;
                }
                player = (Player) sender;
            }

            sender.sendMessage(ChatColor.GOLD + "Ping: " + pingChecker.checkPingForPlayer(player));
            return true;
        }
        return false;
    }
}
