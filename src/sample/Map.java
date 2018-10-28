package sample;

import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

public class Map implements IRender {

    public ArrayList<MapChunk> mapChunks;


    public Map() {
        this.mapChunks = new ArrayList<>();
        this.mapChunks.addAll(createMap());
    }

    private List<MapChunk> createMap() {

        List<MapChunk> mapChunks = new ArrayList<>();

        mapChunks.add(new MapChunk(-150, -150, MapChunk.BiomeType.PLAIN));
        mapChunks.add(new MapChunk(-150 + (15 *25), -150, MapChunk.BiomeType.DESERT));

        return mapChunks;
    }


    public void render(Pane gameScreen) {
        for (MapChunk mapChunk : mapChunks) {
            for (int currentTile = 0; currentTile < mapChunk.getBiomeSize(); currentTile++) {
                mapChunk.getTile(currentTile).getNode().setTranslateX(mapChunk.getTile(currentTile).getBounds().getX());
                mapChunk.getTile(currentTile).getNode().setTranslateY(mapChunk.getTile(currentTile).getBounds().getY());
                gameScreen.getChildren().add(mapChunk.getTile(currentTile).getNode());
            }
        }
    }

    @Override
    public void update() {

    }

}
