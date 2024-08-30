package me.RafaelAulerDeMeloAraujo.main;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.RafaelAulerDeMeloAraujo.SpecialAbility.API;
import me.RafaelAulerDeMeloAraujo.SpecialAbility.Cooldown;
import me.RafaelAulerDeMeloAraujo.SpecialAbility.Deshfire;
import me.RafaelAulerDeMeloAraujo.SpecialAbility.Habilidade;
import me.RafaelAulerDeMeloAraujo.SpecialAbility.Join;
import me.RafaelAulerDeMeloAraujo.Warps.SettingsManager;
import me.RafaelAulerDeMeloAraujo.X1.Sumo;

public class Warp implements CommandExecutor {
	SettingsManager settings = SettingsManager.getInstance();
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if (cmd.getName().equalsIgnoreCase("kpsetwarp")) {
			if (!sender.hasPermission("kitpvp.setwarp")) {
                sender.sendMessage(ChatColor.RED + "You do not have permission!");
                return true;
        }
            if (args.length == 0) {
                    sender.sendMessage(ChatColor.RED + "Use /kpsetwarp <FPS , Challenge, Sumo");
                    return true;
            }
            Player p = (Player)sender;
            settings.getData().set("warps." + args[0] + ".world", p.getLocation().getWorld().getName());
            settings.getData().set("warps." + args[0] + ".x", p.getLocation().getX());
            settings.getData().set("warps." + args[0] + ".y", p.getLocation().getY());
            settings.getData().set("warps." + args[0] + ".z", p.getLocation().getZ());
            settings.saveData();
            p.sendMessage(ChatColor.GREEN + "Seted warp " + args[0] + " with sucess!");
    }
   
    if (cmd.getName().equalsIgnoreCase("kpwarp")) {
            if (args.length == 0) {
                    sender.sendMessage(ChatColor.RED + "/kpwarp <FPS , Challenge>");
                    return true;
            }
            if (!Join.game.contains(sender.getName())) {
                sender.sendMessage(ChatColor.RED + "[KITPVP] You are not in game!");
                return true;
        }
            Player p = (Player)sender;
            if (Habilidade.getAbility(p) != "None") {
                sender.sendMessage(ChatColor.RED + "[KITPVP] You cannot go to a warp with a kit selected!");
                return true;
        }
            
            if (settings.getData().getConfigurationSection("warps." + args[0]) == null) {
                    p.sendMessage(ChatColor.YELLOW + "Warp " + args[0] + " is not seted yet!");
                    return true;
            }
            if (args[0].equalsIgnoreCase("FPS")) {
            	 World w = Bukkit.getServer().getWorld(settings.getData().getString("warps." + args[0] + ".world"));
            	double x = settings.getData().getDouble("warps." + args[0] + ".x");
                double y = settings.getData().getDouble("warps." + args[0] + ".y");
                double z = settings.getData().getDouble("warps." + args[0] + ".z");
                p.teleport(new Location(w, x, y, z));
                warp(p);
                API.give(p);
                API.sopa(p);
                p.sendMessage(ChatColor.GREEN + "Teleported to " + args[0] + "!");
            }
            else if (args[0].equalsIgnoreCase("Challenge")) {
           	 World w = Bukkit.getServer().getWorld(settings.getData().getString("warps." + args[0] + ".world"));
           	double x = settings.getData().getDouble("warps." + args[0] + ".x");
               double y = settings.getData().getDouble("warps." + args[0] + ".y");
               double z = settings.getData().getDouble("warps." + args[0] + ".z");
               p.teleport(new Location(w, x, y, z));
               warp(p);
               API.sopa(p);
               ItemStack marrom21 = new ItemStack(Material.COMPASS);
               ItemMeta marrom211 = marrom21.getItemMeta();
               marrom211.setDisplayName("§eCompass");
               List<String> itemlorem11 = new ArrayList();
               itemlorem11.add("§cFind enemies near you!");
               marrom211.setLore(itemlorem11);
               marrom21.setItemMeta(marrom211);
            	  	
               ItemStack sopas = new ItemStack(Material.BOWL, 64);
               ItemMeta ksopas = sopas.getItemMeta();
               ksopas.setDisplayName("§eBowl");
               sopas.setItemMeta(ksopas);
               
               ItemStack cogur = new ItemStack(Material.RED_MUSHROOM, 64);
               ItemMeta kcogur = cogur.getItemMeta();
               kcogur.setDisplayName("§3--> §cRed §3<--");
               cogur.setItemMeta(kcogur);
               ItemStack cogum = new ItemStack(Material.BROWN_MUSHROOM, 64);
               ItemMeta kcogum = cogum.getItemMeta();
               kcogum.setDisplayName("§3--> §8Brown §3<--");
               cogum.setItemMeta(kcogum);
              p.getInventory().setItem(14, cogum);
              p.getInventory().setItem(15, cogur);
              p.getInventory().setItem(13, sopas);
               p.sendMessage(ChatColor.GREEN + "Teleported to " + args[0] + "!");
           }
            else if (args[0].equalsIgnoreCase("Sumo")) {
            	Sumo.entrar1v1(p);
            }
            
    }
		return false;
	}


private void warp(Player player) {
	player.setGameMode(GameMode.ADVENTURE);
	player.getInventory().clear();
	player.getInventory().setArmorContents(null);
	player.setAllowFlight(false);
	Habilidade.setAbility(player, "Warp");
	Cooldown.remove(player);
	  /* 280 */       Deshfire.Armadura.remove(player);
	  /* 281 */       Deshfire.Armadura2.remove(player);
	  /* 282 */       Deshfire.cooldownm.remove(player);
	player.setFlying(false);
	player.getInventory().setHeldItemSlot(0);
	player.getActivePotionEffects().forEach(potion -> player.removePotionEffect(potion.getType()));
}

}

