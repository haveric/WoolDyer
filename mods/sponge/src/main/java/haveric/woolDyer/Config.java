package haveric.woolDyer;

import java.io.File;

import org.spongepowered.api.config.DefaultConfig;

import com.google.inject.Inject;

import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.loader.ConfigurationLoader;

public class Config {

    @Inject
    @DefaultConfig(sharedRoot = true)
    private ConfigurationLoader configManager;

    @Inject
    @DefaultConfig(sharedRoot = true)
    private File defaultConfig;

    private WoolDyer plugin;

    public Config(WoolDyer woolDyer) {
        plugin = woolDyer;

        ConfigurationNode config = null;
        /*
        try {
             if (!defaultConfig.exists()) {
                defaultConfig.createNewFile();
                config = configManager.load();

                config.getNode("version").setValue(1);
                config.getNode("doStuff").setValue(true);
                config.getNode("doMoreStuff").setValue(false);
                configManager.save(config);
            }
            config = configManager.load();

        } catch (IOException exception) {
            plugin.getLog().error("The default configuration could not be loaded or created!");
        }
        */
    }

    public static boolean canReplaceAll() {
        return true; // TODO: Replace with actual config
    }

}
