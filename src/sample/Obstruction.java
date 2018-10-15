package sample;

import javafx.scene.Node;

import java.awt.geom.Rectangle2D;


public class Obstruction {

    private boolean isObstruction;
    private Rectangle2D.Double bounds;
    private Node node;

    public Obstruction(boolean isObstruction, double x, double y, double width, double height) {
        this.isObstruction = isObstruction;
        bounds = new Rectangle2D.Double(x, y, width, height);
    }

    public boolean getObstruction() {
        return isObstruction;
    }


    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    public void setBounds(double x, double y, double width, double height) {
        bounds = new Rectangle2D.Double(x, y, width, height);
    }

    public Rectangle2D.Double getBounds() {
        return bounds;
    }


}
