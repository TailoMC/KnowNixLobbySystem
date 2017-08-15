package me.Tailo.KnowNixLobbySystem.Listener;

import me.Tailo.KnowNixLobbySystem.System.main;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PotionSplashEvent;
import org.bukkit.potion.PotionEffect;

public class PotionSplashEvent_Listener implements Listener {

	private main plugin;

	public PotionSplashEvent_Listener(main main) {
		this.plugin = main;
		plugin.getServer().getPluginManager().registerEvents(this, main);
	}
	
	@EventHandler
	public void onSplash(PotionSplashEvent e) {
		
		for(Entity all: e.getAffectedEntities()) {
			if(all instanceof Player) {
				Player p = (Player) all;
				
				for(PotionEffect effect: e.getPotion().getEffects()) {
					p.removePotionEffect(effect.getType());
					e.setCancelled(true);
				}
			}
		}		
	}
}
