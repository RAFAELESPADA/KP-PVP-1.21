package me.RafaelAulerDeMeloAraujo.SpecialAbility;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.plugin.Plugin;

import me.RafaelAulerDeMeloAraujo.main.Main;

public class Phantom implements Listener
{
    public static HashMap<String, ItemStack[]> salvararmor;
    public static ArrayList<String> emphantom;
    
    static {
        Phantom.salvararmor = new HashMap<String, ItemStack[]>();
        Phantom.emphantom = new ArrayList<String>();
    }
    
    @EventHandler
    public void aogigante(final PlayerInteractEvent e) {
        final Player p = e.getPlayer();
        if (Habilidade.getAbility(p).equalsIgnoreCase("Phantom") && (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) && p.getItemInHand().getType() == Material.FEATHER) {
            if (Cooldown.add(p)) {
                API.MensagemCooldown(p);
                return;
            }
      	  if (API.isInRegion(p)) {
    		  p.sendMessage(ChatColor.RED + "Leave the NO PVP Zone to use this kit!");
    		  return;
    	  }
            Phantom.salvararmor.put(p.getName(), p.getInventory().getArmorContents());
            API.tirarArmadura(p);
            /*  88 */       ItemStack Capacete = new ItemStack(Material.LEATHER_HELMET);
            /*  89 */       LeatherArmorMeta kCapacete = (LeatherArmorMeta)Capacete.getItemMeta();
            /*  90 */       kCapacete.setColor(Color.WHITE);
            /*  91 */       Capacete.setItemMeta(kCapacete);
            /*  92 */       Capacete.addEnchantment(Enchantment.PROTECTION, 3);
            /*  93 */       ItemStack Peitoral = new ItemStack(Material.LEATHER_CHESTPLATE);
            /*  94 */       LeatherArmorMeta kPeitoral = (LeatherArmorMeta)Peitoral.getItemMeta();
            /*  95 */       kPeitoral.setColor(Color.WHITE);
            /*  96 */       Peitoral.setItemMeta(kPeitoral);
            /*  97 */       Peitoral.addEnchantment(Enchantment.PROTECTION, 3);
            /*  98 */       ItemStack Calss = new ItemStack(Material.LEATHER_LEGGINGS);
            /*  99 */       LeatherArmorMeta kCalss = (LeatherArmorMeta)Calss.getItemMeta();
            /* 100 */       kCalss.setColor(Color.WHITE);
            /* 101 */       Calss.setItemMeta(kCalss);
            /* 102 */       Calss.addEnchantment(Enchantment.PROTECTION, 3);
            /* 103 */       ItemStack Bota = new ItemStack(Material.LEATHER_BOOTS);
            /* 104 */       LeatherArmorMeta kBota = (LeatherArmorMeta)Capacete.getItemMeta();
            /* 105 */       kBota.setColor(Color.WHITE);
            /* 106 */       Bota.setItemMeta(kBota);
            /* 107 */       Bota.addEnchantment(Enchantment.PROTECTION, 3);
            
            /*     */       
            /* 110 */       p.getInventory().setHelmet(Capacete);
            /* 111 */       p.getInventory().setChestplate(Peitoral);
            /* 112 */       p.getInventory().setLeggings(Calss);
            /* 113 */       p.getInventory().setBoots(Bota);
            /* 114 */       p.updateInventory();
            Phantom.emphantom.add(p.getName());
            p.setAllowFlight(true);
            p.setFlying(true);
            Cooldown.add(p, Main.kits.getInt("PhantomCooldown"));
            Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)Main.getInstance(), (Runnable)new Runnable() {
                @Override
                public void run() {
                	p.sendMessage(String.valueOf(API.NomeServer) + (Main.messages.getString("PhantomFly").replace("&", "§")).replace("%time%", "5"));
                }
            }, 0L);
            Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)Main.getInstance(), (Runnable)new Runnable() {
                @Override
                public void run() {
                	p.sendMessage(String.valueOf(API.NomeServer) + (Main.messages.getString("PhantomFly").replace("&", "§")).replace("%time%", "4"));
                }
            }, 20L);
            Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)Main.getInstance(), (Runnable)new Runnable() {
                @Override
                public void run() {
                	p.sendMessage(String.valueOf(API.NomeServer) + (Main.messages.getString("PhantomFly").replace("&", "§")).replace("%time%", "3"));
                }
            }, 40L);
            Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)Main.getInstance(), (Runnable)new Runnable() {
                @Override
                public void run() {
                	p.sendMessage(String.valueOf(API.NomeServer) + (Main.messages.getString("PhantomFly").replace("&", "§")).replace("%time%", "2"));
                }
            }, 60L);
            Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)Main.getInstance(), (Runnable)new Runnable() {
                @Override
                public void run() {
                	p.sendMessage(String.valueOf(API.NomeServer) + (Main.messages.getString("PhantomFly").replace("&", "§")).replace("%time%", "1"));
                }
            }, 80L);
            Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)Main.getInstance(), (Runnable)new Runnable() {
                @Override
                public void run() {
                	p.sendMessage(String.valueOf(API.NomeServer) + (Main.messages.getString("PhantomFlyEnd").replace("&", "§")));
                    Phantom.emphantom.remove(p.getName());
                    p.getInventory().setArmorContents((ItemStack[])Phantom.salvararmor.get(p.getName()));
                    p.updateInventory();
                    p.setAllowFlight(false);
                    p.setFlying(false);
                }
            }, 100L);
            Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)Main.getInstance(), (Runnable)new Runnable() {
                @Override
                public void run() {
                    p.sendMessage(API.fimcooldown);
                }
            }, Main.kits.getInt("PhantomCooldown") * 20);
        }
    }
    @EventHandler
	public void onUpdate(me.RafaelAulerDeMeloAraujo.main.ServerTimerEvent event) {
		if (event.getCurrentTick() % 2 > 0)
			return;
		for (Player ghosts : Bukkit.getOnlinePlayers())
		if (emphantom.contains(ghosts.getName())) {
			Location l = ghosts.getLocation();

			l.getWorld().playEffect(l, Effect.PORTAL_TRAVEL, 20);
			
		}
    }
    
    
    @EventHandler
    public void aoinv(final InventoryClickEvent e) {
        try {
            final Player p = (Player)e.getWhoClicked();
            if (Phantom.emphantom.contains(p.getName()) && e.getSlotType().equals((Object)InventoryType.SlotType.ARMOR)) {
                e.setCancelled(true);
            }
        }
        catch (Exception ex) {}
    }
}
