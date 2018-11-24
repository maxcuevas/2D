package game.Map;

import game.Biome.Desert;
import game.Biome.IBiomeProbabilities;
import game.Biome.Plain;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MapChunk {
    private final int tileWidth = 15;
    private final int tileHeight = 15;
    private final int biomeWidthCount = 5;
    private final int biomeLengthCount = 5;
    private double minX;
    private double minY;
    private double maxX;
    private double maxY;
    private boolean inRange;
    private List<Obstruction> chunk;

    public MapChunk(double minX, double minY, BiomeType biomeType) {
        this.minX = minX;
        this.minY = minY;
        this.maxX = minX + (tileWidth * biomeWidthCount);
        this.maxY = minY + (tileHeight * biomeLengthCount);
        this.chunk = createBiome(biomeType);
        this.inRange = true;
    }

    public int getBiomeWidth() {
        return tileWidth * biomeWidthCount;
    }

    public int getBiomeHeight() {
        return tileHeight * biomeLengthCount;
    }

    public boolean isInRange() {
        return inRange;
    }

    public void setInRange(boolean inRange) {
        this.inRange = inRange;
    }

    public double getMinX() {
        return minX;
    }

    public double getMinY() {
        return minY;
    }

    public double getMaxX() {
        return maxX;
    }

    public double getMaxY() {
        return maxY;
    }

    private List<Obstruction> createBiome(BiomeType biomeType) {


        if (biomeType.equals(BiomeType.PLAIN)) {
            return getNewBiome(new Plain());
        }

        return getNewBiome(new Desert());

    }

    private List<Obstruction> getNewBiome(IBiomeProbabilities iBiomeProbabilities) {
        List<List<Obstruction>> listOfChunkRows = IntStream
                .range(0, biomeLengthCount)
                .mapToObj(row -> getNewChunkRow(iBiomeProbabilities, row))
                .collect(Collectors.toList());

        return listOfChunkRows
                .stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    private List<Obstruction> getNewChunkRow(IBiomeProbabilities iBiomeProbabilities, int row) {
        int[] randomNumbers = new Random()
                .ints(biomeWidthCount, 0, 100)
                .toArray();

        return IntStream
                .range(0, biomeWidthCount)
                .mapToObj(count -> new MapTileFactory(false,
                        minX + tileWidth * row,
                        minY + tileHeight * count,
                        tileWidth, tileHeight,
                        iBiomeProbabilities.getBiomeTile(randomNumbers[count])))
                .collect(Collectors.toList());
    }

    public Obstruction getTile(int id) {
        return chunk.get(id);
    }

    public List<Obstruction> getChunk() {
        return chunk;
    }


    public int getBiomeSize() {
        return chunk.size();
    }

    enum BiomeType {
        PLAIN, DESERT
    }

}
