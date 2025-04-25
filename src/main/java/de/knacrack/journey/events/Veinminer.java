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

public class Veinminer implements Listener {

    private static final Set<Material> ores = new HashSet<>();

    static {
        ores.add(Material.COAL_ORE);
        ores.add(Material.LAPIS_ORE);
        ores.add(Material.REDSTONE_ORE);
        ores.add(Material.COPPER_ORE);
        ores.add(Material.IRON_ORE);
        ores.add(Material.GOLD_ORE);
        ores.add(Material.DIAMOND_ORE);
        ores.add(Material.EMERALD_ORE);

        ores.add(Material.DEEPSLATE_COAL_ORE);
        ores.add(Material.DEEPSLATE_LAPIS_ORE);
        ores.add(Material.DEEPSLATE_REDSTONE_ORE);
        ores.add(Material.DEEPSLATE_COPPER_ORE);
        ores.add(Material.DEEPSLATE_IRON_ORE);
        ores.add(Material.DEEPSLATE_GOLD_ORE);
        ores.add(Material.DEEPSLATE_DIAMOND_ORE);
        ores.add(Material.DEEPSLATE_EMERALD_ORE);

        ores.add(Material.NETHER_GOLD_ORE);
        ores.add(Material.NETHER_QUARTZ_ORE);
        ores.add(Material.ANCIENT_DEBRIS);
    }

    public Veinminer() {
        Bukkit.getPluginManager().registerEvents(this, Journey.getInstance());
    }

    @EventHandler
    public void breakOre(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlock();
        ItemStack item = player.getInventory().getItemInMainHand();

        if (CustomEnchantment.VEINMINER == null || !item.containsEnchantment(CustomEnchantment.VEINMINER)) return;
        if (player.isSneaking()) return;
        if (!ores.contains(block.getType())) return;

        List<Block> list = BlockSearch.findConnectedBlocks(block, block.getType());

        for (int i = 0; i < list.size(); i++) {
            if (i >= 128) break;
            list.get(i).getDrops(item).forEach(drop -> player.getWorld().dropItemNaturally(player.getLocation(), drop));
            list.get(i).setType(Material.AIR);
        }
        int exp = event.getExpToDrop() * (Math.min(list.size(), 128));
        event.setExpToDrop(exp);
        //((ExperienceOrb)player.getWorld().spawn(player.getLocation(), ExperienceOrb.class)).setExperience(exp);
        player.playSound(player.getLocation(), Sound.BLOCK_STONE_BREAK, 1f, 1f);
    }
}
