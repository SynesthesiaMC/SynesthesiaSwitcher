package services.synesthesia.synesthesia_switcher.managers;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.configuration.file.FileConfiguration;

import services.synesthesia.synesthesia_switcher.Main;

public class CooldownManager {

	private Main plugin;
	private FileConfiguration config;
	private HashMap<UUID, Long> cooldowns;

	public CooldownManager(Main plugin) {
		this.plugin = plugin;
		this.config = this.plugin.getConfig();
		this.cooldowns = new HashMap<UUID, Long>();
	}

	public boolean isOnCooldown(UUID player) {
		if (this.cooldowns.containsKey(player)) {
			double cooldown = this.config.getDouble("SwitcherBall.Cooldown");
			if (this.cooldowns.get(player) + cooldown > System.currentTimeMillis() / 1000) {
				return true;
			} else {
				this.removeFromCooldown(player);
				return false;
			}
		} else {
			return false;
		}
	}

	public void setOnCooldown(UUID player, Long time) {
		this.cooldowns.put(player, time / 1000);
	}

	public void removeFromCooldown(UUID player) {
		this.cooldowns.remove(player);
	}

}
