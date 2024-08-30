package me.RafaelAulerDeMeloAraujo.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.Server;
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

import me.RafaelAulerDeMeloAraujo.SpecialAbility.Join;
import me.RafaelAulerDeMeloAraujo.main.Main;

@SuppressWarnings("unused")
public class LeaveSign
  implements Listener
{
  private Main plugin;
  
  public LeaveSign(Main main)
  {
    this.plugin = main;
  }
  
  public LeaveSign() {}
  
  @EventHandler
  public void onSignChange(SignChangeEvent e)
  {
    if (e.getLine(0).equalsIgnoreCase("[kp]") && (e.getLine(1).equalsIgnoreCase("leave")) && e.getPlayer().hasPermission("kitpvp.createsigns"))
    {
      e.setLine(0, Main.messages.getString("LeaveSignLine1").replace("&", "§"));
      e.setLine(1, Main.messages.getString("LeaveSignLine2").replace("&", "§"));
      e.setLine(2, Main.messages.getString("LeaveSignLine3").replace("&", "§"));
      e.setLine(3, Main.messages.getString("LeaveSignLine4").replace("&", "§"));
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
		      if ((lines.length > 0) && (lines[0].equals(Main.messages.getString("LeaveSignLine1").replace("&", "§")) && 
		        (lines.length > 1) && (lines[1].equals(Main.messages.getString("LeaveSignLine2").replace("&", "§")) && 
		        (lines.length > 2) && (lines[2].equals(Main.messages.getString("LeaveSignLine3").replace("&", "§")) && 
		        (lines.length > 3) && (lines[3].equals(Main.messages.getString("LeaveSignLine4").replace("&", "§"))))))) {
		    	  p.chat("/kitpvp leave");
		      }
  }
  }
  }
