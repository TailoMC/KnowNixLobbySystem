package me.Tailo.KnowNixLobbySystem.Listener;

import me.Tailo.KnowNixLobbySystem.System.main;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.FurnaceBurnEvent;
import org.bukkit.event.inventory.FurnaceSmeltEvent;

public class FurnaceEvents_Listener implements Listener {

	private main plugin;

	public FurnaceEvents_Listener(main main) {
		this.plugin = main;
		plugin.getServer().getPluginManager().registerEvents(this, main);
	}
	
	@EventHandler
	public void onFurnaceSmelt(FurnaceSmeltEvent e) {
		e.setCancelled(true);		
	}
	
	@EventHandler
	public void onFurnaceBurn(FurnaceBurnEvent e) {
		e.setCancelled(true);		
	}

}
