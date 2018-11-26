package game.Map;

import game.IRender;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Predicate;
import javafx.scene.layout.Pane;

public class Map implements IRender {

    public ArrayList<MapChunk> mapChunks;
    private MapChunkFactory mapChunkFactory;

    public Map(MapChunkFactory mapChunkFactory) {
        this.mapChunkFactory = mapChunkFactory;
        this.mapChunks = new ArrayList<>();
        this.mapChunks.add(mapChunkFactory.newPlainsChunk(0, 0));
    }

    private MapChunk createChunk(double minX, double minY) {
        int randomNum = ThreadLocalRandom.current().nextInt(0, 2);

        if (randomNum == 0) {
            return mapChunkFactory.newPlainsChunk(minX, minY);
        } else {
            return mapChunkFactory.newDesert(minX, minY);
        }

    }


    public void render(Pane gameScreen) {
        for (MapChunk mapChunk : mapChunks) {
            for (int currentObstruction = 0; currentObstruction < mapChunk.getObstructionCount(); currentObstruction++) {
                Obstruction obstruction = mapChunk.getTile(currentObstruction);

                if (!gameScreen.getChildren().contains(obstruction.getNode())) {
                    obstruction.getNode().setTranslateX(obstruction.getBounds().getX());
                    obstruction.getNode().setTranslateY(obstruction.getBounds().getY());
                    gameScreen.getChildren().add(obstruction.getNode());
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
        Predicate<MapChunk> isInChunk = mapChunk -> isInBounds(mapChunk.getMinX(), playerX, mapChunk.getMaxX())
                && isInBounds(mapChunk.getMinY(), playerY, mapChunk.getMaxY());
        return mapChunks
                .parallelStream()
                .anyMatch(isInChunk);
    }
}
