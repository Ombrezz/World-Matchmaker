package LemMem.MurdercraftUtils;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import LemMem.MurdercraftUtils.CMD.Spectate;

public class App extends JavaPlugin
{
	public WorldUtilities worldUtils;
	public MatchMaker match;
	public FileConfiguration cfg = getConfig();
	
	@Override
	public void onEnable() {
		getCommand("spectate").setExecutor(new Spectate(this));
		worldUtils = new WorldUtilities();
		match = new MatchMaker(this);
		
		cfg.addDefault("MatchSize", 8);
		cfg.addDefault("IgnorePlayAndSpectate", "world");
		cfg.options().copyDefaults(true);
		saveConfig();
	}
}
