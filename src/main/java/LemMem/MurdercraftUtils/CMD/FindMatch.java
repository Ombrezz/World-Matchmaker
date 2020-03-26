package LemMem.MurdercraftUtils.CMD;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import LemMem.MurdercraftUtils.App;

public class FindMatch implements CommandExecutor {

	// Command init
	private App app;
		
	public FindMatch(App _app)
	{
		app = _app;
	}
	
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
		
		// Put player in queue
		app.match.AddPlayerToQueue(p);
		
		return true;
	}

}
