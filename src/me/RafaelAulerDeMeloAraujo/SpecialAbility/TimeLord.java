/*    */ package me.RafaelAulerDeMeloAraujo.SpecialAbility;
/*    */ 
/*    */ import java.util.ArrayList;

import org.bukkit.ChatColor;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.block.Action;
/*    */ import org.bukkit.event.player.PlayerInteractEvent;
/*    */ import org.bukkit.event.player.PlayerMoveEvent;

/*    */ import me.RafaelAulerDeMeloAraujo.main.Main;
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
   public static ArrayList<String> playercongelados = new ArrayList<>();
/*    */   
/*    */   @EventHandler
/*    */   public void onTimerLord(PlayerInteractEvent e)
/*    */   {
/* 33 */     final Player p = e.getPlayer();
/* 34 */     if (Habilidade.getAbility(p) != null
        && Habilidade.getAbility(p).equalsIgnoreCase("TimeLord") && ((e.getAction() == Action.RIGHT_CLICK_AIR) || (e.getAction() == Action.RIGHT_CLICK_BLOCK)) && (p.getInventory().getItemInHand().getType() == org.bukkit.Material.CLOCK)) {
/* 35 */       if (Cooldown.add(p)) {
/* 36 */         API.MensagemCooldown(p);
/* 37 */         return;
/*    */       }
if (API.isInRegion(p)) {
	  p.sendMessage(ChatColor.RED + "Leave the NO PVP Zone to use this kit!");
	  return;
}
Cooldown.add(p, Main.kits.getInt("TimelordCooldown"));
p.sendMessage(
    String.valueOf(API.NomeServer) +
    Main.messages.getString("TimelordUse").replace("&", "§")
);
/* 39 */  for (final Entity pertos : p.getNearbyEntities(
        Main.kits.getDouble("TimelordRadius"),
        Main.kits.getDouble("TimelordRadius"),
        Main.kits.getDouble("TimelordRadius"))) {

    if (!(pertos instanceof Player)) {
        continue;
    }

    Player alvo = (Player) pertos;
    if (alvo.equals(p)) {
        continue;
    }
    if (!Habilidade.ContainsAbility(alvo)) {
        continue;
    }

    playercongelados.add(alvo.getName());

    alvo.sendMessage(
        API.NomeServer +
        Main.messages.getString("Timelordfrozen").replace("&", "§")
    );
    
    Main.getFolia().getScheduler().runAtEntityLater(alvo, () -> {
        playercongelados.remove(alvo.getName());

        alvo.sendMessage(
            API.NomeServer +
            Main.messages.getString("TimelordUnfrozen").replace("&", "§")
        );
    }, 160L);
}

Main.getFolia().getScheduler().runAtEntityLater(p, () -> {
    p.sendMessage(API.fimcooldown);
}, Main.kits.getInt("TimelordCooldown") * 20L);
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
