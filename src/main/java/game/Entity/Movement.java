package game.Entity;

import game.Map.Map;
import game.Map.MapChunk;
import game.Map.MapChunkData;
import game.Map.Obstruction;

import java.awt.geom.Rectangle2D;
import java.util.stream.IntStream;


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


    }


    private boolean getCollidedShapeIntersection(Map map, Rectangle2D.Double bounds) {
       return map.mapChunks
                .stream()
                .anyMatch(mapChunk -> checkMapChunkForCollision(bounds, mapChunk));

    }

    private boolean checkMapChunkForCollision(Rectangle2D.Double bounds, MapChunk mapChunk) {
        return IntStream
                .range(0, mapChunk.getBiomeSize())
                .anyMatch(count -> isCollision(mapChunk.getTile(count), bounds));
    }

    private boolean isCollision(Obstruction obstruction, Rectangle2D.Double bounds) {
        return !obstruction.getBounds().equals(bounds) &&
                obstruction.isObstruction() &&
                obstruction.getBounds().intersects(bounds);
    }

}
