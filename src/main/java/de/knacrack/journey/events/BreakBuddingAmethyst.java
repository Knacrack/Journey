package de.knacrack.journey.events;

import de.knacrack.journey.Journey;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class BreakBuddingAmethyst implements Listener {

    public BreakBuddingAmethyst() {
        Bukkit.getPluginManager().registerEvents(this, Journey.getInstance());
    }

    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    public void breakBuddingAmethyst(BlockBreakEvent event) {
        ItemStack item = event.getPlayer().getEquipment().getItemInMainHand();

        if (!Material.BUDDING_AMETHYST.equals(event.getBlock().getType())) return;
        if (!item.hasItemMeta() || !item.getItemMeta().hasEnchant(Enchantment.SILK_TOUCH)) return;

        event.getPlayer().getWorld().dropItemNaturally(event.getBlock().getLocation(), new ItemStack(Material.BUDDING_AMETHYST, 1));
    }

}
