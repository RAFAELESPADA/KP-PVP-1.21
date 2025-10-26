package me.RafaelAulerDeMeloAraujo.ScoreboardManager;

import java.util.List;

import org.bukkit.entity.Player;

import me.RafaelAulerDeMeloAraujo.Coins.XP;
import me.RafaelAulerDeMeloAraujo.main.Main;


public class Level
{
    public static Integer getLevel(final Player p) {
        final int a = XP.getXP(p);
        if (a == 0.0) {
            return 0;
        }
        if (a > 0.0 && a % Main.customization.getInt("XP-Required-To-LevelUP") >= 0) {
            return a/Main.customization.getInt("XP-Required-To-LevelUP");
        }
    return 0;
}
    public static final int getXPToLevelUp(Player p) {
        // How much XP is needed to level?
       return (Main.customization.getInt("XP-Required-To-LevelUP") - (XP.getXP(p) - Main.customization.getInt("XP-Required-To-LevelUP") * getLevel(p))); // 600XP for lvl1, 700XP for lvl2, 800XP for lvl3 ...
   }
    public static final List<Integer> getXPAllLevels() {
        // How much XP is needed to level?
       return (Main.customization.getIntegerList("Levels.Levels.")); // 600XP for lvl1, 700XP for lvl2, 800XP for lvl3 ...
   }
public static String getPlayerLevelPrefix(Player username) {
    String playerLevel = String.valueOf(getLevel(username));
    return Main.customization.getString("Levels.Levels." + playerLevel + ".Prefix")
            .replace("%level%", playerLevel).replace("&", "§");
}
}
//1799
//0
