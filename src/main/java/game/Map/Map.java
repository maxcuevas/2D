package game.Map;

import game.Biome.BiomeFactory;
import game.Biome.BiomeType;
import game.IRender;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Predicate;

public class Map implements IRender {

    public ArrayList<MapChunk> mapChunks;

    public Map() {
        this.mapChunks = new ArrayList<>();
        this.mapChunks.addAll(createMap());
    }

    private List<MapChunk> createMap() {

        List<MapChunk> mapChunks = new ArrayList<>();

        mapChunks.add(new MapChunk(0, 0, BiomeType.PLAIN, new MapTileFactory(), new BiomeFactory(new MapTileFactory())));

        return mapChunks;
    }

    private MapChunk createChunk(double minX, double minY) {

        int randomNum = ThreadLocalRandom.current().nextInt(0, 2);

        if(randomNum == 0){
            return new MapChunk(minX, minY, BiomeType.PLAIN, new MapTileFactory(), new BiomeFactory(new MapTileFactory()));
        }else{
            return new MapChunk(minX, minY, BiomeType.DESERT, new MapTileFactory(), new BiomeFactory(new MapTileFactory()));
        }

    }

    public void render(Pane gameScreen) {
        for (MapChunk mapChunk : mapChunks) {
            for (int currentTile = 0; currentTile < mapChunk.getBiomeSize(); currentTile++) {
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
                            getNewMapPosition(playerX, mapChunks.get(0).getBiomeWidth()),
                            getNewMapPosition(playerY, mapChunks.get(0).getBiomeHeight())));
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
        Predicate<MapChunk> isInChunk = mapChunk -> isInBounds(mapChunk.getMinX(), playerX, mapChunk.getMaxX())
                && isInBounds(mapChunk.getMinY(), playerY, mapChunk.getMaxY());
        return mapChunks
                .parallelStream()
                .anyMatch(isInChunk);
    }
}
