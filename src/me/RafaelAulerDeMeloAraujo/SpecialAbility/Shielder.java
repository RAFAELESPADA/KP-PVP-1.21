package me.RafaelAulerDeMeloAraujo.SpecialAbility;


/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.command.Command;
/*    */ import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
/*    */ 
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ import org.bukkit.inventory.meta.ItemMeta;

/*    */ 
/*    */ import me.RafaelAulerDeMeloAraujo.main.Main;
/*    */ 
/*    */ public class Shielder implements org.bukkit.command.CommandExecutor
/*    */ {
/*    */   private Main main;
/*    */   static Main plugin;
/*    */   
/*    */   public Shielder (Main main)
/*    */   {
/* 20 */     this.main = main;
/* 21 */     plugin = main;
/*    */   }
/*    */   
/*    */ 
/*    */   public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
/*    */   {
/* 27 */     Player p = (Player)sender;
/*    */     
/* 29 */     if (cmd.getName().equalsIgnoreCase("kshielder"))
/*    */     {
/*    */ 
/*    */ 
/*    */ 
/* 34 */       if (Habilidade.ContainsAbility(p)) {
/* 35 */         p.sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "�")) + this.main.getConfig().getString("Message.KitUse").replace("&", "�"));
/* 36 */         p.playSound(p.getLocation(), org.bukkit.Sound.valueOf(this.main.getConfig().getString("Sound.KitUse")), 1.0F, 1.0F);
/* 37 */         return true;
/*    */       }
/* 39 */       if (!Join.game.contains(p.getName()))
/*    */       {
/* 41 */         p.sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "�")) + " �eYou are not in kitpvp to choose this kit!");
/* 42 */         return true;
/*    */       }
/* 44 */       p.getInventory().clear();
/* 45 */       ItemStack dima = new ItemStack(Material.NETHERITE_SWORD);
/* 46 */       ItemMeta souperaa = dima.getItemMeta();
/* 47 */       souperaa.setDisplayName("�cSword");
/* 48 */       dima.setItemMeta(souperaa);
               dima.addEnchantment(Enchantment.SHARPNESS, 1);
               
               ItemStack monk = new ItemStack(Material.SHIELD);
               /* 196 */     ItemMeta ims = monk.getItemMeta();
               /* 197 */     ims.setDisplayName("�bShield");
               /* 198 */     monk.setItemMeta(ims);
               
/* 49 */       ItemStack sopa = new ItemStack(Material.MUSHROOM_STEW);
/* 50 */       ItemMeta sopas = sopa.getItemMeta();
/* 51 */       sopas.setDisplayName("�6Soup");
/* 52 */       sopa.setItemMeta(sopas);
/*    */       
/*    */ 
/* 55 */       ItemStack capacete0 = new ItemStack(Material.CHAINMAIL_HELMET);
/*    */       
/* 57 */       ItemStack peitoral0 = new ItemStack(Material.IRON_CHESTPLATE);
/*    */       
/* 59 */       ItemStack calca0 = new ItemStack(Material.GOLDEN_LEGGINGS);
/*    */       
/* 61 */       ItemStack Bota0 = new ItemStack(Material.CHAINMAIL_BOOTS);
/*    */       
/* 63 */       p.getInventory().setHelmet(capacete0);
/* 64 */       p.getInventory().setChestplate(peitoral0);
/* 65 */       p.getInventory().setLeggings(calca0);
/* 66 */       p.getInventory().setBoots(Bota0);
/* 67 */       Habilidade.setAbility(p, "Basic");
/* 68 */       p.sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "�")) + this.main.getConfig().getString("Message.Kit").replaceAll("%kit%", "Basic").replace("&", "�"));
/*    */       API.give(p);
RTP.TeleportArenaRandom(p);
/* 70 */       p.getInventory().addItem(new ItemStack[] { dima });
/*    */        p.getInventory().addItem(new ItemStack[] { monk });
/*    */ 
/*    */ 
/*    */ 
/* 75 */       for (int i = 0; i <= 34; i++) {
/* 76 */         p.getInventory().addItem(new ItemStack[] { sopa });
/* 77 */         me.RafaelAulerDeMeloAraujo.TitleAPI.TitleAPI.sendTitle(p, Integer.valueOf(20), Integer.valueOf(60), Integer.valueOf(20), this.main.getConfig().getString("Title.KitTitle"), this.main.getConfig().getString("Title.KitSubTitle").replaceAll("%kit%", "Basic"));
/*    */       }
/*    */     }
/*    */     
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/* 85 */     return false;
/*    */   }
/*    */ }


/* Location:              D:\Desktop\video\Minhas Coisas do Desktop\KP-PVPvB12 (1).jar!\me\RafaelAulerDeMeloAraujo\SpecialAbility\Basic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */
