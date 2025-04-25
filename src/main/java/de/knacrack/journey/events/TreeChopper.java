package de.knacrack.journey.events;

import de.knacrack.journey.Journey;
import de.knacrack.journey.utility.BlockSearch;
import de.knacrack.journey.utility.CustomEnchantment;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TreeChopper implements Listener {

    private static final Set<Material> logs = new HashSet<>();

    static {
        logs.add(Material.ACACIA_LOG);
        logs.add(Material.BIRCH_LOG);
        logs.add(Material.DARK_OAK_LOG);
        logs.add(Material.JUNGLE_LOG);
        logs.add(Material.OAK_LOG);
        logs.add(Material.SPRUCE_LOG);
        logs.add(Material.CHERRY_LOG);
        logs.add(Material.MANGROVE_LOG);

        logs.add(Material.WARPED_STEM);
        logs.add(Material.CRIMSON_STEM);

        logs.add(Material.STRIPPED_ACACIA_LOG);
        logs.add(Material.STRIPPED_BIRCH_LOG);
        logs.add(Material.STRIPPED_DARK_OAK_LOG);
        logs.add(Material.STRIPPED_JUNGLE_LOG);
        logs.add(Material.STRIPPED_OAK_LOG);
        logs.add(Material.STRIPPED_SPRUCE_LOG);
        logs.add(Material.STRIPPED_CHERRY_LOG);
        logs.add(Material.STRIPPED_MANGROVE_LOG);

        logs.add(Material.STRIPPED_WARPED_STEM);
        logs.add(Material.STRIPPED_CRIMSON_STEM);
    }

    public TreeChopper() {
        Bukkit.getPluginManager().registerEvents(this, Journey.getInstance());
    }

    @EventHandler
    public void breakTreeEvent(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlock();
        ItemStack item = player.getInventory().getItemInMainHand();

        if (CustomEnchantment.TREE_CHOPPER == null || !item.containsEnchantment(CustomEnchantment.TREE_CHOPPER)) return;
        if (player.isSneaking()) return;
        if (!logs.contains(block.getType())) return;

        List<Block> list = BlockSearch.findConnectedBlocks(block, block.getType());

        for (int i = 0; i < list.size(); i++) {
            if (i >= 128) break;
            list.get(i).breakNaturally(item);
        }
        player.playSound(player.getLocation(), Sound.BLOCK_WOOD_BREAK, 1f, 1f);

    }

}
