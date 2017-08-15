package me.Tailo.KnowNixLobbySystem.Listener;

import me.Tailo.KnowNixLobbySystem.System.main;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;

public class PlayerBedEnterEvent_Listener implements Listener {

	private main plugin;

	public PlayerBedEnterEvent_Listener(main main) {
		this.plugin = main;
		plugin.getServer().getPluginManager().registerEvents(this, main);
	}
	
	@EventHandler
	public void onPlayerBedEnter(PlayerBedEnterEvent e) {
		
		Player p = e.getPlayer();
		
		if(p.getWorld().getTime() >= 10000) {
			p.sendMessage(plugin.prefix + "§3Du darfst keine §6Betten §3betreten!");
		}
		
		e.setCancelled(true);
	}
}
