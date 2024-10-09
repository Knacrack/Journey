package de.knacrack.journey.events;

import de.knacrack.journey.Journey;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.data.Openable;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

public class OpenIronDoorsWithHand implements Listener {

    public OpenIronDoorsWithHand() {
        Bukkit.getPluginManager().registerEvents(this, Journey.getInstance());
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onIronDoorOpen(PlayerInteractEvent event) {
        if (!EquipmentSlot.HAND.equals(event.getHand())) return;
        if (event.getPlayer().isSneaking()) return;
        if (!event.getAction().isRightClick()) return;
        if (event.getClickedBlock() == null) return;
        Block block = event.getClickedBlock();
        World world = block.getWorld();
        switch (block.getType()) {
            case IRON_DOOR -> {
                if (open(block, event.getPlayer())) {
                    world.playSound(block.getLocation(), Sound.BLOCK_IRON_DOOR_OPEN, SoundCategory.BLOCKS, 1f, 1f);
                } else {
                    world.playSound(block.getLocation(), Sound.BLOCK_IRON_DOOR_CLOSE, SoundCategory.BLOCKS, 1f, 1f);
                }
            }
            case IRON_TRAPDOOR -> {
                if (open(block, event.getPlayer())) {
                    world.playSound(block.getLocation(), Sound.BLOCK_IRON_TRAPDOOR_OPEN, SoundCategory.BLOCKS, 1f, 1f);
                } else {
                    world.playSound(block.getLocation(), Sound.BLOCK_IRON_TRAPDOOR_CLOSE, SoundCategory.BLOCKS, 1f, 1f);
                }
            }
        }
    }

    private boolean open(Block block, Player player) {
        Openable openable = (Openable) block.getBlockData();
        boolean isOpen = openable.isOpen();
        openable.setOpen(!isOpen);
        block.setBlockData(openable);
        player.swingMainHand();
        return !isOpen;
    }

}
