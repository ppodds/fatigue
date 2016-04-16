package ppodds.rpg.fatigue.event;

import java.io.File;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import ppodds.rpg.fatigue.fatigue;
import ppodds.rpg.fatigue.value;

public class PlayerItemConsume implements Listener
{
	private final fatigue fg;
	
	public PlayerItemConsume(fatigue fg)
	{
		this.fg = fg;
	}
	
	@EventHandler
	public void onPlayerItemConsume(PlayerItemConsumeEvent event)
	{
		Player player = event.getPlayer();
		final File PlayerData = new File(fg.getDataFolder() + File.separator + "Players" + File.separator + player.getUniqueId().toString() + ".yml");
		final YamlConfiguration y = YamlConfiguration.loadConfiguration(PlayerData);
		if (YamlConfiguration.loadConfiguration(PlayerData).getBoolean("canDrink"))
		{
			if (event.getItem().getItemMeta().getDisplayName().equals("FG potion"))
			{
				
				if (value.getFatigue(player) + 1500 > 10000)
				{
					value.setFatigue(player, 10000);
					for (PotionEffect pe : player.getActivePotionEffects())
					{
						player.removePotionEffect(pe.getType());
					}
					try
					{
						y.set("canDrink", false);
						y.save(PlayerData);
						fg.getServer().getScheduler().runTaskLater(fg, new Runnable()
						{
							@Override
							public void run()
							{
								try
								{
									y.set("canDrink", true);
									y.save(PlayerData);
								}
								catch (Exception e)
								{
									e.printStackTrace();
								}
							}
						}, 36000L);
					}
					catch (Exception e) 
					{
						e.printStackTrace();
					}
				}
				else
				{
					value.setFatigue(player, value.getFatigue(player) + 1500);
					if (value.getFatigue(player) > 3000)
					{
						for (PotionEffect pe : player.getActivePotionEffects())
						{
							player.removePotionEffect(pe.getType());
						}
						try
						{
							y.set("canDrink", false);
							y.save(PlayerData);
							fg.getServer().getScheduler().runTaskLater(fg, new Runnable()
							{
								@Override
								public void run()
								{
									try
									{
										y.set("canDrink", true);
										y.save(PlayerData);
									}
									catch (Exception e)
									{
										e.printStackTrace();
									}
								}
							}, 36000L);
						}
						catch (Exception e) 
						{
							e.printStackTrace();
						}
					}
					else if (value.getFatigue(player) < 3000)
					{
						for (PotionEffect pe : player.getActivePotionEffects())
						{
							player.removePotionEffect(pe.getType());
						}
						try
						{
							y.set("canDrink", false);
							y.save(PlayerData);
							fg.getServer().getScheduler().runTaskLater(fg, new Runnable()
							{
								@Override
								public void run()
								{
									try
									{
										y.set("canDrink", true);
										y.save(PlayerData);
									}
									catch (Exception e)
									{
										e.printStackTrace();
									}
								}
							}, 36000L);
						}
						catch (Exception e) 
						{
							e.printStackTrace();
						}
						player.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER ,Integer.MAX_VALUE ,1));
						player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING ,Integer.MAX_VALUE ,1));
						player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION ,Integer.MAX_VALUE ,1));
						player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW ,Integer.MAX_VALUE ,1));
					}
					else if (value.getFatigue(player) < 1000)
					{
						for (PotionEffect pe : player.getActivePotionEffects())
						{
							player.removePotionEffect(pe.getType());
						}
						try
						{
							y.set("canDrink", false);
							y.save(PlayerData);
							fg.getServer().getScheduler().runTaskLater(fg, new Runnable()
							{
								@Override
								public void run()
								{
									try
									{
										y.set("canDrink", true);
										y.save(PlayerData);
									}
									catch (Exception e)
									{
										e.printStackTrace();
									}
								}
							}, 36000L);
						}
						catch (Exception e) 
						{
							e.printStackTrace();
						}
						player.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER ,Integer.MAX_VALUE ,2));
						player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING ,Integer.MAX_VALUE ,2));
						player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION ,Integer.MAX_VALUE ,2));
						player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW ,Integer.MAX_VALUE ,2));
					}
				}
			}
		}
		else
		{
			player.sendMessage("還未達到可飲用的時間.");
		}
	}
}
