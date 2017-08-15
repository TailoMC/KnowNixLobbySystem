package me.Tailo.KnowNixLobbySystem.Items;

import me.Tailo.KnowNixLobbySystem.Methoden.NavigatorItem_methoden;
import me.Tailo.KnowNixLobbySystem.System.main;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class Item_navigator implements Listener {

	private main plugin;

	public Item_navigator(main main) {
		this.plugin = main;
		plugin.getServer().getPluginManager().registerEvents(this, main);
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		
		if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			try {
				if(e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §9Teleporter")){
					
					Player p = e.getPlayer();
					
					NavigatorItem_methoden.openNavigatorInv(p);
				}
			} catch(Exception ex) {				
			}
		}
		
	}
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent e){
		
		Player p = (Player) e.getWhoClicked();
		
		if(e.getInventory().getName().equalsIgnoreCase("§bTeleporter")){
			try {
				if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§a§lSpawn")){
					e.setCancelled(true);
					p.teleport(plugin.spawn);
					p.playSound(p.getLocation(), Sound.CLICK, 1.0F, 1.0F);
				}
				if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§5§lKnowUnity")){
					e.setCancelled(true);
					p.teleport(plugin.community);
					p.playSound(p.getLocation(), Sound.CLICK, 1.0F, 1.0F);
				}
				if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§e§lKnockOut")){
					e.setCancelled(true);
					p.teleport(plugin.knockout);
					p.playSound(p.getLocation(), Sound.CLICK, 1.0F, 1.0F);
				}
				if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§a§lQSG")){
					e.setCancelled(true);
					p.teleport(plugin.qsg);
					p.playSound(p.getLocation(), Sound.CLICK, 1.0F, 1.0F);
				}
				if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§c§lBedWars")){
					e.setCancelled(true);
					p.teleport(plugin.bedwars);
					p.playSound(p.getLocation(), Sound.CLICK, 1.0F, 1.0F);
				}
				if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6§lSkyWars")){
					e.setCancelled(true);
					p.teleport(plugin.skywars);
					p.playSound(p.getLocation(), Sound.CLICK, 1.0F, 1.0F);
				}
				if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(" ")){
					e.setCancelled(true);
				}
			} catch(Exception ex){
			}
		}
	}

}
