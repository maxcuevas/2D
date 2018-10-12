package sample;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Wood extends Obstruction {
    public Wood(boolean isObstruction, double width, double height, Color color) {
        super(isObstruction);
        this.setNode(new Rectangle(width, height, color));
    }
}
