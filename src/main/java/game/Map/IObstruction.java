package game.Map;

import javafx.scene.Node;

import java.awt.geom.Rectangle2D;

public interface IObstruction {

    boolean isObstruction();
    Rectangle2D.Double getBounds();
    Node getNode();

}
