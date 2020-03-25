package LemMem.MurdercraftUtils.CMD;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import LemMem.MurdercraftUtils.App;

public class Spectate implements CommandExecutor {

	private App app;
	
	public Spectate(App _app) {
		app = _app;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Player p;
		if(sender instanceof Player) {
			p = (Player)sender;
		} else {
			sender.sendMessage("Only players can use this command!");
			return true;
		}
		if(p.getWorld() == Bukkit.getWorld(app.cfg.getString("IgnorePlayAndSpectate"))) {
			
		}
		p.setGameMode(GameMode.SPECTATOR);
		sender.sendMessage("You are now Spectating the Match! Use /Play to play instead!");
		return true;
	}

}
