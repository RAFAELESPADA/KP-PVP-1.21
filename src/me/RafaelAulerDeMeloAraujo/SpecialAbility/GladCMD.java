/*    */ package me.RafaelAulerDeMeloAraujo.SpecialAbility;
import org.bukkit.ChatColor;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.command.Command;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */
/*    */ import org.bukkit.inventory.meta.ItemMeta;

/*    */ 
/*    */ import me.RafaelAulerDeMeloAraujo.main.Main;
/*    */ 
/*    */ public class GladCMD implements org.bukkit.command.CommandExecutor
/*    */ {
/*    */   private Main main;
/*    */   static Main plugin;
/*    */   
/*    */   public GladCMD(Main main)
/*    */   {
/* 20 */     this.main = main;
/* 21 */     plugin = main;
/*    */   }
/*    */   
/*    */ 
/*    */   public boolean onCommand(org.bukkit.command.CommandSender sender, Command cmd, String commandLabel, String[] args)
/*    */   {
/* 27 */     Player p = (Player)sender;
/*    */     
/* 29 */     if (cmd.getName().equalsIgnoreCase("gladiator"))
/*    */     {
/* 31 */       if (!Join.game.contains(p.getName()))
/*    */       {
/* 33 */         p.sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§")) + " §eYou are not in kitpvp to do choose this kit!");
/* 34 */         return true;
/*    */       }
/* 36 */       if (!p.hasPermission("kitpvp.kit.gladiator")) {
/* 37 */         p.sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§")) + this.main.getConfig().getString("Permission").replace("&", "§").replaceAll("%permisson%", commandLabel));
/* 38 */         
NewKitMenu.playSound(p, Main.getInstace().getConfig().getString("Sound.KitUse"), 1.0F, 1.0F);return true;
/*    */       }
/*    */       
/*    */ 
/*    */ 
/* 44 */       if (Habilidade.ContainsAbility(p)) {
/* 45 */         p.sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§")) + this.main.getConfig().getString("Message.KitUse").replace("&", "§"));
/* 46 */        
NewKitMenu.playSound(p, Main.getInstace().getConfig().getString("Sound.KitUse"), 1.0F, 1.0F); return true;
/*    */       }
/* 49 */       if (!Join.game.contains(p.getName()))
/*    */       {
/* 51 */         p.sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§")) + " §eYou are not in kitpvp to do choose this kit!");
/* 52 */         return true;
/*    */       }
/*    */       if (Main.kits.getBoolean("GladiatorDisabled")) {
	p.sendMessage(API.NomeServer + ChatColor.RED + "The Gladiator kit is disabled, sorry");
	return true;
}
/* 54 */       p.getInventory().clear();
/* 55 */       ItemStack dima = new ItemStack(Material.DIAMOND_SWORD);
/* 56 */       ItemMeta souperaa = dima.getItemMeta();
/* 57 */       souperaa.setDisplayName("§cSword");
/* 58 */       dima.setItemMeta(souperaa);
/* 59 */       ItemStack sopa = new ItemStack(Material.MUSHROOM_STEW);
/* 60 */       ItemMeta sopas = sopa.getItemMeta();
/* 61 */       sopas.setDisplayName("§6Soup");
/* 62 */       sopa.setItemMeta(sopas);
/* 63 */       ItemStack especial = new ItemStack(Material.IRON_BARS);
/* 64 */       ItemMeta especial2 = especial.getItemMeta();
/* 65 */       especial2.setDisplayName("§bGladiator Arena");
/* 66 */       especial.setItemMeta(especial2);
/*    */       
/*    */ 
/* 69 */       ItemStack capacete0 = new ItemStack(Material.IRON_HELMET);
/*    */       
/* 71 */       ItemStack peitoral0 = new ItemStack(Material.IRON_CHESTPLATE);
/*    */       
/* 73 */       ItemStack calca0 = new ItemStack(Material.IRON_LEGGINGS);
/*    */       
/* 75 */       ItemStack Bota0 = new ItemStack(Material.IRON_BOOTS);
/*    */       
/* 77 */       p.getInventory().setHelmet(capacete0);
/* 78 */       p.getInventory().setChestplate(peitoral0);
/* 79 */       p.getInventory().setLeggings(calca0);
/* 80 */       p.getInventory().setBoots(Bota0);
/*    */       
/* 82 */       p.sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§")) + this.main.getConfig().getString("Message.Kit").replaceAll("%kit%", "Gladiator").replace("&", "§"));
/*    */       
/* 84 */       p.getInventory().addItem(new ItemStack[] { dima });
/* 85 */       p.getInventory().addItem(new ItemStack[] { especial });
/*    */       
/*    */ API.give(p);
RTP.TeleportArenaRandom(p);
/* 88 */       Habilidade.setAbility(p, "Gladiator");
/* 89 */       for (int i = 0; i <= 34; i++) {
/* 90 */         p.getInventory().addItem(new ItemStack[] { sopa });
/* 91 */         me.RafaelAulerDeMeloAraujo.TitleAPI.TitleAPI.sendTitle(p, Integer.valueOf(20), Integer.valueOf(60), Integer.valueOf(20), this.main.getConfig().getString("Title.KitTitle"), this.main.getConfig().getString("Title.KitSubTitle").replaceAll("%kit%", "Gladiator"));
/* 92 */       
NewKitMenu.playSound(p, Main.getInstace().getConfig().getString("Sound.KitUse"), 1.0F, 1.0F);}
/*    */     }
/*    */     
/* 96 */     return false;
/*    */   }
/*    */ }


/* Location:              D:\Desktop\video\Minhas Coisas do Desktop\KP-PVPvB12 (1).jar!\me\RafaelAulerDeMeloAraujo\SpecialAbility\GladCMD.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */
