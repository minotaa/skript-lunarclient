package space.minota.skriptclient;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import com.google.gson.JsonObject;
import net.labymod.serverapi.bukkit.LabyModPlugin;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

public class EffLabySendGamemode extends Effect {

    private Expression<Player> player;
    private Expression<String> name;
    private Expression<Boolean> visible;


    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        this.player = (Expression<Player>) expressions[0];
        this.name = (Expression<String>) expressions[1];
        this.visible = (Expression<Boolean>) expressions[2];
        return true;
    }

    @Override
    public String toString(Event event, boolean debug) {
        return "Send server name effect to: " + player.toString(event, debug) + " with name: " + name.toString(event, debug);
    }

    @Override
    protected void execute(Event event) {
        if (player == null) return;
        JsonObject object = new JsonObject();
        object.addProperty("show_gamemode", visible.getSingle(event));
        object.addProperty("gamemode_name", name.getSingle(event));
        LabyModPlugin.getInstance().sendServerMessage( player.getSingle(event), "server_gamemode", object );
    }

}
