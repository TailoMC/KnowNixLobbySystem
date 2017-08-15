package me.Tailo.KnowNixLobbySystem.Commands;

import me.Tailo.KnowNixLobbySystem.System.main;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class COMMAND_build implements CommandExecutor {

	private main plugin;

	public COMMAND_build(main main) {
		this.plugin = main;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(sender.hasPermission("admin")) {
			
			if(args.length != 1) {
				
				if(sender instanceof Player ) {
					
					Player p = (Player) sender;
					
					if(plugin.build.contains(p)) {
						plugin.build.remove(p);
						
						restoreInventory(p);
						
						p.setGameMode(GameMode.SURVIVAL);
						
						p.sendMessage(plugin.prefix + "§cDu kannst jetzt nicht mehr bauen!");
					} else {
						plugin.build.add(p);
						
						saveInventory(p);
						
						p.setGameMode(GameMode.CREATIVE);
						
						p.sendMessage(plugin.prefix + "§aDu kannst jetzt bauen!");
					}
					
				} else {
					
					sender.sendMessage("In der Console kann nur '/build <Spielername>' ausgeführt werden!");
					
				}
				
			} else {
				
				Player p = Bukkit.getPlayer(args[0]);
				
				if(p != null) {
					
					if(plugin.build.contains(p)) {
						plugin.build.remove(p);
						
						restoreInventory(p);
						
						p.setGameMode(GameMode.SURVIVAL);
						
						sender.sendMessage(plugin.prefix + p.getDisplayName() + " §ckann jetzt nicht mehr bauen!");
					} else {
						plugin.build.add(p);
						
						saveInventory(p);
						
						p.setGameMode(GameMode.CREATIVE);
						
						sender.sendMessage(plugin.prefix + p.getDisplayName() + " §akann jetzt bauen!");
					}
					
				}
				
			}
			
		}
		
		return true;
	}
	
	private void restoreInventory(Player p) {
		
		p.getInventory().setContents(plugin.inventory.get(p));
		
		p.getInventory().setArmorContents(plugin.armor.get(p));
		
		p.updateInventory();
		
	}
	
	private void saveInventory(Player p) {
		
		plugin.inventory.put(p, p.getInventory().getContents());
		plugin.armor.put(p, p.getInventory().getArmorContents());
		
		p.getInventory().clear();
		p.getInventory().setArmorContents(null);
		
	}

}
