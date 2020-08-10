package space.minota.skriptclient;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import net.mineaus.lunar.api.LunarClientAPI;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import java.io.IOException;

public class EffLunarUpdateServerName extends Effect {

    private Expression<Player> player;
    private Expression<String> name;


    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        this.player = (Expression<Player>) expressions[0];
        this.name = (Expression<String>) expressions[1];
        return true;
    }

    @Override
    public String toString(Event event, boolean debug) {
        return "Update server name effect with expression player: " + player.toString(event, debug) + " and name: " + name.toString(event, debug);
    }

    @Override
    protected void execute(Event event) {
        if (player == null) return;
        try {
            LunarClientAPI.INSTANCE().updateServerName(player.getSingle(event), name.getSingle(event).toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
