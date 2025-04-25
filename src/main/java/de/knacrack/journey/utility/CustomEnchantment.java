package de.knacrack.journey.utility;

import org.bukkit.NamespacedKey;
import org.bukkit.Registry;
import org.bukkit.enchantments.Enchantment;
import org.jetbrains.annotations.NotNull;

public abstract class CustomEnchantment {

    public static final Enchantment SPAWNER_TOUCH = getEnchantment("simplexity", "spawner_touch");
    public static final Enchantment BOOST = getEnchantment("simplexity", "boost");
    public static final Enchantment VEINMINER = getEnchantment("simplexity", "veinminer");
    public static final Enchantment TREE_CHOPPER = getEnchantment("simplexity", "tree_chopper");

    public static @NotNull Enchantment getEnchantment(@NotNull String key, String name) {
        NamespacedKey namespacedKey = new NamespacedKey(key, name);
        return getEnchantment(namespacedKey);
    }

    public static @NotNull Enchantment getEnchantment(@NotNull NamespacedKey key) {
        return Registry.ENCHANTMENT.get(key);
    }

}
