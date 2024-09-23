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

public class WarpMenu
  implements Listener
{
  @EventHandler
  public void warps(InventoryClickEvent e)
  {
    if ((e.getCurrentItem() != null) && (e.getCurrentItem().getItemMeta() != null))
    {
    	if (e.getClickedInventory() == null) {
    		return;
    	}
      Player p = (Player)e.getWhoClicked();
	  if (!(e.getClickedInventory().getHolder() instanceof CustomHolder4)) {
	  	return;
	  }
        e.setCancelled(true);
        if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§aFPS"))
        {
          Bukkit.dispatchCommand(p, "kpwarp fps");
          p.closeInventory();
        }
        if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§aSumo"))
        {
          Sumo.entrar1v1(p);
          p.closeInventory();
        }
        if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§aLavaChallenge"))
        {
          Bukkit.dispatchCommand(p, "kpwarp challenge");
          p.closeInventory();
        }
      }
    }
 
  
  public static void openwarp(Player p)
  {
      Inventory warps = Bukkit.createInventory(new CustomHolder4(), 9, "Warps");
      
      ItemStack fps = new ItemStack(Material.GLASS);
      ItemMeta fps2 = fps.getItemMeta();
      fps2.setDisplayName("§aFPS");
      fps.setItemMeta(fps2);
      
      
      ItemStack lava = new ItemStack(Material.LAVA_BUCKET);
      ItemMeta lava2 = lava.getItemMeta();
      lava2.setDisplayName("§aLavaChallenge");
      lava.setItemMeta(lava2);
      
      ItemStack sumo = new ItemStack(Material.APPLE);
      ItemMeta sumo2 = sumo.getItemMeta();
      sumo2.setDisplayName("§aSumo");
      sumo.setItemMeta(sumo2);
      
      
      warps.setItem(0, fps);
      warps.setItem(1, lava);
      warps.setItem(2, sumo);
      p.openInventory(warps);
    }
  {
  }
}
