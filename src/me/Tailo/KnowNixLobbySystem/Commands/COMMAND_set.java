package me.Tailo.KnowNixLobbySystem.Commands;

import me.Tailo.KnowNixLobbySystem.Methoden.Location_methoden;
import me.Tailo.KnowNixLobbySystem.System.main;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class COMMAND_set implements CommandExecutor {

	private main plugin;

	public COMMAND_set(main main) {
		this.plugin = main;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(sender instanceof Player) {
			
			Player p = (Player) sender;
			
			if(p.hasPermission("srdev")) {
				
				if(args.length == 1) {
					
					if(args[0].equalsIgnoreCase("spawn")) {
						Location_methoden.setSpawn(p);
					} else if(args[0].equalsIgnoreCase("knockout")) {
						Location_methoden.setKnockbackFFA(p);
					} else if(args[0].equalsIgnoreCase("bedwars")) {
						Location_methoden.setBedWars(p);
					} else if(args[0].equalsIgnoreCase("skywars")) {
						Location_methoden.setSkyWars(p);
					} else if(args[0].equalsIgnoreCase("community")) {
						Location_methoden.setCommunity(p);
					} else if(args[0].equalsIgnoreCase("qsg")) {
						Location_methoden.setQSG(p);
					} else {
						p.sendMessage(plugin.prefix + "§c/set [spawn/knockout/bedwars/skywars/community/qsg]");
					}
					
				} else {
					p.sendMessage(plugin.prefix + "§c/set [Location]");
				}
				
			}
			
		} else {
			sender.sendMessage("Dieser Befehl kann nur als Spieler ausgeführt werden!");
		}
		
		return true;
	}

}
