package services.synesthesia.synesthesia_switcher;

import java.util.Date;

import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import services.synesthesia.synesthesia_switcher.commands.SwitcherCommand;
import services.synesthesia.synesthesia_switcher.events.SwitcherHitEvent;
import services.synesthesia.synesthesia_switcher.managers.CooldownManager;

public class Main extends JavaPlugin {

	private boolean SaberFactions, SavageFactions, SupremeFactions, WorldGuard;
	private CooldownManager manager;
	
	@Override
	public void onEnable() {

		final Date date = new Date();
		final long startTime = date.getTime();

		this.getLogger().info("------------[SYNESTHESIASWITCHER]-------------");

		if (this.getServer().getPluginManager().getPlugin("Factions") != null) {
			final Plugin plugin = this.getServer().getPluginManager().getPlugin("Factions");
			if (plugin.getDescription().getAuthors().contains("DroppingAnvil")) {
				this.getLogger().info(" - Successfully Hooked into SaberFactions");
				this.SaberFactions = true;
			}
			if (plugin.getDescription().getAuthors().contains("SvenjaReiﬂaus")) {
				this.getLogger().info(" - Successfully Hooked into SavageFactions");
				this.SavageFactions = true;
			}
			if (plugin.getDescription().getAuthors().contains("SupremeDev")) {
				this.getLogger().info(" - Successfully Hooked into SupremeFactions");
				this.SupremeFactions = true;
			}
		}

		if (this.getServer().getPluginManager().getPlugin("WorldGuard") != null) {
			this.getLogger().info(" - Successfully Hooked into WorldGuard");
			this.WorldGuard = true;
		}
		
		this.saveDefaultConfig();
		this.manager = new CooldownManager(this);
		getServer().getPluginManager().registerEvents(new SwitcherHitEvent(this), this);
		SwitcherCommand switcher = new SwitcherCommand(this);
		this.getCommand("switcherball").setExecutor((CommandExecutor) switcher);
		
		this.getLogger().info("");
		this.getLogger().info("Loading Plugin:");
		this.getLogger().info("");
		this.getLogger().info(" - Loaded Switcher Hit Event");
		this.getLogger().info(" - Loaded Cooldown Manager");
		this.getLogger().info("");
		this.getLogger().info("SynesthesiaSwitcher v1.0 has successfully loaded!");
		final Date date2 = new Date();
		final long endTime = date2.getTime();
		this.getLogger().info("Plugin successfully loaded in " + (endTime - startTime) + "ms.");
		this.getLogger().info("------------[SYNESTHESIASWITCHER]-------------");
		
	}

	@Override
	public void onDisable() {

	}
	
	public CooldownManager getCooldownManager() {
		return this.manager;
	}
	
	public boolean getWorldGuard() {
		return this.WorldGuard;
	}
	
	public boolean getSaberFactions() {
		return this.SaberFactions;
	}
	
	public boolean getSavageFactions() {
		return this.SavageFactions;
	}
	
	public boolean getSupremeFactions() {
		return this.SupremeFactions;
	}

}
