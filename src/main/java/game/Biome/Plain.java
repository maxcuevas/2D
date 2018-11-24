package game.Biome;

import game.Map.MapTileType;

import java.util.HashMap;
import java.util.Map;

public class Plain implements IBiomeProbabilities {

    private Biome biome;

    public Plain() {
        biome = new Biome(createTileProbabilities());
    }

    public MapTileType getBiomeTile(int i) {
        return biome.getTileType(i);
    }

    public Map<MapTileType, Integer> createTileProbabilities() {
        Map<MapTileType, Integer> tileTypeProbability = new HashMap<>();
        tileTypeProbability.put(MapTileType.GRASS, 90);
        tileTypeProbability.put(MapTileType.STONE, 5);
        tileTypeProbability.put(MapTileType.DIRT, 5);

        return tileTypeProbability;
    }
}
