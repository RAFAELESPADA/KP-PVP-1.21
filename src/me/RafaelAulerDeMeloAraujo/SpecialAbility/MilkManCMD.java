package me.RafaelAulerDeMeloAraujo.SpecialAbility;




import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.RafaelAulerDeMeloAraujo.TitleAPI.TitleAPI;
import me.RafaelAulerDeMeloAraujo.main.Main;
import me.RafaelAulerDeMeloAraujo.main.RTP;
import net.wavemc.core.bukkit.item.ItemBuilder;

/*    */ public class MilkManCMD implements org.bukkit.command.CommandExecutor
/*    */ {
/*    */   private Main main;
/*    */   static Main plugin;
/*    */   
/*    */   public MilkManCMD(Main main)
/*    */   {
/* 20 */     this.main = main;
/* 21 */     plugin = main;
/*    */   }
/*    */   

/* 202 */     public boolean onCommand(CommandSender sender, Command command, String cmd, String[] args) {
	/* 186 */     Player p = (Player)sender;
	/* 187 */     ItemStack dima = new ItemStack(Material.DIAMOND_SWORD);
	/* 188 */     ItemMeta souperaa = dima.getItemMeta();
	/* 189 */     souperaa.setDisplayName("§cSword");
	/* 190 */     dima.setItemMeta(souperaa);
	/* 191 */     ItemStack sopa = new ItemStack(Material.MUSHROOM_STEW);
	/* 192 */     ItemMeta sopas = sopa.getItemMeta();
	/* 193 */     sopas.setDisplayName("§6Soup");
	/* 194 */     sopa.setItemMeta(sopas);
	/* 195 */      p.getInventory().setItem(1, new ItemBuilder(Material.MILK_BUCKET)
            .displayName("§fMilk")
            .nbt("cancel-drop")
            .nbt("kit-handler", "milkman")
            .toStack()
    );

	/*     */     
	/*     */ 
	/*     */ 
	/* 202 */     if (cmd.equalsIgnoreCase("kmilkman")) {
	/* 203 */       if (Habilidade.ContainsAbility(p)) {
	/* 204 */         p.sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§")) + this.main.getConfig().getString("Message.KitUse").replace("&", "§"));
	/* 205 */         
	NewKitMenu.playSound(p, Main.getInstace().getConfig().getString("Sound.KitUse"), 1.0F, 1.0F);return true;
	/*     */       }
	/* 208 */       if (!Join.game.contains(p.getName()))
	/*     */       {
	/* 210 */         p.sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§")) + " §eYou are not in kitpvp to choose this kit!");
	/* 211 */         return true;
	/*     */       }
	/* 213 */       if (!p.hasPermission("kitpvp.kit.phantom")) {
	/* 214 */         p.sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§")) + this.main.getConfig().getString("Permission").replace("&", "§").replaceAll("%permisson%", cmd));
	/* 215 */        
	NewKitMenu.playSound(p, Main.getInstace().getConfig().getString("Sound.KitUse"), 1.0F, 1.0F); return true;
	/*     */       }
	if (Main.kits.getBoolean("MilkmanDisabled")) {
		p.sendMessage(API.NomeServer + ChatColor.RED + "The MilkMan kit is disabled, sorry");
		return true;
	}
	/* 218 */       p.getInventory().clear();
	/* 219 */       p.sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§")) + this.main.getConfig().getString("Message.Kit").replaceAll("%kit%", "Milkman").replace("&", "§"));
	/* 220 */       p.setGameMode(GameMode.ADVENTURE);
	/*     */       
	/* 222 */       Habilidade.setAbility(p, "Milkman");
	/* 223 */       
	/* 235 */       ItemStack capacete0 = new ItemStack(Material.IRON_HELMET);
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
	/* 236 */       
	/*     */        p.getInventory().addItem(new ItemStack[] { dima });
	/* 238 */       for (int i = 0; i <= 34; i++) {
	/* 239 */         p.getInventory().addItem(new ItemStack[] { sopa });
	/*     */       }
	/*     */     }
	/*     */     API.give(p);
	RTP.TeleportArenaRandom(p);
	/* 243 */     TitleAPI.sendTitle(p, Integer.valueOf(20), Integer.valueOf(60), Integer.valueOf(20), this.main.getConfig().getString("Title.KitTitle"), this.main.getConfig().getString("Title.KitSubTitle").replaceAll("%kit%", "Phantom"));
	/*     */     
	/*     */ 
	/*     */ 
	/* 247 */     return false;
	/*     */   }
	/*     */ }


	/* Location:              D:\Desktop\video\Minhas Coisas do Desktop\KP-PVPvB12 (1).jar!\me\RafaelAulerDeMeloAraujo\SpecialAbility\Kangaroo.class
	 * Java compiler version: 8 (52.0)
	 * JD-Core Version:       0.7.1
	 */

