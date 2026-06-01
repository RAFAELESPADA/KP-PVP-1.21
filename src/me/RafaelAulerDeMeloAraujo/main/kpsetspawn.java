package me.RafaelAulerDeMeloAraujo.main;


import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.RafaelAulerDeMeloAraujo.SpecialAbility.API;



public class kpsetspawn
implements  CommandExecutor{
	

	
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
		if(!(sender instanceof Player)){
			return true;
		}
		final Player p = (Player)sender;
	      if (cmd.getName().equalsIgnoreCase("kpsetspawn")){
	        if (!p.hasPermission("kitpvp.setspawn")){
	          return true;
	        }
	        Main.plugin.getConfig().set("Spawn.World", p.getLocation().getWorld().getName());
	        Main.plugin.getConfig().set("Spawn.X", Double.valueOf(p.getLocation().getX()));
	        Main.plugin.getConfig().set("Spawn.Y", Double.valueOf(p.getLocation().getY()));
	        Main.plugin.getConfig().set("Spawn.Z", Double.valueOf(p.getLocation().getZ()));
	        Main.plugin.getConfig().set("Spawn.Pitch", Float.valueOf(p.getLocation().getPitch()));
	        Main.plugin.getConfig().set("Spawn.Yaw", Float.valueOf(p.getLocation().getYaw()));
	        Main.plugin.saveConfig();
	        Main.plugin.getConfig().set("SpawnD.World", p.getLocation().getWorld().getName());
	        Main.plugin.getConfig().set("SpawnD.X", Double.valueOf(p.getLocation().getX()));
	        Main.plugin.getConfig().set("SpawnD.Y", Double.valueOf(p.getLocation().getY()));
	        Main.plugin.getConfig().set("SpawnD.Z", Double.valueOf(p.getLocation().getZ()));
	        Main.plugin.getConfig().set("SpawnD.Pitch", Float.valueOf(p.getLocation().getPitch()));
	        Main.plugin.getConfig().set("SpawnD.Yaw", Float.valueOf(p.getLocation().getYaw()));
	        Main.plugin.saveConfig();
	     
	        p.sendMessage(API.NomeServer + Main.messages.getString("SpawnSeted").replace("&", "§"));
	        return true;
	      }
		return false;
	}
	}
