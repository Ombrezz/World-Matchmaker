package LemMem.MurdercraftUtils;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class Match {
	World matchWorld;
	Player[] redPlayers;
	Player[] bluePlayers;
	App app;
	int ID;
	
	public Match(World _matworl, Player[] redPLR, Player[] bluPLR, int _id, App _app) {
		redPlayers = redPLR;
		bluePlayers = bluPLR;
		app = _app;
		ID = _id;
		
		Location redSpawn = new Location(matchWorld, 0,0,0);
		Location blueSpawn = new Location(matchWorld, 0,0,0);
		for (int i = 0; i < redPLR.length; i++) {
			redPLR[i].teleport(redSpawn);
		}
		
		for (int i = 0; i < bluPLR.length; i++) {
			bluPLR[i].teleport(blueSpawn);
		}
		
		matchWorld = app.worldUtils.CopyWorld(_matworl, Integer.toString(ID));
	}
	
	public void EndMatch() {
		for (Player player : bluePlayers) {
			Disconnect(player);
		}
		for (Player player : redPlayers) {
			Disconnect(player);
		}
		
		app.worldUtils.DestroyWorld(matchWorld);
	}
	
	public Player[] getRedTeam() {
		return redPlayers;
	}
	public Player[] getBlueTeam() {
		return bluePlayers;
	}
	
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
