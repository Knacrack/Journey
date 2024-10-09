package de.knacrack.journey.events;

import com.google.common.collect.Lists;
import de.knacrack.journey.Journey;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.vehicle.VehicleEnterEvent;
import org.bukkit.event.vehicle.VehicleExitEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class FasterMinecard implements Listener {

    private Double MAX_SPEED = 0.4;
    private Map blockMaxSpeeds = new HashMap<Material, Double>();
    private Collection<Material> rails = Lists.newArrayList(Material.RAIL, Material.POWERED_RAIL, Material.DETECTOR_RAIL, Material.ACTIVATOR_RAIL);

    public FasterMinecard() {
        Bukkit.getPluginManager().registerEvents(this, Journey.getInstance());
    }

    /*@EventHandler()
    public void minecardMove(VehicleMoveEvent event) {
        if (event.getVehicle() instanceof Minecart minecard && !minecard.isEmpty() && minecard.getPassengers().getFirst() instanceof Player player) {
            @NotNull Material block = minecard.getLocation().getBlock().getType();
            if (rails.contains(block)) {
                minecard.setMaxSpeed(0.8);
            }
        }
    }*/

    @EventHandler()
    public void minecartEnter(VehicleEnterEvent event) {
        if (event.getVehicle() instanceof Minecart minecard && event.getEntered() instanceof Player player) {
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
    }

}
