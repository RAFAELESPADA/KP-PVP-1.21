package me.RafaelAulerDeMeloAraujo.SpecialAbility;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.scheduler.BukkitRunnable;

import me.RafaelAulerDeMeloAraujo.main.Main;

public class Ghast implements Listener {


	   
@EventHandler(priority = EventPriority.HIGH)
public void onInteract(EntityDamageEvent event) {
	if (!(event.getEntity() instanceof Player)) {
		return;
	}
	Player p = (Player)event.getEntity();
	if (!(Habilidade.getAbility(p) == "Ghast")) {
		return;
	}	
	if (event.getCause() == DamageCause.ENTITY_EXPLOSION) {
		event.setCancelled(true);
	}
}

@EventHandler
public void dano(EntityDamageByEntityEvent e)
{
  if (((e.getEntity() instanceof Player)) && ((e.getDamager() instanceof Fireball)))
  {
   Fireball s = (Fireball)e.getDamager();
    if (s.hasMetadata("GGG")) {
      e.setDamage(e.getDamage() + 2);
    }
  }
  }
@EventHandler(priority = EventPriority.LOWEST)
public void onInteract(PlayerInteractEvent event) {
	Player p = event.getPlayer();
	if (event.getItem() == null) {
		return;
	}
	if (!(Habilidade.getAbility(p) == "Ghast")) {
		return;
	}	

	
	if (!event.getAction().equals(Action.RIGHT_CLICK_AIR)) {
	return;
	}
	if ((p.getItemInHand().getType() == Material.FIRE_CHARGE) && 
			/*  63 */       (Habilidade.getAbility(p) == "Ghast"))
			/*     */     {
		event.setCancelled(true);
		 if (Cooldown.add(p)) {
             API.MensagemCooldown(p);
             return;
         }
		  if (API.isInRegion(p)) {
    		  p.sendMessage(ChatColor.RED + "Leave the NO PVP Zone to use this kit!");
    		  return;
    	  }
			new BukkitRunnable() {
	            int fireballs = 0;
	            int total = Main.kits.getInt("GhastFireballs"); // total de bolas de fogo

	            @Override
	            public void run() {
	                fireballs++;

	    	        Fireball h = ( Fireball)p.launchProjectile(Fireball.class);
	    	        h.setMetadata("GGG", new FixedMetadataValue(Main.getInstance(), Boolean.valueOf(true)));
	                if ((Bukkit.getVersion().contains("1.9") || (Bukkit.getVersion().contains("1.10") || (Bukkit.getVersion().contains("1.11") || (Bukkit.getVersion().contains("1.12") || (Bukkit.getVersion().contains("1.13") || (Bukkit.getVersion().contains("1.14") || (Bukkit.getVersion().contains("1.15") || (Bukkit.getVersion().contains("1.16") || (Bukkit.getVersion().contains("1.17") || (Bukkit.getVersion().contains("1.18") || (Bukkit.getVersion().contains("1.19") || (Bukkit.getVersion().contains("1.20") || (Bukkit.getVersion().contains("1.21") || (Bukkit.getVersion().contains("1.22"))))))))))))))))
	                /*     */     
	                p.playSound(p.getLocation(), Sound.ENTITY_GHAST_SCREAM, 1F, 1F);
	                else {

		                p.playSound(p.getLocation(), Sound.ENTITY_GHAST_SCREAM, 1F, 1F);
	                }
	                if(fireballs >= total) {
	                    cancel();
	                }
	            }
	        }.runTaskTimer(Main.getInstance(), 0, 40);

/*  74 */     Cooldown.add(p, Main.kits.getInt("GhastCooldown"));
}
}
}

