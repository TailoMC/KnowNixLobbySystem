package me.Tailo.KnowNixLobbySystem.Items;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import me.Tailo.KnowNixLobbySystem.Methoden.LobbySwitcherItem_methoden;
import me.Tailo.KnowNixLobbySystem.System.main;

public class Item_lobbyswitcher implements Listener {

	private main plugin;

	public Item_lobbyswitcher(main main) {
		this.plugin = main;
		plugin.getServer().getPluginManager().registerEvents(this, main);
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		
		if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			try {
				if(e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §fLobby wechseln")){
					
					Player p = e.getPlayer();
					
					LobbySwitcherItem_methoden.openLobbySwitcherInv(p);
				}
			} catch(Exception ex) {				
			}
		}
		
	}
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {
		
		if(e.getInventory().getName().equalsIgnoreCase("§fLobby wechseln")) {
			
			Player p = (Player) e.getWhoClicked();
			
			e.setCancelled(true);
			
			try {
				
				if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§5VIPLobby")) {
					joinServer(p, "viplobby-1");
				} else {
					joinServer(p, ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName().toLowerCase().replace(" ", "-")));
				}
				
			} catch(Exception ex){				
			}
			
		}
		
	}
	
	private void joinServer(Player p, String servername) {		
		ByteArrayOutputStream b = new ByteArrayOutputStream();
		DataOutputStream out = new DataOutputStream(b);
		
		try {
			out.writeUTF("Connect");
			out.writeUTF(servername);
		} catch(Exception ex) {			
		}
		
		p.sendPluginMessage(plugin, "BungeeCord", b.toByteArray());
	}

}
