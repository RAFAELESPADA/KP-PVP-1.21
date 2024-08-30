package me.RafaelAulerDeMeloAraujo.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityExplodeEvent;

import me.RafaelAulerDeMeloAraujo.SpecialAbility.Habilidade;
import me.RafaelAulerDeMeloAraujo.SpecialAbility.Join;
import me.RafaelAulerDeMeloAraujo.main.Main;

public class NoExplosion
  implements Listener
{
	  @EventHandler
	  public void NoExplodeMyMapPlease(EntityExplodeEvent e)
	  {
		  World w = Bukkit.getServer().getWorld(Main.plugin.getConfig().getString("Spawn.World"));
		  if (e.getEntity().getWorld().equals(w)) {
	    e.setCancelled(true);
		  }
	  }





}
