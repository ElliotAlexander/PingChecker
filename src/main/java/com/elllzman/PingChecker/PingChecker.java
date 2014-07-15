package com.elllzman.PingChecker;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class PingChecker
{
    public PingChecker(Plugin plugin, ProtocolManager protocolManager)
    {
        protocolManager.addPacketListener(
                new PacketAdapter(plugin, ListenerPriority.NORMAL) {
                    @Override
                    public void onPacketSending(PacketEvent event)
                    {
                        if(event.getPacketType() == PacketType.Play.Server.PLAYER_INFO) {
                            long currentTime = System.currentTimeMillis();
                            //TODO
                        }
                    }
                }
        );
    }

    public PingInfo checkPingForPlayer(Player sender)
    {
        //TODO
        return null;
    }
}
