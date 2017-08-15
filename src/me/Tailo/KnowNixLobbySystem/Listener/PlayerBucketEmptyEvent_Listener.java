package me.Tailo.KnowNixLobbySystem.Listener;

import me.Tailo.KnowNixLobbySystem.System.main;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBucketEmptyEvent;

public class PlayerBucketEmptyEvent_Listener implements Listener {

	private main plugin;

	public PlayerBucketEmptyEvent_Listener(main main) {
		this.plugin = main;
		plugin.getServer().getPluginManager().registerEvents(this, main);
	}
	
	@EventHandler
	public void onPlayerBucketEmpty(PlayerBucketEmptyEvent e) {
		e.setCancelled(true);
		
		Player p = e.getPlayer();
		p.sendMessage(plugin.prefix + "§3Du darfst keinen §6Eimer §3leeren!");
	}
	
}
