package de.knacrack.journey.utility;

import org.bukkit.entity.EntityType;

public class MobUtils {
    public static boolean isSkeleton(EntityType type) {
        return type == EntityType.SKELETON || type == EntityType.WITHER_SKELETON || type == EntityType.STRAY || type == EntityType.SKELETON_HORSE || type == EntityType.BOGGED || type == EntityType.WITHER;
    }

    public static boolean isZombie(EntityType type) {
        return type == EntityType.ZOMBIE || type == EntityType.ZOMBIE_HORSE || type == EntityType.HUSK || type == EntityType.DROWNED || type == EntityType.ZOMBIE_VILLAGER || type == EntityType.ZOMBIFIED_PIGLIN || type == EntityType.ZOGLIN;
    }

    public static boolean isUndead(EntityType type) {
        return isZombie(type) || isSkeleton(type);
    }

}
