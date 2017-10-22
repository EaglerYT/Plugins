package me.Eagler.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class FoodLevelChange_Listener implements Listener{
	
	@EventHandler
	public void onFoodLevelChange(FoodLevelChangeEvent e){
		e.setCancelled(true);
	}
	
}
