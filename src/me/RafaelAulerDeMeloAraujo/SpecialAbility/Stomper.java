/*     */ package me.RafaelAulerDeMeloAraujo.SpecialAbility;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.EventHandler;
/*     */ import org.bukkit.event.EventPriority;
/*     */ import org.bukkit.event.Listener;
/*     */ import org.bukkit.event.block.Action;
/*     */ import org.bukkit.event.entity.EntityDamageEvent;
/*     */ import org.bukkit.event.player.PlayerDropItemEvent;
/*     */ import org.bukkit.event.player.PlayerInteractEvent;
/*     */ import org.bukkit.util.Vector;

/*     */ 
/*     */ import me.RafaelAulerDeMeloAraujo.main.Main;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Stomper
/*     */   implements Listener
/*     */ {
/*     */   private Main main;
/*     */   static Main plugin;
/*     */   
/*     */   public Stomper(Main main)
/*     */   {
/*  35 */     this.main = main;
/*  36 */     plugin = main;
/*     */   }
/*     */   
/*     */   @EventHandler(priority=EventPriority.HIGH)
/*     */   public void onPlayerStomp(EntityDamageEvent e) {
/*  41 */     if (!(e.getEntity() instanceof Player)) {
/*  42 */       return;
/*     */     }
/*  44 */     Player p = (Player)e.getEntity();
/*  45 */     if (e.getCause() == EntityDamageEvent.DamageCause.FALL)
/*     */     {
/*  47 */       if (Habilidade.getAbility(p).equalsIgnoreCase("Stomper"))
/*     */       {
/*  49 */         for (Entity ent : p.getNearbyEntities(Main.kits.getDouble("StomperRadius"), Main.kits.getDouble("StomperRadius"), Main.kits.getDouble("StomperRadius"))) {
/*  50 */           if ((ent instanceof Player))
/*     */           {
/*  52 */             Player plr = (Player)ent;
if (!Habilidade.ContainsAbility(plr)) {
	return;
}
if (API.isInRegion(p)) {
	  p.sendMessage(ChatColor.RED + "Leave the NO PVP Zone to use this kit!");
	  e.setCancelled(true);
	  return;
}
if (Habilidade.getAbility(plr) == "AntiStomper") {
	p.sendMessage(ChatColor.AQUA + "You stomped a AntiStomper and your");
	p.sendMessage(ChatColor.AQUA + "Stomper failed");
	plr.sendMessage(ChatColor.AQUA + "a AntiStomper kit protect everyone nearby from a stomper");
	plr.sendMessage(ChatColor.AQUA + "a AntiStomper kit protect everyone nearby from a stomper");
	return;
}
boolean isCitizensNPC = plr.hasMetadata("NPC");
if (isCitizensNPC) {
	return;
}
/*  58 */             if (plr.isSneaking())
/*     */             {
/*  60 */               plr.damage(6.0D, p);
/*  61 */               plr.sendMessage(ChatColor.GRAY + "You have been stomped by: " + ChatColor.AQUA + p.getName());
/*     */             }
/*     */             else
/*     */             {
	plr.damage(e.getDamage(), p);
    plr.damage(p.getFallDistance());
/*  66 */               plr.sendMessage(ChatColor.GRAY + "You have been stomped by: " + ChatColor.AQUA + p.getName());
/*     */             }
/*     */           }
/*     */         }
NewKitMenu.playSound(p, Main.getInstace().getConfig().getString("Sound.StomperDamage"), 1.0F, 1.0F);

/*  72 */         e.setDamage(4.0D);
/*  73 */         return;
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   @EventHandler
/*     */   public void BotaStomper(PlayerInteractEvent e)
/*     */   {
/*  84 */     final Player p = e.getPlayer();
/*  85 */     if ((e.getPlayer().getItemInHand().getType() == Material.DIAMOND) && (Habilidade.getAbility(p).equalsIgnoreCase("Stomper")) && ((e.getAction() == Action.RIGHT_CLICK_BLOCK) || (e.getAction() == Action.RIGHT_CLICK_AIR)))
/*     */     {
/*  87 */       e.setCancelled(true);
/*  88 */       p.updateInventory();
/*  89 */       if (Cooldown.add(p))
/*     */       {
/*  91 */         API.MensagemCooldown(p);
/*  92 */         return;
/*     */       }

/*  94 */       Vector vector = p.getEyeLocation().getDirection();
/*  95 */       vector.multiply(0.0F);
/*  96 */       vector.setY(10.0F);
/*  97 */       p.setVelocity(vector);
/*  98 */       Location loc = p.getLocation();NewKitMenu.playSound(p, Main.getInstace().getConfig().getString("Sound.Stomper"), 1.0F, 1.0F);

/*     */       
/* 101 */       Cooldown.add(p, Main.kits.getInt("StomperCooldown"));
/* 102 */       Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable()
/*     */       {
/*     */         public void run()
/*     */         {
/* 106 */           p.sendMessage(API.fimcooldown);
/* 107 */           Cooldown.remove(p);
/*     */         }
/* 109 */       }, Main.kits.getInt("StomperCooldown") * 20);
/*     */     }
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onDrop(PlayerDropItemEvent event)
/*     */   {
/* 116 */     if (event.getItemDrop().getItemStack().getType() == Material.DIAMOND && Habilidade.ContainsAbility(event.getPlayer())) {
/* 117 */       event.setCancelled(true);
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\Desktop\video\Minhas Coisas do Desktop\KP-PVPvB12 (1).jar!\me\RafaelAulerDeMeloAraujo\SpecialAbility\Stomper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */
