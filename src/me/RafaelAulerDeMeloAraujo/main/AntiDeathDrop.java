package me.RafaelAulerDeMeloAraujo.main;


import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ItemDespawnEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

import me.RafaelAulerDeMeloAraujo.SpecialAbility.Join;
import net.helix.core.bukkit.HelixBukkit;
import net.helix.core.bukkit.account.HelixPlayer;



public class AntiDeathDrop
  implements Listener
{
  private Main main;
  
  public AntiDeathDrop(Main main)
  {
    this.main = main;
  }
  
  @EventHandler
  public void onDeath(PlayerRespawnEvent e)
  {
    if (((e.getPlayer() instanceof Player)) && ((e.getPlayer().getKiller() instanceof Player)))
    {
      Player p = e.getPlayer();
      Player k = p.getKiller();
      if (Join.game.contains(p.getName()))
      {
     
      if ((this.main.getConfig().getString("RespawnSound").equalsIgnoreCase("true")) && (Join.game.contains(p.getName()))) {
        p.playSound(p.getLocation(), Sound.valueOf(this.main.getConfig().getString("Sound.Respawn")), 1.0F, 1.0F);
      }
      }}
    }
  
  @EventHandler
  public void onDeatht(PlayerDeathEvent e)
  {
	  Player p = e.getEntity();
	  if (Join.game.contains(p.getName()))
      {
		  e.getDrops().clear(); 
      }
  }

  
	@EventHandler
	public void Drop(ItemDespawnEvent e) {
		
		Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable()
				/*     */         {
				public void run()

				/*     */           {
					World w = Bukkit.getServer().getWorld(Main.plugin.getConfig().getString("Spawn.World"));
		  if (e.getEntity().getWorld().getName().equals(w) && (Main.getInstace().getConfig().getString("ClearItemsOnGround").equalsIgnoreCase("true"))) {
		e.getEntity().remove();
		e.getEntity().getWorld().playEffect(e.getEntity().getLocation(), Effect.SMOKE, 2);
		e.getEntity().getWorld().playSound(e.getEntity().getLocation(), Sound.valueOf(Main.getInstace().getConfig().getString("Sound.ItemDespawn")), 2.0F, 2.0F);
		  /*     */           }
				/* 167 */         }}, 20 * this.main.getConfig().getInt(("ClearInterval")));
				/*     */       }
		          
	


	  public static int GetKills(Player p)
	  {
		  if (p == null) {
			  return 0;
		  }
		  HelixPlayer killerHelixPlayer = HelixBukkit.getInstance().getPlayerManager().getPlayer(p.getName());
			
	      
	    return killerHelixPlayer.getPvp().getKills();
	  }
	 
	  
	  public static int GetDeaths(Player p)
	  {
		  if (p == null) {
			  return 0;
		  }
		  HelixPlayer killerHelixPlayer = HelixBukkit.getInstance().getPlayerManager().getPlayer(p.getName());
			
	      
		    return killerHelixPlayer.getPvp().getDeaths();
	  }
}
