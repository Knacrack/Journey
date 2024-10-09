package de.knacrack.journey.utility;

public class BlockSch {
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
}
