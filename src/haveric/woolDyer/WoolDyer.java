package haveric.woolDyer;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class WoolDyer extends JavaPlugin{
    final Logger log = Logger.getLogger("Minecraft");
    private final WDPlayerInteract playerInteract = new WDPlayerInteract(this);

    // Config variables
    public FileConfiguration config;
    public File configFile;
    
    final boolean REPLACE_ALL_DEFAULT = true; 
	
	@Override
	public void onEnable() {
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(playerInteract, this);

		// create a new config file
        configFile = new File(getDataFolder() + "/config.yml");
        
        if (!configFile.exists()){
        	setupConfig();
        } else {
        	config = YamlConfiguration.loadConfiguration(configFile);
        }
        
		log.info(String.format("[%s] v%s Started",getDescription().getName(), getDescription().getVersion()));
	}

	@Override
	public void onDisable() {
		log.info(String.format("[%s] Disabled",getDescription().getName()));
	}

	private void setupConfig(){
		config = YamlConfiguration.loadConfiguration(configFile);
		config.set("replaceAll", REPLACE_ALL_DEFAULT);
		try {
			config.save(configFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean canReplaceAll(){
		return config.getBoolean("replaceAll");
	}
}
