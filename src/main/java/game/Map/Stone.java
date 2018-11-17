package game.Map;

import javafx.scene.paint.Color;


public class Stone extends Obstruction {
    public Stone(boolean isObstruction, double x, double y, double width, double height, Color color) {
        super(isObstruction, x, y, width, height);


//        this.setNode(new Circle(x, y, radius));
//        ((Shape) this.getNode()).setFill(color);
    }

}
