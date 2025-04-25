package de.knacrack.journey.utility;

import com.google.common.collect.Lists;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.stream.Collectors;

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

    public static final List<String> getOnlinePlayers(String argument) {
        List<Player> players = Bukkit.getOnlinePlayers().stream().filter(player -> player.getName().startsWith(argument)).collect(Collectors.toList());
        List<String> names = Lists.newArrayList();

        if (!players.isEmpty()) {
            players.forEach(player -> names.add(player.getName()));
        }
        return names;
    }

}
