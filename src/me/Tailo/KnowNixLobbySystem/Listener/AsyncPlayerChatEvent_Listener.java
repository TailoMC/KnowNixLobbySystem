package me.Tailo.KnowNixLobbySystem.Listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import me.Tailo.KnowNixLobbySystem.System.main;

public class AsyncPlayerChatEvent_Listener implements Listener {

	private main plugin;

	public AsyncPlayerChatEvent_Listener(main main) {
		this.plugin = main;
		plugin.getServer().getPluginManager().registerEvents(this, main);
	}
	
	@EventHandler
	public void onAsyncPlayerChat(AsyncPlayerChatEvent e) {
		
		e.setFormat("%1$s §8» §r%2$s");
		
	}

}
