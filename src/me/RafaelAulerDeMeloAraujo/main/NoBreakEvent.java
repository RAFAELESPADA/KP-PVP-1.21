/*    */ package me.RafaelAulerDeMeloAraujo.main;
/*    */ 


import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerBucketFillEvent;

import me.RafaelAulerDeMeloAraujo.SpecialAbility.API;
import me.RafaelAulerDeMeloAraujo.SpecialAbility.Join;

public final class NoBreakEvent
  implements Listener, CommandExecutor
{
  public static ArrayList<Player> embuild = new ArrayList<Player>();
  
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
  {
    Player p = (Player)sender;
    if (cmd.getName().equalsIgnoreCase("kpeditmode")) {
      if (p.hasPermission("kitpvp.editmode"))
      {
        if (args.length == 0)
        {
          if (!embuild.contains(p))
          {
            embuild.add(p);
            p.sendMessage(String.valueOf(API.NomeServer) + "§aYou can now edit the kitpvp arena");
          }
          else
          {
            embuild.remove(p);
            p.sendMessage(String.valueOf(API.NomeServer) + "§cYou can no longer edit the kitpvp arena");
          }
        }
        else
        {
          Player t = Bukkit.getPlayer(args[0]);
          if (t == null)
          {
            p.sendMessage(API.NomeServer + "§cThis player is offline");
            return true;
          }
          if (!embuild.contains(t))
          {
            embuild.add(t);
            p.sendMessage(String.valueOf(API.NomeServer) + "§aNow: §7" + t.getName() + " §acan edit kitpvp arena");
          }
          else
          {
            embuild.remove(t);
            p.sendMessage(String.valueOf(API.NomeServer) + "§aPlayer: §7" + t.getName() + " §ano longer can edit kitpvp arena");
          }
        }
      }
      else {
        p.sendMessage(String.valueOf(API.NomeServer) + "§cYou don't have permission to do this!");
      }
    }
    return false;
  }
  
  @EventHandler
  public void aoconstruir(BlockPlaceEvent e)
  {
    Player p = e.getPlayer();
    if (!embuild.contains(p) && Join.game.contains(e.getPlayer().getName()) && !Main.getInstance().getConfig().getBoolean("EnableBuildingOnKitPvP")) {
      e.setCancelled(true);
    }
  }
  @EventHandler
  public void aoconstruir(PlayerBucketFillEvent e)
  {
    Player p = e.getPlayer();
    if (!embuild.contains(p) && Join.game.contains(e.getPlayer().getName()) && !Main.getInstance().getConfig().getBoolean("EnableBuildingOnKitPvP")) {
      e.setCancelled(true);
    }
  }  
  @EventHandler
  public void aoconstruir(PlayerBucketEmptyEvent e)
  {
    Player p = e.getPlayer();
    if (!embuild.contains(p) && Join.game.contains(e.getPlayer().getName()) && !Main.getInstance().getConfig().getBoolean("EnableBuildingOnKitPvP")) {
      e.setCancelled(true);
    }
  }
  @EventHandler
  public void aoconstruir(BlockBreakEvent e)
  {
    Player p = e.getPlayer();
    if (!embuild.contains(p) && Join.game.contains(e.getPlayer().getName())  && !Main.getInstance().getConfig().getBoolean("EnableBuildingOnKitPvP")) {
      e.setCancelled(true);
    }
  }
}
