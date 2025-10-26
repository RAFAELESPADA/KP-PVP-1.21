/*    */ package me.RafaelAulerDeMeloAraujo.Listeners;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
/*    */ 
/*    */ import org.bukkit.Effect;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.util.Vector;

import me.RafaelAulerDeMeloAraujo.SpecialAbility.API;
import me.RafaelAulerDeMeloAraujo.SpecialAbility.Cooldown;
import me.RafaelAulerDeMeloAraujo.SpecialAbility.Habilidade;
import me.RafaelAulerDeMeloAraujo.SpecialAbility.NewKitMenu;
import me.RafaelAulerDeMeloAraujo.main.Main;
/*    */ 
/*    */ public class Switcher implements org.bukkit.event.Listener
/*    */ {
	
	@EventHandler
	public void snowball(final ProjectileLaunchEvent e) {
	    if (e.getEntity() instanceof Snowball) {
	    	if (e.getEntity().getShooter() instanceof Player) {
	        final Player p = (Player) e.getEntity().getShooter();
	            if (!Habilidade.ContainsAbility(p)) {
	            	return;
	            }
	            if (Cooldown.add(p) && Habilidade.getAbility(p) == "Switcher") {
	             e.setCancelled(true);
	             API.MensagemCooldown(p);	             
	             return;
	            }
	      	  if (API.isInRegion(p)) {
	    		  p.sendMessage(ChatColor.RED + "Leave the NO PVP Zone to use this kit!");
	    		  return;
	    	  }
	            if (Habilidade.getAbility(p) == "Switcher") {
		             Vector multiplied = p.getEyeLocation().getDirection().multiply(4);
		             Snowball snowball1 = p.getWorld().spawn(p.getEyeLocation(), Snowball.class);
		             e.setCancelled(true);
		             snowball1.setVelocity(multiplied);
		             snowball1.setShooter(p);
		             Bukkit.getServer().getScheduler().runTaskLater(Main.getInstance(), () -> {
		                 
		                 snowball1.remove();
		             }, 20);
	            Cooldown.add(p, 7);
	        }
	    	}
	    
	    }
	}

/*    */   @org.bukkit.event.EventHandler
/*    */   public void playerDamage(EntityDamageByEntityEvent e)
/*    */   {
/*    */     Snowball s;
/* 17 */     if (((e.getEntity() instanceof Player)) && ((e.getEntity() instanceof Player)) && ((e.getDamager() instanceof Snowball)) && (((s = (Snowball)e.getDamager()).getShooter() instanceof Player) && Habilidade.getAbility((Player)s.getShooter()) == "Switcher")) {
/* 18 */       Player p = (Player)s.getShooter();
/* 19 */       Player p2 = (Player)e.getEntity();
boolean isCitizensNPC = p2.hasMetadata("NPC");
if (isCitizensNPC) {
	return;
}
if (API.isInRegion(p2)) {
	  p.sendMessage(ChatColor.RED + "The player is on a protected zone! Switcher failed!");
	  return;
}
NewKitMenu.playSound(p, Main.getInstace().getConfig().getString("Sound.Fisherman"), 1.0F, 1.0F);
/*    */  
NewKitMenu.playSound(p2, Main.getInstace().getConfig().getString("Sound.Fisherman"), 1.0F, 1.0F);
/* 20 */       p.getWorld().playEffect(p.getLocation().add(0.0D, 1.5D, 0.0D), Effect.ENDER_SIGNAL, 1);
/* 23 */       p2.getWorld().playEffect(p2.getLocation().add(0.0D, 1.5D, 0.0D), Effect.ENDER_SIGNAL, 1);

/* 24 */       Location loc1 = p.getLocation();
/* 25 */       Location loc2 = p2.getLocation();
/* 26 */       p.teleport(loc2);
/* 27 */       p2.teleport(loc1);
/*    */     }
/*    */   }
/*    */ }


