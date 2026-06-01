package me.RafaelAulerDeMeloAraujo.Coins;


import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import me.RafaelAulerDeMeloAraujo.SpecialAbility.API;
import net.wavemc.core.bukkit.WaveBukkit;
import net.wavemc.core.bukkit.account.WavePlayer;

public class PayCoins implements CommandExecutor, Listener {
   public static boolean isNumeric(String str) {
      try {
         Integer.parseInt(str);
         return true;
      } catch (NumberFormatException var2) {
         return false;
      }
   }

   public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
      Player p = (Player)sender;
      if (cmd.getName().equalsIgnoreCase("paycoins")) {
            if (args.length == 0) {
               sender.sendMessage(String.valueOf(API.NomeServer) + " §8-> §cUse: /paycoins [Player] [Quantity]");
               return true;
            }

            Player target = Bukkit.getPlayerExact(args[0]);
            if (target == null || !(target instanceof Player)) {
               sender.sendMessage(String.valueOf(API.NomeServer) + " §8-> §cThis player is offline.");
               return true;
            }

            if (isNumeric(args[1])) {
               int coins = Integer.parseInt(args[1].replace("-", ""));
               if (Coins.getCoins(p) < coins) {
                  p.sendMessage(String.valueOf(API.NomeServer) + " §8-> §cYou dont have enought money!");
                  return true;
               }

               Coins.addCoins(target, coins);
               Coins.removeCoins(p, coins);
               WavePlayer t1 = WaveBukkit.getInstance().getPlayerManager().getPlayer(target.getName());
			   try {
				WaveBukkit.getInstance().getPlayerManager().getController().save(t1);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
               p.sendMessage(String.valueOf(API.NomeServer) + " §8-> §7You donate to: §d" + target.getName() + " §7§ §2" + coins + " Coins§7.");
               target.sendMessage(String.valueOf(API.NomeServer) + " §8-> §7You receive from the player: §d" + p.getName() + " §7§ §2" + coins + " Coins§7.");
            }
         }
	return false;
      }
   {

   }
}
