/*    */ package me.RafaelAulerDeMeloAraujo.main;
import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.player.PlayerCommandPreprocessEvent;
/*    */ 
/*    */ public class BlockCommands implements org.bukkit.event.Listener
/*    */ {
/*    */   private Main main;
/*    */   static Main plugin;
/*    */   
/*    */   public BlockCommands(Main main)
/*    */   {
/* 15 */     this.main = main;
/* 16 */     plugin = main;
/*    */   }
/*    */   

/*    */   
@EventHandler
public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent e) {
  if (!e.getPlayer().hasPermission("kitpvp.unblockedcmds") && !e.getPlayer().hasPermission("kitpvp.*") && !e.getPlayer().isOp() && (me.RafaelAulerDeMeloAraujo.SpecialAbility.Join.game.contains(e.getPlayer().getName()) && (Main.getInstace().getConfig().getString("EnableCommandBlockingInKitPvP").equalsIgnoreCase("true")))) {
	  java.util.List<String> list = Main.plugin.getConfig().getStringList("BLOCKED_COMMANDS");
    list.stream().filter(cmd -> e.getMessage().toLowerCase().contains(cmd.toLowerCase())).forEach(msg -> {
          e.setCancelled(true);
          e.getPlayer().closeInventory();
          e.getPlayer().sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§")) + String.valueOf(this.main.getConfig().getString("Message.BlockedCMD-OnKitPvP").replace("&", "§")));
        });
  } 
}
@EventHandler
public void onPlayerCommandPreproctess(PlayerCommandPreprocessEvent e) {
  if (!e.getPlayer().hasPermission("kitpvp.unblockedcmds") && !e.getPlayer().hasPermission("kitpvp.*") && !e.getPlayer().isOp() && (me.RafaelAulerDeMeloAraujo.SpecialAbility.Join.game.contains(e.getPlayer().getName()) && (Main.getInstace().getConfig().getString("EnableOnlyCommandsAllowedInKitPvP").equalsIgnoreCase("true")))) {
	  java.util.List<String> list = Main.plugin.getConfig().getStringList("ALLOWED_COMMANDS");  
      // function to check whether all elements are divisible by 2.

      boolean result = list.stream().anyMatch(name -> {
          return e.getMessage().toLowerCase().contains(name.toLowerCase());
      });
   if (!result) {
	   e.setCancelled(true);
       e.getPlayer().closeInventory();
       e.getPlayer().sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§")) + String.valueOf(this.main.getConfig().getString("Message.BlockedCMD-OnKitPvP").replace("&", "§")));
   }
  }
}
}

/*    */ 


/* Location:              D:\Desktop\video\Minhas Coisas do Desktop\KP-PVPvB12 (1).jar!\me\RafaelAulerDeMeloAraujo\main\BlockCommands.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */
