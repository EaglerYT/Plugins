package me.Eagler.listener;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PlayerChat_Listener implements Listener{
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent e){
		Player p = e.getPlayer();
		
		e.setCancelled(true);
		
		for(Player all : Bukkit.getOnlinePlayers()){
			if(!PlayerInteract_Listener.silentLobby.contains(p) && !PlayerInteract_Listener.silentLobby.contains(all)){
				if(p.isOp()){
					all.sendMessage("§4Admin | " + p.getName() + " §8>> §7" + e.getMessage());
				}else{
					all.sendMessage("§7Spieler | " + p.getName() + " §8>> §7" + e.getMessage());
				}
			}
		}
	}
	
}
