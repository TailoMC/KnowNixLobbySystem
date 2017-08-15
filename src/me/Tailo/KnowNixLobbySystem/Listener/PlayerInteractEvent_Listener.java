package me.Tailo.KnowNixLobbySystem.Listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import me.Tailo.KnowNixLobbySystem.System.main;

public class PlayerInteractEvent_Listener implements Listener {

	private main plugin;

	public PlayerInteractEvent_Listener(main main) {
		this.plugin = main;
		plugin.getServer().getPluginManager().registerEvents(this, main);
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		
		e.setCancelled(true);
		
		Player p = e.getPlayer();
		
		if(plugin.build.contains(p)) {
			e.setCancelled(false);
		}
		
	}

}
