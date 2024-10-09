package de.knacrack.journey.events;

import de.knacrack.journey.Journey;
import de.knacrack.journey.utility.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class RunOnPaths implements Listener {

    public RunOnPaths() {
        Bukkit.getPluginManager().registerEvents(this, Journey.getInstance());
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void playerMove(final PlayerMoveEvent event) {
        // Players mustn't be flying
        final var player = event.getPlayer();
        if (player.isGliding() || player.isFlying()) {
            return;
        }

        LivingEntity effect_entity = player;
        if (player.isInsideVehicle() && player.getVehicle() instanceof LivingEntity vehicle) {
            effect_entity = vehicle;
        }

        // Inspect block type just a little below
        Block block = effect_entity.getLocation().clone().subtract(0.0, 0.1, 0.0).getBlock();
        if (!Material.DIRT_PATH.equals(block.getType())) {
            return;
        }

        // Apply potion effect
        effect_entity.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, (int) Utils.ms_to_ticks(1000), 1).withAmbient(false).withParticles(false).withIcon(false));
    }

}
