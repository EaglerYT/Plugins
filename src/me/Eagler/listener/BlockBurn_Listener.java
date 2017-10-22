package me.Eagler.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBurnEvent;

public class BlockBurn_Listener implements Listener{
	
	@EventHandler
	public void onBurn(BlockBurnEvent e){
		e.setCancelled(true);
	}
	
}
