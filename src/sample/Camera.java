package sample;

import javafx.scene.layout.Pane;

import java.util.ArrayList;

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

        for (ArrayList<Obstruction> mapRow : map.obstructions) {
            for (Obstruction obstruction : mapRow) {
                obstruction.getNode().setTranslateX(obstruction.getBounds().getX() + offsetX + deltaX);
                obstruction.getNode().setTranslateY(obstruction.getBounds().getY() + offsetY + deltaY);
            }
        }

    }


}
