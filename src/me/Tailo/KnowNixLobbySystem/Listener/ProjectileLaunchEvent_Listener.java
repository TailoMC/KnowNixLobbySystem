package me.Tailo.KnowNixLobbySystem.Listener;

import me.Tailo.KnowNixLobbySystem.System.main;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;

public class ProjectileLaunchEvent_Listener implements Listener {

	private main plugin;

	public ProjectileLaunchEvent_Listener(main main) {
		this.plugin = main;
		plugin.getServer().getPluginManager().registerEvents(this, main);
	}
	
	@EventHandler
	public void onProjectileLaunch(ProjectileLaunchEvent e) {
		e.setCancelled(true);
	}

}
