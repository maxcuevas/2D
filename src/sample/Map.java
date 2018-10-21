package sample;

import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class Map implements IRender {

    public ArrayList<Biome> biomes;


    public Map() {
        this.biomes = new ArrayList<>();
        this.biomes.add(createMap());
    }

    private Biome createMap() {
        return new Biome(-150, -150, Biome.BiomeType.PLAIN);
    }


    public void render(Pane gameScreen) {
        for (Biome biome : biomes) {
            for (int currentTile = 0; currentTile < biome.getBiomeSize(); currentTile++) {
                biome.getTile(currentTile).getNode().setTranslateX(biome.getTile(currentTile).getBounds().getX());
                biome.getTile(currentTile).getNode().setTranslateY(biome.getTile(currentTile).getBounds().getY());
                gameScreen.getChildren().add(biome.getTile(currentTile).getNode());
            }
        }
    }

    @Override
    public void update() {

    }

}
