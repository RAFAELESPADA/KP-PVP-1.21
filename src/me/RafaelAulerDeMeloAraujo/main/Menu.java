package me.RafaelAulerDeMeloAraujo.main;



import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;

/*     */ import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.World;
/*     */ import org.bukkit.command.Command;
/*     */ import org.bukkit.command.CommandExecutor;
/*     */ import org.bukkit.command.CommandSender;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
/*     */ import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerChangedWorldEvent;
/*     */ import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerInteractEvent;
/*     */ import org.bukkit.event.player.PlayerJoinEvent;
/*     */ import org.bukkit.event.player.PlayerKickEvent;
/*     */ import org.bukkit.event.player.PlayerQuitEvent;
/*     */ import org.bukkit.inventory.Inventory;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ 
/*     */ import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import me.RafaelAulerDeMeloAraujo.Listeners.CombatLog;
import me.RafaelAulerDeMeloAraujo.Listeners.StatusGUI;
import me.RafaelAulerDeMeloAraujo.Listeners.UpdateUtil;
import me.RafaelAulerDeMeloAraujo.ScoreboardManager.Level;
import me.RafaelAulerDeMeloAraujo.ScoreboardManager.WaveAnimation;
import me.RafaelAulerDeMeloAraujo.SpecialAbility.API;
/*     */ import me.RafaelAulerDeMeloAraujo.SpecialAbility.Cooldown;
/*     */ import me.RafaelAulerDeMeloAraujo.SpecialAbility.Deshfire;
import me.RafaelAulerDeMeloAraujo.SpecialAbility.Gladiator;
/*     */ import me.RafaelAulerDeMeloAraujo.SpecialAbility.Habilidade;
/*     */ import me.RafaelAulerDeMeloAraujo.SpecialAbility.Join;
import me.RafaelAulerDeMeloAraujo.SpecialAbility.NewKitMenu;
import me.RafaelAulerDeMeloAraujo.SpecialAbility.PlayerLevelUPEvent;
import me.RafaelAulerDeMeloAraujo.TitleAPI.TitleAPI;
import me.RafaelAulerDeMeloAraujo.X1.X1;


/*     */ public class Menu
/*     */   implements Listener , CommandExecutor
/*     */ {
/*     */   private Main main;
private static WaveAnimation waveAnimation;
private static String text = "";
/*     */  public static ArrayList<String> has = new ArrayList();
/*     */   
/*     */   public Menu(Main main)
/*     */   {
/*  62 */     this.main = main;
/*     */   }

   @EventHandler(priority = EventPriority.MONITOR)
/*     */   public void addtoTop2(PlayerJoinEvent e) {
	Player p = e.getPlayer();
	UpdateUtil updateChecker = new UpdateUtil(Main.getInstance(), true);
	if (!p.hasPlayedBefore()) {
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "givekitunlocker " + p.getName() + " 1");
	}
	if (p.isOp() && !Main.getInstance().getConfig().getBoolean("UpdateCheckerDisabled")) {
	switch (updateChecker.check()) {
	    case OUT_OF_DATE:
	        p.sendMessage("§c[KP-PVP] An update for KP-PVP (" + updateChecker.getNewVersion() + ") was found. Please update at: https://www.spigotmc.org/resources/kp-pvp-the-ultimate-kitpvp-plugin.50969/");
	        break;
	    case UP_TO_DATE:
	    	 p.sendMessage("§a[KP-PVP] You are on latest version");
	    default:
	        break;
	}
	}
	}
/*     */   
	
	/*     */   


@EventHandler
/*  45 */   public void playerdeath(PlayerLevelUPEvent ev) { 
	   int playerLevel = Level.getLevel(ev.getPlayer());
				  for (String commands : Main.customization.getStringList("Levels.Levels." + playerLevel + ".commands")) {
				  Bukkit.dispatchCommand(Bukkit.getConsoleSender(), commands.replace("%player%", ev.getPlayer().getName()));
			}
	}

/*     */   @EventHandler
/*     */   public void onJoin(PlayerJoinEvent e) {
/*  67 */     Player p = e.getPlayer();
/*     */     
/*  70 */     Habilidade.removeAbility(p);
/*  71 */     Deshfire.Armadura.remove(p);
/*  72 */     Deshfire.Armadura2.remove(p);
/*  73 */     Deshfire.cooldownm.remove(p);
if (!Join.game.contains(p.getName())) {
	if (Join.saveinv.containsKey(p.getName())) {
		p.getInventory().setContents(Join.saveinv.get(p.getName()));
		p.sendMessage(ChatColor.BLUE + "[KitPvP] Your inventory gets restaured! :)");
		Join.saveinv.remove(p.getName());
	}
	
/*  74 */     Join.game.remove(p.getName());
Bukkit.getConsoleSender().sendMessage("[KP-PVP] Removing " + p.getName() + " from kitpvp variable!");
}
/*  75 */     Cooldown.remove(p);
/*     */     
/*     */ 
/*     */ 
/*     */     

/*     */     
/*  84 */     if (this.main.getConfig().getString("JoinItem.JoinSound").equalsIgnoreCase("true"))
	NewKitMenu.playSound(p, this.main.getConfig().getString("Sound.Join"), 1.0F, 1.0F);
/*    */
/*  85 */       
/*     */   }
/*     */   
/*     */   public static void createButton(Material mat, Inventory inv, int Slot, String name, String lore) {
/*  89 */     ItemStack item = new ItemStack(mat);
/*     */     
/*  91 */     ItemMeta meta = item.getItemMeta();
/*  92 */     meta.setDisplayName(name);
/*  93 */     meta.setLore(Arrays.asList(new String[] { lore }));
/*  94 */     item.setItemMeta(meta);
/*  95 */     inv.setItem(Slot, item);
/*     */   }
/*     */   @EventHandler
/*     */   public void onEvent(PlayerJoinEvent e)
/*     */   {
     Player p = e.getPlayer(); 
     if (!Main.plugin.getConfig().getBoolean("bungeemode")) {
	 return;
}
if (!Join.game.contains(p.getName())) {
/*  74 */     Join.game.add(p.getName());
}
new BukkitRunnable() {	
	@Override
		public void run() {
/*     */           if (!Join.game.contains(p.getName())) {
	return;
}
/*     */ 	/*     */       ;

		}}.runTaskTimer(Main.getInstance(), 10 * 20L, 20L * Main.getInstance().getConfig().getInt("ScoreBoard-Interval-Update"));




	/*     */ 
	/* 200 */           World w = Bukkit.getServer().getWorld(Main.plugin.getConfig().getString("Spawn.World"));
	/* 201 */           double x = Main.plugin.getConfig().getDouble("Spawn.X");
	/* 202 */           double y = Main.plugin.getConfig().getDouble("Spawn.Y");
	/* 203 */           double z = Main.plugin.getConfig().getDouble("Spawn.Z");
	/* 204 */           Location lobby = new Location(w, x, y, z);


	/*     */ 
	/* 211 */           lobby.setPitch((float)Main.plugin.getConfig().getDouble("Spawn.Pitch"));
	/* 212 */           lobby.setYaw((float)Main.plugin.getConfig().getDouble("Spawn.Yaw"));
	/* 213 */           p.getInventory().clear();
	/*     */           
	/*     */ 
	/* 216 */           p.teleport(lobby);
	/*     */           
	/*     */ 
	/* 219 */           p.getInventory().clear();
	/* 220 */           p.getInventory().setArmorContents(null);
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
	ItemStack sair = Main.getInstance().getConfig().getItemStack("LeaveItem");
	/* 227 */           ItemMeta sair2 = sair.getItemMeta();
	/* 228 */           sair2.setDisplayName(Main.messages.getString("LeaveItemName").replace("&", "§"));
	/* 229 */           sair.setItemMeta(sair2);
	if (!Main.getInstance().getConfig().getBoolean("DisableLeaveItem")) {
		p.getInventory().setItem(Main.getInstance().getConfig().getInt("LeaveItemSlot"), sair);
	}
	/* 103 */     
	if (!Main.getInstance().getConfig().getBoolean("DisableClickTestItem")) {
		p.getInventory().setItem(Main.getInstance().getConfig().getInt("ClickTestItemSlot"), stats1);
		}
	p.getInventory().setItem(Main.getInstance().getConfig().getInt("KitsItemSlot"), kitsr);
	/* 103 */     	p.getInventory().setItem(Main.getInstance().getConfig().getInt("ShopItemSlot"), kits);
	/* 104 */       	p.getInventory().setItem(Main.getInstance().getConfig().getInt("1v1ItemSlot"), st);
	/*     */       
	/*     */ 
	/* 107 */       p.updateInventory();
	}
	p.getInventory().setArmorContents(null);
	/*     */       
		
	/* 107 */       p.updateInventory();
	/*     */           
	/*     */ 

	/*     */ 
	/* 235 */           p.setExp(0.0F);
	/* 236 */           p.setExhaustion(20.0F);
	/* 237 */           p.setFireTicks(0);
	/* 238 */           p.setFoodLevel(20000);
	/* 239 */           TitleAPI.sendTitle(p, Integer.valueOf(40), Integer.valueOf(80), Integer.valueOf(40), Main.getInstance().getConfig().getString("Title.JoinTitle"), Main.getInstance().getConfig().getString("Title.JoinSubTitle"));
	API.tirarEfeitos(p);
}
@EventHandler
/*     */   public void onEvent2(PlayerJoinEvent e)
/*     */   {
     Player p = e.getPlayer(); 
     if (!Main.plugin.getConfig().getBoolean("PlayersRemainOnKitPvPOnLeave")) {
	 return;
}
     if (Main.plugin.getConfig().getBoolean("bungeemode")) {
    	 return;
    }
if (!Join.game.contains(p.getName())) {
/*  74 */     return;
}
new BukkitRunnable() {	
	@Override
		public void run() {
/*     */           if (!Join.game.contains(p.getName())) {
	return;
}
/*     */ 	/*     */       ;

		}};



Bukkit.getConsoleSender().sendMessage("Putting " + p.getName() + " back on KITPVP!");
	/*     */ if (!Join.game.contains(p.getName())) {
		Bukkit.getConsoleSender().sendMessage("Adding " + p.getName() + " kitpvp variable!");
		/*  74 */     Join.game.add(p.getName());
		Join.game.add(p.getName());
	}
	Join.game.add(p.getName());
	/* 200 */           World w = Bukkit.getServer().getWorld(Main.plugin.getConfig().getString("Spawn.World"));
	/* 201 */           double x = Main.plugin.getConfig().getDouble("Spawn.X");
	/* 202 */           double y = Main.plugin.getConfig().getDouble("Spawn.Y");
	/* 203 */           double z = Main.plugin.getConfig().getDouble("Spawn.Z");
	/* 204 */           Location lobby = new Location(w, x, y, z);


	/*     */ 
	/* 211 */           lobby.setPitch((float)Main.plugin.getConfig().getDouble("Spawn.Pitch"));
	/* 212 */           lobby.setYaw((float)Main.plugin.getConfig().getDouble("Spawn.Yaw"));
	/* 213 */           p.getInventory().clear();
	/*     */           
	/*     */ 
	/* 216 */           p.teleport(lobby);
	/*     */           
	/*     */ 
	/* 219 */           p.getInventory().clear();
	/* 220 */           p.getInventory().setArmorContents(null);
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
	ItemStack sair = Main.getInstance().getConfig().getItemStack("LeaveItem");
	/* 227 */           ItemMeta sair2 = sair.getItemMeta();
	/* 228 */           sair2.setDisplayName(Main.messages.getString("LeaveItemName").replace("&", "§"));
	/* 229 */           sair.setItemMeta(sair2);
	if (!Main.getInstance().getConfig().getBoolean("DisableLeaveItem")) {
		p.getInventory().setItem(Main.getInstance().getConfig().getInt("LeaveItemSlot"), sair);
	}
	/* 103 */     
	if (!Main.getInstance().getConfig().getBoolean("DisableClickTestItem")) {
		p.getInventory().setItem(Main.getInstance().getConfig().getInt("ClickTestItemSlot"), stats1);
		}
	p.getInventory().setItem(Main.getInstance().getConfig().getInt("KitsItemSlot"), kitsr);
	/* 103 */     	p.getInventory().setItem(Main.getInstance().getConfig().getInt("ShopItemSlot"), kits);
	/* 104 */       	p.getInventory().setItem(Main.getInstance().getConfig().getInt("1v1ItemSlot"), st);
	/*     */       }
	/*     */ 
	/* 107 */       p.updateInventory();	p.getInventory().setArmorContents(null);
	/*     */       
	
	/* 107 */       p.updateInventory();
	/*     */           
	/*     */ 

	/*     */ 
	/* 235 */           p.setExp(0.0F);
	/* 236 */           p.setExhaustion(20.0F);
	/* 237 */           p.setFireTicks(0);
	/* 238 */           p.setFoodLevel(20000);
	/* 239 */           TitleAPI.sendTitle(p, Integer.valueOf(40), Integer.valueOf(80), Integer.valueOf(40), Main.getInstance().getConfig().getString("Title.JoinTitle"), Main.getInstance().getConfig().getString("Title.JoinSubTitle"));
	API.tirarEfeitos(p);
}
/*     */   
@EventHandler
/*     */   public void onLeave2(PlayerQuitEvent e)
/*     */   {
/* 117 */     Player p = e.getPlayer();
if (X1.inx1.contains(p)) {
	X1.sair1v1(p);
}
}
/*     */   @EventHandler
/*     */   public void onLeave(PlayerQuitEvent e)
/*     */   {
/* 117 */     Player p = e.getPlayer();
/*     */     if (Join.game.contains(p.getName())&& !Main.plugin.getConfig().getBoolean("bungeemode") && !Main.plugin.getConfig().getBoolean("PlayersRemainOnKitPvPOnLeave")) {
	/*     */ 
	/*     */ 
	/* 133 */    	/* 279 */       Habilidade.removeAbility(p);
	/* 280 */       Deshfire.Armadura.remove(p.getName());
	/* 281 */       Deshfire.Armadura2.remove(p.getName());
	/* 282 */       Deshfire.cooldownm.remove(p);
	/* 283 */       Join.game.remove(p.getName());
	/* 284 */       Join.game.remove(p.getName());
	/* 285 */       Join.game.remove(p.getName());
	/* 286 */       Join.game.remove(p.getName());
	/* 287 */       Join.game.remove(p.getName());
	/* 288 */       Join.game.remove(p.getName());
	/* 289 */       Join.game.remove(p.getName());
	/* 290 */       Join.game.remove(p.getName());
	/* 291 */       Join.game.remove(p.getName());
	/* 292 */       Join.game.remove(p.getName());
	/* 293 */       Join.game.remove(p.getName());
	/* 294 */       Join.game.remove(p.getName());Join.game.remove(p.getName());
	/* 295 */       Join.game.remove(p.getName());
	/* 296 */       Join.game.remove(p.getName());
	/* 297 */       Join.game.remove(p.getName());

	/*     */ 
	/*     */ 
	/*     */ 
	/* 302 */       Cooldown.remove(p);
	/* 303 */       p.sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§")) + String.valueOf(this.main.getConfig().getString("Message.KitPvpLeave-Message").replace("&", "§")));
	/* 304 */       p.getInventory().clear();
	/* 305 */       p.teleport((Location)Join.saveworld.get(p.getName()));
	/* 306 */       p.getInventory().setContents((ItemStack[])Join.saveinv.get(p.getName()));
	/* 307 */       p.setGameMode((GameMode)Join.savegamemode.get(p.getName()));
	p.setScoreboard(Join.savescore.get(p.getName()));
	p.setLevel(Join.savelevel.get(p.getName()));
	p.setFoodLevel(Join.savehunger.get(p.getName()));
	p.setRemainingAir(Join.saveair.get(p.getName()));
	/* 308 */       p.getInventory().setArmorContents((ItemStack[])Join.savearmor.get(p.getName()));
	TitleAPI.sendTitle(p, Integer.valueOf(40), Integer.valueOf(80), Integer.valueOf(40), this.main.getConfig().getString("Title.LeaveTitle"), this.main.getConfig().getString("Title.LeaveSubTitle"));

	/*     */   
	/* 311 */       p.updateInventory();
	API.tirarEfeitos(p);
}
/*     */   }
@EventHandler
/*     */   public void onEventt(PlayerJoinEvent e)
/*     */   {
     Player p = e.getPlayer(); 
     if (p.getGameMode() == GameMode.SPECTATOR) {
    	 p.setGameMode(GameMode.SURVIVAL);
     }
}
/*     */   
/*     */   @EventHandler
/*     */   public void onLeave(PlayerKickEvent e)
/*     */   {
	
/* 128 */     Player p = e.getPlayer();
/*     */     if (Join.game.contains(p.getName()) && !Main.plugin.getConfig().getBoolean("bungeemode") && !Main.plugin.getConfig().getBoolean("PlayersRemainOnKitPvPOnLeave")) {
	/*     */ 
	/*     */ 
	/* 133 */    	/* 279 */       Habilidade.removeAbility(p);
	/* 280 */       Deshfire.Armadura.remove(p.getName());
	/* 281 */       Deshfire.Armadura2.remove(p.getName());
	/* 282 */       Deshfire.cooldownm.remove(p);
	/* 283 */       Join.game.remove(p.getName());
	/* 284 */       Join.game.remove(p.getName());
	/* 285 */       Join.game.remove(p.getName());
	/* 286 */       Join.game.remove(p.getName());
	/* 287 */       Join.game.remove(p.getName());
	/* 288 */       Join.game.remove(p.getName());
	/* 289 */       Join.game.remove(p.getName());
	/* 290 */       Join.game.remove(p.getName());
	/* 291 */       Join.game.remove(p.getName());
	/* 292 */       Join.game.remove(p.getName());
	/* 293 */       Join.game.remove(p.getName());
	/* 294 */       Join.game.remove(p.getName());Join.game.remove(p.getName());
	/* 295 */       Join.game.remove(p.getName());
	/* 296 */       Join.game.remove(p.getName());
	/* 297 */       Join.game.remove(p.getName());

	/*     */ 
	/*     */ 
	/*     */ 
	/* 302 */       Cooldown.remove(p);
	/* 303 */       p.sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§")) + String.valueOf(this.main.getConfig().getString("Message.KitPvpLeave-Message").replace("&", "§")));
	/* 304 */       p.getInventory().clear();
	/* 305 */       p.teleport((Location)Join.saveworld.get(p.getName()));
	/* 306 */       p.getInventory().setContents((ItemStack[])Join.saveinv.get(p.getName()));
	/* 307 */       p.setGameMode((GameMode)Join.savegamemode.get(p.getName()));
	p.setScoreboard(Join.savescore.get(p.getName()));
	p.setLevel(Join.savelevel.get(p.getName()));
	p.setFoodLevel(Join.savehunger.get(p.getName()));
	p.setRemainingAir(Join.saveair.get(p.getName()));
	/* 308 */       p.getInventory().setArmorContents((ItemStack[])Join.savearmor.get(p.getName()));
	TitleAPI.sendTitle(p, Integer.valueOf(40), Integer.valueOf(80), Integer.valueOf(40), this.main.getConfig().getString("Title.LeaveTitle"), this.main.getConfig().getString("Title.LeaveSubTitle"));

	/*     */   
	/* 311 */       p.updateInventory();
	API.tirarEfeitos(p);
}
/*     */   } 

/*     */ 
/*     */   private ItemStack make(Material material, int amount, int shrt, String displayName, List<String> lore)
/*     */   {
/* 237 */     ItemStack item = new ItemStack(material, amount, (short)shrt);
/* 238 */     ItemMeta meta = item.getItemMeta();
/* 239 */     meta.setDisplayName(displayName);
/* 240 */     meta.setLore(lore);
/* 241 */     item.setItemMeta(meta);
/* 242 */     return item;
/*     */   }
/*     */ 
/*     */   @EventHandler
/*     */   public void quickcommand(PlayerCommandPreprocessEvent e)
/*     */   {
/* 336 */     if ((e.getMessage().equalsIgnoreCase("/kitpvp kit")) && (Join.game.contains(e.getPlayer().getName()))) {
/* 337 */       e.setCancelled(true);
/* 338 */       Player p = e.getPlayer();
/* 339 */       p.chat("/kpkitmenu");
/*     */       
/* 341 */       if (!Join.game.contains(e.getPlayer().getName()))
/*     */       {
/* 343 */         e.getPlayer().sendMessage("§cYou must be in game to open kit menu!");
/* 344 */         e.getPlayer().closeInventory();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void quickcommand1(PlayerCommandPreprocessEvent e)
/*     */   {
/* 352 */     if (e.getMessage().equalsIgnoreCase("/kitpvp kits")) {
/* 353 */       e.setCancelled(true);
/* 354 */       Player p = e.getPlayer();
/* 355 */       p.chat("/kpkitmenu");
/* 356 */       if (!Join.game.contains(e.getPlayer().getName()))
/*     */       {
/* 358 */         e.getPlayer().sendMessage("§cYou must be in game to open kit menu!");
/* 359 */         e.getPlayer().closeInventory();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void quickcommand2(PlayerCommandPreprocessEvent e)
/*     */   {
/* 367 */     if (e.getMessage().equalsIgnoreCase("/kitpvp kitmenu")) {
/* 368 */       e.setCancelled(true);
/* 369 */       Player p = e.getPlayer();
/* 370 */       p.chat("/kpkitmenu");
/* 371 */       if (!Join.game.contains(e.getPlayer().getName()))
/*     */       {
/* 373 */         e.getPlayer().sendMessage("§cYou must be in game to open kit menu!");
/* 374 */         e.getPlayer().closeInventory();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void quickcommand3(PlayerCommandPreprocessEvent e)
/*     */   {
/* 382 */     if (e.getMessage().equalsIgnoreCase("/kpkits")) {
/* 383 */       e.setCancelled(true);
/* 384 */       Player p = e.getPlayer();
/* 385 */       p.chat("/kpkitmenu");
/*     */       
/*     */ 
/* 388 */       if (!Join.game.contains(e.getPlayer().getName()))
/*     */       {
/* 390 */         e.getPlayer().sendMessage("§cYou must be in game to open kit menu!");
/* 391 */         e.getPlayer().closeInventory();
/*     */       }
/*     */     }
/*     */   }
/*     */   @EventHandler
/*     */   public void quickcommand3f(PlayerCommandPreprocessEvent e)
/*     */   {
/* 382 */     if (e.getMessage().equalsIgnoreCase("/kp kits")) {
/* 383 */       e.setCancelled(true);
/* 384 */       Player p = e.getPlayer();
/* 385 */       p.chat("/kpkitmenu");
/*     */       
/*     */ 
/* 388 */       if (!Join.game.contains(e.getPlayer().getName()))
/*     */       {
/* 390 */         e.getPlayer().sendMessage("§cYou must be in game to open kit menu!");
/* 391 */         e.getPlayer().closeInventory();
/*     */       }
/*     */     }
/*     */   }
@EventHandler
/*     */   public void quickcommand3f(PlayerChangedWorldEvent e) {
	if (e.getFrom().equals(Bukkit.getWorld(Main.plugin.getConfig().getString("Spawn.World"))) && Join.game.contains(e.getPlayer().getName())) {
Player p = e.getPlayer();
if (Main.getInstance().getConfig().getBoolean("DisableWorldLeaveKitPvPEvent")) {
	return;
}
if (Main.plugin.getConfig().getBoolean("PlayersRemainOnKitPvPOnLeave")) {
	return;
}
    	if (p.getGameMode().equals(GameMode.SPECTATOR)) {
    		return;
    	}
    	if (Main.plugin.getConfig().getBoolean("bungeemode")) {
    		return;
    	}
    	/* 279 */       Habilidade.removeAbility(p);
    	/* 280 */       Deshfire.Armadura.remove(p);
    	/* 281 */       Deshfire.Armadura2.remove(p);
    	/* 282 */       Deshfire.cooldownm.remove(p);
    	/* 283 */       Join.game.remove(p.getName());
    	/* 284 */       Join.game.remove(p.getName());
    	/* 285 */       Join.game.remove(p.getName());
    	/* 286 */       Join.game.remove(p.getName());
    	/* 287 */       Join.game.remove(p.getName());
    	/* 288 */       Join.game.remove(p.getName());
    	/* 289 */       Join.game.remove(p.getName());
    	/* 290 */       Join.game.remove(p.getName());
    	/* 291 */       Join.game.remove(p.getName());
    	/* 292 */       Join.game.remove(p.getName());
    	/* 293 */       Join.game.remove(p.getName());
    	/* 294 */       Join.game.remove(p.getName());Join.game.remove(p.getName());
    	/* 295 */       Join.game.remove(p.getName());
    	/* 296 */       Join.game.remove(p.getName());
    	/* 297 */       Join.game.remove(p.getName());

    	/*     */ 
    	/*     */ 
    	/*     */ 
    	/* 302 */       Cooldown.remove(p);
    	/* 303 */       p.sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§")) + String.valueOf(this.main.getConfig().getString("Message.KitPvpLeave-Message").replace("&", "§")));
    	/* 304 */       p.getInventory().clear();
    	/* 305 */       p.teleport((Location)Join.saveworld.get(p.getName()));
    	/* 306 */       p.getInventory().setContents((ItemStack[])Join.saveinv.get(p.getName()));
    	/* 307 */       p.setGameMode((GameMode)Join.savegamemode.get(p.getName()));
    	p.setScoreboard(Join.savescore.get(p.getName()));
    	p.setLevel(Join.savelevel.get(p.getName()));
    	p.setFoodLevel(Join.savehunger.get(p.getName()));
    	p.setRemainingAir(Join.saveair.get(p.getName()));
    	/* 308 */       p.getInventory().setArmorContents((ItemStack[])Join.savearmor.get(p.getName()));
    	TitleAPI.sendTitle(p, Integer.valueOf(40), Integer.valueOf(80), Integer.valueOf(40), this.main.getConfig().getString("Title.LeaveTitle"), this.main.getConfig().getString("Title.LeaveSubTitle"));

    	/*     */   
    	/* 311 */       p.updateInventory();
    	API.tirarEfeitos(p);
          NewKitMenu.playSound(p, this.main.getConfig().getString("Sound.CommandSounds"), 1.0F, 1.0F);
	/*    */   }  }
	

/*     */   
/*     */   
/*     */ 
/*     */   @EventHandler
/*     */   public void quickcommand4(PlayerCommandPreprocessEvent e)
/*     */   {
/* 400 */     if (e.getMessage().equalsIgnoreCase("/kpkit")) {
/* 401 */       e.setCancelled(true);
/* 402 */       Player p = e.getPlayer();
/* 403 */       p.chat("/kpkitmenu");
/* 404 */       if (!Join.game.contains(e.getPlayer().getName()))
/*     */       {
/* 406 */         e.getPlayer().sendMessage("§cYou must be in game to open kit menu!");
/* 407 */         e.getPlayer().closeInventory();
/*     */       }
/*     */     }
/*     */   }
/*     */   @EventHandler
/*     */   public void quickcommand4t(PlayerCommandPreprocessEvent e)
/*     */   {
/* 400 */     if (e.getMessage().equalsIgnoreCase("/spawn") && Main.plugin.getConfig().getBoolean("KP-Spawn-Command-Enabled")) {
/* 401 *
/* 402 */       Player p = e.getPlayer();
/* 403 */       
/* 404 */       if (Join.game.contains(p.getName()))
/*     */       {
	if (CombatLog.emCombate(p)) {
		p.sendMessage("§cYou are in combat!");
		e.setCancelled(true);	
		return;
	}
	if (X1.lutadores.containsKey(p.getName())) {
		p.sendMessage("§cYou are in combat!");
		e.setCancelled(true);
		return;
	}
	 p.sendMessage("§eTeleporting");
	 e.setCancelled(true);
	 p.getInventory().clear();
	 p.setHealth(1.0);
	 X1.inx1.remove(p);
	 X1.inx1.remove(p);
	 X1.inx1.remove(p);
	 X1.inx1.remove(p);
	 X1.inx1.remove(p);
	 X1.inx1.remove(p);
	 X1.inx1.remove(p);
	 X1.inx1.remove(p);
		/*     */       p.setGameMode(GameMode.SURVIVAL);
	 X1.inx1.remove(p);
	 X1.inx1.remove(p);
	 X1.inx1.remove(p);
	 Cooldown.remove(p);
	 X1.inx1.remove(p);
	 X1.inx1.remove(p);
	 X1.inx1.remove(p);
	 X1.inx1.remove(p);
	 X1.inx1.remove(p);
	 X1.inx1.remove(p);
	 X1.inx1.remove(p);
	 API.darEfeito(p, PotionEffectType.SLOWNESS, 99, 6);
	Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable()
			{
      public void run()
      {
/* 406 */   for (PotionEffect effect : p.getActivePotionEffects()) {
	/*  70 */         p.removePotionEffect(effect.getType());
	/*     */       }
	/*  72 */       p.sendMessage(String.valueOf(Main.getInstace().getConfig().getString("Prefix").replace("&", "§")) + Main.getInstace().getConfig().getString("Message.Clear").replace("&", "§"));
	/*  73 */       p.getInventory().clear();
	/*  74 */       p.getInventory().setHelmet(new ItemStack(Material.AIR));
	/*  75 */       p.getInventory().setChestplate(new ItemStack(Material.AIR));
	/*  76 */       Habilidade.removeAbility(p);
	/*  77 */       me.RafaelAulerDeMeloAraujo.SpecialAbility.Cooldown.remove(p);
	/*  78 */       Deshfire.cooldownm.remove(p);
	/*  79 */       Deshfire.armadura.remove(p);
	/*  80 */       Gladiator.lutando.remove(p);
	/*  81 */       Gladiator.gladgladiator.remove(p);
	/*  82 */       org.bukkit.World w = org.bukkit.Bukkit.getServer().getWorld(Main.plugin.getConfig().getString("Spawn.World"));
	/*  83 */       double x = Main.plugin.getConfig().getDouble("Spawn.X");
	/*  84 */       double y = Main.plugin.getConfig().getDouble("Spawn.Y");
	/*  85 */       double z = Main.plugin.getConfig().getDouble("Spawn.Z");
	/*  86 */       Location lobby = new Location(w, x, y, z);
	/*  87 */       lobby.setPitch((float)Main.plugin.getConfig().getDouble("Spawn.Pitch"));
	/*  88 */       lobby.setYaw((float)Main.plugin.getConfig().getDouble("Spawn.Yaw"));
	/*  89 */       p.getInventory().clear();
	/*  90 */       p.teleport(lobby);
	/*     */      API.vida(p);
	/*  92 */       p.getInventory().setLeggings(new ItemStack(Material.AIR));
	/*  93 */       p.getInventory().setBoots(new ItemStack(Material.AIR));
	/*  94 */       p.getInventory().setArmorContents(null);
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
	ItemStack sair = Main.getInstance().getConfig().getItemStack("LeaveItem");
	/* 227 */           ItemMeta sair2 = sair.getItemMeta();
	/* 228 */           sair2.setDisplayName(Main.messages.getString("LeaveItemName").replace("&", "§"));
	/* 229 */           sair.setItemMeta(sair2);
	if (!Main.getInstance().getConfig().getBoolean("DisableLeaveItem")) {
		p.getInventory().setItem(Main.getInstance().getConfig().getInt("LeaveItemSlot"), sair);
	}
	/* 103 */     
	if (!Main.getInstance().getConfig().getBoolean("DisableClickTestItem")) {
		p.getInventory().setItem(Main.getInstance().getConfig().getInt("ClickTestItemSlot"), stats1);
		}
	p.getInventory().setItem(Main.getInstance().getConfig().getInt("KitsItemSlot"), kitsr);
	/* 103 */     	p.getInventory().setItem(Main.getInstance().getConfig().getInt("ShopItemSlot"), kits);
	/* 104 */       	p.getInventory().setItem(Main.getInstance().getConfig().getInt("1v1ItemSlot"), st);
	/*     */       
	/*     */ 
	/* 107 */       p.updateInventory();	/* 107 */       p.updateInventory();
	/*     */       p.setAllowFlight(false);
	API.vida(p);

	/* 107 */    
	/*     */       
	/*     */ API.tirarEfeitos(p);
	if (Main.getInstance().getConfig().getBoolean("DisableInitialItems")) {
		 p.getInventory().clear();
	 }

	 GiveKitUnlocker.GiveUnlockers(p);
	/* 107 */       p.updateInventory();
/*     */         

/*     */         
/*     */ 
/*     */ 
/*     */ 
/* 159 */         
    NewKitMenu.playSound(p, "UI_BUTTON_CLICK", 1.0F, 1.0F);  } }, 10L);
	}
}

/* 407 */        
/*     */       
/*     */     
/*     */   } 
@EventHandler
public void onBauKit(PlayerInteractEvent e)
{
  Player p = e.getPlayer();
  if ((p.getItemInHand().equals(Main.getInstance().getConfig().getItemStack("ShopItem"))) && (p.getItemInHand().getItemMeta().hasDisplayName()) && !Habilidade.ContainsAbility(p) && Join.game.contains(p.getName()))
  {
    e.setCancelled(true);
    if ((e.getAction() == Action.RIGHT_CLICK_AIR) || (e.getAction() == Action.RIGHT_CLICK_BLOCK))
    {
      p.openInventory(Shop.shop);
      NewKitMenu.playSound(p, "UI_BUTTON_CLICK", 1.0F, 1.0F);  }   	  }
}
@EventHandler
public void onLeaveKit(PlayerInteractEvent e)
{
  Player p = e.getPlayer();
  if ((p.getItemInHand().equals(Main.getInstance().getConfig().getItemStack("LeaveItem"))) && (p.getItemInHand().getItemMeta().hasDisplayName()) && !Habilidade.ContainsAbility(p) && Join.game.contains(p.getName()))
  {
    e.setCancelled(true);
    if ((e.getAction() == Action.RIGHT_CLICK_AIR) || (e.getAction() == Action.RIGHT_CLICK_BLOCK))
    {
    	if ((!Join.game.contains(p.getName()))) {
    		p.sendMessage(API.NomeServer + Main.messages.getString("MustBeInGame").replace("&", "§"));
    		return;
    	}
    	if (p.getGameMode().equals(GameMode.SPECTATOR)) {
    		p.sendMessage(API.NomeServer + Main.messages.getString("NeedToRespawn").replace("&", "§"));
    		return;
    	}
    	if (Main.plugin.getConfig().getBoolean("bungeemode")) {
    		p.sendMessage(API.NomeServer + Main.messages.getString("BungeeModeEnabled").replace("&", "§"));
    		return;
    	}
    	/*     */       
    	/*     */ 
    	/*     */ 
    	/*     */ 
    	/*     */ 
    	/*     */ 
    	/* 279 */       Habilidade.removeAbility(p);
    	/* 280 */       Deshfire.Armadura.remove(p);
    	/* 281 */       Deshfire.Armadura2.remove(p);
    	/* 282 */       Deshfire.cooldownm.remove(p);
    	/* 283 */       Join.game.remove(p.getName());
    	/* 284 */       Join.game.remove(p.getName());
    	/* 285 */       Join.game.remove(p.getName());
    	/* 286 */       Join.game.remove(p.getName());
    	/* 287 */       Join.game.remove(p.getName());
    	/* 288 */       Join.game.remove(p.getName());
    	/* 289 */       Join.game.remove(p.getName());
    	/* 290 */       Join.game.remove(p.getName());
    	/* 291 */       Join.game.remove(p.getName());
    	/* 292 */       Join.game.remove(p.getName());
    	/* 293 */       Join.game.remove(p.getName());
    	/* 294 */       Join.game.remove(p.getName());Join.game.remove(p.getName());
    	/* 295 */       Join.game.remove(p.getName());
    	/* 296 */       Join.game.remove(p.getName());
    	/* 297 */       Join.game.remove(p.getName());

    	/*     */ 
    	/*     */ 
    	/*     */ 
    	/* 302 */       Cooldown.remove(p);
    	/* 303 */       p.sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§")) + String.valueOf(this.main.getConfig().getString("Message.KitPvpLeave-Message").replace("&", "§")));
    	/* 304 */       p.getInventory().clear();
    	/* 305 */       p.teleport((Location)Join.saveworld.get(p.getName()));
    	/* 306 */       p.getInventory().setContents((ItemStack[])Join.saveinv.get(p.getName()));
    	/* 307 */       p.setGameMode((GameMode)Join.savegamemode.get(p.getName()));
    	p.setScoreboard(Join.savescore.get(p.getName()));
    	p.setLevel(Join.savelevel.get(p.getName()));
    	p.setFoodLevel(Join.savehunger.get(p.getName()));
    	p.setRemainingAir(Join.saveair.get(p.getName()));
    	/* 308 */       p.getInventory().setArmorContents((ItemStack[])Join.savearmor.get(p.getName()));
    	TitleAPI.sendTitle(p, Integer.valueOf(40), Integer.valueOf(80), Integer.valueOf(40), this.main.getConfig().getString("Title.LeaveTitle"), this.main.getConfig().getString("Title.LeaveSubTitle"));

    	/*     */   
    	/* 311 */       p.updateInventory();
    	API.tirarEfeitos(p);

        NewKitMenu.playSound(p, "UI_BUTTON_CLICK", 1.0F, 1.0F);  
    }
    	
    
  }
}
@EventHandler
public void onKit(PlayerInteractEvent e)
{
  Player p = e.getPlayer();
  if ((p.getItemInHand().equals(Main.getInstance().getConfig().getItemStack("KitsItem"))) && (p.getItemInHand().getItemMeta().hasDisplayName()) && !Habilidade.ContainsAbility(p) && Join.game.contains(p.getName()))
  {
    e.setCancelled(true);
    if ((e.getAction() == Action.RIGHT_CLICK_AIR) || (e.getAction() == Action.RIGHT_CLICK_BLOCK))
    {
      NewKitMenu.OPENKIT(p);
      NewKitMenu.playSound(p, Main.getInstace().getConfig().getString("Sound.ShopMenu"), 1.0F, 1.0F);

    }
  }
}
@EventHandler
public void onKit2(PlayerInteractEvent e)
{
  Player p = e.getPlayer();
  if (p.getItemInHand().equals(Main.getInstance().getConfig().getItemStack("ClickTestItem")) &&  (p.getItemInHand().getItemMeta().hasDisplayName()) && !Habilidade.ContainsAbility(p) && Join.game.contains(p.getName()))
  {
    e.setCancelled(true);
    if ((e.getAction() == Action.RIGHT_CLICK_AIR) || (e.getAction() == Action.RIGHT_CLICK_BLOCK))
    {
      ClickTest.StartClick(p);
    }
  }
}

@EventHandler
public void onStats(PlayerInteractEvent e)
{
  Player p = e.getPlayer();
  if ((p.getItemInHand().equals(Main.getInstance().getConfig().getItemStack("StatsItem")))  && (p.getItemInHand().getItemMeta().hasDisplayName()) && !Habilidade.ContainsAbility(p) && Join.game.contains(p.getName()))
  {
    e.setCancelled(true);
    if ((e.getAction() == Action.RIGHT_CLICK_AIR) || (e.getAction() == Action.RIGHT_CLICK_BLOCK))
    {
      StatusGUI.openGUI(p, p);
    }
  }
}
@EventHandler
public void onStats2(PlayerInteractEvent e)
{
  Player p = e.getPlayer();
  if ((p.getItemInHand().equals(Main.getInstance().getConfig().getItemStack("WarpItem")))  && (p.getItemInHand().getItemMeta().hasDisplayName()) && !Habilidade.ContainsAbility(p) && Join.game.contains(p.getName()))
  {
    e.setCancelled(true);
    if ((e.getAction() == Action.RIGHT_CLICK_AIR) || (e.getAction() == Action.RIGHT_CLICK_BLOCK))
    {
      WarpMenu.openwarp(p);
      NewKitMenu.playSound(p, Main.getInstace().getConfig().getString("Sound.ShopMenu"), 1.0F, 1.0F);
    }
  }
}
@EventHandler
public void v1(PlayerInteractEvent e)
{
  Player p = e.getPlayer();
  if ((p.getItemInHand().equals(Main.getInstance().getConfig().getItemStack("1v1Item")))  && (p.getItemInHand().getItemMeta().hasDisplayName()) && !Habilidade.ContainsAbility(p) && Join.game.contains(p.getName()))
  {
    e.setCancelled(true);
    if ((e.getAction() == Action.RIGHT_CLICK_AIR) || (e.getAction() == Action.RIGHT_CLICK_BLOCK))
    {
    	if (Main.cfg_x1.getString("x1.coords.spawn.world") == null) {
    		p.sendMessage(ChatColor.YELLOW + "The KitPvP 1vs1 is not seted yet!");
    		return;
    	}
      X1.entrar1v1(p);
      NewKitMenu.playSound(p, Main.getInstance().getConfig().getString("Sound.ShopMenu"), 1.0F, 1.0F);  
    }
  }
}   
/*     */
@Override
public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
	// TODO Auto-generated method stub
	return false;
}
}

