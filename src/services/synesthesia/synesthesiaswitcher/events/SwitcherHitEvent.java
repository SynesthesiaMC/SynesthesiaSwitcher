package services.synesthesia.synesthesiaswitcher.events;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import com.github.sirblobman.combatlogx.api.ICombatLogX;
import com.github.sirblobman.combatlogx.api.manager.ICombatManager;

import services.synesthesia.synesthesiaswitcher.Main;
import services.synesthesia.synesthesiaswitcher.managers.CooldownManager;

public class SwitcherHitEvent implements Listener {

	private Main plugin;
	private CooldownManager manager;

	public SwitcherHitEvent(Main plugin) {
		this.plugin = plugin;
		this.manager = this.plugin.getCooldownManager();
	}

	@EventHandler(priority = EventPriority.LOWEST)
	public void onSnowballHit(EntityDamageByEntityEvent event) {

		Entity ent = event.getEntity();
		Entity damager = event.getDamager();

		if (!(ent instanceof Player)) {
			return;
		}

		final Player hit = (Player) ent;
		if (!(damager instanceof Snowball)) {
			return;
		}

		Snowball snowball = (Snowball) damager;
		if (!(snowball.getShooter() instanceof Player)) {
			return;
		}

		Player shooter = (Player) snowball.getShooter();

		if (this.plugin.getCombatLogX() && (this.isInCombat(shooter) || this.isInCombat(hit))) {
			return;
		}

		if (!this.manager.isOnCooldown(shooter.getUniqueId())) {
			this.manager.setOnCooldown(shooter.getUniqueId(), System.currentTimeMillis());
			Location ploc = shooter.getLocation();
			Location hitloc = hit.getLocation();
			shooter.teleport(hitloc);
			hit.teleport(ploc);
		}

	}

	private boolean isInCombat(Player player) {
		// You need to ensure that CombatLogX is enabled before using it for anything.
		ICombatLogX plugin = (ICombatLogX) Bukkit.getPluginManager().getPlugin("CombatLogX");
		ICombatManager combatManager = plugin.getCombatManager();
		return combatManager.isInCombat(player);
	}
}
