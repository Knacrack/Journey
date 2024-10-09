package de.knacrack.journey.events;

import de.knacrack.journey.Journey;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuit implements Listener {

    public PlayerQuit() {
        Bukkit.getPluginManager().registerEvents(this, Journey.getInstance());
    }

    @EventHandler
    void playerJoin(PlayerQuitEvent event) {
        event.quitMessage(MiniMessage.miniMessage().deserialize(String.format("<dark_gray>[<red>-<dark_gray>] <gold><bold>%s</bold>", event.getPlayer().getName())));
    }

}
