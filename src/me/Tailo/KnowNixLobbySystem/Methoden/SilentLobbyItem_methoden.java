package me.Tailo.KnowNixLobbySystem.Methoden;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

import me.Tailo.KnowNixLobbySystem.System.main;

import org.bukkit.entity.Player;

public class SilentLobbyItem_methoden {

	public static void connectToSilentLobby(Player p) {
		
		ByteArrayOutputStream b = new ByteArrayOutputStream();
		DataOutputStream out = new DataOutputStream(b);
		
		try {
			out.writeUTF("Connect");
			out.writeUTF("silentlobby-1");
		} catch(Exception ex) {
		}
		
		p.sendPluginMessage(main.instance, "BungeeCord", b.toByteArray());
		
	}
	
}
