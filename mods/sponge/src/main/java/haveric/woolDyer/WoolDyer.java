package haveric.woolDyer;


import org.slf4j.Logger;
import org.spongepowered.api.Game;
import org.spongepowered.api.event.Subscribe;
import org.spongepowered.api.event.state.ServerAboutToStartEvent;
import org.spongepowered.api.event.state.ServerStartingEvent;
import org.spongepowered.api.event.state.ServerStoppingEvent;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.plugin.PluginContainer;
import org.spongepowered.api.plugin.PluginManager;
import org.spongepowered.api.service.event.EventManager;

import com.google.common.base.Optional;


@Plugin(id = "WoolDyer", name = "Wool Dyer", version = "2.0")
public class WoolDyer {
    private Logger log;

    private Game game;
    @SuppressWarnings("unused")
    private Commands commands;
    private Config config;

    @Subscribe
    public void preStartup(ServerAboutToStartEvent event) {
        log.info("Pre startup");
        game = event.getGame();
        PluginManager pm = game.getPluginManager();

        Optional<PluginContainer> optionalContainer = pm.fromInstance(this);
        if (optionalContainer.isPresent()) {
            PluginContainer pc = optionalContainer.get();
            log = pm.getLogger(pc);
        }
    }

    @Subscribe
    public void onStartup(ServerStartingEvent event) {
        log.info("Starting");
        EventManager em = game.getEventManager();
        em.register(this, new WDPlayerInteract(this));

        commands = new Commands(this);
        config = new Config(this);
    }

    @Subscribe
    public void onShutdown(ServerStoppingEvent event) {
        log.info("Shutdown");
    }

    public Game getGame() {
        return game;
    }

    public Logger getLog() {
        return log;
    }

    // TODO: Replace with Plugin annotation's id/name when possible
    public String getName() {
        return "WoolDyer";
    }

    // TODO: Replace with Plugin annotation's version when possible
    public String getVersion() {
        return "2.0";
    }
}