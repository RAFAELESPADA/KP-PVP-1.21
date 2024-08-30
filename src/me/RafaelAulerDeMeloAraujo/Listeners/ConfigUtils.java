/*    */ package me.RafaelAulerDeMeloAraujo.Listeners;
/*    */ 
/*    */ 
/*    */ import me.RafaelAulerDeMeloAraujo.main.Main;
/*    */ 
/*    */ 
/*    */ public class ConfigUtils
/*    */ {
/*    */   private Main pl;
/*    */   
/*    */   public boolean loadConfig()
/*    */   {
/* 13 */     java.io.File pluginFolder = new java.io.File("plugins" + System.getProperty("file.separator") + Main.pluginName);
/* 14 */     if (!pluginFolder.exists()) {
/* 15 */       pluginFolder.mkdir();
/*    */     }
/* 17 */     java.io.File configFile = new java.io.File("plugins" + System.getProperty("file.separator") + Main.pluginName + System.getProperty("file.separator") + "config.yml");
/* 18 */     if (!configFile.exists())
/*    */     {
/* 20 */       Main.log.info("No config file found! Creating new one...");
/* 21 */       this.pl.saveDefaultConfig();
/*    */     }
/*    */     try
/*    */     {
/* 25 */       Main.log.info("Loading the config file...");
/* 26 */       this.pl.getConfig().load(configFile);
/* 27 */       Main.log.info("Config file loaded!");
/* 28 */       return true;
/*    */     }
/*    */     catch (Exception e)
/*    */     {
/* 32 */       Main.log.info("Could not load config file! You need to regenerate the config! Error: " + e.getMessage());
/* 33 */       e.printStackTrace();
/*    */     }
/* 35 */     return false;
/*    */   }
/*    */   
/*    */   public java.util.List<String> getStringList(String key) {
/* 39 */     if (!this.pl.getConfig().contains(key))
/*    */     {
/* 41 */       this.pl.getLogger().severe("Could not locate '" + key + "' in the config.yml inside of the " + Main.pluginName + " folder! (Try generating a new one by deleting the current)");
/* 42 */       return null;
/*    */     }
/* 44 */     return this.pl.getConfig().getStringList(key);
/*    */   }
/*    */   
/*    */   public String getString(String key)
/*    */   {
/* 49 */     if (!this.pl.getConfig().contains(key))
/*    */     {
/* 51 */       this.pl.getLogger().severe("Could not locate " + key + " in the config.yml inside of the " + Main.pluginName + " folder! (Try generating a new one by deleting the current)");
/* 52 */       return "errorCouldNotLocateInConfigYml:" + key;
/*    */     }
/* 54 */     return this.pl.getConfig().getString(key);
/*    */   }
/*    */   
/*    */   public String getStringWithColor(String key)
/*    */   {
/* 59 */     if (!this.pl.getConfig().contains(key))
/*    */     {
/* 61 */       this.pl.getLogger().severe("Could not locate " + key + " in the config.yml inside of the " + Main.pluginName + " folder! (Try generating a new one by deleting the current)");
/* 62 */       return "errorCouldNotLocateInConfigYml:" + key;
/*    */     }
/* 64 */     return this.pl.getConfig().getString(key).replaceAll("&", "§");
/*    */   }
/*    */ }


/* Location:              D:\Desktop\video\Minhas Coisas do Desktop\KP-PVPvB12 (1).jar!\me\RafaelAulerDeMeloAraujo\Listeners\ConfigUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */
