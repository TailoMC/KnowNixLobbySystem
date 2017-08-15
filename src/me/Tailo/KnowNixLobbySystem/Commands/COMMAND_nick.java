package me.Tailo.KnowNixLobbySystem.Commands;

import me.Tailo.KnowNixLobbySystem.Methoden.Inventory_methoden;
import me.Tailo.KnowNixLobbySystem.Methoden.NickItem_methoden;
import me.Tailo.KnowNixLobbySystem.System.main;
import me.Tailo.NickSystem.Nick.NickAPI;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class COMMAND_nick implements CommandExecutor {

	@SuppressWarnings("unused")
	private main plugin;
	private String prefix = "§5Nick §8» §r";
	
	public COMMAND_nick(main main) {
		this.plugin = main;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(sender instanceof Player) {
			
			if(sender.hasPermission("premiumplus")) {
				
				Player p = (Player) sender;
				
				if(NickAPI.isNicked(p)) {
					
					Inventory_methoden.setBoots(p);
					
					NickAPI.unnick(p);
					
					p.sendMessage(prefix + "§4Dein Nick wurde zurückgesetzt!");
					
				} else {
					
					NickItem_methoden.nick(p);
					
				}
				
				
			}
			
		} else {
			sender.sendMessage("Dieser Befehl kann nur als Spieler ausgeführt werden!");
		}
				
		return true;
	}

}
