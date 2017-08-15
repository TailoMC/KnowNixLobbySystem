package me.Tailo.KnowNixLobbySystem.Listener;

import me.Tailo.KnowNixLobbySystem.System.main;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class EntityDeathEvent_Listener implements Listener {

	private main plugin;

	public EntityDeathEvent_Listener(main main) {
		this.plugin = main;
		plugin.getServer().getPluginManager().registerEvents(this, main);
	}
	
	@EventHandler
	public void onEntityDeath(EntityDeathEvent e) {
		
		if(!(e.getEntity() instanceof Player)) {
			e.getDrops().clear();
			e.setDroppedExp(0);
		}		
	}
}
