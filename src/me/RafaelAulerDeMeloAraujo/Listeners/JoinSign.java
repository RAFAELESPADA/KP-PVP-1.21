package me.RafaelAulerDeMeloAraujo.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.RafaelAulerDeMeloAraujo.Coins.Coins;
import me.RafaelAulerDeMeloAraujo.ScoreboardManager.Streak;
import me.RafaelAulerDeMeloAraujo.SpecialAbility.Join;
import me.RafaelAulerDeMeloAraujo.Warps.SettingsManager;
import me.RafaelAulerDeMeloAraujo.main.Main;

@SuppressWarnings("unused")
public class JoinSign
  implements Listener
{
  private Main plugin;
  SettingsManager settings = SettingsManager.getInstance();
  public JoinSign(Main main)
  {
    this.plugin = main;
  }
  
  public JoinSign() {}
  
  @EventHandler
  public void onSignChange2(SignChangeEvent e)
  {
    if (e.getLine(0).equalsIgnoreCase("[kp]") && (e.getLine(1).equalsIgnoreCase("join")) && e.getPlayer().hasPermission("kitpvp.createsigns"))
    {
      e.setLine(0, Main.messages.getString("JoinSignLine1").replace("&", "§"));
      e.setLine(1, Main.messages.getString("JoinSignLine2").replace("&", "§"));
      e.setLine(2, Main.messages.getString("JoinSignLine3").replace("&", "§"));
      e.setLine(3, Main.messages.getString("JoinSignLine4").replace("&", "§"));
    }
  }
  

  
  @EventHandler
  public void inv(PlayerInteractEvent e)
  {
	  Player p = e.getPlayer();
	  if ((e.getAction() == Action.RIGHT_CLICK_BLOCK) && (e.getClickedBlock() != null) && (
		      (e.getClickedBlock().getType() == Material.OAK_SIGN) || (e.getClickedBlock().getType() == Material.OAK_WALL_SIGN)))
		    {
		      Sign s = (Sign)e.getClickedBlock().getState();
		      String[] lines = s.getLines();
		      if ((lines.length > 0) && (lines[0].equals(Main.messages.getString("JoinSignLine1").replace("&", "§")) && 
		        (lines.length > 1) && (lines[1].equals(Main.messages.getString("JoinSignLine2").replace("&", "§")) && 
		        (lines.length > 2) && (lines[2].equals(Main.messages.getString("JoinSignLine3").replace("&", "§")) && 
		        (lines.length > 3) && (lines[3].equals(Main.messages.getString("JoinSignLine4").replace("&", "§"))))))) {
		    	  p.performCommand("kitpvp join");
		      }
  }
  }
  
@EventHandler
public void onSignChange(SignChangeEvent event) {
	Player player = event.getPlayer();
	String firstLine = event.getLine(0);

	if (player.hasPermission("kitpvp.createsigns") && firstLine.startsWith("lc")) {
		String[] lineSplit;
		if ((lineSplit = firstLine.split(" ")).length != 2) {
			return;
		}

		int level;
		try {
			level = Integer.parseInt(lineSplit[1]);
		}catch (NumberFormatException ignored) {
			player.sendMessage("§c§lSIGN §cLevel invalid");
			event.setCancelled(true);
			return;
		}

		if (level == 0 || level > 4) {
			player.sendMessage("§c§lSIGN §cWrite a level (1 to 4)");
			event.setCancelled(true);
			return;
		}

		event.setLine(0, "");
		event.setLine(1, "§c§lLEVEL " + level);
		event.setLine(2, "§b(Click)");
		event.setLine(3, "");
	}
}

@EventHandler
public void onSignOpen(PlayerInteractEvent event) {
    Player player = event.getPlayer();

    if(player != null) {
    if(Join.game.contains(player.getName())) {
    if (event.getClickedBlock() != null && event.getAction().equals(Action.RIGHT_CLICK_BLOCK)
            && (event.getClickedBlock().getState() instanceof Sign))  {
        Sign sign = (Sign) event.getClickedBlock().getState();
        String[] lines = sign.getLines();

        String levelLine = ChatColor.stripColor(lines[1]);
        String[] lineSplit;

        if ((lineSplit = levelLine.split(" ")).length != 2) {
            return;
        }

        int level;
        try {
            level = Integer.parseInt(lineSplit[1]);
        }catch (NumberFormatException ignored) {
            // This is little broken too
             player.sendMessage("§c");
             player.sendMessage("§c§lSIGN §cLevel invalid");
            return;
        }

        int coins = level * 200;
        // Bukkit.broadcastMessage

        // I think players do not need to know that someone passed lava challenge
        // Bukkit.broadcastMessage("§5§lCHALLENGE §7" + player.getName() + " passed the lava challenge! §5§l(LEVEL " + level + ")");
        // Or this method can be used:
         Streak.sendToGame("§5§lCHALLENGE §7" + player.getName() + " passed the lava challenge! §5§l(LEVEL " + level + ")");
        // but i think that its better if its only send to player (not everyone)

        World w = Bukkit.getServer().getWorld(settings.getData().getString("warps." + "challenge" + ".world"));
         double x = settings.getData().getDouble("warps." + "challenge" + ".x");
         double y = settings.getData().getDouble("warps." + "challenge" + ".y");
         double z = settings.getData().getDouble("warps." + "challenge" + ".z");
         player.teleport(new Location(w, x, y, z));
        Coins.addCoins(player, coins);
        player.sendMessage("§6§lCHALLENGE §eYou completed the lava challenge level " + level + " and earned " + coins + " coins!");
    }
    }
    }
}
}

