package com.elllzman.PingChecker;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.UnknownDependencyException;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class PingChecker
{
    private final Method getEntityPlayerMethod;
    private final Field pingField;

    /**
     * Creates a new ping checker
     *
     * @param plugin the plugin class
     * @throws com.elllzman.PingChecker.UnknownMinecraftVersionException when class/methods cannot be found, invalid MC version?
     */
    public PingChecker(Plugin plugin) throws UnknownMinecraftVersionException
    {
        // Get full package string of CraftServer.
        // org.bukkit.craftbukkit.version
        String packageName = plugin.getServer().getClass().getPackage().getName();
        String version = packageName.substring(packageName.lastIndexOf('.') + 1);

        String craftPlayerClassName = "org.bukkit.craftbukkit." + version + ".entity.CraftPlayer";

        try {
            Class craftPlayerClass = Class.forName(craftPlayerClassName);
            getEntityPlayerMethod = craftPlayerClass.getMethod("getHandle");
            pingField = getEntityPlayerMethod.getReturnType().getField("ping");
        } catch(NoSuchMethodException e) {
            throw new UnknownMinecraftVersionException("Handle method not found");
        } catch(ClassNotFoundException e) {
            throw new UnknownDependencyException("CraftPlayer class not found");
        } catch(NoSuchFieldException e) {
            throw new UnknownDependencyException("Ping field not found");
        }
    }

    public int checkPingForPlayer(Player player)
    {
        try {
            Object entityPlayer = getEntityPlayerMethod.invoke(player);
            return (Integer) pingField.get(entityPlayer);
        } catch(IllegalAccessException e) {
            e.printStackTrace();
        } catch(InvocationTargetException e) {
            e.printStackTrace();
        }
        throw new UnknownDependencyException("Error getting the ping field from the player");
    }
}
