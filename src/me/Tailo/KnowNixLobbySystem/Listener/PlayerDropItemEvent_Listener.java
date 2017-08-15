package me.Tailo.KnowNixLobbySystem.Listener;

import me.Tailo.KnowNixLobbySystem.System.main;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class PlayerDropItemEvent_Listener implements Listener {

	private main plugin;

	public PlayerDropItemEvent_Listener(main main) {
		this.plugin = main;
		plugin.getServer().getPluginManager().registerEvents(this, main);
	}
	
	@EventHandler
	public void onPlayerDropItem(PlayerDropItemEvent e) {
		e.setCancelled(true);
	}

}
