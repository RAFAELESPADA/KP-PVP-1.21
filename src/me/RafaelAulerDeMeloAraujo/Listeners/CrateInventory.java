package me.RafaelAulerDeMeloAraujo.Listeners;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;

import me.RafaelAulerDeMeloAraujo.Coins.Coins;
import me.RafaelAulerDeMeloAraujo.SpecialAbility.Habilidade;
import me.RafaelAulerDeMeloAraujo.SpecialAbility.Join;
import me.RafaelAulerDeMeloAraujo.Warps.SettingsManager;
import me.RafaelAulerDeMeloAraujo.main.Main;

public class CrateInventory implements Listener {

	@SuppressWarnings("unused")
	private static HashMap<String, Long> cooldown = new HashMap<String, Long>();
	static SettingsManager settings = SettingsManager.getInstance();
	 public static ArrayList<String> kitdiariofireman = new ArrayList<>();
	 public static ArrayList<String> kitdiarioninja = new ArrayList<>();
	 public static ArrayList<String> kitdiarioSonic = new ArrayList<>();
	 public static ArrayList<String> kitdiraioMonk = new ArrayList<>();
	 public static ArrayList<String> kitdiarioAjnin = new ArrayList<>();
	 public static ArrayList<String> kitdiariomerlin = new ArrayList<>();
	 public static ArrayList<String> kitdiarioDeshFire = new ArrayList<>();
	 public static ArrayList<String> kitdiarioAntiStomper = new ArrayList<>();
	 public static ArrayList<String> kitdiarioGlad = new ArrayList<>();
	 public static ArrayList<String> setandokit = new ArrayList<>();


	public static void playFirework(Location location, Color color, Player p) {
		Firework firework = (Firework) location.getWorld().spawn(location, Firework.class);
		FireworkMeta fMeta = firework.getFireworkMeta();
		fMeta.addEffect(FireworkEffect.builder().withColor(color).build());
		firework.setFireworkMeta(fMeta);
		firework.setVelocity(new Vector(0.0D, -1.0D, 0.0D));
	}

	public void playFirework(Location location, FireworkEffect effecta, Player p) {
		Firework firework = (Firework) location.getWorld().spawn(location, Firework.class);
		FireworkMeta fMeta = firework.getFireworkMeta();
		fMeta.addEffect(effecta);
		firework.setFireworkMeta(fMeta);
	}

	@SuppressWarnings({ "unused", "deprecation" })
	@EventHandler
	public void aoClicarNoInv(InventoryClickEvent e) {
		Player jogador = (Player) e.getWhoClicked();
		if (e.getClickedInventory() == null) {
    		return;
    	}
		if (!(e.getClickedInventory().getHolder() instanceof CustomHolder5)) {
    		return;
    	}
		if (e.getCurrentItem() != null
				&& (e.getCurrentItem().getType() != Material.AIR)) {
			e.setCancelled(true);
		}
	}

	@SuppressWarnings("deprecation")
	public static void Inventario(final Player jogador) {
		cooldown.put(jogador.getName(), Long.valueOf(10000l));
		jogador.getWorld().playSound(jogador.getLocation(), Sound.valueOf(Main.getInstance().getConfig().getString("Sound.KitMenu")), 10F, 10F);
		final Inventory random = Bukkit.createInventory(new CustomHolder5(), 9, "Unlocking a new kit");
		final ItemStack vidro = new ItemStack(Material.GREEN_STAINED_GLASS_PANE);
		ItemMeta vidrometa = vidro.getItemMeta();
		vidrometa.setDisplayName("§fUnlocking a new kit");
		vidro.setItemMeta(vidrometa);

		final ItemStack vidro1 = new ItemStack(Material.RED_STAINED_GLASS_PANE);
		vidro1.setDurability((short) 7);
		ItemMeta vidrometa1 = vidro1.getItemMeta();
		vidrometa1.setDisplayName("§fUnlocking a new kit");
		vidro1.setItemMeta(vidrometa1);

		final ItemStack vidrovermelho = new ItemStack(Material.BLUE_STAINED_GLASS_PANE);
		vidrovermelho.setDurability((short) 14);
		ItemMeta vidrovermelhoa = vidrovermelho.getItemMeta();
		vidrovermelhoa.setDisplayName("§fUnlocking a new kit");
		vidrovermelho.setItemMeta(vidrovermelhoa);

		final ItemStack vidropreto = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
		vidropreto.setDurability((short) 0);
		ItemMeta vidropretoa = vidropreto.getItemMeta();
		vidropretoa.setDisplayName("§fUnlocking a new kit");
		vidropreto.setItemMeta(vidropretoa);

		final ItemStack espadademadeira = new ItemStack(Material.LAPIS_BLOCK);
		  ItemMeta espadademadeiraa = espadademadeira.getItemMeta();
		  espadademadeiraa.setDisplayName("§eSonic");
		  espadademadeira.setItemMeta(espadademadeiraa);

		  final ItemStack sopa = new ItemStack(Material.LAVA_BUCKET);
		  ItemMeta sopaa = sopa.getItemMeta();
		  sopaa.setDisplayName("§eFireman");
		  sopa.setItemMeta(sopaa);

		  final ItemStack maçadourada = new ItemStack(Material.IRON_BARS);
		  ItemMeta maçadouradaa = maçadourada.getItemMeta();
		  maçadouradaa.setDisplayName("§eGladiator");
		  maçadourada.setItemMeta(maçadouradaa);

		  final ItemStack espadadepedra = new ItemStack(Material.FISHING_ROD);
		  ItemMeta espadadepedraa = espadadepedra.getItemMeta();
		  espadadepedraa.setDisplayName("§eFisherman");
		  espadadepedra.setItemMeta(espadadepedraa);

		  final ItemStack xp = new ItemStack(Material.BLAZE_ROD);
		  ItemMeta xpa = xp.getItemMeta();
		  xpa.setDisplayName("§eMonk");
		  xp.setItemMeta(xpa);

		  final ItemStack Ajnin = new ItemStack(Material.ANVIL);
		  ItemMeta Ajnina = Ajnin.getItemMeta();
		  Ajnina.setDisplayName("§eAnchor");
		  Ajnin.setItemMeta(Ajnina);
		  
		  final ItemStack g = new ItemStack(Material.COAL);
		  ItemMeta gg = g.getItemMeta();
		  gg.setDisplayName("§eNinja");
		  g.setItemMeta(gg);
		  
		  final ItemStack g0 = new ItemStack(Material.TNT);
		  ItemMeta gg0 = g0.getItemMeta();
		  gg0.setDisplayName("§eCreeper");
		  g0.setItemMeta(gg0);

		  final ItemStack enderperal = new ItemStack(Material.FIREWORK_ROCKET);
		  ItemMeta enderperala = enderperal.getItemMeta();
		  enderperala.setDisplayName("§eKangaroo");
		  enderperal.setItemMeta(enderperala);
		  
		  final ItemStack Sonic = new ItemStack(Material.SAND);
		  ItemMeta Sonica = Sonic.getItemMeta();
		  Sonica.setDisplayName("§eCamel");
		  Sonic.setItemMeta(Sonica);
		  

		  final ItemStack Sonic1 = new ItemStack(Material.NETHER_STAR);
		  ItemMeta Sonica1 = Sonic1.getItemMeta();
		  Sonica1.setDisplayName("§eNaruto");
		  Sonic1.setItemMeta(Sonica1);
		  

		  final ItemStack P2 = new ItemStack(Material.WATER_BUCKET);
		  ItemMeta P21 = P2.getItemMeta();
		  P21.setDisplayName("§ePoseidon");
		  P2.setItemMeta(P21);
		  

		  final ItemStack MILK = new ItemStack(Material.MILK_BUCKET);
		  ItemMeta MILK2 = MILK.getItemMeta();
		  MILK2.setDisplayName("§eMilkMan");
		  MILK.setItemMeta(MILK2);
		  
		  

		  final ItemStack RY = new ItemStack(Material.BEACON);
		  ItemMeta RY2 = RY.getItemMeta();
		  RY2.setDisplayName("§eRyu");
		  RY.setItemMeta(RY2);
		  

		  final ItemStack TIME = new ItemStack(Material.CLOCK);
		  ItemMeta TIME2 = TIME.getItemMeta();
		  TIME2.setDisplayName("§eTimelord");
		  TIME.setItemMeta(TIME2);
		  
		  final ItemStack BS = new ItemStack(Material.GOLDEN_CARROT);
		  ItemMeta BS2 = BS.getItemMeta();
		  BS2.setDisplayName("§eBerserker");
		  BS.setItemMeta(BS2);
		  
		  final ItemStack ST = new ItemStack(Material.IRON_BOOTS);
		  ItemMeta ST2 = ST.getItemMeta();
		  ST2.setDisplayName("§eStomper");
		  ST.setItemMeta(ST2);
		  
		  final ItemStack ph = new ItemStack(Material.FEATHER);
		  ItemMeta ph2 = ph.getItemMeta();
		  ph2.setDisplayName("§ePhantom");
		  ph.setItemMeta(ph2);
		  AtomicInteger animation = new AtomicInteger(0);

		  Main.getFolia().getScheduler().runTimer(task -> {

		      if (!jogador.isOnline()) {
		          cooldown.remove(jogador.getName());
		          task.cancel();
		          return;
		      }

		      random.setItem(4,
		              animation.get() % 2 == 0 ? vidro : vidro1);

		      jogador.playSound(
		              jogador.getLocation(),
		              Sound.valueOf(
		                      Main.getInstance().getConfig().getString("Sound.KitMenu")
		              ),
		              10F,
		              10F
		      );

		      animation.incrementAndGet();

		      if (animation.get() >= 22) {

		          task.cancel();

		          Random r = new Random();
		          int gg2 = r.nextInt(17);

		          if (gg2 == 0) {
		              random.setItem(4, espadademadeira);
		              kitdiarioSonic.add(jogador.getName());
		              jogador.sendMessage(ChatColor.AQUA + "You received the Sonic kit!");
		              Coins.perms.playerAdd(jogador, "kitpvp.kit.sonic");
		          }

		          else if (gg2 == 1) {
		              random.setItem(4, sopa);
		              kitdiariofireman.add(jogador.getName());
		              jogador.sendMessage(ChatColor.AQUA + "You received the Fireman kit!");
		              Coins.perms.playerAdd(jogador, "kitpvp.kit.fireman");
		          }

		          else if (gg2 == 2) {
		              random.setItem(4, g);
		              kitdiarioninja.add(jogador.getName());
		              jogador.sendMessage(ChatColor.AQUA + "You received the Ninja kit!");
		              Coins.perms.playerAdd(jogador, "kitpvp.kit.ninja");
		          }

		          else if (gg2 == 3) {
		              random.setItem(4, xp);
		              kitdiraioMonk.add(jogador.getName());
		              jogador.sendMessage(ChatColor.AQUA + "You received the Monk kit!");
		              Coins.perms.playerAdd(jogador, "kitpvp.kit.monk");
		          }

		          else if (gg2 == 4) {
		              random.setItem(4, Ajnin);
		              kitdiarioAntiStomper.add(jogador.getName());
		              jogador.sendMessage(ChatColor.AQUA + "You received the Anchor kit!");
		              Coins.perms.playerAdd(jogador, "kitpvp.kit.anchor");
		          }

		          else if (gg2 == 5) {
		              random.setItem(4, g);
		              jogador.sendMessage(ChatColor.AQUA + "You received the Ninja kit!");
		              Coins.perms.playerAdd(jogador, "kitpvp.kit.ninja");
		          }

		          else if (gg2 == 6) {
		              random.setItem(4, g0);
		              jogador.sendMessage(ChatColor.AQUA + "You received the Creeper kit!");
		              Coins.perms.playerAdd(jogador, "kitpvp.kit.creeper");
		          }

		          else if (gg2 == 7) {
		              random.setItem(4, enderperal);
		              jogador.sendMessage(ChatColor.AQUA + "You received the Kangaroo kit!");
		              Coins.perms.playerAdd(jogador, "kitpvp.kit.kangaroo");
		          }

		          else if (gg2 == 8) {
		              random.setItem(4, Sonic);
		              jogador.sendMessage(ChatColor.AQUA + "You received the Camel kit!");
		              Coins.perms.playerAdd(jogador, "kitpvp.kit.camel");
		          }

		          else if (gg2 == 9) {
		              random.setItem(4, Sonic1);
		              jogador.sendMessage(ChatColor.AQUA + "You received the Naruto kit!");
		              Coins.perms.playerAdd(jogador, "kitpvp.kit.naruto");
		          }

		          else if (gg2 == 10) {
		              random.setItem(4, P2);
		              jogador.sendMessage(ChatColor.AQUA + "You received the Poseidon kit!");
		              Coins.perms.playerAdd(jogador, "kitpvp.kit.poseidon");
		          }

		          else if (gg2 == 11) {
		              random.setItem(4, MILK);
		              jogador.sendMessage(ChatColor.AQUA + "You received the Milkman kit!");
		              Coins.perms.playerAdd(jogador, "kitpvp.kit.milkman");
		          }

		          else if (gg2 == 12) {
		              random.setItem(4, RY);
		              jogador.sendMessage(ChatColor.AQUA + "You received the Ryu kit!");
		              Coins.perms.playerAdd(jogador, "kitpvp.kit.ryu");
		          }

		          else if (gg2 == 13) {
		              random.setItem(4, TIME);
		              jogador.sendMessage(ChatColor.AQUA + "You received the Timelord kit!");
		              Coins.perms.playerAdd(jogador, "kitpvp.kit.timelord");
		          }

		          else if (gg2 == 14) {
		              random.setItem(4, BS);
		              jogador.sendMessage(ChatColor.AQUA + "You received the Berserker kit!");
		              Coins.perms.playerAdd(jogador, "kitpvp.kit.berserker");
		          }

		          else if (gg2 == 15) {
		              random.setItem(4, ST);
		              jogador.sendMessage(ChatColor.AQUA + "You received the Stomper kit!");
		              Coins.perms.playerAdd(jogador, "kitpvp.kit.stomper");
		          }

		          else {
		              random.setItem(4, ph);
		              jogador.sendMessage(ChatColor.AQUA + "You received the Phantom kit!");
		              Coins.perms.playerAdd(jogador, "kitpvp.kit.phantom");
		          }
		      }

		  }, 10L, 10L);

		  Main.getFolia().getScheduler().runLater(task -> {

		      if (jogador.isOnline()) {
		          jogador.closeInventory();
		      }

		      cooldown.remove(jogador.getName());

		  }, 300L);

		  jogador.openInventory(random);	
	}

		

@SuppressWarnings("deprecation")
@EventHandler
public void rafaelonBauKit(PlayerInteractEvent e)
{
  Player p = e.getPlayer();
  if ( p.getItemInHand().getItemMeta() == null) {
	  return;
  }
  if (!Habilidade.ContainsAbility(p) && Join.game.contains(p.getName()) && p.getItemInHand().getItemMeta().hasDisplayName() && p.getItemInHand().getItemMeta().getDisplayName().equals("§bKit Unlocker"))
  {
    e.setCancelled(true);
    if ((e.getAction() == Action.RIGHT_CLICK_AIR) || (e.getAction() == Action.RIGHT_CLICK_BLOCK))
    {
    	if (cooldown.containsKey(p.getName())) {
			p.sendMessage("You are already unlocking a kit!");
			return;
		}
      CrateInventory.Inventario(p); 
      
      for (ItemStack item : p.getInventory().getContents()) {
          if (item != null && item.getType() == Material.CHEST) {
              // Found the item, now remove one from its stack
              if (item.getAmount() > 1) {
                  item.setAmount(item.getAmount() - 1);
              } else {
                  // If only one item in the stack, set the slot to null
            	  p.getInventory().removeItem(item); // Or set the specific slot to null if you know the index
              }
              p.updateInventory(); // Update the player's client-side inventory
              break; // Stop after removing one
          }
      }
        int y = settings.getData().getInt("crates." + p.getName() + ".amount");
        y--;
  	    settings.getData().set("crates." + p.getName() + ".amount", y);
  	  settings.saveData();
      p.playSound(p.getLocation(), Sound.valueOf(Main.getInstance().getConfig().getString("Sound.ShopMenu")), 12.0F, 1.0F);
    }
  
  }}
}
