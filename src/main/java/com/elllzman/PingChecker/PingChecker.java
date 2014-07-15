package com.elllzman.PingChecker;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.net.InetAddress;
import java.util.Date;

public class PingChecker extends JavaPlugin {

    private Plugin plugin;

    public void onEnable()
    {
        plugin = this;

    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
        if(cmd.getName().equalsIgnoreCase("ping")) {

            final Player p = (Player) sender;
            final InetAddress address = p.getAddress().getAddress();


            Bukkit.getScheduler().runTaskAsynchronously(plugin, new Runnable() {
                @Override
                public void run() {
                    try {
                        Date start, stop;
                        Long time = null;
                        start = new Date();
                        if (address.isReachable(1000)) {
                            stop = new Date();
                            time = (stop.getTime() - start.getTime());
                            p.sendMessage(ChatColor.GREEN + "Your ping is " + time.toString() + "ms");
                            return;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        p.sendMessage(ChatColor.RED + "Ping failed! Check console for more details!");
                    }
                }
            });
            return true;
        }
        return false;
    }
}
