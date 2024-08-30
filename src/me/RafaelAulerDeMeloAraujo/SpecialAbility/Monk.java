package me.RafaelAulerDeMeloAraujo.SpecialAbility;

import org.bukkit.event.player.*;
import org.bukkit.entity.*;

import java.util.*;
import org.bukkit.*;
import org.bukkit.plugin.*;
import me.RafaelAulerDeMeloAraujo.main.Main;

import org.bukkit.inventory.*;
import org.bukkit.event.*;

public class Monk implements Listener
{
    @EventHandler
    public void aoMonk(final PlayerInteractEntityEvent e) {
        final Player p = e.getPlayer();
        if (e.getRightClicked() instanceof Player) {
            final Player jogadorClicado = (Player)e.getRightClicked();
            if (Habilidade.getAbility(p).equalsIgnoreCase("Monk") && p.getItemInHand().getType() == Material.BLAZE_ROD) {
                if (Cooldown.add(p)) {
                    API.MensagemCooldown(p);
                    return;
                }
                boolean isCitizensNPC = jogadorClicado.hasMetadata("NPC");
                if (isCitizensNPC) {
                	p.sendMessage("Please dont monk our npcs!");
                	return;
                }
                final int random = new Random().nextInt(jogadorClicado.getInventory().getSize() - 10 + 1 + 10);
                final ItemStack ItemSelecionado = jogadorClicado.getInventory().getItem(random);
                final ItemStack ItemMudado = jogadorClicado.getItemInHand();
                jogadorClicado.setItemInHand(ItemSelecionado);
                jogadorClicado.getInventory().setItem(random, ItemMudado);
                jogadorClicado.sendMessage(String.valueOf(API.NomeServer) + Main.messages.getString("MonkedMessage").replace("&", "§"));
                p.sendMessage(String.valueOf(API.NomeServer) + Main.messages.getString("MonkUse").replace("&", "§").replace("%player%", jogadorClicado.getName()));
                Cooldown.add(p, Main.kits.getInt("MonkCooldown"));
                Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)Main.getInstance(), (Runnable)new Runnable() {
                    @Override
                    public void run() {
                        p.sendMessage(API.fimcooldown);
                    }
                }, 20L * Main.kits.getInt("MonkCooldown"));
            }
        }
    }
}
