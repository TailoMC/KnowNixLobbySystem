package me.Tailo.KnowNixLobbySystem.Listener;

import me.Tailo.KnowNixLobbySystem.System.main;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.WorldSaveEvent;

public class WorldSaveEvent_Listener implements Listener {

	private main plugin;

	public WorldSaveEvent_Listener(main main) {
		this.plugin = main;
		plugin.getServer().getPluginManager().registerEvents(this, main);
	}
	
	@EventHandler
	public void onWorldSave(WorldSaveEvent e) {
		e.getWorld().setAutoSave(false);
	}

}
