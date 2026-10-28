package me.RafaelAulerDeMeloAraujo.main;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerBucketFillEvent;

import io.papermc.paper.event.player.PlayerOpenSignEvent;

import me.RafaelAulerDeMeloAraujo.Listeners.TrainingArea;
import me.RafaelAulerDeMeloAraujo.SpecialAbility.API;
import me.RafaelAulerDeMeloAraujo.SpecialAbility.Join;

public final class NoBreakEvent implements Listener, CommandExecutor {

    public static final ArrayList<Player> embuild = new ArrayList<>();

    private final TrainingArea trainingArea;

    public NoBreakEvent(TrainingArea trainingArea) {
        this.trainingArea = trainingArea;
    }

    private boolean isTrainingArea(Location location) {
        return trainingArea != null
                && trainingArea.isInside(location);
    }

    private boolean isAllowed(Player player, Location location) {
        return embuild.contains(player)
                || isTrainingArea(location);
    }

    private boolean shouldBlockKitPvP(Player player) {
        return Join.game.contains(player.getName())
                && !Main.getInstance().getConfig()
                        .getBoolean("EnableBuildingOnKitPvP");
    }

    @Override
    public boolean onCommand(
            CommandSender sender,
            Command cmd,
            String label,
            String[] args) {

        if (!cmd.getName().equalsIgnoreCase("kpeditmode")) {
            return false;
        }

        if (!(sender instanceof Player)) {
            sender.sendMessage("This command can only be used by a player.");
            return true;
        }

        Player player = (Player) sender;

        if (!player.hasPermission("kitpvp.editmode")) {
            player.sendMessage(
                    API.NomeServer + "§cYou do not have permission!"
            );
            return true;
        }

        if (args.length == 0) {
            if (embuild.remove(player)) {
                player.sendMessage(
                        API.NomeServer + "§cEdit mode disabled."
                );
            } else {
                embuild.add(player);
                player.sendMessage(
                        API.NomeServer + "§aEdit mode enabled."
                );
            }
            return true;
        }

        Player target = Bukkit.getPlayerExact(args[0]);

        if (target == null) {
            player.sendMessage(
                    API.NomeServer + "§cThis player is offline."
            );
            return true;
        }

        if (embuild.remove(target)) {
            player.sendMessage(
                    API.NomeServer + "§cEdit mode disabled for §7"
                            + target.getName()
            );
        } else {
            embuild.add(target);
            player.sendMessage(
                    API.NomeServer + "§aEdit mode enabled for §7"
                            + target.getName()
            );
        }

        return true;
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onBlockPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        Location location = event.getBlockPlaced().getLocation();

        // Jogadores em modo de edição podem construir.
        if (embuild.contains(player)) {
            return;
        }

        // Permite construção na área de treinamento.
        if (isTrainingArea(location)) {
            return;
        }

        // Bloqueia construção durante o KitPvP.
        if (shouldBlockKitPvP(player)) {
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Location location = event.getBlock().getLocation();

        if (isAllowed(player, location)) {
            return;
        }

        if (shouldBlockKitPvP(player)) {
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onBucketEmpty(PlayerBucketEmptyEvent event) {
        Player player = event.getPlayer();
        Location location = event.getBlock().getRelative(
                event.getBlockFace()
        ).getLocation();

        if (isAllowed(player, location)) {
            return;
        }

        if (shouldBlockKitPvP(player)) {
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onBucketFill(PlayerBucketFillEvent event) {
        Player player = event.getPlayer();
        Location location = event.getBlock().getLocation();

        if (isAllowed(player, location)) {
            return;
        }

        if (shouldBlockKitPvP(player)) {
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onSignOpen(PlayerOpenSignEvent event) {
        Player player = event.getPlayer();
        Location location = event.getSign().getLocation();

        if (isAllowed(player, location)) {
            return;
        }

        if (shouldBlockKitPvP(player)) {
            event.setCancelled(true);
        }
    }
}