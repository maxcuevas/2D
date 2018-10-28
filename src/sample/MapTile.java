package sample;

import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;

public class MapTile extends Obstruction {


    Rectangle tile;

    public MapTile(boolean obstruction, double x, double y, double width, double height, TileType tileType) {
        super(obstruction, x, y, width, height);
        createTile(width, height, tileType);
        this.setNode(tile);
        this.getNode().setTranslateX(x);
        this.getNode().setTranslateY(y);
    }

    private void createTile(double width, double height, TileType tileType) {
        tile = new Rectangle(width, height);
        tile.setFill(getTileColor(tileType));
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

    enum TileType {
        GRASS, STONE, DIRT, WATER, SAND, UNKNOWN
    }


}
