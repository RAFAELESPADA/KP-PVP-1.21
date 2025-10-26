package me.RafaelAulerDeMeloAraujo.Coins;

import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.wavemc.core.bukkit.WaveBukkit;
import net.wavemc.core.bukkit.account.WavePlayer;



@SuppressWarnings("unused")
public class RemoveCoins implements CommandExecutor {
	public static boolean isNumeric(String str) {
		try {
			Integer.parseInt(str);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (cmd.getName().equalsIgnoreCase("removecoins")) {
			if (!sender.hasPermission("kitpvp.coins")) {
				sender.sendMessage("You dont have permission");
			} else {
				if (args.length == 0) {
					sender.sendMessage( "§c§l/removecoins [player] [amount]");
					return true;
				}
				Player target = Bukkit.getPlayerExact(args[0]);
				if ((target == null) || (!(target instanceof Player))) {
					sender.sendMessage( "§c§lThe Player is offline");
					return true;
				}
				if (isNumeric(args[1])) {
					Integer coins = Integer.parseInt(args[1]);
					Coins.removeCoins(target, coins);
					sender.sendMessage( "§eYou remove from the player " + target.getName() + "" + coins
							+ "§bCoins");
					target.sendMessage( "§e" + coins
							+ "§bCoins has been removed from your account!");
					target.sendMessage( "§6Your balance is updated!");
					WavePlayer t1 = WaveBukkit.getInstance().getPlayerManager().getPlayer(target.getName());
					try {
						WaveBukkit.getInstance().getPlayerManager().getController().save(t1);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		return false;
	}
}
