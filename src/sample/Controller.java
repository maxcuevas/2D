package sample;

import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;


public class Controller {

    public Canvas gameCanvas;
    public Button startButton;
    private GameLoop gameLoop = new GameLoop();

    public void handleClick(MouseEvent mouseEvent) {
        if (!gameLoop.isRunning()){
            gameLoop.setRunning(true);
            gameLoop.start();
            gameLoop.setCanvas(gameCanvas);
        }

    }

    public void handleKeyDown(KeyEvent keyEvent) {
        gameLoop.readInput(keyEvent.getText());
    }

    public void handleKeyUp(KeyEvent keyEvent) {
        gameLoop.clearInput(keyEvent.getText());
    }
}
