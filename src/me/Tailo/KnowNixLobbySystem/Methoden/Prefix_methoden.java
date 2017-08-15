package me.Tailo.KnowNixLobbySystem.Methoden;

import org.bukkit.Bukkit;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class Prefix_methoden {
	
	public static Scoreboard board = Bukkit.getScoreboardManager().getMainScoreboard();
	
	public static void prefix(String playername, Prefix prefix) {
		
		Team t = null;
	
		if (prefix == Prefix.Admin) {
			t = board.getTeam("aaa");
			if(t == null) {
				t = board.registerNewTeam("aaa");
				t.setPrefix("§4Admin §8× §4");
			}
		} else if(prefix == Prefix.SrDev) {
			t = board.getTeam("bbb");
			if(t == null) {
				t = board.registerNewTeam("bbb");
				t.setPrefix("§bSrDev §8× §b");
			}
		} else if(prefix == Prefix.Dev) {
			t = board.getTeam("ccc");
			if(t == null) {
				t = board.registerNewTeam("ccc");
				t.setPrefix("§bDev §8× §b");
			}
		} else if(prefix == Prefix.Content) {
			t = board.getTeam("ddd");
			if(t == null) {
				t = board.registerNewTeam("ddd");
				t.setPrefix("§bContent §8× §b");
			}
		} else if(prefix == Prefix.SrMod) {
			t = board.getTeam("eee");
			if(t == null) {
				t = board.registerNewTeam("eee");
				t.setPrefix("§cSrMod §8× §c");
			}
		} else if(prefix == Prefix.Mod) {
			t = board.getTeam("fff");
			if(t == null) {
				t = board.registerNewTeam("fff");
				t.setPrefix("§cMod §8× §c");
			}
		} else if(prefix == Prefix.Sup) {
			t = board.getTeam("ggg");
			if(t == null) {
				t = board.registerNewTeam("ggg");
				t.setPrefix("§9Sup §8× §9");
			}
		} else if(prefix == Prefix.Builder) {
			t = board.getTeam("hhh");
			if(t == null) {
				t = board.registerNewTeam("hhh");
				t.setPrefix("§eBuilder §8× §e");
			}
		} else if(prefix == Prefix.VIP) {
			t = board.getTeam("iii");
			if(t == null) {
				t = board.registerNewTeam("iii");
				t.setPrefix("§5VIP §8× §5");
			}
		} else if(prefix == Prefix.PremiumPlus) {
			t = board.getTeam("jjj");
			if(t == null) {
				t = board.registerNewTeam("jjj");
				t.setPrefix("§6Premium §8× §6");
			}
		} else if(prefix == Prefix.Premium) {
			t = board.getTeam("kkk");
			if(t == null) {
				t = board.registerNewTeam("kkk");
				t.setPrefix("§6Premium §8× §6");
			}
		} else if(prefix == Prefix.Spieler) {
			t = board.getTeam("lll");
			if(t == null) {
				t = board.registerNewTeam("lll");
				t.setPrefix("§aSpieler §8× §a");
			}
		}
		
		t.addEntry(playername);
	
	}
	
	public enum Prefix {
		
		Admin, SrDev, Dev, SrMod, Mod, Sup, Content, Builder, VIP, PremiumPlus, Premium, Spieler
		
	}
	
}
