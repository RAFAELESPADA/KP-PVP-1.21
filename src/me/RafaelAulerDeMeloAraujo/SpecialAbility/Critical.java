/*     */ package me.RafaelAulerDeMeloAraujo.SpecialAbility;
/*     */ 
/*     */ 
/*     */ import java.util.Random;

/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.Effect;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.Sound;
/*     */ 
/*     */ import org.bukkit.command.Command;
/*     */ import org.bukkit.command.CommandExecutor;
/*     */ import org.bukkit.command.CommandSender;
/*     */ 
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.EventHandler;
/*     */ import org.bukkit.event.Listener;
/*     */ import org.bukkit.event.entity.EntityDamageByEntityEvent;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ 
/*     */ import org.bukkit.inventory.meta.ItemMeta;

/*     */ import me.RafaelAulerDeMeloAraujo.TitleAPI.TitleAPI;
/*     */ import me.RafaelAulerDeMeloAraujo.main.Main;
/*     */ 
/*     */ 
/*     */ public class Critical
/*     */   implements Listener, CommandExecutor
/*     */ {
/*     */   private Main main;
/*     */   static Main plugin;
/*     */   
/*     */   public Critical(Main main)
/*     */   {
/*  33 */     this.main = main;
/*  34 */     plugin = main;
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void dano(EntityDamageByEntityEvent e)
/*     */   {
/*  40 */     if (((e.getEntity() instanceof Player)) && ((e.getDamager() instanceof Player)))
/*     */     {
/*  42 */       Player p = (Player)e.getEntity();
/*  43 */       Player d = (Player)e.getDamager();
/*  44 */       if (Habilidade.getAbility(d) == "Critical")
/*     */       {

		/*     */       
/*  46 */         Random r = new Random();
/*  47 */         int c = r.nextInt(100);
/*  48 */         if (c <= Main.kits.getInt("KitCriticalChance"))
/*     */         {
/*  50 */           e.setDamage(e.getDamage() + Main.kits.getDouble("CriticalExtraDamage"));
/*  51 */           p.getWorld().playEffect(p.getLocation(), Effect.STEP_SOUND, Material.REDSTONE_BLOCK, 10);
/*  52 */           d.sendMessage(Main.messages.getString("KitCriticalHit").replace("&", "§").replace("%player%", p.getName()));
/*  53 */           p.sendMessage(Main.messages.getString("KitCriticalHitted").replace("&", "§").replace("%player%", d.getName()));
/*     */         }
/*     */       }
}
/*     */     }
/*     */   
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
/*     */   {
/*  65 */     Player p = (Player)sender;
/*  66 */     if (cmd.getName().equalsIgnoreCase("critical"))
/*     */     {
/*     */ 
/*  69 */       if (!p.hasPermission("kitpvp.kit.critical"))
/*     */       {
/*  71 */         p.sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§")) + this.main.getConfig().getString("Permission").replace("&", "§").replaceAll("%permisson%", commandLabel));
/*  72 */         p.playSound(p.getLocation(), Sound.valueOf(this.main.getConfig().getString("Sound.NoPermissionMessage")), 1.0F, 1.0F);
/*  73 */         return true;
/*     */       }
/*  75 */       if (!Join.game.contains(p.getName()))
/*     */       {
/*  77 */         p.sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§")) + " §eYou are not in kitpvp to choose this kit!");
/*  78 */         return true;
/*     */       }
if (Main.kits.getBoolean("CriticalDisabled")) {
	p.sendMessage(API.NomeServer + ChatColor.RED + "The Critical kit is disabled, sorry");
	return true;
}
/*     */       
/*  81 */       if (Habilidade.ContainsAbility(p)) {
/*  82 */         p.sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§")) + this.main.getConfig().getString("Message.KitUse").replace("&", "§"));
/*  83 */        NewKitMenu.playSound(p, Main.getInstace().getConfig().getString("Sound.StomperDamage"), 1.0F, 1.0F);
 return true;
/*     */       }
/*  86 */       p.getInventory().clear();
/*  87 */       ItemStack dima = new ItemStack(Material.DIAMOND_SWORD);
/*  88 */       ItemMeta souperaa = dima.getItemMeta();
/*  89 */       souperaa.setDisplayName("§cSword");
/*  90 */       dima.setItemMeta(souperaa);
/*  91 */       ItemStack sopa = new ItemStack(Material.MUSHROOM_STEW);
/*  92 */       ItemMeta sopas = sopa.getItemMeta();
/*  93 */       sopas.setDisplayName("§6Soup");
/*  94 */       sopa.setItemMeta(sopas);
/*     */       
/*     */ 
/*  97 */       ItemStack capacete0 = new ItemStack(Material.IRON_HELMET);
/*     */       
/*  99 */       ItemStack peitoral0 = new ItemStack(Material.IRON_CHESTPLATE);
/*     */       
/* 101 */       ItemStack calca0 = new ItemStack(Material.IRON_LEGGINGS);
/*     */       
/* 103 */       ItemStack Bota0 = new ItemStack(Material.IRON_BOOTS);
/*     */       
/* 105 */       p.getInventory().setHelmet(capacete0);
/* 106 */       p.getInventory().setChestplate(peitoral0);
/* 107 */       p.getInventory().setLeggings(calca0);
/* 108 */       p.getInventory().setBoots(Bota0);
/*     */       
/* 110 */       p.sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§")) + this.main.getConfig().getString("Message.Kit").replaceAll("%kit%", "Critical").replace("&", "§"));
/* 111 */       Habilidade.setAbility(p, "Critical");
/* 112 */       p.getInventory().addItem(new ItemStack[] { dima });
/*     */       
/*     */ 
/*     */ 
/*     */ API.give(p);
RTP.TeleportArenaRandom(p);
/* 117 */       for (int i = 0; i <= 34; i++) {
/* 118 */         p.getInventory().addItem(new ItemStack[] { sopa });
/* 119 */        NewKitMenu.playSound(p, Main.getInstace().getConfig().getString("Sound.StomperDamage"), 1.0F, 1.0F);
 TitleAPI.sendTitle(p, Integer.valueOf(20), Integer.valueOf(60), Integer.valueOf(20), this.main.getConfig().getString("Title.KitTitle"), this.main.getConfig().getString("Title.KitSubTitle").replaceAll("%kit%", "Critical"));
/*     */       }
/*     */     }
/*     */     
/* 124 */     return false;
/*     */   }
/*     */ }


/* Location:              D:\Desktop\video\Minhas Coisas do Desktop\KP-PVPvB12 (1).jar!\me\RafaelAulerDeMeloAraujo\SpecialAbility\Critical.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */
