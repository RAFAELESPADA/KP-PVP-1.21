package me.RafaelAulerDeMeloAraujo.Coins;


import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import me.RafaelAulerDeMeloAraujo.main.Main;
import net.wavemc.core.bukkit.WaveBukkit;
import net.wavemc.core.bukkit.account.WavePlayer;

public class XP
{
  @SuppressWarnings({ "unchecked", "rawtypes" })
public static HashMap<Player, Integer> bal = new HashMap();
  public static HashMap<Player, Integer> getCoinsMap()
  {
    return bal;
  }
  
  public static void addXP(Player player, int amount)
  {
	  WavePlayer Sun8oxData = WaveBukkit.getInstance().getPlayerManager().getPlayer(player.getName());
	  Sun8oxData.getPvp().addXP(amount);
  }
  
  public static void setXP(Player player, int amount)
  {
	  WavePlayer Sun8oxData = WaveBukkit.getInstance().getPlayerManager().getPlayer(player.getName());
	  Sun8oxData.getPvp().setXp(amount);
  }
  
  public static int getXP(Player player)
  {
	  if (player == null) {
		  Bukkit.getConsoleSender().sendMessage("Could not get a player XP");
		  return 0;
	  }
	  WavePlayer Sun8oxData = WaveBukkit.getInstance().getPlayerManager().getPlayer(player.getName());
	  
	  if (Sun8oxData == null) {
		  return 0;
	  }
	  return  Sun8oxData.getPvp().getXp();
  }

  public static void removeXP(Player player, int amount)
  {
	  WavePlayer Sun8oxData = WaveBukkit.getInstance().getPlayerManager().getPlayer(player.getName());
	  if (amount < Sun8oxData.getPvp().getXp()) {
	  setXP(player, Sun8oxData.getPvp().getXp() - amount);
	  } else {
		Sun8oxData.getPvp().setXp(Main.customization.getInt("MinimiumAmountOfXP"));  
	  }
  }
}