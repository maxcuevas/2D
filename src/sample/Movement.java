package sample;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;


public class Movement {


    public double getNewPositionY(Map map, long deltaTime, Rectangle2D.Double bounds, double velocityY) {
        double proposedMove = velocityY * (deltaTime * 1e-3);
        return checkProposedMoveY(map, proposedMove, bounds);
    }

    private double checkProposedMoveY(Map map, double proposedMove, Rectangle2D.Double bounds) {
        Rectangle2D.Double tmp = new Rectangle2D.Double(bounds.getX(),
                bounds.getY() + proposedMove, bounds.getWidth(), bounds.getHeight());

        return getCollidedShapeIntersection(map, tmp) ? bounds.getY() : tmp.getY();
    }

    public double getNewPositionX(Map map, long deltaTime, Rectangle2D.Double bounds, double velocityX) {
        double proposedMove = velocityX * (deltaTime * 1e-3);
        return checkProposedMoveX(map, proposedMove, bounds);
    }

    private double checkProposedMoveX(Map map, double proposedMove, Rectangle2D.Double bounds) {
        Rectangle2D.Double tmp = new Rectangle2D.Double(bounds.getX() + proposedMove,
                bounds.getY(), bounds.getWidth(), bounds.getHeight());

        return getCollidedShapeIntersection(map, tmp) ? bounds.getX() : tmp.getX();

//        Shape intersectedShape = getCollidedShapeIntersection(map, tmp);
//        bounds.add(getNewPosition(Math.signum(proposedMove), bounds.getX(), ShapeWrapper.getShapeWidth(intersectedShape)), position.getY());
//        node.setTranslateX(node.getTranslateX() - proposedMove);

        //now shift everything so that the player is always at dead center

//        for (ArrayList<Obstruction> mapRow : map.map) {
//            for (Obstruction obstruction : mapRow) {
//                obstruction.getNode().setTranslateX(
////                        getNewPosition(Math.signum(proposedMove), obstruction.getNode().getTranslateX(), ShapeWrapper.getShapeWidth(intersectedShape)));
//                                obstruction.getNode().getTranslateX() - proposedMove - getCollisionOffset(Math.signum(proposedMove), ShapeWrapper.getShapeWidth(intersectedShape)));
//            }
//        }

    }


    private boolean getCollidedShapeIntersection(Map map, Rectangle2D.Double bounds) {

        for (ArrayList<Obstruction> row : map.map) {
            for (Obstruction obstruction : row) {
                if (isCollision(obstruction, bounds)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean isCollision(Obstruction obstruction, Rectangle2D.Double bounds) {
        return !obstruction.getBounds().equals(bounds) && obstruction.getObstruction() && obstruction.getBounds().intersects(bounds);
    }

}
