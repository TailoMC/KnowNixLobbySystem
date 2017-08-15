package me.Tailo.KnowNixLobbySystem.Items;

import java.util.Arrays;

import me.Tailo.KnowNixLobbySystem.Methoden.NickItem_methoden;
import me.Tailo.KnowNixLobbySystem.Methoden.Settings_methoden;
import me.Tailo.KnowNixLobbySystem.System.main;
import me.Tailo.KnowNixSystem.MySQL.MySQL;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Item_nickname implements Listener {

	private main plugin;

	public Item_nickname(main main) {
		this.plugin = main;
		plugin.getServer().getPluginManager().registerEvents(this, main);
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		
		if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			try {
				if(e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §cAutomatischer Nickname") || e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §aAutomatischer Nickname")){
					
					Player p = e.getPlayer();
					
					if(Settings_methoden.isNickEnabled(p)) {
						//=====================================================
						ItemStack nickname = new ItemStack(Material.NAME_TAG);
						ItemMeta nicknamemeta = nickname.getItemMeta();
						nicknamemeta.setDisplayName("§8» §cAutomatischer Nickname");
						nicknamemeta.setLore(Arrays.asList("§7Rechtsklick / Linksklick"));
						nickname.setItemMeta(nicknamemeta);
						//=====================================================
						
						MySQL.update("UPDATE nick SET nick = '" + 0 + "' WHERE UUID = '" + p.getUniqueId().toString() + "'");				
						p.getInventory().setItem(4, nickname);
						p.sendMessage("§5Nick §8» §4Der automatische Nickname wurde: §cDeaktiviert");
					} else {
						//=====================================================
						ItemStack nickname = new ItemStack(Material.NAME_TAG);
						ItemMeta nicknamemeta = nickname.getItemMeta();
						nicknamemeta.setDisplayName("§8» §aAutomatischer Nickname");
						nicknamemeta.setLore(Arrays.asList("§7Rechtsklick / Linksklick"));
						nickname.setItemMeta(nicknamemeta);
						//=====================================================
						
						MySQL.update("UPDATE nick SET nick = '" + 1 + "' WHERE UUID = '" + p.getUniqueId().toString() + "'");				
						p.getInventory().setItem(4, nickname);
						p.sendMessage("§5Nick §8» §4Der automatische Nickname wurde: §aAktiviert");
					}
					
					p.playSound(p.getLocation(), Sound.CLICK, 1.0F, 1.0F);
					
				}
			} catch(Exception ex) {				
			}
		} else if(e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK) {
			try {
				if(e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §cAutomatischer Nickname") || e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §aAutomatischer Nickname")){
					
					Player p = e.getPlayer();
					
					NickItem_methoden.openNickGui(p);
					
				}
			} catch(Exception ex) {				
			}
		}
		
	}
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {
		
		if(e.getInventory().getTitle().equalsIgnoreCase("§cNick Einstellungen")) {
			
			Player p = (Player) e.getWhoClicked();
			
			if(e.getSlot() == 12 || e.getSlot() == 21) {
				e.setCancelled(true);
				NickItem_methoden.togglePremium(p, e.getInventory());
			} else if(e.getSlot() == 16 || e.getSlot() == 25) {
				e.setCancelled(true);
				NickItem_methoden.toggleKeepNick(p, e.getInventory());
			} else if(e.getSlot() == 10 || e.getSlot() == 19) {
				e.setCancelled(true);
				NickItem_methoden.toggleLobbyNick(p, e.getInventory());
			} else if(e.getSlot() == 14 || e.getSlot() == 23) {
				e.setCancelled(true);
				NickItem_methoden.toggleSeeNicked(p, e.getInventory());
			}
			
		}
		
	}

}
