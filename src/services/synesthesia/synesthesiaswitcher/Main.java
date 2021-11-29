package services.synesthesia.synesthesiaswitcher;

import java.util.Date;

import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import services.synesthesia.synesthesiaswitcher.commands.SwitcherCommand;
import services.synesthesia.synesthesiaswitcher.events.SwitcherHitEvent;
import services.synesthesia.synesthesiaswitcher.managers.CooldownManager;

public class Main extends JavaPlugin {

	private boolean SaberFactions, SavageFactions, SupremeFactions, WorldGuard, CombatLogX;
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
			if (plugin.getDescription().getAuthors().contains("SvenjaRei�aus")) {
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
		
		if (this.getServer().getPluginManager().getPlugin("CombatLogX") != null) {
			this.getLogger().info(" - Successfully Hooked into CombatLogX");
			this.CombatLogX = true;
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
		this.getLogger().info("SynesthesiaSwitcher v1.1 has successfully loaded!");
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
	
	public boolean getCombatLogX() {
		return this.CombatLogX;
	}

}
