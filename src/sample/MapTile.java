package sample;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class MapTile {

    private Obstruction obstruction = new Obstruction(true);
    Node mapTile;


    public MapTile(double width, double height, Color color) {
        mapTile = new Rectangle(width, height, color);
    }

    public Obstruction getObstruction() {
        return obstruction;
    }

}
