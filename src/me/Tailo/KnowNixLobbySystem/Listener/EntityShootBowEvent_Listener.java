package me.Tailo.KnowNixLobbySystem.Listener;

import me.Tailo.KnowNixLobbySystem.System.main;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;

public class EntityShootBowEvent_Listener implements Listener {

	private main plugin;

	public EntityShootBowEvent_Listener(main main) {
		this.plugin = main;
		plugin.getServer().getPluginManager().registerEvents(this, main);
	}
	
	@EventHandler
	public void onEntityShootBow(EntityShootBowEvent e) {
		e.setCancelled(true);
		
		if(e.getEntity() instanceof Player) {
			Player p = (Player) e.getEntity();
			
			p.sendMessage(plugin.prefix + "§3Du darfst keinen Bogen verwenden!");
		}
	}
}
