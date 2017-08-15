package me.Tailo.KnowNixLobbySystem.Listener;

import me.Tailo.KnowNixLobbySystem.System.main;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeathEvent_Listener implements Listener {

	private main plugin;

	public PlayerDeathEvent_Listener(main main) {
		this.plugin = main;
		plugin.getServer().getPluginManager().registerEvents(this, main);
	}
	
	@EventHandler
	public void onDeath(PlayerDeathEvent e) {
		
		e.setDeathMessage("");
		e.setDroppedExp(0);
		e.getDrops().clear();
		
		Player p = e.getEntity();
		
		p.setHealth(20.0);
		p.setFoodLevel(20);
		p.setFireTicks(0);
		
		p.kickPlayer(plugin.prefix + "§3Du hast offenbar einen §6Fehler verursacht! Joinen neu!");
		
	}
}
