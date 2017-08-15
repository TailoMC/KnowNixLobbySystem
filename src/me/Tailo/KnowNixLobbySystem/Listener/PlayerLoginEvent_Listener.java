package me.Tailo.KnowNixLobbySystem.Listener;

import me.Tailo.KnowNixLobbySystem.System.main;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class PlayerLoginEvent_Listener implements Listener {

	private main plugin;

	public PlayerLoginEvent_Listener(main main) {
		this.plugin = main;
		plugin.getServer().getPluginManager().registerEvents(this, main);
	}
	
	@EventHandler
	public void onPlayerLogin(PlayerLoginEvent e) {
		
		Player p = e.getPlayer();
		
		if(Bukkit.getOnlinePlayers().size() >= Bukkit.getMaxPlayers()) {
			if(!p.hasPermission("premium")) {
				
				e.disallow(null, plugin.prefix + "§cDiese Lobby ist voll! Um dennoch beizutreten brauchst du §6Premium§c!");
				
			} else {
				
				e.allow();
				
			}
		}
		
	}

}
