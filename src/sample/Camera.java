package sample;

import javafx.scene.Node;
import javafx.scene.layout.Pane;

public class Camera {

    private final double offsetX;
    private final double offsetY;
    private double deltaX;
    private double deltaY;
    private Pane gameScreen;

    public Camera(Pane gameScreen, Player player) {
        this.gameScreen = gameScreen;

        double playerX = player.getView().getTranslateX();
        double playerY = player.getView().getTranslateY();

        double gameScreenMidX = gameScreen.getWidth() / 2;
        double gameScreenMidY = gameScreen.getHeight() / 2;

        offsetX = gameScreenMidX - playerX;
        offsetY = gameScreenMidY - playerY;
    }


    public void updateCamera(Player player, Map map) {
        getPlayerDeltas(player);
        fixPlayerToCenter(player);
        drawMap(map);
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
        player.getView().setTranslateX(gameScreen.getWidth() / 2);
        player.getView().setTranslateY(gameScreen.getHeight() / 2);
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
        return node.getTranslateX() < 0 ||
                node.getTranslateY() < 0 ||
                node.getTranslateX() > gameScreen.getWidth() ||
                node.getTranslateY() > gameScreen.getHeight();
    }


}
