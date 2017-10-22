package me.Eagler.utils;

import java.lang.reflect.Field;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_8_R3.PacketPlayOutPlayerListHeaderFooter;

public class Tablist {
	
	public static Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
	
	public static void setScoreboard(Player p) {
     //   scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();

        Team leitungPrefix = scoreboard.registerNewTeam("a");
        Team spielerPrefix = scoreboard.registerNewTeam("b");

        leitungPrefix.setPrefix("§4Admin | §4");
        leitungPrefix.setDisplayName("§4Admin | §4");
        spielerPrefix.setPrefix("§7");
        spielerPrefix.setDisplayName("§7");

        for (Player all : Bukkit.getOnlinePlayers()) {
            Team playerTeam = getTeamForPlayer(scoreboard, all);
            if (playerTeam.hasEntry(all.getName())) playerTeam.removeEntry(all.getName());
            playerTeam.addEntry(all.getName());
        }
        sendTablistHeaderAndFooter(p, "▄▆█Server-IP.de█▆▄", "TeamSpeak: ts.Server-IP.de");
        p.setScoreboard(scoreboard);
    }
	
	@SuppressWarnings("deprecation")
	public static void setSideScoreboard(Player p){
		//	scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
			Objective obj = scoreboard.registerNewObjective("aaa", "bbb");
			obj.setDisplayName("§cServer-IP.de");
			obj.setDisplaySlot(DisplaySlot.SIDEBAR);
			
			obj.getScore(Bukkit.getOfflinePlayer("§1")).setScore(16);
			
			obj.getScore(Bukkit.getOfflinePlayer("§7Rang")).setScore(15);
			if(p.isOp()){
				obj.getScore(Bukkit.getOfflinePlayer("§4Admin")).setScore(14);
			}else{
				obj.getScore(Bukkit.getOfflinePlayer("§7Spieler")).setScore(14);
			}
			
			obj.getScore(Bukkit.getOfflinePlayer("§c")).setScore(13);
			
			obj.getScore(Bukkit.getOfflinePlayer("§7TeamSpeak")).setScore(12);
			obj.getScore(Bukkit.getOfflinePlayer("§cts.Server-IP.de")).setScore(11);
			
			obj.getScore(Bukkit.getOfflinePlayer("§7")).setScore(10);
			
			p.setScoreboard(scoreboard);
	}
	
	public static Team getTeamForPlayer(Scoreboard scoreboard, Player player) {
		if (player.isOp()) {
            return scoreboard.getTeam("a");
        }
        return scoreboard.getTeam("b");
    }
	
	public static void sendTablistHeaderAndFooter(Player p, String header, String footer){
		if(header == null) header = "";
		if(footer == null) footer = "";
		
		IChatBaseComponent tabHeader = ChatSerializer.a("{\"text\":\"" + header + "\"}");
		IChatBaseComponent tabFooter = ChatSerializer.a("{\"text\":\"" + footer + "\"}");
		
		PacketPlayOutPlayerListHeaderFooter headerPacket = new PacketPlayOutPlayerListHeaderFooter(tabHeader);
		try {
			Field field = headerPacket.getClass().getDeclaredField("b");
			field.setAccessible(true);
			field.set(headerPacket, tabFooter);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			((CraftPlayer)p).getHandle().playerConnection.sendPacket(headerPacket);
		}
	}
	
}

