package ppodds.rpg.fatigue;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class value
{
	private static Map<Player , Integer> FATIGUE = new HashMap<Player , Integer>();
	private static fatigue FG;
	private value(fatigue FG)
	{
		this.FG = FG;
	}
	
	
	public static int getFatigue(Player player)
	{
		return FATIGUE.get(player);
	}
	
	public static void setFatigue(Player player , Integer amount)
	{
		FATIGUE.put(player, amount);
	}
	
	public static void loadPlayerFatigue(Plugin plugin)
	{
		for (Player player : plugin.getServer().getOnlinePlayers())
		{
				File PlayerData = new File(plugin.getDataFolder() + File.separator + "Players" + File.separator + player.getUniqueId().toString() + ".yml");
				YamlConfiguration y = YamlConfiguration.loadConfiguration(PlayerData);
				value.setFatigue(player, y.getInt("fatigue"));
				System.out.println(player + "的玩家資料讀取成功!");
		}
	}
	
	public static void savePlayerFatigue(Plugin plugin)
	{
		for (Player player : plugin.getServer().getOnlinePlayers())
		{
			try
			{
				File PlayerData = new File(plugin.getDataFolder() + File.separator + "Players" + File.separator + player.getUniqueId().toString() + ".yml");
				YamlConfiguration y = YamlConfiguration.loadConfiguration(PlayerData);
				y.set("fatigue", value.getFatigue(player));
				y.save(PlayerData);
				System.out.println(player + "的玩家資料儲存成功!");
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}
}
