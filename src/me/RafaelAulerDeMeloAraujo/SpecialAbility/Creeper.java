package me.RafaelAulerDeMeloAraujo.SpecialAbility;


import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.potion.PotionEffectType;

import me.RafaelAulerDeMeloAraujo.main.Main;

public class Creeper implements Listener {



	public static HashMap<String, Location> saveworld = new HashMap();

@EventHandler
public void onDamage1(EntityDamageEvent event) {
    if (event.getEntity() instanceof Player) {
        Player p = (Player)event.getEntity();
        if ((event.getCause() == EntityDamageEvent.DamageCause.ENTITY_EXPLOSION) || (event.getCause() == EntityDamageEvent.DamageCause.BLOCK_EXPLOSION)) {
        	if (Habilidade.getAbility(p) == "Creeper") {
        	    event.setCancelled(true);
        	}
        }
    }
}
@EventHandler(priority = EventPriority.MONITOR)
public void onDeatgh(PlayerDeathEvent event) {
	final Player morreu = event.getEntity();
    if (morreu.getKiller() != null) {
	final Player matou = event.getEntity().getKiller();
			
	
	
	
	 if (Habilidade.getAbility(matou) != "Berserker") {
		 return;
	 }
API.darEfeito(matou, PotionEffectType.STRENGTH, 7, 0);

API.darEfeito(matou, PotionEffectType.SPEED, 7, 1);
		}
     }

			
@EventHandler(priority = EventPriority.MONITOR)
public void onDeath(PlayerDeathEvent event) {
	final Player morreu = event.getEntity();
			
	
	
	
	 if (Habilidade.getAbility(morreu) != "Creeper") {
		 return;
	 }
	  if (API.isInRegion(morreu)) {
		  return;
	  }
     if (morreu.getKiller() != null) {
		morreu.getLocation().getWorld().playEffect(morreu.getLocation(), Effect.EXTINGUISH, 40);
for (Player p : Bukkit.getOnlinePlayers()) {
        p.playSound(p.getLocation(), Sound.valueOf(Main.getInstance().getConfig().getString("Sound.RyuAbility")), 3.0F, 3.0F);
}
		morreu.sendMessage(ChatColor.GREEN + "You died with creeper kit and created a explosion");
		Location deathLoc = morreu.getLocation();
		saveworld.put(morreu.getName(), deathLoc);
		morreu.spigot().respawn();
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable() {
		  public void run() {
			  morreu.getWorld().createExplosion(saveworld.get(morreu.getKiller().getName()), 20.0F);
		  }
		}, 1);
		morreu.getWorld().createExplosion(saveworld.get(morreu.getKiller().getName()), 10.0F);
		for (final Entity pertos : morreu.getKiller().getNearbyEntities(4.0, 4.0, 4.0)) {
			if (pertos instanceof Player) {
				morreu.getWorld().createExplosion(pertos.getLocation(), 4.0F);
				morreu.getWorld().strikeLightning(pertos.getLocation());
	}
}
     }
}
}