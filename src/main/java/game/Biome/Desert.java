package game.Biome;

import game.Map.MapTileType;

import java.util.HashMap;
import java.util.Map;

public class Desert implements IBiomeProbabilities {

    private Biome biome;

    public Desert() {
        biome = new Biome(createTileProbabilities());
    }

    public MapTileType getBiomeTile(int i) {
        return biome.getTileType(i);
    }

    public Map<MapTileType, Integer> createTileProbabilities() {
        Map<MapTileType, Integer> tileTypeProbability = new HashMap<>();
        tileTypeProbability.put(MapTileType.WATER, 1);
        tileTypeProbability.put(MapTileType.SAND, 90);
        tileTypeProbability.put(MapTileType.STONE, 9);

        return tileTypeProbability;
    }
}
