package game.Biome;

import game.Map.MapTile;

public interface IBiomeProbabilities {

    java.util.Map<MapTile.TileType, Integer> createTileProbabilities();

    MapTile.TileType getBiomeTile(int i);

}
