package sample;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;


public class Stone {

    private Obstruction obstruction = new Obstruction(false);
    Circle stone = new Circle(3, Color.GRAY);

    public Obstruction getObstruction() {
        return obstruction;
    }
}
