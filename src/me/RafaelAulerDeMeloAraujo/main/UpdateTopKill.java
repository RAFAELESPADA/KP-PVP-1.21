package me.RafaelAulerDeMeloAraujo.main;



import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

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
