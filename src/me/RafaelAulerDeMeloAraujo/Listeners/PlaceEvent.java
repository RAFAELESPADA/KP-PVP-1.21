package me.RafaelAulerDeMeloAraujo.Listeners;



import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import me.RafaelAulerDeMeloAraujo.SpecialAbility.Habilidade;
import me.RafaelAulerDeMeloAraujo.SpecialAbility.Join;
import me.RafaelAulerDeMeloAraujo.main.Main;

/*    */ public class PlaceEvent
/*    */   implements Listener
/*    */ {
    @EventHandler
    public void onPlayerInteractGlad(final PlayerInteractEvent e) {
        final Player p = e.getPlayer();
        if (p.getItemInHand().getType() == Material.BEACON && Habilidade.getAbility(p).equalsIgnoreCase("Ryu")) {
            e.setCancelled(true);
            p.updateInventory();
        }
    }
        @EventHandler
        public void onPlayerInteractGlad2(final PlayerInteractEvent e) {
            final Player p = e.getPlayer();
            if (p.getItemInHand().getType() == Material.TNT && Habilidade.getAbility(p).equalsIgnoreCase("Bomber")) {
                e.setCancelled(true);
                p.updateInventory();
            }
    }
}
 