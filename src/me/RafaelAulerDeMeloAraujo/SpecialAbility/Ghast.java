package me.RafaelAulerDeMeloAraujo.SpecialAbility;


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
		  int total = Main.kits.getInt("GhastFireballs");

		  for (int i = 0; i < total; i++) {

		      long delay = i * 40L;

		      Main.getFolia().getScheduler().runAtEntityLater(p, () -> {

		          if (!p.isOnline()) {
		              return;
		          }

		          Fireball h = p.launchProjectile(Fireball.class);

		          h.setMetadata(
		              "GGG",
		              new FixedMetadataValue(Main.getInstance(), true)
		          );

		          p.playSound(
		              p.getLocation(),
		              Sound.ENTITY_GHAST_SCREAM,
		              1F,
		              1F
		          );

		      }, delay);
		  }

/*  74 */     Cooldown.add(p, Main.kits.getInt("GhastCooldown"));
}
}
}

