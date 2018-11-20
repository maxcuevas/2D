package game.Biome;

import game.Map.MapTile;

import java.util.HashMap;
import java.util.Map;

public class Desert implements IBiomeProbabilities {

    private Biome biome;

    public Desert() {
        biome = new Biome(createTileProbabilities());
    }

    public MapTile.TileType getBiomeTile(int i) {
        return biome.getTileType(i);
    }

    public Map<MapTile.TileType, Integer> createTileProbabilities() {
        Map<MapTile.TileType, Integer> tileTypeProbability = new HashMap<>();
        tileTypeProbability.put(MapTile.TileType.WATER, 1);
        tileTypeProbability.put(MapTile.TileType.SAND, 90);
        tileTypeProbability.put(MapTile.TileType.STONE, 9);

        return tileTypeProbability;
    }
}
