package me.Tailo.KnowNixLobbySystem.Listener;

import me.Tailo.KnowNixLobbySystem.System.main;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPortalEvent;

public class EntityPortalEvent_Listener implements Listener {

	private main plugin;

	public EntityPortalEvent_Listener(main main) {
		this.plugin = main;
		plugin.getServer().getPluginManager().registerEvents(this, main);
	}
	
	@EventHandler
	public void onEntityPortal(EntityPortalEvent e) {
		e.setCancelled(true);
	}
}
