package LemMem.MurdercraftUtils;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class MatchMaker {
	
	// Setup variables
	private List<Player> Queued = new ArrayList<Player>();
	private HashMap<String, Match> Matches = new HashMap<String, Match>();
	private App app;
	
	int nextID = 1;
	
	
	public boolean running = true;
	
	// Stop match maker
	public void stopFinding() {
		running = false;
	}
	
	// Constructor
	public MatchMaker(App _app) {
		app = _app;
	}
	
	// Refresh the queue
	public void CheckMoreQueue() {
		int MatchSize = app.cfg.getInt("MatchSize");
		app.getLogger().log(Level.INFO, "CheckMoreQueue called.");
		app.getLogger().log(Level.INFO, Queued.toString());
		if(Queued.size() >= MatchSize) {
			Player[] blue = new Player[MatchSize/2];
			Player[] red = new Player[MatchSize/2];

			for (int i = 0; i < blue.length; i++) {
				blue[i] = Queued.remove(0);
				app.getLogger().log(Level.INFO, "Player " + blue[i].getDisplayName() + "added to team blue.");
			}

			for (int i = 0; i < red.length; i++) {
				red[i] = Queued.remove(0);
				app.getLogger().log(Level.INFO, "Player " + red[i].getDisplayName() + "added to team red.");
			}

			Match match = new Match(Bukkit.getWorld("map"), red, blue, nextID, app);
			app.getLogger().log(Level.INFO, "Match " + Integer.toString(nextID) + " has been created");
			Matches.put(Integer.toString(nextID), match);
		}
	}
	// End a match with a given ID
	public void EndMatch(int ID) {
		Matches.remove(Integer.toString(ID));
	}
	
	// Add a player to the queue
	public void AddPlayerToQueue(Player _player) {
		Queued.add(_player);
		app.getLogger().log(Level.INFO, "Added player " + _player.getDisplayName() + "to Queue.");
	}
}
