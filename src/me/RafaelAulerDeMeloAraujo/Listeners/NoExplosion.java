package me.RafaelAulerDeMeloAraujo.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.entity.AbstractWindCharge;

import me.RafaelAulerDeMeloAraujo.main.Main;

public class NoExplosion implements Listener {

    @EventHandler
    public void onEntityExplode(EntityExplodeEvent e) {
        World spawnWorld = Bukkit.getWorld(
                Main.plugin.getConfig().getString("Spawn.World")
        );

        if (spawnWorld == null
                || !e.getEntity().getWorld().equals(spawnWorld)) {
            return;
        }

        // Permite a explosão da Wind Charge sem destruir blocos.
        if (e.getEntity() instanceof AbstractWindCharge) {
            e.blockList().clear();
            e.setYield(0.0F);
            return;
        }

        // Cancela as demais explosões de entidades no Spawn.
        e.setCancelled(true);
    }

    @EventHandler
    public void onBlockExplode(BlockExplodeEvent e) {
        World spawnWorld = Bukkit.getWorld(
                Main.plugin.getConfig().getString("Spawn.World")
        );

        if (spawnWorld == null
                || !e.getBlock().getWorld().equals(spawnWorld)) {
            return;
        }

        // Impede explosões originadas por blocos de destruírem o mapa.
        e.setCancelled(true);
    }
}