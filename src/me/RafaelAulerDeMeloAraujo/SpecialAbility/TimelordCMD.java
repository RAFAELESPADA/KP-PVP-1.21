/*    */ package me.RafaelAulerDeMeloAraujo.SpecialAbility;
import org.bukkit.ChatColor;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.command.Command;
/*    */ import org.bukkit.command.CommandExecutor;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.bukkit.inventory.meta.ItemMeta;

/*    */ import me.RafaelAulerDeMeloAraujo.TitleAPI.TitleAPI;
/*    */ import me.RafaelAulerDeMeloAraujo.main.Main;
/*    */ 
/*    */ public class TimelordCMD implements CommandExecutor
/*    */ {
/*    */   private Main main;
/*    */   static Main plugin;
/*    */   
/*    */   public TimelordCMD(Main main)
/*    */   {
/* 24 */     this.main = main;
/* 25 */     plugin = main;
/*    */   }
/*    */   
/*    */ 
/*    */   public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
/*    */   {
/* 31 */     Player p = (Player)sender;
/*    */     
/* 33 */     if (cmd.getName().equalsIgnoreCase("timelord"))
/*    */     {
/* 35 */       if (!Join.game.contains(p.getName()))
/*    */       {
/* 37 */         p.sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§")) + " §eYou are not in kitpvp to do choose this kit!");
/* 38 */         return true;
/*    */       }
/* 40 */       if (!p.hasPermission("kitpvp.kit.timelord"))
/*    */       {
/* 42 */         p.sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§")) + this.main.getConfig().getString("Permission").replace("&", "§").replaceAll("%permisson%", commandLabel));
/* 43 */                NewKitMenu.playSound(p, Main.getInstace().getConfig().getString("Sound.NoPermissionMessage"), 1.0F, 1.0F);
return true;
/*    */       }
/*    */       
/* 47 */       if (Habilidade.ContainsAbility(p)) {
/* 48 */         p.sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§")) + this.main.getConfig().getString("Message.KitUse").replace("&", "§"));
/* 49 */                NewKitMenu.playSound(p, Main.getInstace().getConfig().getString("Sound.NoPermissionMessage"), 1.0F, 1.0F);
return true;
/*    */       }
if (Main.kits.getBoolean("TimelordDisabled")) {
	p.sendMessage(API.NomeServer + ChatColor.RED + "The Timelord kit is disabled, sorry");
	return true;
}
/* 52 */       p.getInventory().clear();
/* 53 */       ItemStack dima = new ItemStack(Material.DIAMOND_SWORD);
/* 54 */       ItemMeta souperaa = dima.getItemMeta();
/* 55 */       souperaa.setDisplayName("§cSword");
/* 56 */       dima.setItemMeta(souperaa);
/* 57 */       ItemStack sopa = new ItemStack(Material.MUSHROOM_STEW);
/* 58 */       ItemMeta sopas = sopa.getItemMeta();
/* 59 */       sopas.setDisplayName("§6Soup");
/* 60 */       sopa.setItemMeta(sopas);
/* 61 */       ItemStack especial = new ItemStack(Material.CLOCK);
/* 62 */       ItemMeta especial2 = especial.getItemMeta();
/* 63 */       especial2.setDisplayName("§bFreeze the Time!");
/* 64 */       especial.setItemMeta(especial2);
/*    */       
/* 66 */       ItemStack capacete0 = new ItemStack(Material.IRON_HELMET);
/*    */       
/* 68 */       ItemStack peitoral0 = new ItemStack(Material.IRON_CHESTPLATE);
/*    */       
/* 70 */       ItemStack calca0 = new ItemStack(Material.IRON_LEGGINGS);
/*    */       
/* 72 */       ItemStack Bota0 = new ItemStack(Material.IRON_BOOTS);
/*    */       
/* 74 */       p.getInventory().setHelmet(capacete0);
/* 75 */       p.getInventory().setChestplate(peitoral0);
/* 76 */       p.getInventory().setLeggings(calca0);
/* 77 */       p.getInventory().setBoots(Bota0);
/*    */       
/* 79 */       p.sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§")) + this.main.getConfig().getString("Message.Kit").replaceAll("%kit%", "TimeLord").replace("&", "§"));
/* 80 */       Habilidade.setAbility(p, "TimeLord");
/* 81 */       p.getInventory().addItem(new ItemStack[] { dima });
/* 82 */       p.getInventory().addItem(new ItemStack[] { especial });
/*    */       
/*    */ 
/*    */ API.give(p);
RTP.TeleportArenaRandom(p);
NewKitMenu.playSound(p, Main.getInstace().getConfig().getString("Sound.KitUse"), 1.0F, 1.0F);
/* 86 */       for (int i = 0; i <= 34; i++) {
/* 87 */         p.getInventory().addItem(new ItemStack[] { sopa });
/* 89 */         TitleAPI.sendTitle(p, Integer.valueOf(20), Integer.valueOf(60), Integer.valueOf(20), this.main.getConfig().getString("Title.KitTitle"), this.main.getConfig().getString("Title.KitSubTitle").replaceAll("%kit%", "Timelord"));
/*    */       }
/*    */     }
/*    */     
/* 93 */     return false;
/*    */   }
/*    */ }


/* Location:              D:\Desktop\video\Minhas Coisas do Desktop\KP-PVPvB12 (1).jar!\me\RafaelAulerDeMeloAraujo\SpecialAbility\TimelordCMD.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */
