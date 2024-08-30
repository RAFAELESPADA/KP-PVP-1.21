package me.RafaelAulerDeMeloAraujo.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import me.RafaelAulerDeMeloAraujo.SpecialAbility.API;





public class ClickTest implements Listener {
	 public static ArrayList<String> emclicktest;
	    public static ArrayList<String> fazendoclicktest;
	    public static Map<String, Integer> click = new HashMap();
	    static {
	        ClickTest.emclicktest = new ArrayList<String>();
	        ClickTest.fazendoclicktest = new ArrayList<String>();
	    }

public static void StartClick(Player p) {
	 if (ClickTest.fazendoclicktest.contains(p.getName())) {
         p.sendMessage(String.valueOf(API.NomeServer) + Main.messages.getString("AlreadyonClickTest").replace("&", "§"));
         return;
	 }
         ClickTest.fazendoclicktest.add(p.getName());
         Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, (Runnable)new Runnable() {
             @Override
             public void run() {
            	 p.sendMessage(String.valueOf(API.NomeServer) + (Main.messages.getString("ClickTestStarting").replace("&", "§")).replace("%time%", "5"));
                 p.playSound(p.getLocation(), Sound.valueOf(Main.getInstace().getConfig().getString("Sound.ClickTest")), 5.0F, 5.0F);
                 p.setLevel(0);
             }
         }, 0L);
         Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, (Runnable)new Runnable() {
             @Override
             public void run() {
            	 p.sendMessage(String.valueOf(API.NomeServer) + (Main.messages.getString("ClickTestStarting").replace("&", "§")).replace("%time%", "4"));
                 p.playSound(p.getLocation(), Sound.valueOf(Main.getInstace().getConfig().getString("Sound.ClickTest")), 5.0F, 5.0F);
             }
         }, 20L);
         Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, (Runnable)new Runnable() {
             @Override
             public void run() {
            	 p.sendMessage(String.valueOf(API.NomeServer) + (Main.messages.getString("ClickTestStarting").replace("&", "§")).replace("%time%", "3"));
                 p.playSound(p.getLocation(), Sound.valueOf(Main.getInstace().getConfig().getString("Sound.ClickTest")), 5.0F, 5.0F);
             }
         }, 40L);
         Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, (Runnable)new Runnable() {
             @Override
             public void run() {
            	 p.sendMessage(String.valueOf(API.NomeServer) + (Main.messages.getString("ClickTestStarting").replace("&", "§")).replace("%time%", "2"));
                 p.playSound(p.getLocation(), Sound.valueOf(Main.getInstace().getConfig().getString("Sound.ClickTest")), 5.0F, 5.0F);
             }
         }, 60L);
         Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, (Runnable)new Runnable() {
             @Override
             public void run() {
                 p.sendMessage(String.valueOf(API.NomeServer) + (Main.messages.getString("ClickTestStarting").replace("&", "§")).replace("%time%", "1"));
                 p.playSound(p.getLocation(), Sound.valueOf(Main.getInstace().getConfig().getString("Sound.ClickTest")), 5.0F, 5.0F);
             }
         }, 80L);
         Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, (Runnable)new Runnable() {
             @Override
             public void run() {
                 p.sendMessage(String.valueOf(API.NomeServer) + Main.messages.getString("ClickTestStarted").replace("&", "§"));
                 p.playSound(p.getLocation(), Sound.valueOf(Main.getInstace().getConfig().getString("Sound.ClickTest")), 5.0F, 5.0F);
                 ClickTest.emclicktest.add(p.getName());
             }
         }, 100L);
         Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, (Runnable)new Runnable() {
             @Override
             public void run() {
                 p.sendMessage(String.valueOf(API.NomeServer) + Main.messages.getString("ClickTestFinished").replace("&", "§"));
                 p.sendMessage(String.valueOf(API.NomeServer) + (Main.messages.getString("ClickTestResult").replace("&", "§").replace("%clicks%", Integer.toString(p.getLevel() / 5))));
                 ClickTest.emclicktest.remove(p.getName());
                 ClickTest.fazendoclicktest.remove(p.getName());
                 p.setLevel(0);
             }
         }, 200L);
     }
@org.bukkit.event.EventHandler
public void click(final PlayerInteractEvent e) {
    final Player p = e.getPlayer();
    
    if (ClickTest.emclicktest.contains(p.getName()) && e.getAction() == Action.LEFT_CLICK_AIR) {
    	p.setLevel(p.getLevel() + 1);
    }
}
}

