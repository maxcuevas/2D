package sample;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class MapTile extends Obstruction {
    public MapTile(double width, double height, Color color) {
        super(true);
        this.setNode(new Rectangle(width, height, color));
    }


}
