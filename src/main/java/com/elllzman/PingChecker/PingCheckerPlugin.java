package com.elllzman.PingChecker;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class PingCheckerPlugin extends JavaPlugin {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
        if(cmd.getName().equalsIgnoreCase("ping")) {

            final Player p = (Player) sender;

            getServer().getScheduler().runTaskAsynchronously(this, new PingChecker(p));
            return true;
        }
        return false;
    }
}
