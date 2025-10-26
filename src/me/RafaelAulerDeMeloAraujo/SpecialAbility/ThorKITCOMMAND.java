/*    */ package me.RafaelAulerDeMeloAraujo.SpecialAbility;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.command.Command;
/*    */ import org.bukkit.command.CommandExecutor;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.bukkit.inventory.meta.ItemMeta;

/*    */ import me.RafaelAulerDeMeloAraujo.main.Main;
/*    */ 
/*    */ public class ThorKITCOMMAND implements CommandExecutor
/*    */ {
/*    */   private Main main;
/*    */   static Main plugin;
/*    */   
/*    */   public ThorKITCOMMAND(Main main)
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
/* 32 */     if (cmd.getName().equalsIgnoreCase("kthor"))
/*    */     {
/* 34 */       if (!Join.game.contains(p.getName()))
/*    */       {
/* 36 */         p.sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§")) + " §eYou are not in kitpvp to do choose this kit!");
/* 37 */         return true;
/*    */       }
/* 39 */       if (!p.hasPermission("kitpvp.kit.thor")) {
/*    */       
/* 41 */         p.sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§")) + this.main.getConfig().getString("Permission").replace("&", "§").replaceAll("%permisson%", commandLabel));
/* 42 */               NewKitMenu.playSound(p, Main.getInstace().getConfig().getString("Sound.NoPermissionMessage"), 1.0F, 1.0F);
return true;
}
/*    */       
/* 46 */       if (Habilidade.ContainsAbility(p)) {
/* 47 */         p.sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§")) + this.main.getConfig().getString("Message.KitUse").replace("&", "§"));
/* 48 */               NewKitMenu.playSound(p, Main.getInstace().getConfig().getString("Sound.NoPermissionMessage"), 1.0F, 1.0F);
return true;
}
/* 51 */       p.getInventory().clear();
/* 52 */       ItemStack dima = new ItemStack(Material.DIAMOND_SWORD);
/* 53 */       ItemMeta souperaa = dima.getItemMeta();
/* 54 */       souperaa.setDisplayName("§cSword");
/* 55 */       dima.setItemMeta(souperaa);
/* 56 */       ItemStack sopa = new ItemStack(Material.MUSHROOM_STEW);
/* 57 */       ItemMeta sopas = sopa.getItemMeta();
/* 58 */       sopas.setDisplayName("§6Soup");
/* 59 */       sopa.setItemMeta(sopas);
/* 60 */       ItemStack especial = new ItemStack(Material.GOLDEN_AXE);
/* 61 */       ItemMeta especial2 = especial.getItemMeta();
/* 62 */       especial2.setDisplayName("§cCaboom!");
/* 63 */       especial.setItemMeta(especial2);
/*    */       
/* 65 */       ItemStack capacete0 = new ItemStack(Material.IRON_HELMET);
/*    */       
/* 67 */       ItemStack peitoral0 = new ItemStack(Material.IRON_CHESTPLATE);
/*    */       
/* 69 */       ItemStack calca0 = new ItemStack(Material.IRON_LEGGINGS);
/*    */       
/* 71 */       ItemStack Bota0 = new ItemStack(Material.IRON_BOOTS);
/*    */       
/* 73 */       p.getInventory().setHelmet(capacete0);
/* 74 */       p.getInventory().setChestplate(peitoral0);
/* 75 */       p.getInventory().setLeggings(calca0);
/* 76 */       p.getInventory().setBoots(Bota0);
/*    */       
/* 78 */       p.sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§")) + this.main.getConfig().getString("Message.Kit").replaceAll("%kit%", "Thor").replace("&", "§"));
/* 79 */       Habilidade.setAbility(p, "Thor");
/* 80 */       p.getInventory().addItem(new ItemStack[] { dima });
/* 81 */       p.getInventory().addItem(new ItemStack[] { especial });
/*    */       
/*    */ 
/*    */ API.give(p);
RTP.TeleportArenaRandom(p);
/* 85 */       for (int i = 0; i <= 34; i++) {
/* 86 */         p.getInventory().addItem(new ItemStack[] { sopa });
NewKitMenu.playSound(p, Main.getInstace().getConfig().getString("Sound.KitUse"), 1.0F, 1.0F);
/* 88 */         me.RafaelAulerDeMeloAraujo.TitleAPI.TitleAPI.sendTitle(p, Integer.valueOf(20), Integer.valueOf(60), Integer.valueOf(20), this.main.getConfig().getString("Title.KitTitle"), this.main.getConfig().getString("Title.KitSubTitle").replaceAll("%kit%", "Thor"));
/*    */       }
/*    */     }
/*    */     
/* 92 */     return false;
/*    */   }
/*    */ 
}


/* Location:              D:\Desktop\video\Minhas Coisas do Desktop\KP-PVPvB12 (1).jar!\me\RafaelAulerDeMeloAraujo\SpecialAbility\ThorKITCOMMAND.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */
