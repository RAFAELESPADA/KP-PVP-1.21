/*    */ package me.RafaelAulerDeMeloAraujo.X1;
/*    */ 
/*    */ import java.io.IOException;

/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.command.Command;
/*    */ import org.bukkit.command.CommandExecutor;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.configuration.InvalidConfigurationException;
/*    */ 
/*    */ import org.bukkit.entity.Player;

/*    */ import me.RafaelAulerDeMeloAraujo.main.Main;
/*    */ 
/*    */ 
/*    */ public class SetX1
/*    */   implements CommandExecutor
/*    */ {
/*    */   public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
/*    */   {
/* 19 */     if (!(sender instanceof Player)) {
/* 20 */       sender.sendMessage("This command is only for players =)");
/* 21 */       return true;
/*    */     }
/* 23 */     Player p = (Player)sender;
/* 24 */     if (!p.hasPermission("kitpvp.set1v1")) {
/* 25 */       p.sendMessage("§cYou dont have the permission kitpvp.set1v1");
/* 26 */       return true;
/*    */     }
/* 28 */     Location loc = p.getLocation();
String w = (loc.getWorld().getName());
/* 29 */     Double x = Double.valueOf(loc.getX());
/* 30 */     Double y = Double.valueOf(loc.getY());
/* 31 */     Double z = Double.valueOf(loc.getZ());
/* 32 */     Float yaw = Float.valueOf(loc.getYaw());
/* 33 */     Float pitch = Float.valueOf(loc.getPitch());
/*    */     
/* 35 */     if (args.length == 0) {
/* 36 */       p.sendMessage("§cUse /set1v1 <spawn/loc1/loc2>");
/* 37 */       return true;
/*    */     }
/* 39 */     if (args[0].equalsIgnoreCase("spawn")) {	
	Main.cfg_x1.set("x1.coords.spawn.world", w);
/* 40 */       Main.cfg_x1.set("x1.coords.spawn.x", x);
/* 41 */       Main.cfg_x1.set("x1.coords.spawn.y", y);
/* 42 */       Main.cfg_x1.set("x1.coords.spawn.z", z);
/* 43 */       Main.cfg_x1.set("x1.coords.spawn.yaw", yaw);
/* 44 */       Main.cfg_x1.set("x1.coords.spawn.pitch", pitch);
/*    */       try {
/* 46 */         Main.cfg_x1.save(Main.file_x1);
/* 47 */         Main.cfg_x1.load(Main.file_x1);
/*    */       } catch (IOException|InvalidConfigurationException e) {
/* 49 */         e.printStackTrace();
/*    */       }
/* 51 */       p.sendMessage("§a1V1 Spawn Setted with sucess!");
/* 52 */       return true; }
/* 53 */     if (args[0].equalsIgnoreCase("loc1")) {
	Main.cfg_x1.set("x1.coords.loc_1.world", w);
/* 54 */       Main.cfg_x1.set("x1.coords.loc_1.x", x);
/* 55 */       Main.cfg_x1.set("x1.coords.loc_1.y", y);
/* 56 */       Main.cfg_x1.set("x1.coords.loc_1.z", z);
/* 57 */       Main.cfg_x1.set("x1.coords.loc_1.yaw", yaw);
/* 58 */       Main.cfg_x1.set("x1.coords.loc_1.pitch", pitch);
/*    */       try {
/* 60 */         Main.cfg_x1.save(Main.file_x1);
/* 61 */         Main.cfg_x1.load(Main.file_x1);
/*    */       } catch (IOException|InvalidConfigurationException e) {
/* 63 */         e.printStackTrace();
/*    */       }
/* 65 */       p.sendMessage("§aFirst Player Location Setted with sucess!");
/* 66 */       return true; }
/* 67 */     if (args[0].equalsIgnoreCase("loc2")) {
	Main.cfg_x1.set("x1.coords.loc_2.world", w);
/* 68 */       Main.cfg_x1.set("x1.coords.loc_2.x", x);
/* 69 */       Main.cfg_x1.set("x1.coords.loc_2.y", y);
/* 70 */       Main.cfg_x1.set("x1.coords.loc_2.z", z);
/* 71 */       Main.cfg_x1.set("x1.coords.loc_2.yaw", yaw);
/* 72 */       Main.cfg_x1.set("x1.coords.loc_2.pitch", pitch);
/*    */       try {
/* 74 */         Main.cfg_x1.save(Main.file_x1);
/* 75 */         Main.cfg_x1.load(Main.file_x1);
/*    */       } catch (IOException|InvalidConfigurationException e) {
/* 77 */         e.printStackTrace();
/*    */       }
/* 79 */       p.sendMessage("§aSecond Player Location Setted with sucess!");
/* 80 */       return true;
/*    */     }
/* 82 */     p.sendMessage("§cCorrect Usage: /set1v1 <spawn/loc1/loc2>");
/*    */     
/*    */ 
/* 85 */     return false;
/*    */   }
/*    */ }


/* Location:              D:\Desktop\video\Minhas Coisas do Desktop\KP-PVPvB12 (1).jar!\me\RafaelAulerDeMeloAraujo\X1\SetX1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */
