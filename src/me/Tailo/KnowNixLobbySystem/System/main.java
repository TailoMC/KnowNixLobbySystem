package me.Tailo.KnowNixLobbySystem.System;

import java.util.ArrayList;
import java.util.HashMap;

import me.Tailo.KnowNixLobbySystem.Commands.COMMAND_build;
import me.Tailo.KnowNixLobbySystem.Commands.COMMAND_nick;
import me.Tailo.KnowNixLobbySystem.Commands.COMMAND_set;
import me.Tailo.KnowNixLobbySystem.Features.Feature_Launchpad;
import me.Tailo.KnowNixLobbySystem.Items.Item_friend;
import me.Tailo.KnowNixLobbySystem.Items.Item_hider;
import me.Tailo.KnowNixLobbySystem.Items.Item_lobbyswitcher;
import me.Tailo.KnowNixLobbySystem.Items.Item_navigator;
import me.Tailo.KnowNixLobbySystem.Items.Item_nickname;
import me.Tailo.KnowNixLobbySystem.Items.Item_silentlobby;
import me.Tailo.KnowNixLobbySystem.Listener.AsyncPlayerChatEvent_Listener;
import me.Tailo.KnowNixLobbySystem.Listener.BlockBreakEvent_Listener;
import me.Tailo.KnowNixLobbySystem.Listener.BlockPlaceEvent_Listener;
import me.Tailo.KnowNixLobbySystem.Listener.BrewEvent_Listener;
import me.Tailo.KnowNixLobbySystem.Listener.CraftItemEvent_Listener;
import me.Tailo.KnowNixLobbySystem.Listener.CreatureSpawnEvent_Listener;
import me.Tailo.KnowNixLobbySystem.Listener.EntityDamageByBlockEvent_Listener;
import me.Tailo.KnowNixLobbySystem.Listener.EntityDamageByEntityEvent_Listener;
import me.Tailo.KnowNixLobbySystem.Listener.EntityDamageEvent_Listener;
import me.Tailo.KnowNixLobbySystem.Listener.EntityDeathEvent_Listener;
import me.Tailo.KnowNixLobbySystem.Listener.EntityExplodeEvent_Listener;
import me.Tailo.KnowNixLobbySystem.Listener.EntityPortalEvent_Listener;
import me.Tailo.KnowNixLobbySystem.Listener.EntityShootBowEvent_Listener;
import me.Tailo.KnowNixLobbySystem.Listener.ExpBottleEvent_Listener;
import me.Tailo.KnowNixLobbySystem.Listener.ExplosionPrimeEvent_Listener;
import me.Tailo.KnowNixLobbySystem.Listener.FoodLevelChangeEvent_Listener;
import me.Tailo.KnowNixLobbySystem.Listener.FurnaceEvents_Listener;
import me.Tailo.KnowNixLobbySystem.Listener.HangingBreakByEntityEvent_Listener;
import me.Tailo.KnowNixLobbySystem.Listener.InventoryClickEvent_Listener;
import me.Tailo.KnowNixLobbySystem.Listener.PlayerBedEnterEvent_Listener;
import me.Tailo.KnowNixLobbySystem.Listener.PlayerBucketEmptyEvent_Listener;
import me.Tailo.KnowNixLobbySystem.Listener.PlayerCommandPreprocessEvent_Listener;
import me.Tailo.KnowNixLobbySystem.Listener.PlayerDeathEvent_Listener;
import me.Tailo.KnowNixLobbySystem.Listener.PlayerDropItemEvent_Listener;
import me.Tailo.KnowNixLobbySystem.Listener.PlayerEggThrowEvent_Listener;
import me.Tailo.KnowNixLobbySystem.Listener.PlayerExpChangeEvent_Listener;
import me.Tailo.KnowNixLobbySystem.Listener.PlayerFishEvent_Listener;
import me.Tailo.KnowNixLobbySystem.Listener.PlayerInteractEntityEvent_Listener;
import me.Tailo.KnowNixLobbySystem.Listener.PlayerInteractEvent_Listener;
import me.Tailo.KnowNixLobbySystem.Listener.PlayerJoinEvent_Listener;
import me.Tailo.KnowNixLobbySystem.Listener.PlayerLoginEvent_Listener;
import me.Tailo.KnowNixLobbySystem.Listener.PlayerPortalEvent_Listener;
import me.Tailo.KnowNixLobbySystem.Listener.PlayerQuitEvent_Listener;
import me.Tailo.KnowNixLobbySystem.Listener.PluginMessage_Listener;
import me.Tailo.KnowNixLobbySystem.Listener.PotionSplashEvent_Listener;
import me.Tailo.KnowNixLobbySystem.Listener.PrepareItemEnchantEvent_Listener;
import me.Tailo.KnowNixLobbySystem.Listener.ProjectileLaunchEvent_Listener;
import me.Tailo.KnowNixLobbySystem.Listener.SlimeSplitEvent_Listener;
import me.Tailo.KnowNixLobbySystem.Listener.WeatherChangeEvent_Listener;
import me.Tailo.KnowNixLobbySystem.Listener.WorldSaveEvent_Listener;
import me.Tailo.KnowNixLobbySystem.Methoden.Location_methoden;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class main extends JavaPlugin {
	
	public String prefix = "§a§oLobby §8§o» §r";
	
	public ArrayList<Player> build = new ArrayList<Player>();
	
	public HashMap<Player, ItemStack[]> inventory = new HashMap<>();
	public HashMap<Player, ItemStack[]> armor = new HashMap<>();
	
	public Location spawn;
	public Location knockout;
	public Location bedwars;
	public Location skywars;
	public Location community;
	public Location onevsone;
	public Location qsg;
	
	public static main instance;
	
	@Override
	public void onEnable() {
		
		instance = this;
		
		new PlayerLoginEvent_Listener(this);
		new PlayerJoinEvent_Listener(this);
		new AsyncPlayerChatEvent_Listener(this);
		new BlockBreakEvent_Listener(this);
		new BlockPlaceEvent_Listener(this);
		new EntityDamageByEntityEvent_Listener(this);
		new EntityDamageByBlockEvent_Listener(this);
		new EntityDamageEvent_Listener(this);
		new PotionSplashEvent_Listener(this);
		new EntityDeathEvent_Listener(this);
		new FoodLevelChangeEvent_Listener(this);
		new PlayerDeathEvent_Listener(this);
		new PlayerPortalEvent_Listener(this);
		new PrepareItemEnchantEvent_Listener(this);
		new CreatureSpawnEvent_Listener(this);
		new EntityExplodeEvent_Listener(this);
		new EntityPortalEvent_Listener(this);
		new EntityShootBowEvent_Listener(this);
		new ExpBottleEvent_Listener(this);
		new ProjectileLaunchEvent_Listener(this);
		new SlimeSplitEvent_Listener(this);
		new PlayerBedEnterEvent_Listener(this);
		new PlayerBucketEmptyEvent_Listener(this);
		new PlayerCommandPreprocessEvent_Listener(this);
		new PlayerDropItemEvent_Listener(this);
		new PlayerEggThrowEvent_Listener(this);
		new PlayerExpChangeEvent_Listener(this);
		new BrewEvent_Listener(this);
		new CraftItemEvent_Listener(this);
		new FurnaceEvents_Listener(this);
		new InventoryClickEvent_Listener(this);
		new ExplosionPrimeEvent_Listener(this);
		new PlayerFishEvent_Listener(this);
		new WeatherChangeEvent_Listener(this);
		new WorldSaveEvent_Listener(this);
		new HangingBreakByEntityEvent_Listener(this);
		new PlayerInteractEvent_Listener(this);
		new PlayerInteractEntityEvent_Listener(this);
		new PlayerQuitEvent_Listener(this);
		
		new Item_navigator(this);
		new Item_nickname(this);
		new Item_hider(this);
		new Item_lobbyswitcher(this);
		new Item_silentlobby(this);
		new Item_friend(this);
		
		new Feature_Launchpad(this);
		
		new Location_methoden(this);
		
		getCommand("build").setExecutor(new COMMAND_build(this));
		getCommand("set").setExecutor(new COMMAND_set(this));
		getCommand("nick").setExecutor(new COMMAND_nick(this));
		
		getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
		getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord", new PluginMessage_Listener());
		
	}

}
