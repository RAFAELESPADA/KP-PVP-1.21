/*    */ package me.RafaelAulerDeMeloAraujo.main;
/*    */ 
/*    */ import java.util.HashMap;

/*    */ import org.bukkit.command.Command;
/*    */ import org.bukkit.command.CommandExecutor;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */

import me.RafaelAulerDeMeloAraujo.SpecialAbility.API;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Skit
/*    */   implements Listener, CommandExecutor
/*    */ {
/*    */   
/* 22 */   public HashMap<String, ItemStack[]> kit = new HashMap<String, ItemStack[]>();
/*    */   
/* 24 */   public HashMap<String, ItemStack[]> armadura = new HashMap<String, ItemStack[]>();
/*    */   
/*    */   public boolean isInt(String s)
/*    */   {
/*    */     try
/*    */     {
/* 30 */       Integer.parseInt(s);
/* 31 */       return true;
/*    */     }
/*    */     catch (NumberFormatException localNumberFormatException) {}
/* 34 */     return false;
/*    */   }
/*    */   
/*    */   public boolean onCommand(CommandSender sender, Command cmd, String CommandLabel, String[] args) {
/* 38 */     Player p = (Player)sender;
/* 39 */     if ((cmd.getName().equalsIgnoreCase("skit")) && 
/* 40 */       (p.hasPermission("kitpvp.skit")))
/*    */     {
/* 42 */       if (args.length == 0) {
/* 43 */         p.sendMessage(API.NomeServer + "" + "§7> §cUse /skit create|apply [name]|[range]");
/* 44 */         return true;
/*    */       }
/* 46 */       if (args[0].equalsIgnoreCase("create")) {
/* 47 */         if (args.length == 1) {
/* 48 */           p.sendMessage(API.NomeServer + "" + "§7> §cCorrect usage: /skit create [KitName]");
/* 49 */           return true;
/*    */         }
/* 51 */         String name = args[1];
/* 52 */         this.kit.put(name, p.getInventory().getContents());
/* 53 */         this.armadura.put(name, p.getInventory().getArmorContents());
/* 54 */         p.sendMessage(API.NomeServer + "" + "§7> §aKit " + args[1] + " §a created with sucess!");
/* 55 */         return true;
/*    */       }
/* 57 */       if (args[0].equalsIgnoreCase("apply")) {
/* 58 */         if (args.length <= 2) {
/* 59 */           p.sendMessage(API.NomeServer + "" + "§7> §cCorrect usage: /skit apply [kits] [range]");
/* 60 */           return true;
/*    */         }
/* 62 */         String name = args[1];
/* 63 */         if ((!this.kit.containsKey(name)) && (!this.armadura.containsKey(name))) {
/* 64 */           p.sendMessage(API.NomeServer + "" + "§7> §cThis kit " + name + " does not exist!");
/* 65 */           return true;
/*    */         }
/* 67 */         if (isInt(args[2])) {
/* 68 */           int numero = Integer.parseInt(args[2]);
/* 69 */           for (Entity ent : p.getNearbyEntities(numero, numero, numero)) {
/* 70 */             if ((ent instanceof Player))
/*    */             {
/* 72 */               Player plr = (Player)ent;
/* 73 */               plr.getInventory().setArmorContents((ItemStack[])this.armadura.get(name));
/* 74 */               plr.getInventory().setContents((ItemStack[])this.kit.get(name));
/*    */             }
/*    */           }
/* 77 */           p.sendMessage(API.NomeServer + "" + "§7> §aKit " + name + " has been applied to the players on range of " + numero + " blocks");
/* 78 */           return true;
/*    */         }
/*    */       }
/*    */     }
/* 82 */     return true;
/*    */   }
/*    */ }


/* Location:              D:\Desktop\video\Minhas Coisas do Desktop\KP-PVPvB12 (1).jar!\me\RafaelAulerDeMeloAraujo\main\Skit.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */
