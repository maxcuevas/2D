package game.Biome;

import game.Map.MapTileType;

public interface IBiomeProbabilities {

    java.util.Map<MapTileType, Integer> createTileProbabilities();

    MapTileType getBiomeTile(int i);

}
