package me.Eagler.listener;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

import me.Eagler.Main;
import me.Eagler.utils.ItemBuilder;
import me.Eagler.utils.Tablist;

public class JoinQuit_Listener implements Listener{
	
	static ItemStack compass = ItemBuilder.createItem(Material.COMPASS, 1, "§cTeleporter");
	static ItemStack player_hider = ItemBuilder.createItem(Material.BLAZE_ROD, 1, "§cHider");
	static ItemStack silentlobby = ItemBuilder.createItem(Material.TNT, 1, "§cSilentLobby §7| Off");
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e){
		Player p = e.getPlayer();
		e.setJoinMessage(null);
		if(Main.instance.getConfig().contains("location.spawn")){
			p.teleport((Location) Main.instance.getConfig().get("location.spawn"));
		}else{
			p.sendMessage(Main.prefix + "§cDer Spawn wurde noch nicht gesetzt.");
		}
		
		for(Player all : Bukkit.getOnlinePlayers()){
			all.showPlayer(p);
			p.showPlayer(all);
			Tablist.setScoreboard(all);
		}
		
		Bukkit.getScheduler().runTaskLater(Main.instance, new Runnable() {
			
			@Override
			public void run() {
				Tablist.setSideScoreboard(p);
			}
		}, 20 * 2);
		
		p.setHealth(20D);
		p.setFoodLevel(20);
		
		p.setGameMode(GameMode.SURVIVAL);
		
		p.getInventory().setItem(0, compass);
		
		if(p.hasPermission("lobby.silentlobby")){
			p.getInventory().setItem(4, silentlobby);
		}
		
		p.getInventory().setItem(8, player_hider);
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent e){
		Player p = e.getPlayer();
		e.setQuitMessage(null);
		
		p.getInventory().clear();
		p.getInventory().setHelmet(null);
		p.getInventory().setChestplate(null);
		p.getInventory().setLeggings(null);
		p.getInventory().setBoots(null);
		
		if(PlaceBreak_Listener.build.contains(p)){
			PlaceBreak_Listener.build.remove(p);
		}
		
		if(PlayerInteract_Listener.silentLobby.contains(p)){
			PlayerInteract_Listener.silentLobby.remove(p);
		}
	}
	
}
