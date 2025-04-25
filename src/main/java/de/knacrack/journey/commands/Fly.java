package de.knacrack.journey.commands;

import de.knacrack.journey.utility.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;

public class Fly implements Command {

    public Fly() {
        registerCommand(this);
    }


    @Override
    public String commandName() {
        return "fly";
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
        return "Enables the player to fly.";
    }

    @Override
    public void command(CommandSender sender, String[] args) {
        if(sender.hasPermission(permission())) return;

        if(!(sender instanceof Player player)) return;

        if (args.length == 0) {
            player.setAllowFlight(!player.getAllowFlight());
            player.sendMessage("Flying is now " + (player.getAllowFlight() ? "enabled!" : "disabled!"));
        }

        if (args.length == 1) {
            Player target = Bukkit.getPlayer(args[0]);
            target.setAllowFlight(!target.getAllowFlight());
            player.sendMessage("Flying is now " + (target.getAllowFlight() ? "enabled" : "disabled") + " for " + target.getName() + "!");
            target.sendMessage("Flying is now " + (target.getAllowFlight() ? "enabled!" : "disabled!"));
        }
    }

    @Override
    public List<String> tab(CommandSender sender, String[] args) {
        List<String> list = Collections.emptyList();

        if (sender.hasPermission(permission()) && args.length == 1) {
            list = Utils.getOnlinePlayers(args[0]);
        }

        return list;
    }
}
