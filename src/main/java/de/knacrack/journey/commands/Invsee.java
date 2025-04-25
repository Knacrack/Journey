package de.knacrack.journey.commands;

import de.knacrack.journey.Journey;
import de.knacrack.journey.utility.Utils;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Invsee implements Command {
    public Invsee() {
        registerCommand(this);
    }

    @Override
    public String commandName() {
        return "invsee";
    }

    @Override
    public List<String> aliases() {
        return List.of("inv");
    }

    @Override
    public @NotNull String permission() {
        return "*";
    }

    @Override
    public @NotNull String description() {
        return "Open the inventory of another player.";
    }

    @Override
    public void command(CommandSender sender, String[] args) {
        if (!sender.hasPermission(permission())) return;
        if (!(sender instanceof Player player)) return;
        if (args.length == 0) {
            player.openInventory(player.getInventory());
            player.playSound(player.getLocation(), Sound.BLOCK_CHEST_OPEN, 1f, 1f);
        }

        if (args.length == 1) {
            Player target;
            if ((target = Bukkit.getPlayer(args[0])) == null) {
                sender.sendMessage("Dieser Spieler ist nicht Online.");
            } else {
                player.openInventory(target.getInventory());
                player.playSound(player.getLocation(), Sound.BLOCK_CHEST_OPEN, 1f, 1f);
            }
        }

        switch (args.length) {
            case 0 -> {
                player.openInventory(player.getInventory());
                player.playSound(player.getLocation(), Sound.BLOCK_CHEST_OPEN, 1f, 1f);
            }
            case 1 -> {
                OfflinePlayer target = Journey.getInstance().getServer().getOfflinePlayer(args[0]);
                if (!target.hasPlayedBefore()) return;
                player.openInventory(target.getPlayer().getInventory());
                player.playSound(player.getLocation(), Sound.BLOCK_CHEST_OPEN, 1f, 1f);
            }
        }
    }

    @Override
    public List<String> tab(CommandSender sender, String[] args) {
        List<String> list = Collections.emptyList();
        if (args.length == 1 && sender.hasPermission(permission())) {
            list = Utils.getOnlinePlayers(args[0]);
            list.addAll(Arrays.asList(Bukkit.getOfflinePlayers()).stream().map(OfflinePlayer::getName).toList());
        }
        return list;
    }
}
