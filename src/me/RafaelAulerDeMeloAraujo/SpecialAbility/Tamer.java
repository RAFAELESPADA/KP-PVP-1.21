package me.RafaelAulerDeMeloAraujo.SpecialAbility;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.Sound;
/*    */ import org.bukkit.command.Command;
/*    */ import org.bukkit.command.CommandExecutor;
/*    */ import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
/*    */ import org.bukkit.entity.Player;
import org.bukkit.entity.Wolf;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;

/*    */ import me.RafaelAulerDeMeloAraujo.main.Main;
import me.RafaelAulerDeMeloAraujo.main.RTP;
/*    */ 
/*    */ public class Tamer implements CommandExecutor, Listener
/*    */ {
/*    */   private Main main;
/*    */   static Main plugin;
/*    */   
/*    */   public Tamer(Main main)
/*    */   {
/* 23 */     this.main = main;
/* 24 */     plugin = main;
/*    */   }
/*    */   @EventHandler
public void dano(EntityDamageByEntityEvent e)
{
  if (((e.getEntity() instanceof Wolf)) && ((e.getDamager() instanceof Player)))
  {
   Wolf s = (Wolf)e.getEntity();
    if (s.hasMetadata("GGG2")) {
      e.setCancelled(true);
    }
  }
}
/*    */   @EventHandler
public void dano2(EntityDamageByEntityEvent e)
{
  if (((e.getEntity() instanceof Player)) && ((e.getDamager() instanceof Wolf)))
  {
   Wolf s = (Wolf)e.getDamager();
    if (s.hasMetadata("GGG2")) {
     e.setDamage(e.getDamage() + 1.7);
    }
  }
}
  @EventHandler(priority = EventPriority.LOW)
  public void onInteract(PlayerInteractEvent event) {
  	Player p = event.getPlayer();
  	if (event.getItem() == null) {
  		return;
  	}
  	if (!(Habilidade.getAbility(p) == "Tamer")) {
  		return;
  	}	
  	if ((p.getItemInHand().getType() == Material.BONE) && 
  			/*  63 */       (Habilidade.getAbility(p) == "Tamer"))
  			/*     */     {
  		event.setCancelled(true);
  		 if (Cooldown.add(p)) {
               API.MensagemCooldown(p);
               return;
           }
  	      Wolf wolf = (Wolf) p.getWorld().spawnEntity(p.getLocation(), EntityType.WOLF);

  	      Wolf wolf2 = (Wolf) p.getWorld().spawnEntity(p.getLocation(), EntityType.WOLF);

  	      Wolf wolf3 = (Wolf) p.getWorld().spawnEntity(p.getLocation(), EntityType.WOLF);

  	      Wolf wolf4 = (Wolf) p.getWorld().spawnEntity(p.getLocation(), EntityType.WOLF);
  	    wolf.setOwner(p);
  	  wolf2.setOwner(p);
  	 wolf3.setOwner(p);
  	 wolf4.setOwner(p);
  	 Cooldown.add(p, Main.kits.getInt("TamerCooldown"));
  	wolf.setMetadata("GGG2", new FixedMetadataValue(Main.getInstance(), Boolean.valueOf(true)));
  	wolf2.setMetadata("GGG2", new FixedMetadataValue(Main.getInstance(), Boolean.valueOf(true)));
  	wolf3.setMetadata("GGG2", new FixedMetadataValue(Main.getInstance(), Boolean.valueOf(true)));
  	wolf4.setMetadata("GGG2", new FixedMetadataValue(Main.getInstance(), Boolean.valueOf(true)));
  	Bukkit.getScheduler().runTaskLater(Main.getInstance() , new Runnable() {
	    @Override
	    public void run() {
	   	wolf.remove();
	   	wolf2.remove();
	   	wolf3.remove();
	   	wolf4.remove();
	        }
	    }
	, Main.kits.getInt("TamerDogsLastsFor") * 20);
  	}
  }
/*    */ 
/*    */   public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
/*    */   {
/* 30 */     Player p = (Player)sender;
/*    */     
/* 32 */     if (cmd.getName().equalsIgnoreCase("ktamer"))
/*    */     {
/* 34 */       if (!Join.game.contains(p.getName()))
/*    */       {
/* 36 */         p.sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§")) + " §eYou are not in kitpvp to do choose this kit!");
/* 37 */         return true;
/*    */       }
/* 39 */       if (!p.hasPermission("kitpvp.kit.tamer"))
/*    */       {
/* 41 */         p.sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§")) + this.main.getConfig().getString("Permission").replace("&", "§").replaceAll("%permisson%", commandLabel));
/* 42 */         p.playSound(p.getLocation(), Sound.valueOf(this.main.getConfig().getString("Sound.NoPermissionMessage")), 1.0F, 1.0F);
/* 43 */         return true;
/*    */       }
if (Main.kits.getBoolean("TamerDisabled")) {
	p.sendMessage(API.NomeServer + ChatColor.RED + "The Tamer kit is disabled, sorry");
	return true;
}
/*    */       
/* 46 */       if (Habilidade.ContainsAbility(p)) {
/* 47 */         p.sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§")) + this.main.getConfig().getString("Message.KitUse").replace("&", "§"));
/* 48 */         p.playSound(p.getLocation(), Sound.valueOf(this.main.getConfig().getString("Sound.KitUse")), 1.0F, 1.0F);
/* 49 */         return true;
/*    */       }
/* 51 */       p.getInventory().clear();
/* 52 */       ItemStack dima = new ItemStack(Material.DIAMOND_SWORD);
/* 53 */       ItemMeta souperaa = dima.getItemMeta();
/* 54 */       souperaa.setDisplayName("§cSword");
/* 55 */       dima.setItemMeta(souperaa);
/* 56 */       ItemStack sopa = new ItemStack(Material.MUSHROOM_STEW);
/* 57 */       ItemMeta sopas = sopa.getItemMeta();
/* 58 */       sopas.setDisplayName("§6Soup");
/* 59 */       sopa.setItemMeta(sopas);
/* 60 */       ItemStack especial = new ItemStack(Material.BONE);
/* 61 */       ItemMeta especial2 = especial.getItemMeta();
/* 62 */       especial2.setDisplayName("§cSpawn Wolfs!");
/* 63 */       especial.setItemMeta(especial2);
/*    */       /*    */ 
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
/* 65 */       
/*    */       
/* 78 */       p.sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§")) + this.main.getConfig().getString("Message.Kit").replaceAll("%kit%", "Tamer").replace("&", "§"));
/* 79 */       Habilidade.setAbility(p, "Tamer");
/* 80 */       
/* 81 */       
/*    */       
/*    */ 
/*    */ API.give(p);
RTP.TeleportArenaRandom(p);

/* 70 */       p.getInventory().addItem(new ItemStack[] { dima });
/* 85 */       for (int i = 0; i <= 34; i++) {
/* 86 */         p.getInventory().addItem(new ItemStack[] { sopa });
/* 87 */         p.playSound(p.getLocation(), Sound.valueOf(this.main.getConfig().getString("Sound.KitUse")), 1.0F, 1.0F);
/* 88 */         me.RafaelAulerDeMeloAraujo.TitleAPI.TitleAPI.sendTitle(p, Integer.valueOf(20), Integer.valueOf(60), Integer.valueOf(20), this.main.getConfig().getString("Title.KitTitle"), this.main.getConfig().getString("Title.KitSubTitle").replaceAll("%kit%", "Tamer"));
/*    */       }
p.getInventory().setItem(1, especial);
/*    */     }
/*    */     
/* 92 */     return false;
/*    */   }
/*    */ }


/* Location:              D:\Desktop\video\Minhas Coisas do Desktop\KP-PVPvB12 (1).jar!\me\RafaelAulerDeMeloAraujo\SpecialAbility\ThorKITCOMMAND.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */

