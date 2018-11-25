package game.Map;

import game.Biome.BiomeFactory;
import game.Biome.BiomeType;

public class MapChunkFactory {

    private final int tileLength = 15;
    private final int chunkLength = 5;
    private BiomeFactory biomeFactory;

    public MapChunkFactory(BiomeFactory biomeFactory) {
        this.biomeFactory = biomeFactory;
    }

    public MapChunkData create(double minX, double minY, BiomeType biomeType) {
        return new MapChunkData(minX, minY, biomeFactory.createBiome(biomeType, minX, minY, chunkLength, tileLength));
    }


}
