package LemMem.MurdercraftUtils;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import LemMem.MurdercraftUtils.CMD.FindMatch;
import LemMem.MurdercraftUtils.CMD.Spectate;

public class App extends JavaPlugin
{
	// Setup global variables
	public WorldUtilities worldUtils;
	public MatchMaker match;
	public FileConfiguration cfg;
	public QueueChecker checker;
	
	// Runs when the plugin is started
	@Override
	public void onEnable() 
	{
		// Register commands
		getCommand("spectate").setExecutor(new Spectate(this));
		getCommand("findmatch").setExecutor(new FindMatch(this));
		
		// Assign variables
		worldUtils = new WorldUtilities();
		match = new MatchMaker(this);
		
		// Setup config
		cfg = getConfig();
		
		cfg.addDefault("MatchSize", 8);
		cfg.addDefault("IgnorePlayAndSpectate", "world");
		cfg.addDefault("QueueCheckTiming", 30);
		cfg.options().copyDefaults(true);
		
		saveConfig();

		// Setup the QueueChecker
		checker.initChecker(this);
		checker.start();
		
	}
	
	// Runs when the plugin is stopped
	@Override
	public void onDisable()
	{
		// Stop the QueueChecker
		checker.interrupt();
	}
}
