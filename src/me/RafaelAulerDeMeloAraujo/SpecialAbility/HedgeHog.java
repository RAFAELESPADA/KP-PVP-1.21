package me.RafaelAulerDeMeloAraujo.SpecialAbility;



import java.util.HashMap;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import me.RafaelAulerDeMeloAraujo.main.Main;

public class HedgeHog implements Listener {
  public static HashMap<String, Integer> cd = new HashMap<>();
  
  @EventHandler
  public void onHedgehog(PlayerInteractEvent e) {
    final Player p = e.getPlayer();
    
    if (p.getItemInHand().getType() == Material.ARROW && 
      Habilidade.getAbility(p) == "HedgeHog")
      if (Cooldown.add(p)) {
        e.setCancelled(true);
        p.updateInventory();
        API.sendMessageCooldown(p);
      } else {
          Cooldown.add(p, Main.kits.getInt("HedgeHogCooldown"));
        e.setCancelled(true);
        p.updateInventory();
        Location loc = p.getEyeLocation();
        for (int pitch = 0; pitch >= -90; pitch -= 15) {
          for (int yaw = 180; yaw >= -180; yaw -= 15) {
            loc.setYaw(yaw);
            loc.setPitch(pitch);
            final Arrow arrow = (Arrow)p.launchProjectile(Arrow.class);
            arrow.setVelocity(loc.getDirection().multiply(1.2D));
            arrow.setKnockbackStrength(5);
            (new BukkitRunnable() {
                public void run() {
                  arrow.remove();
                }
              }).runTaskLater((Plugin)Main.getInstance(), 100L);
          } 
        }
      }
  }
}

