package de.knacrack.journey.utility;

import org.bukkit.Material;
import org.bukkit.block.Block;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BlockSearch {
    // Funktion zur Suche der verbundenen Blöcke
    public static List<Block> findConnectedBlocks(Block startBlock, Set<Material> validMaterials) {
        Set<Block> foundBlocks = new HashSet<>();
        searchBlocks(startBlock, validMaterials, foundBlocks);
        return new ArrayList<>(foundBlocks);
    }

    // Rekursive Methode zum Suchen von Blöcken
    private static void searchBlocks(Block block, Set<Material> validMaterials, Set<Block> foundBlocks) {
        // Block ignorieren, wenn er schon gefunden wurde oder nicht das richtige Material hat
        if (foundBlocks.contains(block) || !validMaterials.contains(block.getType())) {
            return;
        }

        // Block hinzufügen
        foundBlocks.add(block);

        // Alle benachbarten Blöcke (inklusive diagonal) durchsuchen
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                for (int dz = -1; dz <= 1; dz++) {
                    // Den Block in dieser Richtung erhalten
                    Block neighbor = block.getRelative(dx, dy, dz);
                    searchBlocks(neighbor, validMaterials, foundBlocks);
                }
            }
        }
    }

    // Funktion zur Suche der verbundenen Blöcke
    public static List<Block> findConnectedBlocks(Block startBlock, Material validMaterial) {
        Set<Block> foundBlocks = new HashSet<>();
        searchBlocks(startBlock, validMaterial, foundBlocks);
        return new ArrayList<>(foundBlocks);
    }

    private static void searchBlocks(Block block, Material validMaterial, Set<Block> foundBlocks) {
        // Block ignorieren, wenn er schon gefunden wurde oder nicht das richtige Material hat
        if (foundBlocks.contains(block) || validMaterial != block.getType()) {
            return;
        }

        // Block hinzufügen
        foundBlocks.add(block);

        // Alle benachbarten Blöcke (inklusive diagonal) durchsuchen
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                for (int dz = -1; dz <= 1; dz++) {
                    // Den Block in dieser Richtung erhalten
                    Block neighbor = block.getRelative(dx, dy, dz);
                    searchBlocks(neighbor, validMaterial, foundBlocks);
                }
            }
        }
    }
}
