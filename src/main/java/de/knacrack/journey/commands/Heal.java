package de.knacrack.journey.commands;

import de.knacrack.journey.utility.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;

public class Heal implements Command {
    public Heal() {
        registerCommand(this);
    }


    @Override
    public String commandName() {
        return "heal";
    }

    @Override
    public List<String> aliases() {
        return List.of("feed");
    }

    @Override
    public @NotNull String permission() {
        return "*";
    }

    @Override
    public @NotNull String description() {
        return "Heal yourself or another player.";
    }

    @Override
    public void command(CommandSender sender, String[] args) {
        if (!sender.hasPermission(permission())) return;
        switch (args.length) {
            case 0 -> {
                if (!(sender instanceof Player player))
                    return;
                player.setHealth(player.getMaxHealth());
                player.setFoodLevel(20);
                player.setSaturation(20f);
                player.sendMessage("Du hast dich geheilt.");
                return;
            }

            case 1 -> {
                Player target = Bukkit.getPlayer(args[0]);
                if (target == null) {
                    sender.sendMessage("Dieser Spieler ist nicht Online.");
                    return;
                }

                target.setHealth(target.getMaxHealth());
                target.setFoodLevel(20);
                target.setSaturation(20f);
                sender.sendMessage("Du Hast %p% geheilt.".replace("%p%", target.getName()));
                target.sendMessage("Du hast dich geheilt.");
                return;
            }
        }

        sender.sendMessage("/heal <player>");
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
