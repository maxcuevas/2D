package sample;

import javafx.scene.Node;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import java.util.ArrayList;


public class Movement {


    public void moveY(Map map, long deltaTime, Node node, double velocityY) {
        double proposedMove = velocityY * (deltaTime * 1e-3);
        node.setTranslateY(node.getTranslateY() + proposedMove);
        checkProposedMoveY(map, proposedMove, node);
        node.toFront();
    }

    private void checkProposedMoveY(Map map, double proposedMove, Node node) {
        Shape intersectedShape = getCollidedShapeIntersection(map, node);
        node.setTranslateY(getNewPosition(Math.signum(proposedMove), node.getTranslateY(), ShapeWrapper.getShapeHeight(intersectedShape)));
    }

    public void moveX(Map map, long deltaTime, Node node, double velocityX) {
        double proposedMove = velocityX * (deltaTime * 1e-3);
        node.setTranslateX(node.getTranslateX() + proposedMove);
        checkProposedMoveX(map, proposedMove, node);
        node.toFront();
    }

    private void checkProposedMoveX(Map map, double proposedMove, Node node) {
        Shape intersectedShape = getCollidedShapeIntersection(map, node);
        node.setTranslateX(getNewPosition(Math.signum(proposedMove), node.getTranslateX(), ShapeWrapper.getShapeWidth(intersectedShape)));
    }


    private double getNewPosition(double sign, double movingShape1DPosition, double collidedShapeIntersection) {
        return movingShape1DPosition - getCollisionOffset(sign, collidedShapeIntersection);
    }

    private double getCollisionOffset(double sign, double collidedShape1DSize) {
        return Double.compare(collidedShape1DSize, 0) == 0 ? 0 : sign * (collidedShape1DSize + 1);
    }

    private Shape getCollidedShapeIntersection(Map map, Node node) {

        for (ArrayList<Obstruction> row : map.map) {

            for (Obstruction mapTile : row) {

                if (isCollision(mapTile, node)) {
                    return Shape.intersect((Shape) node, (Shape) mapTile.getNode());

                }
            }
        }

//        Optional<Node> collidedNode = map.getChildren()
//                .stream()
//                .filter(node -> isCollision(node, rectangle))
//                .findFirst();
//        return collidedNode.isPresent() ?
//                Shape.intersect(rectangle, (Shape) collidedNode.get())
//                : new Node(0, 0);
        return new Rectangle(0, 0);
    }

    private boolean isCollision(Obstruction obstruction, Node node2) {
        return !obstruction.equals(node2) && obstruction.getObstruction() && obstruction.getNode().getBoundsInParent().intersects(node2.getBoundsInParent());
    }

}
