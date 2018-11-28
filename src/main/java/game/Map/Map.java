package game.Map;

import game.IRender;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Map implements IRender {

    public List<MapChunk> mapChunks;
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
            return mapChunkFactory.newDesertChunk(minX, minY);
        }

    }

    public void render(Pane gameScreen) {
        mapChunks.forEach(mapChunk -> updateRenderedMapTiles(gameScreen, mapChunk));
        mapChunks.forEach(mapChunk -> updateRenderedItems(gameScreen, mapChunk));

    }

    private void updateRenderedMapTiles(Pane gameScreen, MapChunk mapChunk) {
        List<Obstruction> newMapTiles = getNewMapTiles(gameScreen, mapChunk);
        newMapTiles.forEach(this::setObstructionNodePosition);
        gameScreen.getChildren().addAll(getObstructions(newMapTiles));
    }

    private void updateRenderedItems(Pane gameScreen, MapChunk mapChunk) {
        List<Obstruction> newItems = getNewMapItems(gameScreen, mapChunk);
        newItems.forEach(this::setObstructionNodePosition);
        gameScreen.getChildren().addAll(getObstructions(newItems));
    }

    @NotNull
    private List<Node> getObstructions(List<Obstruction> newObstructions) {
        return newObstructions.stream()
                .map(Obstruction::getNode)
                .collect(Collectors.toList());
    }

    @NotNull
    private List<Obstruction> getNewMapTiles(Pane gameScreen, MapChunk mapChunk) {
        return mapChunk.getMapTiles()
                .stream()
                .filter(mapTile -> !doesGameScreenContain(gameScreen, mapTile))
                .collect(Collectors.toList());
    }

    @NotNull
    private List<Obstruction> getNewMapItems(Pane gameScreen, MapChunk mapChunk) {
        return mapChunk.getItems()
                .stream()
                .filter(item -> !doesGameScreenContain(gameScreen, item))
                .collect(Collectors.toList());
    }

    private void setObstructionNodePosition(Obstruction obstruction) {
        obstruction.getNode().setTranslateX(obstruction.getBounds().getX());
        obstruction.getNode().setTranslateY(obstruction.getBounds().getY());
    }

    private boolean doesGameScreenContain(Pane gameScreen, Obstruction obstruction) {
        return gameScreen.getChildren().contains(obstruction.getNode());
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
