package sample;

import javafx.scene.layout.Pane;

public class Camera {

    //this class should be completely tied to the pane that shows all of the pretty pictures


    private double deltaX;
    private double deltaY;


    private Pane gameScreen;

    public Camera(Pane gameScreen, Player player) {
        this.gameScreen = gameScreen;

        double playerX = player.view.getTranslateX();
        double playerY = player.view.getTranslateY();

        double gameScreenMidX = gameScreen.getWidth() / 2;
        double gameScreenMidY = gameScreen.getHeight() / 2;

        deltaX = gameScreenMidX - playerX;
        deltaY = gameScreenMidY - playerY;
    }

    //need a function that will let adjust centerXY to the center of the center of the gamescreen

    public void updateCamera(Player player) {
        player.view.setTranslateX(player.view.getTranslateX() + deltaX);
        player.view.setTranslateY(player.view.getTranslateY() + deltaY);
    }


}
