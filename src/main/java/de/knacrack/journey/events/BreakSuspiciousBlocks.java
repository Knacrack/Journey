package de.knacrack.journey.events;

import de.knacrack.journey.Journey;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockStateMeta;

public class BreakSuspiciousBlocks implements Listener {

    public BreakSuspiciousBlocks() {
        Bukkit.getPluginManager().registerEvents(this, Journey.getInstance());
    }

    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    public void breakSuspiciousBlock(BlockBreakEvent event) {
        Block block = event.getBlock();
        ItemStack item = event.getPlayer().getEquipment().getItemInMainHand();

        if (!Material.SUSPICIOUS_GRAVEL.equals(block.getType()) && !Material.SUSPICIOUS_SAND.equals(block.getType()))
            return;
        if (!item.hasItemMeta() || !item.getItemMeta().hasEnchant(Enchantment.SILK_TOUCH)) return;

        ItemStack drop = new ItemStack(block.getType(), 1);
        BlockStateMeta meta = (BlockStateMeta) drop.getItemMeta();
        meta.setBlockState(block.getState());
        drop.setItemMeta(meta);

        event.getPlayer().getWorld().dropItemNaturally(block.getLocation(), drop);
    }

}
