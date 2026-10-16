package me.RafaelAulerDeMeloAraujo.main;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.RafaelAulerDeMeloAraujo.Listeners.CustomHolder4;
import me.RafaelAulerDeMeloAraujo.X1.Sumo;

public class WarpMenu implements Listener {

    @EventHandler
    public void warps(InventoryClickEvent e) {
        if (e.getClickedInventory() == null) {
            return;
        }

        if (!(e.getClickedInventory().getHolder()
                instanceof CustomHolder4)) {
            return;
        }

        e.setCancelled(true);

        ItemStack item = e.getCurrentItem();

        if (item == null || !item.hasItemMeta()) {
            return;
        }

        ItemMeta meta = item.getItemMeta();

        if (meta == null || !meta.hasDisplayName()) {
            return;
        }

        Player p = (Player) e.getWhoClicked();

        String name = meta.getDisplayName();

        if (name.equals("§aFPS")) {
            Bukkit.dispatchCommand(p, "kpwarp fps");
            p.closeInventory();

        } else if (name.equals("§aSumo")) {
            p.closeInventory();
            Sumo.entrar1v1(p);

        } else if (name.equals("§aLavaChallenge")) {
            Bukkit.dispatchCommand(p, "kpwarp challenge");
            p.closeInventory();

        } else if (name.equals("§aTraining")) {
            Bukkit.dispatchCommand(p, "kpwarp treino");
            p.closeInventory();
        }
    }

    public static void openwarp(Player p) {
        Inventory warps = Bukkit.createInventory(
                new CustomHolder4(),
                9,
                "Warps"
        );

        // FPS
        ItemStack fps = new ItemStack(Material.GLASS);
        ItemMeta fpsMeta = fps.getItemMeta();
        fpsMeta.setDisplayName("§aFPS");
        fps.setItemMeta(fpsMeta);

        // Lava Challenge
        ItemStack lava = new ItemStack(Material.LAVA_BUCKET);
        ItemMeta lavaMeta = lava.getItemMeta();
        lavaMeta.setDisplayName("§aLavaChallenge");
        lava.setItemMeta(lavaMeta);

        // Sumo
        ItemStack sumo = new ItemStack(Material.APPLE);
        ItemMeta sumoMeta = sumo.getItemMeta();
        sumoMeta.setDisplayName("§aSumo");
        sumo.setItemMeta(sumoMeta);

        // Training - Golden Spear
        ItemStack training = new ItemStack(
                Material.GOLDEN_SPEAR
        );

        ItemMeta trainingMeta = training.getItemMeta();
        trainingMeta.setDisplayName("§aTraining");
        training.setItemMeta(trainingMeta);

        // Slots do menu
        warps.setItem(0, fps);
        warps.setItem(1, lava);
        warps.setItem(2, sumo);
        warps.setItem(3, training);

        p.openInventory(warps);
    }
}