package game.Camera;

import game.Map.Map;
import game.Map.MapChunk;

import java.util.List;

public class ObstructionDrawer {

    private ObstructionVisibility obstructionVisibility;
    private  ObstructionMover obstructionMover;

    public ObstructionDrawer(ObstructionVisibility obstructionVisibility,ObstructionMover obstructionMover){
        this.obstructionVisibility = obstructionVisibility;
        this.obstructionMover = obstructionMover;
    }

    public void drawMap(Map map, Double gameScreenWidth, Double gameScreenHeight, Double offsetAndDeltaY, Double offsetAndDeltaX) {
        drawMapTiles(map.mapChunks,offsetAndDeltaY,offsetAndDeltaX);
        drawItems(map.mapChunks,offsetAndDeltaY,offsetAndDeltaX);
        setMapTileVisibility(map,gameScreenWidth,gameScreenHeight);
        setItemVisibility(map,gameScreenWidth,gameScreenHeight);
    }


    private void setItemVisibility(Map map, Double gameScreenWidth, Double gameScreenHeight) {
        map.mapChunks.forEach(
                mapChunk -> mapChunk.getItems()
                        .forEach(x ->
                                obstructionVisibility.setVisibility(
                                        x.getNode(),
                                        gameScreenWidth,
                                        gameScreenHeight))
        );
    }

    private void setMapTileVisibility(Map map, Double gameScreenWidth, Double gameScreenHeight) {
        map.mapChunks.forEach(
                mapChunk -> mapChunk.getMapTiles()
                        .forEach(x ->
                                obstructionVisibility.setVisibility(
                                        x.getNode(),
                                        gameScreenWidth,
                                        gameScreenHeight))
        );
    }

    private void drawItems(List<MapChunk> mapChunks, Double offsetAndDeltaY, Double offsetAndDeltaX) {
        mapChunks.forEach(
                mapChunk -> mapChunk.getItems()
                        .forEach(item ->
                                obstructionMover.move(
                                        item,
                                        offsetAndDeltaY,
                                        offsetAndDeltaX))
        );
    }

    private void drawMapTiles(List<MapChunk> mapChunks, Double offsetAndDeltaY, Double offsetAndDeltaX) {
        mapChunks.forEach(
                mapChunk -> mapChunk.getMapTiles()
                        .forEach(item ->
                                obstructionMover.move(
                                        item,
                                        offsetAndDeltaY,
                                        offsetAndDeltaX))
        );
    }

}
