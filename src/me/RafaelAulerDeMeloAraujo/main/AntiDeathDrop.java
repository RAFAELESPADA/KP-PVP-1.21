package me.RafaelAulerDeMeloAraujo.main;


import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ItemDespawnEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;

import me.RafaelAulerDeMeloAraujo.SpecialAbility.Join;
import me.RafaelAulerDeMeloAraujo.SpecialAbility.NewKitMenu;
import net.wavemc.core.bukkit.WaveBukkit;
import net.wavemc.core.bukkit.account.WavePlayer;



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
      if (Join.game.contains(p.getName()))
      {
     
      if ((this.main.getConfig().getString("RespawnSound").equalsIgnoreCase("true")) && (Join.game.contains(p.getName()))) {
	        NewKitMenu.playSound(e.getPlayer(), this.main.getConfig().getString("Sound.Respawn"), 1.0F, 1.0F);
      }
      }}
    }
  
  @EventHandler
  public void onDeatht(PlayerDeathEvent event)
  {
	  Player p = event.getEntity();
	  if (Join.game.contains(p.getName()))
      {
		  List<ItemStack> drops = event.getDrops();
		  
		  





      ListIterator<ItemStack> litr = drops.listIterator();

      while(litr.hasNext()){

          ItemStack stack = litr.next();


          if (!stack.equals(new ItemStack(Material.MUSHROOM_STEW))) {

              litr.remove();

          }
      }
      }
			  }

  
	@EventHandler
	public void Drop(ItemDespawnEvent e) {
		
		Main.getFolia().getScheduler().runAtEntityLater(
			    e.getEntity(),
			    () -> {

			        World w = Bukkit.getServer().getWorld(
			            Main.plugin.getConfig().getString("Spawn.World")
			        );

			        if (e.getEntity().getWorld().equals(w)
			                && Main.getInstace().getConfig()
			                .getString("ClearItemsOnGround")
			                .equalsIgnoreCase("true")) {

			            e.getEntity().remove();

			            e.getEntity().getWorld().playEffect(
			                e.getEntity().getLocation(),
			                Effect.SMOKE,
			                2
			            );
			        }

			    },
			    20L * this.main.getConfig().getInt("ClearInterval")
			);
	}
		          
	


	public static int GetKills(UUID uuid)
	{
	    WavePlayer wavePlayer =
	            WaveBukkit.getPlayerManager()
	                      .getPlayers()
	                      .stream()
	                      .filter(p -> p.getUuid().equals(uuid))
	                      .findFirst()
	                      .orElse(null);

	    if (wavePlayer == null) {
	        return 0;
	    }

	    return wavePlayer.getPvp().getKills();
	}
	  public static int GetSumoK(Player p)
	  {
		  if (p == null) {
			  return 0;
		  }
		  WavePlayer killerHelixPlayer = WaveBukkit.getPlayerManager().getPlayer(p.getName());
			
	      
	    return killerHelixPlayer.getPvp().getWinssumo();
	  }
	  public static int GetSumoD(Player p)
	  {
		  if (p == null) {
			  return 0;
		  }
		  WavePlayer killerHelixPlayer = WaveBukkit.getInstance().getPlayerManager().getPlayer(p.getName());
			
	      
	    return killerHelixPlayer.getPvp().getDeathssumo();
	  }

	  public static int GetSumoWin(Player p)
	  {
		  if (p == null) {
			  return 0;
		  }
		  WavePlayer killerHelixPlayer = WaveBukkit.getInstance().getPlayerManager().getPlayer(p.getName());
			
	      
	    return killerHelixPlayer.getPvp().getWinstreaksumo();
	  }
	  public static int GetX1K(Player p)
	  {
		  if (p == null) {
			  return 0;
		  }
		  WavePlayer killerHelixPlayer = WaveBukkit.getInstance().getPlayerManager().getPlayer(p.getName());
			
	      
	    return killerHelixPlayer.getPvp().getWinsx1();
	  }
	  public static int GetX1D(Player p)
	  {
		  if (p == null) {
			  return 0;
		  }
		  WavePlayer killerHelixPlayer = WaveBukkit.getInstance().getPlayerManager().getPlayer(p.getName());
			
	      
	    return killerHelixPlayer.getPvp().getDeathsx1();
	  }
	  public static int GetX1W(Player p)
	  {
		  if (p == null) {
			  return 0;
		  }
		  WavePlayer killerHelixPlayer = WaveBukkit.getInstance().getPlayerManager().getPlayer(p.getName());
			
	      
	    return killerHelixPlayer.getPvp().getWinstreakx1();
	  }
	 
	  
	  public static int GetDeaths(UUID uuid) {
		  WavePlayer wavePlayer = WaveBukkit.getPlayerManager()
		    .getPlayers()
		    .stream()
		    .filter(p -> p.getUuid().equals(uuid))
		    .findFirst()
		    .orElse(null);

		    if (wavePlayer == null) {
		        return 0;
		    }

		    return wavePlayer.getPvp().getDeaths();
		}
}
