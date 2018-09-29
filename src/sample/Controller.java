package sample;

import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.util.HashSet;
import java.util.Set;


public class Controller {

    public Canvas gameCanvas;

    public Button startButton;


    public void handleClick(MouseEvent mouseEvent) {


        Thread thread = new Thread(new testRunnable(gameCanvas));

        thread.start();


    }

    public void handleKeyDown(KeyEvent keyEvent) {


        startButton.setText(keyEvent.getText());
        testRunnable.keysDown.add(keyEvent.getText());
    }

    public void handleKeyUp(KeyEvent keyEvent) {
        testRunnable.keysDown.remove(keyEvent.getText());
    }
}
