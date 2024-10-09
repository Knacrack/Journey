package de.knacrack.journey.events;

import de.knacrack.journey.Journey;
import org.bukkit.Bukkit;
import org.bukkit.PortalType;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Piglin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPortalEvent;

@SuppressWarnings("ConstantValue")
public class PiglinZombification implements Listener {

    public PiglinZombification() {
        Bukkit.getPluginManager().registerEvents(this, Journey.getInstance());
    }

    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    public void piglinWalkTroughPortal(EntityPortalEvent event) {
        EntityType entityType = event.getEntityType();
        if (!EntityType.PIGLIN.equals(entityType) || !EntityType.PIGLIN_BRUTE.equals(entityType)) return;
        if (!PortalType.NETHER.equals(event.getPortalType())) return;
        ((Piglin) event.getEntity()).setImmuneToZombification(true);
    }

}
