package me.Eagler.listener;

import java.util.ArrayList;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class PlaceBreak_Listener implements Listener{
	
	public static ArrayList<Player> build = new ArrayList<Player>();
	
	@EventHandler
	public void onBreak(BlockBreakEvent e){
		if(!build.contains(e.getPlayer())){
			e.setCancelled(true);
		}else{
			e.setCancelled(false);
		}
	}
	
	@EventHandler
	public void onPlace(BlockPlaceEvent e){
		if(!build.contains(e.getPlayer())){
			e.setCancelled(true);
		}else{
			e.setCancelled(false);
		}
	}
	
}
