package me.Tailo.KnowNixLobbySystem.Methoden;

import java.util.Arrays;

import org.bukkit.Color;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class Inventory_methoden {

	public static void clearInventory(Player p){
		
		p.getActivePotionEffects().clear();
		p.getInventory().clear();
		p.getInventory().setHelmet(null);
		p.getInventory().setChestplate(null);
		p.getInventory().setLeggings(null);
		p.getInventory().setBoots(null);
		
		p.setLevel(0);
		p.setExp(0);
		p.setAllowFlight(false);
		p.setFlying(false);
		p.setGameMode(GameMode.SURVIVAL);
		
		p.setMaxHealth(6.0D);
		p.setHealth(6.0D);
		p.setFoodLevel(20);
		p.setFireTicks(0);
		
	}
	
	public static void setIngameItems(Player p) {
		
		//=====================================================
		ItemStack navigator = new ItemStack(Material.COMPASS);
		ItemMeta navigatormeta = navigator.getItemMeta();
		navigatormeta.setDisplayName("§8» §9Teleporter");
		navigatormeta.setLore(Arrays.asList("§7Rechtsklick"));
		navigator.setItemMeta(navigatormeta);
		//=====================================================
		ItemStack hider = new ItemStack(Material.BLAZE_ROD);
		ItemMeta hidermeta = hider.getItemMeta();
		hidermeta.setDisplayName("§8» §6Spieler verstecken");
		hidermeta.setLore(Arrays.asList("§7Rechtsklick"));
		hider.setItemMeta(hidermeta);
		//=====================================================
		ItemStack lobbyswitcher = new ItemStack(Material.NETHER_STAR);
		ItemMeta lobbyswitchermeta = lobbyswitcher.getItemMeta();
		lobbyswitchermeta.setDisplayName("§8» §fLobby wechseln");
		lobbyswitchermeta.setLore(Arrays.asList("§7Rechtsklick"));
		lobbyswitcher.setItemMeta(lobbyswitchermeta);
		//=====================================================
		ItemStack friend = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
		SkullMeta friendmeta = (SkullMeta) friend.getItemMeta();
		friendmeta.setDisplayName("§8» §4Freunde");
		friendmeta.setLore(Arrays.asList("§7Rechtsklick"));
		friendmeta.setOwner(p.getName());
		friend.setItemMeta(friendmeta);
		//=====================================================
		
		p.getInventory().setItem(0, navigator);
		p.getInventory().setItem(1, hider);
		p.getInventory().setItem(7, lobbyswitcher);
		p.getInventory().setItem(8, friend);
		
		if(p.hasPermission("premiumplus")) {
			
			if(Settings_methoden.isNickEnabled(p)) {
				
				//=====================================================
				ItemStack nickname = new ItemStack(Material.NAME_TAG);
				ItemMeta nicknamemeta = nickname.getItemMeta();
				nicknamemeta.setDisplayName("§8» §aAutomatischer Nickname");
				nicknamemeta.setLore(Arrays.asList("§7Rechtsklick / Linksklick"));
				nickname.setItemMeta(nicknamemeta);
				//=====================================================
				p.getInventory().setItem(4, nickname);
				
			} else {
				
				//=====================================================
				ItemStack nickname = new ItemStack(Material.NAME_TAG);
				ItemMeta nicknamemeta = nickname.getItemMeta();
				nicknamemeta.setDisplayName("§8» §cAutomatischer Nickname");
				nicknamemeta.setLore(Arrays.asList("§7Rechtsklick / Linksklick"));
				nickname.setItemMeta(nicknamemeta);
				//=====================================================
				p.getInventory().setItem(4, nickname);
				
			}
			
		}
		
		if(p.hasPermission("vip")) {
			
			//=====================================================
			ItemStack silentlobby = new ItemStack(Material.TNT);
			ItemMeta silentlobbymeta = silentlobby.getItemMeta();
			silentlobbymeta.setDisplayName("§8» §cSilentLobby");
			silentlobbymeta.setLore(Arrays.asList("§7Rechtsklick"));
			silentlobby.setItemMeta(silentlobbymeta);
			//=====================================================
			
			p.getInventory().setItem(2, silentlobby);
			
		}
		
	}
	
	public static void setBoots(Player p) {
		
		if(p.hasPermission("admin")) {
			Inventory_methoden.setBoots(p, 170, 0, 0);
		} else if(p.hasPermission("srdev")) {
			Inventory_methoden.setBoots(p, 85, 255, 255);
		} else if(p.hasPermission("dev")) {
			Inventory_methoden.setBoots(p, 85, 255, 255);
		} else if(p.hasPermission("srmod")) {
			Inventory_methoden.setBoots(p, 255, 85, 85);
		} else if(p.hasPermission("mod")) {
			Inventory_methoden.setBoots(p, 255, 85, 85);
		} else if(p.hasPermission("sup")) {
			Inventory_methoden.setBoots(p, 85, 85, 255);
		} else if(p.hasPermission("content")) {
			Inventory_methoden.setBoots(p, 85, 255, 255);
		} else if(p.hasPermission("builder")) {
			Inventory_methoden.setBoots(p, 255, 255, 85);
		} else if(p.hasPermission("vip")) {
			Inventory_methoden.setBoots(p, 170, 0, 170);
		} else if(p.hasPermission("premiumplus")) {
			Inventory_methoden.setBoots(p, 255, 170, 0);
		} else if(p.hasPermission("premium")) {
			Inventory_methoden.setBoots(p, 255, 170, 0);
		}
		
	}
	
	public static void setPremiumBoots(Player p) {
		
		Inventory_methoden.setBoots(p, 255, 170, 0);
		
	}
	
	private static void setBoots(Player p, int red, int green, int blue) {
		
		//=====================================================
		ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
		LeatherArmorMeta bootsmeta = (LeatherArmorMeta) boots.getItemMeta();
		bootsmeta.setDisplayName("§8» Boots");
		bootsmeta.setColor(Color.fromRGB(red, green, blue));
		boots.setItemMeta(bootsmeta);
		//=====================================================
		
		p.getInventory().setBoots(boots);
		
	}
	
}
