package haveric.woolDyer;


import org.slf4j.Logger;
import org.spongepowered.api.Game;
import org.spongepowered.api.event.state.ServerStartingEvent;
import org.spongepowered.api.event.state.ServerStoppingEvent;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.service.event.EventManager;
import org.spongepowered.api.util.event.Subscribe;


@Plugin(id = "WoolDyer", name = "WoolDyer", version = "2.0")
public class WoolDyer {
    public Logger log;

    private Game game;

    @Subscribe
    public void onStartup(ServerStartingEvent event) {
        game = event.getGame();
        EventManager em = game.getEventManager();

        em.register(this, new WDPlayerInteract(this));
    }

    @Subscribe
    public void onShutdown(ServerStoppingEvent event) {

    }
}
