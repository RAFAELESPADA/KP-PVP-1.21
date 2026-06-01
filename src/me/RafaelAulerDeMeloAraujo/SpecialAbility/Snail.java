/*     */ package me.RafaelAulerDeMeloAraujo.SpecialAbility;
/*     */ import java.util.Random;

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
/*     */ public class Snail
/*     */   implements Listener, CommandExecutor
/*     */ {
/*     */   private Main main;
/*     */   static Main plugin;
/*     */   
/*     */   public Snail(Main main)
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
/*  44 */       if (Habilidade.getAbility(p).equalsIgnoreCase("Snail"))
/*     */       {
	Player ent = (Player)entity;
    if (!Habilidade.ContainsAbility(ent)) {
    	return;
    }
	  if (API.isInRegion(p)) {
		  return;
	  }
/*  46 */         Random rand = new Random();
/*  47 */         int percent = rand.nextInt(100);
/*  48 */         if (percent <= Main.kits.getInt("SnailChance")) {
/*  49 */           entity.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, Main.kits.getInt("SnailSlownessTime") * 20, 1));
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
/*  72 */     if (cmd.equalsIgnoreCase("snail")) {
/*  73 */       if (Habilidade.ContainsAbility(p)) {
/*  74 */         p.sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§")) + this.main.getConfig().getString("Message.KitUse").replace("&", "§"));
/*  75 */            NewKitMenu.playSound(p, Main.getInstace().getConfig().getString("Sound.NoPermissionMessage"), 1.0F, 1.0F);
  return true;
/*     */       }
/*  78 */       if (!Join.game.contains(p.getName()))
/*     */       {
/*  80 */         p.sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§")) + " §eYou are not in kitpvp to do choose this kit!");
/*  81 */         return true;
/*     */       }
/*  83 */       if (!p.hasPermission("kitpvp.kit.snail")) {
/*  84 */         p.sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§")) + this.main.getConfig().getString("Permission").replace("&", "§").replaceAll("%permisson%", cmd));
/*  85 */         return true;
/*     */       }
/*     */       
/*  88 */       p.sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§")) + this.main.getConfig().getString("Message.Kit").replaceAll("%kit%", "Snail").replace("&", "§"));
/*  89 */       p.setGameMode(GameMode.ADVENTURE);
/*  90 */       p.getInventory().clear();
/*  91 */       Habilidade.setAbility(p, "Snail");
/*  92 */       ItemStack capacete0 = new ItemStack(Material.LEATHER_HELMET);
/*     */       
/*  94 */       ItemStack peitoral0 = new ItemStack(Material.IRON_CHESTPLATE);
/*     */       
/*  96 */       ItemStack calca0 = new ItemStack(Material.IRON_LEGGINGS);
/*     */       
/*  98 */       ItemStack Bota0 = new ItemStack(Material.IRON_BOOTS);
/*     */       
/* 100 */       p.getInventory().setHelmet(capacete0);
/* 101 */       p.getInventory().setChestplate(peitoral0);
/* 102 */       p.getInventory().setLeggings(calca0);
/* 103 */       p.getInventory().setBoots(Bota0);
/* 104 */       p.getInventory().addItem(new ItemStack[] { dima });
/*     */       API.give(p);
RTP.TeleportArenaRandom(p);
/* 106 */       for (int i = 0; i <= 34; i++) {
/* 107 */         p.getInventory().addItem(new ItemStack[] { sopa });
/* 108 */         
NewKitMenu.playSound(p, Main.getInstace().getConfig().getString("Sound.KitUse"), 1.0F, 1.0F);TitleAPI.sendTitle(p, Integer.valueOf(20), Integer.valueOf(60), Integer.valueOf(20), this.main.getConfig().getString("Title.KitTitle"), this.main.getConfig().getString("Title.KitSubTitle").replaceAll("%kit%", "Snail"));
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 114 */     return false;
/*     */   }
/*     */ }


/* Location:              D:\Desktop\video\Minhas Coisas do Desktop\KP-PVPvB12 (1).jar!\me\RafaelAulerDeMeloAraujo\SpecialAbility\Snail.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */
