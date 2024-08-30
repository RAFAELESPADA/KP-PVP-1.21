package me.RafaelAulerDeMeloAraujo.main;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.RafaelAulerDeMeloAraujo.SpecialAbility.API;
import me.RafaelAulerDeMeloAraujo.SpecialAbility.Join;

public class KPKICK implements CommandExecutor {


/*     */   public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
/*     */   {
/*  49 */     if (!(sender instanceof Player)) {
/*  50 */       sender.sendMessage("§bYou need to be a player");
/*  51 */       return true;
/*     */     }
/*  53 */     
/*  54 */     if (!sender.hasPermission("kitpvp.kpkickall")) {
/*  55 */       sender.sendMessage("§cYou dont have the permission kitpvp.kpkickall");
/*  57 */       return true;
/*     */     }
for(Player all: Bukkit.getOnlinePlayers()){
	sender.sendMessage("§cYou forced " + Join.game.size() + " players to leave the kitpvp");
	all.chat("/kitpvp leave");
	
	all.sendMessage(API.NomeServer + Main.messages.getString("ForcedToLeave").replace("&", "§"));
}
return true;
}
}
