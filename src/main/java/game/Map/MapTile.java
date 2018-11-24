package game.Map;

import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;

public class MapTile extends Obstruction {


    Rectangle tile;

    public MapTile(boolean obstruction, double x, double y, double width, double height, MapTileType mapTileType) {
        super(obstruction, x, y, width, height);
        createTile(width, height, mapTileType);
        this.setNode(tile);
        this.getNode().setTranslateX(x);
        this.getNode().setTranslateY(y);
    }

    private void createTile(double width, double height, MapTileType mapTileType) {
        tile = new Rectangle(width, height);
        tile.setFill(mapTileType.getColor());
        tile.setStroke(Color.BLACK);
        tile.setStrokeType(StrokeType.INSIDE);
        tile.setStrokeWidth(0);
        setEvents();
    }

    private void setEvents() {
        tile.addEventHandler(MouseEvent.MOUSE_ENTERED,
                (event) -> {
                    tile.setStrokeWidth(2);
                });

        tile.addEventHandler(MouseEvent.MOUSE_EXITED,
                (event) -> {
                    tile.setStrokeWidth(0);
                });


        tile.addEventHandler(MouseEvent.MOUSE_CLICKED,
                (event) -> {
                    tile.setFill(Color.BROWN);
                });
    }




}
