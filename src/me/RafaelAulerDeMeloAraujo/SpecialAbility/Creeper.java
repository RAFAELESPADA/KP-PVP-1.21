package me.RafaelAulerDeMeloAraujo.SpecialAbility;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.potion.PotionEffectType;

import me.RafaelAulerDeMeloAraujo.main.Main;

public class Creeper implements Listener {

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        if (!(event.getEntity() instanceof Player p)) {
            return;
        }

        if (event.getCause() == EntityDamageEvent.DamageCause.ENTITY_EXPLOSION
                || event.getCause() == EntityDamageEvent.DamageCause.BLOCK_EXPLOSION) {

            if ("Creeper".equals(Habilidade.getAbility(p))) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onBerserkerKill(PlayerDeathEvent event) {
        Player morreu = event.getEntity();

        if (morreu.getKiller() == null) {
            return;
        }

        Player matou = morreu.getKiller();

        if (!"Berserker".equals(Habilidade.getAbility(matou))) {
            return;
        }

        API.darEfeito(matou, PotionEffectType.STRENGTH, 7, 0);
        API.darEfeito(matou, PotionEffectType.SPEED, 7, 1);
    }

    @SuppressWarnings({ "deprecation", "removal" })
	@EventHandler(priority = EventPriority.MONITOR)
    public void onCreeperDeath(PlayerDeathEvent event) {
        Player morreu = event.getEntity();

        if (!"Creeper".equals(Habilidade.getAbility(morreu))) {
            return;
        }

        if (API.isInRegion(morreu)) {
            return;
        }

        if (morreu.getKiller() == null) {
            return;
        }

        Location deathLoc = morreu.getLocation().clone();

        deathLoc.getWorld().playEffect(deathLoc, Effect.EXTINGUISH, 40);

        for (Player online : Bukkit.getOnlinePlayers()) {
            try {
                Sound sound = Sound.valueOf(
                        Main.getInstance().getConfig().getString("Sound.RyuAbility"));

                online.playSound(online.getLocation(), sound, 3.0F, 3.0F);
            } catch (Exception ignored) {
            }
        }

        morreu.sendMessage(ChatColor.GREEN +
                "You died with Creeper kit and created an explosion!");

        Main.getFolia().getScheduler().runAtEntityLater(morreu, () -> {
            morreu.spigot().respawn();
        }, 1L);

        Main.getFolia().getScheduler().runAtEntityLater(morreu, () -> {

            if (deathLoc.getWorld() != null) {
                deathLoc.getWorld().createExplosion(deathLoc, 8.0F);
            }

        }, 2L);

        for (Entity entity : morreu.getKiller().getNearbyEntities(4.0, 4.0, 4.0)) {
            if (entity instanceof Player) {
                entity.getWorld().createExplosion(entity.getLocation(), 4.0F);
                entity.getWorld().strikeLightning(entity.getLocation());
            }
        }
    }
}