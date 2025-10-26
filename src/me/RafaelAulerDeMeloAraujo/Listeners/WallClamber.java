/*    */ package me.RafaelAulerDeMeloAraujo.Listeners;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;

import org.bukkit.ChatColor;
/*    */ 
/*    */ import org.bukkit.Material;
/*    */ 
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.block.Action;
/*    */ import org.bukkit.event.player.PlayerInteractEvent;
/*    */ 
import org.bukkit.potion.PotionEffectType;
/*    */ import org.bukkit.util.Vector;

import me.RafaelAulerDeMeloAraujo.SpecialAbility.API;
import me.RafaelAulerDeMeloAraujo.SpecialAbility.Habilidade;
import me.RafaelAulerDeMeloAraujo.SpecialAbility.NewKitMenu;
/*    */ 
/*    */ import me.RafaelAulerDeMeloAraujo.main.Main;
/*    */ 
/*    */ public class WallClamber implements org.bukkit.event.Listener
/*    */ {
/*    */   private Main main;
/*    */   Main plugin;
/* 23 */   List<Player> FlyCooldown = new ArrayList();
/* 24 */   List<Player> TogglePlayersCooldown = new ArrayList();
/*    */   
/*    */   public WallClamber(Main main) {
/* 27 */     this.main = main;
/*    */   }
/*    */   
/*    */   @EventHandler
/*    */   public void onWall(PlayerInteractEvent e)
/*    */   {
/* 33 */     Player p = e.getPlayer();
/* 34 */     Action a = e.getAction();

/* 35 */     if ((a.equals(Action.RIGHT_CLICK_BLOCK)) && (p.getItemInHand().getType() == Material.DIAMOND_SWORD) && (Habilidade.getAbility(p).equalsIgnoreCase("Spiderman"))) {
	  if (API.isInRegion(p)) {
		  p.sendMessage(ChatColor.RED + "Leave the NO PVP Zone to use this kit!");
		  return;
	  }
/* 36 */       Vector v2 = p.getLocation().getDirection().multiply(0.0D).setY(1.0D);
/* 37 */       API.darEfeito(p, PotionEffectType.REGENERATION, 3, 1);
/* 38 */       
NewKitMenu.playSound(p, Main.getInstace().getConfig().getString("Sound.Spiderman"), 1.0F, 1.0F);
  
/* 40 */       p.setVelocity(v2);
/*    */     }
/*    */   }
/*    */ }


