package me.Tailo.KnowNixLobbySystem.Listener;

import me.Tailo.KnowNixLobbySystem.System.main;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerEggThrowEvent;

public class PlayerEggThrowEvent_Listener implements Listener {

	private main plugin;

	public PlayerEggThrowEvent_Listener(main main) {
		this.plugin = main;
		plugin.getServer().getPluginManager().registerEvents(this, main);
	}
	
	@EventHandler
	public void onPlayerEggThrow(PlayerEggThrowEvent e) {
		e.getEgg().remove();
	}

}
