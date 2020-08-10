package space.minota.skriptclient;

import ch.njol.skript.doc.*;

import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import net.mineaus.lunar.api.LunarClientAPI;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

@Name("Lunar Client Authenticated")
@Description("Tests if the player is authenticated with Lunar Client.")

@Since("")
public class CondLunarAuthenticated extends Condition {

    Expression<Player> player;

    @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        this.player = (Expression<Player>) expressions[0];
        setNegated(parser.mark == 1);
        return true;
    }

    @Override
    public String toString(Event event, boolean debug) {
        return "Player is " + player.toString(event, debug);
    }

    @Override
    public boolean check(Event event) {
        Player p = player.getSingle(event);
        if (p == null) return isNegated();
        return LunarClientAPI.INSTANCE().isAuthenticated(p);
    }


}
