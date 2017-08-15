package me.Tailo.KnowNixLobbySystem.Methoden;

import java.sql.ResultSet;
import java.sql.SQLException;

import me.Tailo.KnowNixSystem.MySQL.MySQL;

import org.bukkit.entity.Player;

public class Settings_methoden {

	public static boolean isNickEnabled(Player p) {
		
		ResultSet rs = MySQL.getResult("SELECT nick FROM nick WHERE UUID = '" + p.getUniqueId().toString() + "'");
		
		try {
			while(rs.next()) {
				return rs.getBoolean("nick");
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		
		return false;
		
	}
	
}
