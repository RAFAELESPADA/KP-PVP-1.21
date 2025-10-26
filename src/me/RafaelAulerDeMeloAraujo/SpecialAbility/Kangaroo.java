/*     */ package me.RafaelAulerDeMeloAraujo.SpecialAbility;
/*     */ 
/*     */ import java.util.ArrayList;

import org.bukkit.ChatColor;
/*     */ import org.bukkit.GameMode;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.block.BlockFace;
/*     */ import org.bukkit.command.Command;
/*     */ import org.bukkit.command.CommandExecutor;
/*     */ import org.bukkit.command.CommandSender;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.EventHandler;
/*     */ import org.bukkit.event.Listener;
/*     */ import org.bukkit.event.entity.EntityDamageByEntityEvent;
/*     */ import org.bukkit.event.entity.EntityDamageEvent;
/*     */ import org.bukkit.event.player.PlayerDropItemEvent;
/*     */ import org.bukkit.event.player.PlayerInteractEvent;
/*     */ import org.bukkit.event.player.PlayerMoveEvent;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.meta.ItemMeta;
/*     */ import org.bukkit.util.Vector;

/*     */ import me.RafaelAulerDeMeloAraujo.TitleAPI.TitleAPI;
/*     */ import me.RafaelAulerDeMeloAraujo.main.Main;

/*     */ public class Kangaroo implements Listener, CommandExecutor
/*     */ {
/*     */   private Main main;
/*     */   static Main plugin;
/*     */   
/*     */   public Kangaroo(Main main)
/*     */   {
/*  41 */     this.main = main;
/*  42 */     plugin = main;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 



/*  49 */   ArrayList<String> tempo = new ArrayList();

/*  51 */   ArrayList<String> naofugir = new ArrayList();
/*     */   
/*     */   @EventHandler
/*     */   public void pular(PlayerInteractEvent event)
/*     */   {
	
/*  56 */     Player p = event.getPlayer();


/*  57 */     if ((p.getItemInHand().getType() == Material.FIREWORK_ROCKET) && 
/*  58 */       (Habilidade.getAbility(p) == "Kangaroo"))
/*     */     {
/*  60 */       event.setCancelled(true);

/*  61 */       if (Gladiator.lutando.containsKey(p.getName())) {
/*  62 */         p.sendMessage(String.valueOf("§cYou enter in a gladiator arena! You win a Speed Effect"));
/*     */         
/*  64 */         Main.darEfeito(p, org.bukkit.potion.PotionEffectType.SPEED, 10, 2);
/*     */       }
/*     */       else
/*     */       {
	  if (API.isInRegion(p)) {
		  p.sendMessage(ChatColor.RED + "Leave the NO PVP Zone to use this kit!");
		  return;
	  }
/*  68 */         event.setCancelled(true);
/*  69 */         Vector vector = p.getEyeLocation().getDirection();
/*  70 */         if (!this.naofugir.contains(p.getName()))
/*     */         {
/*  72 */           if (!this.tempo.contains(p.getName()))
/*     */           {
/*  74 */             this.tempo.add(p.getName());
/*  75 */             if (!p.isSneaking())
/*     */             {
/*  77 */               p.setFallDistance(-1.0F);
/*  78 */               vector.multiply(0.5F);
/*  79 */               vector.setY(1.0D);
/*  80 */               p.setVelocity(vector);
/*     */             }
/*     */             else
/*     */             {
/*  84 */               p.setFallDistance(-1.0F);
/*  85 */               vector.multiply(1.5F);
/*  86 */               vector.setY(0.5D);
/*  87 */               p.setVelocity(vector);
/*     */             }
/*     */           }
/*     */         }
/*  91 */         else if (!this.tempo.contains(p.getName()))
/*     */         {
/*  93 */           this.tempo.add(p.getName());
/*  94 */           p.setFallDistance(0.0F);
/*  95 */           vector.multiply(0.0F);
/*  96 */           vector.setY(0.0D);
/*  97 */           p.setVelocity(vector);
/*     */         }
/*     */         
/* 100 */         if (this.naofugir.contains(p.getName())) {
/* 101 */           p.sendMessage(Main.messages.getString("KangarooCooldown").replace("&", "§"));
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onDamageds(EntityDamageEvent event)
/*     */   {
/* 110 */     Entity e = event.getEntity();
/* 111 */     if ((e instanceof Player))
/*     */     {
/* 113 */       Player player = (Player)e;
/* 114 */       if (((event.getEntity() instanceof Player)) && 
/* 115 */         (event.getCause() == EntityDamageEvent.DamageCause.FALL) && 
/* 116 */         (player.getInventory().contains(Material.FIREWORK_ROCKET)) && 
/* 117 */         (event.getDamage() >= 7.0D)) {
/* 118 */         event.setDamage(7.0D);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void removertempo(PlayerMoveEvent event)
/*     */   {
/* 126 */     Player p = event.getPlayer();
/* 127 */     if (this.tempo.contains(p.getName()))
/*     */     {
/* 129 */       Block b = p.getLocation().getBlock();
/* 130 */       if ((b.getType() != Material.AIR) || (b.getRelative(BlockFace.DOWN).getType() != Material.AIR)) {
/* 131 */         this.tempo.remove(p.getName());
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void adicionartempo(EntityDamageByEntityEvent event)
/*     */   {
/* 139 */     if (!(event.getEntity() instanceof Player)) {
/* 140 */       return;
/*     */     }
/* 142 */     final Player kangaroo = (Player)event.getEntity();
/* 143 */     if ((event.getDamager() instanceof Player))
/*     */     {
/* 145 */       Player p = (Player)event.getDamager();
/* 146 */       if (((kangaroo instanceof Player)) && ((p instanceof Player)) && 
/* 147 */         (kangaroo.getInventory().contains(Material.FIREWORK_ROCKET)))
/*     */       {
/* 149 */         this.naofugir.add(kangaroo.getName());
/* 150 */         org.bukkit.Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable()
/*     */         {
/*     */           public void run()
/*     */           {
/* 154 */             Kangaroo.this.naofugir.remove(kangaroo.getName());
/*     */           }
/* 156 */         }, 60L);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   @EventHandler
/*     */   public void onDrop(PlayerDropItemEvent event)
/*     */   {
/* 166 */     if (event.getItemDrop().getItemStack().getType() == Material.FIREWORK_ROCKET && Habilidade.ContainsAbility(event.getPlayer())) {
/* 167 */       event.setCancelled(true);
/*     */     }
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onDamage(EntityDamageEvent event) {
/* 173 */     Entity e = event.getEntity();
/* 174 */     if ((e instanceof Player))
/*     */     {
/* 176 */       Player player = (Player)e;
/* 177 */       if (((event.getEntity() instanceof Player)) && 
/* 178 */         (event.getCause() == EntityDamageEvent.DamageCause.FALL) && 
/* 179 */         (player.getInventory().contains(Material.FIREWORK_ROCKET)) && 
/* 180 */         (event.getDamage() >= 12.0D) && Habilidade.ContainsAbility(player))
/* 181 */         event.setDamage(12.0D);
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean onCommand(CommandSender sender, Command command, String cmd, String[] args) {
/* 186 */     Player p = (Player)sender;
/* 187 */     ItemStack dima = new ItemStack(Material.DIAMOND_SWORD);
/* 188 */     ItemMeta souperaa = dima.getItemMeta();
/* 189 */     souperaa.setDisplayName("§cSword");
/* 190 */     dima.setItemMeta(souperaa);
/* 191 */     ItemStack sopa = new ItemStack(Material.MUSHROOM_STEW);
/* 192 */     ItemMeta sopas = sopa.getItemMeta();
/* 193 */     sopas.setDisplayName("§6Soup");
/* 194 */     sopa.setItemMeta(sopas);
/* 195 */     ItemStack monk = new ItemStack(Material.FIREWORK_ROCKET);
/* 196 */     ItemMeta ims = monk.getItemMeta();
/* 197 */     ims.setDisplayName("§bDouble-Jump");
/* 198 */     monk.setItemMeta(ims);
/*     */     
/*     */ 
/*     */ 
/* 202 */     if (cmd.equalsIgnoreCase("kangaroo")) {
/* 203 */       if (Habilidade.ContainsAbility(p)) {
/* 204 */         p.sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§")) + this.main.getConfig().getString("Message.KitUse").replace("&", "§"));
/* 205 */         
NewKitMenu.playSound(p, Main.getInstace().getConfig().getString("Sound.KitUse"), 1.0F, 1.0F);return true;
/*     */       }
/* 208 */       if (!Join.game.contains(p.getName()))
/*     */       {
/* 210 */         p.sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§")) + " §eYou are not in kitpvp to do choose this kit!");
/* 211 */         return true;
/*     */       }
/* 213 */       if (!p.hasPermission("kitpvp.kit.kangaroo")) {
/* 214 */         p.sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§")) + this.main.getConfig().getString("Permission").replace("&", "§").replaceAll("%permisson%", cmd));
/* 215 */         
NewKitMenu.playSound(p, Main.getInstace().getConfig().getString("Sound.KitUse"), 1.0F, 1.0F); return true;
/*     */       }
if (Main.kits.getBoolean("KangarooDisabled")) {
	p.sendMessage(API.NomeServer + ChatColor.RED + "The Kangaroo kit is disabled, sorry");
	return true;
}
/* 218 */       p.getInventory().clear();
/* 219 */       p.sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§")) + this.main.getConfig().getString("Message.Kit").replaceAll("%kit%", "Kangaroo").replace("&", "§"));
/* 220 */       p.setGameMode(GameMode.ADVENTURE);
/*     */       
/* 222 */       Habilidade.setAbility(p, "Kangaroo");
/* 223 */       ItemStack capacete0 = new ItemStack(Material.IRON_HELMET);
/*     */       
/* 225 */       ItemStack peitoral0 = new ItemStack(Material.IRON_CHESTPLATE);
/*     */       
/* 227 */       ItemStack calca0 = new ItemStack(Material.IRON_LEGGINGS);
/*     */       
/* 229 */       ItemStack Bota0 = new ItemStack(Material.IRON_BOOTS);
/*     */       
/* 231 */       p.getInventory().setHelmet(capacete0);
/* 232 */       p.getInventory().setChestplate(peitoral0);
/* 233 */       p.getInventory().setLeggings(calca0);
/* 234 */       p.getInventory().setBoots(Bota0);
/* 235 */       p.getInventory().addItem(new ItemStack[] { dima });
/* 236 */       p.getInventory().addItem(new ItemStack[] { monk });
/*     */       
/* 238 */       for (int i = 0; i <= 34; i++) {
/* 239 */         p.getInventory().addItem(new ItemStack[] { sopa });
/*     */       }
/*     */     }
/*     */     API.give(p);
RTP.TeleportArenaRandom(p);
/* 243 */     TitleAPI.sendTitle(p, Integer.valueOf(20), Integer.valueOf(60), Integer.valueOf(20), this.main.getConfig().getString("Title.KitTitle"), this.main.getConfig().getString("Title.KitSubTitle").replaceAll("%kit%", "Kangaroo"));
/*     */     
/*     */ 
/*     */ 
/* 247 */     return false;
/*     */   }
/*     */ }


/* Location:              D:\Desktop\video\Minhas Coisas do Desktop\KP-PVPvB12 (1).jar!\me\RafaelAulerDeMeloAraujo\SpecialAbility\Kangaroo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */
