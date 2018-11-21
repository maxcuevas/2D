package game;

import game.Entity.Player;
import game.Map.IObstruction;
import game.Map.Map;
import game.Map.MapChunk;
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
    private Pane gameScreen;


    public Camera(Pane gameScreen, Player player) {
        this.gameScreen = gameScreen;
        setGameScreenSizes(gameScreen);
        setOffsets(player);
        border = createBorder();
        gameScreen.getChildren().add(border);

    }

    private void setGameScreenSizes(Pane gameScreen) {
        gameScreenWidth = gameScreen.getWidth();
        gameScreenHeight = gameScreen.getHeight();
    }

    private void setOffsets(Player player) {
        offsetX = getOffset(gameScreenWidth, player.getView().getTranslateX());
        offsetY = getOffset(gameScreenHeight, player.getView().getTranslateY());
    }

    private Rectangle createBorder() {
        Rectangle border = new Rectangle(0, 0, gameScreenWidth, gameScreenHeight);
        border.setFill(Color.TRANSPARENT);
        border.setStrokeType(StrokeType.INSIDE);
        border.setStroke(Color.WHITE);
        border.setStrokeWidth(20);
        border.setMouseTransparent(true);

        return border;
    }


    private double getOffset(double gameScreenSize, double playerPosition) {
        return (gameScreenSize / 2) - playerPosition;
    }


    public void updateCamera(Player player, Map map, boolean mapChange) {
        getPlayerDeltas(player);
        fixPlayerToCenter(player);
        if (mapChange) {
            map.render(gameScreen);
        }
        drawMap(map);
        border.toFront();
    }

    private void drawMap(Map map) {
        for (MapChunk mapChunk : map.mapChunks) {
            for (IObstruction iObstruction : mapChunk.getChunk()) {
                moveObstruction(iObstruction);
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

    private void moveObstruction(IObstruction iObstruction) {
        moveObstructionX(iObstruction);
        moveObstructionY(iObstruction);
        setVisibility(iObstruction.getNode());
    }

    private void moveObstructionY(IObstruction iObstruction) {
        iObstruction.getNode().setTranslateY(
                iObstruction.getBounds().getY() + offsetY + deltaY);
    }

    private void moveObstructionX(IObstruction iObstruction) {
        iObstruction.getNode().setTranslateX(
                iObstruction.getBounds().getX() + offsetX + deltaX);
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
