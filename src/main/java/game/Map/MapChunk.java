package game.Map;

import game.Biome.Desert;
import game.Biome.Plain;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

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
        ArrayList<Obstruction> chunk = new ArrayList<>();


        if (biomeType.equals(BiomeType.PLAIN)) {
            for (int row = 0; row < biomeLengthCount; row++) {
                for (int column = 0; column < biomeWidthCount; column++) {
                    int randomNum = ThreadLocalRandom.current().nextInt(0, 100);
                    Plain plain = new Plain();
                    chunk.add(new MapTile(false,
                            minX + tileWidth * row,
                            minY + tileHeight * column,
                            tileWidth, tileHeight,
                            plain.getBiomeTile(randomNum)));
                }
            }
        } else if (biomeType.equals(BiomeType.DESERT)) {
            for (int row = 0; row < biomeLengthCount; row++) {
                for (int column = 0; column < biomeWidthCount; column++) {
                    int randomNum = ThreadLocalRandom.current().nextInt(0, 100);
                    Desert desert = new Desert();
                    chunk.add(new MapTile(false,
                            minX + tileWidth * row,
                            minY + tileHeight * column,
                            tileWidth, tileHeight,
                            desert.getBiomeTile(randomNum)));
                }
            }
        }


        return chunk;
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
