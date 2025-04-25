package de.knacrack.journey.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class Equip implements Command {

    public Equip() {
        registerCommand(this);
    }

    @Override
    public String commandName() {
        return "equip";
    }

    @Override
    public List<String> aliases() {
        return List.of();
    }

    @Override
    public String permission() {
        return "*";
    }

    @Override
    public String description() {
        return "Equip an item in your hand to a slot.";
    }

    @Override
    public void command(CommandSender sender, String[] args) {
        if (!sender.hasPermission(permission())) return;
        if (!(sender instanceof Player player)) return;
        ItemStack itemHand = player.getEquipment().getItemInMainHand();
        if (args.length < 1) return;
        switch (args[0].toUpperCase()) {
            case "HELMET", "HEAD" -> equipItem(player, EquipmentSlot.HEAD, itemHand);
            case "CHESTPLATE", "BODY" -> equipItem(player, EquipmentSlot.CHEST, itemHand);
            case "LEGGINGS", "LEGS" -> equipItem(player, EquipmentSlot.LEGS, itemHand);
            case "BOOTS", "FEET" -> equipItem(player, EquipmentSlot.FEET, itemHand);
        }
    }

    private void equipItem(Player player, EquipmentSlot slot, ItemStack item) {
        ItemStack equippedItem = player.getEquipment().getItem(slot).clone();
        player.getInventory().remove(item);
        player.getInventory().addItem(equippedItem);
        player.getEquipment().setItem(slot, item);
    }

    @Override
    public List<String> tab(CommandSender sender, String[] args) {
        return sender.hasPermission(permission()) ? List.of("HELMET", "CHESTPLATE", "LEGGINGS", "BOOTS") : List.of();
    }
}
