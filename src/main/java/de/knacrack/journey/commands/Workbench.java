package de.knacrack.journey.commands;

import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Workbench implements Command {

    public Workbench() {
        registerCommand(this);
    }

    @Override
    public String commandName() {
        return "workbench";
    }

    @Override
    public List<String> aliases() {
        return List.of("craft", "wb");
    }

    @Override
    public @NotNull String permission() {
        return "*";
    }

    @Override
    public @NotNull String description() {
        return "Open a workbench.";
    }

    @Override
    public void command(CommandSender sender, String[] args) {
        if (!sender.hasPermission(permission())) return;
        if (!(sender instanceof Player player)) return;
        player.openWorkbench(null, false);
        player.playSound(player.getLocation(), Sound.BLOCK_CRAFTER_CRAFT, 1, 1);
    }

    @Override
    public List<String> tab(CommandSender sender, String[] args) {
        return List.of();
    }
}
