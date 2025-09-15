package me.RafaelAulerDeMeloAraujo.SpecialAbility;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldguard.LocalPlayer;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.flags.Flags;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import com.sk89q.worldguard.protection.regions.RegionQuery;

import cooldown1.ItemCooldown;
import cooldown1.WaveCooldown2;
import me.RafaelAulerDeMeloAraujo.main.Main;
import net.wavemc.core.util.WaveCooldownAPI;





public class API
{
  
    public static String NomeServer;

    public static String fimcooldown;
	
	  public static String monkCooldownMessage = "§c§bYou cannot use your ability for more: §5 %s seconds!";
	  public String monkedMessage = "§cYou use your ability !";
	  public static int cooldownmonk = 15;
	  public static boolean sendThroughInventory = true;
	  public static transient HashMap<ItemStack, Long> monkStaff = new HashMap<ItemStack, Long>();
	
	public static ArrayList<String> admin = new ArrayList<String>();
	public static ArrayList<String> report = new ArrayList<String>();
	
	public static ArrayList<String> used = new ArrayList<String>();
	public static ArrayList<String> warp = new ArrayList<String>();
	
	public static ArrayList<String> Velotrol2 = new ArrayList<String>();
	public static ArrayList<String> freeze = new ArrayList<String>();
	public static ArrayList<String> freezing = new ArrayList<String>();
	   private static String text = "";
	public static HashMap<Player, String> kit = new HashMap<Player, String>();
	private static me.RafaelAulerDeMeloAraujo.ScoreboardManager.WaveAnimation waveAnimation;
    public static void MensagemCooldown(final Player p) {
    	sendMessageCooldown(p);
        p.sendMessage(String.valueOf(API.NomeServer) + (Main.messages.getString("KitCooldown").replace("&", "§")).replace("%time%", String.valueOf(Cooldown.cooldown(p))));
    }
   
    	   static {
    	       
    	        API.NomeServer = Main.getInstance().getConfig().getString("Prefix").replace("&", "§");
    	        API.fimcooldown = Main.getInstance().getConfig().getString("Prefix").replace("&", "§") + (Main.messages.getString("KitCooldownEnd").replace("&", "§"));
    	    
    	    
    	    
    	   }
    	   public static void sopa(final Player p) {
    		   ItemStack sopa = new ItemStack(Material.MUSHROOM_STEW);
    		   /* 50 */       ItemMeta sopas = sopa.getItemMeta();
    		   /* 51 */       sopas.setDisplayName("§6Soup");
    		   /* 52 */       sopa.setItemMeta(sopas);
    		   for (int i = 0; i <= 34; i++) {
    				/* 76 */         p.getInventory().addItem(new ItemStack[] { sopa });
    			}
    		   ItemStack dima = new ItemStack(Material.STONE_SWORD);
    		   /* 46 */       ItemMeta souperaa = dima.getItemMeta();
    		   /* 47 */       souperaa.setDisplayName("§cSword");
    		   /* 48 */       dima.setItemMeta(souperaa);
    		                  dima.addEnchantment(Enchantment.SHARPNESS, 1);
    		                  p.getInventory().setItem(0 , dima);
   	    }
    	   public static void sopak(final Player p) {
    		   ItemStack sopa = new ItemStack(Material.MUSHROOM_STEW);
    		   /* 50 */       ItemMeta sopas = sopa.getItemMeta();
    		   /* 51 */       sopas.setDisplayName("§6Soup");
    		   /* 52 */       sopa.setItemMeta(sopas);
    		   for (int i = 0; i <= 34; i++) {
    				/* 76 */         p.getInventory().addItem(new ItemStack[] { sopa });
    			}
    		 
   	    }
    	   public static boolean isInRegion(Player player){

    		   RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
    		   RegionQuery query = container.createQuery();
    		   ApplicableRegionSet set = query.getApplicableRegions(BukkitAdapter.adapt(player.getLocation()));

    		   for (ProtectedRegion region : set) {
    			   if (region != null){
    				   LocalPlayer localPlayer = WorldGuardPlugin.inst().wrapPlayer(player);
    				   if (!set.testState(localPlayer, Flags.PVP)) {                     
                           return true;
                       }
    		    
    		           return true;

    		       }
    		   }
    		 return false;
    		 }
   	    
		public static void vida(final Player o) {        
			 Class<?> clazz = null;
		       try {  
					 clazz = Class.forName("org.bukkit.attribute.Attribute");
	       }  catch (ClassNotFoundException | NoClassDefFoundError e) {
	           o.setHealth(o.getMaxHealth());
	           return;
	       }
		       Class<?> clazz2 = org.bukkit.attribute.Attribute.class;

		        // Check for a public method
		        try {
		            Method publicMethod = clazz2.getMethod("MAX_HEALTH");

		            Bukkit.getLogger().info("[KP-PVP - DEBUG] publicMethod MAX_HEALTH exists: " + (publicMethod != null));
		        } catch (NoSuchMethodException e) {
		        	Bukkit.getLogger().info("[KP-PVP - DEBUG] publicMethod MAX_HEALTH does not exist.");
		        	  o.setHealth(o.getMaxHealth());
		        	  return;
		        }
			 o.setHealth(o.getAttribute(Attribute.MAX_HEALTH).getDefaultValue());
		}
	    public static void tirarArmadura(final Player p) {
	        p.getInventory().setHelmet(new ItemStack(Material.AIR));
	        p.getInventory().setChestplate(new ItemStack(Material.AIR));
	        p.getInventory().setLeggings(new ItemStack(Material.AIR));
	        p.getInventory().setBoots(new ItemStack(Material.AIR));
	    }
	    public static ItemStack darArmadura(final Material material, final Color cor) {
	        final ItemStack item = new ItemStack(material);
	        final LeatherArmorMeta itemm = (LeatherArmorMeta)item.getItemMeta();
	        itemm.setColor(cor);
	        item.setItemMeta((ItemMeta)itemm);
	        return item;
	    }
	    
		  public static void darEfeito(final Player p, final PotionEffectType tipo, final int duracao, final int level) {
		        p.addPotionEffect(new PotionEffect(tipo, 20 * duracao, level));
		    }
			public static String cor(String s) {
				return s.replace("&", "§");
			} 

public static void tirarEfeitos(final Player p) {
    p.removePotionEffect(PotionEffectType.ABSORPTION);
    p.removePotionEffect(PotionEffectType.BLINDNESS);
    p.removePotionEffect(PotionEffectType.NAUSEA);
    p.removePotionEffect(PotionEffectType.RESISTANCE);
    p.removePotionEffect(PotionEffectType.MINING_FATIGUE);
    p.removePotionEffect(PotionEffectType.FIRE_RESISTANCE);
    p.removePotionEffect(PotionEffectType.INSTANT_DAMAGE);
    p.removePotionEffect(PotionEffectType.INSTANT_HEALTH);
    p.removePotionEffect(PotionEffectType.HEALTH_BOOST);
    p.removePotionEffect(PotionEffectType.HUNGER);
    p.removePotionEffect(PotionEffectType.STRENGTH);
    p.removePotionEffect(PotionEffectType.INVISIBILITY);
    p.removePotionEffect(PotionEffectType.JUMP_BOOST);
    p.removePotionEffect(PotionEffectType.NIGHT_VISION);
    p.removePotionEffect(PotionEffectType.POISON);
    p.removePotionEffect(PotionEffectType.REGENERATION);
    p.removePotionEffect(PotionEffectType.SATURATION);
    p.removePotionEffect(PotionEffectType.SLOWNESS);
    p.removePotionEffect(PotionEffectType.SPEED);
    p.removePotionEffect(PotionEffectType.WATER_BREATHING);
    p.removePotionEffect(PotionEffectType.WEAKNESS);
    p.removePotionEffect(PotionEffectType.WITHER);
}
protected boolean hasCooldown(Player player) {
    return WaveCooldown2.hasCooldown(player, "Kit");
}

protected boolean hasCooldown(Player player, String cooldown) {
    return WaveCooldown2.hasCooldown(player, cooldown);
}

protected boolean inCooldown(Player player, String cooldown) {
    return WaveCooldown2.inCooldown(player, cooldown);
}

protected boolean inCooldown(Player player) {
    return WaveCooldown2.inCooldown(player, "Kit");
}

protected static void sendMessageCooldown(Player player) {
	WaveCooldown2.sendMessage(player, "Kit");
}

protected void sendMessageCooldown(Player player, String cooldown) {
	WaveCooldown2.sendMessage(player, cooldown);
}

protected void addCooldown(Player player, String cooldownName, long time) {
    if (WaveCooldown2.hasCooldown(player, cooldownName)) {
       WaveCooldown2.removeCooldown(player, cooldownName);
    }
    WaveCooldown2.addCooldown(player, new net.wavemc.core.util.WaveCooldownAPI(cooldownName, time));
}

protected static void addCooldown(Player player, long time) {
    if (WaveCooldown2.hasCooldown(player, "Kit")) {
        WaveCooldown2.removeCooldown(player, "Kit");
    }
    WaveCooldown2.addCooldown(player, new WaveCooldownAPI("Kit", time));
}

protected void addItemCooldown(Player player, ItemStack item, String cooldownName, long time) {
    if (WaveCooldown2.hasCooldown(player, cooldownName)) {
       WaveCooldown2.removeCooldown(player, cooldownName);
    }
    WaveCooldown2.addCooldown(player, new ItemCooldown(item, cooldownName, time));
}

public static void give(Player p)
{
	if (!Main.getInstance().getConfig().getBoolean("DisableCompassItem")) {
		
   ItemStack marrom21 = new ItemStack(Material.COMPASS);
   ItemMeta marrom211 = marrom21.getItemMeta();
   marrom211.setDisplayName("§eCompass");
   List<String> itemlorem11 = new ArrayList();
   itemlorem11.add("§cFind enemies near you!");
   marrom211.setLore(itemlorem11);
   marrom21.setItemMeta(marrom211);
   p.getInventory().setItem(8, marrom21); 
	}
	if (!Main.getInstance().getConfig().getBoolean("DisableRecraftItem")) {
  ItemStack vermelho = new ItemStack(Material.RED_MUSHROOM, 64);
  ItemMeta vermelho2 = vermelho.getItemMeta();
  vermelho2.setDisplayName("§cRed Mushroom");
  vermelho.setItemMeta(vermelho2);
  
  ItemStack marrom = new ItemStack(Material.BROWN_MUSHROOM, 64);
  ItemMeta marrom2 = marrom.getItemMeta();
  marrom2.setDisplayName("§eBrown Mushroom");
  marrom.setItemMeta(marrom2);
  
  ItemStack item = new ItemStack(Material.BOWL, 64);
  ItemMeta item2 = item.getItemMeta();
  item2.setDisplayName("§7Bowl");
  @SuppressWarnings({ "unchecked", "rawtypes" })
	List<String> itemlore = new ArrayList();
  itemlore.add("§4Make soups with that bowls!");
  item2.setLore(itemlore);
  item.setItemMeta(item2);
  p.getInventory().setItem(14, vermelho);
  p.getInventory().setItem(15, marrom);
  p.getInventory().setItem(13, item);
	}
 

}
}
