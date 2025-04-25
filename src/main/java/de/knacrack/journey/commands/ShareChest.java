package de.knacrack.journey.commands;

import de.knacrack.journey.utility.Utils;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.*;

public class ShareChest implements Command {

    // Map, um die Spieleranfragen zu speichern
    private final Map<UUID, UUID> pendingRequests = new HashMap<>();
    // Map, um die geteilten Kisten zu speichern
    private final Map<UUID, Map<UUID, Inventory>> sharedChests = new HashMap<>();


    public ShareChest() {
        registerCommand(this);
    }


    @Override
    public String commandName() {
        return "sharechest";
    }

    @Override
    public List<String> aliases() {
        return List.of();
    }

    @Override
    public String permission() {
        return "";
    }

    @Override
    public String description() {
        return "Teile eine Kiste mit einem anderen Spieler. Nutzung: /sharechest <Spielername> oder /sharechest accept.";
    }

    @Override
    public void command(CommandSender sender, String[] args) {
        if (!sender.hasPermission(permission())) return;
        if (!(sender instanceof Player player)) {
            sender.sendMessage(Component.text("Nur Spieler können diesen Befehl ausführen.").color(NamedTextColor.RED));
            return;
        }

        if (args.length < 1) {
            player.sendMessage(Component.text("Benutzung: /sharechest <Spielername> oder /sharechest accept.").color(NamedTextColor.RED));
            return;
        }


        switch (args[0].toLowerCase()) {
            case "accept" -> {

                if (!pendingRequests.containsKey(player.getUniqueId())) {
                    player.sendMessage(Component.text("Du hast keine offene Anfrage!").color(NamedTextColor.RED));
                    return;
                }


                UUID sharerUUID = pendingRequests.get(player.getUniqueId());
                Player sharer = Bukkit.getPlayer(sharerUUID);

                if (sharer == null || !sharer.isOnline()) {
                    player.sendMessage(Component.text("Der Spieler, der deine Anfrage erhalten hat, ist nicht mehr online!").color(NamedTextColor.RED));
                    pendingRequests.remove(player.getUniqueId());
                    return;
                }

                // Gemeinsame Kiste erstellen/abrufen
                Inventory sharedChest = sharedChests
                        .computeIfAbsent(sharerUUID, k -> new HashMap<>())
                        .computeIfAbsent(player.getUniqueId(), k -> Bukkit.createInventory(null, 27, "Geteilte Kiste"));

                player.openInventory(sharedChest);
                sharer.openInventory(sharedChest);

                // Anfrage entfernen und Erfolgsmeldung
                pendingRequests.remove(player.getUniqueId());
            }
            default -> {
                Player target = Bukkit.getPlayer(args[0]);

                if (target == null || !target.isOnline()) return;

                if (target.getUniqueId().equals(player.getUniqueId())) {
                    player.sendMessage(ChatColor.RED + "Du kannst keine Kiste mit dir selbst teilen.");
                    return;
                }

                if (pendingRequests.containsKey(target.getUniqueId())) {
                    Component alreadyRequestedMessage = Component.text(target.getName() + " hat bereits eine offene Anfrage!")
                            .color(TextColor.color(255, 0, 0)); // Rote Farbe
                    sender.sendMessage(alreadyRequestedMessage);
                    return;
                }

                // Anfrage speichern und Spieler benachrichtigen
                pendingRequests.put(target.getUniqueId(), player.getUniqueId());
                player.sendMessage(Component.text("Du hast " + target.getName() + " eingeladen, eine Kiste zu teilen.").color(NamedTextColor.GREEN));
                Component message = Component.text(player.getName() + " möchte eine Kiste mit dir teilen.")
                        .color(TextColor.color(255, 255, 0)) // Gelbe Farbe
                        .append(Component.text(" [ACCEPT]") // Zusätzlicher Text "[ACCEPT]"
                                .color(TextColor.color(0, 255, 0)) // Grüne Farbe
                                .hoverEvent(HoverEvent.showText(Component.text("Klicke hier, um die Anfrage anzunehmen!"))) // Hover-Text
                                .clickEvent(ClickEvent.runCommand("/sharechest accept"))); // Klick-Event

                target.sendMessage(message);

            }
        }

    }

    @Override
    public List<String> tab(CommandSender sender, String[] args) {
        List<String> list = Collections.emptyList();
        if (sender.hasPermission(permission())) {
            list = Utils.getOnlinePlayers(args[0]);
            list.add("accept");
        }
        return list;
    }
}
