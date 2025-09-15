package me.RafaelAulerDeMeloAraujo.SpecialAbility;
import java.util.*;
import org.bukkit.entity.*;

import me.RafaelAulerDeMeloAraujo.main.Main;

import org.bukkit.*;

public class CooldownX1
{
    public static HashMap<Player, Long> run;
   
    static {
        CooldownX1.run = new HashMap<Player, Long>();
    }
    
    	/*     */   
    	/*     */   
    public static void add(final Player p, final int seconds) {
        final long cooldownLength = System.currentTimeMillis() + seconds * 1000;
        CooldownX1.run.remove(p);
        CooldownX1.run.put(p, cooldownLength);
        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, (Runnable)new Runnable() {
            @Override
            public void run() {
                CooldownX1.run.remove(p);
            }
        }, (long)(seconds * 20));
    }
    
    public static long cooldown(final Player p) {
        final long cooldownLength = CooldownX1.run.get(p);
        final long left = (cooldownLength - System.currentTimeMillis()) / 1000L;
        return left;
    }
    
    public static boolean add(final Player p) {
        return CooldownX1.run.containsKey(p);
    }
    
    public static void remove(final Player p) {
        CooldownX1.run.remove(p);
    }
}
