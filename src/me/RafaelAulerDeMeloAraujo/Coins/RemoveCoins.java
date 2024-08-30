package me.RafaelAulerDeMeloAraujo.Coins;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.helix.core.bukkit.HelixBukkit;
import net.helix.core.bukkit.account.HelixPlayer;



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
					HelixPlayer t1 = HelixBukkit.getInstance().getPlayerManager().getPlayer(target.getName());
					HelixBukkit.getInstance().getPlayerManager().getController().save(t1);
				}
			}
		}
		return false;
	}
}
