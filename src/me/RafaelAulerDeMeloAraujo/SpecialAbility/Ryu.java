package me.RafaelAulerDeMeloAraujo.SpecialAbility;



import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Effect;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.util.BlockIterator;
import org.bukkit.util.Vector;

import cooldown1.CooldownFinishEvent;
import cooldown1.CooldownStartEvent;
import cooldown1.ItemCooldown;
import cooldown1.WaveCooldown2;
import me.RafaelAulerDeMeloAraujo.main.Main;
import me.RafaelAulerDeMeloAraujo.main.RTP;
import me.RafaelAulerDeMeloAraujo.main.ServerTimerEvent;
import net.wavemc.core.bukkit.api.HelixActionBar;
import net.wavemc.core.util.WaveCooldownAPI;


public class Ryu 
implements Listener, CommandExecutor
/*    */ {
/*    */   private Main main;
/*    */   static Main plugin;
/*    */   
/*    */   public Ryu(Main main)
/*    */   {
/* 20 */     this.main = main;
/* 21 */     plugin = main;
/*    */   }

/*    */   
	@EventHandler
	  public void hadouken(PlayerInteractEvent e)
	  {
	    Player p = e.getPlayer();
	    if (((e.getAction() == Action.RIGHT_CLICK_AIR) || (e.getAction() == Action.RIGHT_CLICK_BLOCK)) && 
	      (Habilidade.getAbility(p).equalsIgnoreCase("Ryu")) && 
	      (p.getItemInHand().getType() == Material.BEACON)) {
	    	if (Cooldown.add(p)) {
	    	    API.MensagemCooldown(p);
	    	    return;
	    	}
	  	  if (API.isInRegion(p)) {
    		  p.sendMessage(ChatColor.RED + "Leave the NO PVP Zone to use this kit!");
    		  return;
    	  }
	     
	        e.setCancelled(true);
	        p.updateInventory();
	        
	        Location location = p.getEyeLocation();
	        BlockIterator blocksToAdd = new BlockIterator(location, 0.0D, 40);
	        while (blocksToAdd.hasNext())
	        {
	          Location blockToAdd = blocksToAdd.next().getLocation();
	          p.getWorld().playEffect(blockToAdd, Effect.STEP_SOUND, Material.LAVA, 40);

NewKitMenu.playSound(p, Main.getInstace().getConfig().getString("Sound.RyuAbility"), 3.0F, 3.0F);
	        }
	        Snowball h = (Snowball)p.launchProjectile(Snowball.class);
	        Vector velo1 = p.getLocation().getDirection().normalize().multiply(10);
	        h.setVelocity(velo1);
	        h.setMetadata("hadouken", new FixedMetadataValue(plugin, Boolean.valueOf(true)));
	        Cooldown.add(p, Main.kits.getInt("RyuCooldown"));
	        return;
	      }
	  }
	public static void throwRandomFirework(Player p) {
	    Firework fw = (Firework) p.getWorld().spawnEntity(p.getLocation(), EntityType.FIREWORK_ROCKET);
	    FireworkMeta fwm = fw.getFireworkMeta();

	    //Our random generator
	    Random r = new Random();

	    FireworkEffect.Type type = FireworkEffect.Type.BALL_LARGE;

	    Color c1 = Color.RED;
	    Color c2 = Color.WHITE;
	    FireworkEffect effect = FireworkEffect.builder().flicker(r.nextBoolean()).withColor(c1).withFade(c2).with(type).trail(r.nextBoolean()).build();

	    //Then apply the effect to the meta
	    fwm.addEffect(effect);

	    //Generate some random power and set it


	    //Create our effect with this   int rp = r.nextInt(2) + 1;
	    int rp = r.nextInt(4) + 1;
	    fwm.setPower(rp);

	    //Then apply this to our rocket
	    fw.setFireworkMeta(fwm);
	    
	}
	@EventHandler
	public void onUpdate(ServerTimerEvent event) {
		if (event.getCurrentTick() % 2 > 0)
			return;

		for (UUID uuid : WaveCooldown2.map.keySet()) {
			Player player = Bukkit.getPlayer(uuid);
			if (player != null) {
				List<WaveCooldownAPI> list = WaveCooldown2.map.get(uuid);
				Iterator<WaveCooldownAPI> it = list.iterator();

				WaveCooldownAPI found = null;
				while (it.hasNext()) {
					WaveCooldownAPI cooldown = it.next();
					if (!cooldown.expired()) {
						if (cooldown instanceof ItemCooldown) {
							ItemStack hand = player.getEquipment().getItemInHand();
							if (hand != null && hand.getType() != Material.AIR) {
								ItemCooldown item = (ItemCooldown) cooldown;
								if (hand.equals(item.getItem())) {
									item.setSelected(true);
									found = item;
									break;
								}
							}
							continue;
						}
						found = cooldown;
						continue;
					}
					it.remove();
					Bukkit.getServer().getPluginManager().callEvent(new CooldownFinishEvent(player, cooldown));

NewKitMenu.playSound(player, Main.getInstace().getConfig().getString("Sound.Respawn"), 3.0F, 3.0F);
				}

				if (found != null) {
					CooldownStartEvent e = new CooldownStartEvent(player, found);
					Bukkit.getPluginManager().callEvent(e);
					if (!e.isCancelled()) {
						WaveCooldown2.display(player, found);
					}
				} else if (list.isEmpty()) {
					HelixActionBar.send(player, "");
					WaveCooldown2.map.remove(uuid);
				} else {
					WaveCooldownAPI cooldown = list.get(0);
					if (cooldown instanceof ItemCooldown) {
						ItemCooldown item = (ItemCooldown) cooldown;
						if (item.isSelected()) {
							item.setSelected(false);
							HelixActionBar.send(player, "");
						}
					}
				}
			}
		}
	}
	  
	  
	  @EventHandler
	  public void dano(EntityDamageByEntityEvent e)
	  {
	    if (((e.getEntity() instanceof Player)) && ((e.getDamager() instanceof Snowball)))
	    {
	      Snowball s = (Snowball)e.getDamager();
	      if (s.hasMetadata("hadouken")) {
	    	  Player e2 = (Player)e.getEntity();
	    	  throwRandomFirework(e2);
	        e.setDamage(e.getDamage() + Main.kits.getDouble("RyuDamage"));
	      }
	    }
	    }
	    /*    */   
	     
	  

	  /*    */ 
	  /*    */   public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
	  /*    */   {
	  /* 27 */     Player p = (Player)sender;
	  /*    */     
	  /* 29 */     if (cmd.getName().equalsIgnoreCase("kryu"))
	  /*    */     {
	  /*    */ 
	  /*    */ 
	  /*    */ 
	  /* 34 */       if (Habilidade.ContainsAbility(p)) {
	  /* 35 */         p.sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§")) + this.main.getConfig().getString("Message.KitUse").replace("&", "§"));
	  /* 36 */        
	  NewKitMenu.playSound(p, Main.getInstace().getConfig().getString("Sound.KitUse"), 1.0F, 1.0F); return true;
	  /*    */       }
	  /* 39 */       if (!p.hasPermission("kitpvp.kit.ryu"))
	  /*    */       {
	  /* 41 */         p.sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§")) + this.main.getConfig().getString("Permission").replace("&", "§").replaceAll("%permisson%", commandLabel));
	    NewKitMenu.playSound(p, Main.getInstace().getConfig().getString("Sound.NoPermissionMessage"), 1.0F, 1.0F);
	  /* 43 */         return true;
	  /*    */       }
	  if (Main.kits.getBoolean("RyuDisabled")) {
			p.sendMessage(API.NomeServer + ChatColor.RED + "The Ryu kit is disabled, sorry");
			return true;
		}
	  /* 39 */       if (!Join.game.contains(p.getName()))
	  /*    */       {
	  /* 41 */         p.sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§")) + " §eYou are not in kitpvp to choose this kit!");
	  /* 42 */         return true;
	  /*    */       }
	  /* 44 */       p.getInventory().clear();
	  /* 45 */          p.getInventory().clear();
	  /* 45 */       ItemStack dima = new ItemStack(Material.DIAMOND_SWORD);
	  /* 46 */       ItemMeta souperaa = dima.getItemMeta();
	  /* 47 */       souperaa.setDisplayName("§bSword");
	  /* 48 */       dima.setItemMeta(souperaa);
	  /* 49 */       
	  /* 49 */       ItemStack sopa = new ItemStack(Material.MUSHROOM_STEW);
	  /* 50 */       ItemMeta sopas = sopa.getItemMeta();
	  /* 51 */       sopas.setDisplayName("§6Soup");
	  /* 52 */       sopa.setItemMeta(sopas);
	  ItemStack especial = new ItemStack(Material.BEACON);
	  /* 61 */       ItemMeta especial2 = especial.getItemMeta();
	  /* 62 */       especial2.setDisplayName("§bHadouken");
	  /* 63 */       especial.setItemMeta(especial2);
	  /*    */       
	  /*    */  ItemStack capacete0 = new ItemStack(Material.IRON_HELMET);
		/*     */       
		/* 225 */       ItemStack peitoral0 = new ItemStack(Material.IRON_CHESTPLATE);
		/*     */       
		/* 227 */       ItemStack calca0 = new ItemStack(Material.IRON_LEGGINGS);
		/*     */       
		/* 229 */       ItemStack Bota0 = new ItemStack(Material.LEATHER_BOOTS);
		/*     */       
		/* 231 */       p.getInventory().setHelmet(capacete0);
		/* 232 */       p.getInventory().setChestplate(peitoral0);
		/* 233 */       p.getInventory().setLeggings(calca0);
		/* 234 */       p.getInventory().setBoots(Bota0);
		/* 235 */       p.getInventory().addItem(new ItemStack[] { dima });
		/*     */       p.getInventory().setItem(1, especial);
		/* 238 */       for (int i = 0; i <= 34; i++) {
		/* 239 */         p.getInventory().addItem(new ItemStack[] { sopa });
		/*     */       }
		/*     */     }
	  /* 55 */       
	  /* 67 */       Habilidade.setAbility(p, "Ryu");
	  /* 68 */       p.sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§")) + this.main.getConfig().getString("Message.Kit").replaceAll("%kit%", "Ryu").replace("&", "§"));
	  /*    */       
	  /* 70 */       
	  /*    */       
	  /*    */ API.give(p);
	  /*    */ RTP.TeleportArenaRandom(p);
	  /*    */ 
	
	  /*    */     
	  /*    */ 
	  /*    */ 
	  /*    */ 
	  /*    */ 
	  /* 85 */     return false;
	  /*    */   }
	  /*    */ }

