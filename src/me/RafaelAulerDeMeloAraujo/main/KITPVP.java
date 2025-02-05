/*    */ package me.RafaelAulerDeMeloAraujo.main;
/*    */ 
/*    */ import org.bukkit.command.Command;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ public class KITPVP implements org.bukkit.command.CommandExecutor
/*    */ {
/*    */   
/*    */   public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
/*    */   {
/* 13 */     Player p = (Player)sender;
/* 14 */     if (label.equalsIgnoreCase("kphelp")) {
	if (!p.hasPermission("kitpvp.reload")) {
		  p.sendMessage("§eYou do not have permission");
		  return true;
	}
/* 15 */       p.sendMessage("§b§l\u2605 §e§lKP-PVP Command Help Menu §b§l\u2605");
/* 16 */       p.sendMessage("§a<> = Opcional §b/ §a[] = Required");
/* 17 */       p.sendMessage("§e/kitpvp §7(View the main command list)");
/* 18 */       p.sendMessage("§e/kpkits §7(Open the kit menu)");
/* 19 */       p.sendMessage("§e/kpshop §7(Open the Shop menu)");
/* 20 */       p.sendMessage("§e/kitreload §7(Reload the configuration)");
/* 21 */       p.sendMessage("§e/stats <Player> §7(View your or other player stats)");
/* 22 */       p.sendMessage("§e/kitclear §7(Clear your kit)");
/* 23 */        p.sendMessage("§e/kpkills §7(Give or Remove kills from other player)");
/* 24 */       p.sendMessage("§e/kitcredits §7(Display some information about the plugin)");
/* 25 */       p.sendMessage("§e/ksc [Message] §7(A Custom StaffChat)");
/* 26 */       p.sendMessage("§e/paycoins <Player> <Amount> - pay coins to someone");
p.sendMessage("§e/givecoins <Player> <Amount> - give coins to someone");
p.sendMessage("§e/removecoins <Player> <Amount> - remove coins from someone");
/* 27 */       p.sendMessage("§e/adminmode §7(Stay invisible and receive items to catch hackers!)");
/*    */       p.sendMessage("§e/kpeditmode §7(Enable building for you while in kitpvp!)");
/* 29 */       p.sendMessage("§e/kpsetspawn §7(Set the kitpvp spawn!)");
/* 30 */       p.sendMessage("§e/set1v1 §7(Set the 1VS1 spawn!)");

/*    */     }
/*    */     

/*    */ 
/* 34 */     return false;
/*    */   }
/*    */ }


/* Location:              D:\Desktop\video\Minhas Coisas do Desktop\KP-PVPvB12 (1).jar!\me\RafaelAulerDeMeloAraujo\main\KITPVP.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */
