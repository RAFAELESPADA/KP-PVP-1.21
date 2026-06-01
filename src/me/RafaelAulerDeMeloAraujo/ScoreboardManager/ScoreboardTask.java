package me.RafaelAulerDeMeloAraujo.ScoreboardManager;


import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.Scoreboard;

import me.RafaelAulerDeMeloAraujo.Coins.Coins;
import me.RafaelAulerDeMeloAraujo.Coins.XP;
import me.RafaelAulerDeMeloAraujo.SpecialAbility.Habilidade;
import me.RafaelAulerDeMeloAraujo.main.AntiDeathDrop;
import net.wavemc.core.bukkit.WaveBukkit;
import net.wavemc.core.bukkit.account.WavePlayer;

public class ScoreboardTask extends BukkitRunnable
{

	
	

	  public static void start()
	  {
		  for (Player player : Bukkit.getOnlinePlayers()) {
	    	if (player.getScoreboard().getObjective("pvp") == null) {
				return;
			}
			Scoreboard scoreboard = player.getScoreboard();
			 WavePlayer Sun8oxData = WaveBukkit.getInstance().getPlayerManager().getPlayer(player.getName());
				int ks = Sun8oxData.getPvp().getKillstreak();
			scoreboard.getTeam("kit").setSuffix(Habilidade.getAbility(player));
			scoreboard.getTeam("kills").setSuffix(String.valueOf(AntiDeathDrop.GetKills(player)));
			scoreboard.getTeam("deaths").setSuffix(String.valueOf(AntiDeathDrop.GetDeaths(player)));
			scoreboard.getTeam("killstreak").setSuffix(String.valueOf((ks)));
			scoreboard.getTeam("coins").setSuffix(String.valueOf(Coins.getCoins(player)));
			scoreboard.getTeam("xp").setSuffix(String.valueOf(XP.getXP(player)));
			scoreboard.getTeam("level").setSuffix(String.valueOf(Level.getLevel(player)));
		}
	  }
	      public void run()
	      {
	       start();
	      }
	      {
}
}
	    
