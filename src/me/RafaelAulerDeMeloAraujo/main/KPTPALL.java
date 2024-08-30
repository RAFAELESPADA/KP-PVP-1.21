package me.RafaelAulerDeMeloAraujo.main;




import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import me.RafaelAulerDeMeloAraujo.SpecialAbility.Join;


public class KPTPALL
  implements Listener, CommandExecutor
{
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
  {
    Player p = (Player)sender;
    if (!p.hasPermission("kitpvp.tpall"))
    {
    	p.sendMessage("§c§lYou need kitpvp.tpall to run this command");
      return true;
    }
    for(String player : Join.game) {
        if(player != null) {
            Player p2 = Bukkit.getPlayer(player);
      if (p2 != p)
      {
        p2.teleport(p);
        p2.sendMessage("§7 " + p.getName() + " teleported everyone to him !");
      }
    }
    p.sendMessage("§aAll KITPVP players are in your location !");
    return false;
  }
	return false;
  }
}

