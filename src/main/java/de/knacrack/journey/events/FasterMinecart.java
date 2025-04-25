package de.knacrack.journey.events;

import de.knacrack.journey.Journey;
import org.bukkit.Bukkit;
import org.bukkit.entity.Minecart;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.vehicle.VehicleCreateEvent;

public class FasterMinecart implements Listener {

    public FasterMinecart() {
        Bukkit.getPluginManager().registerEvents(this, Journey.getInstance());
    }

    @EventHandler()
    public void minecartEnter(VehicleCreateEvent event) {
        if (event.getVehicle() instanceof Minecart minecard) {
            minecard.setMaxSpeed(0.8);
        }
    }

}
