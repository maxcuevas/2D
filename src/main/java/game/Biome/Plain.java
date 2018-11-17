package game.Biome;

import game.Map.MapTile;

import java.util.HashMap;
import java.util.Map;

public class Plain implements IBiomeProbabilities {

    private Biome biome;

    public Plain() {

        biome = new Biome(createTileProbabilities());

    }

    public MapTile.TileType getBiomeTile(int i) {
        return biome.getTileType(i);
    }

    public Map<MapTile.TileType, Integer> createTileProbabilities() {
        Map<MapTile.TileType, Integer> tileTypeProbability = new HashMap<>();
        tileTypeProbability.put(MapTile.TileType.GRASS, 90);
        tileTypeProbability.put(MapTile.TileType.STONE, 5);
        tileTypeProbability.put(MapTile.TileType.DIRT, 5);

        return tileTypeProbability;
    }
}
