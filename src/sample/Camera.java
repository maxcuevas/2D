package sample;

import javafx.scene.layout.Pane;

public class Camera {

    private double deltaX;
    private double deltaY;

    private final double offsetX;
    private final double offsetY;


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


        deltaX = -player.getX();
        deltaY = -player.getY();

        player.getView().setTranslateX(gameScreen.getWidth() / 2);

        player.getView().setTranslateY(gameScreen.getHeight() / 2);

        for (MapChunk mapChunk : map.mapChunks) {

            for (int currentObstruction = 0; currentObstruction < mapChunk.getBiomeSize(); currentObstruction++) {
                mapChunk.getTile(currentObstruction).getNode().setTranslateX(mapChunk.getTile(currentObstruction).getBounds().getX() + offsetX + deltaX);
                mapChunk.getTile(currentObstruction).getNode().setTranslateY(mapChunk.getTile(currentObstruction).getBounds().getY() + offsetY + deltaY);
            }
//            for (Obstruction obstruction : mapChunk) {
//                obstruction.getNode().setTranslateX(obstruction.getBounds().getX() + offsetX + deltaX);
//                obstruction.getNode().setTranslateY(obstruction.getBounds().getY() + offsetY + deltaY);
//            }
        }

    }


}
