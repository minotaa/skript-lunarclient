package space.minota.skriptlunarclient;

import ch.njol.skript.Skript;
import ch.njol.skript.SkriptAddon;
import ch.njol.skript.lang.ExpressionType;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private SkriptAddon skriptAddon;
    private static Main main;

    public void onEnable() {
        if (Bukkit.getPluginManager().getPlugin("Skript") != null) {
            main = this;
            this.skriptAddon = Skript.registerAddon(this);
            Skript.registerCondition(CondLunarAuthenticated.class, "%player% is ([auth[enticated] with]|on) [lunar [client]]");
            Skript.registerEffect(EffSendCooldown.class, "send [lunar] cooldown to %player% called %string% with %itemstack% for %integer% [(milliseconds|ms)]");
            Skript.registerEffect(EffSendNotification.class, "send [lunar] notification to %player% with message %string% delayed by %integer% [seconds]");

        } else {
            Bukkit.getLogger().warning("skript-lunarclient requires Skript, otherwise, the plugin will not add the effects!");
        }

        if (Bukkit.getPluginManager().getPlugin("LunarClientAPI") == null) {
            Bukkit.getLogger().warning("skript-lunarclient requires the Lunar Client API, otherwise, the plugin will not function correctly.");
        }

        Bukkit.getLogger().info("skript-lunarclient is now enabled!");
    }
}
