package me.Eagler.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByBlockEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public class EntityDamage_Listener implements Listener{
	
	@EventHandler
	public void onEntityDamage(EntityDamageEvent e){
		e.setCancelled(true);
	}
	
	@EventHandler
	public void onEntityDamageByEntity(EntityDamageByEntityEvent e){
		e.setCancelled(true);
	}
	
	@EventHandler
	public void onEntityDamageByBlock(EntityDamageByBlockEvent e){
		e.setCancelled(true);
	}
	
}
