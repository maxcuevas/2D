package game;

import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Map implements IRender {

    public ArrayList<MapChunk> mapChunks;


    public Map() {
        this.mapChunks = new ArrayList<>();
        this.mapChunks.addAll(createMap());
    }

    private List<MapChunk> createMap() {

        List<MapChunk> mapChunks = new ArrayList<>();

        mapChunks.add(new MapChunk(-10, -10, MapChunk.BiomeType.PLAIN));
//        mapChunks.add(new MapChunk(-150 + (15 * 25), -150, MapChunk.BiomeType.DESERT));

        return mapChunks;
    }

    private MapChunk createChunk(double minX, double minY) {
        return new MapChunk(minX, minY, MapChunk.BiomeType.PLAIN);
    }


    public void render(Pane gameScreen) {
        for (MapChunk mapChunk : mapChunks) {
            for (int currentTile = 0; currentTile < mapChunk.getBiomeSize(); currentTile++) {
//                mapChunk.getTile(currentTile).getNode().setTranslateX(mapChunk.getTile(currentTile).getBounds().getX());
//                mapChunk.getTile(currentTile).getNode().setTranslateY(mapChunk.getTile(currentTile).getBounds().getY());
//                gameScreen.getChildren().add(mapChunk.getTile(currentTile).getNode());


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

    public boolean updateMap(double playerX, double playerY) {
        return !isInValidChunk(playerX, playerY) &&
                (isXAddition(playerX, getMinY(playerY)) || isYAddition(playerY, getMinX(playerX)));
    }

    private boolean isXAddition(double playerX, OptionalDouble minY) {
        boolean result = false;
        if (minY.isPresent()) {
            double finalMinY = minY.getAsDouble();
            List<MapChunk> chunksOnY = getChunksOnY(finalMinY);

            result |= isPlayerPastX(playerX, finalMinY, chunksOnY);
            result |= isPlayerBeforeX(playerX, finalMinY, chunksOnY);

        }
        return result;
    }

    private boolean isYAddition(double playerY, OptionalDouble minX) {
        boolean result = false;
        if (minX.isPresent()) {
            double finalMinX = minX.getAsDouble();
            List<MapChunk> chunksOnX = getChunksOnX(finalMinX);

            result |= isPlayerPastY(playerY, finalMinX, chunksOnX);
            result |= isPlayerBeforeY(playerY, finalMinX, chunksOnX);

        }
        return result;
    }

    private boolean isPlayerBeforeX(double playerX, double finalMinY, List<MapChunk> chunksOnY) {
        OptionalDouble minX = chunksOnY.parallelStream().mapToDouble(x -> x.getMinX()).min();
        if (minX.isPresent() && Double.compare(minX.getAsDouble(), playerX) == 1) {
            mapChunks.add(createChunk(minX.getAsDouble() - mapChunks.get(0).getBiomeWidth(), finalMinY));
            return true;
        }
        return false;
    }

    private boolean isPlayerBeforeY(double playerY, double finalMinX, List<MapChunk> chunksOnX) {
        OptionalDouble minY = chunksOnX.parallelStream().mapToDouble(x -> x.getMinY()).min();
        if (minY.isPresent() && Double.compare(minY.getAsDouble(), playerY) == 1) {
            mapChunks.add(createChunk(finalMinX, minY.getAsDouble() - mapChunks.get(0).getBiomeWidth()));
            return true;
        }
        return false;
    }

    private boolean isPlayerPastX(double playerX, double finalMinY, List<MapChunk> chunksOnY) {
        OptionalDouble maxX = chunksOnY.parallelStream().mapToDouble(x -> x.getMaxX()).max();
        if (maxX.isPresent() && Double.compare(maxX.getAsDouble(), playerX) == -1) {
            mapChunks.add(createChunk(maxX.getAsDouble(), finalMinY));
            return true;
        }
        return false;
    }

    private boolean isPlayerPastY(double playerY, double finalMinX, List<MapChunk> chunksOnX) {
        OptionalDouble maxY = chunksOnX.parallelStream().mapToDouble(x -> x.getMaxY()).max();
        if (maxY.isPresent() && Double.compare(maxY.getAsDouble(), playerY) == -1) {
            mapChunks.add(createChunk(finalMinX, maxY.getAsDouble()));
            return true;
        }
        return false;
    }

    private OptionalDouble getMinY(double playerY) {
        return mapChunks
                .parallelStream()
                .filter(x -> x.getMinY() < playerY && x.getMaxY() > playerY)
                .mapToDouble(x -> x.getMinY()).findFirst();
    }

    private OptionalDouble getMinX(double playerX) {
        return mapChunks
                .parallelStream()
                .filter(x -> x.getMinX() < playerX && x.getMaxX() > playerX)
                .mapToDouble(x -> x.getMinX()).findFirst();
    }

    private List<MapChunk> getChunksOnY(double min) {
        return mapChunks
                .parallelStream()
                .filter(x -> Double.compare(min, x.getMinY()) == 0)
                .collect(Collectors.toList());
    }

    private List<MapChunk> getChunksOnX(double min) {
        return mapChunks
                .parallelStream()
                .filter(x -> Double.compare(min, x.getMinX()) == 0)
                .collect(Collectors.toList());
    }

    private boolean isInValidChunk(double playerX, double playerY) {
        Predicate<MapChunk> isInChunk = x -> x.getMinX() < playerX && x.getMinY() < playerY
                && x.getMaxX() > playerX && x.getMaxY() > playerY;
        return mapChunks.parallelStream().anyMatch(isInChunk);
    }
}
