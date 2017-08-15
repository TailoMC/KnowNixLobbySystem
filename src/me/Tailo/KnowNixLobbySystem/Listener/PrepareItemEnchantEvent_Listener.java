package me.Tailo.KnowNixLobbySystem.Listener;

import me.Tailo.KnowNixLobbySystem.System.main;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.PrepareItemEnchantEvent;

public class PrepareItemEnchantEvent_Listener implements Listener {

	private main plugin;

	public PrepareItemEnchantEvent_Listener(main main) {
		this.plugin = main;
		plugin.getServer().getPluginManager().registerEvents(this, main);
	}
	
	@EventHandler
	public void onPrepareItemEnchantEvent(PrepareItemEnchantEvent e) {		
		e.setCancelled(true);		
	}

}
