package sample;

import javafx.scene.Node;

public class Obstruction {

    private boolean isObstruction;
    private Node node;

    public Obstruction(boolean isObstruction) {
        this.isObstruction = isObstruction;
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
}
