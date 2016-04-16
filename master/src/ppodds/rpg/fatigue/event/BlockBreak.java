package ppodds.rpg.fatigue.event;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.potion.*;

import ppodds.rpg.fatigue.fatigue;
import ppodds.rpg.fatigue.value;

public class BlockBreak implements Listener
{
	private final fatigue fg;
	
	public BlockBreak(fatigue fg)
	{
		this.fg = fg;
	}
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent event)
	{
		Player player = event.getPlayer();
		String BrokenBlock = event.getBlock().getType().toString();
		
		YamlConfiguration config = YamlConfiguration.loadConfiguration(new File(fg.getDataFolder() + File.separator + "config.yml"));
		if (BrokenBlock.equals("STONE"))
		{
		
			value.setFatigue(player, value.getFatigue(player) - config.getInt("Item.STONE"));
			
		}
		else
		if (BrokenBlock.equals("COBBLESTONE"))
		{
			value.setFatigue(player, value.getFatigue(player) - config.getInt("Item.COBBLESTONE"));
		}
		else
		if (BrokenBlock.equals("COAL_ORE"))
		{
			value.setFatigue(player, value.getFatigue(player) - config.getInt("Item.COAL_ORE"));
		}
		else
		if (BrokenBlock.equals("IRON_ORE"))
		{
			value.setFatigue(player, value.getFatigue(player) - config.getInt("Item.IRON_ORE"));
		}
		else
		if (BrokenBlock.equals("GOLD_ORE"))
		{
			value.setFatigue(player, value.getFatigue(player) - config.getInt("Item.GOLD_ORE"));
		}
		else
		if (BrokenBlock.equals("REDSTONE_ORE"))
		{
			value.setFatigue(player, value.getFatigue(player) - config.getInt("Item.REDSTONE_ORE"));
		}
		else
		if (BrokenBlock.equals("LAPIS_ORE"))
		{
			value.setFatigue(player, value.getFatigue(player) - config.getInt("Item.LAPIS_ORE"));
		}
		else
		if (BrokenBlock.equals("EMERALD_ORE"))
		{
			value.setFatigue(player, value.getFatigue(player) - config.getInt("Item.EMERALD_ORE"));
		}
		else
		if (BrokenBlock.equals("DIAMOND_ORE"))
		{
			value.setFatigue(player, value.getFatigue(player) - config.getInt("Item.DIAMOND_ORE"));
		}
		
		
		if (value.getFatigue(player) < 0 )
		{
			value.setFatigue(player, 0);
		}
		else if (value.getFatigue(player) < 3000 && value.getFatigue(player) >= 1000)
		{
			player.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER ,Integer.MAX_VALUE ,1));
			player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING ,Integer.MAX_VALUE ,1));
			player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION ,Integer.MAX_VALUE ,1));
			player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW ,Integer.MAX_VALUE ,1));
		}
		else if (value.getFatigue(player) < 1000)
		{
			player.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER ,Integer.MAX_VALUE ,2));
			player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING ,Integer.MAX_VALUE ,2));
			player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION ,Integer.MAX_VALUE ,2));
			player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW ,Integer.MAX_VALUE ,2));
		}
	}
}
