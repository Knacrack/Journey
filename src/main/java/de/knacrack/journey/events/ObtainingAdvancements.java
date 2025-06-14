package de.knacrack.journey.events;

import de.knacrack.journey.Journey;
import io.papermc.paper.advancement.AdvancementDisplay;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;

public class ObtainingAdvancements implements Listener {

    public ObtainingAdvancements() {
        Bukkit.getPluginManager().registerEvents(this, Journey.getInstance());
    }

    @EventHandler
    public void obtainingAdvancements(PlayerAdvancementDoneEvent event) {
        AdvancementDisplay advancementDisplay = event.getAdvancement().getDisplay();
        if (advancementDisplay != null && event.getAdvancement().getDisplay().doesAnnounceToChat()) {
            Component message = MiniMessage.miniMessage().deserialize("<dark_gray>[<green>Advancement<dark_gray>] <white>").append(event.getPlayer().displayName().color(TextColor.color(Color.ORANGE.asRGB()))).append(Component.text(" has obtained ")).append(event.getAdvancement().displayName());
            event.message(message);
        }
    }

}
