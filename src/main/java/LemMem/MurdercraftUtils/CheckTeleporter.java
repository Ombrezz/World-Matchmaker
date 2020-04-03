package LemMem.MurdercraftUtils;

import java.util.HashMap;

import org.bukkit.Location;

import org.bukkit.entity.Player;

public class CheckTeleporter {
    
    private HashMap<Player, Location> toTeleport = new HashMap<Player, Location>();

    public void Teleport(Player p, Location l) {
        toTeleport.put(p, l);
    }

    public void Update() {
        boolean e = true;
        while(e) {
            
        }
    }

}