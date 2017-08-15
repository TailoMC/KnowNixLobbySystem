package me.Tailo.KnowNixLobbySystem.Listener;

import me.Tailo.KnowNixLobbySystem.Methoden.Friend_methoden;
import me.Tailo.KnowNixLobbySystem.Methoden.InfoBoard;
import me.Tailo.KnowNixLobbySystem.Methoden.Inventory_methoden;
import me.Tailo.KnowNixLobbySystem.Methoden.NickItem_methoden;
import me.Tailo.KnowNixLobbySystem.Methoden.Prefix_methoden;
import me.Tailo.KnowNixLobbySystem.Methoden.Tab_methoden;
import me.Tailo.KnowNixLobbySystem.Methoden.Prefix_methoden.Prefix;
import me.Tailo.KnowNixLobbySystem.System.main;
import me.Tailo.NickSystem.Nick.NickAPI;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinEvent_Listener implements Listener {

	private main plugin;

	public PlayerJoinEvent_Listener(main main) {
		this.plugin = main;
		plugin.getServer().getPluginManager().registerEvents(this, main);
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		
		Player p = e.getPlayer();
		
		e.setJoinMessage("");
		
		Inventory_methoden.clearInventory(p);
		Inventory_methoden.setIngameItems(p);
		
		Tab_methoden.setTablist(p);
		
		p.teleport(plugin.spawn);
		p.setCompassTarget(plugin.spawn);
		
		if(p.hasPermission("admin")) {
			Prefix_methoden.prefix(p.getName(), Prefix.Admin);
			p.setDisplayName("§4" + p.getName());
		} else if(p.hasPermission("srdev")) {
			Prefix_methoden.prefix(p.getName(), Prefix.SrDev);
			p.setDisplayName("§b" + p.getName());
		} else if(p.hasPermission("dev")) {
			Prefix_methoden.prefix(p.getName(), Prefix.Dev);
			p.setDisplayName("§b" + p.getName());
		} else if(p.hasPermission("srmod")) {
			Prefix_methoden.prefix(p.getName(), Prefix.SrMod);
			p.setDisplayName("§c" + p.getName());
		} else if(p.hasPermission("mod")) {
			Prefix_methoden.prefix(p.getName(), Prefix.Mod);
			p.setDisplayName("§c" + p.getName());
		} else if(p.hasPermission("sup")) {
			Prefix_methoden.prefix(p.getName(), Prefix.Sup);
			p.setDisplayName("§9" + p.getName());
		} else if(p.hasPermission("content")) {
			Prefix_methoden.prefix(p.getName(), Prefix.Content);
			p.setDisplayName("§b" + p.getName());
		} else if(p.hasPermission("builder")) {
			Prefix_methoden.prefix(p.getName(), Prefix.Builder);
			p.setDisplayName("§e" + p.getName());
		} else if(p.hasPermission("vip")) {
			Prefix_methoden.prefix(p.getName(), Prefix.VIP);
			p.setDisplayName("§5" + p.getName());
		} else if(p.hasPermission("premiumplus")) {
			Prefix_methoden.prefix(p.getName(), Prefix.PremiumPlus);
			p.setDisplayName("§6" + p.getName());
		} else if(p.hasPermission("premium")) {
			Prefix_methoden.prefix(p.getName(), Prefix.Premium);
			p.setDisplayName("§6" + p.getName());
		} else {
			Prefix_methoden.prefix(p.getName(), Prefix.Spieler);
			p.setDisplayName("§a" + p.getName());
		}
		
		if(NickAPI.isAutoNickEnabled(p) && NickItem_methoden.isLobbyNickEnabled(p)) {
			NickItem_methoden.nick(p);
		} else {
			Inventory_methoden.setBoots(p);
		}
		
		Friend_methoden.preLoad(p);
		
		new InfoBoard(p);
		
	}
	
}
