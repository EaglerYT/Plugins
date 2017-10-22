package me.Eagler.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockIgniteEvent;
import org.bukkit.event.Listener;

public class BlockIgnite_Listener implements Listener{
	
	@EventHandler
	public void onIgnite(BlockIgniteEvent e){
		e.setCancelled(true);
	}
	
}
