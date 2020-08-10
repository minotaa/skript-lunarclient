package space.minota.skriptclient;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import com.google.gson.JsonObject;
import net.labymod.serverapi.bukkit.LabyModPlugin;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

public class EffLabyServerSwitch extends Effect {

    private Expression<Player> player;
    private Expression<String> title;
    private Expression<String> address;
    private Expression<Boolean> preview;


    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        this.player = (Expression<Player>) expressions[0];
        this.title = (Expression<String>) expressions[1];
        this.address = (Expression<String>) expressions[2];
        this.preview = (Expression<Boolean>) expressions[3];
        return true;
    }

    @Override
    public String toString(Event event, boolean debug) {
        return "Server switch to player: " + player.toString(event, debug) + " with address: " + address.toString(event, debug) + " and title: " + title.toString(event, debug);
    }

    @Override
    protected void execute(Event event) {
        if (player == null) return;
        JsonObject object = new JsonObject();
        object.addProperty( "title", title.getSingle(event) ); // Title of the warning
        object.addProperty( "address", address.getSingle(event) ); // Destination server address
        object.addProperty( "preview", preview.getSingle(event) );
        LabyModPlugin.getInstance().sendServerMessage( player.getSingle(event), "server_switch", object );
    }

}
