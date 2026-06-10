package me.RafaelAulerDeMeloAraujo.main;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import me.RafaelAulerDeMeloAraujo.SpecialAbility.Habilidade;
import me.RafaelAulerDeMeloAraujo.SpecialAbility.Join;

public class Compass implements Listener {

    @EventHandler
    public void onCompass(PlayerInteractEvent event) {

        Player p = event.getPlayer();

        if (!Join.game.contains(p.getName())) {
            return;
        }

        if (Habilidade.getAbility(p).equals(
                Main.getInstance().getConfig().getString("NoKit-DefaultName"))) {
            return;
        }

        if (p.getInventory().getItemInMainHand().getType() != Material.COMPASS) {
            return;
        }

        Action action = event.getAction();

        if (action != Action.LEFT_CLICK_AIR
                && action != Action.LEFT_CLICK_BLOCK
                && action != Action.RIGHT_CLICK_AIR
                && action != Action.RIGHT_CLICK_BLOCK) {
            return;
        }

        List<Entity> nearby = p.getNearbyEntities(1000, 128, 1000);

        Player nearest = null;
        double nearestDistance = Double.MAX_VALUE;

        for (Entity entity : nearby) {

            if (entity.hasMetadata("NPC")) {
                continue;
            }

            if (entity.getType() != EntityType.PLAYER) {
                continue;
            }

            Player target = (Player) entity;

            if (target.equals(p)) {
                continue;
            }

            double distance = p.getLocation().distanceSquared(target.getLocation());

            if (distance < nearestDistance) {
                nearestDistance = distance;
                nearest = target;
            }
        }

        Player finalNearest = nearest;

        Main.getFolia().getScheduler().runAtEntity(
                p,
                task -> {

                    if (!p.isOnline()) {
                        return;
                    }

                    if (finalNearest != null && finalNearest.isOnline()) {

                        p.setCompassTarget(finalNearest.getLocation());

                        p.sendMessage(
                                "§fCompass pointing to: §5"
                                        + finalNearest.getName());

                    } else {

                        p.setCompassTarget(
                                p.getWorld().getSpawnLocation());

                        p.sendMessage(
                                "§cNo Player has found! Pointing to spawn");
                    }
                }
        );
    }
}