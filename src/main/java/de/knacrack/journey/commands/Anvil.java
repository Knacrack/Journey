package de.knacrack.journey.commands;

import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Anvil implements Command {

    public Anvil() {
        registerCommand(this);
    }

    @Override
    public String commandName() {
        return "anvil";
    }

    @Override
    public List<String> aliases() {
        return List.of();
    }

    @Override
    public @NotNull String permission() {
        return "*";
    }

    @Override
    public @NotNull String description() {
        return "Opens an anvil.";
    }

    @Override
    public void command(CommandSender sender, String[] args) {
        if (!sender.hasPermission(permission())) return;
        if (!(sender instanceof Player player)) return;
        player.openAnvil(null, false);
        player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_PLACE, 1, 1);
    }

    @Override
    public List<String> tab(CommandSender sender, String[] args) {
        return List.of();
    }
}
