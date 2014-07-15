package com.elllzman.PingChecker;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.net.InetAddress;
import java.util.Date;

public class PingChecker implements Runnable
{
    private final Player player;

    public PingChecker(Player player)
    {
        this.player = player;
    }

    @Override
    public void run()
    {
        InetAddress address = player.getAddress().getAddress();

        try {
            Date start, stop;
            Long time = null;
            start = new Date();
            if (address.isReachable(1000)) {
                stop = new Date();
                time = (stop.getTime() - start.getTime());
                player.sendMessage(ChatColor.GREEN + "Your ping is " + time.toString() + "ms");
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
            player.sendMessage(ChatColor.RED + "Ping failed! Check console for more details!");
        }
    }
}
