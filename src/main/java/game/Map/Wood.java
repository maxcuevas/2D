package game.Map;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Wood extends Obstruction {
    public Wood(boolean isObstruction, double x, double y, double width, double height, Color color) {
        super(isObstruction, x, y, width, height);
        this.setNode(new Rectangle(x, y, width, height));
        ((Shape) this.getNode()).setFill(color);

    }
}
