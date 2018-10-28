package sample;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class MapChunk {

    private final int tileWidth = 15;
    private final int tileHeight = 15;
    private final int biomeWidthCount = 25;
    private final int biomeLengthCount = 25;
    private double x;
    private double y;
    private ArrayList<Obstruction> chunk;


    public MapChunk(double x, double y, BiomeType biomeType) {
        this.x = x;
        this.y = y;
        this.chunk = createBiome(biomeType);
    }

    private ArrayList<Obstruction> createBiome(BiomeType biomeType) {
        ArrayList<Obstruction> chunk = new ArrayList<>();


        if (biomeType.equals(BiomeType.PLAIN)) {
            Plain plain = new Plain();
            for (int row = 0; row < biomeLengthCount; row++) {
                for (int column = 0; column < biomeWidthCount; column++) {
                    int randomNum = ThreadLocalRandom.current().nextInt(0, 100);
                    chunk.add(new MapTile(false,
                            x + tileWidth * row,
                            y + tileHeight * column,
                            tileWidth, tileHeight,
                            plain.getBiomeTile(randomNum)));
                }
            }
        }
        else if (biomeType.equals(BiomeType.DESERT)) {
            Desert desert = new Desert();
            for (int row = 0; row < biomeLengthCount; row++) {
                for (int column = 0; column < biomeWidthCount; column++) {
                    int randomNum = ThreadLocalRandom.current().nextInt(0, 100);
                    chunk.add(new MapTile(false,
                            x + tileWidth * row,
                            y + tileHeight * column,
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

    public int getBiomeSize() {
        return chunk.size();
    }

    enum BiomeType {
        PLAIN, DESERT
    }

}
