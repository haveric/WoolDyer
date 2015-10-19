package haveric.woolDyer;


import org.slf4j.Logger;
import org.spongepowered.api.Game;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameAboutToStartServerEvent;
import org.spongepowered.api.event.game.state.GameStartingServerEvent;
import org.spongepowered.api.event.game.state.GameStoppingServerEvent;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.plugin.PluginManager;
import org.spongepowered.api.service.event.EventManager;

import com.google.inject.Inject;


@Plugin(id = "WoolDyer", name = "Wool Dyer", version = "2.0")
public class WoolDyer {
    @Inject
    private Logger log;

    private Game game;
    @SuppressWarnings("unused")
    private Commands commands;
    private Config config;

    @Listener
    public void preStartup(GameAboutToStartServerEvent event) {
        game = event.getGame();
        PluginManager pm = game.getPluginManager();
    }

    @Listener
    public void onStartup(GameStartingServerEvent event) {
        EventManager em = game.getEventManager();
        em.registerListeners(this, new WDPlayerInteract(this));

        commands = new Commands(this);
        config = new Config(this);
    }

    @Listener
    public void onShutdown(GameStoppingServerEvent event) {

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
