package game.Map;

import game.Biome.*;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MapChunk {

    public MapChunkData mapChunkData;
    private BiomeFactory biomeFactory;

    private MapTileFactory mapTileFactory;

    private final int tileLength = 15;
    private final int chunkLength = 5;

    public MapChunk(double minX, double minY, BiomeType biomeType, MapTileFactory mapTileFactory, BiomeFactory biomeFactory) {
        this.mapTileFactory = mapTileFactory;
        this.biomeFactory = biomeFactory;
        mapChunkData = new MapChunkData(minX, minY, createBiome(biomeType,minX,minY));
    }

    private List<Obstruction> createBiome(BiomeType biomeType, double minX, double minY) {
        if (biomeType.equals(BiomeType.PLAIN)) {
            return getNewBiome(new Plain(), minX, minY);
        }
        return getNewBiome(new Desert(), minX, minY);
    }

    private List<Obstruction> getNewBiome(IBiomeProbabilities iBiomeProbabilities, double minX, double minY) {
        List<List<Obstruction>> listOfChunkRows = IntStream
                .range(0, chunkLength)
                .mapToObj(row -> biomeFactory.getNewChunkRow(iBiomeProbabilities, row,chunkLength,
                        minX,minY,tileLength))
                .collect(Collectors.toList());

        return listOfChunkRows
                .stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

//    private List<Obstruction> getNewChunkRow(IBiomeProbabilities iBiomeProbabilities, int row, double minX, double minY) {
//        int[] randomNumbers = new Random()
//                .ints(chunkLength, 0, 100)
//                .toArray();
//
//        return IntStream
//                .range(0, chunkLength)
//                .mapToObj(count -> mapTileFactory.create(false,
//                        minX + (tileLength * row),
//                        minY + (tileLength * count),
//                        tileLength,
//                        iBiomeProbabilities.getBiomeTile(randomNumbers[count])))
//                .collect(Collectors.toList());
//    }

}
