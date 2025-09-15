/*     */ package me.RafaelAulerDeMeloAraujo.main;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;

import org.bukkit.ChatColor;
/*     */ import org.bukkit.Color;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.command.Command;
/*     */ import org.bukkit.command.CommandSender;
/*     */ 
/*     */ import org.bukkit.enchantments.Enchantment;
/*     */ import org.bukkit.entity.Player;
/*     */ 
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ 
/*     */ import org.bukkit.inventory.meta.ItemMeta;
/*     */ import org.bukkit.inventory.meta.LeatherArmorMeta;
/*     */ 
/*     */ import org.bukkit.potion.PotionEffect;
/*     */ import org.bukkit.potion.PotionEffectType;

import me.RafaelAulerDeMeloAraujo.SpecialAbility.API;
import me.RafaelAulerDeMeloAraujo.SpecialAbility.Cooldown;
/*     */ import me.RafaelAulerDeMeloAraujo.SpecialAbility.Deshfire;
/*     */ import me.RafaelAulerDeMeloAraujo.SpecialAbility.Gladiator;
/*     */ import me.RafaelAulerDeMeloAraujo.SpecialAbility.Habilidade;
/*     */ import me.RafaelAulerDeMeloAraujo.SpecialAbility.Join;
import me.RafaelAulerDeMeloAraujo.SpecialAbility.RTP;
/*     */ import me.RafaelAulerDeMeloAraujo.TitleAPI.TitleAPI;
/*     */ 
/*     */ public class Kits implements org.bukkit.command.CommandExecutor
/*     */ {
/*  35 */   List<Enchantment[]> enchantsList = new ArrayList<Enchantment[]>();
/*     */   private Main main;
/*     */   static Main plugin;
/*     */   
/*     */   public Kits(Main main) {
/*  40 */     this.main = main;
/*  41 */     plugin = main;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
/*     */   {
/*  47 */     ItemStack sopa = new ItemStack(Material.MUSHROOM_STEW);
/*  48 */     ItemMeta sopas = sopa.getItemMeta();
/*  49 */     sopas.setDisplayName("§6Soup");
/*  50 */     sopa.setItemMeta(sopas);
/*  51 */     if (!(sender instanceof Player)) {
/*  52 */       sender.sendMessage("[KitPvP] This command is only for players");
/*  53 */       return true;
/*     */     }
/*  55 */     Player s = (Player)sender;
/*  56 */     Player p = (Player)sender;
/*  57 */     double x; if (cmd.getName().equalsIgnoreCase("kitclear")) {
	/*  51 */     if (!(sender instanceof Player)) {
		/*  52 */       sender.sendMessage("§e[KitPvP] §cThis command is only for players");
		/*  53 */       return true;
		/*     */     }
/*  58 */       if (!Join.game.contains(p.getName()))
/*     */       {
/*  60 */         p.sendMessage(String.valueOf("§cYou are not in kitpvp to do this!"));
/*  61 */         return true;
/*     */       }
/*     */       
/*  64 */       if (!sender.hasPermission("kitpvp.kitclear")) {
/*  65 */         p.sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§")) + this.main.getConfig().getString("Permission").replace("&", "§").replaceAll("%permisson%", commandLabel));
/*  66 */        
/*  67 */         return true;
/*     */       }
/*  69 */       for (PotionEffect effect : s.getActivePotionEffects()) {
/*  70 */         s.removePotionEffect(effect.getType());
/*     */       }
Cooldown.remove(p);
/*  72 */       p.sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§")) + this.main.getConfig().getString("Message.Clear").replace("&", "§"));
/*  73 */       p.getInventory().clear();
/*  74 */       s.getInventory().setHelmet(new ItemStack(Material.AIR));
/*  75 */       s.getInventory().setChestplate(new ItemStack(Material.AIR));
/*  76 */       Habilidade.removeAbility(p);
/*  77 */       me.RafaelAulerDeMeloAraujo.SpecialAbility.Cooldown.remove(p);
/*  78 */       Deshfire.cooldownm.remove(p);
/*  79 */       Deshfire.armadura.remove(p.getName());
/*  80 */       Gladiator.lutando.remove(p.getName());
/*  81 */       Gladiator.gladgladiator.remove(p.getName());
/*  82 */       org.bukkit.World w = org.bukkit.Bukkit.getServer().getWorld(Main.plugin.getConfig().getString("Spawn.World"));
/*  83 */       x = Main.plugin.getConfig().getDouble("Spawn.X");
/*  84 */       double y = Main.plugin.getConfig().getDouble("Spawn.Y");
/*  85 */       double z = Main.plugin.getConfig().getDouble("Spawn.Z");
/*  86 */       Location lobby = new Location(w, x, y, z);
/*  87 */       lobby.setPitch((float)Main.plugin.getConfig().getDouble("Spawn.Pitch"));
/*  88 */       lobby.setYaw((float)Main.plugin.getConfig().getDouble("Spawn.Yaw"));
/*  89 */       p.getInventory().clear();
/*  90 */       p.teleport(lobby);
/*     */       
/*  92 */       s.getInventory().setLeggings(new ItemStack(Material.AIR));
/*  93 */       s.getInventory().setBoots(new ItemStack(Material.AIR));
p.getInventory().setArmorContents(null);
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
ItemStack sair = Main.getInstance().getConfig().getItemStack("LeaveItem");
/* 227 */           ItemMeta sair2 = sair.getItemMeta();
/* 228 */           sair2.setDisplayName(Main.messages.getString("LeaveItemName").replace("&", "§"));
/* 229 */           sair.setItemMeta(sair2);
if (!Main.getInstance().getConfig().getBoolean("DisableLeaveItem")) {
	p.getInventory().setItem(Main.getInstance().getConfig().getInt("LeaveItemSlot"), sair);
}
if (!Main.getInstance().getConfig().getBoolean("DisableWarpItem")) {
	p.getInventory().setItem(Main.getInstance().getConfig().getInt("WarpItemSlot"), warp);
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
/*     */       
/*     */ 
/* 107 */       p.updateInventory();
/*     */       
/*     */ 
GiveKitUnlocker.GiveUnlockers(p);
/* 107 */       p.updateInventory();
/*     */        p.setAllowFlight(false);
/*     */ 
/* 107 */       p.updateInventory();
/*     */     }
/*     */     
/*     */ 
/*     */     ItemStack colete1;
/*     */     
/* 113 */     if (cmd.getName().equalsIgnoreCase("kpvp")) {
	if (Main.kits.getBoolean("PvPDisabled")) {
		p.sendMessage(API.NomeServer + ChatColor.RED + "The PvP kit is disabled, sorry");
		return true;
	}
/* 114 */       if (!sender.hasPermission("kitpvp.kit.pvp")) {
/* 115 */         sender.sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§")) + this.main.getConfig().getString("Permission").replace("&", "§").replaceAll("%permisson%", commandLabel));
/* 116 */        
/* 117 */         return true;
/*     */       }
/* 119 */       if (Habilidade.ContainsAbility(p)) {
/* 120 */         s.sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§")) + this.main.getConfig().getString("Message.KitUse").replace("&", "§"));
/* 121 */         
/* 122 */         return true;
/*     */       }
/* 124 */       if (!Join.game.contains(p.getName()))
/*     */       {
/* 126 */         s.sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§")) + " §eYou are not in kitpvp to choose this kit!");
/* 127 */         return true;
/*     */       }
/* 129 */       s.sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§")) + this.main.getConfig().getString("Message.Kit").replaceAll("%kit%", "PvP").replace("&", "§"));
/* 130 */       s.getInventory().clear();
/* 131 */      API.vida(s);
/* 132 */       s.setFoodLevel(20);
/* 133 */       for (PotionEffect effect : s.getActivePotionEffects()) {
/* 134 */         s.removePotionEffect(effect.getType());
/*     */       }
/* 136 */       ItemStack sword = new ItemStack(Material.DIAMOND_SWORD);
/* 137 */       sword.addEnchantment(Enchantment.SHARPNESS, 1);
/* 138 */       ItemStack colete = new ItemStack(Material.IRON_CHESTPLATE);
/* 139 */       colete.addEnchantment(Enchantment.PROTECTION, 1);
/* 140 */       colete.addEnchantment(Enchantment.UNBREAKING, 3);
/* 141 */       colete1 = new ItemStack(Material.IRON_HELMET);
/* 142 */       colete1.addEnchantment(Enchantment.PROTECTION, 1);
/* 143 */       colete1.addEnchantment(Enchantment.UNBREAKING, 3);
/* 144 */       Habilidade.setAbility(s, "PvP");
/* 145 */       ItemStack calcinhasuja = new ItemStack(Material.IRON_LEGGINGS);
                API.give(p);
                calcinhasuja.addEnchantment(Enchantment.UNBREAKING, 3);
                calcinhasuja.addEnchantment(Enchantment.PROTECTION, 1);
                RTP.TeleportArenaRandom(p);
/* 148 */       ItemStack bota = new ItemStack(Material.IRON_BOOTS);
/* 150 */       bota.addEnchantment(Enchantment.UNBREAKING, 3);
/* 151 */       s.getInventory().addItem(new ItemStack[] { sword });
/* 152 */       for (int i = 0; i <= 34; i++) {
/* 153 */         s.getInventory().addItem(new ItemStack[] { sopa });
/* 154 */         TitleAPI.sendTitle(p, Integer.valueOf(20), Integer.valueOf(60), Integer.valueOf(20), this.main.getConfig().getString("Title.KitTitle"), this.main.getConfig().getString("Title.KitSubTitle").replaceAll("%kit%", "PvP"));
/*     */       }
/*     */       
/* 157 */       s.getInventory().setHelmet(new ItemStack(colete1));
/* 158 */       s.getInventory().setChestplate(new ItemStack(colete));
/* 159 */       s.getInventory().setLeggings(new ItemStack(calcinhasuja));
/* 160 */       s.getInventory().setBoots(new ItemStack(bota));
/*     */       
/* 162 */      
/*     */       
/* 164 */       return true;
/*     */     }
/* 113 */     if (cmd.getName().equalsIgnoreCase("kviking")) {
/* 114 */       if (!sender.hasPermission("kitpvp.kit.viking")) {
/* 115 */         sender.sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§")) + this.main.getConfig().getString("Permission").replace("&", "§").replaceAll("%permisson%", commandLabel));
/* 116 */        
/* 117 */         return true;
/*     */       }
/* 119 */       if (Habilidade.ContainsAbility(p)) {
/* 120 */         s.sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§")) + this.main.getConfig().getString("Message.KitUse").replace("&", "§"));
/* 121 */         
/* 122 */         return true;
/*     */       }
/* 124 */       if (!Join.game.contains(p.getName()))
/*     */       {
/* 126 */         s.sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§")) + " §eYou are not in kitpvp to choose this kit!");
/* 127 */         return true;
/*     */       }
/* 129 */       s.sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§")) + this.main.getConfig().getString("Message.Kit").replaceAll("%kit%", "Viking").replace("&", "§"));
/* 130 */       s.getInventory().clear();
/* 131 */      API.vida(s);
/* 132 */       s.setFoodLevel(20);
/* 133 */       for (PotionEffect effect : s.getActivePotionEffects()) {
/* 134 */         s.removePotionEffect(effect.getType());
/*     */       }
/* 136 */       ItemStack sword = new ItemStack(Material.DIAMOND_AXE);
/* 137 */       sword.addEnchantment(Enchantment.SHARPNESS, 1);
/* 138 */       ItemStack colete = new ItemStack(Material.IRON_CHESTPLATE);
/* 139 */       colete.addEnchantment(Enchantment.PROTECTION, 1);
/* 140 */       colete.addEnchantment(Enchantment.UNBREAKING, 3);
/* 141 */       colete1 = new ItemStack(Material.IRON_HELMET);
/* 142 */       colete1.addEnchantment(Enchantment.PROTECTION, 1);
/* 143 */       colete1.addEnchantment(Enchantment.UNBREAKING, 3);
/* 144 */       Habilidade.setAbility(s, "Viking");
/* 145 */       ItemStack calcinhasuja = new ItemStack(Material.IRON_LEGGINGS);
/*     */       
/* 147 */       calcinhasuja.addEnchantment(Enchantment.UNBREAKING, 3);
                calcinhasuja.addEnchantment(Enchantment.PROTECTION, 1);
/* 148 */       ItemStack bota = new ItemStack(Material.IRON_BOOTS);
/*     */       API.give(p);
RTP.TeleportArenaRandom(p);
/* 150 */       bota.addEnchantment(Enchantment.UNBREAKING, 3);
/* 151 */       s.getInventory().addItem(new ItemStack[] { sword });
/* 152 */       for (int i = 0; i <= 34; i++) {
/* 153 */         s.getInventory().addItem(new ItemStack[] { sopa });
/* 154 */         TitleAPI.sendTitle(p, Integer.valueOf(20), Integer.valueOf(60), Integer.valueOf(20), this.main.getConfig().getString("Title.KitTitle"), this.main.getConfig().getString("Title.KitSubTitle").replaceAll("%kit%", "Viking"));
/*     */       }
/*     */       
/* 157 */       s.getInventory().setHelmet(new ItemStack(colete1));
/* 158 */       s.getInventory().setChestplate(new ItemStack(colete));
/* 159 */       s.getInventory().setLeggings(new ItemStack(calcinhasuja));
/* 160 */       s.getInventory().setBoots(new ItemStack(bota));
/*     */       
/* 162 */      
/*     */       
/* 164 */       return true;
/*     */     }
/* 166 */     if (cmd.getName().equalsIgnoreCase("warper")) {
/* 167 */       if (!sender.hasPermission("kitpvp.kit.warper")) {
/* 168 */         sender.sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§")) + this.main.getConfig().getString("Permission").replace("&", "§").replaceAll("%permisson%", commandLabel));
/* 169 */        
/* 170 */         return true;
/*     */       }
/* 172 */       if (Habilidade.ContainsAbility(p)) {
/* 173 */         s.sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§")) + this.main.getConfig().getString("Message.KitUse").replace("&", "§"));
/* 174 */         
/* 175 */         return true;
/*     */       }
/* 177 */       if (!Join.game.contains(p.getName()))
/*     */       {
/* 179 */         p.sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§")) + " §eYou are not in kitpvp to do choose this kit!");
/* 180 */         return true;
/*     */       }
if (Main.kits.getBoolean("WarperDisabled")) {
	     p.sendMessage(API.NomeServer + ChatColor.RED + "The Warper kit is disabled, sorry");
	     return true;
 }
/* 182 */       s.sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§")) + this.main.getConfig().getString("Message.Kit").replaceAll("%kit%", "Warper").replace("&", "§"));
/* 183 */       s.getInventory().clear();
/* 184 */      API.vida(s);
/* 185 */       s.setFoodLevel(20);
/* 186 */       Habilidade.setAbility(s, "Warper");
/* 187 */       for (PotionEffect effect : s.getActivePotionEffects()) {
/* 188 */         s.removePotionEffect(effect.getType());
/*     */       }
/* 190 */       ItemStack sword = new ItemStack(Material.DIAMOND_SWORD);
/* 191 */       s.getInventory().addItem(new ItemStack[] { sword });
/* 192 */       s.getInventory().addItem(new ItemStack[] { new ItemStack(Material.ENDER_PEARL, 10) });
/* 193 */       for (int i = 0; i <= 34; i++) {
/* 194 */         s.getInventory().addItem(new ItemStack[] { sopa });
/* 195 */         TitleAPI.sendTitle(p, Integer.valueOf(20), Integer.valueOf(60), Integer.valueOf(20), this.main.getConfig().getString("Title.KitTitle"), this.main.getConfig().getString("Title.KitSubTitle").replaceAll("%kit%", "Warper"));
/*     */       }
RTP.TeleportArenaRandom(p);
/* 197 */       ItemStack tunic = new ItemStack(Material.LEATHER_CHESTPLATE);
/* 198 */       tunic.addUnsafeEnchantment(Enchantment.UNBREAKING, 150);
/* 199 */       Object meta = (LeatherArmorMeta)tunic.getItemMeta();
/* 200 */       ((LeatherArmorMeta)meta).setColor(Color.BLUE);
/* 201 */       tunic.setItemMeta((ItemMeta)meta);
/* 202 */       s.getInventory().setHelmet(new ItemStack(Material.IRON_HELMET));
/* 203 */       s.getEquipment().setChestplate(tunic);
/* 204 */       s.getInventory().setLeggings(new ItemStack(Material.IRON_LEGGINGS));
/* 205 */       s.getInventory().setBoots(new ItemStack(Material.IRON_BOOTS));
/* 206 */      
/*     */       API.give(p);
/* 208 */       return true;
/*     */     }
/* 210 */     if (cmd.getName().equalsIgnoreCase("Switcher")) {
/* 211 */       if (!sender.hasPermission("kitpvp.kit.switcher")) {
/* 212 */         sender.sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§")) + this.main.getConfig().getString("Permission").replace("&", "§").replaceAll("%permisson%", commandLabel));
/* 213 */        
/* 214 */         return true;
/*     */       }
if (Main.kits.getBoolean("SwitcherDisabled")) {
	p.sendMessage(API.NomeServer + ChatColor.RED + "The Switcher kit is disabled, sorry");
	return true;
}
/* 216 */       if (!Join.game.contains(p.getName()))
/*     */       {
/* 218 */         p.sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§")) + " §eYou are not in kitpvp to do choose this kit!");
/* 219 */         return true;
/*     */       }
/* 221 */       if (Habilidade.ContainsAbility(p)) {
/* 222 */         s.sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§")) + this.main.getConfig().getString("Message.KitUse").replace("&", "§"));
/* 223 */         
/* 224 */         return true;
/*     */       }
/* 226 */       s.sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§")) + this.main.getConfig().getString("Message.Kit").replaceAll("%kit%", "Switcher").replace("&", "§"));
/* 227 */       s.getInventory().clear();
/* 228 */      API.vida(s);
/* 229 */       s.setFoodLevel(20);
/* 230 */       Habilidade.setAbility(s, "Switcher");

/* 231 */       TitleAPI.sendTitle(p, Integer.valueOf(20), Integer.valueOf(60), Integer.valueOf(20), this.main.getConfig().getString("Title.KitTitle"), this.main.getConfig().getString("Title.KitSubTitle").replaceAll("%kit%", "Switcher"));
/* 232 */       for (Object meta1 = s.getActivePotionEffects().iterator(); ((Iterator)meta1).hasNext();) { PotionEffect effect = (PotionEffect)((Iterator)meta1).next();
/* 233 */         s.removePotionEffect(effect.getType());
/*     */       }
/* 235 */       s.getInventory().addItem(new ItemStack[] { new ItemStack(Material.DIAMOND_SWORD) });
/* 236 */       s.getInventory().addItem(new ItemStack[] { new ItemStack(Material.SNOWBALL, 1) });
/* 237 */       for (int i = 0; i <= 34; i++) {
/* 238 */         s.getInventory().addItem(new ItemStack[] { sopa });
/*     */       }
/*     */       
/* 241 */       ItemStack tunic = new ItemStack(Material.LEATHER_HELMET);
/* 242 */       tunic.addUnsafeEnchantment(Enchantment.UNBREAKING, 150);
/* 243 */       LeatherArmorMeta meta1 = (LeatherArmorMeta)tunic.getItemMeta();
/* 244 */       ((LeatherArmorMeta)meta1).setColor(Color.WHITE);
/* 245 */       tunic.setItemMeta((ItemMeta)meta1);
/* 246 */       s.getEquipment().setHelmet(tunic);
/* 247 */       s.getInventory().setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
/* 248 */       s.getInventory().setLeggings(new ItemStack(Material.IRON_LEGGINGS));
/* 249 */       s.getInventory().setBoots(new ItemStack(Material.IRON_BOOTS));
/* 250 */      
/*     */       API.give(p);
RTP.TeleportArenaRandom(p);
/* 252 */       return true;
/*     */     }
/* 254 */     if (cmd.getName().equalsIgnoreCase("archer")) {
/* 255 */       if (!sender.hasPermission("kitpvp.kit.archer")) {
/* 256 */         sender.sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§")) + this.main.getConfig().getString("Permission").replace("&", "§").replaceAll("%permisson%", commandLabel));
/* 257 */        
/* 258 */         return true;
/*     */       }
/* 260 */       if (Habilidade.ContainsAbility(p)) {
/* 261 */         s.sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§")) + this.main.getConfig().getString("Message.KitUse").replace("&", "§"));
/* 262 */         
/* 263 */         return true;
/*     */       }
/* 265 */       if (!Join.game.contains(p.getName()))
/*     */       {
/* 267 */         p.sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§")) + " §eYou are not in kitpvp to do choose this kit!");
/* 268 */         return true;
/*     */       }
/* 270 */       s.sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§")) + this.main.getConfig().getString("Message.Kit").replaceAll("%kit%", "Archer").replace("&", "§"));
/* 271 */       s.getInventory().clear();
/* 272 */      API.vida(s);
/* 273 */       Habilidade.setAbility(s, "Archer");
/* 274 */       TitleAPI.sendTitle(p, Integer.valueOf(20), Integer.valueOf(60), Integer.valueOf(20), this.main.getConfig().getString("Title.KitTitle"), this.main.getConfig().getString("Title.KitSubTitle").replaceAll("%kit%", "Archer"));
/* 275 */       s.setFoodLevel(20);
/* 276 */       for (Object meta1 = s.getActivePotionEffects().iterator(); ((Iterator)meta1).hasNext();) { PotionEffect effect = (PotionEffect)((Iterator)meta1).next();
/* 277 */         s.removePotionEffect(effect.getType());
/*     */       }
/* 279 */       ItemStack sword2 = new ItemStack(Material.STONE_SWORD);
/* 280 */       ItemStack flecha = new ItemStack(Material.ARROW);
/* 281 */       sword2.addEnchantment(Enchantment.KNOCKBACK, 2);
/* 282 */       sword2.addEnchantment(Enchantment.SHARPNESS, 2);
/* 283 */       Object bow = new ItemStack(Material.BOW);
/* 284 */       ((ItemStack)bow).addEnchantment(Enchantment.POWER, 2);
/* 285 */       ((ItemStack)bow).addEnchantment(Enchantment.INFINITY, 1);
/* 286 */       ((ItemStack)bow).addEnchantment(Enchantment.PUNCH, 1);
/* 287 */       s.getInventory().addItem(new ItemStack[] { sword2 });
/* 288 */       s.getInventory().addItem(new ItemStack[] { (ItemStack)bow });
/* 289 */       s.getInventory().addItem(new ItemStack[] { flecha });
/* 290 */       for (int i = 0; i <= 33; i++) {
/* 291 */         s.getInventory().addItem(new ItemStack[] { sopa });
/*     */       }
/*     */       
/*     */ API.give(p);
RTP.TeleportArenaRandom(p);
/* 295 */       s.getInventory().addItem(new ItemStack[] { new ItemStack(Material.ARROW, 64) });
/* 296 */       s.getInventory().setHelmet(new ItemStack(Material.IRON_HELMET));
/* 297 */       ItemStack Peitoral = new ItemStack(Material.CHAINMAIL_CHESTPLATE);/* 298 */      
/* 301 */       s.getInventory().setLeggings(new ItemStack(Material.IRON_LEGGINGS));
/* 302 */       s.getInventory().setBoots(new ItemStack(Material.IRON_BOOTS));
/* 303 */       s.getInventory().setChestplate(Peitoral);
/* 304 */      
/*     */       
/* 306 */       return true;
/*     */     }
/* 308 */     if (cmd.getName().equalsIgnoreCase("pyro")) {
/* 309 */       if (!sender.hasPermission("kitpvp.kit.pyro")) {
/* 310 */         sender.sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§")) + this.main.getConfig().getString("Permission").replace("&", "§").replaceAll("%permisson%", commandLabel));
/* 311 */        
/* 312 */         return true;
/*     */       }
/* 314 */       if (!Join.game.contains(p.getName()))
/*     */       {
/* 316 */         p.sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§")) + " §eYou are not in kitpvp to do choose this kit!");
/* 317 */         return true;
/*     */       }
/* 319 */       if (Habilidade.ContainsAbility(p)) {
/* 320 */         s.sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§")) + this.main.getConfig().getString("Message.KitUse").replace("&", "§"));
/* 321 */         
/* 322 */         return true;
/*     */       }
/* 324 */       s.sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§")) + this.main.getConfig().getString("Message.Kit").replaceAll("%kit%", "Pyro").replace("&", "§"));
/* 325 */       s.getInventory().clear();
/* 326 */      API.vida(s);
/* 327 */       s.setFoodLevel(20);
/* 328 */       for (Object bow = s.getActivePotionEffects().iterator(); ((Iterator)bow).hasNext();) { PotionEffect effect = (PotionEffect)((Iterator)bow).next();
/* 329 */         s.removePotionEffect(effect.getType());
/*     */       }
/* 331 */       ItemStack sword3 = new ItemStack(Material.STONE_SWORD);
/* 332 */       sword3.addEnchantment(Enchantment.KNOCKBACK, 2);
/* 333 */       sword3.addEnchantment(Enchantment.SHARPNESS, 1);
/* 334 */       ItemStack flecha = new ItemStack(Material.ARROW, 64);
/* 335 */       sword3.addEnchantment(Enchantment.FIRE_ASPECT, 1);
/* 336 */       Object bow1 = new ItemStack(Material.BOW);
/* 337 */       ((ItemStack)bow1).addEnchantment(Enchantment.POWER, 1);
((ItemStack)bow1).addEnchantment(Enchantment.PUNCH, 1);
/* 338 */       ((ItemStack)bow1).addEnchantment(Enchantment.FLAME, 1);
/* 339 */       s.getInventory().addItem(new ItemStack[] { sword3 });
/* 340 */       TitleAPI.sendTitle(p, Integer.valueOf(20), Integer.valueOf(60), Integer.valueOf(20), this.main.getConfig().getString("Title.KitTitle"), this.main.getConfig().getString("Title.KitSubTitle").replaceAll("%kit%", "Pyro"));
/* 341 */       s.getInventory().addItem(new ItemStack[] { (ItemStack)bow1 });
/* 342 */       s.getInventory().addItem(new ItemStack[] { flecha });
/* 343 */       for (int i = 0; i <= 33; i++) {
/* 344 */         s.getInventory().addItem(new ItemStack[] { sopa });
/*     */       }
/*     */       API.give(p);
RTP.TeleportArenaRandom(p);
/* 347 */       s.getInventory().addItem(new ItemStack[] { new ItemStack(Material.ARROW, 64) });
/* 348 */       ItemStack tunic = new ItemStack(Material.LEATHER_HELMET);
/* 349 */       tunic.addUnsafeEnchantment(Enchantment.UNBREAKING, 150);
/* 350 */       LeatherArmorMeta meta11 = (LeatherArmorMeta)tunic.getItemMeta();
/* 351 */       meta11.setColor(Color.YELLOW);
/* 352 */       tunic.setItemMeta(meta11);
/* 353 */       Habilidade.setAbility(s, "Pyro");
/* 354 */       s.getEquipment().setHelmet(tunic);
/* 355 */       s.getInventory().setChestplate(new ItemStack(Material.CHAINMAIL_CHESTPLATE));
/* 356 */       s.getInventory().setLeggings(new ItemStack(Material.CHAINMAIL_LEGGINGS));
/* 357 */       s.getInventory().setBoots(new ItemStack(Material.CHAINMAIL_BOOTS));
/* 358 */      
/*     */       
/* 360 */       return true;
/*     */     }
/* 362 */     if (cmd.getName().equalsIgnoreCase("Tank")) {
/* 363 */       if (!sender.hasPermission("kitpvp.kit.tank")) {
/* 364 */         sender.sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§")) + this.main.getConfig().getString("Permission").replace("&", "§").replaceAll("%permisson%", commandLabel));
/* 365 */        
/* 366 */         return true;
/*     */       }
/* 368 */       if (!Join.game.contains(p.getName()))
/*     */       {
/* 370 */         p.sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§")) + " §eYou are not in kitpvp to do choose this kit!");
/* 371 */         return true;
/*     */       }
/* 373 */       if (Habilidade.ContainsAbility(p)) {
/* 374 */         s.sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§")) + this.main.getConfig().getString("Message.KitUse").replace("&", "§"));
/* 375 */         
/* 376 */         return true;
/*     */       }
/* 378 */       if (!Join.game.contains(p.getName()))
/*     */       {
/* 380 */         p.sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§")) + " §eYou are not in kitpvp to do choose this kit!");
/* 381 */         return true;
/*     */       }
/* 383 */       s.sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§")) + this.main.getConfig().getString("Message.Kit").replaceAll("%kit%", "Tank").replace("&", "§"));
/* 384 */       s.getInventory().clear();
/* 385 */      API.vida(s);
/* 386 */       s.setFoodLevel(20);
/* 387 */       for (Object bow1 = s.getActivePotionEffects().iterator(); ((Iterator)bow1).hasNext();) { PotionEffect effect = (PotionEffect)((Iterator)bow1).next();
/* 388 */         s.removePotionEffect(effect.getType());
/*     */       }
/* 390 */       ItemStack sword4 = new ItemStack(Material.IRON_SWORD);
/* 391 */       sword4.addUnsafeEnchantment(Enchantment.UNBREAKING, 150);
/* 392 */       TitleAPI.sendTitle(p, Integer.valueOf(20), Integer.valueOf(60), Integer.valueOf(20), this.main.getConfig().getString("Title.KitTitle"), this.main.getConfig().getString("Title.KitSubTitle").replaceAll("%kit%", "Tank"));
/* 393 */       s.getInventory().addItem(new ItemStack[] { sword4 });
/* 394 */       for (int i = 0; i <= 34; i++) {
/* 395 */         s.getInventory().addItem(new ItemStack[] { sopa });
/*     */       }
/*     */       API.give(p);
RTP.TeleportArenaRandom(p);
/* 398 */       s.getInventory().setHelmet(new ItemStack(Material.DIAMOND_HELMET));
/* 399 */       s.getInventory().setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE));
/* 400 */       s.getInventory().setLeggings(new ItemStack(Material.DIAMOND_LEGGINGS));
/* 401 */       s.getInventory().setBoots(new ItemStack(Material.DIAMOND_BOOTS));
/* 402 */       Habilidade.setAbility(s, "Tank");
/* 403 */       s.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, 999999999, 2));
/* 404 */      
/*     */       
/* 406 */       return true;
/*     */     }
/* 408 */     if (cmd.getName().equalsIgnoreCase("jumper")) {
/* 409 */       if (!sender.hasPermission("kitpvp.kit.jumper")) {
/* 410 */         sender.sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§")) + this.main.getConfig().getString("Permission").replace("&", "§").replaceAll("%permisson%", commandLabel));
/* 411 */        
/* 412 */         return true;
/*     */       }
/* 414 */       if (!Join.game.contains(p.getName()))
/*     */       {
/* 416 */         p.sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§")) + " §eYou are not in kitpvp to do choose this kit!");
/* 417 */         return true;
/*     */       }
/* 419 */       if (Habilidade.ContainsAbility(p)) {
/* 420 */         s.sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§")) + this.main.getConfig().getString("Message.KitUse").replace("&", "§"));
/* 421 */         
/* 422 */         return true;
/*     */       }
/* 424 */       s.sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§")) + this.main.getConfig().getString("Message.Kit").replaceAll("%kit%", "Jumper").replace("&", "§"));
/* 425 */       s.getInventory().clear();
/* 426 */      API.vida(s);
/* 427 */       s.setFoodLevel(20);
/* 428 */       for (Object bow1 = s.getActivePotionEffects().iterator(); ((Iterator)bow1).hasNext();) { PotionEffect effect = (PotionEffect)((Iterator)bow1).next();
/* 429 */         s.removePotionEffect(effect.getType());
/*     */       }
/* 431 */       TitleAPI.sendTitle(p, Integer.valueOf(20), Integer.valueOf(60), Integer.valueOf(20), this.main.getConfig().getString("Title.KitTitle"), this.main.getConfig().getString("Title.KitSubTitle").replaceAll("%kit%", "Jumper"));
/* 432 */       ItemStack sword5 = new ItemStack(Material.DIAMOND_SWORD);
/* 433 */       sword5.addEnchantment(Enchantment.SHARPNESS, 1);
/* 434 */       s.getInventory().addItem(new ItemStack[] { sword5 });
/* 435 */       for (int i = 0; i <= 34; i++) {
/* 436 */         s.getInventory().addItem(new ItemStack[] { sopa });
/*     */       }
/*     */       API.give(p);
RTP.TeleportArenaRandom(p);
/* 439 */       Object helmet = new ItemStack(Material.IRON_HELMET);
/* 440 */       ((ItemStack)helmet).addUnsafeEnchantment(Enchantment.UNBREAKING, 150);
/* 441 */       ((ItemStack)helmet).addEnchantment(Enchantment.PROJECTILE_PROTECTION, 2);
/*     */       
/*     */ 
/* 444 */       s.getEquipment().setHelmet((ItemStack)helmet);
/* 445 */       ItemStack tunic1 = new ItemStack(Material.IRON_CHESTPLATE);
/* 446 */       tunic1.addUnsafeEnchantment(Enchantment.UNBREAKING, 150);
/* 447 */       tunic1.addEnchantment(Enchantment.PROJECTILE_PROTECTION, 2);
/*     */       
/*     */ 
/* 450 */       s.getEquipment().setChestplate(tunic1);
/* 451 */       ItemStack tunic11 = new ItemStack(Material.LEATHER_LEGGINGS);
/* 452 */       tunic11.addUnsafeEnchantment(Enchantment.UNBREAKING, 150);
/* 453 */       tunic11.addEnchantment(Enchantment.PROJECTILE_PROTECTION, 2);
/* 454 */       LeatherArmorMeta meta1111 = (LeatherArmorMeta)tunic11.getItemMeta();
/* 455 */       meta1111.setColor(Color.BLACK);
/* 456 */       tunic11.setItemMeta(meta1111);
/* 457 */       s.getEquipment().setLeggings(tunic11);
/* 458 */       ItemStack tunic111 = new ItemStack(Material.LEATHER_BOOTS);
/* 459 */       tunic111.addUnsafeEnchantment(Enchantment.UNBREAKING, 150);
/* 460 */       tunic111.addEnchantment(Enchantment.PROJECTILE_PROTECTION, 2);
tunic111.addEnchantment(Enchantment.FEATHER_FALLING, 4);
/* 461 */       LeatherArmorMeta meta11111 = (LeatherArmorMeta)tunic111.getItemMeta();
/* 462 */       meta11111.setColor(Color.YELLOW);
/* 463 */       Habilidade.setAbility(s, "Jumper");
/* 464 */       tunic111.setItemMeta(meta11111);
/* 465 */       s.getEquipment().setBoots(tunic111);
/* 466 */       s.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 999999999, 1));
/* 467 */       s.addPotionEffect(new PotionEffect(PotionEffectType.JUMP_BOOST, 999999999, 3));
/* 468 */      
/*     */       
/* 470 */       return true;
/*     */     }
/* 472 */     if (cmd.getName().equalsIgnoreCase("Cactus")) {
/* 473 */       if (!sender.hasPermission("kitpvp.kit.cactus")) {
/* 474 */         sender.sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§")) + this.main.getConfig().getString("Permission").replace("&", "§").replaceAll("%permisson%", commandLabel));
/* 475 */        
/* 476 */         return true;
/*     */       }
/* 478 */       if (!Join.game.contains(p.getName()))
/*     */       {
/* 480 */         p.sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§")) + " §eYou are not in kitpvp to do choose this kit!");
/* 481 */         return true;
/*     */       }
/* 483 */       if (Habilidade.ContainsAbility(p)) {
/* 484 */         s.sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§")) + this.main.getConfig().getString("Message.KitUse").replace("&", "§"));
/* 485 */         
/* 486 */         return true;
/*     */       }
/* 488 */       s.sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§")) + this.main.getConfig().getString("Message.Kit").replaceAll("%kit%", "Cactus").replace("&", "§"));
/* 489 */       s.getInventory().clear();
/* 490 */      API.vida(s);
/* 491 */       s.setFoodLevel(20);
/* 492 */       for (Object helmet1 = s.getActivePotionEffects().iterator(); ((Iterator)helmet1).hasNext();) { PotionEffect effect = (PotionEffect)((Iterator)helmet1).next();
/* 493 */         s.removePotionEffect(effect.getType());
/*     */       }
/* 495 */       ItemStack sword6 = new ItemStack(Material.DIAMOND_SWORD);
/* 496 */       s.getInventory().addItem(new ItemStack[] { sword6 });
/* 497 */       for (int i = 0; i <= 34; i++) {
/* 498 */         s.getInventory().addItem(new ItemStack[] { sopa });
/* 499 */         TitleAPI.sendTitle(p, Integer.valueOf(20), Integer.valueOf(60), Integer.valueOf(20), this.main.getConfig().getString("Title.KitTitle"), this.main.getConfig().getString("Title.KitSubTitle").replaceAll("%kit%", "Cactus"));
/*     */       }
/* 501 */       ItemStack helmet1 = new ItemStack(Material.IRON_HELMET);
/* 502 */       ((ItemStack)helmet1).addUnsafeEnchantment(Enchantment.UNBREAKING, 150);
/* 503 */       ((ItemStack)helmet1).addEnchantment(Enchantment.THORNS, 3);
/* 504 */       s.getEquipment().setHelmet((ItemStack)helmet1);
/* 505 */       ItemStack tunic1 = new ItemStack(Material.IRON_CHESTPLATE);
/* 506 */       tunic1.addEnchantment(Enchantment.THORNS, 3);
/* 507 */       tunic1.addUnsafeEnchantment(Enchantment.UNBREAKING, 150);
/* 508 */       s.getEquipment().setChestplate(tunic1);
/* 509 */       ItemStack tunic11 = new ItemStack(Material.LEATHER_LEGGINGS);
/* 510 */       tunic11.addEnchantment(Enchantment.THORNS, 3);
/* 511 */       tunic11.addUnsafeEnchantment(Enchantment.UNBREAKING, 150);
/* 512 */       s.getEquipment().setLeggings(tunic11);
/* 513 */       Habilidade.setAbility(s, "Cactus");
/* 514 */       ItemStack tunic111 = new ItemStack(Material.LEATHER_BOOTS);
/* 515 */       tunic111.addEnchantment(Enchantment.THORNS, 3);
/* 516 */       tunic111.addUnsafeEnchantment(Enchantment.UNBREAKING, 150);
/* 517 */       s.getEquipment().setBoots(tunic111);
/* 518 */      
/*     */       API.give(p);
RTP.TeleportArenaRandom(p);
/* 520 */       return true;
/*     */     }
/* 522 */     if (cmd.getName().equalsIgnoreCase("bomber")) {
/* 523 */       if (!sender.hasPermission("kitpvp.kit.bomber")) {
/* 524 */         sender.sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§")) + this.main.getConfig().getString("Permission").replace("&", "§").replaceAll("%permisson%", commandLabel));
/* 525 */        
/* 526 */         return true;
/*     */       }
/* 528 */       if (!Join.game.contains(p.getName()))
/*     */       {
/* 530 */         p.sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§")) + " §eYou are not in kitpvp to do choose this kit!");
/* 531 */         return true;
/*     */       }
/* 533 */       if (Habilidade.ContainsAbility(p)) {
/* 534 */         s.sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§")) + this.main.getConfig().getString("Message.KitUse").replace("&", "§"));
/* 535 */         
/* 536 */         return true;
/*     */       }
if (Main.kits.getBoolean("BomberDisabled")) {
	p.sendMessage(API.NomeServer + ChatColor.RED + "The Bomber kit is disabled, sorry");
	return true;
}
/* 538 */       s.sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§")) + this.main.getConfig().getString("Message.Kit").replaceAll("%kit%", "Bomber").replace("&", "§"));
/* 539 */       s.getInventory().clear();
/* 540 */      API.vida(s);
/* 541 */       s.setFoodLevel(20);
/* 542 */       for (Object helmet1 = s.getActivePotionEffects().iterator(); ((Iterator)helmet1).hasNext();) { PotionEffect effect = (PotionEffect)((Iterator)helmet1).next();
/* 543 */         s.removePotionEffect(effect.getType());
/*     */       }
/* 545 */       s.getInventory().addItem(new ItemStack[] { new ItemStack(Material.DIAMOND_SWORD) });
/* 546 */       s.getInventory().addItem(new ItemStack[] { new ItemStack(Material.TNT, 15) });
/* 547 */       for (int i = 0; i <= 34; i++) {
/* 548 */         s.getInventory().addItem(new ItemStack[] { sopa });
/*     */       }
TitleAPI.sendTitle(p, Integer.valueOf(5), Integer.valueOf(20), Integer.valueOf(5), this.main.getConfig().getString("Title.KitTitle"), this.main.getConfig().getString("Title.KitSubTitle").replace("%kit%", "Bomber"));
/* 551 */       ItemStack helmet11 = new ItemStack(Material.LEATHER_HELMET);
/* 552 */       helmet11.addUnsafeEnchantment(Enchantment.UNBREAKING, 150);
/* 553 */       helmet11.addEnchantment(Enchantment.PROJECTILE_PROTECTION, 2);
                helmet11.addEnchantment(Enchantment.PROTECTION, 2);
/* 554 */       Object meta11 = (LeatherArmorMeta)helmet11.getItemMeta();
/* 555 */       ((LeatherArmorMeta)meta11).setColor(Color.RED);
/* 556 */       helmet11.setItemMeta((ItemMeta)meta11);
/* 557 */       s.getEquipment().setHelmet(helmet11);
/* 558 */       ItemStack tunic1 = new ItemStack(Material.LEATHER_CHESTPLATE);
/* 559 */       tunic1.addUnsafeEnchantment(Enchantment.UNBREAKING, 150);
/* 560 */       tunic1.addEnchantment(Enchantment.PROJECTILE_PROTECTION, 2);
                tunic1.addEnchantment(Enchantment.PROTECTION, 2);
/* 561 */       LeatherArmorMeta meta111 = (LeatherArmorMeta)tunic1.getItemMeta();
/* 562 */       meta111.setColor(Color.WHITE);
/* 563 */       tunic1.setItemMeta(meta111);
/* 564 */       s.getEquipment().setChestplate(tunic1);
/* 565 */       ItemStack tunic11 = new ItemStack(Material.LEATHER_LEGGINGS);
/* 566 */       tunic11.addUnsafeEnchantment(Enchantment.UNBREAKING, 150);
/* 567 */       tunic11.addEnchantment(Enchantment.PROJECTILE_PROTECTION, 2);
                tunic11.addEnchantment(Enchantment.PROTECTION, 2);
/* 568 */       LeatherArmorMeta meta1111 = (LeatherArmorMeta)tunic11.getItemMeta();
/* 569 */       meta1111.setColor(Color.RED);
/* 570 */       tunic11.setItemMeta(meta1111);
/* 571 */       s.getEquipment().setLeggings(tunic11);
/* 572 */       ItemStack tunic111 = new ItemStack(Material.LEATHER_BOOTS);
/* 573 */       tunic111.addUnsafeEnchantment(Enchantment.UNBREAKING, 150);
/* 574 */       tunic111.addEnchantment(Enchantment.PROJECTILE_PROTECTION, 2);
                tunic111.addEnchantment(Enchantment.PROTECTION, 2);
/* 575 */       LeatherArmorMeta meta11111 = (LeatherArmorMeta)tunic111.getItemMeta();
/* 576 */       meta11111.setColor(Color.WHITE);
/* 577 */       tunic111.setItemMeta(meta11111);
/* 578 */       s.getEquipment().setBoots(tunic111);
/* 579 */       Habilidade.setAbility(s, "Bomber");
/* 580 */       s.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 999999999, 1));
/* 581 */      
/*     */       API.give(p);
RTP.TeleportArenaRandom(p);
/* 583 */       return true;
/*     */     }
/* 585 */     if (cmd.getName().equalsIgnoreCase("wasp")) {
/* 586 */       if (!sender.hasPermission("kitpvp.kit.wasp")) {
/* 587 */         sender.sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§")) + this.main.getConfig().getString("Permission").replace("&", "§").replaceAll("%permisson%", commandLabel));
/* 588 */        
/* 589 */         return true;
/*     */       }
/* 591 */       if (!Join.game.contains(p.getName()))
/*     */       {
/* 593 */         p.sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§")) + " §eYou are not in kitpvp to do choose this kit!");
/* 594 */         return true;
/*     */       }
/* 596 */       if (Habilidade.ContainsAbility(p)) {
/* 597 */         s.sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§")) + this.main.getConfig().getString("Message.KitUse").replace("&", "§"));
/* 598 */         
/* 599 */         return true;
/*     */       }
if (Main.kits.getBoolean("WaspDisabled")) {
	s.sendMessage(ChatColor.RED + "The wasp kit is disabled, sorry");
	return true;
}
/* 601 */       s.sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§")) + this.main.getConfig().getString("Message.Kit").replaceAll("%kit%", "Wasp").replace("&", "§"));
/* 602 */       s.getInventory().clear();
/* 603 */      API.vida(s);
/* 604 */       s.setFoodLevel(20);
/* 605 */       for (Object meta11 = s.getActivePotionEffects().iterator(); ((Iterator)meta11).hasNext();) { PotionEffect effect = (PotionEffect)((Iterator)meta11).next();
/* 606 */         s.removePotionEffect(effect.getType());
/*     */       }
/* 608 */       ItemStack sword7 = new ItemStack(Material.BLAZE_ROD);
/* 609 */       sword7.addUnsafeEnchantment(Enchantment.SHARPNESS, 8);
/* 610 */       s.getInventory().addItem(new ItemStack[] { sword7 });
/* 611 */       for (int i = 0; i <= 34; i++) {
/* 612 */         s.getInventory().addItem(new ItemStack[] { sopa });
/*     */       }
/*     */       
/* 615 */       Object helmet11 = new ItemStack(Material.LEATHER_HELMET);
/* 616 */       ((ItemStack)helmet11).addUnsafeEnchantment(Enchantment.UNBREAKING, 150);
/* 617 */       ((ItemStack)helmet11).addEnchantment(Enchantment.PROJECTILE_PROTECTION, 3);
                ((ItemStack)helmet11).addEnchantment(Enchantment.PROTECTION, 2);
/* 618 */       LeatherArmorMeta meta111 = (LeatherArmorMeta)((ItemStack)helmet11).getItemMeta();
/* 619 */       meta111.setColor(Color.BLACK);
/* 620 */       ((ItemStack)helmet11).setItemMeta(meta111);
/* 621 */       s.getEquipment().setHelmet((ItemStack)helmet11);
/* 622 */       ItemStack tunic1 = new ItemStack(Material.LEATHER_CHESTPLATE);
/* 623 */       tunic1.addUnsafeEnchantment(Enchantment.UNBREAKING, 150);
/* 624 */       tunic1.addEnchantment(Enchantment.PROJECTILE_PROTECTION, 3);
tunic1.addEnchantment(Enchantment.PROTECTION, 3);
/* 625 */       LeatherArmorMeta meta1111 = (LeatherArmorMeta)tunic1.getItemMeta();
/* 626 */       meta1111.setColor(Color.YELLOW);
/* 627 */       tunic1.setItemMeta(meta1111);
/* 628 */       s.getEquipment().setChestplate(tunic1);
/* 629 */       ItemStack tunic11 = new ItemStack(Material.LEATHER_LEGGINGS);
/* 630 */       tunic11.addUnsafeEnchantment(Enchantment.UNBREAKING, 150);
/* 631 */       tunic11.addEnchantment(Enchantment.PROJECTILE_PROTECTION, 2);
                tunic11.addEnchantment(Enchantment.PROTECTION, 1);
/* 632 */       LeatherArmorMeta meta11111 = (LeatherArmorMeta)tunic11.getItemMeta();
/* 633 */       meta11111.setColor(Color.BLACK);
/* 634 */       tunic11.setItemMeta(meta11111);
/* 635 */       s.getEquipment().setLeggings(tunic11);
/* 636 */       ItemStack tunic111 = new ItemStack(Material.LEATHER_BOOTS);
/* 637 */       tunic111.addUnsafeEnchantment(Enchantment.UNBREAKING, 150);
/* 638 */       tunic111.addEnchantment(Enchantment.PROJECTILE_PROTECTION, 2);
tunic111.addEnchantment(Enchantment.PROTECTION, 2);
/* 639 */       LeatherArmorMeta meta111111 = (LeatherArmorMeta)tunic111.getItemMeta();
/* 640 */       meta111111.setColor(Color.YELLOW);
/* 641 */       tunic111.setItemMeta(meta111111);
/* 642 */       s.getEquipment().setBoots(tunic111);
/* 643 */       Habilidade.setAbility(s, "Wasp");
API.give(p);
RTP.TeleportArenaRandom(p);
/* 644 */      
/* 645 */       TitleAPI.sendTitle(p, Integer.valueOf(20), Integer.valueOf(60), Integer.valueOf(20), this.main.getConfig().getString("Title.KitTitle"), this.main.getConfig().getString("Title.KitSubTitle").replace("%kit%", "Wasp"));
/* 646 */       return true;
/*     */     }
/* 648 */     if (cmd.getName().equalsIgnoreCase("spiderman")) {
/* 649 */       if (!sender.hasPermission("kitpvp.kit.spiderman")) {
/* 650 */         sender.sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§")) + this.main.getConfig().getString("Permission").replace("&", "§").replaceAll("%permisson%", commandLabel));
/* 651 */        
/* 652 */         return true;
/*     */       }
/* 654 */       if (!Join.game.contains(p.getName()))
/*     */       {
/* 656 */         p.sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§")) + " §eYou are not in kitpvp to choose this kit!");
/* 657 */         return true;
/*     */       }
/* 659 */       if (Habilidade.ContainsAbility(p)) {
/* 660 */         s.sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§")) + this.main.getConfig().getString("Message.KitUse").replace("&", "§"));
/* 661 */         
/* 662 */         return true;
/*     */       }
if (Main.kits.getBoolean("SpidermanDisabled")) {
	p.sendMessage(API.NomeServer + ChatColor.RED + "The Spiderman kit is disabled, sorry");
	return true;
}
/* 664 */       s.sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§")) + this.main.getConfig().getString("Message.Kit").replaceAll("%kit%", "Spiderman").replace("&", "§"));
/* 665 */       s.getInventory().clear();
/* 666 */      
/* 667 */       s.setFoodLevel(20);
/* 668 */       for (Object helmet11 = s.getActivePotionEffects().iterator(); ((Iterator)helmet11).hasNext();) { PotionEffect effect = (PotionEffect)((Iterator)helmet11).next();
/* 669 */         s.removePotionEffect(effect.getType());
/*     */       }
/* 671 */       s.getInventory().addItem(new ItemStack[] { new ItemStack(make(Material.DIAMOND_SWORD, 1, 0, this.main.getConfig().getString("SidermanKit.Name").replace("&", "§"), Arrays.asList(new String[] { this.main.getConfig().getString("SidermanKit.Lore").replace("&", "§") }))) });
/* 672 */       for (int i = 0; i <= 34; i++) {
/* 673 */         s.getInventory().addItem(new ItemStack[] { sopa });
/*     */       }
/*     */       API.give(p);
RTP.TeleportArenaRandom(p);
/* 676 */       ItemStack helmet111 = new ItemStack(Material.LEATHER_HELMET);
/* 677 */       helmet111.addUnsafeEnchantment(Enchantment.UNBREAKING, 150);
/* 678 */       helmet111.addEnchantment(Enchantment.PROJECTILE_PROTECTION, 2);
/* 679 */       helmet111.addEnchantment(Enchantment.PROTECTION, 2);
/* 680 */       Object meta111 = (LeatherArmorMeta)helmet111.getItemMeta();
/* 681 */       ((LeatherArmorMeta)meta111).setColor(Color.RED);
/* 682 */       helmet111.setItemMeta((ItemMeta)meta111);
/* 683 */       s.getEquipment().setHelmet(helmet111);
/* 684 */       ItemStack tunic1 = new ItemStack(Material.LEATHER_CHESTPLATE);
/* 685 */       tunic1.addUnsafeEnchantment(Enchantment.UNBREAKING, 150);
/* 686 */       tunic1.addEnchantment(Enchantment.PROJECTILE_PROTECTION, 2);
/* 687 */       tunic1.addEnchantment(Enchantment.PROTECTION, 2);
/* 688 */       LeatherArmorMeta meta1111 = (LeatherArmorMeta)tunic1.getItemMeta();
/* 689 */       meta1111.setColor(Color.RED);
/* 690 */       tunic1.setItemMeta(meta1111);
/* 691 */       s.getEquipment().setChestplate(tunic1);
/* 692 */       ItemStack tunic11 = new ItemStack(Material.LEATHER_LEGGINGS);
/* 693 */       tunic11.addUnsafeEnchantment(Enchantment.UNBREAKING, 150);
/* 694 */       tunic11.addEnchantment(Enchantment.PROJECTILE_PROTECTION, 2);
/* 695 */       LeatherArmorMeta meta11111 = (LeatherArmorMeta)tunic11.getItemMeta();
/* 696 */       tunic11.addEnchantment(Enchantment.PROTECTION, 2);
/* 697 */       meta11111.setColor(Color.RED);
/* 698 */       tunic11.setItemMeta(meta11111);
/* 699 */       s.getEquipment().setLeggings(tunic11);
/* 700 */       ItemStack tunic111 = new ItemStack(Material.LEATHER_BOOTS);
/* 701 */       tunic111.addUnsafeEnchantment(Enchantment.UNBREAKING, 150);
tunic111.addEnchantment(Enchantment.FEATHER_FALLING, 4);
/* 702 */       tunic111.addEnchantment(Enchantment.PROJECTILE_PROTECTION, 2);
/* 703 */       LeatherArmorMeta meta111111 = (LeatherArmorMeta)tunic111.getItemMeta();
/* 704 */       meta111111.setColor(Color.RED);
/* 705 */       tunic111.setItemMeta(meta111111);
/* 706 */       s.getEquipment().setBoots(tunic111);
/* 707 */       Habilidade.setAbility(s, "Spiderman");
/* 708 */       tunic111.addEnchantment(Enchantment.PROTECTION, 2);
/* 709 */      
/*     */       TitleAPI.sendTitle(p, Integer.valueOf(20), Integer.valueOf(60), Integer.valueOf(20), this.main.getConfig().getString("Title.KitTitle"), this.main.getConfig().getString("Title.KitSubTitle").replace("%kit%", "Spiderman"));
/* 711 */       return true;
/*     */     }
/* 713 */     if (cmd.getName().equalsIgnoreCase("airman")) {
/* 714 */       if (!sender.hasPermission("kitpvp.kit.airman")) {
/* 715 */         sender.sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§")) + this.main.getConfig().getString("Permission").replace("&", "§").replaceAll("%permisson%", commandLabel));
/* 716 */        
/* 717 */         return true;
/*     */       }
/* 719 */       if (!Join.game.contains(p.getName()))
/*     */       {
/* 721 */         p.sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§")) + " §eYou are not in kitpvp to do choose this kit!");
/* 722 */         return true;
/*     */       }
/* 724 */       if (Habilidade.ContainsAbility(p)) {
/* 725 */         s.sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§")) + this.main.getConfig().getString("Message.KitUse").replace("&", "§"));
/* 726 */         
/* 727 */         return true;
/*     */       }
if (Main.kits.getBoolean("AirmanDisabled")) {
	p.sendMessage(API.NomeServer + ChatColor.RED + "The Airman kit is disabled, sorry");
	return true;
}
/* 729 */       s.sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§")) + this.main.getConfig().getString("Message.Kit").replaceAll("%kit%", "AirMan").replace("&", "§"));
/* 730 */       s.getInventory().clear();
/* 731 */      API.vida(s);
/* 732 */       s.setFoodLevel(20);
/* 733 */       for (Object meta111 = s.getActivePotionEffects().iterator(); ((Iterator)meta111).hasNext();) { PotionEffect effect = (PotionEffect)((Iterator)meta111).next();
/* 734 */         s.removePotionEffect(effect.getType());
/*     */       }
/* 736 */       ItemStack sword8 = new ItemStack(Material.IRON_SWORD);
/* 737 */       sword8.addUnsafeEnchantment(Enchantment.SHARPNESS, 1);
sword8.addUnsafeEnchantment(Enchantment.UNBREAKING, 3);
/* 738 */       Habilidade.setAbility(s, "Airman");
/*     */       
/* 740 */       s.getInventory().addItem(new ItemStack[] { sword8 });
/* 741 */       s.getInventory().addItem(new ItemStack[] { new ItemStack(make(Material.FEATHER, 1, 0, this.main.getConfig().getString("AirmanKit.Name").replace("&", "§"), Arrays.asList(new String[] { this.main.getConfig().getString("AirmanKit.Lore").replace("&", "§") }))) });
/* 742 */       for (int i = 0; i <= 34; i++) {
/* 743 */         s.getInventory().addItem(new ItemStack[] { sopa });
/* 744 */         TitleAPI.sendTitle(p, Integer.valueOf(20), Integer.valueOf(60), Integer.valueOf(20), this.main.getConfig().getString("Title.KitTitle"), this.main.getConfig().getString("Title.KitSubTitle").replaceAll("%kit%", "Airman"));
/*     */       }
/* 746 */       Object helmet111 = new ItemStack(Material.LEATHER_HELMET);
/* 747 */       ((ItemStack)helmet111).addUnsafeEnchantment(Enchantment.UNBREAKING, 150);
/* 748 */       ((ItemStack)helmet111).addEnchantment(Enchantment.PROJECTILE_PROTECTION, 2);
((ItemStack)helmet111).addEnchantment(Enchantment.PROTECTION, 3);
/* 749 */       LeatherArmorMeta meta1111 = (LeatherArmorMeta)((ItemStack)helmet111).getItemMeta();
/* 750 */       meta1111.setColor(Color.BLACK);
/* 751 */       ((ItemStack)helmet111).setItemMeta(meta1111);
/* 752 */       s.getEquipment().setHelmet((ItemStack)helmet111);
/* 753 */       ItemStack tunic1 = new ItemStack(Material.LEATHER_CHESTPLATE);
/* 754 */       tunic1.addUnsafeEnchantment(Enchantment.UNBREAKING, 150);
/* 755 */       tunic1.addEnchantment(Enchantment.PROJECTILE_PROTECTION, 2);
tunic1.addEnchantment(Enchantment.PROTECTION, 2);
/* 756 */       LeatherArmorMeta meta11111 = (LeatherArmorMeta)tunic1.getItemMeta();
/* 757 */       meta11111.setColor(Color.BLACK);
/* 758 */       tunic1.setItemMeta(meta11111);
/* 759 */       s.getEquipment().setChestplate(tunic1);
/* 760 */       ItemStack tunic11 = new ItemStack(Material.LEATHER_LEGGINGS);
/* 761 */       tunic11.addUnsafeEnchantment(Enchantment.UNBREAKING, 150);
/* 762 */       tunic11.addEnchantment(Enchantment.PROJECTILE_PROTECTION, 2);
                tunic11.addEnchantment(Enchantment.PROTECTION, 2);
/* 763 */       LeatherArmorMeta meta111111 = (LeatherArmorMeta)tunic11.getItemMeta();
/* 764 */       meta111111.setColor(Color.BLACK);
/* 765 */       tunic11.setItemMeta(meta111111);
/* 766 */       s.getEquipment().setLeggings(tunic11);
/* 767 */       ItemStack tunic111 = new ItemStack(Material.LEATHER_BOOTS);
/* 768 */       tunic111.addUnsafeEnchantment(Enchantment.UNBREAKING, 150);
/* 769 */       tunic111.addEnchantment(Enchantment.PROJECTILE_PROTECTION, 2);
tunic111.addEnchantment(Enchantment.PROTECTION, 3);
tunic111.addEnchantment(Enchantment.FEATHER_FALLING, 4);
/* 770 */       LeatherArmorMeta meta1111111 = (LeatherArmorMeta)tunic111.getItemMeta();
/* 771 */       meta1111111.setColor(Color.BLACK);
/* 772 */       tunic111.setItemMeta(meta1111111);
/* 773 */       s.getEquipment().setBoots(tunic111);
/* 774 */      
/*     */       API.give(p);
RTP.TeleportArenaRandom(p);
/* 776 */       return true;
/*     */     }
/* 778 */     if (cmd.getName().equalsIgnoreCase("fisherman")) {
/* 779 */       if (!sender.hasPermission("kitpvp.kit.fisherman")) {
/* 780 */         sender.sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§")) + this.main.getConfig().getString("Permission").replace("&", "§").replaceAll("%permisson%", commandLabel));
/* 781 */        
/* 782 */         return true;
/*     */       }
/* 784 */       if (!Join.game.contains(p.getName()))
/*     */       {
/* 786 */         p.sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§")) + " §eYou are not in kitpvp to choose this kit!");
/* 787 */         return true;
/*     */       }
/* 789 */       if (Habilidade.ContainsAbility(p)) {
/* 790 */         s.sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§")) + this.main.getConfig().getString("Message.KitUse").replace("&", "§"));
/* 791 */         
/* 792 */         return true;
/*     */       }
/* 794 */       s.sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§")) + this.main.getConfig().getString("Message.Kit").replaceAll("%kit%", "FisherMan").replace("&", "§"));
/* 795 */       s.getInventory().clear();
/* 796 */       Habilidade.setAbility(p, "Fisherman");
/* 797 */      API.vida(s);
/* 798 */       s.setFoodLevel(20);
/* 799 */       for (Object helmet1111 = s.getActivePotionEffects().iterator(); ((Iterator)helmet1111).hasNext();) { PotionEffect effect = (PotionEffect)((Iterator)helmet1111).next();
/* 800 */         s.removePotionEffect(effect.getType());
/*     */       }
/* 802 */       ItemStack sword9 = new ItemStack(Material.DIAMOND_SWORD);
ItemStack FISH9 = new ItemStack(Material.FISHING_ROD);
/* 804 */       FISH9.addUnsafeEnchantment(Enchantment.UNBREAKING, 5);
/* 805 */       s.getInventory().addItem(new ItemStack[] { sword9 });
s.getInventory().addItem(new ItemStack[] { FISH9 });
/* 806 */       
/* 807 */       for (int i = 0; i <= 34; i++) {
/* 808 */         s.getInventory().addItem(new ItemStack[] { sopa });
/* 809 */         TitleAPI.sendTitle(p, Integer.valueOf(20), Integer.valueOf(60), Integer.valueOf(20), this.main.getConfig().getString("Title.KitTitle"), this.main.getConfig().getString("Title.KitSubTitle").replace(Habilidade.getAbility(p), "%kit%"));
/*     */       }
/* 811 */       ItemStack capacete0 = new ItemStack(Material.IRON_HELMET);
/*    */       
/* 57 */       ItemStack peitoral0 = new ItemStack(Material.IRON_CHESTPLATE);
/*    */       
/* 59 */       ItemStack calca0 = new ItemStack(Material.IRON_LEGGINGS);
/*    */       
/* 61 */       ItemStack Bota0 = new ItemStack(Material.IRON_BOOTS);
/*    */        API.give(p);
RTP.TeleportArenaRandom(p);
/* 63 */       p.getInventory().setHelmet(capacete0);
/* 64 */       p.getInventory().setChestplate(peitoral0);
/* 65 */       p.getInventory().setLeggings(calca0);
/* 66 */       p.getInventory().setBoots(Bota0);
/* 842 */      
/* 843 */       TitleAPI.sendTitle(p, Integer.valueOf(20), Integer.valueOf(60), Integer.valueOf(20), this.main.getConfig().getString("Title.KitTitle"), this.main.getConfig().getString("Title.KitSubTitle").replaceAll("%kit%", "Fisherman"));
/* 844 */       return true;
/*     */     }
/* 846 */     if (cmd.getName().equalsIgnoreCase("freezer")) {
/* 847 */       if (!sender.hasPermission("kitpvp.kit.freezer")) {
/* 848 */         sender.sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§")) + this.main.getConfig().getString("Permission").replace("&", "§").replaceAll("%permisson%", commandLabel));
/* 849 */        
/* 850 */         return true;
/*     */       }
/* 852 */       if (!Join.game.contains(p.getName()))
/*     */       {
/* 854 */         p.sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§")) + " §eYou are not in kitpvp to do choose this kit!");
/* 855 */         return true;
/*     */       }
/* 857 */       if (Habilidade.ContainsAbility(p)) {
/* 858 */         s.sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§")) + this.main.getConfig().getString("Message.KitUse").replace("&", "§"));
/* 859 */         
/* 860 */         return true;
/*     */       }
/* 862 */       s.sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§")) + this.main.getConfig().getString("Message.Kit").replaceAll("%kit%", "Freezer").replace("&", "§"));
/* 863 */       s.getInventory().clear();
/* 864 */      API.vida(s);
/* 865 */       s.setFoodLevel(20);
                API.give(p);
                RTP.TeleportArenaRandom(p);
/* 866 */       for (Object helmet1111 = s.getActivePotionEffects().iterator(); ((Iterator)helmet1111).hasNext();) { PotionEffect effect = (PotionEffect)((Iterator)helmet1111).next();
/* 867 */         s.removePotionEffect(effect.getType());
/*     */       }
/* 869 */       ItemStack sword10 = new ItemStack(Material.STONE_SWORD);
/* 870 */       sword10.addUnsafeEnchantment(Enchantment.SHARPNESS, 3);
/* 871 */       sword10.addUnsafeEnchantment(Enchantment.UNBREAKING, 8);
/*     */       
/* 873 */       s.getInventory().addItem(new ItemStack[] { sword10 });
/* 874 */       s.getInventory().addItem(new ItemStack[] { new ItemStack(Material.FISHING_ROD) });
/*     */       
/* 876 */       ItemStack helmet11111 = new ItemStack(Material.LEATHER_HELMET);
/* 877 */       helmet11111.addUnsafeEnchantment(Enchantment.UNBREAKING, 150);
/* 878 */       helmet11111.addEnchantment(Enchantment.PROJECTILE_PROTECTION, 2);
/* 879 */       LeatherArmorMeta meta1111 = (LeatherArmorMeta)helmet11111.getItemMeta();
/* 880 */       meta1111.setColor(Color.WHITE);
/* 881 */       helmet11111.setItemMeta(meta1111);
/* 882 */       s.getEquipment().setHelmet(helmet11111);
/* 883 */       ItemStack tunic1 = new ItemStack(Material.LEATHER_CHESTPLATE);
/* 884 */       tunic1.addUnsafeEnchantment(Enchantment.UNBREAKING, 150);
/* 885 */       tunic1.addEnchantment(Enchantment.PROJECTILE_PROTECTION, 2);
/* 886 */       LeatherArmorMeta meta11111 = (LeatherArmorMeta)tunic1.getItemMeta();
/* 887 */       meta11111.setColor(Color.BLUE);
/* 888 */       tunic1.setItemMeta(meta11111);
/* 889 */       s.getEquipment().setChestplate(tunic1);
/* 890 */       ItemStack tunic11 = new ItemStack(Material.LEATHER_LEGGINGS);
/* 891 */       tunic11.addUnsafeEnchantment(Enchantment.UNBREAKING, 150);
/* 892 */       tunic11.addEnchantment(Enchantment.PROJECTILE_PROTECTION, 2);
/* 893 */       LeatherArmorMeta meta111111 = (LeatherArmorMeta)tunic11.getItemMeta();
/* 894 */       meta111111.setColor(Color.BLUE);
/* 895 */       tunic11.setItemMeta(meta111111);
/* 896 */       s.getEquipment().setLeggings(tunic11);
/* 897 */       ItemStack tunic111 = new ItemStack(Material.LEATHER_BOOTS);
/* 898 */       tunic111.addUnsafeEnchantment(Enchantment.UNBREAKING, 150);
/*     */       
/* 900 */       LeatherArmorMeta meta1111111 = (LeatherArmorMeta)tunic111.getItemMeta();
/* 901 */       meta1111111.setColor(Color.WHITE);
/* 902 */       tunic111.setItemMeta(meta1111111);
/* 903 */       s.getEquipment().setBoots(tunic111);
/* 904 */       int i = 0; if (i <= 34) {
/* 905 */         s.getInventory().addItem(new ItemStack[] { sopa });
/* 906 */        
/* 907 */         TitleAPI.sendTitle(p, Integer.valueOf(5), Integer.valueOf(20), Integer.valueOf(5), this.main.getConfig().getString("Title.KitTitle"), this.main.getConfig().getString("Title.KitSubTitle").replace(Habilidade.getAbility(p), "%kit%"));
/* 908 */         return true;
/*     */       }
/*     */     }
/* 911 */     return false;
/*     */   }
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private ItemStack make(Material material, int amount, int shrt, String displayName, List<String> lore)
/*     */   {
/* 936 */     ItemStack item = new ItemStack(material, amount, (short)shrt);
/* 937 */     ItemMeta meta = item.getItemMeta();
/* 938 */     meta.setDisplayName(displayName);
/* 939 */     meta.setLore(lore);
/* 940 */     item.setItemMeta(meta);
/* 941 */     return item;
/*     */   }
/*     */ }


/* Location:              D:\Desktop\video\Minhas Coisas do Desktop\KP-PVPvB12 (1).jar!\me\RafaelAulerDeMeloAraujo\main\Kits.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */
