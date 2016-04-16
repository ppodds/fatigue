package ppodds.rpg.fatigue.event;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import ppodds.rpg.fatigue.fatigue;
import ppodds.rpg.fatigue.value;

public class PlayerQuit implements Listener
{
	private final fatigue fg;
	
	public PlayerQuit(fatigue fg)
	{
		this.fg = fg;
	}
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event)
	{
		Player player = event.getPlayer();
		try
		{
			File PlayerData = new File(fg.getDataFolder() + File.separator + "Players" + File.separator + player.getUniqueId().toString() + ".yml");
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
