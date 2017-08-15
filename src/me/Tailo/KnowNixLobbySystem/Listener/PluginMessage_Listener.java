package me.Tailo.KnowNixLobbySystem.Listener;

import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;

import me.Tailo.KnowNixLobbySystem.Methoden.InfoBoard;

public class PluginMessage_Listener implements PluginMessageListener {

	public PluginMessage_Listener() {
	}

	@Override
	public void onPluginMessageReceived(String channel, Player p, byte[] message) {
		
		if(channel.equals("BungeeCord")) {
			
			ByteArrayDataInput in = ByteStreams.newDataInput(message);
			String subchannel = in.readUTF();
			
			if(subchannel.equals("PlayerCount")) {
				
				in.readUTF();
				InfoBoard.online = in.readInt();
				
			}
			
		}
		
	}

}
