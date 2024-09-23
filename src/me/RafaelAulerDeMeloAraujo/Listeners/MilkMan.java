package me.RafaelAulerDeMeloAraujo.Listeners;




import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.RafaelAulerDeMeloAraujo.SpecialAbility.API;
import me.RafaelAulerDeMeloAraujo.SpecialAbility.Cooldown;
import me.RafaelAulerDeMeloAraujo.SpecialAbility.Habilidade;
import me.RafaelAulerDeMeloAraujo.main.Main;
import net.wavemc.core.bukkit.item.ItemBuilder;

public class MilkMan implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
    	if (Habilidade.getAbility(event.getPlayer()) != "Milkman") {
    		return;
    	}
        if (!event.hasItem() || !ItemBuilder.has(event.getItem(), "kit-handler", "milkman")) {
        	return;
        }
        event.setCancelled(true);
        if (Cooldown.add(event.getPlayer())) {
			API.MensagemCooldown(event.getPlayer());
			return;
		}
       Cooldown.add(event.getPlayer(), Main.kits.getInt("MilkManCooldown"));
        event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 5 * 20, 0));
        event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 10 * 20, 0));
        event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 5 * 20, 0));
        event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.STRENGTH, 3 * 20, 0));
        event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 3 * 20, 0));
        event.getPlayer().sendMessage("§aMilkman applied!");
    }
}