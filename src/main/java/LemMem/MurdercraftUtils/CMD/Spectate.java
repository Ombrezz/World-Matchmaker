package LemMem.MurdercraftUtils.CMD;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import LemMem.MurdercraftUtils.App;

public class Spectate implements CommandExecutor {

	// Command init
	private App app;
	
	public Spectate(App _app) {
		app = _app;
	}
	
	// Command behavior
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		// Make sure command sender is player
		Player p;
		if(sender instanceof Player) {
			p = (Player)sender;
		} else {
			sender.sendMessage("Only players can use this command!");
			return true;
		}
		
		// Make player a spectator
		if(p.getWorld() == Bukkit.getWorld(app.cfg.getString("IgnorePlayAndSpectate"))) {
			
		}
		p.setGameMode(GameMode.SPECTATOR);
		sender.sendMessage("You are now Spectating the Match! Use /Play to play instead!");
		return true;
	}

}
