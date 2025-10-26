/*    */ package me.RafaelAulerDeMeloAraujo.main;

/*    */ import org.bukkit.command.Command;
/*    */ import org.bukkit.command.CommandExecutor;
/*    */ import org.bukkit.command.CommandSender;
/*    */ 
/*    */ public class KitCredits implements CommandExecutor
/*    */ {
/*    */   static Main plugin;
/*    */   
/*    */   public KitCredits(Main main)
/*    */   {
/* 18 */     plugin = main;
/*    */   }
/*    */   
/*    */   public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
/*    */   {
/* 23 */     
/* 24 */     if (cmd.getName().equalsIgnoreCase("kitcredits"))
/*    */     {
/*    */ 
/* 27 */       sender.sendMessage("§4§l\u274C §2§lCREDITS §f§lAND §e§lINFORMATION §4§l \u274C");
/* 28 */       sender.sendMessage("§6\u279C §cPlugin Name: §eKP-PVP");
/* 29 */       sender.sendMessage("§6\u279C §cPlugin Version: §e " + Main.getInstance().getDescription().getVersion());
/* 30 */       sender.sendMessage("§6\u279C §cAuthor: §ezEnderX5_ , Rafael Auler");
/* 31 */       sender.sendMessage("§6\u279C §cAuthor Channel: http://bit.ly/2kC345B");
/* 32 */       sender.sendMessage("§6\u279C §cSpigot Profile: http://bit.ly/2j5qvnM");
/* 33 */       sender.sendMessage("§6\u279C §cPlugin Page: http://bit.ly/2BZCtLE");
/* 34 */       sender.sendMessage("§cThanks for use this plugin i really appreaciate IT");
/* 35 */       sender.sendMessage("§cIf you like it consider giving a §e§l\u2605\u2605\u2605\u2605\u2605 §cReview");
/* 36 */       sender.sendMessage("§cPS: §eSubscribe to my channel and follow me on Spigot Thanks! §9§l=)");
/* 37 */       
/*    */       
/* 39 */       return false;
/*    */     }
/* 41 */     return false;
/*    */   }
/*    */ }


/* Location:              D:\Desktop\video\Minhas Coisas do Desktop\KP-PVPvB12 (1).jar!\me\RafaelAulerDeMeloAraujo\main\KitCredits.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */
