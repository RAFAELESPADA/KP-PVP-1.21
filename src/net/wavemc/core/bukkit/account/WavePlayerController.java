package net.wavemc.core.bukkit.account;

import com.hidan.folialib.FoliaLib;
import com.hidan.folialib.wrapper.task.WrappedTask;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import me.RafaelAulerDeMeloAraujo.main.Main;
import net.wavemc.core.bukkit.WaveBukkit;
import net.wavemc.core.bukkit.account.WavePlayer;
import net.wavemc.core.bukkit.account.provider.PlayerPvP;
import net.wavemc.core.bukkit.data.HelixDataStorageController;
import net.wavemc.core.storage.StorageConnection;
import net.wavemc.core.storage.provider.SettingsManager;
import net.wavemc.core.storage.provider.Yaml2;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class WavePlayerController extends HelixDataStorageController<WavePlayer> {
  public WavePlayerController(WaveBukkit plugin) {
    super(plugin);
  }
  
  static SettingsManager settings = SettingsManager.getInstance();
  
  public void save(WavePlayer wavePlayer) throws IOException {
    Player p = Bukkit.getPlayerExact(wavePlayer.getName());
    WavePlayer paramPlayer = wavePlayer;
    
    if (WaveBukkit.getInstance().getConfig().getBoolean("mysql.enable")) {
      try {
        com.hidan2.folialib.FoliaLib foliaLib = WaveBukkit.getFolia();
        foliaLib.getScheduler().runLaterAsync(task -> {
              try {
            	  try (StorageConnection storageConnection = WaveBukkit.getStorage().newConnection()) {
                      
            	  ResultSet resultSet = storageConnection.query("select * from wave_pvp where ID = '" + p.getUniqueId() + "'");
                  String update = "update wave_pvp set `kills` = '" + String.valueOf(wavePlayer.getPvp().getKills()) + "', `passouchallenge` = '" + String.valueOf(wavePlayer.getPvp().getPassouchallenge()) + "', `deaths` = '" + String.valueOf(wavePlayer.getPvp().getDeaths()) + "', `killstreak` = '" + String.valueOf(wavePlayer.getPvp().getKillstreak()) + "' , `killsfps` = '" + String.valueOf(wavePlayer.getPvp().getKillsfps()) + "', `deathsfps` = '" + String.valueOf(wavePlayer.getPvp().getDeathsfps()) + "', `name` = '" + wavePlayer.getName() + "', `winssumo` = '" + String.valueOf(wavePlayer.getPvp().getWinssumo()) + "', `losessumo` = '" + String.valueOf(wavePlayer.getPvp().getDeathssumo()) + "', `kssumo` = '" + String.valueOf(wavePlayer.getPvp().getWinstreaksumo()) + "', `wins1v1` = '" + String.valueOf(wavePlayer.getPvp().getWinstreakx1()) + "', `deaths1v1` = '" + String.valueOf(wavePlayer.getPvp().getDeathsx1()) + "', `ks1v1` = '" + String.valueOf(wavePlayer.getPvp().getWinsx1()) + "', `coins` = '" + String.valueOf(wavePlayer.getPvp().getCoins()) + "', `thepitkills` = '" + String.valueOf(wavePlayer.getPvp().getThepitkills()) + "', `thepitdeaths` = '" + String.valueOf(wavePlayer.getPvp().getThepitdeaths()) + "', `thepitstreak` = '" + String.valueOf(wavePlayer.getPvp().getThepitstreak()) + "', `gold` = '" + String.valueOf(wavePlayer.getPvp().getGold()) + "', `thepitxp` = '" + String.valueOf(wavePlayer.getPvp().getThepitxp()) + "', `xp` = '" + String.valueOf(wavePlayer.getPvp().getXp()) + "' where `ID` = '" + String.valueOf(p.getUniqueId() + "'");
                  String insert = "insert into wave_pvp (name, kills, passouchallenge, deaths, killstreak, killsfps , deathsfps , winssumo , losessumo , kssumo , wins1v1 , deaths1v1 , ks1v1, coins, xp, thepitkills, thepitdeaths, thepitstreak, gold, thepitxp, ID) values ('" + wavePlayer.getName() + "', '" + wavePlayer.getPvp().getKills() + "', '" + wavePlayer.getPvp().getDeaths() + "', '" + wavePlayer.getPvp().getPassouchallenge() + "', '" + wavePlayer.getPvp().getKillstreak() + "', '" + wavePlayer.getPvp().getKillsfps() + "', '" + wavePlayer.getPvp().getDeathsfps() + "', '" + wavePlayer.getPvp().getWinssumo() + "', '" + wavePlayer.getPvp().getDeathssumo() + "', '" + wavePlayer.getPvp().getWinstreaksumo() + "', '" + wavePlayer.getPvp().getWinsx1() + "', '" + wavePlayer.getPvp().getDeathsx1() + "', '" + wavePlayer.getPvp().getWinstreakx1() + "', '" + wavePlayer.getPvp().getCoins() + "', '" + wavePlayer.getPvp().getXp() + "', '" + wavePlayer.getPvp().getThepitkills() + "', '" + wavePlayer.getPvp().getThepitdeaths() + "', '" + wavePlayer.getPvp().getThepitstreak() + "', '" + wavePlayer.getPvp().getThepitxp() + "', '" + wavePlayer.getPvp().getGold() + "', '" +p.getUniqueId() + "')";
                  if (resultSet.next()) {
                      storageConnection.execute(update);
                  } else {
                      storageConnection.execute(insert);
                  }
                  resultSet.close();
                  if (!WaveBukkit.getInstance().getConfig().getBoolean("mysql.enable"))
                      storageConnection.close();
              } catch (Exception exception) {
                exception.printStackTrace();
              } 
            	 
              } catch (Exception exception2) {
                  exception2.printStackTrace();
                } }, 40L);
      } catch (Exception exception) {
        throw new RuntimeException(exception);
      } 
    } else {
      YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(new File(WaveBukkit.getInstance().getDataFolder(), "data.yml"));
      yamlConfiguration.options().copyDefaults(true);
      Yaml2 config = new Yaml2((JavaPlugin)WaveBukkit.getInstance(), WaveBukkit.getInstance().getDataFolder(), "data.yml", true, true);
      Bukkit.getConsoleSender().sendMessage("Saving " + wavePlayer.getName() + " stats to file!");
      if (p != null) {
    	    settings.getData().set(
    	        "stats." + paramPlayer.getUuid() + ".name",
    	        p.getName()
    	    );
    	} else {
    	    settings.getData().set(
    	        "stats." + paramPlayer.getUuid() + ".name",
    	        wavePlayer.getName()
    	    );
    	}
      if (settings.getData().get("stats." + paramPlayer.getUuid().toString() + ".name") != wavePlayer.getName()) {
        settings.getData().set("stats." + paramPlayer.getUuid().toString() + ".name", wavePlayer.getName());
      } else {
        settings.getData().set("stats." + paramPlayer.getUuid().toString() + ".name", wavePlayer.getName());
      } 
      com.hidan2.folialib.FoliaLib foliaLib = WaveBukkit.getFolia();
      foliaLib.getScheduler().runLaterAsync(task -> {
            settings.getData().set("stats." + paramPlayer.getUuid().toString() + ".ID", paramPlayer.getPvp().getUuid().toString());
            settings.getData().set("stats." + paramPlayer.getUuid().toString() + ".kills", Integer.valueOf(wavePlayer.getPvp().getKills()));
            settings.getData().set("stats." + paramPlayer.getUuid().toString() + ".deaths", Integer.valueOf(wavePlayer.getPvp().getDeaths()));
            settings.getData().set("stats." + paramPlayer.getUuid().toString() + ".killstreak", Integer.valueOf(wavePlayer.getPvp().getKillstreak()));
            settings.getData().set("stats." + paramPlayer.getUuid().toString() + ".coins", Integer.valueOf(wavePlayer.getPvp().getCoins()));
            settings.getData().set("stats." + paramPlayer.getUuid().toString() + ".xp", Integer.valueOf(wavePlayer.getPvp().getXp()));
            settings.getData().set("stats." + paramPlayer.getUuid().toString() + ".winsx1", Integer.valueOf(wavePlayer.getPvp().getWinsx1()));
            settings.getData().set("stats." + paramPlayer.getUuid().toString() + ".thepitxp", Integer.valueOf(wavePlayer.getPvp().getThepitxp()));
            settings.getData().set("stats." + paramPlayer.getUuid().toString() + ".winstreaksumo", Integer.valueOf(wavePlayer.getPvp().getWinstreaksumo()));
            settings.getData().set("stats." + paramPlayer.getUuid().toString() + ".winstreakx1", Integer.valueOf(wavePlayer.getPvp().getWinstreakx1()));
            settings.getData().set("stats." + paramPlayer.getUuid().toString() + ".winsx1", Integer.valueOf(wavePlayer.getPvp().getWinsx1()));
            settings.getData().set("stats." + paramPlayer.getUuid().toString() + ".winssumo", Integer.valueOf(wavePlayer.getPvp().getWinssumo()));
            settings.getData().set("stats." + paramPlayer.getUuid().toString() + ".thepitstreak", Integer.valueOf(wavePlayer.getPvp().getThepitstreak()));
            settings.getData().set("stats." + paramPlayer.getUuid().toString() + ".thepitkills", Integer.valueOf(wavePlayer.getPvp().getThepitkills()));
            settings.getData().set("stats." + paramPlayer.getUuid().toString() + ".thepitdeaths", Integer.valueOf(wavePlayer.getPvp().getThepitdeaths()));
            settings.getData().set("stats." + paramPlayer.getUuid().toString() + ".gold", Integer.valueOf(wavePlayer.getPvp().getGold()));
            settings.getData().set("stats." + paramPlayer.getUuid().toString() + ".deathsx1", Integer.valueOf(wavePlayer.getPvp().getDeathsx1()));
            settings.getData().set("stats." + paramPlayer.getUuid().toString() + ".killsfps", Integer.valueOf(wavePlayer.getPvp().getKillsfps()));
            settings.getData().set("stats." + paramPlayer.getUuid().toString() + ".passouchallenge", Integer.valueOf(wavePlayer.getPvp().getPassouchallenge()));
            settings.saveData();
          }, 20L);
    } 
  }
  
  public WavePlayer load(WavePlayer wavePlayer, StorageConnection storageConnection) {
	  if (WaveBukkit.getInstance().getConfig().getBoolean("mysql.enable")) {

		    try {

		        ResultSet rs = storageConnection.query(
		            "SELECT * FROM wave_pvp WHERE ID = '" +
		            wavePlayer.getUuid() + "'"
		        );

		        if (rs != null && rs.next()) {

		            wavePlayer.getPvp().setKills(rs.getInt("kills"));
		            wavePlayer.getPvp().setDeaths(rs.getInt("deaths"));
		            wavePlayer.getPvp().setKillstreak(rs.getInt("killstreak"));
		            wavePlayer.getPvp().setCoins(rs.getInt("coins"));
		            wavePlayer.getPvp().setXp(rs.getInt("xp"));

		            wavePlayer.getPvp().setKillsfps(rs.getInt("killsfps"));
		            wavePlayer.getPvp().setDeathsfps(rs.getInt("deathsfps"));

		            wavePlayer.getPvp().setWinssumo(rs.getInt("winssumo"));
		            wavePlayer.getPvp().setDeathssumo(rs.getInt("losessumo"));
		            wavePlayer.getPvp().setWinstreaksumo(rs.getInt("kssumo"));

		            wavePlayer.getPvp().setWinsx1(rs.getInt("wins1v1"));
		            wavePlayer.getPvp().setDeathsx1(rs.getInt("deaths1v1"));
		            wavePlayer.getPvp().setWinstreakx1(rs.getInt("ks1v1"));

		            wavePlayer.getPvp().setThepitkills(rs.getInt("thepitkills"));
		            wavePlayer.getPvp().setThepitdeaths(rs.getInt("thepitdeaths"));
		            wavePlayer.getPvp().setThepitstreak(rs.getInt("thepitstreak"));
		            wavePlayer.getPvp().setThepitxp(rs.getInt("thepitxp"));

		            wavePlayer.getPvp().setGold(rs.getInt("gold"));
		            wavePlayer.getPvp().setPassouchallenge(rs.getInt("passouchallenge"));
		        }

		        if (rs != null) {
		            rs.close();
		        }

		    } catch (Exception e) {
		        e.printStackTrace();
		    }

		    return wavePlayer;
		}
    Bukkit.getConsoleSender().sendMessage("LOADING " + wavePlayer.getName() + " stats from file!");
    UUID uuid = wavePlayer.getUuid();
    File f = new File(WaveBukkit.getInstance().getDataFolder(), "data.yml");
    Yaml2 config = new Yaml2((JavaPlugin)WaveBukkit.getInstance(), WaveBukkit.getInstance().getDataFolder(), "data.yml", true, true);
    wavePlayer.getPvp().setKills(settings.getData().getInt("stats." + String.valueOf(uuid + ".kills")));
    wavePlayer.getPvp().setXp(settings.getData().getInt("stats." + String.valueOf(uuid + ".xp")));
    wavePlayer.getPvp().setKillsfps(settings.getData().getInt("stats." + String.valueOf(uuid + ".killsfps")));
    wavePlayer.getPvp().setWinssumo(settings.getData().getInt("stats." + String.valueOf(uuid + ".winssumo")));
    wavePlayer.getPvp().setWinstreaksumo(
    	    settings.getData().getInt("stats." + uuid + ".winstreaksumo"));
   wavePlayer.getPvp().setDeaths(settings.getData().getInt("stats." + String.valueOf(uuid + ".deaths")));wavePlayer.getPvp().setWinsx1(
    	    settings.getData().getInt("stats." + uuid + ".winsx1"));wavePlayer.getPvp().setDeathsx1(
    	    	    settings.getData().getInt("stats." + uuid + ".deathsx1"));
    wavePlayer.getPvp().setPassouchallenge(settings.getData().getInt("stats." + String.valueOf(uuid + ".passouchallenge")));
    wavePlayer.getPvp().setWinstreakx1(
    	    settings.getData().getInt("stats." + uuid + ".winstreakx1"));
    wavePlayer.getPvp().setDeathssumo(
    	    settings.getData().getInt("stats." + uuid + ".losessumo")
    	);
    wavePlayer.getPvp().setDeathsfps(settings.getData().getInt("stats." + String.valueOf(uuid + ".deathsfps")));
    wavePlayer.getPvp().setKillstreak(settings.getData().getInt("stats." + String.valueOf(uuid + ".killstreak")));
    wavePlayer.getPvp().setCoins(settings.getData().getInt("stats." + String.valueOf(uuid + ".coins")));
    wavePlayer.getPvp().setKills(settings.getData().getInt("stats." + String.valueOf(uuid + ".kills")));
    wavePlayer.getPvp().setThepitkills(settings.getData().getInt("stats." + String.valueOf(uuid + ".thepitkills")));
    wavePlayer.getPvp().setThepitdeaths(settings.getData().getInt("stats." + String.valueOf(uuid + ".thepitdeaths")));
    wavePlayer.getPvp().setThepitstreak(settings.getData().getInt("stats." + String.valueOf(uuid + ".thepitstreak")));
    wavePlayer.getPvp().setGold(settings.getData().getInt("stats." + String.valueOf(uuid + ".gold")));
    wavePlayer.getPvp().setThepitxp(settings.getData().getInt("stats." + String.valueOf(uuid + ".thepitxp")));
    wavePlayer.getPvp().setUuid(UUID.fromString(Objects.<String>requireNonNull(config.getConfig().getString("stats." + String.valueOf(uuid) + ".ID"))));
    settings.saveData();
    return wavePlayer;
  }
  
  @Override
  public List<WavePlayer> load() {

      List<WavePlayer> players = new ArrayList<>();

      if (WaveBukkit.getInstance().getConfig().getBoolean("mysql.enable")) {

    	  try (StorageConnection storageConnection =
    	             WaveBukkit.getStorage().newConnection()) {

    	    ResultSet resultSet =
    	            storageConnection.query("SELECT * FROM wave_pvp");

    	    while (resultSet != null && resultSet.next()) {

    	        WavePlayer player = new WavePlayer(
    	                resultSet.getString("name"),
    	                UUID.fromString(resultSet.getString("ID")),
    	                true,
    	                new PlayerPvP(
    	                        0,0,0,0,0,0,0,0,0,0,
    	                        0,0,0,0,0,0,0,0,0
    	                )
    	        );

    	        load(player, storageConnection);
    	        players.add(player);
    	    }

    	    if (resultSet != null) {
    	        resultSet.close();
    	    }

    	} catch (Exception e) {
    	    e.printStackTrace();
    	}


          return players;
      }

      Bukkit.getConsoleSender().sendMessage("YAML IS GETTING LOADED!");

      ConfigurationSection section =
              settings.getData().getConfigurationSection("stats");

      if (section == null) {
          return players;
      }

      for (String uuidString : section.getKeys(false)) {

          try {

              UUID uuid = UUID.fromString(uuidString);

              String name =
                      settings.getData().getString(
                              "stats." + uuidString + ".name",
                              "Unknown"
                      );

              WavePlayer player = new WavePlayer(
                      name,
                      uuid,
                      false,
                      new PlayerPvP(
                              0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                              0, 0, 0, 0, 0, 0, 0, 0, 0
                      )
              );

              load(player, null);

              players.add(player);

          } catch (Exception e) {
              e.printStackTrace();
          }
      }

      Bukkit.getConsoleSender().sendMessage(
              "Loaded " + players.size() + " players from YAML."
      );

      return players;
  }
}
