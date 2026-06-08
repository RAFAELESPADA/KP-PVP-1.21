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
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import me.RafaelAulerDeMeloAraujo.main.Main;

public class HedgeHog implements Listener {
  public static HashMap<String, Integer> cd = new HashMap<>();
  
  @EventHandler
  public void onHedgehog(PlayerInteractEvent e) {
    final Player p = e.getPlayer();
    if (p.getInventory().getItemInMainHand().getType() != Material.ARROW) {
        return;
    }
    if (
      Habilidade.getAbility(p) == "HedgeHog")
      if (Cooldown.add(p)) {
        e.setCancelled(true);
        p.updateInventory();
        API.sendMessageCooldown(p);
      } else {
        e.setCancelled(true);
        p.updateInventory();
        Action action = e.getAction();

        if (action != Action.RIGHT_CLICK_AIR &&
            action != Action.RIGHT_CLICK_BLOCK) {
            return;
        }
        Location base = p.getEyeLocation();

        Cooldown.add(p, Main.kits.getInt("HedgeHogCooldown"));
        for (int pitch = 0; pitch >= -80; pitch -= 20) {
            for (int yaw = 180; yaw >= -180; yaw -= 20) {
Location loc = base.clone();
                loc.setYaw(yaw);
                loc.setPitch(pitch);
            final Arrow arrow = (Arrow)p.launchProjectile(Arrow.class);
            arrow.setVelocity(loc.getDirection().multiply(1.4D));
            arrow.setKnockbackStrength(5);

            Main.getFolia().getScheduler().runLater(() -> {
                if (!arrow.isDead() && arrow.isValid()) {
                    arrow.remove();
                }
            }, 100L);
        }}
      }}}

