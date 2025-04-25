package de.knacrack.journey.events;

import de.knacrack.journey.Journey;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class StepOnFarmland implements Listener {

    public StepOnFarmland() {
        Bukkit.getPluginManager().registerEvents(this, Journey.getInstance());
    }

    @EventHandler
    public void onFarmland(PlayerInteractEvent event) {
        if (event.getAction() != Action.PHYSICAL) return;

        Block block = event.getClickedBlock();

        if (block == null) return;
        if (block.getType() == Material.FARMLAND) event.setCancelled(true);
    }

}
