package me.RafaelAulerDeMeloAraujo.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.entity.EntityExplodeEvent;

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



	  @EventHandler
	  public void NoExplodeMyMapPlease(BlockExplodeEvent e)
	  {
		  World w = Bukkit.getServer().getWorld(Main.plugin.getConfig().getString("Spawn.World"));
		  if (e.getBlock().getWorld().equals(w)) {
	    e.setCancelled(true);
		  }
	  }


}
