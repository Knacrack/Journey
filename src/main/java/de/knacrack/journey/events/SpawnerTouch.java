package de.knacrack.journey.events;

import de.knacrack.journey.Journey;
import de.knacrack.journey.utility.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.block.TrialSpawner;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockStateMeta;

public class SpawnerTouch implements Listener {

    public SpawnerTouch() {
        Bukkit.getPluginManager().registerEvents(this, Journey.getInstance());
    }

    @EventHandler
    public void onSpawnerTouch(BlockBreakEvent event) {
        Player player = event.getPlayer();

        if (Material.SPAWNER.equals(event.getBlock().getType()) && player.getInventory().getItem(EquipmentSlot.HAND).getEnchantments().containsKey(Utils.getEnchantment(new NamespacedKey("knacrack", "spawner_touch")))) {
            event.setExpToDrop(0);
            ItemStack item = new ItemStack(Material.SPAWNER, 1);
            BlockStateMeta meta = (BlockStateMeta) item.getItemMeta();
            CreatureSpawner cs = (CreatureSpawner) meta.getBlockState();
            EntityType type = ((CreatureSpawner) event.getBlock().getState()).getSpawnedType();

            if (type == null) return;
            cs.setSpawnedType(type);
            meta.setBlockState(cs);
            //meta.displayName(Component.text("§c§l" + "Spawner (" + type.name().toLowerCase() + ")"));
            item.setItemMeta(meta);
            player.getWorld().dropItem(event.getBlock().getLocation().add(0, 1, 0), item);
        } else if (Material.TRIAL_SPAWNER.equals(event.getBlock().getType()) && player.getInventory().getItem(EquipmentSlot.HAND).getEnchantments().containsKey(Utils.getEnchantment(new NamespacedKey("knacrack", "spawner_touch")))) {
            event.setExpToDrop(0);

            ItemStack item = new ItemStack(Material.TRIAL_SPAWNER, 1);
            BlockStateMeta meta = (BlockStateMeta) item.getItemMeta();
            TrialSpawner ts = (TrialSpawner) meta.getBlockState();

            TrialSpawner state = (TrialSpawner) event.getBlock().getState();
            if (state == null) return;

            EntityType spawnedType = state.getNormalConfiguration().getSpawnedType();
            EntityType spawnedTypeOminous = state.getOminousConfiguration().getSpawnedType();
            if (spawnedType == null || spawnedTypeOminous == null) return;
            ts.getNormalConfiguration().setSpawnedType(spawnedType);
            ts.getOminousConfiguration().setSpawnedType(spawnedTypeOminous);
            meta.setBlockState(ts);
            //meta.setBlockState(state);
            item.setItemMeta(meta);
            player.getWorld().dropItem(event.getBlock().getLocation().add(0, 1, 0), item);
        }
    }

    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    public void placeMobSpawner(BlockPlaceEvent event) {
        Block block = event.getBlock();
        if (!Material.SPAWNER.equals(block.getType())) return;

        block = event.getBlockPlaced();
        CreatureSpawner creatureSpawner = (CreatureSpawner) block.getState();
        creatureSpawner.setSpawnedType(getEntityType(event.getItemInHand()));
        creatureSpawner.update();
    }

    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    public void placeTrialSpawner(BlockPlaceEvent event) {
        Block block = event.getBlock();
        if (!Material.TRIAL_SPAWNER.equals(block.getType())) return;

        block = event.getBlockPlaced();
        TrialSpawner trialSpawner = (TrialSpawner) block.getState();

        trialSpawner.getNormalConfiguration().setSpawnedType(getEntityTypeTrial(event.getItemInHand()));
        trialSpawner.getOminousConfiguration().setSpawnedType(getEntityTypeTrialOminous(event.getItemInHand()));


        trialSpawner.update();
    }

    private EntityType getEntityType(ItemStack item) {
        BlockStateMeta meta = (BlockStateMeta) item.getItemMeta();
        CreatureSpawner spawner = (CreatureSpawner) meta.getBlockState();
        return spawner.getSpawnedType();
    }

    private EntityType getEntityTypeTrial(ItemStack item) {
        BlockStateMeta meta = (BlockStateMeta) item.getItemMeta();
        TrialSpawner spawner = (TrialSpawner) meta.getBlockState();
        return spawner.getNormalConfiguration().getSpawnedType();
    }
    private EntityType getEntityTypeTrialOminous(ItemStack item) {
        BlockStateMeta meta = (BlockStateMeta) item.getItemMeta();
        TrialSpawner spawner = (TrialSpawner) meta.getBlockState();
        return spawner.getOminousConfiguration().getSpawnedType();
    }

    private CreatureSpawner getCreatureSpawner(ItemStack item) {
        BlockStateMeta meta = (BlockStateMeta) item.getItemMeta();
        return (CreatureSpawner) meta.getBlockState();
    }

    private TrialSpawner getTrialSpawner(ItemStack item) {
        BlockStateMeta meta = (BlockStateMeta) item.getItemMeta();
        return (TrialSpawner) meta.getBlockState();
    }

}
