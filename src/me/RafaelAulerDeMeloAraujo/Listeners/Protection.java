package me.RafaelAulerDeMeloAraujo.Listeners;


import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import me.RafaelAulerDeMeloAraujo.SpecialAbility.Habilidade;
import me.RafaelAulerDeMeloAraujo.SpecialAbility.Join;
import me.RafaelAulerDeMeloAraujo.X1.X1;
import me.RafaelAulerDeMeloAraujo.main.Main;


public class Protection implements Listener {



@EventHandler(priority = EventPriority.HIGHEST)
public void onEntityDamageByEntity(final EntityDamageByEntityEvent event) {
	if (Main.getInstance().getConfig().getBoolean("DisableKPSpawnProtection")) {
		return;
	}

	if (event.getEntity() instanceof Player && (event.getDamager() instanceof Player)) {
		boolean isCitizensNPC = event.getEntity().hasMetadata("NPC");
		boolean isCitizensNPC2 = event.getDamager().hasMetadata("NPC");
		if (isCitizensNPC) {
			return;
		}
		if (isCitizensNPC2) {
			return;
		}
	if (Join.game.contains(event.getEntity().getName()) && !Join.game.contains(event.getDamager().getName())) {
		event.getDamager().sendMessage(ChatColor.BLUE + "You cannot attack " + event.getEntity().getName() + " because he is on kitpvp and you are not");
		event.setCancelled(true);
	}
	if (!Join.game.contains(event.getEntity().getName()) && Join.game.contains(event.getDamager().getName())) {
		event.getDamager().sendMessage(ChatColor.BLUE + "You cannot attack " + event.getEntity().getName() + " because you are on kitpvp and he is not");
		event.setCancelled(true);
	}
	if (Join.game.contains(event.getEntity().getName()) && Join.game.contains(event.getDamager().getName()) && !X1.lutadores.containsKey(event.getDamager().getName()) && !X1.lutadores.containsKey(event.getEntity().getName())) {

		final Player damaged = (Player) event.getEntity();
		final Player damager = (Player) event.getDamager();
		if (Habilidade.getAbility(damaged) == Main.getInstance().getConfig().getString("NoKit-DefaultName")) {
			event.setCancelled(true);
		}
		if (Habilidade.getAbility(damager) == Main.getInstance().getConfig().getString("NoKit-DefaultName")) {
			event.setCancelled(true);
		}
		if (Habilidade.getAbility(damaged) == Main.cfg_x1.getString("x1.ability")) {
			event.setCancelled(true);
		}
		if (Habilidade.getAbility(damaged) == Main.cfg_x1.getString("sumo.ability")) {
			event.setCancelled(true);
		}
		}
	}
}
}
