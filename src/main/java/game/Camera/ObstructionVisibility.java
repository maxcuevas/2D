package game.Camera;

import javafx.scene.Node;

public class ObstructionVisibility {

    public void setVisibility(Node node, Double gameScreenWidth, Double gameScreenHeight) {
        if (isVisible(node,gameScreenWidth,gameScreenHeight)) {
            node.setVisible(false);
        } else {
            node.setVisible(true);
        }
    }

    private boolean isVisible(Node node, Double gameScreenWidth, Double gameScreenHeight) {
        return node.getBoundsInParent().getMinX() < 0 ||
                gameScreenWidth < node.getBoundsInParent().getMaxX() ||
                node.getBoundsInParent().getMinY() < 0 ||
                gameScreenHeight < node.getBoundsInParent().getMaxY();
    }
}
