package space.minota.skriptclient;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import net.mineaus.lunar.api.LunarClientAPI;
import net.mineaus.lunar.api.type.Notification;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import java.io.IOException;

public class EffLunarSendNotification extends Effect {


    private Expression<Player> player;
    private Expression<String> message;
    private Expression<Integer> seconds;


    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        this.player = (Expression<Player>) expressions[0];
        this.message = (Expression<String>) expressions[1];
        this.seconds = (Expression<Integer>) expressions[2];
        return true;
    }

    @Override
    public String toString(Event event, boolean debug) {
        return "Notification effect with expression player: " + player.toString(event, debug) + " and message: " + message.toString(event, debug);
    }

    @Override
    protected void execute(Event event) {
        if (player == null) return;
        try {
            LunarClientAPI.INSTANCE().sendNotification(player.getSingle(event), message.getSingle(event), Notification.INFO, seconds.getSingle(event));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
