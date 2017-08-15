package me.Tailo.KnowNixLobbySystem.Features;

import me.Tailo.KnowNixLobbySystem.System.main;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

public class Feature_Launchpad implements Listener {

	private main plugin;

	public Feature_Launchpad(main main) {
		this.plugin = main;
		plugin.getServer().getPluginManager().registerEvents(this, main);
	}
	
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent e) {
		
		Player p = e.getPlayer();
		
		if(p.getLocation().getBlock().getType() == Material.IRON_PLATE && p.getLocation().subtract(0, 1, 0).getBlock().getType() == Material.GOLD_BLOCK) {
			
			Vector v = p.getLocation().getDirection().multiply(4.0D).setY(1.0D);
			p.setVelocity(v);
			
			p.playSound(p.getLocation(), Sound.GHAST_FIREBALL, 1.0F, 1.0F);
		}	
		
	}

}
