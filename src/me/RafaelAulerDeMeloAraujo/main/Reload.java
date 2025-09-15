/*    */ package me.RafaelAulerDeMeloAraujo.main;
/*    */ 
/*    */ 
/*    */ import org.bukkit.command.Command;
/*    */ import org.bukkit.command.CommandExecutor;
/*    */ import org.bukkit.command.CommandSender;
/*    */
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.plugin.Plugin;
/*    */ 
/*    */ 
/*    */ public class Reload implements CommandExecutor
/*    */ {
/*    */   private Main main;
/*    */   static Main plugin;
/*    */   
/*    */   public Reload(Main main)
/*    */   {
/* 19 */     this.main = main;
/* 20 */     plugin = main;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
/*    */   {
/* 27 */     Player p = (Player)sender;
/* 28 */     if (command.getName().equalsIgnoreCase("kitreload"))
/*    */     {
/* 30 */       if (!sender.hasPermission("kitpvp.reload")) {
/* 31 */         p.sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§")) + this.main.getConfig().getString("Permission").replace("&", "§").replaceAll("%permisson%", label));
/* 32 */        return true;
/*    */       }
/*    */       
/* 36 */       Plugin plugin = p.getServer().getPluginManager().getPlugin("KP-PVP");
/* 37 */       plugin.reloadConfig();
/* 38 */       p.getServer().getPluginManager().disablePlugin(plugin);
/* 39 */       p.getServer().getPluginManager().enablePlugin(plugin);
/* 40 */       p.sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§")) + Main.messages.getString("PluginReloaded").replace("&", "§"));
/* 41 */       return true;
/*    */     }
/* 43 */     return false;
/*    */   }
/*    */ }


/* Location:              D:\Desktop\video\Minhas Coisas do Desktop\KP-PVPvB12 (1).jar!\me\RafaelAulerDeMeloAraujo\main\Reload.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */
