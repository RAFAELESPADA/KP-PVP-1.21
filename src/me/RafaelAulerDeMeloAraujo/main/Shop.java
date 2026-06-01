package me.RafaelAulerDeMeloAraujo.main;


import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.RafaelAulerDeMeloAraujo.Coins.Coins;
import me.RafaelAulerDeMeloAraujo.Listeners.CustomHolder2;
import me.RafaelAulerDeMeloAraujo.SpecialAbility.API;
import me.RafaelAulerDeMeloAraujo.SpecialAbility.Join;

public class Shop
  implements Listener, CommandExecutor
{
  public static Inventory shop = Bukkit.getServer().createInventory(new CustomHolder2(), 54, Main.messages.getString("ShopInventoryName").replace("&", "§"));
  
  static
  {
    createButton(Material.DIAMOND_SWORD, shop, 0, "§6-> §cPvP", Main.messages.getString("ShopPriceLore").replace("&", "§") + " 4500");
    createButton(Material.BOW, shop, 1, "§6-> §cArcher", Main.messages.getString("ShopPriceLore").replace("&", "§") + " 3000");
    createButton(Material.FLINT_AND_STEEL, shop, 2, "§6-> §cPyro", Main.messages.getString("ShopPriceLore").replace("&", "§") + " 6000");
    createButton(Material.DIAMOND_CHESTPLATE, shop, 3, "§6-> §cTank", Main.messages.getString("ShopPriceLore").replace("&", "§") + " 7000");
    createButton(Material.SNOWBALL, shop, 4, "§6-> §cSwitcher", Main.messages.getString("ShopPriceLore").replace("&", "§") + " 6500");
    createButton(Material.MAGMA_CREAM, shop, 5, "§6-> §cJumper", Main.messages.getString("ShopPriceLore").replace("&", "§") + " 8000");
    createButton(Material.CACTUS, shop, 6, "§6-> §cCactus", Main.messages.getString("ShopPriceLore").replace("&", "§") + " 4000");
    createButton(Material.TNT, shop, 7, "§6-> §cBomber",  Main.messages.getString("ShopPriceLore").replace("&", "§") + " 9000");
    createButton(Material.ENDER_PEARL, shop, 8, "§6-> §cWarper", Main.messages.getString("ShopPriceLore").replace("&", "§") + " 4000");
    
    createButton(Material.FISHING_ROD, shop, 9, "§6-> §cFisherman", Main.messages.getString("ShopPriceLore").replace("&", "§") + " 5000");
    
    createButton(Material.IRON_BOOTS, shop, 10, "§6-> §cStomper", Main.messages.getString("ShopPriceLore").replace("&", "§") + " 17500");
    createButton(Material.REDSTONE_BLOCK, shop, 11, "§6-> §cDeshfire", Main.messages.getString("ShopPriceLore").replace("&", "§") + " 8500");
    createButton(Material.SPIDER_EYE, shop, 12, "§6-> §cViper", Main.messages.getString("ShopPriceLore").replace("&", "§") + " 7000");
    createButton(Material.STRING, shop, 13, "§6-> §cSpiderman", Main.messages.getString("ShopPriceLore").replace("&", "§") + " 6000");
    createButton(Material.IRON_BARS, shop, 14, "§6-> §cGladiator", Main.messages.getString("ShopPriceLore").replace("&", "§") + " 16000");
    createButton(Material.FIREWORK_ROCKET, shop, 15, "§6-> §cKangaroo", Main.messages.getString("ShopPriceLore").replace("&", "§") + " 5000");
    createButton(Material.COAL, shop, 16, "§6-> §cNinja", Main.messages.getString("ShopPriceLore").replace("&", "§") + " 6500");
    createButton(Material.CLOCK, shop, 17, "§6-> §cTimelord", Main.messages.getString("ShopPriceLore").replace("&", "§") + " 7000");
    createButton(Material.GOLDEN_APPLE, shop, 18, "§6-> §cCritical", Main.messages.getString("ShopPriceLore").replace("&", "§") + " 8000");
    createButton(Material.CHAINMAIL_BOOTS, shop, 19, "§6-> §cDoubleJump", Main.messages.getString("ShopPriceLore").replace("&", "§") + " 9500");
    createButton(Material.GOLDEN_AXE, shop, 20, "§6-> §cThor", Main.messages.getString("ShopPriceLore").replace("&", "§") + " 7000");
    createButton(Material.FERMENTED_SPIDER_EYE, shop, 21, "§6-> §cSnail", Main.messages.getString("ShopPriceLore").replace("&", "§") + " 6000");
    createButton(Material.BLAZE_ROD, shop, 22, "§6-> §cWasp", Main.messages.getString("ShopPriceLore").replace("&", "§") + " 6000");
    createButton(Material.NETHER_STAR, shop, 23, "§6-> §cNaruto", Main.messages.getString("ShopPriceLore").replace("&", "§") + " 12500");
    createButton(Material.FEATHER, shop, 24, "§6-> §cAirman", Main.messages.getString("ShopPriceLore").replace("&", "§") + " 9000");
    createButton(Material.GOLD_NUGGET, shop, 25, "§6-> §cVampire", Main.messages.getString("ShopPriceLore").replace("&", "§") + " 10000");
    createButton(Material.LAPIS_BLOCK, shop, 26, "§6-> §cSonic", Main.messages.getString("ShopPriceLore").replace("&", "§") + " 11000");
    createButton(Material.BOOK, shop, 27, "§6-> §cPhantom", Main.messages.getString("ShopPriceLore").replace("&", "§") + " 10000");
    createButton(Material.SAND, shop, 28, "§6-> §cCamel", Main.messages.getString("ShopPriceLore").replace("&", "§") + " 5000");
    createButton(Material.POTION, shop, 29, "§6-> §cConfuser", Main.messages.getString("ShopPriceLore").replace("&", "§") + " 5500");
    createButton(Material.IRON_AXE, shop, 30, "§6-> §cViking", Main.messages.getString("ShopPriceLore").replace("&", "§") + " 5500");
    createButton(Material.WATER_BUCKET, shop, 31, "§6-> §cPoseidon", Main.messages.getString("ShopPriceLore").replace("&", "§") + " 6000");
    createButton(Material.MUSHROOM_STEW, shop, 32, "§6-> §cResouper", Main.messages.getString("ShopPriceLore").replace("&", "§") + " 3000");
    createButton(Material.ANVIL, shop, 33, "§6-> §cAnchor", Main.messages.getString("ShopPriceLore").replace("&", "§") + " 8000");
    createButton(Material.BEACON, shop, 34, "§6-> §cRyu", Main.messages.getString("ShopPriceLore").replace("&", "§") + " 5000");
    createButton(Material.BLAZE_ROD, shop, 35, "§6-> §cMonk", Main.messages.getString("ShopPriceLore").replace("&", "§") + " 6000");
    createButton(Material.LAVA_BUCKET, shop, 36, "§6-> §cFireman", Main.messages.getString("ShopPriceLore").replace("&", "§") + " 6000");
    createButton(Material.SHIELD, shop, 37, "§6-> §cShielder", Main.messages.getString("ShopPriceLore").replace("&", "§") + " 8000");
    createButton(Material.MILK_BUCKET, shop, 38, "§6-> §cMilkMan", Main.messages.getString("ShopPriceLore").replace("&", "§") + " 8000");
    createButton(Material.NETHERITE_HELMET, shop, 39, "§6-> §cAntiStomper", Main.messages.getString("ShopPriceLore").replace("&", "§") + " 8000");
    createButton(Material.GHAST_TEAR, shop, 40, "§6-> §cGhast", Main.messages.getString("ShopPriceLore").replace("&", "§") + " 18000");
    createButton(Material.ARROW, shop, 41, "§6-> §cHedgeHog", Main.messages.getString("ShopPriceLore").replace("&", "§") + " 18000");
    createButton(Material.BARRIER, shop, 53, "§4§l-> §cClose", Main.messages.getString("CloseShopLore").replace("&", "§"));
  }
  
  public Shop(Main main)
  {
	ItemStack vidro = new ItemStack(Material.valueOf(Main.getInstace().getConfig().getString("SHOP-BACKGROUND-ITEM-MATERIAL")));
    ItemMeta vidro2 = vidro.getItemMeta();
    vidro2.setDisplayName(Main.messages.getString("ShopItemContents").replace("&", "§"));
    vidro.setItemMeta(vidro2);
    ItemStack[] arrayOfItemStack;
    int descpyro1 = (arrayOfItemStack = shop.getContents()).length;
    for (int metapyro1 = 0; metapyro1 < descpyro1; metapyro1++)
    {
      ItemStack item = arrayOfItemStack[metapyro1];
      if (item == null) {
        shop.setItem(shop.firstEmpty(), vidro);
      }
    }
  }
  
  private static void createButton(Material mat, Inventory inv, int Slot, String name, String lore)
  {
    ItemStack item = new ItemStack(mat);
    ItemMeta meta = item.getItemMeta();
    meta.setDisplayName(name);
    meta.setLore(Arrays.asList(new String[] { lore }));
    item.setItemMeta(meta);
    inv.setItem(Slot, item);
  }
  
  @EventHandler
  public void quickcommand(PlayerCommandPreprocessEvent e)
  {
    if (e.getMessage().equalsIgnoreCase("/kpshop"))
    {
      e.setCancelled(true);
      openMenu(e.getPlayer());
      if (!Join.game.contains(e.getPlayer().getName()))
      {
        e.getPlayer().sendMessage("§cYou must be in game to open shop menu!");
        e.getPlayer().closeInventory();
      }
    }
  }
  @EventHandler
  public void quickcommandy(PlayerCommandPreprocessEvent e)
  {
    if (e.getMessage().equalsIgnoreCase("/kp shop"))
    {
      e.setCancelled(true);
      openMenu(e.getPlayer());
      if (!Join.game.contains(e.getPlayer().getName()))
      {
        e.getPlayer().sendMessage("§cYou must be in game to open shop menu!");
        e.getPlayer().closeInventory();
      }
    }
  }
  
  @EventHandler
  public void quickcommand1(PlayerCommandPreprocessEvent e)
  {
    if (e.getMessage().equalsIgnoreCase("/kploja"))
    {
      e.setCancelled(true);
      openMenu(e.getPlayer());
      if (!Join.game.contains(e.getPlayer().getName()))
      {
        e.getPlayer().sendMessage("§cYou must be in game to open shop menu!");
        e.getPlayer().closeInventory();
      }
    }
  }
  
  @EventHandler
  public void quickcommand2(PlayerCommandPreprocessEvent e)
  {
    if (e.getMessage().equalsIgnoreCase("/shopmenu"))
    {
      e.setCancelled(true);
      openMenu(e.getPlayer());
      if (!Join.game.contains(e.getPlayer().getName()))
      {
        e.getPlayer().sendMessage("§cYou must be in game to open shop menu!");
        e.getPlayer().closeInventory();
      }
    }
  }
  
  @EventHandler
  public void quickcommand3(PlayerCommandPreprocessEvent e)
  {
    if (e.getMessage().equalsIgnoreCase("/kitpvp shop"))
    {
      e.setCancelled(true);
      openMenu(e.getPlayer());
      if (!Join.game.contains(e.getPlayer().getName()))
      {
        e.getPlayer().sendMessage("§cYou must be in game to open shop menu!");
        e.getPlayer().closeInventory();
      }
    }
  }
  
  public void openMenu(Player p)
  {
    p.openInventory(shop);
  }
  
  @EventHandler
  public void kit(InventoryClickEvent e)
  {
	  if (e.getClickedInventory() == null) {
  		return;
  	}
	  if (!(e.getInventory().getHolder() instanceof CustomHolder2)) {
		  	return;
		  }
      e.setCancelled(true);
    }
  
  
  @EventHandler
  public void warps(InventoryClickEvent e) {
  if (e.getClickedInventory() == null) {
		return;
	}
	  if (!(e.getClickedInventory().getHolder() instanceof CustomHolder2)) {
	  	return;
	  }
    Player p = (Player)e.getWhoClicked();
    ItemStack clicked = e.getCurrentItem();
    if (e.getCurrentItem() == null) {
      return;
    }
    if (e.getCurrentItem().getType() == Material.AIR) {
      return;
    }
    if ((p.hasPermission("kitpvp.kit.*") && (clicked.getType()) != Material.BARRIER) && !(clicked.getType() == Material.GREEN_STAINED_GLASS_PANE)) {
    	p.sendMessage("§e[KitPvP] §cYou already have all kits!");
    	p.closeInventory();
    	return;
    }
    if ((clicked.getType() == Material.BOW)) {
    	if (p.hasPermission("kitpvp.kit.archer")) {
    		p.sendMessage("§e[KitPvP] §cYou already have the Kit Archer!");
      		p.closeInventory();
      		return;
    	}
      if (Coins.getCoins(p) >= 3000)
      {
    	Coins.perms.playerAdd(p, "kitpvp.kit.archer");
        p.sendMessage("§6-> §cYou buy the Archer kit §e-3000 coins");
        Coins.removeCoins(p, 3000);
        e.setCancelled(true);
        p.closeInventory();
      }
      else if (Coins.getCoins(p) < 9000)
      {
        p.sendMessage(Main.messages.getString("NoFundsShop").replace("&", "§"));
        e.setCancelled(true);
        p.closeInventory();
      }
    }

    if ((clicked.getType() == Material.MILK_BUCKET)) {
        if (Coins.getCoins(p) >= 6000)
        {
      	  if (p.hasPermission("kitpvp.kit.milkman")) {
      		  p.sendMessage("§e[KitPvP] §cYou already have the Kit Milkman!");
          		p.closeInventory();
          		return;
        	}
      	
      	Coins.perms.playerAdd(p, "kitpvp.kit.milkman");
          p.sendMessage("§6-> §cYou buy the MilkMan kit §e-6000 coins");
          Coins.removeCoins(p, 6000);
          e.setCancelled(true);
          p.closeInventory();
        }
        else if (Coins.getCoins(p) < 6000)
        {
          p.sendMessage(Main.messages.getString("NoFundsShop").replace("&", "§"));
          e.setCancelled(true);
          p.closeInventory();
        }
      }
    if ((clicked.getType() == Material.FEATHER)) {
      if (Coins.getCoins(p) >= 9000)
      {
    	  if (p.hasPermission("kitpvp.kit.airman")) {
    		  p.sendMessage("§e[KitPvP] §cYou already have the Kit Airman!");
        		p.closeInventory();
        		return;
      	}
    	  if (Main.kits.getBoolean("AirmanDisabled")) {
      	     p.sendMessage(API.NomeServer + ChatColor.RED + "The Airman kit is disabled, sorry");
      	     return ;
          }
    	  Coins.perms.playerAdd(p, "kitpvp.kit.airman");
        p.sendMessage("§6-> §cYou buy the Airman kit §e-9000 coins");
        Coins.removeCoins(p, 9000);
        e.setCancelled(true);
        p.closeInventory();
      }
      else if (Coins.getCoins(p) < 9000)
      {
        p.sendMessage(Main.messages.getString("NoFundsShop").replace("&", "§"));
        e.setCancelled(true);
        p.closeInventory();
      }
    }
    if ((clicked.getType() == Material.MILK_BUCKET)) {
        if (Coins.getCoins(p) >= 8000)
        {
      	  if (p.hasPermission("kitpvp.kit.milkman")) {
      		  p.sendMessage("§e[KitPvP] §cYou already have the Kit MilkMan!");
          		p.closeInventory();
          		return;
        	}
      	  if (Main.kits.getBoolean("MilkManDisabled")) {
        	     p.sendMessage(API.NomeServer + ChatColor.RED + "The MilkMan kit is disabled, sorry");
        	     return ;
            }
      	  Coins.perms.playerAdd(p, "kitpvp.kit.milkman");
          p.sendMessage("§6-> §cYou buy the MilkMan kit §e-8000 coins");
          Coins.removeCoins(p, 8000);
          e.setCancelled(true);
          p.closeInventory();
        }
        else if (Coins.getCoins(p) < 8000)
        {
          p.sendMessage(Main.messages.getString("NoFundsShop").replace("&", "§"));
          e.setCancelled(true);
          p.closeInventory();
        }
      }
    if ((clicked.getType() == Material.SHIELD)) {
        if (Coins.getCoins(p) >= 8000)
        {
      	  if (p.hasPermission("kitpvp.kit.shielder")) {
      		  p.sendMessage("§e[KitPvP] §cYou already have the Kit Shielder!");
          		p.closeInventory();
          		return;
        	}
      	  Coins.perms.playerAdd(p, "kitpvp.kit.shielder");
          p.sendMessage("§6-> §cYou buy the Shielder kit §e-8000 coins");
          Coins.removeCoins(p, 8000);
          e.setCancelled(true);
          p.closeInventory();
        }
        else if (Coins.getCoins(p) < 8000)
        {
          p.sendMessage(Main.messages.getString("NoFundsShop").replace("&", "§"));
          e.setCancelled(true);
          p.closeInventory();
        }
      }
    if ((clicked.getType() == Material.NETHERITE_HELMET)) {
        if (Coins.getCoins(p) >= 8000)
        {
      	  if (p.hasPermission("kitpvp.kit.antistomper")) {
      		  p.sendMessage("§e[KitPvP] §cYou already have the Kit AntiStomper!");
          		p.closeInventory();
          		return;
        	}
      	  Coins.perms.playerAdd(p, "kitpvp.kit.antistomper");
          p.sendMessage("§6-> §cYou buy the AntiStomper kit §e-8000 coins");
          Coins.removeCoins(p, 8000);
          e.setCancelled(true);
          p.closeInventory();
        }
        else if (Coins.getCoins(p) < 8000)
        {
          p.sendMessage(Main.messages.getString("NoFundsShop").replace("&", "§"));
          e.setCancelled(true);
          p.closeInventory();
        }
      }
    if ((clicked.getType() == Material.BOOK)) {
        if (Coins.getCoins(p) >= 10000)
        {
      	  if (p.hasPermission("kitpvp.kit.phantom")) {
      		  p.sendMessage("§e[KitPvP] §cYou already have the Kit Phantom!");
          		p.closeInventory();
          		return;
        	}
      	 if (Main.kits.getBoolean("PhantomDisabled")) {
      	     p.sendMessage(API.NomeServer + ChatColor.RED + "The Phantom kit is disabled, sorry");
      	     return ;
          }
      	Coins.perms.playerAdd(p, "kitpvp.kit.phantom");
          p.sendMessage("§6-> §cYou buy the Phantom kit §e-9000 coins");
          Coins.removeCoins(p, 10000);
          e.setCancelled(true);
          p.closeInventory();
        }
        else if (Coins.getCoins(p) < 10000)
        {
          p.sendMessage(Main.messages.getString("NoFundsShop").replace("&", "§"));
          e.setCancelled(true);
          p.closeInventory();
          return;
        }
      }
    if ((clicked.getType() == Material.SAND)) {
        if (Coins.getCoins(p) >= 5000)
        {
      	  if (p.hasPermission("kitpvp.kit.camel")) {
      		  p.sendMessage("§e[KitPvP] §cYou already have the Kit Camel!");
          		p.closeInventory();
          		return;
        	}
      	Coins.perms.playerAdd(p, "kitpvp.kit.camel");
          p.sendMessage("§6-> §cYou buy the Camel kit §e-5000 coins");
          Coins.removeCoins(p, 5000);
          e.setCancelled(true);
          p.closeInventory();
        }
        else if (Coins.getCoins(p) < 5000)
        {
          p.sendMessage(Main.messages.getString("NoFundsShop").replace("&", "§"));
          e.setCancelled(true);
          p.closeInventory();
          return;
        }
      }
    if ((clicked.getType() == Material.NETHER_STAR)) {
      if (Coins.getCoins(p) >= 12500)
      {
    	  if (p.hasPermission("kitpvp.kit.naruto")) {
    		  p.sendMessage("§e[KitPvP] §cYou already have the Kit Naruto!");
        		p.closeInventory();
        		return;
      	}
    	  if (Main.kits.getBoolean("NarutoDisabled")) {
    			p.sendMessage(API.NomeServer + ChatColor.RED + "The Naruto kit is disabled, sorry");
    			return;
    		}
    	  Coins.perms.playerAdd(p, "kitpvp.kit.naruto");
        p.sendMessage("§6-> §cYou buy the Naruto kit §e-12500 coins");
        Coins.removeCoins(p, 12500);
        e.setCancelled(true);
        p.closeInventory();
      }
      else if (Coins.getCoins(p) < 12500)
      {
        p.sendMessage(Main.messages.getString("NoFundsShop").replace("&", "§"));
        e.setCancelled(true);
        p.closeInventory();
        return;
      }
    }
    if ((clicked.getType() == Material.MUSHROOM_STEW)) {
        if (Coins.getCoins(p) >= 3000)
        {
      	  if (p.hasPermission("kitpvp.kit.resouper")) {
      		  p.sendMessage("§e[KitPvP] §cYou already have the Kit Resouper!");
          		p.closeInventory();
          		return;
        	}
          if (Main.kits.getBoolean("ResouperDisabled")) {
        	  p.sendMessage(API.NomeServer + ChatColor.RED + "The Resouper kit is disabled, sorry");
        	  return;
        	  }
      	Coins.perms.playerAdd(p, "kitpvp.kit.resouper");
          p.sendMessage("§6-> §cYou buy the Resouper kit §e-3000 coins");
          Coins.removeCoins(p, 3000);
          e.setCancelled(true);
          p.closeInventory();
        }
        else if (Coins.getCoins(p) < 3000)
        {
          p.sendMessage(Main.messages.getString("NoFundsShop").replace("&", "§"));
          e.setCancelled(true);
          p.closeInventory();
          return;
        }
      }
    if ((clicked.getType() == Material.DIAMOND_SWORD)) {
    	if (Main.kits.getBoolean("PvPDisabled")) {
    		p.sendMessage(API.NomeServer + ChatColor.RED + "The PvP kit is disabled, sorry");
    		return;
    	}
      if (Coins.getCoins(p) >= 4500)
      {
    	  if (p.hasPermission("kitpvp.kit.pvp")) {
    		  p.sendMessage("§e[KitPvP] §cYou already have the Kit PvP!");
        		p.closeInventory();
        		return;
      	}
    	Coins.perms.playerAdd(p, "kitpvp.kit.pvp");
        p.sendMessage("§6-> §cYou buy the PvP kit §e-3500 coins");
        Coins.removeCoins(p, 4500);
        e.setCancelled(true);
        p.closeInventory();
      }
      else if (Coins.getCoins(p) < 4500)
      {
        p.sendMessage(Main.messages.getString("NoFundsShop").replace("&", "§"));
        e.setCancelled(true);
        p.closeInventory();
      }
    }
    if ((clicked.getType() == Material.GOLD_NUGGET)) {
      if (Coins.getCoins(p) >= 10000)
      {
    	  if (p.hasPermission("kitpvp.kit.vampire")) {
    		  p.sendMessage("§e[KitPvP] §cYou already have the Kit Vampire!");
        		p.closeInventory();
        		return;
      	}
    	  /*     */       if (Main.kits.getBoolean("VampireDisabled")) {
    			p.sendMessage(API.NomeServer + ChatColor.RED + "The Vampire kit is disabled, sorry");
    			return;
    		}
    	  Coins.perms.playerAdd(p, "kitpvp.kit.vampire");
        p.sendMessage("§6-> §cYou buy the Vampire kit §e-10000 coins");
        Coins.removeCoins(p, 10000);
        e.setCancelled(true);
        p.closeInventory();
      }
      else if (Coins.getCoins(p) < 10000)
      {
        p.sendMessage(Main.messages.getString("NoFundsShop").replace("&", "§"));
        e.setCancelled(true);
        p.closeInventory();
      }
    }
    if ((clicked.getType() == Material.COAL)) {
      if (Coins.getCoins(p) >= 6500)
      {
    	  if (p.hasPermission("kitpvp.kit.ninja")) {
    		  p.sendMessage("§e[KitPvP] §cYou already have the Kit Ninja!");
        		p.closeInventory();
        		return;
      	}
    	  /*     */       if (Main.kits.getBoolean("NinjaDisabled")) {
    			p.sendMessage(API.NomeServer + ChatColor.RED + "The Ninja kit is disabled, sorry");
    			return;
    		}
    	  Coins.perms.playerAdd(p, "kitpvp.kit.ninja");
        
        p.sendMessage("§6-> §cYou buy the Ninja kit §e-6500 coins");
        Coins.removeCoins(p, 6500);
        e.setCancelled(true);
        p.closeInventory();
      }
      else if (Coins.getCoins(p) < 6500)
      {
        p.sendMessage(Main.messages.getString("NoFundsShop").replace("&", "§"));
        e.setCancelled(true);
        p.closeInventory();
      }
    }
    if ((e.getCurrentItem().getItemMeta().getDisplayName().equals("§6-> § cMonk"))) {
      if (Coins.getCoins(p) >= 5000)
      {
    	  if (p.hasPermission("kitpvp.kit.monk")) {
    		  p.sendMessage("§e[KitPvP] §cYou already have the Kit Monk!");
        		p.closeInventory();
        		return;
      	}
    	  if (Main.kits.getBoolean("MonkDisabled")) {
    			p.sendMessage(API.NomeServer + ChatColor.RED + "The Monk kit is disabled, sorry");
    			return;
    		}
    	  Coins.perms.playerAdd(p, "kitpvp.kit.monk");
        p.sendMessage("§6-> §cYou buy the Monk kit §e-5000 coins");
        Coins.removeCoins(p, 5000);
        e.setCancelled(true);
        p.closeInventory();
      }
      else if (Coins.getCoins(p) < 5000)
      {
        p.sendMessage(Main.messages.getString("NoFundsShop").replace("&", "§"));
        e.setCancelled(true);
        p.closeInventory();
      }
    }
    if ((e.getCurrentItem().getItemMeta().getDisplayName().equals("§6-> §cWasp"))) {
      if (Coins.getCoins(p) >= 6000)
      {
    	  if (p.hasPermission("kitpvp.kit.wasp")) {
    		  p.sendMessage("§e[KitPvP] §cYou already have the Kit Wasp!");
        		p.closeInventory();
        		return;
      	}
    	  if (Main.kits.getBoolean("WaspDisabled")) {
    			p.sendMessage(ChatColor.RED + "The wasp kit is disabled, sorry");
    			return;
    		}
    	  Coins.perms.playerAdd(p, "kitpvp.kit.wasp");
        p.sendMessage("§6-> §cYou buy the Wasp kit §e-6000 coins");
        Coins.removeCoins(p, 6000);
        e.setCancelled(true);
        p.closeInventory();
      }
      else if (Coins.getCoins(p) < 6000)
      {
        p.sendMessage(Main.messages.getString("NoFundsShop").replace("&", "§"));
        e.setCancelled(true);
        p.closeInventory();
      }
    }
    if ((clicked.getType() == Material.IRON_BARS)) {
      if (Coins.getCoins(p) >= 16000)
      {
    	  if (p.hasPermission("kitpvp.kit.gladiator")) {
    		  p.sendMessage("§e[KitPvP] §cYou already have the Kit Gladiator!");
        		p.closeInventory();
        		return;
      	}
    	  if (Main.kits.getBoolean("GladiatorDisabled")) {
       	     p.sendMessage(API.NomeServer + ChatColor.RED + "The Gladiator kit is disabled, sorry");
       	     return ;
           }
    	  Coins.perms.playerAdd(p, "kitpvp.kit.gladiator");
        p.sendMessage("§6-> §cYou buy the Gladiator kit §e-16000 coins");
        Coins.removeCoins(p, 16000);
        e.setCancelled(true);
        p.closeInventory();
      }
      else if (Coins.getCoins(p) < 16000)
      {
        p.sendMessage(Main.messages.getString("NoFundsShop").replace("&", "§"));
        e.setCancelled(true);
        p.closeInventory();
      }
    }
    if ((clicked.getType() == Material.LAPIS_BLOCK)) {
        if (Coins.getCoins(p) >= 11000)
        {
        	if (p.hasPermission("kitpvp.kit.sonic")) {
        		p.sendMessage("§e[KitPvP] §cYou already have the Kit Sonic!");
          		p.closeInventory();
          		return;
        	}
        	if (Main.kits.getBoolean("SonicDisabled")) {
        		p.sendMessage(API.NomeServer + ChatColor.RED + "The Sonic kit is disabled, sorry");
        		return;
        	}
        	Coins.perms.playerAdd(p, "kitpvp.kit.sonic");
          p.sendMessage("§6-> §cYou buy the Sonic kit §e-11000 coins");
          Coins.removeCoins(p, 11000);
          e.setCancelled(true);
          p.closeInventory();
        }
        else if (Coins.getCoins(p) < 11000)
        {
          p.sendMessage(Main.messages.getString("NoFundsShop").replace("&", "§"));
          e.setCancelled(true);
          p.closeInventory();
        }
      }
    if ((clicked.getType() == Material.LAVA_BUCKET)) {
        if (Coins.getCoins(p) >= 6000)
        {
      	  if (p.hasPermission("kitpvp.kit.fireman")) {
      		  p.sendMessage("§e[KitPvP] §cYou already have the Kit Fireman!");
          		p.closeInventory();
          		return;
        	}
      	if (Main.kits.getBoolean("FiremanDisabled")) {
      		p.sendMessage(API.NomeServer + ChatColor.RED + "The Fireman kit is disabled, sorry");
      		return;
      	}
      	Coins.perms.playerAdd(p, "kitpvp.kit.fireman");
          p.sendMessage("§6-> §cYou buy the Fireman kit §e-6000 coins");
          Coins.removeCoins(p, 6000);
          e.setCancelled(true);
          p.closeInventory();
        }
        else if (Coins.getCoins(p) < 6000)
        {
          p.sendMessage(Main.messages.getString("NoFundsShop").replace("&", "§"));
          e.setCancelled(true);
          p.closeInventory();
        }
      }
    if ((clicked.getType() == Material.GHAST_TEAR)) {
        if (Coins.getCoins(p) >= 18000)
        {
      	  if (p.hasPermission("kitpvp.kit.ghast")) {
      		  p.sendMessage("§e[KitPvP] §cYou already have the Kit Ghast!");
          		p.closeInventory();
          		return;
        	}
      	if (Main.kits.getBoolean("GhastDisabled")) {
      		p.sendMessage(API.NomeServer + ChatColor.RED + "The Ghast kit is disabled, sorry");
      		return;
      	}
      	Coins.perms.playerAdd(p, "kitpvp.kit.ghast");
          p.sendMessage("§6-> §cYou buy the Ghast kit §e-18000 coins");
          Coins.removeCoins(p, 18000);
          e.setCancelled(true);
          p.closeInventory();
        }
        else if (Coins.getCoins(p) < 18000)
        {
          p.sendMessage(Main.messages.getString("NoFundsShop").replace("&", "§"));
          e.setCancelled(true);
          p.closeInventory();
        }
      }
    if ((clicked.getType() == Material.ARROW)) {
        if (Coins.getCoins(p) >= 18000)
        {
      	  if (p.hasPermission("kitpvp.kit.hedgehog")) {
      		  p.sendMessage("§e[KitPvP] §cYou already have the Kit HedgeHog!");
          		p.closeInventory();
          		return;
        	}
      	if (Main.kits.getBoolean("HedgehogDisabled")) {
      		p.sendMessage(API.NomeServer + ChatColor.RED + "The HedgeHog kit is disabled, sorry");
      		return;
      	}
      	Coins.perms.playerAdd(p, "kitpvp.kit.hedgehog");
          p.sendMessage("§6-> §cYou buy the Hedgehog kit §e-18000 coins");
          Coins.removeCoins(p, 18000);
          e.setCancelled(true);
          p.closeInventory();
        }
        else if (Coins.getCoins(p) < 18000)
        {
          p.sendMessage(Main.messages.getString("NoFundsShop").replace("&", "§"));
          e.setCancelled(true);
          p.closeInventory();
        }
      }
    if (((clicked.getType() == Material.BEACON))) {
        if (Coins.getCoins(p) >= 5000)
        {
      	  if (p.hasPermission("kitpvp.kit.ryu")) {
      		  p.sendMessage("§e[KitPvP] §cYou already have the Kit Ryu!");
          		p.closeInventory();
          		return;
        	}
      	if (Main.kits.getBoolean("RyuDisabled")) {
			p.sendMessage(API.NomeServer + ChatColor.RED + "The Ryu kit is disabled, sorry");
			return;
		}
      	Coins.perms.playerAdd(p, "kitpvp.kit.ryu");
          p.sendMessage("§6-> §cYou buy the Ryu kit §e-5000 coins");
          Coins.removeCoins(p, 5000);
          e.setCancelled(true);
          p.closeInventory();
        }
        else if (Coins.getCoins(p) < 5000)
        {
          p.sendMessage(Main.messages.getString("NoFundsShop").replace("&", "§"));
          e.setCancelled(true);
          p.closeInventory();
        }
      }
    if ((clicked.getType() == Material.FIREWORK_ROCKET)) {
      if (Coins.getCoins(p) >= 5000)
      {
    	  if (p.hasPermission("kitpvp.kit.kangaroo")) {
    		  p.sendMessage("§e[KitPvP] §cYou already have the Kit Kangaroo!");
        		p.closeInventory();
        		return;
      	}
    	  if (Main.kits.getBoolean("KangarooDisabled")) {
    			p.sendMessage(API.NomeServer + ChatColor.RED + "The Kangaroo kit is disabled, sorry");
    			return;
    		}
    	  Coins.perms.playerAdd(p, "kitpvp.kit.kangaroo");
        p.sendMessage("§6-> §cYou buy the Kangaroo kit §e-5000 coins");
        Coins.removeCoins(p, 5000);
        e.setCancelled(true);
        p.closeInventory();
      }
      else if (Coins.getCoins(p) < 5000)
      {
        p.sendMessage(Main.messages.getString("NoFundsShop").replace("&", "§"));
        e.setCancelled(true);
        p.closeInventory();
      }
    }
    if ((clicked.getType() == Material.STRING)) {
      if (Coins.getCoins(p) >= 6000)
      {
    	  if (p.hasPermission("kitpvp.kit.spiderman")) {
    		  p.sendMessage("§e[KitPvP] §cYou already have the Kit Spiderman!");
        		p.closeInventory();
        		return;
      	}
    	  if (Main.kits.getBoolean("SpidermanDisabled")) {
       	     p.sendMessage(API.NomeServer + ChatColor.RED + "The Spiderman kit is disabled, sorry");
       	     return ;
           }
    	  Coins.perms.playerAdd(p, "kitpvp.kit.spiderman");
        p.sendMessage("§6-> §cYou buy the PvP kit §e-6000 coins");
        Coins.removeCoins(p, 6000);
        e.setCancelled(true);
        p.closeInventory();
      }
      else if (Coins.getCoins(p) < 6000)
      {
        p.sendMessage(Main.messages.getString("NoFundsShop").replace("&", "§"));
        e.setCancelled(true);
        p.closeInventory();
      }
    }
    if ((clicked.getType() == Material.SPIDER_EYE)) {
      if (Coins.getCoins(p) >= 7000)
      {
    	  if (p.hasPermission("kitpvp.kit.viper")) {
    		  p.sendMessage("§e[KitPvP] §cYou already have the Kit Viper!");
        		p.closeInventory();
        		return;
      	}
    	  if (Main.kits.getBoolean("ViperDisabled")) {
    		  p.sendMessage("§e[KitPvP] §cThe Viper kit is disabled, sorry!");
      		p.closeInventory();
      		return; 
    	  }
    	  Coins.perms.playerAdd(p, "kitpvp.kit.viper");
        p.sendMessage("§6-> §cYou buy the Viper kit §e-7000 coins");
        Coins.removeCoins(p, 7000);
        e.setCancelled(true);
        p.closeInventory();
      }
      else if (Coins.getCoins(p) < 7000)
      {
        p.sendMessage(Main.messages.getString("NoFundsShop").replace("&", "§"));
        e.setCancelled(true);
        p.closeInventory();
      }
    }
    if ((clicked.getType() == Material.GOLDEN_AXE)) {
      if (Coins.getCoins(p) >= 7000)
      {
    	  if (p.hasPermission("kitpvp.kit.thor")) {
    		  p.sendMessage("§e[KitPvP] §cYou already have the Kit Thor!");
        		p.closeInventory();
        		return;
      	}
    	  Coins.perms.playerAdd(p, "kitpvp.kit.thor");
        p.sendMessage("§6-> §cYou buy the Thor kit §e-7000 coins");
        Coins.removeCoins(p, 7000);
        e.setCancelled(true);
        p.closeInventory();
      }
      else if (Coins.getCoins(p) < 7000)
      {
        p.sendMessage(Main.messages.getString("NoFundsShop").replace("&", "§"));
        e.setCancelled(true);
        p.closeInventory();
      }
    }
    if ((clicked.getType() == Material.BARRIER))
    {
      p.closeInventory();
      p.sendMessage(Main.messages.getString("ShopMenuClosed").replace("&", "§"));
    }
    if ((clicked.getType() == Material.REDSTONE_BLOCK)) {
      if (Coins.getCoins(p) >= 8500)
      {
    	  if (p.hasPermission("kitpvp.kit.deshfire")) {
    		  p.sendMessage("§e[KitPvP] §cYou already have the Kit Deshfire!");
        		p.closeInventory();
        		return;
      	}
    	  if (Main.kits.getBoolean("DeshfireDisabled")) {
    			p.sendMessage(API.NomeServer + ChatColor.RED + "The Deshfire kit is disabled, sorry");
    			return;
    		}
    	  Coins.perms.playerAdd(p, "kitpvp.kit.deshfire");
        
        p.sendMessage("§6-> §cYou buy the Deshfire kit §e-8500 coins");
        Coins.removeCoins(p, 8500);
        e.setCancelled(true);
        p.closeInventory();
      }
      else if (Coins.getCoins(p) < 8500)
      {
        p.sendMessage(Main.messages.getString("NoFundsShop").replace("&", "§"));
        e.setCancelled(true);
        p.closeInventory();
      }
    }
    if ((clicked.getType() == null))
    {
      e.setCancelled(true);
      p.closeInventory();
      
      return;
    }
    if ((clicked.getType() == Material.FLINT_AND_STEEL)) {
      if (Coins.getCoins(p) >= 6000)
      {
    	  if (p.hasPermission("kitpvp.kit.pyro")) {
    		  p.sendMessage("§e[KitPvP] §cYou already have the Kit Pyro!");
        		p.closeInventory();
        		return;
      	}
    	  Coins.perms.playerAdd(p, "kitpvp.kit.pyro");
        p.sendMessage("§6-> §cYou buy the Pyro kit §e-6000 coins");
        Coins.removeCoins(p, 6000);
        e.setCancelled(true);
        p.closeInventory();
      }
      else if (Coins.getCoins(p) < 6000)
      {
        p.sendMessage(Main.messages.getString("NoFundsShop").replace("&", "§"));
        e.setCancelled(true);
        p.closeInventory();
      }
    }
    if ((clicked.getType() == Material.FERMENTED_SPIDER_EYE)) {
      if (Coins.getCoins(p) >= 6000)
      {
    	  if (p.hasPermission("kitpvp.kit.snail")) {
    		  p.sendMessage("§e[KitPvP] §cYou already have the Kit Snail!");
        		p.closeInventory();
        		return;
      	}
    	  Coins.perms.playerAdd(p, "kitpvp.kit.snail");
        p.sendMessage("§6-> §cYou buy the Snail kit §e-6000 coins");
        Coins.removeCoins(p, 6000);
        e.setCancelled(true);
        p.closeInventory();
      }
      else if (Coins.getCoins(p) < 6000)
      {
        p.sendMessage(Main.messages.getString("NoFundsShop").replace("&", "§"));
        e.setCancelled(true);
        p.closeInventory();
      }
    }
    if ((clicked.getType() == Material.WATER_BUCKET)) {
        if (Coins.getCoins(p) >= 6000)
        {
      	  if (p.hasPermission("kitpvp.kit.poseidon")) {
      		  p.sendMessage("§e[KitPvP] §cYou already have the Kit Poseidon!");
          		p.closeInventory();
          		return;
        	}
      	  if (Main.kits.getBoolean("PoseidonDisabled")) {
  			p.sendMessage(API.NomeServer + ChatColor.RED + "The Poseidon kit is disabled, sorry");
  			return;
  		}
      	Coins.perms.playerAdd(p, "kitpvp.kit.poseidon");
          p.sendMessage("§6-> §cYou buy the Poseidon kit §e-6000 coins");
          Coins.removeCoins(p, 6000);
          e.setCancelled(true);
          p.closeInventory();
        }
        else if (Coins.getCoins(p) < 6000)
        {
          p.sendMessage(Main.messages.getString("NoFundsShop").replace("&", "§"));
          e.setCancelled(true);
          p.closeInventory();
        }
      }
    if ((clicked.getType() == Material.IRON_AXE)) {
        if (Coins.getCoins(p) >= 5500)
        {
      	  if (p.hasPermission("kitpvp.kit.viking")) {
      		  p.sendMessage("§e[KitPvP] §cYou already have the Kit Viking!");
          		p.closeInventory();
          		return;
        	}
      	Coins.perms.playerAdd(p, "kitpvp.kit.viking");
          p.sendMessage("§6-> §cYou buy the Poseidon kit §e-5500 coins");
          Coins.removeCoins(p, 5500);
          e.setCancelled(true);
          p.closeInventory();
        }
        else if (Coins.getCoins(p) < 5500)
        {
          p.sendMessage(Main.messages.getString("NoFundsShop").replace("&", "§"));
          e.setCancelled(true);
          p.closeInventory();
        }
      }
    if ((clicked.getType() == Material.POTION)) {
        if (Coins.getCoins(p) >= 5500)
        {
      	  if (p.hasPermission("kitpvp.kit.confuser")) {
      		  p.sendMessage("§e[KitPvP] §cYou already have the Kit Confuser!");
          		p.closeInventory();
          		return;
        	}
      	if (Main.kits.getBoolean("ConfuserDisabled")) {
      		p.sendMessage(API.NomeServer + ChatColor.RED + "The Confuser kit is disabled, sorry");
      		return;
      	}
      	Coins.perms.playerAdd(p, "kitpvp.kit.confuser");
          p.sendMessage("§6-> §cYou buy the Confuser kit §e-5500 coins");
          Coins.removeCoins(p, 5500);
          e.setCancelled(true);
          p.closeInventory();
        }
        else if (Coins.getCoins(p) < 5500)
        {
          p.sendMessage(Main.messages.getString("NoFundsShop").replace("&", "§"));
          e.setCancelled(true);
          p.closeInventory();
        }
      }
    if ((clicked.getType() == Material.DIAMOND_CHESTPLATE)) {
      if (Coins.getCoins(p) >= 7000)
      {
    	  if (p.hasPermission("kitpvp.kit.tank")) {
    		  p.sendMessage("§e[KitPvP] §cYou already have the Kit Tank!");
        		p.closeInventory();
        		return;
      	}
    	  Coins.perms.playerAdd(p, "kitpvp.kit.tank");
        p.sendMessage("§6-> §cYou buy the Tank kit §e-7000 coins");
        Coins.removeCoins(p, 7000);
        e.setCancelled(true);
        p.closeInventory();
      }
      else if (Coins.getCoins(p) < 7000)
      {
        p.sendMessage(Main.messages.getString("NoFundsShop").replace("&", "§"));
        e.setCancelled(true);
        p.closeInventory();
      }
    }
    if ((clicked.getType() == Material.CLOCK)) {
      if (Coins.getCoins(p) >= 7000)
      {
    	  if (p.hasPermission("kitpvp.kit.timelord")) {
    		  p.sendMessage("§e[KitPvP] §cYou already have the Kit Timelord!");
        		p.closeInventory();
        		return;
      	}
    	  if (Main.kits.getBoolean("TimelordDisabled")) {
  			p.sendMessage(API.NomeServer + ChatColor.RED + "The Timelord kit is disabled, sorry");
  			return;
  		}
    	Coins.perms.playerAdd(p, "kitpvp.kit.timelord");
        p.sendMessage("§6-> §cYou buy the Timelord kit §e-7000 coins");
        Coins.removeCoins(p, 7000);
        e.setCancelled(true);
        p.closeInventory();
      }
      else if (Coins.getCoins(p) < 7000)
      {
        p.sendMessage(Main.messages.getString("NoFundsShop").replace("&", "§"));
        e.setCancelled(true);
        p.closeInventory();
      }
    }
    if ((clicked.getType() == Material.ANVIL)) {
        if (Coins.getCoins(p) >= 8000)
        {
      	  if (p.hasPermission("kitpvp.kit.anchor")) {
      		  p.sendMessage("§e[KitPvP] §cYou already have the Kit Anchor!");
          		p.closeInventory();
          		return;
        	}
      	 if (Main.kits.getBoolean("AnchorDisabled")) {
      	     p.sendMessage(API.NomeServer + ChatColor.RED + "The Anchor kit is disabled, sorry");
      	     return ;
          }
      	Coins.perms.playerAdd(p, "kitpvp.kit.anchor");
          p.sendMessage("§6-> §cYou buy the Anchor kit §e-8000 coins");
          Coins.removeCoins(p, 8000);
          e.setCancelled(true);
          p.closeInventory();
        }
        else if (Coins.getCoins(p) < 8000)
        {
          p.sendMessage(Main.messages.getString("NoFundsShop").replace("&", "§"));
          e.setCancelled(true);
          p.closeInventory();
        }
      }
    if ((clicked.getType() == Material.SNOWBALL)) {
      if (Coins.getCoins(p) >= 6000)
      {
    	  if (p.hasPermission("kitpvp.kit.switcher")) {
    		  p.sendMessage("§e[KitPvP] §cYou already have the Kit Switcher!");
        		p.closeInventory();
        		return;
      	}
    	  if (Main.kits.getBoolean("SwitcherDisabled")) {
  			p.sendMessage(API.NomeServer + ChatColor.RED + "The Switcher kit is disabled, sorry");
  			return;
  		}
    	Coins.perms.playerAdd(p, "kitpvp.kit.switcher");
        p.sendMessage("§6-> §cYou buy the Switcher kit §e-6000 coins");
        Coins.removeCoins(p, 6000);
        e.setCancelled(true);
        p.closeInventory();
      }
      else if (Coins.getCoins(p) < 6000)
      {
        p.sendMessage(Main.messages.getString("NoFundsShop").replace("&", "§"));
        e.setCancelled(true);
        p.closeInventory();
      }
    }
    if ((clicked.getType() == Material.MAGMA_CREAM)) {
      if (Coins.getCoins(p) >= 8000)
      {
    	  if (p.hasPermission("kitpvp.kit.jumper")) {
    		  p.sendMessage("§e[KitPvP] §cYou already have the Kit Jumper!");
        		p.closeInventory();
        		return;
      	}
    	Coins.perms.playerAdd(p, "kitpvp.kit.jumper");
        p.sendMessage("§6-> §cYou buy the JUMPER kit §e-8000 coins");
        Coins.removeCoins(p, 8000);
        e.setCancelled(true);
        p.closeInventory();
      }
      else if (Coins.getCoins(p) < 8000)
      {
        p.sendMessage(Main.messages.getString("NoFundsShop").replace("&", "§"));
        e.setCancelled(true);
        p.closeInventory();
      }
    }
    if ((clicked.getType() == Material.CACTUS)) {
      if (Coins.getCoins(p) >= 4000)
      {
    	  if (p.hasPermission("kitpvp.kit.cactus")) {
    		  p.sendMessage("§e[KitPvP] §cYou already have the Kit Cactus!");
        		p.closeInventory();
        		return;
      	}
    	Coins.perms.playerAdd(p, "kitpvp.kit.cactus");
        p.sendMessage("§6-> §cYou buy the Cactus kit §e-4000 coins");
        Coins.removeCoins(p, 4000);
        e.setCancelled(true);
        p.closeInventory();
      }
      else if (Coins.getCoins(p) < 4000)
      {
        p.sendMessage(Main.messages.getString("NoFundsShop").replace("&", "§"));
        e.setCancelled(true);
        p.closeInventory();
      }
    }
    if ((clicked.getType() == Material.TNT)) {
      if (Coins.getCoins(p) >= 9000)
      {
    	  if (p.hasPermission("kitpvp.kit.bomber")) {
    		  p.sendMessage("§e[KitPvP] §cYou already have the Kit Bomber!");
        		p.closeInventory();
        		return;
      	}
    	  if (Main.kits.getBoolean("BomberDisabled")) {
       	     p.sendMessage(API.NomeServer + ChatColor.RED + "The Bomber kit is disabled, sorry");
       	     return ;
           }
    	  Coins.perms.playerAdd(p, "kitpvp.kit.bomber");
        p.sendMessage("§6-> §cYou buy the Bomber kit §e-9000 coins");
        Coins.removeCoins(p, 9000);
        e.setCancelled(true);
        p.closeInventory();
      }
      else if (Coins.getCoins(p) < 9000)
      {
        p.sendMessage(Main.messages.getString("NoFundsShop").replace("&", "§"));
        e.setCancelled(true);
        p.closeInventory();
      }
    }
    if ((clicked.getType() == Material.ENDER_PEARL)) {
      if (Coins.getCoins(p) >= 4000)
      {
    	  if (p.hasPermission("kitpvp.kit.warper")) {
    		  p.sendMessage("§e[KitPvP] §cYou already have the Kit Warper!");
        		p.closeInventory();
      	}
    	  if (Main.kits.getBoolean("WarperDisabled")) {
      	     p.sendMessage(API.NomeServer + ChatColor.RED + "The Warper kit is disabled, sorry");
      	     return ;
          }
    	  Coins.perms.playerAdd(p, "kitpvp.kit.warper");
        p.sendMessage("§6-> §cYou buy the Warper kit §e-4000 coins");
        Coins.removeCoins(p, 4000);
        e.setCancelled(true);
        p.closeInventory();
      }
      else if (Coins.getCoins(p) < 4000)
      {
        p.sendMessage(Main.messages.getString("NoFundsShop").replace("&", "§"));
        e.setCancelled(true);
        p.closeInventory();
      }
    }
    if ((clicked.getType() == Material.GOLDEN_APPLE)) {
      if (Coins.getCoins(p) >= 8000)
      {
    	  if (p.hasPermission("kitpvp.kit.critical")) {
    		  p.sendMessage("§e[KitPvP] §cYou already have the Kit Critical!");
        		p.closeInventory();
        		return;
      	}
    	  if (Main.kits.getBoolean("CriticalDisabled")) {
    			p.sendMessage(API.NomeServer + ChatColor.RED + "The Critical kit is disabled, sorry");
    			return;
    		}
    	  Coins.perms.playerAdd(p, "kitpvp.kit.critical");
        p.sendMessage("§6-> §cYou buy the Critical kit §e-8000 coins");
        Coins.removeCoins(p, 8000);
        e.setCancelled(true);
        p.closeInventory();
      }
      else if (Coins.getCoins(p) < 8000)
      {
        p.sendMessage(Main.messages.getString("NoFundsShop").replace("&", "§"));
        e.setCancelled(true);
        p.closeInventory();
      }
    }
    if ((clicked.getType() == Material.FISHING_ROD)) {
      if (Coins.getCoins(p) >= 5000)
      {
    	  if (p.hasPermission("kitpvp.kit.fisherman")) {
    		  p.sendMessage("§e[KitPvP] §cYou already have the Kit Fisherman!");
        		p.closeInventory();
        		return;
      	}
    	  Coins.perms.playerAdd(p, "kitpvp.kit.fisherman");
        p.sendMessage("§6-> §cYou buy the Fisherman kit §e-5000 coins");
        Coins.removeCoins(p, 5000);
        e.setCancelled(true);
        p.closeInventory();
      }
      else if (Coins.getCoins(p) < 5000)
      {
        p.sendMessage(Main.messages.getString("NoFundsShop").replace("&", "§"));
        e.setCancelled(true);
        p.closeInventory();
      }
    }
    if ((clicked.getType() == Material.ICE)) {
      if (Coins.getCoins(p) >= 6000)
      {
    	  if (p.hasPermission("kitpvp.kit.freezer")) {
      		p.sendMessage("§e[KitPvP] §cYou already have this kit!");
      	}
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "permissions player " + p.getName() + " set kitpvp.kit.freezer");
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "upc " + p.getName() + " addplayerpermission  kitpvp.kit.freezer local");
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "upc " + p.getName() + " addplayerpermission  kitpvp.kit.freezer global");
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + p.getName() + " add kitpvp.kit.freezer");
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + p.getName() + " permission set kitpvp.kit.freezer true");
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "manuaddp " + p.getName() + " kitpvp.kit.freezer");
        p.sendMessage("§6-> § cYou buy the Freezer kit §e-5000 coins");
        Coins.removeCoins(p, 6000);
        e.setCancelled(true);
        p.closeInventory();
      }
      else if (Coins.getCoins(p) < 6000)
      {
        p.sendMessage(Main.messages.getString("NoFundsShop").replace("&", "§"));
        e.setCancelled(true);
        p.closeInventory();
      }
    }
    if ((clicked.getType() == Material.CHAINMAIL_BOOTS)) {
      if (Coins.getCoins(p) >= 9500)
      {
    	  if (p.hasPermission("kitpvp.kit.doublejump")) {
    		  p.sendMessage("§e[KitPvP] §cYou already have the Kit DoubleJump!");
        		p.closeInventory();
        		return;
      	}
    	  if (Main.kits.getBoolean("DoubleJumpDisabled")) {
       	     p.sendMessage(API.NomeServer + ChatColor.RED + "The DoubleJump kit is disabled, sorry");
       	     return ;
           }
    	  Coins.perms.playerAdd(p, "kitpvp.kit.doublejump");
        p.sendMessage("§6-> § cYou buy the DoubleJump kit e-9500 coins");
        Coins.removeCoins(p, 9500);
        e.setCancelled(true);
        p.closeInventory();
      }
      else if (Coins.getCoins(p) < 9500)
      {
        p.sendMessage(Main.messages.getString("NoFundsShop").replace("&", "§"));
        e.setCancelled(true);
        p.closeInventory();
      }
    }
    if ((clicked.getType() == Material.IRON_BOOTS)) {
      if (Coins.getCoins(p) >= 17500)
      {
    	  if (p.hasPermission("kitpvp.kit.stomper")) {
      		p.sendMessage("§e[KitPvP] §cYou already have the Kit Stomper!");
      		p.closeInventory();
      		return;
      	}
    	  if (Main.kits.getBoolean("StomperDisabled")) {
       	     p.sendMessage(API.NomeServer + ChatColor.RED + "The Stomper kit is disabled, sorry");
       	     return ;
           }
    	  Coins.perms.playerAdd(p, "kitpvp.kit.stomper");
        p.sendMessage(Main.messages.getString("NoFundsShop").replace("&", "§"));
        Coins.removeCoins(p, 17500);
        e.setCancelled(true);
        p.closeInventory();
      }
      else if (Coins.getCoins(p) < 17500)
      {
        p.sendMessage(Main.messages.getString("NoFundsShop").replace("&", "§"));
        e.setCancelled(true);
        p.closeInventory();
      }
    }
  }
  
  

public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3)
  {
    return false;
  }
}
