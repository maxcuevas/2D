package game.Biome;

import game.Map.MapTileType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Biome {

    List<MapTileType> tileLookUp;

    public Biome(Map<MapTileType, Integer> tileTypeProbabilities) {
        tileLookUp = createTileLookUp(tileTypeProbabilities);
    }

    public MapTileType getTileType(int i) {
        return tileLookUp.get(i);
    }

    private List<MapTileType> createTileLookUp(java.util.Map<MapTileType, Integer> tileTypeProbabilities) {

        List<MapTileType> tileLookUp = new ArrayList<>();

        for (Map.Entry<MapTileType, Integer> tileTypeProbability : tileTypeProbabilities.entrySet()) {
            for (int i = 0; i < tileTypeProbability.getValue(); i++) {
                tileLookUp.add(tileTypeProbability.getKey());
            }
        }

        return tileLookUp;
    }


}
