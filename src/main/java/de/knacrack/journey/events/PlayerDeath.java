package de.knacrack.journey.events;

import de.knacrack.journey.Journey;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class PlayerDeath implements Listener {

    public PlayerDeath() {
        Bukkit.getPluginManager().registerEvents(this, Journey.getInstance());
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onPlayerDeath(@NotNull PlayerDeathEvent event) {
        String message = PlainTextComponentSerializer.plainText().serialize(Objects.requireNonNull(event.deathMessage()));
        event.deathMessage(MiniMessage.miniMessage().deserialize(String.format("<dark_gray>[<white>☠<dark_gray>] <red>%s", message)));
    }

}
