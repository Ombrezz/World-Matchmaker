package LemMem.MurdercraftUtils;

import java.util.HashMap;
import java.util.PriorityQueue;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class MatchMaker {
	private PriorityQueue<Player> Queued = new PriorityQueue<Player>();
	private HashMap<String, Match> Matches = new HashMap<String, Match>();
	private App app;
	
	int nextID = 1;
	
	
	public boolean running = true;
	
	public void stopFinding() {
		running = false;
	}
	
	public MatchMaker(App _app) {
		app = _app;
	}
	
	public void CheckMoreQueue() {
		int MatchSize = app.cfg.getInt("MatchSize");
		if(Queued.size() >= MatchSize) {
			Player[] blue = new Player[MatchSize/2];
			Player[] red = new Player[MatchSize/2];
			for(int i = 0; i < MatchSize/2; i++) {
				blue[i] = Queued.poll();
			}
			for(int i = MatchSize/2; i < MatchSize; i++) {
				red[i] = Queued.poll();
			}
			Match match = new Match(Bukkit.getWorld("map"), red, blue, nextID, app);
			Matches.put(Integer.toString(nextID), match);
			nextID++;
		}
	}
	
	public void EndMatch(int ID) {
		Matches.remove(Integer.toString(ID));
	}
	
	public void AddPlayerToQueue(Player _player) {
		Queued.add(_player);
	}
}
