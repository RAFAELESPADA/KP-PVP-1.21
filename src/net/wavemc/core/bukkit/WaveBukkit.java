package net.wavemc.core.bukkit;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import net.wavemc.core.bukkit.account.UUIDFetcher;
import net.wavemc.core.bukkit.account.WavePlayerController;
import net.wavemc.core.bukkit.account.WavePlayerManager;
import net.wavemc.core.bukkit.item.listener.ItemBuilderListener;
import net.wavemc.core.bukkit.listener.PlaceHolderAPIHook;
import net.wavemc.core.bukkit.listener.PlayerLoadListener;
import net.wavemc.core.bukkit.listener.PlayerQuitListener;
import net.wavemc.core.bukkit.message.BukkitMessage;
import net.wavemc.core.bukkit.util.CCommand;
import net.wavemc.core.bukkit.warp.WaveWarpManager;
import net.wavemc.core.storage.Storage;
import net.wavemc.core.storage.provider.MySQL;
import net.wavemc.core.storage.provider.SettingsManager;
import net.wavemc.core.storage.provider.Yaml2;
import org.bukkit.Bukkit;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.SimplePluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;

import com.hidan2.folialib.FoliaLib;

public class WaveBukkit extends JavaPlugin {
  private UUIDFetcher uuidFetcher;
  
  private static final ExecutorService executorService = Executors.newFixedThreadPool(50);
  
  public static Storage storage;
  
  static WaveBukkit instance;
  
  private static WavePlayerController playerController;
  
  private static WavePlayerManager Manager;
  private static FoliaLib foliaLib;
  private static SimpleCommandMap scm;
  
  private SimplePluginManager spm;
  
  private WaveWarpManager warpManager;
  
  private boolean chat = true;
  
  public void onEnable() {
    setupSimpleCommandMap();
    SettingsManager settings = SettingsManager.getInstance();
    this.uuidFetcher = new UUIDFetcher(1);
    instance = this;
    saveDefaultConfig();

foliaLib = new FoliaLib(this);
    (new PlaceHolderAPIHook(this)).register();
    Bukkit.getMessenger().registerOutgoingPluginChannel((Plugin)this, "wave:cre");
    Bukkit.getMessenger().registerIncomingPluginChannel((Plugin)this, "wave:cre", (PluginMessageListener)new BukkitMessage());
    if (getConfig().getBoolean("mysql.enable")) {
      storage = 
        (Storage)new MySQL(
          getConfig().getString("mysql.host"), 
          getConfig().getString("mysql.user"), 
          getConfig().getString("mysql.password"), 
          getConfig().getString("mysql.database"), 
          getConfig().getInt("mysql.port"));
      storage.createTables();
    } else {
      storage = (Storage)new Yaml2(this, getDataFolder(), "data", true, true);
    } 
    Bukkit.getConsoleSender().sendMessage("[KPCORE] Loading listeners and managers.");
    Manager = new WavePlayerManager(this);
    this.warpManager = new WaveWarpManager(this);
    loadListeners(Bukkit.getPluginManager());
    playerController = new WavePlayerController(this);
    settings.setup((Plugin)this);
  }
  
  private void loadListeners(PluginManager pluginManager) {
    pluginManager.registerEvents((Listener)new PlayerLoadListener(), (Plugin)this);
    pluginManager.registerEvents((Listener)new PlayerQuitListener(), (Plugin)this);
    pluginManager.registerEvents((Listener)new ItemBuilderListener(), (Plugin)this);
  }
  
  private void registerCommands(CCommand... commands) {
    Arrays.<CCommand>stream(commands).forEach(command -> {
        
        });
  }
  
  public static WaveBukkit getInstance() {
    return instance;
  }
  public static com.hidan2.folialib.FoliaLib getFolia() {
	   return foliaLib;
	}
  public static Storage getStorage() {
    return storage;
  }
  
  private void setupSimpleCommandMap() {
    this.spm = (SimplePluginManager)getServer().getPluginManager();
    Field f = null;
    try {
      f = SimplePluginManager.class.getDeclaredField("commandMap");
    } catch (Exception e) {
      e.printStackTrace();
    } 
    f.setAccessible(true);
    try {
      scm = (SimpleCommandMap)f.get(this.spm);
    } catch (Exception e) {
      e.printStackTrace();
    } 
  }
  
  public static SimpleCommandMap getCommandMap() {
    return scm;
  }
  
  public static ExecutorService getExecutorService() {
    return executorService;
  }
  
  public static WavePlayerManager getPlayerManager() {
    return Manager;
  }
  
  public static WavePlayerController getPlayerController() {
    return playerController;
  }
  
  public void onDisable() {
    this.uuidFetcher.shutdown();
  }
}
