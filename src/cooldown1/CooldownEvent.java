package cooldown1;



import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import net.wavemc.core.util.WaveCooldownAPI;


@RequiredArgsConstructor
public abstract class CooldownEvent extends Event {

    

	@Getter
    @NonNull
    private Player player;

    @Getter
    @NonNull
    private WaveCooldownAPI cooldown;
}