package me.Tailo.KnowNixLobbySystem.Listener;

import me.Tailo.KnowNixLobbySystem.System.main;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;

public class PlayerFishEvent_Listener implements Listener {

	private main plugin;

	public PlayerFishEvent_Listener(main main) {
		this.plugin = main;
		plugin.getServer().getPluginManager().registerEvents(this, main);
	}
	
	@EventHandler
	public void onPlayerFish(PlayerFishEvent e) {
		e.setCancelled(true);
		e.setExpToDrop(0);
	}

}
