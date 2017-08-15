package me.Tailo.KnowNixLobbySystem.Methoden;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class NavigatorItem_methoden {

	public static void openNavigatorInv(Player p){
		
		Inventory inv = Bukkit.createInventory(null, 54, "§bTeleporter");		
		
		//========================================================
		ItemStack spawn = new ItemStack(Material.MAGMA_CREAM);
		ItemMeta spawnmeta = spawn.getItemMeta();
		spawnmeta.setDisplayName("§a§lSpawn");
		spawn.setItemMeta(spawnmeta);
		//========================================================
		ItemStack community = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
		SkullMeta communitymeta = (SkullMeta) community.getItemMeta();
		communitymeta.setDisplayName("§5§lKnowUnity");
		communitymeta.setOwner("KnowNix");
		community.setItemMeta(communitymeta);
		//========================================================
		ItemStack knockout = new ItemStack(Material.STICK);
		ItemMeta knockoutmeta = knockout.getItemMeta();
		knockoutmeta.setDisplayName("§e§lKnockOut");
		knockout.setItemMeta(knockoutmeta);
		//========================================================
		ItemStack qsg = new ItemStack(Material.IRON_SWORD);
		ItemMeta qsgmeta = qsg.getItemMeta();
		qsgmeta.setDisplayName("§a§lQSG");
		qsg.setItemMeta(qsgmeta);
		//========================================================
		ItemStack bedwars = new ItemStack(Material.BED);
		ItemMeta bedwarsmeta = bedwars.getItemMeta();
		bedwarsmeta.setDisplayName("§c§lBedWars");
		bedwars.setItemMeta(bedwarsmeta);
		//========================================================
		ItemStack skywars = new ItemStack(Material.GRASS);
		ItemMeta skywarsmeta = skywars.getItemMeta();
		skywarsmeta.setDisplayName("§6§lSkyWars");
		skywars.setItemMeta(skywarsmeta);
		//========================================================
		ItemStack glass = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 14);
		ItemMeta glassmeta = glass.getItemMeta();
		glassmeta.setDisplayName(" ");
		glass.setItemMeta(glassmeta);
		//========================================================
		
		for(int i = 0; i < 54;) {
			inv.setItem(i, glass);
			i++;
		}
		
		inv.setItem(11, skywars);
		inv.setItem(15, bedwars);
		inv.setItem(22, community);
		inv.setItem(28, knockout);
		inv.setItem(34, qsg);
		inv.setItem(40, spawn);
		
		p.openInventory(inv);
		
	}
	
}
