package de.knacrack.journey.events;

import com.google.common.collect.Lists;
import de.knacrack.journey.Journey;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.vehicle.VehicleCreateEvent;
import org.bukkit.event.vehicle.VehicleEnterEvent;
import org.bukkit.event.vehicle.VehicleExitEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

public class FasterMinecart implements Listener {

    private final Collection<Material> rails = Lists.newArrayList(Material.RAIL, Material.POWERED_RAIL, Material.DETECTOR_RAIL, Material.ACTIVATOR_RAIL);

    public FasterMinecart() {
        Bukkit.getPluginManager().registerEvents(this, Journey.getInstance());
    }

    @EventHandler()
    public void minecartEnter(VehicleCreateEvent event) {
        if (event.getVehicle() instanceof Minecart minecard) {
            minecard.setMaxSpeed(0.8);
        }
    }

    /*@EventHandler()
    public void minecartEnter(VehicleEnterEvent event) {
        if (event.getVehicle() instanceof Minecart minecard && event.getEntered() instanceof Player) {
            @NotNull Material block = minecard.getLocation().getBlock().getType();
            if (rails.contains(block)) {
                minecard.setMaxSpeed(0.8);
            }
        }
    }

    @EventHandler()
    public void minecartExit(VehicleExitEvent event) {
        if (event.getVehicle() instanceof Minecart minecard && event.getExited() instanceof Player) {
            if (minecard.getMaxSpeed() > 0.4) {
                minecard.setMaxSpeed(0.4);
            }
        }
    }*/

}
