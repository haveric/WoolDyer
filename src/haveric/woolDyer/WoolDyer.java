package haveric.woolDyer;

import haveric.woolDyer.blockLogger.BlockLogger;
import haveric.woolDyer.guard.Guard;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import net.coreprotect.CoreProtect;
import net.coreprotect.CoreProtectAPI;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.palmergames.bukkit.towny.Towny;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;

import de.diddiz.LogBlock.Consumer;
import de.diddiz.LogBlock.LogBlock;

public class WoolDyer extends JavaPlugin {
    private static Logger log;

    private Commands commands = new Commands(this);

    // Config variables
    private FileConfiguration config;
    private File configFile;

    private final static boolean REPLACE_ALL_DEFAULT = true;

    @Override
    public void onEnable() {
        log = getLogger();

        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new WDPlayerInteract(this), this);

        // create a new config file
        configFile = new File(getDataFolder() + "/config.yml");

        if (!configFile.exists()) {
            setupConfig();
        } else {
            config = YamlConfiguration.loadConfiguration(configFile);
        }

        // WorldGuard
        setupWorldGuard(pm);
        // Towny
        setupTowny(pm);
        // CoreProtect
        setupCoreProtect(pm);
        // LogBlock
        setupLogBlock(pm);

        getCommand(commands.getMain()).setExecutor(commands);
    }

    @Override
    public void onDisable() {

    }

    private void setupWorldGuard(PluginManager pm) {
        Plugin worldGuard = pm.getPlugin("WorldGuard");
        if (worldGuard == null || !(worldGuard instanceof WorldGuardPlugin)) {
            log.info("WorldGuard not found.");
        } else {
            Guard.setWorldGuard((WorldGuardPlugin) worldGuard);
        }
    }

    private void setupTowny(PluginManager pm) {
        Plugin towny = pm.getPlugin("Towny");
        if (towny ==  null || !(towny instanceof Towny)) {
            log.info("Towny not found.");
        } else {
            Guard.setTowny((Towny) towny);
        }
    }

    private void setupCoreProtect(PluginManager pm) {
        Plugin coreProtect = pm.getPlugin("CoreProtect");
        if (coreProtect == null || !(coreProtect instanceof CoreProtect)) {
            // CoreProtect not found
        } else {
            CoreProtectAPI coreProtectAPI = ((CoreProtect) coreProtect).getAPI();
            BlockLogger.setCoreProtectAPI(coreProtectAPI);
        }
    }

    private void setupLogBlock(PluginManager pm) {
        Plugin logBlock = pm.getPlugin("LogBlock");
        if (logBlock == null || !(logBlock instanceof LogBlock)) {
            // LogBlock not found
        } else {
            Consumer logBlockConsumer = ((LogBlock) logBlock).getConsumer();
            BlockLogger.setLogBlockConsumer(logBlockConsumer);
        }
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
