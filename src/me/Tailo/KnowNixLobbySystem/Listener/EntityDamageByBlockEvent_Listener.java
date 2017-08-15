package me.Tailo.KnowNixLobbySystem.Listener;

import me.Tailo.KnowNixLobbySystem.System.main;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByBlockEvent;

public class EntityDamageByBlockEvent_Listener implements Listener {

	private main plugin;

	public EntityDamageByBlockEvent_Listener(main main) {
		this.plugin = main;
		plugin.getServer().getPluginManager().registerEvents(this, main);;
	}
	
	
	@EventHandler
	public void onEntityDamageByBlock(EntityDamageByBlockEvent e) {
		e.setCancelled(true);
	}

}
