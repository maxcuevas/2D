package game.Map;

import game.Biome.BiomeType;
import game.IRender;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Predicate;

public class Map implements IRender {

    public ArrayList<MapChunkData> mapChunks;
    private MapChunkFactory mapChunkFactory;

    public Map(MapChunkFactory mapChunkFactory) {
        this.mapChunkFactory = mapChunkFactory;
        this.mapChunks = new ArrayList<>();
        this.mapChunks.addAll(createMap());
    }

    private List<MapChunkData> createMap() {

        List<MapChunkData> mapChunkFactories = new ArrayList<>();

        mapChunkFactories.add(mapChunkFactory.create(0, 0, BiomeType.PLAIN));

        return mapChunkFactories;
    }

    private MapChunkData createChunk(double minX, double minY) {

        int randomNum = ThreadLocalRandom.current().nextInt(0, 2);

        if(randomNum == 0){
            return mapChunkFactory.create(minX, minY, BiomeType.PLAIN);
        }else{
            return mapChunkFactory.create(minX, minY, BiomeType.DESERT);
        }

    }

    public void render(Pane gameScreen) {
        for (MapChunkData mapChunk : mapChunks) {
            for (int currentTile = 0; currentTile < mapChunk.getTileCount(); currentTile++) {
                if (!gameScreen.getChildren().contains(mapChunk.getTile(currentTile).getNode())) {
                    mapChunk.getTile(currentTile).getNode().setTranslateX(mapChunk.getTile(currentTile).getBounds().getX());
                    mapChunk.getTile(currentTile).getNode().setTranslateY(mapChunk.getTile(currentTile).getBounds().getY());
                    gameScreen.getChildren().add(mapChunk.getTile(currentTile).getNode());
                }
            }

        }
    }

    @Override
    public void update() {

    }

    public boolean doesMapNeedUpdate(double playerX, double playerY) {
        if (!isInValidChunk(playerX, playerY)) {
            mapChunks.add(
                    createChunk(
                            getNewMapPosition(playerX, mapChunks.get(0).getSideLength()),
                            getNewMapPosition(playerY, mapChunks.get(0).getSideLength())));
            return true;

        }
        return false;
    }

    private int getNewMapPosition(double position, int size) {
        if (position < 0) {
            return ((int) (position / size) - 1) * size;
        } else {
            return (int) (position / size) * size;
        }
    }

    private boolean isInBounds(double low, double position, double high) {
        return low < position && position < high;
    }

    private boolean isInValidChunk(double playerX, double playerY) {
        Predicate<MapChunkData> isInChunk = mapChunk -> isInBounds(mapChunk.getMinX(), playerX, mapChunk.getMaxX())
                && isInBounds(mapChunk.getMinY(), playerY, mapChunk.getMaxY());
        return mapChunks
                .parallelStream()
                .anyMatch(isInChunk);
    }
}
