package sample;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class MapTile extends Obstruction {

    Rectangle mapTile;


    public MapTile(double width, double height, Color color) {
        mapTile = new Rectangle(width, height, color);
    }

}
