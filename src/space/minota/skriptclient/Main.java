package space.minota.skriptclient;

import ch.njol.skript.Skript;
import ch.njol.skript.SkriptAddon;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private SkriptAddon skriptAddon;
    private static Main main;

    public void onEnable() {
        if (Bukkit.getPluginManager().getPlugin("Skript") != null) {
            main = this;
            this.skriptAddon = Skript.registerAddon(this);
            if (Bukkit.getPluginManager().getPlugin("LunarClientAPI") == null) {
                Bukkit.getLogger().warning("skript-client requires the Lunar Client API, otherwise, the plugin will not function correctly.");
            } else {
                Skript.registerCondition(CondLunarAuthenticated.class, "%player% is ([auth[enticated] with]|on) [lunar [client]]");
                Skript.registerEffect(EffLunarSendCooldown.class, "send [lunar] cooldown to %player% called %string% with %itemstack% for %integer% [(milliseconds|ms)]");
                Skript.registerEffect(EffLunarSendNotification.class, "send [lunar] notification to %player% with message %string% delayed by %integer% [seconds]");
                Skript.registerEffect(EffLunarUpdateServerName.class, "update [lunar] server name of %player% to %string%");
            }

            if (Bukkit.getPluginManager().getPlugin("LabyModAPI") == null) {
                Bukkit.getLogger().warning("skript-client depends on the LabyMod Server API for its LabyMod effects, you don't have this however and they will not be loaded.");
            } else {
                Skript.registerEffect(EffLabySendGamemode.class, "send [laby[mod]] server gamemode of %player% to %string% with visibility %boolean%");
                Skript.registerEffect(EffLabyRecommendAddon.class, "recommend [laby[mod]] addon to %player% with uuid %string% with required %boolean%");
                Skript.registerEffect(EffLabyServerSwitch.class, "send [laby[mod]] server switch to %player% with title %string% with address %string% with preview %boolean%");
            }
        } else {
            Bukkit.getLogger().warning("skript-client requires Skript, otherwise, the plugin will not add the effects!");
        }

        Bukkit.getLogger().info("skript-client is now enabled!");
    }
}
