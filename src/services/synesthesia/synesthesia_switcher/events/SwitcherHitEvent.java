package services.synesthesia.synesthesia_switcher.events;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import services.synesthesia.synesthesia_switcher.Main;
import services.synesthesia.synesthesia_switcher.managers.CooldownManager;

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
		if (!this.manager.isOnCooldown(shooter.getUniqueId())) {
			this.manager.setOnCooldown(shooter.getUniqueId(), System.currentTimeMillis());
			Location ploc = shooter.getLocation();
			Location hitloc = hit.getLocation();
			shooter.teleport(hitloc);
			hit.teleport(ploc);
		}

	}
}
