package me.RafaelAulerDeMeloAraujo.main;


import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.RafaelAulerDeMeloAraujo.Coins.Commands;
import me.RafaelAulerDeMeloAraujo.SpecialAbility.Habilidade;
import me.RafaelAulerDeMeloAraujo.SpecialAbility.Join;
import me.RafaelAulerDeMeloAraujo.Warps.SettingsManager;

public class GiveKitUnlocker implements CommandExecutor {




	static SettingsManager settings = SettingsManager.getInstance();

/*    */   public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
/*    */   {
/* 19 */     if (cmd.getName().equalsIgnoreCase("givekitunlocker"))
/*    */     {
/* 22 */       if (args.length != 2) {
/* 23 */         sender.sendMessage(ChatColor.RED+ "Use /givekitunlocker <Nick> <Amount>");
/* 34 */         return true;
/*    */       }       
/* 37 */       if ((args.length > 0) && (args.length == 2)) {
/* 38 */         Player t = org.bukkit.Bukkit.getServer().getPlayer(args[0]);
		if (!Commands.isNumeric(args[1])) {
			sender.sendMessage("Use only numbers and a player name!");
			return true;
		}
		if (!sender.hasPermission("kitpvp.givekitunlocker")) {
			sender.sendMessage("You dont have permission to do that!");
			return true;
		}
		Integer i = Integer.parseInt(args[1]);
		if (t == null) {
			sender.sendMessage("This player is offline!");
			return true;
		}
		if (Join.game.contains(t.getName()) && !Habilidade.ContainsAbility(t)) {
		    final ItemStack sopas = new ItemStack(Material.CHEST);
		    final ItemMeta sopasm = sopas.getItemMeta();
		    sopasm.setDisplayName("§bKit Unlocker");
		    sopas.setItemMeta(sopasm);
		    sopas.setAmount(i);
		    t.getInventory().addItem(sopas);	
		}
	    settings.getData().set("crates." + t.getName() + ".amount", settings.getData().getInt("crates." + t.getName() + ".amount") + i);
sender.sendMessage(ChatColor.GREEN + "You give: " + i + " kitunlockers to the player: " + t.getName());
/*    */ 	 settings.saveData();
	        
	
/*    */       
/*    */     }     
/* 54 */     
/*    */   }
/*    */


return false;
}

public static void GiveUnlockers(Player p) {
if (settings.getData() == null) {
	return;
}
Integer i = settings.getData().getInt("crates." + p.getName() + ".amount");
if (i == 0 || i == null) {
	return;
}
    final ItemStack sopas = new ItemStack(Material.CHEST);
    final ItemMeta sopasm = sopas.getItemMeta();
    sopasm.setDisplayName("§bKit Unlocker");
    sopas.setItemMeta(sopasm);
    sopas.setAmount(i);
    p.getInventory().addItem(sopas);	
}
}

