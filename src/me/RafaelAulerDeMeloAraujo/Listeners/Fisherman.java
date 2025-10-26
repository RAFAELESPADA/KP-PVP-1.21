/*    */ package me.RafaelAulerDeMeloAraujo.Listeners;

/*    */ 
/*    */ import me.RafaelAulerDeMeloAraujo.SpecialAbility.Habilidade;
import me.RafaelAulerDeMeloAraujo.SpecialAbility.Join;
/*    */ import me.RafaelAulerDeMeloAraujo.main.Main;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.Sound;
/*    */ 
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.player.PlayerFishEvent;
/*    */ 
/*    */ 
/*    */ public class Fisherman implements Listener
/*    */ {
/*    */   private Main main;
/*    */   
/*    */   public Fisherman(Main main)
/*    */   {
/* 20 */     this.main = main;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   @EventHandler
/*    */   public void onPlayerHitFishingrodscorpion(PlayerFishEvent event)
/*    */   {
/* 28 */     Player player = event.getPlayer();

/* 29 */     if ((event.getCaught() instanceof Player)) {
/* 30 */       Player caught = (Player)event.getCaught();
if (!Habilidade.ContainsAbility(caught) && Join.game.contains(caught.getName())) {
	event.setCancelled(true);
	return;
}
/* 31 */       if ((player.getItemInHand().getType() == Material.FISHING_ROD) && (Habilidade.getAbility(player) == "Fisherman")) {

/* 32 */         player.getWorld().playEffect(player.getLocation().add(0.0D, 1.5D, 0.0D), org.bukkit.Effect.MOBSPAWNER_FLAMES, 1);
/* 33 */         caught.teleport(player.getLocation());
/* 34 */         caught.playSound(caught.getLocation(), Sound.valueOf(this.main.getConfig().getString("Sound.Fisherman")), 1.0F, 1.0F);
/* 35 */         player.playSound(player.getLocation(), Sound.valueOf(this.main.getConfig().getString("Sound.Fisherman")), 1.0F, 1.0F);
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\Desktop\video\Minhas Coisas do Desktop\KP-PVPvB12 (1).jar!\me\RafaelAulerDeMeloAraujo\Listeners\Fisherman.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */
