package game.Biome;

import game.Map.MapTile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Biome {

    List<MapTile.TileType> tileLookUp;

    public Biome(Map<MapTile.TileType, Integer> tileTypeProbabilities) {
        tileLookUp = createTileLookUp(tileTypeProbabilities);
    }

    public MapTile.TileType getTileType(int i) {
        return tileLookUp.get(i);
    }

    private List<MapTile.TileType> createTileLookUp(java.util.Map<MapTile.TileType, Integer> tileTypeProbabilities) {

        List<MapTile.TileType> tileLookUp = new ArrayList<>();

        for (Map.Entry<MapTile.TileType, Integer> tileTypeProbability : tileTypeProbabilities.entrySet()) {
            for (int i = 0; i < tileTypeProbability.getValue(); i++) {
                tileLookUp.add(tileTypeProbability.getKey());
            }
        }

        return tileLookUp;
    }


}
