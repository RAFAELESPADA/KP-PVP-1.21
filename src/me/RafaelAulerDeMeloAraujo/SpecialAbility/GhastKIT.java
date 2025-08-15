package me.RafaelAulerDeMeloAraujo.SpecialAbility;

import org.bukkit.ChatColor;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.command.Command;
/*    */ import org.bukkit.command.CommandExecutor;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.bukkit.inventory.meta.ItemMeta;

/*    */ import me.RafaelAulerDeMeloAraujo.main.Main;
import me.RafaelAulerDeMeloAraujo.main.RTP;
/*    */ 
/*    */ public class GhastKIT implements CommandExecutor
/*    */ {
/*    */   private Main main;
/*    */   static Main plugin;
/*    */   
/*    */   public GhastKIT(Main main)
/*    */   {
/* 23 */     this.main = main;
/* 24 */     plugin = main;
/*    */   }
/*    */   
/*    */ 
/*    */   public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
/*    */   {
/* 30 */     Player p = (Player)sender;
/*    */     
/* 32 */     if (cmd.getName().equalsIgnoreCase("kghast"))
/*    */     {
/* 34 */       if (!Join.game.contains(p.getName()))
/*    */       {
/* 36 */         p.sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§")) + " §eYou are not in kitpvp to do choose this kit!");
/* 37 */         return true;
/*    */       }
/* 39 */       if (!p.hasPermission("kitpvp.kit.ghast"))
/*    */       {
/* 41 */         p.sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§")) + this.main.getConfig().getString("Permission").replace("&", "§").replaceAll("%permisson%", commandLabel));
/* 42 */         
NewKitMenu.playSound(p, Main.getInstace().getConfig().getString("Sound.KitUse"), 1.0F, 1.0F);return true;
/*    */       }
if (Main.kits.getBoolean("GhastDisabled")) {
	p.sendMessage(API.NomeServer + ChatColor.RED + "The Ghast kit is disabled, sorry");
	return true;
}
/*    */       
/* 46 */       if (Habilidade.ContainsAbility(p)) {
/* 47 */         p.sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§")) + this.main.getConfig().getString("Message.KitUse").replace("&", "§"));
/* 48 */         
NewKitMenu.playSound(p, Main.getInstace().getConfig().getString("Sound.KitUse"), 1.0F, 1.0F);return true;
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
/* 60 */       ItemStack especial = new ItemStack(Material.FIRE_CHARGE);
/* 61 */       ItemMeta especial2 = especial.getItemMeta();
/* 62 */       especial2.setDisplayName("§cGhast!");
/* 63 */       especial.setItemMeta(especial2);
/*    */       
/* 65 */       /*    */ 
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
/*    */       
/* 78 */       p.sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§")) + this.main.getConfig().getString("Message.Kit").replaceAll("%kit%", "Ghast").replace("&", "§"));
/* 79 */       Habilidade.setAbility(p, "Ghast");
/* 80 */       
/* 81 */       
/*    */       
/*    */ 
/*    */ API.give(p);
RTP.TeleportArenaRandom(p);

/* 70 */       p.getInventory().addItem(new ItemStack[] { dima });
/* 85 */       for (int i = 0; i <= 34; i++) {
/* 86 */         p.getInventory().addItem(new ItemStack[] { sopa });
/* 87 */       
NewKitMenu.playSound(p, Main.getInstace().getConfig().getString("Sound.KitUse"), 1.0F, 1.0F);  me.RafaelAulerDeMeloAraujo.TitleAPI.TitleAPI.sendTitle(p, Integer.valueOf(20), Integer.valueOf(60), Integer.valueOf(20), this.main.getConfig().getString("Title.KitTitle"), this.main.getConfig().getString("Title.KitSubTitle").replaceAll("%kit%", "Ghast"));
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

