package me.Tailo.KnowNixLobbySystem.Listener;

import me.Tailo.KnowNixLobbySystem.System.main;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class EntityDamageByEntityEvent_Listener implements Listener {

	private main plugin;

	public EntityDamageByEntityEvent_Listener(main main) {
		this.plugin = main;
		plugin.getServer().getPluginManager().registerEvents(this, main);
	}
	
	@EventHandler
	public void onEntityDamageByEntity(EntityDamageByEntityEvent e) {
		
		if(e.getEntity() instanceof Player & e.getDamager() instanceof Player) {
			e.setCancelled(true);
		} else
			if(e.getEntity() instanceof Player & (!(e.getDamager() instanceof Player))) {
				e.setCancelled(true);
			}	
		
	}
}
