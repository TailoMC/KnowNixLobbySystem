package me.Tailo.KnowNixLobbySystem.Items;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import me.Tailo.KnowNixLobbySystem.Methoden.Friend_methoden;
import me.Tailo.KnowNixLobbySystem.Methoden.HiderItem_methoden;
import me.Tailo.KnowNixLobbySystem.System.main;
import me.Tailo.KnowNixSystem.MySQL.MySQL;
import me.Tailo.NickSystem.Nick.NickAPI;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class Item_hider implements Listener {

	private main plugin;

	private static ArrayList<String> hidden1 = new ArrayList<>();
	private static ArrayList<String> hidden2 = new ArrayList<>();
	
	public Item_hider(main main) {
		this.plugin = main;
		plugin.getServer().getPluginManager().registerEvents(this, main);
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		
		if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			try {
				if(e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §6Spieler verstecken")){
					
					Player p = e.getPlayer();
					
					HiderItem_methoden.openHideInv(p);
				}
			} catch(Exception ex) {				
			}
		}
		
	}	
	
	@EventHandler
	public void onInvInteract(InventoryClickEvent e) {
		
		Player p = (Player) e.getWhoClicked();
		
		if(e.getInventory().getName().equalsIgnoreCase("§6Spieler verstecken")) {
			try {
				if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(" ")) {
					e.setCancelled(true);
				}
			} catch(Exception ex) {				
			}
			try {
				if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aAlle Spieler anzeigen")) {
					e.setCancelled(true);
					
					if(hidden1.contains(p.getName())) {
						hidden1.remove(p.getName());
					}
					
					if(hidden2.contains(p.getName())) {
						hidden2.remove(p.getName());
					}
					
					for(Player players: Bukkit.getOnlinePlayers()) {
						if(players.hasPermission("sup") || players.hasPermission("content")) {
							if(hasVanishEnabled(players)) {
								if(p.hasPermission("sup") || p.hasPermission("content")) {
									p.showPlayer(players);
								} else {
									p.hidePlayer(players);		
								}
							} else {
								p.showPlayer(players);
							}
						} else {
							p.showPlayer(players);
						}
					}
					p.playSound(p.getLocation(), Sound.NOTE_PLING, 1.0F, 1.0F);
					p.closeInventory();
				}
			} catch(Exception ex) {				
			}
			try {
				if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§5Nur Freunde, Youtuber & Teammitglieder anzeigen")) {
					e.setCancelled(true);
					
					if(hidden1.contains(p.getName())) {
						hidden1.remove(p.getName());
					}
					
					if(!hidden2.contains(p.getName())) {
						hidden2.add(p.getName());
					}
					
					for(Player players: Bukkit.getOnlinePlayers()) {
						if(players.hasPermission("vip") || p.hasPermission("sup") || p.hasPermission("content") || p.hasPermission("builder")) {
							if(hasVanishEnabled(players)) {
								if(p.hasPermission("sup") || p.hasPermission("content")) {
									p.showPlayer(players);
								} else {
									p.hidePlayer(players);		
								}
							} else {
								if(NickAPI.isNicked(players)) {
									p.hidePlayer(players);
								} else {
									p.showPlayer(players);
								}
							}
						} else {
							if(Friend_methoden.getFriends(p).contains(players.getUniqueId().toString())) {
								p.showPlayer(players);
							} else {
								p.hidePlayer(players);
							}
						}
					}
					p.playSound(p.getLocation(), Sound.NOTE_PLING, 1.0F, 1.0F);
					p.closeInventory();
				}
			} catch(Exception ex) {				
			}
			try {
				if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§4Alle Spieler verstecken")) {
					e.setCancelled(true);
					
					if(hidden2.contains(p.getName())) {
						hidden2.remove(p.getName());
					}
					
					if(!hidden1.contains(p.getName())) {
						hidden1.add(p.getName());
					}
					
					for(Player players: Bukkit.getOnlinePlayers()) {
						p.hidePlayer(players);
					}
					p.playSound(p.getLocation(), Sound.NOTE_PLING, 1.0F, 1.0F);
					p.closeInventory();
				}
			} catch(Exception ex) {				
			}
		}		
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		
		Player p = e.getPlayer();
		
		for(Player players: Bukkit.getOnlinePlayers()) {
			
			if(hidden1.contains(players)) {
				players.hidePlayer(p);
			}
			
			if(hidden2.contains(players)) {
				if(players.hasPermission("vip") || p.hasPermission("sup") || p.hasPermission("content") || p.hasPermission("builder")) {
					players.showPlayer(p);							
				} else {
					if(Friend_methoden.getFriends(players).contains(p.getUniqueId().toString())) {
						players.showPlayer(p);
					} else {
						players.hidePlayer(p);
					}
				}
				return;
			}
			
			if(!hidden1.contains(players) & !hidden2.contains(players)) {
				players.showPlayer(p);
			}
		}		
	}
	
	private static boolean hasVanishEnabled(Player p) {
		
		ResultSet rs = MySQL.getResult("SELECT playername FROM vanish WHERE UUID = '" + p.getUniqueId().toString() + "'");
		
		try {
			return rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
		
	}
}
