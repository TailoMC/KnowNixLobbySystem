package me.Tailo.KnowNixLobbySystem.Listener;

import me.Tailo.KnowNixLobbySystem.System.main;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class PlayerCommandPreprocessEvent_Listener implements Listener {

	private main plugin;

	public PlayerCommandPreprocessEvent_Listener(main main) {
		this.plugin = main;
		plugin.getServer().getPluginManager().registerEvents(this, main);
	}
	
	@EventHandler
	public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent e) {
		
		if(e.getMessage().toLowerCase().startsWith("/plugins") || e.getMessage().toLowerCase().startsWith("/pl")) {
			if(e.getPlayer().hasPermission("dev")) {
				e.setCancelled(true);
				e.getPlayer().sendMessage(plugin.prefix + "§3Du hast keinen Zugriff auf unsere §6Plugins§3!");
			}
			
			return;
		}	
	}
}
