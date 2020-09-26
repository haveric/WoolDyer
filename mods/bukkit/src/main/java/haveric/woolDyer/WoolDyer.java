package haveric.woolDyer;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class WoolDyer extends JavaPlugin {
    Logger log;

    private Commands commands = new Commands(this);

    // Config variables
    private FileConfiguration config;
    private File configFile;

    private static final boolean REPLACE_ALL_DEFAULT = true;

    @Override
    public void onEnable() {
        log = getLogger();

        Supports.init();

        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new WDPlayerInteract(this), this);

        // create a new config file
        configFile = new File(getDataFolder() + File.separator + "config.yml");

        if (!configFile.exists()) {
            setupConfig();
        } else {
            config = YamlConfiguration.loadConfiguration(configFile);
        }

        getCommand(commands.getMain()).setExecutor(commands);
    }

    @Override
    public void onDisable() {

    }

    private void setupConfig() {
        config = YamlConfiguration.loadConfiguration(configFile);
        config.set("replaceAll", REPLACE_ALL_DEFAULT);
        try {
            config.save(configFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean canReplaceAll() {
        return config.getBoolean("replaceAll");
    }
}
