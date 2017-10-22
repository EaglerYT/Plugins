package me.Eagler.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;

public class EntityExplode_Listener implements Listener{
	
	@EventHandler
	public void onEntityExplode(EntityExplodeEvent e){
		e.setCancelled(true);
	}
	
}
