package ppodds.rpg.fatigue;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import ppodds.rpg.fatigue.event.PlayerBedLeave;
import ppodds.rpg.fatigue.event.BlockBreak;
import ppodds.rpg.fatigue.event.PlayerItemConsume;
import ppodds.rpg.fatigue.event.PlayerJoin;
import ppodds.rpg.fatigue.event.PlayerKick;
import ppodds.rpg.fatigue.event.PlayerQuit;

public class fatigue extends JavaPlugin
{
	
	@Override
	public void onEnable()
	{
		value.loadPlayerFatigue(this);
		saveDefaultConfig();
		getServer().getPluginManager().registerEvents(new PlayerJoin(this), this);
		getServer().getPluginManager().registerEvents(new PlayerQuit(this), this);
		getServer().getPluginManager().registerEvents(new PlayerKick(this), this);
		getServer().getPluginManager().registerEvents(new BlockBreak(this), this);
		getServer().getPluginManager().registerEvents(new PlayerBedLeave(this), this);
		getServer().getPluginManager().registerEvents(new PlayerItemConsume(this), this);
	}
	
	@Override
	public void onDisable()
	{
		value.savePlayerFatigue(this);
	}
	
	public boolean onCommand(CommandSender sender , Command cmd , String lable , String[] args)
	{
		if (lable.equalsIgnoreCase("fg") && sender instanceof Player && args.length <= 2)
		{
			Player player = (Player) sender;
			if (args.length == 0 && player.hasPermission("fatigue.use.fg"))
			{
				player.sendMessage("你還剩下" + (double)value.getFatigue(player)/100 + "%的疲勞值");
			}
			else if(args.length == 2 && args[0].equalsIgnoreCase("give") && player.hasPermission("fatigue.use.give"))
			{
				try
				{
					int amount = Integer.parseInt(args[1]);
					ItemStack potion = new ItemStack(Material.POTION,amount);
					ItemMeta im = potion.getItemMeta();
					im.setDisplayName("FG potion");
					potion.setItemMeta(im);
					player.getInventory().addItem(potion);
				}
				catch (Exception e)
				{
					player.sendMessage(ChatColor.RED + "物品數量必須要是整數!而且最好不要大於64!");
				}
			}
			else
			{
				player.sendMessage(ChatColor.RED + "用法錯誤!請詳查用法");
			}
		}
	
		return false;
	} 
}
