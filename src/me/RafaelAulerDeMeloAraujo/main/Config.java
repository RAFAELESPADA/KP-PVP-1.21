
package me.RafaelAulerDeMeloAraujo.main;

public class Config
{
  private static Main main;
  
  public Config(Main main)
  {

  }
  
  public static String tr(String s)
  {
    return String.valueOf(main.getConfig().getString("Prefix").replace("&", "§"));
  }
}
