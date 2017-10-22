package me.Eagler.utils;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

public class ItemBuilder {
	
	public static ItemStack createItem(Material mat, int anzahl, String name) {
		ItemStack item = new ItemStack(mat, anzahl);
		ItemMeta mitem = item.getItemMeta();
		mitem.setDisplayName(name);
		item.setItemMeta(mitem);
		return item;
	}
	
	public static ItemStack createid(Material mat, int anzahl, short id, String Name) {
		ItemStack item = new ItemStack(mat, anzahl, (byte)id);
		ItemMeta mitem = item.getItemMeta();
		mitem.setDisplayName(Name);
		item.setItemMeta(mitem);
		return item;
    }
	
	public static ItemStack createLeatherArmor(Material mat, int anzahl, Color color, String name) {
		ItemStack item = new ItemStack(mat, anzahl);
		LeatherArmorMeta mitem = (LeatherArmorMeta) item.getItemMeta();
		mitem.setColor(color);
		mitem.setDisplayName(name);
		item.setItemMeta(mitem);
		return item;
	}
	
}
