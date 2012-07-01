package haveric.woolDyer;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import net.milkbowl.vault.permission.Permission;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class WoolDyer extends JavaPlugin {
    final Logger log = Logger.getLogger("Minecraft");
    private final WDPlayerInteract playerInteract = new WDPlayerInteract(this);

    private Commands commands = new Commands(this);

    // Config variables
    private FileConfiguration config;
    private File configFile;

    private final static boolean REPLACE_ALL_DEFAULT = true;

    // Vault
    private Permission perm;

    @Override
    public void onEnable() {
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(playerInteract, this);

        // create a new config file
        configFile = new File(getDataFolder() + "/config.yml");

        if (!configFile.exists()) {
            setupConfig();
        } else {
            config = YamlConfiguration.loadConfiguration(configFile);
        }

        getCommand(Commands.getMain()).setExecutor(commands);

        // Vault
        setupVault();

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

    private void setupVault() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            log.info(String.format("[%s] Vault not found. Permissions disabled.", getDescription().getName()));
            return;
        }
        RegisteredServiceProvider<Permission> permProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.permission.Permission.class);
        if (permProvider != null) {
            perm = permProvider.getProvider();
        }
    }

    public Permission getPerm() {
        return perm;
    }
}
