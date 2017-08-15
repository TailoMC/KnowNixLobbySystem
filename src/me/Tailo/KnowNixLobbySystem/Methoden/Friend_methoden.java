package me.Tailo.KnowNixLobbySystem.Methoden;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Executors;

import me.Tailo.KnowNixSystem.MySQL.MySQL;
import me.Tailo.KnowNixSystem.Permission.UUIDFetcher;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class Friend_methoden {
	
	public static void openInv(Player p, int page) {
		
		Inventory inv = Bukkit.createInventory(null, 54, "§aFreunde §7(Seite " + page + ")");
		int i = 0;
		
		int max = page * 36;
		int min = max - 36;
		
		String string = "";
		
		ResultSet rs = MySQL.getResult("SELECT friends FROM friends WHERE UUID = '" + p.getUniqueId().toString() + "'");
		
		try {
			while(rs.next()) {
				string = rs.getString("friends");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		List<String> list = new ArrayList<String>(Arrays.asList(string.split(";")));
		
		list.remove("");
		
		HashMap<String, String> online = new HashMap<>();
		List<String> offline = new ArrayList<String>();
		
		for(String uuid : list) {
			
			String server = "";
			
			ResultSet rs1 = MySQL.getResult("SELECT server FROM friends WHERE UUID = '" + uuid + "'");
			
			try {
				while(rs1.next()) {
					server = rs1.getString("server");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			if(!server.equals("")) {
				
				online.put(uuid, server);
				
			} else {
				
				offline.add(uuid);
				
			}
			
		}
		
		List<String> friendlist = new ArrayList<String>();
		for(String uuid : online.keySet()) {
			friendlist.add(uuid);
		}
		for(String uuid : offline) {
			friendlist.add(uuid);
		}
		
		for(int count = min; count < max && count < friendlist.size();) {
			
			String uuid = friendlist.get(count);
			
			if(online.containsKey(uuid)) {
				
				UUID id = UUID.fromString(uuid);
				
				String name = getRankColor(id) + UUIDFetcher.getName(id);
				
				List<String> lore = new ArrayList<>();
				lore.add("§aOnline");
				lore.add("§7» §e" + online.get(uuid));
				
				ItemStack item = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
				SkullMeta itemeta = (SkullMeta) item.getItemMeta();
				itemeta.setDisplayName(name);
				itemeta.setOwner(UUIDFetcher.getName(id));
				itemeta.setLore(lore);
				item.setItemMeta(itemeta);
				
				inv.setItem(i, item);
				
				i++;
				
			} else {
				
				List<String> lore = new ArrayList<>();
				lore.add("§cOffline");
				
				UUID id = UUID.fromString(uuid);
				
				String name = getRankColor(id) + UUIDFetcher.getName(id);
				
				ItemStack item = new ItemStack(Material.SKULL_ITEM);
				SkullMeta itemeta = (SkullMeta) item.getItemMeta();
				itemeta.setDisplayName(name);
				itemeta.setLore(lore);
				item.setItemMeta(itemeta);
				
				inv.setItem(i, item);
				
				i++;
				
			}
						
			count ++;
		}
		
		String requests = "";
		
		ResultSet rs2 = MySQL.getResult("SELECT requests FROM friends WHERE UUID = '" + p.getUniqueId().toString() + "'");
		
		try {
			while(rs2.next()) {
				requests = rs2.getString("requests");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		List<String> list2 = new ArrayList<String>(Arrays.asList(requests.split(";")));
		
		list2.remove("");
		
		//=====================================================
		List<String> lore = new ArrayList<>();
		lore.add("§a" + list2.size() + " §eAnfrage(n)");
		
		ItemStack request = new ItemStack(Material.PAPER, list2.size());
		ItemMeta requestmeta = request.getItemMeta();
		requestmeta.setDisplayName("§5Freundschaftsanfragen");
		requestmeta.setLore(lore);
		request.setItemMeta(requestmeta);
		//=====================================================
		
		boolean allowrequestsbol = hasRequestsEnabled(p);
		boolean allowpartyrequestsbol = hasPartyRequestsEnabled(p);
		boolean allowjumpbol = hasJumpEnabled(p);
		boolean allowmsgbol = hasMsgEnabled(p);
		boolean allowonlinebol = hasOnlineMsgEnabled(p);
		
		//=====================================================
		ItemStack allowrequests = new ItemStack(Material.STAINED_CLAY, 1, allowrequestsbol ? (byte)13 : (byte)14);
		ItemMeta allowrequestsmeta = allowrequests.getItemMeta();
		allowrequestsmeta.setDisplayName((allowrequestsbol ? "§a" : "§c") + "Freundschaftsanfragen erlauben");
		allowrequests.setItemMeta(allowrequestsmeta);
		//=====================================================
		ItemStack allowpartyrequests = new ItemStack(Material.STAINED_CLAY, 1, allowpartyrequestsbol ? (byte)13 : (byte)14);
		ItemMeta allowpartyrequestsmeta = allowpartyrequests.getItemMeta();
		allowpartyrequestsmeta.setDisplayName((allowpartyrequestsbol ? "§a" : "§c") + "Partyanfragen erlauben");
		allowpartyrequests.setItemMeta(allowpartyrequestsmeta);
		//=====================================================
		ItemStack allowjump = new ItemStack(Material.STAINED_CLAY, 1, allowjumpbol ? (byte)13 : (byte)14);
		ItemMeta allowjumpmeta = allowjump.getItemMeta();
		allowjumpmeta.setDisplayName((allowjumpbol ? "§a" : "§c") + "Nachspringen erlauben");
		allowjump.setItemMeta(allowjumpmeta);
		//=====================================================
		ItemStack allowmsg = new ItemStack(Material.STAINED_CLAY, 1, allowmsgbol ? (byte)13 : (byte)14);
		ItemMeta allowmsgmeta = allowmsg.getItemMeta();
		allowmsgmeta.setDisplayName((allowmsgbol ? "§a" : "§c") + "Private Nachrichten");
		allowmsg.setItemMeta(allowmsgmeta);
		//=====================================================
		ItemStack allowonline = new ItemStack(Material.STAINED_CLAY, 1, allowonlinebol ? (byte)13 : (byte)14);
		ItemMeta allowonlinemeta = allowonline.getItemMeta();
		allowonlinemeta.setDisplayName((allowonlinebol ? "§a" : "§c") + "Online/Offline Nachrichten");
		allowonline.setItemMeta(allowonlinemeta);
		//=====================================================
		
		if(page != 1) {
			//=====================================================
			ItemStack previous = new ItemStack(Material.IRON_DOOR);
			ItemMeta previousmeta = previous.getItemMeta();
			previousmeta.setDisplayName("§eVorige Seite");
			previous.setItemMeta(previousmeta);
			//=====================================================
			
			inv.setItem(36, previous);
		}
		if(list.size() > max) {
			//=====================================================
			ItemStack next = new ItemStack(Material.IRON_DOOR);
			ItemMeta nextmeta = next.getItemMeta();
			nextmeta.setDisplayName("§eNächste Seite");
			next.setItemMeta(nextmeta);
			//=====================================================
			
			inv.setItem(44, next);
		}
		
		inv.setItem(45, request);
		inv.setItem(49, allowrequests);
		inv.setItem(50, allowpartyrequests);
		inv.setItem(51, allowjump);
		inv.setItem(52, allowmsg);
		inv.setItem(53, allowonline);
		
		p.openInventory(inv);
		
	}
	
	public static void openOnlineOptions(Player p, String namestring) {
		
		String name = getRankColor(UUIDFetcher.getUUID(namestring)) + namestring;
		
		Inventory inv = Bukkit.createInventory(null, 27, "§a" + name);
		
		//=====================================================
		ItemStack back = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
		SkullMeta backmeta = (SkullMeta) back.getItemMeta();
		backmeta.setDisplayName("§9Zurück");
		backmeta.setOwner("MHF_ArrowLeft");
		back.setItemMeta(backmeta);
		//=====================================================
		ItemStack item = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
		SkullMeta itemeta = (SkullMeta) item.getItemMeta();
		itemeta.setDisplayName(name);
		itemeta.setOwner(namestring);
		item.setItemMeta(itemeta);
		//=====================================================
		ItemStack party = new ItemStack(Material.CAKE);
		ItemMeta partymeta = party.getItemMeta();
		partymeta.setDisplayName(name + " §5in deine Party einladen");
		party.setItemMeta(partymeta);
		//=====================================================
		ItemStack jump = new ItemStack(Material.ENDER_PEARL);
		ItemMeta jumpmeta = jump.getItemMeta();
		jumpmeta.setDisplayName("§aZu " + name + " §aspringen");
		jump.setItemMeta(jumpmeta);
		//=====================================================
		ItemStack delete = new ItemStack(Material.BARRIER);
		ItemMeta deletemeta = delete.getItemMeta();
		deletemeta.setDisplayName(name + " §centfernen");
		delete.setItemMeta(deletemeta);
		//=====================================================
		
		inv.setItem(0, back);
		inv.setItem(4, item);
		inv.setItem(11, party);
		inv.setItem(13, jump);
		inv.setItem(15, delete);
		
		p.openInventory(inv);
		
	}
	
	public static void openOfflineOptions(Player p, String namestring) {
		
		String name = getRankColor(UUIDFetcher.getUUID(namestring)) + namestring;
		
		Inventory inv = Bukkit.createInventory(null, 27, "§b" + name);
		
		//=====================================================
		ItemStack back = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
		SkullMeta backmeta = (SkullMeta) back.getItemMeta();
		backmeta.setDisplayName("§9Zurück");
		backmeta.setOwner("MHF_ArrowLeft");
		back.setItemMeta(backmeta);
		//=====================================================
		ItemStack item = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
		SkullMeta itemeta = (SkullMeta) item.getItemMeta();
		itemeta.setDisplayName(name);
		itemeta.setOwner(namestring);
		item.setItemMeta(itemeta);
		//=====================================================
		ItemStack delete = new ItemStack(Material.BARRIER);
		ItemMeta deletemeta = delete.getItemMeta();
		deletemeta.setDisplayName(name + " §centfernen");
		delete.setItemMeta(deletemeta);
		//=====================================================
		
		inv.setItem(0, back);
		inv.setItem(4, item);
		inv.setItem(13, delete);
		
		p.openInventory(inv);
		
	}
	
	public static void openRequests(Player p) {
		
		String requests = "";
		
		ResultSet rs = MySQL.getResult("SELECT requests FROM friends WHERE UUID = '" + p.getUniqueId().toString() + "'");
		
		try {
			while(rs.next()) {
				requests = rs.getString("requests");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		List<String> list = new ArrayList<String>(Arrays.asList(requests.split(";")));
		
		list.remove("");
		
		int lines = 0;
		while(lines * 9 < list.size()) {
			lines++;
		}
		
		Inventory inv = Bukkit.createInventory(null, 9 + (lines * 9), "§5Freundschaftsanfragen");
		
		int i = 9;
		
		for(String uuid : list) {
			
			UUID id = UUID.fromString(uuid);
			
			String name = getRankColor(id) + UUIDFetcher.getName(id);
			
			List<String> lore = new ArrayList<>();
			lore.add("§cLinksklick §b= annehmen");
			lore.add("§cRechtsklick §b= ablehnen");
			
			ItemStack item = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
			SkullMeta itemeta = (SkullMeta) item.getItemMeta();
			itemeta.setDisplayName(name);
			itemeta.setOwner(UUIDFetcher.getName(id));
			itemeta.setLore(lore);
			item.setItemMeta(itemeta);
			
			inv.setItem(i, item);
			
			i++;
			
		}
		
		//=====================================================
		ItemStack back = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
		SkullMeta backmeta = (SkullMeta) back.getItemMeta();
		backmeta.setDisplayName("§9Zurück");
		backmeta.setOwner("MHF_ArrowLeft");
		back.setItemMeta(backmeta);
		//=====================================================
		
		inv.setItem(4, back);
		
		p.openInventory(inv);
		
	}
	
	public static void openConfirm(Player p, String name) {
		
		UUID id = UUIDFetcher.getUUID(name);
		
		Inventory inv = Bukkit.createInventory(null, 9, getRankColor(id) + name + " §centfernen?");
		
		//=====================================================
		ItemStack ja = new ItemStack(Material.WOOL, 1, (byte) 5);
		ItemMeta jameta = ja.getItemMeta();
		jameta.setDisplayName("§aJa");
		ja.setItemMeta(jameta);
		//=====================================================
		ItemStack nein = new ItemStack(Material.WOOL, 1, (byte) 14);
		ItemMeta neinmeta = nein.getItemMeta();
		neinmeta.setDisplayName("§cNein");
		nein.setItemMeta(neinmeta);
		//=====================================================
		
		inv.setItem(0, ja);
		inv.setItem(8, nein);
		
		p.openInventory(inv);
		
	}
	
	public static void preLoad(Player p) {
		
		Executors.newCachedThreadPool().execute(new Runnable() {
			@Override
			public void run() {
				
				String string = "";
				
				ResultSet rs = MySQL.getResult("SELECT friends FROM friends WHERE UUID = '" + p.getUniqueId().toString() + "'");
				
				try {
					while(rs.next()) {
						string = rs.getString("friends");
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				List<String> list = new ArrayList<String>(Arrays.asList(string.split(";")));
				
				list.remove("");
				
				list.forEach(uuid -> UUIDFetcher.getName(UUID.fromString(uuid)));
				
				String requests = "";
				
				ResultSet rs2 = MySQL.getResult("SELECT requests FROM friends WHERE UUID = '" + p.getUniqueId().toString() + "'");
				
				try {
					while(rs2.next()) {
						requests = rs2.getString("requests");
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				List<String> list2 = new ArrayList<String>(Arrays.asList(requests.split(";")));
				
				list2.remove("");
				
				list2.forEach(uuid -> UUIDFetcher.getName(UUID.fromString(uuid)));
				
			}
		});
		
	}
	
	public static boolean hasJumpEnabled(Player p) {
		
		try {
			ResultSet rs = MySQL.getResult("SELECT jump FROM friends WHERE UUID = '" + p.getUniqueId().toString() + "'");
			
			while(rs.next()) {
				return rs.getBoolean("jump");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;		
		
	}
	
	public static boolean hasRequestsEnabled(Player p) {
		
		try {
			ResultSet rs = MySQL.getResult("SELECT friendrequest FROM friends WHERE UUID = '" + p.getUniqueId().toString() + "'");
			
			while(rs.next()) {				
				return rs.getBoolean("friendrequest");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;		
		
	}
	
	public static boolean hasOnlineMsgEnabled(Player p) {
		
		try {
			ResultSet rs = MySQL.getResult("SELECT friendonline FROM friends WHERE UUID = '" + p.getUniqueId().toString() + "'");
			
			while(rs.next()) {
				return rs.getBoolean("friendonline");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;		
		
	}
	
	public static boolean hasPartyRequestsEnabled(Player p) {
		
		try {
			ResultSet rs = MySQL.getResult("SELECT party FROM friends WHERE UUID = '" + p.getUniqueId().toString() + "'");
			
			while(rs.next()) {
				return rs.getBoolean("party");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;		
		
	}
	
	public static boolean hasMsgEnabled(Player p) {
		
		try {
			ResultSet rs = MySQL.getResult("SELECT msg FROM friends WHERE UUID = '" + p.getUniqueId().toString() + "'");
			
			while(rs.next()) {
				return rs.getBoolean("msg");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;		
		
	}
	
	public static void toggleJump(Player p, Inventory inv) {
		
		ResultSet rs = MySQL.getResult("SELECT jump FROM friends WHERE UUID = '" + p.getUniqueId().toString() + "'");
		
		try {
			while(rs.next()) {
				boolean bol = rs.getBoolean("jump");
				
				if(bol) {
					
					//=====================================================
					ItemStack allowjump = new ItemStack(Material.STAINED_CLAY, 1, (byte) 14);
					ItemMeta allowjumpmeta = allowjump.getItemMeta();
					allowjumpmeta.setDisplayName("§cNachspringen erlauben");
					allowjump.setItemMeta(allowjumpmeta);
					//=====================================================
					
					MySQL.update("UPDATE friends SET jump = '" + 0 + "' WHERE UUID = '" + p.getUniqueId().toString() + "'");
					inv.setItem(51, allowjump);
					p.playSound(p.getLocation(), Sound.NOTE_PLING, 1.0F, 1.0F);
				} else {
					
					//=====================================================
					ItemStack allowjump = new ItemStack(Material.STAINED_CLAY, 1, (byte) 13);
					ItemMeta allowjumpmeta = allowjump.getItemMeta();
					allowjumpmeta.setDisplayName("§aNachspringen erlauben");
					allowjump.setItemMeta(allowjumpmeta);
					//=====================================================
					
					MySQL.update("UPDATE friends SET jump = '" + 1 + "' WHERE UUID = '" + p.getUniqueId().toString() + "'");
					inv.setItem(51, allowjump);
					p.playSound(p.getLocation(), Sound.NOTE_PLING, 1.0F, 1.0F);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void toggleFriendRequests(Player p, Inventory inv) {
		
		ResultSet rs = MySQL.getResult("SELECT friendrequest FROM friends WHERE UUID = '" + p.getUniqueId().toString() + "'");
		
		try {
			while(rs.next()) {
				boolean bol = rs.getBoolean("friendrequest");
				
				if(bol) {
					
					//=====================================================
					ItemStack allowrequests = new ItemStack(Material.STAINED_CLAY, 1, (byte) 14);
					ItemMeta allowrequestsmeta = allowrequests.getItemMeta();
					allowrequestsmeta.setDisplayName("§cFreundschaftsanfragen erlauben");
					allowrequests.setItemMeta(allowrequestsmeta);
					//=====================================================
					
					MySQL.update("UPDATE friends SET friendrequest = '" + 0 + "' WHERE UUID = '" + p.getUniqueId().toString() + "'");
					inv.setItem(49, allowrequests);
					p.playSound(p.getLocation(), Sound.NOTE_PLING, 1.0F, 1.0F);
				} else {
					
					//=====================================================
					ItemStack allowrequests = new ItemStack(Material.STAINED_CLAY, 1, (byte) 13);
					ItemMeta allowrequestsmeta = allowrequests.getItemMeta();
					allowrequestsmeta.setDisplayName("§aFreundschaftsanfragen erlauben");
					allowrequests.setItemMeta(allowrequestsmeta);
					//=====================================================
					
					MySQL.update("UPDATE friends SET friendrequest = '" + 1 + "' WHERE UUID = '" + p.getUniqueId().toString() + "'");
					inv.setItem(49, allowrequests);
					p.playSound(p.getLocation(), Sound.NOTE_PLING, 1.0F, 1.0F);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void togglePartyRequests(Player p, Inventory inv) {
		
		ResultSet rs = MySQL.getResult("SELECT party FROM friends WHERE UUID = '" + p.getUniqueId().toString() + "'");
		
		try {
			while(rs.next()) {
				boolean bol = rs.getBoolean("party");
				
				if(bol) {
					
					//=====================================================
					ItemStack allowpartyrequests = new ItemStack(Material.STAINED_CLAY, 1, (byte) 14);
					ItemMeta allowpartyrequestsmeta = allowpartyrequests.getItemMeta();
					allowpartyrequestsmeta.setDisplayName("§cPartyanfragen erlauben");
					allowpartyrequests.setItemMeta(allowpartyrequestsmeta);
					//=====================================================
					
					MySQL.update("UPDATE friends SET party = '" + 0 + "' WHERE UUID = '" + p.getUniqueId().toString() + "'");
					inv.setItem(50, allowpartyrequests);
					p.playSound(p.getLocation(), Sound.NOTE_PLING, 1.0F, 1.0F);
				} else {
					
					//=====================================================
					ItemStack allowpartyrequests = new ItemStack(Material.STAINED_CLAY, 1, (byte) 13);
					ItemMeta allowpartyrequestsmeta = allowpartyrequests.getItemMeta();
					allowpartyrequestsmeta.setDisplayName("§aPartyanfragen erlauben");
					allowpartyrequests.setItemMeta(allowpartyrequestsmeta);
					//=====================================================
					
					MySQL.update("UPDATE friends SET party = '" + 1 + "' WHERE UUID = '" + p.getUniqueId().toString() + "'");
					inv.setItem(50, allowpartyrequests);
					p.playSound(p.getLocation(), Sound.NOTE_PLING, 1.0F, 1.0F);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void toggleOnlineMsg(Player p, Inventory inv) {
		
		ResultSet rs = MySQL.getResult("SELECT friendonline FROM friends WHERE UUID = '" + p.getUniqueId().toString() + "'");
		
		try {
			while(rs.next()) {
				boolean bol = rs.getBoolean("friendonline");
				
				if(bol) {
					
					//=====================================================
					ItemStack allowonline = new ItemStack(Material.STAINED_CLAY, 1, (byte) 14);
					ItemMeta allowonlinemeta = allowonline.getItemMeta();
					allowonlinemeta.setDisplayName("§cOnline/Offline Nachrichten");
					allowonline.setItemMeta(allowonlinemeta);
					//=====================================================
					
					MySQL.update("UPDATE friends SET friendonline = '" + 0 + "' WHERE UUID = '" + p.getUniqueId().toString() + "'");
					inv.setItem(53, allowonline);
					p.playSound(p.getLocation(), Sound.NOTE_PLING, 1.0F, 1.0F);
				} else {
					
					//=====================================================
					ItemStack allowonline = new ItemStack(Material.STAINED_CLAY, 1, (byte) 13);
					ItemMeta allowonlinemeta = allowonline.getItemMeta();
					allowonlinemeta.setDisplayName("§aOnline/Offline Nachrichten");
					allowonline.setItemMeta(allowonlinemeta);
					//=====================================================
					
					MySQL.update("UPDATE friends SET friendonline = '" + 1 + "' WHERE UUID = '" + p.getUniqueId().toString() + "'");
					inv.setItem(53, allowonline);
					p.playSound(p.getLocation(), Sound.NOTE_PLING, 1.0F, 1.0F);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void toggleMsg(Player p, Inventory inv) {
		
		ResultSet rs = MySQL.getResult("SELECT msg FROM friends WHERE UUID = '" + p.getUniqueId().toString() + "'");
		
		try {
			while(rs.next()) {
				boolean bol = rs.getBoolean("msg");
				
				if(bol) {
					
					//=====================================================
					ItemStack allowmsg = new ItemStack(Material.STAINED_CLAY, 1, (byte) 14);
					ItemMeta allowmsgmeta = allowmsg.getItemMeta();
					allowmsgmeta.setDisplayName("§cPrivate Nachrichten");
					allowmsg.setItemMeta(allowmsgmeta);
					//=====================================================
					
					MySQL.update("UPDATE friends SET msg = '" + 0 + "' WHERE UUID = '" + p.getUniqueId().toString() + "'");
					inv.setItem(52, allowmsg);
					p.playSound(p.getLocation(), Sound.NOTE_PLING, 1.0F, 1.0F);
				} else {
					
					//=====================================================
					ItemStack allowmsg = new ItemStack(Material.STAINED_CLAY, 1, (byte) 13);
					ItemMeta allowmsgmeta = allowmsg.getItemMeta();
					allowmsgmeta.setDisplayName("§aPrivate Nachrichten");
					allowmsg.setItemMeta(allowmsgmeta);
					//=====================================================
					
					MySQL.update("UPDATE friends SET msg = '" + 1 + "' WHERE UUID = '" + p.getUniqueId().toString() + "'");
					inv.setItem(52, allowmsg);
					p.playSound(p.getLocation(), Sound.NOTE_PLING, 1.0F, 1.0F);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static List<String> getFriends(Player p) {
		
		String string = "";
		
		ResultSet rs = MySQL.getResult("SELECT friends FROM friends WHERE UUID = '" + p.getUniqueId().toString() + "'");
		
		try {
			while(rs.next()) {
				string = rs.getString("friends");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		List<String> list = new ArrayList<String>(Arrays.asList(string.split(";")));
		
		list.remove("");
		
		return list;
		
	}
	
	private static String getRankColor(UUID uuid) {
		
		ResultSet rs = MySQL.getResult("SELECT rank FROM permissions WHERE UUID = '" + uuid.toString() + "'");
		
		try {
			while(rs.next()) {
				String groupname = rs.getString("rank");
				
				if (groupname.equalsIgnoreCase("premium")) {
					return "§6";
				} else if (groupname.equalsIgnoreCase("premiumplus")) {
					return "§6";
				} else if (groupname.equalsIgnoreCase("vip")) {
					return "§5";
				} else if (groupname.equalsIgnoreCase("builder")) {
					return "§e";
				} else if (groupname.equalsIgnoreCase("content")) {
					return "§b";
				} else if (groupname.equalsIgnoreCase("sup")) {
					return "§9";
				} else if (groupname.equalsIgnoreCase("mod")) {
					return "§c";
				} else if (groupname.equalsIgnoreCase("srmod")) {
					return "§c";
				} else if (groupname.equalsIgnoreCase("dev")) {
					return "§b";
				} else if (groupname.equalsIgnoreCase("srdev")) {
					return "§b";
				} else if (groupname.equalsIgnoreCase("admin")) {
					return "§4";
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return "§a";
		
	}
	
}
