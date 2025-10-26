package me.RafaelAulerDeMeloAraujo.main;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import me.RafaelAulerDeMeloAraujo.SpecialAbility.API;
import me.RafaelAulerDeMeloAraujo.SpecialAbility.Cooldown;
import me.RafaelAulerDeMeloAraujo.SpecialAbility.Deshfire;
import me.RafaelAulerDeMeloAraujo.SpecialAbility.Habilidade;
import me.RafaelAulerDeMeloAraujo.SpecialAbility.Join;
import me.RafaelAulerDeMeloAraujo.SpecialAbility.NewKitMenu;
import me.RafaelAulerDeMeloAraujo.TitleAPI.TitleAPI;
import me.RafaelAulerDeMeloAraujo.Warps.SettingsManager;
import me.RafaelAulerDeMeloAraujo.X1.X1;


public class Respawn implements Listener {
	

	SettingsManager settings = SettingsManager.getInstance();
	
	@EventHandler
	public void onDeath(PlayerDeathEvent e) {
		
		if (Join.game.contains((e.getEntity().getName()))) {
			
			Player victim = e.getEntity();
			e.setDeathMessage("");
			
			victim.getWorld().playEffect(victim.getLocation().add(0.0D, 1.0D, 0.0D), Effect.STEP_SOUND, 152);
			respawnPlayer(victim);
			
		}
	
	}

	private void respawnPlayer(Player p) {
			
			Location deathLocation = p.getLocation();
			Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), () -> p.spigot().respawn(), 1);
			
			p.setGameMode(GameMode.SPECTATOR);
			p.teleport(deathLocation);
			

			if (X1.inx1.contains(p)) {
				new BukkitRunnable() {
					
					int time = 1;
					
					@Override
					public void run() {
						
						
						
						if (time != 0) {

							TitleAPI.sendTitle(p, Integer.valueOf(20), Integer.valueOf(40), Integer.valueOf(20), Main.getInstace().getConfig().getString("Title.DeathTitle"), Main.getInstace().getConfig().getString("Title.DeathSubTitle"));
							NewKitMenu.playSound(p, Main.getInstace().getConfig().getString("Sound.Respawning"), 1.0F, 1.0F);
							time--;
							
						} else {
							/* 191 */       p.getInventory().setArmorContents(null);
							/*     */       
							 GiveKitUnlocker.GiveUnlockers(p);
							/* 193 */       p.updateInventory();
							/* 194 */       Habilidade.removeAbility(p);
							/* 195 */       Deshfire.Armadura.remove(p.getName());
							/* 196 */       Deshfire.Armadura2.remove(p.getName());
							/* 197 */       Deshfire.cooldownm.remove(p);
							/*     */       p.setGameMode(GameMode.SURVIVAL);
							/* 199 */       Cooldown.remove(p);
							
							  X1.entrar1v1(p);
							  
							  /*     */     
								Bukkit.getConsoleSender().sendMessage("[KP-PVP] " + p.getName() + " Respawned on 1V1.");
							p.sendMessage(ChatColor.YELLOW + "You respawned");
							p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 30, 0));
							NewKitMenu.playSound(p, Main.getInstace().getConfig().getString("Sound.RespawnSucess"), 1.0F, 1.0F);
							
							cancel();
							
						}
					}}	
				.runTaskTimer(Main.getInstance(), 0L, 20L);
			}
				else if (Habilidade.getAbility(p) == "FPS") {
					new BukkitRunnable() {
						
						int time = 1;
						
						@Override
						public void run() {
							
							
							
							if (time != 0) {

								TitleAPI.sendTitle(p, Integer.valueOf(20), Integer.valueOf(40), Integer.valueOf(20), Main.getInstace().getConfig().getString("Title.DeathTitle"), Main.getInstace().getConfig().getString("Title.DeathSubTitle"));
								NewKitMenu.playSound(p, Main.getInstace().getConfig().getString("Sound.Respawning"), 1.0F, 1.0F);time--;
								
							} else {
						Player player = p;
						
		           	 World w = Bukkit.getServer().getWorld(settings.getData().getString("warps."  + "fps" + ".world"));
		           	double x = settings.getData().getDouble("warps." + "fps" + ".x");
		               double y = settings.getData().getDouble("warps."  + "fps" + ".y");
		               double z = settings.getData().getDouble("warps."  + "fps" + ".z");
		               p.teleport(new Location(w, x, y, z));
		           	player.setGameMode(GameMode.ADVENTURE);
		        	player.getInventory().clear();
		        	player.getInventory().setArmorContents(null);
		        	player.setAllowFlight(false);
		        	Cooldown.remove(player);
		        	  /* 280 */       Deshfire.Armadura.remove(player.getName());
		        	  /* 281 */       Deshfire.Armadura2.remove(player.getName());
		        	  /* 282 */       Deshfire.cooldownm.remove(player);
		        	player.setFlying(false);
		        	player.getInventory().setHeldItemSlot(0);
		        	player.getActivePotionEffects().forEach(potion -> player.removePotionEffect(potion.getType()));	
		               ItemStack sopas = new ItemStack(Material.BOWL, 64);
		               ItemMeta ksopas = sopas.getItemMeta();
		               ksopas.setDisplayName("§eBowl");
		               sopas.setItemMeta(ksopas);

		             	Habilidade.setAbility(p, "FPS");
		               ItemStack cogur = new ItemStack(Material.RED_MUSHROOM, 64);
		               ItemMeta kcogur = cogur.getItemMeta();
		               kcogur.setDisplayName("§3--> §cRed §3<--");
		               cogur.setItemMeta(kcogur);
		               ItemStack cogum = new ItemStack(Material.BROWN_MUSHROOM, 64);
		               ItemMeta kcogum = cogum.getItemMeta();
		               kcogum.setDisplayName("§3--> §8Brown §3<--");
		               cogum.setItemMeta(kcogum);
		              p.getInventory().setItem(14, cogum);
		              p.getInventory().setItem(15, cogur);
		              p.getInventory().setItem(13, sopas);
		               API.sopa(p);
		               ItemStack espada = new ItemStack(Material.STONE_SWORD);
		               ItemMeta kespada = espada.getItemMeta();
		               kespada.setDisplayName("§eSword");
		               espada.setItemMeta(kespada);
		               p.getInventory().setItem(0, espada);
		                
		               p.sendMessage(ChatColor.GREEN + "Teleported to FPS!");	
		               cancel();
						
							}
						}}	
					.runTaskTimer(Main.getInstance(), 0L, 20L);
				}
			else {
				
				new BukkitRunnable() {
					
					int time = 2;
					
					@Override
					public void run() {
						
						
						
						if (time != 0) {

							TitleAPI.sendTitle(p, Integer.valueOf(20), Integer.valueOf(40), Integer.valueOf(20), Main.getInstace().getConfig().getString("Title.DeathTitle"), Main.getInstace().getConfig().getString("Title.DeathSubTitle"));
							NewKitMenu.playSound(p, Main.getInstace().getConfig().getString("Sound.Respawning"), 1.0F, 1.0F);time--;
							
						} else {
							

							
							
							/*     */      World w = Bukkit.getServer().getWorld(Main.plugin.getConfig().getString("SpawnD.World"));
							/* 153 */      double x = Main.plugin.getConfig().getDouble("SpawnD.X");
							/* 154 */      double y = Main.plugin.getConfig().getDouble("SpawnD.Y");
							/* 155 */      double z = Main.plugin.getConfig().getDouble("SpawnD.Z");
							/* 156 */      Location lobby = new Location(w, x, y, z);
							if (!Main.getInstance().getConfig().getBoolean("DisableInitialItems")) {
								
							/* 157 */      lobby.setPitch((float)Main.plugin.getConfig().getDouble("SpawnD.Pitch"));
							/* 158 */      lobby.setYaw((float)Main.plugin.getConfig().getDouble("SpawnD.Yaw"));
							/*  94 */       ItemStack kitsr = Main.getInstance().getConfig().getItemStack("KitsItem");
							/*  96 */       ItemMeta kitsr2 = kitsr.getItemMeta();
							/*  97 */       kitsr2.setDisplayName(Main.messages.getString("KitsItemName").replace("&", "§"));
							/*  98 */       kitsr.setItemMeta(kitsr2);
							/*  95 */       ItemStack kits = Main.getInstance().getConfig().getItemStack("ShopItem");
							/*  96 */       ItemMeta kits2 = kits.getItemMeta();
							/*  97 */       kits2.setDisplayName(Main.messages.getString("ShopItemName").replace("&", "§"));
							/*  98 */       kits.setItemMeta(kits2);
							/*  99 */       ItemStack st = Main.getInstance().getConfig().getItemStack("1v1Item");
							/* 100 */       ItemMeta st2 = st.getItemMeta();
							/* 101 */       st2.setDisplayName(Main.messages.getString("1v1ItemName").replace("&", "§"));
							/* 102 */       st.setItemMeta(st2);
							ItemStack stats = Main.getInstance().getConfig().getItemStack("StatsItem");
							/* 227 */           ItemMeta stats2 = kits.getItemMeta();
							/* 228 */           stats2.setDisplayName(Main.messages.getString("StatsItemName").replace("&", "§"));
							/* 229 */           stats.setItemMeta(stats2);
							p.getInventory().setItem(Main.getInstance().getConfig().getInt("StatsItemSlot"), stats);

							ItemStack stats1 = Main.getInstance().getConfig().getItemStack("ClickTestItem");
							/* 227 */           ItemMeta stats12 = stats1.getItemMeta();
							/* 228 */           stats12.setDisplayName(Main.messages.getString("ClickTestItemName").replace("&", "§"));
							/* 229 */           stats1.setItemMeta(stats12);
							ItemStack warp = Main.getInstance().getConfig().getItemStack("WarpItem");
							/* 227 */           ItemMeta warp2 = warp.getItemMeta();
							/* 228 */           warp2.setDisplayName("§aWarps");
							/* 229 */           warp.setItemMeta(warp2);
							if (!Main.getInstance().getConfig().getBoolean("DisableWarpItem")) {
								p.getInventory().setItem(Main.getInstance().getConfig().getInt("WarpItemSlot"), warp);
								}
							ItemStack sair = Main.getInstance().getConfig().getItemStack("LeaveItem");
							/* 227 */           ItemMeta sair2 = sair.getItemMeta();
							/* 228 */           sair2.setDisplayName(Main.messages.getString("LeaveItemName").replace("&", "§"));
							/* 229 */           sair.setItemMeta(sair2);
							if (!Main.getInstance().getConfig().getBoolean("DisableLeaveItem")) {
								p.getInventory().setItem(Main.getInstance().getConfig().getInt("LeaveItemSlot"), sair);
							}
							/* 103 */     
							if (!Main.getInstance().getConfig().getBoolean("DisableClickTestItem")) {
								p.getInventory().setItem(Main.getInstance().getConfig().getInt("ClickTestItemSlot"), stats1);
								}
							p.getInventory().setItem(Main.getInstance().getConfig().getInt("KitsItemSlot"), kitsr);
							if (!Main.getInstance().getConfig().getBoolean("DisableShopItem")) {
							/* 103 */     	p.getInventory().setItem(Main.getInstance().getConfig().getInt("ShopItemSlot"), kits);
							}
							
							/* 104 */       	p.getInventory().setItem(Main.getInstance().getConfig().getInt("1v1ItemSlot"), st);
							/*     */       }
							/*     */ 
							/* 107 */       p.updateInventory();
							/*     */       
							/*     */ 
							/* 107 */       
							/*     */       
							/*     */ 
							/*     */ 
							/*     */ 
							/* 187 */       p.setExp(0.0F);
							/* 188 */       p.setExhaustion(20.0F);
							/* 189 */       p.setFireTicks(0);
							/* 190 */       p.setFoodLevel(20000);
							/* 191 */       p.getInventory().setArmorContents(null);
							/*     */       
							/* 193 */       p.updateInventory();
							/* 194 */       Habilidade.removeAbility(p);
							/* 195 */       Deshfire.Armadura.remove(p.getName());
							/* 196 */       Deshfire.Armadura2.remove(p.getName());
							/* 197 */       Deshfire.cooldownm.remove(p);
							/*     */       p.setGameMode(GameMode.SURVIVAL);
							/* 199 */       Cooldown.remove(p);
							p.teleport(lobby);
							p.sendMessage(ChatColor.YELLOW + "You respawned");
							p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 30, 0));
							NewKitMenu.playSound(p, Main.getInstace().getConfig().getString("Sound.RespawnSucess"), 1.0F, 1.0F);
							
							cancel();
							
						}
						
					}
					
				}.runTaskTimer(Main.getInstance(), 0L, 20L);
			}
	}
	
	
	

			
		

		
}
