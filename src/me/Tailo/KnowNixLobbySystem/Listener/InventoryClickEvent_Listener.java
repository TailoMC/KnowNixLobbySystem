package me.Tailo.KnowNixLobbySystem.Listener;

import me.Tailo.KnowNixLobbySystem.System.main;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryClickEvent_Listener implements Listener {

	private main plugin;

	public InventoryClickEvent_Listener(main main) {
		this.plugin = main;
		plugin.getServer().getPluginManager().registerEvents(this, main);
	}
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {	
		
		e.setCancelled(true);
		
		Player p = (Player) e.getWhoClicked();
		
		if(plugin.build.contains(p)) {
			e.setCancelled(false);
		}
		
	}	
	
}
