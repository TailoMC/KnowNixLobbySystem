package me.Tailo.KnowNixLobbySystem.Methoden;

import java.io.File;
import java.io.IOException;

import me.Tailo.KnowNixLobbySystem.System.main;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class Location_methoden {

	private static main plugin;

	@SuppressWarnings("static-access")
	public Location_methoden(main main) {
		this.plugin = main;
		loadLocations();
	}
	
	public static void setSpawn(Player p) {	
		saveLoc("spawn", p.getLocation());
		plugin.spawn = p.getLocation();
		p.playSound(p.getLocation(), Sound.LEVEL_UP, 1.0F, 1.0F);		
		p.sendMessage(plugin.prefix + "§aDer Spawn wurde gesetzt!");
	}
	
	public static void setKnockbackFFA(Player p) {	
		saveLoc("knockout", p.getLocation());
		plugin.knockout = p.getLocation();
		p.playSound(p.getLocation(), Sound.LEVEL_UP, 1.0F, 1.0F);		
		p.sendMessage(plugin.prefix + "§aDer KnockOut-Spawn wurde gesetzt!");
	}
	
	public static void setBedWars(Player p) {	
		saveLoc("bedwars", p.getLocation());
		plugin.bedwars = p.getLocation();
		p.playSound(p.getLocation(), Sound.LEVEL_UP, 1.0F, 1.0F);		
		p.sendMessage(plugin.prefix + "§aDer BedWars-Spawn wurde gesetzt!");
	}
	
	public static void setSkyWars(Player p) {	
		saveLoc("skywars", p.getLocation());
		plugin.skywars = p.getLocation();
		p.playSound(p.getLocation(), Sound.LEVEL_UP, 1.0F, 1.0F);		
		p.sendMessage(plugin.prefix + "§aDer SkyWars-Spawn wurde gesetzt!");
	}
	
	public static void setCommunity(Player p) {	
		saveLoc("community", p.getLocation());
		plugin.community = p.getLocation();
		p.playSound(p.getLocation(), Sound.LEVEL_UP, 1.0F, 1.0F);		
		p.sendMessage(plugin.prefix + "§aDer Community-Spawn wurde gesetzt!");
	}
	
	public static void setQSG(Player p) {	
		saveLoc("qsg", p.getLocation());
		plugin.qsg = p.getLocation();
		p.playSound(p.getLocation(), Sound.LEVEL_UP, 1.0F, 1.0F);		
		p.sendMessage(plugin.prefix + "§aDer QSG-Spawn wurde gesetzt!");
	}
	
	private static void loadLocations() {
		
		try {			
			plugin.spawn = loadLoc("spawn");
			plugin.knockout = loadLoc("knockout");
			plugin.bedwars = loadLoc("bedwars");
			plugin.skywars = loadLoc("skywars");
			plugin.community = loadLoc("community");
			plugin.qsg = loadLoc("qsg");
			
		} catch(Exception e) {
			System.err.println("[KnowNixLobbySystem] Es wurden noch nicht alle Locations gesetzt!");
		}
		
	}
	
	private static Location loadLoc(String name) {
		
		File file = new File("plugins/KnowNixLobbySystem", "locations.yml");
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		
		String world = cfg.getString(name + ".world");
		double x = cfg.getDouble(name + ".x");
		double y = cfg.getDouble(name + ".y");
		double z = cfg.getDouble(name + ".z");
		double yaw = cfg.getDouble(name + ".yaw");
		double pitch = cfg.getDouble(name + ".pitch");
		
		Location loc = new Location(Bukkit.getWorld(world), x, y, z);
		loc.setYaw((float) yaw);
		loc.setPitch((float) pitch);
		
		return loc;
		
	}
	
	private static void saveLoc(String name, Location loc) {
		
		File file = new File("plugins/KnowNixLobbySystem", "locations.yml");
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		
		String world = loc.getWorld().getName();
		double x = loc.getX();
		double y = loc.getY();
		double z = loc.getZ();
		double yaw = loc.getYaw();
		double pitch = loc.getPitch();
		
		cfg.set(name + ".world", world);
		cfg.set(name + ".x", x);
		cfg.set(name + ".y", y);
		cfg.set(name + ".z", z);
		cfg.set(name + ".yaw", yaw);
		cfg.set(name + ".pitch", pitch);
		
		try {
			cfg.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
