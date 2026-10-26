package net.itsthesky.disky;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.event.inventory.InventoryEvent;
import org.bukkit.event.entity.EntityEvent;
import org.bukkit.entity.HumanEntity;

public final class DiSkyFoliaScheduler {
 private DiSkyFoliaScheduler() { }
 public static void runAsync(Plugin plugin,Runnable r) {
   Bukkit.getAsyncScheduler().runNow(plugin,task -> r.run());
 }
 public static void runGlobal(Plugin plugin,Runnable r) {
   Bukkit.getGlobalRegionScheduler().execute(plugin,r);
 }
 public static void runForEvent(Plugin plugin,Event ev,Runnable r) {
   if(ev instanceof PlayerEvent p) {
     p.getPlayer().getScheduler().run(plugin,task -> r.run(),null);
   } else if(ev instanceof InventoryEvent i) {
     HumanEntity who=i.getView().getPlayer();
     who.getScheduler().run(plugin,task -> r.run(),null);
   } else if(ev instanceof EntityEvent e) {
     e.getEntity().getScheduler().run(plugin,task -> r.run(),null);
   } else {
     runGlobal(plugin,r);
   }
 }
}
