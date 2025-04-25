package de.knacrack.journey.commands;

import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Stonecutter implements Command {
    @Override
    public String commandName() {
        return "stonecutter";
    }

    @Override
    public List<String> aliases() {
        return List.of("cutter");
    }

    @Override
    public @NotNull String permission() {
        return "*";
    }

    @Override
    public @NotNull String description() {
        return "Opens a stonecutter.";
    }

    @Override
    public void command(CommandSender sender, String[] args) {
        if (!sender.hasPermission(permission())) return;
        if (!(sender instanceof Player player)) return;
        player.openStonecutter(null, false);
        player.playSound(player.getLocation(), Sound.UI_STONECUTTER_SELECT_RECIPE, 1, 1);
    }

    @Override
    public List<String> tab(CommandSender sender, String[] args) {
        return List.of();
    }
}
