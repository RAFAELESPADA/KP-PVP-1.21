package me.RafaelAulerDeMeloAraujo.SpecialAbility;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffectType;

import me.RafaelAulerDeMeloAraujo.main.Main;

public class Poseidon implements Listener
{
    @EventHandler
    public void aoPoseidon(final PlayerMoveEvent e) {
        final Player p = e.getPlayer();
        final Block b = p.getLocation().getBlock();
        if (Habilidade.getAbility(p).equalsIgnoreCase("Poseidon") && (b.getType() == Material.WATER)) {
        	API.darEfeito(p, PotionEffectType.STRENGTH, Main.kits.getInt("PoseidonStrenghtTime"), Main.kits.getInt("PoseidonStrenghtAmplifier"));
            API.darEfeito(p, PotionEffectType.RESISTANCE, Main.kits.getInt("PoseidonDamageResistanceTime"), Main.kits.getInt("PoseidonDamageResistanceAmplifier"));
            API.darEfeito(p, PotionEffectType.SPEED, Main.kits.getInt("PoseidonSpeedTime"), Main.kits.getInt("PoseidonSpeedAmplifier"));
        }
    }
}

