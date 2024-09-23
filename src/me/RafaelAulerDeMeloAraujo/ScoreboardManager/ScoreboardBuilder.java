package me.RafaelAulerDeMeloAraujo.ScoreboardManager;


import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.entity.Player;

import me.RafaelAulerDeMeloAraujo.Coins.Coins;
import me.RafaelAulerDeMeloAraujo.Coins.XP;
import me.RafaelAulerDeMeloAraujo.SpecialAbility.Habilidade;
import me.RafaelAulerDeMeloAraujo.SpecialAbility.Join;
import me.RafaelAulerDeMeloAraujo.main.AntiDeathDrop;
import me.RafaelAulerDeMeloAraujo.main.Main;
import net.wavemc.core.bukkit.WaveBukkit;
import net.wavemc.core.bukkit.account.WavePlayer;
public class ScoreboardBuilder {


	private static WaveAnimation waveAnimation;
	private static String text = "";
	public static final Map<UUID, FastBoard> boards = new HashMap<>();
	public static void build(FastBoard board) {
		 Player player = board.getPlayer();
		if (!Join.game.contains(player.getName())) {
			return;
		}
		
		
		;
		WavePlayer Sun8oxData = WaveBukkit.getInstance().getPlayerManager().getPlayer(player.getName());
		int ks = Sun8oxData.getPvp().getKillstreak();
		String l10 = "§3";
		String l9 = Main.messages.getString("ScoreBoardKit").replace("&", "§") + Habilidade.getAbility(player);
		String l8 = "§2";
		String l7 = Main.messages.getString("ScoreBoardKills").replace("&", "§") + AntiDeathDrop.GetKills(player);
		String l6 = Main.messages.getString("ScoreBoardDeaths").replace("&", "§") + AntiDeathDrop.GetDeaths(player);
		String l5 = Main.messages.getString("ScoreBoardKS").replace("&", "§") + ks;
		String l4 = "§1";
		String l3 = Main.messages.getString("ScoreBoardCoins").replace("&", "§") + Coins.getCoins(player);
		String l2 = Main.messages.getString("ScoreBoardXP").replace("&", "§") + XP.getXP(player);
		String l1 = "§0";
		String l0 = Main.messages.getString("ScoreBoardLevel").replace("&", "§") + Level.getLevel(player);
		String lxp = Main.messages.getString("ScoreBoardNeedXP").replace("&", "§") + Level.getXPToLevelUp(player);
		
		board.updateLines(
		        l10,
		        l9,
		        l8,
		        l7,
		        l6,
		        l5,
		        l4,
		        l3,
		        l2,
		        l1,
		        l0,
		        lxp
		);
		
	}
}
