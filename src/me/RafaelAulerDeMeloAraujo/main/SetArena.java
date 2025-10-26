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
                    p.sendMessage(String.valueOf(API.NomeServer) + "Write: /setarena (1|12)");
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
                if (args[0].equalsIgnoreCase("5")) {
                    p.sendMessage(String.valueOf(API.NomeServer) + "§aYou seted the ARENA 5");
                    Main.plugin.getConfig().set("arena5.x", (Object)p.getLocation().getX());
                    Main.plugin.getConfig().set("arena5.y", (Object)p.getLocation().getY());
                    Main.plugin.getConfig().set("arena5.z", (Object)p.getLocation().getZ());
                    Main.plugin.getConfig().set("arena5.pitch", (Object)p.getLocation().getPitch());
                    Main.plugin.getConfig().set("arena5.yaw", (Object)p.getLocation().getYaw());
                    Main.plugin.getConfig().set("arena5.world", (Object)p.getLocation().getWorld().getName());
                    Main.plugin.saveConfig();
                }
                if (args[0].equalsIgnoreCase("6")) {
                    p.sendMessage(String.valueOf(API.NomeServer) + "§aYou seted the ARENA 6");
                    Main.plugin.getConfig().set("arena6.x", (Object)p.getLocation().getX());
                    Main.plugin.getConfig().set("arena6.y", (Object)p.getLocation().getY());
                    Main.plugin.getConfig().set("arena6.z", (Object)p.getLocation().getZ());
                    Main.plugin.getConfig().set("arena6.pitch", (Object)p.getLocation().getPitch());
                    Main.plugin.getConfig().set("arena6.yaw", (Object)p.getLocation().getYaw());
                    Main.plugin.getConfig().set("arena6.world", (Object)p.getLocation().getWorld().getName());
                    Main.plugin.saveConfig();
                }
                if (args[0].equalsIgnoreCase("7")) {
                    p.sendMessage(String.valueOf(API.NomeServer) + "§aYou seted the ARENA 5");
                    Main.plugin.getConfig().set("arena7.x", (Object)p.getLocation().getX());
                    Main.plugin.getConfig().set("arena7.y", (Object)p.getLocation().getY());
                    Main.plugin.getConfig().set("arena7.z", (Object)p.getLocation().getZ());
                    Main.plugin.getConfig().set("arena7.pitch", (Object)p.getLocation().getPitch());
                    Main.plugin.getConfig().set("arena7.yaw", (Object)p.getLocation().getYaw());
                    Main.plugin.getConfig().set("arena7.world", (Object)p.getLocation().getWorld().getName());
                    Main.plugin.saveConfig();
                }
                if (args[0].equalsIgnoreCase("8")) {
                    p.sendMessage(String.valueOf(API.NomeServer) + "§aYou seted the ARENA 8");
                    Main.plugin.getConfig().set("arena8.x", (Object)p.getLocation().getX());
                    Main.plugin.getConfig().set("arena8.y", (Object)p.getLocation().getY());
                    Main.plugin.getConfig().set("arena8.z", (Object)p.getLocation().getZ());
                    Main.plugin.getConfig().set("arena8.pitch", (Object)p.getLocation().getPitch());
                    Main.plugin.getConfig().set("arena8.yaw", (Object)p.getLocation().getYaw());
                    Main.plugin.getConfig().set("arena8.world", (Object)p.getLocation().getWorld().getName());
                    Main.plugin.saveConfig();
                }
                if (args[0].equalsIgnoreCase("9")) {
                    p.sendMessage(String.valueOf(API.NomeServer) + "§aYou seted the ARENA 9");
                    Main.plugin.getConfig().set("arena9.x", (Object)p.getLocation().getX());
                    Main.plugin.getConfig().set("arena9.y", (Object)p.getLocation().getY());
                    Main.plugin.getConfig().set("arena9.z", (Object)p.getLocation().getZ());
                    Main.plugin.getConfig().set("arena9.pitch", (Object)p.getLocation().getPitch());
                    Main.plugin.getConfig().set("arena9.yaw", (Object)p.getLocation().getYaw());
                    Main.plugin.getConfig().set("arena9.world", (Object)p.getLocation().getWorld().getName());
                    Main.plugin.saveConfig();
                }
                if (args[0].equalsIgnoreCase("10")) {
                    p.sendMessage(String.valueOf(API.NomeServer) + "§aYou seted the ARENA 10");
                    Main.plugin.getConfig().set("arena10.x", (Object)p.getLocation().getX());
                    Main.plugin.getConfig().set("arena10.y", (Object)p.getLocation().getY());
                    Main.plugin.getConfig().set("arena10.z", (Object)p.getLocation().getZ());
                    Main.plugin.getConfig().set("arena10.pitch", (Object)p.getLocation().getPitch());
                    Main.plugin.getConfig().set("arena10.yaw", (Object)p.getLocation().getYaw());
                    Main.plugin.getConfig().set("arena10.world", (Object)p.getLocation().getWorld().getName());
                    Main.plugin.saveConfig();
                }
                if (args[0].equalsIgnoreCase("11")) {
                    p.sendMessage(String.valueOf(API.NomeServer) + "§aYou seted the ARENA 11");
                    Main.plugin.getConfig().set("arena11.x", (Object)p.getLocation().getX());
                    Main.plugin.getConfig().set("arena11.y", (Object)p.getLocation().getY());
                    Main.plugin.getConfig().set("arena11.z", (Object)p.getLocation().getZ());
                    Main.plugin.getConfig().set("arena11.pitch", (Object)p.getLocation().getPitch());
                    Main.plugin.getConfig().set("arena11.yaw", (Object)p.getLocation().getYaw());
                    Main.plugin.getConfig().set("arena11.world", (Object)p.getLocation().getWorld().getName());
                    Main.plugin.saveConfig();
                }
                if (args[0].equalsIgnoreCase("12")) {
                    p.sendMessage(String.valueOf(API.NomeServer) + "§aYou seted the ARENA 12");
                    Main.plugin.getConfig().set("arena12.x", (Object)p.getLocation().getX());
                    Main.plugin.getConfig().set("arena12.y", (Object)p.getLocation().getY());
                    Main.plugin.getConfig().set("arena12.z", (Object)p.getLocation().getZ());
                    Main.plugin.getConfig().set("arena12.pitch", (Object)p.getLocation().getPitch());
                    Main.plugin.getConfig().set("arena12.yaw", (Object)p.getLocation().getYaw());
                    Main.plugin.getConfig().set("arena12.world", (Object)p.getLocation().getWorld().getName());
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
