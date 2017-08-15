package me.Tailo.KnowNixLobbySystem.Listener;

import me.Tailo.KnowNixLobbySystem.System.main;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

public class EntityDamageEvent_Listener implements Listener {

	private main plugin;

	public EntityDamageEvent_Listener(main main) {
		this.plugin = main;
		plugin.getServer().getPluginManager().registerEvents(this, main);
	}
	
	@EventHandler
	public void onEntityDamage(EntityDamageEvent e) {
		
		if(e.getEntity() instanceof Player) {
			Player p = (Player) e.getEntity();
			
			if(p.getFireTicks() > 0) {
				p.setFireTicks(0);
			}
			
			if(e.getCause() == DamageCause.VOID) {
				p.teleport(plugin.spawn);
			}
			
			e.setCancelled(true);
			
		}		
	}
}
