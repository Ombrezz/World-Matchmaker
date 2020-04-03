package LemMem.MurdercraftUtils;

import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import LemMem.MurdercraftUtils.CMD.FindMatch;

public class App extends JavaPlugin
{
	// Setup global variables
	public WorldUtilities worldUtils;
	public MatchMaker match;
	public FileConfiguration cfg;
	public int QueueCheckTiming;
	
	// Runs when the plugin is started
	@Override
	public void onEnable() 
	{
		// Setup config
		cfg = getConfig();
		
		cfg.addDefault("MatchSize", 8);
		cfg.addDefault("IgnorePlayAndSpectate", "world");
		cfg.addDefault("QueueCheckTiming", 30);
		cfg.options().copyDefaults(true);

		saveDefaultConfig();
		
		QueueCheckTiming = cfg.getInt("QueueCheckTiming") * 20;
		// Setup the QueueChecker
        BukkitScheduler scheduler = Bukkit.getServer().getScheduler();

        scheduler.runTaskTimer(this, new Runnable() {
            @Override
            public void run() {
    			match.CheckMoreQueue();
            }
		}, 0, QueueCheckTiming);
		
		//Initialize World Thingy and MatchMaker

		worldUtils = new WorldUtilities();
		
		if(!(cfg.getInt("MatchSize") % 2 == 0)) {
			Bukkit.getLogger().log(Level.SEVERE, "The MatchSize in the config file for MurdercraftUtils is odd, it must be even.");
		} else {
			match = new MatchMaker(this);
			getCommand("findmatch").setExecutor(new FindMatch(this));
		}	
	}
	
	// Runs when the plugin is stopped
	@Override
	public void onDisable()
	{
		// Stop the QueueChecker
	}
}
