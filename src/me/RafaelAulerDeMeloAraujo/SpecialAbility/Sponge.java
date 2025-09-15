 package me.RafaelAulerDeMeloAraujo.SpecialAbility;
/*  
/*    */ import java.util.ArrayList;

import org.bukkit.Bukkit;
/*    */ import org.bukkit.Effect;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.entity.EntityDamageEvent;
/*    */ import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
/*    */ import org.bukkit.util.Vector;

/*    */ import me.RafaelAulerDeMeloAraujo.main.Main;
/*    */ 
/*    */ public class Sponge
/*    */   implements Listener
/*    */ {
/*    */   private Main main;
/*    */   static Main plugin;
/*    */   
/*    */   public Sponge(Main main)
/*    */   {
/* 31 */     this.main = main;
/* 32 */     plugin = main;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/* 37 */   ArrayList<String> fall = new ArrayList<>();
/*    */   ArrayList<String> fall2 = new ArrayList<>();
public void Atirar(Player p) {
	int y = Main.getInstance().getConfig().getInt("SpongeBoostAmplifier");
	Block block = p.getLocation().getBlock().getRelative(0, -1, 0);
	if (block.getType() == Material.SPONGE && Join.game.contains(p.getName())) {
		Vector vector = new Vector(0, y, 0);
		p.setVelocity(vector);
		this.fall.remove(p.getName());    NewKitMenu.playSound(p, Main.getInstace().getConfig().getString("Sound.SpongeUse"), 1.0F, 1.0F);
		
		p.getPlayer().getWorld().playEffect(p.getPlayer().getLocation(), Effect.MOBSPAWNER_FLAMES, 10);
		this.fall.add(p.getName());
	}
}
public void Atirar2(Player p) {
	final Location loc = p.getEyeLocation();

    final Vector sponge = p.getLocation().getDirection().multiply(3.8).setY(0.45);
	Block block = p.getLocation().getBlock().getRelative(0, -1, 0);
	if (block.getType() == Material.SLIME_BLOCK && Join.game.contains(p.getName())) {
		p.setVelocity(sponge);
	    p.playEffect(loc, Effect.MOBSPAWNER_FLAMES, (Object)null);
		this.fall2.remove(p.getName());
		this.fall2.add(p.getName());
	}
}
@EventHandler
public void RemoverDan2o(EntityDamageEvent e) 
{
   if (!(e.getEntity() instanceof Player)) {
       return;
   }
   Player p = (Player) e.getEntity();
   if (e.getCause() == EntityDamageEvent.DamageCause.FALL && !Habilidade.ContainsAbility(p) && this.fall2.contains(p.getName()) && e.getEntity().getLocation().getY() < Main.plugin.getConfig().getInt("Spawn.Y") && Join.game.contains(p.getName()) && p.getWorld().equals(Bukkit.getWorld(Main.plugin.getConfig().getString("Spawn.World"))))  {
	   e.setCancelled(true);
	      p.getInventory().clear();
	      /* 45 */       ItemStack dima = new ItemStack(Material.DIAMOND_SWORD);
	      /* 46 */       ItemMeta souperaa = dima.getItemMeta();
	      /* 47 */       souperaa.setDisplayName("§cSword");
	      /* 48 */       dima.setItemMeta(souperaa);
	                     dima.addEnchantment(Enchantment.SHARPNESS, 1);
	                     
	      /* 49 */       ItemStack sopa = new ItemStack(Material.MUSHROOM_STEW);
	      /* 50 */       ItemMeta sopas = sopa.getItemMeta();
	      /* 51 */       sopas.setDisplayName("§6Soup");
	      /* 52 */       sopa.setItemMeta(sopas);
	      /*    */       
	      /*    */ 
	      /* 55 */       ItemStack capacete0 = new ItemStack(Material.IRON_HELMET);
	      /*    */       
	      /* 57 */       ItemStack peitoral0 = new ItemStack(Material.IRON_CHESTPLATE);
	      /*    */       
	      /* 59 */       ItemStack calca0 = new ItemStack(Material.IRON_LEGGINGS);
	      /*    */       
	      /* 61 */       ItemStack Bota0 = new ItemStack(Material.IRON_BOOTS);
	      /*    */       
	      /* 63 */       p.getInventory().setHelmet(capacete0);
	      /* 64 */       p.getInventory().setChestplate(peitoral0);
	      /* 65 */       p.getInventory().setLeggings(calca0);
	      /* 66 */       p.getInventory().setBoots(Bota0);
	      /* 67 */       Habilidade.setAbility(p, "Basic");
	      /* 68 */       p.sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§")) + this.main.getConfig().getString("Message.Kit").replaceAll("%kit%", "Basic").replace("&", "§"));
	      /*    */       API.give(p);
	      /* 70 */       p.getInventory().addItem(new ItemStack[] { dima });
	      /*    */       
	      /*    */ 
	      /*    */ 
	      /*    */ 
	      /* 75 */       for (int i = 0; i <= 34; i++) {
	      /* 76 */         p.getInventory().addItem(new ItemStack[] { sopa });
	      /* 77 */         me.RafaelAulerDeMeloAraujo.TitleAPI.TitleAPI.sendTitle(p, Integer.valueOf(20), Integer.valueOf(60), Integer.valueOf(20), this.main.getConfig().getString("Title.KitTitle"), this.main.getConfig().getString("Title.KitSubTitle").replaceAll("%kit%", "Basic"));
	      /*    */       }
	      /*    */     }
	      /*    */     
	   /*    */     
   this.fall2.remove(p.getName());
   }

@EventHandler
private void Jumps(PlayerMoveEvent e) {
	Player p = e.getPlayer();
	Atirar(p);
	Atirar2(p);
}

 
   @EventHandler
	public void RemoverDano(EntityDamageEvent e) 
	{
	   if (!(e.getEntity() instanceof Player)) {
           return;
       }
		Player p = (Player) e.getEntity();
		if (e.getCause() == EntityDamageEvent.DamageCause.FALL && this.fall.contains(p.getName())) 
		{
			this.fall.remove(p.getName());
			e.setCancelled(true);

		}
		if (e.getCause() == EntityDamageEvent.DamageCause.FALL && this.fall2.contains(p.getName())) 
		{
			this.fall2.remove(p.getName());
			e.setCancelled(true);
			if (!Habilidade.ContainsAbility(p)) {
				/* 44 */       p.getInventory().clear();
				   /* 45 */       
				   /*    */       
				   /*    */ 
				   /* 67 */       Habilidade.setAbility(p, "Basic");
				   /* 68 */       p.sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§")) + this.main.getConfig().getString("Message.Kit").replaceAll("%kit%", "Basic").replace("&", "§"));
				   /*    */       
				   /* 70 */       ItemStack sopa = new ItemStack(Material.MUSHROOM_STEW);
				   /* 50 */       ItemMeta sopas = sopa.getItemMeta();
				   /* 51 */       sopas.setDisplayName("§6Soup");
				   /* 52 */       sopa.setItemMeta(sopas);
				   /*    */       API.give(p);
				   for (int i = 0; i <= 34; i++) {
				   	/* 76 */         p.getInventory().addItem(new ItemStack[] { sopa });
				   }
				   /* 77 */         me.RafaelAulerDeMeloAraujo.TitleAPI.TitleAPI.sendTitle(p, Integer.valueOf(20), Integer.valueOf(60), Integer.valueOf(20), this.main.getConfig().getString("Title.KitTitle"), this.main.getConfig().getString("Title.KitSubTitle").replaceAll("%kit%", "Basic"));
				   /*    */       }
			}

		else if(e.getCause() == EntityDamageEvent.DamageCause.ENTITY_ATTACK)
		{
			this.fall.remove(p.getName());
			this.fall2.remove(p.getName());

		}
		
	}
	
}
