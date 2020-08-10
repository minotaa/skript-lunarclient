package space.minota.skriptclient;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import net.mineaus.lunar.api.LunarClientAPI;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;

import java.io.IOException;

public class EffLunarSendCooldown extends Effect {

    private Expression<Player> player;
    private Expression<String> message;
    private Expression<ItemStack> item;
    private Expression<Integer> milliseconds;


    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        this.player = (Expression<Player>) expressions[0];
        this.message = (Expression<String>) expressions[1];
        this.item = (Expression<ItemStack>) expressions[2];
        this.milliseconds = (Expression<Integer>) expressions[3];
        return true;
    }

    @Override
    public String toString(Event event, boolean debug) {
        return "Cooldown effect with expression player: " + player.toString(event, debug) + " and message: " + message.toString(event, debug) + " with itemstack expression: " + item.toString(event, debug);
    }

    @Override
    protected void execute(Event event) {
        if (player == null) return;
        try {
            LunarClientAPI.INSTANCE().sendCooldown(player.getSingle(event), message.getSingle(event), item.getSingle(event).getType(), milliseconds.getSingle(event).longValue());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
