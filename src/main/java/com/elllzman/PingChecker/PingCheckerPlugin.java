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
        try {
            pingChecker = new PingChecker(this);
        } catch(UnknownMinecraftVersionException e) {
            e.printStackTrace();
        }
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
        if(cmd.getName().equalsIgnoreCase("ping")) {
            if(!(sender instanceof Player)) {
                sender.sendMessage(ChatColor.RED + "This command can only be called by a player");
                return true;
            }

            if(pingChecker == null) {
                sender.sendMessage(ChatColor.RED + "There was an when the plugin was started, check the console for more information");
                return true;
            }

            sender.sendMessage(ChatColor.GOLD + "Your ping is: " + pingChecker.checkPingForPlayer((Player) sender));

            return true;
        }
        return false;
    }
}
