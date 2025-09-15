package me.RafaelAulerDeMeloAraujo.Listeners;



import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import me.RafaelAulerDeMeloAraujo.Coins.Coins;
import me.RafaelAulerDeMeloAraujo.Coins.XP;
import me.RafaelAulerDeMeloAraujo.ScoreboardManager.Level;
import me.RafaelAulerDeMeloAraujo.SpecialAbility.Join;
import me.RafaelAulerDeMeloAraujo.main.AntiDeathDrop;
import me.RafaelAulerDeMeloAraujo.main.Main;
import net.wavemc.core.bukkit.WaveBukkit;
import net.wavemc.core.bukkit.account.WavePlayer;

public class StatusGUI implements Listener {



    @EventHandler
    private void onPlayerInteract(PlayerInteractAtEntityEvent event) {
        Player player = event.getPlayer();
        if (player.getItemInHand().getType().equals(Material.PLAYER_HEAD)) {
        	if (Join.game.contains(player.getName())) {
            if (event.getRightClicked().getType() == EntityType.ARMOR_STAND)
                event.setCancelled(true);
        }
        }
    }
    public static Inventory getTopInventory(InventoryEvent event) {
        try {
            Object view = event.getView();
            Method getTopInventory = view.getClass().getMethod("getTopInventory");
            getTopInventory.setAccessible(true);
            return (Inventory) getTopInventory.invoke(view);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
    public static Inventory getTopInventory2(InventoryEvent event) {
        try {
            Object view = event.getView();
            Method getTopInventory = view.getClass().getMethod("getTopInventory");
            getTopInventory.setAccessible(true);
            return (Inventory) getTopInventory.invoke(view);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @EventHandler
    private void onInventoryClick(InventoryClickEvent event) {
    	if (event.getClickedInventory() == null) {
    		return;
    	}
    	if (!(event.getClickedInventory().getHolder() instanceof CustomHolder3)) {
    		return;
    	}
    	else if (event.getWhoClicked() instanceof Player && event.getCurrentItem() != null && event.getCurrentItem().hasItemMeta() && !event.getCurrentItem().getItemMeta().getDisplayName().equals(Main.messages.getString("DisplayinChat").replace("&", "§"))) {
            event.setCancelled(true);
        }
    }
    @EventHandler
    private void onInv2entoryClick(InventoryClickEvent event) {
    	Player p = (Player) event.getWhoClicked();
    	Player player = (Player) event.getWhoClicked();
    	if (event.getCurrentItem() == null) {
    		return;
    	}
    	
    	if (!(event.getClickedInventory().getHolder() instanceof CustomHolder3)) {
    		return;
    	}
    
    	else if (event.getWhoClicked() instanceof Player && event.getCurrentItem().getItemMeta().getDisplayName().equals(Main.messages.getString("DisplayinChat").replace("&", "§"))) {
        	int kills = AntiDeathDrop.GetKills(p);
            int deaths = AntiDeathDrop.GetDeaths(p);
            WavePlayer Sun8oxData = WaveBukkit.getInstance().getPlayerManager().getPlayer(player.getName());
    		int ks = Sun8oxData.getPvp().getKillstreak();
            p.sendMessage(Main.messages.getString("Status").replace("&", "§").replace("%player%", p.getName()));
            p.sendMessage("");
            p.sendMessage(Main.messages.getString("StatusKills").replace("&", "§") + kills);
            p.sendMessage(Main.messages.getString("StatusDeaths").replace("&", "§") + deaths);      
            p.sendMessage(Main.messages.getString("StatusCoins").replace("&", "§") + Coins.getCoins(p));
            p.sendMessage(Main.messages.getString("StatusKS").replace("&", "§") + ks);
            p.sendMessage(Main.messages.getString("StatusXP").replace("&", "§") + XP.getXP(p));
            p.sendMessage(Main.messages.getString("StatusLevel").replace("&", "§") + Level.getLevel(p));
            p.sendMessage("§b");
            p.playSound(p.getLocation(), Sound.valueOf(Main.getInstance().getConfig().getString("Sound.ShopMenu")), 12.0F, 1.0F);
            event.setCancelled(true);
        }
    }

    public static void openGUI(Player player, Player target) {
    	
        Inventory inv = Bukkit.createInventory(new CustomHolder3(), 54, Main.messages.getString("StatusGuiInventory").replace("&", "§"));
        ItemStack glass = getCustomItemStack(Material.getMaterial(Main.messages.getString("StatusGuiMaterial")), " ", " ");
        for (int i = 0; i < 54; ++i) {
            if ((i <= 9 || i >= 17) && (i <= 27 || i >= 35)) {
                if (i <= 36 || i >= 44) {
                    inv.setItem(i, glass);
                }
            }
        }
        try {
        double kdr = AntiDeathDrop.GetDeaths(player) == 0 ? (double) AntiDeathDrop.GetKills(player) : (double) AntiDeathDrop.GetKills(player) / (double) AntiDeathDrop.GetDeaths(player);
        inv.setItem(11, glass);
        inv.setItem(12, glass);
        inv.setItem(14, glass);
        inv.setItem(15, glass);
        WavePlayer Sun8oxData = WaveBukkit.getInstance().getPlayerManager().getPlayer(player.getName());
		int ks = Sun8oxData.getPvp().getKillstreak();
        for (int i = 17; i > 0; i--) {
        	if(Main.messages.getString("StatusGlassMaterial") == null){
        		inv.addItem(getCustomItemStack(Material.getMaterial("YELLOW_STAINED_GLASS_PANE"), "§7(" + "§6§lLEVEL" + "§7) §b§l" + i, Arrays.asList("§a" + player.getName() + Main.messages.getString("StatusLevelINFO").replace("&", "§") + Level.getLevel(player),  Main.messages.getString("StatusLevelQuestion").replace("&", "§") + i  + "§f? " + (Level.getLevel(player) >= i ? Main.messages.getString("StatusGuiYes").replace("&", "§") : Main.messages.getString("StatusGuiNo").replace("&", "§")))));
        		}
        	else
            inv.addItem(getCustomItemStack(Material.getMaterial(Main.messages.getString("StatusGlassMaterial").toUpperCase()), "§7(" + Main.messages.getString("StatusLevelName").replace("&", "§") + "§7) §b§l" + i, Arrays.asList("§a" + player.getName() + Main.messages.getString("StatusLevelINFO").replace("&", "§") + Level.getLevel(player),  Main.messages.getString("StatusLevelQuestion").replace("&", "§") + i  + "§f? " + (Level.getLevel(player) >= i ? Main.messages.getString("StatusGuiYes").replace("&", "§") : Main.messages.getString("StatusGuiNo").replace("&", "§")))));
    }
        inv.setItem(4, editItemStack(getPlayerSkull(player.getName()), Main.messages.getString("StatusGuiInformation").replace("&", "§"), Arrays.asList("§fNick: §a" + player.getName(), "§fUUID: §a" + player.getUniqueId(), "§fCoins: §a" + new DecimalFormat("###,###.##").format(Coins.getCoins(player)), Main.messages.getString("StatusGuiFirstAcess").replace("&", "§") + " §a" + new SimpleDateFormat("dd/MM/yyyy HH:mm").format(player.getFirstPlayed()))));
        inv.setItem(10, getCustomItemStack(Material.DIAMOND_SWORD, Main.messages.getString("StatusGuiPlayerStats").replace("&", "§"), Arrays.asList("§fKills: §a" + AntiDeathDrop.GetKills(player), "§fDeaths: §a" + AntiDeathDrop.GetDeaths(player) , "§fWins ( Sumo ): §a" + AntiDeathDrop.GetSumoK(player), "§fLosses ( Sumo ): §a" + AntiDeathDrop.GetSumoD(player) , "§fWinstreak ( Sumo ): §a" + AntiDeathDrop.GetSumoWin(player) , "§fWins ( 1V1 ): §a" + AntiDeathDrop.GetX1K(player) , "§fLosses ( 1V1 ): §a" + AntiDeathDrop.GetSumoD(player) , "§fWinStreak ( 1V1 ): §a" + AntiDeathDrop.GetX1W(player), "§fKDR: §a" + String.format("%.2f",kdr),"§fKillstreak: §a" + ks)));
        inv.setItem(13, getCustomItemStack(Material.ENDER_EYE, Main.messages.getString("StatusGuiBoosters").replace("&", "§"), Arrays.asList("§fXP Boost: §a" + (player.hasPermission("kitpvp.doublexp") ? "Yes" : "No"), "§fCoins Boost: §a" + (player.hasPermission("kitpvp.doublecoins") ? Main.messages.getString("StatusGuiYes").replace("&", "§") : Main.messages.getString("StatusGuiNo").replace("&", "§")))));
        inv.setItem(16, getCustomItemStack(Material.EXPERIENCE_BOTTLE, Main.messages.getString("StatusGuiLevel").replace("&", "§"), Arrays.asList("§fLevel: §7(" + "§6§lLEVEL" + "§7) §b" + Level.getLevel(player), Main.messages.getString("StatusLevelNext").replace("&", "§") + (Level.getLevel(player) + 1), Main.messages.getString("StatusXpNecessary").replace("&", "§") + Level.getXPToLevelUp(player) + "XP")));
        inv.setItem(22, getCustomItemStack(Material.DIAMOND_AXE, Main.messages.getString("DisplayinChat").replace("&", "§"), Arrays.asList(Main.messages.getString("StatusGuiYourStatsLore").replace("&", "§"))));
        target.openInventory(inv);
        } catch (NullPointerException e) { 	
        target.sendMessage("THERE IS A FATAL ERROR OPENING THIS MENU! CONTACT SERVER ADMINS");
        	return;
        }
    }

    public static ItemStack editItemStack(ItemStack itemStack, String name, List<String> lore) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(name);
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public static ItemStack getPlayerSkull(String name) {
        ItemStack itemStack = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta meta = (SkullMeta) itemStack.getItemMeta();
        meta.setOwner(name);
        itemStack.setItemMeta(meta);
        return itemStack;
    }

public static ItemStack getCustomItemStack(Material material, String name, String lore) {
    ItemStack itemStack = new ItemStack(material);
    ItemMeta itemMeta = itemStack.getItemMeta();
    itemMeta.setDisplayName(name);
    if (lore != null) {
        List<String> l = Collections.singletonList(lore);
        itemMeta.setLore(l);
    }
    itemStack.setItemMeta(itemMeta);
    return itemStack;
}

public static ItemStack getCustomItemStack(Material material, String name, List<String> lore) {
    ItemStack itemStack = new ItemStack(material);
    ItemMeta itemMeta = itemStack.getItemMeta();
    itemMeta.setDisplayName(name);
    itemMeta.setLore(lore);
    itemStack.setItemMeta(itemMeta);
    return itemStack;
}

}
