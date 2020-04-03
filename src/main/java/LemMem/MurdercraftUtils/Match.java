package LemMem.MurdercraftUtils;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class Match {
	
	// Setup Variables
	World matchWorld;
	Player[] redPlayers;
	Player[] bluePlayers;
	App app;
	int ID;
	
	// Constructor
	public Match(World _matworl, Player[] redPLR, Player[] bluPLR, int _id, App _app) {
		redPlayers = redPLR;
		bluePlayers = bluPLR;
		app = _app;
		ID = _id;
		
		matchWorld = app.worldUtils.CopyWorld(_matworl, Integer.toString(ID));

		Location redSpawn = new Location(matchWorld, 0,0,0);
		Location blueSpawn = new Location(matchWorld, 0,0,0);
		for (Player player : redPLR) {
			player.teleport(player, redSpawn);
		}
		
		for (Player player : bluPLR) {
			app.QueuedTeleport(player, blueSpawn);
		}
	}
	
	// End the current match
	public void EndMatch() {
		for (Player player : bluePlayers) {
			Disconnect(player);
		}
		for (Player player : redPlayers) {
			Disconnect(player);
		}
		
		app.worldUtils.DestroyWorld(matchWorld);
	}
	
	
	// Get the red team
	public Player[] getRedTeam() {
		return redPlayers;
	}
	
	// Get the blue team
	public Player[] getBlueTeam() {
		return bluePlayers;
	}
	
	// Disconnect a player from a match
	public void Disconnect(Player player) {
		for (int i = 0; i < bluePlayers.length; i++) {
			if(bluePlayers[i] == player) {
				bluePlayers[i] = null;
				return;
			}
		}
		for (int i = 0; i < redPlayers.length; i++) {
			if(redPlayers[i] == player) {
				redPlayers[i] = null;
				return;
			}
		}
	}
}
