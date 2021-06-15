package services.synesthesia.synesthesia_switcher.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import services.synesthesia.synesthesia_switcher.Main;
import services.synesthesia.synesthesia_switcher.Utils;

public class SwitcherCommand implements CommandExecutor {

	private Main plugin;
	private FileConfiguration config;

	public SwitcherCommand(Main plugin) {
		this.plugin = plugin;
		this.config = this.plugin.getConfig();
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		if (label.equalsIgnoreCase("switcherball") && sender.hasPermission("switcher.give")) {
			final Player p = (Player) sender;

			if (!sender.hasPermission("switcher.give")) {
				sender.sendMessage(Utils.chat(this.config.getString("Messages.NoPermission")).replace("%prefix%",
						Utils.chat(this.config.getString("prefix"))));
				return true;
			}

			if (args.length == 0) {
				p.sendMessage(Utils.chat("&cIncorrect usage! /switcherball give <player> <amount>"));
			}
			if (args.length == 1) {
				p.sendMessage(Utils.chat("&cIncorrect usage! /switcherball give <player> <amount>"));
			}
			if (args.length == 2 && args[0].equalsIgnoreCase("give") && Bukkit.getServer().getPlayer(args[1]) != null) {

				Player target = Bukkit.getServer().getPlayer(args[1]);
				ItemStack item = new ItemStack(Material.SNOW_BALL, 1);
				ItemMeta meta = item.getItemMeta();
				List<String> lore = new ArrayList<String>();
				meta.setDisplayName(Utils.chat(this.config.getString("SwitcherBall.ItemName")));

				for (String lore_line : this.config.getStringList("SwitcherBall.ItemLore")) {
					lore.add(Utils.chat(lore_line));
				}

				item.setItemMeta(meta);
				target.getInventory().addItem(new ItemStack[] { item });
				return true;
			}
			if (args.length == 3 && args[0].equalsIgnoreCase("give") && Bukkit.getServer().getPlayer(args[1]) != null) {
				Player target = Bukkit.getServer().getPlayer(args[1]);
				ItemStack item = new ItemStack(Material.SNOW_BALL, Integer.parseInt(args[2]));
				ItemMeta meta = item.getItemMeta();
				List<String> lore = new ArrayList<String>();
				meta.setDisplayName(Utils.chat(this.config.getString("SwitcherBall.ItemName")));

				for (String lore_line : this.config.getStringList("SwitcherBall.ItemLore")) {
					lore.add(Utils.chat(lore_line));
				}

				item.setItemMeta(meta);
				target.getInventory().addItem(new ItemStack[] { item });
				return true;
			}
		}

		return true;

	}

}
