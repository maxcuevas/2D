package game.Map;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;

import java.awt.geom.Rectangle2D;

public class MapTileFactory implements Obstruction {

    MapTileData mapTileData;

    public MapTileFactory(boolean obstruction, double x, double y, double width, double height, MapTileType mapTileType) {
        ObstructionImpl obstruction1mpl = new ObstructionImpl(obstruction, new Rectangle2D.Double(x, y, width, height), createTile(width, height, mapTileType));
        mapTileData = new MapTileData(obstruction1mpl);
    }

    private Rectangle createTile(double width, double height, MapTileType mapTileType) {
        Rectangle tile = new Rectangle(width, height);
        tile.setFill(mapTileType.getColor());
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
}
