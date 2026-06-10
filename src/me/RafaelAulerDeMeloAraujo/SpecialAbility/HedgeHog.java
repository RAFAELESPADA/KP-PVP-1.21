package me.RafaelAulerDeMeloAraujo.SpecialAbility;

import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import me.RafaelAulerDeMeloAraujo.main.Main;

public class HedgeHog implements Listener {

    public static HashMap<String, Integer> cd = new HashMap<>();

    @EventHandler
    public void onHedgehog(PlayerInteractEvent e) {

        Player p = e.getPlayer();

        if (p.getInventory().getItemInMainHand().getType() != Material.ARROW) {
            return;
        }

        if (!"HedgeHog".equals(Habilidade.getAbility(p))) {
            return;
        }

        Action action = e.getAction();

        if (action != Action.RIGHT_CLICK_AIR
                && action != Action.RIGHT_CLICK_BLOCK) {
            return;
        }

        e.setCancelled(true);

        if (Cooldown.add(p)) {
            API.sendMessageCooldown(p);
            return;
        }

        Cooldown.add(p, Main.kits.getInt("HedgeHogCooldown"));

        Location base = p.getEyeLocation();

        for (int pitch = 0; pitch >= -80; pitch -= 20) {

            for (int yaw = 180; yaw >= -180; yaw -= 20) {

                Location loc = base.clone();
                loc.setYaw(yaw);
                loc.setPitch(pitch);

                Arrow arrow = p.launchProjectile(Arrow.class);

                arrow.setVelocity(loc.getDirection().multiply(1.4D));
                arrow.setKnockbackStrength(5);

                arrow.getScheduler().runDelayed(
                        Main.getInstance(),
                        task -> {
                            if (arrow.isValid()) {
                                arrow.remove();
                            }
                        },
                        () -> {},
                        100L
                );
            }
        }
    }
}