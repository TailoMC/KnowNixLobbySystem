package me.Tailo.KnowNixLobbySystem.Listener;

import me.Tailo.KnowNixLobbySystem.System.main;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.hanging.HangingBreakByEntityEvent;

public class HangingBreakByEntityEvent_Listener implements Listener {

	private main plugin;

	public HangingBreakByEntityEvent_Listener(main main) {
		this.plugin = main;
		plugin.getServer().getPluginManager().registerEvents(this, main);
	}
	
	@EventHandler
	public void onHangingBreakByEntity(HangingBreakByEntityEvent e) {
		e.setCancelled(true);
	}

}
