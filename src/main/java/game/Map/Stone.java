package game.Map;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.awt.geom.Rectangle2D;


public class Stone {

    ObstructionData obstructionData;

    public Stone(boolean isObstruction, double x, double y, double width, double height, Color color) {

        obstructionData = new ObstructionData(false,
                new Rectangle2D.Double(x, y, width, height),
                new Rectangle(x, y, width, height));

//        this.setNode(new Circle(x, y, radius));
//        ((Shape) this.getNode()).setFill(color);
    }

}
