package me.RafaelAulerDeMeloAraujo.main;


import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;



public class RTP {


public static void TeleportArenaRandom(final Player p) {
    final Random dice = new Random();
    final int number = dice.nextInt(12);
    switch (number) {
        case 0: {
        	if (Main.plugin.getConfig().getString("arena1.world") == null) {
        		return;
        	}
        	final World w = Bukkit.getServer().getWorld(Main.plugin.getConfig().getString("arena1.world"));
            final double x = Main.plugin.getConfig().getDouble("arena1.x");
            final double y = Main.plugin.getConfig().getDouble("arena1.y");
            final double z = Main.plugin.getConfig().getDouble("arena1.z");
            final Location lobby = new Location(w, x, y, z);
            lobby.setPitch((float)Main.plugin.getConfig().getDouble("arena1.pitch"));
            lobby.setYaw((float)Main.plugin.getConfig().getDouble("arena1.yaw"));
            p.teleport(lobby);
            break;
        }
        case 1: {
        	if (Main.plugin.getConfig().getString("arena2.world") == null) {
        		return;
        	}
        	final World w = Bukkit.getServer().getWorld(Main.plugin.getConfig().getString("arena2.world"));
            final double x = Main.plugin.getConfig().getDouble("arena2.x");
            final double y = Main.plugin.getConfig().getDouble("arena2.y");
            final double z = Main.plugin.getConfig().getDouble("arena2.z");
            final Location lobby2 = new Location(w, x, y, z);
            lobby2.setPitch((float)Main.plugin.getConfig().getDouble("arena2.pitch"));
            lobby2.setYaw((float)Main.plugin.getConfig().getDouble("arena2.yaw"));
            p.teleport(lobby2);
            break;
        }
        case 2: {
        	if (Main.plugin.getConfig().getString("arena3.world") == null) {
        		return;
        	}
        	final World w = Bukkit.getServer().getWorld(Main.plugin.getConfig().getString("arena3.world"));
            final double x = Main.plugin.getConfig().getDouble("arena3.x");
            final double y = Main.plugin.getConfig().getDouble("arena3.y");
            final double z = Main.plugin.getConfig().getDouble("arena3.z");
            final Location lobby3 = new Location(w, x, y, z);
            lobby3.setPitch((float)Main.plugin.getConfig().getDouble("arena3.pitch"));
            lobby3.setYaw((float)Main.plugin.getConfig().getDouble("arena3.yaw"));
            p.teleport(lobby3);
            break;
        }
        case 3: {
        	if (Main.plugin.getConfig().getString("arena4.world") == null) {
        		return;
        	}
        	final World w = Bukkit.getServer().getWorld(Main.plugin.getConfig().getString("arena4.world"));
            final double x = Main.plugin.getConfig().getDouble("arena4.x");
            final double y = Main.plugin.getConfig().getDouble("arena4.y");
            final double z = Main.plugin.getConfig().getDouble("arena4.z");
            final Location lobby4 = new Location(w, x, y, z);
            lobby4.setPitch((float)Main.plugin.getConfig().getDouble("arena4.pitch"));
            lobby4.setYaw((float)Main.plugin.getConfig().getDouble("arena4.yaw"));
            p.teleport(lobby4);
            break;
        }
        case 4: {
        	if (Main.plugin.getConfig().getString("arena5.world") == null) {
        		return;
        	}
        	final World w = Bukkit.getServer().getWorld(Main.plugin.getConfig().getString("arena5.world"));
            final double x = Main.plugin.getConfig().getDouble("arena5.x");
            final double y = Main.plugin.getConfig().getDouble("arena5.y");
            final double z = Main.plugin.getConfig().getDouble("arena5.z");
            final Location lobby4 = new Location(w, x, y, z);
            lobby4.setPitch((float)Main.plugin.getConfig().getDouble("arena5.pitch"));
            lobby4.setYaw((float)Main.plugin.getConfig().getDouble("arena5.yaw"));
            p.teleport(lobby4);
            break;
        }
        case 5: {
        	if (Main.plugin.getConfig().getString("arena6.world") == null) {
        		return;
        	}
        	final World w = Bukkit.getServer().getWorld(Main.plugin.getConfig().getString("arena6.world"));
            final double x = Main.plugin.getConfig().getDouble("arena6.x");
            final double y = Main.plugin.getConfig().getDouble("arena6.y");
            final double z = Main.plugin.getConfig().getDouble("arena6.z");
            final Location lobby4 = new Location(w, x, y, z);
            lobby4.setPitch((float)Main.plugin.getConfig().getDouble("arena6.pitch"));
            lobby4.setYaw((float)Main.plugin.getConfig().getDouble("arena6.yaw"));
            p.teleport(lobby4);
            break;
        }
        case 6: {
        	if (Main.plugin.getConfig().getString("arena7.world") == null) {
        		return;
        	}
        	final World w = Bukkit.getServer().getWorld(Main.plugin.getConfig().getString("arena7.world"));
            final double x = Main.plugin.getConfig().getDouble("arena7.x");
            final double y = Main.plugin.getConfig().getDouble("arena7.y");
            final double z = Main.plugin.getConfig().getDouble("arena7.z");
            final Location lobby4 = new Location(w, x, y, z);
            lobby4.setPitch((float)Main.plugin.getConfig().getDouble("arena7.pitch"));
            lobby4.setYaw((float)Main.plugin.getConfig().getDouble("arena7.yaw"));
            p.teleport(lobby4);
            break;
        }
        case 7: {
        	if (Main.plugin.getConfig().getString("arena8.world") == null) {
        		return;
        	}
        	final World w = Bukkit.getServer().getWorld(Main.plugin.getConfig().getString("arena8.world"));
            final double x = Main.plugin.getConfig().getDouble("arena8.x");
            final double y = Main.plugin.getConfig().getDouble("arena8.y");
            final double z = Main.plugin.getConfig().getDouble("arena8.z");
            final Location lobby4 = new Location(w, x, y, z);
            lobby4.setPitch((float)Main.plugin.getConfig().getDouble("arena8.pitch"));
            lobby4.setYaw((float)Main.plugin.getConfig().getDouble("arena8.yaw"));
            p.teleport(lobby4);
            break;
        }
        case 8: {
        	if (Main.plugin.getConfig().getString("arena9.world") == null) {
        		return;
        	}
        	final World w = Bukkit.getServer().getWorld(Main.plugin.getConfig().getString("arena9.world"));
            final double x = Main.plugin.getConfig().getDouble("arena9.x");
            final double y = Main.plugin.getConfig().getDouble("arena9.y");
            final double z = Main.plugin.getConfig().getDouble("arena9.z");
            final Location lobby4 = new Location(w, x, y, z);
            lobby4.setPitch((float)Main.plugin.getConfig().getDouble("arena9.pitch"));
            lobby4.setYaw((float)Main.plugin.getConfig().getDouble("arena9.yaw"));
            p.teleport(lobby4);
            break;
        }
        case 9: {
        	if (Main.plugin.getConfig().getString("arena10.world") == null) {
        		return;
        	}
        	final World w = Bukkit.getServer().getWorld(Main.plugin.getConfig().getString("arena10.world"));
            final double x = Main.plugin.getConfig().getDouble("arena10.x");
            final double y = Main.plugin.getConfig().getDouble("arena10.y");
            final double z = Main.plugin.getConfig().getDouble("arena10.z");
            final Location lobby4 = new Location(w, x, y, z);
            lobby4.setPitch((float)Main.plugin.getConfig().getDouble("arena10.pitch"));
            lobby4.setYaw((float)Main.plugin.getConfig().getDouble("arena10.yaw"));
            p.teleport(lobby4);
            break;
        }
        case 10: {
        	if (Main.plugin.getConfig().getString("arena11.world") == null) {
        		return;
        	}
        	final World w = Bukkit.getServer().getWorld(Main.plugin.getConfig().getString("arena11.world"));
            final double x = Main.plugin.getConfig().getDouble("arena11.x");
            final double y = Main.plugin.getConfig().getDouble("arena11.y");
            final double z = Main.plugin.getConfig().getDouble("arena11.z");
            final Location lobby4 = new Location(w, x, y, z);
            lobby4.setPitch((float)Main.plugin.getConfig().getDouble("arena11.pitch"));
            lobby4.setYaw((float)Main.plugin.getConfig().getDouble("arena11.yaw"));
            p.teleport(lobby4);
            break;
        }
        case 11: {
        	if (Main.plugin.getConfig().getString("arena12.world") == null) {
        		return;
        	}
        	final World w = Bukkit.getServer().getWorld(Main.plugin.getConfig().getString("arena12.world"));
            final double x = Main.plugin.getConfig().getDouble("arena12.x");
            final double y = Main.plugin.getConfig().getDouble("arena12.y");
            final double z = Main.plugin.getConfig().getDouble("arena12.z");
            final Location lobby4 = new Location(w, x, y, z);
            lobby4.setPitch((float)Main.plugin.getConfig().getDouble("arena12.pitch"));
            lobby4.setYaw((float)Main.plugin.getConfig().getDouble("arena12.yaw"));
            p.teleport(lobby4);
            break;
        }
    }
}
}
