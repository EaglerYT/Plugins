package me.Eagler.listener;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.Eagler.utils.ItemBuilder;

public class PlayerInteract_Listener implements Listener{
	
	public static ArrayList<Player> silentLobby = new ArrayList<Player>();
	
	public Inventory compass = Bukkit.createInventory(null, 45, "§cTeleporter");
	public Inventory player_hider = Bukkit.createInventory(null, 9, "§cHider");
	
	static ItemStack silentlobby_off = ItemBuilder.createItem(Material.TNT, 1, "§cSilentLobby §7| Off");
	static ItemStack silentlobby_on = ItemBuilder.createItem(Material.TNT, 1, "§cSilentLobby §7| On");
	
	@EventHandler
	public void onInteract(PlayerInteractEvent e){
		Player p = e.getPlayer();
		
		if(e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
			if(e.getItem() != null){
				if(e.getItem().hasItemMeta()){
					if(e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cTeleporter")){
						setCompass();
						p.openInventory(compass);
					}
					if(e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cHider")){
						setHider();
						p.openInventory(player_hider);
					}
					if(e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cSilentLobby §7| Off")){
						for(Player all : Bukkit.getOnlinePlayers()){
							all.hidePlayer(p);
							p.hidePlayer(all);
						}
						silentLobby.add(p);
						p.playSound(p.getLocation(), Sound.LEVEL_UP, 66.0F, 66.0F);
						p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 20 * 3, 0, false, false));
						p.getInventory().setItem(4, silentlobby_on);
					}
					if(e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cSilentLobby §7| On")){
						for(Player all : Bukkit.getOnlinePlayers()){
							all.showPlayer(p);
							p.showPlayer(all);
						}
						silentLobby.remove(p);
						p.playSound(p.getLocation(), Sound.LEVEL_UP, 66.0F, 66.0F);
						p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 20 * 3, 0, false, false));
						p.getInventory().setItem(4, silentlobby_off);
					}
				}
			}
		}
	}
	
	public void setCompass(){
		ItemStack air = ItemBuilder.createid(Material.STAINED_GLASS_PANE, 1, (short) 15, "§0");
		ItemStack bedwars = ItemBuilder.createItem(Material.BED, 1, "§cBedwars");
		ItemStack skywars = ItemBuilder.createItem(Material.GRASS, 1, "§cSkywars");
		ItemStack survivalgames = ItemBuilder.createItem(Material.IRON_SWORD, 1, "§cSurvivalGames");
		ItemStack spawn = ItemBuilder.createItem(Material.MAGMA_CREAM, 1, "§cSpawn");
		
		for(int i = 0; i < 45; i++){
			if(i != 13 && i != 19 && i != 25 && i != 31){
				compass.setItem(i, air);
			}
		}
		
		compass.setItem(13, survivalgames);
		compass.setItem(19, bedwars);
		compass.setItem(25, skywars);
		compass.setItem(31, spawn);
	}
	
	public void setHider(){
		ItemStack air = ItemBuilder.createid(Material.STAINED_GLASS_PANE, 1, (short) 15, "§0");
		ItemStack player_on = ItemBuilder.createid(Material.STAINED_CLAY, 1, (short) 13, "§aAlle Spieler anzeigen");
		ItemStack player_off = ItemBuilder.createid(Material.STAINED_CLAY, 1, (short) 14, "§cAlle Spieler verstecken");
		
		for(int i = 0; i < 8; i++){
			if(i != 0 && i != 8){
				player_hider.setItem(i, air);
			}
		}
		
		player_hider.setItem(0, player_on);
		player_hider.setItem(8, player_off);
	}
	
}
