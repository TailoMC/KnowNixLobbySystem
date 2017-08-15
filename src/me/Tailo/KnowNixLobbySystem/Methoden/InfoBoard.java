package me.Tailo.KnowNixLobbySystem.Methoden;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;

import me.Tailo.KnowNixLobbySystem.System.main;
import net.minecraft.server.v1_8_R3.IScoreboardCriteria;
import net.minecraft.server.v1_8_R3.PacketPlayOutScoreboardDisplayObjective;
import net.minecraft.server.v1_8_R3.PacketPlayOutScoreboardObjective;
import net.minecraft.server.v1_8_R3.PacketPlayOutScoreboardScore;
import net.minecraft.server.v1_8_R3.PlayerConnection;
import net.minecraft.server.v1_8_R3.Scoreboard;
import net.minecraft.server.v1_8_R3.ScoreboardObjective;
import net.minecraft.server.v1_8_R3.ScoreboardScore;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import com.google.common.collect.Iterables;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

public class InfoBoard {	

	private static HashMap<Player, InfoBoard> getboard = new HashMap<>();
	
	private Player p;
	
	Scoreboard board;
	ScoreboardObjective obj;

	static int run;
	public static int online;
	
	ScoreboardScore rank;
	ScoreboardScore players;
	
	public InfoBoard(Player p) {
		this.p = p;
		setBoard();
		getboard.put(p, this);
		if(getboard.size() <= 1) {
			startUpdate();
		}
	}
	
	public static InfoBoard getInfoBoard(Player p) {
		if(getboard.containsKey(p)) {
			return getboard.get(p);
		} else {
			return new InfoBoard(p);
		}
	}
	
	public void removeBoard() {
		getboard.remove(p);
		if(getboard.size() == 0) {
			stopUpdate();
		}
	}
	
	private void updateBoard() {
		
		PacketPlayOutScoreboardScore rankremove = new PacketPlayOutScoreboardScore(rank);
		setRemove(rankremove);
		
		PacketPlayOutScoreboardScore playersremove = new PacketPlayOutScoreboardScore(players);
		setRemove(playersremove);
		
		PlayerConnection con = ((CraftPlayer)p).getHandle().playerConnection;
		
		con.sendPacket(rankremove);
		con.sendPacket(playersremove);
		
		rank = board.getPlayerScoreForObjective("    §8» §e" + getRank(p), obj);
		rank.setScore(7);
		
		this.players = board.getPlayerScoreForObjective("    §8» §a" + online, obj);
		this.players.setScore(4);
		
		PacketPlayOutScoreboardScore mappacket = new PacketPlayOutScoreboardScore(rank);
		PacketPlayOutScoreboardScore playerspacket = new PacketPlayOutScoreboardScore(players);
		
		con.sendPacket(mappacket);
		con.sendPacket(playerspacket);
		
	}
	
	public void setBoard() {
		
		board = new Scoreboard();
		
		board.registerObjective("aaa", IScoreboardCriteria.b);
		obj = board.getObjective("aaa");
		obj.setDisplayName("§8» §6§lKnow§6§lNix.de §8«");
		
		ArrayList<ScoreboardScore> scores = new ArrayList<>();
		
		ScoreboardScore s9 = board.getPlayerScoreForObjective("   ", obj);
		s9.setScore(9);
		scores.add(s9);
		ScoreboardScore s8 = board.getPlayerScoreForObjective("  §7Rang:", obj);
		s8.setScore(8);
		scores.add(s8);
		ScoreboardScore s7 = board.getPlayerScoreForObjective("    §8» §e" + getRank(p), obj);
		s7.setScore(7);
		this.rank = s7;
		scores.add(s7);
		ScoreboardScore s6 = board.getPlayerScoreForObjective("    ", obj);
		s6.setScore(6);
		scores.add(s6);
		ScoreboardScore s5 = board.getPlayerScoreForObjective("  §7Spieler:", obj);
		s5.setScore(5);
		scores.add(s5);
		ScoreboardScore s4 = board.getPlayerScoreForObjective("    §8» §a" + online, obj);
		s4.setScore(4);
		this.players = s4;
		scores.add(s4);
		ScoreboardScore s3 = board.getPlayerScoreForObjective("     ", obj);
		s3.setScore(3);
		scores.add(s3);
		ScoreboardScore s2 = board.getPlayerScoreForObjective("  §7Teamspeak:", obj);
		s2.setScore(2);
		scores.add(s2);
		ScoreboardScore s1 = board.getPlayerScoreForObjective("    §8» §3Know§3Nix.de", obj);
		s1.setScore(1);
		scores.add(s1);
		
		PacketPlayOutScoreboardObjective objpacket = new PacketPlayOutScoreboardObjective(obj, 0);
		PacketPlayOutScoreboardDisplayObjective disppacket = new PacketPlayOutScoreboardDisplayObjective(1, obj);
		
		PlayerConnection con = ((CraftPlayer)p).getHandle().playerConnection;
		con.sendPacket(objpacket);
		
		for(ScoreboardScore s : scores) {
			PacketPlayOutScoreboardScore score = new PacketPlayOutScoreboardScore(s);
			con.sendPacket(score);
		}
		
		con.sendPacket(disppacket);
		
	}

	private static void setRemove(Object obj) {
		
		try {
			Field f = obj.getClass().getDeclaredField("d");
			f.setAccessible(true);
			f.set(obj, PacketPlayOutScoreboardScore.EnumScoreboardAction.REMOVE);
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
		
	}
	
	private String getRank(Player p) {
		
		if(p.hasPermission("admin")) {
			return "§4Admin";
		} else if(p.hasPermission("srdev")) {
			return "§bSrDev";
		} else if(p.hasPermission("dev")) {
			return "§bDev";
		} else if(p.hasPermission("srmod")) {
			return "§cSrMod";
		} else if(p.hasPermission("mod")) {
			return "§cMod";
		} else if(p.hasPermission("sup")) {
			return "§9Sup";
		} else if(p.hasPermission("content")) {
			return "§bContent";
		} else if(p.hasPermission("builder")) {
			return "§eBuilder";
		} else if(p.hasPermission("vip")) {
			return "§5VIP";
		} else if(p.hasPermission("premiumplus")) {
			return "§6PremiumPlus";
		} else if(p.hasPermission("premium")) {
			return "§6Premium";
		} else {
			return "§aSpieler";
		}
		
	}
	
	private static void stopUpdate() {
		Bukkit.getScheduler().cancelTask(run);
	}
	
	private static void startUpdate() {
		
		run = Bukkit.getScheduler().scheduleSyncRepeatingTask(main.instance, new Runnable() {
			
			@Override
			public void run() {
				
				for(Player players : Bukkit.getOnlinePlayers()) {
					InfoBoard.getInfoBoard(players).updateBoard();
				}
				
				updateOnline();
				
			}
		}, 20 * 5, 20 * 5);
		
	}
	
	private static void updateOnline() {
		
		ByteArrayDataOutput out = ByteStreams.newDataOutput();
		out.writeUTF("PlayerCount");
		out.writeUTF("ALL");
		
		Player p = Iterables.getFirst(Bukkit.getOnlinePlayers(), null);
		
		p.sendPluginMessage(main.instance, "BungeeCord", out.toByteArray());
		
	}
	
}
