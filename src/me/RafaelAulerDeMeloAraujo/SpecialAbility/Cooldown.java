package me.RafaelAulerDeMeloAraujo.SpecialAbility;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import me.RafaelAulerDeMeloAraujo.main.Main;

public class Cooldown extends API
{
    public static HashMap<Player, Long> run;
   
    static {
        Cooldown.run = new HashMap<Player, Long>();
    }
    
    	/*     */   
    	/*     */   
    public static void add(final Player p, final int seconds) {
        final long cooldownLength = System.currentTimeMillis() + seconds * 1000L;

        Cooldown.run.remove(p);
        Cooldown.run.put(p, cooldownLength);

        Main.getFolia().getScheduler().runAtEntityLater(p, () -> {
            Cooldown.run.remove(p);
        }, seconds * 20L);
    }
    public static long cooldown(final Player p) {
        final long cooldownLength = Cooldown.run.get(p);
        final long left = (cooldownLength - System.currentTimeMillis()) / 1000L;
        return left;
    }
    
    public static boolean add(final Player p) {
        return Cooldown.run.containsKey(p);
    }
    
    public static void remove(final Player p) {
        Cooldown.run.remove(p);
    }
}
