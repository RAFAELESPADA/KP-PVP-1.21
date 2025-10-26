package me.RafaelAulerDeMeloAraujo.main;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.IOException;
import java.util.Arrays;
/*     */
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/*     */ import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
/*     */
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.command.ConsoleCommandSender;
/*     */
/*     */ import org.bukkit.configuration.InvalidConfigurationException;
/*     */ import org.bukkit.configuration.file.FileConfiguration;
/*     */
/*     */ import org.bukkit.configuration.file.YamlConfiguration;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.EventHandler;
/*     */ import org.bukkit.event.Listener;
/*     */ import org.bukkit.event.entity.EntityDeathEvent;
/*     */ import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ import org.bukkit.plugin.PluginManager;
/*     */ import org.bukkit.plugin.java.JavaPlugin;
/*     */ import org.bukkit.potion.PotionEffect;
/*     */ import org.bukkit.potion.PotionEffectType;
/*     */
import org.bukkit.scheduler.BukkitRunnable;

import cooldown1.UpdateScheduler;
import cooldown1.UpdateScheduler2;
import eu.decentsoftware.holograms.api.DHAPI;
import eu.decentsoftware.holograms.api.holograms.Hologram;
/*     */ import me.RafaelAulerDeMeloAraujo.BStatsSystem.Metrics;
import me.RafaelAulerDeMeloAraujo.Coins.Coins;
import me.RafaelAulerDeMeloAraujo.Coins.CoinsCommand;
/*     */ import me.RafaelAulerDeMeloAraujo.Coins.Commands;
import me.RafaelAulerDeMeloAraujo.Coins.PayCoins;
import me.RafaelAulerDeMeloAraujo.Coins.RemoveCoins;
/*     */ import me.RafaelAulerDeMeloAraujo.Listeners.AirmanFly;
import me.RafaelAulerDeMeloAraujo.Listeners.ArrowMessage;
import me.RafaelAulerDeMeloAraujo.Listeners.CombatLog;
/*     */ import me.RafaelAulerDeMeloAraujo.Listeners.CommandsSounds;
/*     */ import me.RafaelAulerDeMeloAraujo.Listeners.ConfigUtils;
import me.RafaelAulerDeMeloAraujo.Listeners.CrateInventory;
/*     */ import me.RafaelAulerDeMeloAraujo.Listeners.Fisherman;
/*     */ import me.RafaelAulerDeMeloAraujo.Listeners.JoinSign;
/*     */ import me.RafaelAulerDeMeloAraujo.Listeners.LeaveSign;
import me.RafaelAulerDeMeloAraujo.Listeners.MilkMan;
/*     */ import me.RafaelAulerDeMeloAraujo.Listeners.NoDrops;
/*     */ import me.RafaelAulerDeMeloAraujo.Listeners.NoExplosion;
import me.RafaelAulerDeMeloAraujo.Listeners.PlaceEvent;
import me.RafaelAulerDeMeloAraujo.Listeners.Protection;
/*     */ import me.RafaelAulerDeMeloAraujo.Listeners.Soup;
/*     */ import me.RafaelAulerDeMeloAraujo.Listeners.SoupSign;
import me.RafaelAulerDeMeloAraujo.Listeners.StatusGUI;
/*     */ import me.RafaelAulerDeMeloAraujo.Listeners.Switcher;
/*     */ import me.RafaelAulerDeMeloAraujo.Listeners.ThrowTnt;
import me.RafaelAulerDeMeloAraujo.Listeners.UpdateUtil;
/*     */ import me.RafaelAulerDeMeloAraujo.Listeners.WallClamber;
import me.RafaelAulerDeMeloAraujo.PluginHooks.PlaceHolderAPIHook;

/*     */ 


/*     */ import me.RafaelAulerDeMeloAraujo.ScoreboardManager.Streak;
import me.RafaelAulerDeMeloAraujo.SpecialAbility.API;
import me.RafaelAulerDeMeloAraujo.SpecialAbility.Anchor;
import me.RafaelAulerDeMeloAraujo.SpecialAbility.AntiStomper;
/*     */ import me.RafaelAulerDeMeloAraujo.SpecialAbility.Basic;
import me.RafaelAulerDeMeloAraujo.SpecialAbility.BerserkerKit;
import me.RafaelAulerDeMeloAraujo.SpecialAbility.Camel;
import me.RafaelAulerDeMeloAraujo.SpecialAbility.ConfuserHability;
import me.RafaelAulerDeMeloAraujo.SpecialAbility.Cooldown;
import me.RafaelAulerDeMeloAraujo.SpecialAbility.Creeper;
import me.RafaelAulerDeMeloAraujo.SpecialAbility.CreeperK;
/*     */ import me.RafaelAulerDeMeloAraujo.SpecialAbility.Critical;
/*     */ import me.RafaelAulerDeMeloAraujo.SpecialAbility.Deshfire;
/*     */ import me.RafaelAulerDeMeloAraujo.SpecialAbility.DesifireCMD;
/*     */ import me.RafaelAulerDeMeloAraujo.SpecialAbility.Dublejump;
import me.RafaelAulerDeMeloAraujo.SpecialAbility.Fireman;
import me.RafaelAulerDeMeloAraujo.SpecialAbility.FiremanCMD;
import me.RafaelAulerDeMeloAraujo.SpecialAbility.Ghast;
import me.RafaelAulerDeMeloAraujo.SpecialAbility.GhastKIT;
/*     */ import me.RafaelAulerDeMeloAraujo.SpecialAbility.GladCMD;
/*     */ import me.RafaelAulerDeMeloAraujo.SpecialAbility.Gladiator;
import me.RafaelAulerDeMeloAraujo.SpecialAbility.Habilidade;
import me.RafaelAulerDeMeloAraujo.SpecialAbility.HedgeHof;
import me.RafaelAulerDeMeloAraujo.SpecialAbility.HedgeHog;
/*     */ import me.RafaelAulerDeMeloAraujo.SpecialAbility.Join;
/*     */ import me.RafaelAulerDeMeloAraujo.SpecialAbility.Kangaroo;
import me.RafaelAulerDeMeloAraujo.SpecialAbility.MilkManCMD;
import me.RafaelAulerDeMeloAraujo.SpecialAbility.Monk;
import me.RafaelAulerDeMeloAraujo.SpecialAbility.MonkCMD;
/*     */ import me.RafaelAulerDeMeloAraujo.SpecialAbility.Naruto;
/*     */ import me.RafaelAulerDeMeloAraujo.SpecialAbility.NewKitMenu;
/*     */ import me.RafaelAulerDeMeloAraujo.SpecialAbility.Ninja;
import me.RafaelAulerDeMeloAraujo.SpecialAbility.Phantom;
import me.RafaelAulerDeMeloAraujo.SpecialAbility.PhantomCMD;
import me.RafaelAulerDeMeloAraujo.SpecialAbility.Poseidon;
import me.RafaelAulerDeMeloAraujo.SpecialAbility.Poseidonkit;
import me.RafaelAulerDeMeloAraujo.SpecialAbility.Resouper;
import me.RafaelAulerDeMeloAraujo.SpecialAbility.ResouperCMD;
import me.RafaelAulerDeMeloAraujo.SpecialAbility.Ryu;
import me.RafaelAulerDeMeloAraujo.SpecialAbility.Shielder;
/*     */ import me.RafaelAulerDeMeloAraujo.SpecialAbility.Snail;
import me.RafaelAulerDeMeloAraujo.SpecialAbility.Sonic;
/*     */ import me.RafaelAulerDeMeloAraujo.SpecialAbility.Sponge;
/*     */ import me.RafaelAulerDeMeloAraujo.SpecialAbility.Stomper;
/*     */ import me.RafaelAulerDeMeloAraujo.SpecialAbility.StomperKITCOMMAND;
import me.RafaelAulerDeMeloAraujo.SpecialAbility.Tamer;
/*     */ import me.RafaelAulerDeMeloAraujo.SpecialAbility.Thor;
/*     */ import me.RafaelAulerDeMeloAraujo.SpecialAbility.ThorKITCOMMAND;
/*     */ import me.RafaelAulerDeMeloAraujo.SpecialAbility.TimeLord;
/*     */ import me.RafaelAulerDeMeloAraujo.SpecialAbility.TimelordCMD;
import me.RafaelAulerDeMeloAraujo.SpecialAbility.Vampire;
/*     */ import me.RafaelAulerDeMeloAraujo.SpecialAbility.Viper;
import me.RafaelAulerDeMeloAraujo.Warps.SettingsManager;
import me.RafaelAulerDeMeloAraujo.X1.SetSumo;
/*     */ import me.RafaelAulerDeMeloAraujo.X1.SetX1;
import me.RafaelAulerDeMeloAraujo.X1.Sumo;
/*     */ import me.RafaelAulerDeMeloAraujo.X1.X1;
import net.wavemc.core.bukkit.WaveBukkit;
import us.ajg0702.leaderboards.LeaderboardPlugin;





/*     */ 
/*     */ 
/*     */ 
/*     */ public class Main
/*     */   extends JavaPlugin
/*     */   implements Listener
/*     */ {
/*  77 */   public static String pluginName = "KP-PVP";
/*     */   
/*     */   
/*     */   public static Main getInstance()
/*     */   {
/*  82 */     return instance;
/*     */   }
/*     */   
/*     */   public static Main getInstace()
/*     */   {
/*  87 */     return instance;
/*     */   }
private static Hologram topPlayersHd;
/*     */   private Server server = Bukkit.getServer();
/*  90 */   public static final String instance2 = null;
/*     */   public static Object instancs;
/*     */   public static Logger log;
/*  93 */   public static File customizationf = new File("plugins/KP-PVP", "settings.yml");
/*     */   public YamlConfiguration cf;
/*  93 */   public static File messagesf = new File("plugins/KP-PVP", "messages.yml");
            public static File kitsf = new File("plugins/KP-PVP", "kits.yml");
/*     */   public static Plugin plugin;
/*     */   public static Main instance;
/*     */   private static ConfigUtils cH;
/*  98 */   public static File file_x1 = new File("plugins/KP-PVP", "1v1.yml");
/*     */   SettingsManager settings = SettingsManager.getInstance();
/*     */   public static Main getPlugin()
/*     */   {
/* 102 */     return instance;
/*     */   }
/*     */   
/*     */   public void onEnable()
/*     */   {

/* 121 */     instance = this;
/* 122 */     plugin = this;
	
   instance = this;
   plugin = this;
	ConsoleCommandSender cmd = Bukkit.getConsoleSender();
	if (!Coins.setupPermissions()) {
		cmd.sendMessage("KP-PVP - Disabled due to no Vault dependency found! KP-PVP VERSION" + getDescription().getVersion());
        cmd.sendMessage("Install vault to KP-PVP work!");
        getServer().getPluginManager().disablePlugin(this);
        return;
    }
	if (!Coins.setupEconomy()) {
		cmd.sendMessage("KP-PVP - Disabled due to no Vault dependency found! KP-PVP VERSION" + getDescription().getVersion());
        cmd.sendMessage("Install vault to KP-PVP work!");
        getServer().getPluginManager().disablePlugin(this);
        return;
    }
	if (!Bukkit.getPluginManager().isPluginEnabled("KPCore")) {
		 Bukkit.getConsoleSender().sendMessage("KPCORE is not installed! It is required for MYSQL SUPPORT (MANDATORY)");
		 Bukkit.getConsoleSender().sendMessage("Disabling KP-PVP");
		 getServer().getPluginManager().disablePlugin(this);
	        return;
	 }

	 
/* 107 */     getLogger().info("KP-PVP plugin is now enable [By zEnderX5_]");
/* 108 */     getLogger().info("Website: http://bit.ly/2kC345B");
int pluginId = 1955; // <-- Replace with the id of your plugin!
Metrics metrics = new Metrics(this, pluginId);
metrics.addCustomChart(new Metrics.DrilldownPie("serverAddress", () -> {
	Map<String, Map<String, Integer>> map = new HashMap<>();
	Map<String, Integer> entry = new HashMap<>();
	if (getConfig().getBoolean("SendIPAddressData")) entry.put(server.getIp(), 1);
	else entry.put("Hidden", 1);
	
	map.put("Port " + server.getPort(), entry);
	
	return map;
}));





if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null){
	/* 151 */       Bukkit.getConsoleSender().sendMessage("§e[KP-PVP] §aPlaceHolderAPI is found!");
	/* 151 */       Bukkit.getConsoleSender().sendMessage("§e[KP-PVP] §aHooking into it!");
    new PlaceHolderAPIHook(this).register();
	/* 151 */       Bukkit.getConsoleSender().sendMessage("§e[KP-PVP] §aPlaceHolderAPI has hooked sucefully!");
}
 



	

		

				
/* 117 */     this.pm = Bukkit.getPluginManager();
/* 118 */     
/* 119 */    

/*     */     
/* 124 */     cH = new ConfigUtils();
/*     */     
/* 126 */     File cf = new File(getDataFolder(), "config.yml");
/* 127 */     if (!cf.exists()) {
/* 128 */       saveResource("config.yml", false);
/*     */     }
/* 130 */     this.cf1 = new File(getDataFolder(), "config.yml");
/* 131 */     if (!file_x1.exists()) {
/* 132 */       saveResource("1v1.yml", false);
/*     */     }
/* 134 */     if (!customizationf.exists()) {
/* 135 */       saveResource("settings.yml", false);
/*     */     }
/* 134 */     if (!messagesf.exists()) {
/* 135 */       saveResource("messages.yml", false);
/*     */     }
/* 134 */     if (!kitsf.exists()) {
/* 135 */       saveResource("kits.yml", false);
/*     */     }
/*     */     try
/*     */     {
/* 139 */       cfg_x1.load(file_x1);
messages.load(messagesf);
kits.load(kitsf);

/* 140 */       customization.load(customizationf);
/*     */     }
/*     */     catch (IOException|InvalidConfigurationException e1)
/*     */     {
/* 144 */       System.out.println(e1.getMessage());
/*     */     }

ItemStack kits = new ItemStack(Material.BOOK);
ItemStack shop = new ItemStack(Material.EMERALD);
ItemStack ct = new ItemStack(Material.WOODEN_SWORD);
ItemStack V1 = new ItemStack(Material.BLAZE_ROD);
ItemStack warps = new ItemStack(Material.PAPER);
ItemStack nt = new ItemStack(Material.NAME_TAG);
ItemStack sair = new ItemStack(Material.RED_BED);
if (getConfig().get("KitsItem") == null) {
getConfig().set("KitsItem", kits);
getConfig().set("ShopItem", shop);
getConfig().set("ClickTestItem", ct);
getConfig().set("StatsItem", nt);
getConfig().set("1v1Item", V1);
getConfig().set("WarpItem", warps);
getConfig().set("LeaveItem", sair);
}
new BukkitRunnable() {

	private long tick;

	@Override
	public void run() {
		getServer().getPluginManager().callEvent(new ServerTimerEvent());
		tick++;
	}
}.runTaskTimer(this, 1, 1);
new BukkitRunnable() {
@Override
public void run() {
	for (Player p : Bukkit.getOnlinePlayers()) {
	if (Join.game.contains(p.getName()) && p.getGameMode() == GameMode.SPECTATOR) {
		int i = 5;
		i--;
	    if (i <= 0) {
	    	p.setGameMode(GameMode.SURVIVAL);
			p.sendMessage("You are not allowed to be in gamemode spectator while on kitpvp for too long!");
			i = 5;
		    cancel();
		}
	   }
		}
	}
}.runTaskTimer(this, 20L, 20L);
/* 146 */     if ((Bukkit.getVersion().contains("1.14") || (Bukkit.getVersion().contains("1.15") || (Bukkit.getVersion().contains("1.16") || (Bukkit.getVersion().contains("1.17") || (Bukkit.getVersion().contains("1.18") || (Bukkit.getVersion().contains("1.19") || (Bukkit.getVersion().contains("1.20") || (Bukkit.getVersion().contains("1.21") || (Bukkit.getVersion().contains("1.22") || (Bukkit.getVersion().contains("1.23") || (Bukkit.getVersion().contains("1.24")))))))))))))
/*     */     {
/* 148 */       getConfig().options().copyDefaults(true);
/* 149 */       getConfig().options().copyHeader(true);
/* 150 */       saveConfig();
/* 151 */       Bukkit.getConsoleSender().sendMessage("§e[KP-PVP] §aThe server is using 1.20/1.21+ MINECRAFT VERSION");
/* 152 */       Bukkit.getConsoleSender().sendMessage("§e[KP-PVP] §aAltering config sounds to 1.20 Sounds...");
getConfig().options().copyDefaults(true);
getConfig().options().copyHeader(true);
saveConfig();
if (!(getConfig().get("Sound.Join").equals("ENTITY_PLAYER_LEVELUP") && getConfig().get("Sound.1v1").equals("UI_BUTTON_CLICK"))) {
getConfig().set("Sound.Kit", "ENTITY_ITEM_PICKUP");
getConfig().set("Sound.ShopMenu", "BLOCK_CHEST_OPEN");
getConfig().set("Sound.KitUse", "ENTITY_ENDER_DRAGON_HURT");
getConfig().set("Sound.Soup", "ENTITY_GENERIC_EAT");
getConfig().set("Sound.Fisherman", "ENTITY_ENDERMAN_TELEPORT");
getConfig().set("Sound.Spiderman", "ENTITY_SLIME_JUMP");
getConfig().set("Sound.Respawn", "ENTITY_PLAYER_LEVELUP");
getConfig().set("Sound.Join", "ENTITY_PLAYER_LEVELUP");
getConfig().set("Sound.KitMenu", "BLOCK_WOODEN_DOOR_CLOSE");
getConfig().set("Sound.SpongeUse", "ENTITY_ENDERMAN_TELEPORT");
getConfig().set("Sound.BowlDrop", "ENTITY_ITEM_PICKUP");
getConfig().set("Sound.ErrorMessage", "ENTITY_ARROW_HIT_PLAYER");
getConfig().set("Sound.SucefullMessage", "ENTITY_EXPERIENCE_ORB_PICKUP");
getConfig().set("Sound.NoPermissionMessage", "ENTITY_WITHER_SHOOT");
getConfig().set("Sound.SwitcherShoot", "ENTITY_ENDERMAN_TELEPORT");
getConfig().set("Sound.Timelord", "ENTITY_WITHER_SPAWN");
getConfig().set("Sound.ItemDespawn", "BLOCK_LAVA_POP");
getConfig().set("Sound.Stomper", "ENTITY_FIREWORK_ROCKET_BLAST");
getConfig().set("Sound.NarutoAbility", "ENTITY_BLAZE_DEATH");
getConfig().set("Sound.CommandsSounds", "UI_BUTTON_CLICK");
getConfig().set("Sound.ClickTest", "UI_BUTTON_CLICK");
getConfig().set("Sound.1v1", "UI_BUTTON_CLICK");
getConfig().set("Sound.Respawning", "UI_BUTTON_CLICK");
getConfig().set("Sound.RespawnSucess", "ENTITY_EXPERIENCE_ORB_PICKUP");
getConfig().set("Sound.ShopMenu-Click", "ENTITY_PLAYER_LEVELUP");
getConfig().set("Sound.Airman-Fly", "ENTITY_ENDERMAN_SCREAM");
getConfig().set("Sound.DoubleJump-Ability", "ENTITY_FIREWORK_ROCKET_BLAST");
getConfig().set("Sound.StomperDamage", "BLOCK_ANVIL_LAND");
getConfig().set("Sound.AnchorHit", "BLOCK_ANVIL_LAND");
getConfig().set("Sound.RyuAbility", "ENTITY_GENERIC_EXPLODE");
Bukkit.getConsoleSender().sendMessage("§e[KP-PVP] §aDone! All Sounds have been modified to 1.20+ Sounds.");

}
else {
	Bukkit.getConsoleSender().sendMessage("§e[KP-PVP] §aNo need to alter the sounds names in config again.");

}
registerEvents();
Bukkit.getConsoleSender().sendMessage("§e[KP-PVP] §aSetupping warps");
settings.setup(this);
Bukkit.getConsoleSender().sendMessage("§e[KP-PVP] §aWarps Loaded");
registerCommands();
Bukkit.getConsoleSender().sendMessage("§e[KP-PVP] §aLoading top kills system");
loadTopPlayersHologram();
Bukkit.getConsoleSender().sendMessage("§e[KP-PVP] §aTopKills setupped sucessufully.");
Bukkit.getConsoleSender().sendMessage("§e[KP-PVP] §aStorage: " + WaveBukkit.getStorage().getName());
Bukkit.getConsoleSender().sendMessage("§e[KP-PVP] §chttps://www.spigotmc.org/resources/kp-pvp-the-ultimate-kitpvp-plugin.50969/");
Bukkit.getConsoleSender().sendMessage("§e[KP-PVP] §2Your version is: §e" + Main.getInstance().getDescription().getVersion());
/*     */   
}
  
}
public static void handleTopPlayers(Location location) {
	Plugin lb = Bukkit.getPluginManager().getPlugin("ajLeaderboards");
	if (lb == null || !lb.isEnabled() || !(lb instanceof LeaderboardPlugin)) {
		Bukkit.getLogger().severe("AjLeaderBoards not found! TopKills will not work.");
		return;
	}
	Plugin dc = Bukkit.getPluginManager().getPlugin("DecentHolograms");
	if (dc == null) {
		Bukkit.getLogger().severe("DecentHolograms not found! TopKills will not work.");
		return;
	}
	LeaderboardPlugin ajlb = (LeaderboardPlugin) lb;
	if (!ajlb.getCache().createBoard("kp-pvp_player_kills")) {
		Bukkit.getLogger().severe("[KP-PVP] TopKills Creation Failed. Aborting");
		return;
	}
		
		String header = "§e§lTop 15 players §a(KILLS)";	
			List<String> lines = Arrays.asList(header,
				"§61"  + "* " + "§e" + "%ajlb_lb_kp-pvp_player_kills_1_alltime_name%" +
				" §fKills: §6" + "%ajlb_lb_kp-pvp_player_kills_1_alltime_value%", "§62" + "* " + "§e" + "%ajlb_lb_kp-pvp_player_kills_2_alltime_name%" +
						" §fKills: §6" + "%ajlb_lb_kp-pvp_player_kills_2_alltime_value%", "§63" + "* " + "§e" + "%ajlb_lb_kp-pvp_player_kills_3_alltime_name%" +
								" §fKills: §6" + "%ajlb_lb_kp-pvp_player_kills_3_alltime_value%", "§64" + "* " + "§e" + "%ajlb_lb_kp-pvp_player_kills_4_alltime_name%" +
										" §fKills: §6" + "%ajlb_lb_kp-pvp_player_kills_4_alltime_value%", "§65" + "* " + "§e" + "%ajlb_lb_kp-pvp_player_kills_5_alltime_name%" +
												" §fKills: §6" + "%ajlb_lb_kp-pvp_player_kills_5_alltime_value%", "§66" + "* " + "§e" + "%ajlb_lb_kp-pvp_player_kills_6_alltime_name%" +
														" §fKills: §6" + "%ajlb_lb_kp-pvp_player_kills_6_alltime_value%", "§67" + "* " + "§e" + "%ajlb_lb_kp-pvp_player_kills_7_alltime_name%" +
																" §fKills: §6" + "%ajlb_lb_kp-pvp_player_kills_7_alltime_value%", "§68" + "* " + "§e" + "%ajlb_lb_kp-pvp_player_kills_8_alltime_name%" +
																		" §fKills: §6" + "%ajlb_lb_kp-pvp_player_kills_8_alltime_value%", "§69" + "* " + "§e" + "%ajlb_lb_kp-pvp_player_kills_9_alltime_name%" +
																				" §fKills: §6" + "%ajlb_lb_kp-pvp_player_kills_9_alltime_value%", "§610" + "* " + "§e" + "%ajlb_lb_kp-pvp_player_kills_10_alltime_name%" +
																						" §fKills: §6" + "%ajlb_lb_kp-pvp_player_kills_10_alltime_value%", "§611" + "* " + "§e" + "%ajlb_lb_kp-pvp_player_kills_11_alltime_name%" +
																								" §fKills: §6" + "%ajlb_lb_kp-pvp_player_kills_11_alltime_value%", "§612" + "* " + "§e" + "%ajlb_lb_kp-pvp_player_kills_12_alltime_name%" +
																										" §fKills: §6" + "%ajlb_lb_kp-pvp_player_kills_12_alltime_value%", "§613" + "* " + "§e" + "%ajlb_lb_kp-pvp_player_kills_13_alltime_name%" +
																												" §fKills: §6" + "%ajlb_lb_kp-pvp_player_kills_13_alltime_value%", "§614" + "* " + "§e" + "%ajlb_lb_kp-pvp_player_kills_14_alltime_name%" +
																														" §fKills: §6" + "%ajlb_lb_kp-pvp_player_kills_14_alltime_value%", "§615" + "* " + "§e" + "%ajlb_lb_kp-pvp_player_kills_15_alltime_name%" +
																																" §fKills: §6" + "%ajlb_lb_kp-pvp_player_kills_15_alltime_value%");
		
		
			
		Hologram hologram = DHAPI.getHologram("topkills");
		if (hologram == null) {
			 Bukkit.getConsoleSender().sendMessage("[KP-PVP] Creating hologram...");
		 Hologram holo = DHAPI.createHologram("topkills", location, true, lines);
	 holo.updateAll();
		}
		for (Player p : Bukkit.getOnlinePlayers()) {
			if (p.hasPermission("ajleaderboards.dontupdate.kp-pvp_player_kills") && !Main.getInstance().getConfig().getBoolean("disable-negation-of-ajleaderboards-no-update-permission")) {
		        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + p.getName() + " permission set ajleaderboards.dontupdate.kp-pvp_player_kills false");
			}
		}
		
			DHAPI.updateHologram("topkills");
}

			

			

public static void loadTopPlayersHologram() {
	new BukkitRunnable() {
		@Override
		public void run() {
			if (Main.plugin.getConfig().getString("TOP.World") == null) {
				return;
			}
		      World w = Bukkit.getServer().getWorld(Main.plugin.getConfig().getString("TOP.World"));
		  	/* 201 */           double x = Main.plugin.getConfig().getDouble("TOP.X");
		  	/* 202 */           double y = Main.plugin.getConfig().getDouble("TOP.Y");
		  	/* 203 */           double z = Main.plugin.getConfig().getDouble("TOP.Z");
		  	/* 204 */           Location lobby = new Location(w, x, y, z);
			
			Location location = lobby;
			handleTopPlayers(location);
			
		}
	}.runTaskTimer(Main.getInstance(), 10 * 20L, 2 * (60 * 20L));
}

/*     */ 
/*     */ 
/*     */   public ConfigUtils getConfigHandler()
/*     */   {
/* 186 */     return cH;
/*     */   }

/*     */   
/*     */   private void registerCommands()
/*     */   {
	getCommand("kpsoups").setExecutor(new KPSoup(this));
	getCommand("antistomper").setExecutor(new AntiStomper(this));
	getCommand("kmilkman").setExecutor(new MilkManCMD(this));
/* 191 */     getCommand("kitreload").setExecutor(new Reload(this));
/* 192 */     getCommand("givecoins").setExecutor(new Commands());
getCommand("settopkills").setExecutor(new SetTopKills());
              getCommand("removecoins").setExecutor(new RemoveCoins());
              getCommand("coins").setExecutor(new CoinsCommand());
/* 193 */     getCommand("kitcredits").setExecutor(new KitCredits(this));
/* 194 */     getCommand("dev").setExecutor(new Dev(this));
/* 195 */     getCommand("ksc").setExecutor(new BSC());
         
/* 196 */     getCommand("kpstats").setExecutor(new Stats());
/* 197 */     getCommand("kphelp").setExecutor(new KITPVP());
/* 198 */     getCommand("adminmode").setExecutor(new AdminMode(this));
/*     */     getCommand("kpeditmode").setExecutor(new NoBreakEvent()); 
/* 201 */     getCommand("kpvp").setExecutor(new Kits(this));
getCommand("khedgehog").setExecutor(new HedgeHof(this));
getCommand("updatetopkill").setExecutor(new UpdateTopKill());
/* 202 */     getCommand("freezer").setExecutor(new Kits(this));
/* 203 */     getCommand("basic").setExecutor(new Basic(this));
getCommand("kposeidon").setExecutor(new Poseidonkit(this));
getCommand("kcamel").setExecutor(new Camel(this));
getCommand("paycoins").setExecutor(new PayCoins());
/* 204 */     getCommand("airman").setExecutor(new Kits(this));
/* 205 */     getCommand("fisherman").setExecutor(new Kits(this));
/* 206 */     getCommand("archer").setExecutor(new Kits(this));
getCommand("kptpall").setExecutor(new KPTPALL());
/* 207 */     getCommand("tank").setExecutor(new Kits(this));
/* 208 */     getCommand("pyro").setExecutor(new Kits(this));
getCommand("kviking").setExecutor(new Kits(this));
getCommand("kp").setExecutor(new Join(this));
startUpdating();
/* 209 */     getCommand("kthor").setExecutor(new ThorKITCOMMAND(this));
getCommand("kvampire").setExecutor(new Vampire(this));
getCommand("kcreeper").setExecutor(new CreeperK(this));
getCommand("kberserker").setExecutor(new BerserkerKit(this));
getCommand("kpsetdeathspawn").setExecutor(new kpsetdeathspawn());
/* 210 */     getCommand("switcher").setExecutor(new Kits(this));
/* 211 */     getCommand("viper").setExecutor(new Viper(this));
getCommand("kconfuser").setExecutor(new ConfuserHability(this));
/* 212 */     getCommand("snail").setExecutor(new Snail(this));
getCommand("kpsetlevel").setExecutor(new KPSETLEVEL());
/* 213 */     getCommand("warper").setExecutor(new Kits(this));
getCommand("setsumo").setExecutor(new SetSumo());
/* 214 */     getCommand("jumper").setExecutor(new Kits(this));
/* 215 */     getCommand("wasp").setExecutor(new Kits(this));
/* 216 */     getCommand("cactus").setExecutor(new Kits(this));
getCommand("kshielder").setExecutor(new Shielder(this));
/* 217 */     getCommand("bomber").setExecutor(new Kits(this));
/* 218 */     getCommand("spiderman").setExecutor(new Kits(this));
/* 219 */     getCommand("stats").setExecutor(new Stats());
/* 220 */     getCommand("kpstats").setExecutor(new KPStats());
/* 221 */     getCommand("kitmenu").setExecutor(new Menu(this));
/* 222 */     getCommand("kitpvp").setExecutor(new Join());
/*     */         getCommand("ktamer").setExecutor(new Tamer(this));
/*     */   getCommand("setarena").setExecutor(new SetArena());
/* 225 */     getCommand("kpkits").setExecutor(new Menu(this));
              getCommand("kpkickall").setExecutor(new KPKICK());
/* 226 */     getCommand("kpshop").setExecutor(new Shop(this));
/* 227 */     getCommand("kploja").setExecutor(new Shop(this));
/* 228 */     getCommand("shopmenu").setExecutor(new Shop(this));
/* 229 */     getCommand("kpkitmenu").setExecutor(new NewKitMenu(this));
/* 230 */     getCommand("kitsc").setExecutor(new StaffChat(this));
/* 231 */     getCommand("kpkit").setExecutor(new Menu(this));
/* 232 */     getCommand("kpsetspawn").setExecutor(new kpsetspawn());
/* 233 */     getCommand("stomper").setExecutor(new StomperKITCOMMAND(this));
/* 234 */     getCommand("gladiator").setExecutor(new GladCMD(this));
/* 235 */     getCommand("deshfire").setExecutor(new DesifireCMD(this));
/* 236 */     getCommand("ninja").setExecutor(new Ninja(this));
getCommand("kmonk").setExecutor(new MonkCMD(this));
/* 237 */   getCommand("kanchor").setExecutor(new Anchor(this));

/* 237 */   getCommand("givekitunlocker").setExecutor(new GiveKitUnlocker());
/* 238 */     getCommand("kangaroo").setExecutor(new Kangaroo(this));
/* 239 */     getCommand("timelord").setExecutor(new TimelordCMD(this));
/* 240 */     getCommand("doublejump").setExecutor(new Dublejump(this));
getCommand("kresouper").setExecutor(new ResouperCMD(this));
/* 241 */     getCommand("kitclear").setExecutor(new Kits(this));
/* 242 */     getCommand("skit").setExecutor(new Skit());
              getCommand("kpkills").setExecutor(new Kills());
/* 243 */     getCommand("critical").setExecutor(new Critical(this));
getCommand("kryu").setExecutor(new Ryu(this));
/* 244 */     getCommand("kitpvp").setExecutor(new Join(this));
getCommand("kresouper").setExecutor(new ResouperCMD(this));
getCommand("kfireman").setExecutor(new FiremanCMD(this));
/* 245 */     getCommand("naruto").setExecutor(new Naruto(this));
getCommand("kphantom").setExecutor(new PhantomCMD(this));
getCommand("sonic").setExecutor(new Sonic());
getCommand("kpwarp").setExecutor(new Warp());

getCommand("kghast").setExecutor(new GhastKIT(this));
getCommand("kpsetwarp").setExecutor(new Warp());
this.pm.registerEvents(new ClickTest(), this);
/* 246 */     
/* 247 */     getCommand("set1v1").setExecutor(new SetX1());
UpdateUtil updateChecker = new UpdateUtil(this, true);
switch (updateChecker.check()) {
    case OUT_OF_DATE:
        getLogger().info("An update for KP-PVP (" + updateChecker.getNewVersion() + ") was found. Please update at: https://www.spigotmc.org/resources/kp-pvp-the-ultimate-kitpvp-plugin.50969/");
        break;
    case UP_TO_DATE:
    	getLogger().info("You are on latest version");
    default:
        break;
}
/*     */   }
/*     */   
private void startUpdating() {

    Bukkit.getServer().getScheduler().runTaskTimer(getInstance(), new UpdateScheduler(), 20, 20);
    Bukkit.getServer().getScheduler().runTaskTimer(getInstance(), new UpdateScheduler2(), 20, 20);
 }
/*     */   private void registerEvents()
/*     */   {
	          this.pm.registerEvents(new ArrowMessage(), this);
	          this.pm.registerEvents(new Respawn(), this);
/* 252 */     this.pm.registerEvents(new ThrowTnt(this), this);
/* 253 */     this.pm.registerEvents(new Dublejump(this), this);
              this.pm.registerEvents(new Phantom(), this);
              this.pm.registerEvents(new Ryu(this), this);
              this.pm.registerEvents(new Camel(this), this);
              this.pm.registerEvents(new HedgeHog(), this);
              this.pm.registerEvents(new Poseidon(), this);
              this.pm.registerEvents(new WarpMenu(), this);
              this.pm.registerEvents(new Sumo(this), this);
              this.pm.registerEvents(new Ghast(), this);
              this.pm.registerEvents(new Tamer(this), this);
              this.pm.registerEvents(new Recraft(), this);
              this.pm.registerEvents(new Creeper(), this);
              this.pm.registerEvents(new CrateInventory(), this);
              this.pm.registerEvents(new Anchor(this), this);
              this.pm.registerEvents(new ConfuserHability(this), this);
/* 256 */     this.pm.registerEvents(new NewKitMenu(this), this);
/* 257 */     this.pm.registerEvents(new Sponge(this), this);
this.pm.registerEvents(new StatusGUI(), this);
/* 258 */     this.pm.registerEvents(new Naruto(this), this);
/* 259 */     this.pm.registerEvents(new Critical(this), this);
this.pm.registerEvents(new Resouper(), this);
this.pm.registerEvents(new Fireman(), this);
this.pm.registerEvents(new Monk(), this);
if (Main.getInstace().getConfig().getString("DisableCombatLog").equalsIgnoreCase("false")) {
	this.pm.registerEvents(new CombatLog(), this);	
}
this.pm.registerEvents(new PlaceEvent(), this);
/* 260 */     this.pm.registerEvents(new BlockCommands(this), this);
/* 261 */     this.pm.registerEvents(new Menu(this), this);
/* 262 */     this.pm.registerEvents(new NoBreakEvent(), this);
/* 263 */     this.pm.registerEvents(new Fisherman(this), this);
/* 264 */     this.pm.registerEvents(new Switcher(), this);
              this.pm.registerEvents(new Vampire(this), this);
/* 265 */     this.pm.registerEvents(new Viper(this), this);
/* 266 */     this.pm.registerEvents(new SoupSign(), this);
this.pm.registerEvents(new Compass(), this);
/* 267 */     this.pm.registerEvents(new Deshfire(), this);
/* 268 */     this.pm.registerEvents(new Thor(this), this);
/* 269 */     this.pm.registerEvents(new Ninja(this), this);
/* 270 */     this.pm.registerEvents(new JoinSign(this), this);
/* 271 */     this.pm.registerEvents(new LeaveSign(this), this);
/* 272 */     this.pm.registerEvents(new Kangaroo(this), this);
/* 273 */     this.pm.registerEvents(new AdminMode(this), this);
this.pm.registerEvents(new Sonic(), this);
/* 274 */     this.pm.registerEvents(new TimeLord(this), this);
/* 275 */     this.pm.registerEvents(new Soup(this), this);
/* 276 */     this.pm.registerEvents(new NoDrops(this), this);
/* 277 */     this.pm.registerEvents(new NoHunger(), this);
/* 278 */     this.pm.registerEvents(new Gladiator(), this);
/* 279 */     this.pm.registerEvents(new Join(this), this);
/* 280 */     this.pm.registerEvents(new Shop(this), this);
/* 281 */     this.pm.registerEvents(new Stomper(this), this);
/* 282 */     this.pm.registerEvents(new Snail(this), this);
/* 283 */     this.pm.registerEvents(new X1(this), this);
/* 284 */     this.pm.registerEvents(new Streak(), this);
/* 285 */     this.pm.registerEvents(new NoExplosion(), this);
    this.pm.registerEvents(new MilkMan(), this);
/* 286 */     this.pm.registerEvents(new AntiDeathDrop(this), this);
/* 287 */     this.pm.registerEvents(new WallClamber(this), this);
/* 288 */     this.pm.registerEvents(new SoupSign(), this);
this.pm.registerEvents(new Protection(), this);
/* 289 */     this.pm.registerEvents(new CommandsSounds(this), this);
/* 290 */     this.pm.registerEvents(new AirmanFly(this), this);
/* 291 */     this.pm.registerEvents(new NewKitMenu(this), this);
}
/*     */     
/* 293 *
/*     */   @EventHandler
/*     */   public void playerKill(EntityDeathEvent evt)
/*     */   {
/* 313 */     File f = new File("plugins/KP-PVP/Stats/stats.yml");
/* 314 */     YamlConfiguration yamlFile = YamlConfiguration.loadConfiguration(f);
/* 315 */     if ((evt instanceof PlayerDeathEvent))
/*     */     {
/* 317 */       getLogger().info("Recognised player death");
/* 318 */       PlayerDeathEvent event = (PlayerDeathEvent)evt;
/* 319 */       Player dead = event.getEntity();
/* 320 */       Player killer = dead.getKiller();
/* 321 */       getLogger().info("Dead: " + dead.getDisplayName());
/* 322 */       if ((killer instanceof Player))
/*     */       {
/* 324 */         getLogger().info("Killer recognised as a player.");
/* 325 */         Player murderer = killer;
/* 326 */         getLogger().info("Killer: " + murderer.getName());
/*     */         
/* 328 */         int streak = yamlFile.getInt("players." + murderer.getName() + ".streak");
/*     */         
/* 330 */         int newStreak = streak + 1;
/* 331 */         yamlFile.set("players." + murderer.getName() + ".streak", Integer.valueOf(newStreak));
/*     */       }
/*     */       try
/*     */       {
/* 335 */         yamlFile.save(f);
/*     */       }
/*     */       catch (IOException e)
/*     */       {
/* 339 */         e.printStackTrace();
/*     */       }
/* 341 */       getLogger().info("Set and saved stats.");
/*     */     }
/*     */   }
/*     */   
/*     */   public static void Funcionou(Player p) {}
/*     */   
/*     */   public static void darEfeito(Player p, PotionEffectType tipo, int duracao, int level)
/*     */   {
/* 349 */     p.addPotionEffect(new PotionEffect(tipo, 20 * duracao, level));
/*     */   }
/*     */   
/*     */   public void save()
/*     */   {
/*     */     try
/*     */     {
/* 356 */       this.cf.save(this.cf1);
/*     */     }
/*     */     catch (IOException e)
/*     */     {
/* 360 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/* 364 */   public static FileConfiguration cfg_x1 = YamlConfiguration.loadConfiguration(file_x1);
public static FileConfiguration messages = YamlConfiguration.loadConfiguration(messagesf);
public static FileConfiguration kits = YamlConfiguration.loadConfiguration(kitsf);
/* 365 */   public static FileConfiguration customization = YamlConfiguration.loadConfiguration(customizationf);
/*     */   private File cf1;
/*     */   FileConfiguration config;
/*     */   private PluginManager pm;
/*     */   
/*     */   public boolean IsOnKitPvP(String name)
/*     */   {
/* 372 */     return Join.game.contains(name);
/*     */   }
/*     */ 
public void onDisable()
{
  ConsoleCommandSender cmd = Bukkit.getConsoleSender();
  cmd.sendMessage(" ");
  cmd.sendMessage("    §7KP-PVP Version " + Main.getInstance().getDescription().getVersion() + " has been disabled");
  cmd.sendMessage("    §cAuthor: §EzEnderX5_ , Rafael Auler    ");
  cmd.sendMessage("    §cThank you for use the plugin §a=D    ");
  cmd.sendMessage(" ");
  /*     */ for(Player p: Bukkit.getOnlinePlayers()){
	  if (Join.game.contains(p.getName()) && !Main.plugin.getConfig().getBoolean("bungeemode")) {
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
  /* 303 */       p.sendMessage(ChatColor.RED + "The plugin has been reloaded/disabled so you are forced to leave the game");
  /* 304 */       p.getInventory().clear();
  /* 305 */       p.teleport((Location)Join.saveworld.get(p.getName()));
  /* 306 */       p.getInventory().setContents((ItemStack[])Join.saveinv.get(p.getName()));
  /* 307 */       p.setGameMode((GameMode)Join.savegamemode.get(p.getName()));
  p.setLevel(Join.savelevel.get(p.getName()));
p.setScoreboard(Join.savescore.get(p.getName()));
  /* 308 */       p.getInventory().setArmorContents((ItemStack[])Join.savearmor.get(p.getName()));

  /*     */   
  /* 311 */       p.updateInventory();
  API.tirarEfeitos(p);
	  }
  }
}



public static String TAC(String s)
{
  return ChatColor.translateAlternateColorCodes('&', s);
}

{
	
}





}




/* Location:              D:\Desktop\video\Minhas Coisas do Desktop\KP-PVPvB12 (1).jar!\me\RafaelAulerDeMeloAraujo\main\Main.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */
