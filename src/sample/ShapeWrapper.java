package sample;

import javafx.scene.shape.Shape;

public class ShapeWrapper {

    public static double getShapeWidth(Shape shape) {
        return shape.getBoundsInParent().getWidth();
    }

    public static double getShapeHeight(Shape shape) {
        return shape.getBoundsInParent().getHeight();
    }
}
