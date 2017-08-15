package me.Tailo.KnowNixLobbySystem.Listener;

import me.Tailo.KnowNixLobbySystem.System.main;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.hanging.HangingPlaceEvent;

public class BlockPlaceEvent_Listener implements Listener {
	
	private main plugin;

	public BlockPlaceEvent_Listener(main main) {
		this.plugin = main;
		plugin.getServer().getPluginManager().registerEvents(this, main);
	}
	
	@EventHandler
	public void onPlace(BlockPlaceEvent e) {
		
		e.setCancelled(true);
		
		Player p = e.getPlayer();
		
		if(plugin.build.contains(p)) {
			e.setCancelled(false);
		}
		
	}
	
	@EventHandler
	public void onHangingPlace(HangingPlaceEvent e) {
		
		e.setCancelled(true);
		
		Player p = e.getPlayer();
		
		if(plugin.build.contains(p)) {
			e.setCancelled(false);
		}
		
	}

}
