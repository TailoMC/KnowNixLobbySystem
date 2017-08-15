package me.Tailo.KnowNixLobbySystem.Listener;

import me.Tailo.KnowNixLobbySystem.System.main;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

public class CreatureSpawnEvent_Listener implements Listener {

	private main plugin;

	public CreatureSpawnEvent_Listener(main main) {
		this.plugin = main;
		plugin.getServer().getPluginManager().registerEvents(this, main);
	}
	
	@EventHandler
	public void onCreatureSpawn(CreatureSpawnEvent e) {
		e.setCancelled(true);
	}

}
