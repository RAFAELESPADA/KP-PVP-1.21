package me.RafaelAulerDeMeloAraujo.main;




import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.RafaelAulerDeMeloAraujo.SpecialAbility.API;



public class UpdateTopKill
implements  CommandExecutor{
	

	
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
		if(!(sender instanceof Player)){
			return true;
		}
		final Player p = (Player)sender;
	      if (cmd.getName().equalsIgnoreCase("updatetopkill")){
	        if (!p.hasPermission("kitpvp.settopkills")){
	          return true;
	        }
	        Main.loadTopPlayersHologram();
	        p.sendMessage(API.NomeServer + "§cTop Reloaded.");
	        return true;
	      }
		return false;
	}
	}
