package de.knacrack.journey.events;

import de.knacrack.journey.utility.Utils;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.util.Vector;

import java.util.concurrent.ThreadLocalRandom;

public class ElytraBoost implements Listener {

    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    public void on_player_toggle_sneak(PlayerToggleSneakEvent event) {
        // Check sneaking, flying and having the enchantment
        final Player player = event.getPlayer();
        if ((!event.isSneaking() || !player.isGliding()) && player.getInventory().getChestplate().containsEnchantment(Utils.getEnchantment(new NamespacedKey("knacrack","boost")))) {
            return;
        }

        // Check cooldown
        if (player.getCooldown(Material.ELYTRA) > 0) {
            return;
        }

        // Apply boost
        player.setCooldown(Material.ELYTRA, 4000 / 50);
        Utils.applyElytraBoost(player, 0.8d);
        if (!GameMode.CREATIVE.equals(player.getGameMode())) {
            Utils.adjustDurability(player.getEquipment().getChestplate(), (int) (1.0 + 2.0 * Math.random()), player);
        }

        // Spawn particles
        final Location loc = player.getLocation();
        final double vel = player.getVelocity().length();
        for (int i = 0; i < 16; ++i) {
            final Vector rnd = Vector.getRandom().subtract(new Vector(.5, .5, .5)).normalize().multiply(.25);
            final Vector dir = rnd.clone().multiply(.5).subtract(player.getVelocity());
            loc.getWorld().spawnParticle(Particle.FIREWORK, loc.add(rnd), 0, dir.getX(), dir.getY(), dir.getZ(), vel * ThreadLocalRandom.current().nextDouble(0.4, 0.6));
        }
    }


}
