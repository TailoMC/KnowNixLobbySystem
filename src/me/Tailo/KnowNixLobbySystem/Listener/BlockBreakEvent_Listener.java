package me.Tailo.KnowNixLobbySystem.Listener;

import me.Tailo.KnowNixLobbySystem.System.main;

import org.bukkit.entity.Hanging;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.hanging.HangingBreakByEntityEvent;

public class BlockBreakEvent_Listener implements Listener {

	private main plugin;

	public BlockBreakEvent_Listener(main main) {
		this.plugin = main;
		plugin.getServer().getPluginManager().registerEvents(this, main);
	}
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {
		
		e.setCancelled(true);
		
		Player p = e.getPlayer();
		
		if(plugin.build.contains(p)) {
			e.setCancelled(false);
		}
		
	}
	
	@EventHandler
	public void onHangingBreakByEntity(HangingBreakByEntityEvent e) {
		e.setCancelled(true);
		
		if(e.getRemover() instanceof Player) {
			Player p = (Player) e.getRemover();
			
			if(plugin.build.contains(p)) {
				e.setCancelled(false);
			}
		}
		
	}
	
	@EventHandler
	public void onEntityDamageByEntity(EntityDamageByEntityEvent e) {
		
		if(e.getEntity() instanceof Hanging) {
			
			if(e.getDamager() instanceof Player) {
				
				e.setCancelled(true);
				
				Player p = (Player) e.getDamager();
				
				if(plugin.build.contains(p)) {
					e.setCancelled(false);
				}
			}
			
		}
		
	}

}
