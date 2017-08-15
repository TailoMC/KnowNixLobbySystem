package me.Tailo.KnowNixLobbySystem.Methoden;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import de.devcubehd.cloudsystem.CloudSystemCore;
import de.devcubehd.cloudsystem.sign.ServerObject;
import de.devcubehd.cloudsystem.sign.SignSystemManager;

public class LobbySwitcherItem_methoden {

	public static void openLobbySwitcherInv(Player p){
		
		List<ServerObject> lobbys = new ArrayList<ServerObject>();
		
		for(String server : SignSystemManager.getServers().keySet()) {
			if(server.startsWith("lobby")) {
				lobbys.add(SignSystemManager.getServers().get(server));
			}
		}
		
		int lines = 0;
		while(lines * 9 < lobbys.size() + 1) {
			lines++;
		}
		
		Inventory inv = Bukkit.createInventory(null, lines * 9, "§fLobby wechseln");		
		int i = 0;
		for(ServerObject lobbyobj : lobbys) {
			
			if(CloudSystemCore.getServerName().equalsIgnoreCase(lobbyobj.getName())) {
				
				ItemStack lobby = new ItemStack(Material.GLOWSTONE_DUST);
				ItemMeta lobbymeta = lobby.getItemMeta();
				lobbymeta.setDisplayName("§aL" + lobbyobj.getName().replace("-", " ").substring(1));
				lobbymeta.setLore(Arrays.asList("§b" + Bukkit.getOnlinePlayers().size() + " Spieler" , "§eDu bist hier"));
				lobby.setItemMeta(lobbymeta);
				
				inv.setItem(i, lobby);
				
			} else {
				
				ItemStack lobby = new ItemStack(Material.SUGAR);
				ItemMeta lobbymeta = lobby.getItemMeta();
				lobbymeta.setDisplayName("§aL" + lobbyobj.getName().replace("-", " ").substring(1));
				lobbymeta.setLore(Arrays.asList("§b" + lobbyobj.getPlayers() + " Spieler"));
				lobby.setItemMeta(lobbymeta);
				
				inv.setItem(i, lobby);
				
			}
			
			i++;
			
		}
		
		//=======================================================
		ItemStack vip = new ItemStack(Material.INK_SACK, 1, (byte) 5);
		ItemMeta vipmeta = vip.getItemMeta();
		vipmeta.setDisplayName("§5VIPLobby");
		vip.setItemMeta(vipmeta);
		//=======================================================
		
		inv.setItem(lines * 9 - 1, vip);
		
		p.openInventory(inv);
		
	}
	
}
