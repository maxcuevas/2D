package sample;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;


public class Stone extends Obstruction {
    public Stone(boolean isObstruction, double radius, Color color) {
        super(isObstruction);
        this.setNode(new Circle(radius, color));
    }

}
