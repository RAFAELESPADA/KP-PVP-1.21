
package me.RafaelAulerDeMeloAraujo.main;

import org.bukkit.configuration.file.FileConfiguration;

public class Config
{
  private static Main main;
  
  public Config(Main main)
  {
    main = main;
  }
  
  public static String tr(String s)
  {
    return String.valueOf(main.getConfig().getString("Prefix").replace("&", "§"));
  }
}
