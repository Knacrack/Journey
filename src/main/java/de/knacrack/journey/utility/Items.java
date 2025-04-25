package de.knacrack.journey.utility;

import io.papermc.paper.datacomponent.DataComponentTypes;
import io.papermc.paper.datacomponent.item.DamageResistant;
import io.papermc.paper.datacomponent.item.Equippable;
import io.papermc.paper.datacomponent.item.ItemAttributeModifiers;
import io.papermc.paper.registry.keys.AttributeKeys;
import io.papermc.paper.registry.keys.SoundEventKeys;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemRarity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.UUID;

public class Items {
    public static ItemStack DIVER_HELMET() {

        ItemStack item = new ItemStack(Material.DIAMOND_HELMET);

        ItemMeta meta = item.getItemMeta();
        meta.displayName(Component.text("Diver").color(NamedTextColor.AQUA));
        item.setItemMeta(meta);

        Equippable.Builder builder = item.getData(DataComponentTypes.EQUIPPABLE).toBuilder();
        builder.equipSound(SoundEventKeys.ENTITY_GHAST_HURT);
        builder.damageOnHurt(true);

        item.setData(DataComponentTypes.EQUIPPABLE, builder.build());


        item.setType(Material.GLASS);

        return item;
    }

    public static ItemStack DECREPIT_FLAMBERGE() {
        ItemStack flamberge = new ItemStack(Material.NETHERITE_SWORD);
        ItemMeta itemMeta = flamberge.getItemMeta();
        itemMeta.displayName(Component.text("Flamberge").color(NamedTextColor.YELLOW));
        flamberge.setItemMeta(itemMeta);

        flamberge.setData(DataComponentTypes.DAMAGE, 11);
        AttributeModifier modifier = new AttributeModifier(new NamespacedKey(NamespacedKey.MINECRAFT, AttributeKeys.ARMOR.value()), 4.0d, AttributeModifier.Operation.ADD_NUMBER);
        flamberge.setData(DataComponentTypes.ATTRIBUTE_MODIFIERS, ItemAttributeModifiers.itemAttributes().addModifier(Attribute.ARMOR, modifier));
        return flamberge;
    }

    public static ItemStack LEGIONNAIRE_FLAMBERGE() {
        ItemStack legionnaire_flamberge = new ItemStack(Material.NETHERITE_SWORD);
        ItemMeta itemMeta = legionnaire_flamberge.getItemMeta();
        itemMeta.displayName(Component.text("Legionnaire Flamberge").color(NamedTextColor.RED));
        legionnaire_flamberge.setItemMeta(itemMeta);

        legionnaire_flamberge.setData(DataComponentTypes.DAMAGE, 11);

        AttributeModifier modifier = new AttributeModifier(new NamespacedKey(NamespacedKey.MINECRAFT, AttributeKeys.ARMOR.value()), 4.0d, AttributeModifier.Operation.ADD_NUMBER);
        legionnaire_flamberge.setData(DataComponentTypes.ATTRIBUTE_MODIFIERS, ItemAttributeModifiers.itemAttributes().addModifier(Attribute.ARMOR, modifier));

        modifier = new AttributeModifier(new NamespacedKey(NamespacedKey.MINECRAFT, AttributeKeys.ENTITY_INTERACTION_RANGE.value()), 2, AttributeModifier.Operation.ADD_NUMBER);
        legionnaire_flamberge.setData(DataComponentTypes.ATTRIBUTE_MODIFIERS, ItemAttributeModifiers.itemAttributes().addModifier(Attribute.ENTITY_INTERACTION_RANGE, modifier));

        return legionnaire_flamberge;
    }

    public static ItemStack DECREPIT_SCYTHE() {
        ItemStack decrepitScythe = new ItemStack(Material.NETHERITE_SWORD);
        ItemMeta itemMeta = decrepitScythe.getItemMeta();
        itemMeta.displayName(Component.text("Decrepit Scythe").color(NamedTextColor.YELLOW));
        decrepitScythe.setItemMeta(itemMeta);

        decrepitScythe.setData(DataComponentTypes.DAMAGE, 11);

        AttributeModifier modifier = new AttributeModifier(new NamespacedKey(NamespacedKey.MINECRAFT, AttributeKeys.ATTACK_SPEED.value()), -0.2, AttributeModifier.Operation.ADD_NUMBER);
        decrepitScythe.setData(DataComponentTypes.ATTRIBUTE_MODIFIERS, ItemAttributeModifiers.itemAttributes().addModifier(Attribute.ENTITY_INTERACTION_RANGE, modifier));

        return decrepitScythe;
    }

    public static ItemStack HELLRAZOR() {
        ItemStack hellrazor = new ItemStack(Material.NETHERITE_SWORD);
        ItemMeta itemMeta = hellrazor.getItemMeta();
        itemMeta.displayName(Component.text("Hellrazor").color(NamedTextColor.RED));
        hellrazor.setItemMeta(itemMeta);

        hellrazor.setData(DataComponentTypes.DAMAGE, 11);

        AttributeModifier modifier = new AttributeModifier(new NamespacedKey(NamespacedKey.MINECRAFT, AttributeKeys.ARMOR.value()), 4.0d, AttributeModifier.Operation.ADD_NUMBER);
        hellrazor.setData(DataComponentTypes.ATTRIBUTE_MODIFIERS, ItemAttributeModifiers.itemAttributes().addModifier(Attribute.ARMOR, modifier));

        modifier = new AttributeModifier(new NamespacedKey(NamespacedKey.MINECRAFT, AttributeKeys.ENTITY_INTERACTION_RANGE.value()), 2, AttributeModifier.Operation.ADD_NUMBER);
        hellrazor.setData(DataComponentTypes.ATTRIBUTE_MODIFIERS, ItemAttributeModifiers.itemAttributes().addModifier(Attribute.ENTITY_INTERACTION_RANGE, modifier));

        modifier = new AttributeModifier(new NamespacedKey(NamespacedKey.MINECRAFT, AttributeKeys.ATTACK_SPEED.value()), -0.2, AttributeModifier.Operation.ADD_NUMBER);
        hellrazor.setData(DataComponentTypes.ATTRIBUTE_MODIFIERS, ItemAttributeModifiers.itemAttributes().addModifier(Attribute.ENTITY_INTERACTION_RANGE, modifier));

        return hellrazor;
    }

    /*public static ItemStack DIVER_HELMET(){
        ItemStack item = new ItemStack(Material.GLASS);

        ItemMeta meta = item.getItemMeta();
        meta.displayName(Component.text("Diver").color(NamedTextColor.AQUA::value));
        item.setItemMeta(meta);


        Equippable.Builder builder = new ItemStack(Material.DIAMOND_HELMET).getData(DataComponentTypes.EQUIPPABLE).toBuilder();
        builder.equipSound(SoundEventKeys.ENTITY_GHAST_HURT);
        builder.damageOnHurt(true);
        item.setData(DataComponentTypes.EQUIPPABLE, builder.build());

        return item;
    }*/

}
