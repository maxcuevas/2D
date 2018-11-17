package game;

import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;


public class Controller {

    public Pane gameScreen;
    public Button startButton;
    private GameLoop gameLoop = new GameLoop();
    public Pane mainPane;


    public void handleClick(MouseEvent mouseEvent) {
        if (!gameLoop.isRunning()) {
            gameLoop.setRunning(true);
            gameLoop.setGameScreen(gameScreen);
            gameLoop.start();
            gameLoop.handle(System.currentTimeMillis());
        }
    }

    public void handleKeyDown(KeyEvent keyEvent) {
        gameLoop.readInput(keyEvent.getCode());
    }

    public void handleKeyUp(KeyEvent keyEvent) {
        gameLoop.clearInput(keyEvent.getCode());
    }
}
