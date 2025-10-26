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
import org.bukkit.metadata.Metadatable;

import me.RafaelAulerDeMeloAraujo.SpecialAbility.Habilidade;
import me.RafaelAulerDeMeloAraujo.SpecialAbility.Join;

public class Compass implements Listener
{
    @EventHandler
    public void onCompass(final PlayerInteractEvent event) {
        final Player p = event.getPlayer();
        if (Habilidade.getAbility(p) != Main.getInstance().getConfig().getString("NoKit-DefaultName") && p.getItemInHand().getType() == Material.COMPASS && Join.game.contains(p.getName()) && (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR)) {
            Boolean pesquisado = false;
            for (int i = 0; i < 1000; ++i) {
                final List<Entity> pertos = (List<Entity>)p.getNearbyEntities((double)i, 128.0, (double)i);
                
                for (final Object e : pertos) {
                	boolean isCitizensNPC = ((Metadatable) e).hasMetadata("NPC");
                    if (isCitizensNPC) {
                    	p.sendMessage(String.valueOf("§cNo Player has found! Pointing to spawn"));
                    	return;
                    }
                    if (((Entity)e).getType().equals((Object)EntityType.PLAYER) && p.getLocation().distance(((Entity)e).getLocation()) > 0.0) {
                        p.setCompassTarget(((Entity)e).getLocation());
                        p.sendMessage(String.valueOf( "§fCompass pointing to: §5" + ((Player)e).getName()));
                        pesquisado = true;
                        break;
                    }
                }
                if (pesquisado) {
                    break;
                }
            }
            if (!pesquisado) {
                p.sendMessage(String.valueOf("§cNo Player has found! Pointing to spawn"));
                p.setCompassTarget(p.getWorld().getSpawnLocation());
            }
        }
    }
}

