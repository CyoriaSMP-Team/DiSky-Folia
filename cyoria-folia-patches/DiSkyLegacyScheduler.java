package net.itsthesky.disky;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.scheduler.BukkitScheduler;
/** Method adapter for legacy binary methods that discard BukkitTask. */
public final class DiSkyLegacyScheduler {
 private DiSkyLegacyScheduler() {}
 public static BukkitTask runTask(BukkitScheduler ignored, Plugin plugin, Runnable r) {
    DiSkyFoliaScheduler.runGlobal(plugin,r);
    return null;
 }
 public static BukkitTask runTaskAsynchronously(BukkitScheduler ignored, Plugin plugin, Runnable r) {
    DiSkyFoliaScheduler.runAsync(plugin,r);
    return null;
 }
}
