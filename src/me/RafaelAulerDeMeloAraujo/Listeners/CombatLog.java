package me.RafaelAulerDeMeloAraujo.Listeners;





import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;

import me.RafaelAulerDeMeloAraujo.ScoreboardManager.Streak;
import me.RafaelAulerDeMeloAraujo.SpecialAbility.Habilidade;
import me.RafaelAulerDeMeloAraujo.SpecialAbility.Join;
import me.RafaelAulerDeMeloAraujo.SpecialAbility.NewKitMenu;
import me.RafaelAulerDeMeloAraujo.main.Main;

public class CombatLog implements Listener
{
    public static HashMap<Player, Player> emcombate;
    
    static {
        CombatLog.emcombate = new HashMap<Player, Player>();
    }
    public static boolean emCombate(final Player p) {
        return CombatLog.emcombate.containsKey(p);
    }
    
    public static String statuscombat(final Player p) {
        String nome = "";
        if (emCombate(p)) {
            nome = "§aYes";
        }
        else if (!emCombate(p)) {
            nome = "§cNo §4";
        }
        return nome;
    }
    
    @EventHandler(ignoreCancelled = true)
    public void aocombatlog(final EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Player && e.getEntity() instanceof Player) {
            final Player p = (Player)e.getEntity();
            final Player hitter = (Player)e.getDamager();
            if (Habilidade.ContainsAbility(p) && !CombatLog.emcombate.containsKey(p) && !CombatLog.emcombate.containsKey(hitter) && Join.game.contains(p.getName())) {
            	if (e.isCancelled()) {
            		return;
            	}
                CombatLog.emcombate.put(p, hitter);
                CombatLog.emcombate.put(hitter, p);
                hitter.sendMessage("§7You are now in combat with §c" + p.getDisplayName());
                p.sendMessage("§7You are now in combat with §c" + hitter.getDisplayName());
                Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)Main.instance, (Runnable)new Runnable() {
                    @Override
                    public void run() {
                        CombatLog.emcombate.remove(p);
                        CombatLog.emcombate.remove(hitter);
                        hitter.sendMessage("§aYou are no longer in combat");
                        p.sendMessage("§aYou are no longer in combat");
                    }
                }, 20L * Main.getInstance().getConfig().getInt("CombatLogTimer"));
            }
        }
    }
    
    
    @EventHandler
    public void aosair(PlayerQuitEvent e) {
        final Player p = e.getPlayer();
        if (CombatLog.emcombate.containsKey(p)) {
            p.setHealth(0.0);
            p.teleport(p.getWorld().getSpawnLocation());
            CombatLog.emcombate.remove(p);
            for (String p3 : Join.game) {
				if (p3 != null) {
					Player p2 = Bukkit.getPlayer(p3);				
					NewKitMenu.playSound(p2, Main.getInstace().getConfig().getString("Sound.CommandSounds"), 1.0F, 1.0F);
				/*    */   }
			}
            Streak.sendToGame("§e[KitPvP] §7 " + p.getName() + "§c logget out in combat and is killed!");
        }
        }
        
    
    @EventHandler
    public void aomorrer(final PlayerDeathEvent e) {
        final Player p = e.getEntity();
        if (CombatLog.emcombate.containsKey(p)) {
            CombatLog.emcombate.remove(p);
        }
    }
    

    }
