package sample;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class MapTile extends Obstruction {
    public MapTile(boolean obstruction, double x, double y, double width, double height, Color color) {
        super(obstruction, x, y, width, height);
        this.setNode(new Rectangle(width, height, color));
        this.getNode().setTranslateX(x);
        this.getNode().setTranslateY(y);
    }


}
