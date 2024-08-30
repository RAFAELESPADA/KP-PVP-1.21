/*    */ package me.RafaelAulerDeMeloAraujo.SpecialAbility;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import me.RafaelAulerDeMeloAraujo.main.Main;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.block.Action;
/*    */ import org.bukkit.event.entity.EntityDamageByEntityEvent;
/*    */ import org.bukkit.event.player.PlayerInteractEvent;
/*    */ import org.bukkit.event.player.PlayerMoveEvent;
/*    */ import org.bukkit.inventory.PlayerInventory;
/*    */ import org.bukkit.scheduler.BukkitScheduler;
/*    */ 
/*    */ public class TimeLord implements org.bukkit.event.Listener
/*    */ {
/*    */   private Main main;
/*    */   static Main plugin;
/*    */   
/*    */   public TimeLord(Main main)
/*    */   {
/* 22 */     this.main = main;
/* 23 */     plugin = main;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/* 28 */   public static ArrayList<String> playercongelados = new ArrayList();
/*    */   
/*    */   @EventHandler
/*    */   public void onTimerLord(PlayerInteractEvent e)
/*    */   {
/* 33 */     final Player p = e.getPlayer();
/* 34 */     if ((Habilidade.getAbility(p).equalsIgnoreCase("TimeLord")) && ((e.getAction() == Action.RIGHT_CLICK_AIR) || (e.getAction() == Action.RIGHT_CLICK_BLOCK)) && (p.getInventory().getItemInHand().getType() == org.bukkit.Material.CLOCK)) {
/* 35 */       if (Cooldown.add(p)) {
/* 36 */         API.MensagemCooldown(p);
/* 37 */         return;
/*    */       }

/* 39 */       Cooldown.add(p, Main.kits.getInt("TimelordCooldown"));
/* 40 */       p.sendMessage(String.valueOf(API.NomeServer) + Main.messages.getString("TimelordUse").replace("&", "§"));
/* 41 */       for (final Entity pertos : p.getNearbyEntities(Main.kits.getDouble("TimelordRadius"), Main.kits.getDouble("TimelordRadius"), Main.kits.getDouble("TimelordRadius"))) {
	if (pertos instanceof Player) {
	    if (!Habilidade.ContainsAbility((Player)pertos)) {
	    	return;
	    }
/* 42 */         playercongelados.add(((Player)pertos).getName());
/* 43 */         ((Player)pertos).sendMessage(String.valueOf(API.NomeServer) + Main.messages.getString("Timelordfrozen").replace("&", "§"));
/* 45 */         org.bukkit.Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable()
/*    */         {
/*    */           public void run() {
/* 48 */             TimeLord.playercongelados.remove(((Player)pertos).getName());
/* 49 */             ((Player)pertos).sendMessage(String.valueOf(API.NomeServer) + Main.messages.getString("TimelordUnfrozen").replace("&", "§"));
/*    */           }
/* 51 */         }, 160L);
/*    */       {
/* 53 */       org.bukkit.Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable()
/*    */       {
/*    */         public void run() {
/* 56 */           p.sendMessage(API.fimcooldown);
/*    */         }
/* 58 */       }, Main.kits.getInt("TimelordCooldown") * 20);
/*    */     }
}
}
}
/*    */   }
/*   
/*    */   
/*    */   @EventHandler
/*    */   public void onTimerLordado(PlayerMoveEvent e) {
/* 64 */     Player p = e.getPlayer();
/* 65 */     if (playercongelados.contains(p.getName())) {
/* 66 */       e.setTo(p.getLocation());
/*    */     }
/*    */   }
/*    */   
/*    */ }


/* Location:              D:\Desktop\video\Minhas Coisas do Desktop\KP-PVPvB12 (1).jar!\me\RafaelAulerDeMeloAraujo\SpecialAbility\TimeLord.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */
