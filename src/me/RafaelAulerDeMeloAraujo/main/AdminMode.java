/*     */ package me.RafaelAulerDeMeloAraujo.main;
/*     */ 
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;

/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.GameMode;
/*     */ 
/*     */ import org.bukkit.Material;
/*     */ 
/*     */ import org.bukkit.command.Command;
/*     */ import org.bukkit.command.CommandExecutor;
/*     */ import org.bukkit.command.CommandSender;
/*     */ 
/*     */ import org.bukkit.entity.Damageable;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.EventHandler;
/*     */ import org.bukkit.event.Listener;
/*     */ import org.bukkit.event.block.Action;
/*     */ import org.bukkit.event.player.PlayerInteractEntityEvent;
/*     */ import org.bukkit.event.player.PlayerInteractEvent;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ 
/*     */ import org.bukkit.inventory.meta.ItemMeta;

/*     */ import me.RafaelAulerDeMeloAraujo.Coins.Coins;
import me.RafaelAulerDeMeloAraujo.SpecialAbility.API;
/*     */ import me.RafaelAulerDeMeloAraujo.SpecialAbility.Habilidade;
/*     */ import me.RafaelAulerDeMeloAraujo.SpecialAbility.Join;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AdminMode
/*     */   implements CommandExecutor, Listener
/*     */ {
/*     */   private Main main;
/*     */   
/*  39 */   public AdminMode(Main main) { this.main = main; }
/*     */   
/*  41 */   public static ArrayList<String> admin = new ArrayList<String>();
/*  42 */   public static HashMap<String, ItemStack[]> saveinv = new HashMap<String, ItemStack[]>();
/*  43 */   public static HashMap<String, ItemStack[]> savearmor = new HashMap<String, ItemStack[]>();
/*     */   
/*     */ 
/*     */ 
/*     */   @SuppressWarnings("deprecation")
public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
/*     */   {
/*  49 */     if (!(sender instanceof Player)) {
/*  50 */       sender.sendMessage("§bYou need to be a player");
/*  51 */       return true;
/*     */     }
/*  53 */     Player p = (Player)sender;
/*  54 */     if (!sender.hasPermission("kitpvp.adminmode")) {
/*  55 */       sender.sendMessage("§cYou dont have the permission kitpvp.adminmode");
/*  56 */       p.playSound(p.getLocation(), org.bukkit.Sound.valueOf(this.main.getConfig().getString("Sound.NoPermissionMessage")), 1.0F, 1.0F);
/*  57 */       return true;
/*     */     }
/*     */     
/*  60 */     if (args.length == 0) { Object estrelameta;
/*  61 */       if (!admin.contains(p.getName())) {
/*  62 */          p.sendMessage(Main.messages.getString("AdminModeJoin").replace("&", "§"));
/*     */         
/*  64 */         for (Player s : Bukkit.getOnlinePlayers()) {
/*  65 */           if (!s.hasPermission("kitpvp.adminmode")) {
/*  66 */             s.hidePlayer(p);
/*     */           }
/*     */         }
/*  69 */         p.setGameMode(GameMode.CREATIVE);
/*  70 */         admin.add(p.getName());
/*  71 */         saveinv.put(p.getName(), p.getInventory().getContents());
/*  72 */         savearmor.put(p.getName(), p.getInventory().getArmorContents());
/*  73 */         p.setAllowFlight(true);
/*  74 */         p.getInventory().clear();
/*     */         
/*  76 */         ItemStack estrela = new ItemStack(Material.SLIME_BALL);
/*  77 */         estrelameta = estrela.getItemMeta();
/*  78 */         ((ItemMeta)estrelameta).setDisplayName("§a§lQuick Change!");
/*  79 */         estrela.setItemMeta((ItemMeta)estrelameta);
/*     */         
/*     */ 
/*     */ 
/*  83 */         ItemStack barra = new ItemStack(Material.IRON_BARS);
/*  84 */         ItemMeta barrameta = barra.getItemMeta();
/*  85 */         barrameta.setDisplayName("§a§lJail!");
/*  86 */         barra.setItemMeta(barrameta);
/*     */         
/*     */ 
/*     */ 
/*  90 */         ItemStack etiqueta = new ItemStack(Material.FEATHER);
/*  91 */         ItemMeta etiquetameta = etiqueta.getItemMeta();
/*  92 */         etiquetameta.setDisplayName("§a§lNo Fall Test!");
/*  93 */         etiqueta.setItemMeta(etiquetameta);
/*     */         
/*  95 */         ItemStack kb = new ItemStack(Material.STICK);
/*  96 */         ItemMeta kbmeta = kb.getItemMeta();
/*  97 */         kbmeta.addEnchant(org.bukkit.enchantments.Enchantment.KNOCKBACK, 10, true);
/*  98 */         kbmeta.setDisplayName("§a§lAntiKnock Test!");
/*  99 */         kb.setItemMeta(kbmeta);
/*     */         
/* 101 */         ItemStack kb1 = new ItemStack(Material.BRICK);
/* 102 */         ItemMeta kbmeta1 = kb1.getItemMeta();
/*     */         
/* 104 */         kbmeta1.setDisplayName("§b§lInfo Player!");
/* 105 */         kb1.setItemMeta(kbmeta1);
/*     */         
/* 107 */         p.getInventory().setItem(0, kb);
/*     */         
/* 109 */         p.getInventory().setItem(2, estrela);
/* 110 */         p.getInventory().setItem(4, etiqueta);
/* 111 */         p.getInventory().setItem(6, barra);
/* 112 */         p.getInventory().setItem(8, kb1);
/*     */         
/*     */ 
/*     */ 
/*     */ 
/* 117 */         p.updateInventory();
/*     */       }
/*     */       else {
/* 120 */         p.sendMessage(Main.messages.getString("AdminModeLeave").replace("&", "§"));
/* 121 */       API.vida(p);
/* 122 */         p.getInventory().clear();
/* 123 */         admin.remove(p.getName());
/* 124 */         for (estrelameta = Bukkit.getOnlinePlayers().iterator(); ((Iterator)estrelameta).hasNext();) { Player s = (Player)((Iterator)estrelameta).next();
/* 125 */           if (!s.hasPermission("kitpvp.adminmode")) {
/* 126 */             s.showPlayer(p);
/*     */           }
/*     */         }
/* 129 */         p.setGameMode(GameMode.SURVIVAL);
/* 130 */         p.getInventory().setContents((ItemStack[])saveinv.get(p.getName()));
/* 131 */         p.getInventory().setArmorContents((ItemStack[])savearmor.get(p.getName()));
/*     */       }
/*     */     }
/* 134 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public static int getMaterial(Player p, Material m)
/*     */   {
/* 140 */     int value = 0;
/* 141 */     ItemStack[] arrayOfItemStack; int j = (arrayOfItemStack = p.getInventory().getContents()).length; for (int i = 0; i < j; i++) { ItemStack item = arrayOfItemStack[i];
/* 142 */       if ((item != null) && (item.getType() == m) && (item.getAmount() > 0)) {
/* 143 */         value += item.getAmount();
/*     */       }
/*     */     }
/*     */     
/* 147 */     return value;
/*     */   }
/*     */   
/*     */ 
/*     */   @EventHandler
/*     */   public void onInteractPlayerSlimeFun(PlayerInteractEvent e)
/*     */   {
/* 154 */     final Player p = e.getPlayer();
/* 155 */     if ((admin.contains(p.getName())) && (
/* 156 */       (e.getAction() == Action.RIGHT_CLICK_BLOCK) || (e.getAction() == Action.RIGHT_CLICK_AIR)))
/*     */     {
/* 158 */       if (p.getItemInHand().getType() == Material.SLIME_BALL)
/*     */       {
/* 160 */         p.chat("/adminmode");
/* 161 */         Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable()
/*     */         {
/*     */           public void run()
/*     */           {
/* 165 */             p.chat("/adminmode");
/*     */           }
/* 167 */         }, 10L);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onPlayerInfo(PlayerInteractEntityEvent e) {
/* 174 */     Player p = e.getPlayer();
/* 175 */     if ((admin.contains(p.getName())) && (p.getItemInHand().getType() == Material.BRICK))
/*     */     {
/* 177 */       Player p1 = (Player)e.getRightClicked();
/* 178 */       Damageable hp = p1;
/*     */       
/* 180 */       String kitpvp = "§aYes";
/* 181 */       String nokitpvp = "§cNo";
/* 182 */       p.sendMessage("§b§lKITPVP INFORMATION OF: §6§l" + p1.getName());
/* 183 */       if (Join.game.contains(p1.getName()))
/*     */       {
/* 185 */         p.sendMessage(String.valueOf("§cPlayer is on kitpvp? " + kitpvp));
/*     */       }
/*     */       else {
/* 188 */         p.sendMessage(String.valueOf("§cPlayer is on kitpvp? " + nokitpvp));
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 194 */     if ((admin.contains(p.getName())) && (p.getItemInHand().getType() == Material.BRICK))
/*     */     {
/*     */ 
/* 197 */       Player p1 = (Player)e.getRightClicked();
/*     */       
/* 199 */       Damageable hp = p1;
/* 200 */       int kills = Main.plugin.getConfig().getInt("status." + p1.getName().toLowerCase() + ".kills");
/* 201 */       int deaths = Main.plugin.getConfig().getInt("status." + p1.getName().toLowerCase() + ".mortes");
/* 202 */       p.sendMessage("§eHealth: §c§l" + (int)hp.getHealth());
/*     */       
/* 204 */       p.sendMessage("§eGamemode: §c§l" + p1.getGameMode());
/* 205 */       p.sendMessage("§eKitPvP Kills: §c§l" + kills);
/* 206 */       p.sendMessage("§eKitPvP Deaths: §c§l" + deaths);
/* 207 */       p.sendMessage("§eKitPvP Coins: §c§l" + Coins.getCoins(p1));
/* 208 */       p.sendMessage("§eKitPvP Kit: §c§l" + Habilidade.getAbility(p1));
/* 209 */       p.sendMessage("§eIs Flying? §c§l" + p1.isFlying());
/*     */       
/*     */ 
/* 212 */       Player target = null;
/* 213 */       p.sendMessage("§eIp: §c§l" + p1.getAddress().getHostName());
/*     */     }
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void ClickOpeninventory(PlayerInteractEntityEvent e) {
/* 219 */     if (((e.getRightClicked() instanceof Player)) && (e.getPlayer().getItemInHand().getType() == Material.AIR) && (admin.contains(e.getPlayer().getName()))) {
/* 220 */       Player t = (Player)e.getRightClicked();
/* 221 */       e.getPlayer().openInventory(t.getInventory());
/*     */     }
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onAdminArena(PlayerInteractEntityEvent event) {
/* 227 */     Player p = event.getPlayer();
/* 228 */     if ((admin.contains(p.getName())) && (p.getItemInHand().getType() == Material.IRON_BARS))
/*     */     {
/* 230 */       Player p1 = (Player)event.getRightClicked();
/* 231 */       if (p1 != null)
/*     */       {
/* 233 */         p1.getLocation().add(0.0D, 13.0D, 0.0D).getBlock().setType(Material.BEDROCK);
/* 234 */         p1.getLocation().add(0.0D, 11.0D, 1.0D).getBlock().setType(Material.BEDROCK);
/* 235 */         p1.getLocation().add(1.0D, 11.0D, 0.0D).getBlock().setType(Material.BEDROCK);
/* 236 */         p1.getLocation().add(0.0D, 11.0D, -1.0D).getBlock().setType(Material.BEDROCK);
/* 237 */         p1.getLocation().add(-1.0D, 11.0D, 0.0D).getBlock().setType(Material.BEDROCK);
/* 238 */         p1.getLocation().add(0.0D, 10.0D, 0.0D).getBlock().setType(Material.BEDROCK);
/* 239 */         p1.teleport(p1.getLocation().add(0.0D, 11.0D, -0.05D));
/* 240 */         p.sendMessage(Main.messages.getString("JailedPlayer").replace("%player%", p1.getName()).replace("&", "§"));
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onAdminNoFall(PlayerInteractEntityEvent event)
/*     */   {
/* 248 */     Player p = event.getPlayer();
/* 249 */     if ((admin.contains(p.getName())) && (p.getItemInHand().getType() == Material.FEATHER))
/*     */     {
/* 251 */       Player p1 = (Player)event.getRightClicked();
/* 252 */       if (p1 != null)
/*     */       {
/* 254 */         p1.getLocation().add(0.0D, 40.0D, 0.0D).getBlock().setType(Material.AIR);
/* 255 */         p1.getLocation().add(0.0D, 40.0D, 1.0D).getBlock().setType(Material.AIR);
/* 256 */         p1.getLocation().add(1.0D, 40.0D, 0.0D).getBlock().setType(Material.AIR);
/* 257 */         p1.getLocation().add(0.0D, 40.0D, -1.0D).getBlock().setType(Material.AIR);
/* 258 */         p1.getLocation().add(-1.0D, 40.0D, 0.0D).getBlock().setType(Material.AIR);
/* 259 */         p1.getLocation().add(0.0D, 40.0D, 0.0D).getBlock().setType(Material.AIR);
/* 260 */         p1.teleport(p1.getLocation().add(0.0D, 11.0D, -0.05D));
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\Desktop\video\Minhas Coisas do Desktop\KP-PVPvB12 (1).jar!\me\RafaelAulerDeMeloAraujo\main\AdminMode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */
