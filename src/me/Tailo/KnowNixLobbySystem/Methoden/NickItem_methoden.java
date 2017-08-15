package me.Tailo.KnowNixLobbySystem.Methoden;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

import me.Tailo.KnowNixSystem.MySQL.MySQL;
import me.Tailo.NickSystem.Nick.Nick;
import me.Tailo.NickSystem.Nick.NickAPI;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scoreboard.Team;

public class NickItem_methoden {
	
	private static String prefix = "§5Nick §8» §r";

	public static void openNickGui(Player p) {
		
		Inventory inv = Bukkit.createInventory(null, 36, "§cNick Einstellungen");
		
		//==========================================================
		ItemStack rank = new ItemStack(Material.NAME_TAG);
		ItemMeta rankmeta = rank.getItemMeta();
		rankmeta.setDisplayName("§cRang");
		rankmeta.setLore(Arrays.asList("§7Der Rang, als welcher du genickt wirst"));
		rank.setItemMeta(rankmeta);
		//==========================================================		
		ItemStack keepnick = new ItemStack(Material.TRIPWIRE_HOOK);
		ItemMeta keepnickmeta = keepnick.getItemMeta();
		keepnickmeta.setDisplayName("§cKeepNick");
		keepnickmeta.setLore(Arrays.asList("§7Automatisch nach einer Runde genickt bleiben"));
		keepnick.setItemMeta(keepnickmeta);
		//==========================================================
		ItemStack lobbynick = new ItemStack(Material.NETHER_STAR);
		ItemMeta lobbynickmeta = lobbynick.getItemMeta();
		lobbynickmeta.setDisplayName("§cLobbyNick");
		lobbynickmeta.setLore(Arrays.asList("§7Aktiviert den automatischen Nicknamen für die Lobby"));
		lobbynick.setItemMeta(lobbynickmeta);
		//==========================================================
		ItemStack seenicked = new ItemStack(Material.EYE_OF_ENDER);
		ItemMeta seenickedmeta = seenicked.getItemMeta();
		seenickedmeta.setDisplayName("§cSeeNicked");
		seenickedmeta.setLore(Arrays.asList("§7Sehe dich und andere genickt"));
		seenicked.setItemMeta(seenickedmeta);
		//==========================================================
		
		if(isPremiumEnabled(p)) {
			
			//==========================================================		
			ItemStack premium = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 1);
			ItemMeta premiummeta = premium.getItemMeta();
			premiummeta.setDisplayName("§6Premium");
			premium.setItemMeta(premiummeta);
			//==========================================================
			
			inv.setItem(21, premium);
			
		} else {
			
			//==========================================================		
			ItemStack spieler = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 5);
			ItemMeta spielermeta = spieler.getItemMeta();
			spielermeta.setDisplayName("§aSpieler");
			spieler.setItemMeta(spielermeta);
			//==========================================================
			
			inv.setItem(21, spieler);
			
		}
		
		if(isKeepNickEnabled(p)) {
			
			//==========================================================		
			ItemStack aktiv = new ItemStack(Material.INK_SACK, 1, (byte) 10);
			ItemMeta aktivmeta = aktiv.getItemMeta();
			aktivmeta.setDisplayName("§aAktiv");
			aktiv.setItemMeta(aktivmeta);
			//==========================================================
			
			inv.setItem(25, aktiv);
			
		} else {
			
			//==========================================================		
			ItemStack inaktiv = new ItemStack(Material.INK_SACK, 1, (byte) 8);
			ItemMeta inaktivmeta = inaktiv.getItemMeta();
			inaktivmeta.setDisplayName("§cInaktiv");
			inaktiv.setItemMeta(inaktivmeta);
			//==========================================================
			
			inv.setItem(25, inaktiv);
			
		}
		
		if(isLobbyNickEnabled(p)) {
			
			//==========================================================		
			ItemStack aktiv = new ItemStack(Material.INK_SACK, 1, (byte) 10);
			ItemMeta aktivmeta = aktiv.getItemMeta();
			aktivmeta.setDisplayName("§aAktiv");
			aktiv.setItemMeta(aktivmeta);
			//==========================================================
			
			inv.setItem(19, aktiv);
			
		} else {
			
			//==========================================================		
			ItemStack inaktiv = new ItemStack(Material.INK_SACK, 1, (byte) 8);
			ItemMeta inaktivmeta = inaktiv.getItemMeta();
			inaktivmeta.setDisplayName("§cInaktiv");
			inaktiv.setItemMeta(inaktivmeta);
			//==========================================================
			
			inv.setItem(19, inaktiv);
			
		}
		
		if(isSeeNickedEnabled(p)) {
			
			//==========================================================		
			ItemStack aktiv = new ItemStack(Material.INK_SACK, 1, (byte) 10);
			ItemMeta aktivmeta = aktiv.getItemMeta();
			aktivmeta.setDisplayName("§aAktiv");
			aktiv.setItemMeta(aktivmeta);
			//==========================================================
			
			inv.setItem(23, aktiv);
			
		} else {
			
			//==========================================================		
			ItemStack inaktiv = new ItemStack(Material.INK_SACK, 1, (byte) 8);
			ItemMeta inaktivmeta = inaktiv.getItemMeta();
			inaktivmeta.setDisplayName("§cInaktiv");
			inaktiv.setItemMeta(inaktivmeta);
			//==========================================================
			
			inv.setItem(23, inaktiv);
			
		}
		
		inv.setItem(12, rank);
		inv.setItem(16, keepnick);
		inv.setItem(10, lobbynick);
		inv.setItem(14, seenicked);
		
		p.openInventory(inv);
		
	}
	
	public static boolean isPremiumEnabled(Player p) {
		
		if(p.hasPermission("premiumplus")) {
			
			ResultSet rs = MySQL.getResult("SELECT premium FROM nick WHERE UUID = '" + p.getUniqueId().toString() + "'");
			
			try {
				while(rs.next()) {
					return rs.getBoolean("premium");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return false;
			
		}
		
		return false;
		
	}
	
	public static void togglePremium(Player p, Inventory inv) {
		
		ResultSet rs = MySQL.getResult("SELECT premium FROM nick WHERE UUID = '" + p.getUniqueId().toString() + "'");
		
		try {
			while(rs.next()) {
				boolean bol = rs.getBoolean("premium");
				
				if(bol) {
					
					//==========================================================		
					ItemStack spieler = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 5);
					ItemMeta spielermeta = spieler.getItemMeta();
					spielermeta.setDisplayName("§aSpieler");
					spieler.setItemMeta(spielermeta);
					//==========================================================
					
					MySQL.update("UPDATE nick SET premium = '" + 0 + "' WHERE UUID = '" + p.getUniqueId().toString() + "'");
					inv.setItem(21, spieler);
					p.playSound(p.getLocation(), Sound.NOTE_PLING, 1.0F, 1.0F);
				} else {
					
					//==========================================================		
					ItemStack premium = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 1);
					ItemMeta premiummeta = premium.getItemMeta();
					premiummeta.setDisplayName("§6Premium");
					premium.setItemMeta(premiummeta);
					//==========================================================
					
					MySQL.update("UPDATE nick SET premium = '" + 1 + "' WHERE UUID = '" + p.getUniqueId().toString() + "'");
					inv.setItem(21, premium);
					p.playSound(p.getLocation(), Sound.NOTE_PLING, 1.0F, 1.0F);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static boolean isKeepNickEnabled(Player p) {
		
		if(p.hasPermission("premiumplus")) {
			
			ResultSet rs = MySQL.getResult("SELECT keepnick FROM nick WHERE UUID = '" + p.getUniqueId().toString() + "'");
			
			try {
				while(rs.next()) {
					return rs.getBoolean("keepnick");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return false;
			
		}
		
		return false;
		
	}
	
	public static void toggleKeepNick(Player p, Inventory inv) {
		
		ResultSet rs = MySQL.getResult("SELECT keepnick FROM nick WHERE UUID = '" + p.getUniqueId().toString() + "'");
		
		try {
			while(rs.next()) {
				boolean bol = rs.getBoolean("keepnick");
				
				if(bol) {
					
					//==========================================================		
					ItemStack inaktiv = new ItemStack(Material.INK_SACK, 1, (byte) 8);
					ItemMeta inaktivmeta = inaktiv.getItemMeta();
					inaktivmeta.setDisplayName("§cInaktiv");
					inaktiv.setItemMeta(inaktivmeta);
					//==========================================================
					
					MySQL.update("UPDATE nick SET keepnick = '" + 0 + "' WHERE UUID = '" + p.getUniqueId().toString() + "'");
					inv.setItem(25, inaktiv);
					p.playSound(p.getLocation(), Sound.NOTE_PLING, 1.0F, 1.0F);
				} else {
					
					//==========================================================		
					ItemStack aktiv = new ItemStack(Material.INK_SACK, 1, (byte) 10);
					ItemMeta aktivmeta = aktiv.getItemMeta();
					aktivmeta.setDisplayName("§aAktiv");
					aktiv.setItemMeta(aktivmeta);
					//==========================================================
					
					MySQL.update("UPDATE nick SET keepnick = '" + 1 + "' WHERE UUID = '" + p.getUniqueId().toString() + "'");
					inv.setItem(25, aktiv);
					p.playSound(p.getLocation(), Sound.NOTE_PLING, 1.0F, 1.0F);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static boolean isLobbyNickEnabled(Player p) {
		
		if(p.hasPermission("premiumplus")) {
			
			ResultSet rs = MySQL.getResult("SELECT lobbynick FROM nick WHERE UUID = '" + p.getUniqueId().toString() + "'");
			
			try {
				while(rs.next()) {
					return rs.getBoolean("lobbynick");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return false;
			
		}
		
		return false;
		
	}
	
	public static void toggleLobbyNick(Player p, Inventory inv) {
		
		ResultSet rs = MySQL.getResult("SELECT lobbynick FROM nick WHERE UUID = '" + p.getUniqueId().toString() + "'");
		
		try {
			while(rs.next()) {
				boolean bol = rs.getBoolean("lobbynick");
				
				if(bol) {
					
					//==========================================================		
					ItemStack inaktiv = new ItemStack(Material.INK_SACK, 1, (byte) 8);
					ItemMeta inaktivmeta = inaktiv.getItemMeta();
					inaktivmeta.setDisplayName("§cInaktiv");
					inaktiv.setItemMeta(inaktivmeta);
					//==========================================================
					
					MySQL.update("UPDATE nick SET lobbynick = '" + 0 + "' WHERE UUID = '" + p.getUniqueId().toString() + "'");
					inv.setItem(19, inaktiv);
					p.playSound(p.getLocation(), Sound.NOTE_PLING, 1.0F, 1.0F);
				} else {
					
					//==========================================================		
					ItemStack aktiv = new ItemStack(Material.INK_SACK, 1, (byte) 10);
					ItemMeta aktivmeta = aktiv.getItemMeta();
					aktivmeta.setDisplayName("§aAktiv");
					aktiv.setItemMeta(aktivmeta);
					//==========================================================
					
					MySQL.update("UPDATE nick SET lobbynick = '" + 1 + "' WHERE UUID = '" + p.getUniqueId().toString() + "'");
					inv.setItem(19, aktiv);
					p.playSound(p.getLocation(), Sound.NOTE_PLING, 1.0F, 1.0F);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static boolean isSeeNickedEnabled(Player p) {
		
		if(p.hasPermission("premiumplus")) {
			
			ResultSet rs = MySQL.getResult("SELECT seenicked FROM nick WHERE UUID = '" + p.getUniqueId().toString() + "'");
			
			try {
				while(rs.next()) {
					return rs.getBoolean("seenicked");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return false;
			
		}
		
		return false;
		
	}
	
	public static void toggleSeeNicked(Player p, Inventory inv) {
		
		ResultSet rs = MySQL.getResult("SELECT seenicked FROM nick WHERE UUID = '" + p.getUniqueId().toString() + "'");
		
		try {
			while(rs.next()) {
				boolean bol = rs.getBoolean("seenicked");
				
				if(bol) {
					
					//==========================================================		
					ItemStack inaktiv = new ItemStack(Material.INK_SACK, 1, (byte) 8);
					ItemMeta inaktivmeta = inaktiv.getItemMeta();
					inaktivmeta.setDisplayName("§cInaktiv");
					inaktiv.setItemMeta(inaktivmeta);
					//==========================================================
					
					MySQL.update("UPDATE nick SET seenicked = '" + 0 + "' WHERE UUID = '" + p.getUniqueId().toString() + "'");
					inv.setItem(23, inaktiv);
					p.playSound(p.getLocation(), Sound.NOTE_PLING, 1.0F, 1.0F);
				} else {
					
					//==========================================================		
					ItemStack aktiv = new ItemStack(Material.INK_SACK, 1, (byte) 10);
					ItemMeta aktivmeta = aktiv.getItemMeta();
					aktivmeta.setDisplayName("§aAktiv");
					aktiv.setItemMeta(aktivmeta);
					//==========================================================
					
					MySQL.update("UPDATE nick SET seenicked = '" + 1 + "' WHERE UUID = '" + p.getUniqueId().toString() + "'");
					inv.setItem(23, aktiv);
					p.playSound(p.getLocation(), Sound.NOTE_PLING, 1.0F, 1.0F);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void nick(Player p) {
		
		if(NickAPI.isPremiumEnabled(p)) {
			
			Team t = Bukkit.getScoreboardManager().getMainScoreboard().getTeam("kkk");
			if(t == null) {
				t = Bukkit.getScoreboardManager().getMainScoreboard().registerNewTeam("kkk");
				t.setPrefix("§6Premium §8× §6");
			}
			
			Nick n = NickAPI.nickRandom(p, t, "§6");
			
			p.sendMessage("");
			p.sendMessage(prefix + "§4Aktueller Nickname§8: §6" + n.getNick());
			p.sendMessage("");
			
			Inventory_methoden.setPremiumBoots(p);
			
		} else {
			
			Team t = Bukkit.getScoreboardManager().getMainScoreboard().getTeam("lll");
			if(t == null) {
				t = Bukkit.getScoreboardManager().getMainScoreboard().registerNewTeam("lll");
				t.setPrefix("§aSpieler §8× §a");
			}
			
			Nick n = NickAPI.nickRandom(p, t, "§a");
			
			p.sendMessage("");
			p.sendMessage(prefix + "§4Aktueller Nickname§8: §a" + n.getNick());
			p.sendMessage("");
			
			p.getInventory().setBoots(null);
			
		}
		
	}
	
}
