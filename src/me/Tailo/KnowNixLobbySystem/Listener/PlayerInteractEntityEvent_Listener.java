package me.Tailo.KnowNixLobbySystem.Listener;

import me.Tailo.KnowNixLobbySystem.System.main;

import org.bukkit.entity.Hanging;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class PlayerInteractEntityEvent_Listener implements Listener {

	private main plugin;

	public PlayerInteractEntityEvent_Listener(main main) {
		this.plugin = main;
		plugin.getServer().getPluginManager().registerEvents(this, main);
	}
	
	@EventHandler
	public void onPlayerIteractEntity(PlayerInteractEntityEvent e) {
		
		if(e.getRightClicked() instanceof Hanging) {
			e.setCancelled(true);
			if(plugin.build.contains(e.getPlayer())) {
				e.setCancelled(false);
			}
		}
		
	}

}
