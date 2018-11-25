package game.Biome;

import game.Map.MapTileType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Biome {

    List<MapTileType> mapTileTypeLookUp;

    public Biome(Map<MapTileType, Integer> tileTypeProbabilities) {
        mapTileTypeLookUp = createTileLookUp(tileTypeProbabilities);
    }

    public MapTileType getTileType(int i) {
        return mapTileTypeLookUp.get(i);
    }

    private List<MapTileType> createTileLookUp(java.util.Map<MapTileType, Integer> tileTypeProbabilities) {
        List<MapTileType> tileLookUp = new ArrayList<>();

        for (Map.Entry<MapTileType, Integer> tileTypeProbability : tileTypeProbabilities.entrySet()) {
            tileLookUp.addAll(
                    IntStream
                            .range(0, tileTypeProbability.getValue())
                            .mapToObj(i -> tileTypeProbability.getKey())
                            .collect(Collectors.toList()));
        }


        return tileLookUp;
    }


}
