package me.Tailo.KnowNixLobbySystem.Listener;

import me.Tailo.KnowNixLobbySystem.System.main;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerExpChangeEvent;

public class PlayerExpChangeEvent_Listener implements Listener {

	private main plugin;

	public PlayerExpChangeEvent_Listener(main main) {
		this.plugin = main;
		plugin.getServer().getPluginManager().registerEvents(this, main);
	}
	
	@EventHandler
	public void onPlayerExpChange(PlayerExpChangeEvent e) {
		e.setAmount(0);
	}

}
