package me.RafaelAulerDeMeloAraujo.main;


import org.bukkit.Bukkit;
import org.bukkit.Material;
/*    */ 
/*    */ 
/*    */ import org.bukkit.command.Command;
/*    */ import org.bukkit.command.CommandExecutor;
/*    */ import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.RafaelAulerDeMeloAraujo.SpecialAbility.Join;

/*    */ 
/*    */ 
/*    */ public class KPSoup
/*    */   implements CommandExecutor
/*    */ {
/*    */   
/*    */   static Main plugin;
/*    */   
/*    */   public KPSoup(Main main)
/*    */   {
/* 20 */     plugin = main;
/*    */   }
/*    */   
/*    */   public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
/*    */   {
/* 25 */     
/* 26 */     if (label.equalsIgnoreCase("kpsoups")) {
	if (!Join.game.contains(sender.getName())) {
		sender.sendMessage("You must be in game!");
		return true;
	}
	final Player p = (Player)sender;
    final ItemStack sopas = new ItemStack(Material.MUSHROOM_STEW);
    final ItemMeta sopasm = sopas.getItemMeta();
    sopasm.setDisplayName("§6Soup");
    sopas.setItemMeta(sopasm);
    final Inventory inve = Bukkit.getServer().createInventory((InventoryHolder)p, 36, "§bFree Soups!");
    inve.setItem(0, sopas);
    inve.setItem(1, sopas);
    inve.setItem(2, sopas);
    inve.setItem(3, sopas);
    inve.setItem(4, sopas);
    inve.setItem(5, sopas);
    inve.setItem(6, sopas);
    inve.setItem(7, sopas);
    inve.setItem(8, sopas);
    inve.setItem(9, sopas);
    inve.setItem(10, sopas);
    inve.setItem(11, sopas);
    inve.setItem(12, sopas);
    inve.setItem(13, sopas);
    inve.setItem(14, sopas);
    inve.setItem(15, sopas);
    inve.setItem(16, sopas);
    inve.setItem(17, sopas);
    inve.setItem(18, sopas);
    inve.setItem(19, sopas);
    inve.setItem(20, sopas);
    inve.setItem(21, sopas);
    inve.setItem(22, sopas);
    inve.setItem(23, sopas);
    inve.setItem(24, sopas);
    inve.setItem(25, sopas);
    inve.setItem(26, sopas);
    inve.setItem(27, sopas);
    inve.setItem(28, sopas);
    inve.setItem(29, sopas);
    inve.setItem(30, sopas);
    inve.setItem(31, sopas);
    inve.setItem(32, sopas);
    inve.setItem(33, sopas);
    inve.setItem(34, sopas);
    inve.setItem(35, sopas);
    p.openInventory(inve);
/* 37 */       
/*    */     }
/* 39 */     return false;
/*    */   }
/*    */ }

