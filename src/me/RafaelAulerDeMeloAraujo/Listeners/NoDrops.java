/*    */ package me.RafaelAulerDeMeloAraujo.Listeners;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.event.player.PlayerDropItemEvent;
/*    */ import org.bukkit.event.player.PlayerPickupItemEvent;

import me.RafaelAulerDeMeloAraujo.SpecialAbility.Habilidade;
/*    */ import me.RafaelAulerDeMeloAraujo.SpecialAbility.Join;
/*    */ import me.RafaelAulerDeMeloAraujo.main.Main;
/*    */ 
/*    */ public class NoDrops implements org.bukkit.event.Listener
/*    */ {
/*    */   private Main main;
/*    */   
/*    */   public NoDrops(Main main)
/*    */   {
/* 17 */     this.main = main;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ /*    */   @org.bukkit.event.EventHandler
/*    */   public void onPlayerDropItem(PlayerDropItemEvent paramPlayerPickupItemEvent)
/*    */   {
/* 26 */     if (!Join.game.contains(paramPlayerPickupItemEvent.getPlayer().getName()) || (Main.getInstace().getConfig().getString("DisableDropsOnKitPvP").equalsIgnoreCase("false"))) {
/* 27 */       return;
/*    */     }
if (paramPlayerPickupItemEvent.getItemDrop().getItemStack().getItemMeta().hasDisplayName()) {
	paramPlayerPickupItemEvent.setCancelled(true);	
}
/* 29 */     if (Join.game.contains(paramPlayerPickupItemEvent.getPlayer().getName()) && (Main.getInstace().getConfig().getString("DisableDropsOnKitPvP").equalsIgnoreCase("true")))
/*    */     {
/* 31 */       if (paramPlayerPickupItemEvent.getItemDrop().getItemStack().getType() == Material.BOWL || paramPlayerPickupItemEvent.getItemDrop().getItemStack().getType() == Material.MUSHROOM_STEW)
/*    */       {
/* 33 */         paramPlayerPickupItemEvent.setCancelled(false);
/* 34 */         paramPlayerPickupItemEvent.getPlayer().playSound(paramPlayerPickupItemEvent.getPlayer().getLocation(), org.bukkit.Sound.valueOf(this.main.getConfig().getString("Sound.BowlDrop")), 1.0F, 1.0F);
/*    */       }
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */   @org.bukkit.event.EventHandler
/*    */   public void onPlayerPickupItem(PlayerPickupItemEvent paramPlayerPickupItemEvent)
/*    */   {
/* 46 */     if (!Join.game.contains(paramPlayerPickupItemEvent.getPlayer().getName()) || (Main.getInstace().getConfig().getString("DisablePickupItemsOnKitPvP").equalsIgnoreCase("false"))) {
/* 47 */       return;
/*    */     }
if (!Habilidade.ContainsAbility(paramPlayerPickupItemEvent.getPlayer()) && Join.game.contains(paramPlayerPickupItemEvent.getPlayer().getName())) {
	paramPlayerPickupItemEvent.setCancelled(true);
	return;
}
/* 49 */     if (Join.game.contains(paramPlayerPickupItemEvent.getPlayer().getName()) && (Main.getInstace().getConfig().getString("DisablePickupItemsOnKitPvP").equalsIgnoreCase("true")))
/*    */     {
/* 51 */       if ((paramPlayerPickupItemEvent.getItem().getItemStack().getType() == Material.MUSHROOM_STEW))
/*    */       {
/* 53 */         paramPlayerPickupItemEvent.setCancelled(false);
/*    */       } else {
/* 55 */         paramPlayerPickupItemEvent.setCancelled(true);
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\Desktop\video\Minhas Coisas do Desktop\KP-PVPvB12 (1).jar!\me\RafaelAulerDeMeloAraujo\Listeners\NoDrops.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */
