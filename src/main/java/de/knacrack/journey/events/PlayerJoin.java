package de.knacrack.journey.events;

import de.knacrack.journey.Journey;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.title.Title;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {

    @Deprecated
    public PlayerJoin() {
        Bukkit.getPluginManager().registerEvents(this, Journey.getInstance());
    }

    @EventHandler
    void playerJoin(PlayerJoinEvent event) {
        event.joinMessage(MiniMessage.miniMessage().deserialize(String.format("<dark_gray>[<green>+<dark_gray>] <gold><bold>%s</bold>", event.getPlayer().getName())));
        Player player = event.getPlayer();

        Component title;
        Component subtitle = Component.text(player.getName()).color(TextColor.color(Color.GREEN.asRGB()));

        if (player.hasPlayedBefore()) {
            title = Component.text("§6Welcome back!");
            player.showTitle(Title.title(title, subtitle, Title.DEFAULT_TIMES));
        } else {
            title = Component.text("§6Welcome on this Server!");
            player.showTitle(Title.title(title, subtitle, Title.DEFAULT_TIMES));
        }
    }

}
