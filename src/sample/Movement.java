package sample;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import java.util.Optional;


public class Movement {


    public void moveY(Pane gameScreen, long deltaTime, Rectangle rectangle, double velocityY) {
        double proposedMove = velocityY * (deltaTime * 1e-3);
        rectangle.setY(rectangle.getY() + proposedMove);
        checkProposedMoveY(gameScreen, proposedMove, rectangle);
        rectangle.toFront();
    }

    private void checkProposedMoveY(Pane gameScreen, double proposedMove, Rectangle rectangle) {
        Shape intersectedShape = getCollidedShapeIntersection(gameScreen, rectangle);
        rectangle.setY(getNewPosition(Math.signum(proposedMove), rectangle.getY(), ShapeWrapper.getShapeHeight(intersectedShape)));
    }

    public void moveX(Pane gameScreen, long deltaTime, Rectangle rectangle, double velocityX) {
        double proposedMove = velocityX * (deltaTime * 1e-3);
        rectangle.setX(rectangle.getX() + proposedMove);
        checkProposedMoveX(gameScreen, proposedMove, rectangle);
        rectangle.toFront();
    }

    private void checkProposedMoveX(Pane gameScreen, double proposedMove, Rectangle rectangle) {
        Shape intersectedShape = getCollidedShapeIntersection(gameScreen, rectangle);
        rectangle.setX(getNewPosition(Math.signum(proposedMove), rectangle.getX(), ShapeWrapper.getShapeWidth(intersectedShape)));
    }


    private double getNewPosition(double sign, double movingShape1DPosition, double collidedShapeIntersection) {
        return movingShape1DPosition - getCollisionOffset(sign, collidedShapeIntersection);
    }

    private double getCollisionOffset(double sign, double collidedShape1DSize) {
        return Double.compare(collidedShape1DSize, 0) == 0 ? 0 : sign * (collidedShape1DSize + 1);
    }

    private Shape getCollidedShapeIntersection(Pane gameScreen, Rectangle rectangle) {
        Optional<Node> collidedNode = gameScreen.getChildren()
                .stream()
                .filter(node -> isCollision(node, rectangle))
                .findFirst();
        return collidedNode.isPresent() ?
                Shape.intersect(rectangle, (Shape) collidedNode.get())
                : new Rectangle(0, 0);
    }

    private boolean isCollision(Node node, Rectangle rectangle) {
        return !rectangle.equals(node) && rectangle.getBoundsInParent().intersects(node.getBoundsInParent());
    }

}
