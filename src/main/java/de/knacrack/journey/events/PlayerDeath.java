package de.knacrack.journey.events;

import de.knacrack.journey.Journey;
import de.knacrack.journey.utility.PlayerHead;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.Random;

public class PlayerDeath implements Listener {

    public PlayerDeath() {
        Bukkit.getPluginManager().registerEvents(this, Journey.getInstance());
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onPlayerDeath(@NotNull PlayerDeathEvent event) {
        String message = PlainTextComponentSerializer.plainText().serialize(Objects.requireNonNull(event.deathMessage()));
        //event.deathMessage(Component.text(String.format("§8[§r☠§8]§c %s", message))); // <dark_gray>[<white>☠<dark_gray>] <red>Knacrack
        event.deathMessage(MiniMessage.miniMessage().deserialize(String.format("<dark_gray>[<white>☠<dark_gray>] <red>%s", message)));
        /*if (event.getPlayer().getKiller() != null && new Random().nextFloat() <= 0.33f) {
            event.getPlayer().getWorld().dropItem(event.getPlayer().getKiller().getLocation(), PlayerHead.getPlayerHead(event.getPlayer().getUniqueId()));
        }*/
    }

}
