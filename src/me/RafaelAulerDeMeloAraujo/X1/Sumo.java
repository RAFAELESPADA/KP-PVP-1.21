package me.RafaelAulerDeMeloAraujo.X1;

import java.io.IOException;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;

/*     */ import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.EventHandler;
/*     */ import org.bukkit.event.Listener;
/*     */ import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
/*     */ import org.bukkit.event.player.PlayerCommandPreprocessEvent;
/*     */ import org.bukkit.event.player.PlayerInteractEntityEvent;
/*     */ import org.bukkit.event.player.PlayerInteractEvent;
/*     */ import org.bukkit.event.player.PlayerJoinEvent;
/*     */ import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerMoveEvent;
/*     */ import org.bukkit.event.player.PlayerQuitEvent;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.meta.ItemMeta;
/*     */ import org.bukkit.potion.PotionEffect;
/*     */ import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import me.RafaelAulerDeMeloAraujo.SpecialAbility.API;
import me.RafaelAulerDeMeloAraujo.SpecialAbility.Cooldown;
/*     */ import me.RafaelAulerDeMeloAraujo.SpecialAbility.Habilidade;
/*     */ import me.RafaelAulerDeMeloAraujo.SpecialAbility.Join;
import me.RafaelAulerDeMeloAraujo.SpecialAbility.NewKitMenu;
import me.RafaelAulerDeMeloAraujo.TitleAPI.TitleAPI;
import me.RafaelAulerDeMeloAraujo.main.GiveKitUnlocker;
/*     */ import me.RafaelAulerDeMeloAraujo.main.Main;
import net.wavemc.core.bukkit.WaveBukkit;
import net.wavemc.core.bukkit.account.WavePlayer;

/*    */ public class Sumo
/*    */   implements Listener
/*    */ {
/*    */   private Main main;
/*    */   
/*    */   public Sumo(Main main)
/*    */   {
/* 19 */     this.main = main;
/*    */   }
/*    */   
/*     */   
/*     */ public static Map<String, String> convites = new HashMap();
/*  47 */   
/*  50 */   public static ArrayList<Player> frezze = new ArrayList();
/*  48 */   public static Map<String, String> lutadores = new HashMap();
/*  49 */   public static ArrayList<Player> hide = new ArrayList();
/*  50 */   public static ArrayList<Player> insumo = new ArrayList();
private BukkitTask runTaskLater;
/*     */   
/*     */   public static void sairSumo(Player p) {
/*  53 */     p.getInventory().clear();
/*  54 */     insumo.remove(p);
insumo.remove(p);
insumo.remove(p);
insumo.remove(p);
insumo.remove(p);
insumo.remove(p);
insumo.remove(p);
insumo.remove(p);
insumo.remove(p);
insumo.remove(p);
insumo.remove(p);
insumo.remove(p);
insumo.remove(p);
insumo.remove(p);
insumo.remove(p);
insumo.remove(p);
insumo.remove(p);
insumo.remove(p);
insumo.remove(p);
/*  56 */     org.bukkit.World w = Bukkit.getServer().getWorld(Main.plugin.getConfig().getString("Spawn.World"));
/*  57 */     double x = Main.plugin.getConfig().getDouble("Spawn.X");
/*  58 */     double y = Main.plugin.getConfig().getDouble("Spawn.Y");
/*  59 */     double z = Main.plugin.getConfig().getDouble("Spawn.Z");
/*  60 */     Location lobby = new Location(w, x, y, z);
/*  61 */     lobby.setPitch((float)Main.plugin.getConfig().getDouble("Spawn.Pitch"));
/*  62 */     lobby.setYaw((float)Main.plugin.getConfig().getDouble("Spawn.Yaw"));
/*  63 */     p.getInventory().clear();
/*  64 */     p.teleport(lobby);
/*  65 */     p.getInventory().setLeggings(new ItemStack(Material.AIR));
/*  66 */     p.getInventory().setBoots(new ItemStack(Material.AIR));
p.getInventory().setArmorContents(null);
if (!Main.getInstance().getConfig().getBoolean("DisableInitialItems")) {
	
/*  94 */       ItemStack kitsr = Main.getInstance().getConfig().getItemStack("KitsItem");
/*  96 */       ItemMeta kitsr2 = kitsr.getItemMeta();
/*  97 */       kitsr2.setDisplayName(Main.messages.getString("KitsItemName").replace("&", "§"));
/*  98 */       kitsr.setItemMeta(kitsr2);
/*  95 */       ItemStack kits = Main.getInstance().getConfig().getItemStack("ShopItem");
/*  96 */       ItemMeta kits2 = kits.getItemMeta();
/*  97 */       kits2.setDisplayName(Main.messages.getString("ShopItemName").replace("&", "§"));
/*  98 */       kits.setItemMeta(kits2);
/*  99 */       ItemStack st = Main.getInstance().getConfig().getItemStack("1v1Item");
/* 100 */       ItemMeta st2 = st.getItemMeta();
/* 101 */       st2.setDisplayName(Main.messages.getString("1v1ItemName").replace("&", "§"));
/* 102 */       st.setItemMeta(st2);
ItemStack stats = Main.getInstance().getConfig().getItemStack("StatsItem");
/* 227 */           ItemMeta stats2 = kits.getItemMeta();
/* 228 */           stats2.setDisplayName(Main.messages.getString("StatsItemName").replace("&", "§"));
/* 229 */           stats.setItemMeta(stats2);
p.getInventory().setItem(Main.getInstance().getConfig().getInt("StatsItemSlot"), stats);

ItemStack stats1 = Main.getInstance().getConfig().getItemStack("ClickTestItem");
/* 227 */           ItemMeta stats12 = stats1.getItemMeta();
/* 228 */           stats12.setDisplayName(Main.messages.getString("ClickTestItemName").replace("&", "§"));
/* 229 */           stats1.setItemMeta(stats12);
ItemStack warp = Main.getInstance().getConfig().getItemStack("WarpItem");
/* 227 */           ItemMeta warp2 = warp.getItemMeta();
/* 228 */           warp2.setDisplayName("§aWarps");
/* 229 */           warp.setItemMeta(warp2);
if (!Main.getInstance().getConfig().getBoolean("DisableWarpItem")) {
	p.getInventory().setItem(Main.getInstance().getConfig().getInt("WarpItemSlot"), warp);
	}

/* 103 */     
if (!Main.getInstance().getConfig().getBoolean("DisableClickTestItem")) {
	p.getInventory().setItem(Main.getInstance().getConfig().getInt("ClickTestItemSlot"), stats1);
	}
ItemStack sair = Main.getInstance().getConfig().getItemStack("LeaveItem");
/* 227 */           ItemMeta sair2 = sair.getItemMeta();
/* 228 */           sair2.setDisplayName(Main.messages.getString("LeaveItemName").replace("&", "§"));
/* 229 */           sair.setItemMeta(sair2);
if (!Main.getInstance().getConfig().getBoolean("DisableLeaveItem")) {
	p.getInventory().setItem(Main.getInstance().getConfig().getInt("LeaveItemSlot"), sair);
}
p.getInventory().setItem(Main.getInstance().getConfig().getInt("KitsItemSlot"), kitsr);
if (!Main.getInstance().getConfig().getBoolean("DisableShopItem")) {
/* 103 */     	p.getInventory().setItem(Main.getInstance().getConfig().getInt("ShopItemSlot"), kits);
}
/* 104 */       	p.getInventory().setItem(Main.getInstance().getConfig().getInt("1v1ItemSlot"), st);
/*     */       }
/*     */ 
/* 107 */       p.updateInventory();

/*     */       
/*     */ 
GiveKitUnlocker.GiveUnlockers(p);
/* 107 */       p.updateInventory();
/*  83 */     Habilidade.removeAbility(p);
/*  84 */    API.vida(p);
/*     */     
/*     */ 
/*  87 */     p.updateInventory();
/*  88 */     p.sendMessage(Main.messages.getString("SumoLeave").replace("&", "§"));
NewKitMenu.playSound(p, Main.getInstace().getConfig().getString("Sound.1v1"), 1.0F, 1.0F);TitleAPI.sendTitle(p, Integer.valueOf(20), Integer.valueOf(60), Integer.valueOf(20), Main.getInstance().getConfig().getString("Title.SumoLeave").replace("&", "§"), "");
/*     */   }
/*     */   @EventHandler
public void onDamage(EntityDamageByEntityEvent event) {
    if (!(event.getEntity() instanceof Player)
            || !(event.getDamager() instanceof Player)) {
        return;
    }
if (!Join.game.contains(event.getEntity().getName())) {
	return;
}
    Player damager = (Player) event.getDamager();
    Player victim = (Player) event.getEntity();

 if (insumo.contains(victim) && insumo.contains(damager)) {
        event.setDamage(0);
    }
}
/*     */  @EventHandler
public void onMove(PlayerMoveEvent event) throws IOException {
    Player player = event.getPlayer();
    Block block = player.getLocation().getBlock().getRelative(BlockFace.DOWN);

    if (!Sumo.insumo.contains(player) || !Join.game.contains(player.getName())) {
        return;
    }
    if (!lutadores.containsKey(player.getName())) {
    	return;
    }
    Player matou = Bukkit.getServer().getPlayerExact((String)lutadores.get(player.getName()));
    if (!block.getType().equals(Material.WATER) || matou == null) {
        return;
    }
    /* 243 */     if (!Habilidade.getAbility(player).equalsIgnoreCase("SumoFight") && !Habilidade.getAbility(matou).equalsIgnoreCase("SumoFight")) {
    /* 244 */       return;
    }
    /* 245 */     if ((player instanceof Player)) {
    /* 246 */       
    /* 247 */       player.setFireTicks(0);
    /* 248 */       for (PotionEffect effect : player.getActivePotionEffects())
    /* 249 */         player.removePotionEffect(effect.getType());
    /*     */     }
    /* 251 */     if (((player instanceof Player)) && ((matou instanceof Player))) {
    /* 252 */       
    /* 253 */       player.setFireTicks(0);
    /* 254 */       for (PotionEffect effect : matou.getActivePotionEffects())
    /* 255 */         player.removePotionEffect(effect.getType());
    /*     */     }
    /* 257 */     
    /*     */ 
    /*     */ 
    /*     */ 
    /*     */ 
    /*     */ 
    /*     */ 
    /*     */ 
    /*     */ 
    /*     */ 
    /*     */ 
    /*     */ 
    /*     */ 
    /*     */ 
    /*     */ 
    /*     */ 
    /*     */ 
    WavePlayer k1 = WaveBukkit.getInstance().getPlayerManager().getPlayer(matou.getName());
    WavePlayer m = WaveBukkit.getInstance().getPlayerManager().getPlayer(player.getName());
    k1.getPvp().addWinsSumo(1);
    m.getPvp().setWinstreaksumo(0);
    k1.getPvp().addWinstreakSumo(1);
    m.getPvp().setDeathssumo(m.getPvp().getDeathssumo() + 1);
    WaveBukkit.getInstance().getPlayerManager().getController().save(m);
    WaveBukkit.getInstance().getPlayerManager().getController().save(k1);
    /*     */ 
    /*     */ 
    /*     */ 
    /*     */ 
    /*     */ 
    /* 280 */       new BukkitRunnable()
    /*     */       {
    /*     */         public void run()
    /*     */         {
    /* 262 */       
    /* 264 */           Player morreu = player;
    /* 265 */           morreu.spigot().respawn();
    /* 266 */           Sumo.lutadores.remove(morreu.getName());
    /* 267 */           Sumo.lutadores.remove(matou.getName());
    Bukkit.getConsoleSender().sendMessage("§b" + morreu.getName() + " has been killed by " + matou.getName() + " on kitpvp Sumo");
    /* 268 */           Sumo.hide.remove(matou);
    /* 269 */           Sumo.hide.remove(morreu);
    /* 270 */           Sumo.entrar1v1(matou);
    /* 271 */           Sumo.entrar1v1(player);
    /* 272 */           morreu.updateInventory();
    /* 273 */           matou.updateInventory();
    /* 274 */           for (Player online : Bukkit.getOnlinePlayers()) {
    /* 275 */             morreu.showPlayer(online);
    /* 276 */             matou.showPlayer(online);
    /*     */           }
    /*     */           
    /*     */         }
    /* 280 */       }.runTaskLater(Main.plugin, 5L);
    /*     */     }

/*     */ 
/*     */   public static void entrar1v1(Player p)
/*     */   {
	
	if (Main.cfg_x1.getString("sumo.coords.spawn.world") == null) {
        p.sendMessage(ChatColor.YELLOW + "Sumo is not seted yet!");
        return;
      } 
	org.bukkit.World w = Bukkit.getServer().getWorld(Main.cfg_x1.getString("sumo.coords.spawn.world"));
/*  97 */     insumo.add(p);
/*  98 */     p.teleport(new Location(w, Main.cfg_x1.getDouble("sumo.coords.spawn.x"), 
/*  99 */       Main.cfg_x1.getDouble("sumo.coords.spawn.y"), Main.cfg_x1.getDouble("sumo.coords.spawn.z")));
/* 100 */     p.getInventory().clear();
/* 101 */     p.getInventory().setArmorContents(null);
/*     */     
/* 103 */     ItemStack v1 = new ItemStack(Material.APPLE);
/* 104 */     ItemMeta v12 = v1.getItemMeta();
/* 105 */     v12.setDisplayName(Main.messages.getString("SumoInviteItemName").replace("&", "§"));
/* 106 */     v1.setItemMeta(v12);
/* 107 */     ItemStack rd1 = new ItemStack(Material.REDSTONE);
/* 108 */     ItemMeta rd12 = rd1.getItemMeta();
/* 109 */     rd12.setDisplayName(Main.messages.getString("SumoLeaveItemName").replace("&", "§"));
/* 110 */     rd1.setItemMeta(rd12);
/*     */     
/* 112 */     p.getInventory().setItem(0, v1);
/* 113 */     p.getInventory().setItem(8, rd1);
/*     */     
/* 115 */     Cooldown.remove(p);API.vida(p);
/*     */     
/* 117 */     p.setExp(0.0F);
/* 118 */     p.setLevel(0);
/* 119 */     Habilidade.setAbility(p, Main.cfg_x1.getString("sumo.ability"));
NewKitMenu.playSound(p, Main.getInstace().getConfig().getString("Sound.1v1"), 1.0F, 1.0F);}
/*     */   
/*     */ 
/*     */   public static void set1v1(Player p)
/*     */   {
/* 125 */     p.getInventory().clear();
/* 126 */     p.getInventory().setArmorContents(null);
Habilidade.setAbility(p, "SumoFight");
/* 141 */     
/*     */   }
/*     */  
      

/*     */ 
/*     */ 
/*     */ 
/*     */   public static void aceitar(Player p1, Player p2)
/*     */   {
	org.bukkit.World w1 = Bukkit.getServer().getWorld(Main.cfg_x1.getString("sumo.coords.loc_1.world"));
	org.bukkit.World w2 = Bukkit.getServer().getWorld(Main.cfg_x1.getString("sumo.coords.loc_2.world"));
/* 152 */     p1.teleport(new Location(w1, Main.cfg_x1.getDouble("sumo.coords.loc_1.x"), 
/* 153 */       Main.cfg_x1.getDouble("sumo.coords.loc_1.y"), Main.cfg_x1.getDouble("sumo.coords.loc_1.z"), 
/* 154 */       Float.valueOf((float)Main.cfg_x1.getDouble("sumo.coords.loc_1.yaw")).floatValue(), Float.valueOf((float)Main.cfg_x1.getDouble("sumo.coords.loc_1.pitch")).floatValue()));
/* 155 */     p2.teleport(new Location(w2, Main.cfg_x1.getDouble("sumo.coords.loc_2.x"), 
/* 156 */       Main.cfg_x1.getDouble("sumo.coords.loc_2.y"), Main.cfg_x1.getDouble("sumo.coords.loc_2.z"), 
/* 157 */       Float.valueOf((float)Main.cfg_x1.getDouble("sumo.coords.loc_2.yaw")).floatValue(), Float.valueOf((float)Main.cfg_x1.getDouble("sumo.coords.loc_2.pitch")).floatValue()));
/*     */     
/* 159 */     set1v1(p1);
/* 160 */     set1v1(p2);
/* 161 */     lutadores.put(p1.getName(), p2.getName());
/* 162 */     lutadores.put(p2.getName(), p1.getName());
/* 163 */     convites.remove(p1.getName());
/* 164 */     for (Player pp : Bukkit.getOnlinePlayers()) {
/* 165 */       p1.hidePlayer(pp);
/*     */     }
/* 167 */     hide.add(p1);
/* 168 */     for (Player pp : Bukkit.getOnlinePlayers()) {
/* 169 */       p2.hidePlayer(pp);
/*     */     }
/* 171 */     hide.add(p2);
frezze.add(p1);
frezze.add(p2);
/* 172 */     p1.showPlayer(p2);
/* 173 */     p2.showPlayer(p1);
/* 174 */     p1.updateInventory();
/* 175 */     p2.updateInventory();
/* 176 */     p2.sendMessage(Main.cfg_x1.getString("sumo.msg.invite_accept").replace("$player$", p1.getName()).replace("&", "§"));
/* 177 */     p1.sendMessage(Main.cfg_x1.getString("sumo.msg.guest_accept").replace("$player$", p2.getName()).replace("&", "§"));
p1.playSound(p1.getLocation(), Sound.valueOf(Main.getInstance().getConfig().getString("Sound.1v1")), 5.0F, 5.0F);
p2.playSound(p2.getLocation(), Sound.valueOf(Main.getInstance().getConfig().getString("Sound.1v1")), 5.0F, 5.0F);
/* 280 */       new BukkitRunnable()
/*     */       {
/*     */         public void run()
/*     */         {
                   TitleAPI.sendTitle(p1, 10, 10, 10, "§4§l3");
                   NewKitMenu.playSound(p1, Main.getInstace().getConfig().getString("Sound.1v1"), 1.0F, 1.0F); 
                   NewKitMenu.playSound(p2, Main.getInstace().getConfig().getString("Sound.1v1"), 1.0F, 1.0F);TitleAPI.sendTitle(p2, 10, 10, 10, "§4§l3");
/*     */         }
/* 280 */       }.runTaskLater(Main.plugin, 20L);
/* 280 */       new BukkitRunnable()
/*     */       {
/*     */         public void run()
/*     */         {
                   TitleAPI.sendTitle(p1, 10, 10, 10, "§6§l2");
                   NewKitMenu.playSound(p1, Main.getInstace().getConfig().getString("Sound.1v1"), 1.0F, 1.0F); 
                   NewKitMenu.playSound(p2, Main.getInstace().getConfig().getString("Sound.1v1"), 1.0F, 1.0F);TitleAPI.sendTitle(p2, 10, 10, 10, "§6§l2");
/*     */         }
/* 280 */       }.runTaskLater(Main.plugin, 40L);
/*     */     /* 280 */       new BukkitRunnable()
/*     */       {
/*     */         public void run()
/*     */         {
                   TitleAPI.sendTitle(p1, 10, 10, 10, "§a§l1");
                   NewKitMenu.playSound(p1, Main.getInstace().getConfig().getString("Sound.1v1"), 1.0F, 1.0F); 
                   NewKitMenu.playSound(p2, Main.getInstace().getConfig().getString("Sound.1v1"), 1.0F, 1.0F);TitleAPI.sendTitle(p2, 10, 10, 10, "§a§l1");
                  }
/* 280 */       }.runTaskLater(Main.plugin, 60L);
/*     */     /* 280 */       new BukkitRunnable()
/*     */       {
/*     */         public void run()
/*     */         {
                   TitleAPI.sendTitle(p1, 10, 10, 10, "§2§lGO!");
                   NewKitMenu.playSound(p1, Main.getInstace().getConfig().getString("Sound.1v1"), 1.0F, 1.0F); 
                   NewKitMenu.playSound(p2, Main.getInstace().getConfig().getString("Sound.1v1"), 1.0F, 1.0F);TitleAPI.sendTitle(p2, 10, 10, 10, "§2§lGO!");
/*     */         }
/* 280 */       }.runTaskLater(Main.plugin, 80L);
/*     */     frezze.remove(p1);
frezze.remove(p2);
/*     */   
/*     */   }
/*     */   
@EventHandler
/*     */   public void entrar(PlayerMoveEvent e)
/*     */   {
/* 193 */     if (frezze.contains(e.getPlayer())) {
	e.setCancelled(true);
/*     */     }
/*     */   }
/*     */  @EventHandler
/*     */   public void interact(PlayerInteractEvent e) {
/* 182 */     if ((Habilidade.getAbility(e.getPlayer()).equalsIgnoreCase(Main.cfg_x1.getString("sumo.ability"))) && 
/* 183 */       (((e.getAction() == Action.RIGHT_CLICK_AIR) || (e.getAction() == Action.RIGHT_CLICK_BLOCK))&& 
/* 184 */       (e.getPlayer().getItemInHand().getType().equals(Material.REDSTONE)))) {
	            e.setCancelled(true);
/* 185 */       sairSumo(e.getPlayer());
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   @EventHandler
/*     */   public void entrar(PlayerJoinEvent e)
/*     */   {
/* 193 */     for (Player p : hide) {
/* 194 */       p.hidePlayer(e.getPlayer());
/*     */     }
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void cmd(PlayerCommandPreprocessEvent e) {
/* 200 */     if (Habilidade.getAbility(e.getPlayer()).equalsIgnoreCase(Main.cfg_x1.getString("sumo.ability"))) {
/* 201 */       e.getPlayer().sendMessage(Main.cfg_x1.getString("sumo.msg.no_cmd").replace("&", "§"));
/* 202 */       e.setCancelled(true);
/* 203 */       return;
/*     */     }
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void kick(PlayerQuitEvent e) {
/* 209 */     if (lutadores.containsKey(e.getPlayer().getName())) {
/* 210 */       Player matou = Bukkit.getServer().getPlayerExact((String)lutadores.get(e.getPlayer().getName()));
/* 211 */       Player perdedor = e.getPlayer();
/* 212 */       matou.sendMessage(Main.cfg_x1.getString("sumo.msg.disconect").replace("&", "§"));
/* 213 */       hide.remove(perdedor);
/* 214 */       hide.remove(matou);
/* 215 */       for (Player pp : Bukkit.getOnlinePlayers()) {
/* 216 */         matou.showPlayer(pp);
/* 217 */         perdedor.showPlayer(pp);
lutadores.remove(e.getPlayer().getName());
/*     */       }
/* 219 */       entrar1v1(matou);
/*     */     }
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void kick(PlayerKickEvent e) {
/* 225 */     if (lutadores.containsKey(e.getPlayer().getName())) {
/* 226 */       Player matou = Bukkit.getServer().getPlayerExact((String)lutadores.get(e.getPlayer().getName()));
/* 227 */       Player perdedor = e.getPlayer();
/* 228 */       matou.sendMessage(Main.cfg_x1.getString("sumo.msg.disconect").replace("&", "§"));
/* 229 */       hide.remove(perdedor);
/* 230 */       hide.remove(matou);
/* 231 */       for (Player pp : Bukkit.getOnlinePlayers()) {
/* 232 */         matou.showPlayer(pp);
/* 233 */         perdedor.showPlayer(pp);
lutadores.remove(e.getPlayer().getName());
/*     */       }
/* 235 */       entrar1v1(matou);
/*     */     }
/*     */   }
/*     */   
/*     */  

/*     */   
/*     */   
/*     */   @EventHandler
/*     */   public void InteractItem(PlayerInteractEntityEvent e)
/*     */   {
/* 287 */     if ((Habilidade.getAbility(e.getPlayer()).equalsIgnoreCase(Main.cfg_x1.getString("sumo.ability"))) && 
/* 288 */       ((e.getRightClicked() instanceof Player)) && 
/* 289 */       (e.getPlayer().getItemInHand().getType() == Material.APPLE)) {
/* 290 */       final Player p = e.getPlayer();
/* 291 */       Player target = (Player)e.getRightClicked();
/* 292 */       if (convites.containsKey(target.getName())) {
/* 293 */         if (((String)convites.get(target.getName())).equalsIgnoreCase(p.getName())) {
/* 294 */           aceitar(target, p);
/* 295 */         } else if (!convites.containsKey(p.getName())) {
/* 296 */           p.sendMessage(Main.cfg_x1.getString("sumo.msg.invite").replace("$player$", target.getName()).replace("&", "§"));
/* 297 */           target.sendMessage(
/* 298 */             Main.cfg_x1.getString("sumo.msg.guest").replace("$player$", p.getName()).replace("&", "§"));
/* 299 */           convites.put(p.getName(), target.getName());
/* 300 */           Bukkit.getServer().getScheduler().runTaskLater(Main.plugin, new BukkitRunnable()
/*     */           {
/*     */             public void run()
/*     */             {
/* 304 */               if (Sumo.convites.containsKey(p.getName())) {
/* 305 */                 Sumo.convites.remove(p.getName());
/*     */               }
/*     */             }
/* 308 */           }, 200L);
/*     */         } else {
/* 310 */           p.sendMessage(Main.cfg_x1.getString("sumo.msg.invite_cooldown").replace("&", "§"));
/*     */         }
/* 312 */       } else if (!convites.containsKey(p.getName())) {
/* 313 */         p.sendMessage(Main.cfg_x1.getString("sumo.msg.invite").replace("$player$", target.getName()).replace("&", "§"));
/* 314 */         target.sendMessage(Main.cfg_x1.getString("sumo.msg.guest").replace("$player$", p.getName()).replace("&", "§"));
/* 316 */         convites.put(p.getName(), target.getName());
/* 317 */         BukkitTask runTaskLater = Bukkit.getServer().getScheduler().runTaskLater(Main.plugin, new BukkitRunnable()
/*     */         {
/*     */           public void run()
/*     */           {
/* 321 */             if (Sumo.convites.containsKey(p.getName())) {
/* 322 */               Sumo.convites.remove(p.getName());
/*     */             }
/*     */           }
/* 325 */         }, 200L);
/*     */       } else {
/* 327 */         p.sendMessage(Main.cfg_x1.getString("sumo.msg.invite_cooldown").replace("&", "§"));
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
}


/* Location:              D:\Desktop\video\Minhas Coisas do Desktop\KP-PVPvB12 (1).jar!\me\RafaelAulerDeMeloAraujo\sumo\sumo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */

