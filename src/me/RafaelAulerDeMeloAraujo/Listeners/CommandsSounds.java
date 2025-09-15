/*    */ package me.RafaelAulerDeMeloAraujo.Listeners;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import me.RafaelAulerDeMeloAraujo.SpecialAbility.NewKitMenu;
/*    */ 
/*    */ import me.RafaelAulerDeMeloAraujo.main.Main;
/*    */ 
/*    */ 
/*    */ public class CommandsSounds
/*    */   implements Listener
/*    */ {
/*    */   private Main main;
/*    */   
/*    */   public CommandsSounds(Main main)
/*    */   {
/* 19 */     this.main = main;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   @EventHandler
/*    */   public void writeCommands(PlayerCommandPreprocessEvent e)
/*    */   {
/* 29 */     if (this.main.getConfig().getString("EnableSoundsToAllCommands").equalsIgnoreCase("true")) {
/* 30 */      
	/*  54 */         NewKitMenu.playSound(e.getPlayer(), "UI_BUTTON_CLICK", 1.0F, 1.0F);
/*    */   }
}
/*    */ }


/* Location:              D:\Desktop\video\Minhas Coisas do Desktop\KP-PVPvB12 (1).jar!\me\RafaelAulerDeMeloAraujo\Listeners\CommandsSounds.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */
