package me.Tailo.KnowNixLobbySystem.Items;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

import me.Tailo.KnowNixLobbySystem.Methoden.Friend_methoden;
import me.Tailo.KnowNixLobbySystem.System.main;
import me.Tailo.NickSystem.Nick.NickAPI;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class Item_friend implements Listener {

	private main plugin;

	public Item_friend(main main) {
		this.plugin = main;
		plugin.getServer().getPluginManager().registerEvents(this, main);
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		
		if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			try {
				if(e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §4Freunde")){
					
					Player p = e.getPlayer();
					
					Friend_methoden.openInv(p, 1);
				}
			} catch(Exception ex) {				
			}
		}
		
	}
	
	@EventHandler
	public void onEntityDamageByEntity(EntityDamageByEntityEvent e) {
		
		if(e.getDamager() instanceof Player) {
			
			Player p = (Player) e.getDamager();
			
			if(e.getEntity() instanceof Player) {
				
				try {
					
					if(p.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §4Freunde")) {
						
						Player target = (Player) e.getEntity();
						
						if(!NickAPI.isNicked(target)) {
							
							send(p, "sendrequest", target.getName());
							
						} else {
							
							p.sendMessage("§4Freunde §8» §r§cDieser Spieler nimmt keine Freundschaftsanfragen an!");
							
						}						
						
					}
					
				} catch(Exception ex) {					
				}
				
			}
			
		}
		
	}
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent e){
		
		if(e.getSlot() == e.getRawSlot()) {
			final Player p = (Player) e.getWhoClicked();
			
			if(e.getInventory().getTitle().startsWith("§aFreunde")) {
				e.setCancelled(true);
				
				try {
					ItemStack item = e.getCurrentItem();
					
					if(item != null) {
						
						if(item.getItemMeta().getLore() != null && item.getItemMeta().getLore().size() == 2) {
							
							String name = ChatColor.stripColor(item.getItemMeta().getDisplayName());
							
							Friend_methoden.openOnlineOptions(p, name);
							
						} else {
							
							if(item.getItemMeta().getDisplayName().equalsIgnoreCase("§5Freundschaftsanfragen")) {
								
								if(item.getAmount() != 0) {
									
									Friend_methoden.openRequests(p);
									
								} else {
									
									p.sendMessage(plugin.prefix + "§cDu hast keine Freundschaftsanfragen!");
									
								}
								
							} else if(item.getItemMeta().getDisplayName().contains("Freundschaftsanfragen erlauben")) {
								
								Friend_methoden.toggleFriendRequests(p, e.getInventory());
								
							} else if(item.getItemMeta().getDisplayName().contains("Partyanfragen erlauben")) {
								
								Friend_methoden.togglePartyRequests(p, e.getInventory());
								
							} else if(item.getItemMeta().getDisplayName().contains("Nachspringen erlauben")) {
								
								Friend_methoden.toggleJump(p, e.getInventory());
								
							} else if(item.getItemMeta().getDisplayName().contains("Private Nachrichten")) {
								
								Friend_methoden.toggleMsg(p, e.getInventory());
								
							} else if(item.getItemMeta().getDisplayName().contains("Online/Offline Nachrichten")) {
								
								Friend_methoden.toggleOnlineMsg(p, e.getInventory());
								
							} else if(item.getItemMeta().getDisplayName().equalsIgnoreCase("§eVorige Seite")) {
								
								int page = Integer.parseInt(e.getInventory().getTitle().replace("§aFreunde §7(Seite ", "").replace(")", ""));
								
								Friend_methoden.openInv(p, page - 1);
								
							} else if(item.getItemMeta().getDisplayName().equalsIgnoreCase("§eNächste Seite")) {
								
								int page = Integer.parseInt(e.getInventory().getTitle().replace("§aFreunde §7(Seite ", "").replace(")", ""));
								
								Friend_methoden.openInv(p, page + 1);
								
							} else {
								
								String name = ChatColor.stripColor(item.getItemMeta().getDisplayName());
									
								Friend_methoden.openOfflineOptions(p, name);
									
							}
							
						}
						
					}
					
				} catch(Exception ex) {					
				}
				
			}
			
			if(e.getInventory().getTitle().contains(" §centfernen?")) {
				e.setCancelled(true);
				
				try {
					ItemStack item = e.getCurrentItem();
					
					String name = ChatColor.stripColor(e.getInventory().getTitle()).replace(" entfernen?", "");
					
					if(item.getItemMeta().getDisplayName().equalsIgnoreCase("§aJa")) {
						
						send(p, "removefriend", name);
						p.closeInventory();
						
					}
					
					if(item.getItemMeta().getDisplayName().equalsIgnoreCase("§cNein")) {
						
						Friend_methoden.openInv(p, 1);
						
					}
					
				} catch(Exception ex) {					
				}
				
			}
			
			if(e.getInventory().getTitle().startsWith("§b") | e.getInventory().getTitle().startsWith("§a")) {
				e.setCancelled(true);
				
				try {
					
					ItemStack item = e.getCurrentItem();
					
					String name = ChatColor.stripColor(e.getInventory().getTitle());
					
					if(item.getItemMeta().getDisplayName().contains(" §centfernen")) {
						
						Friend_methoden.openConfirm(p, name);
						
					}
					
					if(item.getItemMeta().getDisplayName().contains(" §5in deine Party einladen")) {
						e.setCancelled(true);
						
						try {
							
							send(p, "party", name);
							p.closeInventory();
							
						} catch(Exception ex) {					
						}
						
					}
					
					if(item.getItemMeta().getDisplayName().contains(" §aspringen")) {
						e.setCancelled(true);
						
						try {
							
							send(p, "jump", name);
							p.closeInventory();
							
						} catch(Exception ex) {					
						}
						
					}
					
					if(item.getItemMeta().getDisplayName().equalsIgnoreCase("§9Zurück")) {
						
						Friend_methoden.openInv(p, 1);
						
					}
					
				} catch(Exception ex) {					
				}
				
			}
			
			if(e.getInventory().getTitle().equalsIgnoreCase("§5Freundschaftsanfragen")) {
				e.setCancelled(true);
				
				try {
					ItemStack item = e.getCurrentItem();
					
					if(item != null) {
						
						if(item.getItemMeta().getDisplayName().equalsIgnoreCase("§9Zurück")) {
							
							Friend_methoden.openInv(p, 1);
							
							return;
						}
						
						String name = ChatColor.stripColor(item.getItemMeta().getDisplayName());
						
						if(e.isLeftClick()) {
							
							send(p, "acceptfriend", name);
							
						}
						
						if(e.isRightClick()) {
							
							send(p, "denyfriend", name);
							
						}
						
						Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {							
							@Override
							public void run() {
								Friend_methoden.openRequests(p);
							}
						}, 3L);
						
					}
					
				} catch(Exception ex) {					
				}
				
			}
			
			if(e.getInventory().getTitle().equalsIgnoreCase("§9Einstellungen")) {
				e.setCancelled(true);
				
				try {
					ItemStack item = e.getCurrentItem();
					
					if(item.getItemMeta().getDisplayName().equalsIgnoreCase("§aErlaube Freunden zu dir zu springen") || e.getSlot() == 13) {
						
						Friend_methoden.toggleJump(p, e.getInventory());
						
					}
					
					if(item.getItemMeta().getDisplayName().equalsIgnoreCase("§bOnline/Offline Benachrichtigungen") || e.getSlot() == 11) {
						
						Friend_methoden.toggleOnlineMsg(p, e.getInventory());
						
					}
					
					if(item.getItemMeta().getDisplayName().equalsIgnoreCase("§6Erlaube Freundschaftsanfragen") || e.getSlot() == 15) {
						
						Friend_methoden.toggleFriendRequests(p, e.getInventory());
						
					}
					
					if(item.getItemMeta().getDisplayName().equalsIgnoreCase("§9Zurück")) {
						
						Friend_methoden.openInv(p, 1);
						
					}
					
				} catch(Exception ex) {					
				}
				
			}
			
		}
		
	}
	
	private static void send(Player p, String channel, String input) {
		
		ByteArrayOutputStream b = new ByteArrayOutputStream();
		DataOutputStream out = new DataOutputStream(b);
		
		try {
			out.writeUTF(channel);
			out.writeUTF(input);
		} catch(Exception ex) {
		}
		
		p.sendPluginMessage(main.instance, "BungeeCord", b.toByteArray());
		
	}

}
