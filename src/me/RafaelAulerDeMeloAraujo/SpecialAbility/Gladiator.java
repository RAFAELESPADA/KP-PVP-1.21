package me.RafaelAulerDeMeloAraujo.SpecialAbility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.RafaelAulerDeMeloAraujo.main.Main;

public class Gladiator implements Listener {
    public static ArrayList<String> gladgladiator;
    public boolean generateGLASS;
    public HashMap<String, Location> oldl;
    public static HashMap<String, String> lutando;
    public HashMap<UUID, List<Location>> blocks;
    public HashMap<Player, Location> localizacao;
    public HashMap<Location, Block> bloco;
    public HashMap<Integer, String[]> players;
    public HashMap<String, Integer> tasks;
    int nextID;
    public int id1;
    public int id2;


    static {
        Gladiator.gladgladiator = new ArrayList<String>();
        Gladiator.lutando = new HashMap<String, String>();
    }

    public Gladiator() {
        this.generateGLASS = true;
        this.oldl = new HashMap<String, Location>();
        this.blocks = new HashMap<>();
        this.localizacao = new HashMap<Player, Location>();
        this.bloco = new HashMap<Location, Block>();
        this.players = new HashMap<Integer, String[]>();
        this.tasks = new HashMap<String, Integer>();
        this.nextID = 0;
    }

    @EventHandler
    public void aoComando(final PlayerCommandPreprocessEvent e) {
        final Player p = e.getPlayer();
        if (Gladiator.lutando.containsKey(p.getName()) && e.getMessage().startsWith("/")) {
            e.setCancelled(true);
            p.sendMessage(String.valueOf(API.NomeServer) + (Main.messages.getString("NoGladCreateArena").replace("&", "§")));
        }
    }

    @EventHandler
    public void OnGladiatorKit(final PlayerInteractEntityEvent event) {
        final Player p = event.getPlayer();
        if (!(event.getRightClicked() instanceof Player)) {
            return;
        }
        final Player r = (Player) event.getRightClicked();
        if (p.getItemInHand().getType() != Material.IRON_BARS || !Habilidade.getAbility(p).equalsIgnoreCase("Gladiator")) {
            return;
        }
        if (Gladiator.lutando.containsKey(p.getName()) || Gladiator.lutando.containsKey(r.getName())) {
            event.setCancelled(true);
            p.sendMessage(String.valueOf(API.NomeServer) + (Main.messages.getString("AlreadyInGlad").replace("&", "§")));
            return;
        }
        if (!Habilidade.ContainsAbility(r) && Join.game.contains(r.getName())) {
        	p.sendMessage(String.valueOf(API.NomeServer) + (Main.messages.getString("Only-Use-Glad-On-Players-That-Have-Choosed-A-Kit").replace("&", "§")));
        	event.setCancelled(true);
        	return;
        }
        boolean isCitizensNPC = r.hasMetadata("NPC");
        if (isCitizensNPC) {
        	p.sendMessage("Please dont challenge our npcs to Gladiator!");
        	return;
        }
  	  if (API.isInRegion(r)) {
		  p.sendMessage(ChatColor.RED + "Leave the NO PVP Zone to use this kit!");
		  return;
	  }
        if (Cooldown.add(p))
        /*     */       {
        /*  67 */         event.setCancelled(true);
        /*  68 */         API.MensagemCooldown(p);
        /*  69 */         return;
        /*     */       }
        int y = 80 + new Random().nextInt(100);
        final Location loc = new Location(p.getWorld(), (double) p.getLocation().getBlockX(), p.getLocation().getBlockY() + y, (double) p.getLocation().getBlockZ());
        this.localizacao.put(p, loc);
        this.localizacao.put(r, loc);
        final Location loc2 = new Location(p.getWorld(), p.getLocation().getBlockX() - 5, p.getLocation().getBlockY() + y, p.getLocation().getBlockZ() - 5);
        final Location loc3 = new Location(p.getWorld(), p.getLocation().getBlockX() + 6, p.getLocation().getBlockY() + y, p.getLocation().getBlockZ() + 6);
        final Integer currentID = this.nextID;
        ++this.nextID;
        final ArrayList<String> list = new ArrayList<String>();
        list.add(p.getName());
        list.add(r.getName());
        this.players.put(currentID, list.toArray(new String[1]));
        this.oldl.put(p.getName(), p.getLocation());
        this.oldl.put(r.getName(), r.getLocation());
        if (this.generateGLASS) {
            final List<Location> cuboid = new ArrayList<Location>();
            cuboid.clear();
            for (int bX = -8; bX <= 8; ++bX) {
                for (int bZ = -8; bZ <= 8; ++bZ) {
                    for (int bY = -1; bY <= 4; ++bY) {
                        final Block b = loc.clone().add(bX, bY, bZ).getBlock();
                        if (!b.isEmpty()) {
                            event.setCancelled(true);
                            p.sendMessage(String.valueOf(API.NomeServer) + (Main.messages.getString("NoGladCreateArena").replace("&", "§")));
                            return;
                        }
                        if (bY == 4) {
                            cuboid.add(loc.clone().add(bX, bY, bZ));
                        } else if (bY == -1) {
                            cuboid.add(loc.clone().add(bX, bY, bZ));
                        } else if (bX == -8 || bZ == -8 || bX == 8 || bZ == 8) {
                            cuboid.add(loc.clone().add(bX, bY, bZ));
                        }
                    }
                }
            }
            blocks.put(p.getUniqueId(), new ArrayList<>());
            blocks.get(p.getUniqueId()).addAll(cuboid);
            blocks.put(r.getUniqueId(), new ArrayList<>());
            blocks.get(r.getUniqueId()).addAll(cuboid);
            for (final Location loc4 : cuboid) {
                loc4.getBlock().setType(Material.GLASS);
                this.bloco.put(loc4, loc4.getBlock());
            }
            loc2.setYaw(-75.0f);
            p.teleport(loc2);
            loc3.setYaw(75.0f);
            r.teleport(loc3);
            p.addPotionEffect(new PotionEffect(PotionEffectType.RESISTANCE, 110, 5));
            r.addPotionEffect(new PotionEffect(PotionEffectType.RESISTANCE, 110, 5));
            p.sendMessage(API.NomeServer + "§7You challenged "  + r.getName() + "! You have five seconds of invencibility!");
            p.sendMessage(API.NomeServer + "§7If dont have a winner in five minutes you will return to your previous location!");
            r.sendMessage(API.NomeServer + "§7You have been challenged by a gladiator! You have five seconds of invencibility!");
            r.sendMessage(API.NomeServer + "§7If dont have a winner in five minutes you will return to your previous location!");
            Gladiator.lutando.put(p.getName(), r.getName());
            Gladiator.lutando.put(r.getName(), p.getName());
            Gladiator.gladgladiator.add(p.getName());
            Gladiator.gladgladiator.add(r.getName());
            this.id2 = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable() {
                @Override
                public void run() {
                    if (Gladiator.lutando.containsKey(p.getName()) && Gladiator.lutando.get(p.getName()).equalsIgnoreCase(r.getName()) && Gladiator.lutando.containsKey(r.getName()) && Gladiator.lutando.get(r.getName()).equalsIgnoreCase(p.getName())) {
                        p.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 2000000, 3));
                        r.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 2000000, 3));
                    }
                }
            }, 2400L);
            this.id1 = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable() {
                @Override
                public void run() {
                    if (Gladiator.lutando.containsKey(p.getName()) && Gladiator.lutando.get(p.getName()).equalsIgnoreCase(r.getName()) && Gladiator.lutando.containsKey(r.getName()) && Gladiator.lutando.get(r.getName()).equalsIgnoreCase(p.getName())) {
                        removeGlad(p);
                        removeGlad(r);
                        p.sendMessage(String.valueOf(API.NomeServer) + (Main.messages.getString("GladNoWinner").replace("&", "§")));
                        r.sendMessage(String.valueOf(API.NomeServer) + (Main.messages.getString("GladNoWinner").replace("&", "§")));
                    }
                }
            }, 4800L);
        }
    }
    
    @EventHandler
    public void onTeleportENDER(PlayerTeleportEvent e) {
    	Player p = e.getPlayer();
    	if (e.getCause() == TeleportCause.ENDER_PEARL && Gladiator.lutando.containsKey(p.getName())) {
    		p.sendMessage(API.NomeServer + "§7You cannot use enderpearls on gladiator!");
    		e.setCancelled(true);
    	}   	
    }

    @EventHandler
    public void onPlayerInteractGlad(final PlayerInteractEvent e) {
        final Player p = e.getPlayer();
        if (p.getItemInHand().getType() == Material.IRON_BARS && Habilidade.getAbility(p).equalsIgnoreCase("Gladiator")) {
            e.setCancelled(true);
            p.updateInventory();
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlyaerInteract(final PlayerInteractEvent e) {
        if (e.getAction() == Action.LEFT_CLICK_BLOCK && e.getClickedBlock().getType() == Material.GLASS && e.getPlayer().getGameMode() != GameMode.CREATIVE && Gladiator.lutando.containsKey(e.getPlayer().getName())) {
            e.setCancelled(true);
            e.getClickedBlock().setType(Material.BEDROCK);
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable() {
                @Override
                public void run() {
                    if (Gladiator.lutando.containsKey(e.getPlayer().getName())) {
                        e.getClickedBlock().setType(Material.GLASS);
                    }
                }
            }, 30L);
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onBlockBreak(final BlockBreakEvent e) {
        if (e.getBlock().getType() == Material.GLASS && e.getPlayer().getGameMode() != GameMode.CREATIVE && Gladiator.lutando.containsKey(e.getPlayer().getName())) {
            e.setCancelled(true);
            e.getBlock().setType(Material.BEDROCK);
            Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable() {
                @Override
                public void run() {
                    if (e.getPlayer().getGameMode() != GameMode.CREATIVE && Gladiator.lutando.containsKey(e.getPlayer().getName())) {
                        e.getBlock().setType(Material.GLASS);
                    }
                }
            }, 30L);
        }
    }
    
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerLeftt(final PlayerQuitEvent e) {
        final Player p = e.getPlayer();
        removeGlad(p);
        if (Gladiator.lutando.containsKey(e.getPlayer().getName())) {
        	p.setHealth(0.0);
        	final Player t = Bukkit.getServer().getPlayer((String)Gladiator.lutando.get(p.getName()));
        	t.sendMessage("§7Your oponnent disconnected");
        	if (Main.kits.getBoolean("GladiatorHasCooldown")) {
        		if (Habilidade.getAbility(t) == "Gladiator") {
            	Cooldown.add(t, Main.kits.getInt("GladiatorCooldown"));
        	}
        	}
        }
        }

        /**if (Gladiator.lutando.containsKey(p.getName())) {
         final Player t = Bukkit.getServer().getPlayer((String)Gladiator.lutando.get(p.getName()));
         Gladiator.lutando.remove(t.getName());
         Gladiator.lutando.remove(p.getName());
         Gladiator.gladgladiator.remove(p.getName());
         Gladiator.gladgladiator.remove(t.getName());
         final Location old = this.oldl.get(t.getName());
         t.teleport(old);
         t.sendMessage(String.valueOf(API.NomeServer) + "§cO jogador " + p.getName() + " deslogou");
         Bukkit.getScheduler().cancelTask(this.id1);
         Bukkit.getScheduler().cancelTask(this.id2);
         t.removePotionEffect(PotionEffectType.WITHER);
         final Location loc = this.localizacao.get(p);
         final List<Location> cuboid = new ArrayList<Location>();
         for (int bX = -8; bX <= 8; ++bX) {
         for (int bZ = -8; bZ <= 8; ++bZ) {
         for (int bY = -1; bY <= 4; ++bY) {
         if (bY == 4) {
         cuboid.add(loc.clone().add((double)bX, (double)bY, (double)bZ));
         }
         else if (bY == -1) {
         cuboid.add(loc.clone().add((double)bX, (double)bY, (double)bZ));
         }
         else if (bX == -8 || bZ == -8 || bX == 8 || bZ == 8) {
         cuboid.add(loc.clone().add((double)bX, (double)bY, (double)bZ));
         }
         }
         }
         }
         for (final Location loc2 : cuboid) {
         loc2.getBlock().setType(Material.AIR);
         this.bloco.get(loc2).setType(Material.AIR);
         }
         for (final Location loc2 : cuboid) {
         loc2.getBlock().setType(Material.AIR);
         this.bloco.get(loc2).setType(Material.AIR);
         }
         for (final Location loc2 : cuboid) {
         loc2.getBlock().setType(Material.AIR);
         this.bloco.get(loc2).setType(Material.AIR);
         }
         }*/
    

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onDeathGladiator(final PlayerDeathEvent e) {
        removeGlad(e.getEntity());
    }

    public void removeGlad(Player player) {
        if (blocks.containsKey(player.getUniqueId())) {
            for (Location location : blocks.get(player.getUniqueId())) {
                location.getBlock().setType(Material.AIR);
            }
        }
        gladgladiator.remove(player.getName());
        blocks.remove(player.getUniqueId());
        localizacao.remove(player);
        player.removePotionEffect(PotionEffectType.WITHER);
        if (Gladiator.lutando.containsKey(player.getName())) {
            final Player t = Bukkit.getServer().getPlayer((String)Gladiator.lutando.get(player.getName()));
            if (oldl.containsKey(t.getName())) {
                t.teleport(oldl.get(t.getName()));
            }
            if (oldl.containsKey(player.getName())) {
                player.teleport(oldl.get(player.getName()));
            }
            if (Main.kits.getBoolean("GladiatorHasCooldown")) {
            	Cooldown.add(t, Main.kits.getInt("GladiatorCooldown"));
            	}
            t.removePotionEffect(PotionEffectType.WITHER);
            Gladiator.lutando.remove(t.getName());
            Gladiator.lutando.remove(player.getName());
            gladgladiator.remove(t.getName());
            blocks.remove(t.getUniqueId());
            localizacao.remove(t);
            oldl.remove(t.getName());
        }
        oldl.remove(player.getName());
        
        /**
         *
         *         final Player p = e.getEntity();
         *         if (Gladiator.lutando.containsKey(p.getName())) {
         *             final Player k = Bukkit.getServer().getPlayer((String)Gladiator.lutando.get(p.getName()));
         *             final Location old = this.oldl.get(p.getName());
         *             k.teleport(old);
         *             k.sendMessage(String.valueOf(API.NomeServer) + "§7Voce ganhou a batalha contra " + p.getName());
         *             Bukkit.getScheduler().cancelTask(this.id1);
         *             Bukkit.getScheduler().cancelTask(this.id2);
         *             k.removePotionEffect(PotionEffectType.WITHER);
         *             k.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 100, 10));
         *             Gladiator.lutando.remove(k.getName());
         *             Gladiator.lutando.remove(p.getName());
         *             Gladiator.gladgladiator.remove(p.getName());
         *             Gladiator.gladgladiator.remove(k.getName());
         *             final Location loc = this.localizacao.get(p);
         *             final List<Location> cuboid = new ArrayList<Location>();
         *             cuboid.clear();
         *             for (int bX = -8; bX <= 8; ++bX) {
         *                 for (int bZ = -8; bZ <= 8; ++bZ) {
         *                     for (int bY = -1; bY <= 4; ++bY) {
         *                         if (bY == 4) {
         *                             cuboid.add(loc.clone().add((double)bX, (double)bY, (double)bZ));
         *                         }
         *                         else if (bY == -1) {
         *                             cuboid.add(loc.clone().add((double)bX, (double)bY, (double)bZ));
         *                         }
         *                         else if (bX == -8 || bZ == -8 || bX == 8 || bZ == 8) {
         *                             cuboid.add(loc.clone().add((double)bX, (double)bY, (double)bZ));
         *                         }
         *                     }
         *                 }
         *             }
         *             for (final Location loc2 : cuboid) {
         *                 loc2.getBlock().setType(Material.AIR);
         *                 if (this.bloco.containsKey(loc2)) {
         *                     this.bloco.get(loc2).setType(Material.AIR);
         *                 }
         *             }
         *         }
         *
         */
    }

}
