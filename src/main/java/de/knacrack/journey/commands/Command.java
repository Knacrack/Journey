package de.knacrack.journey.commands;

import de.knacrack.journey.Journey;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface Command {

    String commandName();

    List<String> aliases();

    String permission();

    String description();

    void command(CommandSender sender, String[] args);

    List<String> tab(CommandSender sender, String[] args);

    default void registerCommand(@NotNull Command command) {
        org.bukkit.command.Command cmd = new org.bukkit.command.Command(commandName()) {
            @Override
            public boolean execute(@NotNull CommandSender commandSender, @NotNull String s, @NotNull String[] strings) {
                command(commandSender, strings);
                return false;
            }

            @Override
            public @NotNull List<String> tabComplete(@NotNull CommandSender sender, @NotNull String alias, @NotNull String[] args) throws IllegalArgumentException {
                return tab(sender, args);
            }
        };

        cmd.setAliases(aliases());
        cmd.setPermission(permission());
        cmd.setDescription(description());

        Bukkit.getCommandMap().register(Journey.getInstance().getName(), cmd);
    }
}