package me.RafaelAulerDeMeloAraujo.main;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@RequiredArgsConstructor
public class ServerTimerEvent extends Event {
	
	private static final HandlerList handlers = new HandlerList();
	
	private final long currentTick = 0;

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}

	public long getCurrentTick() {
		// TODO Auto-generated method stub
		return currentTick;
	}
}
