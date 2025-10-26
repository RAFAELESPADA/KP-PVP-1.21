package me.RafaelAulerDeMeloAraujo.Listeners;




import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.RafaelAulerDeMeloAraujo.SpecialAbility.API;
import me.RafaelAulerDeMeloAraujo.SpecialAbility.Cooldown;
import me.RafaelAulerDeMeloAraujo.SpecialAbility.Habilidade;
import me.RafaelAulerDeMeloAraujo.main.Main;
import net.wavemc.core.bukkit.item.ItemBuilder;

public class MilkMan implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
    	if (Habilidade.getAbility(event.getPlayer()) != "Milkman") {
    		return;
    	}
        if (!event.hasItem() || !ItemBuilder.has(event.getItem(), "kit-handler", "milkman")) {
        	return;
        }
        Player p = event.getPlayer();
        event.setCancelled(true);
        if (Cooldown.add(p)) {
            API.MensagemCooldown(p);
            return;
        }
  	  if (API.isInRegion(p)) {
		  p.sendMessage(ChatColor.RED + "Leave the NO PVP Zone to use this kit!");
		  return;
	  }
        Cooldown.add(p, 30);
        Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)Main.getInstance(), (Runnable)new Runnable() {
            @Override
            public void run() {

                event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 5 * 20, 0));
                event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 10 * 20, 0));
                event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 5 * 20, 0));
                event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.STRENGTH, 3 * 20, 0));
                event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 3 * 20, 0));
                event.getPlayer().sendMessage("§aMilkman applied!");
                            }
        }, 20L * 1);
        Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)Main.getInstance(), (Runnable)new Runnable() {
            @Override
            public void run() {
            	p.sendMessage(API.fimcooldown);
    }}, 20L * 30);
        
    }}
