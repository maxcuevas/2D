package game.Map;

import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;

import java.awt.geom.Rectangle2D;

public class MapTile implements IObstruction {

    MapTileData mapTileData;

    public MapTile(boolean obstruction, double x, double y, double width, double height, TileType tileType) {
        mapTileData = new MapTileData(obstruction, x, y, createTile(width, height, tileType));
    }

    private Rectangle createTile(double width, double height, TileType tileType) {
        Rectangle tile = new Rectangle(width, height);
        tile.setFill(getTileColor(tileType));
        tile.setStroke(Color.BLACK);
        tile.setStrokeType(StrokeType.INSIDE);
        tile.setStrokeWidth(0);
        setEvents();
        return tile;
    }

    private void setEvents() {
//        tile.addEventHandler(MouseEvent.MOUSE_ENTERED,
//                (event) -> {
//                    tile.setStrokeWidth(2);
//                });
//
//        tile.addEventHandler(MouseEvent.MOUSE_EXITED,
//                (event) -> {
//                    tile.setStrokeWidth(0);
//                });
//
//
//        tile.addEventHandler(MouseEvent.MOUSE_CLICKED,
//                (event) -> {
//                    tile.setFill(Color.BROWN);
//                });
    }

    private Color getTileColor(TileType tileType) {
        switch (tileType) {
            case GRASS:
                return Color.GREEN;
            case STONE:
                return Color.GRAY;
            case DIRT:
                return Color.BROWN;
            case WATER:
                return Color.BLUE;
            case SAND:
                return Color.SANDYBROWN;
        }
        return Color.YELLOW;
    }

    @Override
    public boolean isObstruction() {
        return mapTileData.isObstruction();
    }

    @Override
    public Rectangle2D.Double getBounds() {
        return mapTileData.getBounds();
    }

    @Override
    public Node getNode() {
        return mapTileData.getNode();
    }

    public enum TileType {
        GRASS, STONE, DIRT, WATER, SAND, UNKNOWN
    }


}
