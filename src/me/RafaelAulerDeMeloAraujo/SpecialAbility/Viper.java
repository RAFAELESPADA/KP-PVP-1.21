/*     */ package me.RafaelAulerDeMeloAraujo.SpecialAbility;
/*     */ import java.util.Random;

import org.bukkit.ChatColor;
/*     */ import org.bukkit.GameMode;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.command.Command;
/*     */ import org.bukkit.command.CommandExecutor;
/*     */ import org.bukkit.command.CommandSender;
/*     */ import org.bukkit.entity.LivingEntity;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.EventHandler;
/*     */ import org.bukkit.event.Listener;
/*     */ import org.bukkit.event.entity.EntityDamageByEntityEvent;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.meta.ItemMeta;
/*     */ import org.bukkit.potion.PotionEffect;
/*     */ import org.bukkit.potion.PotionEffectType;

/*     */ import me.RafaelAulerDeMeloAraujo.TitleAPI.TitleAPI;
/*     */ import me.RafaelAulerDeMeloAraujo.main.Main;
/*     */ 
/*     */ public class Viper
/*     */   implements Listener, CommandExecutor
/*     */ {
/*     */   private Main main;
/*     */   static Main plugin;
/*     */   
/*     */   public Viper(Main main)
/*     */   {
/*  33 */     this.main = main;
/*  34 */     plugin = main;
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void viper(EntityDamageByEntityEvent e) {
/*  39 */     if (((e.getDamager() instanceof Player)) && 
/*  40 */       ((e.getEntity() instanceof LivingEntity)))
/*     */     {
	
/*  42 */       LivingEntity entity = (LivingEntity)e.getEntity();
/*  43 */       Player p = (Player)e.getDamager();


/*  44 */       if (Habilidade.getAbility(p).equalsIgnoreCase("Viper"))
/*     */       {
	Player ent = (Player)entity;
    if (!Habilidade.ContainsAbility(ent)) {
    	return;
    }
	  if (API.isInRegion(p)) {
		  p.sendMessage(ChatColor.RED + "Leave the NO PVP Zone to use this kit!");
		  return;
	  }
/*  46 */         Random rand = new Random();
/*  47 */         int percent = rand.nextInt(100);
/*  48 */         if (percent <= Main.kits.getInt("ViperChance")) {
/*  49 */           entity.addPotionEffect(new PotionEffect(PotionEffectType.POISON, Main.kits.getInt("ViperPoisonTime") * 20, 1));
/*  50 */           return;
/*     */         }
/*  52 */         return;
/*     */       }
/*  54 */       return;
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean onCommand(CommandSender sender, Command command, String cmd, String[] args)
/*     */   {
/*  60 */     Player p = (Player)sender;
/*  61 */     ItemStack dima = new ItemStack(Material.DIAMOND_SWORD);
/*  62 */     ItemMeta souperaa = dima.getItemMeta();
/*  63 */     souperaa.setDisplayName("§cSword");
/*  64 */     dima.setItemMeta(souperaa);
/*  65 */     ItemStack sopa = new ItemStack(Material.MUSHROOM_STEW);
/*  66 */     ItemMeta sopas = sopa.getItemMeta();
/*  67 */     sopas.setDisplayName("§6Soup");
/*  68 */     sopa.setItemMeta(sopas);
/*     */     
/*     */ 
/*     */ 
/*  72 */     if (cmd.equalsIgnoreCase("viper")) {
/*  73 */       if (Habilidade.ContainsAbility(p)) {
/*  74 */         p.sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§")) + this.main.getConfig().getString("Message.KitUse").replace("&", "§"));
/*  75 */         
NewKitMenu.playSound(p, Main.getInstace().getConfig().getString("Sound.KitUse"), 1.0F, 1.0F);return true;
/*     */       }
/*  78 */       if (!Join.game.contains(p.getName()))
/*     */       {
/*  80 */         p.sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§")) + " §eYou are not in kitpvp to do choose this kit!");
/*  81 */         return true;
/*     */       }
/*  83 */       if (!p.hasPermission("kitpvp.kit.viper")) {
/*  84 */         p.sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§")) + this.main.getConfig().getString("Permission").replace("&", "§").replaceAll("%permisson%", cmd));
/*  85 */         
NewKitMenu.playSound(p, Main.getInstace().getConfig().getString("Sound.KitUse"), 1.0F, 1.0F);return true;
/*     */       }
/*     */       if (Main.kits.getBoolean("ViperDisabled")) {
	p.sendMessage(API.NomeServer + ChatColor.RED + "The Viper kit is disabled, sorry");
	return true;
}
/*     */       
/*  89 */       p.sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§")) + this.main.getConfig().getString("Message.Kit").replaceAll("%kit%", "Viper").replace("&", "§"));
/*  90 */       p.setGameMode(GameMode.ADVENTURE);
/*  91 */       p.getInventory().clear();
/*  92 */       Habilidade.setAbility(p, "Viper");
/*  93 */       ItemStack capacete0 = new ItemStack(Material.LEATHER_HELMET);
/*     */       
/*  95 */       ItemStack peitoral0 = new ItemStack(Material.IRON_CHESTPLATE);
/*     */       
/*  97 */       ItemStack calca0 = new ItemStack(Material.IRON_LEGGINGS);
/*     */       
/*  99 */       ItemStack Bota0 = new ItemStack(Material.IRON_BOOTS);
/*     */       
/* 101 */       p.getInventory().setHelmet(capacete0);
/* 102 */       p.getInventory().setChestplate(peitoral0);
/* 103 */       p.getInventory().setLeggings(calca0);
/* 104 */       p.getInventory().setBoots(Bota0);
/* 105 */       p.getInventory().addItem(new ItemStack[] { dima });
/*     */       API.give(p);
RTP.TeleportArenaRandom(p);
/* 107 */       for (int i = 0; i <= 34; i++) {
/* 108 */         p.getInventory().addItem(new ItemStack[] { sopa });
NewKitMenu.playSound(p, Main.getInstace().getConfig().getString("Sound.KitUse"), 1.0F, 1.0F);
/* 110 */         TitleAPI.sendTitle(p, Integer.valueOf(20), Integer.valueOf(60), Integer.valueOf(20), this.main.getConfig().getString("Title.KitTitle"), this.main.getConfig().getString("Title.KitSubTitle").replaceAll("%kit%", "Viper"));
/*     */       }
/*     */     }
/* 113 */     return false;
/*     */   }
/*     */ }


/* Location:              D:\Desktop\video\Minhas Coisas do Desktop\KP-PVPvB12 (1).jar!\me\RafaelAulerDeMeloAraujo\SpecialAbility\Viper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */
