package cooldown1;

import org.bukkit.inventory.ItemStack;

import lombok.Getter;
import lombok.Setter;
import net.wavemc.core.util.WaveCooldownAPI;

public class ItemCooldown extends WaveCooldownAPI {

    @Getter
    private ItemStack item;

    @Getter
    @Setter
    private boolean selected;

    public ItemCooldown(ItemStack item, String name, Long duration) {
        super(name, duration);
        this.item = item;
    }
}
