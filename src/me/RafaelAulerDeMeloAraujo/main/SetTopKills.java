package me.RafaelAulerDeMeloAraujo.main;




import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


import me.RafaelAulerDeMeloAraujo.SpecialAbility.API;



public class SetTopKills
implements  CommandExecutor{
	

	
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
		if(!(sender instanceof Player)){
			return true;
		}
		final Player p = (Player)sender;
	      if (cmd.getName().equalsIgnoreCase("settopkills")){
	        if (!p.hasPermission("kitpvp.settopkills")){
	          return true;
	        }
	        Main.plugin.getConfig().set("TOP.World", p.getLocation().getWorld().getName());
	        Main.plugin.getConfig().set("TOP.X", Double.valueOf(p.getLocation().getX()));
	        Main.plugin.getConfig().set("TOP.Y", Double.valueOf(p.getLocation().getY()));
	        Main.plugin.getConfig().set("TOP.Z", Double.valueOf(p.getLocation().getZ()));
	        Main.plugin.saveConfig();
	        Main.loadTopPlayersHologram();
	        p.sendMessage(API.NomeServer + "§cYou seted the top kills to your current location.");
	        return true;
	      }
		return false;
	}
	}
