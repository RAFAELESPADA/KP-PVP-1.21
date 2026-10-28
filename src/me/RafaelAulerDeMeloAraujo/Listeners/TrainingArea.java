package me.RafaelAulerDeMeloAraujo.Listeners;

import me.RafaelAulerDeMeloAraujo.SpecialAbility.API;
import me.RafaelAulerDeMeloAraujo.SpecialAbility.Habilidade;
import me.RafaelAulerDeMeloAraujo.main.Main;
import me.RafaelAulerDeMeloAraujo.main.NoBreakEvent;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.CrossbowMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * KP-PVP training area for Paper/Purpur/Folia 1.21.11.
 * Saves BlockData; it does not save chest contents, sign text, or block NBT.
 */
public final class TrainingArea implements Listener, CommandExecutor {

    private static final int MAX_BLOCKS = 200000;
    // Limits the amount of restoration work scheduled per tick to reduce lag.
    private static final int RESTORE_BLOCKS_PER_TICK = 25;
    private static final long RESTORE_INTERVAL_TICKS = 12000L; // 10 minutes
    private static final long LIGHTNING_INTERVAL_TICKS = 600L; // 30 seconds
    private static final int TRAINING_EFFECT_DURATION_TICKS = 60;
    private final NamespacedKey trainingCrossbowKey;
    private final Set<UUID> crossbowReloadPending =
            ConcurrentHashMap.newKeySet();
    private final File file;
    private final Object fileLock = new Object();
    private final Map<UUID, Long> trainingCrossbowCooldowns =
            new ConcurrentHashMap<>();
    private Location pos1;
    private Location pos2;

    private volatile boolean configured;
    private volatile String worldName;
    private volatile int minX;
    private volatile int minY;
    private volatile int minZ;
    private volatile int maxX;
    private volatile int maxY;
    private volatile int maxZ;
    private volatile List<String> snapshot = Collections.emptyList();

    private final AtomicBoolean restoreInProgress = new AtomicBoolean(false);
    private final AtomicBoolean automaticTasksStarted = new AtomicBoolean(false);

    private final Map<UUID, InventoryBackup> trainingBackups = new ConcurrentHashMap<UUID, InventoryBackup>();
    private final Map<UUID, PotionBackup> trainingPotionBackups = new ConcurrentHashMap<UUID, PotionBackup>();
    private final Set<UUID> potionTasks = ConcurrentHashMap.newKeySet();

    private static final class InventoryBackup {
        private final ItemStack[] storage;
        private final ItemStack[] armor;
        private final ItemStack offhand;
        private final int heldSlot;
        private final GameMode gameMode;
        private InventoryBackup(Player player) {
            storage = cloneItems(player.getInventory().getStorageContents());
            armor = cloneItems(player.getInventory().getArmorContents());

            ItemStack item = player.getInventory().getItemInOffHand();
            offhand = item == null ? null : item.clone();

            heldSlot = player.getInventory().getHeldItemSlot();
            gameMode = player.getGameMode();
        }

        private void restore(Player player) {
            player.getInventory().clear();
            player.getInventory().setStorageContents(cloneItems(storage));
            player.getInventory().setArmorContents(cloneItems(armor));
            player.getInventory().setItemInOffHand(offhand == null ? null : offhand.clone());
            player.getInventory().setHeldItemSlot(heldSlot);
            player.setGameMode(gameMode);
            player.updateInventory();
        }
    }
    public void handleTrainingArrival(Player player) {
        if (player == null || !player.isOnline()) {
            return;
        }

        if (!isInside(player.getLocation())) {
            player.sendMessage(
                    "§cThe warp destination is outside the training area!"
            );
            World w = Objects.requireNonNull(
                    Bukkit.getWorld(
                            Main.plugin.getConfig().getString("Spawn.World")
                    )
            );

            double x = Main.plugin.getConfig().getDouble("Spawn.X");
            double y = Main.plugin.getConfig().getDouble("Spawn.Y");
            double z = Main.plugin.getConfig().getDouble("Spawn.Z");

            Location lobby = new Location(w, x, y, z);
            lobby.setPitch((float) Main.plugin.getConfig().getDouble("Spawn.Pitch"));
            lobby.setYaw((float) Main.plugin.getConfig().getDouble("Spawn.Yaw"));
            Player p = player;
            p.getScheduler().runDelayed(
                    Main.getPlugin(),
                    task -> {

                        if (!p.isOnline()) {
                            return;
                        }

                        Main.getFolia().getScheduler().teleportAsync(p, lobby);

                        p.getInventory().clear();
                        p.getInventory().setArmorContents(null);

                        p.setExp(0F);
                        p.setExhaustion(20F);
                        p.setFireTicks(0);
                        p.setFoodLevel(20);

                        if (!Main.getInstance().getConfig().getBoolean("DisableInitialItems")) {

                            ItemStack kitsr = Main.getInstance().getConfig().getItemStack("KitsItem");
                            if (kitsr != null) {
                                ItemMeta kitsr2 = kitsr.getItemMeta();
                                kitsr2.setDisplayName(
                                        Main.messages.getString("KitsItemName").replace("&", "§")
                                );
                                kitsr.setItemMeta(kitsr2);
                            }

                            ItemStack kits = Main.getInstance().getConfig().getItemStack("ShopItem");
                            if (kits != null) {
                                ItemMeta kits2 = kits.getItemMeta();
                                kits2.setDisplayName(
                                        Main.messages.getString("ShopItemName").replace("&", "§")
                                );
                                kits.setItemMeta(kits2);
                            }

                            ItemStack st = Main.getInstance().getConfig().getItemStack("1v1Item");
                            if (st != null) {
                                ItemMeta st2 = st.getItemMeta();
                                st2.setDisplayName(
                                        Main.messages.getString("1v1ItemName").replace("&", "§")
                                );
                                st.setItemMeta(st2);
                            }

                            ItemStack stats = Main.getInstance().getConfig().getItemStack("StatsItem");
                            if (stats != null) {
                                ItemMeta stats2 = stats.getItemMeta(); // CORRIGIDO
                                stats2.setDisplayName(
                                        Main.messages.getString("StatsItemName").replace("&", "§")
                                );
                                stats.setItemMeta(stats2);

                                p.getInventory().setItem(
                                        Main.getInstance().getConfig().getInt("StatsItemSlot"),
                                        stats
                                );
                            }

                            ItemStack clickTest = Main.getInstance().getConfig().getItemStack("ClickTestItem");
                            if (clickTest != null) {
                                ItemMeta clickMeta = clickTest.getItemMeta();
                                clickMeta.setDisplayName(
                                        Main.messages.getString("ClickTestItemName").replace("&", "§")
                                );
                                clickTest.setItemMeta(clickMeta);

                                if (!Main.getInstance().getConfig().getBoolean("DisableClickTestItem")) {
                                    p.getInventory().setItem(
                                            Main.getInstance().getConfig().getInt("ClickTestItemSlot"),
                                            clickTest
                                    );
                                }
                            }

                            ItemStack warp = Main.getInstance().getConfig().getItemStack("WarpItem");
                            if (warp != null) {
                                ItemMeta warp2 = warp.getItemMeta();
                                warp2.setDisplayName("§aWarps");
                                warp.setItemMeta(warp2);

                                if (!Main.getInstance().getConfig().getBoolean("DisableWarpItem")) {
                                    p.getInventory().setItem(
                                            Main.getInstance().getConfig().getInt("WarpItemSlot"),
                                            warp
                                    );
                                }
                            }

                            ItemStack sair = Main.getInstance().getConfig().getItemStack("LeaveItem");
                            if (sair != null) {
                                ItemMeta sair2 = sair.getItemMeta();
                                sair2.setDisplayName(
                                        Main.messages.getString("LeaveItemName").replace("&", "§")
                                );
                                sair.setItemMeta(sair2);

                                if (!Main.getInstance().getConfig().getBoolean("DisableLeaveItem")) {
                                    p.getInventory().setItem(
                                            Main.getInstance().getConfig().getInt("LeaveItemSlot"),
                                            sair
                                    );
                                }
                            }

                            if (kitsr != null) {
                                p.getInventory().setItem(
                                        Main.getInstance().getConfig().getInt("KitsItemSlot"),
                                        kitsr
                                );
                            }

                            if (kits != null) {
                                p.getInventory().setItem(
                                        Main.getInstance().getConfig().getInt("ShopItemSlot"),
                                        kits
                                );
                            }

                            if (st != null) {
                                p.getInventory().setItem(
                                        Main.getInstance().getConfig().getInt("1v1ItemSlot"),
                                        st
                                );
                            }
                        }

                        API.tirarEfeitos(p);


                    },
                    null,
                    20L
            );
        
            return;
        }

        UUID uuid = player.getUniqueId();

        InventoryBackup backup = new InventoryBackup(player);

        // Guarda o inventário original uma única vez.
        if (trainingBackups.putIfAbsent(uuid, backup) == null) {
            giveTrainingKit(player);
        }

        // Garante os efeitos de treinamento.
        startTrainingEffects(player);
        player.setGameMode(GameMode.SURVIVAL);
    }
    private static final class PotionBackup {
        private final PotionEffect oldSpeed;
        private final PotionEffect oldRegeneration;
        private final long capturedAtMillis;

        private PotionBackup(Player player) {
            oldSpeed = player.getPotionEffect(PotionEffectType.SPEED);
            oldRegeneration = player.getPotionEffect(PotionEffectType.REGENERATION);
            capturedAtMillis = System.currentTimeMillis();
        }

        private void restore(Player player) {
            player.removePotionEffect(PotionEffectType.SPEED);
            player.removePotionEffect(PotionEffectType.REGENERATION);
            restoreEffect(player, oldSpeed);
            restoreEffect(player, oldRegeneration);
        }

        private void restoreEffect(Player player, PotionEffect original) {
            if (original == null) {
                return;
            }

            long elapsedTicks = (System.currentTimeMillis() - capturedAtMillis) / 50L;
            long remainingTicks = original.getDuration() - elapsedTicks;
            if (remainingTicks <= 0L) {
                return;
            }

            PotionEffect restored = new PotionEffect(
                    original.getType(),
                    (int) Math.min(Integer.MAX_VALUE, remainingTicks),
                    original.getAmplifier(),
                    original.isAmbient(),
                    original.hasParticles(),
                    original.hasIcon()
            );
            player.addPotionEffect(restored, true);
        }
    }

    public TrainingArea() {
        this.file = new File(Main.plugin.getDataFolder(), "treino.yml");

        this.trainingCrossbowKey =
                new NamespacedKey(Main.plugin, "training_crossbow");
        load();
    }

    private static ItemStack[] cloneItems(ItemStack[] items) {
        ItemStack[] copy = new ItemStack[items.length];
        for (int i = 0; i < items.length; i++) {
            copy[i] = items[i] == null ? null : items[i].clone();
        }
        return copy;
    }

    private void load() {
        YamlConfiguration yaml = YamlConfiguration.loadConfiguration(file);
        pos1 = readCorner(yaml, "selection.pos1");
        pos2 = readCorner(yaml, "selection.pos2");

        configured = yaml.getBoolean("region.configured", false);
        worldName = yaml.getString("region.world");
        minX = yaml.getInt("region.minX");
        minY = yaml.getInt("region.minY");
        minZ = yaml.getInt("region.minZ");
        maxX = yaml.getInt("region.maxX");
        maxY = yaml.getInt("region.maxY");
        maxZ = yaml.getInt("region.maxZ");
        snapshot = Collections.unmodifiableList(new ArrayList<String>(yaml.getStringList("blocks")));
    }

    private Location readCorner(YamlConfiguration yaml, String path) {
        String savedWorld = yaml.getString(path + ".world");
        if (savedWorld == null) {
            return null;
        }

        World world = Bukkit.getWorld(savedWorld);
        if (world == null) {
            // The world may not be loaded yet; the selection can be set again in-game.
            return null;
        }

        return new Location(
                world,
                yaml.getInt(path + ".x"),
                yaml.getInt(path + ".y"),
                yaml.getInt(path + ".z")
        );
    }

    private void saveCorner(String key, Location location) {
        synchronized (fileLock) {
            YamlConfiguration yaml = YamlConfiguration.loadConfiguration(file);
            String path = "selection." + key;
            yaml.set(path + ".world", location.getWorld().getName());
            yaml.set(path + ".x", location.getBlockX());
            yaml.set(path + ".y", location.getBlockY());
            yaml.set(path + ".z", location.getBlockZ());
            saveYamlLocked(yaml);
        }
    }

    private void saveYamlLocked(YamlConfiguration yaml) {
        try {
            File parent = file.getParentFile();
            if (parent != null && !parent.exists() && !parent.mkdirs() && !parent.exists()) {
                throw new IOException("Could not create the file directory.");
            }
            yaml.save(file);
        } catch (IOException ex) {
            Main.plugin.getLogger().severe("Error saving treino.yml: " + ex.getMessage());
        }
    }

    public boolean isInside(Location location) {
        if (!configured || location == null || location.getWorld() == null || worldName == null) {
            return false;
        }
        if (!worldName.equals(location.getWorld().getName())) {
            return false;
        }

        int x = location.getBlockX();
        int y = location.getBlockY();
        int z = location.getBlockZ();
        return x >= minX && x <= maxX
                && y >= minY && y <= maxY
                && z >= minZ && z <= maxZ;
    }

    private boolean isRegionWorld(Location location) {
        return configured && location != null && location.getWorld() != null
                && worldName != null && worldName.equals(location.getWorld().getName());
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("This command can only be used by a player.");
            return true;
        }

        Player player = (Player) sender;
        if (!player.hasPermission("kppvp.admin")) {
            player.sendMessage("§cYou do not have permission. Requires kppvp.admin.");
            return true;
        }

        if (args.length == 0) {
            player.sendMessage("§eTraining area commands:");
            player.sendMessage("§f/training pos1");
            player.sendMessage("§f/training pos2");
            player.sendMessage("§f/training save");
            player.sendMessage("§f/training restore");
            return true;
        }

        String subcommand = args[0].toLowerCase(Locale.ROOT);
        switch (subcommand) {
            case "pos1":
                pos1 = player.getLocation().getBlock().getLocation();
                saveCorner("pos1", pos1);
                player.sendMessage("§aFirst corner selected.");
                return true;

            case "pos2":
                pos2 = player.getLocation().getBlock().getLocation();
                saveCorner("pos2", pos2);
                player.sendMessage("§aSecond corner selected.");
                return true;

            case "save":
            case "salvar":
                saveArea(player);
                return true;

            case "restore":
            case "restaurar":
            case "restaure":
                restoreArea(player);
                return true;

            default:
                player.sendMessage("§cUse /training to view the commands.");
                return true;
        }
    }

    private void saveArea(final Player player) {
        if (pos1 == null || pos2 == null) {
            player.sendMessage("§cSet pos1 and pos2 first.");
            return;
        }
        if (pos1.getWorld() == null || pos2.getWorld() == null
                || !pos1.getWorld().getName().equals(pos2.getWorld().getName())) {
            player.sendMessage("§cBoth corners must be in the same world.");
            return;
        }

        final World world = pos1.getWorld();
        final int x1 = Math.min(pos1.getBlockX(), pos2.getBlockX());
        final int y1 = Math.min(pos1.getBlockY(), pos2.getBlockY());
        final int z1 = Math.min(pos1.getBlockZ(), pos2.getBlockZ());
        final int x2 = Math.max(pos1.getBlockX(), pos2.getBlockX());
        final int y2 = Math.max(pos1.getBlockY(), pos2.getBlockY());
        final int z2 = Math.max(pos1.getBlockZ(), pos2.getBlockZ());

        final int sizeX = x2 - x1 + 1;
        final int sizeY = y2 - y1 + 1;
        final int sizeZ = z2 - z1 + 1;
        long volume = (long) sizeX * sizeY * sizeZ;

        if (volume <= 0L || volume > MAX_BLOCKS) {
            player.sendMessage("§cInvalid or oversized area. Limit: " + MAX_BLOCKS + " blocks.");
            return;
        }

        player.sendMessage("§eSaving the original state of " + volume + " blocks...");

        final String[] data = new String[(int) volume];
        final AtomicInteger remaining = new AtomicInteger((int) volume);
        final AtomicBoolean captureFailed = new AtomicBoolean(false);

        for (int x = x1; x <= x2; x++) {
            for (int y = y1; y <= y2; y++) {
                for (int z = z1; z <= z2; z++) {
                    final int fx = x;
                    final int fy = y;
                    final int fz = z;
                    final int index = ((x - x1) * sizeY + (y - y1)) * sizeZ + (z - z1);
                    final Location location = new Location(world, fx, fy, fz);

                    try {
                        Main.getFolia().getScheduler().runAtLocation(location, task -> {
                            try {
                                data[index] = fx + "," + fy + "," + fz + "|"
                                        + location.getBlock().getBlockData().getAsString();
                            } catch (RuntimeException ex) {
                                captureFailed.set(true);
                                Main.plugin.getLogger().warning("Error capturing block "
                                        + fx + "," + fy + "," + fz + ": " + ex.getMessage());
                            } finally {
                                if (remaining.decrementAndGet() == 0) {
                                    if (captureFailed.get()) {
                                        sendMessageToPlayer(player,
                                                "§cCould not save all blocks. Check the console.");
                                    } else {
                                        finishSnapshot(player, world.getName(),
                                                x1, y1, z1, x2, y2, z2, data);
                                    }
                                }
                            }
                        });
                    } catch (RuntimeException ex) {
                        captureFailed.set(true);
                        Main.plugin.getLogger().warning("Could not schedule block read: "
                                + ex.getMessage());
                        if (remaining.decrementAndGet() == 0) {
                            sendMessageToPlayer(player,
                                    "§cCould not save all blocks. Check the console.");
                        }
                    }
                }
            }
        }
    }

    private void finishSnapshot(
            final Player player,
            final String savedWorld,
            final int x1, final int y1, final int z1,
            final int x2, final int y2, final int z2,
            String[] data) {

        final List<String> copied = Collections.unmodifiableList(
                new ArrayList<String>(Arrays.asList(data))
        );

        Main.getFolia().getScheduler().runAsync(task -> {
            try {
                synchronized (fileLock) {
                    YamlConfiguration yaml = YamlConfiguration.loadConfiguration(file);
                    yaml.set("region.configured", true);
                    yaml.set("region.world", savedWorld);
                    yaml.set("region.minX", x1);
                    yaml.set("region.minY", y1);
                    yaml.set("region.minZ", z1);
                    yaml.set("region.maxX", x2);
                    yaml.set("region.maxY", y2);
                    yaml.set("region.maxZ", z2);
                    yaml.set("blocks", copied);

                    File parent = file.getParentFile();
                    if (parent != null && !parent.exists()
                            && !parent.mkdirs() && !parent.exists()) {
                        throw new IOException("Could not create the file directory.");
                    }
                    yaml.save(file);
                }

                worldName = savedWorld;
                minX = x1;
                minY = y1;
                minZ = z1;
                maxX = x2;
                maxY = y2;
                maxZ = z2;
                snapshot = copied;
                configured = true;

                sendMessageToPlayer(player,
                        "§aTraining area saved! " + copied.size() + " blocks recorded.");
            } catch (IOException ex) {
                Main.plugin.getLogger().severe("Error saving treino.yml: " + ex.getMessage());
                sendMessageToPlayer(player, "§cError saving the area. Check the console.");
            }
        });
    }

    private void sendMessageToPlayer(final Player player, final String message) {
        Main.getFolia().getScheduler().runAtEntity(player, task -> {
            if (player.isOnline()) {
                player.sendMessage(message);
            }
        });
    }

    private void restoreArea(final Player player) {
        if (!configured || snapshot.isEmpty()) {
            if (player != null) {
                sendMessageToPlayer(player, "§cNo saved area found. Use /training save.");
            }
            return;
        }

        final World world = Bukkit.getWorld(worldName);
        if (world == null) {
            Main.plugin.getLogger().warning("The training area world is not loaded: " + worldName);
            if (player != null) {
                sendMessageToPlayer(player, "§cThe training area world is not loaded.");
            }
            return;
        }

        if (!restoreInProgress.compareAndSet(false, true)) {
            if (player != null) {
                sendMessageToPlayer(player, "§eThe area is already being restored.");
            }
            return;
        }

        // Do not enqueue thousands of region tasks at once. Dispatch only a
        // small number every tick, while each block is still changed by the
        // scheduler for its own location (safe for Folia region threading).
        final List<String> blocks = new ArrayList<String>(snapshot);
        final AtomicInteger nextIndex = new AtomicInteger(0);
        final AtomicInteger remaining = new AtomicInteger(blocks.size());
        final AtomicInteger failed = new AtomicInteger(0);

        if (player != null) {
            sendMessageToPlayer(player, "§eRestoring " + blocks.size()
                    + " blocks gradually to reduce lag...");
        }

        try {
            Main.getFolia().getScheduler().runTimer(task -> {
                int dispatchedThisTick = 0;

                while (dispatchedThisTick < RESTORE_BLOCKS_PER_TICK) {
                    int index = nextIndex.getAndIncrement();
                    if (index >= blocks.size()) {
                        task.cancel();
                        break;
                    }

                    dispatchedThisTick++;
                    final String entry = blocks.get(index);

                    try {
                        final ParsedBlock parsed = parseBlock(entry);
                        final Location location = new Location(
                                world, parsed.x, parsed.y, parsed.z);

                        Main.getFolia().getScheduler().runAtLocation(location, regionTask -> {
                            try {
                                location.getBlock().setBlockData(
                                        Bukkit.createBlockData(parsed.blockData), false);
                            } catch (RuntimeException ex) {
                                failed.incrementAndGet();
                                Main.plugin.getLogger().warning(
                                        "Error restoring block at " + parsed.x + ","
                                                + parsed.y + "," + parsed.z + ": "
                                                + ex.getMessage());
                            } finally {
                                completeRestoreBlock(player, remaining, failed);
                            }
                        });
                    } catch (RuntimeException ex) {
                        failed.incrementAndGet();
                        Main.plugin.getLogger().warning(
                                "Invalid block entry during restoration: "
                                        + ex.getMessage());
                        completeRestoreBlock(player, remaining, failed);
                    }
                }

                // Stop the dispatcher once every entry has been handed off.
                if (nextIndex.get() >= blocks.size()) {
                    task.cancel();
                }
            }, 1L, 1L);
        } catch (RuntimeException ex) {
            restoreInProgress.set(false);
            Main.plugin.getLogger().severe(
                    "Could not start the gradual training-area restoration: "
                            + ex.getMessage());
            if (player != null) {
                sendMessageToPlayer(player,
                        "§cCould not start restoration. Check the console.");
            }
        }
    }

    private void completeRestoreBlock(
            final Player player,
            AtomicInteger remaining,
            AtomicInteger failed) {
        if (remaining.decrementAndGet() != 0) {
            return;
        }

        restoreInProgress.set(false);

        if (player != null) {
            String result = failed.get() == 0
                    ? "§aTraining area restored!"
                    : "§eArea restored with " + failed.get()
                            + " error(s). Check the console.";
            sendMessageToPlayer(player, result);
        } else {
            Main.plugin.getLogger().info(
                    "Automatic training area restoration completed. Errors: "
                            + failed.get());
        }
    }

    private static final class ParsedBlock {
        private final int x;
        private final int y;
        private final int z;
        private final String blockData;

        private ParsedBlock(int x, int y, int z, String blockData) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.blockData = blockData;
        }
    }

    private ParsedBlock parseBlock(String entry) {
        String[] parts = entry.split("\\|", 2);
        if (parts.length != 2) {
            throw new IllegalArgumentException("Invalid block entry: " + entry);
        }
        String[] coordinates = parts[0].split(",", 3);
        if (coordinates.length != 3) {
            throw new IllegalArgumentException("Invalid coordinates: " + entry);
        }
        return new ParsedBlock(
                Integer.parseInt(coordinates[0]),
                Integer.parseInt(coordinates[1]),
                Integer.parseInt(coordinates[2]),
                parts[1]
        );
    }

    public void startAutomaticRestore() {
        if (!automaticTasksStarted.compareAndSet(false, true)) {
            return;
        }

        Main.getFolia().getScheduler().runTimer(task -> this.restoreArea(null),
                RESTORE_INTERVAL_TICKS, RESTORE_INTERVAL_TICKS);

        Main.getFolia().getScheduler().runTimer(task -> spawnRandomLightning(),
                LIGHTNING_INTERVAL_TICKS, LIGHTNING_INTERVAL_TICKS);
    }

    private void spawnRandomLightning() {
        if (!configured || worldName == null || maxX < minX || maxZ < minZ) {
            return;
        }

        final World world = Bukkit.getWorld(worldName);
        if (world == null) {
            return;
        }

        final ThreadLocalRandom random = ThreadLocalRandom.current();
        final int x = random.nextInt(minX, maxX + 1);
        final int z = random.nextInt(minZ, maxZ + 1);
        final Location scheduledLocation = new Location(world, x, minY, z);

        Main.getFolia().getScheduler().runAtLocation(scheduledLocation, task -> {
            if (!configured || !world.isChunkLoaded(x >> 4, z >> 4)) {
                return;
            }

            int surfaceY = world.getHighestBlockYAt(x, z) + 1;
            int strikeY = Math.max(minY, Math.min(surfaceY, maxY));
            Location lightning = new Location(world, x + 0.5, strikeY, z + 0.5);

            if (isInside(lightning)) {
                // Visual-only lightning: causes no damage or fire.
                world.strikeLightningEffect(lightning);
            }
        });
    }

    private void startTrainingEffects(final Player player) {
        final UUID uuid = player.getUniqueId();
        trainingPotionBackups.putIfAbsent(uuid, new PotionBackup(player));
        applyTrainingEffects(player);

        if (!potionTasks.add(uuid)) {
            return;
        }

        Main.getFolia().getScheduler().runAtEntityTimer(
                player,
                task -> {
                    if (!player.isOnline()) {
                        task.cancel();
                        potionTasks.remove(uuid);
                        return;
                    }

                    if (isInside(player.getLocation())) {
                        applyTrainingEffects(player);
                    } else {
                        restoreTrainingEffects(player);
                        task.cancel();
                        potionTasks.remove(uuid);
                    }
                },
                () -> potionTasks.remove(uuid),
                1L,
                20L
        );
    }

    private void applyTrainingEffects(Player player) {
        player.addPotionEffect(new PotionEffect(
                PotionEffectType.SPEED,
                TRAINING_EFFECT_DURATION_TICKS,
                0,
                true,
                false,
                true
        ), true);

        player.addPotionEffect(new PotionEffect(
                PotionEffectType.REGENERATION,
                TRAINING_EFFECT_DURATION_TICKS,
                0,
                true,
                false,
                true
        ), true);
    }

    private void restoreTrainingEffects(Player player) {
        PotionBackup backup = trainingPotionBackups.remove(player.getUniqueId());
        if (backup != null) {
            backup.restore(player);
        }
    }



    private boolean isTrainingAreaNearSpawn(Location location) {
        if (location == null || location.getWorld() == null) {
            return false;
        }

        String worldName = Main.plugin.getConfig()
                .getString("Spawn.World");

        if (worldName == null || worldName.trim().isEmpty()) {
            return false;
        }

        World spawnWorld = Bukkit.getWorld(worldName);

        if (spawnWorld == null
                || !spawnWorld.equals(location.getWorld())) {
            return false;
        }

        double spawnX = Main.plugin.getConfig()
                .getDouble("Spawn.X");

        double spawnZ = Main.plugin.getConfig()
                .getDouble("Spawn.Z");

        double dx = location.getX() - spawnX;
        double dz = location.getZ() - spawnZ;

        return (dx * dx + dz * dz) <= 2500.0;
    }
@EventHandler(ignoreCancelled = true)
public void onTrainingMove(PlayerMoveEvent event) {
    // Os teletransportes serão tratados pelo evento específico.
    if (event instanceof PlayerTeleportEvent) {
        return;
    }

    handleTrainingTransition(
            event.getPlayer(),
            event.getFrom(),
            event.getTo(),
            false
    );
}

@EventHandler(ignoreCancelled = true)
public void onTrainingTeleport(PlayerTeleportEvent event) {
    handleTrainingTransition(
            event.getPlayer(),
            event.getFrom(),
            event.getTo(),
            true
    );
}

private void handleTrainingTransition(
        Player player,
        Location from,
        Location to,
        boolean teleport
) {
    if (to == null || from.getWorld() == null || to.getWorld() == null) {
        return;
    }

    // Ignora pequenos movimentos dentro do mesmo bloco.
    // Teletransportes sempre são processados.
    if (!teleport
            && from.getWorld().equals(to.getWorld())
            && from.getBlockX() == to.getBlockX()
            && from.getBlockY() == to.getBlockY()
            && from.getBlockZ() == to.getBlockZ()) {
        return;
    }

    UUID uuid = player.getUniqueId();

    boolean wasInside = isInside(from);
    boolean isNowInside = isInside(to);

    // Entrou na área de treino.
    if (wasInside && !isNowInside) {

        restoreTrainingEffects(player);

        // O backup só é criado quando a entrada atende
        // à regra dos 50 blocos do spawn.
        InventoryBackup backup = trainingBackups.remove(uuid);

        if (backup != null) {
            backup.restore(player);
            player.updateInventory();

            player.sendMessage(
                    "§eYou left the training area. "
                    + "Old inventory restored."
            );
            World w = Objects.requireNonNull(
                    Bukkit.getWorld(
                            Main.plugin.getConfig().getString("Spawn.World")
                    )
            );

            double x = Main.plugin.getConfig().getDouble("Spawn.X");
            double y = Main.plugin.getConfig().getDouble("Spawn.Y");
            double z = Main.plugin.getConfig().getDouble("Spawn.Z");

            Location lobby = new Location(w, x, y, z);
            lobby.setPitch((float) Main.plugin.getConfig().getDouble("Spawn.Pitch"));
            lobby.setYaw((float) Main.plugin.getConfig().getDouble("Spawn.Yaw"));
            Player p = player;
            p.getScheduler().runDelayed(
                    Main.getPlugin(),
                    task -> {

                        if (!p.isOnline()) {
                            return;
                        }

                        Main.getFolia().getScheduler().teleportAsync(p, lobby);

                        p.getInventory().clear();
                        p.getInventory().setArmorContents(null);

                        p.setExp(0F);
                        p.setExhaustion(20F);
                        p.setFireTicks(0);
                        p.setFoodLevel(20);

                        if (!Main.getInstance().getConfig().getBoolean("DisableInitialItems")) {

                            ItemStack kitsr = Main.getInstance().getConfig().getItemStack("KitsItem");
                            if (kitsr != null) {
                                ItemMeta kitsr2 = kitsr.getItemMeta();
                                kitsr2.setDisplayName(
                                        Main.messages.getString("KitsItemName").replace("&", "§")
                                );
                                kitsr.setItemMeta(kitsr2);
                            }

                            ItemStack kits = Main.getInstance().getConfig().getItemStack("ShopItem");
                            if (kits != null) {
                                ItemMeta kits2 = kits.getItemMeta();
                                kits2.setDisplayName(
                                        Main.messages.getString("ShopItemName").replace("&", "§")
                                );
                                kits.setItemMeta(kits2);
                            }

                            ItemStack st = Main.getInstance().getConfig().getItemStack("1v1Item");
                            if (st != null) {
                                ItemMeta st2 = st.getItemMeta();
                                st2.setDisplayName(
                                        Main.messages.getString("1v1ItemName").replace("&", "§")
                                );
                                st.setItemMeta(st2);
                            }

                            ItemStack stats = Main.getInstance().getConfig().getItemStack("StatsItem");
                            if (stats != null) {
                                ItemMeta stats2 = stats.getItemMeta(); // CORRIGIDO
                                stats2.setDisplayName(
                                        Main.messages.getString("StatsItemName").replace("&", "§")
                                );
                                stats.setItemMeta(stats2);

                                p.getInventory().setItem(
                                        Main.getInstance().getConfig().getInt("StatsItemSlot"),
                                        stats
                                );
                            }

                            ItemStack clickTest = Main.getInstance().getConfig().getItemStack("ClickTestItem");
                            if (clickTest != null) {
                                ItemMeta clickMeta = clickTest.getItemMeta();
                                clickMeta.setDisplayName(
                                        Main.messages.getString("ClickTestItemName").replace("&", "§")
                                );
                                clickTest.setItemMeta(clickMeta);

                                if (!Main.getInstance().getConfig().getBoolean("DisableClickTestItem")) {
                                    p.getInventory().setItem(
                                            Main.getInstance().getConfig().getInt("ClickTestItemSlot"),
                                            clickTest
                                    );
                                }
                            }

                            ItemStack warp = Main.getInstance().getConfig().getItemStack("WarpItem");
                            if (warp != null) {
                                ItemMeta warp2 = warp.getItemMeta();
                                warp2.setDisplayName("§aWarps");
                                warp.setItemMeta(warp2);

                                if (!Main.getInstance().getConfig().getBoolean("DisableWarpItem")) {
                                    p.getInventory().setItem(
                                            Main.getInstance().getConfig().getInt("WarpItemSlot"),
                                            warp
                                    );
                                }
                            }

                            ItemStack sair = Main.getInstance().getConfig().getItemStack("LeaveItem");
                            if (sair != null) {
                                ItemMeta sair2 = sair.getItemMeta();
                                sair2.setDisplayName(
                                        Main.messages.getString("LeaveItemName").replace("&", "§")
                                );
                                sair.setItemMeta(sair2);

                                if (!Main.getInstance().getConfig().getBoolean("DisableLeaveItem")) {
                                    p.getInventory().setItem(
                                            Main.getInstance().getConfig().getInt("LeaveItemSlot"),
                                            sair
                                    );
                                }
                            }

                            if (kitsr != null) {
                                p.getInventory().setItem(
                                        Main.getInstance().getConfig().getInt("KitsItemSlot"),
                                        kitsr
                                );
                            }

                            if (kits != null) {
                                p.getInventory().setItem(
                                        Main.getInstance().getConfig().getInt("ShopItemSlot"),
                                        kits
                                );
                            }

                            if (st != null) {
                                p.getInventory().setItem(
                                        Main.getInstance().getConfig().getInt("1v1ItemSlot"),
                                        st
                                );
                            }
                        }

                        API.tirarEfeitos(p);
Habilidade.removeAbility(p);

                    },
                    null,
                    20L
            );
        }

    // Saiu da área de treino, inclusive por /spawn.
    if (wasInside && !isNowInside) {

        restoreTrainingEffects(player);

        // Mantém a regra de restauração somente para a área
        // de treino que atende ao limite de 50 blocos do spawn.
        if (isTrainingAreaNearSpawn(from)) {


            if (backup != null) {
                backup.restore(player);
                player.updateInventory();

                player.sendMessage(
                        "§eYou left the training area. "
                        + "Inventory restored."
                );
            }
        }
    }}
}

    private ItemStack createTrainingCrossbow() {
        ItemStack crossbow = new ItemStack(Material.CROSSBOW);
        ItemMeta itemMeta = crossbow.getItemMeta();

        if (!(itemMeta instanceof CrossbowMeta)) {
            return crossbow;
        }

        CrossbowMeta meta = (CrossbowMeta) itemMeta;

        meta.setDisplayName("§6Training Crossbow");

        meta.addEnchant(Enchantment.QUICK_CHARGE, 1, true);
        meta.addEnchant(Enchantment.MULTISHOT, 1, true);

        meta.setLore(Arrays.asList(
                "§7Quick Charge I",
                "§7Multishot I",
                "§aUnlimited ammunition"
        ));

        // Identifica esta besta como a besta especial do treinamento.
        meta.getPersistentDataContainer().set(
                trainingCrossbowKey,
                PersistentDataType.BYTE,
                (byte) 1
        );

        // Entrega a besta já carregada, sem colocar flechas no inventário.
        meta.setChargedProjectiles(
                Collections.singletonList(
                        new ItemStack(Material.ARROW)
                )
        );

        crossbow.setItemMeta(meta);

        return crossbow;
    }

    private boolean isTrainingCrossbow(ItemStack item) {
        if (item == null || item.getType() != Material.CROSSBOW) {
            return false;
        }

        ItemMeta meta = item.getItemMeta();

        return meta != null
                && meta.getPersistentDataContainer().has(
                        trainingCrossbowKey,
                        PersistentDataType.BYTE
                );
    }


private void reloadTrainingCrossbow(ItemStack item) {
    if (!isTrainingCrossbow(item)) {
        return;
    }

    if (!(item.getItemMeta() instanceof CrossbowMeta)) {
        return;
    }

    CrossbowMeta meta = (CrossbowMeta) item.getItemMeta();

    meta.setChargedProjectiles(
            Collections.singletonList(new ItemStack(Material.ARROW))
    );

    item.setItemMeta(meta);
}

    @EventHandler
    public void onTrainingQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();

        restoreTrainingEffects(player);
        // The scheduler retirement callback removes the UUID from the task set.
        InventoryBackup backup = trainingBackups.remove(uuid);
        if (backup != null) {
            backup.restore(player);
        }
        crossbowReloadPending.remove(player.getUniqueId());
    }


private void giveTrainingKit(Player player) {
    PlayerInventory inv = player.getInventory();

    // Limpa o inventário anterior.
    inv.clear();
    inv.setArmorContents(new ItemStack[4]);
    inv.setItemInOffHand(null);

    // ==========================================
    // HOTBAR: ARMAS PRINCIPAIS
    // ==========================================

    // Slot 1 - Espada de netherite
    ItemStack sword = new ItemStack(Material.NETHERITE_SWORD);
    sword.addUnsafeEnchantment(Enchantment.SHARPNESS, 5);
    sword.addUnsafeEnchantment(Enchantment.UNBREAKING, 3);
    inv.setItem(0, sword);

    // Slot 2 - Maça com Density V
    ItemStack mace = new ItemStack(Material.MACE);
    mace.addUnsafeEnchantment(Enchantment.DENSITY, 5);
    mace.addUnsafeEnchantment(Enchantment.UNBREAKING, 3);
    inv.setItem(1, mace);

    // Slot 3 - Lança de netherite
    ItemStack spear = new ItemStack(Material.NETHERITE_SPEAR);
    spear.addUnsafeEnchantment(Enchantment.SHARPNESS, 5);
    spear.addUnsafeEnchantment(Enchantment.LUNGE, 3);
    spear.addUnsafeEnchantment(Enchantment.UNBREAKING, 3);
    inv.setItem(2, spear);

    // Slot 4 - Tridente
    ItemStack trident = new ItemStack(Material.TRIDENT);
    trident.addUnsafeEnchantment(Enchantment.IMPALING, 5);
    trident.addUnsafeEnchantment(Enchantment.LOYALTY, 3);
    trident.addUnsafeEnchantment(Enchantment.CHANNELING, 1);
    trident.addUnsafeEnchantment(Enchantment.UNBREAKING, 3);
    inv.setItem(3, trident);

    // Slot 5 - Arco
    ItemStack bow = new ItemStack(Material.BOW);
    bow.addUnsafeEnchantment(Enchantment.POWER, 5);
    bow.addUnsafeEnchantment(Enchantment.PUNCH, 2);
    bow.addUnsafeEnchantment(Enchantment.FLAME, 1);
    bow.addUnsafeEnchantment(Enchantment.UNBREAKING, 3);
    inv.setItem(4, bow);

    // Slot 6 - Besta
    ItemStack crossbow = new ItemStack(Material.CROSSBOW);
    crossbow.addUnsafeEnchantment(Enchantment.QUICK_CHARGE, 3);
    crossbow.addUnsafeEnchantment(Enchantment.MULTISHOT, 1);
    crossbow.addUnsafeEnchantment(Enchantment.UNBREAKING, 3);
    inv.setItem(5, crossbow);

    // Slot 7 - Machado de netherite
    ItemStack axe = new ItemStack(Material.NETHERITE_AXE);
    axe.addUnsafeEnchantment(Enchantment.SHARPNESS, 5);
    axe.addUnsafeEnchantment(Enchantment.UNBREAKING, 3);
    inv.setItem(6, axe);

    // Slot 8 - Vara de pesca para combos PvP
    inv.setItem(7, new ItemStack(Material.FISHING_ROD));

    // Slot 9 - Pérolas do End
    inv.setItem(8, new ItemStack(Material.ENDER_PEARL, 16));

    // ==========================================
    // LANÇAS: TODOS OS MATERIAIS
    // ==========================================

    inv.setItem(14, new ItemStack(Material.DIAMOND_SPEAR));
    inv.setItem(15, new ItemStack(Material.NETHERITE_SPEAR));

    // ==========================================
    // ESPADAS: TODOS OS MATERIAIS
    // ==========================================


    // Slot 23 - Elytra para testar combate aéreo
    inv.setItem(22, new ItemStack(Material.ELYTRA));

    // ==========================================
    // OUTRAS ARMAS E VARIAÇÕES
    // ==========================================

    // Machado de cobre
    inv.setItem(23, new ItemStack(Material.COPPER_AXE));

    // Machado de diamante
    inv.setItem(24, new ItemStack(Material.DIAMOND_AXE));

    // Maça com Wind Burst III para testar outra estratégia
    ItemStack windMace = new ItemStack(Material.MACE);
    windMace.addUnsafeEnchantment(Enchantment.WIND_BURST, 3);
    windMace.addUnsafeEnchantment(Enchantment.UNBREAKING, 3);

    ItemMeta maceMeta = windMace.getItemMeta();
    if (maceMeta != null) {
        maceMeta.setDisplayName("§bMaça - Wind Burst III");
        windMace.setItemMeta(maceMeta);
    }

    inv.setItem(25, windMace);

    // ==========================================
    // MUNIÇÃO E ITENS DE COMBATE
    // ==========================================

    inv.setItem(26, new ItemStack(Material.ARROW, 64));
    inv.addItem(new ItemStack(Material.DIRT, 64));

    inv.addItem(new ItemStack(Material.STONE, 64));

    inv.addItem(new ItemStack(Material.COBBLESTONE, 64));

    inv.addItem(new ItemStack(Material.BLACK_WOOL, 64));
    inv.setItem(27, new ItemStack(Material.GOLDEN_APPLE, 64));

    inv.setItem(28,
            new ItemStack(Material.ENCHANTED_GOLDEN_APPLE, 3));

    // Crystal PvP
    inv.setItem(29, new ItemStack(Material.END_CRYSTAL, 64));
    inv.setItem(30, new ItemStack(Material.OBSIDIAN, 64));

    // Anchor PvP
    inv.setItem(31, new ItemStack(Material.RESPAWN_ANCHOR, 64));
    inv.setItem(32, new ItemStack(Material.CRYING_OBSIDIAN, 64));

    // Mobilidade
    inv.setItem(33, new ItemStack(Material.WIND_CHARGE, 32));

    // Pérolas adicionais
    inv.setItem(34, new ItemStack(Material.ENDER_PEARL, 16));

    // Totem
    inv.setItem(35, new ItemStack(Material.TOTEM_OF_UNDYING));

    // ==========================================
    // ESCUDO NA MÃO SECUNDÁRIA
    // ==========================================

    inv.setItemInOffHand(new ItemStack(Material.SHIELD));

    // ==========================================
    // ARMADURA COMPLETA DE NETHERITE
    // ==========================================

    ItemStack boots = new ItemStack(Material.NETHERITE_BOOTS);
    ItemStack leggings = new ItemStack(Material.NETHERITE_LEGGINGS);
    ItemStack chestplate = new ItemStack(Material.NETHERITE_CHESTPLATE);
    ItemStack helmet = new ItemStack(Material.NETHERITE_HELMET);

    ItemStack[] armor = {
        boots, leggings, chestplate, helmet
    };

    for (ItemStack piece : armor) {
        piece.addUnsafeEnchantment(Enchantment.PROTECTION, 4);
        piece.addUnsafeEnchantment(Enchantment.UNBREAKING, 3);
    }

    boots.addUnsafeEnchantment(Enchantment.FEATHER_FALLING, 4);

    inv.setArmorContents(armor);

    // Seleciona a espada ao receber o kit.
    inv.setHeldItemSlot(0);

    player.updateInventory();

    player.sendMessage(
            "§aYou received the training kit!"
    );
}

    public void restoreOnDisable() {
        if (!configured || snapshot.isEmpty()) {
            return;
        }

        final World world = Bukkit.getWorld(worldName);
        if (world == null) {
            Main.plugin.getLogger().warning("Could not restore the area during shutdown: world is not loaded.");
            return;
        }

        final List<String> blocks = new ArrayList<String>(snapshot);

        if (!isFoliaServer()) {
            for (String entry : blocks) {
                try {
                    applySavedBlock(world, entry);
                } catch (RuntimeException ex) {
                    Main.plugin.getLogger().warning("Error restoring block during shutdown: " + ex.getMessage());
                }
            }
            Main.plugin.getLogger().info("Training area restored during shutdown.");
            return;
        }

        final CountDownLatch latch = new CountDownLatch(blocks.size());
        for (final String entry : blocks) {
            try {
                final ParsedBlock parsed = parseBlock(entry);
                final Location location = new Location(world, parsed.x, parsed.y, parsed.z);
                Main.getFolia().getScheduler().runAtLocation(location, task -> {
                    try {
                        location.getBlock().setBlockData(Bukkit.createBlockData(parsed.blockData), false);
                    } catch (RuntimeException ex) {
                        Main.plugin.getLogger().warning("Error restoring block during shutdown: " + ex.getMessage());
                    } finally {
                        latch.countDown();
                    }
                });
            } catch (RuntimeException ex) {
                latch.countDown();
                Main.plugin.getLogger().warning("Could not schedule block restoration during shutdown: " + ex.getMessage());
            }
        }

        try {
            if (!latch.await(10L, TimeUnit.SECONDS)) {
                Main.plugin.getLogger().warning("Shutdown restoration did not finish within 10 seconds; Folia may have rejected some tasks.");
            } else {
                Main.plugin.getLogger().info("Training area restored during shutdown.");
            }
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
            Main.plugin.getLogger().warning("Waiting for shutdown restoration was interrupted.");
        }
    }

    private boolean isFoliaServer() {
        String serverName = Bukkit.getServer().getName();
        String className = Bukkit.getServer().getClass().getName();
        return (serverName != null && serverName.toLowerCase(Locale.ROOT).contains("folia"))
                || className.toLowerCase(Locale.ROOT).contains("threadedregions");
    }

    private void applySavedBlock(World world, String entry) {
        ParsedBlock parsed = parseBlock(entry);
        world.getBlockAt(parsed.x, parsed.y, parsed.z)
                .setBlockData(Bukkit.createBlockData(parsed.blockData), false);
    }

@EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
public void onLeaveTrainingArea(PlayerMoveEvent event) {
    Location from = event.getFrom();
    Location to = event.getTo();

    if (to == null) {
        return;
    }

    // Ignora movimentos apenas de cabeça.
    if (from.getBlockX() == to.getBlockX()
            && from.getBlockY() == to.getBlockY()
            && from.getBlockZ() == to.getBlockZ()) {
        return;
    }

    // Só aplica a jogadores que estão dentro da área.
    if (!isInside(from)) {
        return;
    }

    // Impede sair da área sem teleportar.
    if (!isInside(to)) {
        Location safe = from.clone();
        safe.setYaw(to.getYaw());
        safe.setPitch(to.getPitch());
        event.setTo(safe);
    }
}

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onBlockPlace(BlockPlaceEvent event) {
    	Player player = event.getPlayer();
    	if (NoBreakEvent.embuild.contains(player)) {
    		return;
    	}
        Location location = event.getBlockPlaced().getLocation();
        if (isRegionWorld(location) && !isInside(location)) {
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onBlockBreak(BlockBreakEvent event) {
    	Player player = event.getPlayer();
    	if (NoBreakEvent.embuild.contains(player)) {
    		return;
    	}
        Location location = event.getBlock().getLocation();
        if (!isRegionWorld(location)) {
            return;
        }
        if (!isInside(location)) {
            event.setCancelled(true);
            return;
        }
        event.setDropItems(false);
        event.setExpToDrop(0);
    }
}
