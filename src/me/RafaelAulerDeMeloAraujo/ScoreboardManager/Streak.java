/*     */ package me.RafaelAulerDeMeloAraujo.ScoreboardManager;
import java.io.IOException;
import java.util.Random;

/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.EventHandler;
/*     */ import org.bukkit.event.Listener;
/*     */ import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.meta.FireworkMeta;

/*     */ import me.RafaelAulerDeMeloAraujo.Coins.Coins;
import me.RafaelAulerDeMeloAraujo.Coins.XP;
import me.RafaelAulerDeMeloAraujo.SpecialAbility.API;
/*     */ import me.RafaelAulerDeMeloAraujo.SpecialAbility.Join;
import me.RafaelAulerDeMeloAraujo.SpecialAbility.PlayerLevelUPEvent;
/*     */ import me.RafaelAulerDeMeloAraujo.main.Main;
import net.wavemc.core.bukkit.WaveBukkit;
import net.wavemc.core.bukkit.account.WavePlayer;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Streak
/*     */   implements Listener
/*     */ {
/*     */   private Main main;
/*     */   static Main plugin;
/*     */   
/*     */   public Streak()
/*     */   {
/*  31 */     this.main = this.main;
/*  32 */     plugin = this.main;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  40 */   
/*     */   
/*     */   public void onEnable() {}
/*     */   
/*     */   @EventHandler
/*  45 */   public void playerdeath(PlayerDeathEvent ev) throws IOException { 
	if (ev.getEntity().getKiller() == null) {
		return;
	}
Player p = ev.getEntity();
Player k = p.getKiller();
boolean isCitizensNPC = p.hasMetadata("NPC");

double killstreak = XP.getXP(k);
/*  46 */     if ((p.getKiller() instanceof Player))
/*     */     {
/*     */ 
/*  49 */     
if (!Join.game.contains(k.getName())) {
	return;
}

WavePlayer Sun8oxData = WaveBukkit.getInstance().getPlayerManager().getPlayer(k.getName());
	
if (isCitizensNPC && Main.getInstance().getConfig().getBoolean("BotsKillsAllowed")) {
	Sun8oxData.getPvp().addKills(1);
	if (killstreak % Main.customization.getInt("XP-Required-To-LevelUP") == 0) {

		Streak.sendToGame(String.valueOf(API.NomeServer + Main.messages.getString("LevelUP").replaceAll("%player%", k.getName()).replaceAll("%level%", Integer.toString(Level.getLevel(k)))).replaceAll("&", "§"));
		Streak.sendToGame(String.valueOf(API.NomeServer + Main.messages.getString("LevelUP").replaceAll("%player%", k.getName()).replaceAll("%level%", Integer.toString(Level.getLevel(k)))).replaceAll("&", "§"));

		Streak.sendToGame(String.valueOf(API.NomeServer + Main.messages.getString("LevelUP").replaceAll("%player%", k.getName()).replaceAll("%level%", Integer.toString(Level.getLevel(k)))).replaceAll("&", "§"));
		Streak.sendToGame(String.valueOf(API.NomeServer + Main.messages.getString("LevelUP").replaceAll("%player%", k.getName()).replaceAll("%level%", Integer.toString(Level.getLevel(k)))).replaceAll("&", "§"));

	}
	k.sendMessage(String.valueOf(API.NomeServer + Main.getInstace().getConfig().getString("Kill.Tell").replaceAll("%player%", p.getName())));
	XP.addXP(k, Main.customization.getInt("XPEarned-OnKill"));
	Coins.addCoins(k, Main.customization.getInt("Earned-Coins-Per-Kill"));               

	k.sendMessage("§a+" + Main.customization.getInt("XPEarned-OnKill") + "XP");
	k.sendMessage("§a+" + Main.customization.getInt("Earned-Coins-Per-Kill")  + "COINS");

    WaveBukkit.getInstance().getPlayerManager().getController().save(Sun8oxData);
}

  else if (!isCitizensNPC) {
		  WavePlayer Sun8oxData2 = WaveBukkit.getInstance().getPlayerManager().getPlayer(p.getName());
			

/*  52 */       p.sendMessage(API.NomeServer + "" + (Main.messages.getString("StreakDestroyed").replace("&", "§").replace("%player%", k.getName()))); 

/*  53 */       addtokillstreak(k);
API.tirarEfeitos(p);        
Sun8oxData.getPvp().addKills(1);
Sun8oxData2.getPvp().addDeaths(1);

Sun8oxData2.getPvp().setKillstreak(0);
if (Main.getInstance().getConfig().getBoolean("ThrowFireworksOnKill")) {
	throwRandomFirework(p);
}
  if (Main.getInstance().getConfig().getBoolean("Commands-ON-KILL-Enabled")) {
	  for (String commands : Main.getInstance().getConfig().getStringList("Commands-Executed-On-Kill")) {
	  Bukkit.dispatchCommand(Bukkit.getConsoleSender(), commands.replace("%killer%", k.getName()).replace("%killed%", p.getName()));
  }
  }
  
if (killstreak % Main.customization.getInt("XP-Required-To-LevelUP") == 0 && Level.getLevel(k) != 0) {

	Streak.sendToGame(String.valueOf(API.NomeServer + Main.messages.getString("LevelUP").replaceAll("%player%", k.getName()).replaceAll("%level%", Integer.toString(Level.getLevel(k)))).replaceAll("&", "§"));
	PlayerLevelUPEvent helixPlayerDeathEvent = new me.RafaelAulerDeMeloAraujo.SpecialAbility.PlayerLevelUPEvent(
			k
	);
	Bukkit.getPluginManager().callEvent(helixPlayerDeathEvent);
}
/*  64 */       int kills2 = Sun8oxData2.getPvp().getKillstreak();
if (kills2 >= 3) {
	broadcast(API.NomeServer + "" + Main.messages.getString("KillStreakLostBroadcast").replace("&", "§").replace("%killstreak%", String.valueOf(kills2)).replace("%player%", p.getName()).replace("%killer%", k.getName()) , p.getWorld());
	Streak.sendToGame(String.valueOf(API.NomeServer + Main.messages.getString("LevelUP").replaceAll("%player%", k.getName()).replaceAll("%level%", Integer.toString(Level.getLevel(k)))).replaceAll("&", "§"));

	Streak.sendToGame(String.valueOf(API.NomeServer + Main.messages.getString("LevelUP").replaceAll("%player%", k.getName()).replaceAll("%level%", Integer.toString(Level.getLevel(k)))).replaceAll("&", "§"));
}

p.sendMessage(String.valueOf(API.NomeServer + Main.getInstace().getConfig().getString("Death.Tell").replaceAll("%player%", k.getName())));
k.sendMessage(String.valueOf(API.NomeServer + Main.getInstace().getConfig().getString("Kill.Tell").replaceAll("%player%", p.getName())));
XP.addXP(k, Main.customization.getInt("XPEarned-OnKill"));
XP.removeXP(p, Main.customization.getInt("XPLost-OnDeath"));
Coins.addCoins(k, Main.customization.getInt("Earned-Coins-Per-Kill"));               
Coins.removeCoins(p, Main.customization.getInt("Lost-Coins-Per-Death"));
p.sendMessage("§cYou died to " + k.getName());
k.sendMessage("§a+" + Main.customization.getInt("XPEarned-OnKill") + "XP");
k.sendMessage("§a+" + Main.customization.getInt("Earned-Coins-Per-Kill")  + "COINS");
WaveBukkit.getInstance().getPlayerManager().getController().save(Sun8oxData);
WaveBukkit.getInstance().getPlayerManager().getController().save(Sun8oxData2);
Bukkit.getConsoleSender().sendMessage("§e" + p.getName() + " (" +  ev.getEntityType() + ")" + " has been killed by " + k.getName() + " (" +  ev.getEntity().getKiller().getType() + ")" + " on kitpvp");     
   
  }
}
}
public static void throwRandomFirework(Player p) {
    Firework fw = (Firework) p.getWorld().spawnEntity(p.getLocation(), EntityType.FIREWORK_ROCKET);
    FireworkMeta fwm = fw.getFireworkMeta();

    //Our random generator
    Random r = new Random();

    //Get the type
    int rt = r.nextInt(5) + 1;
    FireworkEffect.Type type = FireworkEffect.Type.BALL;
    if (rt == 2) type = FireworkEffect.Type.BALL_LARGE;
    if (rt == 3) type = FireworkEffect.Type.BURST;
    if (rt == 4) type = FireworkEffect.Type.CREEPER;
    if (rt == 5) type = FireworkEffect.Type.STAR;

    //Get our random colours
    int r1i = r.nextInt(17) + 1;
    int r2i = r.nextInt(17) + 1;
    Color c1 = Color.fromRGB(r1i);
    Color c2 = Color.fromRGB(r2i);
    FireworkEffect effect = FireworkEffect.builder().flicker(r.nextBoolean()).withColor(c1).withFade(c2).with(type).trail(r.nextBoolean()).build();

    //Then apply the effect to the meta
    fwm.addEffect(effect);

    //Generate some random power and set it


    //Create our effect with this   int rp = r.nextInt(2) + 1;
    int rp = r.nextInt(2) + 1;
    fwm.setPower(rp);

    //Then apply this to our rocket
    fw.setFireworkMeta(fwm);
    
}
/*     */   
/*     
*/  

/*     */ 
/*     */   public static void addtokillstreak(Player killer)
/*     */   {
/*  61 */     String name = killer.getName();
/*  62 */     if (Join.game.contains(name))
/*     */     {


	  WavePlayer Sun8oxData = WaveBukkit.getInstance().getPlayerManager().getPlayer(killer.getName());
		       int kills = Sun8oxData.getPvp().getKillstreak();
/*     */      Sun8oxData.getPvp().addKillstreak(1);
if (kills != 0) {
killer.sendMessage(API.NomeServer + "" + ChatColor.GREEN + "You are on " + ChatColor.RED + Integer.toString(kills) + ChatColor.GREEN + " Killstreak.");
}
/*  69 */       if (kills % 3 == 0 && kills != 0) {
	broadcast(API.NomeServer + "" + Main.messages.getString("KillStreakBroadcast").replace("&", "§").replace("%killstreak%", String.valueOf(kills)).replace("%player%", name) , killer.getWorld());
        killer.sendMessage(API.NomeServer + "" + ChatColor.GOLD + "You have been awarded " + Main.customization.getDouble("KS-3") + ( kills * 3 ) + " Coins!");
/*  74 */         Coins.addCoins(killer, Main.customization.getInt("KS-3") + ( kills * 3 ));
/*     */       }
/*  76 */      
/*     */     
}
/*     */   }
/*     */ 

  public static void broadcast(String text, World w){
    for(Player p: w.getPlayers()){
        p.sendMessage(text);
    }
  }
public static void sendToGame(String message) {
    for(String player : Join.game) {
        if(player != null) {
            Player p = Bukkit.getPlayer(player);
            p.sendMessage(message);
        }
    }
}
}
/* Location:              D:\Desktop\video\Minhas Coisas do Desktop\KP-PVPvB12 (1).jar!\me\RafaelAulerDeMeloAraujo\ScoreboardManager\Streak.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */
