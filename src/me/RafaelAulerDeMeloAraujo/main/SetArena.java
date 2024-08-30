package me.RafaelAulerDeMeloAraujo.main;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.RafaelAulerDeMeloAraujo.SpecialAbility.API;

public class SetArena implements CommandExecutor
{
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("No console");
            return true;
        }
        final Player p = (Player)sender;
        if (label.equalsIgnoreCase("setarena")) {
        	if (!p.hasPermission("kitpvp.setarena")){
        	  	  sender.sendMessage("§c§lERROR: §fYou dont have permission to do that");
        	  	  return true;
        	    }
                if (args.length == 0) {
                    p.sendMessage(String.valueOf(API.NomeServer) + "Write: /setarena (1|4)");
                    return true;
                }
                if (args[0].equalsIgnoreCase("1")) {
                    p.sendMessage(String.valueOf(API.NomeServer) + "§aYou seted the ARENA 1");
                    Main.plugin.getConfig().set("arena1.x", (Object)p.getLocation().getX());
                    Main.plugin.getConfig().set("arena1.y", (Object)p.getLocation().getY());
                    Main.plugin.getConfig().set("arena1.z", (Object)p.getLocation().getZ());
                    Main.plugin.getConfig().set("arena1.pitch", (Object)p.getLocation().getPitch());
                    Main.plugin.getConfig().set("arena1.yaw", (Object)p.getLocation().getYaw());
                    Main.plugin.getConfig().set("arena1.world", (Object)p.getLocation().getWorld().getName());
                    Main.plugin.saveConfig();
                }
                if (args[0].equalsIgnoreCase("2")) {
                    p.sendMessage(String.valueOf(API.NomeServer) + "§aYou seted the ARENA 2");
                    Main.plugin.getConfig().set("arena2.x", (Object)p.getLocation().getX());
                    Main.plugin.getConfig().set("arena2.y", (Object)p.getLocation().getY());
                    Main.plugin.getConfig().set("arena2.z", (Object)p.getLocation().getZ());
                    Main.plugin.getConfig().set("arena2.pitch", (Object)p.getLocation().getPitch());
                    Main.plugin.getConfig().set("arena2.yaw", (Object)p.getLocation().getYaw());
                    Main.plugin.getConfig().set("arena2.world", (Object)p.getLocation().getWorld().getName());
                    Main.plugin.saveConfig();
                }
                if (args[0].equalsIgnoreCase("3")) {
                    p.sendMessage(String.valueOf(API.NomeServer) + "§aYou seted the ARENA 3");
                    Main.plugin.getConfig().set("arena3.x", (Object)p.getLocation().getX());
                    Main.plugin.getConfig().set("arena3.y", (Object)p.getLocation().getY());
                    Main.plugin.getConfig().set("arena3.z", (Object)p.getLocation().getZ());
                    Main.plugin.getConfig().set("arena3.pitch", (Object)p.getLocation().getPitch());
                    Main.plugin.getConfig().set("arena3.yaw", (Object)p.getLocation().getYaw());
                    Main.plugin.getConfig().set("arena3.world", (Object)p.getLocation().getWorld().getName());
                    Main.plugin.saveConfig();
                }
                if (args[0].equalsIgnoreCase("4")) {
                    p.sendMessage(String.valueOf(API.NomeServer) + "§aYou seted the ARENA 4");
                    Main.plugin.getConfig().set("arena4.x", (Object)p.getLocation().getX());
                    Main.plugin.getConfig().set("arena4.y", (Object)p.getLocation().getY());
                    Main.plugin.getConfig().set("arena4.z", (Object)p.getLocation().getZ());
                    Main.plugin.getConfig().set("arena4.pitch", (Object)p.getLocation().getPitch());
                    Main.plugin.getConfig().set("arena4.yaw", (Object)p.getLocation().getYaw());
                    Main.plugin.getConfig().set("arena4.world", (Object)p.getLocation().getWorld().getName());
                    Main.plugin.saveConfig();
                }
            }
            else {
                p.sendMessage("§c[ERROR] You dont have permission to use that command.");
            }
        {
        return false;
    }
}
}
