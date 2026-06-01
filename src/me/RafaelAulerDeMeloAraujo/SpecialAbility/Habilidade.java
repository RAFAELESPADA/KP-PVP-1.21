package me.RafaelAulerDeMeloAraujo.SpecialAbility;



import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.Player;

import me.RafaelAulerDeMeloAraujo.main.Main;

public class Habilidade
{
    public static Map<String, String> powerMap;
    
    static {
        Habilidade.powerMap = new HashMap<String, String>();
    }
    
    public static String NomeDoKit(final String original) {
        if (original.length() == 0) {
            return original;
        }
        return String.valueOf(original.substring(0, 1).toUpperCase()) + original.substring(1);
    }
    
    public static String getAbility(final Player player) {
        if (!Habilidade.powerMap.containsKey(player.getName())) {
            Habilidade.powerMap.put(player.getName(), Main.getInstance().getConfig().getString("NoKit-DefaultName"));
        }
        return Habilidade.powerMap.get(player.getName());
    }
    
    public static void setAbility(final Player player, final String ability) {
        Habilidade.powerMap.put(player.getName(), ability);
       
        
    }
    public static boolean ContainsAbility(final Player player) {
    
    		 if (Habilidade.getAbility(player) != Main.getInstance().getConfig().getString("NoKit-DefaultName")) {
    			 return true;
    	            
    	        }
			return false;
    	
		
    }

    
    

    	
    	


    

    		
    	
    	
        
    
    
    public static void removeAbility(final Player p) {
        Habilidade.powerMap.remove(p.getName());
        
    }
}

