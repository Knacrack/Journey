package de.knacrack.journey.commands;

import de.knacrack.journey.utility.Items;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Item implements Command {

    public Item() {
        registerCommand(this);
    }

    @Override
    public String commandName() {
        return "item";
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
        return "";
    }

    @Override
    public void command(CommandSender sender, String[] args) {
        if (!sender.hasPermission(permission())) return;
        if (!(sender instanceof Player player)) return;
        player.getInventory().addItem(Items.DIVER_HELMET());
    }

    @Override
    public List<String> tab(CommandSender sender, String[] args) {
        return List.of();
    }
}
