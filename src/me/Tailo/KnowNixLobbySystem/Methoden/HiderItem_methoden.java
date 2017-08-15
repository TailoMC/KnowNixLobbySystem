package me.Tailo.KnowNixLobbySystem.Methoden;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class HiderItem_methoden {
	
	public static void openHideInv(Player p) {
		Inventory inv = Bukkit.createInventory(null, 9, "§6Spieler verstecken");
		
		//============================================================
		ItemStack allon = new ItemStack(Material.WOOL, 1, (byte) 13);
		ItemMeta allonmeta = allon.getItemMeta();
		allonmeta.setDisplayName("§aAlle Spieler anzeigen");
		allon.setItemMeta(allonmeta);
		//============================================================
		ItemStack vipon = new ItemStack(Material.WOOL, 1, (byte) 10);
		ItemMeta viponmeta = vipon.getItemMeta();
		viponmeta.setDisplayName("§5Nur Freunde, Youtuber & Teammitglieder anzeigen");
		vipon.setItemMeta(viponmeta);
		//============================================================
		ItemStack alloff = new ItemStack(Material.WOOL, 1, (byte) 14);
		ItemMeta alloffmeta = alloff.getItemMeta();
		alloffmeta.setDisplayName("§4Alle Spieler verstecken");
		alloff.setItemMeta(alloffmeta);
		//============================================================
		ItemStack glas = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 1);
		ItemMeta glasmeta = glas.getItemMeta();
		glasmeta.setDisplayName(" ");
		glas.setItemMeta(glasmeta);
		//============================================================
		
		inv.setItem(0, glas);
		inv.setItem(1, glas);
		inv.setItem(2, glas);
		inv.setItem(3, glas);
		inv.setItem(4, glas);
		inv.setItem(5, glas);
		inv.setItem(6, glas);
		inv.setItem(7, glas);
		inv.setItem(8, glas);		
		
		inv.setItem(0, allon);
		inv.setItem(4, vipon);
		inv.setItem(8, alloff);
		
		p.openInventory(inv);
		
	}

}
