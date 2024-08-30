
/*     */ package me.RafaelAulerDeMeloAraujo.SpecialAbility;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Random;
/*     */ import me.RafaelAulerDeMeloAraujo.TitleAPI.TitleAPI;
/*     */ import me.RafaelAulerDeMeloAraujo.main.Main;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.Effect;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.Sound;
/*     */ import org.bukkit.World;
/*     */ import org.bukkit.command.Command;
/*     */ import org.bukkit.command.CommandExecutor;
/*     */ import org.bukkit.command.CommandSender;
/*     */ import org.bukkit.configuration.file.FileConfiguration;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.EventHandler;
/*     */ import org.bukkit.event.Listener;
/*     */ import org.bukkit.event.entity.EntityDamageByEntityEvent;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.PlayerInventory;
/*     */ import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffectType;
/*     */ 
/*     */ 
/*     */ public class Vampire
/*     */   implements Listener, CommandExecutor
/*     */ {
/*     */   private Main main;
/*     */   static Main plugin;
/*     */   
/*     */   public Vampire(Main main)
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

/*  44 */       if (Habilidade.getAbility(d) == "Vampire")
/*     */       {
/*  46 */         Random r = new Random();
/*  47 */         int c = r.nextInt(100);
/*  48 */       if (c <= Main.kits.getInt("VampireChance"))
/*     */         {
/*  50 */           
                    API.darEfeito(d, PotionEffectType.REGENERATION, 4, 2);
                    d.sendMessage("§cYou ingest " + p.getName() + " blood and receive");
                    d.sendMessage("§cA regeneration effect!");
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
/*     */   {
/*  65 */     Player p = (Player)sender;
/*  66 */     if (cmd.getName().equalsIgnoreCase("kVampire"))
/*     */     {
/*     */ 
/*  69 */       if (!p.hasPermission("kitpvp.kit.Vampire"))
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
/*     */       
/*  81 */       if (Habilidade.ContainsAbility(p)) {
/*  82 */         p.sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§")) + this.main.getConfig().getString("Message.KitUse").replace("&", "§"));
/*  83 */         p.playSound(p.getLocation(), Sound.valueOf(this.main.getConfig().getString("Sound.KitUse")), 1.0F, 1.0F);
/*  84 */         return true;
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
/* 103 */       ItemStack Bota0 = new ItemStack(Material.LEATHER_BOOTS);
/*     */       
/* 105 */       p.getInventory().setHelmet(capacete0);
/* 106 */       p.getInventory().setChestplate(peitoral0);
/* 107 */       p.getInventory().setLeggings(calca0);
/* 108 */       p.getInventory().setBoots(Bota0);
/*     */       
/* 110 */       p.sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§")) + this.main.getConfig().getString("Message.Kit").replaceAll("%kit%", "Vampire").replace("&", "§"));
/* 111 */       Habilidade.setAbility(p, "Vampire");
/* 112 */       p.getInventory().addItem(new ItemStack[] { dima });
/*     */       
/*     */ 
/*     */ 
/*     */ API.give(p);
RTP.TeleportArenaRandom(p);
/* 117 */       for (int i = 0; i <= 34; i++) {
/* 118 */         p.getInventory().addItem(new ItemStack[] { sopa });
/* 119 */         p.playSound(p.getLocation(), Sound.valueOf(this.main.getConfig().getString("Sound.KitUse")), 1.0F, 1.0F);
/* 120 */         TitleAPI.sendTitle(p, Integer.valueOf(20), Integer.valueOf(60), Integer.valueOf(20), this.main.getConfig().getString("Title.KitTitle"), this.main.getConfig().getString("Title.KitSubTitle").replaceAll("%kit%", "Vampire"));
/*     */       }
/*     */     }
/*     */     
/* 124 */     return false;
/*     */   }
/*     */ }


/* Location:              D:\Desktop\video\Minhas Coisas do Desktop\KP-PVPvB12 (1).jar!\me\RafaelAulerDeMeloAraujo\SpecialAbility\Vampire.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */
