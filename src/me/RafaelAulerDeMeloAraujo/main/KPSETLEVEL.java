package me.RafaelAulerDeMeloAraujo.main;


import java.io.IOException;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.RafaelAulerDeMeloAraujo.Coins.Commands;
import me.RafaelAulerDeMeloAraujo.Coins.XP;
import net.wavemc.core.bukkit.WaveBukkit;
import net.wavemc.core.bukkit.account.WavePlayer;


public class KPSETLEVEL implements CommandExecutor {


public static void SetLevel(final Player p , int level) {
   XP.setXP(p, Main.customization.getInt("XP-Required-To-LevelUP") * level);
}


/*    */   public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
/*    */   {
/* 18 */     
/* 19 */     if (cmd.getName().equalsIgnoreCase("kpsetlevel"))
/*    */     {
/* 22 */       if (args.length == 0) {
/* 23 */         sender.sendMessage(ChatColor.RED+ "Use /kpsetlevel <Nick> <Level>");
/* 34 */         return true;
/*    */       }       
/* 37 */       if (!sender.hasPermission("kitpvp.setlevel")) {
	sender.sendMessage(ChatColor.DARK_RED + "You do not have access to that command.");
	return true;
}
/* 38 */         Player t = org.bukkit.Bukkit.getServer().getPlayer(args[0]);
/* 39 */         if (t == null) {
	sender.sendMessage(ChatColor.RED+ "Please insert a online player name");
	return true;
}
else if (args.length == 1) {
/* 23 */         sender.sendMessage(ChatColor.RED+ "Use /kpsetlevel <Nick> <Level>");
/* 34 */         return true;
/*    */       } 

Integer i = Integer.parseInt(args[1]);
	
	if (!Commands.isNumeric(i.toString())) {
		sender.sendMessage("Use only numbers and a player name!");
		return true;
	}
		
/* 40 */           SetLevel(t, i);
WavePlayer t1 = WaveBukkit.getInstance().getPlayerManager().getPlayer(t.getName());
try {
	WaveBukkit.getInstance().getPlayerManager().getController().save(t1);
} catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
sender.sendMessage(ChatColor.GREEN + "You seted the level: " + i + " to the player " + t.getName());
/*    */ 	
	        
	
/*    */       
/*    */     }     
/* 54 */     
/*    */ 


return false;
}
}

