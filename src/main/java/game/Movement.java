package game;

import java.awt.geom.Rectangle2D;


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

//        Shape intersectedShape = getCollidedShapeIntersection(mapChunks, tmp);
//        bounds.add(getNewPosition(Math.signum(proposedMove), bounds.getX(), game.ShapeWrapper.getShapeWidth(intersectedShape)), position.getY());
//        node.setTranslateX(node.getTranslateX() - proposedMove);

        //now shift everything so that the player is always at dead center

//        for (ArrayList<game.Obstruction> mapRow : mapChunks.mapChunks) {
//            for (game.Obstruction obstruction : mapRow) {
//                obstruction.getNode().setTranslateX(
////                        getNewPosition(Math.signum(proposedMove), obstruction.getNode().getTranslateX(), game.ShapeWrapper.getShapeWidth(intersectedShape)));
//                                obstruction.getNode().getTranslateX() - proposedMove - getCollisionOffset(Math.signum(proposedMove), game.ShapeWrapper.getShapeWidth(intersectedShape)));
//            }
//        }

    }


    private boolean getCollidedShapeIntersection(Map map, Rectangle2D.Double bounds) {

        for (MapChunk mapChunk : map.mapChunks) {

            for (int currentObstruction = 0; currentObstruction < mapChunk.getBiomeSize(); currentObstruction++) {
                if (isCollision(mapChunk.getTile(currentObstruction), bounds)){
                    return true;
                }
            }

//            for (game.Obstruction obstruction : mapChunk) {
//                if (isCollision(obstruction, bounds)) {
//                    return true;
//                }
//            }
        }

        return false;
    }

    private boolean isCollision(Obstruction obstruction, Rectangle2D.Double bounds) {
        return !obstruction.getBounds().equals(bounds) && obstruction.getObstruction() && obstruction.getBounds().intersects(bounds);
    }

}
