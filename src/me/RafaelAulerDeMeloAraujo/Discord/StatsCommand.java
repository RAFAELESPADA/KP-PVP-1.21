package me.RafaelAulerDeMeloAraujo.Discord;

import java.sql.SQLException;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;

import me.RafaelAulerDeMeloAraujo.ScoreboardManager.Level;
import me.RafaelAulerDeMeloAraujo.main.AntiDeathDrop;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.wavemc.core.bukkit.WaveBukkit;
import net.wavemc.core.bukkit.account.WavePlayer;
import net.wavemc.core.bukkit.account.provider.PlayerPvP;
import net.wavemc.core.storage.StorageConnection;

public class StatsCommand extends ListenerAdapter {

	@Override
	public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {

		if (!event.getName().equals("kitpvp")) {
			return;
		}
		String sub = event.getSubcommandName();

		if (sub == null) {
			return;
		}
		if (sub.equals("stats")) {

			try {
				event.deferReply().queue();
				OptionMapping option = event.getOption("jogador");

				if (option == null) {
					event.getHook().editOriginal("You must specify a player.").queue();
					return;
				}

				String player = option.getAsString();
				UUID uuid;

				WavePlayer data2 = WaveBukkit.getPlayerManager().getOfflinePlayer(player);
				if (data2 == null) {
					event.getHook().editOriginal("Player not found").queue();
					return;
				}
				try {
					uuid = data2.getUuid();
				} catch (Exception e) {
					event.getHook().editOriginal("Player not found").queue();
					return;
				}

				// Carrega do banco/arquivo
				try (StorageConnection storageConnection = WaveBukkit.getStorage().newConnection()) {
					WaveBukkit.getPlayerManager().getController().load(data2, storageConnection);
					System.out.println("Loading Player: " + data2.getName());
					System.out.println("Kills: " + data2.getPvp().getKills());
					System.out.println("Deaths: " + data2.getPvp().getDeaths());
					System.out.println("Coins: " + data2.getPvp().getCoins());
				} catch (SQLException exception) {
					exception.printStackTrace();
				}
				if (data2.getPvp() == null) {
					event.getHook().editOriginal("Player data not found").queue();
					return;
				}

				PlayerPvP data = data2.getPvp();
				double kdr = AntiDeathDrop.GetDeaths(uuid) == 0 ? (double) AntiDeathDrop.GetKills(uuid)
						: (double) AntiDeathDrop.GetKills(uuid) / (double) AntiDeathDrop.GetDeaths(uuid);

				EmbedBuilder embed = new EmbedBuilder().setTitle("📊 KITPVP Stats of " + player)
						.addField("Kills", String.valueOf(data.getKills()), true)
						.addField("Deaths", String.valueOf(data.getDeaths()), true)
						.addField("Coins", String.valueOf(data.getCoins()), true)
						.addField("KDR", String.format("%.2f", kdr), true)
						.addField("Killstreak", String.valueOf(data.getKillstreak()), true)
						.addField("Wins (Sumo)", "" + data.getWinssumo(), true)

						.addField("Losses (Sumo)", "" + data.getDeathssumo(), true)
						.addField("Winstreak (Sumo)", "" + data.getWinstreaksumo(), true)

						.addField("Wins (1v1)", "" + data.getWinsx1(), true)

						.addField("Losses (1v1)", "" + data.getDeathsx1(), true)

						.addField("Winstreak (1v1)", "" + data.getWinstreakx1(), true)

						.addField("Level", "" + Level.getLevel(uuid), true)

						.addField("XP To LevelUP", "" + Level.getXPToLevelUp(uuid), true)
						.addField("Next Level", "" + (Level.getLevel(uuid) + 1), true)
						.addField("XP", "" + data.getXp(), true);
				embed.setThumbnail("https://crafatar.com/avatars/" + player);
				embed.setColor(0x00AAFF);
				event.getHook().editOriginalEmbeds(embed.build()).queue();
				return;
			} catch (Exception ex) {
				ex.printStackTrace();

				if (!event.isAcknowledged()) {
					event.getHook().editOriginal("Internal error: " + ex.getClass().getSimpleName()).queue();
				}
			}}
			

			if (sub.equals("leaderboard")) {
				event.deferReply().queue();
				System.out.println("[DISCORD] Executing leaderboard commabd");
				System.out.println("[DISCORD] Players loaded: " + WaveBukkit.getPlayerManager().getPlayers().size());
				try {

					List<WavePlayer> topPlayers = WaveBukkit.getPlayerManager().getPlayers().stream()
							.filter(p -> p != null).filter(p -> p.getPvp() != null)
							.sorted((a, b) -> Integer.compare(b.getPvp().getKills(), a.getPvp().getKills())).limit(10)
							.toList();

					if (topPlayers.isEmpty()) {
						event.getHook().editOriginal("No players found.").queue();
						return;
					}

					StringBuilder sb = new StringBuilder();

					int pos = 1;

					for (WavePlayer player : topPlayers) {

						String medal = switch (pos) {
						case 1 -> "🥇";
						case 2 -> "🥈";
						case 3 -> "🥉";
						default -> "🏅";
						};

						sb.append(medal).append(" ").append(player.getName()).append(" • ")
								.append(player.getPvp().getKills()).append(" kills\n");

						pos++;
					}

					EmbedBuilder embed = new EmbedBuilder().setTitle("🏆 KITPVP Leaderboard")
							.setDescription(sb.toString()).setColor(0xFFAA00);

					event.getHook().editOriginalEmbeds(embed.build()).queue();

				} catch (Exception ex) {

					ex.printStackTrace();

					event.getHook().editOriginal("Leaderboard error: " + ex.getClass().getSimpleName()).queue();
				}

				return;
			}

		
		if (sub.equals("online")) {

			int online = Bukkit.getOnlinePlayers().size();

			StringBuilder players = new StringBuilder();

			Bukkit.getOnlinePlayers().stream().limit(20)
					.forEach(player -> players.append("• ").append(player.getName()).append("\n"));

			EmbedBuilder embed = new EmbedBuilder().setTitle("🟢 Players Online")
					.setDescription(players.length() == 0 ? "No Players Online." : players.toString())
					.addField("Total Online", String.valueOf(online), false).setColor(0x00FF00)
					.setTimestamp(Instant.now()).setFooter("Players online on kitpvp server.");

			event.replyEmbeds(embed.build()).queue();
		}
	}
}
