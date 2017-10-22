package me.Eagler;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import me.Eagler.listener.*;
import me.Eagler.utils.ItemBuilder;

public class Main extends JavaPlugin{
	
	public static String prefix = "§8[§cLobby§8] §7";
	
	public static Main instance;
	
	static ItemStack compass = ItemBuilder.createItem(Material.COMPASS, 1, "§cTeleporter");
	static ItemStack player_hider = ItemBuilder.createItem(Material.BLAZE_ROD, 1, "§cHider");
	static ItemStack silentlobby = ItemBuilder.createItem(Material.TNT, 1, "§cSilentLobby §7| Off");
	
	public void onEnable(){
		System.out.println("Lobby | Active");
		instance = this;
		this.registerEvents();
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player){
			Player p = (Player) sender;
			
			if(cmd.getName().equalsIgnoreCase("setloc")){
				if(p.isOp()){
					if(args.length == 1){
						if(args[0].equalsIgnoreCase("bedwars")){
							getConfig().set("location.bedwars", p.getLocation());
							saveConfig();
							p.sendMessage(prefix + "Du hast die Location für §cBedwars §7gesetzt.");
						}else if(args[0].equalsIgnoreCase("skywars")){
							getConfig().set("location.skywars", p.getLocation());
							saveConfig();
							p.sendMessage(prefix + "Du hast die Location für §cSkywars §7gesetzt.");
						}else if(args[0].equalsIgnoreCase("survivalgames")){
							getConfig().set("location.survivalgames", p.getLocation());
							saveConfig();
							p.sendMessage(prefix + "Du hast die Location für §cSurvivalGames §7gesetzt.");
						}else if(args[0].equalsIgnoreCase("spawn")){
							getConfig().set("location.spawn", p.getLocation());
							saveConfig();
							p.sendMessage(prefix + "Du hast die Location für den §cSpawn §7gesetzt.");
						}else{
							p.sendMessage(prefix + "§cNutze /setloc [Bedwars, Skywars, SurvivalGames, Spawn]");
						}
					}else{
						p.sendMessage(prefix + "§cNutze /setloc [Bedwars, Skywars, SurvivalGames, Spawn]");
					}
				}else{
					p.sendMessage(prefix + "§cDafür hast du keine Rechte!");
				}
			}
			
			if(cmd.getName().equalsIgnoreCase("build")){
				if(p.hasPermission("lobby.build")){
					if(PlaceBreak_Listener.build.contains(p)){
						PlaceBreak_Listener.build.remove(p);
						p.sendMessage(prefix + "Du kannst nun nicht mehr §cbauen§7.");
						p.setGameMode(GameMode.SURVIVAL);
						p.getInventory().clear();
						p.getInventory().setHelmet(null);
						p.getInventory().setChestplate(null);
						p.getInventory().setLeggings(null);
						p.getInventory().setBoots(null);
						
						p.getInventory().setItem(0, compass);
						
						if(p.hasPermission("lobby.silentlobby")){
							p.getInventory().setItem(4, silentlobby);
						}
						
						p.getInventory().setItem(8, player_hider);
					}else{
						PlaceBreak_Listener.build.add(p);
						p.sendMessage(prefix + "Du kannst nun §abauen§7.");
						p.setGameMode(GameMode.CREATIVE);
						p.getInventory().clear();
					}
				}else{
					p.sendMessage(prefix + "§cDafür hast du keine Rechte!");
				}
			}
			
		}
		return true;
	}
	
	public void registerEvents(){
		Bukkit.getPluginManager().registerEvents(new JoinQuit_Listener(), this);
		Bukkit.getPluginManager().registerEvents(new FoodLevelChange_Listener(), this);
		Bukkit.getPluginManager().registerEvents(new EntityDamage_Listener(), this);
		Bukkit.getPluginManager().registerEvents(new BlockIgnite_Listener(), this);
		Bukkit.getPluginManager().registerEvents(new PlaceBreak_Listener(), this);
		Bukkit.getPluginManager().registerEvents(new BlockBurn_Listener(), this);
		Bukkit.getPluginManager().registerEvents(new PlayerInteract_Listener(), this);
		Bukkit.getPluginManager().registerEvents(new PlayerChat_Listener(), this);
		Bukkit.getPluginManager().registerEvents(new InventoryClick_Listener(), this);
		Bukkit.getPluginManager().registerEvents(new EntityExplode_Listener(), this);
	}
	
}
