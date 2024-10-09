package de.knacrack.journey.utility;

import com.google.common.base.Preconditions;
import org.bukkit.NamespacedKey;
import org.bukkit.Registry;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings({"unused", "deprecation"})
public class Utils {

    public static long ms_to_ticks(long ms) {
        return ms / 50;
    }

    public static long ticks_to_ms(long ticks) {
        return ticks * 50;
    }

    public static void applyElytraBoost(final Player player, double factor) {
        final var v = player.getLocation().getDirection();
        v.normalize();
        v.multiply(factor);

        // Set velocity, play sound
        player.setVelocity(player.getVelocity().add(v));
        player.getWorld().playSound(player.getLocation(), Sound.ENTITY_ENDER_DRAGON_FLAP, SoundCategory.PLAYERS, 0.4f, 2.0f);
    }

    public static ItemStack adjustDurability(ItemStack item, int amount) {
        if (!item.getItemMeta().isUnbreakable()) {
            item.setDurability((short) (item.getDurability() + amount));
            if (item.getDurability() > item.getType().getMaxDurability()) {
                item.setAmount(0);
            }
        }
        return item;
    }

    @SuppressWarnings("UnusedReturnValue")
    public static ItemStack adjustDurability(ItemStack item, int amount, Player player) {
        if (!item.getItemMeta().isUnbreakable()) {
            item.setDurability((short) (item.getDurability() + amount));
            if (item.getDurability() > item.getType().getMaxDurability()) {
                item.setAmount(0);
                player.playSound(player.getLocation(), Sound.ENTITY_ITEM_BREAK, 1f, 1f);
            }
        }
        return item;
    }

    @SuppressWarnings("JavaExistingMethodCanBeUsed")
    public static @NotNull Enchantment getEnchantment(@NotNull String key) {
        NamespacedKey namespacedKey = new NamespacedKey("journey", key);
        Enchantment enchantment = Registry.ENCHANTMENT.get(namespacedKey);
        Preconditions.checkNotNull(enchantment, "No Enchantment found for %s. This is a bug.", namespacedKey);
        return enchantment;
    }
    public static @NotNull Enchantment getEnchantment(@NotNull NamespacedKey key) {

        Enchantment enchantment = Registry.ENCHANTMENT.get(key);
        Preconditions.checkNotNull(enchantment, "No Enchantment found for %s. This is a bug.", key);
        return enchantment;
    }

}
