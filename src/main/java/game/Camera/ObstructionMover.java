package game.Camera;

import game.Map.Obstruction;
import javafx.scene.Node;

public class ObstructionMover {

    public void move(Obstruction obstruction, Double offsetAndDeltaY, Double offsetAndDeltaX) {
        moveX(obstruction, offsetAndDeltaX);
        moveY(obstruction, offsetAndDeltaY);
    }

    private void moveY(Obstruction obstruction, Double offsetAndDeltaY) {
        obstruction.getNode().setTranslateY(
                obstruction.getBounds().getY() + offsetAndDeltaY);
    }

    private void moveX(Obstruction obstruction, Double offsetAndDeltaX) {
        obstruction.getNode().setTranslateX(
                obstruction.getBounds().getX() + offsetAndDeltaX);
    }


}
