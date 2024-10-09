package de.knacrack.journey.events;

import de.knacrack.journey.Journey;
import org.bukkit.Bukkit;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.projectiles.ProjectileSource;

public class FriendlyFirePet implements Listener {

    public FriendlyFirePet() {
        Bukkit.getPluginManager().registerEvents(this, Journey.getInstance());
    }

    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    public void onPetDamage(EntityDamageByEntityEvent damageEvent) {
        Entity damager = damageEvent.getDamager();
        if (!(damageEvent.getEntity() instanceof Tameable pet)) return;
        if (!pet.isTamed()) return;
        Player owner = (Player) pet.getOwner();
        if (damager instanceof Projectile projectile) {
            ProjectileSource shooter = projectile.getShooter();
            if (!(shooter instanceof Player playerShooter)) return;
            if (playerShooter != owner) return;
            damageEvent.setCancelled(true);
            if ((projectile instanceof AbstractArrow && !projectile.getType().equals(EntityType.TRIDENT))) {
                projectile.remove();
            }
            return;
        }
        if (!(damager instanceof Player damagingPlayer)) return;
        if (damagingPlayer != owner) return;
        damageEvent.setCancelled(true);
    }

}
