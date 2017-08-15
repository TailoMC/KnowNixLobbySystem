package me.Tailo.KnowNixLobbySystem.Methoden;

import java.lang.reflect.Field;

import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_8_R3.PacketPlayOutPlayerListHeaderFooter;

import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class Tab_methoden {
	
	public static void setTablist(Player p) {
		
		IChatBaseComponent header = ChatSerializer.a("{\"text\": \"\n §6§lKnowNix.de §8» §e§lNetzwerk\n    §7Dein Server §8» §aLobby \n\"}");
		IChatBaseComponent footer = ChatSerializer.a("{\"text\": \"\n §bTwitter §8» §e@KnownixDE \n §7TeamSpeak §8» §eKnowNix.de \n\"}");
		
		PacketPlayOutPlayerListHeaderFooter packet = new PacketPlayOutPlayerListHeaderFooter(header);
		
		try {
			Field f = packet.getClass().getDeclaredField("b");
			f.setAccessible(true);
			f.set(packet, footer);
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
		
		((CraftPlayer)p).getHandle().playerConnection.sendPacket(packet);
		
	}

}
