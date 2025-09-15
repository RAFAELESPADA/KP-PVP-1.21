/*     */ package me.RafaelAulerDeMeloAraujo.SpecialAbility;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;

/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.Color;
/*     */ import org.bukkit.Effect;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.enchantments.Enchantment;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.EventHandler;
/*     */ import org.bukkit.event.Listener;
/*     */ import org.bukkit.event.block.Action;
/*     */ import org.bukkit.event.player.PlayerDropItemEvent;
/*     */ import org.bukkit.event.player.PlayerInteractEvent;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.meta.LeatherArmorMeta;
/*     */ import org.bukkit.util.Vector;

/*     */ import me.RafaelAulerDeMeloAraujo.main.Main;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Deshfire
/*     */   implements Listener
/*     */ {
/*  44 */   public int boost = Integer.valueOf(6).intValue();
/*  45 */  
/*  46 */   static ArrayList<String> fall = new ArrayList<String>();
/*  47 */   public static HashMap<String, ItemStack[]> Armadura = new HashMap();
/*  48 */   public static HashMap<String, ItemStack[]> saveinv = new HashMap();
/*  49 */   public static HashMap<String, ItemStack[]> armadura = new HashMap();
/*  50 */   public static HashMap<String, ItemStack[]> Armadura2 = new HashMap();
/*  51 */   public static List<Player> cooldownm = new ArrayList();
/*     */   public static Main plugin;
/*     */   
/*     */   @EventHandler
/*     */   public void DeshClick(PlayerInteractEvent event)
/*     */   {
/*  57 */     int fire = Integer.valueOf(6).intValue();
/*     */     
/*  59 */     final Player p = event.getPlayer();
/*  60 */     if ((event.getPlayer().getItemInHand().getType() == Material.REDSTONE_BLOCK) && 
/*  61 */       (Habilidade.getAbility(p).equalsIgnoreCase("Deshfire")))
/*     */     {

/*  63 */       if ((event.getAction() == Action.LEFT_CLICK_AIR) || 
/*  64 */         (event.getAction() == Action.LEFT_CLICK_BLOCK) || 
/*  65 */         (event.getAction() == Action.RIGHT_CLICK_BLOCK) || 
/*  66 */         (event.getAction() == Action.RIGHT_CLICK_AIR)) {
/*  67 */         event.setCancelled(true);
/*     */       }
if (Cooldown.add(p)) {
    API.MensagemCooldown(p);
    return;
}

/*  74 */     Cooldown.add(p, Main.kits.getInt("DeshfireCooldown"));
/*  75 */       fall.add(p.getName());
/*  76 */       p.setVelocity(p.getEyeLocation().getDirection().multiply(this.boost).add(new Vector(0, 0, 0)));
/*  77 */       p.getPlayer().getWorld().playEffect(p.getPlayer().getLocation(), Effect.SMOKE, 10, 0);
/*  78 */       Location loc = p.getLocation();
/*  79 */       for (Entity pertos : p.getNearbyEntities(8.0D, 8.0D, 8.0D)) {
/*  80 */         if ((pertos instanceof Player))
/*     */         {
    if (!Habilidade.ContainsAbility((Player)pertos)) {
    	return;
    }
/*  82 */           Player perto = (Player)pertos;
/*  83 */           ((Player)pertos).damage(Main.kits.getDouble("DeshfireDamage"));
/*  84 */           pertos.setVelocity(new Vector(0.1D, 0.0D, 0.1D));
/*  85 */           ((Player)pertos).setFireTicks(fire * 20);
/*     */         }
/*     */       }
/*  88 */       ItemStack Capacete = new ItemStack(Material.LEATHER_HELMET);
/*  89 */       LeatherArmorMeta kCapacete = (LeatherArmorMeta)Capacete.getItemMeta();
/*  90 */       kCapacete.setColor(Color.RED);
/*  91 */       Capacete.setItemMeta(kCapacete);
/*  92 */       Capacete.addEnchantment(Enchantment.PROTECTION, 3);
/*  93 */       ItemStack Peitoral = new ItemStack(Material.LEATHER_CHESTPLATE);
/*  94 */       LeatherArmorMeta kPeitoral = (LeatherArmorMeta)Peitoral.getItemMeta();
/*  95 */       kPeitoral.setColor(Color.RED);
/*  96 */       Peitoral.setItemMeta(kPeitoral);
/*  97 */       Peitoral.addEnchantment(Enchantment.PROTECTION, 3);
/*  98 */       ItemStack Calss = new ItemStack(Material.LEATHER_LEGGINGS);
/*  99 */       LeatherArmorMeta kCalss = (LeatherArmorMeta)Calss.getItemMeta();
/* 100 */       kCalss.setColor(Color.RED);
/* 101 */       Calss.setItemMeta(kCalss);
/* 102 */       Calss.addEnchantment(Enchantment.PROTECTION, 3);
/* 103 */       ItemStack Bota = new ItemStack(Material.LEATHER_BOOTS);
/* 104 */       LeatherArmorMeta kBota = (LeatherArmorMeta)Capacete.getItemMeta();
/* 105 */       kBota.setColor(Color.RED);
/* 106 */       Bota.setItemMeta(kBota);
/* 107 */       Bota.addEnchantment(Enchantment.PROTECTION, 3);
/* 108 */       Armadura.put(p.getName(), p.getInventory().getArmorContents());
/*     */       
/* 110 */       p.getInventory().setHelmet(Capacete);
/* 111 */       p.getInventory().setChestplate(Peitoral);
/* 112 */       p.getInventory().setLeggings(Calss);
/* 113 */       p.getInventory().setBoots(Bota);
/* 114 */       p.updateInventory();
/*     */       
/* 116 */       Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable()
/*     */       {
/*     */         public void run()
/*     */         {
/* 120 */           ItemStack capacete0 = new ItemStack(Material.IRON_HELMET);
/*     */           
/* 122 */           ItemStack peitoral0 = new ItemStack(Material.IRON_CHESTPLATE);
/*     */           
/* 124 */           ItemStack calca0 = new ItemStack(Material.IRON_LEGGINGS);
/*     */           
/* 126 */           ItemStack Bota0 = new ItemStack(Material.IRON_BOOTS);
/* 127 */           p.getInventory().setHelmet(capacete0);
/* 128 */           p.getInventory().setChestplate(peitoral0);
/* 129 */           p.getInventory().setLeggings(calca0);
/* 130 */           p.getInventory().setBoots(Bota0);
/* 131 */           p.updateInventory();
/* 132 */           Deshfire.fall.remove(p.getName());
/*     */         }
/* 134 */       }, 60L);
/*     */       
/* 136 */       Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable()
/*     */       {
/*     */         public void run()
/*     */         {
/* 140 */           Cooldown.remove(p);
/*     */           
/* 142 */           p.sendMessage((Main.messages.getString("DeshFireCooldownEnd").replace("&", "§")));
/*     */         }
/*     */         
/* 145 */       }, Main.kits.getInt("DeshfireCooldown") * 20);
/*     */     }
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onDrop(PlayerDropItemEvent event) {
/* 151 */     if (event.getItemDrop().getItemStack().getType() == Material.REDSTONE_BLOCK && Habilidade.ContainsAbility(event.getPlayer())) {
/* 152 */       event.setCancelled(true);
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\Desktop\video\Minhas Coisas do Desktop\KP-PVPvB12 (1).jar!\me\RafaelAulerDeMeloAraujo\SpecialAbility\Deshfire.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */
