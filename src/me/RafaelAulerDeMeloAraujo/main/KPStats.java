/*    */ package me.RafaelAulerDeMeloAraujo.main;

/*    */ import org.bukkit.command.Command;
/*    */ import org.bukkit.command.CommandExecutor;
/*    */ import org.bukkit.command.CommandSender;
/*    */ 
/*    */ import org.bukkit.entity.Player;

import me.RafaelAulerDeMeloAraujo.Listeners.StatusGUI;
/*    */ 
/*    */ 
/*    */ public class KPStats implements CommandExecutor
/*    */ {
/*    */   public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
/*    */   {
/* 18 */     Player p = (Player)sender;
/* 19 */     if (cmd.getName().equalsIgnoreCase("kpstats"))
/*    */     {
/* 22 */       if (args.length == 0) {
/* 23 */         StatusGUI.openGUI(p, p);
/* 34 */         return true;
/*    */       }       
/* 37 */       if ((args.length > 0) && (args.length < 2) && (p.hasPermission("kitpvp.stats.see.other"))) {
/* 38 */         Player t = org.bukkit.Bukkit.getServer().getPlayer(args[0]);
/* 39 */         if (t != null) {
/* 40 */           StatusGUI.openGUI(t, p);
/*    */         }
/*    */       }
/*    */     }     
/* 54 */     return false;
/*    */   }
/*    */ }


/* Location:              D:\Desktop\video\Minhas Coisas do Desktop\KP-PVPvB12 (1).jar!\me\RafaelAulerDeMeloAraujo\main\Stats.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */


/* Location:              D:\Desktop\video\Minhas Coisas do Desktop\KP-PVPvB12 (1).jar!\me\RafaelAulerDeMeloAraujo\main\KPStats.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */
