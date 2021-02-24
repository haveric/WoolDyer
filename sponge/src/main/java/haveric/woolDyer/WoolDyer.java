package haveric.woolDyer;


import org.slf4j.Logger;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.event.EventManager;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameAboutToStartServerEvent;
import org.spongepowered.api.event.game.state.GameStartingServerEvent;
import org.spongepowered.api.event.game.state.GameStoppingServerEvent;
import org.spongepowered.api.plugin.Plugin;

import com.google.inject.Inject;


@Plugin(id = "WoolDyer", name = "Wool Dyer", version = "2.0")
public class WoolDyer {
    @Inject
    private Logger log;

    @SuppressWarnings("unused")
    private Commands commands;
    private Config config;

    @Listener
    public void preStartup(GameAboutToStartServerEvent event) {
    }

    @Listener
    public void onStartup(GameStartingServerEvent event) {
        EventManager em = Sponge.getEventManager();
        em.registerListeners(this, new WDPlayerInteract(this));

        commands = new Commands(this);
        config = new Config(this);
    }

    @Listener
    public void onShutdown(GameStoppingServerEvent event) {

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
