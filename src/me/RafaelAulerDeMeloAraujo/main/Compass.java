package me.RafaelAulerDeMeloAraujo.main;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
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

        Player nearest = null;
        double nearestDistance = Double.MAX_VALUE;

        Location playerLocation = p.getLocation();

        for (Player target : Bukkit.getOnlinePlayers()) {

            if (target.equals(p)) {
                continue;
            }

            if (!target.getWorld().equals(p.getWorld())) {
                continue;
            }

            double distance = playerLocation.distanceSquared(
                    target.getLocation());

            if (distance < nearestDistance) {
                nearestDistance = distance;
                nearest = target;
            }
        }

        if (nearest == null) {

            p.setCompassTarget(
                    p.getWorld().getSpawnLocation());

            p.sendMessage(
                    "§cNo Player has found! Pointing to spawn");

            return;
        }

        Player target = nearest;

        Main.getFolia().getScheduler().runAtEntity(
                target,
                task -> {

                    if (!target.isOnline()) {
                        return;
                    }

                    Location targetLocation =
                            target.getLocation().clone();

                    Main.getFolia().getScheduler().runAtEntity(
                            p,
                            task2 -> {

                                if (!p.isOnline()) {
                                    return;
                                }

                                p.setCompassTarget(
                                        targetLocation);

                                p.sendMessage(
                                        "§fCompass pointing to: §5"
                                                + target.getName());
                            });
                });
    }
}