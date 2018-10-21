package sample;

import java.util.ArrayList;

public class Biome {

    private final int tileWidth = 15;
    private final int tileHeight = 15;
    private final int biomeWidthCount = 25;
    private final int biomeLengthCount = 25;
    private double x;
    private double y;
    private ArrayList<Obstruction> chunk;



    public Biome(double x, double y, BiomeType biomeType) {
        this.x = x;
        this.y = y;
        this.chunk = createBiome(biomeType);
    }

    private ArrayList<Obstruction> createBiome(BiomeType biomeType) {
        ArrayList<Obstruction> chunk = new ArrayList<>();

        for (int row = 0; row < biomeLengthCount; row++) {
            for (int column = 0; column < biomeWidthCount; column++) {
                chunk.add(new MapTile(false, -150 + tileWidth * row, -150 + tileHeight * column, tileWidth, tileHeight, MapTile.TileType.WATER));
            }
        }

        return chunk;
    }

    public Obstruction getTile(int id){
        return chunk.get(id);
    }

    public int getBiomeSize(){
        return chunk.size();
    }

    enum BiomeType {
        PLAIN, DESERT
    }

}
