package me.Tailo.KnowNixLobbySystem.Listener;

import me.Tailo.KnowNixLobbySystem.Methoden.InfoBoard;
import me.Tailo.KnowNixLobbySystem.System.main;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitEvent_Listener implements Listener {

	private main plugin;

	public PlayerQuitEvent_Listener(main main) {
		this.plugin = main;
		plugin.getServer().getPluginManager().registerEvents(this, main);
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		
		e.setQuitMessage("");
		
		InfoBoard.getInfoBoard(e.getPlayer()).removeBoard();
		
	}

}
