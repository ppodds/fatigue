package ppodds.rpg.fatigue.event;



import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import ppodds.rpg.fatigue.fatigue;
import ppodds.rpg.fatigue.value;

public class PlayerJoin implements Listener
{
	private final fatigue fg;
	
	public PlayerJoin(fatigue fg)
	{
		this.fg = fg;
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event)
	{
		Player player = event.getPlayer();
		File dir = new File(fg.getDataFolder() + File.separator + "Players");
		File PlayerData = new File(fg.getDataFolder() + File.separator + "Players" + File.separator + player.getUniqueId().toString() + ".yml");
		if (!dir.exists())
		{
			value.setFatigue(player, 10000);
			try
			{
				dir.mkdir();
				PlayerData.createNewFile();
				YamlConfiguration data = YamlConfiguration.loadConfiguration(PlayerData);
				data.set("fatigue", 10000);
				data.set("canDrink", true);
				data.set("canSleep", true);
				data.save(PlayerData);
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		else
		{
			if (!PlayerData.exists())
			{
				value.setFatigue(player, 10000);
				try
				{
					PlayerData.createNewFile();
					YamlConfiguration data = YamlConfiguration.loadConfiguration(PlayerData);
					data.set("fatigue", 10000);
					data.set("canDrink", true);
					data.set("canSleep", true);
					data.save(PlayerData);
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
			else
			{
				value.setFatigue(player, YamlConfiguration.loadConfiguration(PlayerData).getInt("fatigue"));
			}
		}
	}
}
