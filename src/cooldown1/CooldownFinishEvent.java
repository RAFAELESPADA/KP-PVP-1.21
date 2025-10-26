package cooldown1;



	import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;

import net.wavemc.core.util.WaveCooldownAPI;



	public class CooldownFinishEvent extends CooldownEvent {

	    public CooldownFinishEvent(Player player, WaveCooldownAPI cooldown) {
	        super();
	    }

	    private static final HandlerList handlers = new HandlerList();

	    public static HandlerList getHandlerList() {
	        return handlers;
	    }

	    @Override
	    public HandlerList getHandlers() {
	        return handlers;
	    }
	}
