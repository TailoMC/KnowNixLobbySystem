package me.Tailo.KnowNixLobbySystem.Items;

import me.Tailo.KnowNixLobbySystem.Methoden.SilentLobbyItem_methoden;
import me.Tailo.KnowNixLobbySystem.System.main;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class Item_silentlobby implements Listener {

	private main plugin;

	public Item_silentlobby(main main) {
		this.plugin = main;
		plugin.getServer().getPluginManager().registerEvents(this, main);
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		
		if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			try {
				if(e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §cSilentLobby")){
					
					Player p = e.getPlayer();
					
					SilentLobbyItem_methoden.connectToSilentLobby(p);
				}
			} catch(Exception ex) {				
			}
		}
		
	}

}
