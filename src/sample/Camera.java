package sample;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;

public class Camera {

    private static double offsetX;
    private static double offsetY;
    private static double deltaX;
    private static double deltaY;
    private static double gameScreenWidth;
    private static double gameScreenHeight;
    private Rectangle border;


    public Camera(Pane gameScreen, Player player) {
        gameScreenWidth = gameScreen.getWidth();
        gameScreenHeight = gameScreen.getHeight();
        offsetX = getOffset(gameScreenWidth, player.getView().getTranslateX());
        offsetY = getOffset(gameScreenHeight, player.getView().getTranslateY());

        border = new Rectangle(0, 0, gameScreenWidth, gameScreenHeight);
        border.setFill(Color.TRANSPARENT);
        border.setStrokeType(StrokeType.INSIDE);
        border.setStroke(Color.WHITE);
        border.setStrokeWidth(20);

        gameScreen.getChildren().add(border);

    }


    private double getOffset(double gameScreenSize, double playerPosition) {
        return (gameScreenSize / 2) - playerPosition;
    }


    public void updateCamera(Player player, Map map) {
        getPlayerDeltas(player);
        fixPlayerToCenter(player);
        drawMap(map);
        border.toFront();
    }

    private void drawMap(Map map) {
        for (MapChunk mapChunk : map.mapChunks) {
            for (Obstruction obstruction : mapChunk.getChunk()) {
                moveObstruction(obstruction);
            }
        }
    }

    private void getPlayerDeltas(Player player) {
        deltaX = -player.getX();
        deltaY = -player.getY();
    }

    private void fixPlayerToCenter(Player player) {
        player.getView().setTranslateX(gameScreenWidth / 2);
        player.getView().setTranslateY(gameScreenHeight / 2);
    }

    private void moveObstruction(Obstruction obstruction) {
        moveObstructionX(obstruction);
        moveObstructionY(obstruction);
        setVisibility(obstruction.getNode());
    }

    private void moveObstructionY(Obstruction obstruction) {
        obstruction.getNode().setTranslateY(
                obstruction.getBounds().getY() + offsetY + deltaY);
    }

    private void moveObstructionX(Obstruction obstruction) {
        obstruction.getNode().setTranslateX(
                obstruction.getBounds().getX() + offsetX + deltaX);
    }

    private void setVisibility(Node node) {
        if (isVisible(node)) {
            node.setVisible(false);
        } else {
            node.setVisible(true);
        }
    }


    private boolean isVisible(Node node) {
        return node.getBoundsInParent().getMinX() < 0 ||
                node.getBoundsInParent().getMinY() < 0 ||
                node.getBoundsInParent().getMaxX() > gameScreenWidth ||
                node.getBoundsInParent().getMaxY() > gameScreenHeight;
    }


}
