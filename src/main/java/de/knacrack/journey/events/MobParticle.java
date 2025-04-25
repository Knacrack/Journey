package de.knacrack.journey.events;

import de.knacrack.journey.Journey;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class MobParticle implements Listener {

    public MobParticle() {
        Bukkit.getPluginManager().registerEvents(this, Journey.getInstance());
    }

    @EventHandler
    public void onMobDamaged(EntityDamageEvent event) {
        if (event.getFinalDamage() == 0) return;
        EntityType type = event.getEntity().getType();
        if (!type.isAlive()) return;

        Location location = event.getEntity().getLocation().add(0, 0.5, 0);
        Material particleMaterial;

        switch (type) {
            case SKELETON -> particleMaterial = Material.BONE_BLOCK;
            case STRAY, POLAR_BEAR -> particleMaterial = Material.BLUE_ICE;
            case HUSK -> particleMaterial = Material.SAND;
            case CREEPER -> particleMaterial = Material.TNT;
            case SPIDER, CAVE_SPIDER -> particleMaterial = Material.COBWEB;
            case BOGGED -> particleMaterial = Material.MUD;
            case WITHER_SKELETON, WITHER -> particleMaterial = Material.COAL_BLOCK;
            case ENDERMAN, ENDERMITE -> particleMaterial = Material.PURPLE_SHULKER_BOX;
            case SLIME -> particleMaterial = Material.SLIME_BLOCK;
            case MAGMA_CUBE -> particleMaterial = Material.MAGMA_BLOCK;
            default -> particleMaterial = Material.REDSTONE_BLOCK; // Standard (wenn keine andere Bedingung erfüllt ist)
        }

        spawnParticle(location, 10, 0.2, 1, 0.2, 0.025, particleMaterial);
    }

    private void spawnParticle(Location location, int amount, double offsetX, double offsetY, double offsetZ, double speed, Material material) {
        String blockDataString = "minecraft:" + material.name().toLowerCase();
        BlockData blockData = Bukkit.getServer().createBlockData(blockDataString);

        location.getWorld().spawnParticle(Particle.BLOCK_CRUMBLE, location, amount, offsetX, offsetY, offsetZ, speed, blockData);
    }

}
