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
    private double minX;
    private double minY;
    private double maxX;
    private double maxY;
    private List<Obstruction> chunk;

    public MapChunk(double minX, double minY, BiomeType biomeType, MapTileFactory mapTileFactory, BiomeFactory biomeFactory) {

        this.mapTileFactory = mapTileFactory;
        this.biomeFactory = biomeFactory;
        mapChunkData = new MapChunkData(minX, minY, createBiome(biomeType));

        this.minX = minX;
        this.minY = minY;
        this.maxX = minX + (tileLength * chunkLength);
        this.maxY = minY + (tileLength * chunkLength);
        this.chunk = createBiome(biomeType);


    }


    private List<Obstruction> createBiome(BiomeType biomeType) {


        if (biomeType.equals(BiomeType.PLAIN)) {
            return getNewBiome(new Plain());
        }

        return getNewBiome(new Desert());

    }

    private List<Obstruction> getNewBiome(IBiomeProbabilities iBiomeProbabilities) {
        List<List<Obstruction>> listOfChunkRows = IntStream
                .range(0, chunkLength)
                .mapToObj(row -> getNewChunkRow(iBiomeProbabilities, row))
//                .mapToObj(row -> biomeFactory.getNewChunkRow(iBiomeProbabilities, row,chunkLength,
//                        minX + tileLength,minY + tileLength,tileLength))
                .collect(Collectors.toList());

        return listOfChunkRows
                .stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    private List<Obstruction> getNewChunkRow(IBiomeProbabilities iBiomeProbabilities, int row) {
        int[] randomNumbers = new Random()
                .ints(chunkLength, 0, 100)
                .toArray();

        return IntStream
                .range(0, chunkLength)
                .mapToObj(count -> mapTileFactory.create(false,
                        minX + tileLength * row,
                        minY + tileLength * count,
                        tileLength,
                        iBiomeProbabilities.getBiomeTile(randomNumbers[count])))
                .collect(Collectors.toList());
    }

    public Obstruction getTile(int id) {
        return chunk.get(id);
    }

    public List<Obstruction> getChunk() {
        return chunk;
    }


}
