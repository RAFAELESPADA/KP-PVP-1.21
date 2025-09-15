/*     */ package me.RafaelAulerDeMeloAraujo.SpecialAbility;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;

/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.GameMode;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.World;
/*     */ import org.bukkit.command.Command;
/*     */ import org.bukkit.command.CommandExecutor;
/*     */ import org.bukkit.command.CommandSender;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.EventHandler;
/*     */ import org.bukkit.event.Listener;
/*     */ import org.bukkit.event.player.PlayerCommandPreprocessEvent;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.meta.ItemMeta;
/*     */ import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.scoreboard.Scoreboard;

import me.RafaelAulerDeMeloAraujo.Listeners.UpdateUtil;
/*     */ import me.RafaelAulerDeMeloAraujo.TitleAPI.TitleAPI;
/*     */ import me.RafaelAulerDeMeloAraujo.X1.X1;
import me.RafaelAulerDeMeloAraujo.main.GiveKitUnlocker;
/*     */ import me.RafaelAulerDeMeloAraujo.main.Main;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Join
/*     */   implements CommandExecutor, Listener
/*     */ {
/*  43 */   public static HashMap<String, ItemStack[]> saveinv = new HashMap<String, ItemStack[]>();
/*  44 */   public static HashMap<String, ItemStack[]> savearmor = new HashMap<String, ItemStack[]>();
/*  45 */   public static HashMap<String, Location> saveworld = new HashMap<String, Location>();
/*  46 */   public static HashMap<String, GameMode> savegamemode = new HashMap<String, GameMode>();
public static HashMap<String, Scoreboard> savescore = new HashMap<String, Scoreboard>();
public static HashMap<String, Integer> savelevel = new HashMap<String, Integer>();
public static HashMap<String, Integer> savehunger = new HashMap<String, Integer>();
public static HashMap<String, PotionEffect> saveeffect = new HashMap<String, PotionEffect>();
public static HashMap<String, Integer> saveair = new HashMap<String, Integer>();

/*     */ 
/*     */   private Main main;
/*     */   
/*     */ 
/*     */   static Main plugin;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Join(Main main)
/*     */   {
/*  61 */     this.main = main;
/*  62 */     plugin = main;
/*     */   }
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
/*  76 */   HashMap<String, Location> maps = new HashMap<String, Location>();
/*  77 */   public static ArrayList<String> game = new ArrayList<String>();
public static ArrayList<Player> player = new ArrayList<Player>();
/*  78 */   List<String> commands = Arrays.asList(new String[] { "admin", "list", "create", "delete", "1v1", "score", "setspawn", "join", "leave", "reset", "coins", "setchallenge", "kit", "kitunlocker", "resetkit", "stats", "reload", "update" });
/*     */   
/*     */   public Join() {}
/*     */   

/*     */   
/*     */   public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
/*  91 */     if (commandLabel.equalsIgnoreCase("kitpvp") || (commandLabel.equalsIgnoreCase("kp")))
/*     */     {
/*  93 */       if (args.length == 0)
/*     */       {

/* 106 */         sender.sendMessage(ChatColor.DARK_AQUA + "§m-----------" + ChatColor.AQUA + " KP-PVP COMMANDS " + ChatColor.DARK_AQUA + ChatColor.STRIKETHROUGH + "-------------");
/* 107 */         sender.sendMessage(ChatColor.DARK_AQUA + "§eCreated by Rafael Auler");
/* 108 */         sender.sendMessage("");
/* 109 */         sender.sendMessage(ChatColor.DARK_AQUA + " - " + ChatColor.AQUA + "/" + commandLabel + ChatColor.DARK_AQUA + " - " + ChatColor.GRAY + "Main command");
/* 110 */         sender.sendMessage(ChatColor.DARK_AQUA + " - " + ChatColor.AQUA + "/" + commandLabel + " " + ChatColor.GREEN + "join" + ChatColor.DARK_AQUA + " - " + ChatColor.GRAY + "Join the kitpvp!");
/* 111 */         sender.sendMessage(ChatColor.DARK_AQUA + " - " + ChatColor.AQUA + "/" + commandLabel + " " + ChatColor.GREEN + "leave" + ChatColor.DARK_AQUA + " - " + ChatColor.GRAY + "Leave the kitpvp!");
/* 112 */         sender.sendMessage(ChatColor.DARK_AQUA + " - " + ChatColor.AQUA + "/" + commandLabel + " " + ChatColor.GREEN + "kits" + ChatColor.DARK_AQUA + " - " + ChatColor.GRAY + "Open kit menu!");
/* 113 */         sender.sendMessage(ChatColor.DARK_AQUA + " - " + ChatColor.AQUA + "/" + commandLabel + " " + ChatColor.GREEN + "shop" + ChatColor.DARK_AQUA + " - " + ChatColor.GRAY + "Open shop menu!");
/* 114 */         sender.sendMessage(ChatColor.DARK_AQUA + " - " + ChatColor.AQUA + "/" + commandLabel + " " + ChatColor.GREEN + "1v1" + ChatColor.DARK_AQUA + " - " + ChatColor.GRAY + "Join kitpvp 1v1!");
/*     */         sender.sendMessage(ChatColor.DARK_AQUA + " - " + ChatColor.AQUA + "/" + commandLabel + " " + ChatColor.GREEN + "clear" + ChatColor.DARK_AQUA + " - " + ChatColor.GRAY + "Clear your kit and return to kitpvp spawn");                              
/* 116 */         sender.sendMessage(ChatColor.DARK_AQUA + " - " + ChatColor.AQUA + "/" + commandLabel + " " + ChatColor.GREEN + "info" + ChatColor.DARK_AQUA + " - " + ChatColor.GRAY + "Shows plugin info");
/* 117 */         sender.sendMessage(ChatColor.DARK_AQUA + " - " + ChatColor.AQUA + "/" + commandLabel + " " + ChatColor.GREEN + "update" + ChatColor.DARK_AQUA + " - " + ChatColor.RED + "Get a link to verify updates");
/* 118 */         sender.sendMessage(ChatColor.DARK_AQUA + " - " + ChatColor.AQUA + "/" + commandLabel + " " + ChatColor.GREEN + "reload" + ChatColor.DARK_AQUA + " - " + ChatColor.RED + "Reload config files");
/* 119 */         sender.sendMessage(ChatColor.DARK_AQUA + " - " + ChatColor.AQUA + "/kphelp" + ChatColor.DARK_AQUA + " - " + ChatColor.GRAY + "View the full command list");
/* 120 */         sender.sendMessage(ChatColor.DARK_AQUA + "§m------------------------------------------");

NewKitMenu.playSound((Player)sender, Main.getInstace().getConfig().getString("Sound.SucefullMessage"), 1.0F, 1.0F);
/* 102 */         return true;
/*     */       }
/* 104 */       if (args[0].equalsIgnoreCase("info"))
/*     */       {
	/* 27 */       sender.sendMessage("§4§l\u274C §2§lCREDITS §f§lAND §e§lINFORMATION §4§l \u274C");
	/* 28 */       sender.sendMessage("§6\u279C §cPlugin Name: §eKP-PVP");
	/* 29 */       sender.sendMessage("§6\u279C §cPlugin Version: §e " + Main.getInstance().getDescription().getVersion());
	/* 30 */       sender.sendMessage("§6\u279C §cAuthor: §ezEnderX5_ , Rafael Auler");
	/* 31 */       sender.sendMessage("§6\u279C §cAuthor Channel: http://bit.ly/2kC345B");
	/* 32 */       sender.sendMessage("§6\u279C §cSpigot Profile: http://bit.ly/2j5qvnM");
	/* 33 */       sender.sendMessage("§6\u279C §cPlugin Page: http://bit.ly/2BZCtLE");
	/* 34 */       sender.sendMessage("§cThanks for use this plugin i really appreaciate IT");
	/* 35 */       sender.sendMessage("§cIf you like it consider giving a §e§l\u2605\u2605\u2605\u2605\u2605 §cReview");
	/* 36 */       sender.sendMessage("§cPS: §eSubscribe to my channel and follow me on Spigot Thanks! §9§l=)");
	NewKitMenu.playSound((Player)sender, Main.getInstace().getConfig().getString("Sound.SucefullMessage"), 1.0F, 1.0F);
/* 122 */         return true;
/*     */       }
/*     */       
/* 125 */       Player p = (Player)sender;
/* 126 */       if (args[0].equalsIgnoreCase("reload"))
/*     */       {
/* 128 */         if (!sender.hasPermission("kitpvp.reload")) {
/* 129 */           p.sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§")) + this.main.getConfig().getString("Permission").replace("&", "§"));
/* 130 */  
NewKitMenu.playSound((Player)sender, Main.getInstace().getConfig().getString("Sound.SucefullMessage"), 1.0F, 1.0F);
/* 131 */           return true;
/*     */         }
/*     */         
/* 134 */         Plugin plugin = p.getServer().getPluginManager().getPlugin("KP-PVP");
/* 135 */         plugin.reloadConfig();
/* 136 */         p.getServer().getPluginManager().disablePlugin(plugin);
/* 137 */         p.getServer().getPluginManager().enablePlugin(plugin);
/* 138 */         p.sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§")) + " §eThe plugin Config has been sucefully reloaded.");
/* 139 */         return true;
/*     */       }
/* 141 */       if (args[0].equalsIgnoreCase("update"))
/*     */       {
/* 143 */         if (!sender.hasPermission("kitpvp.admin")) {
/* 144 */           sender.sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§")) + this.main.getConfig().getString("Permission").replace("&", "§"));
/* 145 */           return true;
/*     */         }
/*     */ 
UpdateUtil updateChecker = new UpdateUtil(Main.getInstance(), true);
/*     */         switch (updateChecker.check()) {
case OUT_OF_DATE:
    sender.sendMessage("§c[KitPvP] §fAn update for KP-PVP (" + updateChecker.getNewVersion() + ") was found. Please update at: https://www.spigotmc.org/resources/kp-pvp-the-ultimate-kitpvp-plugin.50969/");
    break;
case UP_TO_DATE:
	sender.sendMessage("§c[KitPvP] §fYou are on the very latest version of KP-PVP.");
default:
    break;
}
/*     */ 
/*     */ 
/*     */ 
NewKitMenu.playSound((Player)sender, Main.getInstace().getConfig().getString("Sound.SucefullMessage"), 1.0F, 1.0F);return true;
/*     */       }
/* 141 */       if (args[0].equalsIgnoreCase("clear"))
/*     */       {
	/*  58 */       if (!Join.game.contains(p.getName()))
	/*     */       {
	/*  60 */         p.sendMessage(String.valueOf("§cYou are not in kitpvp to do this!"));
	/*  61 */         return true;
	/*     */       }
	/*     */       
	/*  64 */       if (!sender.hasPermission("kitpvp.kitclear")) {
	/*  65 */         p.sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§")) + this.main.getConfig().getString("Permission").replace("&", "§").replaceAll("%permisson%", commandLabel));
	/*  66 */         return true;
	/*     */       }
	/*  69 */       for (PotionEffect effect : p.getActivePotionEffects()) {
	/*  70 */         p.removePotionEffect(effect.getType());
	/*     */       }
	if (!Main.getInstance().getConfig().getBoolean("DisableInitialItems")) {
		
	/*  72 */       p.sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§")) + this.main.getConfig().getString("Message.Clear").replace("&", "§"));
	/*  73 */       p.getInventory().clear();
	/*  74 */       p.getInventory().setHelmet(new ItemStack(Material.AIR));
	/*  75 */       p.getInventory().setChestplate(new ItemStack(Material.AIR));
	/*  76 */       Habilidade.removeAbility(p);
	/*  77 */       me.RafaelAulerDeMeloAraujo.SpecialAbility.Cooldown.remove(p);
	/*  78 */       Deshfire.cooldownm.remove(p);
	/*  79 */       Deshfire.armadura.remove(p.getName());
	/*  80 */       Gladiator.lutando.remove(p.getName());
	Cooldown.remove(p);
	/*  81 */       Gladiator.gladgladiator.remove(p.getName());
	/*  82 */       org.bukkit.World w = org.bukkit.Bukkit.getServer().getWorld(Main.plugin.getConfig().getString("Spawn.World"));
	/*  83 */       double x = Main.plugin.getConfig().getDouble("Spawn.X");
	/*  84 */       double y = Main.plugin.getConfig().getDouble("Spawn.Y");
	/*  85 */       double z = Main.plugin.getConfig().getDouble("Spawn.Z");
	/*  86 */       Location lobby = new Location(w, x, y, z);
	/*  87 */       lobby.setPitch((float)Main.plugin.getConfig().getDouble("Spawn.Pitch"));
	/*  88 */       lobby.setYaw((float)Main.plugin.getConfig().getDouble("Spawn.Yaw"));
	/*  89 */       p.getInventory().clear();
	/*  90 */       p.teleport(lobby);
	/*  92 */       p.getInventory().setLeggings(new ItemStack(Material.AIR));
	/*  93 */       p.getInventory().setBoots(new ItemStack(Material.AIR));
	p.getInventory().setArmorContents(null);
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
	ItemStack sair = Main.getInstance().getConfig().getItemStack("LeaveItem");
	/* 227 */           ItemMeta sair2 = sair.getItemMeta();
	/* 228 */           sair2.setDisplayName(Main.messages.getString("LeaveItemName").replace("&", "§"));
	/* 229 */           sair.setItemMeta(sair2);
	if (!Main.getInstance().getConfig().getBoolean("DisableLeaveItem")) {
		p.getInventory().setItem(Main.getInstance().getConfig().getInt("LeaveItemSlot"), sair);
	}
	if (!Main.getInstance().getConfig().getBoolean("DisableWarpItem")) {
		p.getInventory().setItem(Main.getInstance().getConfig().getInt("WarpItemSlot"), warp);
		}

	/* 103 */     
	if (!Main.getInstance().getConfig().getBoolean("DisableClickTestItem")) {
		p.getInventory().setItem(Main.getInstance().getConfig().getInt("ClickTestItemSlot"), stats1);
		}
	p.getInventory().setItem(Main.getInstance().getConfig().getInt("KitsItemSlot"), kitsr);
	if (!Main.getInstance().getConfig().getBoolean("DisableShopItem")) {
	/* 103 */     	p.getInventory().setItem(Main.getInstance().getConfig().getInt("ShopItemSlot"), kits);
	}
	/* 104 */       	p.getInventory().setItem(Main.getInstance().getConfig().getInt("1v1ItemSlot"), st);
	/*     */       
	/*     */ }

	 GiveKitUnlocker.GiveUnlockers(p);
	/* 107 */       p.updateInventory();
	/*     */       
	/*     */ 
	/* 107 */       
	/*     */       p.setAllowFlight(false);
	/*     */ 
	/* 107 */    
	/*     */       
	/*     */ API.tirarEfeitos(p);
	/* 107 */       p.updateInventory();
/*     */         

/*     */         
/*     */ 
/*     */ 
/*     */ 
/* 159 */         return true;
/*     */       }
/* 162 */       if (!this.commands.contains(args[0].toLowerCase()))
/*     */       {
/* 164 */         sender.sendMessage(ChatColor.DARK_AQUA + String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§")) + " §eUnknown command");
/* 165 */         return true;
/*     */       }
/* 167 */       if (args[0].equalsIgnoreCase("join"))
/*     */       {
/* 169 */         if ((sender instanceof Player))
/*     */         {
/* 171 */           if (game.contains(sender.getName()))
/*     */           {
/* 173 */             sender.sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§") + "§cYou are already on kitpvp!"));
/* 174 */             return true;
/*     */           }
/*     */           
/*     */ 
/*     */ 
/* 179 */           p.sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§")) + String.valueOf(this.main.getConfig().getString("Message.KitPvpJoin-Message").replace("&", "§")));
/*     */           
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 185 */           game.add(p.getName());
/*     */           
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 

}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
;
		/* 200 */           World w = Bukkit.getServer().getWorld(Main.plugin.getConfig().getString("Spawn.World"));
		/* 201 */           double x = Main.plugin.getConfig().getDouble("Spawn.X");
		/* 202 */           double y = Main.plugin.getConfig().getDouble("Spawn.Y");
		/* 203 */           double z = Main.plugin.getConfig().getDouble("Spawn.Z");
		/* 204 */           Location lobby = new Location(w, x, y, z);
		/* 205 */           saveworld.put(p.getName(), p.getLocation());
		                    savescore.put(p.getName(), p.getScoreboard());
		/* 206 */           saveinv.put(p.getName(), p.getInventory().getContents());
		/* 207 */           savearmor.put(p.getName(), p.getInventory().getArmorContents());
		/* 208 */           savegamemode.put(p.getName(), p.getGameMode());
		/*     */           savelevel.put(p.getName(), p.getLevel());
		savehunger.put(p.getName(), p.getFoodLevel());
		saveair.put(p.getName(), p.getRemainingAir());
		/*     */ 
		/* 211 */           lobby.setPitch((float)Main.plugin.getConfig().getDouble("Spawn.Pitch"));
		/* 212 */           lobby.setYaw((float)Main.plugin.getConfig().getDouble("Spawn.Yaw"));
		/* 213 */           p.getInventory().clear();
/* 216 */           p.teleport(lobby);
/*     */           
/*     */ 
/* 219 */           p.getInventory().clear();
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
ItemStack sair = Main.getInstance().getConfig().getItemStack("LeaveItem");
/* 227 */           ItemMeta sair2 = sair.getItemMeta();
/* 228 */           sair2.setDisplayName(Main.messages.getString("LeaveItemName").replace("&", "§"));
/* 229 */           sair.setItemMeta(sair2);
if (!Main.getInstance().getConfig().getBoolean("DisableLeaveItem")) {
	p.getInventory().setItem(Main.getInstance().getConfig().getInt("LeaveItemSlot"), sair);
}
if (!Main.getInstance().getConfig().getBoolean("DisableWarpItem")) {
	p.getInventory().setItem(Main.getInstance().getConfig().getInt("WarpItemSlot"), warp);
	}

/* 103 */     
if (!Main.getInstance().getConfig().getBoolean("DisableClickTestItem")) {
	p.getInventory().setItem(Main.getInstance().getConfig().getInt("ClickTestItemSlot"), stats1);
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
/*     */           
/*     */ 

/*     */ 
/* 235 */           p.setExp(0.0F);
/* 236 */           p.setExhaustion(20.0F);
/* 237 */           p.setFireTicks(0);
/* 238 */           p.setFoodLevel(20000);
/* 239 */           TitleAPI.sendTitle(p, Integer.valueOf(40), Integer.valueOf(80), Integer.valueOf(40), this.main.getConfig().getString("Title.JoinTitle"), this.main.getConfig().getString("Title.JoinSubTitle"));
API.tirarEfeitos(p);

}
if (args[0].equalsIgnoreCase("leave"))
/*     */       {
	if ((!game.contains(p.getName()))) {
		p.sendMessage(API.NomeServer + Main.messages.getString("MustBeInGame").replace("&", "§"));
		return true;
	}
	if (p.getGameMode().equals(GameMode.SPECTATOR)) {
		p.sendMessage(API.NomeServer + Main.messages.getString("NeedToRespawn").replace("&", "§"));
		return true;
	}
	if (Main.plugin.getConfig().getBoolean("bungeemode")) {
		p.sendMessage(API.NomeServer + Main.messages.getString("BungeeModeEnabled").replace("&", "§"));
		return true;
	}
	/*     */       
	/*     */ 
	/*     */ 
	/*     */ 
	/*     */ 
	/*     */ 
	/* 279 */       Habilidade.removeAbility(p);
	/* 280 */       Deshfire.Armadura.remove(p.getName());
	/* 281 */       Deshfire.Armadura2.remove(p.getName());
	/* 282 */       Deshfire.cooldownm.remove(p);
	/* 283 */       game.remove(p.getName());
	/* 284 */       game.remove(p.getName());
	/* 285 */       game.remove(p.getName());
	/* 286 */       game.remove(p.getName());
	/* 287 */       game.remove(p.getName());
	/* 288 */       game.remove(p.getName());
	Cooldown.remove(p);
	/* 289 */       game.remove(p.getName());
	/* 290 */       game.remove(p.getName());
	/* 291 */       game.remove(p.getName());
	/* 292 */       game.remove(p.getName());
	/* 293 */       game.remove(p.getName());
	/* 294 */       game.remove(p.getName());game.remove(p.getName());
	/* 295 */       game.remove(p.getName());
	/* 296 */       game.remove(p.getName());
	/* 297 */       game.remove(p.getName());

	/*     */ 
	/*     */ 
	/*     */ 
	/* 302 */       Cooldown.remove(p);
	/* 303 */       p.sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§")) + String.valueOf(this.main.getConfig().getString("Message.KitPvpLeave-Message").replace("&", "§")));
	/* 304 */       p.getInventory().clear();
	/* 305 */       p.teleport((Location)saveworld.get(p.getName()));
	/* 306 */       p.getInventory().setContents((ItemStack[])saveinv.get(p.getName()));
	/* 307 */       p.setGameMode((GameMode)savegamemode.get(p.getName()));
	p.setScoreboard(savescore.get(p.getName()));
	p.setLevel(savelevel.get(p.getName()));
	p.setFoodLevel(savehunger.get(p.getName()));
	p.setRemainingAir(saveair.get(p.getName()));
	/* 308 */       p.getInventory().setArmorContents((ItemStack[])savearmor.get(p.getName()));
	TitleAPI.sendTitle(p, Integer.valueOf(40), Integer.valueOf(80), Integer.valueOf(40), this.main.getConfig().getString("Title.LeaveTitle"), this.main.getConfig().getString("Title.LeaveSubTitle"));

	/*     */   
	/* 311 */       p.updateInventory();
	API.tirarEfeitos(p);

	/*     */     }
}




/*     */         
/*     */       
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 247 */     
return false;
}
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
/* 260 */

/*     */   
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   /*     */     
/*     */ 
/*     */ 
/* 316 */   
/*     */   
/*     */   @EventHandler
/*     */   public void quickcommand7(PlayerCommandPreprocessEvent e)
/*     */   {
/* 322 */     Player p = e.getPlayer();
/*     */     
/* 324 */     if ((e.getMessage().equalsIgnoreCase("/kitpvp 1v1"))) {
	/* 327 */       if (!game.contains(e.getPlayer().getName()))
	/*     */       {
	/* 329 */         e.getPlayer().sendMessage("§cYou must be in game to join 1v1!");
	/* 330 */         e.setCancelled(true);
	return;
	/*     */       }else{
/* 325 */       e.setCancelled(true);
/* 326 */       X1.entrar1v1(p);
	}
}
/*     */     }
	/*     */   @EventHandler
	/*     */   public void quickcommand8(PlayerCommandPreprocessEvent e)
	/*     */   {
	/* 322 */     Player p = e.getPlayer();
	/*     */     
	/* 324 */     if ((e.getMessage().equalsIgnoreCase("/kp 1v1"))) {
		/* 327 */       if (!game.contains(e.getPlayer().getName()))
		/*     */       {
		/* 329 */         e.getPlayer().sendMessage("§cYou must be in game to join 1v1!");
		/* 330 */         e.setCancelled(true);
		return;
		/*     */       }else{
	/* 325 */       e.setCancelled(true);
	/* 326 */       X1.entrar1v1(p);

	/*     */     }
/*     */     
/* 334 */  
/*     */  

/*     */ }
}}


/* Location:              D:\Desktop\video\Minhas Coisas do Desktop\KP-PVPvB12 (1).jar!\me\RafaelAulerDeMeloAraujo\SpecialAbility\Join.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */
