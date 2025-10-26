package me.RafaelAulerDeMeloAraujo.SpecialAbility;


import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import me.RafaelAulerDeMeloAraujo.Coins.XP;
import me.RafaelAulerDeMeloAraujo.ScoreboardManager.Level;

public class PlayerLevelUPEvent extends Event {

	private static final HandlerList HANDLERS = new HandlerList();
	
	private final Player player;
	private final int level;
	private final int xp2;
	
	public PlayerLevelUPEvent(Player player) {
		this.player = player;
		this.level = Level.getLevel(player);

		this.xp2 = XP.getXP(player);
	}
	
	public Player getPlayer() { return player; }
	
	public int getLevel() { return level; }
	

	public int getXP() { return xp2; }
	
	public HandlerList getHandlers() { return HANDLERS; }

	public String getEventName() { return "PlayerLevelUPEvent"; }

	public static HandlerList getHandlerList() { return HANDLERS; }
	
}