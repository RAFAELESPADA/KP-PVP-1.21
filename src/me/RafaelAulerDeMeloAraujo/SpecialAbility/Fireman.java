package me.RafaelAulerDeMeloAraujo.SpecialAbility;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.event.*;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffectType;

import me.RafaelAulerDeMeloAraujo.main.Main;

public class Fireman implements Listener
{
    @EventHandler
    public void damage(final EntityDamageEvent e) {
        if (!(e.getEntity() instanceof Player)) {
            return;
        }
        final Player p = (Player)e.getEntity();
        if (Habilidade.getAbility(p).equalsIgnoreCase("Fireman") && (e.getCause() == EntityDamageEvent.DamageCause.LAVA || e.getCause() == EntityDamageEvent.DamageCause.FIRE || e.getCause() == EntityDamageEvent.DamageCause.FIRE_TICK)) {
            e.setCancelled(true);
	    	API.darEfeito(p, PotionEffectType.REGENERATION, Main.kits.getInt("FiremanRegenonLavaTime"), Main.kits.getInt("FiremanRegenonLavaAmplifier"));
        }
    }  
	 @EventHandler
	  public void aoAgua(PlayerMoveEvent e)
	  {
	    Player p = e.getPlayer();
	    Block b = p.getLocation().getBlock();
	    if ((Habilidade.getAbility(p).equalsIgnoreCase("Fireman")) && ((b.getType() == Material.WATER)))
	    {
	    	API.darEfeito(p, PotionEffectType.WEAKNESS, Main.kits.getInt("FiremanWeaknessonWaterTime"), Main.kits.getInt("FiremanWeaknessonWaterAmplifier"));
	    }
	  }
        }
    

