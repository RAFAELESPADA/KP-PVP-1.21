package me.RafaelAulerDeMeloAraujo.Discord;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;

import me.RafaelAulerDeMeloAraujo.ScoreboardManager.Level;
import me.RafaelAulerDeMeloAraujo.main.AntiDeathDrop;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.wavemc.core.bukkit.WaveBukkit;
import net.wavemc.core.bukkit.account.WavePlayer;
import net.wavemc.core.bukkit.account.provider.PlayerPvP;

public class StatsCommand extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {

        if (!event.getName().equals("kitpvp"))
            return;

        if (event.getSubcommandName().equals("stats")) {
          
        	String sub = event.getSubcommandName();

        	if (sub == null) {
        	    return;
        	}
        String player = event.getOption("jogador").getAsString();

        WavePlayer data2 = WaveBukkit
                .getPlayerManager().getPlayer(player);
        if (data2 == null) {
            event.reply("Player not found.")
                    .setEphemeral(true)
                    .queue();
            return;
        }

        UUID uuid = data2.getUuid();
        PlayerPvP data = data2.getPvp();

        if (data == null) {
            event.reply("Player data not found.")
                    .setEphemeral(true)
                    .queue();
            return;
        }
        double kdr = AntiDeathDrop.GetDeaths(uuid) == 0 ? (double) AntiDeathDrop.GetKills(uuid) : (double) AntiDeathDrop.GetKills(uuid) / (double) AntiDeathDrop.GetDeaths(uuid);
        
        EmbedBuilder embed = new EmbedBuilder()
                .setTitle("📊 KITPVP Stats of " + player)
                .addField("Kills", String.valueOf(data.getKills()), true)
                .addField("Deaths", String.valueOf(data.getDeaths()), true)
                .addField("Coins", String.valueOf(data.getCoins()), true)     
                .addField("KDR", String.format("%.2f",kdr), true)
                .addField("Killstreak", String.valueOf(data.getKillstreak()), true)
                .addField("Wins (Sumo)", "#" + data.getWinssumo(), true)

                .addField("Losses (Sumo)", "#" + data.getDeathssumo(), true)
        .addField("Winstreak (Sumo)", "#" + data.getWinstreaksumo(), true)

        .addField("Wins (1v1)", "#" + data.getWinsx1(), true)

        .addField("Losses (1v1)", "#" + data.getDeathsx1(), true)

        .addField("Winstreak (1v1)", "#" + data.getWinstreakx1(), true)

        .addField("Level", "#" + Level.getLevel(uuid), true)

        .addField("XP To LevelUP", "#" + Level.getXPToLevelUp(uuid), true)
        .addField("Next Level",
                "#" + (Level.getLevel(uuid) + 1),
                true)
        .addField("XP", "#" + data.getXp(), true);
        embed.setThumbnail("https://crafatar.com/avatars/" + uuid.toString());
        embed.setColor(0x00AAFF);
        event.replyEmbeds(embed.build()).queue();
    }
        if (event.getSubcommandName().equals("leaderboard")) {

            List<WavePlayer> topPlayers = WaveBukkit.getPlayerManager()
                    .getPlayers()
                    .stream()
                    .sorted((a, b) -> Integer.compare(
                            b.getPvp().getKills(),
                            a.getPvp().getKills()))
                    .limit(10)
                    .toList();

            if (topPlayers.isEmpty()) {
                event.reply("No Player Found in the leaderboard.")
                        .setEphemeral(true)
                        .queue();
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
            

                sb.append(medal)
                .append(" **")
                .append(player.getName())
                .append("**")
                .append(" • ")
                .append(player.getPvp().getKills())
                .append(" Kills\n");
                pos++;
            }
            EmbedBuilder embed = new EmbedBuilder()
                    .setTitle("🏆 Kills Leaderboard (KITPVP)")
                    .setDescription(sb.toString())
                    .setFooter("Top 10 PLAYERS BY KILLS (KITPVP)");
            embed.setColor(0xFFAA00);
            event.replyEmbeds(embed.build()).queue();
        }
        if (event.getSubcommandName().equals("online")) {

            int online = Bukkit.getOnlinePlayers().size();

            StringBuilder players = new StringBuilder();

            Bukkit.getOnlinePlayers().stream()
                    .limit(20)
                    .forEach(player ->
                            players.append("• ")
                                   .append(player.getName())
                                   .append("\n"));

            EmbedBuilder embed = new EmbedBuilder()
                    .setTitle("🟢 Players Online")
                    .setDescription(players.length() == 0
                            ? "No Players Online."
                            : players.toString())
                    .addField("Total Online", String.valueOf(online), false)
                    .setColor(0x00FF00).setTimestamp(Instant.now())
                    .setFooter("Players online on kitpvp server.");

            event.replyEmbeds(embed.build()).queue();
        }
        }}


