package space.minota.skriptclient;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import com.google.gson.JsonObject;
import net.labymod.serverapi.bukkit.LabyModPlugin;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

public class EffLabyRecommendAddon extends Effect {

    private Expression<Player> player;
    private Expression<String> uuid;
    private Expression<Boolean> required;


    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        this.player = (Expression<Player>) expressions[0];
        this.uuid = (Expression<String>) expressions[1];
        this.required = (Expression<Boolean>) expressions[2];
        return true;
    }

    @Override
    public String toString(Event event, boolean debug) {
        return "Recommend addon to player: " + player.toString(event, debug) + " with UUID: " + uuid.toString(event, debug);
    }

    @Override
    protected void execute(Event event) {
        if (player == null) return;
        JsonObject object = new JsonObject();
        object.addProperty("uuid", uuid.getSingle(event));
        object.addProperty("required", required.getSingle(event));
        LabyModPlugin.getInstance().sendServerMessage( player.getSingle(event), "addon_recommendation", object );
    }

}
