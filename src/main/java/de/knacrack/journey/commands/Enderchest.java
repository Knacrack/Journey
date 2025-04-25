package de.knacrack.journey.commands;

import de.knacrack.journey.utility.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;

public class Enderchest implements Command {

    public Enderchest() {
        registerCommand(this);
    }

    @Override
    public String commandName() {
        return "enderchest";
    }

    @Override
    public List<String> aliases() {
        return List.of("ec");
    }

    @Override
    public @NotNull String permission() {
        return "*";
    }

    @Override
    public @NotNull String description() {
        return "Opens your enderchest or another player's enderchest.";
    }

    @Override
    public void command(CommandSender sender, String[] args) {
        if (!sender.hasPermission(permission())) return;
        if (!(sender instanceof Player player)) return;

        if (args.length == 0) {
            player.openInventory(player.getEnderChest());
            player.playSound(player.getLocation(), Sound.BLOCK_ENDER_CHEST_OPEN, 1f, 1f);
        }

        if (args.length == 1) {
            Player target;
            if ((target = Bukkit.getPlayer(args[0])) == null) {
                sender.sendMessage("Dieser Spieler ist nicht Online.");
            } else {
                player.openInventory(target.getEnderChest());
                player.playSound(player.getLocation(), Sound.BLOCK_ENDER_CHEST_OPEN, 1f, 1f);
            }
        }

    }

    @Override
    public List<String> tab(CommandSender sender, String[] args) {
        List<String> list = Collections.emptyList();
        if (args.length == 1 && sender.hasPermission(permission())) {
            list = Utils.getOnlinePlayers(args[0]);
        }
        return list;
    }
}
