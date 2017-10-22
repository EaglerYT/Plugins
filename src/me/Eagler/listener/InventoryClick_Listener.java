package me.Eagler.listener;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;

import me.Eagler.Main;

import org.bukkit.event.Listener;

public class InventoryClick_Listener implements Listener{
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent e){
		if(e.getWhoClicked() instanceof Player){
			Player p = (Player) e.getWhoClicked();
			
			if(e.getInventory().getName().equalsIgnoreCase("§cTeleporter")){
				if(e.getCurrentItem().getType() == Material.MAGMA_CREAM){
					if(Main.instance.getConfig().contains("location.spawn")){
						p.teleport((Location) Main.instance.getConfig().get("location.spawn"));
						p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 3.0F, 3.0F);
					}else{
						p.sendMessage(Main.prefix + "§cDer Spawn wurde noch nicht gesetzt!");
					}
					p.closeInventory();
				}else if(e.getCurrentItem().getType() == Material.BED){
					if(Main.instance.getConfig().contains("location.bedwars")){
						p.teleport((Location) Main.instance.getConfig().get("location.bedwars"));
						p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 3.0F, 3.0F);
					}else{
						p.sendMessage(Main.prefix + "§cDie Location für Bedwars wurde noch nicht gesetzt!");
					}
					p.closeInventory();
				}else if(e.getCurrentItem().getType() == Material.GRASS){
					if(Main.instance.getConfig().contains("location.skywars")){
						p.teleport((Location) Main.instance.getConfig().get("location.skywars"));
						p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 3.0F, 3.0F);
					}else{
						p.sendMessage(Main.prefix + "§cDie Location für Skywars wurde noch nicht gesetzt!");
					}
					p.closeInventory();
				}else if(e.getCurrentItem().getType() == Material.IRON_SWORD){
					if(Main.instance.getConfig().contains("location.survivalgames")){
						p.teleport((Location) Main.instance.getConfig().get("location.survivalgames"));
						p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 3.0F, 3.0F);
					}else{
						p.sendMessage(Main.prefix + "§cDie Location für SurvivalGames wurde noch nicht gesetzt!");
					}
					p.closeInventory();
				}
				e.setCancelled(true);
			}else if(e.getInventory().getName().equalsIgnoreCase("§cHider")){
				if(e.getCurrentItem().hasItemMeta()){
					if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aAlle Spieler anzeigen")){
						for(Player all : Bukkit.getOnlinePlayers()){
							p.showPlayer(all);
						}
						p.playSound(p.getLocation(), Sound.LEVEL_UP, 30.0F, 30.0F);
						p.sendMessage(Main.prefix + "Alle Spieler sind nun §asichtbar§7.");
						p.closeInventory();
					}
					if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cAlle Spieler verstecken")){
						for(Player all : Bukkit.getOnlinePlayers()){
							p.hidePlayer(all);
						}
						p.playSound(p.getLocation(), Sound.LEVEL_UP, 30.0F, 30.0F);
						p.sendMessage(Main.prefix + "Alle Spieler sind nun §cunsichtbar§7.");
						p.closeInventory();
					}
				}
				e.setCancelled(true);
			}else{
				if(PlaceBreak_Listener.build.contains(p)){
					e.setCancelled(false);
				}else{
					e.setCancelled(true);
				}
			}
		}
	}
	
}
