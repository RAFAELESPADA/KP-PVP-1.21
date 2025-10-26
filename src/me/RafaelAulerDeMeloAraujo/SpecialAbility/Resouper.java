package me.RafaelAulerDeMeloAraujo.SpecialAbility;


import org.bukkit.event.entity.*;
import org.bukkit.entity.*;
import org.bukkit.inventory.*;

import me.RafaelAulerDeMeloAraujo.main.Main;

import org.bukkit.*;
import org.bukkit.event.*;

public class Resouper implements Listener
{
    @EventHandler
    public void aomatar(final PlayerDeathEvent e) {
        if (e.getEntity().getKiller() instanceof Player) {
            final Player p = e.getEntity().getKiller();
            if (Habilidade.getAbility(p).equalsIgnoreCase("Resouper")) {
                for (int i = 0; i < p.getInventory().getSize(); ++i) {
                    p.getInventory().addItem(new ItemStack[] { new ItemStack(Material.MUSHROOM_STEW) });
                    p.updateInventory();
                }
                    p.sendMessage(String.valueOf(API.NomeServer) + (Main.messages.getString("ResouperMessage").replace("&", "§")));
                }
            }
        }
    }


